<?xml version="1.0" encoding="utf-8" standalone="no"?><model xmlns="http://www.justep.com/model">
	<action name="queryMyAllZhuXianAction" procedure="bizQueryProcedure">
		<permission name="range" type="List"/>
		<private name="concept" type="String" value="B_GongZuoZhuXian"/>
		<private name="select" type="String" value="B.*,B_GuanZhuZhuXian.fLevelId as fLevelId"/>
		<private name="from" type="String" value="B_GongZuoZhuXian B  optional  join B_GuanZhuZhuXian B_GuanZhuZhuXian on B_GuanZhuZhuXian.fZhuXian = B.FGUID AND B_GuanZhuZhuXian.fCreator = :currentPersonID()"/>
		<private name="aggregate" type="String"/>
		<private name="dataModel" type="String" value="/common/gzzx/data"/>
		<private name="fnModel" type="String"/>
		<protected name="condition" type="String" value="B.fCreator = :currentPersonID() AND B.fStatus &lt;&gt; '已删除'"/>
		<public name="distinct" type="Boolean" value="false"/>
		<public name="idColumn" type="String" value="B"/>
		<public name="filter" type="String"/>
		<public name="limit" type="Integer"/>
		<public name="offset" type="Integer"/>
		<public name="columns" type="String"/>
		<public name="orderBy" type="String" value="B.fCreateTime desc"/>
		<public name="aggregateColumns" type="String"/>
		<public name="variables" type="Map"/>
		<label language="zh_CN">查询我的所有主线</label>
	</action>
	<action name="saveGongZuoZhuXianAction" procedure="bizSaveProcedure">
		<permission name="insertRange" type="List"/>
		<permission name="deleteRange" type="List"/>
		<permission name="updateRange" type="List"/>
		<private name="concept" type="String" value="B_GongZuoZhuXian"/>
		<private name="dataModel" type="String" value="/common/gzzx/data"/>
		<private name="fnModel" type="String"/>
		<protected name="readOnly" type="String"/>
		<protected name="notNull" type="String"/>
		<public name="table" required="true" type="Table"/>
		<label language="zh_CN">保存主线</label>
	</action>
	<action name="createGongZuoZhuXianAction" procedure="bizCreateProcedure">
		<private name="concept" type="String" value="B_GongZuoZhuXian"/>
		<private name="fnModel" type="String"/>
		<public name="table" required="true" type="Table"/>
		<public name="defaultValues" type="Map"/>
		<label language="zh_CN">创建主线</label>
	</action>
	<action name="queryGongZuoHuoDongAction" procedure="bizQueryProcedure">
		<permission name="range" type="List"/>
		<private name="concept" type="String" value="B_GongZuoHuoDong"/>
		<private name="select" type="String" value="B_GongZuoHuoDong.*"/>
		<private name="from" type="String" value="B_GongZuoHuoDong B_GongZuoHuoDong"/>
		<private name="aggregate" type="String"/>
		<private name="dataModel" type="String" value="/common/gzzx/data"/>
		<private name="fnModel" type="String"/>
		<protected name="condition" type="String"/>
		<public name="distinct" type="Boolean" value="false"/>
		<public name="idColumn" type="String" value="B_GongZuoHuoDong"/>
		<public name="filter" type="String"/>
		<public name="limit" type="Integer"/>
		<public name="offset" type="Integer"/>
		<public name="columns" type="String"/>
		<public name="orderBy" type="String" value="B_GongZuoHuoDong.fOrder asc"/>
		<public name="aggregateColumns" type="String"/>
		<public name="variables" type="Map"/>
		<label language="zh_CN">查询主线活动</label>
	</action>
	<action name="saveGongZuoHuoDongAction" procedure="bizSaveProcedure">
		<permission name="insertRange" type="List"/>
		<permission name="deleteRange" type="List"/>
		<permission name="updateRange" type="List"/>
		<private name="concept" type="String" value="B_GongZuoHuoDong"/>
		<private name="dataModel" type="String" value="/common/gzzx/data"/>
		<private name="fnModel" type="String"/>
		<protected name="readOnly" type="String"/>
		<protected name="notNull" type="String"/>
		<public name="table" required="true" type="Table"/>
		<label language="zh_CN">保存主线活动</label>
	</action>
	<action name="createGongZuoHuoDongAction" procedure="bizCreateProcedure">
		<private name="concept" type="String" value="B_GongZuoHuoDong"/>
		<private name="fnModel" type="String"/>
		<public name="table" required="true" type="Table"/>
		<public name="defaultValues" type="Map"/>
		<label language="zh_CN">创建主线活动</label>
	</action>
	<action global="false" name="setGaunZhuAction" procedure="setGaunZhuProcedure">
		<label language="zh_CN">设置关注</label>
		<public name="guid" type="String"/>
		<public name="level" type="String"/>
	</action>
	<action name="queryMyDeletedZhuXianAction" procedure="bizQueryProcedure">
		<permission name="range" type="List"/>
		<private name="concept" type="String" value="B_GongZuoZhuXian"/>
		<private name="select" type="String" value="B.*,B_GuanZhuZhuXian.fLevelId as fLevelId"/>
		<private name="from" type="String" value="B_GongZuoZhuXian B  optional  join B_GuanZhuZhuXian B_GuanZhuZhuXian on B_GuanZhuZhuXian.fZhuXian = B.FGUID AND B_GuanZhuZhuXian.fCreator = :currentPersonID()"/>
		<private name="aggregate" type="String"/>
		<private name="dataModel" type="String" value="/common/gzzx/data"/>
		<private name="fnModel" type="String"/>
		<protected name="condition" type="String" value="B.fStatus = '已删除'  and B.fCreator = :currentPersonID()"/>
		<public name="distinct" type="Boolean" value="false"/>
		<public name="idColumn" type="String" value="B"/>
		<public name="filter" type="String"/>
		<public name="limit" type="Integer"/>
		<public name="offset" type="Integer"/>
		<public name="columns" type="String"/>
		<public name="orderBy" type="String"/>
		<public name="aggregateColumns" type="String"/>
		<public name="variables" type="Map"/>
		<label language="zh_CN">查询我已删除的主线</label>
	</action>
	<action name="queryMyGuanZhuZhuXianAction" procedure="bizQueryProcedure">
		<permission name="range" type="List"/>
		<private name="concept" type="String" value="B_GongZuoZhuXian"/>
		<private name="select" type="String" value="B.*,B_GuanZhuZhuXian.fLevelId as fLevelId"/>
		<private name="from" type="String" value="B_GongZuoZhuXian B  join B_GuanZhuZhuXian B_GuanZhuZhuXian on B_GuanZhuZhuXian.fZhuXian = B.FGUID"/>
		<private name="aggregate" type="String"/>
		<private name="dataModel" type="String" value="/common/gzzx/data"/>
		<private name="fnModel" type="String"/>
		<protected name="condition" type="String" value="B_GuanZhuZhuXian.fCreator = :currentPersonID() and B.fStatus &lt;&gt; '已删除'"/>
		<public name="distinct" type="Boolean" value="false"/>
		<public name="idColumn" type="String" value="B"/>
		<public name="filter" type="String"/>
		<public name="limit" type="Integer"/>
		<public name="offset" type="Integer"/>
		<public name="columns" type="String"/>
		<public name="orderBy" type="String"/>
		<public name="aggregateColumns" type="String"/>
		<public name="variables" type="Map"/>
		<label language="zh_CN">查询我关注的主线</label>
	</action>
	<action name="queryHuoDongCuiBanAction" procedure="bizQueryProcedure">
		<permission name="range" type="List"/>
		<private name="concept" type="String" value="B_HuoDongCuiBan"/>
		<private name="select" type="String" value="B_HuoDongCuiBan.*"/>
		<private name="from" type="String" value="B_HuoDongCuiBan B_HuoDongCuiBan"/>
		<private name="aggregate" type="String"/>
		<private name="dataModel" type="String" value="/common/gzzx/data"/>
		<private name="fnModel" type="String"/>
		<protected name="condition" type="String"/>
		<public name="distinct" type="Boolean" value="false"/>
		<public name="idColumn" type="String" value="B_HuoDongCuiBan"/>
		<public name="filter" type="String"/>
		<public name="limit" type="Integer"/>
		<public name="offset" type="Integer"/>
		<public name="columns" type="String"/>
		<public name="orderBy" type="String"/>
		<public name="aggregateColumns" type="String"/>
		<public name="variables" type="Map"/>
		<label language="zh_CN">查询催办</label>
	</action>
	<action name="saveHuoDongCuiBanAction" procedure="bizSaveProcedure">
		<permission name="insertRange" type="List"/>
		<permission name="deleteRange" type="List"/>
		<permission name="updateRange" type="List"/>
		<private name="concept" type="String" value="B_HuoDongCuiBan"/>
		<private name="dataModel" type="String" value="/common/gzzx/data"/>
		<private name="fnModel" type="String"/>
		<protected name="readOnly" type="String"/>
		<protected name="notNull" type="String"/>
		<public name="table" required="true" type="Table"/>
		<label language="zh_CN">保存催办</label>
	</action>
	<action name="createHuoDongCuiBanAction" procedure="bizCreateProcedure">
		<private name="concept" type="String" value="B_HuoDongCuiBan"/>
		<private name="fnModel" type="String"/>
		<public name="table" required="true" type="Table"/>
		<public name="defaultValues" type="Map"/>
		<label language="zh_CN">创建催办</label>
	</action>
	<action name="queryHuoDongChuLiAction" procedure="bizQueryProcedure">
		<permission name="range" type="List"/>
		<private name="concept" type="String" value="B_HuoDongChuLi"/>
		<private name="select" type="String" value="B_HuoDongChuLi.*"/>
		<private name="from" type="String" value="B_HuoDongChuLi B_HuoDongChuLi"/>
		<private name="aggregate" type="String"/>
		<private name="dataModel" type="String" value="/common/gzzx/data"/>
		<private name="fnModel" type="String"/>
		<protected name="condition" type="String"/>
		<public name="distinct" type="Boolean" value="false"/>
		<public name="idColumn" type="String" value="B_HuoDongChuLi"/>
		<public name="filter" type="String"/>
		<public name="limit" type="Integer"/>
		<public name="offset" type="Integer"/>
		<public name="columns" type="String"/>
		<public name="orderBy" type="String" value="B_HuoDongChuLi.fGlobalOrder asc, B_HuoDongChuLi.fOrgUnitID asc, B_HuoDongChuLi.fFinishTime desc"/>
		<public name="aggregateColumns" type="String"/>
		<public name="variables" type="Map"/>
		<label language="zh_CN">查询活动处理情况</label>
	</action>
	<action name="queryMyYueJianAction" procedure="bizQueryProcedure">
		<permission name="range" type="List"/>
		<private name="concept" type="String" value="B_GongZuoHuoDong"/>
		<private name="select" type="String" value="B_GongZuoHuoDong.*,B_HuoDongChuLi.fOrgUnitName as fLastPerson,B_HuoDongChuLi.fFinishTime as fLastTime,PS_LingDao.fOrgUnitName as fLastLingDao,PS_LingDao.fFinishTime as fLastPSTime,B_GongZuoZhuXian.fContent as fContent,B_GongZuoZhuXian.fAttachment as fZhuXianAttachment,((select  case when count(1) = 0 then '未阅' else '已阅' end  from B_HuoDongChuLi B_HuoDongChuLi  where  B_GongZuoHuoDong.FGUID = B_HuoDongChuLi.fHuoDong AND B_HuoDongChuLi.fOrgUnitID = :currentPersonID() AND B_GongZuoHuoDong.fActivityItem = B_HuoDongChuLi.fActivityItem )) as fState"/>
		<private name="from" type="String" value="B_GongZuoHuoDong B_GongZuoHuoDong  optional  join B_HuoDongChuLi B_HuoDongChuLi on B_GongZuoHuoDong.fLastChuLi = B_HuoDongChuLi AND B_HuoDongChuLi.fHuoDong = B_GongZuoHuoDong optional  join B_GongZuoHuoDong HD_LingDao on HD_LingDao.fZhuXian = B_GongZuoHuoDong.fZhuXian AND HD_LingDao.fItemType = '领导批示' optional  join B_HuoDongChuLi PS_LingDao on PS_LingDao = HD_LingDao.fLastChuLi AND PS_LingDao.fHuoDong = HD_LingDao optional  join B_GongZuoZhuXian B_GongZuoZhuXian on B_GongZuoZhuXian = B_GongZuoHuoDong.fZhuXian"/>
		<private name="aggregate" type="String"/>
		<private name="dataModel" type="String" value="/common/gzzx/data"/>
		<private name="fnModel" type="String"/>
		<protected name="condition" type="String" value="B_GongZuoHuoDong.fItemType in ('阅件', '网上舆情')  AND ( B_GongZuoHuoDong.fPublish = '是' OR exists (select  1 from B_HuoDongRange B_HuoDongRange join SA_OPOrg SA_OPOrg on SA_OPOrg.sPersonID = :currentPersonID() and instr(SA_OPOrg.sFID, B_HuoDongRange.fOrgUnitFID) &gt; 0  where  B_GongZuoHuoDong.FGUID = B_HuoDongRange.fHuoDong) )"/>
		<public name="distinct" type="Boolean" value="false"/>
		<public name="idColumn" type="String" value="B_GongZuoHuoDong"/>
		<public name="filter" type="String"/>
		<public name="limit" type="Integer"/>
		<public name="offset" type="Integer"/>
		<public name="columns" type="String"/>
		<public name="orderBy" type="String" value="PS_LingDao.fFinishTime desc, B_HuoDongChuLi.fFinishTime desc, B_GongZuoHuoDong.fStartTime desc"/>
		<public name="aggregateColumns" type="String"/>
		<public name="variables" type="Map"/>
		<label language="zh_CN">查询我的阅件</label>
	</action>
	<action global="false" name="increaseHuoDongViewCountAction" procedure="increaseHuoDongViewCountProcedure">
		<label language="zh_CN">增加活动浏览次数</label>
		<public name="huodong" required="true" type="String"/>
		<public name="isView" type="Boolean"/>
	</action>
	<action name="queryZhuXianLingDaoPSAction" procedure="bizQueryProcedure">
		<permission name="range" type="List"/>
		<private name="concept" type="String" value="B_HuoDongChuLi"/>
		<private name="select" type="String" value="B_HuoDongChuLi.*"/>
		<private name="from" type="String" value="B_HuoDongChuLi B_HuoDongChuLi  join B_GongZuoHuoDong B_GongZuoHuoDong on B_HuoDongChuLi.fHuoDong = B_GongZuoHuoDong.FGUID"/>
		<private name="aggregate" type="String"/>
		<private name="dataModel" type="String" value="/common/gzzx/data"/>
		<private name="fnModel" type="String"/>
		<protected name="condition" type="String" value="B_GongZuoHuoDong.fZhuXian = :zhuXian and B_GongZuoHuoDong.fItemType='领导批示' AND B_HuoDongChuLi.fFinishTime is not null"/>
		<public name="distinct" type="Boolean" value="false"/>
		<public name="idColumn" type="String" value="B_HuoDongChuLi"/>
		<public name="filter" type="String"/>
		<public name="limit" type="Integer"/>
		<public name="offset" type="Integer"/>
		<public name="columns" type="String"/>
		<public name="orderBy" type="String" value="B_HuoDongChuLi.fGlobalOrder asc, B_HuoDongChuLi.fOrgUnitID asc, B_HuoDongChuLi.fFinishTime desc"/>
		<public name="aggregateColumns" type="String"/>
		<public name="variables" type="Map"/>
		<label language="zh_CN">查询主线的领导批示(无主线采用来源的主键)</label>
	</action>

	<action name="saveHuoDongChuLiAction" procedure="bizSaveProcedure">
		<permission name="insertRange" type="List"/>
		<permission name="deleteRange" type="List"/>
		<permission name="updateRange" type="List"/>
		<private name="concept" type="String" value="B_HuoDongChuLi"/>
		<private name="dataModel" type="String" value="/common/gzzx/data"/>
		<private name="fnModel" type="String"/>
		<protected name="readOnly" type="String"/>
		<protected name="notNull" type="String"/>
		<public name="table" required="true" type="Table"/>
	</action>
	<action name="createHuoDongChuLiAction" procedure="bizCreateProcedure">
		<private name="concept" type="String" value="B_HuoDongChuLi"/>
		<private name="fnModel" type="String"/>
		<public name="table" required="true" type="Table"/>
		<public name="defaultValues" type="Map"/>
	</action>
	<action global="true" name="getPermissionAction" procedure="getPermissionProcedure">
		<label language="zh_CN">获取gridFilter下拉信息</label>
	</action>
	<action name="queryMyYuejian2Action" procedure="queryMyYuejian2Procedure">
		<permission name="range" type="List"/>
		<private name="concept" type="String" value="B_GongZuoHuoDong"/>
		<private name="select" type="String" value="B_GongZuoHuoDong.*,(:ld) as fYuePiLingDao"/>
		<private name="from" type="String" value="B_GongZuoHuoDong B_GongZuoHuoDong"/>
		<private name="aggregate" type="String"/>
		<private name="dataModel" type="String" value="/common/gzzx/data"/>
		<private name="fnModel" type="String"/>
		<protected name="condition" type="String" value="exists (select  1 from B_HuoDongRange B_HuoDongRange join SA_OPOrg SA_OPOrg on  SA_OPOrg.sPersonID = :currentPersonID() AND instr(SA_OPOrg.sFID, B_HuoDongRange.fOrgUnitFID) &gt; 0   where B_GongZuoHuoDong.FGUID = B_HuoDongRange.fHuoDong)"/>
		<public name="distinct" type="Boolean" value="false"/>
		<public name="idColumn" type="String" value="B_GongZuoHuoDong"/>
		<public name="filter" type="String"/>
		<public name="limit" type="Integer"/>
		<public name="offset" type="Integer"/>
		<public name="columns" type="String"/>
		<public name="orderBy" type="String" value="B_GongZuoHuoDong.fStartTime desc"/>
		<public name="aggregateColumns" type="String"/>
		<public name="variables" type="Map"/>
		<label language="zh_CN">简易版我的阅件</label>
		<public name="state" type="String"/>
		<public name="itemType" type="String"/>
	</action>
	<action global="false" name="getHuoDongHandleKindAction" procedure="getHuoDongHandleKindProcedure">
		<label language="zh_CN">获得活动的处理方式</label>
		<public name="huodong" type="String"/>
	</action>
	<action global="false" name="queryLingDaoYuePiTaskAction" procedure="queryLingDaoYuePiTaskProcedure">
		<label language="zh_CN">查询领导的待办阅批任务</label>
		<public name="zhuxian" required="true" type="String"/>
	</action>

<action global="false" name="getPersonsOfDeptAction" procedure="getPersonsOfDeptProcedure"><label language="zh_CN">获得部门所有人员</label>
</action>
<action global="true" name="queryNoHandleYueJianMessageAction" procedure="queryNoHandleYueJianMessageProcedure"><label language="zh_CN">查询未处理的阅件消息</label>

</action>
<action global="false" name="queryHuoDongChuLiDeptListAction" procedure="queryHuoDongChuLiDeptListProcedure"><label language="zh_CN">查询活动处理部门列表</label>
<public name="huodong" type="String"/>
<public type="Boolean" name="includeCurrentDept"></public>
<public type="Boolean" name="includeLD"></public>
</action>
<action name="statisticsNoHandleYueJianCountAction" global="false" procedure="statisticsNoHandleYueJianCountProcedure"><label language="zh_CN">统计未处理阅件数量</label>
<public type="String" name="itemTypes"></public>
</action>
</model>