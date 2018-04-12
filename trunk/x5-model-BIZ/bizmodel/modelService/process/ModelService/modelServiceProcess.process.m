<?xml version="1.0" encoding="UTF-8"?>
<model xmlns="http://www.justep.com/model">
	<process name="modelServiceProcess" kind="SYSTEM">
		<label language="zh_CN">模型服务</label>
		<static-activity name="mainActivity">
			<label language="zh_CN">模型服务</label>
			
		
<has-action action="createElasticSearchIndiceAction" access-permission="public"></has-action>
<has-action action="importDataToElasticSearchAction" access-permission="public"></has-action>
</static-activity>
	</process>
</model>
