<?xml version="1.0" encoding="UTF-8"?>
<model xmlns="http://www.justep.com/model">
<concept name="B_receivePersonTemplate" default-value-expr="guid()"><has-relation relation="version" default-value-expr="0"><label language="zh_CN">版本</label>
</has-relation>
<label language="zh_CN">接收人模版</label>
<has-relation relation="fPersonID" data-type="String" size="32"></has-relation>
<has-relation relation="fPersonName" data-type="String" size="20"></has-relation>
<has-relation relation="fPhone" data-type="String" size="15"></has-relation>
<has-relation relation="fTemplateID" data-type="B_personPhoneTemplate" size="32"></has-relation>
</concept>
<relation name="fPersonID" data-type="String"><label language="zh_CN">人员ID</label>
</relation>
<relation name="fPersonName" data-type="String"><label language="zh_CN">名称</label>
</relation>
<relation name="fPhone" data-type="String"><label language="zh_CN">电话</label>
</relation>

<concept name="B_personPhoneTemplate" default-value-expr="guid()"><has-relation relation="version" default-value-expr="0"><label language="zh_CN">版本</label>
</has-relation>
<label language="zh_CN">电话模版</label>
<has-relation relation="fTemplateName" data-type="String"></has-relation>
<has-relation relation="fCreateTime"></has-relation>
<has-relation relation="fCreatePersonID" data-type="String" size="32" default-value-expr="currentPersonID()"></has-relation>
<has-relation relation="fCreateName" data-type="String" size="20" default-value-expr="currentPersonName()"></has-relation>
</concept>
<relation name="fTemplateName" data-type="String"><label language="zh_CN">模版名称</label>
</relation>
<relation name="fTemplateID" data-type="String"><label language="zh_CN">模版ID</label>
</relation>
<concept name="B_smsInfo" default-value-expr="guid()"><has-relation relation="version" default-value-expr="0"><label language="zh_CN">版本</label>
</has-relation>
<label language="zh_CN">短息信息</label>
<has-relation relation="fSMSContent" data-type="String" size="500"></has-relation>
<has-relation relation="fSenderPersonID" data-type="String" size="32" default-value-expr="currentPersonID()"></has-relation>
<has-relation relation="fSenderName" data-type="String" default-value-expr="currentPersonName()"></has-relation>
<has-relation relation="fSenderTime" data-type="DateTime"></has-relation>
<has-relation relation="fCreateTime" data-type="DateTime" default-value-expr="currentDateTime()"></has-relation>
<has-relation relation="fSMSType" data-type="String" default-value-expr="'我的短信'"></has-relation>
<has-relation relation="fStatus" data-type="String" default-value-expr="'未发送'"></has-relation>
<has-relation relation="fIsReplay" data-type="Integer" default-value-expr="0"></has-relation>
<has-relation relation="fWXContent" data-type="String" size="2000"></has-relation>
</concept>
<relation name="fSMSContent" data-type="String"><label language="zh_CN">短信内容</label>
</relation>
<relation name="fSenderPersonID" data-type="String"><label language="zh_CN">发送人ID</label>
</relation>
<relation name="fSenderName" data-type="String"><label language="zh_CN">发送人</label>
</relation>
<relation name="fSenderTime" data-type="DateTime"><label language="zh_CN">发送时间</label>
</relation>
<relation name="fCreateTime" data-type="DateTime"><label language="zh_CN">创建时间</label>
</relation>
<relation name="fSMSType" data-type="String"><label language="zh_CN">短信类型</label>
</relation>
<relation name="fStatus" data-type="String"><label language="zh_CN">短信状态</label>
</relation>
<relation name="fIsReplay" data-type="String"><label language="zh_CN">是否需要回复</label>
</relation>
<concept name="B_smsReceivePerson" default-value-expr="guid()"><has-relation relation="version" default-value-expr="0"><label language="zh_CN">版本</label>
</has-relation>
<label language="zh_CN">短信接收人</label>
<has-relation relation="fPersonID"></has-relation>
<has-relation relation="fPersonName"></has-relation>
<has-relation relation="fPhone"></has-relation>
<has-relation relation="fSMSID" data-type="B_smsInfo" size="43"></has-relation>
</concept>
<relation name="fSMSID" data-type="String"><label language="zh_CN">短信ID</label>
</relation>
<relation name="fCreatePersonID" data-type="String"><label language="zh_CN">创建人ID</label>
</relation>
<relation name="fCreateName" data-type="String"><label language="zh_CN">创建人名称</label>
</relation>


