<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <process name="recFuncsProcess" kind="SYSTEM"> 
    <label language="zh_CN">案卷相关功能</label>  
    <static-activity name="deptRecActivity"> 
      <label language="zh_CN">部门案卷统计</label> 
    </static-activity>  
    <has-action action="queryBizRecAction" access-permission="public"/>  
    <has-action action="queryMasterRelationAction" access-permission="public"/>  
    <static-activity name="deptProcessOrder"> 
      <label language="zh_CN">部门业务排序</label>  
      <has-action action="queryB_ProcessOrderAction" access-permission="public"/>  
      <has-action action="saveB_ProcessOrderAction" access-permission="public"/>  
      <has-action action="createB_ProcessOrderAction" access-permission="public"/>  
      <has-action action="queryBizProcessAction" access-permission="public"/> 
    </static-activity>  
    <has-action action="queryOPOrgAction" access-permission="public"/> 
  </process> 
</model>
