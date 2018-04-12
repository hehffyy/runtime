<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <store name="Portal2ProfileManager"/>  
  <store name="Portal2Profiles"/>  
  <mapping concept="Portal2Profiles"> 
    <table name="SA_Portal2Profiles" type="owner-table"> 
      <key field="sID" type="String"/>
      <!-- 
      <item field="version" relation="version"/>  
      <item field="sPersonID" relation="sPersonID" type="String"/>  
      <item field="sFunctree" relation="sFunctree" type="Text"/>  
      <item field="sPortal" relation="sPortal" type="Text"/>  
      <item field="sOther" relation="sOther" type="Text"/>  
      <item field="sPublishType" relation="sPublishType"/> 
       -->  
    </table> 
  </mapping>  
  <mapping concept="Portal2ProfileManager">
    <table name="SA_Portal2ProfileManager" type="owner-table">
      <key field="sID" type="String"/>
      <!-- 
      <item relation="version" field="version"/>  
      <item relation="sThemeID" field="sThemeID"/>  
      <item relation="sOrgID" field="sOrgID"/>  
      <item relation="sThemeActivity" field="sThemeActivity"/> 
       -->  
    </table> 
  </mapping> 
</model>
