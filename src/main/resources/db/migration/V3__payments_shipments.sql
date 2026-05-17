do $$
begin
  if not exists (select 1 from pg_type where typname = 'payment_status_enum') then
    create type payment_status_enum as enum ('created','succeeded','failed');
  end if;
end $$;

create table if not exists payments (
  id uuid primary key default gen_random_uuid(),
  order_id uuid not null references orders(id) on delete cascade,

  provider text not null,                 -- "manual"
  provider_reference text not null,       -- idempotency key

  amount numeric not null,
  currency text not null default 'USD',

  status payment_status_enum not null default 'created',
  created_at timestamptz not null default now(),
  confirmed_at timestamptz,

  constraint uq_payments_provider_ref unique (provider, provider_reference)
);

create index if not exists idx_payments_order_id on payments(order_id);