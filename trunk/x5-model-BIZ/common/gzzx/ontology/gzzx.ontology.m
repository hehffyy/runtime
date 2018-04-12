<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">
	<concept name="B_GongZuoZhuXian" default-value-expr="guid()"
		keys="FGUID">
		<has-relation relation="version" default-value-expr="0">
			<label language="zh_CN">版本</label>
		</has-relation>
		<label language="zh_CN">工作主线</label>
		<has-relation relation="FGUID" />
		<has-relation relation="fTitle" data-type="String" size="500"
			required="false" />
		<has-relation relation="fContent" data-type="Text" />
		<has-relation relation="fCreateTime" data-type="DateTime"
			default-value-expr="currentDateTime()" />
		<has-relation relation="fCreator" data-type="String"
			size="36" default-value-expr="currentPersonID()" required="true" />
		<has-relation relation="fCreatorName" data-type="String"
			size="64" default-value-expr="currentPersonName()" />
		<has-relation relation="fStatus" data-type="String" size="20"
			default-value-expr="'处理中'" required="true" />
		<has-relation relation="fSourceGuid" data-type="String"
			size="32" />
		<has-relation relation="fSourceType" data-type="String"
			size="20" />
		<has-relation relation="fParentGuid" data-type="String"
			size="32">
			<label language="zh_CN">上级主线</label>
		</has-relation>
		<has-relation relation="fAttachment" data-type="Text" />
	</concept>
	<relation name="fTitle" data-type="String" size="200">
		<label language="zh_CN">标题</label>
	</relation>
	<relation name="fContent" data-type="Text">
		<label language="zh_CN">内容</label>
	</relation>
	<relation name="fCreateTime" data-type="DateTime"
		default-value-expr="currentDateTime()">
		<label language="zh_CN">创建时间</label>
	</relation>
	<relation name="fCreator" data-type="String" size="36"
		default-value-expr="currentPersonID()">
		<label language="zh_CN">创建人</label>
	</relation>
	<relation name="fCreatorName" data-type="String" size="64"
		default-value-expr="currentPersonName()">
		<label language="zh_CN">创建人名称</label>
	</relation>
	<relation name="fStatus" data-type="String" size="20">
		<label language="zh_CN">状态</label>
	</relation>
	<relation name="fSourceGuid" data-type="String" size="32">
		<label language="zh_CN">来源GUID</label>
	</relation>
	<relation name="fSourceType" data-type="String" size="20">
		<label language="zh_CN">来源类型</label>
	</relation>
	<concept name="B_GongZuoHuoDong" default-value-expr="guid()"
		keys="FGUID">
		<has-relation relation="version" default-value-expr="0">
			<label language="zh_CN">版本</label>
		</has-relation>
		<has-relation relation="FGUID" />
		<has-relation relation="fActivityTitle" data-type="String"
			size="200" />
		<label language="zh_CN">工作活动</label>
		<has-relation relation="fHostOrg" data-type="String"
			size="100" />
		<has-relation relation="fHostOrg_DATA" data-type="String"
			size="1000" />
		<has-relation relation="FAssistedOrg" data-type="String"
			size="200" />
		<has-relation relation="fAssistedOrg_DATA" data-type="String"
			size="2048" />
		<has-relation relation="fActivityTarget" data-type="Text"
			size="2048" />
		<has-relation relation="fStartTime" data-type="DateTime"
			required="true" />
		<has-relation relation="fExpectedFinishDate" data-type="Date" />
		<has-relation relation="fActualFinishDate" data-type="Date" />
		<has-relation xmlns:butone="http://www.butone.com"
			relation="fActivityItem" data-type="String" size="32" required="true">
			<label language="zh_CN">活动事项ID</label>
		</has-relation>
		<has-relation xmlns:butone="http://www.butone.com"
			relation="fItemType" data-type="String" size="20" required="true">
			<label language="zh_CN">事项类型</label>
		</has-relation>
		<has-relation relation="fActivityStatus" data-type="String"
			size="20" required="true" />
		<has-relation relation="fZhuXian" data-type="B_GongZuoZhuXian"
			size="32" required="true" />
		<has-relation relation="fParentGuid" size="32">
			<label language="zh_CN">上级活动</label>
		</has-relation>
		<has-relation relation="fAttachment" />
		<has-relation relation="fItemViewUrl" data-type="String"
			required="true" />
		<has-relation relation="fItemName" data-type="String"
			required="false">
			<label language="zh_CN">事项名称</label>
		</has-relation>
		<has-relation relation="fOrder" data-type="Integer" />
		<has-relation relation="fPublish" data-type="String" />
		<has-relation relation="fLastChuLi" data-type="String"
			size="32" />
		<has-relation relation="fViewCount" data-type="Integer"
			default-value-expr="0"></has-relation>
		<has-relation relation="fChuLiCount" data-type="Integer"
			default-value-expr="0"></has-relation>
	</concept>
	<relation name="fHostOrg" data-type="String" size="50">
		<label language="zh_CN">主办机构</label>
	</relation>
	<relation name="fHostOrg_DATA" data-type="String" size="65">
		<label language="zh_CN">主办机构_DATA</label>
	</relation>
	<relation name="FAssistedOrg" data-type="String" size="2048">
		<label language="zh_CN">协办机构</label>
	</relation>
	<relation name="fAssistedOrg_DATA" data-type="String" size="2048">
		<label language="zh_CN">协办机构</label>
	</relation>
	<relation name="fActivityTarget" data-type="Text">
		<label language="zh_CN">活动目标</label>
	</relation>
	<relation name="fStartTime" data-type="DateTime">
		<label language="zh_CN">开始时间</label>
	</relation>
	<relation name="fExpectedFinishDate" data-type="Date">
		<label language="zh_CN">预期完成日期</label>
	</relation>
	<relation name="fActualFinishDate" data-type="Date">
		<label language="zh_CN">实际完成日期</label>
	</relation>
	<relation name="fItemType" data-type="String" size="20">
		<label language="zh_CN">事项类型(办件,阅件,通知,会议、领导批阅等)</label>
	</relation>
	<relation name="fActivityTitle" data-type="String" size="200">
		<label language="zh_CN">活动标题</label>
	</relation>
	<relation name="fActivityStatus" data-type="String" size="20">
		<label language="zh_CN">活动状态</label>
	</relation>
	<relation name="fActivityItem" data-type="String" size="32">
		<label language="zh_CN">活动事项ID(活动事项的主键,例如通知的ID)</label>
	</relation>
	<relation name="fZhuXian" data-type="String" size="32">
		<label language="zh_CN">主线</label>
	</relation>
	<relation name="fParentGuid" data-type="String" size="32">
		<label language="zh_CN">父GUID</label>
	</relation>
	<concept name="B_GuanZhuZhuXian" default-value-expr="guid()"
		keys="FGUID">
		<has-relation relation="version" default-value-expr="0">
			<label language="zh_CN">版本</label>
		</has-relation>
		<label language="zh_CN">关注的主线</label>
		<has-relation relation="FGUID" />
		<has-relation relation="fZhuXian" size="32" required="true" />
		<has-relation relation="fLevelId" data-type="String"
			size="1" required="true" />
		<has-relation relation="fLevelName" data-type="String"
			size="20" />
		<has-relation relation="fCreator" size="64"
			default-value-expr="currentPersonID()" required="true">
			<label language="zh_CN">关注人</label>
		</has-relation>
	</concept>
	<relation name="fLevelId" data-type="String" size="1">
		<label language="zh_CN">关注等级</label>
	</relation>
	<relation name="fLevelName" data-type="String" size="20">
		<label language="zh_CN">等级名称</label>
	</relation>
	<relation name="fAttachment" data-type="Text">
		<label language="zh_CN">附件</label>
	</relation>
	<relation name="fItemViewUrl" data-type="String" size="200">
		<label language="zh_CN">事项浏览URL</label>
	</relation>
	<relation name="fItemName" data-type="String" size="100">
		<label language="zh_CN">事项名称(业务名)</label>
	</relation>
	<concept name="B_HuoDongCuiBan" default-value-expr="guid()"
		keys="FGUID">
		<has-relation relation="version" default-value-expr="0">
			<label language="zh_CN">版本</label>
		</has-relation>
		<label language="zh_CN">活动催办信息</label>
		<has-relation relation="FGUID" />
		<has-relation relation="fCreateTime" />
		<has-relation relation="fCreator" required="true" />
		<has-relation relation="fCreatorName" />
		<has-relation relation="fContent" />
		<has-relation relation="fStatus" default-value-expr="'催办中'"
			required="true" />
		<has-relation relation="fZhuXian" required="true" />
		<has-relation relation="fHuoDong" data-type="String"
			size="32" required="true" />
		<has-relation relation="fActivityItem" required="true" />
	</concept>
	<relation name="fHuoDong" data-type="String">
		<label language="zh_CN">活动ID</label>
	</relation>
	<relation name="fOrder" data-type="Integer"
		default-value-expr="0">
		<label language="zh_CN">显示顺序</label>
	</relation>
	<concept name="B_HuoDongChuLi" default-value-expr="guid()"
		keys="FGUID">
		<has-relation relation="version" default-value-expr="0">
			<label language="zh_CN">版本</label>
		</has-relation>
		<label language="zh_CN">活动处理结果</label>
		<has-relation relation="FGUID" />
		<has-relation relation="fHuoDong" required="true" />
		<has-relation relation="fActivityItem" required="true">
			<label language="zh_CN">活动事项ID</label>
		</has-relation>
		<has-relation relation="fOrgUnitID" data-type="String"
			default-value-expr="currentPersonID()" />
		<has-relation relation="fOrgUnitName" data-type="String"
			default-value-expr="currentPersonName()" />
		<has-relation relation="fContent">
			<label language="zh_CN">处理意见</label>
		</has-relation>
		<has-relation relation="fCreateTime" />
		<has-relation relation="fFinishTime" data-type="DateTime" />
		<has-relation relation="fGlobalOrder" data-type="String"
			default-value-expr="getCurrentPersonGlobalSequence()" required="false" />
		<has-relation relation="fAttachment"></has-relation>
		<has-relation relation="fDeptName" data-type="String"
			default-value-expr="if(isNull(currentDeptName()),currentOrgName(),currentDeptName())" size="100">
			<label language="zh_CN">所属部门名称</label>
		</has-relation>
		<has-relation relation="fDeptID" data-type="String"
			default-value-expr="if(isNull(currentDeptID()),currentOrgID(),currentDeptID())" size="36"></has-relation>
	</concept>

	<concept name="B_HuoDongChuLi_H" keys="FGUID">
		<has-relation relation="version">
			<label language="zh_CN">版本</label>
		</has-relation>
		<label language="zh_CN">活动处理结果</label>
		<has-relation relation="FGUID" />
		<has-relation relation="fHuoDong" required="true" />
		<has-relation relation="fActivityItem" required="true">
			<label language="zh_CN">活动事项ID</label>
		</has-relation>
		<has-relation relation="fOrgUnitID" data-type="String" />
		<has-relation relation="fOrgUnitName" data-type="String" />
		<has-relation relation="fContent">
			<label language="zh_CN">处理意见</label>
		</has-relation>
		<has-relation relation="fCreateTime" />
		<has-relation relation="fFinishTime" data-type="DateTime" />
		<has-relation relation="fGlobalOrder" data-type="String"
			required="false" />
		<has-relation relation="fAttachment"></has-relation>
		<has-relation relation="fDeptName" data-type="String"
			size="100">
			<label language="zh_CN">所属部门名称</label>
		</has-relation>
		<has-relation relation="fDeptID" data-type="String" size="36"></has-relation>
	</concept>

	<relation name="fOrgUnitID" data-type="String" size="36">
		<label language="zh_CN">处理人</label>
	</relation>
	<relation name="fOrgUnitName" data-type="String" size="64">
		<label language="zh_CN">处理人名称</label>
	</relation>
	<relation name="fFinishTime" data-type="DateTime">
		<label language="zh_CN">完成时间</label>
	</relation>
	<relation name="fGlobalOrder" data-type="String" size="50">
		<label language="zh_CN">全局顺序</label>
	</relation>
	<relation name="fPublish" data-type="String"
		default-value-expr="'否'" size="2" required="false">
		<label language="zh_CN">公开的</label>
	</relation>
	<concept name="B_HuoDongRange" default-value-expr="guid()"
		keys="FGUID">
		<has-relation relation="version" default-value-expr="0">
			<label language="zh_CN">版本</label>
		</has-relation>
		<label language="zh_CN">活动参与者</label>
		<has-relation relation="FGUID" />
		<has-relation relation="fHuoDong" size="32" required="true" />
		<has-relation relation="fOrgUnitFID" data-type="String"
			required="true" />
		<has-relation relation="fOrgUnitFName" data-type="String"
			required="true" />
		<has-relation relation="fHandleKind" data-type="String"
			size="20" required="false" default-value-expr="'无'"></has-relation>
	</concept>
	<concept name="B_HuoDongRange_H" keys="FGUID">
		<has-relation relation="version">
			<label language="zh_CN">版本</label>
		</has-relation>
		<label language="zh_CN">活动参与者</label>
		<has-relation relation="FGUID" />
		<has-relation relation="fHuoDong" size="32" required="true" />
		<has-relation relation="fOrgUnitFID" data-type="String"
			required="true" />
		<has-relation relation="fOrgUnitFName" data-type="String"
			required="true" />
		<has-relation relation="fHandleKind" data-type="String"
			size="20" required="false" default-value-expr="'无'"></has-relation>
	</concept>
	<relation name="fOrgUnitFID" data-type="String" size="2048">
		<label language="zh_CN">参与者FID</label>
	</relation>
	<relation name="fOrgUnitFName" data-type="String" size="2048">
		<label language="zh_CN">参与者FName</label>
	</relation>
	<relation name="fLastChuLi" data-type="String" size="32">
		<label language="zh_CN">最后处理结果</label>
	</relation>
	<relation name="fViewCount" data-type="Integer"
		default-value-expr="0">
		<label language="zh_CN">浏览次数</label>
	</relation>
	<relation name="fChuLiCount" data-type="Integer"
		default-value-expr="0">
		<label language="zh_CN">处理数量</label>
	</relation>
	<relation name="fHandleKind" data-type="String">
		<label language="zh_CN">处理类型</label>
	</relation>
	<relation name="fDeptName" data-type="String">
		<label language="zh_CN">所属部门</label>
	</relation>
	<relation name="fDeptID" data-type="String">
		<label language="zh_CN">所属部门ID</label>
	</relation>
	<concept name="B_GongZuoHuoDong_H" keys="FGUID">
		<has-relation relation="version">
			<label language="zh_CN">版本</label>
		</has-relation>
		<has-relation relation="FGUID" />
		<has-relation relation="fActivityTitle" data-type="String"
			size="200" />
		<label language="zh_CN">工作活动</label>
		<has-relation relation="fHostOrg" data-type="String"
			size="100" />
		<has-relation relation="fHostOrg_DATA" data-type="String"
			size="1000" />
		<has-relation relation="FAssistedOrg" data-type="String"
			size="200" />
		<has-relation relation="fAssistedOrg_DATA" data-type="String"
			size="2048" />
		<has-relation relation="fActivityTarget" data-type="Text"
			size="2048" />
		<has-relation relation="fStartTime" data-type="DateTime"
			required="true" />
		<has-relation relation="fExpectedFinishDate" data-type="Date" />
		<has-relation relation="fActualFinishDate" data-type="Date" />
		<has-relation xmlns:butone="http://www.butone.com"
			relation="fActivityItem" data-type="String" size="32" required="true">
			<label language="zh_CN">活动事项ID</label>
		</has-relation>
		<has-relation xmlns:butone="http://www.butone.com"
			relation="fItemType" data-type="String" size="20" required="true">
			<label language="zh_CN">事项类型</label>
		</has-relation>
		<has-relation relation="fActivityStatus" data-type="String"
			size="20" required="true" />
		<has-relation relation="fZhuXian" data-type="B_GongZuoZhuXian"
			size="32" required="true" />
		<has-relation relation="fParentGuid" size="32">
			<label language="zh_CN">上级活动</label>
		</has-relation>
		<has-relation relation="fAttachment" />
		<has-relation relation="fItemViewUrl" data-type="String"
			required="true" />
		<has-relation relation="fItemName" data-type="String"
			required="false">
			<label language="zh_CN">事项名称</label>
		</has-relation>
		<has-relation relation="fOrder" data-type="Integer" />
		<has-relation relation="fPublish" data-type="String" />
		<has-relation relation="fLastChuLi" data-type="String"
			size="32" />
		<has-relation relation="fViewCount" data-type="Integer"></has-relation>
		<has-relation relation="fChuLiCount" data-type="Integer"></has-relation>
	</concept>
</model>
