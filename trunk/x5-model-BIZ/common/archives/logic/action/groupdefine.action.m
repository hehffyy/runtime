<?xml version="1.0" encoding="UTF-8" standalone="no"?><model xmlns="http://www.justep.com/model"><action name="queryB_BusinessGroupAction" procedure="bizQueryProcedure"><permission name="range" type="List"/>
<private name="concept" type="String" value="B_BusinessGroup"/>
<private name="select" type="String" value="B_BusinessGroup.*"/>
<private name="from" type="String" value="B_BusinessGroup B_BusinessGroup"/>
<private name="aggregate" type="String"/>
<private name="dataModel" type="String" value="/common/archives/data"/>
<private name="fnModel" type="String"/>
<protected name="condition" type="String"/>
<public name="distinct" type="Boolean" value="false"/>
<public name="idColumn" type="String" value="B_BusinessGroup"/>
<public name="filter" type="String"/>
<public name="limit" type="Integer"/>
<public name="offset" type="Integer"/>
<public name="columns" type="String"/>
<public name="orderBy" type="String" value="B_BusinessGroup.fGroupOrder asc"/>
<public name="aggregateColumns" type="String"/>
<public name="variables" type="Map"/>
</action>
<action name="saveB_BusinessGroupAction" procedure="bizSaveProcedure"><permission name="insertRange" type="List"/>
<permission name="deleteRange" type="List"/>
<permission name="updateRange" type="List"/>
<private name="concept" type="String" value="B_BusinessGroup"/>
<private name="dataModel" type="String" value="/common/archives/data"/>
<private name="fnModel" type="String"/>
<protected name="readOnly" type="String"/>
<protected name="notNull" type="String"/>
<public name="table" required="true" type="Table"/>
</action>
<action name="createB_BusinessGroupAction" procedure="bizCreateProcedure"><private name="concept" type="String" value="B_BusinessGroup"/>
<private name="fnModel" type="String"/>
<public name="table" required="true" type="Table"/>
<public name="defaultValues" type="Map"/>
</action>
<action name="queryB_GroupFuncAction" procedure="bizQueryProcedure"><permission name="range" type="List"/>
<private name="concept" type="String" value="B_GroupFunc"/>
<private name="select" type="String" value="B_GroupFunc.*"/>
<private name="from" type="String" value="B_GroupFunc B_GroupFunc"/>
<private name="aggregate" type="String"/>
<private name="dataModel" type="String" value="/common/archives/data"/>
<private name="fnModel" type="String"/>
<protected name="condition" type="String"/>
<public name="distinct" type="Boolean" value="false"/>
<public name="idColumn" type="String" value="B_GroupFunc"/>
<public name="filter" type="String"/>
<public name="limit" type="Integer"/>
<public name="offset" type="Integer"/>
<public name="columns" type="String"/>
<public name="orderBy" type="String" value="B_GroupFunc.fProcessOrder asc"/>
<public name="aggregateColumns" type="String"/>
<public name="variables" type="Map"/>
</action>
<action name="saveB_GroupFuncAction" procedure="bizSaveProcedure"><permission name="insertRange" type="List"/>
<permission name="deleteRange" type="List"/>
<permission name="updateRange" type="List"/>
<private name="concept" type="String" value="B_GroupFunc"/>
<private name="dataModel" type="String" value="/common/archives/data"/>
<private name="fnModel" type="String"/>
<protected name="readOnly" type="String"/>
<protected name="notNull" type="String"/>
<public name="table" required="true" type="Table"/>
</action>
<action name="createB_GroupFuncAction" procedure="bizCreateProcedure"><private name="concept" type="String" value="B_GroupFunc"/>
<private name="fnModel" type="String"/>
<public name="table" required="true" type="Table"/>
<public name="defaultValues" type="Map"/>
</action>
<action name="queryB_GroupFieldAction" procedure="bizQueryProcedure"><permission name="range" type="List"/>
<private name="concept" type="String" value="B_GroupField"/>
<private name="select" type="String" value="B_GroupField.*,1 as fChecked"/>
<private name="from" type="String" value="B_GroupField B_GroupField"/>
<private name="aggregate" type="String"/>
<private name="dataModel" type="String" value="/common/archives/data"/>
<private name="fnModel" type="String"/>
<protected name="condition" type="String"/>
<public name="distinct" type="Boolean" value="false"/>
<public name="idColumn" type="String" value="B_GroupField"/>
<public name="filter" type="String"/>
<public name="limit" type="Integer"/>
<public name="offset" type="Integer"/>
<public name="columns" type="String"/>
<public name="orderBy" type="String" value="B_GroupField.fFieldOrder asc, B_GroupField.fGroupIndex asc"/>
<public name="aggregateColumns" type="String"/>
<public name="variables" type="Map"/>
</action>
<action name="saveB_GroupFieldAction" procedure="bizSaveProcedure"><permission name="insertRange" type="List"/>
<permission name="deleteRange" type="List"/>
<permission name="updateRange" type="List"/>
<private name="concept" type="String" value="B_GroupField"/>
<private name="dataModel" type="String" value="/common/archives/data"/>
<private name="fnModel" type="String"/>
<protected name="readOnly" type="String"/>
<protected name="notNull" type="String"/>
<public name="table" required="true" type="Table"/>
</action>
<action name="createB_GroupFieldAction" procedure="bizCreateProcedure"><private name="concept" type="String" value="B_GroupField"/>
<private name="fnModel" type="String"/>
<public name="table" required="true" type="Table"/>
<public name="defaultValues" type="Map"/>
</action>
<action global="false" name="getBusinessTablesAction" procedure="getBusinessTablesProcedure"><label language="zh_CN">获取当前业务下的工作表</label>
<public name="fBusinessGroupId" type="String"/>
</action>
<action name="query_BusinessTablesAction" procedure="query_BusinessTablesProcedure"><permission name="range" type="List"/>
<private name="concept" type="String" value="B_GroupField"/>
<private name="select" type="String" value="B_GroupField.*,1 as fChecked"/>
<private name="from" type="String" value="B_GroupField B_GroupField"/>
<private name="aggregate" type="String"/>
<private name="dataModel" type="String" value="/common/archives/data"/>
<private name="fnModel" type="String"/>
<protected name="condition" type="String"/>
<public name="distinct" type="Boolean" value="false"/>
<public name="idColumn" type="String" value="B_GroupField"/>
<public name="filter" type="String"/>
<public name="limit" type="Integer"/>
<public name="offset" type="Integer"/>
<public name="columns" type="String"/>
<public name="orderBy" type="String"/>
<public name="aggregateColumns" type="String"/>
<public name="variables" type="Map"/>
<label language="zh_CN">获取当前业务的表字段信息</label>
</action>
<action global="false" name="setTreeFucnAction" procedure="setTreeFucnProcedure"><public name="processes" type="List"/>
<public name="activitiesFNames" type="List"/>
<public name="fBusinessGroupId" type="String"/>
</action>

