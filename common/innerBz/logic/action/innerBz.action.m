<?xml version="1.0" encoding="UTF-8" standalone="no"?><model xmlns="http://www.justep.com/model">


<action global="true" name="queryB_BzInfoAction" procedure="bizQueryProcedure"><permission name="range" type="List"/>
<private name="concept" type="String" value="B_BzInfo"/>
<private name="select" type="String" value="B_BzInfo.*"/>
<private name="from" type="String" value="B_BzInfo B_BzInfo"/>
<private name="aggregate" type="String"/>
<private name="dataModel" type="String" value="/common/innerBz/data"/>
<private name="fnModel" type="String"/>
<protected name="condition" type="String"/>
<public name="distinct" type="Boolean" value="false"/>
<public name="idColumn" type="String" value="B_BzInfo"/>
<public name="filter" type="String"/>
<public name="limit" type="Integer"/>
<public name="offset" type="Integer"/>
<public name="columns" type="String"/>
<public name="orderBy" type="String"/>
<public name="aggregateColumns" type="String"/>
<public name="variables" type="Map"/>
</action>
<action global="true" name="saveB_BzInfoAction" procedure="bizSaveProcedure"><permission name="insertRange" type="List"/>
<permission name="deleteRange" type="List"/>
<permission name="updateRange" type="List"/>
<private name="concept" type="String" value="B_BzInfo"/>
<private name="dataModel" type="String" value="/common/innerBz/data"/>
<private name="fnModel" type="String"/>
<protected name="readOnly" type="String"/>
<protected name="notNull" type="String"/>
<public name="table" required="true" type="Table"/>
</action>
<action global="true" name="createB_BzInfoAction" procedure="bizCreateProcedure"><private name="concept" type="String" value="B_BzInfo"/>
<private name="fnModel" type="String"/>
<public name="table" required="true" type="Table"/>
<public name="defaultValues" type="Map"/>
</action>
<action global="true" name="queryB_BzSqAction" procedure="bizQueryProcedure"><permission name="range" type="List"/>
<private name="concept" type="String" value="B_BzSq"/>
<private name="select" type="String" value="B_BzSq.*"/>
<private name="from" type="String" value="B_BzSq B_BzSq"/>
<private name="aggregate" type="String"/>
<private name="dataModel" type="String" value="/common/innerBz/data"/>
<private name="fnModel" type="String"/>
<protected name="condition" type="String"/>
<public name="distinct" type="Boolean" value="false"/>
<public name="idColumn" type="String" value="B_BzSq"/>
<public name="filter" type="String"/>
<public name="limit" type="Integer"/>
<public name="offset" type="Integer"/>
<public name="columns" type="String"/>
<public name="orderBy" type="String"/>
<public name="aggregateColumns" type="String"/>
<public name="variables" type="Map"/>
</action>
<action global="true" name="saveB_BzSqAction" procedure="bizSaveProcedure"><permission name="insertRange" type="List"/>
<permission name="deleteRange" type="List"/>
<permission name="updateRange" type="List"/>
<private name="concept" type="String" value="B_BzSq"/>
<private name="dataModel" type="String" value="/common/innerBz/data"/>
<private name="fnModel" type="String"/>
<protected name="readOnly" type="String"/>
<protected name="notNull" type="String"/>
<public name="table" required="true" type="Table"/>
</action>
<action global="true" name="createB_BzSqAction" procedure="bizCreateProcedure"><private name="concept" type="String" value="B_BzSq"/>
<private name="fnModel" type="String"/>
<public name="table" required="true" type="Table"/>
<public name="defaultValues" type="Map"/>
</action>
<action global="true" name="bjgzAction" procedure="bjgzProcedure"><public name="suspendInfo" type="String"/>
<label language="zh_CN">补交告知</label>

<public name="bizRecId" type="String"/>
<public name="isSuspend" type="String"/>

<public name="reason" type="String"/>
</action>
<action global="true" name="bjslAction" procedure="bjslProcedure"><label language="zh_CN">补交受理</label>
<public name="suspendInfo" type="String"/>
<public name="bizRecId" type="String"/>

</action>
<action global="true" name="queryV_XMXXAction" procedure="bizQueryProcedure"><permission name="range" type="List"/>
<private name="concept" type="String" value="V_XMXX"/>
<private name="select" type="String" value="V_XMXX.*"/>
<private name="from" type="String" value="V_XMXX V_XMXX"/>
<private name="aggregate" type="String"/>
<private name="dataModel" type="String" value="/common/innerBz/data"/>
<private name="fnModel" type="String"/>
<protected name="condition" type="String"/>
<public name="distinct" type="Boolean" value="false"/>
<public name="idColumn" type="String" value="V_XMXX"/>
<public name="filter" type="String"/>
<public name="limit" type="Integer"/>
<public name="offset" type="Integer"/>
<public name="columns" type="String"/>
<public name="orderBy" type="String"/>
<public name="aggregateColumns" type="String"/>
<public name="variables" type="Map"/>
</action>

<action global="true" name="queryB_BzInfoExAction" procedure="bizQueryProcedure"><permission name="range" type="List"/>
<private name="concept" type="String" value="B_BzInfo"/>
<private name="select" type="String" value="B_BzInfo.*,V_XMXX.XMMC as XMMC,V_XMXX.YDDW as YDDW,V_XMXX.XMLX as XMLX,V_XMXX.SBWH as SBWH,V_XMXX.HSPROCESS as HSPROCESS,V_XMXX.HSACTIVITY as HSACTIVITY"/>
<private name="from" type="String" value="B_BzInfo B_BzInfo  optional  join V_XMXX V_XMXX on B_BzInfo.fBizRecId = V_XMXX.FBIZRECID"/>
<private name="aggregate" type="String"/>
<private name="dataModel" type="String" value="/common/innerBz/data"/>
<private name="fnModel" type="String"/>
<protected name="condition" type="String"/>
<public name="distinct" type="Boolean" value="false"/>
<public name="idColumn" type="String" value="B_BzInfo"/>
<public name="filter" type="String"/>
<public name="limit" type="Integer"/>
<public name="offset" type="Integer"/>
<public name="columns" type="String"/>
<public name="orderBy" type="String"/>
<public name="aggregateColumns" type="String"/>
<public name="variables" type="Map"/>
</action>

<action global="true" name="startHsAction" procedure="startHsProcedure"><label language="zh_CN">启动会审</label>
<public name="bizrecId" type="String"/>
<public type="String" name="hsProcess"></public>
<public type="String" name="hsActivity"></public>
</action>
<action global="true" name="secondBzAction" procedure="secondBzProcedure"><label language="zh_CN">二次补正</label>
<public name="bizRecId" type="String"/>
<public type="String" name="reason"></public>
</action>
</model>