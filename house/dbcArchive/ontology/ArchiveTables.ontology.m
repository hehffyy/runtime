<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <concept name="T_HouseRegBase" default-value-expr="guid()" keys="AutoID"> 
    <has-relation relation="RegisterNo" size="50" required="true"> 
      <label language="zh_CN">RegisterNo</label> 
    </has-relation>  
    <has-relation relation="TerraID" size="150"> 
      <label language="zh_CN">地号</label> 
    </has-relation>  
    <has-relation relation="HouseRepose" size="600"> 
      <label language="zh_CN">房屋坐落</label> 
    </has-relation>  
    <has-relation relation="District" size="50"> 
      <label language="zh_CN">所属区</label> 
    </has-relation>  
    <has-relation relation="Path" size="50"> 
      <label language="zh_CN">路段</label> 
    </has-relation>  
    <has-relation relation="BuildingName" size="100"> 
      <label language="zh_CN">项目名称</label> 
    </has-relation>  
    <has-relation relation="DongName" size="100"> 
      <label language="zh_CN">栋号</label> 
    </has-relation>  
    <has-relation relation="HouseNo" size="100"> 
      <label language="zh_CN">房号</label> 
    </has-relation>  
    <has-relation relation="Floor" size="50"> 
      <label language="zh_CN">所在层</label> 
    </has-relation>  
    <has-relation relation="FloorCount" size="30"> 
      <label language="zh_CN">总层数</label> 
    </has-relation>  
    <has-relation relation="HouseFrame" size="50"> 
      <label language="zh_CN">房屋结构</label> 
    </has-relation>  
    <has-relation relation="HouseType" size="20"> 
      <label language="zh_CN">房屋类型</label> 
    </has-relation>  
    <has-relation relation="EastWall" size="50"> 
      <label language="zh_CN">东墙</label> 
    </has-relation>  
    <has-relation relation="EastWallTo" size="50"> 
      <label language="zh_CN">东墙至</label> 
    </has-relation>  
    <has-relation relation="WestWall" size="50"> 
      <label language="zh_CN">西墙</label> 
    </has-relation>  
    <has-relation relation="WestWallTo" size="50"> 
      <label language="zh_CN">西墙至</label> 
    </has-relation>  
    <has-relation relation="SouthWall" size="50"> 
      <label language="zh_CN">南墙</label> 
    </has-relation>  
    <has-relation relation="SouthWallTo" size="50"> 
      <label language="zh_CN">南墙至</label> 
    </has-relation>  
    <has-relation relation="NorthWall" size="50"> 
      <label language="zh_CN">北墙</label> 
    </has-relation>  
    <has-relation relation="NorthWallTo" size="50"> 
      <label language="zh_CN">北墙至</label> 
    </has-relation>  
    <has-relation relation="UseArea" size="18" scale="2"> 
      <label language="zh_CN">建筑面积</label> 
    </has-relation>  
    <has-relation relation="UseBuildArea" size="18" scale="2"> 
      <label language="zh_CN">住宅套内面积</label> 
    </has-relation>  
    <has-relation relation="UseCommArea" size="18" scale="2"> 
      <label language="zh_CN">住宅分摊面积</label> 
    </has-relation>  
    <has-relation relation="PrivateArea" size="18" scale="2"> 
      <label language="zh_CN">专有部分面积</label> 
    </has-relation>  
    <has-relation relation="HouseUse" size="50"> 
      <label language="zh_CN">房屋用途</label> 
    </has-relation>  
    <has-relation relation="CompleteDate"> 
      <label language="zh_CN">竣工日期</label> 
    </has-relation>  
    <has-relation relation="DroitKind" size="50"> 
      <label language="zh_CN">房屋性质</label> 
    </has-relation>  
    <has-relation relation="UsufructID" size="200"> 
      <label language="zh_CN">土地证号</label> 
    </has-relation>  
    <has-relation relation="UsufructArea" size="24" scale="4"> 
      <label language="zh_CN">土地面积</label> 
    </has-relation>  
    <has-relation relation="LandUseKind" size="50"> 
      <label language="zh_CN">土地性质</label> 
    </has-relation>  
    <has-relation relation="LandUse" size="300"> 
      <label language="zh_CN">土地用途</label> 
    </has-relation>  
    <has-relation relation="LandUseSource" size="100"> 
      <label language="zh_CN">土地所有权取得方式</label> 
    </has-relation>  
    <has-relation relation="FramingNo" size="6"> 
      <label language="zh_CN">幅号</label> 
    </has-relation>  
    <has-relation relation="HillNo" size="4"> 
      <label language="zh_CN">丘号</label> 
    </has-relation>  
    <has-relation relation="StartDate"> 
      <label language="zh_CN">土地取得日期</label> 
    </has-relation>  
    <has-relation relation="UseYears"> 
      <label language="zh_CN">土地使用年限</label> 
    </has-relation>  
    <has-relation relation="RegDate"> 
      <label language="zh_CN">登记日期</label> 
    </has-relation>  
    <has-relation relation="VerifyMan" size="50"> 
      <label language="zh_CN">VerifyMan</label> 
    </has-relation>  
    <has-relation relation="RegMan" size="50"> 
      <label language="zh_CN">RegMan</label> 
    </has-relation>  
    <has-relation relation="CaptureID" size="50"> 
      <label language="zh_CN">收件号</label> 
    </has-relation>  
    <has-relation relation="CaptureFID" size="50"> 
      <label language="zh_CN">CaptureFID</label> 
    </has-relation>  
    <has-relation relation="TableName" size="30"> 
      <label language="zh_CN">TableName</label> 
    </has-relation>  
    <has-relation relation="CreateDate"> 
      <label language="zh_CN">CreateDate</label> 
    </has-relation>  
    <has-relation relation="Ftype" size="50"> 
      <label language="zh_CN">业务类型</label> 
    </has-relation>  
    <has-relation relation="Remark" size="4000"> 
      <label language="zh_CN">附记</label> 
    </has-relation>  
    <has-relation relation="Status" size="50"> 
      <label language="zh_CN">状态</label> 
    </has-relation>  
    <has-relation relation="HorPoint" size="6"> 
      <label language="zh_CN">横坐标</label> 
    </has-relation>  
    <has-relation relation="VerPoint" size="6"> 
      <label language="zh_CN">纵坐标</label> 
    </has-relation>  
    <has-relation relation="Town" size="20"> 
      <label language="zh_CN">乡镇街道</label> 
    </has-relation>  
    <has-relation relation="Town_SN" size="8"> 
      <label language="zh_CN">Town_SN</label> 
    </has-relation>  
    <has-relation relation="DetailFid" size="50"> 
      <label language="zh_CN">DetailFid</label> 
    </has-relation>  
    <has-relation relation="TDSYQQX" size="300"> 
      <label language="zh_CN">TDSYQQX</label> 
    </has-relation>  
    <has-relation relation="ChildID"> 
      <label language="zh_CN">ChildID</label> 
    </has-relation>  
    <has-relation relation="st_flag" required="true"> 
      <label language="zh_CN">st_flag</label> 
    </has-relation>  
    <has-relation relation="AutoID" data-type="Decimal" size="18"/>  
    <has-relation relation="LoginUnitID" data-type="String" size="100"/>  
    <label language="zh_CN">房屋信息主表数据</label> 
  </concept>  
  <relation name="ChildID" data-type="String"> 
    <label language="zh_CN">ChildID</label> 
  </relation>  
  <relation name="VerifyMan" data-type="String"> 
    <label language="zh_CN">VerifyMan</label> 
  </relation>  
  <relation name="VerPoint" data-type="String"> 
    <label language="zh_CN">纵坐标</label> 
  </relation>  
  <relation name="HouseNo" data-type="String"> 
    <label language="zh_CN">房号</label> 
  </relation>  
  <relation name="EastWall" data-type="String"> 
    <label language="zh_CN">东墙</label> 
  </relation>  
  <relation name="EastWallTo" data-type="String"> 
    <label language="zh_CN">东墙至</label> 
  </relation>  
  <relation name="FloorCount" data-type="String"> 
    <label language="zh_CN">总层数</label> 
  </relation>  
  <relation name="CreateDate" data-type="DateTime"> 
    <label language="zh_CN">CreateDate</label> 
  </relation>  
  <relation name="Path" data-type="String"> 
    <label language="zh_CN">路段</label> 
  </relation>  
  <relation name="StartDate" data-type="DateTime"> 
    <label language="zh_CN">土地取得日期</label> 
  </relation>  
  <relation name="SouthWall" data-type="String"> 
    <label language="zh_CN">南墙</label> 
  </relation>  
  <relation name="SouthWallTo" data-type="String"> 
    <label language="zh_CN">南墙至</label> 
  </relation>  
  <relation name="BuildingName" data-type="String"> 
    <label language="zh_CN">项目名称</label> 
  </relation>  
  <relation name="LandUseKind" data-type="String"> 
    <label language="zh_CN">土地性质</label> 
  </relation>  
  <relation name="CompleteDate" data-type="DateTime"> 
    <label language="zh_CN">竣工日期</label> 
  </relation>  
  <relation name="RegDate" data-type="DateTime"> 
    <label language="zh_CN">登记日期</label> 
  </relation>  
  <relation name="Floor" data-type="String"> 
    <label language="zh_CN">所在层</label> 
  </relation>  
  <relation name="Town_SN" data-type="String"> 
    <label language="zh_CN">Town_SN</label> 
  </relation>  
  <relation name="District" data-type="String"> 
    <label language="zh_CN">所属区</label> 
  </relation>  
  <relation name="HillNo" data-type="Decimal"> 
    <label language="zh_CN">丘号</label> 
  </relation>  
  <relation name="Town" data-type="String"> 
    <label language="zh_CN">乡镇街道</label> 
  </relation>  
  <relation name="LandUseSource" data-type="String"> 
    <label language="zh_CN">土地所有权取得方式</label> 
  </relation>  
  <relation name="LandUse" data-type="String"> 
    <label language="zh_CN">土地用途</label> 
  </relation>  
  <relation name="PrivateArea" data-type="Decimal"> 
    <label language="zh_CN">专有部分面积</label> 
  </relation>  
  <relation name="NorthWall" data-type="String"> 
    <label language="zh_CN">北墙</label> 
  </relation>  
  <relation name="CaptureFID" data-type="String"> 
    <label language="zh_CN">CaptureFID</label> 
  </relation>  
  <relation name="DetailFid" data-type="String"> 
    <label language="zh_CN">DetailFid</label> 
  </relation>  
  <relation name="UseArea" data-type="Decimal"> 
    <label language="zh_CN">建筑面积</label> 
  </relation>  
  <relation name="CaptureID" data-type="String"> 
    <label language="zh_CN">收件号</label> 
  </relation>  
  <relation name="WestWall" data-type="String"> 
    <label language="zh_CN">西墙</label> 
  </relation>  
  <relation name="RegMan" data-type="String"> 
    <label language="zh_CN">RegMan</label> 
  </relation>  
  <relation name="HouseUse" data-type="String"> 
    <label language="zh_CN">房屋用途</label> 
  </relation>  
  <relation name="FramingNo" data-type="Decimal"> 
    <label language="zh_CN">幅号</label> 
  </relation>  
  <relation name="UseBuildArea" data-type="Decimal"> 
    <label language="zh_CN">住宅套内面积</label> 
  </relation>  
  <relation name="NorthWallTo" data-type="String"> 
    <label language="zh_CN">北墙至</label> 
  </relation>  
  <relation name="UseYears" data-type="Integer"> 
    <label language="zh_CN">土地使用年限</label> 
  </relation>  
  <relation name="HouseType" data-type="String"> 
    <label language="zh_CN">房屋类型</label> 
  </relation>  
  <relation name="DroitKind" data-type="String"> 
    <label language="zh_CN">房屋性质</label> 
  </relation>  
  <relation name="Ftype" data-type="String"> 
    <label language="zh_CN">业务类型</label> 
  </relation>  
  <relation name="st_flag" data-type="Integer"> 
    <label language="zh_CN">st_flag</label> 
  </relation>  
  <relation name="UseCommArea" data-type="Decimal"> 
    <label language="zh_CN">住宅分摊面积</label> 
  </relation>  
  <relation name="UsufructArea" data-type="Decimal"> 
    <label language="zh_CN">土地面积</label> 
  </relation>  
  <relation name="DongName" data-type="String"> 
    <label language="zh_CN">栋号</label> 
  </relation>  
  <relation name="HorPoint" data-type="String"> 
    <label language="zh_CN">横坐标</label> 
  </relation>  
  <relation name="Status" data-type="String"> 
    <label language="zh_CN">状态</label> 
  </relation>  
  <relation name="HouseFrame" data-type="String"> 
    <label language="zh_CN">房屋结构</label> 
  </relation>  
  <relation name="TerraID" data-type="String"> 
    <label language="zh_CN">地号</label> 
  </relation>  
  <relation name="TableName" data-type="String"> 
    <label language="zh_CN">TableName</label> 
  </relation>  
  <relation name="UsufructID" data-type="String"> 
    <label language="zh_CN">土地证号</label> 
  </relation>  
  <relation name="HouseRepose" data-type="String"> 
    <label language="zh_CN">房屋坐落</label> 
  </relation>  
  <relation name="Remark" data-type="String"> 
    <label language="zh_CN">附记</label> 
  </relation>  
  <relation name="TDSYQQX" data-type="String"> 
    <label language="zh_CN">TDSYQQX</label> 
  </relation>  
  <relation name="WestWallTo" data-type="String"> 
    <label language="zh_CN">西墙至</label> 
  </relation>  
  <relation name="RegisterNo" data-type="String"> 
    <label language="zh_CN">RegisterNo</label> 
  </relation>  
  <relation name="AutoID" data-type="Decimal"> 
    <label language="zh_CN">AutoID</label> 
  </relation>  
  <relation name="LoginUnitID" data-type="String" size="100"> 
    <label language="zh_CN">LoginUnitID</label> 
  </relation>  
  <concept name="T_HouseRegObligee" default-value-expr="guid()" keys="AutoID"> 
    <has-relation relation="RegisterNo" size="50"/>  
    <has-relation relation="CaptureID" size="50"/>  
    <has-relation relation="Ftype" size="20"/>  
    <has-relation relation="Type_jy" size="20"> 
      <label language="zh_CN">交易类型</label> 
    </has-relation>  
    <has-relation relation="Obligee" size="200"> 
      <label language="zh_CN">权属人</label> 
    </has-relation>  
    <has-relation relation="CardID" size="150"> 
      <label language="zh_CN">身份证件号码</label> 
    </has-relation>  
    <has-relation relation="RegAddr" size="500"> 
      <label language="zh_CN">户籍所在地</label> 
    </has-relation>  
    <has-relation relation="ObligeeSex" size="5"> 
      <label language="zh_CN">权属人性别</label> 
    </has-relation>  
    <has-relation relation="Nation" size="30"> 
      <label language="zh_CN">权属人国籍</label> 
    </has-relation>  
    <has-relation relation="ObligeeType" size="20"> 
      <label language="zh_CN">权属人类型</label> 
    </has-relation>  
    <has-relation relation="DroitKind" size="50"/>  
    <has-relation relation="DroitSource" size="200"> 
      <label language="zh_CN">房屋取得方式</label> 
    </has-relation>  
    <has-relation relation="HouseShare" size="30"> 
      <label language="zh_CN">共有份额</label> 
    </has-relation>  
    <has-relation relation="BookID" size="50"> 
      <label language="zh_CN">BookID</label> 
    </has-relation>  
    <has-relation relation="CancelFlag" size="50"> 
      <label language="zh_CN">CancelFlag</label> 
    </has-relation>  
    <has-relation relation="RegDate"/>  
    <has-relation relation="VerifyMan" size="20"/>  
    <has-relation relation="RegMan" size="20"/>  
    <has-relation relation="CaptureFID" size="50"/>  
    <has-relation relation="TableName" size="30"/>  
    <has-relation relation="CreateDate"/>  
    <has-relation relation="Remark" size="2000"/>  
    <has-relation relation="Status" size="50"/>  
    <has-relation relation="FNo" size="100"> 
      <label language="zh_CN">FNo</label> 
    </has-relation>  
    <has-relation relation="CQZH" size="100"> 
      <label language="zh_CN">CQZH</label> 
    </has-relation>  
    <has-relation relation="gfr" size="200"> 
      <label language="zh_CN">gfr</label> 
    </has-relation>  
    <has-relation relation="st_flag" required="true"/>  
    <has-relation relation="st_flag2" required="true"> 
      <label language="zh_CN">st_flag2</label> 
    </has-relation>  
    <has-relation relation="Commshare" size="50"> 
      <label language="zh_CN">Commshare</label> 
    </has-relation>  
    <has-relation relation="AutoID" size="18"/>  
    <label language="zh_CN">房屋登记簿信息</label>  
    <has-relation relation="LoginUnitID"/> 
  </concept>  
  <relation name="CardID" data-type="String"> 
    <label language="zh_CN">身份证件号码</label> 
  </relation>  
  <relation name="gfr" data-type="String"> 
    <label language="zh_CN">gfr</label> 
  </relation>  
  <relation name="st_flag2" data-type="Integer"> 
    <label language="zh_CN">st_flag2</label> 
  </relation>  
  <relation name="ObligeeType" data-type="String"> 
    <label language="zh_CN">权属人类型</label> 
  </relation>  
  <relation name="Obligee" data-type="String"> 
    <label language="zh_CN">权属人</label> 
  </relation>  
  <relation name="DroitSource" data-type="String"> 
    <label language="zh_CN">房屋取得方式</label> 
  </relation>  
  <relation name="Type_jy" data-type="String"> 
    <label language="zh_CN">交易类型</label> 
  </relation>  
  <relation name="BookID" data-type="String"> 
    <label language="zh_CN">BookID</label> 
  </relation>  
  <relation name="Commshare" data-type="String"> 
    <label language="zh_CN">Commshare</label> 
  </relation>  
  <relation name="CancelFlag" data-type="String"> 
    <label language="zh_CN">CancelFlag</label> 
  </relation>  
  <relation name="Nation" data-type="String"> 
    <label language="zh_CN">权属人国籍</label> 
  </relation>  
  <relation name="RegAddr" data-type="String"> 
    <label language="zh_CN">户籍所在地</label> 
  </relation>  
  <relation name="HouseShare" data-type="String"> 
    <label language="zh_CN">共有份额</label> 
  </relation>  
  <relation name="CQZH" data-type="String"> 
    <label language="zh_CN">CQZH</label> 
  </relation>  
  <relation name="FNo" data-type="String"> 
    <label language="zh_CN">FNo</label> 
  </relation>  
  <relation name="ObligeeSex" data-type="String"> 
    <label language="zh_CN">权属人性别</label> 
  </relation>  
  <concept name="T_HouseRegMain" default-value-expr="guid()" keys="RegisterNo"> 
    <has-relation relation="HouseID" size="30"> 
      <label language="zh_CN">HouseID</label> 
    </has-relation>  
    <has-relation relation="PhouseID" size="50"> 
      <label language="zh_CN">PhouseID</label> 
    </has-relation>  
    <has-relation relation="BuildingID" size="50"> 
      <label language="zh_CN">BuildingID</label> 
    </has-relation>  
    <has-relation relation="ZxFlag" size="20"> 
      <label language="zh_CN">ZxFlag</label> 
    </has-relation>  
    <has-relation relation="TxqFlag" size="50"> 
      <label language="zh_CN">TxqFlag</label> 
    </has-relation>  
    <has-relation relation="CfFlag"> 
      <label language="zh_CN">CfFlag</label> 
    </has-relation>  
    <has-relation relation="CreateDate"/>  
    <has-relation relation="HouseRegBaseID" size="10"> 
      <label language="zh_CN">HouseRegBaseID</label> 
    </has-relation>  
    <has-relation relation="HouseRegPlanID" size="10"> 
      <label language="zh_CN">HouseRegPlanID</label> 
    </has-relation>  
    <has-relation relation="HouseRegOblID" size="10"> 
      <label language="zh_CN">HouseRegOblID</label> 
    </has-relation>  
    <has-relation relation="Status" size="50"/>  
    <has-relation relation="ObjectionFlag"> 
      <label language="zh_CN">ObjectionFlag</label> 
    </has-relation>  
    <has-relation relation="YgspfygFlag" data-type="Decimal" size="1"> 
      <label language="zh_CN">YgspfygFlag</label> 
    </has-relation>  
    <has-relation relation="YsgpfdyqygFlag" data-type="Decimal" size="1"> 
      <label language="zh_CN">YsgpfdyqygFlag</label> 
    </has-relation>  
    <has-relation relation="FdcdyqygFlag" data-type="Decimal" size="1"> 
      <label language="zh_CN">FdcdyqygFlag</label> 
    </has-relation>  
    <has-relation relation="FdcqzyygFlag" data-type="Decimal" size="1"> 
      <label language="zh_CN">FdcqzyygFlag</label> 
    </has-relation>  
    <has-relation relation="LockFlag"> 
      <label language="zh_CN">LockFlag</label> 
    </has-relation>  
    <has-relation relation="zbywbz"> 
      <label language="zh_CN">zbywbz</label> 
    </has-relation>  
    <has-relation relation="zbywlx" size="100"> 
      <label language="zh_CN">zbywlx</label> 
    </has-relation>  
    <has-relation relation="mmhtzt"> 
      <label language="zh_CN">mmhtzt</label> 
    </has-relation>  
    <has-relation relation="ycfflag"> 
      <label language="zh_CN">ycfflag</label> 
    </has-relation>  
    <has-relation relation="ServitudeFlag" required="true"> 
      <label language="zh_CN">ServitudeFlag</label> 
    </has-relation>  
    <has-relation relation="st_flag" required="true"/>  
    <has-relation relation="IsCz" data-type="Decimal" size="1"> 
      <label language="zh_CN">IsCz</label> 
    </has-relation>  
    <label language="zh_CN">房屋登记簿主表</label>  
    <has-relation relation="RegisterNo" size="50"/>  
    <has-relation relation="LoginUnitID"/> 
  </concept>  
  <concept name="T_HouseRegMortgage" default-value-expr="guid()" keys="AutoID"> 
    <has-relation relation="RegisterNo" size="30" required="true"/>  
    <has-relation relation="Qlr" size="100"> 
      <label language="zh_CN">Qlr</label> 
    </has-relation>  
    <has-relation relation="QlrCardType" size="20"> 
      <label language="zh_CN">QlrCardType</label> 
    </has-relation>  
    <has-relation relation="QlrCardID" size="40"> 
      <label language="zh_CN">QlrCardID</label> 
    </has-relation>  
    <has-relation relation="Obligee" size="200"/>  
    <has-relation relation="ObligeeCardID" size="300"> 
      <label language="zh_CN">ObligeeCardID</label> 
    </has-relation>  
    <has-relation relation="DkMoney" size="18" scale="2"> 
      <label language="zh_CN">DkMoney</label> 
    </has-relation>  
    <has-relation relation="DroitRange" size="50"> 
      <label language="zh_CN">DroitRange</label> 
    </has-relation>  
    <has-relation relation="TimeLimit" size="30"> 
      <label language="zh_CN">TimeLimit</label> 
    </has-relation>  
    <has-relation relation="BookID" size="50"/>  
    <has-relation relation="PledgeOrder" size="10"> 
      <label language="zh_CN">PledgeOrder</label> 
    </has-relation>  
    <has-relation relation="RegDate"/>  
    <has-relation relation="VerifyMan" size="10"/>  
    <has-relation relation="RegMan" size="10"/>  
    <has-relation relation="CaptureID" size="20"/>  
    <has-relation relation="CaptureFID" size="50"/>  
    <has-relation relation="CreateDate"/>  
    <has-relation relation="RegDate3"> 
      <label language="zh_CN">RegDate3</label> 
    </has-relation>  
    <has-relation relation="VerifyMan3" size="10"> 
      <label language="zh_CN">VerifyMan3</label> 
    </has-relation>  
    <has-relation relation="RegMan3" size="10"> 
      <label language="zh_CN">RegMan3</label> 
    </has-relation>  
    <has-relation relation="CaptureID3" size="20"> 
      <label language="zh_CN">CaptureID3</label> 
    </has-relation>  
    <has-relation relation="CaptureFID3" size="50"> 
      <label language="zh_CN">CaptureFID3</label> 
    </has-relation>  
    <has-relation relation="CreateDate3"> 
      <label language="zh_CN">CreateDate3</label> 
    </has-relation>  
    <has-relation relation="Remark" size="2000"/>  
    <has-relation relation="LoginUnitID" required="true"/>  
    <has-relation relation="Status" size="10"/>  
    <has-relation relation="FNo" size="50"/>  
    <has-relation relation="st_flag" required="true"/>  
    <has-relation relation="PledgeExplain"> 
      <label language="zh_CN">PledgeExplain</label> 
    </has-relation>  
    <label language="zh_CN">房屋按揭归</label>  
    <has-relation relation="AutoID" size="18"/> 
  </concept>  
  <concept name="T_HouseRegPledgeAbuilding" default-value-expr="guid()" keys="AutoID"> 
    <has-relation relation="RegisterNo" size="50" required="true"/>  
    <has-relation relation="Qlr" size="100"> 
      <label language="zh_CN">抵押权人</label> 
    </has-relation>  
    <has-relation relation="QlrCardType" size="20"> 
      <label language="zh_CN">抵押权人证件类型</label> 
    </has-relation>  
    <has-relation relation="QlrCardID" size="40"> 
      <label language="zh_CN">抵押权人证件号码</label> 
    </has-relation>  
    <has-relation relation="Obligee" size="200"/>  
    <has-relation relation="ObligeeCardID" size="300"> 
      <label language="zh_CN">抵押人证件号码</label> 
    </has-relation>  
    <has-relation relation="HouseRepose" size="200"/>  
    <has-relation relation="DkMoney" size="18" scale="2"> 
      <label language="zh_CN">贷款金额</label> 
    </has-relation>  
    <has-relation relation="DroitRange" size="50"> 
      <label language="zh_CN">担保范围</label> 
    </has-relation>  
    <has-relation relation="TimeLimit" size="30"> 
      <label language="zh_CN">债务履行期限</label> 
    </has-relation>  
    <has-relation relation="BookID" size="20"/>  
    <has-relation relation="PledgeOrder" size="10"> 
      <label language="zh_CN">抵押顺位</label> 
    </has-relation>  
    <has-relation relation="RegDate"/>  
    <has-relation relation="VerifyMan" size="10"/>  
    <has-relation relation="RegMan" size="10"/>  
    <has-relation relation="CaptureID" size="20"/>  
    <has-relation relation="CaptureFID" size="50"/>  
    <has-relation relation="CreateDate"/>  
    <has-relation relation="FactAndMoney" size="100"> 
      <label language="zh_CN">最高债权确定事实和数额</label> 
    </has-relation>  
    <has-relation relation="RegDate2"> 
      <label language="zh_CN">最高额抵押登记日期</label> 
    </has-relation>  
    <has-relation relation="VerifyMan2" size="10"> 
      <label language="zh_CN">VerifyMan2</label> 
    </has-relation>  
    <has-relation relation="RegMan2" size="10"> 
      <label language="zh_CN">RegMan2</label> 
    </has-relation>  
    <has-relation relation="CaptureID2" size="20"> 
      <label language="zh_CN">最高额抵押收件号</label> 
    </has-relation>  
    <has-relation relation="RegDate3"> 
      <label language="zh_CN">在建工程注销抵押登记日期</label> 
    </has-relation>  
    <has-relation relation="VerifyMan3" size="10"> 
      <label language="zh_CN">VerifyMan3</label> 
    </has-relation>  
    <has-relation relation="RegMan3" size="10"> 
      <label language="zh_CN">RegMan3</label> 
    </has-relation>  
    <has-relation relation="CaptureID3" size="20"> 
      <label language="zh_CN">在建工程注销收件号</label> 
    </has-relation>  
    <has-relation relation="CaptureFID3" size="50"> 
      <label language="zh_CN">CaptureFID3</label> 
    </has-relation>  
    <has-relation relation="CreateDate3"> 
      <label language="zh_CN">CreateDate3</label> 
    </has-relation>  
    <has-relation relation="Remark" size="4000"/>  
    <has-relation relation="LoginUnitID" required="true"/>  
    <has-relation relation="Status" size="10"/>  
    <has-relation relation="FNo" size="50"/>  
    <has-relation relation="st_flag" required="true"/>  
    <has-relation relation="DetailFid" size="50"/>  
    <has-relation relation="PledgeExplain"> 
      <label language="zh_CN">PledgeExplain</label> 
    </has-relation>  
    <label language="zh_CN">在建工程抵押</label>  
    <has-relation relation="AutoID" size="18"/> 
  </concept>  
  <concept name="T_HouseRegSealup" default-value-expr="guid()" keys="AutoID"> 
    <has-relation relation="RegisterNo" size="30"/>  
    <has-relation relation="CaptureID" size="30"/>  
    <has-relation relation="CaptureFID" size="50"/>  
    <has-relation relation="Lb2" size="20"> 
      <label language="zh_CN">查封类别</label> 
    </has-relation>  
    <has-relation relation="ZxDepartment" size="100"> 
      <label language="zh_CN">查封机关</label> 
    </has-relation>  
    <has-relation relation="Cfsfzl" size="200"> 
      <label language="zh_CN">查封文件</label> 
    </has-relation>  
    <has-relation relation="LicNo" size="50"> 
      <label language="zh_CN">查封文号</label> 
    </has-relation>  
    <has-relation relation="CfDate"> 
      <label language="zh_CN">查封日期</label> 
    </has-relation>  
    <has-relation relation="Range" size="100"> 
      <label language="zh_CN">查封范围</label> 
    </has-relation>  
    <has-relation relation="CfLimit"> 
      <label language="zh_CN">查封期限</label> 
    </has-relation>  
    <has-relation relation="Cfsy" size="2000"> 
      <label language="zh_CN">查封事由</label> 
    </has-relation>  
    <has-relation relation="Cfcd" size="2000"> 
      <label language="zh_CN">裁定内容</label> 
    </has-relation>  
    <has-relation relation="YgName" size="120"> 
      <label language="zh_CN">原告名称</label> 
    </has-relation>  
    <has-relation relation="BgName" size="500"> 
      <label language="zh_CN">被告名称</label> 
    </has-relation>  
    <has-relation relation="RegDate"/>  
    <has-relation relation="VerifyMan" size="20"/>  
    <has-relation relation="RegMan" size="20"/>  
    <has-relation relation="CaptureID3" size="30"> 
      <label language="zh_CN">解封收件号</label> 
    </has-relation>  
    <has-relation relation="CaptureFID3" size="50"> 
      <label language="zh_CN">CaptureFID3</label> 
    </has-relation>  
    <has-relation relation="UnSealFile" size="100"> 
      <label language="zh_CN">解除查封文件</label> 
    </has-relation>  
    <has-relation relation="UnSealID" size="50"> 
      <label language="zh_CN">解除查封文号</label> 
    </has-relation>  
    <has-relation relation="JfDate"> 
      <label language="zh_CN">解封日期</label> 
    </has-relation>  
    <has-relation relation="JfBz" size="250"> 
      <label language="zh_CN">解封说明</label> 
    </has-relation>  
    <has-relation relation="RegDate3"> 
      <label language="zh_CN">解封登记日期</label> 
    </has-relation>  
    <has-relation relation="VerifyMan3" size="10"> 
      <label language="zh_CN">VerifyMan3</label> 
    </has-relation>  
    <has-relation relation="RegMan3" size="10"> 
      <label language="zh_CN">RegMan3</label> 
    </has-relation>  
    <has-relation relation="Remark" size="2000"/>  
    <has-relation relation="Status" size="10"/>  
    <has-relation relation="OldCfLimit" size="50"> 
      <label language="zh_CN">OldCfLimit</label> 
    </has-relation>  
    <has-relation relation="Operater" size="50"> 
      <label language="zh_CN">Operater</label> 
    </has-relation>  
    <has-relation relation="OperateDate" size="50"> 
      <label language="zh_CN">OperateDate</label> 
    </has-relation>  
    <has-relation relation="ContinueRemark" size="2000"> 
      <label language="zh_CN">ContinueRemark</label> 
    </has-relation>  
    <has-relation relation="cdswh" size="200"> 
      <label language="zh_CN">cdswh</label> 
    </has-relation>  
    <has-relation relation="st_flag" required="true"/>  
    <label language="zh_CN">查封信息</label>  
    <has-relation relation="AutoID" size="18"/>  
    <has-relation relation="LoginUnitID"/> 
  </concept>  
  <relation name="Cfcd" data-type="String"> 
    <label language="zh_CN">裁定内容</label> 
  </relation>  
  <relation name="BgName" data-type="String"> 
    <label language="zh_CN">被告名称</label> 
  </relation>  
  <relation name="OldCfLimit" data-type="String"> 
    <label language="zh_CN">OldCfLimit</label> 
  </relation>  
  <relation name="QlrCardID" data-type="String"> 
    <label language="zh_CN">抵押权人证件号码</label> 
  </relation>  
  <relation name="BuildingID" data-type="String"> 
    <label language="zh_CN">BuildingID</label> 
  </relation>  
  <relation name="Lb2" data-type="String"> 
    <label language="zh_CN">查封类别</label> 
  </relation>  
  <relation name="ZxDepartment" data-type="String"> 
    <label language="zh_CN">查封机关</label> 
  </relation>  
  <relation name="DroitRange" data-type="String"> 
    <label language="zh_CN">担保范围</label> 
  </relation>  
  <relation name="VerifyMan3" data-type="String"> 
    <label language="zh_CN">VerifyMan3</label> 
  </relation>  
  <relation name="VerifyMan2" data-type="String"> 
    <label language="zh_CN">VerifyMan2</label> 
  </relation>  
  <relation name="PhouseID" data-type="String"> 
    <label language="zh_CN">PhouseID</label> 
  </relation>  
  <relation name="QlrCardType" data-type="String"> 
    <label language="zh_CN">抵押权人证件类型</label> 
  </relation>  
  <relation name="RegDate3" data-type="DateTime"> 
    <label language="zh_CN">解封登记日期</label> 
  </relation>  
  <relation name="RegDate2" data-type="DateTime"> 
    <label language="zh_CN">最高额抵押登记日期</label> 
  </relation>  
  <relation name="Cfsfzl" data-type="String"> 
    <label language="zh_CN">查封文件</label> 
  </relation>  
  <relation name="HouseRegOblID" data-type="Decimal"> 
    <label language="zh_CN">HouseRegOblID</label> 
  </relation>  
  <relation name="ServitudeFlag" data-type="Integer"> 
    <label language="zh_CN">ServitudeFlag</label> 
  </relation>  
  <relation name="CaptureFID3" data-type="String"> 
    <label language="zh_CN">CaptureFID3</label> 
  </relation>  
  <relation name="TxqFlag" data-type="String"> 
    <label language="zh_CN">TxqFlag</label> 
  </relation>  
  <relation name="DkMoney" data-type="Decimal"> 
    <label language="zh_CN">贷款金额</label> 
  </relation>  
  <relation name="LicNo" data-type="String"> 
    <label language="zh_CN">查封文号</label> 
  </relation>  
  <relation name="YgName" data-type="String"> 
    <label language="zh_CN">原告名称</label> 
  </relation>  
  <relation name="OperateDate" data-type="String"> 
    <label language="zh_CN">OperateDate</label> 
  </relation>  
  <relation name="Qlr" data-type="String"> 
    <label language="zh_CN">抵押权人</label> 
  </relation>  
  <relation name="ObligeeCardID" data-type="String"> 
    <label language="zh_CN">抵押人证件号码</label> 
  </relation>  
  <relation name="Cfsy" data-type="String"> 
    <label language="zh_CN">查封事由</label> 
  </relation>  
  <relation name="LockFlag" data-type="Integer"> 
    <label language="zh_CN">LockFlag</label> 
  </relation>  
  <relation name="FdcdyqygFlag"> 
    <label language="zh_CN">FdcdyqygFlag</label> 
  </relation>  
  <relation name="CaptureID3" data-type="String"> 
    <label language="zh_CN">解封收件号</label> 
  </relation>  
  <relation name="CaptureID2" data-type="String"> 
    <label language="zh_CN">最高额抵押收件号</label> 
  </relation>  
  <relation name="Operater" data-type="String"> 
    <label language="zh_CN">Operater</label> 
  </relation>  
  <relation name="PledgeExplain" data-type="Text"> 
    <label language="zh_CN">PledgeExplain</label> 
  </relation>  
  <relation name="HouseRegPlanID" data-type="Decimal"> 
    <label language="zh_CN">HouseRegPlanID</label> 
  </relation>  
  <relation name="PledgeOrder" data-type="String"> 
    <label language="zh_CN">抵押顺位</label> 
  </relation>  
  <relation name="CfLimit" data-type="DateTime"> 
    <label language="zh_CN">查封期限</label> 
  </relation>  
  <relation name="JfBz" data-type="String"> 
    <label language="zh_CN">解封说明</label> 
  </relation>  
  <relation name="ZxFlag" data-type="String"> 
    <label language="zh_CN">ZxFlag</label> 
  </relation>  
  <relation name="UnSealFile" data-type="String"> 
    <label language="zh_CN">解除查封文件</label> 
  </relation>  
  <relation name="ycfflag" data-type="Integer"> 
    <label language="zh_CN">ycfflag</label> 
  </relation>  
  <relation name="ObjectionFlag" data-type="Integer"> 
    <label language="zh_CN">ObjectionFlag</label> 
  </relation>  
  <relation name="FactAndMoney" data-type="String"> 
    <label language="zh_CN">最高债权确定事实和数额</label> 
  </relation>  
  <relation name="cdswh" data-type="String"> 
    <label language="zh_CN">cdswh</label> 
  </relation>  
  <relation name="IsCz"> 
    <label language="zh_CN">IsCz</label> 
  </relation>  
  <relation name="zbywbz" data-type="Integer"> 
    <label language="zh_CN">zbywbz</label> 
  </relation>  
  <relation name="CfDate" data-type="DateTime"> 
    <label language="zh_CN">查封日期</label> 
  </relation>  
  <relation name="YsgpfdyqygFlag"> 
    <label language="zh_CN">YsgpfdyqygFlag</label> 
  </relation>  
  <relation name="ContinueRemark" data-type="String"> 
    <label language="zh_CN">ContinueRemark</label> 
  </relation>  
  <relation name="Range" data-type="String"> 
    <label language="zh_CN">查封范围</label> 
  </relation>  
  <relation name="HouseRegBaseID" data-type="Decimal"> 
    <label language="zh_CN">HouseRegBaseID</label> 
  </relation>  
  <relation name="UnSealID" data-type="String"> 
    <label language="zh_CN">解除查封文号</label> 
  </relation>  
  <relation name="zbywlx" data-type="String"> 
    <label language="zh_CN">zbywlx</label> 
  </relation>  
  <relation name="TimeLimit" data-type="String"> 
    <label language="zh_CN">债务履行期限</label> 
  </relation>  
  <relation name="JfDate" data-type="DateTime"> 
    <label language="zh_CN">解封日期</label> 
  </relation>  
  <relation name="YgspfygFlag"> 
    <label language="zh_CN">YgspfygFlag</label> 
  </relation>  
  <relation name="FdcqzyygFlag"> 
    <label language="zh_CN">FdcqzyygFlag</label> 
  </relation>  
  <relation name="RegMan3" data-type="String"> 
    <label language="zh_CN">RegMan3</label> 
  </relation>  
  <relation name="CreateDate3" data-type="DateTime"> 
    <label language="zh_CN">CreateDate3</label> 
  </relation>  
  <relation name="mmhtzt" data-type="Integer"> 
    <label language="zh_CN">mmhtzt</label> 
  </relation>  
  <relation name="CfFlag" data-type="Integer"> 
    <label language="zh_CN">CfFlag</label> 
  </relation>  
  <relation name="RegMan2" data-type="String"> 
    <label language="zh_CN">RegMan2</label> 
  </relation>  
  <relation name="HouseID" data-type="String"> 
    <label language="zh_CN">HouseID</label> 
  </relation>  
  <concept name="T_HouseRegCaution" default-value-expr="guid()" keys="AutoID"> 
    <has-relation relation="RegisterNo" size="30" required="true"/>  
    <has-relation relation="CaptureID" size="50"/>  
    <has-relation relation="CaptureFID" size="50"/>  
    <has-relation relation="CreateDate"/>  
    <has-relation relation="CautionType" size="100"> 
      <label language="zh_CN">预告类型</label> 
    </has-relation>  
    <has-relation relation="Obligee" size="100"/>  
    <has-relation relation="ObligeeCardID" size="300"/>  
    <has-relation relation="Obligor" size="100"> 
      <label language="zh_CN">产权人</label> 
    </has-relation>  
    <has-relation relation="ObligorCardID" size="300"> 
      <label language="zh_CN">产权人证件号码</label> 
    </has-relation>  
    <has-relation relation="AssureWorth" size="18" scale="2"> 
      <label language="zh_CN">权利价值</label> 
    </has-relation>  
    <has-relation relation="CautionID" size="300"> 
      <label language="zh_CN">预告证明号</label> 
    </has-relation>  
    <has-relation relation="RegDate"/>  
    <has-relation relation="VerifyMan" size="50"/>  
    <has-relation relation="RegMan" size="50"/>  
    <has-relation relation="RegDate3"/>  
    <has-relation relation="VerifyMan3" size="10"/>  
    <has-relation relation="RegMan3" size="10"/>  
    <has-relation relation="CaptureID3" size="20"/>  
    <has-relation relation="CaptureFID3" size="50"/>  
    <has-relation relation="CreateDate3"/>  
    <has-relation relation="Remark" size="4000"/>  
    <has-relation relation="LoginUnitID" required="true"/>  
    <has-relation relation="Status" size="10"/>  
    <has-relation relation="FNo" size="50"/>  
    <has-relation relation="st_flag" required="true"/>  
    <has-relation relation="st_flag2" required="true"/>  
    <has-relation relation="st_flag3" required="true"> 
      <label language="zh_CN">st_flag3</label> 
    </has-relation>  
    <has-relation relation="DetailfID" size="50"/>  
    <has-relation relation="Commshare" size="50"/>  
    <label language="zh_CN">预告登记</label>  
    <has-relation relation="AutoID" size="18"/> 
  </concept>  
  <relation name="CautionID" data-type="String"> 
    <label language="zh_CN">预告证明号</label> 
  </relation>  
  <relation name="AssureWorth" data-type="Decimal"> 
    <label language="zh_CN">权利价值</label> 
  </relation>  
  <relation name="ObligorCardID" data-type="String"> 
    <label language="zh_CN">产权人证件号码</label> 
  </relation>  
  <relation name="Obligor" data-type="String"> 
    <label language="zh_CN">产权人</label> 
  </relation>  
  <relation name="st_flag3" data-type="Integer"> 
    <label language="zh_CN">st_flag3</label> 
  </relation>  
  <relation name="CautionType" data-type="String"> 
    <label language="zh_CN">预告类型</label> 
  </relation>  
  <relation name="DetailfID" data-type="String"> 
    <label language="zh_CN">DetailfID</label> 
  </relation>  
  <concept name="T_HouseRegObjection" default-value-expr="guid()" keys="AutoID"> 
    <has-relation relation="RegisterNo" size="30" required="true"/>  
    <has-relation relation="CaptureID" size="20"/>  
    <has-relation relation="CaptureFID" size="50"/>  
    <has-relation relation="CreateDate"/>  
    <has-relation relation="Proposer" size="100"> 
      <label language="zh_CN">异议申请人</label> 
    </has-relation>  
    <has-relation relation="Content" size="2000"> 
      <label language="zh_CN">异议内容</label> 
    </has-relation>  
    <has-relation relation="RegDate"/>  
    <has-relation relation="TimeOut"> 
      <label language="zh_CN">失效期</label> 
    </has-relation>  
    <has-relation relation="VerifyMan" size="10"/>  
    <has-relation relation="RegMan" size="10"/>  
    <has-relation relation="RegDate3"/>  
    <has-relation relation="VerifyMan3" size="10"/>  
    <has-relation relation="RegMan3" size="10"/>  
    <has-relation relation="CaptureID3" size="20"/>  
    <has-relation relation="CaptureFID3" size="50"/>  
    <has-relation relation="CreateDate3"/>  
    <has-relation relation="Remark" size="4000"/>  
    <has-relation relation="LoginUnitID" required="true"/>  
    <has-relation relation="Status" size="10"/>  
    <has-relation relation="BookId" size="30"/>  
    <label language="zh_CN">异议登记</label>  
    <has-relation relation="AutoID" size="18"/> 
  </concept>  
  <relation name="BookId" data-type="String"> 
    <label language="zh_CN">BookId</label> 
  </relation>  
  <relation name="TimeOut" data-type="DateTime"> 
    <label language="zh_CN">失效期</label> 
  </relation>  
  <relation name="Content" data-type="String"> 
    <label language="zh_CN">异议内容</label> 
  </relation>  
  <relation name="Proposer" data-type="String"> 
    <label language="zh_CN">异议申请人</label> 
  </relation>  
  <concept name="T_HouseRegPledge" default-value-expr="guid()" keys="AutoID">
    <has-relation relation="RegisterNo" size="30" required="true"/>  
    <has-relation relation="PledgeType" size="20">
      <label language="zh_CN">抵押类型(一般/最高)</label> 
    </has-relation>  
    <has-relation relation="Qlr" size="200"/>  
    <has-relation relation="QlrCardType" size="20"/>  
    <has-relation relation="QlrCardID" size="100"/>  
    <has-relation relation="Obligee" size="200"/>  
    <has-relation relation="ObligeeCardID" size="300"/>  
    <has-relation relation="DkMoney" size="18" scale="2"/>  
    <has-relation relation="DroitRange" size="50"/>  
    <has-relation relation="TimeLimit" size="30"/>  
    <has-relation relation="BookID" size="50"/>  
    <has-relation relation="PledgeOrder" size="10"/>  
    <has-relation relation="RegDate"/>  
    <has-relation relation="VerifyMan" size="10"/>  
    <has-relation relation="RegMan" size="10"/>  
    <has-relation relation="CaptureID" size="20"/>  
    <has-relation relation="CaptureFID" size="50"/>  
    <has-relation relation="CreateDate"/>  
    <has-relation relation="FactAndMoney" size="100"/>  
    <has-relation relation="RegDate2"/>  
    <has-relation relation="VerifyMan2" size="10"/>  
    <has-relation relation="RegMan2" size="10"/>  
    <has-relation relation="CaptureID2" size="20"/>  
    <has-relation relation="RegDate3"/>  
    <has-relation relation="VerifyMan3" size="10"/>  
    <has-relation relation="RegMan3" size="10"/>  
    <has-relation relation="CaptureID3" size="20"/>  
    <has-relation relation="CaptureFID3" size="50"/>  
    <has-relation relation="CreateDate3"/>  
    <has-relation relation="Remark" size="4000"/>  
    <has-relation relation="Status" size="10"/>  
    <has-relation relation="FNo" size="50"/>  
    <has-relation relation="TXQZH" size="100">
      <label language="zh_CN">TXQZH</label> 
    </has-relation>  
    <has-relation relation="DetailFid" size="50"/>  
    <has-relation relation="YY_ZSBH" size="100">
      <label language="zh_CN">YY_ZSBH</label> 
    </has-relation>  
    <has-relation relation="YY_TXQZH" size="100">
      <label language="zh_CN">YY_TXQZH</label> 
    </has-relation>  
    <has-relation relation="DYLX">
      <label language="zh_CN">DYLX</label> 
    </has-relation>  
    <has-relation relation="QDJE" size="18" scale="2">
      <label language="zh_CN">QDJE</label> 
    </has-relation>  
    <has-relation relation="jkr" size="500">
      <label language="zh_CN">jkr</label> 
    </has-relation>  
    <has-relation relation="commObligee" size="200">
      <label language="zh_CN">commObligee</label> 
    </has-relation>  
    <has-relation relation="commJkr" size="200">
      <label language="zh_CN">commJkr</label> 
    </has-relation>  
    <has-relation relation="st_flag" required="true"/>  
    <has-relation relation="st_flag2" required="true"/>  
    <has-relation relation="st_flag3" required="true"/>  
    <has-relation relation="PledgeExplain"/>  
    <label language="zh_CN">抵押登记</label> 
  <has-relation relation="AutoID" size="18"></has-relation>
<has-relation relation="LoginUnitID"></has-relation>
</concept>  
  <relation name="YY_TXQZH" data-type="String">
    <label language="zh_CN">YY_TXQZH</label> 
  </relation>  
  <relation name="commJkr" data-type="String">
    <label language="zh_CN">commJkr</label> 
  </relation>  
  <relation name="commObligee" data-type="String">
    <label language="zh_CN">commObligee</label> 
  </relation>  
  <relation name="PledgeType" data-type="String">
    <label language="zh_CN">抵押类型(一般/最高)</label> 
  </relation>  
  <relation name="jkr" data-type="String">
    <label language="zh_CN">jkr</label> 
  </relation>  
  <relation name="YY_ZSBH" data-type="String">
    <label language="zh_CN">YY_ZSBH</label> 
  </relation>  
  <relation name="TXQZH" data-type="String">
    <label language="zh_CN">TXQZH</label> 
  </relation>  
  <relation name="DYLX" data-type="Integer">
    <label language="zh_CN">DYLX</label> 
  </relation>  
  <relation name="QDJE" data-type="Decimal">
    <label language="zh_CN">QDJE</label> 
  </relation> 
</model>
