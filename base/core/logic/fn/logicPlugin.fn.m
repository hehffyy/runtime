<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <fn name="tableData" category="代码插件运行期" type="Object" code-model="/base/core/logic/code"
    code="LogicPluginContextUtils.tableData"> 
    <label language="zh_CN">获得表当前行数据</label>  
    <parameter type="String" name="objectId"/>  
    <parameter type="String" name="propName"/> 
  </fn>  
  <fn name="tableRecordCount" category="代码插件运行期" code-model="/base/core/logic/code"
    code="LogicPluginContextUtils.tableRecordCount" type="Integer"> 
    <label language="zh_CN">获得表记录数</label>  
    <parameter type="String" name="objectId"/> 
  </fn>  
  <fn name="saveTables" category="代码插件运行期" code-model="/base/core/logic/code"
    code="LogicPluginContextUtils.saveTables" type="Object"> 
    <label language="zh_CN">保存数据对象</label>  
    <parameter type="Boolean" name="ignoreException"/>  
    <parameter type="Boolean" name="emptyData"/>  
    <parameter type="Object" name="tableNames"/> 
  </fn>  
  <fn name="printTableState" category="代码插件运行期" code="LogicPluginContextUtils.printTableState"
    type="Boolean" code-model="/base/core/logic/code"> 
    <label language="zh_CN">打印工作表状态</label>  
    <parameter type="String" name="objectId"/> 
  </fn>  
  <fn name="tableInstance" category="代码插件运行期" code-model="/base/core/logic/code"
    code="LogicPluginContextUtils.tableInstance" type="Object"> 
    <parameter type="String" name="objectId"/>  
    <label language="zh_CN">获得Table实例对象</label> 
  </fn>  
  <fn name="executeCurrentBizLogicPlugin" category="代码插件运行期" code="LogicPluginContextUtils.executeCurrentBizLogicPlugin"
    code-model="/base/core/logic/code" type="Object"> 
    <label language="zh_CN">执行当前业务环节逻辑组件</label>  
    <parameter type="String" name="url"/>  
    <parameter type="String" name="bizRecId"/>  
    <parameter type="Map" name="variants"/>  
    <parameter type="String" name="filters"/> 
  </fn>  
  <fn name="executeTargetBizLogicPlugin" category="代码插件运行期" code="LogicPluginContextUtils.executeTargetBizLogicPlugin"
    code-model="/base/core/logic/code" type="Object"> 
    <label language="zh_CN">执行目标业务环节逻辑组件</label>  
    <parameter type="String" name="url"/>  
    <parameter type="String" name="targetProcess"/>  
    <parameter type="String" name="targetActivity"/>  
    <parameter type="String" name="bizRecId"/>  
    <parameter type="Map" name="variants"/>  
    <parameter type="String" name="filters"/> 
  </fn>  
  <fn name="clearTableData" category="代码插件运行期" code-model="/base/core/logic/code"
    code="LogicPluginContextUtils.clearTableData" type="Object"> 
    <label language="zh_CN">清空工作表数据</label>  
    <parameter type="String" name="objectId"/> 
  </fn>  
  <fn name="getPluginContextBizRecId" category="代码插件运行期" code="LogicPluginContextUtils.getPluginContextBizRecId"
    type="String" code-model="/base/core/logic/code"> 
    <label language="zh_CN">获得插件案卷ID</label> 
  </fn>  
  <fn name="tableRow" category="代码插件运行期" code-model="/base/core/logic/code"
    code="LogicPluginContextUtils.tableRow" type="Object"> 
    <label language="zh_CN">获取当前行</label>  
    <parameter type="String" name="objectId"/> 
  </fn>  
  <fn name="setTableRowValue" category="代码插件运行期" code-model="/base/core/logic/code"
    code="LogicPluginContextUtils.setTableRowValue"> 
    <label language="zh_CN">给当前行字段设置值</label>  
    <parameter type="String" name="objectId"/>  
    <parameter type="String" name="propName"/>  
    <parameter type="String" name="propValue"/> 
  </fn>  
  <fn name="tableRowState" category="代码插件运行期" code-model="/base/core/logic/code"
    code="LogicPluginContextUtils.tableRowState" type="String"> 
    <label language="zh_CN">获取当前行状态</label>  
    <parameter type="String" name="objectId"/> 
  </fn>  
  <fn name="refreshLogicProcessInnerTable" category="代码插件运行期" code="LogicPluginContextUtils.refreshLogicProcessInnerTable"
    code-model="/base/core/logic/code" type="Object"> 
    <label language="zh_CN">刷新逻辑组件内部数据集</label>  
    <parameter type="String" name="objectId"/> 
  </fn>  
  <fn name="setTableCursor" category="代码插件运行期" code="LogicPluginContextUtils.setTableCursor"
    code-model="/base/core/logic/code" type="Object"> 
    <label language="zh_CN">设置表对象游标</label>  
    <parameter type="String" name="objectId"/> 
    <parameter type="Integer" name="index"/> 
  </fn>  
  <fn name="getContextParameter" category="代码插件运行期" code-model="/base/core/logic/code"
    code="LogicPluginContextUtils.getContextParameter" type="Object"> 
    <label language="zh_CN">获得插件执行上下文参数</label>  
    <parameter type="String" name="name"/>  
    <parameter type="Boolean" name="parent"/> 
  </fn>  
  <fn name="setContextParameter" category="代码插件运行期" code-model="/base/core/logic/code"
    code="LogicPluginContextUtils.setContextParameter" type="Object"> 
    <label language="zh_CN">设置插件执行上下文参数</label>  
    <parameter type="String" name="name"/>  
    <parameter type="Object" name="value"/> 
  </fn>  
  <fn name="getBizRecValue" category="代码插件运行期" code="LogicPluginContextUtils.getBizRecValue"
    type="Object" code-model="/base/core/logic/code"> 
    <label language="zh_CN">获得案卷字段值</label>  
    <parameter type="String" name="name"/> 
  </fn>  
  <fn name="setBizRecValue" category="代码插件运行期" code-model="/base/core/logic/code"
    code="LogicPluginContextUtils.setBizRecValue" type="Object"> 
    <label language="zh_CN">设置案卷字段值</label>  
    <parameter type="String" name="name"/>  
    <parameter type="Object" name="value"/> 
  </fn>  
  <fn name="setBizRecValues" category="代码插件运行期" code-model="/base/core/logic/code"
    code="LogicPluginContextUtils.setBizRecValues" type="Object"> 
    <label language="zh_CN">设置案卷多个字段值</label>  
    <parameter type="List" name="names"/>  
    <parameter type="List" name="values"/> 
  </fn> 
  <fn name="checkTargetBizRules" category="业务规则" code-model="/base/core/logic/code"
    code="LogicPluginContextUtils.checkTargetBizRules" type="List">
    <label language="zh_CN">检查业务规则</label>  
    <parameter type="String" name="targetProcess"/>  
    <parameter type="String" name="targetActivity"/>  
    <parameter type="String" name="bizRecId"/>  
    <parameter type="Map" name="variants"/>  
    <parameter type="Map" name="filters"/>  
    <parameter type="List" name="urls"/> 
  </fn> 
  <fn name="joinBizRuleMessages" category="业务规则" code-model="/base/core/logic/code"
    code="LogicPluginContextUtils.joinBizRuleMessages" type="String">
    <label language="zh_CN">连接所有规则检查结果的消息</label>  
    <parameter type="List" name="ruleInfos"/>  
    <parameter type="String" name="separator"/>
  </fn> 
</model>
