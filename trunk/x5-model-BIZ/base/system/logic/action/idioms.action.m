<?xml version="1.0" encoding="UTF-8" standalone="no"?><model xmlns="http://www.justep.com/model"><action global="true" name="queryB_IdiomsAction" procedure="bizQueryProcedure"><permission name="range" type="List"/>
<private name="concept" type="String" value="B_Idioms"/>
<private name="select" type="String" value="B_Idioms.*"/>
<private name="from" type="String" value="B_Idioms B_Idioms"/>
<private name="aggregate" type="String"/>
<private name="dataModel" type="String" value="/base/system/data"/>
<private name="fnModel" type="String"/>
<protected name="condition" type="String" value="(B_Idioms.fPersonID = :currentPersonID())"/>
<public name="distinct" type="Boolean" value="false"/>
<public name="idColumn" type="String" value="B_Idioms"/>
<public name="filter" type="String"/>
<public name="limit" type="Integer"/>
<public name="offset" type="Integer"/>
<public name="columns" type="String"/>
<public name="orderBy" type="String"/>
<public name="aggregateColumns" type="String"/>
<public name="variables" type="Map"/>
</action>
<action global="true" name="saveB_IdiomsAction" procedure="bizSaveProcedure"><permission name="insertRange" type="List"/>
<permission name="deleteRange" type="List"/>
<permission name="updateRange" type="List"/>
<private name="concept" type="String" value="B_Idioms"/>
<private name="dataModel" type="String" value="/base/system/data"/>
<private name="fnModel" type="String"/>
<protected name="readOnly" type="String"/>
<protected name="notNull" type="String"/>
<public name="table" required="true" type="Table"/>
</action>
<action global="true" name="createB_IdiomsAction" procedure="bizCreateProcedure"><private name="concept" type="String" value="B_Idioms"/>
<private name="fnModel" type="String"/>
<public name="table" required="true" type="Table"/>
<public name="defaultValues" type="Map"/>
</action>
</model>