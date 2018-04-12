<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model" xmlns:m="http://www.justep.com/model">  
  <process name="taskCenterProcess"> 
    <label language="zh_CN">任务中心</label>  
     
    <static-activity name="mainActivity"/>  
  





















<has-action action="queryTaskCenterAction" access-permission="public"></has-action>
<has-action action="queryBizGroupAction" access-permission="public"></has-action>
<has-action action="queryBizGroupTaskAction" access-permission="public"></has-action>
<has-action action="queryB_BatchInfoAction" access-permission="public"><protected name="condition" type="String" value="B_BatchOperation.fCreatorID  = :operatorID()"></protected>
</has-action>
<has-action action="queryBizGroupTableAction" access-permission="public"></has-action>
</process> 
</model>
