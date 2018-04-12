<?xml version="1.0" encoding="UTF-8" standalone="no"?><model xmlns="http://www.justep.com/model">
<action global="true" name="queryV_DeptRecSumAction" procedure="queryV_DeptRecSumProcedure"><permission name="range" type="List"/>
<private name="concept" type="String" value="V_DeptRecSum"/>
<private name="select" type="String" value="V_DeptRecSum.*"/>
<private name="from" type="String" value="V_DeptRecSum V_DeptRecSum"/>
<private name="aggregate" type="String"/>
<private name="dataModel" type="String" value="/common/recFuncs/data"/>
<private name="fnModel" type="String"/>
<protected name="condition" type="String"/>
<public name="distinct" type="Boolean" value="false"/>
<public name="idColumn" type="String" value="V_DeptRecSum"/>
<public name="filter" type="String"/>
<public name="limit" type="Integer"/>
<public name="offset" type="Integer"/>
<public name="columns" type="String"/>
<public name="orderBy" type="String"/>
<public name="aggregateColumns" type="String"/>
<public name="variables" type="Map"/>
</action>
<action global="true" name="getAllDepts" procedure="getAllDeptsProcedure"><label language="zh_CN">获得所有部门</label>
</action>
<action global="true" name="queryDeptRec" procedure="queryDeptRecProcedure">
<label language="zh_CN">查询部门案卷</label>
<private name="concept" type="String" value="B_BizRec"/>
<public name="idColumn" type="String" value="B_BizRec"/>
<private name="select" type="String" value="B_BizRec.*,B_BizRecAttr.FInComeDocName as FInComeDocName,B_BizRecAttr.FArchivesCode as FArchivesCode,B_BizRecAttr.fSerialNo as fSerialNo,B_BizRecAttr.FInComeDocOrg as FInComeDocOrg"/>
<private name="from" type="String" value="B_BizRec B_BizRec  optional  join B_BizRecAttr B_BizRecAttr on B_BizRec.fBizRecId = B_BizRecAttr.fBizRecId"/>
<protected name="condition" type="String"/>
<permission name="range" type="List"/>
<public name="filter" type="String"/>
<public name="distinct" type="Boolean" value="false"/>
<public name="offset" type="Integer"/>
<public name="limit" type="Integer"/>
<public name="columns" type="String"/>
<public name="orderBy" type="String" value="B_BizRec.fReceiveTime desc"/>
<private name="aggregate" type="String"/>
<public name="aggregateColumns" type="String"/>
<public name="variables" type="Map"/>
<private name="dataModel" type="String" value="/common/recFuncs/data"/>
<private name="fnModel" type="String"/>
</action>
<action name="queryB_ProcessOrderAction" procedure="bizQueryProcedure"><permission name="range" type="List"/>
<private name="concept" type="String" value="B_ProcessOrder"/>
<private name="select" type="String" value="B_ProcessOrder.*"/>
<private name="from" type="String" value="B_ProcessOrder B_ProcessOrder"/>
<private name="aggregate" type="String"/>
<private name="dataModel" type="String" value="/common/recFuncs/data"/>
<private name="fnModel" type="String"/>
<protected name="condition" type="String"/>
<public name="distinct" type="Boolean" value="false"/>
<public name="idColumn" type="String" value="B_ProcessOrder"/>
<public name="filter" type="String"/>
<public name="limit" type="Integer"/>
<public name="offset" type="Integer"/>
<public name="columns" type="String"/>
<public name="orderBy" type="String"/>
<public name="aggregateColumns" type="String"/>
<public name="variables" type="Map"/>
</action>
<action name="saveB_ProcessOrderAction" procedure="bizSaveProcedure"><permission name="insertRange" type="List"/>
<permission name="deleteRange" type="List"/>
<permission name="updateRange" type="List"/>
<private name="concept" type="String" value="B_ProcessOrder"/>
<private name="dataModel" type="String" value="/common/recFuncs/data"/>
<private name="fnModel" type="String"/>
<protected name="readOnly" type="String"/>
<protected name="notNull" type="String"/>
<public name="table" required="true" type="Table"/>
</action>
<action name="createB_ProcessOrderAction" procedure="bizCreateProcedure"><private name="concept" type="String" value="B_ProcessOrder"/>
<private name="fnModel" type="String"/>
<public name="table" required="true" type="Table"/>
<public name="defaultValues" type="Map"/>
</action>
</model>