<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model" xmlns:m="http://www.justep.com/model">  
  <procedure name="queryBizGroupProcedure" code-model="/SA/task/logic/code" code="TaskCenterCustom.queryBizGroup"> 
    <parameter name="org" type="String"/>  
    <parameter name="taskGroup" type="String"/>  
    <parameter name="taskFilter" type="String"/>  
    <parameter name="variables" type="Map"/> 
  </procedure>  
  <procedure name="queryBizGroupTableProcedure" code-model="/SA/task/logic/code" code="TaskCenterCustom.queryBizGroupTable"> 
    <parameter name="org" type="String"/>  
    <parameter name="taskGroup" type="String"/>  
    <parameter name="taskFilter" type="String"/>  
    <parameter name="variables" type="Map"/> 
  </procedure>  
  <procedure name="queryBizGroupTaskProcedure" code-model="/SA/task/logic/code" code="TaskCenterCustom.queryBizGroupTask"> 
    <parameter name="orderBy" type="String"/>  
    <parameter name="limit" type="Integer"/>  
    <parameter name="offset" type="Integer"/>  
    <parameter name="variables" type="Map"/>  
    <parameter name="filterMap" type="Map"/> 
  </procedure>  
  <procedure name="queryTaskListProcedure" code-model="/SA/task/logic/code" code="TaskCenterCustom.queryTaskList"> 
    <parameter name="variables" type="String"/> 
  </procedure>  
  <procedure name="queryHandInfoProcedure" code-model="/SA/task/logic/code" code="TaskCenterCustom.queryHandInfo"> 
    <parameter name="variables" type="String"/> 
  </procedure>  
  <procedure name="getTaskCenterFuncUrlProcedure" code-model="/SA/task/logic/code" code="TaskCenterCustom.getTaskCenterFuncUrl"/>  
  <procedure name="queryGroupTaskCountProcedure" code-model="/SA/task/logic/code" code="TaskCenterCustom.queryGroupTaskCount"> 
    <parameter name="taskGroup" type="List"/>  
    <parameter name="taskFilter" type="String"/>  
    <parameter name="variables" type="Map"/> 
  </procedure>  
  <procedure name="queryUnsignedTaskProcedure" code-model="/SA/task/logic/code" code="TaskCenterCustom.queryUnsignedTask"/>  
  <procedure name="querySignedTaskProcedure" code-model="/SA/task/logic/code" code="TaskCenterCustom.querySignedTask"/>  
  <procedure name="queryMobileWaitMessageProcedure" code-model="/SA_X/task/logic/code" code="TaskCenterCustom.queryMobileWaitMessage"/>
</model>
