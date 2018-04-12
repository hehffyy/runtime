<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <process name="archivesProcess"> 
    <label language="zh_CN">案卷查询</label>  
    <static-activity name="mainActivity"> 
      <label language="zh_CN">案卷查询</label> 
    </static-activity>  
    <has-action action="queryArchives" access-permission="public"/>  
    <has-action action="queryArchivesInfoAction" access-permission="public"/>  
    <has-action action="getRecordBizFields" access-permission="public"/>  
    <has-action action="queryBizSelect" access-permission="public"/>  
    <has-action action="getBizFilterFields" access-permission="public"/>  
    <has-action action="getBizRecStatuses" access-permission="public"/>  
    <has-action action="queryBizGroupAction" access-permission="public"/>  
    <has-action action="queryV_BizRecAction" access-permission="public"/>  
    <has-action action="openBizRecAction" access-permission="public"/>  
    <has-action action="getArchiveQueryFuncUrlAction" access-permission="public"/> 
  </process> 
</model>
