<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <fn name="excelBook" category="技术支持(Excel)" code="ImpExpExFn.excelBook"
    code-model="/base/core/logic/code" type="Object"> 
    <label language="zh_CN">获得ExcelBook</label>  
    <parameter type="Object" name="input"/>  
    <parameter type="String" name="ext"/> 
  </fn>  
  <fn name="excelSheet" category="技术支持(Excel)" code="ImpExpExFn.excelSheet"
    code-model="/base/core/logic/code" type="Object"> 
    <label language="zh_CN">获得ExcelSheet</label>  
    <parameter type="Object" name="input"/>  
    <parameter type="String" name="ext"/> 
  </fn>  
  <fn name="sheetByIndex" category="技术支持(Excel)" code="ImpExpExFn.sheetByIndex"
    code-model="/base/core/logic/code" type="Object"> 
    <label language="zh_CN">根据索引获得Sheet</label>  
    <parameter type="Object" name="book"/>  
    <parameter type="Integer" name="index"/> 
  </fn>  
  <fn name="excelRow" category="技术支持(Excel)" code="ImpExpExFn.excelRow" code-model="/base/core/logic/code"
    type="Object"> 
    <label language="zh_CN">excelRow</label>  
    <parameter type="Object" name="sheet"/>  
    <parameter type="Integer" name="row"/> 
  </fn>  
  <fn name="cellValue" category="技术支持(Excel)" code="ImpExpExFn.cellValue"
    code-model="/base/core/logic/code" type="Object"> 
    <label language="zh_CN">cellValue</label>  
    <parameter type="Object" name="sheet"/>  
    <parameter type="Integer" name="row"/>  
    <parameter type="Integer" name="col"/> 
  </fn>  
  <fn name="exportTxt" category="技术支持(导出txt)" code="ImpExpExFn.exportTxt"
    code-model="/base/core/logic/code" type="Object"> 
    <label language="zh_CN">导出txt</label>  
    <parameter type="String" name="content"/>  
    <parameter type="String" name="charset"/> 
  </fn>  
  <fn name="importFht" category="技术支持(文件导入)" code="ImpExpExFn.importFht" type="String"
    code-model="/base/core/logic/code"> 
    <label language="zh_CN">导入房产分户图</label>  
    <parameter type="String" name="bizKey"/>  
    <parameter type="Object" name="stream"/> 
  </fn>  
  <fn name="simpleExcelExport" category="技术支持(文件导出)" code="ImpExpExFn.simpleExcelExport"
    code-model="/base/core/logic/code" type="Object"> 
    <label language="zh_CN">简易Excel导出</label>  
    <parameter type="String" name="templateKey"/>  
    <parameter type="Object..." name="params"/> 
  </fn>  
  <fn name="commonExcelExport" category="技术支持(文件导出)" code="ImpExpExFn.commonExcelExport"
    code-model="/base/core/logic/code" type="Object"> 
    <label language="zh_CN">通用Excel导出</label>  
    <parameter type="String" name="templateKey"/>  
    <parameter type="Map" name="sqlParam"/>  
    <parameter type="Map" name="extendParam"/>  
    <parameter type="String" name="outFileType"/> 
  </fn>  
  <fn name="customSqlExcelExport" category="技术支持(文件导出)" code="ImpExpExFn.customSqlExcelExport"
    type="Object" code-model="/base/core/logic/code"> 
    <label language="zh_CN">自定义sqlExcel导出</label>  
    <parameter type="String" name="templateKey"/>  
    <parameter type="String" name="customSql"/>  
    <parameter type="Map" name="sqlParam"/>  
    <parameter type="Map" name="extendParam"/>  
    <parameter type="String" name="outFileType"/> 
  </fn>  
  <fn name="impCadToTable" category="技术支持(文件导入)" code="ImpExpExFn.impCadToTable"
    code-model="/base/core/logic/code"> 
    <label language="zh_CN">DWG文件导入</label>  
    <parameter type="Object" name="_importFileStream"/>  
    <parameter type="String" name="dkTableId"/>  
    <parameter type="String" name="coordTableId"/> 
  </fn> 
</model>
