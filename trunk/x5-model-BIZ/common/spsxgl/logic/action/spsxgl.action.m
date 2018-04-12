<?xml version="1.0" encoding="UTF-8" standalone="no"?><model xmlns="http://www.justep.com/model"><action name="queryT_SYS_SPSXXXAction" procedure="bizQueryProcedure"><permission name="range" type="List"/>
<private name="concept" type="String" value="T_SYS_SPSXXX"/>
<private name="select" type="String" value="T_SYS_SPSXXX.*"/>
<private name="from" type="String" value="T_SYS_SPSXXX T_SYS_SPSXXX"/>
<private name="aggregate" type="String"/>
<private name="dataModel" type="String" value="/common/spsxgl/data"/>
<private name="fnModel" type="String"/>
<protected name="condition" type="String"/>
<public name="distinct" type="Boolean" value="false"/>
<public name="idColumn" type="String" value="T_SYS_SPSXXX"/>
<public name="filter" type="String"/>
<public name="limit" type="Integer"/>
<public name="offset" type="Integer"/>
<public name="columns" type="String"/>
<public name="orderBy" type="String"/>
<public name="aggregateColumns" type="String"/>
<public name="variables" type="Map"/>
<label language="zh_CN">query审批事项信息</label>
</action>
<action name="saveT_SYS_SPSXXXAction" procedure="bizSaveProcedure"><permission name="insertRange" type="List"/>
<permission name="deleteRange" type="List"/>
<permission name="updateRange" type="List"/>
<private name="concept" type="String" value="T_SYS_SPSXXX"/>
<private name="dataModel" type="String" value="/common/spsxgl/data"/>
<private name="fnModel" type="String"/>
<protected name="readOnly" type="String"/>
<protected name="notNull" type="String"/>
<public name="table" required="true" type="Table"/>
<label language="zh_CN">save审批事项信息</label>
</action>
<action name="createT_SYS_SPSXXXAction" procedure="bizCreateProcedure"><private name="concept" type="String" value="T_SYS_SPSXXX"/>
<private name="fnModel" type="String"/>
<public name="table" required="true" type="Table"/>
<public name="defaultValues" type="Map"/>
<label language="zh_CN">create审批事项信息</label>
</action>
<action name="queryT_SYS_SPSXCLAction" procedure="bizQueryProcedure"><permission name="range" type="List"/>
<private name="concept" type="String" value="T_SYS_SPSXCL"/>
<private name="select" type="String" value="T_SYS_SPSXCL.*"/>
<private name="from" type="String" value="T_SYS_SPSXCL T_SYS_SPSXCL"/>
<private name="aggregate" type="String"/>
<private name="dataModel" type="String" value="/common/spsxgl/data"/>
<private name="fnModel" type="String"/>
<protected name="condition" type="String"/>
<public name="distinct" type="Boolean" value="false"/>
<public name="idColumn" type="String" value="T_SYS_SPSXCL"/>
<public name="filter" type="String"/>
<public name="limit" type="Integer"/>
<public name="offset" type="Integer"/>
<public name="columns" type="String"/>
<public name="orderBy" type="String"/>
<public name="aggregateColumns" type="String"/>
<public name="variables" type="Map"/>
<label language="zh_CN">query审批事项材料</label>
</action>
<action name="saveT_SYS_SPSXCLAction" procedure="bizSaveProcedure"><permission name="insertRange" type="List"/>
<permission name="deleteRange" type="List"/>
<permission name="updateRange" type="List"/>
<private name="concept" type="String" value="T_SYS_SPSXCL"/>
<private name="dataModel" type="String" value="/common/spsxgl/data"/>
<private name="fnModel" type="String"/>
<protected name="readOnly" type="String"/>
<protected name="notNull" type="String"/>
<public name="table" required="true" type="Table"/>
<label language="zh_CN">save审批事项材料</label>
</action>
<action name="createT_SYS_SPSXCLAction" procedure="bizCreateProcedure"><private name="concept" type="String" value="T_SYS_SPSXCL"/>
<private name="fnModel" type="String"/>
<public name="table" required="true" type="Table"/>
<public name="defaultValues" type="Map"/>
<label language="zh_CN">create审批事项材料</label>
</action>
<action name="queryT_SYS_SPSX_YWLCAction" procedure="bizQueryProcedure"><permission name="range" type="List"/>
<private name="concept" type="String" value="T_SYS_SPSX_YWLC"/>
<private name="select" type="String" value="T_SYS_SPSX_YWLC.*"/>
<private name="from" type="String" value="T_SYS_SPSX_YWLC T_SYS_SPSX_YWLC"/>
<private name="aggregate" type="String"/>
<private name="dataModel" type="String" value="/common/spsxgl/data"/>
<private name="fnModel" type="String"/>
<protected name="condition" type="String"/>
<public name="distinct" type="Boolean" value="false"/>
<public name="idColumn" type="String" value="T_SYS_SPSX_YWLC"/>
<public name="filter" type="String"/>
<public name="limit" type="Integer"/>
<public name="offset" type="Integer"/>
<public name="columns" type="String"/>
<public name="orderBy" type="String"/>
<public name="aggregateColumns" type="String"/>
<public name="variables" type="Map"/>
<label language="zh_CN">query审批事项业务流程</label>
</action>
<action name="saveT_SYS_SPSX_YWLCAction" procedure="bizSaveProcedure"><permission name="insertRange" type="List"/>
<permission name="deleteRange" type="List"/>
<permission name="updateRange" type="List"/>
<private name="concept" type="String" value="T_SYS_SPSX_YWLC"/>
<private name="dataModel" type="String" value="/common/spsxgl/data"/>
<private name="fnModel" type="String"/>
<protected name="readOnly" type="String"/>
<protected name="notNull" type="String"/>
<public name="table" required="true" type="Table"/>
<label language="zh_CN">save审批事项业务流程</label>
</action>
<action name="createT_SYS_SPSX_YWLCAction" procedure="bizCreateProcedure"><private name="concept" type="String" value="T_SYS_SPSX_YWLC"/>
<private name="fnModel" type="String"/>
<public name="table" required="true" type="Table"/>
<public name="defaultValues" type="Map"/>
<label language="zh_CN">create审批事项业务流程</label>
</action>
<action name="queryT_SYS_SXCL_YWCLAction" procedure="bizQueryProcedure"><permission name="range" type="List"/>
<private name="concept" type="String" value="T_SYS_SXCL_YWCL"/>
<private name="select" type="String" value="T_SYS_SXCL_YWCL.*"/>
<private name="from" type="String" value="T_SYS_SXCL_YWCL T_SYS_SXCL_YWCL"/>
<private name="aggregate" type="String"/>
<private name="dataModel" type="String" value="/common/spsxgl/data"/>
<private name="fnModel" type="String"/>
<protected name="condition" type="String"/>
<public name="distinct" type="Boolean" value="false"/>
<public name="idColumn" type="String" value="T_SYS_SXCL_YWCL"/>
<public name="filter" type="String"/>
<public name="limit" type="Integer"/>
<public name="offset" type="Integer"/>
<public name="columns" type="String"/>
<public name="orderBy" type="String"/>
<public name="aggregateColumns" type="String"/>
<public name="variables" type="Map"/>
<label language="zh_CN">query审批事项材料与业务材料对照</label>
</action>
<action name="saveT_SYS_SXCL_YWCLAction" procedure="bizSaveProcedure"><permission name="insertRange" type="List"/>
<permission name="deleteRange" type="List"/>
<permission name="updateRange" type="List"/>
<private name="concept" type="String" value="T_SYS_SXCL_YWCL"/>
<private name="dataModel" type="String" value="/common/spsxgl/data"/>
<private name="fnModel" type="String"/>
<protected name="readOnly" type="String"/>
<protected name="notNull" type="String"/>
<public name="table" required="true" type="Table"/>
<label language="zh_CN">save审批事项材料与业务材料对照</label>
</action>
<action name="createT_SYS_SXCL_YWCLAction" procedure="bizCreateProcedure"><private name="concept" type="String" value="T_SYS_SXCL_YWCL"/>
<private name="fnModel" type="String"/>
<public name="table" required="true" type="Table"/>
<public name="defaultValues" type="Map"/>
<label language="zh_CN">create审批事项材料与业务材料对照</label>
</action>

</model>