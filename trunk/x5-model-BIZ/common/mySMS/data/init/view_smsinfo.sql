create or replace view view_smsinfo as
select a.fid,
       a.fsmscontent,
       a.fsenderpersonid,
       a.fsendername,
       a.fsmstype,
       a.fstatus,
       to_char(b.fsendtime,'yyyy-mm-dd hh24:mi:ss') as fsendtime,
       b.fstate,
       b.fresendcount,
       (case when b.fneedback = 1 then '需要回复' else '无需回复' end) as fneedback,
       b.frequesttype
  from B_smsInfo a
  left join TMIS_SMSSENDTASK b
    on a.fid = b.frequestid;