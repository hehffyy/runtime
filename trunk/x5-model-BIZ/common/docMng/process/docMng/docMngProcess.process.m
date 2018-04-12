<?xml version="1.0" encoding="UTF-8"?>
<model xmlns="http://www.justep.com/model">
	<process name="docMngProcess">
		<label language="zh_CN">文档管理</label>
		
	
<has-action action="queryB_DocFolderAction" access-permission="public"></has-action>
<has-action action="saveB_DocFolderAction" access-permission="public"></has-action>
<has-action action="createB_DocFolderAction" access-permission="public"></has-action>
<has-action action="queryB_DocFileAction" access-permission="public"></has-action>
<has-action action="saveB_DocFileAction" access-permission="public"></has-action>
<has-action action="createB_DocFileAction" access-permission="public"></has-action>
<static-activity name="manageActivity"><label language="zh_CN">文档管理</label>
</static-activity>
</process>
</model>
