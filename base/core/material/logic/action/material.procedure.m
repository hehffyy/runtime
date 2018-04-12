<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <procedure name="getBizRecMaterialListProcedure" code-model="/base/core/material/logic/code"
    code="Material.getBizRecMaterialList"> 
    <parameter name="bizRecId" type="String"/> 
  </procedure>  
  <procedure name="getBizRecMaterialPermissionsProcedure" code-model="/base/core/material/logic/code"
    code="Material.getBizRecMaterialPermissions"> 
    <parameter name="fBizRecId" type="String"/>  
    <parameter name="fMaterialId" type="String"/>  
    <parameter name="fOperatorId" type="String"/> 
  </procedure>  
  <procedure name="getNeedMaterialsProcedure" code-model="/base/core/material/logic/code"
    code="Material.getNeedMaterials"> 
    <parameter name="concept" type="String"/>  
    <parameter name="select" type="String"/>  
    <parameter name="from" type="String"/>  
    <parameter name="aggregate" type="String"/>  
    <parameter name="dataModel" type="String"/>  
    <parameter name="fnModel" type="String"/>  
    <parameter name="condition" type="String"/>  
    <parameter name="range" type="List"/>  
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
  <procedure name="getNeedTreeMaterialsProcedure" code-model="/base/core/material/logic/code"
    code="Material.getNeedTreeMaterials"> 
    <parameter name="concept" type="String"/>  
    <parameter name="select" type="String"/>  
    <parameter name="from" type="String"/>  
    <parameter name="aggregate" type="String"/>  
    <parameter name="dataModel" type="String"/>  
    <parameter name="fnModel" type="String"/>  
    <parameter name="condition" type="String"/>  
    <parameter name="range" type="List"/>  
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
  <procedure name="upLoadNeedMaterialsProcedure" code-model="/base/core/material/logic/code"
    code="Material.upLoadNeedMaterials"> 
    <parameter name="map" type="Map"/>  
    <parameter name="fBizRecId" type="String"/> 
  </procedure>  
  <procedure name="saveB_MaterialAllProcedure" code-model="/base/core/material/logic/code"
    code="Material.saveB_MaterialAll"> 
    <parameter name="concept" type="String"/>  
    <parameter name="dataModel" type="String"/>  
    <parameter name="fnModel" type="String"/>  
    <parameter name="insertRange" type="List"/>  
    <parameter name="deleteRange" type="List"/>  
    <parameter name="updateRange" type="List"/>  
    <parameter name="readOnly" type="String"/>  
    <parameter name="notNull" type="String"/>  
    <parameter name="table" type="Table"/> 
  </procedure>  
  <procedure name="getBizMaterialBrowseProcedure" code-model="/base/core/material/logic/code"
    code="Material.getBizMaterialBrowse"> 
    <parameter name="concept" type="String"/>  
    <parameter name="select" type="String"/>  
    <parameter name="from" type="String"/>  
    <parameter name="aggregate" type="String"/>  
    <parameter name="dataModel" type="String"/>  
    <parameter name="fnModel" type="String"/>  
    <parameter name="condition" type="String"/>  
    <parameter name="range" type="List"/>  
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
  <procedure name="getBusinessMaterialsProcedure" code-model="/base/core/material/logic/code"
    code="Material.getBusinessMaterials"> 
    <parameter name="concept" type="String"/>  
    <parameter name="select" type="String"/>  
    <parameter name="from" type="String"/>  
    <parameter name="aggregate" type="String"/>  
    <parameter name="dataModel" type="String"/>  
    <parameter name="fnModel" type="String"/>  
    <parameter name="condition" type="String"/>  
    <parameter name="range" type="List"/>  
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
  <procedure name="targMaterialsProcedure" code-model="/base/core/material/logic/code"
    code="Material.targMaterials"> 
    <parameter name="fMaterialIds" type="String"/>  
    <parameter name="fBizRecId" type="String"/> 
  </procedure>  
  <procedure name="queryV_MaterialGroupProcedure" code-model="/base/core/material/logic/code"
    code="Material.queryV_MaterialGroup"> 
    <parameter name="concept" type="String"/>  
    <parameter name="select" type="String"/>  
    <parameter name="from" type="String"/>  
    <parameter name="aggregate" type="String"/>  
    <parameter name="dataModel" type="String"/>  
    <parameter name="fnModel" type="String"/>  
    <parameter name="condition" type="String"/>  
    <parameter name="range" type="List"/>  
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
  <procedure name="userMaterialToProcedure" code-model="/base/core/material/logic/code"
    code="Material.userMaterialTo"> 
    <parameter name="fUserProcessId" type="String"/>  
    <parameter name="fBizRecId" type="String"/> 
  </procedure>  
  <procedure name="isHasChoiceMaterialProcedure" code-model="/base/core/material/logic/code"
    code="Material.isHasChoiceMaterial"> 
    <parameter name="fBizRecId" type="String"/> 
  </procedure>  
  <procedure name="copyMaterialProcedure" code-model="/base/core/material/logic/code"
    code="Material.copyMaterial"> 
    <parameter name="concept" type="String"/>  
    <parameter name="select" type="String"/>  
    <parameter name="from" type="String"/>  
    <parameter name="aggregate" type="String"/>  
    <parameter name="dataModel" type="String"/>  
    <parameter name="fnModel" type="String"/>  
    <parameter name="condition" type="String"/>  
    <parameter name="range" type="List"/>  
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
  <procedure name="materialProcessProcedure" code-model="/base/core/material/logic/code"
    code="Material.materialProcess"> 
    <parameter name="processes" type="List"/>  
    <parameter name="activitiesFNames" type="List"/> 
  </procedure>  
  <procedure name="deleteMaterialProcedure" code-model="/base/core/material/logic/code"
    code="Material.deleteMaterial"> 
    <parameter name="concept" type="String"/>  
    <parameter name="select" type="String"/>  
    <parameter name="from" type="String"/>  
    <parameter name="aggregate" type="String"/>  
    <parameter name="dataModel" type="String"/>  
    <parameter name="fnModel" type="String"/>  
    <parameter name="condition" type="String"/>  
    <parameter name="range" type="List"/>  
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
  <procedure name="choiceMaterialGroupProcedure" code-model="/base/core/material/logic/code"
    code="Material.choiceMaterialGroup"> 
    <parameter name="fBizRecId" type="String"/> 
  </procedure>  
  <procedure name="getFlowBZCLProcedure" code-model="/base/core/material/logic/code"
    code="Material.getFlowBZCL"> 
    <parameter name="bizrecid" type="String"/> 
  </procedure> 
</model>
