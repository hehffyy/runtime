<?xml version="1.0" encoding="UTF-8"?>
<model xmlns="http://www.justep.com/model">




<concept name="V_PreBizRec" default-value-expr="guid()" keys="fBizRecId">
<label language="zh_CN">预收件视图</label>
<has-relation relation="fBizRecId"></has-relation>
<has-relation relation="fSerialNo"></has-relation>
<has-relation relation="fYWLX"></has-relation>
<has-relation relation="fSQDW"></has-relation>
<has-relation relation="fLWRQ"></has-relation>
<has-relation relation="fXMMC"></has-relation>
<has-relation relation="fSFXYSW"></has-relation>

<has-relation relation="fProcess"></has-relation>


<has-relation relation="fProcessName"></has-relation>




<has-relation relation="fStartActivity"></has-relation>
<has-relation relation="fRemainingDays"></has-relation>
<has-relation relation="fStatus"></has-relation>
<has-relation relation="fBrowUrl"></has-relation>
<has-relation relation="fSFNWBJ"></has-relation>
<has-relation relation="fSQRSJ"></has-relation>
<has-relation relation="fSQRYJ"></has-relation>
<has-relation relation="fSQRXM"></has-relation>
</concept>
<concept name="Net_RecStatus" default-value-expr="guid()" keys="FGUID"><has-relation relation="FGUID"></has-relation><has-relation relation="version" default-value-expr="0"><label language="zh_CN">版本</label>
</has-relation>
<label language="zh_CN">网上收件案卷状态(废弃)</label>
<has-relation relation="fBizRecId"></has-relation>


<has-relation relation="fStatus"></has-relation>
<has-relation relation="fCreateTime"></has-relation>
<has-relation relation="fStatusDesc" single-valued="false" size="2000"></has-relation>

<has-relation relation="fFilePath" size="200"></has-relation>
<has-relation relation="fFileName" data-type="String" size="100"></has-relation>





</concept>
<relation name="fFilePath" data-type="String"><label language="zh_CN">文件路径</label>
</relation>
<relation name="fFileName" data-type="String"><label language="zh_CN">文件名</label>
</relation>
<relation name="fExchangeTime" data-type="DateTime"><label language="zh_CN">交换时间</label>
</relation>
<relation name="fChExchangeTime" data-type="DateTime"><label language="zh_CN">测绘平台交换时间</label>
</relation>
<relation name="fExchangeError" data-type="Text"><label language="zh_CN">交换错误信息</label>
</relation>


<concept name="B_ExchangeTask" default-value-expr="guid()" keys="fExTaskID"><has-relation relation="fExTaskID"></has-relation><has-relation relation="version" default-value-expr="0"><label language="zh_CN">版本</label>
</has-relation>
<label language="zh_CN">系统交换任务表</label>

<has-relation relation="fBizRecId"></has-relation>



<has-relation relation="fRelID" data-type="String"></has-relation><has-relation relation="fCreateTime"></has-relation>

<has-relation relation="fCreaterID" data-type="String"></has-relation><has-relation relation="fCreaterName" data-type="String"></has-relation><has-relation relation="fExContent" data-type="Text"></has-relation><has-relation relation="fExCode" data-type="String"></has-relation><has-relation relation="fExTime" data-type="DateTime"></has-relation><has-relation relation="fExStatus" data-type="String"></has-relation>
<has-relation relation="fExCount" data-type="Integer"></has-relation>
<has-relation relation="fError" data-type="Text"></has-relation><has-relation relation="fExKind" data-type="String"></has-relation>
<has-relation relation="fExKey" data-type="String"></has-relation>






<has-relation relation="fSource" data-type="String" default-value-expr="'自定义'"></has-relation>
<has-relation relation="fTaskVersion" data-type="Integer" default-value-expr="1"></has-relation>





</concept>

<relation name="fExStatus" data-type="String"><label language="zh_CN">交换状态</label>
</relation>
<relation name="fExCount" data-type="Integer"><label language="zh_CN">交换次数</label>
</relation>
<relation name="fExKind" data-type="String"><label language="zh_CN">交换类型</label>
</relation>
<relation name="fExKey" data-type="String"><label language="zh_CN">交换标识</label>
</relation>
<relation name="fError" data-type="String"><label language="zh_CN">错误信息</label>
</relation>
<relation name="fExTime" data-type="DateTime"><label language="zh_CN">交换时间</label>
</relation>
<relation name="fExTaskID" data-type="String"><label language="zh_CN">交换任务ID</label>
</relation>
<relation name="fRelID" data-type="String"><label language="zh_CN">关联信息ID</label>
</relation>
<concept name="Net_Files" default-value-expr="guid()" keys="FGUID"><has-relation relation="FGUID"></has-relation><has-relation relation="version" default-value-expr="0"><label language="zh_CN">版本</label>
</has-relation>
<label language="zh_CN">网上受理附件</label>
<has-relation relation="fBizRecId"></has-relation>

