<?xml version="1.0" encoding="UTF-8"?>
<model xmlns="http://www.justep.com/model">
<concept name="B_BzSq" default-value-expr="guid()" keys="FGUID"><has-relation relation="version" default-value-expr="0"><label language="zh_CN">版本</label>
</has-relation>
<label language="zh_CN">补正申请</label>
<has-relation relation="FGUID" data-type="String" ></has-relation><has-relation relation="fDeptId" data-type="String" default-value-expr="currentDeptID()"></has-relation>
<has-relation relation="fDeptName" data-type="String" default-value-expr="currentDeptName()"></has-relation>
<has-relation relation="fPersonId" data-type="String" default-value-expr="currentPersonID()" size="500"></has-relation>
<has-relation relation="fPersonName" data-type="String" default-value-expr="currentPersonName()"></has-relation>
<has-relation relation="fTime" data-type="DateTime" default-value-expr="currentDateTime()"></has-relation>
<has-relation relation="fReason" data-type="Text"></has-relation>
<has-relation relation="fBzId" data-type="String"></has-relation>

<has-relation relation="fBizRecId" data-type="String"></has-relation>

<has-relation relation="fPersonFid" data-type="String" size="500" default-value-expr="currentPersonMemberFID()"><label language="zh_CN">申请人FID</label>
</has-relation>
<has-relation relation="fBZLX" data-type="String" size="50"></has-relation>
</concept>
<relation name="fDeptId" data-type="String"><label language="zh_CN">申请部门ID</label>
</relation>
<relation name="fDeptName" data-type="String"><label language="zh_CN">申请部门</label>
</relation>
<relation name="fPersonId" data-type="String"><label language="zh_CN">申请人ID</label>
</relation>
<relation name="fPersonName" data-type="String"><label language="zh_CN">申请人</label>
</relation>
<relation name="fTime" data-type="DateTime"><label language="zh_CN">申请时间</label>
</relation>
<relation name="fReason" data-type="Text"><label language="zh_CN">申请原因</label>
</relation>
<relation name="fBzId" data-type="String"><label language="zh_CN">补正信息ID</label>
</relation>
<relation name="fPersonFid" data-type="String"><label language="zh_CN">申请人FID</label>
</relation>
<concept name="B_BzInfo" default-value-expr="guid()" keys="fBzId"><has-relation relation="version" default-value-expr="0"><label language="zh_CN">版本</label>
</has-relation>
<label language="zh_CN">补正信息</label>
<has-relation relation="fBzId" default-value-expr="guid()"></has-relation>
<has-relation relation="fBizRecId"></has-relation>
<has-relation relation="fDeptId"><label language="zh_CN">主办部门ID</label>
</has-relation>
<has-relation relation="fPersonId"><label language="zh_CN">主办人ID</label>
</has-relation>
<has-relation relation="fPersonName"><label language="zh_CN">主办人</label>
</has-relation>
<has-relation relation="fTaskId" data-type="String"></has-relation>
<has-relation relation="fStartTime" data-type="DateTime"></has-relation>
<has-relation relation="fFinishTime" data-type="DateTime"></has-relation>
<has-relation relation="fReason"><label language="zh_CN">补正原因</label>
</has-relation>
<has-relation relation="fReplyPerson" data-type="String" default-value-expr="currentDeptFName()"></has-relation>
<has-relation relation="fReply" data-type="Text"></has-relation>
<has-relation relation="fReplyTime" data-type="DateTime"></has-relation>
<has-relation relation="fReplyDocIds" data-type="Text"></has-relation>
<has-relation relation="fState" data-type="String" default-value-expr="'未完成'"></has-relation>
<has-relation relation="fKind" data-type="String" default-value-expr="'系统补正'"></has-relation>
<has-relation relation="fDoState" data-type="String" default-value-expr="'申请中'"></has-relation>
<has-relation relation="fSFGQ" default-value-expr="0"></has-relation>

</concept>
<relation name="fBizRecId" data-type="String"><label language="zh_CN">案卷编号</label>
</relation>
<relation name="fTaskId" data-type="String"><label language="zh_CN">任务编号</label>
</relation>
<relation name="fStartTime" data-type="DateTime"><label language="zh_CN">开始时间</label>
</relation>
<relation name="fFinishTime" data-type="DateTime"><label language="zh_CN">完成时间</label>
</relation>
<relation name="fReplyPerson" data-type="String"><label language="zh_CN">回复人</label>
</relation>
<relation name="fReply" data-type="Text"><label language="zh_CN">回复内容</label>
</relation>
<relation name="fReplyTime" data-type="DateTime"><label language="zh_CN">回复时间</label>
</relation>
<relation name="fReplyDocIds" data-type="Text"><label language="zh_CN">回复附件</label>
</relation>
<relation name="fState" data-type="String"><label language="zh_CN">状态</label>
</relation>
<relation name="fKind" data-type="String"><label language="zh_CN">补正类型</label>
</relation>
<relation name="fDoState" data-type="String"><label language="zh_CN">过程状态</label>
</relation>
<concept name="V_XMXX" default-value-expr="guid()" keys="FBIZRECID">
<label language="zh_CN">项目信息</label>
<has-relation relation="FBIZRECID" data-type="String" default-value-expr="32"></has-relation>
<has-relation relation="XMMC" data-type="String" default-value-expr="300"></has-relation>
<has-relation relation="YDDW" data-type="String" default-value-expr="300"></has-relation>
<has-relation relation="XMLX" data-type="String"></has-relation>
<has-relation relation="SBWH" data-type="String"></has-relation>
<has-relation relation="HSPROCESS" data-type="String" default-value-expr="200"></has-relation>

<has-relation relation="HSACTIVITY" data-type="String"></has-relation>
</concept>
<relation name="FBIZRECID" data-type="String"><label language="zh_CN">案卷编号</label>
</relation>
<relation name="XMMC" data-type="String"><label language="zh_CN">项目名称</label>
</relation>
<relation name="YDDW" data-type="String"><label language="zh_CN">用地单位</label>
</relation>
<relation name="XMLX" data-type="String"><label language="zh_CN">项目类型</label>
</relation>
<relation name="SBWH" data-type="String"><label language="zh_CN">上报文号</label>
</relation>
<relation name="fSFGQ" data-type="Integer"><label language="zh_CN">是否挂起</label>
</relation>
<relation name="fSQYYHZ" data-type="Text"><label language="zh_CN">申请原因汇总</label>
</relation>
<relation name="fSQRFID" data-type="String"><label language="zh_CN">申请人FID</label>
</relation>
<relation name="fBZLX" data-type="String"><label language="zh_CN">补正类型</label>
</relation>

<relation name="HSPROCESS" data-type="String"><label language="zh_CN">会审PROCESS</label>
</relation>

<relation name="HSACTIVITY" data-type="String"><label language="zh_CN">会审Activity</label>
</relation>
</model>