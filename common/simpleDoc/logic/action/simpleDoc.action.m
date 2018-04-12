<?xml version="1.0" encoding="UTF-8" standalone="no"?><model xmlns="http://www.justep.com/model"><action name="queryB_SimpleDocAction" procedure="bizQueryProcedure"><permission name="range" type="List"/>
<private name="concept" type="String" value="B_SimpleDoc"/>
<private name="select" type="String" value="B_SimpleDoc.*"/>
<private name="from" type="String" value="B_SimpleDoc B_SimpleDoc"/>
<private name="aggregate" type="String"/>
<private name="dataModel" type="String" value="/common/simpleDoc/data"/>
<private name="fnModel" type="String"/>
<protected name="condition" type="String"/>
<public name="distinct" type="Boolean" value="false"/>
<public name="idColumn" type="String" value="B_SimpleDoc"/>
<public name="filter" type="String"/>
<public name="limit" type="Integer"/>
<public name="offset" type="Integer"/>
<public name="columns" type="String"/>
<public name="orderBy" type="String"/>
<public name="aggregateColumns" type="String"/>
<public name="variables" type="Map"/>
</action>
<action name="saveB_SimpleDocAction" procedure="bizSaveProcedure"><permission name="insertRange" type="List"/>
<permission name="deleteRange" type="List"/>
<permission name="updateRange" type="List"/>
<private name="concept" type="String" value="B_SimpleDoc"/>
<private name="dataModel" type="String" value="/common/simpleDoc/data"/>
<private name="fnModel" type="String"/>
<protected name="readOnly" type="String"/>
<protected name="notNull" type="String"/>
<public name="table" required="true" type="Table"/>
</action>
<action name="createB_SimpleDocAction" procedure="bizCreateProcedure"><private name="concept" type="String" value="B_SimpleDoc"/>
<private name="fnModel" type="String"/>
<public name="table" required="true" type="Table"/>
<public name="defaultValues" type="Map"/>
</action>
<action name="genWmDoc" global="true" procedure="genWmDocProcedure"><label language="zh_CN">产生水印</label>
<public type="String" name="fileName"></public>
<public type="String" name="filePath"></public>
<public type="String" name="wmFileId"></public>
</action>
</model>