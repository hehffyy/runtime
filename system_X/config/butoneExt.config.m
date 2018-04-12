<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model" xmlns:butone="http://www.butone.com">  
  <config name="disableSupervise" value="false"> 
    <label language="zh_CN">关闭监管规则，只规不管，不产生监管消息</label> 
  </config>  
  <config name="batchAdvanceProcessDialog" value="/UI/base/core/flowOperation/batchProcessDialog.w"> 
    <label language="zh_CN">批量流转对话框</label> 
  </config>
  <config name="processDiaolgWindow" value="/UI/base/core/flowOperation/batchProcessDialog.w"> 
    <label language="zh_CN">默认流程对话框</label> 
  </config>  
  <config name="singleUserLogin" value="false"> 
    <label language="zh_CN">单一用户登录</label> 
  </config>  
  <config name="forceIndex" value="true"> 
    <label language="zh_CN">任务查询使用强制索引</label> 
  </config>  
  <!-- 案卷签收模式,java表达式，登陆时计算一次 -->  
  <config name="signMode" value="true"> 
    <label language="zh_CN">任务签收模式java表达式</label> 
  </config>  
  <!-- 案卷查询功能URL，任意路径，例如/UI/gdstDZZW/archiveQuery.w -->  
  <config name="arhiveQueryFuncUrl" value="/UI/common/archives/process/archives/archiveQuery.w"> 
    <label language="zh_CN">案卷查询功能URL</label> 
  </config>  
  <config name="taskCenterFuncUrl" value="/UI/SA/task/taskCenter/taskCenter.w"> 
    <label language="zh_CN">任务中心功能URL</label> 
  </config>  
  <!-- 案卷(任务)查询使用角色授权 -->  
  <config name="bizRecQueryWithAuthorize" value=""> 
    <label language="zh_CN">案卷查询是否使用角色授权</label> 
  </config>  
  <!-- 办结结果 -->  
  <config name="finishResult" value="" butone:readonly="true"> 
    <item name="发证办结" value="0"> 
      <item name="finishKind" value="fkCertification"/>  
      <item name="isAbort" value="N"/>  
      <item name="desc" value="正常产生证照、批文的办结"/> 
    </item>  
    <item name="退回办结" value="1"> 
      <item name="isAbort" value="Y"/>  
      <item name="finishKind" value="fkUntread"/>  
      <item name="desc" value="退回或驳回申请的办结"/> 
    </item>  
    <item name="作废办结" value="2"> 
      <item name="isAbort" value="Y"/>  
      <item name="finishKind" value="fkAbort"/>  
      <item name="desc" value="指业务处理上无效的记录"/> 
    </item>  
    <item name="删除办结" value="3"> 
      <item name="isAbort" value="Y"/>  
      <item name="finishKind" value="fkDelete"/>  
      <item name="desc" value="指录入错误、操作错误等技术上的无效记录(删除数据，且不可恢复)"/> 
    </item>  
    <item name="转报办结" value="4"> 
      <item name="isAbort" value="Y"/>  
      <item name="finishKind" value="fkSubmit"/>  
      <item name="desc" value="指转报其他单位或上级单位的办结情况"/> 
    </item>  
    <item name="补正不来办结" value="5"> 
      <item name="isAbort" value="Y"/>  
      <item name="finishKind" value="fkApprizeAbort"/>  
      <item name="desc" value="指出现补正告知时，通知之后，申请人长期不来补正材料的办结"/> 
    </item>  
    <item name="办结" value="6"> 
      <item name="isAbort" value="N"/>  
      <item name="finishKind" value="fkNormal"/>  
      <item name="desc" value="除以上所述情况外的办结"/> 
    </item>  
    <item name="纸质办结" value="7"> 
      <item name="isAbort" value="Y"/>  
      <item name="finishKind" value="fkPaper"/>  
      <item name="desc" value="纸张材料已经办结"/> 
    </item> 
  </config>  
  <!-- 人员级别编码 -->  
  <config name="personLevel" value="" butone:readonly="true"> 
    <item name="国家主席" value="00000"> 
      <item name="disable" value="false"/> 
    </item>  
    <item name="国务院总理" value="00100"> 
      <item name="disable" value="false"/> 
    </item>  
    <item name="国务院副总理" value="00200"> 
      <item name="disable" value="false"/> 
    </item>  
    <item name="正部长级" value="00300"> 
      <item name="disable" value="false"/> 
    </item>  
    <item name="正部级" value="00400"> 
      <item name="disable" value="false"/> 
    </item>  
    <item name="副部长级" value="00500"> 
      <item name="disable" value="false"/> 
    </item>  
    <item name="副部级" value="00600"> 
      <item name="disable" value="false"/> 
    </item>  
    <item name="正厅级" value="00700"/>  
    <item name="副厅级" value="00800"/>  
    <item name="巡视员" value="00900"/>  
    <item name="副厅级" value="00A00"/>  
    <item name="助理巡视员" value="00B00"/>  
    <item name="正处级" value="00C00"/>  
    <item name="调研员" value="00D00"/>  
    <item name="副处级" value="00E00"/>  
    <item name="副调研员" value="00F00"/>  
    <item name="正科级" value="00G00"/>  
    <item name="副科级" value="00H00"/>  
    <item name="主任科员" value="00I00"/>  
    <item name="副主任科员" value="00J00"/>  
    <item name="科员" value="00K00"/>  
    <item name="办事员" value="00L00"/>  
    <item name="其他" value="ZZZZZ"/> 
  </config> 
</model>
