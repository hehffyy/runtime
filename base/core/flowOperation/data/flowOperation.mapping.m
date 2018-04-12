<?xml version="1.0" encoding="utf-8" standalone="no"?><model xmlns="http://www.justep.com/model">
<store name="B_BatchOperationTask"/>
<store name="B_BatchOperation"/>  
  <store name="B_BZGZYY"/>  
    
  <store name="B_DICT_TBCXZL"/>  
  <store name="B_BJJLB"/>  
  <store name="B_TBCX"/>  
  <store name="B_BZGZ"/>  
  <store name="B_AJGQJLB"/>  
  <store name="B_BZCLQD"/>
  <mapping concept="B_TBCX">
    <table name="B_TBCX" type="owner-table"> 
      <index fields="fTBCXXH,fBizRecId" name="IDX_B_TBCX_fTBCXXH_fBizRecId" type="UNIQUE"/>  
      <index fields="fAJGQJL" name="IDX_B_TBCX_fAJGQJL" type="UNIQUE"/> 
    </table> 
  </mapping>  
  <mapping concept="B_BZGZ">
    <table name="B_BZGZ" type="owner-table"> 
      <index fields="fAJGQJL" name="IDX_B_BZGZ_fAJGQJL" type="UNIQUE"/>  
       
    <index name="IDX_B_BZGZ_FBIZRECID" type="NORMAL" fields="fBizRecId"></index>
</table> 
  </mapping>  
  <mapping concept="B_BZGZYY">
    <table name="B_BZGZYY" type="owner-table"> 
      <index fields="fBZGZ" name="IDX_B_BZGZYY_fBZGZ" type="NORMAL"/> 
    </table> 
  </mapping>  
  <mapping concept="B_BZCLQD">
    <table name="B_BZCLQD" type="owner-table"> 
      <index fields="fBZGZ" name="IDX_B_BZCLQD_fBZGZ" type="NORMAL"/> 
    </table> 
  </mapping>  
  <mapping concept="B_AJGQJLB">
    <table name="B_AJGQJLB" type="owner-table"> 
      <index fields="fBizRecId,fGQZT" name="IDX_B_AJGQJLB_fBizRecId_fGQZT" type="NORMAL"/> 
    </table> 
  </mapping>  
  <mapping concept="B_BJJLB">
    <table name="B_BJJLB" type="owner-table">
        
       
    </table> 
  </mapping>  
  <mapping concept="B_DICT_TBCXZL">
    <table name="B_DICT_TBCXZL" type="owner-table"/> 
  </mapping> 
<mapping concept="B_BatchOperationTask"><table name="B_BatchOperationTask" type="owner-table">
<index fields="fBatchGuid" name="IDX__290282997" type="NORMAL"/>
<index fields="fTaskId" name="IDX_921851224" type="NORMAL"/>
</table>
</mapping>
</model>