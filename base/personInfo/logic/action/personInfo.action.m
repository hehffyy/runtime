<?xml version="1.0" encoding="UTF-8" standalone="no"?><model xmlns="http://www.justep.com/model">
	<action name="queryB_PersonSignImageAction" procedure="bizQueryProcedure">
		<permission name="range" type="List"/>
		<private name="concept" type="String" value="B_PersonSignImage"/>
		<private name="select" type="String" value="B_PersonSignImage.*"/>
		<private name="from" type="String" value="B_PersonSignImage B_PersonSignImage"/>
		<private name="aggregate" type="String"/>
		<private name="dataModel" type="String" value="/base/personInfo/data"/>
		<private name="fnModel" type="String"/>
		<protected name="condition" type="String"/>
		<public name="distinct" type="Boolean" value="false"/>
		<public name="idColumn" type="String" value="B_PersonSignImage"/>
		<public name="filter" type="String"/>
		<public name="limit" type="Integer"/>
		<public name="offset" type="Integer"/>
		<public name="columns" type="String"/>
		<public name="orderBy" type="String" value="B_PersonSignImage.fLastUpdateTime asc"/>
		<public name="aggregateColumns" type="String"/>
		<public name="variables" type="Map"/>
	</action>
	<action name="saveB_PersonSignImageAction" procedure="bizSaveProcedure">
		<permission name="insertRange" type="List"/>
		<permission name="deleteRange" type="List"/>
		<permission name="updateRange" type="List"/>
		<private name="concept" type="String" value="B_PersonSignImage"/>
		<private name="dataModel" type="String" value="/base/personInfo/data"/>
		<private name="fnModel" type="String"/>
		<protected name="readOnly" type="String"/>
		<protected name="notNull" type="String"/>
		<public name="table" required="true" type="Table"/>
	</action>
	<action name="createB_PersonSignImageAction" procedure="bizCreateProcedure">
		<private name="concept" type="String" value="B_PersonSignImage"/>
		<private name="fnModel" type="String"/>
		<public name="table" required="true" type="Table"/>
		<public name="defaultValues" type="Map"/>
	</action>
	<action global="false" name="queryPersonSignImageInfoAction" procedure="queryPersonSignImageInfoProcedure">
		<label language="zh_CN">查询人员签名图片</label>
		<public name="personID" required="true" type="String"/>
	</action>

<action name="saveDeviceSignImageAction" global="true" procedure="saveDeviceSignImageProcedure"><label language="zh_CN">保存签名图片</label>
<public type="String" name="imageStr"></public>
</action>
</model>