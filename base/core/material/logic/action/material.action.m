<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <action global="true" name="queryB_FilesAction" procedure="bizQueryProcedure"> 
    <permission name="range" type="List"/>  
    <private name="concept" type="String" value="B_Files"/>  
    <private name="select" type="String" value="B_Files.*"/>  
    <private name="from" type="String" value="B_Files B_Files"/>  
    <private name="aggregate" type="String"/>  
    <private name="dataModel" type="String" value="/base/core/material/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="condition" type="String"/>  
    <public name="distinct" type="Boolean" value="false"/>  
    <public name="idColumn" type="String" value="B_Files"/>  
    <public name="filter" type="String"/>  
    <public name="limit" type="Integer"/>  
    <public name="offset" type="Integer"/>  
    <public name="columns" type="String"/>  
    <public name="orderBy" type="String"/>  
    <public name="aggregateColumns" type="String"/>  
    <public name="variables" type="Map"/> 
  </action>  
  <action global="true" name="saveB_FilesAction" procedure="bizSaveProcedure"> 
    <permission name="insertRange" type="List"/>  
    <permission name="deleteRange" type="List"/>  
    <permission name="updateRange" type="List"/>  
    <private name="concept" type="String" value="B_Files"/>  
    <private name="dataModel" type="String" value="/base/core/material/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="readOnly" type="String"/>  
    <protected name="notNull" type="String"/>  
    <public name="table" required="true" type="Table"/> 
  </action>  
  <action global="true" name="createB_FilesAction" procedure="bizCreateProcedure"> 
    <private name="concept" type="String" value="B_Files"/>  
    <private name="fnModel" type="String"/>  
    <public name="table" required="true" type="Table"/>  
    <public name="defaultValues" type="Map"/> 
  </action>  
  <action global="true" name="saveB_MaterialAction" procedure="bizSaveProcedure"> 
    <permission name="insertRange" type="List"/>  
    <permission name="deleteRange" type="List"/>  
    <permission name="updateRange" type="List"/>  
    <private name="concept" type="String" value="B_Material"/>  
    <private name="dataModel" type="String" value="/base/core/material/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="readOnly" type="String"/>  
    <protected name="notNull" type="String"/>  
    <public name="table" required="true" type="Table"/> 
  </action>  
  <action global="true" name="createB_MaterialAction" procedure="bizCreateProcedure"> 
    <private name="concept" type="String" value="B_Material"/>  
    <private name="fnModel" type="String"/>  
    <public name="table" required="true" type="Table"/>  
    <public name="defaultValues" type="Map"/> 
  </action>  
  <action global="true" name="getBizRecMaterialListAction" procedure="getBizRecMaterialListProcedure"> 
    <public name="bizRecId" required="true" type="String"/>  
    <label language="zh_CN">获得案卷材料树</label> 
  </action>  
  <action global="true" name="getBizRecMaterialPermissions" procedure="getBizRecMaterialPermissionsProcedure"> 
    <label language="zh_CN">获得案卷材料权限</label>  
    <public name="bizRecId" required="true" type="String"/> 
  </action>  
  <action global="true" name="getNeedMaterialsAction" procedure="getNeedMaterialsProcedure"> 
    <permission name="range" type="List"/>  
    <private name="concept" type="String" value="B_Material"/>  
    <private name="select" type="String" value="B_Material.*"/>  
    <private name="from" type="String" value="B_Material B_Material"/>  
    <private name="aggregate" type="String"/>  
    <private name="dataModel" type="String" value="/base/core/material/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="condition" type="String"/>  
    <public name="distinct" type="Boolean" value="false"/>  
    <public name="idColumn" type="String" value="B_Material"/>  
    <public name="filter" type="String"/>  
    <public name="limit" type="Integer"/>  
    <public name="offset" type="Integer"/>  
    <public name="columns" type="String"/>  
    <public name="orderBy" type="String"/>  
    <public name="aggregateColumns" type="String"/>  
    <public name="variables" type="Map"/>  
    <label language="zh_CN">必要材料</label> 
  </action>  
  <action global="true" name="getNeedTreeMaterialsAction" procedure="getNeedTreeMaterialsProcedure"> 
    <permission name="range" type="List"/>  
    <private name="concept" type="String" value="B_Material"/>  
    <private name="select" type="String" value="B_Material.*"/>  
    <private name="from" type="String" value="B_Material B_Material"/>  
    <private name="aggregate" type="String"/>  
    <private name="dataModel" type="String" value="/base/core/material/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="condition" type="String"/>  
    <public name="distinct" type="Boolean" value="false"/>  
    <public name="idColumn" type="String" value="B_Material"/>  
    <public name="filter" type="String"/>  
    <public name="limit" type="Integer"/>  
    <public name="offset" type="Integer"/>  
    <public name="columns" type="String"/>  
    <public name="orderBy" type="String"/>  
    <public name="aggregateColumns" type="String"/>  
    <public name="variables" type="Map"/>  
    <label language="zh_CN">案卷即关联案卷必要材料</label> 
  </action>  
  <action global="true" name="upLoadNeedMaterialsAction" procedure="upLoadNeedMaterialsProcedure"> 
    <label language="zh_CN">上传附件到材料列表</label>  
    <public name="map" type="Map"/>  
    <public name="fBizRecId" type="String"/> 
  </action>  
  <action global="true" name="saveB_MaterialAllAction" procedure="saveB_MaterialAllProcedure"> 
    <permission name="insertRange" type="List"/>  
    <permission name="deleteRange" type="List"/>  
    <permission name="updateRange" type="List"/>  
    <private name="concept" type="String" value="B_Material"/>  
    <private name="dataModel" type="String" value="/base/core/material/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="readOnly" type="String"/>  
    <protected name="notNull" type="String"/>  
    <public name="table" required="true" type="Table"/> 
  </action>  
  <action global="true" name="getBizRecMaterialPermissionsAction" procedure="getBizRecMaterialPermissionsProcedure"> 
    <label language="zh_CN">材料权限</label>  
    <public name="fBizRecId" type="String"/>  
    <public name="fMaterialId" type="String"/>  
    <public name="fOperatorId" type="String"/> 
  </action>  
  <action global="true" name="queryB_AddMaterialAction" procedure="bizQueryProcedure"> 
    <permission name="range" type="List"/>  
    <private name="concept" type="String" value="B_Material"/>  
    <private name="select" type="String" value="B_Material.*"/>  
    <private name="from" type="String" value="B_Material B_Material"/>  
    <private name="aggregate" type="String"/>  
    <private name="dataModel" type="String" value="/base/core/material/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="condition" type="String" value="B_Material.fMaterialType = '补充材料'"/>  
    <public name="distinct" type="Boolean" value="false"/>  
    <public name="idColumn" type="String" value="B_Material"/>  
    <public name="filter" type="String"/>  
    <public name="limit" type="Integer"/>  
    <public name="offset" type="Integer"/>  
    <public name="columns" type="String"/>  
    <public name="orderBy" type="String"/>  
    <public name="aggregateColumns" type="String"/>  
    <public name="variables" type="Map"/>  
    <label language="zh_CN">补充材料</label> 
  </action>  
  <action global="true" name="getBizMaterialBrowseAction" procedure="getBizMaterialBrowseProcedure"> 
    <permission name="range" type="List"/>  
    <private name="concept" type="String" value="V_MaterialFile"/>  
    <private name="select" type="String" value="V_MaterialFile.*"/>  
    <private name="from" type="String" value="V_MaterialFile V_MaterialFile"/>  
    <private name="aggregate" type="String"/>  
    <private name="dataModel" type="String" value="/base/core/material/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="condition" type="String"/>  
    <public name="distinct" type="Boolean" value="false"/>  
    <public name="idColumn" type="String" value="V_MaterialFile"/>  
    <public name="filter" type="String"/>  
    <public name="limit" type="Integer"/>  
    <public name="offset" type="Integer"/>  
    <public name="columns" type="String"/>  
    <public name="orderBy" type="String"/>  
    <public name="aggregateColumns" type="String"/>  
    <public name="variables" type="Map"/>  
    <label language="zh_CN">材料浏览</label> 
  </action>  
  <action name="getBusinessMaterialsAction" procedure="getBusinessMaterialsProcedure"> 
    <permission name="range" type="List"/>  
    <private name="concept" type="String" value="B_Material"/>  
    <private name="select" type="String" value="B_Material.*"/>  
    <private name="from" type="String" value="B_Material B_Material"/>  
    <private name="aggregate" type="String"/>  
    <private name="dataModel" type="String" value="/base/core/material/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="condition" type="String"/>  
    <public name="distinct" type="Boolean" value="false"/>  
    <public name="idColumn" type="String" value="B_Material"/>  
    <public name="filter" type="String"/>  
    <public name="limit" type="Integer"/>  
    <public name="offset" type="Integer"/>  
    <public name="columns" type="String"/>  
    <public name="orderBy" type="String"/>  
    <public name="aggregateColumns" type="String"/>  
    <public name="variables" type="Map"/>  
    <label language="zh_CN">获得当前业务下的材料信息</label> 
  </action>  
  <action global="true" name="targMaterialsAction" procedure="targMaterialsProcedure"> 
    <label language="zh_CN">标记打印材料</label>  
    <public name="fBizRecId" type="String"/>  
    <public name="fMaterialIds" type="String"/> 
  </action>  
  <action global="true" name="queryV_MaterialGroupAction" procedure="queryV_MaterialGroupProcedure"> 
    <permission name="range" type="List"/>  
    <private name="concept" type="String" value="V_MaterialGroup"/>  
    <private name="select" type="String" value="V_MaterialNav.fMaterialNav as fMaterialNav,V_MaterialNav.fDispOrder as fDispOrder"/>  
    <private name="from" type="String" value="V_MaterialNav V_MaterialNav"/>  
    <private name="aggregate" type="String"/>  
    <private name="dataModel" type="String" value="/base/core/material/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="condition" type="String"/>  
    <public name="distinct" type="Boolean" value="false"/>  
    <public name="idColumn" type="String" value="fMaterialNav"/>  
    <public name="filter" type="String"/>  
    <public name="limit" type="Integer"/>  
    <public name="offset" type="Integer"/>  
    <public name="columns" type="String"/>  
    <public name="orderBy" type="String"/>  
    <public name="aggregateColumns" type="String"/>  
    <public name="variables" type="Map"/>  
    <label language="zh_CN">查询案卷的材料分组</label> 
  </action>  
  <action global="true" name="saveB_UserProcessAction" procedure="bizSaveProcedure"> 
    <permission name="insertRange" type="List"/>  
    <permission name="deleteRange" type="List"/>  
    <permission name="updateRange" type="List"/>  
    <private name="concept" type="String" value="B_UserProcess"/>  
    <private name="dataModel" type="String" value="/base/core/material/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="readOnly" type="String"/>  
    <protected name="notNull" type="String"/>  
    <public name="table" required="true" type="Table"/> 
  </action>  
  <action global="true" name="createB_UserProcessAction" procedure="bizCreateProcedure"> 
    <private name="concept" type="String" value="B_UserProcess"/>  
    <private name="fnModel" type="String"/>  
    <public name="table" required="true" type="Table"/>  
    <public name="defaultValues" type="Map"/> 
  </action>  
  <action global="true" name="queryB_UserBusinessMaterialAction" procedure="bizQueryProcedure"> 
    <permission name="range" type="List"/>  
    <private name="concept" type="String" value="B_UserBusinessMaterial"/>  
    <private name="select" type="String" value="B_UserBusinessMaterial.*"/>  
    <private name="from" type="String" value="B_UserBusinessMaterial B_UserBusinessMaterial"/>  
    <private name="aggregate" type="String"/>  
    <private name="dataModel" type="String" value="/base/core/material/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="condition" type="String"/>  
    <public name="distinct" type="Boolean" value="false"/>  
    <public name="idColumn" type="String" value="B_UserBusinessMaterial"/>  
    <public name="filter" type="String"/>  
    <public name="limit" type="Integer"/>  
    <public name="offset" type="Integer"/>  
    <public name="columns" type="String"/>  
    <public name="orderBy" type="String"/>  
    <public name="aggregateColumns" type="String"/>  
    <public name="variables" type="Map"/> 
  </action>  
  <action global="true" name="saveB_UserBusinessMaterialAction" procedure="bizSaveProcedure"> 
    <permission name="insertRange" type="List"/>  
    <permission name="deleteRange" type="List"/>  
    <permission name="updateRange" type="List"/>  
    <private name="concept" type="String" value="B_UserBusinessMaterial"/>  
    <private name="dataModel" type="String" value="/base/core/material/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="readOnly" type="String"/>  
    <protected name="notNull" type="String"/>  
    <public name="table" required="true" type="Table"/> 
  </action>  
  <action global="true" name="createB_UserBusinessMaterialAction" procedure="bizCreateProcedure"> 
    <private name="concept" type="String" value="B_UserBusinessMaterial"/>  
    <private name="fnModel" type="String"/>  
    <public name="table" required="true" type="Table"/>  
    <public name="defaultValues" type="Map"/> 
  </action>  
  <action global="true" name="userMaterialToAction" procedure="userMaterialToProcedure"> 
    <public name="fUserProcessId" type="String"/>  
    <public name="fBizRecId" type="String"/>  
    <label language="zh_CN">根据自定义附件生成到材料表</label> 
  </action>  
  <action global="true" name="isHasChoiceMaterialAction" procedure="isHasChoiceMaterialProcedure"> 
    <public name="fBizRecId" type="String"/>  
    <label language="zh_CN">判断是否存已经上传附件</label> 
  </action>  
  <action global="true" name="copyMaterialAction" procedure="copyMaterialProcedure"> 
    <public name="concept" type="String"/>  
    <public name="select" type="String"/>  
    <public name="from" type="String"/>  
    <public name="aggregate" type="String"/>  
    <public name="dataModel" type="String"/>  
    <public name="fnModel" type="String"/>  
    <public name="condition" type="String"/>  
    <public name="range" type="List"/>  
    <public name="distinct" type="Boolean"/>  
    <public name="idColumn" type="String"/>  
    <public name="filter" type="String"/>  
    <public name="limit" type="Integer"/>  
    <public name="offset" type="Integer"/>  
    <public name="columns" type="String"/>  
    <public name="orderBy" type="String"/>  
    <public name="aggregateColumns" type="String"/>  
    <public name="variables" type="Map"/> 
  </action>  
  <action name="queryB_MaterialAction" procedure="bizQueryProcedure"> 
    <permission name="range" type="List"/>  
    <private name="concept" type="String" value="B_Material"/>  
    <private name="select" type="String" value="B_Material.*"/>  
    <private name="from" type="String" value="B_Material B_Material"/>  
    <private name="aggregate" type="String"/>  
    <private name="dataModel" type="String" value="/base/core/material/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="condition" type="String"/>  
    <public name="distinct" type="Boolean" value="false"/>  
    <public name="idColumn" type="String" value="B_Material"/>  
    <public name="filter" type="String"/>  
    <public name="limit" type="Integer"/>  
    <public name="offset" type="Integer"/>  
    <public name="columns" type="String"/>  
    <public name="orderBy" type="String"/>  
    <public name="aggregateColumns" type="String"/>  
    <public name="variables" type="Map"/> 
  </action>  
  <action name="queryB_MaterialProcessAction" procedure="bizQueryProcedure"> 
    <permission name="range" type="List"/>  
    <private name="concept" type="String" value="B_MaterialProcess"/>  
    <private name="select" type="String" value="B_MaterialProcess.*"/>  
    <private name="from" type="String" value="B_MaterialProcess B_MaterialProcess"/>  
    <private name="aggregate" type="String"/>  
    <private name="dataModel" type="String" value="/base/core/material/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="condition" type="String"/>  
    <public name="distinct" type="Boolean" value="false"/>  
    <public name="idColumn" type="String" value="B_MaterialProcess"/>  
    <public name="filter" type="String"/>  
    <public name="limit" type="Integer"/>  
    <public name="offset" type="Integer"/>  
    <public name="columns" type="String"/>  
    <public name="orderBy" type="String"/>  
    <public name="aggregateColumns" type="String"/>  
    <public name="variables" type="Map"/> 
  </action>  
  <action name="saveB_MaterialProcessAction" procedure="bizSaveProcedure"> 
    <permission name="insertRange" type="List"/>  
    <permission name="deleteRange" type="List"/>  
    <permission name="updateRange" type="List"/>  
    <private name="concept" type="String" value="B_MaterialProcess"/>  
    <private name="dataModel" type="String" value="/base/core/material/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="readOnly" type="String"/>  
    <protected name="notNull" type="String"/>  
    <public name="table" required="true" type="Table"/> 
  </action>  
  <action name="createB_MaterialProcessAction" procedure="bizCreateProcedure"> 
    <private name="concept" type="String" value="B_MaterialProcess"/>  
    <private name="fnModel" type="String"/>  
    <public name="table" required="true" type="Table"/>  
    <public name="defaultValues" type="Map"/> 
  </action>  
  <action global="true" name="materialProcessAction" procedure="materialProcessProcedure"> 
    <public name="processes" type="List"/>  
    <public name="activitiesFNames" type="List"/> 
  </action>  
  <action global="true" name="queryB_UserProcessAction" procedure="bizQueryProcedure"> 
    <permission name="range" type="List"/>  
    <private name="concept" type="String" value="B_UserProcess"/>  
    <private name="select" type="String" value="B_UserProcess.*"/>  
    <private name="from" type="String" value="B_UserProcess B_UserProcess"/>  
    <private name="aggregate" type="String"/>  
    <private name="dataModel" type="String" value="/base/core/material/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="condition" type="String" value="B_UserProcess.fProcess = :currentProcess() AND ( B_UserProcess.fUserID = :currentPersonID() OR B_UserProcess.fUserID is null )"/>  
    <public name="distinct" type="Boolean" value="false"/>  
    <public name="idColumn" type="String" value="B_UserProcess"/>  
    <public name="filter" type="String"/>  
    <public name="limit" type="Integer"/>  
    <public name="offset" type="Integer"/>  
    <public name="columns" type="String"/>  
    <public name="orderBy" type="String" value="B_UserProcess.fDispOrder asc"/>  
    <public name="aggregateColumns" type="String"/>  
    <public name="variables" type="Map"/> 
  </action>  
  <action global="true" name="deleteMaterialAction" procedure="deleteMaterialProcedure"> 
    <public name="concept" type="String"/>  
    <public name="select" type="String"/>  
    <public name="from" type="String"/>  
    <public name="aggregate" type="String"/>  
    <public name="dataModel" type="String"/>  
    <public name="fnModel" type="String"/>  
    <public name="condition" type="String"/>  
    <public name="range" type="List"/>  
    <public name="distinct" type="Boolean"/>  
    <public name="idColumn" type="String"/>  
    <public name="filter" type="String"/>  
    <public name="limit" type="Integer"/>  
    <public name="offset" type="Integer"/>  
    <public name="columns" type="String"/>  
    <public name="orderBy" type="String"/>  
    <public name="aggregateColumns" type="String"/>  
    <public name="variables" type="Map"/> 
  </action>  
  <action name="queryB_BusinessProcessAction" procedure="bizQueryProcedure"> 
    <permission name="range" type="List"/>  
    <private name="concept" type="String" value="B_BusinessProcess"/>  
    <private name="select" type="String" value="B_BusinessProcess.*"/>  
    <private name="from" type="String" value="B_BusinessProcess B_BusinessProcess"/>  
    <private name="aggregate" type="String"/>  
    <private name="dataModel" type="String" value="/base/core/material/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="condition" type="String"/>  
    <public name="distinct" type="Boolean" value="false"/>  
    <public name="idColumn" type="String" value="B_BusinessProcess"/>  
    <public name="filter" type="String"/>  
    <public name="limit" type="Integer"/>  
    <public name="offset" type="Integer"/>  
    <public name="columns" type="String"/>  
    <public name="orderBy" type="String"/>  
    <public name="aggregateColumns" type="String"/>  
    <public name="variables" type="Map"/> 
  </action>  
  <action name="saveB_BusinessProcessAction" procedure="bizSaveProcedure"> 
    <permission name="insertRange" type="List"/>  
    <permission name="deleteRange" type="List"/>  
    <permission name="updateRange" type="List"/>  
    <private name="concept" type="String" value="B_BusinessProcess"/>  
    <private name="dataModel" type="String" value="/base/core/material/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="readOnly" type="String"/>  
    <protected name="notNull" type="String"/>  
    <public name="table" required="true" type="Table"/> 
  </action>  
  <action name="createB_BusinessProcessAction" procedure="bizCreateProcedure"> 
    <private name="concept" type="String" value="B_BusinessProcess"/>  
    <private name="fnModel" type="String"/>  
    <public name="table" required="true" type="Table"/>  
    <public name="defaultValues" type="Map"/> 
  </action>  
  <action global="true" name="choiceMaterialGroupAction" procedure="choiceMaterialGroupProcedure"> 
    <public name="fBizRecId" type="String"/>  
    <label language="zh_CN">已选择分组</label> 
  </action>  
  <action name="queryB_UserProcessTwoAction" procedure="bizQueryProcedure"> 
    <permission name="range" type="List"/>  
    <private name="concept" type="String" value="B_UserProcess"/>  
    <private name="select" type="String" value="B_UserProcess.*"/>  
    <private name="from" type="String" value="B_UserProcess B_UserProcess"/>  
    <private name="aggregate" type="String"/>  
    <private name="dataModel" type="String" value="/base/core/material/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="condition" type="String"/>  
    <public name="distinct" type="Boolean" value="false"/>  
    <public name="idColumn" type="String" value="B_UserProcess"/>  
    <public name="filter" type="String"/>  
    <public name="limit" type="Integer"/>  
    <public name="offset" type="Integer"/>  
    <public name="columns" type="String"/>  
    <public name="orderBy" type="String"/>  
    <public name="aggregateColumns" type="String"/>  
    <public name="variables" type="Map"/> 
  </action>  
  <action name="getFlowBZCL" global="true" procedure="getFlowBZCLProcedure"> 
    <label language="zh_CN">获得流程补正材料</label>  
    <public type="String" name="bizrecid"/> 
  </action> 
</model>
