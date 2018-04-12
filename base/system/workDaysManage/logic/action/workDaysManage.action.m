<?xml version="1.0" encoding="UTF-8"?><model xmlns="http://www.justep.com/model"><action name="queryB_WorkDaysMangAction" procedure="bizQueryProcedure"><permission name="range" type="List"/>
<private name="concept" type="String" value="B_WorkDaysMang"/>
<private name="select" type="String" value="B_WorkDaysMang.*"/>
<private name="from" type="String" value="B_WorkDaysMang B_WorkDaysMang"/>
<private name="aggregate" type="String"/>
<private name="dataModel" type="String" value="/base/system/workDaysManage/data"/>
<private name="fnModel" type="String"/>
<protected name="condition" type="String"/>
<public name="distinct" type="Boolean" value="false"/>
<public name="idColumn" type="String" value="B_WorkDaysMang"/>
<public name="filter" type="String"/>
<public name="limit" type="Integer"/>
<public name="offset" type="Integer"/>
<public name="columns" type="String"/>
<public name="orderBy" type="String" value="B_WorkDaysMang.fDate asc"/>
<public name="aggregateColumns" type="String"/>
<public name="variables" type="Map"/>
<label language="zh_CN">工作日调整查询</label>
</action>
<action name="saveB_WorkDaysMangAction" procedure="bizSaveProcedure"><permission name="insertRange" type="List"/>
<permission name="deleteRange" type="List"/>
<permission name="updateRange" type="List"/>
<private name="concept" type="String" value="B_WorkDaysMang"/>
<private name="dataModel" type="String" value="/base/system/workDaysManage/data"/>
<private name="fnModel" type="String"/>
<protected name="readOnly" type="String"/>
<protected name="notNull" type="String"/>
<public name="table" required="true" type="Table"/>
<label language="zh_CN">工作日调整保存</label>
</action>
<action name="createB_WorkDaysMangAction" procedure="bizCreateProcedure"><private name="concept" type="String" value="B_WorkDaysMang"/>
<private name="fnModel" type="String"/>
<public name="table" required="true" type="Table"/>
<public name="defaultValues" type="Map"/>
<label language="zh_CN">工作日调整创建</label>
</action>
<action name="initWorkDaysAction" global="false" procedure="initWorkDaysProcedure"><public type="Integer" name="year"></public>
<label language="zh_CN">初始化工作日</label>
</action>
<action name="refreshWorkDayCahceAction" global="false" procedure="refreshWorkDayCahceProcedure"><public type="Integer" name="year"></public>
<public type="Integer" name="month"></public>
<label language="zh_CN">刷新工作日缓存</label>
</action>
</model>