<?xml version="1.0" encoding="UTF-8"?>
<model xmlns="http://www.justep.com/model">
	<process name="elasticSearchProcess">
		<label language="zh_CN">ElasticSearch</label>
		<static-activity name="buildActivity">
			<label language="zh_CN">创建ElasticSearch索引</label>
		
<has-action action="buildIndiceAction" access-permission="public"></has-action>
<has-action action="importDataToIndiceAction" access-permission="public"></has-action>
</static-activity>
	</process>
</model>
