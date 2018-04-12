<?xml version="1.0" encoding="utf-8" standalone="no"?><model xmlns="http://www.justep.com/model">  
  <store name="T_MapPhotoLib"/>  
  <store name="T_RealtyCommCircs"/>  
  <store name="T_HouseCFDetail"/>  
  <store name="T_HouseCancelDetail"/>  
  <store name="T_HouseCancel"/>  
  <store name="T_HousePledge"/>  
  <store name="HousePresellDetail"/>  
  <store name="HousePresell"/>  
  <store name="KFSData"/>  
  <store name="T_HouseTradeQsDetail"/>  
  <store name="T_HouseTradeQs_Dong"/>  
  <store name="T_HouseTradeQs"/>  
  <store name="HousePresellItem"/>  
  <store name="H_Building"/>  
  <store name="H_House"/>  
  <mapping concept="H_House"> 
    <table name="H_House" type="owner-table"> 
      <index fields="fDRZT" name="IDX_H_House_fDRZT" type="NORMAL"/>  
      <index fields="DISTRICT" name="IDX_H_House_DISTRICT" type="NORMAL"/> 
    </table> 
  </mapping>  
  <mapping concept="H_Building"> 
    <table name="H_Building" type="owner-table"> 
      <index fields="BuildingNo" name="IDX_H_Building_BuildingNo" type="NORMAL"/>  
      <index fields="dbh" name="IDX_H_Building_dbh" type="NORMAL"/>  
      <index fields="DISTRICT" name="IDX_H_Building_DISTRICT" type="NORMAL"/> 
    </table> 
  </mapping>  
  <mapping concept="HousePresellItem"> 
    <table name="H_FCXMB" type="owner-table"/> 
  </mapping>  
  <mapping concept="T_HouseTradeQs"> 
    <table name="H_HouseTradeQs" type="owner-table"/> 
  </mapping>  
  <mapping concept="T_HouseTradeQs_Dong"> 
    <table name="H_HouseTradeQs_Dong" type="owner-table"/> 
  </mapping>  
  <mapping concept="T_HouseTradeQsDetail"> 
    <table name="H_HouseTradeQsDetail" type="owner-table"/> 
  </mapping>  
  <mapping concept="KFSData"> 
    <table name="H_KFSData" type="owner-table"/> 
  </mapping>  
  <mapping concept="HousePresell"> 
    <table name="H_HousePresell" type="owner-table"> 
      <index fields="Presellname" name="IX_HOUSEPRESELL_0" type="NORMAL"/>  
      <index fields="PreSellBookId" name="IX_HOUSEPRESELL_1" type="NORMAL"/>  
      <index fields="CorpName" name="IX_HOUSEPRESELL_2" type="NORMAL"/> 
    </table> 
  </mapping>  
  <mapping concept="HousePresellDetail"> 
    <table name="H_HousePresellDetail" type="owner-table"> 
      <index fields="HouseId" name="IX_HOUSEPRESELLDETAIL1" type="NORMAL"/>  
      <index fields="dbh" name="IX_HOUSEPRESELLDETAIL_2" type="NORMAL"/> 
    </table> 
  </mapping>  
  <mapping concept="T_HousePledge"> 
    <table name="H_HousePledge" type="owner-table"/> 
  </mapping>  
  <mapping concept="T_HouseCancel"> 
    <table name="H_HouseCancel" type="owner-table"/> 
  </mapping>  
  <mapping concept="T_HouseCancelDetail"> 
    <table name="H_HouseCancelDetail" type="owner-table"> 
      <index fields="FFID" name="IX_T_HOUSECANCELDETAIL_FFID" type="NORMAL"/>  
      <index fields="fid" name="IDX_t_HouseCancelDetail_fid" type="NORMAL"/> 
    </table> 
  </mapping>  
  <mapping concept="T_HouseCFDetail"> 
    <table name="H_HouseCFDetail" type="owner-table"> 
      <index fields="ffid" name="IX_T_HOUSECFDETAIL" type="NORMAL"/>  
      <index fields="HouseBookFID" name="IDX_925313724" type="NORMAL"/> 
    </table> 
  </mapping>  
  <mapping concept="T_RealtyCommCircs"> 
    <table name="H_RealtyCommCircs" type="owner-table"> 
      <index fields="fId,LoginUnitID" name="IX_T_REALTYCOMMCIRCS_FID" type="NORMAL"/> 
    </table> 
  </mapping>  
  <mapping concept="T_MapPhotoLib"> 
    <table name="H_MapPhotoLib" type="owner-table"/> 
  </mapping>  
  <mapping concept="T_HousePledgeDetail"> 
    <table name="H_HousePledgeDetail" type="owner-table"> 
      <key field="fID" type="String"/> 
    </table> 
  </mapping>  
  <mapping concept="T_spfht"> 
    <table name="H_spfht" type="owner-table"> 
      <index fields="loginunitid" name="IX_T_SPFHT_HTID" type="NORMAL"/>  
      <index fields="htbh,loginunitid" name="IX_T_SPFHT" type="NORMAL"/>  
      <index fields="fwdm" name="IX_T_SPFHT_FWDM" type="NORMAL"/> 
    </table> 
  </mapping>  
  <mapping concept="HT_Buyer"> 
    <table name="H_Buyer" type="owner-table"> 
        
      <index fields="htbh,LoginUnitID" name="IX_HT_BUYER_HTBH" type="NORMAL"/> 
    </table> 
  </mapping> 
</model>