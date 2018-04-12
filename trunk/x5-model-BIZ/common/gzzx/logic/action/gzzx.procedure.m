<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model" xmlns:m="http://www.justep.com/model">  
  <procedure name="setGaunZhuProcedure" code-model="/common/gzzx/logic/code" code="Gzzx.setGaunZhu"> 
    <parameter name="guid" type="String"/>  
    <parameter name="level" type="String"/> 
  </procedure>  
  <procedure name="increaseHuoDongViewCountProcedure" code-model="/common/gzzx/logic/code" code="Gzzx.increaseHuoDongViewCount"> 
    <parameter name="huodong" type="String"/>  
    <parameter name="isView" type="Boolean"/> 
  </procedure>  
  <procedure name="getPermissionProcedure" code-model="/common/gzzx/logic/code" code="Gzzx.getPermission"/>  
  <procedure name="queryMyYuejian2Procedure" code-model="/common/gzzx/logic/code" code="Gzzx.queryMyYuejian2"> 
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
    <parameter name="state" type="String"/>  
    <parameter name="itemType" type="String"/> 
  </procedure>  
  <procedure name="getHuoDongHandleKindProcedure" code-model="/common/gzzx/logic/code" code="Gzzx.getHuoDongHandleKind"> 
    <parameter name="huodong" type="String"/> 
  </procedure>  
  <procedure name="queryLingDaoYuePiTaskProcedure" code-model="/common/gzzx/logic/code" code="Gzzx.queryLingDaoYuePiTask"> 
    <parameter name="zhuxian" type="String"/> 
  </procedure>  
  <procedure name="getPersonsOfDeptProcedure" code-model="/common/gzzx/logic/code" code="Gzzx.getPersonsOfDept"/>  
  <procedure name="queryNoHandleYueJianMessageProcedure" code-model="/common/gzzx/logic/code" code="Gzzx.queryNoHandleYueJianMessage"/>  
  <procedure name="queryHuoDongChuLiDeptListProcedure" code-model="/common/gzzx/logic/code" code="Gzzx.queryHuoDongChuLiDeptList"> 
    <parameter name="huodong" type="String"/>  
    <parameter name="includeCurrentDept" type="Boolean"/>  
    <parameter name="includeLD" type="Boolean"/> 
  </procedure>  
  <procedure name="statisticsYueJianCountProcedure" code-model="/common/gzzx/logic/code" code="Gzzx.statisticsYueJianCount"> 
    <parameter name="itemTypes" type="String"/> 
  </procedure>  
  <procedure name="statisticsNoHandleYueJianCountProcedure" code-model="/common/gzzx/logic/code" code="Gzzx.statisticsNoHandleYueJianCount">
    <parameter name="itemTypes" type="String"/>
  </procedure>
</model>
