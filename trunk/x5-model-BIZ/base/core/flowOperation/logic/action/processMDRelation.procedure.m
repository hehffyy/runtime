<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model" xmlns:m="http://www.justep.com/model">  
  <procedure name="queryMDRelationProcedure" code-model="/base/core/flow/logic/code" code="ProcessMDRelation.queryMDRelation"> 
    <parameter name="taskID" type="String"/> 
  </procedure>  
  <procedure name="queryMasterRelationProcedure" code-model="/base/core/flowOperation/logic/code" code="ProcessMDRelation.queryMasterRelation"> 
    <parameter name="taskID" type="String"/>  
    <parameter name="bizrecID" type="String"/> 
  </procedure>  
  <procedure name="queryDetailRelationProcedure" code-model="/base/core/flowOperation/logic/code" code="ProcessMDRelation.queryDetailRelation"> 
    <parameter name="taskID" type="String"/> 
  </procedure>  
  <procedure name="queryRelationBizRecProcedure" code-model="/base/core/flowOperation/logic/code" code="ProcessMDRelation.queryRelationBizRec">
    <parameter name="bizRecId" type="String"/>
    <parameter name="taskID" type="String"/>
  </procedure>
</model>
