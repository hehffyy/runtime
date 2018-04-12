<?xml version="1.0" encoding="UTF-8" standalone="no"?><model xmlns="http://www.justep.com/model">
<action global="false" name="shoujianAction" procedure="shoujianProcedure"><label language="zh_CN">收件功能</label>
</action>
<action global="true" name="preBizStartFlowAction" procedure="preBizStartFlowProcedure">

<label language="zh_CN">预收件收件</label>
<public name="fBizRecId" type="String"/>
<public name="fFlowBizRecId" type="String"/>
<public name="fBizProcess" type="String"/>
<public name="fActivity" type="String"/>
<public name="fExecutorexpr" type="String"/>
</action>
<action global="true" name="getActivityMappingAction" procedure="getActivityMappingProcedure">

<label language="zh_CN">获得配置映射</label>
<public name="fdjdlbh" type="String"/>
<public name="fdjxlbh" type="String"/>
<public name="fzmlmc" type="String"/>
</action>
<action global="true" name="djdlSelectAction" procedure="djdlSelectProcedure"><label language="zh_CN">登记大类</label>
</action>
<action global="true" name="qllxSelectAction" procedure="qllxSelectProcedure"><label language="zh_CN">权利类型</label>
</action>
<action name="queryB_V_DJDLAction" procedure="bizQueryProcedure"><permission name="range" type="List"/>
<private name="concept" type="String" value="B_V_YWLXJCB"/>
<private name="select" type="String" value="B_V_YWLXJCB.*"/>
<private name="from" type="String" value="B_V_YWLXJCB B_V_YWLXJCB"/>
<private name="aggregate" type="String"/>
<private name="dataModel" type="String" value="/bdc/gwbl/data"/>
<private name="fnModel" type="String"/>
<protected name="condition" type="String" value="B_V_YWLXJCB.DJLXLB = '登记类' AND B_V_YWLXJCB.SPERSONID =   :currentPersonID()"/>
<public name="distinct" type="Boolean" value="false"/>
<public name="idColumn" type="String" value="B_V_YWLXJCB"/>
<public name="filter" type="String"/>
<public name="limit" type="Integer"/>
<public name="offset" type="Integer"/>
<public name="columns" type="String"/>
<public name="orderBy" type="String" value="B_V_YWLXJCB.PX asc, B_V_YWLXJCB.FGUID asc"/>
<public name="aggregateColumns" type="String"/>
<public name="variables" type="Map"/>
<label language="zh_CN">登记大类</label>
</action>
<action name="queryB_V_QLLXAction" procedure="bizQueryProcedure"><permission name="range" type="List"/>
<private name="concept" type="String" value="B_V_YWLXJCB"/>
<private name="select" type="String" value="B_V_YWLXJCB.*"/>
<private name="from" type="String" value="B_V_YWLXJCB B_V_YWLXJCB"/>
<private name="aggregate" type="String"/>
<private name="dataModel" type="String" value="/bdc/gwbl/data"/>
<private name="fnModel" type="String"/>
<protected name="condition" type="String" value="B_V_YWLXJCB.DJLX = '登记小类' AND B_V_YWLXJCB.SPERSONID = :currentPersonID()"/>
<public name="distinct" type="Boolean" value="false"/>
<public name="idColumn" type="String" value="B_V_YWLXJCB"/>
<public name="filter" type="String"/>
<public name="limit" type="Integer"/>
<public name="offset" type="Integer"/>
<public name="columns" type="String"/>
<public name="orderBy" type="String" value="B_V_YWLXJCB.PX asc, B_V_YWLXJCB.FGUID asc"/>
<public name="aggregateColumns" type="String"/>
<public name="variables" type="Map"/>
<label language="zh_CN">权利类型</label>
</action>
<action name="queryB_V_YWSLBAction" procedure="bizQueryProcedure"><permission name="range" type="List"/>
<private name="concept" type="String" value="B_V_YWSLB"/>
<private name="select" type="String" value="B_V_YWSLB.*"/>
<private name="from" type="String" value="B_V_YWSLB B_V_YWSLB"/>
<private name="aggregate" type="String"/>
<private name="dataModel" type="String" value="/bdc/gwbl/data"/>
<private name="fnModel" type="String"/>
<protected name="condition" type="String" value="B_V_YWSLB.fBizName  = '权籍调查'"/>
<public name="distinct" type="Boolean" value="false"/>
<public name="idColumn" type="String" value="B_V_YWSLB"/>
<public name="filter" type="String"/>
<public name="limit" type="Integer"/>
<public name="offset" type="Integer"/>
<public name="columns" type="String"/>
<public name="orderBy" type="String"/>
<public name="aggregateColumns" type="String"/>
<public name="variables" type="Map"/>
</action>
<action name="saveB_V_YWSLB1Action" procedure="bizSaveProcedure"><permission name="insertRange" type="List"/>
<permission name="deleteRange" type="List"/>
<permission name="updateRange" type="List"/>
<private name="concept" type="String" value="B_V_YWSLB"/>
<private name="dataModel" type="String" value="/bdc/gwbl/data"/>
<private name="fnModel" type="String"/>
<protected name="readOnly" type="String"/>
<protected name="notNull" type="String"/>
<public name="table" required="true" type="Table"/>
</action>


