<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <action name="externalFileImportAction" global="true" procedure="externalFileImportProcedure"> 
    <label language="zh_CN">外部文件导入</label>  
    <public type="Object" name="input"/>  
    <public type="String" name="targetProcess"/>  
    <public type="String" name="targetActivity"/>  
    <public type="String" name="url"/>  
    <public type="String" name="bizRecId"/>  
    <public type="Object" name="variants"/>  
    <public type="Object" name="filters"/>  
    <public type="String" name="returnData"/> 
  <public type="String" name="fileName"></public>
</action>  
  <action name="virtualFileImportAction" global="true" procedure="virtualFileImportProcedure"> 
    <label language="zh_CN">虚拟文件导入</label>  
    <public type="String" name="targetProcess"/>  
    <public type="String" name="targetActivity"/>  
    <public type="String" name="url"/>  
    <public type="String" name="bizRecId"/>  
    <public type="Map" name="variants"/>  
    <public type="Map" name="filters"/>  
    <public type="Boolean" name="returnData"/> 
  </action>  
  <action name="exportExternalFileAction" global="true" procedure="exportExternalFileProcedure"> 
    <public type="String" name="targetProcess"/>  
    <public type="String" name="targetActivity"/>  
    <public type="String" name="url"/>  
    <public type="String" name="bizRecId"/>  
    <public type="Map" name="variants"/>  
    <public type="Map" name="filters"/>  
    <label language="zh_CN">导出外部文件</label> 
  </action>  
  <action name="downloadExternalFileAction" global="true" procedure="downloadExternalFileProcedure"> 
    <label language="zh_CN">下载数据文件</label>  
    <public type="String" name="fileName"/> 
  </action> 
</model>
