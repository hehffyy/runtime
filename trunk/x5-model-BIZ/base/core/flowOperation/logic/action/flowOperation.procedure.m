<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model" xmlns:m="http://www.justep.com/model">  
  <procedure name="queryBatchTaskProcedure" code-model="/base/core/flowOperation/logic/code"
    code="FlowOperation.queryBatchTask"> 
    <parameter name="process" type="String"/>  
    <parameter name="activity" type="String"/>  
    <parameter name="orderBy" type="String"/>  
    <parameter name="limit" type="Integer"/>  
    <parameter name="offset" type="Integer"/>  
    <parameter name="variables" type="Map"/>  
    <parameter name="filterMap" type="Map"/> 
  </procedure>  
  <procedure name="queryFinishResultDictProcedure" code-model="/base/core/flowOperation/logic/code"
    code="FlowOperation.queryFinishResultDict"/>  
  <procedure name="queryBizOperationOptionProcedure" code-model="/base/core/flowOperation/logic/code"
    code="FlowOperation.queryBizOperationOption"> 
    <parameter name="process" type="String"/>  
    <parameter name="activity" type="String"/>  
    <parameter name="operation" type="String"/>  
    <parameter name="isBatch" type="Boolean"/> 
  </procedure> 
</model>
