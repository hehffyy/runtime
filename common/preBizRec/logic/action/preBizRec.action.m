<?xml version="1.0" encoding="utf-8" standalone="no"?><model xmlns="http://www.justep.com/model">  
  <action global="true" name="queryV_PreBizRecAction" procedure="queryV_PreBizRecProcedure"> 
    <permission name="range" type="List"/>  
    <private name="concept" type="String" value="V_PreBizRec"/>  
    <private name="select" type="String" value="B_PrepBizRec.fBizRecId as fBizRecId,B_PrepBizRec.fSerialNo as fSerialNo,B_PrepBizRec.fYWLX as fYWLX,B_PrepBizRec.fXMMC as fXMMC,B_PrepBizRec.fLWRQ as fLWRQ,B_PrepBizRec.fSFXYSW as fSFXYSW,B_PrepBizRec.fItemCode as fItemCode,T_SYS_SPSX_YWLC.fProcess as fProcess,T_SYS_SPSX_YWLC.fProcessName as fProcessName,T_SYS_SPSX_YWLC.fStartActivity as fStartActivity,B_PrepBizRec.fSQDW as fSQDW,B_PrepBizRec.fStatus as fStatus,(0) as fRemainDays,T_SYS_SPSX_YWLC.fBrowUrl as fBrowUrl,B_PrepBizRec.fYSTGSJ as fYSTGSJ,B_PrepBizRec.fJSSJ as fJSSJ,B_PrepBizRec.fSQRXM as fSQRXM,B_PrepBizRec.fSQRYJ as fSQRYJ,B_PrepBizRec.fSQRSJ as fSQRSJ,B_PrepBizRec.fSJR as fSJR,B_PrepBizRec.fSJDZ as fSJDZ,B_PrepBizRec.fSJRDH as fSJRDH,B_PrepBizRec.fYJFS as fYJFS,B_PrepBizRec.fColb as fColb"/>  
    <private name="from" type="String" value="B_PrepBizRec B_PrepBizRec  optional  join T_SYS_SPSX_YWLC T_SYS_SPSX_YWLC on B_PrepBizRec.fBizMappingId = T_SYS_SPSX_YWLC.fID optional  join T_SYS_SPSXXX T_SYS_SPSXXX on B_PrepBizRec.fItemCode = T_SYS_SPSXXX.fFWSXBH"/>  
    <private name="aggregate" type="String"/>  
    <private name="dataModel" type="String" value="/common/preBizRec/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="condition" type="String" value="(B_PrepBizRec.fStatus = '预审通过') OR ( B_PrepBizRec.fStatus in ('未受理', '预审通过') )"/>  
    <public name="distinct" type="Boolean" value="false"/>  
    <public name="idColumn" type="String" value="fBizRecId"/>  
    <public name="filter" type="String"/>  
    <public name="limit" type="Integer"/>  
    <public name="offset" type="Integer"/>  
    <public name="columns" type="String"/>  
    <public name="orderBy" type="String" value="B_PrepBizRec.fLWRQ asc"/>  
    <public name="aggregateColumns" type="String"/>  
    <public name="variables" type="Map"/> 
  </action>  
  <action global="true" name="ystgAction" procedure="ystgProcedure"> 
    <label language="zh_CN">预审通过</label>  
    <public name="bizRecId" type="String"/> 
  </action>  
  <action global="true" name="slAction" procedure="slProcedure"> 
    <label language="zh_CN">受理</label>  
    <public name="bizRecId" type="String"/> 
  </action>  
  <action global="true" name="queryNet_FilesAction" procedure="bizQueryProcedure"> 
    <permission name="range" type="List"/>  
    <private name="concept" type="String" value="Net_Files"/>  
    <private name="select" type="String" value="Net_Files.*"/>  
    <private name="from" type="String" value="Net_Files Net_Files"/>  
    <private name="aggregate" type="String"/>  
    <private name="dataModel" type="String" value="/common/preBizRec/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="condition" type="String"/>  
    <public name="distinct" type="Boolean" value="false"/>  
    <public name="idColumn" type="String" value="Net_Files"/>  
    <public name="filter" type="String"/>  
    <public name="limit" type="Integer"/>  
    <public name="offset" type="Integer"/>  
    <public name="columns" type="String"/>  
    <public name="orderBy" type="String"/>  
    <public name="aggregateColumns" type="String"/>  
    <public name="variables" type="Map"/> 
  </action>  
  <action name="saveNet_FilesAction" procedure="bizSaveProcedure"> 
    <permission name="insertRange" type="List"/>  
    <permission name="deleteRange" type="List"/>  
    <permission name="updateRange" type="List"/>  
    <private name="concept" type="String" value="Net_Files"/>  
    <private name="dataModel" type="String" value="/common/preBizRec/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="readOnly" type="String"/>  
    <protected name="notNull" type="String"/>  
    <public name="table" required="true" type="Table"/> 
  </action>  
  <action name="createNet_FilesAction" procedure="bizCreateProcedure"> 
    <private name="concept" type="String" value="Net_Files"/>  
    <private name="fnModel" type="String"/>  
    <public name="table" required="true" type="Table"/>  
    <public name="defaultValues" type="Map"/> 
  </action>  
  <action global="true" name="byslAction" procedure="byslProcedure"> 
    <label language="zh_CN">不予受理</label>  
    <public name="bizRecId" type="String"/>  
    <public name="reason" type="String"/> 
  </action>  
  <action global="true" name="queryV_BizMappingAction" procedure="bizQueryProcedure"> 
    <permission name="range" type="List"/>  
    <private name="concept" type="String" value="V_BizMapping"/>  
    <private name="select" type="String" value="T_SYS_SPSX_YWLC.fID as fID,T_SYS_SPSX_YWLC.fProcessName as fProcessName,T_SYS_SPSX_YWLC.fBrowUrl as fBrowUrl,T_SYS_SPSXXX.fFWSXBH as fFWSXBH"/>  
    <private name="from" type="String" value="T_SYS_SPSX_YWLC T_SYS_SPSX_YWLC  join T_SYS_SPSXXX T_SYS_SPSXXX on T_SYS_SPSXXX = T_SYS_SPSX_YWLC.fSPSXBH"/>  
    <private name="aggregate" type="String"/>  
    <private name="dataModel" type="String" value="/common/preBizRec/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="condition" type="String"/>  
    <public name="distinct" type="Boolean" value="false"/>  
    <public name="idColumn" type="String" value="fID"/>  
    <public name="filter" type="String"/>  
    <public name="limit" type="Integer"/>  
    <public name="offset" type="Integer"/>  
    <public name="columns" type="String"/>  
    <public name="orderBy" type="String"/>  
    <public name="aggregateColumns" type="String"/>  
    <public name="variables" type="Map"/> 
  </action>  
  <action global="true" name="setBizMapAction" procedure="setBizMapProcedure"> 
    <label language="zh_CN">设置业务映射</label>  
    <public name="bizRecId" type="String"/>  
    <public name="bizMappingId" type="String"/> 
  </action>  
  <action global="true" name="queryV_FilesAction" procedure="bizQueryProcedure"> 
    <permission name="range" type="List"/>  
    <private name="concept" type="String" value="Net_Files"/>  
    <private name="select" type="String" value="f.*,(case when cl.fSPCLMC is null  then f.fType  else  cl.fSPCLMC end) as fMName,   (case when f.fNetUrl is null then 0 else 1 end ) as fFlag"/>  
    <private name="from" type="String" value="Net_Files f  optional  join T_SYS_SPSXCL cl on f.fType = cl.fSPCLBH"/>  
    <private name="aggregate" type="String"/>  
    <private name="dataModel" type="String" value="/common/preBizRec/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="condition" type="String"/>  
    <public name="distinct" type="Boolean" value="false"/>  
    <public name="idColumn" type="String" value="f"/>  
    <public name="filter" type="String"/>  
    <public name="limit" type="Integer"/>  
    <public name="offset" type="Integer"/>  
    <public name="columns" type="String"/>  
    <public name="orderBy" type="String"/>  
    <public name="aggregateColumns" type="String"/>  
    <public name="variables" type="Map"/> 
  </action>  
  <action name="queryNet_MailInfoAction" procedure="bizQueryProcedure">
    <permission name="range" type="List"/>  
    <private name="concept" type="String" value="Net_MailInfo"/>  
    <private name="select" type="String" value="Net_MailInfo.*"/>  
    <private name="from" type="String" value="Net_MailInfo Net_MailInfo"/>  
    <private name="aggregate" type="String"/>  
    <private name="dataModel" type="String" value="/common/preBizRec/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="condition" type="String"/>  
    <public name="distinct" type="Boolean" value="false"/>  
    <public name="idColumn" type="String" value="Net_MailInfo"/>  
    <public name="filter" type="String"/>  
    <public name="limit" type="Integer"/>  
    <public name="offset" type="Integer"/>  
    <public name="columns" type="String"/>  
    <public name="orderBy" type="String"/>  
    <public name="aggregateColumns" type="String"/>  
    <public name="variables" type="Map"/> 
  </action>  
  <action name="saveNet_MailInfoAction" procedure="bizSaveProcedure">
    <permission name="insertRange" type="List"/>  
    <permission name="deleteRange" type="List"/>  
    <permission name="updateRange" type="List"/>  
    <private name="concept" type="String" value="Net_MailInfo"/>  
    <private name="dataModel" type="String" value="/common/preBizRec/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="readOnly" type="String"/>  
    <protected name="notNull" type="String"/>  
    <public name="table" required="true" type="Table"/> 
  </action>  
  <action name="createNet_MailInfoAction" procedure="bizCreateProcedure">
    <private name="concept" type="String" value="Net_MailInfo"/>  
    <private name="fnModel" type="String"/>  
    <public name="table" required="true" type="Table"/>  
    <public name="defaultValues" type="Map"/> 
  </action>  
  <action name="queryNet_MailInfoViewAction" procedure="bizQueryProcedure">
    <permission name="range" type="List"/>  
    <private name="concept" type="String" value="Net_MailInfo"/>  
    <private name="select" type="String" value="Net_MailInfo.*,B_BizRec.fBizName as fBizName,B_BizRecAttr.*"/>  
    <private name="from" type="String" value="Net_MailInfo Net_MailInfo  optional  join B_BizRec B_BizRec on Net_MailInfo.fBizRecId = B_BizRec.fBizRecId optional  join B_BizRecAttr B_BizRecAttr on Net_MailInfo.fBizRecId = B_BizRecAttr.fBizRecId"/>  
    <private name="aggregate" type="String"/>  
    <private name="dataModel" type="String" value="/common/preBizRec/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="condition" type="String" value="B_BizRec.fStatus = 'bsFinished'"/>  
    <public name="distinct" type="Boolean" value="false"/>  
    <public name="idColumn" type="String" value="Net_MailInfo"/>  
    <public name="filter" type="String"/>  
    <public name="limit" type="Integer"/>  
    <public name="offset" type="Integer"/>  
    <public name="columns" type="String"/>  
    <public name="orderBy" type="String"/>  
    <public name="aggregateColumns" type="String"/>  
    <public name="variables" type="Map"/> 
  </action> 
