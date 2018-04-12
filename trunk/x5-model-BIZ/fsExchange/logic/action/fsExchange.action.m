<?xml version="1.0" encoding="UTF-8" standalone="no"?><model xmlns="http://www.justep.com/model">
	<action name="queryEx_View_ShouLiAction" procedure="bizQueryProcedure">
		<permission name="range" type="List"/>
		<private name="concept" type="String" value="Ex_View_ShouLi"/>
		<private name="select" type="String" value="Ex_View_ShouLi.*"/>
		<private name="from" type="String" value="Ex_View_ShouLi Ex_View_ShouLi"/>
		<private name="aggregate" type="String"/>
		<private name="dataModel" type="String" value="/fsExchange/data"/>
		<private name="fnModel" type="String"/>
		<protected name="condition" type="String"/>
		<public name="distinct" type="Boolean" value="false"/>
		<public name="idColumn" type="String" value="Ex_View_ShouLi"/>
		<public name="filter" type="String"/>
		<public name="limit" type="Integer"/>
		<public name="offset" type="Integer"/>
		<public name="columns" type="String"/>
		<public name="orderBy" type="String"/>
		<public name="aggregateColumns" type="String"/>
		<public name="variables" type="Map"/>
		<label language="zh_CN">一体化受理视图查询</label>
	</action>
	<action name="queryEx_View_SPBAction" procedure="bizQueryProcedure">
		<permission name="range" type="List"/>
		<private name="concept" type="String" value="Ex_View_SPB"/>
		<private name="select" type="String" value="Ex_View_SPB.*"/>
		<private name="from" type="String" value="Ex_View_SPB Ex_View_SPB"/>
		<private name="aggregate" type="String"/>
		<private name="dataModel" type="String" value="/fsExchange/data"/>
		<private name="fnModel" type="String"/>
		<protected name="condition" type="String"/>
		<public name="distinct" type="Boolean" value="false"/>
		<public name="idColumn" type="String" value="Ex_View_SPB"/>
		<public name="filter" type="String"/>
		<public name="limit" type="Integer"/>
		<public name="offset" type="Integer"/>
		<public name="columns" type="String"/>
		<public name="orderBy" type="String"/>
		<public name="aggregateColumns" type="String"/>
		<public name="variables" type="Map"/>
	<label language="zh_CN">查询审批过程</label>
</action>
<action name="queryEx_View_ApplyAction" procedure="bizQueryProcedure"><permission name="range" type="List"/>
<private name="concept" type="String" value="Ex_View_Apply"/>
<private name="select" type="String" value="Ex_View_Apply.*"/>
<private name="from" type="String" value="Ex_View_Apply Ex_View_Apply"/>
<private name="aggregate" type="String"/>
<private name="dataModel" type="String" value="/fsExchange/data"/>
<private name="fnModel" type="String"/>
<protected name="condition" type="String"/>
<public name="distinct" type="Boolean" value="false"/>
<public name="idColumn" type="String" value="Ex_View_Apply"/>
<public name="filter" type="String"/>
<public name="limit" type="Integer"/>
<public name="offset" type="Integer"/>
<public name="columns" type="String"/>
<public name="orderBy" type="String"/>
<public name="aggregateColumns" type="String"/>
<public name="variables" type="Map"/>
<label language="zh_CN">查询业务申请表</label>
</action>
<action global="false" name="executeYTHExchangeAction" procedure="executeYTHExchangeProcedure"><label language="zh_CN">执行一体化数据交换</label>
<public name="bizRecId" type="String"/>
</action>
<action global="false" name="testYTHExhcangeAction" procedure="testYTHExhcangeProcedure"><label language="zh_CN">测试交换</label>
<public name="bizRecId" type="String"/>
</action>
</model>