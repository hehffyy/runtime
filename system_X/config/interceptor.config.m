<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <config name="actionInterceptor" value=""> 
    <!-- 
		<item name="sysRuntimeLog" value="/system/logic/code,SysRuntimeLog,before"/>
		<item name="ProcessPushEngine" value="/system/logic/code,ProcessPushEngine,after"/>
		<item name="initClient" value="/system/logic/code/interceptor,com.justep.system.interceptor.InitClient,before"/>
		-->  
    <item name="checkPermission" value="/system/logic/code/interceptor,com.justep.system.interceptor.CheckPermission,before"/>  
    <!-- 
		<item name="checkClientPermission" value="/system/logic/code/interceptor,com.justep.system.interceptor.CheckClientPermission,before"/>
		 -->  
    <item name="logBefore" value="/system/logic/code/interceptor,com.justep.system.interceptor.LogBefore,before"/>  
    <item name="logAfter" value="/base/core/logic/code/interceptor,com.butone.interceptor.LogAfter,after"/>  
    <item name="processActionAfter" value="/base/core/logic/code/interceptor,com.butone.interceptor.ProcessActionAfter,after"/>  
    <item name="processActionBefore" value="/base/core/logic/code/interceptor,com.butone.interceptor.ProcessActionBefore,before"/> 
  </config> 
</model>
