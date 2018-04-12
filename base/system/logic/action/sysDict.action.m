<?xml version="1.0" encoding="utf-8" standalone="no"?>
<model xmlns="http://www.justep.com/model">
	<action name="querySysDictTypeAction" procedure="bizQueryProcedure">
		<permission name="range" type="List" />
		<private name="concept" type="String" value="SysDictType" />
		<private name="select" type="String" value="SysDictType.*" />
		<private name="from" type="String" value="SysDictType SysDictType" />
		<private name="aggregate" type="String" />
		<private name="dataModel" type="String" value="/base/system/data" />
		<private name="fnModel" type="String" />
		<protected name="condition" type="String" />
		<public name="distinct" type="Boolean" value="false" />
		<public name="idColumn" type="String" value="SysDictType" />
		<public name="filter" type="String" />
		<public name="limit" type="Integer" />
		<public name="offset" type="Integer" />
		<public name="columns" type="String" />
		<public name="orderBy" type="String" />
		<public name="aggregateColumns" type="String" />
		<public name="variables" type="Map" />
	</action>
	<action name="saveSysDictTypeAction" procedure="bizSaveProcedure">
		<permission name="insertRange" type="List" />
		<permission name="deleteRange" type="List" />
		<permission name="updateRange" type="List" />
		<private name="concept" type="String" value="SysDictType" />
		<private name="dataModel" type="String" value="/base/system/data" />
		<private name="fnModel" type="String" />
		<protected name="readOnly" type="String" />
		<protected name="notNull" type="String" />
		<public name="table" required="true" type="Table" />
	</action>
	<action name="createSysDictTypeAction" procedure="bizCreateProcedure">
		<private name="concept" type="String" value="SysDictType" />
		<private name="fnModel" type="String" />
		<public name="table" required="true" type="Table" />
		<public name="defaultValues" type="Map" />
	</action>
	<action global="false" name="querySysDictItemAction" procedure="bizQueryProcedure">
		<permission name="range" type="List" />
		<private name="concept" type="String" value="SysDictItem" />
		<private name="select" type="String" value="SysDictItem.*" />
		<private name="from" type="String" value="SysDictItem SysDictItem" />
		<private name="aggregate" type="String" />
		<private name="dataModel" type="String" value="/base/system/data" />
		<private name="fnModel" type="String" />
		<protected name="condition" type="String" />
		<public name="distinct" type="Boolean" value="false" />
		<public name="idColumn" type="String" value="SysDictItem" />
		<public name="filter" type="String" />
		<public name="limit" type="Integer" />
		<public name="offset" type="Integer" />
		<public name="columns" type="String" />
		<public name="orderBy" type="String" />
		<public name="aggregateColumns" type="String" />
		<public name="variables" type="Map" />
	</action>
	<action name="saveSysDictItemAction" procedure="bizSaveProcedure">
		<permission name="insertRange" type="List" />
		<permission name="deleteRange" type="List" />
		<permission name="updateRange" type="List" />
		<private name="concept" type="String" value="SysDictItem" />
		<private name="dataModel" type="String" value="/base/system/data" />
		<private name="fnModel" type="String" />
		<protected name="readOnly" type="String" />
		<protected name="notNull" type="String" />
		<public name="table" required="true" type="Table" />
	</action>
	<action name="createSysDictItemAction" procedure="bizCreateProcedure">
		<private name="concept" type="String" value="SysDictItem" />
		<private name="fnModel" type="String" />
		<public name="table" required="true" type="Table" />
		<public name="defaultValues" type="Map" />
	</action>
	<action name="queryB_sysParaAction" procedure="bizQueryProcedure">
		<permission name="range" type="List" />
		<private name="concept" type="String" value="B_sysPara" />
		<private name="select" type="String" value="B_sysPara.*" />
		<private name="from" type="String" value="B_sysPara B_sysPara" />
		<private name="aggregate" type="String" />
		<private name="dataModel" type="String" value="/base/system/data" />
		<private name="fnModel" type="String" />
		<protected name="condition" type="String" />
		<public name="distinct" type="Boolean" value="false" />
		<public name="idColumn" type="String" value="B_sysPara" />
		<public name="filter" type="String" />
		<public name="limit" type="Integer" />
		<public name="offset" type="Integer" />
		<public name="columns" type="String" />
		<public name="orderBy" type="String" />
		<public name="aggregateColumns" type="String" />
		<public name="variables" type="Map" />
	</action>
	<action name="saveB_sysParaAction" procedure="bizSaveProcedure">
		<permission name="insertRange" type="List" />
		<permission name="deleteRange" type="List" />
		<permission name="updateRange" type="List" />
		<private name="concept" type="String" value="B_sysPara" />
		<private name="dataModel" type="String" value="/base/system/data" />
		<private name="fnModel" type="String" />
		<protected name="readOnly" type="String" />
		<protected name="notNull" type="String" />
		<public name="table" required="true" type="Table" />
	</action>
	<action name="createB_sysParaAction" procedure="bizCreateProcedure">
		<private name="concept" type="String" value="B_sysPara" />
		<private name="fnModel" type="String" />
		<public name="table" required="true" type="Table" />
		<public name="defaultValues" type="Map" />
	</action>
	<action global="true" name="queryB_RtCfgAction" procedure="bizQueryProcedure">
		<permission name="range" type="List" />
		<private name="concept" type="String" value="B_RtCfg" />
		<private name="select" type="String" value="B_RtCfg.*" />
		<private name="from" type="String" value="B_RtCfg B_RtCfg" />
		<private name="aggregate" type="String" />
		<private name="dataModel" type="String" value="/base/system/data" />
		<private name="fnModel" type="String" />
		<protected name="condition" type="String" />
		<public name="distinct" type="Boolean" value="false" />
		<public name="idColumn" type="String" value="B_RtCfg" />
		<public name="filter" type="String" />
		<public name="limit" type="Integer" />
		<public name="offset" type="Integer" />
		<public name="columns" type="String" />
		<public name="orderBy" type="String" />
		<public name="aggregateColumns" type="String" />
		<public name="variables" type="Map" />
	</action>
	<action global="true" name="saveB_RtCfgAction" procedure="bizSaveProcedure">
		<permission name="insertRange" type="List" />
		<permission name="deleteRange" type="List" />
		<permission name="updateRange" type="List" />
		<private name="concept" type="String" value="B_RtCfg" />
		<private name="dataModel" type="String" value="/base/system/data" />
		<private name="fnModel" type="String" />
		<protected name="readOnly" type="String" />
		<protected name="notNull" type="String" />
		<public name="table" required="true" type="Table" />
	</action>
	<action global="true" name="createB_RtCfgAction" procedure="bizCreateProcedure">
		<private name="concept" type="String" value="B_RtCfg" />
		<private name="fnModel" type="String" />
		<public name="table" required="true" type="Table" />
		<public name="defaultValues" type="Map" />
	</action>
	<action name="queryB_SystemUpdateLogAction" procedure="bizQueryProcedure">
		<permission name="range" type="List" />
		<private name="concept" type="String" value="B_SystemUpdateLog" />
		<private name="select" type="String" value="B_SystemUpdateLog.*" />
		<private name="from" type="String"
			value="B_SystemUpdateLog B_SystemUpdateLog" />
		<private name="aggregate" type="String" />
		<private name="dataModel" type="String" value="/base/system/data" />
		<private name="fnModel" type="String" />
		<protected name="condition" type="String" />
		<public name="distinct" type="Boolean" value="false" />
		<public name="idColumn" type="String" value="B_SystemUpdateLog" />
		<public name="filter" type="String" />
		<public name="limit" type="Integer" />
		<public name="offset" type="Integer" />
		<public name="columns" type="String" />
		<public name="orderBy" type="String" />
		<public name="aggregateColumns" type="String" />
		<public name="variables" type="Map" />
	</action>
	<action name="saveB_SystemUpdateLogAction" procedure="bizSaveProcedure">
		<permission name="insertRange" type="List" />
		<permission name="deleteRange" type="List" />
		<permission name="updateRange" type="List" />
		<private name="concept" type="String" value="B_SystemUpdateLog" />
		<private name="dataModel" type="String" value="/base/system/data" />
		<private name="fnModel" type="String" />
		<protected name="readOnly" type="String" />
		<protected name="notNull" type="String" />
		<public name="table" required="true" type="Table" />
	</action>
	<action name="createB_SystemUpdateLogAction" procedure="bizCreateProcedure">
		<private name="concept" type="String" value="B_SystemUpdateLog" />
		<private name="fnModel" type="String" />
		<public name="table" required="true" type="Table" />
		<public name="defaultValues" type="Map" />
	</action>
</model>