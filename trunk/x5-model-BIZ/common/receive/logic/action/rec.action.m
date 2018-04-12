<?xml version="1.0" encoding="UTF-8" standalone="no"?><model xmlns="http://www.justep.com/model">


<action global="false" name="getRecInfo" procedure="getRecInfoProcedure"><public name="personId" type="String"/>
<public name="title" type="String"/>
</action>






<action global="false" name="getRecTitle" procedure="getRecTitleProcedure"><public name="personId" type="String"/>
</action>
<action global="false" name="getImageUrl" procedure="getImageUrlProcedure"><public name="docPath" type="String"/>
<public name="fileID" type="String"/>
</action>
<action name="queryB_CatalogAction" procedure="bizQueryProcedure"><permission name="range" type="List"/>
<private name="concept" type="String" value="B_Catalog"/>
<private name="select" type="String" value="B_Catalog.*"/>
<private name="from" type="String" value="B_Catalog B_Catalog"/>
<private name="aggregate" type="String"/>
<private name="dataModel" type="String" value="/common/receive/data"/>
<private name="fnModel" type="String"/>
<protected name="condition" type="String"/>
<public name="distinct" type="Boolean" value="false"/>
<public name="idColumn" type="String" value="B_Catalog"/>
<public name="filter" type="String"/>
<public name="limit" type="Integer"/>
<public name="offset" type="Integer"/>
<public name="columns" type="String"/>
<public name="orderBy" type="String"/>
<public name="aggregateColumns" type="String"/>
<public name="variables" type="Map"/>
</action>
<action name="saveB_CatalogAction" procedure="bizSaveProcedure"><permission name="insertRange" type="List"/>
<permission name="deleteRange" type="List"/>
<permission name="updateRange" type="List"/>
<private name="concept" type="String" value="B_Catalog"/>
<private name="dataModel" type="String" value="/common/receive/data"/>
<private name="fnModel" type="String"/>
<protected name="readOnly" type="String"/>
<protected name="notNull" type="String"/>
<public name="table" required="true" type="Table"/>
</action>
<action name="createB_CatalogAction" procedure="bizCreateProcedure"><private name="concept" type="String" value="B_Catalog"/>
<private name="fnModel" type="String"/>
<public name="table" required="true" type="Table"/>
<public name="defaultValues" type="Map"/>
</action>
<action name="queryB_ReceiveAction" procedure="bizQueryProcedure"><permission name="range" type="List"/>
<private name="concept" type="String" value="B_Receive"/>
<private name="select" type="String" value="B_Receive.*"/>
<private name="from" type="String" value="B_Receive B_Receive"/>
<private name="aggregate" type="String"/>
<private name="dataModel" type="String" value="/common/receive/data"/>
<private name="fnModel" type="String"/>
<protected name="condition" type="String"/>
<public name="distinct" type="Boolean" value="false"/>
<public name="idColumn" type="String" value="B_Receive"/>
<public name="filter" type="String"/>
<public name="limit" type="Integer"/>
<public name="offset" type="Integer"/>
<public name="columns" type="String"/>
<public name="orderBy" type="String"/>
<public name="aggregateColumns" type="String"/>
<public name="variables" type="Map"/>
</action>
<action name="saveB_ReceiveAction" procedure="bizSaveProcedure"><permission name="insertRange" type="List"/>
<permission name="deleteRange" type="List"/>
<permission name="updateRange" type="List"/>
<private name="concept" type="String" value="B_Receive"/>
<private name="dataModel" type="String" value="/common/receive/data"/>
<private name="fnModel" type="String"/>
<protected name="readOnly" type="String"/>
<protected name="notNull" type="String"/>
<public name="table" required="true" type="Table"/>
</action>
<action name="createB_ReceiveAction" procedure="bizCreateProcedure"><private name="concept" type="String" value="B_Receive"/>
<private name="fnModel" type="String"/>
<public name="table" required="true" type="Table"/>
<public name="defaultValues" type="Map"/>
</action>
<action global="false" name="setTreeCatalogAction" procedure="setTreeCatalogProcedure"><label language="zh_CN">目录添加业务功能</label>
<public name="activities" type="List"/><public name="processes" type="List"/>
<public name="activitiesFNames" type="List"/>
<public name="fBusinessGroupId" type="String"/>

<public type="Integer" name="flevel"></public>
</action>
<action global="false" name="setTreeReceiveAction" procedure="setTreeReceiveProcedure"><label language="zh_CN">收件信息添加业务功能</label>
<public name="fBusinessGroupId" type="String"/>
<public name="processes" type="List"/>
<public name="activitiesFNames" type="List"/>
<public name="activities" type="List"/>
</action>
</model>