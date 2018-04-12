<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model" xmlns:m="http://www.justep.com/model">  
  <procedure name="getPersonInfoProcedure" code-model="/base/system/logic/code" code="SystemExt.getPersonInfo"> 
    <parameter name="userName" type="String"/> 
  </procedure>  
  <procedure name="changePersonInfoProcedure" code-model="/base/system/logic/code"
    code="SystemExt.changePersonInfo"> 
    <parameter name="userName" type="String"/>  
    <parameter name="telPhone" type="String"/>  
    <parameter name="mobilePhone" type="String"/>  
    <parameter name="postalCode" type="String"/> 
  </procedure>  
  <procedure name="getSystemExtConfigProcedure" code-model="/base/system/logic/code"
    code="SystemExt.getSystemExtConfig"/>  
  <procedure name="syncSystemExtConfigProcedure" code-model="/base/system/logic/code"
    code="SystemExt.syncSystemExtConfig"> 
    <parameter name="files" type="String"/> 
  </procedure> 
</model>
