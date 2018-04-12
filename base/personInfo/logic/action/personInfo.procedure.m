<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model" xmlns:m="http://www.justep.com/model">  
  <procedure name="queryPersonSignImageInfoProcedure" code-model="/base/personInfo/logic/code" code="PersonInfo.queryPersonSignImageInfo"> 
    <parameter name="personID" type="String"/> 
  </procedure>  
  <procedure name="saveDeviceSignImageProcedure" code-model="/base/personInfo/logic/code" code="PersonInfo.saveDeviceSignImage">
    <parameter name="imageStr" type="String"/>
  </procedure>
</model>
