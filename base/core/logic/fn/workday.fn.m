<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <fn name="getDateAfterWorkDays" category="日期计算" code-model="/base/core/logic/code"
    code="WorkDay.getDateAfterWorkDays" type="Date"> 
    <label language="zh_CN">计算限办日期(工作日)</label>  
    <parameter type="Object" name="start"/>  
    <parameter type="Object" name="days"/>  
    <parameter type="Boolean" name="includeToday"/> 
  </fn> 
  <fn name="getDateAfterNatureDays" category="日期计算" code-model="/base/core/logic/code"
    code="WorkDay.getDateAfterNatureDays" type="Date"> 
    <label language="zh_CN">计算限办日期(自然日)</label>  
    <parameter type="Object" name="start"/>  
    <parameter type="Object" name="days"/>  
    <parameter type="Boolean" name="includeToday"/> 
  </fn> 
  <fn name="calcWorkDaysBetween" category="日期计算" code-model="/base/core/logic/code"
    code="WorkDay.calcWorkDaysBetween" type="Integer"> 
    <label language="zh_CN">计算工作日天数</label>  
    <parameter type="Date" name="startDate"/>  
    <parameter type="Date" name="endDate"/> 
  </fn>  
  <fn name="getLostWorkDays" category="日期计算" code-model="/base/core/logic/code"
    code="WorkDay.getLostWorkDays" type="Float"> 
    <label language="zh_CN">获得消耗的工作日天数</label>  
    <parameter type="Date" name="startDate"/>  
    <parameter type="Date" name="endDate"/>  
    <parameter type="Boolean" name="includeStart"/>  
    <parameter type="Boolean" name="half"/> 
  </fn>  
  <fn name="getLostNatureDays" category="日期计算" code="WorkDay.getLostWorkDays"
    code-model="/base/core/logic/code" type="Float"> 
    <label language="zh_CN">获得消耗的自然日天数</label>  
    <parameter type="Date" name="startDate"/>  
    <parameter type="Date" name="endDate"/>  
    <parameter type="Boolean" name="includeStart"/>  
    <parameter type="Boolean" name="half"/> 
  </fn>  
  <fn name="toDateEx" category="通用函数" code="WorkDay.toDateEx" code-model="/base/core/logic/code"
    type="Date"> 
    <parameter type="Object" name="obj"/>  
    <label language="zh_CN">转日期</label> 
  </fn>  
  <fn name="toDateTimeEx" category="通用函数" type="DateTime" code="WorkDay.toDateTimeEx"
    code-model="/base/core/logic/code"> 
    <parameter type="Object" name="obj"/>  
    <label language="zh_CN">转日期时间</label> 
  </fn> 
</model>
