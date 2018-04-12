<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <concept name="B_BZGZ" default-value-expr="guid()" keys="FGUID"> 
    <has-relation relation="FGUID"/>  
    <has-relation relation="version" default-value-expr="0"> 
      <label language="zh_CN">版本</label> 
    </has-relation>  
    <label language="zh_CN">补正告知</label>  
    <has-relation relation="fAJGQJL" data-type="B_AJGQJLB" required="true" size="32"/>  
    <has-relation relation="fBZCLQD" data-type="Text" required="true"> 
      <label language="zh_CN">补正材料清单(暂不维护)</label> 
    </has-relation>  
    <has-relation relation="fBizRecId" required="true" unique="true" default-value-expr=""/>  
    <has-relation relation="fBZSLRID" data-type="String" size="65">
      <label language="zh_CN">补正受理人ID</label> 
    </has-relation>  
    <has-relation relation="fBZSLRXM" data-type="String" size="128"/>  
    <has-relation relation="fBZSJ" data-type="DateTime"/>  
    <has-relation relation="fBZSLDD" data-type="String" size="200"/> 
  <has-relation relation="fBZGZYY"></has-relation>
</concept>  
  <relation name="fBZCLQD" data-type="Text"> 
    <label language="zh_CN">补正材料清单</label> 
  </relation>  
  <relation name="fBZSLJTDD" data-type="String"> 
    <label language="zh_CN">补正受理具体地点</label> 
  </relation>  
  <concept name="B_TBCX" default-value-expr="guid()" keys="FGUID"> 
    <has-relation relation="FGUID"/>  
    <has-relation relation="version" default-value-expr="0"> 
      <label language="zh_CN">版本</label> 
    </has-relation>  
    <label language="zh_CN">特别程序</label>  
    <has-relation relation="fTBCXXH" data-type="Integer" required="true" default-value-expr="1"/>  
    <has-relation relation="fTBCXZL" data-type="String" size="2" required="true"/>  
    <has-relation relation="fTBCXMC" size="100"/>
    <has-relation relation="fTBCXPZR" data-type="String" size="200" required="true"
      default-value-expr="currentPersonName()"/>  
    <has-relation relation="fSQNR" data-type="Text" required="true"/>  
    <has-relation relation="fTBCXSX" data-type="Integer" required="true"/>  
    <has-relation relation="fTBCXSXDW" data-type="String" size="10" required="true"
      default-value-expr="'工作日'"/>  
    <has-relation relation="fBizRecId" required="true" default-value-expr=""/>  
    <has-relation relation="fTBCXJG" data-type="Text"> 
      <label language="zh_CN">特别程序结果</label> 
    </has-relation>  
    <has-relation relation="fJGCSRQ"/>
    <has-relation relation="fTBCXSFJE" size="19" scale="2"> 
      <label language="zh_CN">特别程序收费金额</label> 
    </has-relation>  
    <has-relation relation="fJEDWDM" default-value-expr="'CNY'" size="100"> 
      <label language="zh_CN">特别程序金额单位代码</label> 
    </has-relation>  
    <has-relation relation="fAJGQJL" required="true" size="32"/> 
  </concept>  
  <relation name="fTBCXXH" data-type="Integer"> 
    <label language="zh_CN">特别程序序号</label> 
  </relation>  
  <relation name="fTBCXZL" data-type="String"> 
    <label language="zh_CN">特别程序种类</label> 
  </relation>  
  <relation name="fTBCXPZR" data-type="String"> 
    <label language="zh_CN">特别程序批准人</label> 
  </relation>  
  <relation name="fSQNR" data-type="Text"> 
    <label language="zh_CN">申请内容</label> 
  </relation>  
  <relation name="fTBCXSX" data-type="Integer"> 
    <label language="zh_CN">特别程序时限</label> 
  </relation>  
  <relation name="fTBCXSXDW" data-type="String"> 
    <label language="zh_CN">特别程序时限单位</label> 
  </relation>  
  <relation name="fTBCXJG" data-type="Text"> 
    <label language="zh_CN">特别程序结果</label> 
  </relation>  
  <relation name="fJGCSRQ" data-type="Date"> 
    <label language="zh_CN">结果产生日期</label> 
  </relation>  
  <relation name="fTBCXSFJE" data-type="Decimal"> 
    <label language="zh_CN">特别程序收费金额</label> 
  </relation>  
  <relation name="fJEDWDM" data-type="String"> 
    <label language="zh_CN">特别程序金额单位代码</label> 
  </relation>  
  <concept name="B_BZCLQD" default-value-expr="guid()" keys="FGUID"> 
    <has-relation relation="FGUID"/>  
    <has-relation relation="version" default-value-expr="0"> 
      <label language="zh_CN">版本</label> 
    </has-relation>  
    <label language="zh_CN">补正材料清单</label>  
    <has-relation relation="fCLMC" data-type="String" size="200" required="true"/>  
    <has-relation relation="fCLBH" data-type="String" size="50"/>  
    <has-relation relation="fSWCL" data-type="String" size="10" required="true" default-value-expr="'否'"/>  
    <has-relation relation="fFJQD" data-type="Text"/>  
    <has-relation relation="fCLQR" data-type="String" size="10" default-value-expr="'待确认'"
      required="true"/>  
    <has-relation relation="fBZGZ" data-type="B_BZGZ" size="32"/> 
  <has-relation relation="fBLYCL" data-type="String" size="10" default-value-expr="'保留'"></has-relation>
