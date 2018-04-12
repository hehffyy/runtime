<?xml version="1.0" encoding="UTF-8"?>
<model xmlns="http://www.justep.com/model">
	<action name="buildIndiceAction" global="false" procedure="buildIndiceProcedure">
		<label language="zh_CN">创建索引</label>
		<public type="String" name="dataModel" required="true"></public>
		<public type="String" name="concept" required="true"></public>
		<public type="String" name="alias"></public>
	<public type="String" name="columns"></public>
</action>
<action name="importDataToIndiceAction" global="false" procedure="importDataToIndiceProcedure"><public type="String" name="dataModel" required="true"></public>
<public type="String" name="concept" required="true"></public>
<public type="String" name="sql" required="true"></public>
<public type="String" name="alias"></public>
<label language="zh_CN">创建索引</label>
<public type="Integer" name="page" required="true"></public>
<public type="Integer" name="limit" required="true"></public>
</action>
</model>