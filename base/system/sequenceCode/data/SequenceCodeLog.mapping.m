<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <store name="B_SequenceCodeLog"/> 
  <store name="B_SequenceCodeUseRecord"/>  
  <mapping concept="B_SequenceCodeLog"> 
    <table name="B_SequenceCodeLog" type="owner-table"> 
      <key field="fID" type="String"/>  
      <index fields="fCodeGuid,fGroupValue,fRelTableName" name="IDX_737602388"
        type="UNIQUE"/> 
    </table> 
  </mapping>  
  <mapping concept="B_SequenceCodeUseRecord"> 
    <table name="B_SequenceCodeUseRecord" type="owner-table"> 
      <key field="fID" type="String"/>  
      <index fields="fCodeGuid,fGroupValue,fRelTableName,fSequenceValue" name="IDX_1219251201"
        type="UNIQUE"/>  
      <index fields="fUserTableName,fUserField,fUserKeyValues" name="IDX__318320317"
        type="UNIQUE"/> 
    </table> 
  </mapping> 
</model>
