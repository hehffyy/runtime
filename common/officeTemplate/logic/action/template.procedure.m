<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model" xmlns:m="http://www.justep.com/model">  
  <procedure name="checkOutOfficeProcedure" code-model="/common/officeTemplate/logic/code"
    code="OfficeTemplate.checkOutOffice"> 
    <parameter name="template" type="String"/>  
    <parameter name="bizKey" type="String"/>  
    <parameter name="version" type="String"/>  
    <parameter name="parentVersion" type="String"/>  
    <parameter name="kind" type="String"/> 
  </procedure>  
  <procedure name="checkInOfficeProcedure" code-model="/common/officeTemplate/logic/code"
    code="OfficeTemplate.checkInOffice"> 
    <parameter name="versionId" type="String"/>  
    <parameter name="tempFile" type="String"/>  
    <parameter name="isTemplate" type="Boolean"/>  
    <parameter name="ext" type="String"/> 
  </procedure>  
  <procedure name="downOfficeProcedure" code-model="/common/officeTemplate/logic/code"
    code="OfficeTemplate.downOffice"> 
    <parameter name="bizKey" type="String"/>  
    <parameter name="version" type="String"/>  
    <parameter name="parentVersion" type="String"/>  
    <parameter name="template" type="String"/>  
    <parameter name="kind" type="String"/> 
  </procedure>  
  <procedure name="initTemplateProcedure" code-model="/common/officeTemplate/logic/code"
    code="OfficeTemplate.initTemplate"> 
    <parameter name="bizKey" type="String"/>  
    <parameter name="version" type="String"/>  
    <parameter name="parentVersion" type="String"/>  
    <parameter name="template" type="String"/>  
    <parameter name="variants" type="List"/>  
    <parameter name="kind" type="String"/> 
  </procedure>  
  <procedure name="browOfficeProcedure" code-model="/common/officeTemplate/logic/code"
    code="OfficeTemplate.browOffice"> 
    <parameter name="versionId" type="String"/> 
  </procedure>  
  <procedure name="deleteVersionProcedure" code-model="/common/officeTemplate/logic/code"
    code="OfficeTemplate.deleteVersion"> 
    <parameter name="bizKey" type="String"/> 
  </procedure>  
  <procedure name="genReportProcedure" code-model="/common/officeTemplate/logic/code"
    code="OfficeTemplate.genReport"> 
    <parameter name="templateKey" type="String"/>  
    <parameter name="sqlParam" type="String"/>  
    <parameter name="fileKind" type="String"/> 
  </procedure>  
  <procedure name="browOfficeByTemplateProcedure" code-model="/common/officeTemplate/logic/code"
    code="OfficeTemplate.browOfficeByTemplate"> 
    <parameter name="template" type="String"/> 
  </procedure>  
  <procedure name="checkVersionStateProcedure" code-model="/common/officeTemplate/logic/code"
    code="OfficeTemplate.checkVersionState"> 
    <parameter name="bizKey" type="String"/>  
    <parameter name="version" type="String"/> 
  </procedure>  
  <procedure name="getFIDProcedure" code-model="/common/officeTemplate/logic/code"
    code="OfficeTemplate.getFID"> 
    <parameter name="fBizKey" type="String"/>  
    <parameter name="fVersionName" type="String"/> 
  </procedure>  
  <procedure name="checkInOffice2Procedure" code-model="/common/officeTemplate/logic/code"
    code="OfficeTemplate.checkInOffice2"> 
    <parameter name="bizKey" type="String"/>  
    <parameter name="kind" type="String"/>  
    <parameter name="version" type="String"/>  
    <parameter name="parentVersion" type="String"/>  
    <parameter name="template" type="String"/>  
    <parameter name="tempFile" type="String"/>  
    <parameter name="ext" type="String"/> 
  </procedure>  
  <procedure name="getBookMarksProcedure" code-model="/common/officeTemplate/logic/code"
    code="OfficeTemplate.getBookMarks"> 
    <parameter name="templateid" type="String"/>  
    <parameter name="bizrecid" type="String"/> 
  </procedure>  
  <procedure name="queryB_OfficeVersionProcedure" code-model="/common/officeTemplate/logic/code"
    code="OfficeTemplate.queryB_OfficeVersion"> 
    <parameter name="range" type="List"/>  
    <parameter name="concept" type="String"/>  
    <parameter name="select" type="String"/>  
    <parameter name="from" type="String"/>  
    <parameter name="aggregate" type="String"/>  
    <parameter name="dataModel" type="String"/>  
    <parameter name="fnModel" type="String"/>  
    <parameter name="condition" type="String"/>  
    <parameter name="distinct" type="Boolean"/>  
    <parameter name="idColumn" type="String"/>  
    <parameter name="filter" type="String"/>  
    <parameter name="limit" type="Integer"/>  
    <parameter name="offset" type="Integer"/>  
    <parameter name="columns" type="String"/>  
    <parameter name="orderBy" type="String"/>  
    <parameter name="aggregateColumns" type="String"/>  
    <parameter name="variables" type="Map"/> 
  </procedure> 
</model>
