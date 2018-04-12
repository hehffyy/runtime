<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model" xmlns:m="http://www.justep.com/model">  
  <procedure name="queryB_RecHandDetailProcedure" code-model="/base/core/flowOperation/logic/code"
    code="FlowOperation.queryB_RecHandDetail"> 
    <parameter name="range" type="List"/>  
    <parameter name="concept" type="String"/>  
    <parameter name="select" type="String"/>  
    <parameter name="from" type="String"/>  
    <parameter name="aggregate" type="String"/>  
    <parameter name="dataModel" type="String"/>  
    <parameter name="fnModel" type="String"/>  
    <parameter name="condition" type="String"/>  
    <parameter name="distinct" type="Boolean"/>  
    <parameter name="idColumn" type="String"/>  
    <parameter name="filter" type="String"/>  
    <parameter name="limit" type="Integer"/>  
    <parameter name="offset" type="Integer"/>  
    <parameter name="columns" type="String"/>  
    <parameter name="orderBy" type="String"/>  
    <parameter name="aggregateColumns" type="String"/>  
    <parameter name="variables" type="Map"/> 
  </procedure>  
  <procedure name="queryB_RecHandDetailExProcedure" code-model="/base/core/flowOperation/logic/code"
    code="FlowOperationEx.queryB_RecHandDetailEx"> 
    <parameter name="concept" type="String"/>  
    <parameter name="idColumn" type="String"/>  
    <parameter name="select" type="String"/>  
    <parameter name="from" type="String"/>  
    <parameter name="condition" type="String"/>  
    <parameter name="range" type="List"/>  
    <parameter name="filter" type="String"/>  
    <parameter name="distinct" type="Boolean"/>  
    <parameter name="offset" type="Integer"/>  
    <parameter name="limit" type="Integer"/>  
    <parameter name="columns" type="String"/>  
    <parameter name="orderBy" type="String"/>  
    <parameter name="aggregate" type="String"/>  
    <parameter name="aggregateColumns" type="String"/>  
    <parameter name="variables" type="Map"/>  
    <parameter name="dataModel" type="String"/>  
    <parameter name="fnModel" type="String"/> 
  </procedure>  
  <procedure name="queryBatchRecInfoProcedure" code-model="/base/core/flowOperation/logic/code"
    code="FlowOperationEx.queryBatchRecInfo"> 
    <parameter name="bizRecId" type="String"/> 
  </procedure>  
  <procedure name="handRecsProcedure" code-model="/base/core/flowOperation/logic/code"
    code="FlowOperationEx.handRecs"> 
    <parameter name="bizgroup" type="String"/>  
    <parameter name="list" type="List"/>  
    <parameter name="handcode" type="String"/>  
    <parameter name="kind" type="String"/> 
  </procedure>  
  <procedure name="queryPcHandListProcedure" code-model="/base/core/flowOperation/logic/code"
    code="FlowOperationEx.queryPcHandList"> 
    <parameter name="handCode" type="String"/> 
  </procedure>  
  <procedure name="queryHandListByCodeProcedure" code-model="/base/core/flowOperation/logic/code"
    code="FlowOperationEx.queryHandListByCode"> 
    <parameter name="handCode" type="String"/> 
  </procedure> 
</model>
