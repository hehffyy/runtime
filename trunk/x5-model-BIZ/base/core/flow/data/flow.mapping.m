<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <store name="B_SuperviseMsg"/>  
  <store name="B_BizRecAttention"/>  
  <store name="B_FlowOperationLog"/>  
  <store name="B_PrepBizRec"/>  
  <store name="B_BizRecRelation"/>  
  <store name="B_BizRecAttr"/>  
  <store name="B_PrepLog"/>  
  <store name="B_ActivityGroupInstanceTask"/>  
  <store name="B_ActivityGroupInstance"/>  
  <store name="B_BizApprove"/>  
  <store name="B_FlowCoopRecord"/>  
  <store name="B_BizCooperation"/>  
  <store name="B_BizRec"/>  
  <mapping concept="B_ActivityGroupInstanceTask"> 
    <table name="B_ActivityGroupInstanceTask" type="owner-table"> 
      <index fields="fTaskId,fGroupInstance" name="IDX_378194" type="UNIQUE"/>  
      <index fields="fGroupInstance" name="IDX_853253154" type="NORMAL"/> 
    </table> 
  </mapping>  
  <mapping concept="B_ActivityGroupInstance"> 
    <table name="B_ActivityGroupInstance" type="owner-table"> 
      <index fields="fFlowId" name="IDX_1024801046" type="NORMAL"/>  
      <index fields="fBizRecId" name="IDX_524062565" type="NORMAL"/>  
      <index fields="fGroupId" name="IDX__1531650195" type="NORMAL"/> 
    </table> 
  </mapping>  
  <mapping concept="B_BizRec"> 
    <table name="B_BizRec" type="owner-table"> 
      <index fields="fFlowId" name="IDX_B_BizRec_fFlowId" type="UNIQUE"/> 
    </table> 
  </mapping>  
  <mapping concept="B_BizCooperation"> 
    <table name="B_BizCooperation" type="owner-table"/> 
  </mapping>  
  <mapping concept="B_FlowCoopRecord"> 
    <table name="B_FlowCoopRecord" type="owner-table"> 
      <index fields="fApproveId" name="IDX__1802122410" type="NORMAL"/> 
    </table> 
  </mapping>  
  <mapping concept="B_BizApprove"> 
    <table name="B_BizApprove" type="owner-table"/> 
  </mapping>  
  <mapping concept="B_PrepBizRec"> 
    <table name="B_PrepBizRec" type="owner-table"/> 
  </mapping>  
  <mapping concept="B_BizRecRelation"> 
    <table name="B_BizRecRelation" type="owner-table"> 
      <index fields="fParentID" name="B_RECREL_PARENT" type="NORMAL"/> 
    </table> 
  </mapping>  
  <mapping concept="B_BizRecAttention"> 
    <table name="B_BizRecAttention" type="owner-table"> 
      <index fields="fBizRecId,fPersonID" name="IDX__34384936" type="UNIQUE"/> 
    </table> 
  </mapping>  
  <mapping concept="B_FlowOperationLog"> 
    <table name="B_FlowOperationLog" type="owner-table"> 
      <key field="fID" type="String"/>  
      <index fields="fBizRecId" name="IDX__61236983" type="NORMAL"/>  
      <index fields="fPersonID" name="IDX__884992063" type="NORMAL"/> 
    </table> 
  </mapping>  
  <mapping concept="B_SuperviseMsg"> 
    <table name="B_SuperviseMsg" type="owner-table"> 
      <index fields="fTaskId,fBizRecId,fRuleId,fCreatePerson" name="IDX__1066058147"
        type="UNIQUE"/> 
    </table> 
  </mapping> 
</model>
