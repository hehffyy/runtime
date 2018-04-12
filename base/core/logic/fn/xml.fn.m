<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <fn name="createDocument" category="xml操作" code-model="/base/core/logic/code"
    code="XMLFn.createDocument" type="Object"> 
    <label language="zh_CN">创建xml文档</label>  
    <parameter type="String" name="text"/> 
  </fn>  
  <fn name="addNode" category="xml操作" code-model="/base/core/logic/code" code="XMLFn.addNode"
    type="Object"> 
    <label language="zh_CN">添加节点</label>  
    <parameter type="Object" name="doc"/>  
    <parameter type="String" name="name"/>  
    <parameter type="Object" name="value"/> 
  </fn>  
  <fn name="setNodeText" category="xml操作" code-model="/base/core/logic/code"
    code="XMLFn.setNodeText" type="Object"> 
    <label language="zh_CN">设置节点值</label>  
    <parameter type="Object" name="node"/>  
    <parameter type="String" name="text"/> 
  </fn>  
  <fn name="addAttribute" category="xml操作" code-model="/base/core/logic/code"
    code="XMLFn.addAttribute" type="Object"> 
    <label language="zh_CN">为节点添加属性</label>  
    <parameter type="Object" name="node"/>  
    <parameter type="String" name="name"/>  
    <parameter type="Object" name="value"/> 
  </fn>  
  <fn name="setNodeCDATA" category="xml操作" code-model="/base/core/logic/code"
    code="XMLFn.setNodeCDATA" type="Object"> 
    <label language="zh_CN">为节点添加属性</label>  
    <parameter type="Object" name="ele"/>  
    <parameter type="Object" name="value"/> 
  </fn>  
  <fn name="asXML" category="xml操作" code-model="/base/core/logic/code" code="XMLFn.asXML"
    type="String"> 
    <label language="zh_CN">转为xml格式</label>  
    <parameter type="Object" name="node"/> 
  </fn>  
  <fn name="getRootElement" category="xml操作" code-model="/base/core/logic/code"
    code="XMLFn.getRootElement" type="Object"> 
    <label language="zh_CN">获取根节点</label>  
    <parameter type="Object" name="doc"/> 
  </fn>  
  <fn name="getElements" category="xml操作" code-model="/base/core/logic/code"
    code="XMLFn.getElements" type="Object"> 
    <label language="zh_CN">获取子节点</label>  
    <parameter type="Object" name="ele"/>  
    <parameter type="String" name="name"/> 
  </fn>  
  <fn name="getElementListSize" category="xml操作" code-model="/base/core/logic/code"
    code="XMLFn.getElementListSize" type="Integer"> 
    <label language="zh_CN">获取子节点</label>  
    <parameter type="Object" name="elementList"/> 
  </fn>  
  <fn name="getElementFromElementList" category="xml操作" code-model="/base/core/logic/code"
    code="XMLFn.getElementFromElementList" type="Object"> 
    <label language="zh_CN">从节点list中获取节点</label>  
    <parameter type="Object" name="elementList"/>  
    <parameter type="Integer" name="i"/> 
  </fn>  
  <fn name="getElement" category="xml操作" code-model="/base/core/logic/code"
    code="XMLFn.getElement" type="Object"> 
    <label language="zh_CN">获取子节点</label>  
    <parameter type="Object" name="ele"/>  
    <parameter type="String" name="name"/> 
  </fn>  
  <fn name="getChildElementText" category="xml操作" code-model="/base/core/logic/code"
    code="XMLFn.getChildElementText" type="String"> 
    <label language="zh_CN">获取子节点值</label>  
    <parameter type="Object" name="ele"/>  
    <parameter type="String" name="name"/> 
  </fn>  
  <fn name="getElementText" category="xml操作" code-model="/base/core/logic/code"
    code="XMLFn.getElementText" type="String"> 
    <label language="zh_CN">获取节点值</label>  
    <parameter type="Object" name="ele"/> 
  </fn>  
  <fn name="getAttributeValue" category="xml操作" code-model="/base/core/logic/code"
    code="XMLFn.getAttributeValue" type="String"> 
    <label language="zh_CN">获取节点属性值</label>  
    <parameter type="Object" name="ele"/>  
    <parameter type="String" name="name"/> 
  </fn> 
</model>