<relation name="FBACKCONTENT" data-type="String"><label language="zh_CN">FBACKCONTENT</label>
</relation>
<relation name="FSENDSTATE" data-type="String"><label language="zh_CN">FSENDSTATE</label>
</relation>
<relation name="FSENDERNAME" data-type="String"><label language="zh_CN">FSENDERNAME</label>
</relation>
<relation name="FBACKSTATE" data-type="String"><label language="zh_CN">FBACKSTATE</label>
</relation>
<relation name="FSTATE" data-type="String"><label language="zh_CN">FSTATE</label>
</relation>
<relation name="FPHONE" data-type="String"><label language="zh_CN">FPHONE</label>
</relation>
<relation name="FSENDCHECKTIME" data-type="Date"><label language="zh_CN">FSENDCHECKTIME</label>
</relation>
<relation name="FSMSID" data-type="String"><label language="zh_CN">FSMSID</label>
</relation>
<relation name="FPERSONID" data-type="String"><label language="zh_CN">FPERSONID</label>
</relation>
<relation name="FPERSONNAME" data-type="String"><label language="zh_CN">FPERSONNAME</label>
</relation>
<relation name="FID" data-type="String"><label language="zh_CN">FID</label>
</relation>
<relation name="FLOG" data-type="Blob"><label language="zh_CN">FLOG</label>
</relation>
<relation name="FSENDERPERSONID" data-type="String"><label language="zh_CN">FSENDERPERSONID</label>
</relation>
<relation name="FNEEDBACK" data-type="Integer"><label language="zh_CN">FNEEDBACK</label>
</relation>
<relation name="FREQUESTTYPE" data-type="String"><label language="zh_CN">FREQUESTTYPE</label>
</relation>
<relation name="FSENDTIME" data-type="Date"><label language="zh_CN">FSENDTIME</label>
</relation>
<relation name="FFAILEDCOUNT" data-type="Integer"><label language="zh_CN">FFAILEDCOUNT</label>
</relation>
<relation name="FRESENDCOUNT" data-type="Integer"><label language="zh_CN">FRESENDCOUNT</label>
</relation>
<relation name="FSMSCONTENT" data-type="String"><label language="zh_CN">FSMSCONTENT</label>
</relation>
<concept name="VIEW_SMSRECEIVEINFO" default-value-expr="guid()"><has-relation relation="FID" size="100" required="true"></has-relation>
<has-relation relation="FPERSONID" size="100"></has-relation>
<has-relation relation="FPERSONNAME" size="100"></has-relation>
<has-relation relation="FSMSID" size="43"></has-relation>
<has-relation relation="FPHONE" size="100"></has-relation>
<has-relation relation="FSENDSTATE" size="32"></has-relation>
<has-relation relation="FBACKSTATE" size="32"></has-relation>
<has-relation relation="FBACKCONTENT" size="2000"></has-relation>
<has-relation relation="FLASTBACKTIME" data-type="String" size="50"><label language="zh_CN">FLASTBACKTIME</label>
</has-relation>
<has-relation relation="FFAILEDCOUNT" size="22"></has-relation>
<has-relation relation="FSENDCHECKTIME"></has-relation>
<has-relation relation="FLOG"></has-relation>
<label language="zh_CN">短信接收人信息</label>
</concept>
<relation name="FLASTBACKTIME" data-type="Date"><label language="zh_CN">FLASTBACKTIME</label>
</relation>

<concept name="VIEW_SMSINFO" default-value-expr="guid()"><has-relation relation="FID" size="100" required="true"></has-relation>
<has-relation relation="FSMSCONTENT" size="500"></has-relation>
<has-relation relation="FSENDERPERSONID" size="32"></has-relation>
<has-relation relation="FSENDERNAME" size="100"></has-relation>
<has-relation relation="FSMSTYPE" size="100"></has-relation>
<has-relation relation="FSTATUS" size="100"></has-relation>
<has-relation relation="FSENDTIME" data-type="String" size="50"></has-relation>
<has-relation relation="FSTATE" size="32"></has-relation>
<has-relation relation="FRESENDCOUNT" size="22"></has-relation>
<has-relation relation="FNEEDBACK" size="8" data-type="String"></has-relation>
<has-relation relation="FREQUESTTYPE" size="32"></has-relation>
<label language="zh_CN">短信发送信息</label>
</concept>
<relation name="FSTATUS" data-type="String"><label language="zh_CN">FSTATUS</label>
</relation>
<relation name="FSMSTYPE" data-type="String"><label language="zh_CN">FSMSTYPE</label>
</relation>
<relation name="fWXContent" data-type="String"><label language="zh_CN">微信内容</label>
</relation>
</model>