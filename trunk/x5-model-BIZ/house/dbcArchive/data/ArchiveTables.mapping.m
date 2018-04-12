<?xml version="1.0" encoding="utf-8" standalone="no"?><model xmlns="http://www.justep.com/model">
<store name="T_HouseRegPledge"/>
  
  <store name="T_HouseRegCaution"/>  
  <store name="T_HouseRegSealup"/>  
  <store name="T_HouseRegPledgeAbuilding"/>  
  <store name="T_HouseRegMortgage"/>  
  <store name="T_HouseRegMain"/>  
  <store name="T_HouseRegObligee"/>  
  <store name="T_HouseRegBase"/>  
  <mapping concept="T_HouseRegBase"> 
    <table name="H_HouseRegBase" type="owner-table"> 
      <index fields="CaptureFID,RegisterNo" name="IX_T_HOUSEREGBASE_1" type="NORMAL"/>  
      <index fields="UsufructID" name="IX_T_HOUSEREGBASE_2" type="NORMAL"/>  
      <index fields="RegisterNo" name="IX_T_HOUSEREGBASE_3" type="NORMAL"/>  
      <index fields="CaptureFID" name="IX_T_HOUSEREGBASE_4" type="NORMAL"/>  
      <index fields="RegisterNo,CaptureFID" name="IX_T_HOUSEREGBASE_5" type="NORMAL"/>  
      <index fields="LoginUnitID" name="IDX_H_HouseRegBase_6" type="NORMAL"/> 
    </table> 
  </mapping>  
  <mapping concept="T_HouseRegObligee"> 
    <table name="H_HouseRegObligee" type="owner-table"> 
      <index fields="CaptureID" name="IX_T_HOUSEREGOBLIGEE_1" type="NORMAL"/>  
      <index fields="RegisterNo,CaptureFID" name="IX_T_HOUSEREGOBLIGEE_2" type="NORMAL"/>  
      <index fields="Obligee,CardID" name="IX_T_HOUSEREGOBLIGEE_3" type="NORMAL"/>  
      <index fields="BookID,CaptureID,RegisterNo" name="IX_T_HOUSEREGOBLIGEE_4" type="NORMAL"/>  
      <index fields="CQZH,RegisterNo" name="IX_T_HOUSEREGOBLIGEE_5" type="NORMAL"/>  
      <index fields="CQZH" name="IX_T_HOUSEREGOBLIGEE_6" type="NORMAL"/>  
      <index fields="FNo" name="IX_T_HOUSEREGOBLIGEE_7" type="NORMAL"/>  
      <index fields="CardID" name="IX_T_HOUSEREGOBLIGEE_8" type="NORMAL"/>  
      <index fields="CaptureFID" name="IX_T_HOUSEREGOBLIGEE_9" type="NORMAL"/>  
      <index fields="LoginUnitID" name="IX_T_HOUSEREGOBLIGEE_10" type="NORMAL"/> 
    </table> 
  </mapping>  
  <mapping concept="T_HouseRegMain"> 
    <table name="H_HouseRegMain" type="owner-table"> 
      <index fields="HouseID" name="IX_T_HOUSEREGMAIN" type="NORMAL"/>  
      <index fields="LoginUnitID" name="IX_T_HOUSEREGMAIN_1" type="NORMAL"/> 
    </table> 
  </mapping>  
  <mapping concept="T_HouseRegMortgage"> 
    <table name="H_HouseRegMortgage" type="owner-table"> 
      <index fields="ObligeeCardID" name="IX_T_HOUSEREGMORTGAGE_12" type="NORMAL"/>  
      <index fields="LoginUnitID" name="IX_T_HOUSEREGMORTGAGE_2" type="NORMAL"/>  
      <index fields="Obligee" name="IX_T_HOUSEREGMORTGAGE_3" type="NORMAL"/>  
      <index fields="RegisterNo,CaptureFID,LoginUnitID" name="IX_T_HOUSEREGMORTGAGE_4" type="NORMAL"/>  
      <index fields="BookID" name="IX_T_HOUSEREGMORTGAGE_5" type="NORMAL"/>  
      <index fields="RegisterNo" name="IX_T_HOUSEREGMORTGAGE_6" type="NORMAL"/>  
      <index fields="FNo" name="IX_T_HOUSEREGMORTGAGE_7" type="NORMAL"/>  
      <index fields="CaptureFID,LoginUnitID" name="IX_T_HOUSEREGMORTGAGE_8" type="NORMAL"/> 
    </table> 
  </mapping>  
  <mapping concept="T_HouseRegPledgeAbuilding"> 
    <table name="H_HouseRegPledgeAbuilding" type="owner-table"> 
      <index fields="LoginUnitID" name="IX_T_HOUSEREGPLEDGEABUILDING_1" type="NORMAL"/>  
      <index fields="Obligee" name="IX_T_HOUSEREGPLEDGEABUILDING_2" type="NORMAL"/>  
      <index fields="FNo" name="IX_T_HOUSEREGPLEDGEABUILDING_3" type="NORMAL"/>  
      <index fields="RegisterNo,CaptureFID,LoginUnitID" name="IX_T_HOUSEREGPLEDGEABUILDING_4" type="NORMAL"/>  
      <index fields="ObligeeCardID" name="IX_T_HOUSEREGPLEDGEABUILDING_5" type="NORMAL"/>  
      <index fields="BookID" name="IX_T_HOUSEREGPLEDGEABUILDING_6" type="NORMAL"/> 
    </table> 
  </mapping>  
  <mapping concept="T_HouseRegSealup"> 
    <table name="H_HouseRegSealup" type="owner-table"> 
      <index fields="LicNo" name="IX_T_HOUSEREGSEALUP_1" type="NORMAL"/>  
      <index fields="RegisterNo,CaptureFID" name="IX_T_HOUSEREGSEALUP_2" type="NORMAL"/>  
      <index fields="LoginUnitID" name="IX_T_HOUSEREGSEALUP_3" type="NORMAL"/> 
    </table> 
  </mapping>  
  <mapping concept="T_HouseRegCaution"> 
    <table name="H_HouseRegCaution" type="owner-table"> 
      <index fields="LoginUnitID" name="IX_T_HOUSEREGCAUTION_1" type="NORMAL"/>  
      <index fields="ObligeeCardID" name="IX_T_HOUSEREGCAUTION_2" type="NORMAL"/>  
      <index fields="CautionID" name="IX_T_HOUSEREGCAUTION_3" type="NORMAL"/>  
      <index fields="FNo,LoginUnitID" name="IX_T_HOUSEREGCAUTION_4" type="NORMAL"/>  
      <index fields="RegisterNo,CaptureFID,LoginUnitID" name="IX_T_HOUSEREGCAUTION_5" type="NORMAL"/> 
    </table> 
  </mapping>  
  <mapping concept="T_HouseRegObjection"> 
    <table name="H_HouseRegObjection" type="owner-table"> 
        
      <index fields="CaptureFID,RegisterNo,LoginUnitID" name="IX_T_HOUSEREGOBJECTION_0" type="NORMAL"/>  
        
       
    <index fields="LoginUnitID" name="IX_T_HOUSEREGOBJECTION_1" type="NORMAL"/>
</table> 
  </mapping> 
<mapping concept="T_HouseRegPledge"><table name="H_HouseRegPledge" type="owner-table">
<index fields="FNo" name="IX_T_HOUSEREGPLEDGE_1" type="NORMAL"/>
<index fields="ObligeeCardID" name="IX_T_HOUSEREGPLEDGE_2" type="NORMAL"/>
<index fields="CaptureFID,RegisterNo" name="IX_T_HOUSEREGPLEDGE_3" type="NORMAL"/>

<index fields="Obligee" name="IX_T_HOUSEREGPLEDGE_4" type="NORMAL"/>
<index fields="BookID" name="IX_T_HOUSEREGPLEDGE_5" type="NORMAL"/>

<index fields="TXQZH" name="IX_T_HOUSEREGPLEDGE_6" type="NORMAL"/>
<index fields="LoginUnitID" name="IX_T_HOUSEREGPLEDGE_7" type="NORMAL"/>
</table>
</mapping>
</model>