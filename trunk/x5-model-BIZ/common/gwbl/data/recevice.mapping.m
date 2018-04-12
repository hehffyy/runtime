<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <store name="B_Recipient"/>  
  <store name="B_CommonlyUsed"/>
  <mapping concept="B_CommonlyUsed">
    <table name="B_CommonlyUsed" type="owner-table">
      <key field="fID" type="String"/>  
      <index fields="fUserId,fName" name="IDX__429801773" type="UNIQUE"/> 
    </table> 
  </mapping> 
</model>
