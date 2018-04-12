<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <process name="gzzxProcess"> 
    <label language="zh_CN">工作主线</label>  
    <static-activity name="mainActivity"> 
      <label language="zh_CN">工作主线</label>  
      <has-action action="setGaunZhuAction" access-permission="public"/>  
      <has-action action="queryMyAllZhuXianAction" access-permission="public"/>  
      <has-action action="queryGongZuoHuoDongAction" access-permission="public"/>  
      <has-action action="queryMyDeletedZhuXianAction" access-permission="public"/>  
      <has-action action="queryMyGuanZhuZhuXianAction" access-permission="public"/>  
      <has-action action="saveGongZuoZhuXianAction" access-permission="public"/>  
      <has-action action="createGongZuoZhuXianAction" access-permission="public"/>  
      <has-action action="saveGongZuoHuoDongAction" access-permission="public"/>  
      <has-action action="queryHuoDongCuiBanAction" access-permission="public"> 
        <public name="orderBy" type="String" value="B_HuoDongCuiBan.fCreateTime desc"/> 
      </has-action>  
      <has-action action="saveHuoDongCuiBanAction" access-permission="public"/>  
      <has-action action="createHuoDongCuiBanAction" access-permission="public"/>  
      <has-action action="queryHuoDongChuLiAction" access-permission="public"/> 
    </static-activity>  
    <static-activity name="yueJianCenter"> 
      <label language="zh_CN">阅件中心</label>  
      <has-action action="queryMyYueJianAction" access-permission="public"/>  
      <has-action action="increaseHuoDongViewCountAction" access-permission="public"/>  
      <has-action action="queryZhuXianLingDaoPSAction" access-permission="public"/>  
      <has-action action="queryHuoDongChuLiAction" access-permission="public"/>  
      <has-action action="saveHuoDongChuLiAction" access-permission="public"/>  
      <has-action action="createHuoDongChuLiAction" access-permission="public"/>  
      <has-action action="queryMyYuejian2Action" access-permission="public"/>  
      <has-action action="queryGongZuoHuoDongAction" access-permission="public"/>  
      <has-action action="getHuoDongHandleKindAction" access-permission="public"/>  
      <has-action action="queryLingDaoYuePiTaskAction" access-permission="public"/>  
      <has-action action="getPersonsOfDeptAction" access-permission="public"/>  
      <has-action action="queryHuoDongChuLiDeptListAction" access-permission="public"/>  
      <has-action action="statisticsNoHandleYueJianCountAction" access-permission="public"/> 
    </static-activity>  
    <static-activity name="yueJianActivity"> 
      <label language="zh_CN">阅件</label>  
      <has-action action="queryMyYueJianAction" access-permission="public"/>  
      <has-action action="increaseHuoDongViewCountAction" access-permission="public"/>  
      <has-action action="queryZhuXianLingDaoPSAction" access-permission="public"/>  
      <has-action action="queryHuoDongChuLiAction" access-permission="public"/>  
      <has-action action="saveHuoDongChuLiAction" access-permission="public"/>  
      <has-action action="createHuoDongChuLiAction" access-permission="public"/>  
      <has-action action="getfBizRecIdAction" access-permission="public"/>  
      <has-action action="batchReadAction" access-permission="public"/>  
      <has-action action="queryMyYuejian2Action" access-permission="public"/>  
      <has-action action="queryGongZuoHuoDongAction" access-permission="public"/> 
    </static-activity> 
  </process> 
</model>