<action name="queryFinishRecAction" procedure="bizQueryProcedure" global="true"><permission name="range" type="List"/>
<private name="concept" type="String" value="B_PrepBizRec"/>
<private name="select" type="String" value="B_PrepBizRec,B_PrepBizRec.fBizRecId as fBizRecId,B_PrepBizRec.fSerialNo as fSerialNo,B_PrepBizRec.fYWLX as fYWLX,B_PrepBizRec.fSQDW as fSQDW,B_PrepBizRec.fLXDH as fLXDH,B_PrepBizRec.fSQRXM as fSQRXM,B_PrepBizRec.fSQRYJ as fSQRYJ,B_PrepBizRec.fSQRSJ as fSQRSJ,B_PrepBizRec.fLWRQ as fLWRQ,B_PrepBizRec.fXMMC as fXMMC,SQL.Nvl(B_PrepBizRec.SMSShzt, '未审核') as SMSShzt,B_PrepBizRec.SMSShr as SMSShr,B_PrepBizRec.SMSShsj as SMSShsj,B_BJJLB.fBJJGMC as fBJJGMC,B_BJJLB.fBJJGMS as fBJJGMS,B_BJJLB.fZFHTHYY as fZFHTHYY,B_BJJLB.fBJSJ as fBJSJ"/>
<private name="from" type="String" value="B_PrepBizRec B_PrepBizRec  optional  join B_BJJLB B_BJJLB on B_PrepBizRec.fBizRecId = B_BJJLB.fBizRecId"/>
<private name="aggregate" type="String"/>
<private name="dataModel" type="String" value="/common/preBizRec/data"/>
<private name="fnModel" type="String"/>
<protected name="condition" type="String" value="B_BJJLB.fBizRecId is not null and  B_PrepBizRec.fTJFS ='厅网站'"/>
<public name="distinct" type="Boolean" value="false"/>
<public name="idColumn" type="String" value="B_PrepBizRec"/>
<public name="filter" type="String"/>
<public name="limit" type="Integer"/>
<public name="offset" type="Integer"/>
<public name="columns" type="String"/>
<public name="orderBy" type="String" value="B_BJJLB.fBJSJ asc"/>
<public name="aggregateColumns" type="String"/>
<public name="variables" type="Map"/>
</action>
<action name="checSmsAction" global="true" procedure="checSmsProcedure"><label language="zh_CN">短信审核</label>
<public type="String" name="ids"></public>
<public type="String" name="state"></public>
</action>
</model>