
create extension if not exists "pgcrypto";


do $$
begin
  if not exists (select 1 from pg_type where typname = 'role_enum') then
    create type role_enum as enum ('admin', 'buyer', 'staff');
  end if;

  if not exists (select 1 from pg_type where typname = 'block_status_enum') then
    create type block_status_enum as enum (
      'available',
      'reserved',
      'sold',
      'on_hold',
      'damaged'
    );
  end if;

  if not exists (select 1 from pg_type where typname = 'order_status_enum') then
    create type order_status_enum as enum (
      'draft',
      'pending_payment',
      'confirmed',
      'cancelled'
    );
  end if;
end $$;

-- -----------------------------
-- Users (simple local auth version)
-- If later you switch to Supabase Auth, you can replace this with a profiles table.
-- -----------------------------
create table if not exists users (
  id uuid primary key default gen_random_uuid(),
  email text not null unique,
  password_hash text not null,
  full_name text,
  phone text,
  role role_enum not null default 'buyer',
  created_at timestamptz not null default now()
);

-- -----------------------------
-- Vendor (single vendor is fine, still model it)
-- -----------------------------
create table if not exists vendors (
  id uuid primary key default gen_random_uuid(),
  name text not null,
  contact_email text,
  contact_phone text,
  address_jsonb jsonb,
  is_active boolean not null default true,
  created_at timestamptz not null default now()
);

-- -----------------------------
-- Quarries
-- -----------------------------
create table if not exists quarries (
  id uuid primary key default gen_random_uuid(),
  vendor_id uuid not null references vendors(id),
  name text not null,
  state text,
  district text,
  created_at timestamptz not null default now(),
  unique (vendor_id, name)
);

-- -----------------------------
-- Granites (varieties)
-- -----------------------------
create table if not exists granites (
  id uuid primary key default gen_random_uuid(),
  vendor_id uuid not null references vendors(id),
  name text not null,
  slug text not null unique,
  description text,
  created_at timestamptz not null default now()
);

-- -----------------------------
-- Blocks (inventory, unique items)
-- -----------------------------
create table if not exists blocks (
  id uuid primary key default gen_random_uuid(),
  vendor_id uuid not null references vendors(id),
  granite_id uuid not null references granites(id),
  quarry_id uuid not null references quarries(id),

  block_code text not null unique,

  length_cm numeric,
  width_cm numeric,
  height_cm numeric,
  volume_m3 numeric,
  weight_ton numeric,

  grade text,
  extraction_date date,
  notes text,

  price numeric,
  currency text not null default 'USD',

  status block_status_enum not null default 'available',
  reserved_until timestamptz,

  created_at timestamptz not null default now()
);

create index if not exists idx_blocks_status on blocks(status);
create index if not exists idx_blocks_status_granite on blocks(status, granite_id);
create index if not exists idx_blocks_quarry on blocks(quarry_id);
create index if not exists idx_blocks_vendor_created on blocks(vendor_id, created_at desc);

-- -----------------------------
-- Orders
-- -----------------------------
create table if not exists orders (
  id uuid primary key default gen_random_uuid(),
  order_number text not null unique,

  buyer_id uuid not null references users(id),
  vendor_id uuid not null references vendors(id),

  status order_status_enum not null default 'draft',

  created_at timestamptz not null default now(),
  confirmed_at timestamptz,

  -- snapshots (optional but good)
  buyer_company_name text,
  buyer_contact_name text,
  buyer_email text,
  buyer_phone text,

  billing_address jsonb,
  shipping_address jsonb,

  currency text not null default 'USD',
  subtotal numeric not null default 0,
  total numeric not null default 0
);

create index if not exists idx_orders_buyer_created on orders(buyer_id, created_at desc);
create index if not exists idx_orders_status_created on orders(status, created_at desc);

-- -----------------------------
-- Order Items//stickers
-- CRITICAL: UNIQUE(block_id) => "one block can be sold once" guarantee.
-- -----------------------------
create table if not exists order_items (
  id uuid primary key default gen_random_uuid(),
  order_id uuid not null references orders(id) on delete cascade,
  block_id uuid not null references blocks(id),

  quantity int not null default 1,
  unit_price numeric not null default 0,
  line_total numeric not null default 0,

  created_at timestamptz not null default now(),

  constraint uq_order_items_block unique (block_id)
);

create index if not exists idx_order_items_order on order_items(order_id);




create or replace view v_block_reservations as
select
  b.id as block_id,
  b.block_code,
  b.status,
  b.reserved_until,
  oi.order_id
from blocks b
left join order_items oi on oi.block_id = b.id;