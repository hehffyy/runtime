<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model" xmlns:m="http://www.justep.com/model">  
  <procedure name="selectOneFldProcedure" code-model="/base/system/logic/code"
    code="SqlExProcedure.selectOneFld"> 
    <parameter name="table" type="String"/>  
    <parameter name="filter" type="String"/>  
    <parameter name="returnFld" type="String"/> 
  </procedure>  
  <procedure name="selectFieldValueProcedure" code-model="/base/system/logic/code"
    code="SqlExProcedure.selectFieldValue"> 
    <parameter name="virtualTable" type="String"/>  
    <parameter name="filter" type="String"/>  
    <parameter name="fields" type="String"/> 
  </procedure>  
  <procedure name="sqlQueryExProcedure" code-model="/base/system/logic/code" code="SqlExProcedure.sqlQueryEx"> 
    <parameter name="sql" type="String"/>  
    <parameter name="variables" type="String"/> 
  </procedure>  
  <procedure name="reportQueryProcedure" code-model="/base/system/logic/code" code="SqlExProcedure.reportQuery"> 
    <parameter name="dataDefs" type="String"/>  
    <parameter name="variables" type="Map"/> 
  </procedure>
</model>
