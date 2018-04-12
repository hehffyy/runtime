<?xml version="1.0" encoding="UTF-8" standalone="no"?><model xmlns="http://www.justep.com/model">
<store name="VIEW_SMSINFO"/>

<store name="VIEW_SMSRECEIVEINFO"/>


<store name="B_smsReceivePerson"/>
<store name="B_smsInfo"/>
<store name="B_personPhoneTemplate"/>

<store name="B_receivePersonTemplate"/>

<mapping concept="VIEW_SMSRECEIVEINFO"><table name="VIEW_SMSRECEIVEINFO" type="owner-table"><key field="fID" type="String"/>
</table>
</mapping>

<mapping concept="VIEW_SMSINFO"><table name="VIEW_SMSINFO" type="owner-table"><key field="fID" type="String"/>
</table>
</mapping>
</model>