<action global="false" name="shengchengsjAction" procedure="shengchengsjProcedure">

<label language="zh_CN">权籍生成业务收件</label>
<public name="ywh" type="String"/>
</action>
<action global="false" name="scActivityMappingAction" procedure="scActivityMappingProcedure">
<label language="zh_CN">生成映射关系</label>
</action>
<action name="queryB_V_YWSLBSJAction" procedure="bizQueryProcedure"><permission name="range" type="List"/>
<private name="concept" type="String" value="B_V_YWSLB"/>
<private name="select" type="String" value="B_V_YWSLB.*"/>
<private name="from" type="String" value="B_V_YWSLB B_V_YWSLB"/>
<private name="aggregate" type="String"/>
<private name="dataModel" type="String" value="/bdc/gwbl/data"/>
<private name="fnModel" type="String"/>
<protected name="condition" type="String" value="B_V_YWSLB.SLRY = :currentPersonName()"/>
<public name="distinct" type="Boolean" value="false"/>
<public name="idColumn" type="String" value="B_V_YWSLB"/>
<public name="filter" type="String"/>
<public name="limit" type="Integer"/>
<public name="offset" type="Integer"/>
<public name="columns" type="String"/>
<public name="orderBy" type="String"/>
<public name="aggregateColumns" type="String"/>
<public name="variables" type="Map"/>
</action>
<action name="queryT_DACXJLBAction" procedure="bizQueryProcedure"><permission name="range" type="List"/>
<private name="concept" type="String" value="T_DACXJLB"/>
<private name="select" type="String" value="T_DACXJLB.*"/>
<private name="from" type="String" value="T_DACXJLB T_DACXJLB"/>
<private name="aggregate" type="String"/>
<private name="dataModel" type="String" value="/bdc/gwbl/data"/>
<private name="fnModel" type="String"/>
<protected name="condition" type="String"/>
<public name="distinct" type="Boolean" value="false"/>
<public name="idColumn" type="String" value="T_DACXJLB"/>
<public name="filter" type="String"/>
<public name="limit" type="Integer"/>
<public name="offset" type="Integer"/>
<public name="columns" type="String"/>
<public name="orderBy" type="String"/>
<public name="aggregateColumns" type="String"/>
<public name="variables" type="Map"/>
</action>
<action name="saveT_DACXJLBAction" procedure="bizSaveProcedure"><permission name="insertRange" type="List"/>
<permission name="deleteRange" type="List"/>
<permission name="updateRange" type="List"/>
<private name="concept" type="String" value="T_DACXJLB"/>
<private name="dataModel" type="String" value="/bdc/gwbl/data"/>
<private name="fnModel" type="String"/>
<protected name="readOnly" type="String"/>
<protected name="notNull" type="String"/>
<public name="table" required="true" type="Table"/>
</action>
<action name="createT_DACXJLBAction" procedure="bizCreateProcedure"><private name="concept" type="String" value="T_DACXJLB"/>
<private name="fnModel" type="String"/>
<public name="table" required="true" type="Table"/>
<public name="defaultValues" type="Map"/>
</action>
<action name="queryV_DAYQLAction" procedure="bizQueryProcedure"><permission name="range" type="List"/>
<private name="concept" type="String" value="V_DAYQL"/>
<private name="select" type="String" value="V_DAYQL.*"/>
<private name="from" type="String" value="V_DAYQL V_DAYQL"/>
<private name="aggregate" type="String"/>
<private name="dataModel" type="String" value="/bdc/gwbl/data"/>
<private name="fnModel" type="String"/>
<protected name="condition" type="String"/>
<public name="distinct" type="Boolean" value="false"/>
<public name="idColumn" type="String" value="V_DAYQL"/>
<public name="filter" type="String"/>
<public name="limit" type="Integer"/>
<public name="offset" type="Integer"/>
<public name="columns" type="String"/>
<public name="orderBy" type="String"/>
<public name="aggregateColumns" type="String"/>
<public name="variables" type="Map"/>
<label language="zh_CN">原权利查询</label>
</action>





