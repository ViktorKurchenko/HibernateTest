-- REQUIRED PERMISSIONS:
-- 1. grant SELECT_CATALOG_ROLE
-- 2. grant select on V_$... to ...;

-- WHO LOGGED IN
select username, osuser, program, module, machine, terminal, process,
       to_char(logon_time, 'YYYY-MM-DD HH24:MI:ss') as logon_time, status
from v$session
where type = 'USER';

-- CONSUMING RESOURCES
select s.username, s.osuser, s.program, s.module, s.machine, s.terminal, s.process,
       to_char(s.logon_time, 'YYYY-MM-DD HH24:MI:ss') as logon_time, s.status,
       to_char(m.begin_time, 'HH24:MI:ss') as begin_time,
       to_char(m.end_time, 'HH24:MI:ss') as end_time,
       m.cpu, m.pga_memory, m.logical_reads, m.physical_reads,
       m.hard_parses, m.soft_parses
from v$session s
join v$sessmetric m on m.session_id = s.sid
where type = 'USER'
order by m.cpu desc;

-- CURRENTLY RUNNING SQLs
select s.sid, s.username, s.osuser, s.program, s.module, s.machine, s.terminal, s.process,
       q.sql_text, q.optimizer_cost,
       s.blocking_session, bs.username as blocking_user,
       bs.machine as blocking_machine,
       bq.sql_text as blocking_sql,
       s.event as wait_event,
       q.sql_fulltext
from v$session s
join v$sql q on q.sql_id = s.sql_id
left outer join v$session bs on s.blocking_session = bs.sid
left outer join v$sql bq on bq.sql_id = bs.sql_id
where s.type = 'USER';

-- STATEMENTS CONSUME THE MOST RESOURCES
select *
from (
  select sql_id, sql_text, executions, elapsed_time, cpu_time, buffer_gets, disk_reads
  from v$sqlstats
  where executions > 0
  order by elapsed_time / executions desc
)
where rownum <= 25;

-- FULL SCAN STATEMENTS
select pl.object_owner, pl.object_name, pl.sql_id, q.sql_text, q.module,
       pl.operation, pl.options, pl.cost, pl.cpu_cost, pl.io_cost, q.executions
from v$sql_plan pl
join v$sql q on pl.sql_id = q.sql_id
where (pl.operation = 'TABLE ACCESS' and pl.options = 'FULL') or
      (pl.operation = 'INDEX' and pl.options = 'FAST FULL SCAN') or
      (pl.operation = 'INDEX' and pl.options = 'FULL SCAN (MIN/MAX)') or
      (pl.operation = 'INDEX' and pl.options = 'FULL SCAN')
order by pl.object_owner, pl.object_name;

-- GRAB EXECUTION PLAN FOR STATEMENT
select plan_table_output
from table(dbms_xplan.display_cursor('SQL_ID', null, 'typical'));
