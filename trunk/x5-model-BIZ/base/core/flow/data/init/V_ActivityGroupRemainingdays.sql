--环节分组剩余天数
create or replace view V_ActivityGroupRemainingdays as
select G.fGuid,G.fLimitDate,G.fLimitKind,b.fStatus fBizRecStatus,
case when b.Fstatus='bsSuspended' then t.ffqsj else null end fSuspendTime,
case when b.Fstatus='bsSuspended' then 
(case when trunc(G.flimitdate)>=trunc(t.ffqsj) then  (select count(*) from b_workdaysmang m where m.fisworkday='是' and FDate between trunc(t.ffqsj) and trunc(g.flimitdate))
        - (select count(*) from b_workdaysmang m where m.fisworkday='是' and Fdate=trunc(t.ffqsj))
        else (select -count(*) from b_workdaysmang m where m.fisworkday='是' and FDate between trunc(g.flimitdate) and trunc(t.ffqsj))
        + (select count(*) from b_workdaysmang m where m.fisworkday='是' and Fdate=trunc(g.flimitdate)) end) 
when b.Fstatus='bsProcessing' then
(case when trunc(G.flimitdate)>=trunc(sysdate) then  (select count(*) from b_workdaysmang m where m.fisworkday='是' and FDate between trunc(sysdate) and trunc(g.flimitdate))
        - (select count(*) from b_workdaysmang m where m.fisworkday='是' and Fdate=trunc(sysdate))
        else (select -count(*) from b_workdaysmang m where m.fisworkday='是' and FDate between trunc(g.flimitdate) and trunc(sysdate))
        + (select count(*) from b_workdaysmang m where m.fisworkday='是' and Fdate=trunc(g.flimitdate)) end)   
else 
 G.Fremainingdays
end fActualRemainingdays,g.Fremainingdays
from B_ActivityGroupInstance G
inner join B_BizRec B on B.fBizRecId=G.fBizRecId 
left join b_Ajgqjlb t on t.fbizrecid=b.fbizrecid
where G.fEndTime is null and G.fLimitDate is not null