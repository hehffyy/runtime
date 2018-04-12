<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <action global="false" name="queryMQTTConfigAction" procedure="queryMQTTConfigProcedure"> 
    <label language="zh_CN">查询mqtt设置</label> 
  </action>  
  <action global="false" name="reloadMQTTConfigAction" procedure="reloadMQTTConfigProcedure"> 
    <label language="zh_CN">重新加载mqtt设置</label> 
  </action>  
  <action global="true" name="sendMQTTMessageAction" procedure="sendMQTTMessageProcedure"> 
    <public name="targets" required="true" type="String"/>  
    <public name="type" type="String"/>  
    <public name="title" required="true" type="String"/>  
    <public name="url" type="String"/>  
    <label language="zh_CN">发送消息</label>  
    <public name="exts" type="Map"/>  
    <public name="kind" type="String"/> 
  </action>  
  <action global="true" name="sendFlowMQTTMessageAction" procedure="sendFlowMQTTMessageProcedure"> 
    <public name="flowGuid" required="true" type="String"/>  
    <public name="type" type="String"/>  
    <public name="title" required="true" type="String"/>  
    <public name="url" type="String"/>  
    <label language="zh_CN">发送流程消息</label>  
    <public name="exts" type="Map"/>  
    <public name="kind" type="String"/> 
  </action>  
  <action global="true" name="sendTaskMQTTMessageAction" procedure="sendTaskMQTTMessageProcedure"> 
    <public name="taskGuid" required="true" type="String"/>  
    <public name="type" type="String"/>  
    <public name="title" required="true" type="String"/>  
    <public name="url" type="String"/>  
    <label language="zh_CN">发送任务消息</label>  
    <public name="exts" type="Map"/>  
    <public name="kind" type="String"/> 
  </action>  
  <action global="true" name="sendBizRecMQTTMessageAction" procedure="sendBizRecMQTTMessageProcedure"> 
    <public name="bizRecId" required="true" type="String"/>  
    <public name="type" type="String"/>  
    <public name="title" required="true" type="String"/>  
    <public name="url" type="String"/>  
    <label language="zh_CN">发送案卷消息</label>  
    <public name="exts" type="Map"/>  
    <public name="kind" type="String"/> 
  </action>  
  <action global="true" name="statisticsNoReadMessageAction" procedure="statisticsNoReadMessageProcedure"> 
    <label language="zh_CN">统计未读信息</label> 
  </action>  
  <action name="queryMQTTMessageAction" procedure="bizQueryProcedure"> 
    <permission name="range" type="List"/>  
    <private name="concept" type="String" value="B_MessageRecord"/>  
    <private name="select" type="String" value="R,R.fStatusID as fStatusID,R.fReceiveTime as fReceiveTime,R.fClientID as fClientID"/>  
    <private name="from" type="String" value="B_MessageRecord R  join B_Message B on B = R.fMessageID"/>  
    <private name="aggregate" type="String"/>  
    <private name="dataModel" type="String" value="/base/system/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="condition" type="String"/>  
    <public name="distinct" type="Boolean" value="false"/>  
    <public name="idColumn" type="String" value="B_MessageRecord"/>  
    <public name="filter" type="String"/>  
    <public name="limit" type="Integer"/>  
    <public name="offset" type="Integer"/>  
    <public name="columns" type="String"/>  
    <public name="orderBy" type="String"/>  
    <public name="aggregateColumns" type="String"/>  
    <public name="variables" type="Map"/>  
    <label language="zh_CN">查询我的MQTT消息</label> 
  </action>  
  <action global="false" name="saveMQTTConfigAction" procedure="saveMQTTConfigProcedure"> 
    <label language="zh_CN">保存MQTT设置</label>  
    <public name="config" type="Map"/> 
  </action>  
  <action global="true" name="queryMyMessageInfoAction" procedure="bizQueryProcedure"> 
    <permission name="range" type="List"/>  
    <private name="concept" type="String" value="B_MessageRecord"/>  
    <private name="select" type="String" value="B_MessageRecord,B_Message.fCreateTime as fCreateTime,B_Message.fSenderName as fSenderName,B_Message.fURL as fURL,B_Message.fTitle as fTitle,B_Message.fKind as fKind,B_MessageRecord.fStatusName as fStatusName,B_MessageRecord.fReceiveTime as fReceiveTime,B_MessageRecord.fClientID as fClientID,B_Message.fExts as fExts"/>  
    <private name="from" type="String" value="B_MessageRecord B_MessageRecord  join B_Message B_Message on B_Message = B_MessageRecord.fMessageID"/>  
    <private name="aggregate" type="String"/>  
    <private name="dataModel" type="String" value="/base/system/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="condition" type="String" value="B_MessageRecord.fStatusID = 'rsReady' AND B_MessageRecord.fTargetID = :currentPersonID()"/>  
    <public name="distinct" type="Boolean" value="false"/>  
    <public name="idColumn" type="String" value="B_MessageRecord"/>  
    <public name="filter" type="String"/>  
    <public name="limit" type="Integer"/>  
    <public name="offset" type="Integer"/>  
    <public name="columns" type="String"/>  
    <public name="orderBy" type="String"/>  
    <public name="aggregateColumns" type="String"/>  
    <public name="variables" type="Map"/>  
    <label language="zh_CN">查询我的消息</label> 
  </action>  
  <action name="deleteMessageAction" global="true" procedure="deleteMessageProcedure"> 
    <label language="zh_CN">删除消息</label>  
    <public type="String" name="ids"/>  
    <public type="String" name="kind"/> 
  </action>  
  <action name="getSysNoticeInfoAction" global="true" procedure="getSysNoticeInfoProcedure"> 
    <label language="zh_CN">获取系统通知信息</label>  
    <public type="String" name="rowID"/>  
    <public type="String" name="fType"/> 
  </action>  
  <action name="updateSysNoticeStatusAction" global="true" procedure="updateSysNoticeStatusProcedure"> 
    <label language="zh_CN">修改系统通知状态</label>  
    <public type="String" name="mesFID"/> 
  </action> 
</model>
