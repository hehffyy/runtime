<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model" xmlns:m="http://www.justep.com/model">  
  <procedure name="bjgzProcedure" code-model="/common/innerBz/logic/code" code="InnerBz.bjgz"> 
    <parameter name="suspendInfo" type="String"/>  
    <parameter name="bizRecId" type="String"/>  
    <parameter name="isSuspend" type="String"/>  
    <parameter name="reason" type="String"/> 
  </procedure>  
  <procedure name="bjslProcedure" code-model="/common/innerBz/logic/code" code="InnerBz.bjsl"> 
    <parameter name="suspendInfo" type="String"/>  
    <parameter name="bizRecId" type="String"/> 
  </procedure>  
  <procedure name="startHsProcedure" code-model="/common/innerBz/logic/code" code="InnerBz.startHs"> 
    <parameter name="bizrecId" type="String"/>  
    <parameter name="hsProcess" type="String"/>  
    <parameter name="hsActivity" type="String"/> 
  </procedure>  
  <procedure name="secondBzProcedure" code-model="/common/innerBz/logic/code" code="InnerBz.secondBz"> 
    <parameter name="bizRecId" type="String"/>
    <parameter name="reason" type="String"/>
  </procedure> 
</model>
