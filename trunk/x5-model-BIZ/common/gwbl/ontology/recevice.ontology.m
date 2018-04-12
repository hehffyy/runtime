<?xml version="1.0" encoding="UTF-8"?>
<model xmlns="http://www.justep.com/model">
<concept name="B_CommonlyUsed" default-value-expr="guid()"><has-relation relation="version" default-value-expr="0"><label language="zh_CN">版本</label>
</has-relation>
<label language="zh_CN">常用收件</label>
<has-relation relation="fProcess" data-type="String" size="200" required="true"></has-relation>
<has-relation relation="fActivity" data-type="String" size="50" required="true"></has-relation>
<has-relation relation="fURL" data-type="String" size="300" required="true"></has-relation>
<has-relation relation="fUserId" data-type="String" size="100" required="true" default-value-expr="currentPersonID()" unique="false"></has-relation>
<has-relation relation="fName" data-type="String" size="30" required="true" unique="false"></has-relation>
</concept>
<relation name="fProcess" data-type="String"><label language="zh_CN">流程</label>
</relation>
<relation name="fActivity" data-type="String"><label language="zh_CN">环节</label>
</relation>
<relation name="fURL" data-type="String"><label language="zh_CN">URL</label>
</relation>
<relation name="fUserId" data-type="String"><label language="zh_CN">用户ID</label>
</relation>
<relation name="fName" data-type="String"><label language="zh_CN">功能名称</label>
</relation>
<concept name="B_Recipient" default-value-expr="guid()"><has-relation relation="version" default-value-expr="0"><label language="zh_CN">版本</label>
</has-relation>
<label language="zh_CN">收件表</label>
<has-relation relation="fBillNo" data-type="String" size="50"></has-relation>
<has-relation relation="fPassword" data-type="String" size="20"></has-relation>
<has-relation relation="fBusinessName" data-type="String" size="100"></has-relation>
<has-relation relation="fBusinessType" data-type="String" size="50"></has-relation>
<has-relation relation="fBusinessSubType" data-type="String" size="50"></has-relation>
<has-relation relation="fRegion" data-type="String" size="50"></has-relation>
<has-relation relation="fTown" data-type="String" size="100"></has-relation>
<has-relation relation="fLandLocated" data-type="String" size="100"></has-relation>
<has-relation relation="fAppObligee" data-type="String" size="50"></has-relation>
<has-relation relation="fAppNature" data-type="String" size="50"></has-relation>
<has-relation relation="fAppCertificateType" data-type="String" size="50"></has-relation>
<has-relation relation="fAppCertificateNo" data-type="String" size="50"></has-relation>
<has-relation relation="fAppLegal" data-type="String" size="20"></has-relation>
<has-relation relation="fAppLinkman" data-type="String" size="20"></has-relation>
<has-relation relation="fAppTel" data-type="String" size="30"><label language="zh_CN">申请单位_电话或邮件</label>
</has-relation>
<has-relation relation="fAppCommunication" data-type="String" size="200"><label language="zh_CN">申请单位_通讯地址</label>
</has-relation>
<has-relation relation="fAppZipCode" data-type="String" size="20"><label language="zh_CN">申请单位_邮编</label>
</has-relation>
<has-relation relation="fAgentName" data-type="String" size="20"></has-relation>
<has-relation relation="fAgentCertificateType" data-type="String" size="50"></has-relation>
<has-relation relation="fAgentCertificateNo" data-type="String" size="50"></has-relation>
<has-relation relation="fAgentTel" data-type="String" size="20"></has-relation>
<has-relation relation="fSigner" data-type="String" size="20"></has-relation>
<has-relation relation="fSignDate" data-type="Date"></has-relation>
<has-relation relation="fRemark" data-type="Text"></has-relation>
<has-relation relation="fSourceType" data-type="String" size="10" default-value-expr="'窗口'"></has-relation>
<has-relation relation="fRecstatus" data-type="String" size="10" default-value-expr="'草拟'"></has-relation>


<has-relation relation="fProjectName" data-type="String" size="200"></has-relation>
<has-relation relation="fBizRecId" data-type="String" size="32"></has-relation>
</concept>
<relation name="fBillNo" data-type="String"><label language="zh_CN">收件编号</label>
</relation>
<relation name="fPassword" data-type="String"><label language="zh_CN">查询密码</label>
</relation>
<relation name="fBusinessName" data-type="String"><label language="zh_CN">业务名称</label>
</relation>
<relation name="fBusinessType" data-type="String"><label language="zh_CN">业务类型</label>
</relation>
<relation name="fBusinessSubType" data-type="String"><label language="zh_CN">业务子类型</label>
</relation>
<relation name="fRegion" data-type="String"><label language="zh_CN">行政区</label>
</relation>
<relation name="fTown" data-type="String"><label language="zh_CN">街镇</label>
</relation>
<relation name="fLandLocated" data-type="String"><label language="zh_CN">土地坐落</label>
</relation>
<relation name="fAppObligee" data-type="String"><label language="zh_CN">申请单位_权利人</label>
</relation>
<relation name="fAppNature" data-type="String"><label language="zh_CN">申请单位_单位性质</label>
</relation>
<relation name="fAppCertificateType" data-type="String"><label language="zh_CN">申请单位_证件种类</label>
</relation>
<relation name="fAppCertificateNo" data-type="String"><label language="zh_CN">申请单位_证件编号</label>
</relation>
<relation name="fAppLegal" data-type="String"><label language="zh_CN">申请单位_法人代表</label>
</relation>
<relation name="fAppLinkman" data-type="String"><label language="zh_CN">申请单位_联系人</label>
</relation>
<relation name="fAppTel" data-type="String"><label language="zh_CN">电话或邮件</label>
</relation>
<relation name="fAppCommunication" data-type="String"><label language="zh_CN">通讯地址</label>
</relation>
<relation name="fAppZipCode" data-type="String"><label language="zh_CN">邮编</label>
</relation>
<relation name="fAgentName" data-type="String"><label language="zh_CN">代理人名称</label>
</relation>
<relation name="fAgentCertificateType" data-type="String"><label language="zh_CN">代理人_证件种类</label>
</relation>
<relation name="fAgentCertificateNo" data-type="String"><label language="zh_CN">代理人_证件编号</label>
</relation>
<relation name="fAgentTel" data-type="String"><label language="zh_CN">代理人_联系电话</label>
</relation>
<relation name="fSigner" data-type="String"><label language="zh_CN">收件人签名</label>
</relation>
<relation name="fSignDate" data-type="String"><label language="zh_CN">受理时间</label>
</relation>
<relation name="fRemark" data-type="String"><label language="zh_CN">备注</label>
</relation>
<relation name="fSourceType" data-type="String"><label language="zh_CN">收件来源类型</label>
</relation>
<relation name="fRecstatus" data-type="String"><label language="zh_CN">状态</label>
</relation>
<relation name="fCreator" data-type="String"><label language="zh_CN">制定人</label>
</relation>
<relation name="fCreateTime" data-type="DateTime"><label language="zh_CN">制定时间</label>
</relation>
<relation name="fProjectName" data-type="String"><label language="zh_CN">项目名称</label>
</relation>
<relation name="fBizRecId" data-type="String"><label language="zh_CN">案卷编号</label>
</relation>
</model>