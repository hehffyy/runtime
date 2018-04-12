<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <process name="menuSetProcess"> 
    <label language="zh_CN">菜单配置</label>  
    <static-activity name="mainActivity"> 
      <label language="zh_CN">菜单配置</label> 
    </static-activity>  
    <has-action action="readXmlAction" access-permission="public"/>  
    <has-action action="generateXMLAction" access-permission="public"/>  
    <has-action action="hasChildAction" access-permission="public"/>  
    <has-action action="setTreeReceiveAction" access-permission="public"/>  
    <has-action action="queryB_MenuAction" access-permission="public"/>  
    <has-action action="saveB_MenuAction" access-permission="public"/>  
    <has-action action="createB_MenuAction" access-permission="public"/>  
  </process> 
</model>
