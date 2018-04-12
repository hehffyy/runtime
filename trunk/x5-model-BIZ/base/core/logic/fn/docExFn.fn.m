<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <fn name="uploadDoc" category="文档扩展函数" code="DocExFn.uploadDoc" code-model="/base/core/logic/code"
    type="Boolean"> 
    <label language="zh_CN">简易文档上传</label>  
    <parameter type="String" name="bizKey"/>  
    <parameter type="String" name="version"/>  
    <parameter type="String" name="kind"/>  
    <parameter type="Object" name="stream"/> 
  </fn>  
  <fn name="genX5Attachs" category="文档扩展函数" code="DocExFn.genX5Attachs" code-model="/base/core/logic/code"
    type="String"> 
    <label language="zh_CN">X5上传文档</label>  
    <parameter type="String" name="baseUrl"/>  
    <parameter type="String" name="subPath"/>  
    <parameter type="List" name="files"/> 
  </fn>  
  <fn name="genX3Attachs" category="文档扩展函数" type="List" code-model="/base/core/logic/code"
    code="DocExFn.genX3Attachs"> 
    <label language="zh_CN">产生x3附件</label>  
    <parameter type="String" name="x3Url"/>  
    <parameter type="String" name="bizRecId"/>  
    <parameter type="String" name="docIds"/> 
  </fn>  
  <fn name="getSimpleDocStream" category="文档扩展函数" code="DocExFn.getSimpleDocStream"
    type="Object" code-model="/base/core/logic/code"> 
    <label language="zh_CN">获得简易文档流</label>  
    <parameter type="String" name="url"/> 
  </fn>  
  <fn name="streamToWord" category="文档扩展函数" code="DocExFn.streamToWord" code-model="/base/core/logic/code"
    type="String"> 
    <label language="zh_CN">流转Word</label>  
    <parameter type="Object" name="stream"/> 
  </fn> 
<fn name="hasDraftInfo" category="技术支持扩展（word）" code-model="/base/core/logic/code" code="DocExFn.hasDraftInfo" type="Boolean"><label language="zh_CN">是否有拟稿信息</label>
<parameter type="String" name="bizKey"></parameter>
<parameter type="String" name="kind"></parameter>
<parameter type="String" name="parentVersion"></parameter>
</fn>
</model>
