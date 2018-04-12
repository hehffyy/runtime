<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <action global="true" name="getfBizRecIdAction" procedure="getfBizRecIdProcedure"> 
    <label language="zh_CN">获取来文管理的案卷编号</label>  
    <public name="fParentID" type="String"/> 
  </action>  
  <action global="false" name="batchReadAction" procedure="batchReadProcedure"> 
    <label language="zh_CN">批量阅</label>  
    <public name="fHuoDong" type="String"/>  
    <public name="keepUnRead" type="Boolean"/>  
    <public name="type" type="String"/> 
  </action>  
  <action global="true" name="queryYcjAction" procedure="bizQueryProcedure"> 
    <permission name="range" type="List"/>  
    <private name="concept" type="String" value="B_GongZuoHuoDong"/>  
    <private name="select" type="String" value="B_GongZuoHuoDong.*"/>  
    <private name="from" type="String" value="B_GongZuoHuoDong B_GongZuoHuoDong"/>  
    <private name="aggregate" type="String"/>  
    <private name="dataModel" type="String" value="/common/gzzx/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="condition" type="String" value="B_GongZuoHuoDong.fPublish &lt;&gt;'是' AND B_GongZuoHuoDong.fItemType = '阅件' AND exists (select  1 from B_HuoDongRange B_HuoDongRange  where  B_GongZuoHuoDong.FGUID = B_HuoDongRange.fHuoDong AND B_HuoDongRange.fHandleKind = '阅办' AND exists (select  1 from SA_OPOrg SA_OPOrg  where  SA_OPOrg.sPersonID = :currentPersonID() AND instr(SA_OPOrg.sFID, B_HuoDongRange.fOrgUnitFID) &gt; 0 ) )"/>  
    <public name="distinct" type="Boolean" value="false"/>  
    <public name="idColumn" type="String" value="B_GongZuoHuoDong"/>  
    <public name="filter" type="String"/>  
    <public name="limit" type="Integer"/>  
    <public name="offset" type="Integer"/>  
    <public name="columns" type="String"/>  
    <public name="orderBy" type="String"/>  
    <public name="aggregateColumns" type="String"/>  
    <public name="variables" type="Map"/>  
    <label language="zh_CN">查询阅处件</label> 
  </action>  
  <action global="true" name="queryYzjAction" procedure="bizQueryProcedure"> 
    <permission name="range" type="List"/>  
    <private name="concept" type="String" value="B_GongZuoHuoDong"/>  
    <private name="select" type="String" value="B_GongZuoHuoDong.*"/>  
    <private name="from" type="String" value="B_GongZuoHuoDong B_GongZuoHuoDong"/>  
    <private name="aggregate" type="String"/>  
    <private name="dataModel" type="String" value="/common/gzzx/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="condition" type="String" value="B_GongZuoHuoDong.fPublish &lt;&gt;'是' AND B_GongZuoHuoDong.fItemType = '阅件' AND exists (select  1 from B_HuoDongRange B_HuoDongRange  where  B_GongZuoHuoDong.FGUID = B_HuoDongRange.fHuoDong AND B_HuoDongRange.fHandleKind = '阅知' AND exists (select  1 from SA_OPOrg SA_OPOrg  where  SA_OPOrg.sPersonID = :currentPersonID() AND instr(SA_OPOrg.sFID, B_HuoDongRange.fOrgUnitFID) &gt; 0 ) )"/>  
    <public name="distinct" type="Boolean" value="false"/>  
    <public name="idColumn" type="String" value="B_GongZuoHuoDong"/>  
    <public name="filter" type="String"/>  
    <public name="limit" type="Integer"/>  
    <public name="offset" type="Integer"/>  
    <public name="columns" type="String"/>  
    <public name="orderBy" type="String"/>  
    <public name="aggregateColumns" type="String"/>  
    <public name="variables" type="Map"/>  
    <label language="zh_CN">查询阅知件</label> 
  </action>  
  <action global="true" name="queryWsyqAction" procedure="bizQueryProcedure"> 
    <permission name="range" type="List"/>  
    <private name="concept" type="String" value="B_GongZuoHuoDong"/>  
    <private name="select" type="String" value="B_GongZuoHuoDong.*"/>  
    <private name="from" type="String" value="B_GongZuoHuoDong B_GongZuoHuoDong"/>  
    <private name="aggregate" type="String"/>  
    <private name="dataModel" type="String" value="/common/gzzx/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="condition" type="String" value="B_GongZuoHuoDong.fItemType = '网上舆情' AND exists (select  1 from B_HuoDongRange B_HuoDongRange  where  B_GongZuoHuoDong.FGUID = B_HuoDongRange.fHuoDong AND exists (select  1 from SA_OPOrg SA_OPOrg  where  SA_OPOrg.sPersonID = :currentPersonID() AND instr(SA_OPOrg.sFID, B_HuoDongRange.fOrgUnitFID) &gt; 0 ) )"/>  
    <public name="distinct" type="Boolean" value="false"/>  
    <public name="idColumn" type="String" value="B_GongZuoHuoDong"/>  
    <public name="filter" type="String"/>  
    <public name="limit" type="Integer"/>  
    <public name="offset" type="Integer"/>  
    <public name="columns" type="String"/>  
    <public name="orderBy" type="String"/>  
    <public name="aggregateColumns" type="String"/>  
    <public name="variables" type="Map"/>  
    <label language="zh_CN">查询网络舆情</label> 
  </action>  
  <action global="true" name="queryQtyAction" procedure="bizQueryProcedure"> 
    <permission name="range" type="List"/>  
    <private name="concept" type="String" value="B_GongZuoHuoDong"/>  
    <private name="select" type="String" value="B_GongZuoHuoDong.*"/>  
    <private name="from" type="String" value="B_GongZuoHuoDong B_GongZuoHuoDong"/>  
    <private name="aggregate" type="String"/>  
    <private name="dataModel" type="String" value="/common/gzzx/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="condition" type="String" value="B_GongZuoHuoDong.fItemType = '阅件' AND B_GongZuoHuoDong.fPublish = '是'"/>  
    <public name="distinct" type="Boolean" value="false"/>  
    <public name="idColumn" type="String" value="B_GongZuoHuoDong"/>  
    <public name="filter" type="String"/>  
    <public name="limit" type="Integer"/>  
    <public name="offset" type="Integer"/>  
    <public name="columns" type="String"/>  
    <public name="orderBy" type="String"/>  
    <public name="aggregateColumns" type="String"/>  
    <public name="variables" type="Map"/>  
    <label language="zh_CN">查询全厅阅</label> 
  </action> 
</model>