</concept>  
  <relation name="fCLMC" data-type="String"> 
    <label language="zh_CN">材料名称</label> 
  </relation>  
  <relation name="fCLBH" data-type="String"> 
    <label language="zh_CN">材料编号</label> 
  </relation>  
  <relation name="fSWCL" data-type="String"> 
    <label language="zh_CN">实物材料</label> 
  </relation>  
  <relation name="fFJQD" data-type="Text"> 
    <label language="zh_CN">附件清单</label> 
  </relation>  
  <relation name="fCLQR" data-type="String"> 
    <label language="zh_CN">材料确认</label> 
  </relation>  
  <concept name="B_AJGQJLB" default-value-expr="guid()" keys="FGUID"> 
    <has-relation relation="version" default-value-expr="0"> 
      <label language="zh_CN">版本</label> 
    </has-relation>  
    <label language="zh_CN">案卷挂起记录表</label>  
    <has-relation relation="FGUID" required="false"/>  
    <has-relation relation="fBizRecId" required="true" default-value-expr=""/>  
    <has-relation relation="fTaskId" size="32" required="true"/>  
    <has-relation relation="fFQRXM" data-type="String" size="128" required="true" default-value-expr="currentPersonName()"/>  
    <has-relation relation="fFQRID" data-type="String" size="65" required="true" default-value-expr="currentPersonID()"/>  
    <has-relation relation="fFQSJ" data-type="DateTime" required="true" default-value-expr="currentDateTime()"/>  
    <has-relation relation="fJSRXM" data-type="String" size="128"/>  
    <has-relation relation="fJSRID" data-type="String" size="65"/>  
    <has-relation relation="fJSSJ" data-type="DateTime"/>  
    <has-relation relation="fGQYY" data-type="Text"/>  
    <has-relation relation="fGQZT" data-type="String" required="true" size="10" default-value-expr="'挂起中'"/>  
    <has-relation relation="fGQLX" data-type="String" size="20" required="true"/>  
    <has-relation relation="fGQBZ" data-type="Text"/>  
    <has-relation relation="fJGBZ" data-type="Text"/>  
    <has-relation relation="fSQGQTS" data-type="Integer"/>  
    <has-relation relation="fSJGQTS" data-type="Integer"/> 
  <has-relation relation="fRemainingDays" data-type="Integer"></has-relation>
