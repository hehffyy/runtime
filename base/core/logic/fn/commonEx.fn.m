<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <fn name="executeSql" category="废弃停用" code="CommonExFn.executeSql" type="Integer"
    code-model="/base/core/logic/code"> 
    <label language="zh_CN">执行Sql(废弃停用)</label>  
    <parameter type="String" name="sql"/>  
    <parameter type="List" name="params"/> 
  </fn>  
  <fn name="sqlQuery" category="SQL执行函数" code="CommonExFn.sqlQuery" code-model="/base/core/logic/code"
    type="List"> 
    <label language="zh_CN">Sql查询</label>  
    <parameter type="String" name="sql"/>  
    <parameter type="List" name="params"/> 
  </fn>  
  <fn name="listCount" category="SQL执行函数" code="CommonExFn.listCount" type="Integer"
    code-model="/base/core/logic/code"> 
    <label language="zh_CN">列表记录数</label>  
    <parameter type="List" name="queryResult"/> 
  </fn>  
  <fn name="tableNumData" category="数据取值和计算" code="CommonExFn.tableNumData"
    code-model="/base/core/logic/code" type="Object"> 
    <label language="zh_CN">获得数字值</label>  
    <parameter type="String" name="objectId"/>  
    <parameter type="String" name="propName"/> 
  </fn>  
  <fn name="tableSumData" category="数据取值和计算" type="Object" code="CommonExFn.tableSumData"
    code-model="/base/core/logic/code"> 
    <label language="zh_CN">数字sum</label>  
    <parameter name="objs" type="Object..."/> 
  </fn>  
  <fn name="getQueryVal" category="数据取值和计算" code="CommonExFn.getQueryVal"
    type="Object" code-model="/base/core/logic/code"> 
    <label language="zh_CN">获得查询值</label>  
    <parameter type="List" name="queryResult"/>  
    <parameter type="Integer" name="rowIndex"/>  
    <parameter type="String" name="filedName"/> 
  </fn>  
  <fn name="sqlFldValue" category="数据取值和计算" type="Boolean" code="CommonExFn.sqlFldValue"
    code-model="/base/core/logic/code"> 
    <label language="zh_CN">Sql第一个字段值</label>  
    <parameter type="String" name="sql"/>  
    <parameter type="List" name="params"/> 
  </fn>  
  <fn name="recFldValue" category="数据取值和计算" type="Boolean" code="CommonExFn.recFldValue"
    code-model="/base/core/logic/code"> 
    <label language="zh_CN">含有案卷编号字段表取值</label>  
    <parameter type="String" name="tableId"/>  
    <parameter type="String" name="field"/> 
  </fn>  
  <fn name="isFldsNull" category="字符处理函数" type="Boolean" code="CommonExFn.isFldsNull"
    code-model="/base/core/logic/code"> 
    <label language="zh_CN">字段集是否为空</label>  
    <parameter type="String" name="tableId"/>  
    <parameter type="String" name="fields"/> 
  </fn>  
  <fn name="executeAction" category="技术支持扩展(文件导入)" code="CommonExFn.executeAction"
    type="Boolean" code-model="/base/core/logic/code"> 
    <label language="zh_CN">执行Action</label>  
    <parameter type="String" name="action"/>  
    <parameter type="Map" name="params"/> 
  </fn>  
  <fn name="dataVersionMng" category="技术支持扩展（数据）" code="CommonExFn.dataVersionMng"
    code-model="/base/core/logic/code" type="Boolean"> 
    <label language="zh_CN">数据版本管理</label>  
    <parameter type="List" name="srcTables"/>  
    <parameter type="String" name="srcBizRecId"/>  
    <parameter type="List" name="trgTables"/>  
    <parameter type="String" name="trgBizRecId"/> 
  </fn>  
  <fn name="getQueryValFields" category="数据取值和计算" code="CommonExFn.getQueryValFields"
    type="Object" code-model="/base/core/logic/code"> 
    <label language="zh_CN">获取结果集的字段拼串</label>  
    <parameter type="List" name="queryResult"/>  
    <parameter type="String" name="filedName"/> 
  </fn>  
  <fn name="startFlowEx" category="技术支持扩展(流程)" type="String" code="CommonExFn.startFlowEx"
    code-model="/base/core/logic/code"> 
    <label language="zh_CN">启动流程扩展</label>  
    <parameter type="String" name="mainRecId"/>  
    <parameter type="String" name="bizRecId"/>  
    <parameter type="String" name="kind"/>  
    <parameter type="String" name="process"/>  
    <parameter type="String" name="activity"/>  
    <parameter type="List" name="executors"/> 
  </fn>  
  <fn name="isUnique" category="技术支持扩展(数据)" code-model="/base/core/logic/code"
    code="CommonExFn.isUnique" type="Boolean"> 
    <label language="zh_CN">唯一性验证</label>  
    <parameter type="String" name="tableName"/>  
    <parameter type="String" name="colName"/>  
    <parameter type="String" name="colValue"/>  
    <parameter type="String" name="filter"/> 
  </fn>  
  <fn name="butoneSuspendProcess" category="技术支持扩展(流程)" code-model="/base/core/logic/code"
    code="CommonExFn.butoneSuspendProcess" type="Boolean"> 
    <label language="zh_CN">挂起指定taskID案卷(只支持普通挂起)</label>  
    <parameter type="String" name="taskID"/>  
    <parameter type="String" name="fgqyy"/> 
  </fn>  
  <fn name="butoneResumeProcess" category="技术支持扩展(流程)" code-model="/base/core/logic/code"
    code="CommonExFn.butoneResumeProcess" type="Boolean"> 
    <label language="zh_CN">激活指定taskID案卷(只支持普通挂起)</label>  
    <parameter type="String" name="taskID"/> 
  </fn>  
  <fn name="getNextTaskExecutorsInfo" category="技术支持扩展(流程)" code-model="/base/core/logic/code"
    code="CommonExFn.getNextTaskExecutorsInfo" type="Object"> 
    <label language="zh_CN">获取流程执行后的执行者的信息</label> 
  </fn>  
  <fn name="getNextTaskExecutorIDs" category="技术支持扩展(流程)" code-model="/base/core/logic/code"
    code="CommonExFn.getNextTaskExecutorIDs" type="Object"> 
    <label language="zh_CN">获取流程执行后的执行者的personIDs</label> 
  </fn>  
  <fn name="insertTask" category="技术支持扩展(流程)" code="CommonExFn.insertTask"
    code-model="/base/core/logic/code" type="Object"> 
    <label language="zh_CN">添加任务</label>  
    <parameter type="String" name="fBizRecId"/>  
    <parameter type="String" name="prveActID"/>  
    <parameter type="String" name="createActID"/>  
    <parameter type="String" name="executeActID"/>  
    <parameter type="String" name="name"/>  
    <parameter type="List" name="executorFIDs"/> 
  </fn>  
  <fn name="activeTask" category="技术支持扩展(流程)" code="CommonExFn.activeTask"
    code-model="/base/core/logic/code" type="Object"> 
    <label language="zh_CN">激活指定环节</label>  
    <parameter type="String" name="fBizRecId"/>  
    <parameter type="String" name="actID"/>  
    <parameter type="Boolean" name="requireBack"/> 
  </fn>  
  <fn name="containStr" category="字符函数扩展" code="CommonExFn.containStr" code-model="/base/core/logic/code"
    type="Boolean"> 
    <label language="zh_CN">字符包含</label>  
    <parameter type="String" name="str"/>  
    <parameter type="String" name="cldStr"/> 
  </fn>  
  <fn name="sumDecimalFromMap" category="数据计算" code="CommonExFn.sumDecimalFromMap"
    type="Decimal" code-model="/base/core/logic/code"> 
    <label language="zh_CN">Map合计</label>  
    <parameter type="Map" name="map"/>  
    <parameter type="List" name="attrs"/> 
  </fn>  
  <fn name="sendSMSMessage" category="技术支持(消息)" code-model="/base/core/logic/code"
    code="CommonExFn.sendSMSMessage" type="Object"> 
    <label language="zh_CN">发送短信</label>  
    <parameter type="List" name="personIDOrFIDs"/>  
    <parameter type="String" name="fMessageContent"/>  
    <parameter type="String" name="typeName"/> 
  </fn>  
  <fn name="copyDatatoOutDataBase" category="建设用地扩展" code-model="/base/core/logic/code"
    code="CommonExFn.copyDatatoOutDataBase" type="String"> 
    <label language="zh_CN">跨库表拷贝</label>  
    <parameter type="Table" name="table"/>  
    <parameter type="String" name="sender"/>  
    <parameter type="String" name="Driver"/>  
    <parameter type="String" name="jdbcUrl"/>  
    <parameter type="String" name="user"/>  
    <parameter type="String" name="pwd"/>  
    <parameter type="Boolean" name="noGuid"/> 
  </fn>  
  <fn name="getFieldValueFromOutDataBase" category="建设用地扩展" code-model="/base/core/logic/code"
    code="CommonExFn.getFieldValueFromOutDataBase" type="String"> 
    <label language="zh_CN">跨库表拷贝</label>  
    <parameter type="String" name="lsql"/>  
    <parameter type="String" name="fields"/> 
    <parameter type="String" name="Driver"/>  
    <parameter type="String" name="jdbcUrl"/>  
    <parameter type="String" name="user"/>  
    <parameter type="String" name="pwd"/>  
  </fn> 
  <fn name="insertTaskExecutor" category="技术支持扩展(流程)" code-model="/base/core/logic/code"
    code="CommonExFn.insertTaskExecutor" type="String">
    <label language="zh_CN">添加执行者</label>  
    <parameter type="String" name="parentTaskID"/>  
    <parameter type="String" name="sprocess"/>  
    <parameter type="String" name="sActivity"/>  
    <parameter type="String" name="actName"/>
    <parameter type="String" name="cUrl"/>  
    <parameter type="String" name="eUrl"/>  
    <parameter type="String" name="fbizrecid"/>  
    <parameter type="String" name="executors"/>  
    <parameter type="String" name="flowID"/> 
  </fn> 