<action name="queryV_DACXYQL_NEWAction" procedure="bizQueryProcedure"><permission name="range" type="List"/>
<private name="concept" type="String" value="V_DACXYQL"/>
<private name="select" type="String" value="V_DACXYQL.*"/>
<private name="from" type="String" value="V_DACXYQL V_DACXYQL"/>
<private name="aggregate" type="String"/>
<private name="dataModel" type="String" value="/bdc/gwbl/data"/>
<private name="fnModel" type="String"/>
<protected name="condition" type="String"/>
<public name="distinct" type="Boolean" value="false"/>
<public name="idColumn" type="String" value="V_DACXYQL"/>
<public name="filter" type="String"/>
<public name="limit" type="Integer"/>
<public name="offset" type="Integer"/>
<public name="columns" type="String"/>
<public name="orderBy" type="String"/>
<public name="aggregateColumns" type="String"/>
<public name="variables" type="Map"/>
</action>
<action name="queryV_DACXYQL_OLDAction" procedure="bizQueryProcedure"><permission name="range" type="List"/>
<private name="concept" type="String" value="V_DACXYQL"/>
<private name="select" type="String" value="V_DACXYQL.*"/>
<private name="from" type="String" value="V_DACXYQL V_DACXYQL"/>
<private name="aggregate" type="String"/>
<private name="dataModel" type="String" value="/bdc/gwbl/data"/>
<private name="fnModel" type="String"/>
<protected name="condition" type="String" value="V_DACXYQL.FSTATUSNAME  = '办结'"/>
<public name="distinct" type="Boolean" value="false"/>
<public name="idColumn" type="String" value="V_DACXYQL"/>
<public name="filter" type="String"/>
<public name="limit" type="Integer"/>
<public name="offset" type="Integer"/>
<public name="columns" type="String"/>
<public name="orderBy" type="String"/>
<public name="aggregateColumns" type="String"/>
<public name="variables" type="Map"/>
</action>
<action name="queryB_V_ZMLXAction" procedure="bizQueryProcedure"><permission name="range" type="List"/>
<private name="concept" type="String" value="B_V_YWLXJCB"/>
<private name="select" type="String" value="B_V_YWLXJCB.*"/>
<private name="from" type="String" value="B_V_YWLXJCB B_V_YWLXJCB"/>
<private name="aggregate" type="String"/>
<private name="dataModel" type="String" value="/bdc/gwbl/data"/>
<private name="fnModel" type="String"/>
<protected name="condition" type="String" value="B_V_YWLXJCB.DJLXLB = '证明类' AND B_V_YWLXJCB.SPERSONID = :currentPersonID()"/>
<public name="distinct" type="Boolean" value="false"/>
<public name="idColumn" type="String" value="B_V_YWLXJCB"/>
<public name="filter" type="String"/>
<public name="limit" type="Integer"/>
<public name="offset" type="Integer"/>
<public name="columns" type="String"/>
<public name="orderBy" type="String" value="B_V_YWLXJCB.PX asc, B_V_YWLXJCB.FGUID asc"/>
<public name="aggregateColumns" type="String"/>
<public name="variables" type="Map"/>
</action>



