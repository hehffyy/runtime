<?xml version="1.0" encoding="UTF-8" standalone="no"?><model xmlns="http://www.justep.com/model"><action global="true" name="queryB_AreaAction" procedure="bizQueryProcedure"><permission name="range" type="List"/>
<private name="concept" type="String" value="B_Area"/>
<private name="select" type="String" value="B_Area.*"/>
<private name="from" type="String" value="B_Area B_Area"/>
<private name="aggregate" type="String"/>
<private name="dataModel" type="String" value="/base/system/data"/>
<private name="fnModel" type="String"/>
<protected name="condition" type="String"/>
<public name="distinct" type="Boolean" value="false"/>
<public name="idColumn" type="String" value="B_Area"/>
<public name="filter" type="String"/>
<public name="limit" type="Integer"/>
<public name="offset" type="Integer"/>
<public name="columns" type="String"/>
<public name="orderBy" type="String"/>
<public name="aggregateColumns" type="String"/>
<public name="variables" type="Map"/>
<label language="zh_CN">区域查询</label>
</action>
<action global="false" name="saveB_AreaAction" procedure="bizSaveProcedure"><permission name="insertRange" type="List"/>
<permission name="deleteRange" type="List"/>
<permission name="updateRange" type="List"/>
<private name="concept" type="String" value="B_Area"/>
<private name="dataModel" type="String" value="/base/system/data"/>
<private name="fnModel" type="String"/>
<protected name="readOnly" type="String"/>
<protected name="notNull" type="String"/>
<public name="table" required="true" type="Table"/>
<label language="zh_CN">区域保存</label>
</action>
<action name="createB_AreaAction" procedure="bizCreateProcedure"><private name="concept" type="String" value="B_Area"/>
<private name="fnModel" type="String"/>
<public name="table" required="true" type="Table"/>
<public name="defaultValues" type="Map"/>
<label language="zh_CN">区域创建</label>
</action>

 

<action name="queryB_CurrentAreaAction" procedure="bizQueryProcedure" global="true"><permission name="range" type="List"/>
<private name="concept" type="String" value="B_Area"/>
<private name="select" type="String" value="B_Area.*"/>
<private name="from" type="String" value="B_Area B_Area"/>
<private name="aggregate" type="String"/>
<private name="dataModel" type="String" value="/base/system/data"/>
<private name="fnModel" type="String"/>
<protected name="condition" type="String" value="B_Area.fAreaCode = :currentAreaIdOrName( true) OR ( B_Area.fParentId = '441200' AND fLevel = 4 )"/>
<public name="distinct" type="Boolean" value="false"/>
<public name="idColumn" type="String" value="B_Area"/>
<public name="filter" type="String"/>
<public name="limit" type="Integer"/>
<public name="offset" type="Integer"/>
<public name="columns" type="String"/>
<public name="orderBy" type="String"/>
<public name="aggregateColumns" type="String"/>
<public name="variables" type="Map"/>
</action>
</model>