<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model" xmlns:m="http://www.justep.com/model">  
  <procedure name="boundaryFileImportProcedure" code-model="/base/system/fileImpExp/logic/code"
    code="BoundaryFileImport.boundaryFileImport"> 
    <parameter name="uploadFile" type="Object"/>  
    <parameter name="fileType" type="String"/>  
    <parameter name="returnData" type="String"/> 
  </procedure>  
  <procedure name="getImportTableProcedure" code-model="/base/system/fileImpExp/logic/code"
    code="BoundaryFileImport.getImportTable"/> 
</model>
