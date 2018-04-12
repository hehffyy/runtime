--办结案卷剩余天数
create or replace view v_bizrec_finish as
select a.fbizrecid,b.fsuspenddays,b.flimitdate,fbjsj,
 (case when trunc(flimitdate)>=trunc(fbjsj) then  (select count(*) from b_workdaysmang m where m.fisworkday='是' and FDate between trunc(fbjsj) and trunc(flimitdate))
        - (select count(*) from b_workdaysmang m where m.fisworkday='是' and Fdate=trunc(fbjsj))
        else (select -count(*) from b_workdaysmang m where m.fisworkday='是' and FDate between trunc(flimitdate) and trunc(fbjsj))
        + (select count(*) from b_workdaysmang m where m.fisworkday='是' and Fdate=trunc(flimitdate)) end) fActualRemainingdays,b.fRemainingdays
from b_bizrec b,b_bjjlb a where  a.fbizrecid=b.fbizrecid and b.flimitDate is not null
