<?xml version="1.0" encoding="UTF-8" standalone="no"?><model xmlns="http://www.justep.com/model"><action name="queryB_UnitTypeAction" procedure="bizQueryProcedure"><permission name="range" type="List"/>
<private name="concept" type="String" value="B_UnitType"/>
<private name="select" type="String" value="B_UnitType.*"/>
<private name="from" type="String" value="B_UnitType B_UnitType"/>
<private name="aggregate" type="String"/>
<private name="dataModel" type="String" value="/base/system/data"/>
<private name="fnModel" type="String"/>
<protected name="condition" type="String"/>
<public name="distinct" type="Boolean" value="false"/>
<public name="idColumn" type="String" value="B_UnitType"/>
<public name="filter" type="String"/>
<public name="limit" type="Integer"/>
<public name="offset" type="Integer"/>
<public name="columns" type="String"/>
<public name="orderBy" type="String"/>
<public name="aggregateColumns" type="String"/>
<public name="variables" type="Map"/>
<label language="zh_CN">查询--单位类型信息</label>
</action>
<action name="saveB_UnitTypeAction" procedure="bizSaveProcedure"><permission name="insertRange" type="List"/>
<permission name="deleteRange" type="List"/>
<permission name="updateRange" type="List"/>
<private name="concept" type="String" value="B_UnitType"/>
<private name="dataModel" type="String" value="/base/system/data"/>
<private name="fnModel" type="String"/>
<protected name="readOnly" type="String"/>
<protected name="notNull" type="String"/>
<public name="table" required="true" type="Table"/>
<label language="zh_CN">保存--单位类型信息</label>
</action>
<action name="createB_UnitTypeAction" procedure="bizCreateProcedure"><private name="concept" type="String" value="B_UnitType"/>
<private name="fnModel" type="String"/>
<public name="table" required="true" type="Table"/>
<public name="defaultValues" type="Map"/>
<label language="zh_CN">创建--单位类型信息</label>
</action>
<action name="selectSysDictInfo" global="false" procedure="selectSysDictInfoProcedure">

</action>

</model>