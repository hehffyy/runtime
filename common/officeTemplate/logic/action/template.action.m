<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <action global="true" name="queryB_OfficeTemplateAction" procedure="bizQueryProcedure"> 
    <permission name="range" type="List"/>  
    <private name="concept" type="String" value="B_OfficeTemplate"/>  
    <private name="select" type="String" value="B_OfficeTemplate.*"/>  
    <private name="from" type="String" value="B_OfficeTemplate B_OfficeTemplate"/>  
    <private name="aggregate" type="String"/>  
    <private name="dataModel" type="String" value="/common/officeTemplate/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="condition" type="String"/>  
    <public name="distinct" type="Boolean" value="false"/>  
    <public name="idColumn" type="String" value="B_OfficeTemplate"/>  
    <public name="filter" type="String"/>  
    <public name="limit" type="Integer"/>  
    <public name="offset" type="Integer"/>  
    <public name="columns" type="String"/>  
    <public name="orderBy" type="String"/>  
    <public name="aggregateColumns" type="String"/>  
    <public name="variables" type="Map"/> 
  </action>  
  <action global="true" name="checkVersionState" procedure="checkVersionStateProcedure"> 
    <label language="zh_CN">checkVersionState</label>  
    <public name="bizKey" type="String"/>  
    <public type="String" name="version"/> 
  </action>  
  <action global="true" name="getFID" procedure="getFIDProcedure"> 
    <label language="zh_CN">得到FID</label>  
    <public name="fBizKey" type="String"/>  
    <public name="fVersionName" type="String"/> 
  </action>  
  <action global="true" name="saveB_OfficeTemplateAction" procedure="bizSaveProcedure"> 
    <permission name="insertRange" type="List"/>  
    <permission name="deleteRange" type="List"/>  
    <permission name="updateRange" type="List"/>  
    <private name="concept" type="String" value="B_OfficeTemplate"/>  
    <private name="dataModel" type="String" value="/common/officeTemplate/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="readOnly" type="String"/>  
    <protected name="notNull" type="String"/>  
    <public name="table" required="true" type="Table"/> 
  </action>  
  <action global="true" name="createB_OfficeTemplateAction" procedure="bizCreateProcedure"> 
    <private name="concept" type="String" value="B_OfficeTemplate"/>  
    <private name="fnModel" type="String"/>  
    <public name="table" required="true" type="Table"/>  
    <public name="defaultValues" type="Map"/> 
  </action>  
  <action global="true" name="queryB_BookMarkAction" procedure="bizQueryProcedure"> 
    <permission name="range" type="List"/>  
    <private name="concept" type="String" value="B_BookMark"/>  
    <private name="select" type="String" value="B_BookMark.*"/>  
    <private name="from" type="String" value="B_BookMark B_BookMark"/>  
    <private name="aggregate" type="String"/>  
    <private name="dataModel" type="String" value="/common/officeTemplate/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="condition" type="String"/>  
    <public name="distinct" type="Boolean" value="false"/>  
    <public name="idColumn" type="String" value="B_BookMark"/>  
    <public name="filter" type="String"/>  
    <public name="limit" type="Integer"/>  
    <public name="offset" type="Integer"/>  
    <public name="columns" type="String"/>  
    <public name="orderBy" type="String"/>  
    <public name="aggregateColumns" type="String"/>  
    <public name="variables" type="Map"/> 
  </action>  
  <action global="true" name="saveB_BookMarkAction" procedure="bizSaveProcedure"> 
    <permission name="insertRange" type="List"/>  
    <permission name="deleteRange" type="List"/>  
    <permission name="updateRange" type="List"/>  
    <private name="concept" type="String" value="B_BookMark"/>  
    <private name="dataModel" type="String" value="/common/officeTemplate/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="readOnly" type="String"/>  
    <protected name="notNull" type="String"/>  
    <public name="table" required="true" type="Table"/> 
  </action>  
  <action global="true" name="createB_BookMarkAction" procedure="bizCreateProcedure"> 
    <private name="concept" type="String" value="B_BookMark"/>  
    <private name="fnModel" type="String"/>  
    <public name="table" required="true" type="Table"/>  
    <public name="defaultValues" type="Map"/> 
  </action>  
  <action global="true" name="checkOutOffice" procedure="checkOutOfficeProcedure"> 
    <label language="zh_CN">签出Office</label>  
    <public name="bizKey" type="String"/>  
    <public name="kind" type="String"/>  
    <public name="version" type="String"/>  
    <public name="parentVersion" type="String"/>  
    <public name="template" type="String"/> 
  </action>  
  <action global="true" name="checkInOffice" procedure="checkInOfficeProcedure"> 
    <label language="zh_CN">签入Office</label>  
    <public name="versionId" type="String"/>  
    <public name="tempFile" type="String"/>  
    <public name="isTemplate" type="Boolean"/>  
    <public name="ext" type="String"/> 
  </action>  
  <action global="true" name="downOffice" procedure="downOfficeProcedure"> 
    <label language="zh_CN">下载office</label>  
    <public name="bizKey" type="String"/>  
    <public name="version" type="String"/>  
    <public name="parentVersion" type="String"/>  
    <public name="template" type="String"/>  
    <public name="kind" type="String"/> 
  </action>  
  <action global="true" name="initTemplate" procedure="initTemplateProcedure"> 
    <label language="zh_CN">初始化模板</label>  
    <public name="bizKey" type="String"/>  
    <public name="version" type="String"/>  
    <public name="parentVersion" type="String"/>  
    <public name="template" type="String"/>  
    <public name="variants" type="List"/>  
    <public name="kind" type="String"/> 
  </action>  
  <action global="true" name="queryB_OfficeVersionAction" procedure="queryB_OfficeVersionProcedure"> 
    <permission name="range" type="List"/>  
    <private name="concept" type="String" value="B_OfficeVersion"/>  
    <private name="select" type="String" value="B_OfficeVersion.*"/>  
    <private name="from" type="String" value="B_OfficeVersion B_OfficeVersion"/>  
    <private name="aggregate" type="String"/>  
    <private name="dataModel" type="String" value="/common/officeTemplate/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="condition" type="String"/>  
    <public name="distinct" type="Boolean" value="false"/>  
    <public name="idColumn" type="String" value="B_OfficeVersion"/>  
    <public name="filter" type="String"/>  
    <public name="limit" type="Integer"/>  
    <public name="offset" type="Integer"/>  
    <public name="columns" type="String"/>  
    <public name="orderBy" type="String"/>  
    <public name="aggregateColumns" type="String"/>  
    <public name="variables" type="Map"/> 
  </action>  
  <action global="true" name="saveB_OfficeVersionAction" procedure="bizSaveProcedure"> 
    <permission name="insertRange" type="List"/>  
    <permission name="deleteRange" type="List"/>  
    <permission name="updateRange" type="List"/>  
    <private name="concept" type="String" value="B_OfficeVersion"/>  
    <private name="dataModel" type="String" value="/common/officeTemplate/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="readOnly" type="String"/>  
    <protected name="notNull" type="String"/>  
    <public name="table" required="true" type="Table"/> 
  </action>  
  <action global="true" name="createB_OfficeVersionAction" procedure="bizCreateProcedure"> 
    <private name="concept" type="String" value="B_OfficeVersion"/>  
    <private name="fnModel" type="String"/>  
    <public name="table" required="true" type="Table"/>  
    <public name="defaultValues" type="Map"/> 
  </action>  
  <action global="true" name="browOffice" procedure="browOfficeProcedure"> 
    <public name="versionId" type="String"/> 
  </action>  
  <action global="true" name="deleteVersion" procedure="deleteVersionProcedure"> 
    <public name="bizKey" type="String"/> 
  </action>  
  <action global="true" name="genReport" procedure="genReportProcedure"> 
    <public name="templateKey" type="String"/>  
    <public name="sqlParam" type="String"/>  
    <public name="fileKind" type="String"/>  
    <label language="zh_CN">生成报表</label> 
  </action>  
  <action global="true" name="browOfficeByTemplate" procedure="browOfficeByTemplateProcedure"> 
    <label language="zh_CN">浏览模板</label>  
    <public name="template" type="String"/> 
  </action>  
  <action global="true" name="checkInOffice2" procedure="checkInOffice2Procedure"> 
    <label language="zh_CN">签入Office2</label>  
    <public name="bizKey" type="String"/>  
    <public name="kind" type="String"/>  
    <public name="version" type="String"/>  
    <public name="parentVersion" type="String"/>  
    <public name="template" type="String"/>  
    <public name="tempFile" type="String"/>  
    <public name="ext" type="String"/> 
  </action>  
  <action global="true" name="getBookMarks" procedure="getBookMarksProcedure"> 
    <label language="zh_CN">获得模板书签</label>  
    <public name="templateid" type="String"/>  
    <public name="bizrecid" type="String"/> 
  </action>  
  <action global="true" name="queryB_OfficeVerDetailAction" procedure="bizQueryProcedure"> 
    <permission name="range" type="List"/>  
    <private name="concept" type="String" value="B_OfficeVerDetail"/>  
    <private name="select" type="String" value="B_OfficeVerDetail.*"/>  
    <private name="from" type="String" value="B_OfficeVerDetail B_OfficeVerDetail"/>  
    <private name="aggregate" type="String"/>  
    <private name="dataModel" type="String" value="/common/officeTemplate/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="condition" type="String"/>  
    <public name="distinct" type="Boolean" value="false"/>  
    <public name="idColumn" type="String" value="B_OfficeVerDetail"/>  
    <public name="filter" type="String"/>  
    <public name="limit" type="Integer"/>  
    <public name="offset" type="Integer"/>  
    <public name="columns" type="String"/>  
    <public name="orderBy" type="String"/>  
    <public name="aggregateColumns" type="String"/>  
    <public name="variables" type="Map"/> 
  </action>  
  <action name="saveB_OfficeVerDetailAction" procedure="bizSaveProcedure"> 
    <permission name="insertRange" type="List"/>  
    <permission name="deleteRange" type="List"/>  
    <permission name="updateRange" type="List"/>  
    <private name="concept" type="String" value="B_OfficeVerDetail"/>  
    <private name="dataModel" type="String" value="/common/officeTemplate/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="readOnly" type="String"/>  
    <protected name="notNull" type="String"/>  
    <public name="table" required="true" type="Table"/> 
  </action>  
  <action name="createB_OfficeVerDetailAction" procedure="bizCreateProcedure"> 
    <private name="concept" type="String" value="B_OfficeVerDetail"/>  
    <private name="fnModel" type="String"/>  
    <public name="table" required="true" type="Table"/>  
    <public name="defaultValues" type="Map"/> 
  </action> 
</model>
