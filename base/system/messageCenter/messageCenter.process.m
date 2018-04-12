<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <process name="messageCenter" kind="SYSTEM"> 
    <label language="zh_CN">消息中心</label>  
    <static-activity name="mainActivity"> 
      <label language="zh_CN">消息中心</label>  
      <has-action action="queryMQTTConfigAction" access-permission="public"/>  
      <has-action action="reloadMQTTConfigAction" access-permission="public"/>  
      <has-action action="statisticsNoReadMessageAction" access-permission="public"/>  
      <has-action action="saveMQTTConfigAction" access-permission="public"/>  
      <has-action action="sendMessageAction" access-permission="public"/> 
    </static-activity>  
    <static-activity name="messageTest"> 
      <label language="zh_CN">消息测试</label> 
    </static-activity>  
    <static-activity name="myMQTT"> 
      <label language="zh_CN">我的消息</label>  
      <has-action action="queryMQTTMessageAction" access-permission="public">
        <protected name="condition" type="String" value="R.fTargetID =  :currentPersonID()"/> 
      </has-action>  
      <has-action action="deleteMessageAction" access-permission="public"/> 
    </static-activity> 
  </process> 
</model>
