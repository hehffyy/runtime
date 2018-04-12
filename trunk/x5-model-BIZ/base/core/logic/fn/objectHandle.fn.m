<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <fn name="ifNull" category="字符处理函数" type="Object" code="ObjectHandleFn.ifNull"
    code-model="/base/core/logic/code"> 
    <label language="zh_CN">如果为空</label>  
    <parameter type="Object" name="value"/>  
    <parameter type="Object" name="value1"/> 
  </fn>  
  <fn name="decode" category="字符处理函数" type="Object" code="ObjectHandleFn.decode"
    code-model="/base/core/logic/code"> 
    <label language="zh_CN">decode</label>  
    <parameter type="Object..." name="params"/> 
  </fn>  
  <fn name="unEqual" category="字符处理函数" type="Boolean" code="ObjectHandleFn.unEqual"
    code-model="/base/core/logic/code">&gt;
    <label language="zh_CN">判断两个对象是否相等</label>  
    <parameter type="Object" name="obj1"/>  
    <parameter type="Object" name="obj2"/> 
  </fn>  
  <fn name="isNull" category="字符处理函数" code="ObjectHandleFn.isNull" type="Boolean"
    code-model="/base/core/logic/code"> 
    <label language="zh_CN">是否为空</label>  
    <parameter type="Object" name="obj"/> 
  </fn>  
  <fn name="requireExpre" category="业务规则专用函数" code-model="/base/core/logic/code"
    type="Boolean" code="ObjectHandleFn.requireExpre">
    <label language="zh_CN">业务规则必填表达式</label>  
    <parameter type="String" name="tableId"/>  
    <parameter type="String" name="fields"/> 
  </fn> 
  <fn name="requireRuleTip" category="业务规则专用函数" type="String" code="ObjectHandleFn.requireRuleTip"
    code-model="/base/core/logic/code"> 
    <label language="zh_CN">简易必填规则提示</label>  
    <parameter type="String" name="tableId"/>  
    <parameter type="String" name="fields"/>  
    <parameter type="String" name="fieldNames"/> 
  </fn>  
  <fn name="newLine" category="字符处理函数" type="String" code="ObjectHandleFn.newLine"
    code-model="/base/core/logic/code"> 
    <label language="zh_CN">换行</label> 
  </fn>  
  <fn name="quotedStr" category="字符处理函数" code="ObjectHandleFn.quotedStr" type="String"
    code-model="/base/core/logic/code"> 
    <label language="zh_CN">字符串两端增加单引号</label>  
    <parameter type="String" name="str"/> 
  </fn>  
  <fn name="dQuotedStr" category="字符处理函数" type="String" code="ObjectHandleFn.dQuotedStr"
    code-model="/base/core/logic/code"> 
    <label language="zh_CN">字符串两端增加双引号</label>  
    <parameter type="String" name="str"/> 
  </fn>  
  <fn name="attachNames" category="字符处理函数" code="ObjectHandleFn.attachNames"
    type="String" code-model="/base/core/logic/code"> 
    <label language="zh_CN">获取附件字段所有附件名称</label>  
    <parameter type="String" name="docIds"/> 
  </fn>
  <fn name="invokeStaticMethod" category="java反射" code="ObjectHandleFn.invokeStaticMethod"
    type="Object" code-model="/base/core/logic/code"> 
    <label language="zh_CN">调用java静态方法</label>  
     
  <parameter type="String" name="className"></parameter>
<parameter type="String" name="method"></parameter>
<parameter type="List" name="argumentTypes"></parameter>
<parameter type="List" name="argumentValues"></parameter>
</fn>  

</model>
