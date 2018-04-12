<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <concept name="B_WorkDaysMang" default-value-expr="guid()">
    <has-relation relation="version" default-value-expr="0">
      <label language="zh_CN">版本</label> 
    </has-relation>  
    <label language="zh_CN">工作日管理</label>  
    <has-relation relation="fDate" data-type="Date" unique="true" size="32" required="false"/>  
    <has-relation relation="fWeek" data-type="Integer" size="16"/>
    <has-relation relation="fYear" data-type="Integer" size="32"/>  
    <has-relation relation="fMonth" data-type="Integer" size="32"/>  
    <has-relation relation="fDay" data-type="Integer" size="32">
      <label language="zh_CN">日</label> 
    </has-relation>  
    <has-relation relation="fDesc" data-type="String" size="200"/>  
    <has-relation relation="fKind" data-type="String" size="32"/>  
    <has-relation relation="fIsWorkDay" data-type="String" size="16"/> 
  
<has-relation relation="fKey"></has-relation>
</concept>  
  <relation name="fDate" data-type="Date">
    <label language="zh_CN">日期</label> 
  </relation>  
  <relation name="fYear" data-type="Integer">
    <label language="zh_CN">年</label> 
  </relation>  
  <relation name="fMonth" data-type="Integer">
    <label language="zh_CN">月</label> 
  </relation>  
  <relation name="fDay" data-type="Integer">
    <label language="zh_CN">日</label> 
  </relation>  
  <relation name="fDesc" data-type="String">
    <label language="zh_CN">描述</label> 
  </relation>  
  <relation name="fKind" data-type="String">
    <label language="zh_CN">转换方式</label> 
  </relation>  
  <relation name="fIsWorkDay" data-type="String">
    <label language="zh_CN">是否工作日</label> 
  </relation>  
  <relation name="fWeek" data-type="Integer">
    <label language="zh_CN">星期</label> 
  </relation> 

<relation name="fKey" data-type="Integer"><label language="zh_CN">关键值</label>
</relation>
</model>
