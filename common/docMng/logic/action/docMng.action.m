<?xml version="1.0" encoding="UTF-8" standalone="no"?><model xmlns="http://www.justep.com/model"><action name="queryB_DocFolderAction" procedure="bizQueryProcedure"><permission name="range" type="List"/>
<private name="concept" type="String" value="B_DocFolder"/>
<private name="select" type="String" value="B_DocFolder.*"/>
<private name="from" type="String" value="B_DocFolder B_DocFolder"/>
<private name="aggregate" type="String"/>
<private name="dataModel" type="String" value="/common/docMng/data"/>
<private name="fnModel" type="String"/>
<protected name="condition" type="String"/>
<public name="distinct" type="Boolean" value="false"/>
<public name="idColumn" type="String" value="B_DocFolder"/>
<public name="filter" type="String"/>
<public name="limit" type="Integer"/>
<public name="offset" type="Integer"/>
<public name="columns" type="String"/>
<public name="orderBy" type="String"/>
<public name="aggregateColumns" type="String"/>
<public name="variables" type="Map"/>
</action>
<action name="saveB_DocFolderAction" procedure="bizSaveProcedure"><permission name="insertRange" type="List"/>
<permission name="deleteRange" type="List"/>
<permission name="updateRange" type="List"/>
<private name="concept" type="String" value="B_DocFolder"/>
<private name="dataModel" type="String" value="/common/docMng/data"/>
<private name="fnModel" type="String"/>
<protected name="readOnly" type="String"/>
<protected name="notNull" type="String"/>
<public name="table" required="true" type="Table"/>
</action>
<action name="createB_DocFolderAction" procedure="bizCreateProcedure"><private name="concept" type="String" value="B_DocFolder"/>
<private name="fnModel" type="String"/>
<public name="table" required="true" type="Table"/>
<public name="defaultValues" type="Map"/>
</action>
<action name="queryB_DocFileAction" procedure="bizQueryProcedure"><permission name="range" type="List"/>
<private name="concept" type="String" value="B_DocFile"/>
<private name="select" type="String" value="B_DocFile.*"/>
<private name="from" type="String" value="B_DocFile B_DocFile"/>
<private name="aggregate" type="String"/>
<private name="dataModel" type="String" value="/common/docMng/data"/>
<private name="fnModel" type="String"/>
<protected name="condition" type="String"/>
<public name="distinct" type="Boolean" value="false"/>
<public name="idColumn" type="String" value="B_DocFile"/>
<public name="filter" type="String"/>
<public name="limit" type="Integer"/>
<public name="offset" type="Integer"/>
<public name="columns" type="String"/>
<public name="orderBy" type="String"/>
<public name="aggregateColumns" type="String"/>
<public name="variables" type="Map"/>
</action>
<action name="saveB_DocFileAction" procedure="bizSaveProcedure"><permission name="insertRange" type="List"/>
<permission name="deleteRange" type="List"/>
<permission name="updateRange" type="List"/>
<private name="concept" type="String" value="B_DocFile"/>
<private name="dataModel" type="String" value="/common/docMng/data"/>
<private name="fnModel" type="String"/>
<protected name="readOnly" type="String"/>
<protected name="notNull" type="String"/>
<public name="table" required="true" type="Table"/>
</action>
<action name="createB_DocFileAction" procedure="bizCreateProcedure"><private name="concept" type="String" value="B_DocFile"/>
<private name="fnModel" type="String"/>
<public name="table" required="true" type="Table"/>
<public name="defaultValues" type="Map"/>
</action>
<action global="true" name="uploadFile" procedure="uploadFileProcedure"><label language="zh_CN">上传文件</label>
<public name="input" type="Object"/>
<public name="fileName" type="String"/>
<public name="subPath" type="String"/>
<public name="folderID" type="String"/>
</action>
<action name="queryB_DocLimitAction" procedure="bizQueryProcedure"><permission name="range" type="List"/>
<private name="concept" type="String" value="B_DocLimit"/>
<private name="select" type="String" value="B_DocLimit.*"/>
<private name="from" type="String" value="B_DocLimit B_DocLimit"/>
<private name="aggregate" type="String"/>
<private name="dataModel" type="String" value="/common/docMng/data"/>
<private name="fnModel" type="String"/>
<protected name="condition" type="String"/>
<public name="distinct" type="Boolean" value="false"/>
<public name="idColumn" type="String" value="B_DocLimit"/>
<public name="filter" type="String"/>
<public name="limit" type="Integer"/>
<public name="offset" type="Integer"/>
<public name="columns" type="String"/>
<public name="orderBy" type="String"/>
<public name="aggregateColumns" type="String"/>
<public name="variables" type="Map"/>
</action>
<action name="saveB_DocLimitAction" procedure="bizSaveProcedure"><permission name="insertRange" type="List"/>
<permission name="deleteRange" type="List"/>
<permission name="updateRange" type="List"/>
<private name="concept" type="String" value="B_DocLimit"/>
<private name="dataModel" type="String" value="/common/docMng/data"/>
<private name="fnModel" type="String"/>
<protected name="readOnly" type="String"/>
<protected name="notNull" type="String"/>
<public name="table" required="true" type="Table"/>
</action>
<action name="createB_DocLimitAction" procedure="bizCreateProcedure"><private name="concept" type="String" value="B_DocLimit"/>
<private name="fnModel" type="String"/>
<public name="table" required="true" type="Table"/>
<public name="defaultValues" type="Map"/>
</action>
<action global="true" name="queryB_DocFolderByLimitAction" procedure="queryB_DocFolderByLimitProcedure"><permission name="range" type="List"/>
<private name="concept" type="String" value="B_DocFolder"/>
<private name="select" type="String" value="B_DocFolder.*"/>
<private name="from" type="String" value="B_DocFolder B_DocFolder"/>
<private name="aggregate" type="String"/>
<private name="dataModel" type="String" value="/common/docMng/data"/>
<private name="fnModel" type="String"/>
<protected name="condition" type="String"/>
<public name="distinct" type="Boolean" value="false"/>
<public name="idColumn" type="String" value="B_DocFolder"/>
<public name="filter" type="String"/>
<public name="limit" type="Integer"/>
<public name="offset" type="Integer"/>
<public name="columns" type="String"/>
<public name="orderBy" type="String" value="B_DocFolder.fOrder asc"/>
<public name="aggregateColumns" type="String"/>
<public name="variables" type="Map"/>
</action>
<action global="true" name="queryB_DocAuthAction" procedure="bizQueryProcedure"><permission name="range" type="List"/>
<private name="concept" type="String" value="B_DocAuth"/>
<private name="select" type="String" value="B_DocAuth.*"/>
<private name="from" type="String" value="B_DocAuth B_DocAuth"/>
<private name="aggregate" type="String"/>
<private name="dataModel" type="String" value="/common/docMng/data"/>
<private name="fnModel" type="String"/>
<protected name="condition" type="String"/>
<public name="distinct" type="Boolean" value="false"/>
<public name="idColumn" type="String" value="B_DocAuth"/>
<public name="filter" type="String"/>
<public name="limit" type="Integer"/>
<public name="offset" type="Integer"/>
<public name="columns" type="String"/>
<public name="orderBy" type="String"/>
<public name="aggregateColumns" type="String"/>
<public name="variables" type="Map"/>
</action>
<action global="true" name="saveB_DocAuthAction" procedure="bizSaveProcedure"><permission name="insertRange" type="List"/>
<permission name="deleteRange" type="List"/>
<permission name="updateRange" type="List"/>
<private name="concept" type="String" value="B_DocAuth"/>
<private name="dataModel" type="String" value="/common/docMng/data"/>
<private name="fnModel" type="String"/>
<protected name="readOnly" type="String"/>
<protected name="notNull" type="String"/>
<public name="table" required="true" type="Table"/>
</action>
<action global="true" name="createB_DocAuthAction" procedure="bizCreateProcedure"><private name="concept" type="String" value="B_DocAuth"/>
<private name="fnModel" type="String"/>
<public name="table" required="true" type="Table"/>
<public name="defaultValues" type="Map"/>
</action>

<action global="true" name="deleteFile" procedure="deleteFileProcedure"><public name="fileID" type="String"/>
<label language="zh_CN">删除文件</label>
</action>
<action global="true" name="deleteFolder" procedure="deleteFolderProcedure"><public name="folderID" type="String"/>
</action>
</model>