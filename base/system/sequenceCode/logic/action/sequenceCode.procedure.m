<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model" xmlns:m="http://www.justep.com/model">  
  <procedure name="previewSequenceCodeProcedure" code-model="/base/system/sequenceCode/logic/code" code="SequenceCode.previewSequenceCode"> 
    <parameter name="codeGuid" type="String"/>  
    <parameter name="concept" type="String"/>  
    <parameter name="nodeValues" type="Map"/> 
  </procedure>  
  <procedure name="makeSequenceCodeProcedure" code-model="/base/system/sequenceCode/logic/code" code="SequenceCode.makeSequenceCode"> 
    <parameter name="codeGuid" type="String"/>  
    <parameter name="concept" type="String"/>  
    <parameter name="nodeValues" type="Map"/>  
    <parameter name="relation" type="String"/>  
    <parameter name="idValue" type="String"/> 
  </procedure>  
  <!-- 保存前生成编码字段值-->  
  <procedure name="makeSequenceCodeValueBeforeSaveActionProcedure" code-model="/base/system/sequenceCode/logic/code" code="SequenceCode.makeSequenceCodeValueBeforeSaveAction"/>  
  <!-- 保存前生成编码字段值-->  
  <procedure name="makeSequenceCodeValueAfterCreateActionProcedure" code-model="/base/system/sequenceCode/logic/code" code="SequenceCode.makeSequenceCodeValueAfterCreateAction"/>  
  <procedure name="queryUnusedSequenceCodeValuesProcedure" code-model="/base/system/sequenceCode/logic/code" code="SequenceCode.queryUnusedSequenceCodeValues"> 
    <parameter name="codeGuid" type="String"/>  
    <parameter name="concept" type="String"/>  
    <parameter name="nodeValues" type="Map"/> 
  </procedure>  
  <procedure name="lockUnusedSequenceCodeValueProcedure" code-model="/base/system/sequenceCode/logic/code" code="SequenceCode.lockUnusedSequenceCodeValue"> 
    <parameter name="codeGuid" type="String"/>  
    <parameter name="idValue" type="String"/>  
    <parameter name="concept" type="String"/>  
    <parameter name="groupValue" type="String"/>  
    <parameter name="codeValue" type="String"/>  
    <parameter name="relation" type="String"/> 
  </procedure>  
  <procedure name="releaseUsedSequenceCodeValueProcedure" code-model="/base/system/sequenceCode/logic/code" code="SequenceCode.releaseUsedSequenceCodeValue"> 
    <parameter name="concept" type="String"/>
    <parameter name="relation" type="String"/>
    <parameter name="idValue" type="String"/>
  </procedure> 
</model>
