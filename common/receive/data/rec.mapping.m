<?xml version="1.0" encoding="utf-8" standalone="no"?><model xmlns="http://www.justep.com/model">  
  <store name="B_Catalog"/>  
  <store name="B_Receive"/>
  <mapping concept="B_Catalog">
    <table name="B_Catalog" type="owner-table">
      <key field="fID" type="String"/>  
      <item field="fParent" relation="fParent" type="String"/> 
    </table> 
  </mapping>  
  <mapping concept="B_Receive">
    <table name="B_Receive" type="owner-table">
      <key field="fID" type="String"/>  
      <item field="fWJID" relation="fWJID" type="String"/> 
    <index fields="fWJID,fName" name="IDX_B_Receive_fWJID_fName" type="UNIQUE"/>
<index fields="fWJID,fProcess" name="IDX_B_Receive_fWJID_fProcess" type="NORMAL"/>
</table> 
  </mapping> 
</model>