<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <relation name="version" data-type="Integer" default-value-expr="0"> 
    <label language="zh_CN">版本</label> 
  </relation>  
  <relation name="FGUID" data-type="String" size="32" default-value-expr="guid()"> 
    <label language="zh_CN">主键</label> 
  </relation>  
  <relation name="fBizRecId" data-type="String" size="32" default-value-expr="guid()"> 
    <label language="zh_CN">案卷编号</label> 
  </relation> 
</model>
