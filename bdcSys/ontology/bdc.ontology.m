<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <concept name="BDC_H" default-value-expr="guid()" keys="HID"> 
    <label language="zh_CN">不动产户</label>  
    <has-relation relation="HID" data-type="String"/>  
    <has-relation relation="HH" data-type="String"/>  
    <has-relation relation="CH" data-type="String"/>  
    <has-relation relation="ZL" data-type="String"/>  
    <has-relation relation="DYZT" data-type="Integer"/>  
    <has-relation relation="YGZT" data-type="Integer"/>  
    <has-relation relation="DJZT" data-type="Integer"/>  
    <has-relation relation="YYZT" data-type="Integer"/>  
    <has-relation relation="FZZT" data-type="Integer"/>  
    <has-relation relation="CFZT" data-type="Integer"/>  
    <has-relation relation="RECID" data-type="String"/> 
  </concept>  
  <relation name="HH" data-type="String"> 
    <label language="zh_CN">户号</label> 
  </relation>  
  <relation name="CH" data-type="String"> 
    <label language="zh_CN">层号</label> 
  </relation>  
  <relation name="HID" data-type="String"> 
    <label language="zh_CN">户ID</label> 
  </relation>  
  <relation name="DYZT" data-type="String"> 
    <label language="zh_CN">抵押状态</label> 
  </relation>  
  <relation name="YGZT" data-type="Integer"> 
    <label language="zh_CN">预告状态</label> 
  </relation>  
  <relation name="DJZT" data-type="Integer"> 
    <label language="zh_CN">冻结状态</label> 
  </relation>  
  <relation name="YYZT" data-type="Integer"> 
    <label language="zh_CN">异议状态</label> 
  </relation>  
  <relation name="ZL" data-type="String"> 
    <label language="zh_CN">坐落</label> 
  </relation>  
  <relation name="FZZT" data-type="Integer"> 
    <label language="zh_CN">发证状态</label> 
  </relation>  
  <relation name="CFZT" data-type="Integer"> 
    <label language="zh_CN">查封状态</label> 
  </relation>  
  <relation name="RECID" data-type="String"> 
    <label language="zh_CN">办理案卷</label> 
  </relation> 
</model>
