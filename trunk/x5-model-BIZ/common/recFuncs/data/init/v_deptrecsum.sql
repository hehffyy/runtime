create or replace view v_deptrecsum as
with rec as (
select  b.fbizrecid,b.fbizname,b.fprocess,b.fstatus,g.fgqlx,b.fremainingdays
 from b_bizrec b left join b_ajgqjlb g  on b.fBizRecID=g.fbizrecid and g.fjssj is null
)
select case when p.fprocessorder is not null then p.fprocessorder else 9999 end as fprocessorder,d.fdeptid,rec.fbizname,
count(1) FCount,
sum(case when rec.fgqlx is null and rec.fstatus='bsProcessing' and d.fisinner=1 and (rec.fremainingdays>5 or rec.fremainingdays is null) then 1 else 0 end ) FInZcCount,
sum(case when rec.fgqlx is null and rec.fstatus='bsProcessing' and d.fisinner=1 and rec.fremainingdays<=5 and rec.fremainingdays>=2 then 1 else 0 end ) FInYjCount,
sum(case when rec.fgqlx is null and rec.fstatus='bsProcessing' and d.fisinner=1 and rec.fremainingdays<2 and rec.fremainingdays>=0 then 1 else 0 end ) FInYellowCount,
sum(case when rec.fgqlx is null and rec.fstatus='bsProcessing' and d.fisinner=1 and rec.fremainingdays<0 then 1 else 0 end ) FInRedCount,
sum(case when rec.fstatus='bsSuspended' and rec.fgqlx='skApprize' then 1 else 0 end) FApprizeCount,
sum(case when rec.fstatus='bsSuspended' and rec.fgqlx='skSpecialProcedure' then 1 else 0 end) FSpecialProcedureCount,
sum(case when rec.fstatus='bsSuspended' and rec.fgqlx in ('skSubmit','skSuspend') then 1 else 0 end) FQtGqCount,
sum(case when rec.fgqlx is null and rec.fstatus='bsProcessing' and d.fisinner=0 and (rec.fremainingdays>5 or rec.fremainingdays is null) then 1 else 0 end ) FOutZcCount,
sum(case when rec.fgqlx is null and rec.fstatus='bsProcessing' and d.fisinner=0 and rec.fremainingdays<=5 and rec.fremainingdays>=2 then 1 else 0 end ) FOutYjCount,
sum(case when rec.fgqlx is null and rec.fstatus='bsProcessing' and d.fisinner=0 and rec.fremainingdays<2 and rec.fremainingdays>=0 then 1 else 0 end ) FOUtYellowCount,
sum(case when rec.fgqlx is null and rec.fstatus='bsProcessing' and d.fisinner=0 and rec.fremainingdays<0 then 1 else 0 end ) FOutRedCount,
sum(case when rec.fgqlx is null and rec.fstatus in('bsFinished','bsAborted') then 1 else 0 end ) FFinsishCount
from V_DeptRec d
join rec  on d.fBizRecID=rec.fbizrecid
left join B_ProcessOrder p
on d.fDeptID=p.fdeptid and rec.fprocess=p.fprocess
group by d.fDeptID,rec.fbizname,p.fprocessorder
order by p.fprocessorder asc
