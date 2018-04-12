<?xml version="1.0" encoding="utf-8" standalone="no"?><model xmlns="http://www.justep.com/model">
<store name="B_SignImageUseLog"/>  
  <store name="B_PersonSignImage"/>
<mapping concept="B_SignImageUseLog"><table name="B_SignImageUseLog" type="owner-table">
<index fields="fTableName,fFieldName,fRowID" name="IDX_1748317350" type="UNIQUE"/>
</table>
</mapping>
<mapping concept="B_PersonSignImage"><table name="B_PersonSignImage" type="owner-table"><key field="fID" type="String"/>

<index fields="fPersonID,fValid" name="IDX__1938760972" type="NORMAL"/>
</table>
</mapping>
</model>