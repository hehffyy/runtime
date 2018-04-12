<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <concept xmlns:butone="http://www.butone.com" name="B_BizRec" keys="fBizRecId">  
    <has-relation relation="version" default-value-expr="0"> 
      <label language="zh_CN">版本</label> 
    </has-relation>  
    <label language="zh_CN">案卷表</label>  
    <has-relation relation="fReceiverId" data-type="String" size="64" default-value-expr="currentPersonCode()"> 
      <label language="zh_CN">收件人ID</label> 
    </has-relation>  
    <has-relation relation="fReceiverName" data-type="String" default-value-expr="currentPersonName()"
      size="128"/>  
    <has-relation relation="fReceiveTime" data-type="DateTime" default-value-expr="currentDateTime()"/>  
    <has-relation relation="fBizId" data-type="String" size="32" default-value-expr="getProcessBizId()"/>  
    <has-relation relation="fBizName" data-type="String" size="200" default-value-expr="currentProcessBizName()"> 
      <label language="zh_CN">业务名称</label> 
    </has-relation>  
    <has-relation relation="fRecTitle" data-type="String" size="200"> 
      <label language="zh_CN">案卷标题</label> 
    </has-relation>  
    <has-relation relation="fBelongOrg" data-type="String" size="64" default-value-expr="if(currentDeptCode()=null,currentOgnCode(),currentDeptCode())"> 
      <label language="zh_CN">所属机构</label> 
    </has-relation>  
    <has-relation relation="fBelongOrgName" data-type="String" default-value-expr="if(currentDeptName()=null,currentOgnName(),currentDeptName())"
      size="128"/>  
    <has-relation relation="fBelongOrgLevel" data-type="String" size="10"/>  
    <has-relation relation="fFlowId" data-type="String" size="32"/>  
    <has-relation relation="fBizCoopId" data-type="String" size="32" default-value-expr="guid()"> 
      <label language="zh_CN">业务协同实例编号</label> 
    </has-relation>  
    <has-relation relation="fBizCoopActivity" size="255"/>  
    <has-relation relation="fApproveId" size="32" default-value-expr="guid()"> 
      <label language="zh_CN">机构协作实例编号</label> 
    </has-relation>  
    <has-relation relation="fBizOrgLevel" size="10"/>  
    <has-relation relation="fStatus" data-type="String" size="20" default-value-expr="'bsProcessing'"> 
      <label language="zh_CN">案卷状态</label> 
    </has-relation>  
    <has-relation relation="fStatusName" size="50" default-value-expr="'办理中'"/>  
    <has-relation relation="fStatusTime" data-type="DateTime" default-value-expr="currentDateTime()"/>  
    <has-relation relation="fStatusDesc" data-type="Text"/>  
    <has-relation relation="fBizRecId"/>  
    <has-relation relation="fFinishKind" data-type="String" size="20"/>  
    <has-relation relation="fSuspendKind" data-type="String" size="20"/>  
    <has-relation relation="fLimitStartDate" data-type="DateTime"/>  
    <has-relation relation="fLimitDate" data-type="DateTime"/>  
    <has-relation relation="fLimitKind" size="30"/>  
    <has-relation relation="fLimitDays" data-type="Integer"/>  
    <has-relation relation="fSuspendDays" data-type="Integer" default-value-expr="0"/>  
    <has-relation relation="fRemainingDays" data-type="Integer"/>  
    <has-relation relation="fLostDays" data-type="Integer"/>  
    <butone:actions> 
      <xbiz:map xmlns:xbiz="http://www.justep.com/xbiz#">  
        <item key="query"> 
          <simple type="http://www.w3.org/2001/XMLSchema#String">queryBizRecAction</simple> 
        </item>  
        <item key="save"> 
          <simple type="http://www.w3.org/2001/XMLSchema#String">saveBizRecAction</simple> 
        </item>  
        <item key="create"> 
          <simple type="http://www.w3.org/2001/XMLSchema#String">createBizRecAction</simple> 
        </item> 
      </xbiz:map> 
    </butone:actions>  
    <has-relation relation="fBizNo" size="50"> 
      <label language="zh_CN">业务号</label> 
    </has-relation>  
    <has-relation relation="fProcess" default-value-expr="currentProcess()" size="500"/> 
  </concept>  
  <relation name="fReceiverId" data-type="String"> 
    <label language="zh_CN">收件人</label> 
  </relation>  
  <relation name="fReceiveTime" data-type="DateTime"> 
    <label language="zh_CN">收件时间</label> 
  </relation>  
  <relation name="fBizId" data-type="String"> 
    <label language="zh_CN">业务编号</label> 
  </relation>  
  <relation name="fBizName" data-type="String"> 
    <label language="zh_CN">业务名称</label> 
  </relation>  
  <relation name="fBelongOrg" data-type="String"> 
    <label language="zh_CN">案卷所属机构</label> 
  </relation>  
  <relation name="fBizCoopId" data-type="String"> 
    <label language="zh_CN">业务协同实例</label> 
  </relation>  
  <relation name="fBizCoopActivity" data-type="String"> 
    <label language="zh_CN">业务协同环节</label> 
  </relation>  
  <relation name="fBizOrgLevel" data-type="String"> 
    <label language="zh_CN">业务机构级别</label> 
  </relation>  
  <relation name="fFlowId" data-type="String"> 
    <label language="zh_CN">流程ID</label> 
  </relation>  
  <concept name="B_BizCooperation" default-value-expr="guid()" keys="fBizCoopId"> 
    <has-relation relation="version" default-value-expr="0"> 
      <label language="zh_CN">版本</label> 
    </has-relation>  
    <label language="zh_CN">业务协同实例</label>  
    <has-relation relation="fBizCoopId" default-value-expr="guid()" size="32"> 
      <label language="zh_CN">业务协作编号</label> 
    </has-relation>  
    <has-relation relation="fCreateTime" data-type="DateTime" default-value-expr="currentDateTime()"/>  
    <has-relation relation="fBizCoopName" data-type="String" size="100"/>  
    <has-relation relation="fFlowId" size="32"/>  
    <has-relation relation="fStatus" size="20"> 
      <label language="zh_CN">状态</label> 
    </has-relation> 
  </concept>  
  <relation name="fCreateTime" data-type="DateTime"> 
    <label language="zh_CN">创建时间</label> 
  </relation>  
  <relation name="fBizCoopName" data-type="String"> 
    <label language="zh_CN">业务协作名称</label> 
  </relation>  
  <relation name="fApproveId" data-type="String"> 
    <label language="zh_CN">审批过程实例</label> 
  </relation>  
  <relation name="fBizItems" data-type="B_BizRec"> 
    <label language="zh_CN">业务事项</label> 
  </relation>  
  <relation name="fReceiverName" data-type="String"> 
    <label language="zh_CN">收件人姓名</label> 
  </relation>  
  <relation name="fBelongOrgName" data-type="String"> 
    <label language="zh_CN">所属机构名称</label> 
  </relation>  
  <relation name="fRecTitle" data-type="String"> 
    <label language="zh_CN">业务显示名称</label> 
  </relation>  
  <relation name="fBelongOrgLevel" data-type="String"> 
    <label language="zh_CN">所属机构级别</label> 
  </relation>  
  <relation name="fStatus" data-type="String"> 
    <label language="zh_CN">案卷状态</label> 
  </relation>  
  <relation name="fStatusTime" data-type="DateTime"> 
    <label language="zh_CN">案卷状态时间</label> 
  </relation>  
  <relation name="fStatusDesc" data-type="String"> 
    <label language="zh_CN">案卷状态描述</label> 
  </relation>  
  <relation name="fCoopTaskId" data-type="String"> 
    <label language="zh_CN">业务协同的任务ID</label> 
  </relation>  
  <concept name="B_FlowCoopRecord" default-value-expr="guid()" keys="FGUID"> 
    <has-relation relation="FGUID"/>  
    <has-relation relation="version" default-value-expr="0"> 
      <label language="zh_CN">版本</label> 
    </has-relation>  
    <label language="zh_CN">流程协同记录</label>  
    <has-relation relation="fApproveId" size="100"/>  
    <has-relation relation="fCreateTime" default-value-expr="currentDateTime()"/>  
    <has-relation relation="fFromProcess" data-type="String" size="1024"/>  
    <has-relation relation="fFromActivity" data-type="String" size="255"/>  
    <has-relation relation="fWaitActivity" data-type="String" size="255"/>  
    <has-relation relation="fToProcess" data-type="String" size="1024"/>  
    <has-relation relation="fToActivity" data-type="String" size="255"/>  
    <has-relation relation="fStatus" size="32"> 
      <label language="zh_CN">状态</label> 
    </has-relation>  
    <has-relation relation="fSuspendedTaskId" data-type="String" size="100"/> 
  </concept>  
  <relation name="fFromProcess" data-type="String"> 
    <label language="zh_CN">发起方过程</label> 
  </relation>  
  <relation name="fFromActivity" data-type="String"> 
    <label language="zh_CN">发起发环节</label> 
  </relation>  
  <relation name="fWaitActivity" data-type="String"> 
    <label language="zh_CN">发起发等待环节</label> 
  </relation>  
  <relation name="fToProcess" data-type="String"> 
    <label language="zh_CN">目标方过程</label> 
  </relation>  
  <relation name="fToActivity" data-type="String"> 
    <label language="zh_CN">目标方环节</label> 
  </relation>  
  <relation name="fSuspendedTaskId" data-type="String"> 
    <label language="zh_CN">发送方暂停任务</label> 
  </relation>  
  <concept name="B_BizApprove" default-value-expr="guid()" keys="fApproveId"> 
    <has-relation relation="version" default-value-expr="0"> 
      <label language="zh_CN">版本</label> 
    </has-relation>  
    <label language="zh_CN">业务审批过程实例</label>  
    <has-relation relation="fApproveId" size="100"/>  
    <has-relation relation="fCreateTime" default-value-expr="currentDateTime()"/>  
    <has-relation relation="fBizCoopId" size="100"/>  
    <has-relation relation="fBizCoopActivity" size="100"/>  
    <has-relation relation="fCoopTaskId" size="100"/>  
    <has-relation relation="fStatus" size="30"/> 
  </concept>  
  <relation name="fStatusName" data-type="String"> 
    <label language="zh_CN">状态名称</label> 
  </relation>  
  <relation name="fFinishKind" data-type="String"> 
    <label language="zh_CN">办结类型</label> 
  </relation>  
  <relation name="fSuspendKind" data-type="String"> 
    <label language="zh_CN">挂起类型</label> 
  </relation>  
  <relation name="fLimitDate" data-type="DateTime"> 
    <label language="zh_CN">限办时间</label> 
  </relation>  
  <concept name="B_ActivityGroupInstance" default-value-expr="guid()" keys="FGUID"> 
    <has-relation relation="version" default-value-expr="0"> 
      <label language="zh_CN">版本</label> 
    </has-relation>  
    <label language="zh_CN">环节分组实例</label>  
    <has-relation relation="FGUID"/>  
    <has-relation relation="fGroupName" data-type="String" size="100" required="true"/>  
    <has-relation relation="fGroupId" data-type="String" size="32" required="true"/>  
    <has-relation relation="fFlowId" size="32"/>  
    <has-relation relation="fBizRecId"/>  
    <has-relation relation="fBizId" size="32"/>  
    <has-relation relation="fStartTime" data-type="DateTime" default-value-expr="currentDateTime()"
      required="true"/>  
    <has-relation relation="fEndTime" data-type="DateTime"/>  
    <has-relation relation="fStartCount" data-type="Integer" default-value-expr="1"/>  
    <has-relation relation="fLimitDate"/>  
    <has-relation relation="fLimitKind" size="100"/>  
    <has-relation relation="fLimitDays" data-type="Decimal" size="10" scale="1"/>  
    <has-relation relation="fLostDays"/>  
    <has-relation relation="fRemainingDays" data-type="Float"/>
    <has-relation relation="fSuspendDays"></has-relation> 
  </concept>  
  <relation name="fGroupName" data-type="String"> 
    <label language="zh_CN">分组名称</label> 
  </relation>  
  <relation name="fGroupId" data-type="String"> 
    <label language="zh_CN">分组ID</label> 
  </relation>  
  <relation name="fStartTime" data-type="DateTime"> 
    <label language="zh_CN">开始时间</label> 
  </relation>  
  <relation name="fEndTime" data-type="DateTime"> 
    <label language="zh_CN">结束时间</label> 
  </relation>  
  <relation name="fStartCount" data-type="Integer"> 
    <label language="zh_CN">开始次数</label> 
  </relation>  
  <concept name="B_ActivityGroupInstanceTask" default-value-expr="guid()" keys="FGUID"> 
    <has-relation relation="version" default-value-expr="0"> 
      <label language="zh_CN">版本</label> 
    </has-relation>  
    <label language="zh_CN">环节分组实例记录</label>  
    <has-relation relation="FGUID"/>  
    <has-relation relation="fGroupInstance" data-type="B_ActivityGroupInstance" required="true" size="100"/>  
    <has-relation relation="fTaskId" data-type="String" size="32"/> 
  </concept>  
  <relation name="fTaskId" data-type="String"> 
    <label language="zh_CN">任务ID</label> 
  </relation>  
  <relation name="fGroupInstance" data-type="B_ActivityGroupInstance"> 
    <label language="zh_CN">分组实例</label> 
  </relation>  
  <relation name="fLimitKind" data-type="String"> 
    <label language="zh_CN">时限类型</label> 
  </relation>  
  <relation name="fLimitDays" data-type="Integer"> 
    <label language="zh_CN">时限天数</label> 
  </relation>  
  <relation name="fLostDays" data-type="Float"> 
    <label language="zh_CN">耗时</label> 
  </relation>  
  <relation name="fSuspendDays" data-type="Integer"> 
    <label language="zh_CN">挂起天数</label> 
  </relation>  
  <relation name="fRemainingDays" data-type="Integer"> 
    <label language="zh_CN">剩余天数</label> 
  </relation>  
  <relation name="fLimitStartDate" data-type="DateTime"> 
    <label language="zh_CN">限办开始日期</label> 
  </relation>  
  <relation name="fCreatePerson" data-type="String"> 
    <label language="zh_CN">创建人</label> 
  </relation>  
  <relation name="fCreatePersonName" data-type="String"> 
    <label language="zh_CN">创建人名称</label> 
  </relation>  
  <relation name="fProject" data-type="String"> 
    <label language="zh_CN">项目名称</label> 
  </relation>  
  <relation name="fProjectNo" data-type="String"> 
    <label language="zh_CN">报件号</label> 
  </relation>  
  <relation name="fSubmitDate" data-type="DateTime"> 
    <label language="zh_CN">上报时间</label> 
  </relation>  
  <relation name="fFlowBizRecId" data-type="String"> 
    <label language="zh_CN">预收件关联案卷ID</label> 
  </relation>  
  <relation name="fBizNo" data-type="String"> 
    <label language="zh_CN">业务编号</label> 
  </relation>  
  <relation name="fBrowseProcess" data-type="String"> 
    <label language="zh_CN">预收件process</label> 
  </relation>  
  <relation name="fBrowseActivity" data-type="String"> 
    <label language="zh_CN">预收件环节</label> 
  </relation>  
  <relation name="fBizProcess" data-type="String"> 
    <label language="zh_CN">业务process</label> 
  </relation>  
  <concept name="B_PrepLog" default-value-expr="guid()"> 
    <has-relation relation="version" default-value-expr="0"> 
      <label language="zh_CN">版本</label> 
    </has-relation>  
    <label language="zh_CN">预收件日志表</label>  
    <has-relation relation="fLoger" data-type="String" size="20"/>  
    <has-relation relation="fLogTime" data-type="DateTime"/>  
    <has-relation relation="fStatus" size="20"/>  
    <has-relation relation="fRemark"/>  
    <has-relation relation="fPreBizRecId" data-type="String" size="32"/> 
  </concept>  
  <relation name="fLoger" data-type="String"> 
    <label language="zh_CN">操作人</label> 
  </relation>  
  <relation name="fLogTime" data-type="String"> 
    <label language="zh_CN">日志时间</label> 
  </relation>  
  <relation name="fPreBizRecId" data-type="String"> 
    <label language="zh_CN">预收件ID</label> 
  </relation>  
  <relation name="fExecutorexpr" data-type="String"> 
    <label language="zh_CN">执行者表达式</label> 
  </relation>  
  <concept name="B_BizRecAttr" default-value-expr="guid()" keys="fBizRecId"> 
    <has-relation relation="version" default-value-expr="0"> 
      <label language="zh_CN">版本</label> 
    </has-relation>  
    <label language="zh_CN">案卷扩展属性</label>  
    <has-relation relation="fBizRecId"/>  
    <has-relation relation="FExamaxeItemID" data-type="String" size="100"/>  
    <has-relation relation="FExamineItemID" data-type="String" size="100"/>  
    <has-relation relation="FInComeDocName" data-type="String" size="200"/>  
    <has-relation relation="FInComeDocOrg" data-type="String" size="200"/>  
    <has-relation relation="FArchivesCode" data-type="String" size="50"/>  
    <has-relation relation="FMainDept" data-type="String" size="200"/>  
    <has-relation relation="FMainPerson" data-type="String" size="100"/>  
    <has-relation relation="fSerialNo" size="100"/>  
    <has-relation relation="FRecPriority" data-type="String" size="32"/>  
    <has-relation relation="fKind" size="50"/>  
    <has-relation relation="fRecSupervise" data-type="String" size="10"/> 
  </concept>  
  <relation name="FExamaxeItemID" data-type="String"> 
    <label language="zh_CN">审批事项大项编号</label> 
  </relation>  
  <relation name="FExamineItemID" data-type="String"> 
    <label language="zh_CN">审批事项子项编号</label> 
  </relation>  
  <relation name="fProcess" data-type="String"> 
    <label language="zh_CN">过程</label> 
  </relation>  
  <concept name="V_TaskBizData" default-value-expr="guid()" keys="fBizRecId"> 
    <label language="zh_CN">任务业务扩展属性</label>  
    <has-relation relation="fTaskId"/>  
    <has-relation relation="fBizRecId"/>  
    <has-relation relation="fTitle" data-type="String" size="308"/> 
  </concept>  
  <relation name="sqr" data-type="String"> 
    <label language="zh_CN">申请人</label> 
  </relation>  
  <relation name="ywh" data-type="String"> 
    <label language="zh_CN">业务号</label> 
  </relation>  
  <relation name="slr" data-type="String"> 
    <label language="zh_CN">受理人</label> 
  </relation>  
  <relation name="fTitle" data-type="String"> 
    <label language="zh_CN">标题</label> 
  </relation>  
  <concept name="B_BizRecRelation" default-value-expr="guid()" keys="fBizRecId"> 
    <has-relation relation="version" default-value-expr="0"> 
      <label language="zh_CN">版本</label> 
    </has-relation>  
    <label language="zh_CN">案卷关系</label>  
    <has-relation relation="fBizRecId"/>  
    <has-relation relation="fParentID" data-type="String" size="32"/>  
    <has-relation relation="fKind" data-type="String" size="100"/> 
  </concept>  
  <relation name="fParentID" data-type="String"> 
    <label language="zh_CN">父案卷编号</label> 
  </relation>  
  <relation name="fKind" data-type="String"> 
    <label language="zh_CN">类型</label> 
  </relation>  
  <concept name="B_PrepBizRec" default-value-expr="guid()" keys="fBizRecId"> 
    <label language="zh_CN">预收案卷表</label>  
    <has-relation relation="fBizRecId"/>  
    <has-relation relation="fSerialNo" size="100"> 
      <label language="zh_CN">网上受理号</label> 
    </has-relation>  
    <has-relation relation="fYWLX" size="100"> 
      <label language="zh_CN">业务类型</label> 
    </has-relation>  
    <has-relation relation="fSQDW" size="500"> 
      <label language="zh_CN">申请单位</label> 
    </has-relation>  
    <has-relation relation="fLXDH" size="100"> 
      <label language="zh_CN">联系电话</label> 
    </has-relation>  
    <has-relation relation="fSQRXM" size="100"> 
      <label language="zh_CN">申请人姓名</label> 
    </has-relation>  
    <has-relation relation="fSQRYJ" size="100"> 
      <label language="zh_CN">申请人邮件</label> 
    </has-relation>  
    <has-relation relation="fSQRSJ" size="100"> 
      <label language="zh_CN">申请人手机</label> 
    </has-relation>  
    <has-relation relation="fLWRQ"> 
      <label language="zh_CN">来文日期</label> 
    </has-relation>  
    <has-relation relation="fXMMC" size="500"> 
      <label language="zh_CN">项目名称</label> 
    </has-relation>  
    <has-relation relation="fBLTJ" size="100"> 
      <label language="zh_CN">办理条件</label> 
    </has-relation>  
    <has-relation relation="fSFXYSW" size="100"> 
      <label language="zh_CN">是否需要实物</label> 
    </has-relation>  
    <has-relation relation="fItemCode" size="100"> 
      <label language="zh_CN">事项编号</label> 
    </has-relation>  
    <has-relation relation="fSJR" size="100"> 
      <label language="zh_CN">收件人</label> 
    </has-relation>  
    <has-relation relation="fSJDZ" size="500"> 
      <label language="zh_CN">收件地址</label> 
    </has-relation>  
    <has-relation relation="fSJRDH" size="100"> 
      <label language="zh_CN">收件人电话</label> 
    </has-relation>  
    <has-relation relation="fYJFS" size="100"> 
      <label language="zh_CN">邮寄方式</label> 
    </has-relation>  
    <has-relation relation="fColb" size="100"> 
      <label language="zh_CN">测绘流水号</label> 
    </has-relation>  
    <has-relation relation="fTZXMTYBM" size="100"> 
      <label language="zh_CN">投资项目统一编码</label> 
    </has-relation>  
    <has-relation relation="fJSSJ" data-type="DateTime"> 
      <label language="zh_CN">接收时间</label> 
    </has-relation>  
    <has-relation relation="fBJJSSJ" data-type="DateTime"> 
      <label language="zh_CN">补交接收时间</label> 
    </has-relation>  
    <has-relation relation="fCZR" size="30"> 
      <label language="zh_CN">操作人</label> 
    </has-relation>  
    <has-relation relation="fYSTGSJ" data-type="DateTime"> 
      <label language="zh_CN">预审通过时间</label> 
    </has-relation>  
    <has-relation relation="fSLSJ"> 
      <label language="zh_CN">受理时间</label> 
    </has-relation>  
    <has-relation relation="fBYSLYJ" size="1000"> 
      <label language="zh_CN">不予受理意见</label> 
    </has-relation>  
    <has-relation relation="fBYSLSJ"> 
      <label language="zh_CN">不予受理时间</label> 
    </has-relation>  
    <has-relation relation="fSFCQ" size="10"> 
      <label language="zh_CN">是否超期</label> 
    </has-relation>  
    <has-relation relation="fBizMappingId" size="100"> 
      <label language="zh_CN">业务映射ID</label> 
    </has-relation>  
    <has-relation relation="version"/>  
    <has-relation relation="fTJFS" data-type="String" size="32"/>  
    <has-relation relation="fStatus" default-value-expr="'未收件'" size="30"/>  
    <has-relation relation="fBizName" size="100"/>  
    <has-relation relation="fBelongOrg" default-value-expr="if(currentDeptCode()=null,currentOgnCode(),currentDeptCode())" size="100"/>  
    <has-relation relation="fBelongOrgName" default-value-expr="if(currentDeptName()=null,currentOgnName(),currentDeptName())" size="100"/>  
    <has-relation relation="fCreateTime" default-value-expr="currentDateTime()"/>  
    <has-relation relation="fCreatePerson" default-value-expr="currentPersonCode()" size="100"/>  
    <has-relation relation="fCreatePersonName" default-value-expr="currentPersonName()" size="100"/>  
    <has-relation relation="fBizId" size="100"/>  
    <has-relation relation="fBizNo" size="100"/>  
    <has-relation relation="SMSShzt" data-type="String" default-value-expr="'未审核'" size="100"/>  
    <has-relation relation="SMSShr" data-type="String" size="100"/>  
    <has-relation relation="SMSShsj" data-type="DateTime"/> 
  </concept>  
  <relation name="FSTATUS" data-type="String"> 
    <label language="zh_CN">案卷状态</label> 
  </relation>  
  <relation name="fJSSJ" data-type="Date"> 
    <label language="zh_CN">接收时间</label> 
  </relation>  
  <relation name="fSLSJ" data-type="Date"> 
    <label language="zh_CN">受理时间</label> 
  </relation>  
  <relation name="fTZXMTYBM" data-type="String"> 
    <label language="zh_CN">投资项目统一编码</label> 
  </relation>  
  <relation name="fBizMappingId" data-type="String"> 
    <label language="zh_CN">业务映射ID</label> 
  </relation>  
  <relation name="fSQRSJ" data-type="String"> 
    <label language="zh_CN">申请人手机</label> 
  </relation>  
  <relation name="fSJDZ" data-type="String"> 
    <label language="zh_CN">收件地址</label> 
  </relation>  
  <relation name="fSFCQ" data-type="String"> 
    <label language="zh_CN">是否超期</label> 
  </relation>  
  <relation name="fLXDH" data-type="String"> 
    <label language="zh_CN">联系电话</label> 
  </relation>  
  <relation name="fBYSLYJ" data-type="String"> 
    <label language="zh_CN">不予受理意见</label> 
  </relation>  
  <relation name="fBYSLSJ" data-type="Date"> 
    <label language="zh_CN">不予受理时间</label> 
  </relation>  
  <relation name="fBLTJ" data-type="String"> 
    <label language="zh_CN">办理条件</label> 
  </relation>  
  <relation name="fXMMC" data-type="String"> 
    <label language="zh_CN">项目名称</label> 
  </relation>  
  <relation name="fColb" data-type="String"> 
    <label language="zh_CN">测绘流水号</label> 
  </relation>  
  <relation name="fBJJSSJ" data-type="Date"> 
    <label language="zh_CN">补交接收时间</label> 
  </relation>  
  <relation name="fSFXYSW" data-type="String"> 
    <label language="zh_CN">是否需要实物</label> 
  </relation>  
  <relation name="fCZR" data-type="String"> 
    <label language="zh_CN">操作人</label> 
  </relation>  
  <relation name="fSerialNo" data-type="String"> 
    <label language="zh_CN">网上受理号</label> 
  </relation>  
  <relation name="fSJRDH" data-type="String"> 
    <label language="zh_CN">收件人电话</label> 
  </relation>  
  <relation name="fSQRYJ" data-type="String"> 
    <label language="zh_CN">申请人邮件</label> 
  </relation>  
  <relation name="fItemCode" data-type="String"> 
    <label language="zh_CN">事项编号</label> 
  </relation>  
  <relation name="fSJR" data-type="String"> 
    <label language="zh_CN">收件人</label> 
  </relation>  
  <relation name="fYJFS" data-type="String"> 
    <label language="zh_CN">邮寄方式</label> 
  </relation>  
  <relation name="fYSTGSJ" data-type="Date"> 
    <label language="zh_CN">预审通过时间</label> 
  </relation>  
  <relation name="fSQDW" data-type="String"> 
    <label language="zh_CN">申请单位</label> 
  </relation>  
  <relation name="fYWLX" data-type="String"> 
    <label language="zh_CN">业务类型</label> 
  </relation>  
  <relation name="fSQRXM" data-type="String"> 
    <label language="zh_CN">申请人姓名</label> 
  </relation>  
  <relation name="fLWRQ" data-type="Date"> 
    <label language="zh_CN">来文日期</label> 
  </relation>  
  <relation name="fTJFS" data-type="String"> 
    <label language="zh_CN">提交方式</label> 
  </relation>  
  <relation name="FInComeDocName" data-type="String"> 
    <label language="zh_CN">来文名称</label> 
  </relation>  
  <relation name="FInComeDocOrg" data-type="String"> 
    <label language="zh_CN">来文单位</label> 
  </relation>  
  <relation name="FArchivesCode" data-type="String"> 
    <label language="zh_CN">办文号</label> 
  </relation>  
  <relation name="FMainDept" data-type="String"> 
    <label language="zh_CN">主办部门</label> 
  </relation>  
  <relation name="FMainPerson" data-type="String"> 
    <label language="zh_CN">主办人</label> 
  </relation>  
  <relation name="FNetSerialNo" data-type="String"> 
    <label language="zh_CN">网上受理号</label> 
  </relation>  
  <relation name="FRecPriority" data-type="String"> 
    <label language="zh_CN">紧急程度</label> 
  </relation>  
  <relation name="SMSShzt" data-type="String"> 
    <label language="zh_CN">短信审核状态</label> 
  </relation>  
  <relation name="SMSShr" data-type="String"> 
    <label language="zh_CN">短信审核人</label> 
  </relation>  
  <relation name="SMSShsj" data-type="DateTime"> 
    <label language="zh_CN">短信审核时间</label> 
  </relation>  
  <relation name="fRecSupervise" data-type="String"> 
    <label language="zh_CN">案卷督办</label> 
  </relation>  
  <concept name="B_FlowOperationLog" default-value-expr="guid()" keys="FGUID"> 
    <has-relation relation="FGUID"/>  
    <has-relation relation="version" default-value-expr="0"> 
      <label language="zh_CN">版本</label> 
    </has-relation>  
    <label language="zh_CN">流程操作日志</label>  
    <has-relation relation="fPersonID" size="32"/>  
    <has-relation relation="fPersonName" size="16"/>  
    <has-relation relation="fOperationName" data-type="String" size="10"/>
    <has-relation relation="fCreateTime"/>  
    <has-relation relation="fRemark"/>  
    <has-relation relation="fBizRecId" required="true"/> 
  </concept>  
  <concept name="B_BizRecAttention" default-value-expr="guid()" keys="FGUID"> 
    <has-relation relation="FGUID"/>  
    <has-relation relation="version" default-value-expr="0"> 
      <label language="zh_CN">版本</label> 
    </has-relation>  
    <label language="zh_CN">案卷关注记录</label>  
    <has-relation relation="fPersonID" size="100"/>  
    <has-relation relation="fPersonName" size="100"/>  
    <has-relation relation="fCreateTime"/>  
    <has-relation relation="fBizRecId"/> 
  </concept>  
  <relation name="fOperationName" data-type="String">
    <label language="zh_CN">操作名称</label> 
  </relation> 