<action global="true" name="reloadTaskCenterBizGroupAction" procedure="reloadTaskCenterBizGroupProcedure"><label language="zh_CN">重新加载任务中心业务分组</label>
<public name="groupId" type="String"/>
<public name="type" type="String"/>
</action>
<action name="querySysGroupFieldAction" procedure="querySysGroupFieldProcedure"><permission name="range" type="List"/>
<private name="concept" type="String" value="B_GroupField"/>
<private name="select" type="String" value="B_GroupField.*,1 as fChecked"/>
<private name="from" type="String" value="B_GroupField B_GroupField"/>
<private name="aggregate" type="String"/>
<private name="dataModel" type="String" value="/common/archives/data"/>
<private name="fnModel" type="String"/>
<protected name="condition" type="String"/>
<public name="distinct" type="Boolean" value="false"/>
<public name="idColumn" type="String" value="B_GroupField"/>
<public name="filter" type="String"/>
<public name="limit" type="Integer"/>
<public name="offset" type="Integer"/>
<public name="columns" type="String"/>
<public name="orderBy" type="String"/>
<public name="aggregateColumns" type="String"/>
<public name="variables" type="Map"/>
<label language="zh_CN">查询系统分组字段</label>
</action>
<action name="queryB_GroupDataPermissionAction" procedure="bizQueryProcedure"><permission name="range" type="List"/>
<private name="concept" type="String" value="B_GroupDataPermission"/>
<private name="select" type="String" value="B_GroupDataPermission.*"/>
<private name="from" type="String" value="B_GroupDataPermission B_GroupDataPermission"/>
<private name="aggregate" type="String"/>
<private name="dataModel" type="String" value="/common/archives/data"/>
<private name="fnModel" type="String"/>
<protected name="condition" type="String"/>
<public name="distinct" type="Boolean" value="false"/>
<public name="idColumn" type="String" value="B_GroupDataPermission"/>
<public name="filter" type="String"/>
<public name="limit" type="Integer"/>
<public name="offset" type="Integer"/>
<public name="columns" type="String"/>
<public name="orderBy" type="String"/>
<public name="aggregateColumns" type="String"/>
<public name="variables" type="Map"/>
</action>
<action name="saveB_GroupDataPermissionAction" procedure="bizSaveProcedure"><permission name="insertRange" type="List"/>
<permission name="deleteRange" type="List"/>
<permission name="updateRange" type="List"/>
<private name="concept" type="String" value="B_GroupDataPermission"/>
<private name="dataModel" type="String" value="/common/archives/data"/>
<private name="fnModel" type="String"/>
<protected name="readOnly" type="String"/>
<protected name="notNull" type="String"/>
<public name="table" required="true" type="Table"/>
</action>
<action name="createB_GroupDataPermissionAction" procedure="bizCreateProcedure"><private name="concept" type="String" value="B_GroupDataPermission"/>
<private name="fnModel" type="String"/>
<public name="table" required="true" type="Table"/>
<public name="defaultValues" type="Map"/>
</action>
</model>