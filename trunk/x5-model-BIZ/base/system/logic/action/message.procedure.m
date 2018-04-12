<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model" xmlns:m="http://www.justep.com/model">  
  <procedure name="queryMQTTConfigProcedure" code-model="/base/system/logic/code"
    code="MQTT.queryMQTTConfig"/>  
  <procedure name="reloadMQTTConfigProcedure" code-model="/base/system/logic/code"
    code="MQTT.reloadMQTTConfig"/>  
  <procedure name="sendMQTTMessageProcedure" code-model="/base/system/logic/code"
    code="MQTT.sendMessage"> 
    <parameter name="targets" type="String"/>  
    <parameter name="type" type="String"/>  
    <parameter name="title" type="String"/>  
    <parameter name="url" type="String"/>  
    <parameter name="exts" type="Map"/>  
    <parameter name="kind" type="String"/> 
  </procedure>  
  <procedure name="sendFlowMQTTMessageProcedure" code-model="/base/system/logic/code"
    code="MQTT.sendFlowMessage"> 
    <parameter name="flowGuid" type="String"/>  
    <parameter name="type" type="String"/>  
    <parameter name="title" type="String"/>  
    <parameter name="url" type="String"/>  
    <parameter name="exts" type="Map"/>  
    <parameter name="kind" type="String"/> 
  </procedure>  
  <procedure name="sendTaskMQTTMessageProcedure" code-model="/base/system/logic/code"
    code="MQTT.sendTaskMessage"> 
    <parameter name="taskGuid" type="String"/>  
    <parameter name="type" type="String"/>  
    <parameter name="title" type="String"/>  
    <parameter name="url" type="String"/>  
    <parameter name="exts" type="Map"/>  
    <parameter name="kind" type="String"/> 
  </procedure>  
  <procedure name="sendBizRecMQTTMessageProcedure" code-model="/base/system/logic/code"
    code="MQTT.sendBizRecMessage"> 
    <parameter name="bizRecId" type="String"/>  
    <parameter name="type" type="String"/>  
    <parameter name="title" type="String"/>  
    <parameter name="url" type="String"/>  
    <parameter name="exts" type="Map"/>  
    <parameter name="kind" type="String"/> 
  </procedure>  
  <procedure name="statisticsNoReadMessageProcedure" code-model="/base/system/logic/code"
    code="MQTT.statisticsNoReadMessage"/>  
  <procedure name="saveMQTTConfigProcedure" code-model="/base/system/logic/code"
    code="MQTT.saveMQTTConfig"> 
    <parameter name="config" type="Map"/> 
  </procedure>  
  <procedure name="deleteMessageProcedure" code-model="/base/system/logic/code" code="MQTT.deleteMessage"> 
    <parameter name="ids" type="String"/>  
    <parameter name="kind" type="String"/> 
  </procedure>  
  <procedure name="getSysNoticeInfoProcedure" code-model="/base/system/logic/code"
    code="MQTT.getSysNoticeInfo"> 
    <parameter name="rowID" type="String"/>  
    <parameter name="fType" type="String"/> 
  </procedure>  
  <procedure name="updateSysNoticeStatusProcedure" code-model="/base/system/logic/code"
    code="MQTT.updateSysNoticeStatus"> 
    <parameter name="mesFID" type="String"/> 
  </procedure> 
</model>
