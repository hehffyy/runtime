<?xml version="1.0" encoding="UTF-8" standalone="no"?><model xmlns="http://www.justep.com/model"><action global="true" name="queryB_RecHandAction" procedure="bizQueryProcedure"><permission name="range" type="List"/>
<private name="concept" type="String" value="B_RecHand"/>
<private name="select" type="String" value="B_RecHand.*"/>
<private name="from" type="String" value="B_RecHand B_RecHand"/>
<private name="aggregate" type="String"/>
<private name="dataModel" type="String" value="/base/core/flowOperation/data"/>
<private name="fnModel" type="String"/>
<protected name="condition" type="String"/>
<public name="distinct" type="Boolean" value="false"/>
<public name="idColumn" type="String" value="B_RecHand"/>
<public name="filter" type="String"/>
<public name="limit" type="Integer"/>
<public name="offset" type="Integer"/>
<public name="columns" type="String"/>
<public name="orderBy" type="String"/>
<public name="aggregateColumns" type="String"/>
<public name="variables" type="Map"/>
</action>


<action global="true" name="queryB_RecHandDetailAction" procedure="bizQueryProcedure"><permission name="range" type="List"/>
<private name="concept" type="String" value="B_RecHandDetail"/>
<private name="select" type="String" value="B_RecHandDetail.*"/>
<private name="from" type="String" value="B_RecHandDetail B_RecHandDetail"/>
<private name="aggregate" type="String"/>
<private name="dataModel" type="String" value="/base/core/flowOperation/data"/>
<private name="fnModel" type="String"/>
<protected name="condition" type="String"/>
<public name="distinct" type="Boolean" value="false"/>
<public name="idColumn" type="String" value="B_RecHandDetail"/>
<public name="filter" type="String"/>
<public name="limit" type="Integer"/>
<public name="offset" type="Integer"/>
<public name="columns" type="String"/>
<public name="orderBy" type="String"/>
<public name="aggregateColumns" type="String"/>
<public name="variables" type="Map"/>
</action>
<action name="queryB_RecHandDetailExAction" global="true" procedure="queryB_RecHandDetailExProcedure"><private name="concept" type="String" value="B_RecHandDetail"></private>
<public name="idColumn" type="String" value="B_RecHandDetail"></public>
<private name="select" type="String" value="B_RecHandDetail.*"></private>
<private name="from" type="String" value="B_RecHandDetail B_RecHandDetail"></private>
<protected name="condition" type="String"></protected>
<permission name="range" type="List"></permission>
<public name="filter" type="String"></public>
<public name="distinct" type="Boolean" value="false"></public>
<public name="offset" type="Integer"></public>
<public name="limit" type="Integer"></public>
<public name="columns" type="String"></public>
<public name="orderBy" type="String"></public>
<private name="aggregate" type="String"></private>
<public name="aggregateColumns" type="String"></public>
<public name="variables" type="Map"></public>
<private name="dataModel" type="String" value="/base/core/flowOperation/data"></private>
<private name="fnModel" type="String"></private>
</action>
<action name="queryBatchRecInfo" global="true" procedure="queryBatchRecInfoProcedure"><public type="String" name="bizRecId"></public>
</action>
<action name="handRecs" global="true" procedure="handRecsProcedure"><public type="String" name="bizgroup"></public><public type="List" name="list"></public>

<public type="String" name="handcode"></public>
<public type="String" name="kind"></public>
</action>
<action name="queryHandListByCode" global="true" procedure="queryHandListByCodeProcedure"><public type="String" name="handCode"></public>
<label language="zh_CN">查询移交案卷</label>
</action>
</model>