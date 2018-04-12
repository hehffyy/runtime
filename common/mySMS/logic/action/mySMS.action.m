<?xml version="1.0" encoding="UTF-8" standalone="no"?><model xmlns="http://www.justep.com/model"><action name="queryB_receivePersonTemplateAction" procedure="bizQueryProcedure"><permission name="range" type="List"/>
<private name="concept" type="String" value="B_receivePersonTemplate"/>
<private name="select" type="String" value="B_receivePersonTemplate.*"/>
<private name="from" type="String" value="B_receivePersonTemplate B_receivePersonTemplate"/>
<private name="aggregate" type="String"/>
<private name="dataModel" type="String" value="/common/mySMS/data"/>
<private name="fnModel" type="String"/>
<protected name="condition" type="String"/>
<public name="distinct" type="Boolean" value="false"/>
<public name="idColumn" type="String" value="B_receivePersonTemplate"/>
<public name="filter" type="String"/>
<public name="limit" type="Integer"/>
<public name="offset" type="Integer"/>
<public name="columns" type="String"/>
<public name="orderBy" type="String"/>
<public name="aggregateColumns" type="String"/>
<public name="variables" type="Map"/>
</action>
<action name="saveB_receivePersonTemplateAction" procedure="bizSaveProcedure"><permission name="insertRange" type="List"/>
<permission name="deleteRange" type="List"/>
<permission name="updateRange" type="List"/>
<private name="concept" type="String" value="B_receivePersonTemplate"/>
<private name="dataModel" type="String" value="/common/mySMS/data"/>
<private name="fnModel" type="String"/>
<protected name="readOnly" type="String"/>
<protected name="notNull" type="String"/>
<public name="table" required="true" type="Table"/>
</action>
<action name="createB_receivePersonTemplateAction" procedure="bizCreateProcedure"><private name="concept" type="String" value="B_receivePersonTemplate"/>
<private name="fnModel" type="String"/>
<public name="table" required="true" type="Table"/>
<public name="defaultValues" type="Map"/>
</action>
<action name="queryB_personPhoneTemplateAction" procedure="bizQueryProcedure"><permission name="range" type="List"/>
<private name="concept" type="String" value="B_personPhoneTemplate"/>
<private name="select" type="String" value="B_personPhoneTemplate.*"/>
<private name="from" type="String" value="B_personPhoneTemplate B_personPhoneTemplate"/>
<private name="aggregate" type="String"/>
<private name="dataModel" type="String" value="/common/mySMS/data"/>
<private name="fnModel" type="String"/>
<protected name="condition" type="String" value="B_personPhoneTemplate.fCreatePersonID  = :currentPersonID()"/>
<public name="distinct" type="Boolean" value="false"/>
<public name="idColumn" type="String" value="B_personPhoneTemplate"/>
<public name="filter" type="String"/>
<public name="limit" type="Integer"/>
<public name="offset" type="Integer"/>
<public name="columns" type="String"/>
<public name="orderBy" type="String"/>
<public name="aggregateColumns" type="String"/>
<public name="variables" type="Map"/>
</action>
<action name="saveB_personPhoneTemplateAction" procedure="bizSaveProcedure"><permission name="insertRange" type="List"/>
<permission name="deleteRange" type="List"/>
<permission name="updateRange" type="List"/>
<private name="concept" type="String" value="B_personPhoneTemplate"/>
<private name="dataModel" type="String" value="/common/mySMS/data"/>
<private name="fnModel" type="String"/>
<protected name="readOnly" type="String"/>
<protected name="notNull" type="String"/>
<public name="table" required="true" type="Table"/>
</action>
<action name="createB_personPhoneTemplateAction" procedure="bizCreateProcedure"><private name="concept" type="String" value="B_personPhoneTemplate"/>
<private name="fnModel" type="String"/>
<public name="table" required="true" type="Table"/>
<public name="defaultValues" type="Map"/>
</action>
<action name="queryB_smsInfoAction" procedure="bizQueryProcedure"><permission name="range" type="List"/>
<private name="concept" type="String" value="B_smsInfo"/>
<private name="select" type="String" value="B_smsInfo.*"/>
<private name="from" type="String" value="B_smsInfo B_smsInfo"/>
<private name="aggregate" type="String"/>
<private name="dataModel" type="String" value="/common/mySMS/data"/>
<private name="fnModel" type="String"/>
<protected name="condition" type="String" value="B_smsInfo.fSenderPersonID  = :currentPersonID()"/>
<public name="distinct" type="Boolean" value="false"/>
<public name="idColumn" type="String" value="B_smsInfo"/>
<public name="filter" type="String"/>
<public name="limit" type="Integer"/>
<public name="offset" type="Integer"/>
<public name="columns" type="String"/>
<public name="orderBy" type="String"/>
<public name="aggregateColumns" type="String"/>
<public name="variables" type="Map"/>
</action>
<action name="saveB_smsInfoAction" procedure="bizSaveProcedure"><permission name="insertRange" type="List"/>
<permission name="deleteRange" type="List"/>
<permission name="updateRange" type="List"/>
<private name="concept" type="String" value="B_smsInfo"/>
<private name="dataModel" type="String" value="/common/mySMS/data"/>
<private name="fnModel" type="String"/>
<protected name="readOnly" type="String"/>
<protected name="notNull" type="String"/>
<public name="table" required="true" type="Table"/>
</action>
<action name="createB_smsInfoAction" procedure="bizCreateProcedure"><private name="concept" type="String" value="B_smsInfo"/>
<private name="fnModel" type="String"/>
<public name="table" required="true" type="Table"/>
<public name="defaultValues" type="Map"/>
</action>
<action name="queryB_smsReceivePersonAction" procedure="bizQueryProcedure"><permission name="range" type="List"/>
<private name="concept" type="String" value="B_smsReceivePerson"/>
<private name="select" type="String" value="B_smsReceivePerson.*"/>
<private name="from" type="String" value="B_smsReceivePerson B_smsReceivePerson"/>
<private name="aggregate" type="String"/>
<private name="dataModel" type="String" value="/common/mySMS/data"/>
<private name="fnModel" type="String"/>
<protected name="condition" type="String"/>
<public name="distinct" type="Boolean" value="false"/>
<public name="idColumn" type="String" value="B_smsReceivePerson"/>
<public name="filter" type="String"/>
<public name="limit" type="Integer"/>
<public name="offset" type="Integer"/>
<public name="columns" type="String"/>
<public name="orderBy" type="String"/>
<public name="aggregateColumns" type="String"/>
<public name="variables" type="Map"/>
</action>
<action name="saveB_smsReceivePersonAction" procedure="bizSaveProcedure"><permission name="insertRange" type="List"/>
<permission name="deleteRange" type="List"/>
<permission name="updateRange" type="List"/>
<private name="concept" type="String" value="B_smsReceivePerson"/>
<private name="dataModel" type="String" value="/common/mySMS/data"/>
<private name="fnModel" type="String"/>
<protected name="readOnly" type="String"/>
<protected name="notNull" type="String"/>
<public name="table" required="true" type="Table"/>
</action>
<action name="createB_smsReceivePersonAction" procedure="bizCreateProcedure"><private name="concept" type="String" value="B_smsReceivePerson"/>
<private name="fnModel" type="String"/>
<public name="table" required="true" type="Table"/>
<public name="defaultValues" type="Map"/>
</action>
<action global="true" name="sendSMSMessageAction" procedure="sendSMSMessageProcedure"><label language="zh_CN">发送短信</label>
<public name="smsID" type="String"/>
</action>
<action name="queryVIEW_SMSINFOAction" procedure="bizQueryProcedure"><permission name="range" type="List"/>
<private name="concept" type="String" value="VIEW_SMSINFO"/>
<private name="select" type="String" value="VIEW_SMSINFO.*"/>
<private name="from" type="String" value="VIEW_SMSINFO VIEW_SMSINFO"/>
<private name="aggregate" type="String"/>
<private name="dataModel" type="String" value="/common/mySMS/data"/>
<private name="fnModel" type="String"/>
<protected name="condition" type="String" value="VIEW_SMSINFO.FSENDERPERSONID = :currentPersonID()"/>
<public name="distinct" type="Boolean" value="false"/>
<public name="idColumn" type="String" value="VIEW_SMSINFO"/>
<public name="filter" type="String"/>
<public name="limit" type="Integer"/>
<public name="offset" type="Integer"/>
<public name="columns" type="String"/>
<public name="orderBy" type="String" value="VIEW_SMSINFO.FSENDTIME desc"/>
<public name="aggregateColumns" type="String"/>
<public name="variables" type="Map"/>
</action>
<action name="queryVIEW_SMSRECEIVEINFOAction" procedure="bizQueryProcedure"><permission name="range" type="List"/>
<private name="concept" type="String" value="VIEW_SMSRECEIVEINFO"/>
<private name="select" type="String" value="VIEW_SMSRECEIVEINFO.*"/>
<private name="from" type="String" value="VIEW_SMSRECEIVEINFO VIEW_SMSRECEIVEINFO"/>
<private name="aggregate" type="String"/>
<private name="dataModel" type="String" value="/common/mySMS/data"/>
<private name="fnModel" type="String"/>
<protected name="condition" type="String"/>
<public name="distinct" type="Boolean" value="false"/>
<public name="idColumn" type="String" value="VIEW_SMSRECEIVEINFO"/>
<public name="filter" type="String"/>
<public name="limit" type="Integer"/>
<public name="offset" type="Integer"/>
<public name="columns" type="String"/>
<public name="orderBy" type="String"/>
<public name="aggregateColumns" type="String"/>
<public name="variables" type="Map"/>
</action>
<action global="true" name="copyPersonInfoFromOrgToRecAction" procedure="copyPersonInfoFromOrgToRecProcedure"><label language="zh_CN">复制人员信息从组织机构到接收者</label>

<public name="personIDs" type="List"/>
<public name="smsID" type="String"/>
</action>
<action global="true" name="copyPersonInfoFromOrgToTempAction" procedure="copyPersonInfoFromOrgToTempProcedure"><label language="zh_CN">复制人员信息从组织机构到接收者模版</label>
<public name="personIDs" type="List"/>
<public name="templateID" type="String"/>
</action>
<action global="true" name="copyPersonInfoFromTempToRecAction" procedure="copyPersonInfoFromTempToRecProcedure"><label language="zh_CN">复制人员信息从模版到接收者</label>
<public name="templateID" type="String"/>
<public name="smsID" type="String"/>
</action>
</model>