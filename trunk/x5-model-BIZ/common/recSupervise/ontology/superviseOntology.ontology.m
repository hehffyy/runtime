<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <concept name="B_SuperviseRec" default-value-expr="guid()" keys="fDbID"> 
    <has-relation relation="fDbID" default-value-expr="guid()"/>  
    <has-relation relation="version" default-value-expr="0"> 
      <label language="zh_CN">版本</label> 
    </has-relation>  
    <label language="zh_CN">督办案卷</label>  
    <has-relation relation="fBizRecID" data-type="String"/>  
    <has-relation relation="fCreatorID" data-type="String"/>  
    <has-relation relation="fCreator" data-type="String"/>  
    <has-relation relation="fDeptID" data-type="String"/>  
    <has-relation relation="fCreateDate" data-type="Date" default-value-expr="currentDate()"/>  
    <has-relation relation="fFinishDate" data-type="Date"/>  
    <has-relation relation="fDbrID" data-type="String"> 
      <label language="zh_CN">督办人ID</label> 
    </has-relation>  
    <has-relation relation="fDbr" data-type="String"/>  
    <has-relation relation="fBz" data-type="Text"/>  
    <has-relation relation="fStatus" default-value-expr="'督办中'" size="16"/>  
    <has-relation relation="fLevel" data-type="String"/> 
  </concept>  
  <relation name="fBizRecID" data-type="String"> 
    <label language="zh_CN">案卷编号</label> 
  </relation>  
  <relation name="fCreator" data-type="String"> 
    <label language="zh_CN">创建人</label> 
  </relation>  
  <relation name="fDeptID" data-type="String"> 
    <label language="zh_CN">部门ID</label> 
  </relation>  
  <relation name="fFinishDate" data-type="Date"> 
    <label language="zh_CN">完成日期</label> 
  </relation>  
  <relation name="fCreateDate" data-type="Date"> 
    <label language="zh_CN">创建日期</label> 
  </relation>  
  <relation name="fDbrID" data-type="String"> 
    <label language="zh_CN">督办人</label> 
  </relation>  
  <relation name="fDbr" data-type="String"> 
    <label language="zh_CN">督办人</label> 
  </relation>  
  <relation name="fStatus" data-type="String"> 
    <label language="zh_CN">督办状态</label> 
  </relation>  
  <relation name="fBz" data-type="Text"> 
    <label language="zh_CN">备注</label> 
  </relation>  
  <concept name="B_SuperviseStage" default-value-expr="guid()"> 
    <has-relation relation="version" default-value-expr="0"> 
      <label language="zh_CN">版本</label> 
    </has-relation>  
    <label language="zh_CN">督办阶段</label>  
    <has-relation relation="fDbID" data-type="String"/>  
    <has-relation relation="fStage" data-type="String" size="200"/>  
    <has-relation relation="fEndDate" data-type="Date"/>  
    <has-relation relation="fBz"/>  
    <has-relation relation="fStageStatus" data-type="String" default-value-expr="'待处理'"/> 
  </concept>  
  <relation name="fDbID" data-type="String"> 
    <label language="zh_CN">督办ID</label> 
  </relation>  
  <relation name="fStage" data-type="String"> 
    <label language="zh_CN">阶段名称</label> 
  </relation>  
  <relation name="fEndDate" data-type="Date"> 
    <label language="zh_CN">截至日期</label> 
  </relation>  
  <relation name="fCreatorID" data-type="String"> 
    <label language="zh_CN">创建人ID</label> 
  </relation>  
  <relation name="fStageStatus" data-type="String"> 
    <label language="zh_CN">阶段状态</label> 
  </relation>  
  <concept name="B_SuperviseCuiBan" default-value-expr="guid()"> 
    <has-relation relation="version" default-value-expr="0"> 
      <label language="zh_CN">版本</label> 
    </has-relation>  
    <label language="zh_CN">督办催办</label>  
    <has-relation relation="fDbID"/>  
    <has-relation relation="fDeptID" default-value-expr="currentDeptID()"/>  
    <has-relation relation="fCreatorID" default-value-expr="currentPersonID()"/>  
    <has-relation relation="fCreator" default-value-expr="currentPersonName()"/>  
    <has-relation relation="fCreateDate" default-value-expr="currentDateTime()"/>  
    <has-relation relation="fCuiBanInfo" data-type="String" size="1000"/>  
    <has-relation relation="fCuiBanType" data-type="String"/>  
    <has-relation relation="fBizRecID"/> 
  </concept>  
  <relation name="fCuiBanInfo" data-type="String"> 
    <label language="zh_CN">催办信息</label> 
  </relation>  
  <relation name="fCuiBanType" data-type="String"> 
    <label language="zh_CN">催办类型</label> 
  </relation>  
  <relation name="fLevel" data-type="String">
    <label language="zh_CN">优先级</label> 
  </relation>  
</model>
