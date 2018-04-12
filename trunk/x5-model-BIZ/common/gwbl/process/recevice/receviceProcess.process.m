<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <process name="receviceProcess"> 
    <label language="zh_CN">recevice</label>  
    <static-activity name="mainActivity"> 
      <has-action action="queryB_CommonlyUsedAction" access-permission="public"/>  
      <has-action action="saveB_CommonlyUsedAction" access-permission="public"/>  
      <has-action action="createB_CommonlyUsedAction" access-permission="public"/> 
    </static-activity>  
    <has-action action="shoujianAction" access-permission="public"/>  
    <has-action action="queryB_CommonlyUsedAction" access-permission="public"/>  
    <has-action action="saveB_CommonlyUsedAction" access-permission="public"/>  
    <has-action action="createB_CommonlyUsedAction" access-permission="public"/> 
  </process> 
</model>
