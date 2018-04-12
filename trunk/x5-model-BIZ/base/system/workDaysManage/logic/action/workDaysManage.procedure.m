<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model" xmlns:m="http://www.justep.com/model">  
  <procedure name="initWorkDaysProcedure" code-model="/base/system/workDaysManage/logic/code" code="WorkDaysManage.initWorkDays"> 
    <parameter name="year" type="Integer"/> 
  </procedure>  
  <procedure name="refreshWorkDayCahceProcedure" code-model="/base/system/workDaysManage/logic/code" code="WorkDaysManage.refreshWorkDayCahce"> 
    <parameter name="year" type="Integer"/>
    <parameter name="month" type="Integer"/>
  </procedure> 
</model>
