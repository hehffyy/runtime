<?xml version="1.0" encoding="utf-8" standalone="no"?><model xmlns="http://www.justep.com/model">
<store name="B_BusinessProcess"/>
<store name="B_UserBusinessMaterial"/>
<store name="B_UserProcess"/>
  
  <store name="B_Material"/> 
<mapping concept="B_Material"><table name="B_Material" type="owner-table"><key field="fID" type="String"/>
<index fields="fBizRecId" name="IDX_MATERIAL_BIZRECID" type="NORMAL"/>
<index fields="fParentId" name="IDX_MATERIAL_PARENTID" type="NORMAL"/>
<index fields="fBizRecId,fMaterialId" name="IDX_MATERIAL_RECMAT" type="NORMAL"/>
</table>
</mapping>
<mapping concept="B_UserBusinessMaterial"><table name="B_UserBusinessMaterial" type="owner-table">










<index fields="fUserProcessID" name="IDX__1807100761" type="NORMAL"/>

</table>
</mapping>
<mapping concept="B_BusinessProcess"><table name="B_BusinessProcess" type="owner-table"><key field="fID" type="String"/>



<index fields="fProcess" name="IDX_B_BusinessProcess_fProcess" type="NORMAL"/>
</table>
</mapping>
<mapping concept="B_UserProcess"><table name="B_UserProcess" type="owner-table">


<index fields="fUserID" name="IDX_B_UserProcess_fUserID" type="NORMAL"/>
<index fields="fProcess" name="IDX_B_UserProcess_fProcess" type="NORMAL"/>


</table>
</mapping>
</model>