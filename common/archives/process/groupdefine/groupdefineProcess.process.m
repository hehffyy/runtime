<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <process name="groupdefineProcess" kind="SYSTEM"> 
    <label language="zh_CN">groupdefine</label>  
    <static-activity name="mainActivity"> 
      <label language="zh_CN">任务中心业务分组</label> 
    </static-activity>  
      
      
      
      
      
      
      
      
      
      
      
      
    <static-activity name="archivesActivity">
      <label language="zh_CN">案卷查询业务分组</label> 
    </static-activity> 
  




























<has-action action="queryB_BusinessGroupAction" access-permission="public"></has-action>
<has-action action="saveB_BusinessGroupAction" access-permission="public"></has-action>
<has-action action="createB_BusinessGroupAction" access-permission="public"></has-action>
<has-action action="queryB_GroupFuncAction" access-permission="public"></has-action>
<has-action action="saveB_GroupFuncAction" access-permission="public"></has-action>
<has-action action="createB_GroupFuncAction" access-permission="public"></has-action>
<has-action action="queryB_GroupFieldAction" access-permission="public"></has-action>
<has-action action="saveB_GroupFieldAction" access-permission="public"></has-action>
<has-action action="createB_GroupFieldAction" access-permission="public"></has-action>
<has-action action="query_BusinessTablesAction" access-permission="public"></has-action>
<has-action action="setTreeFucnAction" access-permission="public"></has-action>
<has-action action="getBusinessTablesAction" access-permission="public"></has-action>
<has-action action="querySysGroupFieldAction" access-permission="public"></has-action>
<has-action action="queryB_GroupDataPermissionAction" access-permission="public"></has-action>
<has-action action="saveB_GroupDataPermissionAction" access-permission="public"></has-action>
<has-action action="createB_GroupDataPermissionAction" access-permission="public"></has-action>
<static-activity name="mobileActivity"><label language="zh_CN">任务中心业务分组（mobile）</label>
</static-activity>
</process> 
</model>
