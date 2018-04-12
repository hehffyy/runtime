<?xml version="1.0" encoding="UTF-8"?>
<model xmlns="http://www.justep.com/model">
	<process name="systemConfigProcess">
		<label language="zh_CN">系统设置</label>
		<static-activity name="unitTypeConfig">
			<label language="zh_CN">单位类型设置</label>
		</static-activity>


		<static-activity name="importConfig">
			<label language="zh_CN">设计器获取界址点导入的表</label>
		</static-activity>






		<static-activity name="dictListByDictTypeConfig">
			<label language="zh_CN">数据字典值</label>
		</static-activity>
		<has-action action="getUnitType" access-permission="public"></has-action>
		<has-action action="getUnitsByType" access-permission="public"></has-action>
		<has-action action="getImportTable" access-permission="public"></has-action>
		<has-action action="getDictListByDictType"
			access-permission="public"></has-action>
		<static-activity name="systemConfigFileManager">
			<label language="zh_CN">系统配置管理</label>

			
			
		<has-action action="getSystemExtConfigAction" access-permission="public"></has-action>
<has-action action="syncSystemExtConfigAction" access-permission="public"></has-action>
</static-activity>
	</process>
</model>