</concept>  
  <relation name="fTaskId" data-type="String"> 
    <label language="zh_CN">任务ID</label> 
  </relation>  
  <relation name="fFlowId" data-type="String"> 
    <label language="zh_CN">流程ID</label> 
  </relation>  
  <relation name="fFQRXM" data-type="String"> 
    <label language="zh_CN">发起人姓名</label> 
  </relation>  
  <relation name="fFQRID" data-type="String"> 
    <label language="zh_CN">发起人ID</label> 
  </relation>  
  <relation name="fFQSJ" data-type="DateTime"> 
    <label language="zh_CN">发起时间</label> 
  </relation>  
  <relation name="fJSRXM" data-type="String"> 
    <label language="zh_CN">结束人姓名</label> 
  </relation>  
  <relation name="fJSRID" data-type="String"> 
    <label language="zh_CN">结束人ID</label> 
  </relation>  
  <relation name="fJSSJ" data-type="String"> 
    <label language="zh_CN">结束时间</label> 
  </relation>  
  <relation name="fGQYY" data-type="Text"> 
    <label language="zh_CN">挂起原因</label> 
  </relation>  
  <relation name="fGQZT" data-type="String"> 
    <label language="zh_CN">挂起状态</label> 
  </relation>  
  <relation name="fGQLX" data-type="String"> 
    <label language="zh_CN">挂起类型</label> 
  </relation>  
  <relation name="fAJGQJL" data-type="B_AJGQJLB"> 
    <label language="zh_CN">挂起记录</label> 
  </relation>  
  <relation name="fGQBZ" data-type="Text"> 
    <label language="zh_CN">挂起备注</label> 
  </relation>  
  <relation name="fJGBZ" data-type="Text"> 
    <label language="zh_CN">解挂备注</label> 
  </relation>  
  <concept name="B_BJJLB" default-value-expr="guid()" keys="fBizRecId"> 
    <has-relation relation="version" default-value-expr="0"> 
      <label language="zh_CN">版本</label> 
    </has-relation>  
    <label language="zh_CN">办结记录表</label>  
    <has-relation relation="fBJJGDM" data-type="String" required="true" size="10"/>  
    <has-relation relation="fBJJGMC" data-type="String" size="20"/>  
    <has-relation relation="fBJJGMS" data-type="Text" required="false"/>  
    <has-relation relation="fZFHTHYY" data-type="Text"/>  
    <has-relation relation="fZJHGZMC" data-type="String" size="50"/>  
    <has-relation relation="fZJBH" data-type="String" size="50"/>  
    <has-relation relation="fZJYXQX" data-type="Date"/>  
    <has-relation relation="fFZHGZDW" data-type="String" default-value-expr="'广东省国土资源厅'"
      size="50"/>  
    <has-relation relation="fSFJE" data-type="Decimal" size="19" scale="2" required="true"
      default-value-expr="toDecimal(0)"/>  
    <has-relation relation="fJEDWDM" default-value-expr="'CNY'" required="true" size="10"> 
      <label language="zh_CN">金额单位代码</label> 
    </has-relation>
    <has-relation relation="fBJRID" data-type="String" size="65" default-value-expr="currentPersonID()"/>  
    <has-relation relation="fBJRXM" data-type="String" default-value-expr="currentPersonName()"
      size="128">
      <label language="zh_CN">办结人姓名</label> 
    </has-relation>  
    <has-relation relation="fBJSJ" data-type="DateTime" default-value-expr="currentDateTime()"
      required="true"/>  
    <has-relation relation="fBJBZ" data-type="Text"/>  
    <has-relation relation="fBizRecId" unique="true" required="true"/> 
  </concept>  
  <relation name="fBJJGDM" data-type="String"> 
    <label language="zh_CN">办结结果代码</label> 
  </relation>  
  <relation name="fBJJGMS" data-type="Text"> 
    <label language="zh_CN">办结结果描述</label> 
  </relation>  
  <relation name="fZFHTHYY" data-type="Text"> 
    <label language="zh_CN">作废或退回原因</label> 
  </relation>  
  <relation name="fZJHGZMC" data-type="String"> 
    <label language="zh_CN">证件或盖章名称</label> 
  </relation>  
  <relation name="fZJBH" data-type="String"> 
    <label language="zh_CN">证件编号</label> 
  </relation>  
  <relation name="fZJYXQX" data-type="String"> 
    <label language="zh_CN">证件有效期限</label> 
  </relation>  
  <relation name="fFZHGZDW" data-type="String"> 
    <label language="zh_CN">发证或盖章单位</label> 
  </relation>  
  <relation name="fSFJE" data-type="Decimal"> 
    <label language="zh_CN">收费金额</label> 
  </relation>  
  <relation name="fBJRXM" data-type="String"> 
    <label language="zh_CN">办结人</label> 
  </relation>  
  <relation name="fBJSJ" data-type="DateTime"> 
    <label language="zh_CN">办结时间</label> 
  </relation>  
  <relation name="fBJBZ" data-type="Text"> 
    <label language="zh_CN">办结备注</label> 
  </relation>  
  <relation name="ID" data-type="String"> 
    <label language="zh_CN">编号</label> 
  </relation>  
  <relation name="Name" data-type="String"> 
    <label language="zh_CN">名称</label> 
  </relation>  
  <relation name="fBJJGMC" data-type="String"> 
    <label language="zh_CN">办结结果名称</label> 
  </relation>  
  <relation name="fTBCXPZSJ" data-type="DateTime"> 
    <label language="zh_CN">特别程序批准时间</label> 
  </relation>  
  <concept name="B_DICT_TBCXZL" default-value-expr="guid()" keys="FGUID"> 
    <has-relation relation="version" default-value-expr="0"> 
      <label language="zh_CN">版本</label> 
    </has-relation>  
    <label language="zh_CN">特别程序种类字典</label>  
    <has-relation relation="FGUID"/>  
    <has-relation relation="fTBCXZL" size="2"/>  
    <has-relation relation="fTBCXMC" data-type="String" size="100"/>  
    <has-relation relation="fFLGDSX" data-type="Integer"/>  
    <has-relation relation="fFLGDSXDW" data-type="String" size="10" default-value-expr="'工作日'"/> 
  </concept>  
  <relation name="fTBCXMC" data-type="String"> 
    <label language="zh_CN">特别程序名称</label> 
  </relation>  
  <relation name="fFLGDSX" data-type="Integer"> 
    <label language="zh_CN">法律规定时限</label> 
  </relation>  
  <relation name="fFLGDSXDW" data-type="String"> 
    <label language="zh_CN">法律规定时限单位</label> 
  </relation>  
    
  <relation name="fBJLXID" data-type="String"> 
    <label language="zh_CN">办结类型ID</label> 
  </relation>  
  <relation name="fBJLXMC" data-type="String"> 
    <label language="zh_CN">办结类型名称</label> 
  </relation>  
  <relation name="fBJLXSM" data-type="String"> 
    <label language="zh_CN">办结类型说明</label> 
  </relation>  
  <relation name="fBZGZ" data-type="String"> 
    <label language="zh_CN">补正告知</label> 
  </relation>  
  <relation name="fSQGQTS" data-type="Integer">
    <label language="zh_CN">申请挂起天数</label> 
  </relation>  
  <relation name="fXTBJLX" data-type="String">
    <label language="zh_CN">系统办结类型</label> 
  </relation>  
  <relation name="fSFZCBJ" data-type="String">
    <label language="zh_CN">是否正常办结</label> 
  </relation>  
  <relation name="fSJGQTS" data-type="Integer">
    <label language="zh_CN">实际挂起天数</label> 
  </relation>  
  <relation name="fBZSLRID" data-type="String">
    <label language="zh_CN">补正受理人ID</label> 
  </relation>  
  <relation name="fBZSLRXM" data-type="String">
    <label language="zh_CN">补正受理人姓名</label> 
  </relation>  
  <relation name="fBJRID" data-type="String">
    <label language="zh_CN">办结人ID</label> 
  </relation>  
  <relation name="fBZSJ" data-type="DateTime">
    <label language="zh_CN">补正时间</label> 
  </relation>  
  <relation name="fBZSLDD" data-type="String">
    <label language="zh_CN">补正受理地点</label> 
  </relation>  
  <relation name="fBZGZYY" data-type="Text">
    <label language="zh_CN">补正告知原因</label> 
  </relation>  
  <concept name="B_BZGZYY" default-value-expr="guid()" keys="FGUID">
    <has-relation relation="version" default-value-expr="0">
      <label language="zh_CN">版本</label> 
    </has-relation>  
    <label language="zh_CN">补正告知原因</label>  
    <has-relation relation="FGUID"/>  
    <has-relation relation="fFQSJ"/>
    <has-relation relation="fBZGZYY" required="false"/>  
    <has-relation relation="fBZGZ" data-type="B_BZGZ"/> 
  </concept> 
