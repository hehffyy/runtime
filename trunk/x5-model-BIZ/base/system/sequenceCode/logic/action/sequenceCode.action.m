<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <action name="previewSequenceCode" global="true" procedure="previewSequenceCodeProcedure">
    <label language="zh_CN">预览序列编码</label>  
    <public type="String" name="codeGuid" required="true"/>  
    <public type="String" name="concept" required="true"/>  
    <public type="Map" name="nodeValues"/> 
  </action>  
  <action name="makeSequenceCode" global="true" procedure="makeSequenceCodeProcedure">
    <label language="zh_CN">产生序列编码</label>  
    <public type="String" name="codeGuid" required="true"/>  
    <public type="String" name="concept" required="true"/>  
    <public type="Map" name="nodeValues"/>  
    <public type="String" name="relation" required="true"/>  
    <public type="String" name="idValue" required="true"/> 
  </action>  
  <action name="queryUnusedSequenceCodeValuesAction" global="true" procedure="queryUnusedSequenceCodeValuesProcedure">
    <label language="zh_CN">查询为使用的编码</label>  
    <public type="String" name="codeGuid" required="true"/>  
    <public type="String" name="concept" required="true"/>
    <public type="Map" name="nodeValues"/> 
  </action> 
<action name="lockUnusedSequenceCodeValueAction" global="true" procedure="lockUnusedSequenceCodeValueProcedure"><label language="zh_CN">锁定未使用的编码值</label>
<public type="String" name="codeGuid" required="true"></public>
<public type="String" name="concept" required="true"></public>
<public type="String" name="relation" required="true"></public><public type="String" name="groupValue" required="true"></public>
<public type="String" name="codeValue" required="true"></public>
<public type="String" name="idValue" required="true"></public>

</action>
<action name="releaseUsedSequenceCodeValueAction" global="true" procedure="releaseUsedSequenceCodeValueProcedure"><label language="zh_CN">释放使用的编码值</label>
<public type="String" name="concept" required="true"></public>
<public type="String" name="relation" required="true"></public>
<public type="String" name="idValue" required="true"></public>
</action>
</model>
