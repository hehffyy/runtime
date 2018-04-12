<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <concept name="V_DeptRecSum" keys="fBizName"> 
    <label language="zh_CN">部门案卷</label>  
    <has-relation relation="fBizName" data-type="String"/>  
    <has-relation relation="fCount" data-type="Integer"/>  
    <has-relation relation="fInZcCount" data-type="Integer"/>  
    <has-relation relation="fInYjCount" data-type="Integer"/>  
    <has-relation relation="fInYellowCount" data-type="Integer"/>  
    <has-relation relation="fInRedCount" data-type="Integer"/>  
    <has-relation relation="fApprizeCount" data-type="Integer"/>  
    <has-relation relation="fSpecialProcedureCount" data-type="Integer"/>  
    <has-relation relation="fQtGqCount" data-type="Integer"/>  
    <has-relation relation="fOutZcCount" data-type="Integer"/>  
    <has-relation relation="fOutYjCount" data-type="Integer"/>  
    <has-relation relation="fOutYellowCount" data-type="Integer"/>  
    <has-relation relation="fOutRedCount" data-type="Integer"/>  
    <has-relation relation="fFinsishCount" data-type="Integer"/>  
    <has-relation relation="fDeptID" data-type="String"/> 
  </concept>  
  <relation name="fBizName" data-type="String"> 
    <label language="zh_CN">业务名称</label> 
  </relation>  
  <relation name="fCount" data-type="Integer"> 
    <label language="zh_CN">总数</label> 
  </relation>  
  <relation name="fInZcCount" data-type="Integer"> 
    <label language="zh_CN">待办正常</label> 
  </relation>  
  <relation name="fInYjCount" data-type="Integer"> 
    <label language="zh_CN">待办预警</label> 
  </relation>  
  <relation name="fInYellowCount" data-type="Integer"> 
    <label language="zh_CN">待办黄牌</label> 
  </relation>  
  <relation name="fInRedCount" data-type="Integer"> 
    <label language="zh_CN">待办红牌</label> 
  </relation>  
  <relation name="fApprizeCount" data-type="Integer"> 
    <label language="zh_CN">补正告知</label> 
  </relation>  
  <relation name="fSpecialProcedureCount" data-type="Integer"> 
    <label language="zh_CN">特别程序</label> 
  </relation>  
  <relation name="fQtGqCount" data-type="Integer"> 
    <label language="zh_CN">其它挂起</label> 
  </relation>  
  <relation name="fOutZcCount" data-type="Integer"> 
    <label language="zh_CN">经办（未办结）正常</label> 
  </relation>  
  <relation name="fOutYjCount" data-type="Integer"> 
    <label language="zh_CN">经办（未办结）预警</label> 
  </relation>  
  <relation name="fOutYellowCount" data-type="Integer"> 
    <label language="zh_CN">经办（未办结）黄牌</label> 
  </relation>  
  <relation name="fOutRedCount" data-type="Integer"> 
    <label language="zh_CN">经办（未办结）红牌</label> 
  </relation>  
  <relation name="fFinsishCount" data-type="Integer"> 
    <label language="zh_CN">经办（已办结）</label> 
  </relation>  
  <relation name="fDeptID" data-type="String"> 
    <label language="zh_CN">部门ID</label> 
  </relation>  
  <relation name="fFuncName" data-type="String"> 
    <label language="zh_CN">功能名称</label> 
  </relation>  
  <relation name="fProcess" data-type="String"> 
    <label language="zh_CN">fProcess</label> 
  </relation>  
  <relation name="fFuncLongName" data-type="String"> 
    <label language="zh_CN">功能全名</label> 
  </relation>  
  <relation name="fProcessOrder" data-type="Integer"> 
    <label language="zh_CN">流程顺序</label> 
  </relation>  
  <concept name="B_ProcessOrder" default-value-expr="guid()"> 
    <has-relation relation="version" default-value-expr="0"> 
      <label language="zh_CN">版本</label> 
    </has-relation>  
    <label language="zh_CN">业务功能排序</label>  
    <has-relation relation="fDeptID" size="50"/>  
    <has-relation relation="fFuncName" size="50"/>  
    <has-relation relation="fProcess" size="200"/>  
    <has-relation relation="fFuncLongName" size="1000"/>  
    <has-relation relation="fProcessOrder" default-value-expr="9999"/> 
  </concept> 
</model>
