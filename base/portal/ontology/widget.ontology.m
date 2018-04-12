<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <concept name="B_WIDGET_GL" default-value-expr="guid()">
    <has-relation relation="version" default-value-expr="0">
      <label language="zh_CN">版本</label> 
    </has-relation>  
    <label language="zh_CN">widget管理</label>  
    <has-relation relation="fMBMC" data-type="String" required="true"/>  
    <has-relation relation="fMBBM" data-type="String">
      <label language="zh_CN">别名</label> 
    </has-relation>
    <has-relation relation="fBJLX" data-type="String" default-value-expr="'widget'"/>  
    <has-relation relation="fMBNR" data-type="Text"/>  
    <has-relation relation="fMBBJXX" data-type="Text"/> 
  <has-relation relation="fWJ" data-type="B_WIDGET_GLRY" inverse-of="fMBID" single-valued="false" whole-part="composition"></has-relation>
</concept>  
  <relation name="fMBMC" data-type="String">
    <label language="zh_CN">模板名称</label> 
  </relation>  
  <relation name="fBJLX" data-type="String">
    <label language="zh_CN">模板类型</label> 
  </relation>  
  <relation name="fMBNR" data-type="String">
    <label language="zh_CN">模板内容</label> 
  </relation>  
  <relation name="fMBBJXX" data-type="Blob">
    <label language="zh_CN">模板布局信息</label> 
  </relation>  
  <relation name="fMBBM" data-type="String">
    <label language="zh_CN">模板别名</label> 
  </relation>  
  <concept name="B_WIDGET_GLRY" default-value-expr="guid()">
    <has-relation relation="version" default-value-expr="0">
      <label language="zh_CN">版本</label> 
    </has-relation>  
    <label language="zh_CN">widget关联人员</label>  
    <has-relation relation="fMBID" data-type="B_WIDGET_GL" required="true"/>  
    <has-relation relation="sID" data-type="String" required="true"/>  
    <has-relation relation="sName" data-type="String" size="200"/>  
    <has-relation relation="sFID" data-type="String" size="300"/>  
    <has-relation relation="sFName" data-type="String" size="300"/>  
    <has-relation relation="fLevel" data-type="String"/>  
    <has-relation relation="fQT" data-type="String"/> 
  </concept>  
  <relation name="fMBID" data-type="String" inverse-of="fWJ">
    <label language="zh_CN">模板ID</label> 
  </relation>  
  <relation name="sID" data-type="String">
    <label language="zh_CN">对象ID</label> 
  </relation>  
  <relation name="sName" data-type="String">
    <label language="zh_CN">名称</label> 
  </relation>  
  <relation name="sFID" data-type="String">
    <label language="zh_CN">对象标识</label> 
  </relation>  
  <relation name="sFName" data-type="String">
    <label language="zh_CN">路径</label> 
  </relation>  
  <relation name="fLevel" data-type="String">
    <label language="zh_CN">类型</label> 
  </relation>  
  <relation name="fQT" data-type="String">
    <label language="zh_CN">其他</label> 
  </relation> 
<relation name="fWJ" data-type="B_WIDGET_GLRY"><label language="zh_CN">外键</label>
</relation>
</model>