<action name="queryB_V_DJQLLX_DJDLAction" procedure="bizQueryProcedure"><permission name="range" type="List"/>
<private name="concept" type="String" value="B_V_DJQLLX_BASE"/>
<private name="select" type="String" value="B_V_DJQLLX_BASE.*"/>
<private name="from" type="String" value="B_V_DJQLLX_BASE B_V_DJQLLX_BASE"/>
<private name="aggregate" type="String"/>
<private name="dataModel" type="String" value="/bdc/gwbl/data"/>
<private name="fnModel" type="String"/>
<protected name="condition" type="String" value="B_V_DJQLLX_BASE.KIND ='登记类'"/>
<public name="distinct" type="Boolean" value="false"/>
<public name="idColumn" type="String" value="B_V_DJQLLX_BASE"/>
<public name="filter" type="String"/>
<public name="limit" type="Integer"/>
<public name="offset" type="Integer"/>
<public name="columns" type="String"/>
<public name="orderBy" type="String"/>
<public name="aggregateColumns" type="String"/>
<public name="variables" type="Map"/>
</action>
<action name="queryB_V_DJQLLX_DJXLAction" procedure="bizQueryProcedure"><permission name="range" type="List"/>
<private name="concept" type="String" value="B_V_DJQLLX_BASE"/>
<private name="select" type="String" value="B_V_DJQLLX_BASE.*"/>
<private name="from" type="String" value="B_V_DJQLLX_BASE B_V_DJQLLX_BASE"/>
<private name="aggregate" type="String"/>
<private name="dataModel" type="String" value="/bdc/gwbl/data"/>
<private name="fnModel" type="String"/>
<protected name="condition" type="String" value="B_V_DJQLLX_BASE.KIND  = '权利'"/>
<public name="distinct" type="Boolean" value="false"/>
<public name="idColumn" type="String" value="B_V_DJQLLX_BASE"/>
<public name="filter" type="String"/>
<public name="limit" type="Integer"/>
<public name="offset" type="Integer"/>
<public name="columns" type="String"/>
<public name="orderBy" type="String"/>
<public name="aggregateColumns" type="String"/>
<public name="variables" type="Map"/>
</action>
<action name="queryB_V_DJQLLX_ZMLAction" procedure="bizQueryProcedure"><permission name="range" type="List"/>
<private name="concept" type="String" value="B_V_DJQLLX_BASE"/>
<private name="select" type="String" value="B_V_DJQLLX_BASE.*"/>
<private name="from" type="String" value="B_V_DJQLLX_BASE B_V_DJQLLX_BASE"/>
<private name="aggregate" type="String"/>
<private name="dataModel" type="String" value="/bdc/gwbl/data"/>
<private name="fnModel" type="String"/>
<protected name="condition" type="String" value="B_V_DJQLLX_BASE.KIND ='证明类'"/>
<public name="distinct" type="Boolean" value="false"/>
<public name="idColumn" type="String" value="B_V_DJQLLX_BASE"/>
<public name="filter" type="String"/>
<public name="limit" type="Integer"/>
<public name="offset" type="Integer"/>
<public name="columns" type="String"/>
<public name="orderBy" type="String"/>
<public name="aggregateColumns" type="String"/>
<public name="variables" type="Map"/>
</action>
<action name="queryB_ActivityMappingAction" procedure="bizQueryProcedure"><permission name="range" type="List"/>
<private name="concept" type="String" value="B_ActivityMapping"/>
<private name="select" type="String" value="B_ActivityMapping.*"/>
<private name="from" type="String" value="B_ActivityMapping B_ActivityMapping"/>
<private name="aggregate" type="String"/>
<private name="dataModel" type="String" value="/bdc/gwbl/data"/>
<private name="fnModel" type="String"/>
<protected name="condition" type="String"/>
<public name="distinct" type="Boolean" value="false"/>
<public name="idColumn" type="String" value="B_ActivityMapping"/>
<public name="filter" type="String"/>
<public name="limit" type="Integer"/>
<public name="offset" type="Integer"/>
<public name="columns" type="String"/>
<public name="orderBy" type="String"/>
<public name="aggregateColumns" type="String"/>
<public name="variables" type="Map"/>
</action>
<action name="saveB_ActivityMappingAction" procedure="bizSaveProcedure"><permission name="insertRange" type="List"/>
<permission name="deleteRange" type="List"/>
<permission name="updateRange" type="List"/>
<private name="concept" type="String" value="B_ActivityMapping"/>
<private name="dataModel" type="String" value="/bdc/gwbl/data"/>
<private name="fnModel" type="String"/>
<protected name="readOnly" type="String"/>
<protected name="notNull" type="String"/>
<public name="table" required="true" type="Table"/>
</action>
<action name="createB_ActivityMappingAction" procedure="bizCreateProcedure"><private name="concept" type="String" value="B_ActivityMapping"/>
<private name="fnModel" type="String"/>
<public name="table" required="true" type="Table"/>
<public name="defaultValues" type="Map"/>
</action>
</model>