<concept name="B_BatchOperation" default-value-expr="guid()" keys="FGUID"><has-relation relation="version" default-value-expr="0"><label language="zh_CN">版本</label>
</has-relation>
<label language="zh_CN">批量操作</label>
<has-relation relation="FGUID"></has-relation>



<has-relation relation="fOperation" data-type="String" size="30"></has-relation>
<has-relation relation="fOperationName" data-type="String" size="50"></has-relation>

<has-relation relation="fCreateTime" data-type="DateTime" default-value-expr="currentTime()"></has-relation>

<has-relation relation="fCreatorID" data-type="String" size="64" default-value-expr="currentPersonID()"></has-relation>
<has-relation relation="fCreatorName" data-type="String" size="64" default-value-expr="currentPersonName()"></has-relation>
<has-relation relation="fParameter" data-type="Text"></has-relation>
<has-relation relation="fCreatorFID" data-type="String" default-value-expr="currentPersonMemberFID() " size="1000"></has-relation>
<has-relation relation="fSrcBizRecID" data-type="String" size="32"></has-relation>
<has-relation relation="fSrcTaskID" data-type="String" size="32"></has-relation>
</concept>



<relation name="fOperation" data-type="String"><label language="zh_CN">操作</label>
</relation>
<relation name="fOperationName" data-type="String"><label language="zh_CN">操作名称</label>
</relation>
<relation name="fStatus" data-type="String"><label language="zh_CN">状态</label>
</relation>
<relation name="fCreateTime" data-type="DateTime"><label language="zh_CN">创建时间</label>
</relation>

