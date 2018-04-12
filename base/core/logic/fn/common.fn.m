<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <fn name="println" category="通用函数(扩展)" code="CommonFnExt.println" code-model="/base/core/logic/code"
    type="Object"> 
    <label language="zh_CN">打印输出到控制台</label>  
    <parameter type="Object" name="object"/> 
  </fn>  
  <fn name="listAdd" category="通用函数(扩展)" code-model="/base/core/logic/code"
    code="CommonFnExt.listAdd" type="List"> 
    <label language="zh_CN">两个List合并</label>  
    <parameter type="List" name="List"/>  
    <parameter type="Object" name="other"/> 
  </fn>  
  <fn name="inClause" category="通用函数(扩展)" code="CommonFnExt.inClause" type="String"
    code-model="/base/core/logic/code"> 
    <label language="zh_CN">替换为SQL的in子句</label>  
    <parameter type="String" name="in"/>  
    <parameter type="String" name="values"/> 
  </fn>  
  <fn name="updateBizData" category="SQL执行函数" code-model="/base/core/logic/code"
    code="CommonFnExt.updateBizData" type="Boolean"> 
    <label language="zh_CN">更新业务数据(增删改)</label>  
    <parameter type="String" name="updateStatement"/>  
    <parameter type="String" name="dataModel"/> 
  </fn>  
  <fn name="updateSQL" category="SQL执行函数" code-model="/base/core/logic/code"
    code="CommonFnExt.updateSQL" type="Integer"> 
    <label language="zh_CN">更新业务数据带参数(增删改)</label>  
    <parameter type="String" name="updateStatement"/>  
    <parameter type="String" name="dataModel"/>  
    <parameter type="List" name="binds"/> 
  </fn>  
  <fn name="newMap" category="通用函数(扩展)" code-model="/base/core/logic/code"
    code="CommonFnExt.newMap" type="Map"> 
    <label language="zh_CN">创建一个Map对象</label>  
    <parameter type="Object..." name="keyValues"/> 
  </fn>  
  <fn name="mapPut" category="通用函数(扩展)" code-model="/base/core/logic/code"
    code="CommonFnExt.mapPut" type="Object"> 
    <label language="zh_CN">设置Map属性</label>  
    <parameter type="Map" name="map"/>  
    <parameter type="Object..." name="keyValues"/> 
  </fn>  
  <fn name="jsonPut" category="JSON相关" code-model="/base/core/logic/code"
    code="CommonFnExt.jsonPut" type="Object"> 
    <label language="zh_CN">增加json对象属性</label>  
    <parameter type="Object" name="obj"/>  
    <parameter type="Object..." name="key_value"/> 
  </fn>  
  <fn name="jsonArrayAdd" category="JSON相关" code-model="/base/core/logic/code"
    code="CommonFnExt.jsonArrayAdd" type="Object"> 
    <label language="zh_CN">增加jsonArray对象</label>  
    <parameter type="Object" name="obj"/>  
    <parameter type="Object" name="child"/> 
  </fn>  
  <fn name="getJSONArraySize" category="JSON相关" code-model="/base/core/logic/code"
    code="CommonFnExt.getJSONArraySize" type="Integer"> 
    <label language="zh_CN">获取JSON数组size</label>  
    <parameter type="Object" name="obj"/> 
  </fn>  
  <fn name="startFlow" category="流程函数" code-model="/base/core/logic/code"
    code="CommonFnExt.startFlow" type="String"> 
    <label language="zh_CN">启动流程</label>  
    <parameter type="String" name="bizRecId"/>  
    <parameter type="String" name="process"/>  
    <parameter type="String" name="activity"/>  
    <parameter type="List" name="executors"/> 
  </fn>  
  <fn name="jsonGetAttr" category="JSON相关" code-model="/base/core/logic/code"
    code="CommonFnExt.jsonGetAttr" type="Object"> 
    <label language="zh_CN">获取json对象属性值</label>  
    <parameter type="Object" name="obj"/>  
    <parameter type="String" name="key"/> 
  </fn>  
  <fn name="jsonArrayGet" category="JSON相关" code-model="/base/core/logic/code"
    code="CommonFnExt.jsonArrayGet" type="Object"> 
    <label language="zh_CN">获取json数组元素</label>  
    <parameter type="Object" name="obj"/>  
    <parameter type="Integer" name="index"/> 
  </fn>
  <fn name="strToList" category="其他" code-model="/base/core/logic/code" code="CommonFnExt.strToList"
    type="List"> 
    <label language="zh_CN">字符转列表</label>  
    <parameter type="String" name="str"/>  
    <parameter type="String" name="spliter"/> 
  </fn>  
  <fn name="toJsonObj" category="JSON相关" code-model="/base/core/logic/code"
    code="CommonFnExt.toJsonObj" type="Object"> 
    <label language="zh_CN">字符串转json对象</label>  
    <parameter type="Object" name="obj"/> 
  </fn>  
  <fn name="getListSize" category="其他" code-model="/base/core/logic/code"
    code="CommonFnExt.getListSize" type="Integer"> 
    <label language="zh_CN">获取List对象的元素个数</label>  
    <parameter type="List" name="plist"/> 
  </fn>  
  <fn name="mapGet" category="通用函数(扩展)" code-model="/base/core/logic/code"
    code="CommonFnExt.mapGet" type="Object"> 
    <label language="zh_CN">获取Map属性</label>  
    <parameter type="Map" name="obj"/>  
    <parameter type="String" name="key"/> 
  </fn>  
  <fn name="readByJSONpath" category="JSON相关" code-model="/base/core/logic/code"
    code="CommonFnExt.readByJSONpath" type="Object"> 
    <label language="zh_CN">获取json指定节点路径的值</label>  
    <parameter type="Object" name="sourceJson"/>  
    <parameter type="String" name="jsonPath"/> 
  </fn>  
  <fn name="getListValueByIndex" category="其他" code-model="/base/core/logic/code"
    code="CommonFnExt.getListValueByIndex" type="Object"> 
    <label language="zh_CN">获取List的元素</label>  
    <parameter type="Object" name="plist"/>  
    <parameter type="Integer" name="index"/> 
  </fn>  
  <fn name="toInputStream" category="通用函数(扩展)" code-model="/base/core/logic/code"
    code="CommonFnExt.toInputStream" type="Object"> 
    <label language="zh_CN">转为输出流</label>  
    <parameter type="Object" name="obj"/>  
    <parameter type="String" name="charset"/> 
  </fn>  
  <fn name="loadJsonToTables" category="通用函数(扩展)" code-model="/base/core/logic/code"
    code="CommonFnExt.loadJsonToTables" type="Boolean"> 
    <label language="zh_CN">加载内存表数据</label>  
    <parameter type="Object" name="data"/>  
    <parameter type="Map" name="tableMap"/> 
  </fn>  
  <fn name="loadJsonToTables2" category="通用函数(扩展)" code-model="/base/core/logic/code"
    code="CommonFnExt.loadJsonToTables2" type="Boolean"> 
    <label language="zh_CN">加载内存表数据</label>  
    <parameter type="Object" name="data"/>  
    <parameter type="Map" name="tableMap"/>  
    <parameter type="Object" name="fkeyMap"/> 
  </fn>  
  <fn name="getRandomDate" category="随机数函数" code-model="/base/core/logic/code"
    code="CommonFnExt.getRandomDate" type="Date"> 
    <label language="zh_CN">随机日期</label>  
    <parameter type="Date" name="start"/>  
    <parameter type="Date" name="end"/> 
  </fn>  
  <fn name="getRandomInteger" category="随机数函数" code-model="/base/core/logic/code"
    code="CommonFnExt.getRandomInteger" type="Integer"> 
    <label language="zh_CN">随机整数</label>  
    <parameter type="Integer" name="start"/>  
    <parameter type="Integer" name="end"/> 
  </fn>  
  <fn name="updatePreLog" category="其他" code-model="/base/core/logic/code"
    code="CommonFnExt.updatePreLog" type="Integer"> 
    <label language="zh_CN">预收件日志</label>  
    <parameter type="String" name="fPreBizRecId"/>  
    <parameter type="String" name="personName"/>  
    <parameter type="String" name="fStatus"/>  
    <parameter type="String" name="fRemark"/> 
  </fn>  
  <fn name="copyTable" category="数据复制" code="CommonFnExt.copyTable" code-model="/base/core/logic/code"> 
    <label language="zh_CN">复制Table</label>  
    <parameter type="Object" name="fromTable"/>  
    <parameter type="Object" name="toTable"/> 
  </fn>  
  <fn name="copyRow" category="数据复制" code-model="/base/core/logic/code" code="CommonFnExt.copyRow"> 
    <label language="zh_CN">行复制</label>  
    <parameter type="Object" name="fromRow"/>  
    <parameter type="Object" name="toRow"/>  
    <parameter type="Object" name="toTable"/>  
    <parameter type="String" name="column"/>  
    <parameter type="String" name="value"/> 
  </fn>  
  <fn name="copyMaterial" category="数据复制" code="CommonFnExt.copyMaterial"
    code-model="/base/core/logic/code"> 
    <label language="zh_CN">复制材料信息</label>  
    <parameter type="String" name="fromId"/>  
    <parameter type="String" name="toId"/> 
  </fn>  
  <fn name="getBizRecData" category="数据取值和计算" type="Object" code="DataFnExt.getBizRecData"
    code-model="/base/core/logic/code"> 
    <label language="zh_CN">获得案卷数据</label>  
    <parameter type="String" name="concept"/>  
    <parameter type="String" name="relation"/> 
  </fn>  
  <fn name="preStartFlow" category="不动产应用" code-model="/base/core/logic/code"
    code="CommonFnExt.preStartFlow" type="String"> 
    <label language="zh_CN">启动流程</label>  
    <parameter type="String" name="bizRecId"/>  
    <parameter type="String" name="process"/>  
    <parameter type="String" name="activity"/>  
    <parameter type="String" name="fExecutorexpr"/> 
  </fn>  
  <fn name="copyRowEx" category="数据复制" code-model="/base/core/logic/code"
    code="CommonFnExt.copyRowEx"> 
    <label language="zh_CN">行复制</label>  
    <parameter type="Object" name="fromRow"/>  
    <parameter type="Object" name="toRow"/>  
    <parameter type="Object" name="toTable"/>  
    <parameter type="Object" name="fields"/>  
    <parameter type="Object" name="mapValue"/> 
  </fn>  
  <fn name="toJSONArray" category="JSON相关" code-model="/base/core/logic/code"
    code="CommonFnExt.toJSONArray" type="Object"> 
    <label language="zh_CN">字符转json数组</label>  
    <parameter type="String" name="list"/> 
  </fn>  
  <fn name="hashset" category="通用函数(扩展)" code="CommonFnExt.hashset" type="Object"
    code-model="/base/core/logic/code"> 
    <label language="zh_CN">哈希集合</label>  
    <parameter type="Object" name="set"/>  
    <parameter type="Object" name="value"/> 
  </fn>  
  <fn name="contains" category="通用函数(扩展)" code-model="/base/core/logic/code"
    code="CommonFnExt.contains" type="Boolean"> 
    <label language="zh_CN">集合中是否包含值</label>  
    <parameter type="Object" name="coll"/>  
    <parameter type="Object" name="value"/> 
  </fn>  
  <fn name="lpad" category="通用函数(扩展)" code-model="/base/core/logic/code" code="CommonFnExt.lpad"> 
    <label language="zh_CN">从左边对字符串使用指定的字符进行填充</label>  
    <parameter type="String" name="str"/>  
    <parameter type="Integer" name="len"/>  
    <parameter type="String" name="pad"/> 
  </fn>  
  <fn name="getSysPara" category="其他" code-model="/base/core/logic/code" code="CommonFnExt.getSysPara"> 
    <label language="zh_CN">系统参数</label>  
    <parameter type="String" name="sysName"/> 
  </fn>  
  <fn name="sendMessage" category="技术支持(消息)" code-model="/base/system/logic/code"
    code="MQTT.sendMessage" type="Object"> 
    <label language="zh_CN">发送消息</label>  
    <parameter type="String" name="targets"/>  
    <parameter type="String" name="type"/>  
    <parameter type="String" name="title"/>  
    <parameter type="String" name="url"/>  
    <parameter type="Map" name="exts"/>  
    <parameter type="String" name="kind"/> 
  </fn>  
  <fn name="sendFlowMessage" category="技术支持(消息)" code-model="/base/system/logic/code"
    code="MQTT.sendTaskMessage" type="Object"> 
    <label language="zh_CN">发送消息给流程的所有在办执行者</label>  
    <parameter type="String" name="flowGuid"/>  
    <parameter type="String" name="type"/>  
    <parameter type="String" name="title"/>  
    <parameter type="String" name="url"/>  
    <parameter type="Map" name="exts"/>  
    <parameter type="String" name="kind"/> 
  </fn>  
  <fn name="sendTaskMessage" category="技术支持(消息)" code-model="/base/system/logic/code"
    code="MQTT.sendTaskMessage" type="Object"> 
    <label language="zh_CN">发送消息给任务的所有在办执行者</label>  
    <parameter type="String" name="taskGuid"/>  
    <parameter type="String" name="type"/>  
    <parameter type="String" name="title"/>  
    <parameter type="String" name="url"/>  
    <parameter type="Map" name="exts"/>  
    <parameter type="String" name="kind"/> 
  </fn>  
  <fn name="sendBizRecMessage" category="技术支持(消息)" code-model="/base/system/logic/code"
    code="MQTT.sendBizRecMessage" type="Object"> 
    <label language="zh_CN">发送消息给案卷的所有在办执行者</label>  
    <parameter type="String" name="bizRecId"/>  
    <parameter type="String" name="type"/>  
    <parameter type="String" name="title"/>  
    <parameter type="String" name="url"/>  
    <parameter type="Map" name="exts"/>  
    <parameter type="String" name="kind"/> 
  </fn>  
  <fn name="containStr" category="通用函数(扩展)" code-model="/base/core/logic/code"
    code="CommonFnExt.containStr" type="Boolean"> 
    <label language="zh_CN">字符包含</label>  
    <parameter name="srcString" type="String"/>  
    <parameter name="cldString" type="String"/> 
  </fn>  
  <fn name="divide" category="通用函数(扩展)" code-model="/base/core/logic/code"
    code="CommonFnExt.divide" type="Decimal"> 
    <label language="zh_CN">除</label>  
    <parameter name="num1" type="Object"/>  
    <parameter name="num2" type="Object"/> 
  </fn>  
  <fn name="makeSequenceCode" category="通用编码" code="CommonFnExt.makeSequenceCode"
    code-model="/base/core/logic/code" type="String"> 
    <label language="zh_CN">产生通用编码</label>  
    <parameter type="String" name="codeGuid"/>  
    <parameter type="String" name="concept"/>  
    <parameter type="Map" name="nodeValues"/>  
    <parameter type="String" name="relation"/>  
    <parameter type="String" name="idValue"/> 
  </fn>  
  <fn name="formatNumber" category="通用函数(扩展)" code="CommonFnExt.formatNumber"
    code-model="/base/core/logic/code" type="String"> 
    <label language="zh_CN">格式化数字</label>  
    <parameter type="Object" name="val"/>  
    <parameter type="Integer" name="scale"/> 
  </fn>
  <fn name="signInfoIsNull" category="通用函数(扩展)" code="CommonFnExt.signInfoIsNull"
    code-model="/base/core/logic/code" type="String"> 
    <label language="zh_CN">会签是否已签名</label>  
    <parameter type="String" name="objectId"/>  
    <parameter type="Object..." name="key_value"/> 
  </fn>  
</model>
