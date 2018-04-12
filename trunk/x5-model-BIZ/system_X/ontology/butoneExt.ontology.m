<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <!-- 人员表扩展-->  
  <relation name="sGlobalSequence" data-type="String" size="50"> 
    <label language="zh_CN">全局顺序号</label> 
  </relation>  
  <relation name="sLevelCode" data-type="String" size="5"> 
    <label language="zh_CN">职称级别编码</label> 
  </relation>  
  <!-- org表扩展-->  
  <relation name="sAreaId" data-type="String"> 
    <label language="zh_CN">区域ID</label> 
  </relation>  
  <relation name="sAreaName" data-type="String"> 
    <label language="zh_CN">区域名称</label> 
  </relation>  
  <concept name="Helper_ExecutorFID" default-value-expr="guid()"> 
    <label language="zh_CN">Oracle全局临时表(不要stuido内创建)</label>  
    <has-relation relation="FID" data-type="String" size="1000"/> 
  </concept>  
  
  <relation name="FID" data-type="String"> 
    <label language="zh_CN">FID</label> 
  </relation> 
</model>
