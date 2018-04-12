<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model" xmlns:m="http://www.justep.com/model">  
  <procedure name="execDirectiveInfoProcedure" code-model="/system_X/logic/code"
    code="ButoneProcessProcedure.execDirectiveInfo"> 
    <parameter name="processControl" type="Object"/> 
  </procedure>  
  <procedure name="getSysTableInfoProcedure" code-model="/system_X/logic/code" code="ButoneProcessProcedure.getSysTableInfo"> 
    <parameter name="fBizRecId" type="String"/>  
    <parameter name="flowID" type="String"/> 
  </procedure>  
  <procedure name="syncSysTableInfoProcedure" code-model="/system_X/logic/code" code="ButoneProcessProcedure.syncSysTableInfo"> 
    <parameter name="fBizRecId" type="String"/>  
    <parameter name="flowID" type="String"/>  
    <parameter name="tabInfo" type="String"/> 
  </procedure>  
  <procedure name="getUserLoginProcedure" code-model="/system_X/logic/code" code="ButoneProcessProcedure.getUserLogin"> 
    <parameter name="u" type="String"/> 
  </procedure>  
  <procedure name="executeProcessOperateProcedure" code-model="/system_X/logic/code" code="ButoneProcessProcedure.executeProcessOperate"> 
    <parameter name="taskID" type="String"/>  
    <parameter name="operateKind" type="String"/> 
  </procedure>
  <procedure name="executeProcessOperateHasControlProcedure" code-model="/system_X/logic/code" code="ButoneProcessProcedure.executeProcessOperateHasControl"> 
    <parameter name="taskID" type="String"/>  
    <parameter name="operateKind" type="String"/>
    <parameter name="control" type="Object"/> 
  </procedure> 
</model>
