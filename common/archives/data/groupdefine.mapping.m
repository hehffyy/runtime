<?xml version="1.0" encoding="utf-8" standalone="no"?><model xmlns="http://www.justep.com/model">
<store name="B_GroupDataPermission"/>  
  <store name="B_GroupField"/>  
  <store name="B_GroupFunc"/>  
  <store name="B_BusinessGroup"/>
  <mapping concept="B_GroupFunc">
    <table name="B_GroupFunc" type="owner-table">
      <key field="fID" type="String"/>  
      <index fields="fProcess,fBusinessGroupId" name="B_GROUPFUNC_GROUPPROC" type="UNIQUE"/> 
    </table> 
  </mapping> 
<mapping concept="B_GroupField"><table name="B_GroupField" type="owner-table"><key field="fID" type="String"/>
<index fields="fBusinessGroupId,fFieldAlias" name="B_GROUPFIELD_GROUPFN" type="UNIQUE"/>
</table>
</mapping>
<mapping concept="B_GroupDataPermission"><table name="B_GroupDataPermission" type="owner-table"><key field="fID" type="String"/>
<index fields="fBusinessGroupId" name="IDX_1523421338" type="NORMAL"/>
</table>
</mapping>
</model>