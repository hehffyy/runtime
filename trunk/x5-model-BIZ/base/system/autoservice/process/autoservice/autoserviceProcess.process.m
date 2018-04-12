<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <process name="autoserviceProcess" kind="SYSTEM"> 
    <label language="zh_CN">自动服务</label>  
    <static-activity name="mainActivity"> 
      <label language="zh_CN">自动服务</label>  
      <has-action action="syncBizRecRemainingDaysAction" access-permission="public"/>  
      <has-action action="syncActivityGroupRemainingDaysAction" access-permission="public"/>  
      <has-action action="syncActivityRemainingDaysAction" access-permission="public"/> 
    </static-activity> 
  </process> 
</model>