<relation name="fCreatorID" data-type="String"><label language="zh_CN">提交人ID</label>
</relation>
<relation name="fCreatorName" data-type="String"><label language="zh_CN">提交人名称</label>
</relation>
<relation name="fParameter" data-type="Text"><label language="zh_CN">执行参数</label>
</relation>
<concept name="B_BatchOperationTask" default-value-expr="guid()" keys="FGUID"><has-relation relation="version" default-value-expr="0"><label language="zh_CN">版本</label>
</has-relation>
<label language="zh_CN">批量任务</label>
<has-relation relation="FGUID"></has-relation>
<has-relation relation="fFlowId" size="32"></has-relation>
<has-relation relation="fTaskId" size="32"></has-relation>
<has-relation relation="fBizRecId"></has-relation>
<has-relation relation="fExecuteTime" data-type="DateTime"></has-relation>
<has-relation relation="fStatus" size="10" default-value-expr="'等待中'"></has-relation>
<has-relation relation="fCause" data-type="Text"></has-relation>
<has-relation relation="fBatchGuid" data-type="B_BatchOperation" size="100"></has-relation>
<has-relation relation="fFinishTime" data-type="DateTime"></has-relation>
</concept>
<relation name="fExecuteTime" data-type="DateTime"><label language="zh_CN">执行时间</label>
</relation>
<relation name="fCause" data-type="Text"><label language="zh_CN">失败原因</label>
</relation>
<relation name="fBatchGuid" data-type="B_BatchOperation"><label language="zh_CN">批操作</label>
</relation>
<relation name="fRemainingDays" data-type="String"><label language="zh_CN">剩余天数</label>
</relation>
<relation name="fBLYCL" data-type="String"><label language="zh_CN">保留原材料</label>
</relation>
<relation name="fCreatorFID" data-type="String"><label language="zh_CN">提交人FID</label>
</relation>
<relation name="fSrcBizRecID" data-type="String"><label language="zh_CN">源案卷ID</label>
</relation>
<relation name="fSrcTaskID" data-type="String"><label language="zh_CN">源任务ID</label>
</relation>
<relation name="fFinishTime" data-type="DateTime"><label language="zh_CN">完成时间</label>
</relation>
</model>
