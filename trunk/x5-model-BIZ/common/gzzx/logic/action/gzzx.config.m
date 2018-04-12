<?xml version="1.0" encoding="UTF-8"?>
<model xmlns="http://www.justep.com/model" xmlns:butone="http://www.butone.com">
	<config name="yuePiLingDaoRange"
		value="findPersonMembersInOrg(orgUnitsToOrgFIDs(findOrgUnitsByCode('TLD')),false)">
		<label language="zh_CN">领导阅批范围</label>
	</config>

	<config name="yuePiLingDaoDeptID"
		value="0A5FB56A36DF4550BD2B0109FE3EF5C2,6F17EB2709F54850A45B224EB94AA618">
		<label language="zh_CN">批阅领导部门ID，多个部门逗号分割</label>
	</config>

	<config name="yuePiProcessList" value="" butone:list="true">
		<label language="zh_CN">阅批业务process列表</label>
		<item name="/EGovSys/OaSys/OaBizs/LingdPsBiz/process/ldps/ldpsProcess" />
		<item name="/EGovSys/OaSys/OaBizs/LingdPyBiz/process/ldpy/ldpyProcess" />
	</config>
</model>