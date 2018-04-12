create or replace view v_deptrec as
with deptRec1 as (select distinct t.sdata1 as fBizRecID,t.sexecutordeptid as fDeptID,
  case when t.sstatusid in ('tesExecuting','tesPaused','tesReady') then 1 else 0 end as fisinner
  from  SA_Task t  where t.sstatusid not in ('tesCanceled') and sExecutordeptID is not null
and not exists (select 1 from b_bjjlb b where b.fbizrecid = t.sdata1)  )

select fbizrecid,fdeptid,sum(fisinner) fisinner from deptRec1 group by fbizrecid,fdeptid;
