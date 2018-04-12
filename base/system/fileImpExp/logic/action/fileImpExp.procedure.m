<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model" xmlns:m="http://www.justep.com/model">  
  <procedure name="externalFileImportProcedure" code-model="/base/system/fileImpExp/logic/code"
    code="FileImportExport.externalFileImport"> 
    <parameter name="input" type="Object"/>  
    <parameter name="targetProcess" type="String"/>  
    <parameter name="targetActivity" type="String"/>  
    <parameter name="url" type="String"/>  
    <parameter name="bizRecId" type="String"/>  
    <parameter name="variants" type="Map"/>  
    <parameter name="filters" type="Map"/>  
    <parameter name="returnData" type="Boolean"/> 
    <parameter name="fileName" type="String"/>  
  </procedure>  
  <procedure name="virtualFileImportProcedure" code-model="/base/system/fileImpExp/logic/code"
    code="FileImportExport.virtualFileImport"> 
    <parameter name="targetProcess" type="String"/>  
    <parameter name="targetActivity" type="String"/>  
    <parameter name="url" type="String"/>  
    <parameter name="bizRecId" type="String"/>  
    <parameter name="variants" type="Map"/>  
    <parameter name="filters" type="Map"/>  
    <parameter name="returnData" type="Boolean"/> 
  </procedure>  
  <procedure name="exportExternalFileProcedure" code-model="/base/system/fileImpExp/logic/code"
    code="FileImportExport.exportExternalFile"> 
    <parameter name="targetProcess" type="String"/>  
    <parameter name="targetActivity" type="String"/>  
    <parameter name="url" type="String"/>  
    <parameter name="bizRecId" type="String"/>  
    <parameter name="variants" type="Map"/>  
    <parameter name="filters" type="Map"/> 
  </procedure>  
  <procedure name="downloadExternalFileProcedure" code-model="/base/system/fileImpExp/logic/code"
    code="FileImportExport.downloadExternalFile"> 
    <parameter name="fileName" type="String"/> 
  </procedure> 
</model>
