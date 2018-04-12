<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <action name="queryBA_XMXXAction" procedure="bizQueryProcedure"> 
    <permission name="range" type="List"/>  
    <private name="concept" type="String" value="BA_XMXX"/>  
    <private name="select" type="String" value="BA_XMXX.*"/>  
    <private name="from" type="String" value="BA_XMXX BA_XMXX"/>  
    <private name="aggregate" type="String"/>  
    <private name="dataModel" type="String" value="/base/system/fileImpExp/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="condition" type="String"/>  
    <public name="distinct" type="Boolean" value="false"/>  
    <public name="idColumn" type="String" value="BA_XMXX"/>  
    <public name="filter" type="String"/>  
    <public name="limit" type="Integer"/>  
    <public name="offset" type="Integer"/>  
    <public name="columns" type="String"/>  
    <public name="orderBy" type="String"/>  
    <public name="aggregateColumns" type="String"/>  
    <public name="variables" type="Map"/> 
  </action>  
  <action name="saveBA_XMXXAction" procedure="bizSaveProcedure"> 
    <permission name="insertRange" type="List"/>  
    <permission name="deleteRange" type="List"/>  
    <permission name="updateRange" type="List"/>  
    <private name="concept" type="String" value="BA_XMXX"/>  
    <private name="dataModel" type="String" value="/base/system/fileImpExp/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="readOnly" type="String"/>  
    <protected name="notNull" type="String"/>  
    <public name="table" required="true" type="Table"/> 
  </action>  
  <action name="createBA_XMXXAction" procedure="bizCreateProcedure"> 
    <private name="concept" type="String" value="BA_XMXX"/>  
    <private name="fnModel" type="String"/>  
    <public name="table" required="true" type="Table"/>  
    <public name="defaultValues" type="Map"/> 
  </action>  
  <action name="queryBA_DKXXAction" procedure="bizQueryProcedure"> 
    <permission name="range" type="List"/>  
    <private name="concept" type="String" value="BA_DKXX"/>  
    <private name="select" type="String" value="BA_DKXX.*"/>  
    <private name="from" type="String" value="BA_DKXX BA_DKXX"/>  
    <private name="aggregate" type="String"/>  
    <private name="dataModel" type="String" value="/base/system/fileImpExp/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="condition" type="String"/>  
    <public name="distinct" type="Boolean" value="false"/>  
    <public name="idColumn" type="String" value="BA_DKXX"/>  
    <public name="filter" type="String"/>  
    <public name="limit" type="Integer"/>  
    <public name="offset" type="Integer"/>  
    <public name="columns" type="String"/>  
    <public name="orderBy" type="String"/>  
    <public name="aggregateColumns" type="String"/>  
    <public name="variables" type="Map"/> 
  </action>  
  <action name="saveBA_DKXXAction" procedure="bizSaveProcedure"> 
    <permission name="insertRange" type="List"/>  
    <permission name="deleteRange" type="List"/>  
    <permission name="updateRange" type="List"/>  
    <private name="concept" type="String" value="BA_DKXX"/>  
    <private name="dataModel" type="String" value="/base/system/fileImpExp/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="readOnly" type="String"/>  
    <protected name="notNull" type="String"/>  
    <public name="table" required="true" type="Table"/> 
  </action>  
  <action name="createBA_DKXXAction" procedure="bizCreateProcedure"> 
    <private name="concept" type="String" value="BA_DKXX"/>  
    <private name="fnModel" type="String"/>  
    <public name="table" required="true" type="Table"/>  
    <public name="defaultValues" type="Map"/> 
  </action>  
  <action name="queryBA_ZBXXAction" procedure="bizQueryProcedure"> 
    <permission name="range" type="List"/>  
    <private name="concept" type="String" value="BA_ZBXX"/>  
    <private name="select" type="String" value="BA_ZBXX.*"/>  
    <private name="from" type="String" value="BA_ZBXX BA_ZBXX"/>  
    <private name="aggregate" type="String"/>  
    <private name="dataModel" type="String" value="/base/system/fileImpExp/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="condition" type="String"/>  
    <public name="distinct" type="Boolean" value="false"/>  
    <public name="idColumn" type="String" value="BA_ZBXX"/>  
    <public name="filter" type="String"/>  
    <public name="limit" type="Integer"/>  
    <public name="offset" type="Integer"/>  
    <public name="columns" type="String"/>  
    <public name="orderBy" type="String"/>  
    <public name="aggregateColumns" type="String"/>  
    <public name="variables" type="Map"/> 
  </action>  
  <action name="saveBA_ZBXXAction" procedure="bizSaveProcedure"> 
    <permission name="insertRange" type="List"/>  
    <permission name="deleteRange" type="List"/>  
    <permission name="updateRange" type="List"/>  
    <private name="concept" type="String" value="BA_ZBXX"/>  
    <private name="dataModel" type="String" value="/base/system/fileImpExp/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="readOnly" type="String"/>  
    <protected name="notNull" type="String"/>  
    <public name="table" required="true" type="Table"/> 
  </action>  
  <action name="createBA_ZBXXAction" procedure="bizCreateProcedure"> 
    <private name="concept" type="String" value="BA_ZBXX"/>  
    <private name="fnModel" type="String"/>  
    <public name="table" required="true" type="Table"/>  
    <public name="defaultValues" type="Map"/> 
  </action>  
  <action global="false" name="getImportTable" procedure="getImportTableProcedure"> 
    <label language="zh_CN">返回界址点文件项目表</label> 
  </action>  
  <action name="boundaryFileImportAction" global="true" procedure="boundaryFileImportProcedure"> 
    <label language="zh_CN">界址点文件导入</label>  
    <public name="uploadFile" type="Object"/>  
    <public name="fileType" type="String"/>  
    <public name="returnData" type="String"/> 
  </action> 
</model>
