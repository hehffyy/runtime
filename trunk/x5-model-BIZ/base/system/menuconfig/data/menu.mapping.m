<?xml version="1.0" encoding="UTF-8" standalone="no"?><model xmlns="http://www.justep.com/model">
<store name="B_BizApp"/>
<store name="B_Menu"/><mapping concept="B_Menu"><table name="B_Menu" type="owner-table">
<item field="fBizApp" relation="fBizApp"/>
<item field="fID" relation="fID"/>
</table>
</mapping>
<mapping concept="B_BizApp"><table name="B_BizApp" type="owner-table"><key field="fID" type="String"/>

</table>
</mapping>
</model>