<concept name="B_SuperviseMsg" default-value-expr="guid()" keys="FGUID"><has-relation relation="FGUID"></has-relation><has-relation relation="version" default-value-expr="0"><label language="zh_CN">版本</label>
</has-relation>
<label language="zh_CN">业务监管</label>
<has-relation relation="fBizRecId"></has-relation>
<has-relation relation="fBizName" size="100" default-value-expr="currentProcessBizName()"></has-relation>


<has-relation relation="fProcess" size="500" default-value-expr="currentProcess()"></has-relation>
<has-relation relation="fTaskId" size="32"></has-relation>
<has-relation relation="fRuleId" data-type="String" size="32"></has-relation><has-relation relation="fRuleName" data-type="String" size="100"></has-relation>
<has-relation relation="fRuleContent" data-type="Text"></has-relation>
<has-relation relation="fExplainContent" data-type="Text"><label language="zh_CN">依据内容</label>
</has-relation>
<has-relation relation="fExplainFiles" data-type="Text"></has-relation>
<has-relation relation="fCreatePerson" size="32" default-value-expr="currentPersonID()"></has-relation>
<has-relation relation="fCreatePersonName" size="20" default-value-expr="currentPersonMemberNameWithAgent()"></has-relation>


<has-relation relation="fCreateTime" default-value-expr="currentDateTime()"></has-relation>
</concept>
<relation name="fRuleName" data-type="String"><label language="zh_CN">规则名称</label>
</relation>
<relation name="fRuleContent" data-type="Text"><label language="zh_CN">规则内容</label>
</relation>
<relation name="fExplainContent" data-type="Text"><label language="zh_CN">依据内容</label>
</relation>
<relation name="fExplainFiles" data-type="Text"><label language="zh_CN">依据附件</label>
</relation>
<relation name="fRuleId" data-type="String"><label language="zh_CN">规则ID</label>
</relation>
</model>
