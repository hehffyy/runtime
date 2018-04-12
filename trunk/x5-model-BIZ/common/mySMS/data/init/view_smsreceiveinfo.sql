create or replace view view_smsreceiveinfo as
select a.fid,a.fpersonid,a.fpersonname,a.fsmsid,a.fphone,b.fsendstate,b.fbackstate,b.fbackcontent,to_char(b.flastbacktime,'yyyy-mm-dd hh24:mi:ss') as flastbacktime,b.ffailedcount,b.fsendchecktime,b.flog
  from B_smsReceivePerson a
  left join TMIS_SMSSENDRECORD b
    on a.fsmsid = b.frequestid and a.fphone=b.fdestaddr;
