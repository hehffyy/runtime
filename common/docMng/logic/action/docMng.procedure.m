<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model" xmlns:m="http://www.justep.com/model">  
  <procedure name="uploadFileProcedure" code-model="/common/docMng/logic/code" code="DocMng.uploadFile"> 
    <parameter name="input" type="Object"/>  
    <parameter name="fileName" type="String"/>  
    <parameter name="subPath" type="String"/>  
    <parameter name="folderID" type="String"/> 
  </procedure>  
  <procedure name="queryB_DocFolderByLimitProcedure" code-model="/common/docMng/logic/code" code="DocMng.queryB_DocFolderByLimit"> 
    <parameter name="concept" type="String"/>  
    <parameter name="select" type="String"/>  
    <parameter name="from" type="String"/>  
    <parameter name="aggregate" type="String"/>  
    <parameter name="dataModel" type="String"/>  
    <parameter name="fnModel" type="String"/>  
    <parameter name="condition" type="String"/>  
    <parameter name="range" type="List"/>  
    <parameter name="distinct" type="Boolean"/>  
    <parameter name="idColumn" type="String"/>  
    <parameter name="filter" type="String"/>  
    <parameter name="limit" type="Integer"/>  
    <parameter name="offset" type="Integer"/>  
    <parameter name="columns" type="String"/>  
    <parameter name="orderBy" type="String"/>  
    <parameter name="aggregateColumns" type="String"/>  
    <parameter name="variables" type="Map"/> 
  </procedure>  
  <procedure name="queryDocAuthListProcedure" code-model="/common/docMng/logic/code" code="DocMng.queryDocAuthList"> 
    <parameter name="concept" type="String"/>  
    <parameter name="select" type="String"/>  
    <parameter name="from" type="String"/>  
    <parameter name="aggregate" type="String"/>  
    <parameter name="dataModel" type="String"/>  
    <parameter name="fnModel" type="String"/>  
    <parameter name="condition" type="String"/>  
    <parameter name="range" type="List"/>  
    <parameter name="distinct" type="Boolean"/>  
    <parameter name="idColumn" type="String"/>  
    <parameter name="filter" type="String"/>  
    <parameter name="limit" type="Integer"/>  
    <parameter name="offset" type="Integer"/>  
    <parameter name="columns" type="String"/>  
    <parameter name="orderBy" type="String"/>  
    <parameter name="aggregateColumns" type="String"/>  
    <parameter name="variables" type="Map"/> 
  </procedure>  
  <procedure name="deleteFileProcedure" code-model="/common/docMng/logic/code" code="DocMng.deleteFile"> 
    <parameter name="fileID" type="String"/> 
  </procedure>  
  <procedure name="deleteFolderProcedure" code-model="/common/docMng/logic/code" code="DocMng.deleteFolder">
    <parameter name="folderID" type="String"/>
  </procedure>
</model>
