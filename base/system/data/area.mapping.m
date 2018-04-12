<?xml version="1.0" encoding="UTF-8" standalone="no"?><model xmlns="http://www.justep.com/model">
<store name="B_Area"/>
<mapping concept="B_Area"><table name="B_Area" type="owner-table">
<item field="fParentId" relation="fParentId" type="String"/>
<key field="fID" type="String"/>
<index fields="fParentId" name="IDX_AREA_PARENTID" type="NORMAL"/>
</table>
</mapping>
</model>