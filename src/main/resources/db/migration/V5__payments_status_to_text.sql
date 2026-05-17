-- 1) Convert enum -> text
alter table payments
  alter column status type text
  using status::text;

-- 2) Add check constraint only if it doesn't already exist
do $$
begin
  if not exists (
    select 1
    from pg_constraint
    where conname = 'payments_status_chk'
  ) then
    alter table payments
      add constraint payments_status_chk
      check (status in ('created','succeeded','failed'));
  end if;
end $$;