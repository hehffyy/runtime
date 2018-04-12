<?xml version="1.0" encoding="utf-8" standalone="no"?><model xmlns="http://www.justep.com/model">
<store name="B_SystemUpdateLog"/>  
  <store name="B_sysPara"/>  
  <store name="B_RtCfg"/>  
  <store name="SysDictType"/>  
  <store name="SysDictItem"/>  
  <mapping concept="SysDictItem"> 
    <table name="B_SysDictItem" type="owner-table"> 
      <index fields="FTYPE,FCODE" name="SYS_C0017631" type="UNIQUE"/>  
      <key field="fID" type="String"/> 
    </table> 
  </mapping>  
  <mapping concept="SysDictType"> 
    <table name="B_SysDictType" type="owner-table"> 
      <key field="fID" type="String"/> 
    </table> 
  </mapping>  
  <mapping concept="B_RtCfg">
    <table name="B_RtCfg" type="owner-table">
      <key field="fID" type="String"/>  
      <index fields="fPersonID,fKind" name="IDX_B_RtCfg_fPersonID_fKind" type="UNIQUE"/> 
    </table> 
  </mapping> 
<mapping concept="B_sysPara"><table name="B_sysPara" type="owner-table"><key field="fID" type="String"/>
<index fields="sysName" name="IDX_B_sysPara_Name" type="UNIQUE"/>
</table>
</mapping>
</model>