<has-relation relation="fFileName"></has-relation><has-relation relation="fType" data-type="String"></has-relation>
<has-relation relation="fNetUrl" data-type="String"></has-relation>

<has-relation relation="fDocIds" data-type="Text"></has-relation>
<has-relation relation="fYHXZ" data-type="String"></has-relation>
<has-relation relation="fWJYQ" data-type="String"></has-relation>

</concept>
<relation name="fType" data-type="String"><label language="zh_CN">文件类型</label>
</relation>
<relation name="fNetUrl" data-type="String"><label language="zh_CN">网络URL</label>
</relation>
<relation name="fYHXZ" data-type="String"><label language="zh_CN">用户选择</label>
</relation>
<relation name="fWJYQ" data-type="String"><label language="zh_CN">文件要求</label>
</relation>


<relation name="fSource" data-type="String"><label language="zh_CN">产生来源</label>
</relation>
<concept name="V_BizMapping" default-value-expr="guid()">
<label language="zh_CN">业务类型视图 </label>

<has-relation relation="fProcessName"></has-relation>
<has-relation relation="fFWSXBH"></has-relation>

</concept>


<concept name="Net_MailInfo" default-value-expr="guid()" keys="fBizRecId"><has-relation relation="version" default-value-expr="0"><label language="zh_CN">版本</label>
</has-relation>
<label language="zh_CN">邮寄信息</label>
<has-relation relation="fBizRecId"></has-relation>
<has-relation relation="fSJDZ" size="200"></has-relation>
<has-relation relation="fSJRDH" size="20"></has-relation>
<has-relation relation="fSJR" size="100"></has-relation>
<has-relation relation="fMailNo" data-type="String" size="100"></has-relation>
<has-relation relation="fMainTime" data-type="DateTime"></has-relation>
<has-relation relation="fDjr" data-type="String"></has-relation>
<has-relation relation="fMailState" data-type="String"></has-relation>
<has-relation relation="fMailKind"></has-relation>
<has-relation relation="fHint1" data-type="String" size="2000"></has-relation>
<has-relation relation="fHint2" data-type="String" size="2000"></has-relation>
<has-relation relation="fDocIds"></has-relation>
</concept>
<concept name="B_ExchangeLog" default-value-expr="guid()"><has-relation relation="version" default-value-expr="0"><label language="zh_CN">版本</label>
</has-relation>
<label language="zh_CN">系统交换日志表</label>
<has-relation relation="fRelID"></has-relation>

<has-relation relation="fLogInfo" data-type="Text"></has-relation>
<has-relation relation="fExchangeTime"></has-relation>
<has-relation relation="fExKind"></has-relation>
<has-relation relation="fExKey"></has-relation>
</concept>

<relation name="fLogInfo" data-type="Text"><label language="zh_CN">日志内容</label>
</relation>
<relation name="fMailNo" data-type="String"><label language="zh_CN">邮寄单号</label>
</relation>
<relation name="fMainTime" data-type="DateTime"><label language="zh_CN">邮寄时间</label>
</relation>
<relation name="fDjr" data-type="String"><label language="zh_CN">登记人</label>
</relation>
<relation name="fMailState" data-type="String"><label language="zh_CN">邮寄状态</label>
</relation>
<relation name="fHint1" data-type="String"><label language="zh_CN">提示信息1</label>
</relation>
<relation name="fHint2" data-type="String"><label language="zh_CN">提示信息2</label>
</relation>
<relation name="fTaskVersion" data-type="Integer"><label language="zh_CN">任务版本号</label>
</relation>

<relation name="fCreaterID" data-type="String"><label language="zh_CN">创建人ID</label>
</relation>
<relation name="fCreaterName" data-type="String"><label language="zh_CN">创建人名称</label>
</relation>
<relation name="fExContent" data-type="Text"><label language="zh_CN">交换信息</label>
</relation>
<relation name="fExCode" data-type="String"><label language="zh_CN">交换代码</label>
</relation>
</model>