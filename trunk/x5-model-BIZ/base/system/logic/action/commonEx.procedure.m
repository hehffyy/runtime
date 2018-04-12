<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model" xmlns:m="http://www.justep.com/model">  
  <procedure name="updateBlztProcedure" code-model="/base/system/logic/code" code="CommonExProcedure.updateBlzt"> 
    <parameter name="oldIds" type="String"/>  
    <parameter name="oldTables" type="String"/>  
    <parameter name="oldKeyColumns" type="String"/>  
    <parameter name="newIds" type="String"/>  
    <parameter name="newTables" type="String"/>  
    <parameter name="newKeyColumns" type="String"/> 
  </procedure>  
  <procedure name="genSimpleCodeProcedure" code-model="/base/system/logic/code" code="CommonExProcedure.genSimpleCode"> 
    <parameter name="key" type="String"/>  
    <parameter name="format" type="String"/> 
  </procedure>  
  <procedure name="uploadDocExProcedure" code-model="/base/system/logic/code" code="CommonExProcedure.uploadDocEx"> 
    <parameter name="input" type="Object"/>  
    <parameter name="fileName" type="String"/>  
    <parameter name="subPath" type="String"/>  
    <parameter name="docId" type="String"/> 
  </procedure>  
  <procedure name="uploadDocStringProcedure" code-model="/base/system/logic/code" code="CommonExProcedure.uploadDocString"> 
    <parameter name="param" type="String"/> 
  </procedure>  
  <procedure name="genQCodeProcedure" code-model="/base/system/logic/code" code="CommonExProcedure.genQCode"> 
    <parameter name="code" type="String"/> 
  </procedure>  
  <procedure name="outCallPluginProcedure" code-model="/base/system/logic/code" code="CommonExProcedure.outCallPlugin">
    <parameter name="url" type="String"/>
    <parameter name="process" type="String"/>
    <parameter name="activity" type="String"/>
  </procedure>
   <procedure name="getSystemConstProcedure" code-model="/base/system/logic/code" code="CommonExProcedure.getSystemConst">
    <parameter name="param" type="String"/>
  </procedure>
</model>
