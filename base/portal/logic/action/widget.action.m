<?xml version="1.0" encoding="UTF-8" standalone="no"?><model xmlns="http://www.justep.com/model"><action name="queryB_WIDGET_GLAction" procedure="bizQueryProcedure"><permission name="range" type="List"/>
<private name="concept" type="String" value="B_WIDGET_GL"/>
<private name="select" type="String" value="B_WIDGET_GL.*"/>
<private name="from" type="String" value="B_WIDGET_GL B_WIDGET_GL"/>
<private name="aggregate" type="String"/>
<private name="dataModel" type="String" value="/base/portal/data"/>
<private name="fnModel" type="String"/>
<protected name="condition" type="String"/>
<public name="distinct" type="Boolean" value="false"/>
<public name="idColumn" type="String" value="B_WIDGET_GL"/>
<public name="filter" type="String"/>
<public name="limit" type="Integer"/>
<public name="offset" type="Integer"/>
<public name="columns" type="String"/>
<public name="orderBy" type="String"/>
<public name="aggregateColumns" type="String"/>
<public name="variables" type="Map"/>
<label language="zh_CN">wiget管理查询</label>
</action>
<action name="saveB_WIDGET_GLAction" procedure="bizSaveProcedure"><permission name="insertRange" type="List"/>
<permission name="deleteRange" type="List"/>
<permission name="updateRange" type="List"/>
<private name="concept" type="String" value="B_WIDGET_GL"/>
<private name="dataModel" type="String" value="/base/portal/data"/>
<private name="fnModel" type="String"/>
<protected name="readOnly" type="String"/>
<protected name="notNull" type="String"/>
<public name="table" required="true" type="Table"/>
<label language="zh_CN">wiget管理保存</label>
</action>
<action name="createB_WIDGET_GLAction" procedure="bizCreateProcedure"><private name="concept" type="String" value="B_WIDGET_GL"/>
<private name="fnModel" type="String"/>
<public name="table" required="true" type="Table"/>
<public name="defaultValues" type="Map"/>
<label language="zh_CN">wiget管理创建</label>
</action>
<action name="queryB_WIDGET_GLRYAction" procedure="bizQueryProcedure"><permission name="range" type="List"/>
<private name="concept" type="String" value="B_WIDGET_GLRY"/>
<private name="select" type="String" value="B_WIDGET_GLRY.*"/>
<private name="from" type="String" value="B_WIDGET_GLRY B_WIDGET_GLRY"/>
<private name="aggregate" type="String"/>
<private name="dataModel" type="String" value="/base/portal/data"/>
<private name="fnModel" type="String"/>
<protected name="condition" type="String"/>
<public name="distinct" type="Boolean" value="false"/>
<public name="idColumn" type="String" value="B_WIDGET_GLRY"/>
<public name="filter" type="String"/>
<public name="limit" type="Integer"/>
<public name="offset" type="Integer"/>
<public name="columns" type="String"/>
<public name="orderBy" type="String"/>
<public name="aggregateColumns" type="String"/>
<public name="variables" type="Map"/>
<label language="zh_CN">wiget关联信息表查询</label>
</action>
<action name="saveB_WIDGET_GLRYAction" procedure="bizSaveProcedure"><permission name="insertRange" type="List"/>
<permission name="deleteRange" type="List"/>
<permission name="updateRange" type="List"/>
<private name="concept" type="String" value="B_WIDGET_GLRY"/>
<private name="dataModel" type="String" value="/base/portal/data"/>
<private name="fnModel" type="String"/>
<protected name="readOnly" type="String"/>
<protected name="notNull" type="String"/>
<public name="table" required="true" type="Table"/>
<label language="zh_CN">wiget关联信息表保存</label>
</action>
<action name="createB_WIDGET_GLRYAction" procedure="bizCreateProcedure"><private name="concept" type="String" value="B_WIDGET_GLRY"/>
<private name="fnModel" type="String"/>
<public name="table" required="true" type="Table"/>
<public name="defaultValues" type="Map"/>
<label language="zh_CN">wiget关联信息表创建</label>
</action>

<action global="false" name="addWidgetRelationAction" procedure="addWidgetRelationProcedure"><label language="zh_CN">添加wiget关联对象</label>
<public name="fMBID" required="true" type="String"/>
<public name="orgInfo" required="true" type="String"/>




</action>
<action global="false" name="hasWidgetPermissionAction" procedure="hasWidgetPermissionProcedure"><label language="zh_CN">查询widget权限</label>
<public name="wid" type="String"/>
<public name="fMBNR" type="String"/>

</action>
<action global="true" name="getWidgetsToHomeAction" procedure="getWidgetsToHomeProcedure"><label language="zh_CN">个人首页信息获取</label>
</action>
<action name="getWidgetListAction" global="false" procedure="getWidgetListProcedure"><label language="zh_CN">xml获取widget列表信息</label>
<public type="String" name="portal"></public>
</action>
</model>