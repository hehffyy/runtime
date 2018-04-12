<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model" xmlns:m="http://www.justep.com/model">  
  <procedure name="reloadBizInfoProcedure" code-model="/base/core/logic/code" code="Core.reloadBizInfo"> 
    <parameter name="process" type="String"/> 
  </procedure>  
  <procedure name="queryParseProcedure" code-model="/base/core/logic/code" code="Core.queryParse"> 
    <parameter name="sql" type="String"/>  
    <parameter name="dataModel" type="String"/> 
  </procedure>  
  <procedure name="ksqlParseProcedure" code-model="/base/core/logic/code" code="Core.ksqlParse"> 
    <parameter name="sql" type="String"/>  
    <parameter name="dataModels" type="String"/> 
  </procedure>  
  <procedure name="queryActivityVisibleFormsProcedure" code-model="/base/core/logic/code" code="Core.queryActivityVisibleForms"> 
    <parameter name="bizRecId" type="String"/>  
    <parameter name="tableNames" type="List"/>  
    <parameter name="variables" type="Map"/> 
  </procedure>  
  <procedure name="queryActivityVisibleTriggerProcedure" code-model="/base/core/logic/code" code="Core.queryActivityVisibleTrigger"> 
    <parameter name="bizRecId" type="String"/>  
    <parameter name="variables" type="Map"/> 
  </procedure>  
  <procedure name="getConceptStoreInModelProcedure" code-model="/base/core/logic/code" code="Core.getConceptStoreInModel"> 
    <parameter name="path" type="String"/>
    <parameter name="checkStatus" type="Boolean"/>
  </procedure> 
</model>
