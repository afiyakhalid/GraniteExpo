create table if not exists outbox_events (
  id uuid primary key default gen_random_uuid(),

  -- aggregate = what business object produced the event
  aggregate_type text not null,       -- e.g. 'order'
  aggregate_id uuid not null,         -- e.g. orderId

  -- event_type = what happened
  event_type text not null,           -- e.g. 'OrderConfirmed'

  -- payload = JSON string for the event
  payload jsonb not null,

  -- lifecycle
  status text not null default 'PENDING', -- PENDING, PUBLISHED, FAILED
  attempts int not null default 0,

  -- for retries/backoff
  next_attempt_at timestamptz not null default now(),

  -- for debugging
  last_error text,

  created_at timestamptz not null default now(),
  published_at timestamptz
);

create index if not exists idx_outbox_status_next_attempt
  on outbox_events(status, next_attempt_at);

create index if not exists idx_outbox_aggregate
  on outbox_events(aggregate_type, aggregate_id);