<fn name="activeTaskExecutor" category="技术支持扩展(流程)" code-model="/base/core/logic/code" code="CommonExFn.activeTaskExecutor" type="String"><label language="zh_CN">激活任务执行者</label>
<parameter type="String" name="taskid"></parameter>
</fn>
 <fn name="isDataRepeat" category="数据检查校验" code-model="/base/core/logic/code" code="CommonExFn.isDataRepeat" type="Object"><label language="zh_CN">isDataRepeat</label>
	<parameter type="Object" name="tab"></parameter>
	<parameter type="String" name="fields"></parameter>
 </fn>
 <fn name="codeNameIdentical" category="数据检查校验" code-model="/base/core/logic/code" code="CommonExFn.codeNameIdentical" type="Object"><label language="zh_CN">codeNameIdentical</label>
	<parameter type="Object" name="dictTab"></parameter>
	<parameter type="Object" name="dataTab"></parameter>
	<parameter type="String" name="dictType"></parameter>
	<parameter type="String" name="codeColumn"></parameter>
	<parameter type="String" name="nameColumn"></parameter>
	<parameter type="String" name="keyColumn"></parameter>
 </fn>
 <fn name="requireFileds" category="数据检查校验" code-model="/base/core/logic/code" code="CommonExFn.requireFileds" type="Object"><label language="zh_CN">requireFileds</label>
	<parameter type="Object" name="table"></parameter>
	<parameter type="String" name="fields"></parameter>
	<parameter type="String" name="fieldsName"></parameter>
 </fn>
</model>
