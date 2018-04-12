<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <process name="activitymappingProcess" kind="SYSTEM"> 
    <label language="zh_CN">activitymapping</label>  
    <static-activity name="mainActivity"></static-activity>  
    <has-action action="queryB_ActivityMappingAction" access-permission="public"/>  
    <has-action action="saveB_ActivityMappingAction" access-permission="public"/>  
    <has-action action="createB_ActivityMappingAction" access-permission="public"/>  
    <has-action action="queryB_V_DJDLAction" access-permission="public"/>  
    <has-action action="queryB_V_QLLXAction" access-permission="public"/>  
    <has-action action="scActivityMappingAction" access-permission="public"/>  
    <has-action action="queryB_V_DJQLLX_DJDLAction" access-permission="public"/>  
    <has-action action="queryB_V_DJQLLX_DJXLAction" access-permission="public"/>  
    <has-action action="queryB_V_DJQLLX_ZMLAction" access-permission="public"/> 
  </process> 
</model>
