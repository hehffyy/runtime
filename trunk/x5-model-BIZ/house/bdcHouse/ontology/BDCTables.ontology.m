<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <concept name="H_House" default-value-expr="guid()" keys="HouseID"> 
    <has-relation relation="version" default-value-expr="0"> 
      <label language="zh_CN">版本</label> 
    </has-relation>  
    <label language="zh_CN">房</label>  
    <has-relation relation="HouseID" data-type="String" size="50"> 
      <label language="zh_CN">HouseID</label> 
    </has-relation>  
    <has-relation relation="BuildingID" data-type="String" size="50"> 
      <label language="zh_CN">BuildingID</label> 
    </has-relation>  
    <has-relation relation="SumBuildArea" data-type="Decimal" size="18" scale="2"/>  
    <has-relation relation="UseArea" data-type="Decimal" size="18" scale="2"/>  
    <has-relation relation="HouseBookID" data-type="String" size="100"/>  
    <has-relation relation="Ceng" data-type="String" size="20"/>  
    <has-relation relation="HouseRepose" data-type="String" size="600"/>  
    <has-relation relation="RoomNo" data-type="String" size="100"/>  
    <has-relation relation="ZxFlag" data-type="String" size="20"/>  
    <has-relation relation="TxqFlag" data-type="String" size="50"/>  
    <has-relation relation="CfFlag" data-type="Integer"/>  
    <has-relation relation="DISTRICT" data-type="String" size="20"/>  
    <has-relation relation="HouseUse" data-type="String" size="50"/>  
    <has-relation relation="HouseFrame" data-type="String" size="50"/>  
    <has-relation relation="YCJZMJ" data-type="Decimal" size="18" scale="2"/>  
    <has-relation relation="YCSYMJ" data-type="Decimal" size="18" scale="2"/>  
    <has-relation relation="DongNo" data-type="String" size="100"/>  
    <has-relation relation="fDRZT" data-type="String" size="20"/>  
    <has-relation relation="FWHXJG" size="50"/>  
    <has-relation relation="LoginUnitID" size="50"/> 
  </concept>  
  <relation name="HouseID" data-type="String"> 
    <label language="zh_CN">HouseId</label> 
  </relation>  
  <relation name="BuildingID" data-type="String"> 
    <label language="zh_CN">BuildingId</label> 
  </relation>  
  <relation name="SumBuildArea" data-type="Decimal"> 
    <label language="zh_CN">建筑面积</label> 
  </relation>  
  <relation name="UseArea" data-type="Decimal"> 
    <label language="zh_CN">使用面积</label> 
  </relation>  
  <relation name="HouseBookID" data-type="String"> 
    <label language="zh_CN">房产证号</label> 
  </relation>  
  <relation name="Ceng" data-type="String"> 
    <label language="zh_CN">所在层</label> 
  </relation>  
  <relation name="HouseRepose" data-type="String"> 
    <label language="zh_CN">房屋坐落</label> 
  </relation>  
  <relation name="RoomNo" data-type="String"> 
    <label language="zh_CN">房号</label> 
  </relation>  
  <relation name="ZxFlag" data-type="String"> 
    <label language="zh_CN">注销标识</label> 
  </relation>  
  <relation name="TxqFlag" data-type="String"> 
    <label language="zh_CN">他项权标志</label> 
  </relation>  
  <relation name="CfFlag" data-type="Integer"> 
    <label language="zh_CN">查封标志</label> 
  </relation>  
  <relation name="DISTRICT" data-type="String"> 
    <label language="zh_CN">所属区域</label> 
  </relation>  
  <relation name="dbh" data-type="String"> 
    <label language="zh_CN">栋编号</label> 
  </relation>  
  <concept name="H_Building" default-value-expr="guid()" keys="BuildingID"> 
    <has-relation relation="version" default-value-expr="0"> 
      <label language="zh_CN">版本</label> 
    </has-relation>  
    <label language="zh_CN">栋</label>  
    <has-relation relation="BuildingID" size="36"/>  
    <has-relation relation="dbh" size="36"/>  
    <has-relation relation="ZHNo" data-type="String" size="150"/>  
    <has-relation relation="ItemNo" data-type="String" size="150"/>  
    <has-relation relation="CompleteDate" data-type="Date"/>  
    <has-relation relation="CompleteDateText" data-type="String" size="50"/>  
    <has-relation relation="HouseUse" size="50"/>  
    <has-relation relation="HouseFrame" size="50"/>  
    <has-relation relation="BaseBuildArea" data-type="Decimal" size="18" scale="2"/>  
    <has-relation relation="Floor" data-type="Integer"/>  
    <has-relation relation="SumBuildArea" size="18" scale="2"> 
      <label language="zh_CN">总建筑面积</label> 
    </has-relation>  
    <has-relation relation="OwnerBuildArea" data-type="Decimal" size="18" scale="2"/>  
    <has-relation relation="CorpName" data-type="String" size="150"/>  
    <has-relation relation="DJH" data-type="String" size="50"/>  
    <has-relation relation="LandNo" data-type="String" size="100"/>  
    <has-relation relation="ZQNo" data-type="String" size="100"/>  
    <has-relation relation="BuildingNo" data-type="String" size="50"/>  
    <has-relation relation="DISTRICT" size="20"/>  
    <has-relation relation="LoginUnitID" size="50"/> 
  </concept>  
  <relation name="HouseUse" data-type="String"> 
    <label language="zh_CN">规划用途</label> 
  </relation>  
  <relation name="HouseFrame" data-type="String"> 
    <label language="zh_CN">建筑结构</label> 
  </relation>  
  <relation name="YCJZMJ" data-type="Decimal"> 
    <label language="zh_CN">预测建筑面积</label> 
  </relation>  
  <relation name="YCSYMJ" data-type="Decimal"> 
    <label language="zh_CN">预测使用面积</label> 
  </relation>  
  <relation name="DongNo" data-type="String"> 
    <label language="zh_CN">栋号</label> 
  </relation>  
  <relation name="QX" data-type="String"> 
    <label language="zh_CN">区县</label> 
  </relation>  
  <relation name="myfid" data-type="String"> 
    <label language="zh_CN">myfid</label> 
  </relation>  
  <relation name="YCSumBuildArea" data-type="Decimal"> 
    <label language="zh_CN">预测建筑面积</label> 
  </relation>  
  <relation name="YCUseArea" data-type="Decimal"> 
    <label language="zh_CN">预测使用面积</label> 
  </relation>  
  <relation name="HouseNo" data-type="String"> 
    <label language="zh_CN">房号</label> 
  </relation>  
  <relation name="FWHXJG" data-type="String"> 
    <label language="zh_CN">房屋户型结构</label> 
  </relation>  
  <relation name="Status" data-type="String"> 
    <label language="zh_CN">状态</label> 
  </relation>  
  <relation name="fDRZT" data-type="String"> 
    <label language="zh_CN">导入状态</label> 
  </relation>  
  <relation name="ZHNo" data-type="String"> 
    <label language="zh_CN">幢牌号</label> 
  </relation>  
  <relation name="ItemNo" data-type="String"> 
    <label language="zh_CN">项目编号</label> 
  </relation>  
  <relation name="CompleteDate" data-type="DateTime"> 
    <label language="zh_CN">竣工时间</label> 
  </relation>  
  <relation name="CompleteDateText" data-type="String"> 
    <label language="zh_CN">竣工时间串</label> 
  </relation>  
  <relation name="BaseBuildArea" data-type="Decimal"> 
    <label language="zh_CN">建基面积</label> 
  </relation>  
  <relation name="Floor" data-type="Integer"> 
    <label language="zh_CN">总层数</label> 
  </relation>  
  <relation name="OwnerBuildArea" data-type="Decimal"> 
    <label language="zh_CN">专用建筑面积</label> 
  </relation>  
  <relation name="CorpName" data-type="String"> 
    <label language="zh_CN">开发企业名称</label> 
  </relation>  
  <relation name="DJH" data-type="String"> 
    <label language="zh_CN">开发企业登记号</label> 
  </relation>  
  <relation name="LandNo" data-type="String"> 
    <label language="zh_CN">地号</label> 
  </relation>  
  <relation name="ZQNo" data-type="String"> 
    <label language="zh_CN">图号</label> 
  </relation>  
  <relation name="ItemName" data-type="String"> 
    <label language="zh_CN">小区名称</label> 
  </relation>  
  <relation name="DongName" data-type="String"> 
    <label language="zh_CN">栋名</label> 
  </relation>  
  <relation name="BuildingNo" data-type="String"> 
    <label language="zh_CN">楼盘编号</label> 
  </relation>  
  <relation name="UseBuildArea" data-type="Decimal"> 
    <label language="zh_CN">占有建筑面积</label> 
  </relation>  
  <relation name="autoid" data-type="Decimal"> 
    <label language="zh_CN">autoid</label> 
  </relation>  
  <concept name="HousePresellItem" default-value-expr="guid()" keys="DJH:presellname:LoginUnitID"> 
    <has-relation relation="XMMC" size="200"> 
      <label language="zh_CN">XMMC</label> 
    </has-relation>  
    <has-relation relation="ItemRepose" size="499"> 
      <label language="zh_CN">ItemRepose</label> 
    </has-relation>  
    <has-relation relation="ZCount" size="80"> 
      <label language="zh_CN">ZCount</label> 
    </has-relation>  
    <has-relation relation="LandBookID" size="200"> 
      <label language="zh_CN">LandBookID</label> 
    </has-relation>  
    <has-relation relation="LandArea" size="18" scale="4"> 
      <label language="zh_CN">LandArea</label> 
    </has-relation>  
    <has-relation relation="ProgramBookID" size="200"> 
      <label language="zh_CN">ProgramBookID</label> 
    </has-relation>  
    <has-relation relation="ProgramArea" size="18" scale="4"> 
      <label language="zh_CN">ProgramArea</label> 
    </has-relation>  
    <has-relation relation="BuildBookID" size="200"> 
      <label language="zh_CN">BuildBookID</label> 
    </has-relation>  
    <has-relation relation="BuildArea" size="18" scale="4"> 
      <label language="zh_CN">BuildArea</label> 
    </has-relation>  
    <has-relation relation="FQ" size="150"> 
      <label language="zh_CN">FQ</label> 
    </has-relation>  
    <has-relation relation="UseData" size="200"> 
      <label language="zh_CN">UseData</label> 
    </has-relation>  
    <has-relation relation="LandUse" size="30"> 
      <label language="zh_CN">LandUse</label> 
    </has-relation>  
    <has-relation relation="PresellArea"> 
      <label language="zh_CN">PresellArea</label> 
    </has-relation>  
    <has-relation relation="PublicArea"> 
      <label language="zh_CN">PublicArea</label> 
    </has-relation>  
    <has-relation relation="SelfArea"> 
      <label language="zh_CN">SelfArea</label> 
    </has-relation>  
    <has-relation relation="WYArea"> 
      <label language="zh_CN">WYArea</label> 
    </has-relation>  
    <has-relation relation="Autoid" required="true" size="18"/>  
    <has-relation relation="LoginUnitID" data-type="String" size="50"/>  
    <has-relation relation="presellname" data-type="String" size="200"/>  
    <has-relation relation="DJH" size="50"/> 
  </concept>  
  <relation name="LandArea" data-type="Decimal"> 
    <label language="zh_CN">LandArea</label> 
  </relation>  
  <relation name="WYArea" data-type="Float"> 
    <label language="zh_CN">WYArea</label> 
  </relation>  
  <relation name="ProgramBookID" data-type="String"> 
    <label language="zh_CN">ProgramBookID</label> 
  </relation>  
  <relation name="UseData" data-type="String"> 
    <label language="zh_CN">UseData</label> 
  </relation>  
  <relation name="BuildArea" data-type="Decimal"> 
    <label language="zh_CN">BuildArea</label> 
  </relation>  
  <relation name="ZCount" data-type="String"> 
    <label language="zh_CN">ZCount</label> 
  </relation>  
  <relation name="PublicArea" data-type="Float"> 
    <label language="zh_CN">PublicArea</label> 
  </relation>  
  <relation name="PresellArea" data-type="Float"> 
    <label language="zh_CN">PresellArea</label> 
  </relation>  
  <relation name="XMMC" data-type="String"> 
    <label language="zh_CN">XMMC</label> 
  </relation>  
  <relation name="Autoid" data-type="Decimal"> 
    <label language="zh_CN">Autoid</label> 
  </relation>  
  <relation name="FQ" data-type="String"> 
    <label language="zh_CN">FQ</label> 
  </relation>  
  <relation name="SelfArea" data-type="Float"> 
    <label language="zh_CN">SelfArea</label> 
  </relation>  
  <relation name="ItemRepose" data-type="String"> 
    <label language="zh_CN">ItemRepose</label> 
  </relation>  
  <relation name="LandBookID" data-type="String"> 
    <label language="zh_CN">LandBookID</label> 
  </relation>  
  <relation name="ProgramArea" data-type="Decimal"> 
    <label language="zh_CN">ProgramArea</label> 
  </relation>  
  <relation name="BuildBookID" data-type="String"> 
    <label language="zh_CN">BuildBookID</label> 
  </relation>  
  <relation name="LandUse" data-type="String"> 
    <label language="zh_CN">LandUse</label> 
  </relation>  
  <relation name="LoginUnitID" data-type="String"> 
    <label language="zh_CN">LoginUnitID</label> 
  </relation>  
  <relation name="presellname" data-type="String"> 
    <label language="zh_CN">项目名称</label> 
  </relation>  
  <concept name="T_HouseTradeQs" default-value-expr="guid()" keys="fid:LoginUnitID"> 
    <has-relation relation="BuildingID" size="50"/>  
    <has-relation relation="HouseId" size="50"/>  
    <has-relation relation="Status" size="50"/>  
    <has-relation relation="BookStatus" size="50"> 
      <label language="zh_CN">BookStatus</label> 
    </has-relation>  
    <has-relation relation="fType" size="50"> 
      <label language="zh_CN">权属业务类型</label> 
    </has-relation>  
    <has-relation relation="lb2" size="50"> 
      <label language="zh_CN">lb2</label> 
    </has-relation>  
    <has-relation relation="type_no" size="50"> 
      <label language="zh_CN">type_no</label> 
    </has-relation>  
    <has-relation relation="fNo" size="50"> 
      <label language="zh_CN">登记字号</label> 
    </has-relation>  
    <has-relation relation="SZNo"> 
      <label language="zh_CN">缮证号</label> 
    </has-relation>  
    <has-relation relation="BookId" size="50"> 
      <label language="zh_CN">BookId</label> 
    </has-relation>  
    <has-relation relation="ArchiveID" size="50"> 
      <label language="zh_CN">档案号</label> 
    </has-relation>  
    <has-relation relation="CaptureId" size="30"> 
      <label language="zh_CN">产权收件号</label> 
    </has-relation>  
    <has-relation relation="CreateMan" size="20"> 
      <label language="zh_CN">收件人</label> 
    </has-relation>  
    <has-relation relation="CreateDate"> 
      <label language="zh_CN">收件日期</label> 
    </has-relation>  
    <has-relation relation="CorpAddress" size="250"> 
      <label language="zh_CN">公司地址</label> 
    </has-relation>  
    <has-relation relation="Obligee" size="150"> 
      <label language="zh_CN">权属人</label> 
    </has-relation>  
    <has-relation relation="HouseRepose" size="250"/>  
    <has-relation relation="DISTRICT" size="50"/>  
    <has-relation relation="Path" size="20"> 
      <label language="zh_CN">路段</label> 
    </has-relation>  
    <has-relation relation="PathNo" size="20"> 
      <label language="zh_CN">PathNo</label> 
    </has-relation>  
    <has-relation relation="HouseUse" size="30"/>  
    <has-relation relation="DroitKind" size="30"> 
      <label language="zh_CN">房屋性质</label> 
    </has-relation>  
    <has-relation relation="DroitSource" size="100"> 
      <label language="zh_CN">房屋取得方式</label> 
    </has-relation>  
    <has-relation relation="HouseFrame" size="50"/>  
    <has-relation relation="Floor"/>  
    <has-relation relation="CompleteDate"/>  
    <has-relation relation="BuildBaseArea" size="18" scale="2"> 
      <label language="zh_CN">建基面积</label> 
    </has-relation>  
    <has-relation relation="UseArea" size="18" scale="2"/>  
    <has-relation relation="NoUseArea" size="18" scale="2"> 
      <label language="zh_CN">非使用面积</label> 
    </has-relation>  
    <has-relation relation="SumBuildArea" size="18" scale="2"/>  
    <has-relation relation="BuildArea" size="18" scale="2"/>  
    <has-relation relation="TerraId" size="150"> 
      <label language="zh_CN">地号</label> 
    </has-relation>  
    <has-relation relation="LandUseSource" size="100"> 
      <label language="zh_CN">土地所有权取得方式</label> 
    </has-relation>  
    <has-relation relation="LandUseKind" size="50"> 
      <label language="zh_CN">土地性质</label> 
    </has-relation>  
    <has-relation relation="LandUse" size="100"/>  
    <has-relation relation="LandGrade" size="50"> 
      <label language="zh_CN">土地等级</label> 
    </has-relation>  
    <has-relation relation="UsufructKind" size="50"> 
      <label language="zh_CN">土地使用权类型</label> 
    </has-relation>  
    <has-relation relation="UsufructArea" size="18" scale="2"> 
      <label language="zh_CN">土地面积</label> 
    </has-relation>  
    <has-relation relation="EndDate" size="300"> 
      <label language="zh_CN">终止日期</label> 
    </has-relation>  
    <has-relation relation="SelfArea"/>  
    <has-relation relation="CommArea" size="18" scale="2"> 
      <label language="zh_CN">共有面积</label> 
    </has-relation>  
    <has-relation relation="UsufructId" size="200"> 
      <label language="zh_CN">土地证号</label> 
    </has-relation>  
    <has-relation relation="Department" size="100"> 
      <label language="zh_CN">填证机关</label> 
    </has-relation>  
    <has-relation relation="IsYFOk" data-type="Decimal" size="1"> 
      <label language="zh_CN">IsYFOk</label> 
    </has-relation>  
    <has-relation relation="IsEmptyHouseTrade" data-type="Decimal" size="1"> 
      <label language="zh_CN">IsEmptyHouseTrade</label> 
    </has-relation>  
    <has-relation relation="TradeFeeMode" size="10"> 
      <label language="zh_CN">计价方式</label> 
    </has-relation>  
    <has-relation relation="IsModifyTrade" data-type="Decimal" size="1"> 
      <label language="zh_CN">IsModifyTrade</label> 
    </has-relation>  
    <has-relation relation="TradePaymentMode" size="50"> 
      <label language="zh_CN">付款方式</label> 
    </has-relation>  
    <has-relation relation="CheckTotalPrice" size="18" scale="2"> 
      <label language="zh_CN">核定总价</label> 
    </has-relation>  
    <has-relation relation="CheckPrice" size="18" scale="2"> 
      <label language="zh_CN">核定单价</label> 
    </has-relation>  
    <has-relation relation="IsSignOk" data-type="Decimal" size="1"> 
      <label language="zh_CN">IsSignOk</label> 
    </has-relation>  
    <has-relation relation="Ideas"> 
      <label language="zh_CN">Ideas</label> 
    </has-relation>  
    <has-relation relation="Remark" size="2000"> 
      <label language="zh_CN">附记</label> 
    </has-relation>  
    <has-relation relation="IsPrint" data-type="Decimal" size="1"> 
      <label language="zh_CN">IsPrint</label> 
    </has-relation>  
    <has-relation relation="IsArchive" data-type="Decimal" size="1"> 
      <label language="zh_CN">已经收档</label> 
    </has-relation>  
    <has-relation relation="RegDate"> 
      <label language="zh_CN">受理日期</label> 
    </has-relation>  
    <has-relation relation="Collator" size="50"> 
      <label language="zh_CN">校对人</label> 
    </has-relation>  
    <has-relation relation="Geter" size="50"> 
      <label language="zh_CN">领证人</label> 
    </has-relation>  
    <has-relation relation="Producer" size="50"> 
      <label language="zh_CN">缮证人</label> 
    </has-relation>  
    <has-relation relation="Provider" size="50"> 
      <label language="zh_CN">发证人</label> 
    </has-relation>  
    <has-relation relation="GetDate"> 
      <label language="zh_CN">领证日期</label> 
    </has-relation>  
    <has-relation relation="IsGetFee" data-type="Decimal" size="1"> 
      <label language="zh_CN">已经收费</label> 
    </has-relation>  
    <has-relation relation="FeeList"> 
      <label language="zh_CN">FeeList</label> 
    </has-relation>  
    <has-relation relation="FeeJe" size="18" scale="2"> 
      <label language="zh_CN">费用</label> 
    </has-relation>  
    <has-relation relation="DueDate"> 
      <label language="zh_CN">DueDate</label> 
    </has-relation>  
    <has-relation relation="HouseAmount1"> 
      <label language="zh_CN">HouseAmount1</label> 
    </has-relation>  
    <has-relation relation="HouseAmount2"> 
      <label language="zh_CN">HouseAmount2</label> 
    </has-relation>  
    <has-relation relation="JzMam" size="50"> 
      <label language="zh_CN">JzMam</label> 
    </has-relation>  
    <has-relation relation="QyDate"> 
      <label language="zh_CN">QyDate</label> 
    </has-relation>  
    <has-relation relation="JzMemo" size="1000"> 
      <label language="zh_CN">JzMemo</label> 
    </has-relation>  
    <has-relation relation="hz_date"> 
      <label language="zh_CN">hz_date</label> 
    </has-relation>  
    <has-relation relation="fj" size="2000"> 
      <label language="zh_CN">fj</label> 
    </has-relation>  
    <has-relation relation="producerdate"> 
      <label language="zh_CN">缮证日期</label> 
    </has-relation>  
    <has-relation relation="tel" size="100"> 
      <label language="zh_CN">电话</label> 
    </has-relation>  
    <has-relation relation="cdIdea"> 
      <label language="zh_CN">cdIdea</label> 
    </has-relation>  
    <has-relation relation="bookname" size="20"> 
      <label language="zh_CN">证书名</label> 
    </has-relation>  
    <has-relation relation="email" size="50"> 
      <label language="zh_CN">邮箱地址</label> 
    </has-relation>  
    <has-relation relation="validdate"> 
      <label language="zh_CN">validdate</label> 
    </has-relation>  
    <has-relation relation="LawPersonNation" size="20"> 
      <label language="zh_CN">法人代表国籍</label> 
    </has-relation>  
    <has-relation relation="LawPersonSex" size="10"> 
      <label language="zh_CN">法人代表姓别</label> 
    </has-relation>  
    <has-relation relation="LawPerson" size="20"> 
      <label language="zh_CN">法人代表姓名</label> 
    </has-relation>  
    <has-relation relation="Corp" size="50"> 
      <label language="zh_CN">Corp</label> 
    </has-relation>  
    <has-relation relation="Nation" size="20"> 
      <label language="zh_CN">国籍</label> 
    </has-relation>  
    <has-relation relation="ObligeeSex" size="10"> 
      <label language="zh_CN">权属人性别</label> 
    </has-relation>  
    <has-relation relation="LawPersonCardId" size="18"> 
      <label language="zh_CN">法人代表身份证号</label> 
    </has-relation>  
    <has-relation relation="IsKb"> 
      <label language="zh_CN">IsKb</label> 
    </has-relation>  
    <has-relation relation="PresellBookId" size="400"> 
      <label language="zh_CN">PresellBookId</label> 
    </has-relation>  
    <has-relation relation="IsFinish" data-type="Decimal" size="1"> 
      <label language="zh_CN">IsFinish</label> 
    </has-relation>  
    <has-relation relation="zxId" size="50"> 
      <label language="zh_CN">zxId</label> 
    </has-relation>  
    <has-relation relation="DocDate"> 
      <label language="zh_CN">DocDate</label> 
    </has-relation>  
    <has-relation relation="ZNo" size="10"> 
      <label language="zh_CN">ZNo</label> 
    </has-relation>  
    <has-relation relation="ItemName" size="80"/>  
    <has-relation relation="DJH" size="20"/>  
    <has-relation relation="IsDeleted" required="true" data-type="Decimal" size="1"> 
      <label language="zh_CN">IsDeleted</label> 
    </has-relation>  
    <has-relation relation="ObligeeNation" size="50"> 
      <label language="zh_CN">权属人国籍</label> 
    </has-relation>  
    <has-relation relation="HouseFrame_SN" size="8"> 
      <label language="zh_CN">HouseFrame_SN</label> 
    </has-relation>  
    <has-relation relation="LandUseSource_SN" size="8"> 
      <label language="zh_CN">LandUseSource_SN</label> 
    </has-relation>  
    <has-relation relation="LandUseKind_SN" size="8"> 
      <label language="zh_CN">LandUseKind_SN</label> 
    </has-relation>  
    <has-relation relation="LandUse_SN" size="8"> 
      <label language="zh_CN">LandUse_SN</label> 
    </has-relation>  
    <has-relation relation="LandGrade_SN" size="8"> 
      <label language="zh_CN">LandGrade_SN</label> 
    </has-relation>  
    <has-relation relation="UsufructKind_SN" size="8"> 
      <label language="zh_CN">UsufructKind_SN</label> 
    </has-relation>  
    <has-relation relation="ObligeeNation_SN" size="8"> 
      <label language="zh_CN">ObligeeNation_SN</label> 
    </has-relation>  
    <has-relation relation="Town" size="20"> 
      <label language="zh_CN">乡镇街道</label> 
    </has-relation>  
    <has-relation relation="Town_SN" size="8"> 
      <label language="zh_CN">Town_SN</label> 
    </has-relation>  
    <has-relation relation="Village" size="20"> 
      <label language="zh_CN">村、居委会</label> 
    </has-relation>  
    <has-relation relation="Village_SN" size="8"> 
      <label language="zh_CN">Village_SN</label> 
    </has-relation>  
    <has-relation relation="ApplyMan" size="100"> 
      <label language="zh_CN">ApplyMan</label> 
    </has-relation>  
    <has-relation relation="EastWall" size="20"> 
      <label language="zh_CN">东墙</label> 
    </has-relation>  
    <has-relation relation="SouthWall" size="20"> 
      <label language="zh_CN">南墙</label> 
    </has-relation>  
    <has-relation relation="WestWall" size="20"> 
      <label language="zh_CN">西墙</label> 
    </has-relation>  
    <has-relation relation="NorthWall" size="20"> 
      <label language="zh_CN">北墙</label> 
    </has-relation>  
    <has-relation relation="EastWallTo" size="50"> 
      <label language="zh_CN">东墙至</label> 
    </has-relation>  
    <has-relation relation="SouthWallTo" size="50"> 
      <label language="zh_CN">北墙至</label> 
    </has-relation>  
    <has-relation relation="WestWallTo" size="50"> 
      <label language="zh_CN">西墙至</label> 
    </has-relation>  
    <has-relation relation="NorthWallTo" size="50"> 
      <label language="zh_CN">北墙至</label> 
    </has-relation>  
    <has-relation relation="SumUseArea" size="18" scale="2"> 
      <label language="zh_CN">SumUseArea</label> 
    </has-relation>  
    <has-relation relation="SumCommArea" size="18" scale="2"> 
      <label language="zh_CN">SumCommArea</label> 
    </has-relation>  
    <has-relation relation="HouseShare" size="50"> 
      <label language="zh_CN">共有份额</label> 
    </has-relation>  
    <has-relation relation="AcceptanceMan" size="100"> 
      <label language="zh_CN">AcceptanceMan</label> 
    </has-relation>  
    <has-relation relation="RandomPassword" size="100"> 
      <label language="zh_CN">RandomPassword</label> 
    </has-relation>  
    <has-relation relation="HandCreateMan" size="100"> 
      <label language="zh_CN">HandCreateMan</label> 
    </has-relation>  
    <has-relation relation="LockFlag" data-type="Decimal" size="1"> 
      <label language="zh_CN">LockFlag</label> 
    </has-relation>  
    <has-relation relation="ZFTMJ" size="18" scale="2"> 
      <label language="zh_CN">ZFTMJ</label> 
    </has-relation>  
    <has-relation relation="XMBM" size="50"> 
      <label language="zh_CN">XMBM</label> 
    </has-relation>  
    <has-relation relation="st_flag" required="true"> 
      <label language="zh_CN">st_flag</label> 
    </has-relation>  
    <has-relation relation="TDQKBZ"> 
      <label language="zh_CN">TDQKBZ</label> 
    </has-relation>  
    <has-relation relation="MapId" size="200"> 
      <label language="zh_CN">MapId</label> 
    </has-relation>  
    <has-relation relation="bgfid" size="50"> 
      <label language="zh_CN">bgfid</label> 
    </has-relation>  
    <has-relation relation="XQMC" size="100"> 
      <label language="zh_CN">XQMC</label> 
    </has-relation>  
    <has-relation relation="tdqdsj" size="50"> 
      <label language="zh_CN">tdqdsj</label> 
    </has-relation>  
    <has-relation relation="WSSQH" size="50"> 
      <label language="zh_CN">WSSQH</label> 
    </has-relation>  
    <has-relation relation="fid" data-type="String" size="50"/>  
    <has-relation relation="LoginUnitID" size="50"/> 
  </concept>  
  <relation name="HouseAmount1" data-type="Integer"> 
    <label language="zh_CN">HouseAmount1</label> 
  </relation>  
  <relation name="WSSQH" data-type="String"> 
    <label language="zh_CN">WSSQH</label> 
  </relation>  
  <relation name="HouseAmount2" data-type="Integer"> 
    <label language="zh_CN">HouseAmount2</label> 
  </relation>  
  <relation name="HouseFrame_SN" data-type="String"> 
    <label language="zh_CN">HouseFrame_SN</label> 
  </relation>  
  <relation name="EastWall" data-type="String"> 
    <label language="zh_CN">东墙</label> 
  </relation>  
  <relation name="EastWallTo" data-type="String"> 
    <label language="zh_CN">东墙至</label> 
  </relation>  
  <relation name="DroitSource" data-type="String"> 
    <label language="zh_CN">房屋取得方式</label> 
  </relation>  
  <relation name="Path" data-type="String"> 
    <label language="zh_CN">路段</label> 
  </relation>  
  <relation name="PresellBookId" data-type="String"> 
    <label language="zh_CN">PresellBookId</label> 
  </relation>  
  <relation name="fj" data-type="String"> 
    <label language="zh_CN">fj</label> 
  </relation>  
  <relation name="FeeJe" data-type="Decimal"> 
    <label language="zh_CN">费用</label> 
  </relation>  
  <relation name="Nation" data-type="String"> 
    <label language="zh_CN">国籍</label> 
  </relation>  
  <relation name="TradeFeeMode" data-type="String"> 
    <label language="zh_CN">计价方式</label> 
  </relation>  
  <relation name="hz_date" data-type="DateTime"> 
    <label language="zh_CN">hz_date</label> 
  </relation>  
  <relation name="CommArea" data-type="Decimal"> 
    <label language="zh_CN">共有面积</label> 
  </relation>  
  <relation name="IsKb" data-type="Integer"> 
    <label language="zh_CN">IsKb</label> 
  </relation>  
  <relation name="IsArchive"> 
    <label language="zh_CN">已经收档</label> 
  </relation>  
  <relation name="XQMC" data-type="String"> 
    <label language="zh_CN">XQMC</label> 
  </relation>  
  <relation name="RegDate" data-type="DateTime"> 
    <label language="zh_CN">受理日期</label> 
  </relation>  
  <relation name="LandUse_SN" data-type="String"> 
    <label language="zh_CN">LandUse_SN</label> 
  </relation>  
  <relation name="Obligee" data-type="String"> 
    <label language="zh_CN">权属人</label> 
  </relation>  
  <relation name="BookStatus" data-type="String"> 
    <label language="zh_CN">BookStatus</label> 
  </relation>  
  <relation name="NoUseArea" data-type="Decimal"> 
    <label language="zh_CN">非使用面积</label> 
  </relation>  
  <relation name="Town_SN" data-type="String"> 
    <label language="zh_CN">Town_SN</label> 
  </relation>  
  <relation name="lb2" data-type="String"> 
    <label language="zh_CN">lb2</label> 
  </relation>  
  <relation name="QyDate" data-type="DateTime"> 
    <label language="zh_CN">QyDate</label> 
  </relation>  
  <relation name="AcceptanceMan" data-type="String"> 
    <label language="zh_CN">AcceptanceMan</label> 
  </relation>  
  <relation name="IsYFOk"> 
    <label language="zh_CN">IsYFOk</label> 
  </relation>  
  <relation name="IsDeleted"> 
    <label language="zh_CN">IsDeleted</label> 
  </relation>  
  <relation name="Provider" data-type="String"> 
    <label language="zh_CN">发证人</label> 
  </relation>  
  <relation name="Collator" data-type="String"> 
    <label language="zh_CN">校对人</label> 
  </relation>  
  <relation name="fNo" data-type="String"> 
    <label language="zh_CN">登记字号</label> 
  </relation>  
  <relation name="ArchiveID" data-type="String"> 
    <label language="zh_CN">档案号</label> 
  </relation>  
  <relation name="XMBM" data-type="String"> 
    <label language="zh_CN">XMBM</label> 
  </relation>  
  <relation name="HandCreateMan" data-type="String"> 
    <label language="zh_CN">HandCreateMan</label> 
  </relation>  
  <relation name="Village" data-type="String"> 
    <label language="zh_CN">村、居委会</label> 
  </relation>  
  <relation name="CorpAddress" data-type="String"> 
    <label language="zh_CN">公司地址</label> 
  </relation>  
  <relation name="LandUseSource_SN" data-type="String"> 
    <label language="zh_CN">LandUseSource_SN</label> 
  </relation>  
  <relation name="CheckTotalPrice" data-type="Decimal"> 
    <label language="zh_CN">核定总价</label> 
  </relation>  
  <relation name="Village_SN" data-type="String"> 
    <label language="zh_CN">Village_SN</label> 
  </relation>  
  <relation name="GetDate" data-type="DateTime"> 
    <label language="zh_CN">领证日期</label> 
  </relation>  
  <relation name="validdate" data-type="DateTime"> 
    <label language="zh_CN">validdate</label> 
  </relation>  
  <relation name="LandUseKind_SN" data-type="String"> 
    <label language="zh_CN">LandUseKind_SN</label> 
  </relation>  
  <relation name="TerraId" data-type="String"> 
    <label language="zh_CN">地号</label> 
  </relation>  
  <relation name="DocDate" data-type="DateTime"> 
    <label language="zh_CN">DocDate</label> 
  </relation>  
  <relation name="IsPrint"> 
    <label language="zh_CN">IsPrint</label> 
  </relation>  
  <relation name="DroitKind" data-type="String"> 
    <label language="zh_CN">房屋性质</label> 
  </relation>  
  <relation name="st_flag" data-type="Integer"> 
    <label language="zh_CN">st_flag</label> 
  </relation>  
  <relation name="CaptureId" data-type="String"> 
    <label language="zh_CN">产权收件号</label> 
  </relation>  
  <relation name="TradePaymentMode" data-type="String"> 
    <label language="zh_CN">付款方式</label> 
  </relation>  
  <relation name="UsufructKind" data-type="String"> 
    <label language="zh_CN">土地使用权类型</label> 
  </relation>  
  <relation name="LawPersonSex" data-type="String"> 
    <label language="zh_CN">法人代表姓别</label> 
  </relation>  
  <relation name="SumUseArea" data-type="Decimal"> 
    <label language="zh_CN">SumUseArea</label> 
  </relation>  
  <relation name="IsSignOk"> 
    <label language="zh_CN">IsSignOk</label> 
  </relation>  
  <relation name="fType" data-type="String"> 
    <label language="zh_CN">权属业务类型</label> 
  </relation>  
  <relation name="IsFinish"> 
    <label language="zh_CN">IsFinish</label> 
  </relation>  
  <relation name="ObligeeNation_SN" data-type="String"> 
    <label language="zh_CN">ObligeeNation_SN</label> 
  </relation>  
  <relation name="Geter" data-type="String"> 
    <label language="zh_CN">领证人</label> 
  </relation>  
  <relation name="tel" data-type="String"> 
    <label language="zh_CN">电话</label> 
  </relation>  
  <relation name="CreateDate" data-type="DateTime"> 
    <label language="zh_CN">收件日期</label> 
  </relation>  
  <relation name="Corp" data-type="String"> 
    <label language="zh_CN">Corp</label> 
  </relation>  
  <relation name="SouthWall" data-type="String"> 
    <label language="zh_CN">南墙</label> 
  </relation>  
  <relation name="SouthWallTo" data-type="String"> 
    <label language="zh_CN">北墙至</label> 
  </relation>  
  <relation name="Ideas" data-type="Text"> 
    <label language="zh_CN">Ideas</label> 
  </relation>  
  <relation name="LawPersonCardId" data-type="String"> 
    <label language="zh_CN">法人代表身份证号</label> 
  </relation>  
  <relation name="LandUseKind" data-type="String"> 
    <label language="zh_CN">土地性质</label> 
  </relation>  
  <relation name="LawPerson" data-type="String"> 
    <label language="zh_CN">法人代表姓名</label> 
  </relation>  
  <relation name="SZNo" data-type="Integer"> 
    <label language="zh_CN">缮证号</label> 
  </relation>  
  <relation name="IsGetFee"> 
    <label language="zh_CN">已经收费</label> 
  </relation>  
  <relation name="UsufructKind_SN" data-type="String"> 
    <label language="zh_CN">UsufructKind_SN</label> 
  </relation>  
  <relation name="LandGrade_SN" data-type="String"> 
    <label language="zh_CN">LandGrade_SN</label> 
  </relation>  
  <relation name="producerdate" data-type="DateTime"> 
    <label language="zh_CN">缮证日期</label> 
  </relation>  
  <relation name="ZNo" data-type="String"> 
    <label language="zh_CN">ZNo</label> 
  </relation>  
  <relation name="MapId" data-type="String"> 
    <label language="zh_CN">MapId</label> 
  </relation>  
  <relation name="FlowInfo" data-type="Blob"> 
    <label language="zh_CN">FlowInfo</label> 
  </relation>  
  <relation name="DueDate" data-type="DateTime"> 
    <label language="zh_CN">DueDate</label> 
  </relation>  
  <relation name="BookId" data-type="String"> 
    <label language="zh_CN">BookId</label> 
  </relation>  
  <relation name="LockFlag"> 
    <label language="zh_CN">LockFlag</label> 
  </relation>  
  <relation name="EndDate" data-type="String"> 
    <label language="zh_CN">终止日期</label> 
  </relation>  
  <relation name="bookname" data-type="String"> 
    <label language="zh_CN">证书名</label> 
  </relation>  
  <relation name="email" data-type="String"> 
    <label language="zh_CN">邮箱地址</label> 
  </relation>  
  <relation name="JzMemo" data-type="String"> 
    <label language="zh_CN">JzMemo</label> 
  </relation>  
  <relation name="Town" data-type="String"> 
    <label language="zh_CN">乡镇街道</label> 
  </relation>  
  <relation name="LandUseSource" data-type="String"> 
    <label language="zh_CN">土地所有权取得方式</label> 
  </relation>  
  <relation name="type_no" data-type="String"> 
    <label language="zh_CN">type_no</label> 
  </relation>  
  <relation name="PathNo" data-type="String"> 
    <label language="zh_CN">PathNo</label> 
  </relation>  
  <relation name="RandomPassword" data-type="String"> 
    <label language="zh_CN">RandomPassword</label> 
  </relation>  
  <relation name="SumCommArea" data-type="Decimal"> 
    <label language="zh_CN">SumCommArea</label> 
  </relation>  
  <relation name="NorthWall" data-type="String"> 
    <label language="zh_CN">北墙</label> 
  </relation>  
  <relation name="CreateMan" data-type="String"> 
    <label language="zh_CN">收件人</label> 
  </relation>  
  <relation name="BuildBaseArea" data-type="Decimal"> 
    <label language="zh_CN">建基面积</label> 
  </relation>  
  <relation name="bgfid" data-type="String"> 
    <label language="zh_CN">bgfid</label> 
  </relation>  
  <relation name="FeeList" data-type="Text"> 
    <label language="zh_CN">FeeList</label> 
  </relation>  
  <relation name="WestWall" data-type="String"> 
    <label language="zh_CN">西墙</label> 
  </relation>  
  <relation name="TDQKBZ" data-type="Text"> 
    <label language="zh_CN">TDQKBZ</label> 
  </relation>  
  <relation name="ObligeeNation" data-type="String"> 
    <label language="zh_CN">权属人国籍</label> 
  </relation>  
  <relation name="UsufructId" data-type="String"> 
    <label language="zh_CN">土地证号</label> 
  </relation>  
  <relation name="Producer" data-type="String"> 
    <label language="zh_CN">缮证人</label> 
  </relation>  
  <relation name="HouseShare" data-type="String"> 
    <label language="zh_CN">共有份额</label> 
  </relation>  
  <relation name="Department" data-type="String"> 
    <label language="zh_CN">填证机关</label> 
  </relation>  
  <relation name="HouseId" data-type="String"> 
    <label language="zh_CN">房屋编码</label> 
  </relation>  
  <relation name="zxId" data-type="String"> 
    <label language="zh_CN">zxId</label> 
  </relation>  
  <relation name="NorthWallTo" data-type="String"> 
    <label language="zh_CN">北墙至</label> 
  </relation>  
  <relation name="IsModifyTrade"> 
    <label language="zh_CN">IsModifyTrade</label> 
  </relation>  
  <relation name="ZFTMJ" data-type="Decimal"> 
    <label language="zh_CN">ZFTMJ</label> 
  </relation>  
  <relation name="LawPersonNation" data-type="String"> 
    <label language="zh_CN">法人代表国籍</label> 
  </relation>  
  <relation name="UsufructArea" data-type="Decimal"> 
    <label language="zh_CN">土地面积</label> 
  </relation>  
  <relation name="CheckPrice" data-type="Decimal"> 
    <label language="zh_CN">核定单价</label> 
  </relation>  
  <relation name="IsEmptyHouseTrade"> 
    <label language="zh_CN">IsEmptyHouseTrade</label> 
  </relation>  
  <relation name="cdIdea" data-type="Text"> 
    <label language="zh_CN">cdIdea</label> 
  </relation>  
  <relation name="LandGrade" data-type="String"> 
    <label language="zh_CN">土地等级</label> 
  </relation>  
  <relation name="tdqdsj" data-type="String"> 
    <label language="zh_CN">tdqdsj</label> 
  </relation>  
  <relation name="ApplyMan" data-type="String"> 
    <label language="zh_CN">ApplyMan</label> 
  </relation>  
  <relation name="JzMam" data-type="String"> 
    <label language="zh_CN">JzMam</label> 
  </relation>  
  <relation name="Remark" data-type="String"> 
    <label language="zh_CN">附记</label> 
  </relation>  
  <relation name="WestWallTo" data-type="String"> 
    <label language="zh_CN">西墙至</label> 
  </relation>  
  <relation name="ObligeeSex" data-type="String"> 
    <label language="zh_CN">权属人性别</label> 
  </relation>  
  <relation name="fid" data-type="String"> 
    <label language="zh_CN">fid</label> 
  </relation>  
  <concept name="T_HouseTradeQs_Dong" default-value-expr="guid()" keys="Autoid:LoginUnitID"> 
    <has-relation relation="FID" size="50" required="true"> 
      <label language="zh_CN">FID</label> 
    </has-relation>  
    <has-relation relation="ItemName" size="120"/>  
    <has-relation relation="YSID" size="50"> 
      <label language="zh_CN">YSID</label> 
    </has-relation>  
    <has-relation relation="YSDong" size="100"> 
      <label language="zh_CN">YSDong</label> 
    </has-relation>  
    <has-relation relation="DongName" size="100"/>  
    <has-relation relation="Ceng" size="50"/>  
    <has-relation relation="BaseBuildArea" size="18" scale="2"/>  
    <has-relation relation="SumBuildArea" size="18" scale="2"/>  
    <has-relation relation="UseBuildArea" size="18" scale="2"/>  
    <has-relation relation="BuildingID" size="50"/>  
    <has-relation relation="FramingNo" size="12" scale="6"> 
      <label language="zh_CN">FramingNo</label> 
    </has-relation>  
    <has-relation relation="HillNo" size="8" scale="4"> 
      <label language="zh_CN">HillNo</label> 
    </has-relation>  
    <has-relation relation="HorPoint" size="6"> 
      <label language="zh_CN">HorPoint</label> 
    </has-relation>  
    <has-relation relation="VerPoint" size="6"> 
      <label language="zh_CN">VerPoint</label> 
    </has-relation>  
    <has-relation relation="spfsn" size="50"> 
      <label language="zh_CN">spfsn</label> 
    </has-relation>  
    <has-relation relation="LockFlag" data-type="Decimal" size="1"> 
      <label language="zh_CN">LockFlag</label> 
    </has-relation>  
    <has-relation relation="houseframe" size="50"/>  
    <has-relation relation="olddongname" size="50"> 
      <label language="zh_CN">olddongname</label> 
    </has-relation>  
    <has-relation relation="dbh" size="50"/>  
    <has-relation relation="XQMC" size="50"> 
      <label language="zh_CN">XQMC</label> 
    </has-relation>  
    <has-relation relation="XQBM" size="50"> 
      <label language="zh_CN">XQBM</label> 
    </has-relation>  
    <has-relation relation="st_flag" required="true"> 
      <label language="zh_CN">st_flag</label> 
    </has-relation>  
    <has-relation relation="Autoid" size="18"/>  
    <has-relation relation="LoginUnitID" size="50"/> 
  </concept>  
  <relation name="olddongname" data-type="String"> 
    <label language="zh_CN">olddongname</label> 
  </relation>  
  <relation name="YSID" data-type="String"> 
    <label language="zh_CN">YSID</label> 
  </relation>  
  <relation name="FID" data-type="String"> 
    <label language="zh_CN">FID</label> 
  </relation>  
  <relation name="YSDong" data-type="String"> 
    <label language="zh_CN">YSDong</label> 
  </relation>  
  <relation name="VerPoint" data-type="String"> 
    <label language="zh_CN">VerPoint</label> 
  </relation>  
  <relation name="spfsn" data-type="String"> 
    <label language="zh_CN">spfsn</label> 
  </relation>  
  <relation name="HillNo" data-type="Decimal"> 
    <label language="zh_CN">HillNo</label> 
  </relation>  
  <relation name="XQBM" data-type="String"> 
    <label language="zh_CN">XQBM</label> 
  </relation>  
  <relation name="FramingNo" data-type="Decimal"> 
    <label language="zh_CN">FramingNo</label> 
  </relation>  
  <relation name="houseframe" data-type="String"> 
    <label language="zh_CN">houseframe</label> 
  </relation>  
  <relation name="HorPoint" data-type="String"> 
    <label language="zh_CN">HorPoint</label> 
  </relation>  
  <concept name="T_HouseTradeQsDetail" default-value-expr="guid()" keys="myfid"> 
    <has-relation relation="CFID" size="50"> 
      <label language="zh_CN">CFID</label> 
    </has-relation>  
    <has-relation relation="FID" size="50" required="true"> 
      <label language="zh_CN">FID</label> 
    </has-relation>  
    <has-relation relation="HouseId" size="50"/>  
    <has-relation relation="PHouseID" size="50"> 
      <label language="zh_CN">PHouseID</label> 
    </has-relation>  
    <has-relation relation="HouseNo" size="80" required="false"/>  
    <has-relation relation="DongNo" size="100"/>  
    <has-relation relation="IniBookID" size="80"> 
      <label language="zh_CN">IniBookID</label> 
    </has-relation>  
    <has-relation relation="Obligee" size="150"> 
      <label language="zh_CN">权属人</label> 
    </has-relation>  
    <has-relation relation="HouseRepose" size="500"/>  
    <has-relation relation="HouseUse" size="150"/>  
    <has-relation relation="HouseFrame" size="150"/>  
    <has-relation relation="BuildBaseArea" size="18" scale="2"> 
      <label language="zh_CN">建基面积</label> 
    </has-relation>  
    <has-relation relation="SumbuildArea" size="18" scale="2"/>  
    <has-relation relation="UseArea" size="18" scale="2"/>  
    <has-relation relation="Ceng" size="100"/>  
    <has-relation relation="CengNum" size="50"> 
      <label language="zh_CN">CengNum</label> 
    </has-relation>  
    <has-relation relation="FramingNo" size="12" scale="6"> 
      <label language="zh_CN">幅号</label> 
    </has-relation>  
    <has-relation relation="HillNo" size="8" scale="4"> 
      <label language="zh_CN">丘号</label> 
    </has-relation>  
    <has-relation relation="HorPoint" size="6"> 
      <label language="zh_CN">横坐标</label> 
    </has-relation>  
    <has-relation relation="VerPoint" size="6"> 
      <label language="zh_CN">纵坐标</label> 
    </has-relation>  
    <has-relation relation="EastWall" size="50"> 
      <label language="zh_CN">东墙</label> 
    </has-relation>  
    <has-relation relation="SouthWall" size="50"> 
      <label language="zh_CN">南墙</label> 
    </has-relation>  
    <has-relation relation="WestWall" size="50"> 
      <label language="zh_CN">西墙</label> 
    </has-relation>  
    <has-relation relation="NorthWall" size="50"> 
      <label language="zh_CN">北墙</label> 
    </has-relation>  
    <has-relation relation="EastWallTo" size="150"> 
      <label language="zh_CN">东墙至</label> 
    </has-relation>  
    <has-relation relation="SouthWallTo" size="150"> 
      <label language="zh_CN">南墙至</label> 
    </has-relation>  
    <has-relation relation="WestWallTo" size="150"> 
      <label language="zh_CN">西墙至</label> 
    </has-relation>  
    <has-relation relation="NorthWallTo" size="150"> 
      <label language="zh_CN">北墙至</label> 
    </has-relation>  
    <has-relation relation="Remark" size="4000"> 
      <label language="zh_CN">备注</label> 
    </has-relation>  
    <has-relation relation="Autoid" required="true" size="18"/>  
    <has-relation relation="LoginUnitID" required="true" size="50"/>  
    <has-relation relation="fNo" size="50"> 
      <label language="zh_CN">fNo</label> 
    </has-relation>  
    <has-relation relation="isPrint" size="10"> 
      <label language="zh_CN">isPrint</label> 
    </has-relation>  
    <has-relation relation="FTArea" size="18" scale="2"> 
      <label language="zh_CN">住宅分摊面积</label> 
    </has-relation>  
    <has-relation relation="jy_Flag"> 
      <label language="zh_CN">交易状态（1 有效，2交易中，3已登记）</label> 
    </has-relation>  
    <has-relation relation="fwfhsn" size="50"> 
      <label language="zh_CN">fwfhsn</label> 
    </has-relation>  
    <has-relation relation="spfsn" size="50"> 
      <label language="zh_CN">spfsn</label> 
    </has-relation>  
    <has-relation relation="QQ_FLAG_FWFHB" size="50"> 
      <label language="zh_CN">QQ_FLAG_FWFHB</label> 
    </has-relation>  
    <has-relation relation="FWHXJG" size="50"/>  
    <has-relation relation="TDSYNX1"> 
      <label language="zh_CN">TDSYNX1</label> 
    </has-relation>  
    <has-relation relation="TDSYNX2"> 
      <label language="zh_CN">TDSYNX2</label> 
    </has-relation>  
    <has-relation relation="Town" size="50"> 
      <label language="zh_CN">乡镇街道</label> 
    </has-relation>  
    <has-relation relation="Town_SN" size="50"> 
      <label language="zh_CN">Town_SN</label> 
    </has-relation>  
    <has-relation relation="HasPhoto" data-type="Decimal" size="1"> 
      <label language="zh_CN">HasPhoto</label> 
    </has-relation>  
    <has-relation relation="Limit" size="20"> 
      <label language="zh_CN">Limit</label> 
    </has-relation>  
    <has-relation relation="Status" size="50"/>  
    <has-relation relation="txqflag" size="50"/>  
    <has-relation relation="cfflag"/>  
    <has-relation relation="YgspfygFlag" data-type="Decimal" size="1"> 
      <label language="zh_CN">YgspfygFlag</label> 
    </has-relation>  
    <has-relation relation="YsgpfdyqygFlag" data-type="Decimal" size="1"> 
      <label language="zh_CN">YsgpfdyqygFlag</label> 
    </has-relation>  
    <has-relation relation="LockFlag" data-type="Decimal" size="1"> 
      <label language="zh_CN">LockFlag</label> 
    </has-relation>  
    <has-relation relation="DeclarationPrice" size="18" scale="2"> 
      <label language="zh_CN">DeclarationPrice</label> 
    </has-relation>  
    <has-relation relation="DeclarationZJE" size="18" scale="2"> 
      <label language="zh_CN">DeclarationZJE</label> 
    </has-relation>  
    <has-relation relation="producerdate"> 
      <label language="zh_CN">producerdate</label> 
    </has-relation>  
    <has-relation relation="ftype" size="50"> 
      <label language="zh_CN">ftype</label> 
    </has-relation>  
    <has-relation relation="sjbh" size="50"> 
      <label language="zh_CN">sjbh</label> 
    </has-relation>  
    <has-relation relation="spbz" size="50"> 
      <label language="zh_CN">spbz</label> 
    </has-relation>  
    <has-relation relation="FdcdyqygFlag" data-type="Decimal" size="1"> 
      <label language="zh_CN">FdcdyqygFlag</label> 
    </has-relation>  
    <has-relation relation="FdcqzyygFlag" data-type="Decimal" size="1"> 
      <label language="zh_CN">FdcqzyygFlag</label> 
    </has-relation>  
    <has-relation relation="dbh"/>  
    <has-relation relation="XQMC" size="100"> 
      <label language="zh_CN">XQMC</label> 
    </has-relation>  
    <has-relation relation="XQBM" size="50"> 
      <label language="zh_CN">XQBM</label> 
    </has-relation>  
    <has-relation relation="XMBM" size="50"> 
      <label language="zh_CN">XMBM</label> 
    </has-relation>  
    <has-relation relation="MergeFid" size="50"> 
      <label language="zh_CN">MergeFid</label> 
    </has-relation>  
    <has-relation relation="st_flag" required="true"> 
      <label language="zh_CN">st_flag</label> 
    </has-relation>  
    <has-relation relation="djz" size="50"> 
      <label language="zh_CN">djz</label> 
    </has-relation>  
    <has-relation relation="ArchiveID" size="50"> 
      <label language="zh_CN">ArchiveID</label> 
    </has-relation>  
    <has-relation relation="ArchiveDate"> 
      <label language="zh_CN">ArchiveDate</label> 
    </has-relation>  
    <has-relation relation="YCSumbuildArea" size="18" scale="2"/>  
    <has-relation relation="YCUseArea" size="18" scale="2"/>  
    <has-relation relation="xh"> 
      <label language="zh_CN">xh</label> 
    </has-relation>  
    <has-relation relation="CQZH" size="100"> 
      <label language="zh_CN">CQZH</label> 
    </has-relation>  
    <has-relation relation="zymj" size="18" scale="2"> 
      <label language="zh_CN">zymj</label> 
    </has-relation>  
    <has-relation relation="sfbj" size="10"> 
      <label language="zh_CN">sfbj</label> 
    </has-relation>  
    <has-relation relation="szr" size="20"> 
      <label language="zh_CN">szr</label> 
    </has-relation>  
    <has-relation relation="Is_chyy"> 
      <label language="zh_CN">Is_chyy</label> 
    </has-relation>  
    <has-relation relation="SHBW" size="20"> 
      <label language="zh_CN">SHBW</label> 
    </has-relation>  
    <has-relation relation="myfid" size="50"/> 
  </concept>  
  <relation name="xh" data-type="Integer"> 
    <label language="zh_CN">xh</label> 
  </relation>  
  <relation name="isPrint" data-type="String"> 
    <label language="zh_CN">isPrint</label> 
  </relation>  
  <relation name="SumbuildArea" data-type="Decimal"> 
    <label language="zh_CN">建筑面积</label> 
  </relation>  
  <relation name="spbz" data-type="String"> 
    <label language="zh_CN">spbz</label> 
  </relation>  
  <relation name="cfflag" data-type="Integer"> 
    <label language="zh_CN">cfflag</label> 
  </relation>  
  <relation name="sfbj" data-type="String"> 
    <label language="zh_CN">sfbj</label> 
  </relation>  
  <relation name="TDSYNX1" data-type="DateTime"> 
    <label language="zh_CN">TDSYNX1</label> 
  </relation>  
  <relation name="DeclarationPrice" data-type="Decimal"> 
    <label language="zh_CN">DeclarationPrice</label> 
  </relation>  
  <relation name="PHouseID" data-type="String"> 
    <label language="zh_CN">PHouseID</label> 
  </relation>  
  <relation name="TDSYNX2" data-type="DateTime"> 
    <label language="zh_CN">TDSYNX2</label> 
  </relation>  
  <relation name="MergeFid" data-type="String"> 
    <label language="zh_CN">MergeFid</label> 
  </relation>  
  <relation name="SHBW" data-type="String"> 
    <label language="zh_CN">SHBW</label> 
  </relation>  
  <relation name="fwfhsn" data-type="String"> 
    <label language="zh_CN">fwfhsn</label> 
  </relation>  
  <relation name="FTArea" data-type="Decimal"> 
    <label language="zh_CN">住宅分摊面积</label> 
  </relation>  
  <relation name="ArchiveDate" data-type="DateTime"> 
    <label language="zh_CN">ArchiveDate</label> 
  </relation>  
  <relation name="CFID" data-type="String"> 
    <label language="zh_CN">CFID</label> 
  </relation>  
  <relation name="CQZH" data-type="String"> 
    <label language="zh_CN">CQZH</label> 
  </relation>  
  <relation name="linked" data-type="String"> 
    <label language="zh_CN">linked</label> 
  </relation>  
  <relation name="Is_chyy" data-type="Integer"> 
    <label language="zh_CN">Is_chyy</label> 
  </relation>  
  <relation name="YsgpfdyqygFlag"> 
    <label language="zh_CN">YsgpfdyqygFlag</label> 
  </relation>  
  <relation name="szr" data-type="String"> 
    <label language="zh_CN">szr</label> 
  </relation>  
  <relation name="HasPhoto"> 
    <label language="zh_CN">HasPhoto</label> 
  </relation>  
  <relation name="IniBookID" data-type="String"> 
    <label language="zh_CN">IniBookID</label> 
  </relation>  
  <relation name="QQ_FLAG_FWFHB" data-type="String"> 
    <label language="zh_CN">QQ_FLAG_FWFHB</label> 
  </relation>  
  <relation name="jy_Flag" data-type="Integer"> 
    <label language="zh_CN">交易状态（1 有效，2交易中，3已登记）</label> 
  </relation>  
  <relation name="FdcdyqygFlag"> 
    <label language="zh_CN">FdcdyqygFlag</label> 
  </relation>  
  <relation name="djz" data-type="String"> 
    <label language="zh_CN">djz</label> 
  </relation>  
  <relation name="YgspfygFlag"> 
    <label language="zh_CN">YgspfygFlag</label> 
  </relation>  
  <relation name="zymj" data-type="Decimal"> 
    <label language="zh_CN">zymj</label> 
  </relation>  
  <relation name="DeclarationZJE" data-type="Decimal"> 
    <label language="zh_CN">DeclarationZJE</label> 
  </relation>  
  <relation name="txqflag" data-type="String"> 
    <label language="zh_CN">txqflag</label> 
  </relation>  
  <relation name="ftype" data-type="String"> 
    <label language="zh_CN">ftype</label> 
  </relation>  
  <relation name="FdcqzyygFlag"> 
    <label language="zh_CN">FdcqzyygFlag</label> 
  </relation>  
  <relation name="CengNum" data-type="String"> 
    <label language="zh_CN">CengNum</label> 
  </relation>  
  <relation name="YCSumbuildArea" data-type="Decimal"> 
    <label language="zh_CN">YCSumbuildArea</label> 
  </relation>  
  <relation name="Limit" data-type="String"> 
    <label language="zh_CN">Limit</label> 
  </relation>  
  <relation name="sjbh" data-type="String"> 
    <label language="zh_CN">sjbh</label> 
  </relation>  
  <concept name="KFSData" default-value-expr="guid()" keys="Autoid">
    <has-relation relation="Djh" size="20"/>  
    <has-relation relation="businessPermitNO" size="80">
      <label language="zh_CN">businessPermitNO</label> 
    </has-relation>  
    <has-relation relation="Bz" size="200">
      <label language="zh_CN">Bz</label> 
    </has-relation>  
    <has-relation relation="Lxr" size="50">
      <label language="zh_CN">Lxr</label> 
    </has-relation>  
    <has-relation relation="Lxdh" size="60">
      <label language="zh_CN">Lxdh</label> 
    </has-relation>  
    <has-relation relation="Zzdj" size="50">
      <label language="zh_CN">Zzdj</label> 
    </has-relation>  
    <has-relation relation="Fw" size="1000">
      <label language="zh_CN">Fw</label> 
    </has-relation>  
    <has-relation relation="Frdb" size="50">
      <label language="zh_CN">Frdb</label> 
    </has-relation>  
    <has-relation relation="Dz" size="200">
      <label language="zh_CN">Dz</label> 
    </has-relation>  
    <has-relation relation="Fl" size="50">
      <label language="zh_CN">Fl</label> 
    </has-relation>  
    <has-relation relation="Mc" size="200">
      <label language="zh_CN">Mc</label> 
    </has-relation>  
    <has-relation relation="BankNo" size="50">
      <label language="zh_CN">BankNo</label> 
    </has-relation>  
    <has-relation relation="BankName" size="200">
      <label language="zh_CN">BankName</label> 
    </has-relation>  
    <has-relation relation="Jbr" size="50">
      <label language="zh_CN">Jbr</label> 
    </has-relation>  
    <has-relation relation="DjDate">
      <label language="zh_CN">DjDate</label> 
    </has-relation>  
    <has-relation relation="PassWord" size="50">
      <label language="zh_CN">PassWord</label> 
    </has-relation>  
    <has-relation relation="WebAddress" size="50">
      <label language="zh_CN">WebAddress</label> 
    </has-relation>  
    <has-relation relation="KFS_synopsis">
      <label language="zh_CN">KFS_synopsis</label> 
    </has-relation>  
    <has-relation relation="KFS_code" size="30">
      <label language="zh_CN">KFS_code</label> 
    </has-relation>  
    <has-relation relation="LoginUnitID" required="true" size="50"/>  
    <has-relation relation="Bgdz" size="200">
      <label language="zh_CN">Bgdz</label> 
    </has-relation>  
    <has-relation relation="Zczj" size="18" scale="2">
      <label language="zh_CN">Zczj</label> 
    </has-relation>  
    <has-relation relation="MoneyKind">
      <label language="zh_CN">MoneyKind</label> 
    </has-relation>  
    <has-relation relation="Zzzsh" size="50">
      <label language="zh_CN">Zzzsh</label> 
    </has-relation>  
    <has-relation relation="Fzjg" size="100">
      <label language="zh_CN">Fzjg</label> 
    </has-relation>  
    <has-relation relation="Zzfzrq">
      <label language="zh_CN">Zzfzrq</label> 
    </has-relation>  
    <has-relation relation="Zzyxq">
      <label language="zh_CN">Zzyxq</label> 
    </has-relation>  
    <has-relation relation="Pzjyrq">
      <label language="zh_CN">Pzjyrq</label> 
    </has-relation>  
    <has-relation relation="Gszcrq">
      <label language="zh_CN">Gszcrq</label> 
    </has-relation>  
    <has-relation relation="Yyzzyxq">
      <label language="zh_CN">Yyzzyxq</label> 
    </has-relation>  
    <has-relation relation="fax" size="30">
      <label language="zh_CN">fax</label> 
    </has-relation>  
    <has-relation relation="lxrtxdz" size="200">
      <label language="zh_CN">lxrtxdz</label> 
    </has-relation>  
    <has-relation relation="Yzbm" size="20">
      <label language="zh_CN">Yzbm</label> 
    </has-relation>  
    <has-relation relation="email" size="30"/>  
    <has-relation relation="IsReg" data-type="Decimal" size="1">
      <label language="zh_CN">IsReg</label> 
    </has-relation>  
    <has-relation relation="TaxOrg" size="50">
      <label language="zh_CN">TaxOrg</label> 
    </has-relation>  
    <has-relation relation="flag" required="true">
      <label language="zh_CN">flag</label> 
    </has-relation>  
    <has-relation relation="st_flag" required="true"/>  
    <has-relation relation="Equipment">
      <label language="zh_CN">Equipment</label> 
    </has-relation>  
    <label language="zh_CN">开发商</label>  
    <has-relation relation="Autoid" size="18"/> 
  </concept>  
  <relation name="Pzjyrq" data-type="DateTime">
    <label language="zh_CN">Pzjyrq</label> 
  </relation>  
  <relation name="Djh" data-type="String">
    <label language="zh_CN">Djh</label> 
  </relation>  
  <relation name="BankNo" data-type="String">
    <label language="zh_CN">BankNo</label> 
  </relation>  
  <relation name="Zzzsh" data-type="String">
    <label language="zh_CN">Zzzsh</label> 
  </relation>  
  <relation name="Yyzzyxq" data-type="DateTime">
    <label language="zh_CN">Yyzzyxq</label> 
  </relation>  
  <relation name="Lxr" data-type="String">
    <label language="zh_CN">Lxr</label> 
  </relation>  
  <relation name="KFS_synopsis" data-type="Text">
    <label language="zh_CN">KFS_synopsis</label> 
  </relation>  
  <relation name="BankName" data-type="String">
    <label language="zh_CN">BankName</label> 
  </relation>  
  <relation name="Fzjg" data-type="String">
    <label language="zh_CN">Fzjg</label> 
  </relation>  
  <relation name="businessPermitNO" data-type="String">
    <label language="zh_CN">businessPermitNO</label> 
  </relation>  
  <relation name="Lxdh" data-type="String">
    <label language="zh_CN">Lxdh</label> 
  </relation>  
  <relation name="Zzyxq" data-type="DateTime">
    <label language="zh_CN">Zzyxq</label> 
  </relation>  
  <relation name="PassWord" data-type="String">
    <label language="zh_CN">PassWord</label> 
  </relation>  
  <relation name="WebAddress" data-type="String">
    <label language="zh_CN">WebAddress</label> 
  </relation>  
  <relation name="RowVersion">
    <label language="zh_CN">RowVersion</label> 
  </relation>  
  <relation name="KFS_code" data-type="String">
    <label language="zh_CN">KFS_code</label> 
  </relation>  
  <relation name="lxrtxdz" data-type="String">
    <label language="zh_CN">lxrtxdz</label> 
  </relation>  
  <relation name="Zzdj" data-type="String">
    <label language="zh_CN">Zzdj</label> 
  </relation>  
  <relation name="DjDate" data-type="DateTime">
    <label language="zh_CN">DjDate</label> 
  </relation>  
  <relation name="Equipment" data-type="Integer">
    <label language="zh_CN">Equipment</label> 
  </relation>  
  <relation name="fax" data-type="String">
    <label language="zh_CN">fax</label> 
  </relation>  
  <relation name="msrepl_tran_version" data-type="String">
    <label language="zh_CN">msrepl_tran_version</label> 
  </relation>  
  <relation name="Yzbm" data-type="String">
    <label language="zh_CN">Yzbm</label> 
  </relation>  
  <relation name="Frdb" data-type="String">
    <label language="zh_CN">Frdb</label> 
  </relation>  
  <relation name="IsReg">
    <label language="zh_CN">IsReg</label> 
  </relation>  
  <relation name="Zczj" data-type="Decimal">
    <label language="zh_CN">Zczj</label> 
  </relation>  
  <relation name="Dz" data-type="String">
    <label language="zh_CN">Dz</label> 
  </relation>  
  <relation name="Jbr" data-type="String">
    <label language="zh_CN">Jbr</label> 
  </relation>  
  <relation name="MoneyKind" data-type="Integer">
    <label language="zh_CN">MoneyKind</label> 
  </relation>  
  <relation name="Fw" data-type="String">
    <label language="zh_CN">Fw</label> 
  </relation>  
  <relation name="Bz" data-type="String">
    <label language="zh_CN">Bz</label> 
  </relation>  
  <relation name="Gszcrq" data-type="DateTime">
    <label language="zh_CN">Gszcrq</label> 
  </relation>  
  <relation name="flag" data-type="Integer">
    <label language="zh_CN">flag</label> 
  </relation>  
  <relation name="TaxOrg" data-type="String">
    <label language="zh_CN">TaxOrg</label> 
  </relation>  
  <relation name="Zzfzrq" data-type="DateTime">
    <label language="zh_CN">Zzfzrq</label> 
  </relation>  
  <relation name="Fl" data-type="String">
    <label language="zh_CN">Fl</label> 
  </relation>  
  <relation name="Bgdz" data-type="String">
    <label language="zh_CN">Bgdz</label> 
  </relation>  
  <relation name="Mc" data-type="String">
    <label language="zh_CN">Mc</label> 
  </relation>  
  <concept name="HousePresell" default-value-expr="guid()" keys="fid">
    <has-relation relation="fNo" size="100"/>  
    <has-relation relation="ArchiveId" size="50"/>  
    <has-relation relation="Status" size="50"/>  
    <has-relation relation="BookStatus" size="50"/>  
    <has-relation relation="fType" size="50"/>  
    <has-relation relation="CorpName" size="120"/>  
    <has-relation relation="tbDate">
      <label language="zh_CN">tbDate</label> 
    </has-relation>  
    <has-relation relation="CorpAddress" size="120"/>  
    <has-relation relation="CompleteDate" size="20" data-type="String"/>  
    <has-relation relation="CorpPhone" size="50">
      <label language="zh_CN">CorpPhone</label> 
    </has-relation>  
    <has-relation relation="HouseRepose" size="499"/>  
    <has-relation relation="HouseFrame" size="80"/>  
    <has-relation relation="Permit_LandPlanning" size="100">
      <label language="zh_CN">Permit_LandPlanning</label> 
    </has-relation>  
    <has-relation relation="Permit_Construction" size="100">
      <label language="zh_CN">Permit_Construction</label> 
    </has-relation>  
    <has-relation relation="Permit_Engineering" size="200">
      <label language="zh_CN">Permit_Engineering</label> 
    </has-relation>  
    <has-relation relation="BuildBaseArea" size="18" scale="2"/>  
    <has-relation relation="SumBuildArea" size="18" scale="2"/>  
    <has-relation relation="HouseAmount1"/>  
    <has-relation relation="BuildArea" size="18" scale="2"/>  
    <has-relation relation="HouseAmount2"/>  
    <has-relation relation="Part" size="120">
      <label language="zh_CN">Part</label> 
    </has-relation>  
    <has-relation relation="LandArea"/>  
    <has-relation relation="UseDateBegin">
      <label language="zh_CN">UseDateBegin</label> 
    </has-relation>  
    <has-relation relation="UseDateEnd">
      <label language="zh_CN">UseDateEnd</label> 
    </has-relation>  
    <has-relation relation="LandUse" size="120"/>  
    <has-relation relation="PreSellBookId" size="120"/>  
    <has-relation relation="BankName" size="4000">
      <label language="zh_CN">BankName</label> 
    </has-relation>  
    <has-relation relation="BankNo" size="4000">
      <label language="zh_CN">BankNo</label> 
    </has-relation>  
    <has-relation relation="Manager_View">
      <label language="zh_CN">Manager_View</label> 
    </has-relation>  
    <has-relation relation="KFS_View">
      <label language="zh_CN">KFS_View</label> 
    </has-relation>  
    <has-relation relation="Remark" size="4000"/>  
    <has-relation relation="DueDate"/>  
    <has-relation relation="FeeList"/>  
    <has-relation relation="FeeJe" size="18" scale="2"/>  
    <has-relation relation="Djh" size="80"/>  
    <has-relation relation="moneytype" size="120">
      <label language="zh_CN">moneytype</label> 
    </has-relation>  
    <has-relation relation="obligeenation" size="120"/>  
    <has-relation relation="houseuse" size="120"/>  
    <has-relation relation="lb2" size="120"/>  
    <has-relation relation="Housedj" size="18" scale="2">
      <label language="zh_CN">Housedj</label> 
    </has-relation>  
    <has-relation relation="ZJE" size="18" scale="2">
      <label language="zh_CN">ZJE</label> 
    </has-relation>  
    <has-relation relation="CCount" size="120">
      <label language="zh_CN">CCount</label> 
    </has-relation>  
    <has-relation relation="ZhuzhaiArea">
      <label language="zh_CN">ZhuzhaiArea</label> 
    </has-relation>  
    <has-relation relation="ShangYeArea">
      <label language="zh_CN">ShangYeArea</label> 
    </has-relation>  
    <has-relation relation="ZhuZhaiCount" data-type="Integer">
      <label language="zh_CN">ZhuZhaiCount</label> 
    </has-relation>  
    <has-relation relation="ShangYeCount" data-type="Integer">
      <label language="zh_CN">ShangYeCount</label> 
    </has-relation>  
    <has-relation relation="DongNo" size="120"/>  
    <has-relation relation="BuildedCount" size="800">
      <label language="zh_CN">BuildedCount</label> 
    </has-relation>  
    <has-relation relation="HouseCount" data-type="Integer">
      <label language="zh_CN">HouseCount</label> 
    </has-relation>  
    <has-relation relation="IsDiYa" size="50">
      <label language="zh_CN">IsDiYa</label> 
    </has-relation>  
    <has-relation relation="Presellname" size="120"/>  
    <has-relation relation="FZOrg" size="120">
      <label language="zh_CN">FZOrg</label> 
    </has-relation>  
    <has-relation relation="FZDate">
      <label language="zh_CN">FZDate</label> 
    </has-relation>  
    <has-relation relation="FZDateBegin" size="50">
      <label language="zh_CN">FZDateBegin</label> 
    </has-relation>  
    <has-relation relation="FZDateEnd" size="50">
      <label language="zh_CN">FZDateEnd</label> 
    </has-relation>  
    <has-relation relation="TSTel" size="60">
      <label language="zh_CN">TSTel</label> 
    </has-relation>  
    <has-relation relation="FQ" size="50"/>  
    <has-relation relation="UseDate" size="50">
      <label language="zh_CN">UseDate</label> 
    </has-relation>  
    <has-relation relation="Others1">
      <label language="zh_CN">Others1</label> 
    </has-relation>  
    <has-relation relation="Others1Amout">
      <label language="zh_CN">Others1Amout</label> 
    </has-relation>  
    <has-relation relation="TDBH" size="180">
      <label language="zh_CN">TDBH</label> 
    </has-relation>  
    <has-relation relation="KFSZH" size="180">
      <label language="zh_CN">KFSZH</label> 
    </has-relation>  
    <has-relation relation="ViewAmout">
      <label language="zh_CN">ViewAmout</label> 
    </has-relation>  
    <has-relation relation="ManagerCom" size="299">
      <label language="zh_CN">ManagerCom</label> 
    </has-relation>  
    <has-relation relation="ManagerCharge" size="18" scale="2">
      <label language="zh_CN">ManagerCharge</label> 
    </has-relation>  
    <has-relation relation="PaperCourse" size="60">
      <label language="zh_CN">PaperCourse</label> 
    </has-relation>  
    <has-relation relation="FirstTrial">
      <label language="zh_CN">FirstTrial</label> 
    </has-relation>  
    <has-relation relation="Auditing">
      <label language="zh_CN">Auditing</label> 
    </has-relation>  
    <has-relation relation="ExaApprove">
      <label language="zh_CN">ExaApprove</label> 
    </has-relation>  
    <has-relation relation="PresellArea"/>  
    <has-relation relation="PublicArea"/>  
    <has-relation relation="SelfArea"/>  
    <has-relation relation="WYArea"/>  
    <has-relation relation="OfficeArea">
      <label language="zh_CN">OfficeArea</label> 
    </has-relation>  
    <has-relation relation="OfficeAmout">
      <label language="zh_CN">OfficeAmout</label> 
    </has-relation>  
    <has-relation relation="BeginDate">
      <label language="zh_CN">BeginDate</label> 
    </has-relation>  
    <has-relation relation="PlanPresellDateBegin">
      <label language="zh_CN">PlanPresellDateBegin</label> 
    </has-relation>  
    <has-relation relation="PlanPresellDateEnd">
      <label language="zh_CN">PlanPresellDateEnd</label> 
    </has-relation>  
    <has-relation relation="Presell_BeginDate">
      <label language="zh_CN">Presell_BeginDate</label> 
    </has-relation>  
    <has-relation relation="Presell_EndDate">
      <label language="zh_CN">Presell_EndDate</label> 
    </has-relation>  
    <has-relation relation="FirstTrialDate">
      <label language="zh_CN">FirstTrialDate</label> 
    </has-relation>  
    <has-relation relation="First_Name" size="80">
      <label language="zh_CN">First_Name</label> 
    </has-relation>  
    <has-relation relation="Second_Name" size="80">
      <label language="zh_CN">Second_Name</label> 
    </has-relation>  
    <has-relation relation="Last_Name" size="80">
      <label language="zh_CN">Last_Name</label> 
    </has-relation>  
    <has-relation relation="Update_date">
      <label language="zh_CN">Update_date</label> 
    </has-relation>  
    <has-relation relation="Second_date">
      <label language="zh_CN">Second_date</label> 
    </has-relation>  
    <has-relation relation="last_date">
      <label language="zh_CN">last_date</label> 
    </has-relation>  
    <has-relation relation="LXR" size="20">
      <label language="zh_CN">LXR</label> 
    </has-relation>  
    <has-relation relation="gcyszj" size="12" scale="2">
      <label language="zh_CN">gcyszj</label> 
    </has-relation>  
    <has-relation relation="yxq2" size="20">
      <label language="zh_CN">yxq2</label> 
    </has-relation>  
    <has-relation relation="yxq1" size="20">
      <label language="zh_CN">yxq1</label> 
    </has-relation>  
    <has-relation relation="wctze" size="12" scale="2">
      <label language="zh_CN">wctze</label> 
    </has-relation>  
    <has-relation relation="zcze" size="12" scale="2">
      <label language="zh_CN">zcze</label> 
    </has-relation>  
    <has-relation relation="yskze" size="18" scale="2">
      <label language="zh_CN">yskze</label> 
    </has-relation>  
    <has-relation relation="BankConTel" size="40">
      <label language="zh_CN">BankConTel</label> 
    </has-relation>  
    <has-relation relation="Last_View">
      <label language="zh_CN">Last_View</label> 
    </has-relation>  
    <has-relation relation="MapID" size="50"/>  
    <has-relation relation="Reg_remark">
      <label language="zh_CN">Reg_remark</label> 
    </has-relation>  
    <has-relation relation="KPDate">
      <label language="zh_CN">KPDate</label> 
    </has-relation>  
    <has-relation relation="AutoID" required="true"/>  
    <has-relation relation="CaptureID" size="100"/>  
    <has-relation relation="flag" required="true">
      <label language="zh_CN">flag</label> 
    </has-relation>  
    <has-relation relation="PresellName_Fix" size="120">
      <label language="zh_CN">PresellName_Fix</label> 
    </has-relation>  
    <has-relation relation="st_flag" required="true"/>  
    <has-relation relation="TDSYQZH" size="180">
      <label language="zh_CN">TDSYQZH</label> 
    </has-relation>  
    <has-relation relation="LxrTel" size="50">
      <label language="zh_CN">LxrTel</label> 
    </has-relation>  
    <has-relation relation="scfzsj">
      <label language="zh_CN">scfzsj</label> 
    </has-relation>  
    <has-relation relation="qhbm" size="50">
      <label language="zh_CN">qhbm</label> 
    </has-relation>  
    <has-relation relation="fid" size="50"/>  
    <has-relation relation="LoginUnitID" size="50"/>  
    <has-relation relation="IsGetFee" data-type="Decimal" size="1"/> 
  </concept>  
  <relation name="KPDate" data-type="DateTime">
    <label language="zh_CN">KPDate</label> 
  </relation>  
  <relation name="LXR" data-type="String">
    <label language="zh_CN">LXR</label> 
  </relation>  
  <relation name="Second_date" data-type="DateTime">
    <label language="zh_CN">Second_date</label> 
  </relation>  
  <relation name="TSTel" data-type="String">
    <label language="zh_CN">TSTel</label> 
  </relation>  
  <relation name="BeginDate" data-type="DateTime">
    <label language="zh_CN">BeginDate</label> 
  </relation>  
  <relation name="MapID" data-type="String">
    <label language="zh_CN">MapID</label> 
  </relation>  
  <relation name="obligeenation" data-type="String">
    <label language="zh_CN">obligeenation</label> 
  </relation>  
  <relation name="ZJE" data-type="Decimal">
    <label language="zh_CN">ZJE</label> 
  </relation>  
  <relation name="KFSZH" data-type="String">
    <label language="zh_CN">KFSZH</label> 
  </relation>  
  <relation name="OfficeArea" data-type="Float">
    <label language="zh_CN">OfficeArea</label> 
  </relation>  
  <relation name="wctze" data-type="Decimal">
    <label language="zh_CN">wctze</label> 
  </relation>  
  <relation name="Second_Name" data-type="String">
    <label language="zh_CN">Second_Name</label> 
  </relation>  
  <relation name="PaperCourse" data-type="String">
    <label language="zh_CN">PaperCourse</label> 
  </relation>  
  <relation name="ViewAmout" data-type="Integer">
    <label language="zh_CN">ViewAmout</label> 
  </relation>  
  <relation name="HouseCount">
    <label language="zh_CN">HouseCount</label> 
  </relation>  
  <relation name="BankConTel" data-type="String">
    <label language="zh_CN">BankConTel</label> 
  </relation>  
  <relation name="Ant_pnc">
    <label language="zh_CN">Ant_pnc</label> 
  </relation>  
  <relation name="project_caa">
    <label language="zh_CN">project_caa</label> 
  </relation>  
  <relation name="PlanPresellDateEnd" data-type="DateTime">
    <label language="zh_CN">PlanPresellDateEnd</label> 
  </relation>  
  <relation name="safe_Oversee">
    <label language="zh_CN">safe_Oversee</label> 
  </relation>  
  <relation name="UseDateEnd" data-type="DateTime">
    <label language="zh_CN">UseDateEnd</label> 
  </relation>  
  <relation name="ShangYeArea" data-type="Float">
    <label language="zh_CN">ShangYeArea</label> 
  </relation>  
  <relation name="First_Pass">
    <label language="zh_CN">First_Pass</label> 
  </relation>  
  <relation name="BuildedCount" data-type="String">
    <label language="zh_CN">BuildedCount</label> 
  </relation>  
  <relation name="moneytype" data-type="String">
    <label language="zh_CN">moneytype</label> 
  </relation>  
  <relation name="ManagerHT">
    <label language="zh_CN">ManagerHT</label> 
  </relation>  
  <relation name="tbDate" data-type="DateTime">
    <label language="zh_CN">tbDate</label> 
  </relation>  
  <relation name="Last_View" data-type="Text">
    <label language="zh_CN">Last_View</label> 
  </relation>  
  <relation name="scfzsj" data-type="DateTime">
    <label language="zh_CN">scfzsj</label> 
  </relation>  
  <relation name="last_date" data-type="DateTime">
    <label language="zh_CN">last_date</label> 
  </relation>  
  <relation name="FZDate" data-type="DateTime">
    <label language="zh_CN">FZDate</label> 
  </relation>  
  <relation name="ArchiveId" data-type="String">
    <label language="zh_CN">ArchiveId</label> 
  </relation>  
  <relation name="Others1Amout" data-type="Integer">
    <label language="zh_CN">Others1Amout</label> 
  </relation>  
  <relation name="PlanPresellDateBegin" data-type="DateTime">
    <label language="zh_CN">PlanPresellDateBegin</label> 
  </relation>  
  <relation name="houseuse" data-type="String">
    <label language="zh_CN">houseuse</label> 
  </relation>  
  <relation name="ShangYeCount">
    <label language="zh_CN">ShangYeCount</label> 
  </relation>  
  <relation name="UseDate" data-type="String">
    <label language="zh_CN">UseDate</label> 
  </relation>  
  <relation name="ExaApprove" data-type="Text">
    <label language="zh_CN">ExaApprove</label> 
  </relation>  
  <relation name="Auditing" data-type="Text">
    <label language="zh_CN">Auditing</label> 
  </relation>  
  <relation name="Second_Pass">
    <label language="zh_CN">Second_Pass</label> 
  </relation>  
  <relation name="Permit_Engineering" data-type="String">
    <label language="zh_CN">Permit_Engineering</label> 
  </relation>  
  <relation name="FZDateBegin" data-type="String">
    <label language="zh_CN">FZDateBegin</label> 
  </relation>  
  <relation name="Reg_remark" data-type="Text">
    <label language="zh_CN">Reg_remark</label> 
  </relation>  
  <relation name="FZOrg" data-type="String">
    <label language="zh_CN">FZOrg</label> 
  </relation>  
  <relation name="FirstTrial" data-type="Text">
    <label language="zh_CN">FirstTrial</label> 
  </relation>  
  <relation name="qhbm" data-type="String">
    <label language="zh_CN">qhbm</label> 
  </relation>  
  <relation name="First_Name" data-type="String">
    <label language="zh_CN">First_Name</label> 
  </relation>  
  <relation name="PresellName_Fix" data-type="String">
    <label language="zh_CN">PresellName_Fix</label> 
  </relation>  
  <relation name="BuildHT">
    <label language="zh_CN">BuildHT</label> 
  </relation>  
  <relation name="Permit_LandPlanning" data-type="String">
    <label language="zh_CN">Permit_LandPlanning</label> 
  </relation>  
  <relation name="LandBook">
    <label language="zh_CN">LandBook</label> 
  </relation>  
  <relation name="yxq1" data-type="String">
    <label language="zh_CN">yxq1</label> 
  </relation>  
  <relation name="ProgramBook">
    <label language="zh_CN">ProgramBook</label> 
  </relation>  
  <relation name="Update_date" data-type="DateTime">
    <label language="zh_CN">Update_date</label> 
  </relation>  
  <relation name="ManagerCom" data-type="String">
    <label language="zh_CN">ManagerCom</label> 
  </relation>  
  <relation name="TDSYQZH" data-type="String">
    <label language="zh_CN">TDSYQZH</label> 
  </relation>  
  <relation name="Presell_declare">
    <label language="zh_CN">Presell_declare</label> 
  </relation>  
  <relation name="businessBook">
    <label language="zh_CN">businessBook</label> 
  </relation>  
  <relation name="Manager_View" data-type="Text">
    <label language="zh_CN">Manager_View</label> 
  </relation>  
  <relation name="yxq2" data-type="String">
    <label language="zh_CN">yxq2</label> 
  </relation>  
  <relation name="IsEdit">
    <label language="zh_CN">IsEdit</label> 
  </relation>  
  <relation name="CorpPhone" data-type="String">
    <label language="zh_CN">CorpPhone</label> 
  </relation>  
  <relation name="aptitudeBook">
    <label language="zh_CN">aptitudeBook</label> 
  </relation>  
  <relation name="CaptureID" data-type="String">
    <label language="zh_CN">CaptureID</label> 
  </relation>  
  <relation name="PreSellBookId" data-type="String">
    <label language="zh_CN">PreSellBookId</label> 
  </relation>  
  <relation name="yskze" data-type="Decimal">
    <label language="zh_CN">yskze</label> 
  </relation>  
  <relation name="Presell_BeginDate" data-type="DateTime">
    <label language="zh_CN">Presell_BeginDate</label> 
  </relation>  
  <relation name="FZDateEnd" data-type="String">
    <label language="zh_CN">FZDateEnd</label> 
  </relation>  
  <relation name="zcze" data-type="Decimal">
    <label language="zh_CN">zcze</label> 
  </relation>  
  <relation name="ManagerCharge" data-type="Decimal">
    <label language="zh_CN">ManagerCharge</label> 
  </relation>  
  <relation name="OfficeAmout" data-type="Integer">
    <label language="zh_CN">OfficeAmout</label> 
  </relation>  
  <relation name="Part" data-type="String">
    <label language="zh_CN">Part</label> 
  </relation>  
  <relation name="Housedj" data-type="Decimal">
    <label language="zh_CN">Housedj</label> 
  </relation>  
  <relation name="Presell_EndDate" data-type="DateTime">
    <label language="zh_CN">Presell_EndDate</label> 
  </relation>  
  <relation name="UseDateBegin" data-type="DateTime">
    <label language="zh_CN">UseDateBegin</label> 
  </relation>  
  <relation name="TDBH" data-type="String">
    <label language="zh_CN">TDBH</label> 
  </relation>  
  <relation name="Permit_Construction" data-type="String">
    <label language="zh_CN">Permit_Construction</label> 
  </relation>  
  <relation name="Last_Name" data-type="String">
    <label language="zh_CN">Last_Name</label> 
  </relation>  
  <relation name="PresellBasic">
    <label language="zh_CN">PresellBasic</label> 
  </relation>  
  <relation name="gcyszj" data-type="Decimal">
    <label language="zh_CN">gcyszj</label> 
  </relation>  
  <relation name="BuildBook">
    <label language="zh_CN">BuildBook</label> 
  </relation>  
  <relation name="IsPass">
    <label language="zh_CN">IsPass</label> 
  </relation>  
  <relation name="ZhuZhaiCount">
    <label language="zh_CN">ZhuZhaiCount</label> 
  </relation>  
  <relation name="Last_Pass">
    <label language="zh_CN">Last_Pass</label> 
  </relation>  
  <relation name="Others1" data-type="Float">
    <label language="zh_CN">Others1</label> 
  </relation>  
  <relation name="ZhuzhaiArea" data-type="Float">
    <label language="zh_CN">ZhuzhaiArea</label> 
  </relation>  
  <relation name="FirstTrialDate" data-type="DateTime">
    <label language="zh_CN">FirstTrialDate</label> 
  </relation>  
  <relation name="Presellname" data-type="String">
    <label language="zh_CN">Presellname</label> 
  </relation>  
  <relation name="CCount" data-type="String">
    <label language="zh_CN">CCount</label> 
  </relation>  
  <relation name="LxrTel" data-type="String">
    <label language="zh_CN">LxrTel</label> 
  </relation>  
  <relation name="IsDiYa" data-type="String">
    <label language="zh_CN">IsDiYa</label> 
  </relation>  
  <relation name="KFS_View" data-type="Text">
    <label language="zh_CN">KFS_View</label> 
  </relation>  
  <relation name="PresellBook">
    <label language="zh_CN">PresellBook</label> 
  </relation>  
  <relation name="AutoID" data-type="Integer">
    <label language="zh_CN">AutoID</label> 
  </relation>  
  <relation name="PresellBank">
    <label language="zh_CN">PresellBank</label> 
  </relation>  
  <concept name="HousePresellDetail" default-value-expr="guid()" keys="Autoid">
    <has-relation relation="HouseId" size="120"/>  
    <has-relation relation="Status" size="30"/>  
    <has-relation relation="HouseArea" size="38" scale="4">
      <label language="zh_CN">HouseArea</label> 
    </has-relation>  
    <has-relation relation="CarPortId" size="180">
      <label language="zh_CN">CarPortId</label> 
    </has-relation>  
    <has-relation relation="CarPortArea" size="38" scale="2">
      <label language="zh_CN">CarPortArea</label> 
    </has-relation>  
    <has-relation relation="Obligee" size="150"/>  
    <has-relation relation="PayKind" size="150">
      <label language="zh_CN">PayKind</label> 
    </has-relation>  
    <has-relation relation="HouseWorth" size="18" scale="2">
      <label language="zh_CN">HouseWorth</label> 
    </has-relation>  
    <has-relation relation="VisaDate">
      <label language="zh_CN">VisaDate</label> 
    </has-relation>  
    <has-relation relation="RegDate"/>  
    <has-relation relation="Remark" size="200"/>  
    <has-relation relation="sc" size="100">
      <label language="zh_CN">sc</label> 
    </has-relation>  
    <has-relation relation="scdj" size="18" scale="2">
      <label language="zh_CN">scdj</label> 
    </has-relation>  
    <has-relation relation="jc" size="120">
      <label language="zh_CN">jc</label> 
    </has-relation>  
    <has-relation relation="jcdj" size="18" scale="2">
      <label language="zh_CN">jcdj</label> 
    </has-relation>  
    <has-relation relation="Housedj" size="18" scale="2"/>  
    <has-relation relation="lb2" size="50"/>  
    <has-relation relation="obligeenation" size="50"/>  
    <has-relation relation="houseuse" size="50"/>  
    <has-relation relation="housetype" size="50">
      <label language="zh_CN">housetype</label> 
    </has-relation>  
    <has-relation relation="HTBH" size="80">
      <label language="zh_CN">HTBH</label> 
    </has-relation>  
    <has-relation relation="CardID" size="200">
      <label language="zh_CN">CardID</label> 
    </has-relation>  
    <has-relation relation="CMR" size="200">
      <label language="zh_CN">CMR</label> 
    </has-relation>  
    <has-relation relation="Buyer" size="150">
      <label language="zh_CN">Buyer</label> 
    </has-relation>  
    <has-relation relation="BuyerAddr" size="250">
      <label language="zh_CN">BuyerAddr</label> 
    </has-relation>  
    <has-relation relation="ZJE" size="18" scale="2"/>  
    <has-relation relation="FKFS" size="150">
      <label language="zh_CN">FKFS</label> 
    </has-relation>  
    <has-relation relation="JFQX" size="150">
      <label language="zh_CN">JFQX</label> 
    </has-relation>  
    <has-relation relation="password" size="50"/>  
    <has-relation relation="beifen" size="10">
      <label language="zh_CN">beifen</label> 
    </has-relation>  
    <has-relation relation="HUxing" size="50">
      <label language="zh_CN">HUxing</label> 
    </has-relation>  
    <has-relation relation="JisuanType" size="20">
      <label language="zh_CN">JisuanType</label> 
    </has-relation>  
    <has-relation relation="SumBuildArea1" size="38" scale="4">
      <label language="zh_CN">SumBuildArea1</label> 
    </has-relation>  
    <has-relation relation="Ceng" size="80"/>  
    <has-relation relation="fqfkyfhj" size="18" scale="2">
      <label language="zh_CN">fqfkyfhj</label> 
    </has-relation>  
    <has-relation relation="fqfksfhj" size="18" scale="2">
      <label language="zh_CN">fqfksfhj</label> 
    </has-relation>  
    <has-relation relation="fqfkrq" size="18" scale="2">
      <label language="zh_CN">fqfkrq</label> 
    </has-relation>  
    <has-relation relation="HTFS" size="18" scale="2">
      <label language="zh_CN">HTFS</label> 
    </has-relation>  
    <has-relation relation="ManagerCharge" size="18" scale="2"/>  
    <has-relation relation="cfflag"/>  
    <has-relation relation="txqflag" size="50"/>  
    <has-relation relation="zxflag" size="20"/>  
    <has-relation relation="CHX" size="40">
      <label language="zh_CN">CHX</label> 
    </has-relation>  
    <has-relation relation="msrepl_tran_version" required="true"/>  
    <has-relation relation="NewHouseId" size="120">
      <label language="zh_CN">NewHouseId</label> 
    </has-relation>  
    <has-relation relation="Limit" size="20"/>  
    <has-relation relation="DeclarationPrice"/>  
    <has-relation relation="DeclarationZJE"/>  
    <has-relation relation="HouseRepose" size="500"/>  
    <has-relation relation="flag" required="true"/>  
    <has-relation relation="zbywbz">
      <label language="zh_CN">zbywbz</label> 
    </has-relation>  
    <has-relation relation="zbywlx" size="100">
      <label language="zh_CN">zbywlx</label> 
    </has-relation>  
    <has-relation relation="mmhtzt">
      <label language="zh_CN">mmhtzt</label> 
    </has-relation>  
    <has-relation relation="itemno" size="120"/>  
    <has-relation relation="dbh"/>  
    <has-relation relation="XQMC" size="100"/>  
    <has-relation relation="XQBM" size="50"/>  
    <has-relation relation="st_flag" required="true"/>  
    <has-relation relation="st_flag2" required="true">
      <label language="zh_CN">st_flag2</label> 
    </has-relation>  
    <has-relation relation="SumBuildArea1_ch" size="38" scale="4">
      <label language="zh_CN">SumBuildArea1_ch</label> 
    </has-relation>  
    <has-relation relation="HouseArea_ch" size="38" scale="4">
      <label language="zh_CN">HouseArea_ch</label> 
    </has-relation>  
    <has-relation relation="Limit_ch" size="20">
      <label language="zh_CN">Limit_ch</label> 
    </has-relation>  
    <has-relation relation="SHBW" size="20"/>  
    <has-relation relation="Is_chyy"/>  
    <label language="zh_CN"/>  
    <has-relation relation="LoginUnitID" size="50"/>  
    <has-relation relation="HouseNo" size="180"/>  
    <has-relation relation="DongNo" size="100"/>  
    <has-relation relation="Autoid" size="18"/>  
    <has-relation relation="FID" size="50"/> 
  </concept>  
  <relation name="CHX" data-type="String">
    <label language="zh_CN">CHX</label> 
  </relation>  
  <relation name="TradeQsFlag">
    <label language="zh_CN">TradeQsFlag</label> 
  </relation>  
  <relation name="CardID" data-type="String">
    <label language="zh_CN">CardID</label> 
  </relation>  
  <relation name="JFQX" data-type="String">
    <label language="zh_CN">JFQX</label> 
  </relation>  
  <relation name="Limit_ch" data-type="String">
    <label language="zh_CN">Limit_ch</label> 
  </relation>  
  <relation name="HouseArea_ch" data-type="Decimal">
    <label language="zh_CN">HouseArea_ch</label> 
  </relation>  
  <relation name="HouseWorth" data-type="Decimal">
    <label language="zh_CN">HouseWorth</label> 
  </relation>  
  <relation name="st_flag2" data-type="Integer">
    <label language="zh_CN">st_flag2</label> 
  </relation>  
  <relation name="zxflag" data-type="String">
    <label language="zh_CN">zxflag</label> 
  </relation>  
  <relation name="beifen" data-type="String">
    <label language="zh_CN">beifen</label> 
  </relation>  
  <relation name="CarPortId" data-type="String">
    <label language="zh_CN">CarPortId</label> 
  </relation>  
  <relation name="password" data-type="String">
    <label language="zh_CN">password</label> 
  </relation>  
  <relation name="fqfksfhj" data-type="Decimal">
    <label language="zh_CN">fqfksfhj</label> 
  </relation>  
  <relation name="VisaDate" data-type="DateTime">
    <label language="zh_CN">VisaDate</label> 
  </relation>  
  <relation name="zbywbz" data-type="Integer">
    <label language="zh_CN">zbywbz</label> 
  </relation>  
  <relation name="FKFS" data-type="String">
    <label language="zh_CN">FKFS</label> 
  </relation>  
  <relation name="scdj" data-type="Decimal">
    <label language="zh_CN">scdj</label> 
  </relation>  
  <relation name="PayKind" data-type="String">
    <label language="zh_CN">PayKind</label> 
  </relation>  
  <relation name="JisuanType" data-type="String">
    <label language="zh_CN">JisuanType</label> 
  </relation>  
  <relation name="HouseArea" data-type="Decimal">
    <label language="zh_CN">HouseArea</label> 
  </relation>  
  <relation name="CMR" data-type="String">
    <label language="zh_CN">CMR</label> 
  </relation>  
  <relation name="Buyer" data-type="String">
    <label language="zh_CN">Buyer</label> 
  </relation>  
  <relation name="NewHouseId" data-type="String">
    <label language="zh_CN">NewHouseId</label> 
  </relation>  
  <relation name="CarPortArea" data-type="Decimal">
    <label language="zh_CN">CarPortArea</label> 
  </relation>  
  <relation name="SumBuildArea1_ch" data-type="Decimal">
    <label language="zh_CN">SumBuildArea1_ch</label> 
  </relation>  
  <relation name="sc" data-type="String">
    <label language="zh_CN">sc</label> 
  </relation>  
  <relation name="SumBuildArea1" data-type="Decimal">
    <label language="zh_CN">SumBuildArea1</label> 
  </relation>  
  <relation name="zbywlx" data-type="String">
    <label language="zh_CN">zbywlx</label> 
  </relation>  
  <relation name="jc" data-type="String">
    <label language="zh_CN">jc</label> 
  </relation>  
  <relation name="HTFS" data-type="Decimal">
    <label language="zh_CN">HTFS</label> 
  </relation>  
  <relation name="housetype" data-type="String">
    <label language="zh_CN">housetype</label> 
  </relation>  
  <relation name="fqfkrq" data-type="DateTime">
    <label language="zh_CN">fqfkrq</label> 
  </relation>  
  <relation name="BuyerAddr" data-type="String">
    <label language="zh_CN">BuyerAddr</label> 
  </relation>  
  <relation name="HTBH" data-type="String">
    <label language="zh_CN">HTBH</label> 
  </relation>  
  <relation name="fqfkyfhj" data-type="Decimal">
    <label language="zh_CN">fqfkyfhj</label> 
  </relation>  
  <relation name="jcdj" data-type="Decimal">
    <label language="zh_CN">jcdj</label> 
  </relation>  
  <relation name="itemno" data-type="String">
    <label language="zh_CN">itemno</label> 
  </relation>  
  <relation name="mmhtzt" data-type="Integer">
    <label language="zh_CN">mmhtzt</label> 
  </relation>  
  <relation name="HUxing" data-type="String">
    <label language="zh_CN">HUxing</label> 
  </relation>  
  <concept name="T_HousePledgeDetail" default-value-expr="guid()" keys="fid:LoginUnitID">
    <has-relation relation="ffId" size="50">
      <label language="zh_CN">ffId</label> 
    </has-relation>  
    <has-relation relation="PledgeStatus" size="50">
      <label language="zh_CN">PledgeStatus</label> 
    </has-relation>  
    <has-relation relation="HouseId" size="50"/>  
    <has-relation relation="HouseNo" size="50"/>  
    <has-relation relation="HousePrice" size="18" scale="2">
      <label language="zh_CN">房屋价格</label> 
    </has-relation>  
    <has-relation relation="HouseRepose" size="250"/>  
    <has-relation relation="DISTRICT" size="50"/>  
    <has-relation relation="Path" size="50"/>  
    <has-relation relation="HouseObligee" size="100">
      <label language="zh_CN">权属人</label> 
    </has-relation>  
    <has-relation relation="CarNo" size="20">
      <label language="zh_CN">车房号</label> 
    </has-relation>  
    <has-relation relation="HouseType" size="50">
      <label language="zh_CN">房屋类型</label> 
    </has-relation>  
    <has-relation relation="HouseUse" size="50"/>  
    <has-relation relation="HouseFrame" size="50"/>  
    <has-relation relation="DroitKind" size="50"/>  
    <has-relation relation="DroitSource" size="100"/>  
    <has-relation relation="FloorCount" size="20">
      <label language="zh_CN">总层数</label> 
    </has-relation>  
    <has-relation relation="Floor" size="50" data-type="String"/>  
    <has-relation relation="CompleteDate"/>  
    <has-relation relation="BuildBaseArea" size="18" scale="2"/>  
    <has-relation relation="SumbuildArea" size="18" scale="2"/>  
    <has-relation relation="UseArea" size="18" scale="2"/>  
    <has-relation relation="UseBuildArea" size="18" scale="2"/>  
    <has-relation relation="UseCommArea" size="18" scale="2">
      <label language="zh_CN">住宅分摊面积</label> 
    </has-relation>  
    <has-relation relation="NoUseArea" size="18" scale="2"/>  
    <has-relation relation="NoUseBuildArea" size="18" scale="2">
      <label language="zh_CN">NoUseBuildArea</label> 
    </has-relation>  
    <has-relation relation="NoUseCommArea" size="18" scale="2">
      <label language="zh_CN">NoUseCommArea</label> 
    </has-relation>  
    <has-relation relation="HouseBookFID" size="50">
      <label language="zh_CN">HouseBookFID</label> 
    </has-relation>  
    <has-relation relation="HouseBookID" size="50"/>  
    <has-relation relation="BuildNo" size="50">
      <label language="zh_CN">栋号</label> 
    </has-relation>  
    <has-relation relation="FramingNo" size="6"/>  
    <has-relation relation="HillNo" size="4"/>  
    <has-relation relation="HorPoint" size="6"/>  
    <has-relation relation="VerPoint" size="6"/>  
    <has-relation relation="Remark" size="1000"/>  
    <has-relation relation="fNo1" size="50">
      <label language="zh_CN">fNo1</label> 
    </has-relation>  
    <has-relation relation="cqdj_fwcqsn" size="50">
      <label language="zh_CN">cqdj_fwcqsn</label> 
    </has-relation>  
    <has-relation relation="cqjy_fwfhbsn" size="50">
      <label language="zh_CN">cqjy_fwfhbsn</label> 
    </has-relation>  
    <has-relation relation="CQZH" size="50"/>  
    <has-relation relation="IsZxflag" data-type="Decimal" size="1">
      <label language="zh_CN">IsZxflag</label> 
    </has-relation>  
    <has-relation relation="DsFloor" size="50">
      <label language="zh_CN">DsFloor</label> 
    </has-relation>  
    <has-relation relation="DxFloor" size="50">
      <label language="zh_CN">DxFloor</label> 
    </has-relation>  
    <has-relation relation="ZztnArea" size="18" scale="2">
      <label language="zh_CN">ZztnArea</label> 
    </has-relation>  
    <has-relation relation="IsPrint" data-type="Decimal" size="1"/>  
    <has-relation relation="TxqZsbh" size="50">
      <label language="zh_CN">TxqZsbh</label> 
    </has-relation>  
    <has-relation relation="Isgjxz" size="1">
      <label language="zh_CN">Isgjxz</label> 
    </has-relation>  
    <has-relation relation="fid" size="50"/>  
    <has-relation relation="LoginUnitID" size="50"/>  
    <label language="zh_CN">抵押明细</label> 
  </concept>  
  <relation name="cqjy_fwfhbsn" data-type="String">
    <label language="zh_CN">cqjy_fwfhbsn</label> 
  </relation>  
  <relation name="HouseType" data-type="String">
    <label language="zh_CN">房屋类型</label> 
  </relation>  
  <relation name="Isgjxz" data-type="String">
    <label language="zh_CN">Isgjxz</label> 
  </relation>  
  <relation name="UseCommArea" data-type="Decimal">
    <label language="zh_CN">住宅分摊面积</label> 
  </relation>  
  <relation name="NoUseCommArea" data-type="Decimal">
    <label language="zh_CN">NoUseCommArea</label> 
  </relation>  
  <relation name="IsZxflag">
    <label language="zh_CN">IsZxflag</label> 
  </relation>  
  <relation name="FloorCount" data-type="String">
    <label language="zh_CN">总层数</label> 
  </relation>  
  <relation name="HouseBookFID" data-type="String">
    <label language="zh_CN">HouseBookFID</label> 
  </relation>  
  <relation name="DxFloor" data-type="String">
    <label language="zh_CN">DxFloor</label> 
  </relation>  
  <relation name="ZztnArea" data-type="Decimal">
    <label language="zh_CN">ZztnArea</label> 
  </relation>  
  <relation name="CarNo" data-type="String">
    <label language="zh_CN">车房号</label> 
  </relation>  
  <relation name="NoUseBuildArea" data-type="Decimal">
    <label language="zh_CN">NoUseBuildArea</label> 
  </relation>  
  <relation name="cqdj_fwcqsn" data-type="String">
    <label language="zh_CN">cqdj_fwcqsn</label> 
  </relation>  
  <relation name="PledgeStatus" data-type="String">
    <label language="zh_CN">PledgeStatus</label> 
  </relation>  
  <relation name="HousePrice" data-type="Decimal">
    <label language="zh_CN">房屋价格</label> 
  </relation>  
  <relation name="DsFloor" data-type="String">
    <label language="zh_CN">DsFloor</label> 
  </relation>  
  <relation name="BuildNo" data-type="String">
    <label language="zh_CN">栋号</label> 
  </relation>  
  <relation name="ffId" data-type="String">
    <label language="zh_CN">ffId</label> 
  </relation>  
  <relation name="HouseObligee" data-type="String">
    <label language="zh_CN">权属人</label> 
  </relation>  
  <relation name="TxqZsbh" data-type="String">
    <label language="zh_CN">TxqZsbh</label> 
  </relation>  
  <relation name="fNo1" data-type="String">
    <label language="zh_CN">fNo1</label> 
  </relation>  
  <concept name="T_HousePledge" default-value-expr="guid()" keys="fid">
    <has-relation relation="HouseId" size="50"/>  
    <has-relation relation="BuildingID" size="10"/>  
    <has-relation relation="BookId" size="50"/>  
    <has-relation relation="type_no" size="50"/>  
    <has-relation relation="fNo" size="50"/>  
    <has-relation relation="SZNo"/>  
    <has-relation relation="RegDate"/>  
    <has-relation relation="ArchiveId" size="50"/>  
    <has-relation relation="DocDate"/>  
    <has-relation relation="CaptureId" size="50"/>  
    <has-relation relation="CreateDate"/>  
    <has-relation relation="CreateMan" size="50"/>  
    <has-relation relation="Status" size="50"/>  
    <has-relation relation="BookStatus" size="50"/>  
    <has-relation relation="fType" size="50"/>  
    <has-relation relation="AddressAndPhone" size="50">
      <label language="zh_CN">权利人地址和电话</label> 
    </has-relation>  
    <has-relation relation="HouseRepose" size="3000"/>  
    <has-relation relation="DISTRICT" size="50"/>  
    <has-relation relation="DroitSort" size="50">
      <label language="zh_CN">权利种类</label> 
    </has-relation>  
    <has-relation relation="DroitRange" size="50">
      <label language="zh_CN">权利范围</label> 
    </has-relation>  
    <has-relation relation="SumBuildAreaDx" size="18" scale="2">
      <label language="zh_CN">SumBuildAreaDx</label> 
    </has-relation>  
    <has-relation relation="TaxMoney" size="18" scale="2">
      <label language="zh_CN">纳税金额</label> 
    </has-relation>  
    <has-relation relation="DroitTimeLimit" size="30">
      <label language="zh_CN">权利存续期</label> 
    </has-relation>  
    <has-relation relation="DroitStartDate">
      <label language="zh_CN">权利开始日期</label> 
    </has-relation>  
    <has-relation relation="DroitEndDate">
      <label language="zh_CN">权利结束日期</label> 
    </has-relation>  
    <has-relation relation="BuildBaseArea" size="18" scale="2"/>  
    <has-relation relation="SumBuildArea" size="18" scale="2"/>  
    <has-relation relation="useArea" size="18" scale="2"/>  
    <has-relation relation="useBuildArea" size="18" scale="2"/>  
    <has-relation relation="Producer" size="50"/>  
    <has-relation relation="collator" size="50"/>  
    <has-relation relation="provider" size="50"/>  
    <has-relation relation="Geter" size="1000"/>  
    <has-relation relation="GetDate"/>  
    <has-relation relation="Remark" size="2000"/>  
    <has-relation relation="IsGetFee" data-type="Decimal" size="1"/>  
    <has-relation relation="DueDate"/>  
    <has-relation relation="PledgeExplain">
      <label language="zh_CN">抵押说明</label> 
    </has-relation>  
    <has-relation relation="producerdate"/>  
    <has-relation relation="bookname" size="50"/>  
    <has-relation relation="Tel" size="30"/>  
    <has-relation relation="DroitWorth" size="18" scale="2">
      <label language="zh_CN">权利价值</label> 
    </has-relation>  
    <has-relation relation="DeclareWorth" size="18" scale="2">
      <label language="zh_CN">DeclareWorth</label> 
    </has-relation>  
    <has-relation relation="EvaluateWorth" size="18" scale="2">
      <label language="zh_CN">EvaluateWorth</label> 
    </has-relation>  
    <has-relation relation="DkMoney" size="18" scale="2">
      <label language="zh_CN">贷款金额</label> 
    </has-relation>  
    <has-relation relation="ZxDate" size="18">
      <label language="zh_CN">ZxDate</label> 
    </has-relation>  
    <has-relation relation="Qlr" size="200">
      <label language="zh_CN">权利人</label> 
    </has-relation>  
    <has-relation relation="QlrType" size="50">
      <label language="zh_CN">权利人类型</label> 
    </has-relation>  
    <has-relation relation="QlrSex" size="2">
      <label language="zh_CN">权利人性别</label> 
    </has-relation>  
    <has-relation relation="QlrNation" size="20">
      <label language="zh_CN">权利人国籍</label> 
    </has-relation>  
    <has-relation relation="QlrCardType" size="20">
      <label language="zh_CN">权利人证件类型</label> 
    </has-relation>  
    <has-relation relation="QlrCardID" size="300">
      <label language="zh_CN">权利人证件号码</label> 
    </has-relation>  
    <has-relation relation="LawPerson" size="200"/>  
    <has-relation relation="LawPersonType" size="50">
      <label language="zh_CN">LawPersonType</label> 
    </has-relation>  
    <has-relation relation="LawPersonSex" size="2"/>  
    <has-relation relation="LawPersonNation" size="20"/>  
    <has-relation relation="LawPersonCardType" size="20">
      <label language="zh_CN">LawPersonCardType</label> 
    </has-relation>  
    <has-relation relation="LawPersonCardID" size="50"/>  
    <has-relation relation="SupplyPerson" size="200">
      <label language="zh_CN">代理人姓名</label> 
    </has-relation>  
    <has-relation relation="SupplyPersonType" size="50">
      <label language="zh_CN">SupplyPersonType</label> 
    </has-relation>  
    <has-relation relation="SupplyPersonSex" size="2">
      <label language="zh_CN">SupplyPersonSex</label> 
    </has-relation>  
    <has-relation relation="SupplyPersonNation" size="20">
      <label language="zh_CN">SupplyPersonNation</label> 
    </has-relation>  
    <has-relation relation="SupplyPersonCardType" size="20">
      <label language="zh_CN">SupplyPersonCardType</label> 
    </has-relation>  
    <has-relation relation="SupplyPersonCardID" size="50">
      <label language="zh_CN">SupplyPersonCardID</label> 
    </has-relation>  
    <has-relation relation="Obligee" size="200"/>  
    <has-relation relation="ObligeeType" size="50">
      <label language="zh_CN">产权人类型</label> 
    </has-relation>  
    <has-relation relation="ObligeeSex" size="2"/>  
    <has-relation relation="ObligeeNation" size="20"/>  
    <has-relation relation="ObligeeCardType" size="20">
      <label language="zh_CN">产权人证件类型</label> 
    </has-relation>  
    <has-relation relation="ObligeeCardID" size="300">
      <label language="zh_CN">产权人证件号码</label> 
    </has-relation>  
    <has-relation relation="Dbr" size="200">
      <label language="zh_CN">担保人</label> 
    </has-relation>  
    <has-relation relation="DbrType" size="50">
      <label language="zh_CN">DbrType</label> 
    </has-relation>  
    <has-relation relation="DbrSex" size="2">
      <label language="zh_CN">DbrSex</label> 
    </has-relation>  
    <has-relation relation="DbrNation" size="20">
      <label language="zh_CN">DbrNation</label> 
    </has-relation>  
    <has-relation relation="DbrCardType" size="20">
      <label language="zh_CN">DbrCardType</label> 
    </has-relation>  
    <has-relation relation="DbrCardID" size="50">
      <label language="zh_CN">DbrCardID</label> 
    </has-relation>  
    <has-relation relation="Jkr" size="2000">
      <label language="zh_CN">Jkr</label> 
    </has-relation>  
    <has-relation relation="JkrType" size="50">
      <label language="zh_CN">JkrType</label> 
    </has-relation>  
    <has-relation relation="JkrSex" size="2">
      <label language="zh_CN">JkrSex</label> 
    </has-relation>  
    <has-relation relation="JkrNation" size="20">
      <label language="zh_CN">JkrNation</label> 
    </has-relation>  
    <has-relation relation="JkrCardType" size="20">
      <label language="zh_CN">JkrCardType</label> 
    </has-relation>  
    <has-relation relation="JkrCardID" size="100">
      <label language="zh_CN">JkrCardID</label> 
    </has-relation>  
    <has-relation relation="MyRegDate">
      <label language="zh_CN">登记日期</label> 
    </has-relation>  
    <has-relation relation="lb2" size="50"/>  
    <has-relation relation="lb3" size="100">
      <label language="zh_CN">lb3</label> 
    </has-relation>  
    <has-relation relation="IsKb"/>  
    <has-relation relation="zxId" size="50"/>  
    <has-relation relation="IsFinish" data-type="Decimal" size="1"/>  
    <has-relation relation="Ideas" size="2147483647"/>  
    <has-relation relation="OldBookID" size="50">
      <label language="zh_CN">OldBookID</label> 
    </has-relation>  
    <has-relation relation="OldCaptureid" size="50">
      <label language="zh_CN">OldCaptureid</label> 
    </has-relation>  
    <has-relation relation="OldBookFid" size="50">
      <label language="zh_CN">OldBookFid</label> 
    </has-relation>  
    <has-relation relation="Autoid" required="true" size="18"/>  
    <has-relation relation="Proposer" size="500">
      <label language="zh_CN">Proposer</label> 
    </has-relation>  
    <has-relation relation="PresellBookId" size="100"/>  
    <has-relation relation="terraid" size="150"/>  
    <has-relation relation="mapid" size="200"/>  
    <has-relation relation="UsufructId" size="200"/>  
    <has-relation relation="Department" size="50"/>  
    <has-relation relation="LandGrade" size="50"/>  
    <has-relation relation="LandUseKind" size="50"/>  
    <has-relation relation="LandUse" size="50"/>  
    <has-relation relation="StartDate">
      <label language="zh_CN">StartDate</label> 
    </has-relation>  
    <has-relation relation="EndDate" data-type="DateTime"/>  
    <has-relation relation="UsufructKind" size="50"/>  
    <has-relation relation="LandUseSource" size="50"/>  
    <has-relation relation="Usufructer" size="50">
      <label language="zh_CN">Usufructer</label> 
    </has-relation>  
    <has-relation relation="UsufructArea" size="18" scale="2"/>  
    <has-relation relation="ContinueDate">
      <label language="zh_CN">ContinueDate</label> 
    </has-relation>  
    <has-relation relation="ContinueDateto">
      <label language="zh_CN">ContinueDateto</label> 
    </has-relation>  
    <has-relation relation="SelfArea" size="18" scale="2"/>  
    <has-relation relation="CommArea" size="18" scale="2"/>  
    <has-relation relation="HouseBookID" size="500"/>  
    <has-relation relation="Feeje" size="18" scale="2"/>  
    <has-relation relation="taxtype" size="20">
      <label language="zh_CN">taxtype</label> 
    </has-relation>  
    <has-relation relation="taxprice" size="18" scale="2">
      <label language="zh_CN">taxprice</label> 
    </has-relation>  
    <has-relation relation="taxrate">
      <label language="zh_CN">taxrate</label> 
    </has-relation>  
    <has-relation relation="taxadditional" size="18" scale="2">
      <label language="zh_CN">taxadditional</label> 
    </has-relation>  
    <has-relation relation="taxindividual" size="18" scale="2">
      <label language="zh_CN">taxindividual</label> 
    </has-relation>  
    <has-relation relation="taxvalueadded" size="18" scale="2">
      <label language="zh_CN">taxvalueadded</label> 
    </has-relation>  
    <has-relation relation="landchvalue" size="18" scale="2">
      <label language="zh_CN">landchvalue</label> 
    </has-relation>  
    <has-relation relation="tradepart" size="50">
      <label language="zh_CN">tradepart</label> 
    </has-relation>  
    <has-relation relation="tradefeemode" size="20"/>  
    <has-relation relation="tradepaymentmode" size="50"/>  
    <has-relation relation="tradesigndate">
      <label language="zh_CN">tradesigndate</label> 
    </has-relation>  
    <has-relation relation="invoicedate">
      <label language="zh_CN">invoicedate</label> 
    </has-relation>  
    <has-relation relation="checktotalprice" size="18" scale="2"/>  
    <has-relation relation="checkprice" size="18" scale="2"/>  
    <has-relation relation="moneytype" size="60"/>  
    <has-relation relation="BargainNO" size="100">
      <label language="zh_CN">BargainNO</label> 
    </has-relation>  
    <has-relation relation="PurchaseHouseAddress" size="50">
      <label language="zh_CN">PurchaseHouseAddress</label> 
    </has-relation>  
    <has-relation relation="IsDeleted" required="true" data-type="Decimal" size="1"/>  
    <has-relation relation="LoginUnitID" required="true" size="50"/>  
    <has-relation relation="DroitSort_SN" size="8">
      <label language="zh_CN">DroitSort_SN</label> 
    </has-relation>  
    <has-relation relation="QlrType_SN" size="8">
      <label language="zh_CN">QlrType_SN</label> 
    </has-relation>  
    <has-relation relation="QlrNation_SN" size="8">
      <label language="zh_CN">QlrNation_SN</label> 
    </has-relation>  
    <has-relation relation="QlrCardType_SN" size="8">
      <label language="zh_CN">QlrCardType_SN</label> 
    </has-relation>  
    <has-relation relation="LawPersonNation_SN" size="8">
      <label language="zh_CN">LawPersonNation_SN</label> 
    </has-relation>  
    <has-relation relation="LawPersonCardType_SN" size="8">
      <label language="zh_CN">LawPersonCardType_SN</label> 
    </has-relation>  
    <has-relation relation="SupplyPersonNation_SN" size="8">
      <label language="zh_CN">SupplyPersonNation_SN</label> 
    </has-relation>  
    <has-relation relation="SupplyPersonCardType_SN" size="8">
      <label language="zh_CN">SupplyPersonCardType_SN</label> 
    </has-relation>  
    <has-relation relation="ObligeeType_SN" size="8">
      <label language="zh_CN">ObligeeType_SN</label> 
    </has-relation>  
    <has-relation relation="ObligeeNation_SN" size="8"/>  
    <has-relation relation="ObligeeCardType_SN" size="8">
      <label language="zh_CN">ObligeeCardType_SN</label> 
    </has-relation>  
    <has-relation relation="DbrType_SN" size="8">
      <label language="zh_CN">DbrType_SN</label> 
    </has-relation>  
    <has-relation relation="DbrNation_SN" size="8">
      <label language="zh_CN">DbrNation_SN</label> 
    </has-relation>  
    <has-relation relation="DbrCardType_SN" size="8">
      <label language="zh_CN">DbrCardType_SN</label> 
    </has-relation>  
    <has-relation relation="JkrType_SN" size="8">
      <label language="zh_CN">JkrType_SN</label> 
    </has-relation>  
    <has-relation relation="JkrNation_SN" size="8">
      <label language="zh_CN">JkrNation_SN</label> 
    </has-relation>  
    <has-relation relation="JkrCardType_SN" size="8">
      <label language="zh_CN">JkrCardType_SN</label> 
    </has-relation>  
    <has-relation relation="LandGrade_SN" size="50"/>  
    <has-relation relation="LandUseKind_SN" size="8"/>  
    <has-relation relation="LandUse_SN" size="50"/>  
    <has-relation relation="UsufructKind_SN" size="8"/>  
    <has-relation relation="LandUseSource_SN" size="8"/>  
    <has-relation relation="taxtype_SN" size="8">
      <label language="zh_CN">taxtype_SN</label> 
    </has-relation>  
    <has-relation relation="tradefeemode_SN" size="8">
      <label language="zh_CN">tradefeemode_SN</label> 
    </has-relation>  
    <has-relation relation="moneytype_SN" size="8">
      <label language="zh_CN">moneytype_SN</label> 
    </has-relation>  
    <has-relation relation="PurchaseHouseAddress_SN" size="8">
      <label language="zh_CN">PurchaseHouseAddress_SN</label> 
    </has-relation>  
    <has-relation relation="CaptureRemark" size="2000">
      <label language="zh_CN">CaptureRemark</label> 
    </has-relation>  
    <has-relation relation="PledgeStartDate">
      <label language="zh_CN">PledgeStartDate</label> 
    </has-relation>  
    <has-relation relation="PledgeEndDate">
      <label language="zh_CN">PledgeEndDate</label> 
    </has-relation>  
    <has-relation relation="djz" size="50"/>  
    <has-relation relation="TXQZH" size="100">
      <label language="zh_CN">TXQZH</label> 
    </has-relation>  
    <has-relation relation="HTBH_DY" size="100">
      <label language="zh_CN">HTBH_DY</label> 
    </has-relation>  
    <has-relation relation="HTBH_DYQX" size="100">
      <label language="zh_CN">HTBH_DYQX</label> 
    </has-relation>  
    <has-relation relation="HTBH_JK" size="100">
      <label language="zh_CN">HTBH_JK</label> 
    </has-relation>  
    <has-relation relation="HTBH_JKQX" size="100">
      <label language="zh_CN">HTBH_JKQX</label> 
    </has-relation>  
    <has-relation relation="AcceptanceMan" size="100"/>  
    <has-relation relation="RandomPassword" size="100"/>  
    <has-relation relation="HandCreateMan" size="100"/>  
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
    <has-relation relation="DKLX" size="50">
      <label language="zh_CN">DKLX</label> 
    </has-relation>  
    <has-relation relation="PGJG" size="50">
      <label language="zh_CN">PGJG</label> 
    </has-relation>  
    <has-relation relation="tdbz" size="500">
      <label language="zh_CN">tdbz</label> 
    </has-relation>  
    <has-relation relation="Email" size="50"/>  
    <has-relation relation="Unionfid" size="50">
      <label language="zh_CN">Unionfid</label> 
    </has-relation>  
    <has-relation relation="Wssqh" size="50"/>  
    <has-relation relation="fid" size="50"/> 
  </concept>  
  <relation name="DroitTimeLimit" data-type="String">
    <label language="zh_CN">权利存续期</label> 
  </relation>  
  <relation name="ZxDate" data-type="String">
    <label language="zh_CN">ZxDate</label> 
  </relation>  
  <relation name="taxadditional" data-type="Decimal">
    <label language="zh_CN">taxadditional</label> 
  </relation>  
  <relation name="checktotalprice" data-type="Decimal">
    <label language="zh_CN">checktotalprice</label> 
  </relation>  
  <relation name="QlrCardType_SN" data-type="String">
    <label language="zh_CN">QlrCardType_SN</label> 
  </relation>  
  <relation name="mapid" data-type="String">
    <label language="zh_CN">图号</label> 
  </relation>  
  <relation name="SupplyPersonCardType_SN" data-type="String">
    <label language="zh_CN">SupplyPersonCardType_SN</label> 
  </relation>  
  <relation name="tdbz" data-type="String">
    <label language="zh_CN">tdbz</label> 
  </relation>  
  <relation name="SupplyPerson" data-type="String">
    <label language="zh_CN">代理人姓名</label> 
  </relation>  
  <relation name="LawPersonType" data-type="String">
    <label language="zh_CN">LawPersonType</label> 
  </relation>  
  <relation name="ObligeeCardType" data-type="String">
    <label language="zh_CN">产权人证件类型</label> 
  </relation>  
  <relation name="QlrCardType" data-type="String">
    <label language="zh_CN">权利人证件类型</label> 
  </relation>  
  <relation name="StartDate" data-type="DateTime">
    <label language="zh_CN">StartDate</label> 
  </relation>  
  <relation name="DroitSort" data-type="String">
    <label language="zh_CN">权利种类</label> 
  </relation>  
  <relation name="CaptureRemark" data-type="String">
    <label language="zh_CN">CaptureRemark</label> 
  </relation>  
  <relation name="JkrType" data-type="String">
    <label language="zh_CN">JkrType</label> 
  </relation>  
  <relation name="taxtype" data-type="String">
    <label language="zh_CN">taxtype</label> 
  </relation>  
  <relation name="Tel" data-type="String">
    <label language="zh_CN">电话</label> 
  </relation>  
  <relation name="taxprice" data-type="Decimal">
    <label language="zh_CN">taxprice</label> 
  </relation>  
  <relation name="OldBookFid" data-type="String">
    <label language="zh_CN">OldBookFid</label> 
  </relation>  
  <relation name="SupplyPersonNation_SN" data-type="String">
    <label language="zh_CN">SupplyPersonNation_SN</label> 
  </relation>  
  <relation name="tradepart" data-type="String">
    <label language="zh_CN">tradepart</label> 
  </relation>  
  <relation name="taxtype_SN" data-type="String">
    <label language="zh_CN">taxtype_SN</label> 
  </relation>  
  <relation name="JkrNation" data-type="String">
    <label language="zh_CN">JkrNation</label> 
  </relation>  
  <relation name="SupplyPersonSex" data-type="String">
    <label language="zh_CN">SupplyPersonSex</label> 
  </relation>  
  <relation name="Qlr" data-type="String">
    <label language="zh_CN">权利人</label> 
  </relation>  
  <relation name="Dbr" data-type="String">
    <label language="zh_CN">担保人</label> 
  </relation>  
  <relation name="ObligeeType" data-type="String">
    <label language="zh_CN">产权人类型</label> 
  </relation>  
  <relation name="DbrSex" data-type="String">
    <label language="zh_CN">DbrSex</label> 
  </relation>  
  <relation name="ObligeeType_SN" data-type="String">
    <label language="zh_CN">ObligeeType_SN</label> 
  </relation>  
  <relation name="lb3" data-type="String">
    <label language="zh_CN">lb3</label> 
  </relation>  
  <relation name="Email" data-type="String">
    <label language="zh_CN">Email</label> 
  </relation>  
  <relation name="MyRegDate" data-type="DateTime">
    <label language="zh_CN">登记日期</label> 
  </relation>  
  <relation name="checkprice" data-type="Decimal">
    <label language="zh_CN">checkprice</label> 
  </relation>  
  <relation name="JkrCardID" data-type="String">
    <label language="zh_CN">JkrCardID</label> 
  </relation>  
  <relation name="ContinueDate" data-type="DateTime">
    <label language="zh_CN">ContinueDate</label> 
  </relation>  
  <relation name="Feeje" data-type="Decimal">
    <label language="zh_CN">Feeje</label> 
  </relation>  
  <relation name="DbrNation_SN" data-type="String">
    <label language="zh_CN">DbrNation_SN</label> 
  </relation>  
  <relation name="PurchaseHouseAddress" data-type="String">
    <label language="zh_CN">PurchaseHouseAddress</label> 
  </relation>  
  <relation name="Unionfid" data-type="String">
    <label language="zh_CN">Unionfid</label> 
  </relation>  
  <relation name="PledgeStartDate" data-type="DateTime">
    <label language="zh_CN">PledgeStartDate</label> 
  </relation>  
  <relation name="taxindividual" data-type="Decimal">
    <label language="zh_CN">taxindividual</label> 
  </relation>  
  <relation name="DYLX" data-type="Integer">
    <label language="zh_CN">DYLX</label> 
  </relation>  
  <relation name="HTBH_DY" data-type="String">
    <label language="zh_CN">HTBH_DY</label> 
  </relation>  
  <relation name="collator" data-type="String">
    <label language="zh_CN">校对人</label> 
  </relation>  
  <relation name="tradefeemode_SN" data-type="String">
    <label language="zh_CN">tradefeemode_SN</label> 
  </relation>  
  <relation name="DbrNation" data-type="String">
    <label language="zh_CN">DbrNation</label> 
  </relation>  
  <relation name="taxvalueadded" data-type="Decimal">
    <label language="zh_CN">taxvalueadded</label> 
  </relation>  
  <relation name="Jkr" data-type="String">
    <label language="zh_CN">Jkr</label> 
  </relation>  
  <relation name="YY_ZSBH" data-type="String">
    <label language="zh_CN">YY_ZSBH</label> 
  </relation>  
  <relation name="ContinueDateto" data-type="DateTime">
    <label language="zh_CN">ContinueDateto</label> 
  </relation>  
  <relation name="DroitSort_SN" data-type="String">
    <label language="zh_CN">DroitSort_SN</label> 
  </relation>  
  <relation name="DbrType_SN" data-type="String">
    <label language="zh_CN">DbrType_SN</label> 
  </relation>  
  <relation name="LawPersonNation_SN" data-type="String">
    <label language="zh_CN">LawPersonNation_SN</label> 
  </relation>  
  <relation name="DbrCardID" data-type="String">
    <label language="zh_CN">DbrCardID</label> 
  </relation>  
  <relation name="SupplyPersonNation" data-type="String">
    <label language="zh_CN">SupplyPersonNation</label> 
  </relation>  
  <relation name="Usufructer" data-type="String">
    <label language="zh_CN">Usufructer</label> 
  </relation>  
  <relation name="TXQZH" data-type="String">
    <label language="zh_CN">TXQZH</label> 
  </relation>  
  <relation name="QlrType_SN" data-type="String">
    <label language="zh_CN">QlrType_SN</label> 
  </relation>  
  <relation name="DbrType" data-type="String">
    <label language="zh_CN">DbrType</label> 
  </relation>  
  <relation name="EvaluateWorth" data-type="Decimal">
    <label language="zh_CN">EvaluateWorth</label> 
  </relation>  
  <relation name="DbrCardType" data-type="String">
    <label language="zh_CN">DbrCardType</label> 
  </relation>  
  <relation name="QlrCardID" data-type="String">
    <label language="zh_CN">权利人证件号码</label> 
  </relation>  
  <relation name="PledgeEndDate" data-type="DateTime">
    <label language="zh_CN">PledgeEndDate</label> 
  </relation>  
  <relation name="DroitRange" data-type="String">
    <label language="zh_CN">权利范围</label> 
  </relation>  
  <relation name="DroitEndDate" data-type="DateTime">
    <label language="zh_CN">权利结束日期</label> 
  </relation>  
  <relation name="PurchaseHouseAddress_SN" data-type="String">
    <label language="zh_CN">PurchaseHouseAddress_SN</label> 
  </relation>  
  <relation name="SupplyPersonCardID" data-type="String">
    <label language="zh_CN">SupplyPersonCardID</label> 
  </relation>  
  <relation name="HTBH_JKQX" data-type="String">
    <label language="zh_CN">HTBH_JKQX</label> 
  </relation>  
  <relation name="QlrNation" data-type="String">
    <label language="zh_CN">权利人国籍</label> 
  </relation>  
  <relation name="LawPersonCardType_SN" data-type="String">
    <label language="zh_CN">LawPersonCardType_SN</label> 
  </relation>  
  <relation name="landchvalue" data-type="Decimal">
    <label language="zh_CN">landchvalue</label> 
  </relation>  
  <relation name="useArea" data-type="Decimal">
    <label language="zh_CN">建筑面积</label> 
  </relation>  
  <relation name="QlrType" data-type="String">
    <label language="zh_CN">权利人类型</label> 
  </relation>  
  <relation name="HTBH_JK" data-type="String">
    <label language="zh_CN">HTBH_JK</label> 
  </relation>  
  <relation name="LawPersonCardType" data-type="String">
    <label language="zh_CN">LawPersonCardType</label> 
  </relation>  
  <relation name="AddressAndPhone" data-type="String">
    <label language="zh_CN">权利人地址和电话</label> 
  </relation>  
  <relation name="DkMoney" data-type="Decimal">
    <label language="zh_CN">贷款金额</label> 
  </relation>  
  <relation name="DKLX" data-type="String">
    <label language="zh_CN">DKLX</label> 
  </relation>  
  <relation name="ObligeeCardType_SN" data-type="String">
    <label language="zh_CN">ObligeeCardType_SN</label> 
  </relation>  
  <relation name="ObligeeCardID" data-type="String">
    <label language="zh_CN">产权人证件号码</label> 
  </relation>  
  <relation name="QlrNation_SN" data-type="String">
    <label language="zh_CN">QlrNation_SN</label> 
  </relation>  
  <relation name="useBuildArea" data-type="Decimal">
    <label language="zh_CN">useBuildArea</label> 
  </relation>  
  <relation name="Wssqh" data-type="String">
    <label language="zh_CN">Wssqh</label> 
  </relation>  
  <relation name="LawPersonCardID" data-type="String">
    <label language="zh_CN">LawPersonCardID</label> 
  </relation>  
  <relation name="tradesigndate" data-type="DateTime">
    <label language="zh_CN">tradesigndate</label> 
  </relation>  
  <relation name="PledgeExplain" data-type="Text">
    <label language="zh_CN">抵押说明</label> 
  </relation>  
  <relation name="tradefeemode" data-type="String">
    <label language="zh_CN">tradefeemode</label> 
  </relation>  
  <relation name="SupplyPersonCardType" data-type="String">
    <label language="zh_CN">SupplyPersonCardType</label> 
  </relation>  
  <relation name="tradepaymentmode" data-type="String">
    <label language="zh_CN">tradepaymentmode</label> 
  </relation>  
  <relation name="DroitStartDate" data-type="DateTime">
    <label language="zh_CN">权利开始日期</label> 
  </relation>  
  <relation name="JkrCardType_SN" data-type="String">
    <label language="zh_CN">JkrCardType_SN</label> 
  </relation>  
  <relation name="invoicedate" data-type="DateTime">
    <label language="zh_CN">invoicedate</label> 
  </relation>  
  <relation name="QlrSex" data-type="String">
    <label language="zh_CN">权利人性别</label> 
  </relation>  
  <relation name="DbrCardType_SN" data-type="String">
    <label language="zh_CN">DbrCardType_SN</label> 
  </relation>  
  <relation name="HTBH_DYQX" data-type="String">
    <label language="zh_CN">HTBH_DYQX</label> 
  </relation>  
  <relation name="SumBuildAreaDx" data-type="Decimal">
    <label language="zh_CN">SumBuildAreaDx</label> 
  </relation>  
  <relation name="TaxMoney" data-type="Decimal">
    <label language="zh_CN">纳税金额</label> 
  </relation>  
  <relation name="BargainNO" data-type="String">
    <label language="zh_CN">BargainNO</label> 
  </relation>  
  <relation name="YY_TXQZH" data-type="String">
    <label language="zh_CN">YY_TXQZH</label> 
  </relation>  
  <relation name="taxrate" data-type="Float">
    <label language="zh_CN">taxrate</label> 
  </relation>  
  <relation name="DroitWorth" data-type="Decimal">
    <label language="zh_CN">权利价值</label> 
  </relation>  
  <relation name="OldBookID" data-type="String">
    <label language="zh_CN">OldBookID</label> 
  </relation>  
  <relation name="Proposer" data-type="String">
    <label language="zh_CN">Proposer</label> 
  </relation>  
  <relation name="DeclareWorth" data-type="Decimal">
    <label language="zh_CN">DeclareWorth</label> 
  </relation>  
  <relation name="JkrNation_SN" data-type="String">
    <label language="zh_CN">JkrNation_SN</label> 
  </relation>  
  <relation name="JkrType_SN" data-type="String">
    <label language="zh_CN">JkrType_SN</label> 
  </relation>  
  <relation name="provider" data-type="String">
    <label language="zh_CN">发证人</label> 
  </relation>  
  <relation name="QDJE" data-type="Decimal">
    <label language="zh_CN">QDJE</label> 
  </relation>  
  <relation name="JkrSex" data-type="String">
    <label language="zh_CN">JkrSex</label> 
  </relation>  
  <relation name="PGJG" data-type="String">
    <label language="zh_CN">PGJG</label> 
  </relation>  
  <relation name="SupplyPersonType" data-type="String">
    <label language="zh_CN">SupplyPersonType</label> 
  </relation>  
  <relation name="moneytype_SN" data-type="String">
    <label language="zh_CN">moneytype_SN</label> 
  </relation>  
  <relation name="JkrCardType" data-type="String">
    <label language="zh_CN">JkrCardType</label> 
  </relation>  
  <relation name="OldCaptureid" data-type="String">
    <label language="zh_CN">OldCaptureid</label> 
  </relation>  
  <relation name="terraid" data-type="String">
    <label language="zh_CN">地号</label> 
  </relation>  
  <concept name="T_HouseCancel" default-value-expr="guid()" keys="fid">
    <has-relation relation="HouseId" size="50"/>  
    <has-relation relation="Status" size="50"/>  
    <has-relation relation="BookStatus" size="50"/>  
    <has-relation relation="fType" size="50"/>  
    <has-relation relation="type_no" size="50"/>  
    <has-relation relation="BookId" size="50"/>  
    <has-relation relation="BookfId" size="50">
      <label language="zh_CN">BookfId</label> 
    </has-relation>  
    <has-relation relation="ArchiveID" size="30"/>  
    <has-relation relation="CaptureId" size="50"/>  
    <has-relation relation="CreateMan" size="20"/>  
    <has-relation relation="CreateDate"/>  
    <has-relation relation="RegDate"/>  
    <has-relation relation="Ideas"/>  
    <has-relation relation="lb2" size="50"/>  
    <has-relation relation="IsKb"/>  
    <has-relation relation="zxdate"/>  
    <has-relation relation="zxMan" size="50">
      <label language="zh_CN">zxMan</label> 
    </has-relation>  
    <has-relation relation="Remark" size="2000"/>  
    <has-relation relation="DISTRICT" size="50"/>  
    <has-relation relation="LoginUnitID" required="true" size="50"/>  
    <has-relation relation="fType_SN" size="8">
      <label language="zh_CN">fType_SN</label> 
    </has-relation>  
    <has-relation relation="autoid" required="true" size="18"/>  
    <has-relation relation="AcceptanceMan" size="100"/>  
    <has-relation relation="RandomPassword" size="100"/>  
    <has-relation relation="HandCreateMan" size="100"/>  
    <has-relation relation="Geter" size="50"/>  
    <has-relation relation="getdate"/>  
    <has-relation relation="Provider" size="50"/>  
    <has-relation relation="fid" size="50"/> 
  </concept>  
  <concept name="T_HouseCancelDetail" default-value-expr="guid()" keys="Autoid">
    <has-relation relation="FFID" size="50" required="true"/>  
    <has-relation relation="HouseID" size="50"/>  
    <has-relation relation="HouseObligee" size="100"/>  
    <has-relation relation="Houserepose" size="250"/>  
    <has-relation relation="HouseBookid" size="50"/>  
    <has-relation relation="fid" size="50" required="true"/>  
    <has-relation relation="cqdj_fwcqsn" size="50"/>  
    <has-relation relation="cqdj_txqsn" size="50">
      <label language="zh_CN">cqdj_txqsn</label> 
    </has-relation>  
    <has-relation relation="CQZH" size="100"/>  
    <has-relation relation="OLDSUBID" size="50">
      <label language="zh_CN">OLDSUBID</label> 
    </has-relation>  
    <has-relation relation="LoginUnitID" size="50"/>  
    <has-relation relation="Autoid" size="18"/> 
  </concept>  
  <relation name="zxMan" data-type="String">
    <label language="zh_CN">zxMan</label> 
  </relation>  
  <relation name="fType_SN" data-type="String">
    <label language="zh_CN">fType_SN</label> 
  </relation>  
  <relation name="getdate" data-type="DateTime">
    <label language="zh_CN">getdate</label> 
  </relation>  
  <relation name="HouseBookid" data-type="String">
    <label language="zh_CN">HouseBookid</label> 
  </relation>  
  <relation name="OLDSUBID" data-type="String">
    <label language="zh_CN">OLDSUBID</label> 
  </relation>  
  <relation name="zxdate" data-type="DateTime">
    <label language="zh_CN">zxdate</label> 
  </relation>  
  <relation name="FFID" data-type="String">
    <label language="zh_CN">FFID</label> 
  </relation>  
  <relation name="BookfId" data-type="String">
    <label language="zh_CN">BookfId</label> 
  </relation>  
  <relation name="cqdj_txqsn" data-type="String">
    <label language="zh_CN">cqdj_txqsn</label> 
  </relation>  
  <relation name="Houserepose" data-type="String">
    <label language="zh_CN">Houserepose</label> 
  </relation>  
  <concept name="T_HouseCFDetail" default-value-expr="guid()" keys="Autoid">
    <has-relation relation="ffid" size="50" required="true"/>  
    <has-relation relation="HouseId" size="50"/>  
    <has-relation relation="HouseNo" size="50"/>  
    <has-relation relation="HouseRepose" size="250"/>  
    <has-relation relation="HouseBookFID" size="50"/>  
    <has-relation relation="HouseBookID" size="50"/>  
    <has-relation relation="HouseObligee" size="200"/>  
    <has-relation relation="HouseUse" size="50"/>  
    <has-relation relation="HouseFrame" size="50"/>  
    <has-relation relation="SumbuildArea" size="18" scale="2"/>  
    <has-relation relation="CFstatus" size="50">
      <label language="zh_CN">CFstatus</label> 
    </has-relation>  
    <has-relation relation="JFDate">
      <label language="zh_CN">JFDate</label> 
    </has-relation>  
    <has-relation relation="JfMan" size="20">
      <label language="zh_CN">JfMan</label> 
    </has-relation>  
    <has-relation relation="Jfbz" size="400">
      <label language="zh_CN">Jfbz</label> 
    </has-relation>  
    <has-relation relation="Remark" size="2000"/>  
    <has-relation relation="cqdj_fwcqsn" size="50"/>  
    <has-relation relation="cqjy_fwfhsn" size="50">
      <label language="zh_CN">cqjy_fwfhsn</label> 
    </has-relation>  
    <has-relation relation="cfsj">
      <label language="zh_CN">cfsj</label> 
    </has-relation>  
    <has-relation relation="cfqx">
      <label language="zh_CN">cfqx</label> 
    </has-relation>  
    <has-relation relation="CQZH" size="50"/>  
    <has-relation relation="JFZXTZSWH" size="50">
      <label language="zh_CN">JFZXTZSWH</label> 
    </has-relation>  
    <has-relation relation="JFCDSWH" size="50">
      <label language="zh_CN">JFCDSWH</label> 
    </has-relation>  
    <has-relation relation="OldHouseId" size="50">
      <label language="zh_CN">OldHouseId</label> 
    </has-relation>  
    <has-relation relation="sfcz" data-type="Decimal" size="1">
      <label language="zh_CN">sfcz</label> 
    </has-relation>  
    <has-relation relation="cznr" size="200">
      <label language="zh_CN">cznr</label> 
    </has-relation>  
    <has-relation relation="dsdrxm" size="50">
      <label language="zh_CN">dsdrxm</label> 
    </has-relation>  
    <has-relation relation="dsdrlxdh" size="50">
      <label language="zh_CN">dsdrlxdh</label> 
    </has-relation>  
    <has-relation relation="dzxgwzjhm" size="50">
      <label language="zh_CN">dzxgwzjhm</label> 
    </has-relation>  
    <has-relation relation="dgzzjhm" size="50">
      <label language="zh_CN">dgzzjhm</label> 
    </has-relation>  
    <has-relation relation="Autoid" size="18"/>  
    <has-relation relation="LoginUnitID" size="50"/> 
  </concept>  
  <relation name="OldHouseId" data-type="String">
    <label language="zh_CN">OldHouseId</label> 
  </relation>  
  <relation name="dsdrlxdh" data-type="String">
    <label language="zh_CN">dsdrlxdh</label> 
  </relation>  
  <relation name="ffid" data-type="String">
    <label language="zh_CN">ffid</label> 
  </relation>  
  <relation name="sfcz">
    <label language="zh_CN">sfcz</label> 
  </relation>  
  <relation name="dsdrxm" data-type="String">
    <label language="zh_CN">dsdrxm</label> 
  </relation>  
  <relation name="dgzzjhm" data-type="String">
    <label language="zh_CN">dgzzjhm</label> 
  </relation>  
  <relation name="cznr" data-type="String">
    <label language="zh_CN">cznr</label> 
  </relation>  
  <relation name="dzxgwzjhm" data-type="String">
    <label language="zh_CN">dzxgwzjhm</label> 
  </relation>  
  <relation name="cfqx" data-type="DateTime">
    <label language="zh_CN">cfqx</label> 
  </relation>  
  <relation name="JFDate" data-type="DateTime">
    <label language="zh_CN">JFDate</label> 
  </relation>  
  <relation name="cqjy_fwfhsn" data-type="String">
    <label language="zh_CN">cqjy_fwfhsn</label> 
  </relation>  
  <relation name="JFCDSWH" data-type="String">
    <label language="zh_CN">JFCDSWH</label> 
  </relation>  
  <relation name="JFZXTZSWH" data-type="String">
    <label language="zh_CN">JFZXTZSWH</label> 
  </relation>  
  <relation name="Jfbz" data-type="String">
    <label language="zh_CN">Jfbz</label> 
  </relation>  
  <relation name="JfMan" data-type="String">
    <label language="zh_CN">JfMan</label> 
  </relation>  
  <relation name="CFstatus" data-type="String">
    <label language="zh_CN">CFstatus</label> 
  </relation>  
  <relation name="cfsj" data-type="DateTime">
    <label language="zh_CN">cfsj</label> 
  </relation>  
  <concept name="T_RealtyCommCircs" default-value-expr="guid()" keys="autoid">
    <has-relation relation="fId" size="50" required="true"/>  
    <has-relation relation="fNo" size="50"/>  
    <has-relation relation="CommName" size="50">
      <label language="zh_CN">共有人姓名</label> 
    </has-relation>  
    <has-relation relation="CommShare" size="50">
      <label language="zh_CN">占有份额</label> 
    </has-relation>  
    <has-relation relation="CommId" size="50">
      <label language="zh_CN">共有证号</label> 
    </has-relation>  
    <has-relation relation="IsHold" data-type="Decimal" size="1">
      <label language="zh_CN">IsHold</label> 
    </has-relation>  
    <has-relation relation="Remark" size="200"/>  
    <has-relation relation="Host" size="50">
      <label language="zh_CN">Host</label> 
    </has-relation>  
    <has-relation relation="CommCardId" size="150">
      <label language="zh_CN">共有人身份证号</label> 
    </has-relation>  
    <has-relation relation="CommAddress" size="200">
      <label language="zh_CN">地址</label> 
    </has-relation>  
    <has-relation relation="CommSex" size="4">
      <label language="zh_CN">性别</label> 
    </has-relation>  
    <has-relation relation="CommNation" size="50">
      <label language="zh_CN">国籍</label> 
    </has-relation>  
    <has-relation relation="Relations" size="20">
      <label language="zh_CN">Relations</label> 
    </has-relation>  
    <has-relation relation="LoginUnitID" size="50"/>  
    <has-relation relation="CardType" size="50">
      <label language="zh_CN">CardType</label> 
    </has-relation>  
    <has-relation relation="Droitsource" size="100"/>  
    <has-relation relation="Hjszd" size="200">
      <label language="zh_CN">Hjszd</label> 
    </has-relation>  
    <has-relation relation="autoid" size="18"/> 
  </concept>  
  <relation name="IsHold">
    <label language="zh_CN">IsHold</label> 
  </relation>  
  <relation name="Relations" data-type="String">
    <label language="zh_CN">Relations</label> 
  </relation>  
  <relation name="CommShare" data-type="String">
    <label language="zh_CN">占有份额</label> 
  </relation>  
  <relation name="CommName" data-type="String">
    <label language="zh_CN">共有人姓名</label> 
  </relation>  
  <relation name="Host" data-type="String">
    <label language="zh_CN">Host</label> 
  </relation>  
  <relation name="fId" data-type="String">
    <label language="zh_CN">fId</label> 
  </relation>  
  <relation name="Hjszd" data-type="String">
    <label language="zh_CN">Hjszd</label> 
  </relation>  
  <relation name="CommNation" data-type="String">
    <label language="zh_CN">国籍</label> 
  </relation>  
  <relation name="CommCardId" data-type="String">
    <label language="zh_CN">共有人身份证号</label> 
  </relation>  
  <relation name="CommSex" data-type="String">
    <label language="zh_CN">性别</label> 
  </relation>  
  <relation name="CommAddress" data-type="String">
    <label language="zh_CN">地址</label> 
  </relation>  
  <relation name="CardType" data-type="String">
    <label language="zh_CN">CardType</label> 
  </relation>  
  <relation name="Droitsource" data-type="String">
    <label language="zh_CN">Droitsource</label> 
  </relation>  
  <relation name="CommId" data-type="String">
    <label language="zh_CN">共有证号</label> 
  </relation>  
  <concept name="T_MapPhotoLib" default-value-expr="guid()" keys="fid:BHID">
    <has-relation relation="FileName" size="200">
      <label language="zh_CN">FileName</label> 
    </has-relation>  
    <has-relation relation="TableName" size="100">
      <label language="zh_CN">TableName</label> 
    </has-relation>  
    <has-relation relation="Author" size="20">
      <label language="zh_CN">Author</label> 
    </has-relation>  
    <has-relation relation="Desp" size="50">
      <label language="zh_CN">Desp</label> 
    </has-relation>  
    <has-relation relation="CreateDate"/>  
    <has-relation relation="MappFid" size="50">
      <label language="zh_CN">MappFid</label> 
    </has-relation>  
    <has-relation relation="HouseBookInID" size="50">
      <label language="zh_CN">业务收件FID</label> 
    </has-relation>  
    <has-relation relation="LoginUnitID" required="true"/>  
    <has-relation relation="cqjy_fwfhsn" size="50"/>  
    <has-relation relation="cqdj_fwcqsn" size="50"/>  
    <has-relation relation="fcch_fhtsn" size="50">
      <label language="zh_CN">fcch_fhtsn</label> 
    </has-relation>  
    <has-relation relation="fcch_ftsn" size="50">
      <label language="zh_CN">fcch_ftsn</label> 
    </has-relation>  
    <has-relation relation="isdownload">
      <label language="zh_CN">isdownload</label> 
    </has-relation>  
    <has-relation relation="fid" size="50"/>  
    <has-relation relation="BHID" data-type="String" size="50"/> 
  </concept>  
  <relation name="Desp" data-type="String">
    <label language="zh_CN">Desp</label> 
  </relation>  
  <relation name="isdownload" data-type="Integer">
    <label language="zh_CN">isdownload</label> 
  </relation>  
  <relation name="fcch_fhtsn" data-type="String">
    <label language="zh_CN">fcch_fhtsn</label> 
  </relation>  
  <relation name="FileName" data-type="String">
    <label language="zh_CN">FileName</label> 
  </relation>  
  <relation name="HouseBookInID" data-type="String">
    <label language="zh_CN">业务收件FID</label> 
  </relation>  
  <relation name="Author" data-type="String">
    <label language="zh_CN">Author</label> 
  </relation>  
  <relation name="MappFid" data-type="String">
    <label language="zh_CN">MappFid</label> 
  </relation>  
  <relation name="TableName" data-type="String">
    <label language="zh_CN">TableName</label> 
  </relation>  
  <relation name="fcch_ftsn" data-type="String">
    <label language="zh_CN">fcch_ftsn</label> 
  </relation>  
  <relation name="BHID" data-type="String">
    <label language="zh_CN">BHID</label> 
  </relation>  
  <concept name="T_spfht" default-value-expr="guid()" keys="htid">
    <has-relation relation="htbh" size="50" required="false"/>  
    <has-relation relation="xmbm" size="50" required="false"/>  
    <has-relation relation="xmmc" size="100" required="false"/>  
    <has-relation relation="fwdm" size="50">
      <label language="zh_CN">fwdm</label> 
    </has-relation>  
    <has-relation relation="qybm" size="50" required="false">
      <label language="zh_CN">qybm</label> 
    </has-relation>  
    <has-relation relation="qymc" size="100">
      <label language="zh_CN">qymc</label> 
    </has-relation>  
    <has-relation relation="htys" required="false">
      <label language="zh_CN">htys</label> 
    </has-relation>  
    <has-relation relation="htlx" required="false">
      <label language="zh_CN">htlx</label> 
    </has-relation>  
    <has-relation relation="qdfs">
      <label language="zh_CN">qdfs</label> 
    </has-relation>  
    <has-relation relation="qdfs_s" size="200">
      <label language="zh_CN">qdfs_s</label> 
    </has-relation>  
    <has-relation relation="tdxzdm">
      <label language="zh_CN">tdxzdm</label> 
    </has-relation>  
    <has-relation relation="tdxzmc" size="200">
      <label language="zh_CN">tdxzmc</label> 
    </has-relation>  
    <has-relation relation="dkzh" size="200">
      <label language="zh_CN">dkzh</label> 
    </has-relation>  
    <has-relation relation="loginunitid"/>  
    <has-relation relation="tdmz">
      <label language="zh_CN">tdmz</label> 
    </has-relation>  
    <has-relation relation="dh" size="300">
      <label language="zh_CN">dh</label> 
    </has-relation>  
    <has-relation relation="tdzjlx" size="30">
      <label language="zh_CN">tdzjlx</label> 
    </has-relation>  
    <has-relation relation="tdzzrq">
      <label language="zh_CN">tdzzrq</label> 
    </has-relation>  
    <has-relation relation="tdyt" size="50">
      <label language="zh_CN">tdyt</label> 
    </has-relation>  
    <has-relation relation="ghxkzh" size="300">
      <label language="zh_CN">ghxkzh</label> 
    </has-relation>  
    <has-relation relation="sgxkzh" size="300">
      <label language="zh_CN">sgxkzh</label> 
    </has-relation>  
    <has-relation relation="yspzjg" size="100">
      <label language="zh_CN">yspzjg</label> 
    </has-relation>  
    <has-relation relation="ysxkzh" size="50">
      <label language="zh_CN">ysxkzh</label> 
    </has-relation>  
    <has-relation relation="qszjlx" size="30">
      <label language="zh_CN">qszjlx</label> 
    </has-relation>  
    <has-relation relation="qsbadjjg" size="100">
      <label language="zh_CN">qsbadjjg</label> 
    </has-relation>  
    <has-relation relation="qsbadjjglx" size="20">
      <label language="zh_CN">qsbadjjglx</label> 
    </has-relation>  
    <has-relation relation="ghytdm">
      <label language="zh_CN">ghytdm</label> 
    </has-relation>  
    <has-relation relation="ghytmc" size="50">
      <label language="zh_CN">ghytmc</label> 
    </has-relation>  
    <has-relation relation="jzztjg" size="50">
      <label language="zh_CN">jzztjg</label> 
    </has-relation>  
    <has-relation relation="jzztjg_s" size="50">
      <label language="zh_CN">jzztjg_s</label> 
    </has-relation>  
    <has-relation relation="jzzcs">
      <label language="zh_CN">jzzcs</label> 
    </has-relation>  
    <has-relation relation="dscs">
      <label language="zh_CN">dscs</label> 
    </has-relation>  
    <has-relation relation="dxcs">
      <label language="zh_CN">dxcs</label> 
    </has-relation>  
    <has-relation relation="zl" size="200">
      <label language="zh_CN">zl</label> 
    </has-relation>  
    <has-relation relation="gfr" size="500">
      <label language="zh_CN">gfr</label> 
    </has-relation>  
    <has-relation relation="dz" size="10"/>  
    <has-relation relation="dmc" size="50">
      <label language="zh_CN">dmc</label> 
    </has-relation>  
    <has-relation relation="dy" size="50">
      <label language="zh_CN">dy</label> 
    </has-relation>  
    <has-relation relation="c" size="50">
      <label language="zh_CN">c</label> 
    </has-relation>  
    <has-relation relation="h" size="50">
      <label language="zh_CN">h</label> 
    </has-relation>  
    <has-relation relation="fcchjgdm" size="50">
      <label language="zh_CN">fcchjgdm</label> 
    </has-relation>  
    <has-relation relation="fcchjgmc" size="50">
      <label language="zh_CN">fcchjgmc</label> 
    </has-relation>  
    <has-relation relation="jzmj">
      <label language="zh_CN">jzmj</label> 
    </has-relation>  
    <has-relation relation="tnmj">
      <label language="zh_CN">tnmj</label> 
    </has-relation>  
    <has-relation relation="ftmj">
      <label language="zh_CN">ftmj</label> 
    </has-relation>  
    <has-relation relation="cg">
      <label language="zh_CN">cg</label> 
    </has-relation>  
    <has-relation relation="yt">
      <label language="zh_CN">yt</label> 
    </has-relation>  
    <has-relation relation="fbsyt">
      <label language="zh_CN">fbsyt</label> 
    </has-relation>  
    <has-relation relation="ffbsyt">
      <label language="zh_CN">ffbsyt</label> 
    </has-relation>  
    <has-relation relation="jjbz" size="50">
      <label language="zh_CN">jjbz</label> 
    </has-relation>  
    <has-relation relation="jjfsdm">
      <label language="zh_CN">jjfsdm</label> 
    </has-relation>  
    <has-relation relation="jjfsmc" size="50">
      <label language="zh_CN">jjfsmc</label> 
    </has-relation>  
    <has-relation relation="htdj">
      <label language="zh_CN">htdj</label> 
    </has-relation>  
    <has-relation relation="zjk">
      <label language="zh_CN">zjk</label> 
    </has-relation>  
    <has-relation relation="djbz" size="50">
      <label language="zh_CN">djbz</label> 
    </has-relation>  
    <has-relation relation="dj">
      <label language="zh_CN">dj</label> 
    </has-relation>  
    <has-relation relation="djzfsj" size="50">
      <label language="zh_CN">djzfsj</label> 
    </has-relation>  
    <has-relation relation="djyu" size="50">
      <label language="zh_CN">djyu</label> 
    </has-relation>  
    <has-relation relation="fkbz" size="50">
      <label language="zh_CN">fkbz</label> 
    </has-relation>  
    <has-relation relation="fkfsdm">
      <label language="zh_CN">fkfsdm</label> 
    </has-relation>  
    <has-relation relation="fkqs">
      <label language="zh_CN">fkqs</label> 
    </has-relation>  
    <has-relation relation="sqk">
      <label language="zh_CN">sqk</label> 
    </has-relation>  
    <has-relation relation="fqfkbz" size="500">
      <label language="zh_CN">fqfkbz</label> 
    </has-relation>  
    <has-relation relation="sqkzfrq">
      <label language="zh_CN">sqkzfrq</label> 
    </has-relation>  
    <has-relation relation="qkzzzfrq">
      <label language="zh_CN">qkzzzfrq</label> 
    </has-relation>  
    <has-relation relation="dkfsdm">
      <label language="zh_CN">dkfsdm</label> 
    </has-relation>  
    <has-relation relation="dkfsmc" size="50">
      <label language="zh_CN">dkfsmc</label> 
    </has-relation>  
    <has-relation relation="sqkzb">
      <label language="zh_CN">sqkzb</label> 
    </has-relation>  
    <has-relation relation="yk">
      <label language="zh_CN">yk</label> 
    </has-relation>  
    <has-relation relation="dkjgdm" size="20">
      <label language="zh_CN">dkjgdm</label> 
    </has-relation>  
    <has-relation relation="dkjgmc" size="100">
      <label language="zh_CN">dkjgmc</label> 
    </has-relation>  
    <has-relation relation="qtfkfs" size="200">
      <label language="zh_CN">qtfkfs</label> 
    </has-relation>  
    <has-relation relation="zjjgjgid">
      <label language="zh_CN">zjjgjgid</label> 
    </has-relation>  
    <has-relation relation="zjjgjgdm">
      <label language="zh_CN">zjjgjgdm</label> 
    </has-relation>  
    <has-relation relation="zjjgjgmc" size="50">
      <label language="zh_CN">zjjgjgmc</label> 
    </has-relation>  
    <has-relation relation="zjjgzhmc" size="500">
      <label language="zh_CN">zjjgzhmc</label> 
    </has-relation>  
    <has-relation relation="zjjgzh" size="500">
      <label language="zh_CN">zjjgzh</label> 
    </has-relation>  
    <has-relation relation="khyhmc" size="500">
      <label language="zh_CN">khyhmc</label> 
    </has-relation>  
    <has-relation relation="qdsj">
      <label language="zh_CN">qdsj</label> 
    </has-relation>  
    <has-relation relation="qddd" size="200">
      <label language="zh_CN">qddd</label> 
    </has-relation>  
    <has-relation relation="htzt" size="20">
      <label language="zh_CN">htzt</label> 
    </has-relation>  
    <has-relation relation="mkid">
      <label language="zh_CN">mkid</label> 
    </has-relation>  
    <has-relation relation="password" size="50"/>  
    <has-relation relation="Saved" data-type="Integer">
      <label language="zh_CN">Saved</label> 
    </has-relation>  
    <has-relation relation="bar" size="30">
      <label language="zh_CN">bar</label> 
    </has-relation>  
    <has-relation relation="basj">
      <label language="zh_CN">basj</label> 
    </has-relation>  
    <has-relation relation="dymc" size="20">
      <label language="zh_CN">dymc</label> 
    </has-relation>  
    <has-relation relation="cmc" size="20">
      <label language="zh_CN">cmc</label> 
    </has-relation>  
    <has-relation relation="fhmc" size="20">
      <label language="zh_CN">fhmc</label> 
    </has-relation>  
    <has-relation relation="TJYY" size="500" required="false">
      <label language="zh_CN">TJYY</label> 
    </has-relation>  
    <has-relation relation="jg" size="50">
      <label language="zh_CN">jg</label> 
    </has-relation>  
    <has-relation relation="SHBW" size="20"/>  
    <label language="zh_CN">商品房合同</label>  
    <has-relation relation="htid" data-type="String" size="50"/> 
  </concept>  
  <relation name="htys" data-type="Integer">
    <label language="zh_CN">htys</label> 
  </relation>  
  <relation name="zjjgzhmc" data-type="String">
    <label language="zh_CN">zjjgzhmc</label> 
  </relation>  
  <relation name="fkfsdm" data-type="Integer">
    <label language="zh_CN">fkfsdm</label> 
  </relation>  
  <relation name="gfr" data-type="String">
    <label language="zh_CN">gfr</label> 
  </relation>  
  <relation name="zjjgjgid" data-type="String">
    <label language="zh_CN">zjjgjgid</label> 
  </relation>  
  <relation name="dkjgdm" data-type="String">
    <label language="zh_CN">dkjgdm</label> 
  </relation>  
  <relation name="zjjgjgmc" data-type="String">
    <label language="zh_CN">zjjgjgmc</label> 
  </relation>  
  <relation name="ftmj" data-type="Decimal">
    <label language="zh_CN">ftmj</label> 
  </relation>  
  <relation name="dxcs" data-type="Integer">
    <label language="zh_CN">dxcs</label> 
  </relation>  
  <relation name="qsbadjjg" data-type="String">
    <label language="zh_CN">qsbadjjg</label> 
  </relation>  
  <relation name="tdxzdm" data-type="Integer">
    <label language="zh_CN">tdxzdm</label> 
  </relation>  
  <relation name="qdfs_s" data-type="String">
    <label language="zh_CN">qdfs_s</label> 
  </relation>  
  <relation name="dscs" data-type="Integer">
    <label language="zh_CN">dscs</label> 
  </relation>  
  <relation name="jzmj" data-type="Decimal">
    <label language="zh_CN">jzmj</label> 
  </relation>  
  <relation name="zjjgzh" data-type="String">
    <label language="zh_CN">zjjgzh</label> 
  </relation>  
  <relation name="htbh" data-type="String">
    <label language="zh_CN">htbh</label> 
  </relation>  
  <relation name="qymc" data-type="String">
    <label language="zh_CN">qymc</label> 
  </relation>  
  <relation name="dmc" data-type="String">
    <label language="zh_CN">dmc</label> 
  </relation>  
  <relation name="xmmc" data-type="String">
    <label language="zh_CN">xmmc</label> 
  </relation>  
  <relation name="dymc" data-type="String">
    <label language="zh_CN">dymc</label> 
  </relation>  
  <relation name="qdsj" data-type="DateTime">
    <label language="zh_CN">qdsj</label> 
  </relation>  
  <relation name="ghxkzh" data-type="String">
    <label language="zh_CN">ghxkzh</label> 
  </relation>  
  <relation name="yspzjg" data-type="String">
    <label language="zh_CN">yspzjg</label> 
  </relation>  
  <relation name="dkfsdm" data-type="Integer">
    <label language="zh_CN">dkfsdm</label> 
  </relation>  
  <relation name="cmc" data-type="String">
    <label language="zh_CN">cmc</label> 
  </relation>  
  <relation name="fcchjgmc" data-type="String">
    <label language="zh_CN">fcchjgmc</label> 
  </relation>  
  <relation name="jzztjg_s" data-type="String">
    <label language="zh_CN">jzztjg_s</label> 
  </relation>  
  <relation name="jjfsmc" data-type="String">
    <label language="zh_CN">jjfsmc</label> 
  </relation>  
  <relation name="qdfs" data-type="Integer">
    <label language="zh_CN">qdfs</label> 
  </relation>  
  <relation name="jjbz" data-type="String">
    <label language="zh_CN">jjbz</label> 
  </relation>  
  <relation name="cg" data-type="Decimal">
    <label language="zh_CN">cg</label> 
  </relation>  
  <relation name="fqfkbz" data-type="String">
    <label language="zh_CN">fqfkbz</label> 
  </relation>  
  <relation name="bar" data-type="String">
    <label language="zh_CN">bar</label> 
  </relation>  
  <relation name="qtfkfs" data-type="String">
    <label language="zh_CN">qtfkfs</label> 
  </relation>  
  <relation name="qsbadjjglx" data-type="String">
    <label language="zh_CN">qsbadjjglx</label> 
  </relation>  
  <relation name="jzzcs" data-type="Integer">
    <label language="zh_CN">jzzcs</label> 
  </relation>  
  <relation name="fkbz" data-type="String">
    <label language="zh_CN">fkbz</label> 
  </relation>  
  <relation name="dy" data-type="String">
    <label language="zh_CN">dy</label> 
  </relation>  
  <relation name="dz" data-type="String">
    <label language="zh_CN">dz</label> 
  </relation>  
  <relation name="tdyt" data-type="String">
    <label language="zh_CN">tdyt</label> 
  </relation>  
  <relation name="htlx" data-type="Integer">
    <label language="zh_CN">htlx</label> 
  </relation>  
  <relation name="tdmz" data-type="Decimal">
    <label language="zh_CN">tdmz</label> 
  </relation>  
  <relation name="djzfsj" data-type="String">
    <label language="zh_CN">djzfsj</label> 
  </relation>  
  <relation name="qszjlx" data-type="String">
    <label language="zh_CN">qszjlx</label> 
  </relation>  
  <relation name="tnmj" data-type="Decimal">
    <label language="zh_CN">tnmj</label> 
  </relation>  
  <relation name="dkjgmc" data-type="String">
    <label language="zh_CN">dkjgmc</label> 
  </relation>  
  <relation name="fkqs" data-type="Integer">
    <label language="zh_CN">fkqs</label> 
  </relation>  
  <relation name="htdj" data-type="Decimal">
    <label language="zh_CN">htdj</label> 
  </relation>  
  <relation name="fcchjgdm" data-type="String">
    <label language="zh_CN">fcchjgdm</label> 
  </relation>  
  <relation name="dh" data-type="String">
    <label language="zh_CN">dh</label> 
  </relation>  
  <relation name="dj" data-type="Decimal">
    <label language="zh_CN">dj</label> 
  </relation>  
  <relation name="sgxkzh" data-type="String">
    <label language="zh_CN">sgxkzh</label> 
  </relation>  
  <relation name="ghytdm" data-type="Integer">
    <label language="zh_CN">ghytdm</label> 
  </relation>  
  <relation name="mkid" data-type="String">
    <label language="zh_CN">mkid</label> 
  </relation>  
  <relation name="tdxzmc" data-type="String">
    <label language="zh_CN">tdxzmc</label> 
  </relation>  
  <relation name="dkzh" data-type="String">
    <label language="zh_CN">dkzh</label> 
  </relation>  
  <relation name="ffbsyt" data-type="Integer">
    <label language="zh_CN">ffbsyt</label> 
  </relation>  
  <relation name="khyhmc" data-type="String">
    <label language="zh_CN">khyhmc</label> 
  </relation>  
  <relation name="sqkzb" data-type="Decimal">
    <label language="zh_CN">sqkzb</label> 
  </relation>  
  <relation name="fbsyt" data-type="Integer">
    <label language="zh_CN">fbsyt</label> 
  </relation>  
  <relation name="jzztjg" data-type="String">
    <label language="zh_CN">jzztjg</label> 
  </relation>  
  <relation name="zl" data-type="String">
    <label language="zh_CN">zl</label> 
  </relation>  
  <relation name="loginunitid" data-type="String">
    <label language="zh_CN">loginunitid</label> 
  </relation>  
  <relation name="TJYY" data-type="String">
    <label language="zh_CN">TJYY</label> 
  </relation>  
  <relation name="htzt" data-type="String">
    <label language="zh_CN">htzt</label> 
  </relation>  
  <relation name="c" data-type="String">
    <label language="zh_CN">c</label> 
  </relation>  
  <relation name="zjjgjgdm" data-type="Integer">
    <label language="zh_CN">zjjgjgdm</label> 
  </relation>  
  <relation name="tdzjlx" data-type="String">
    <label language="zh_CN">tdzjlx</label> 
  </relation>  
  <relation name="Saved">
    <label language="zh_CN">Saved</label> 
  </relation>  
  <relation name="djbz" data-type="String">
    <label language="zh_CN">djbz</label> 
  </relation>  
  <relation name="jjfsdm" data-type="Integer">
    <label language="zh_CN">jjfsdm</label> 
  </relation>  
  <relation name="tdzzrq" data-type="DateTime">
    <label language="zh_CN">tdzzrq</label> 
  </relation>  
  <relation name="fwdm" data-type="String">
    <label language="zh_CN">fwdm</label> 
  </relation>  
  <relation name="h" data-type="String">
    <label language="zh_CN">h</label> 
  </relation>  
  <relation name="sqk" data-type="Decimal">
    <label language="zh_CN">sqk</label> 
  </relation>  
  <relation name="fhmc" data-type="String">
    <label language="zh_CN">fhmc</label> 
  </relation>  
  <relation name="qddd" data-type="String">
    <label language="zh_CN">qddd</label> 
  </relation>  
  <relation name="jg" data-type="String">
    <label language="zh_CN">jg</label> 
  </relation>  
  <relation name="yt" data-type="Integer">
    <label language="zh_CN">yt</label> 
  </relation>  
  <relation name="qybm" data-type="String">
    <label language="zh_CN">qybm</label> 
  </relation>  
  <relation name="ghytmc" data-type="String">
    <label language="zh_CN">ghytmc</label> 
  </relation>  
  <relation name="sqkzfrq" data-type="DateTime">
    <label language="zh_CN">sqkzfrq</label> 
  </relation>  
  <relation name="dkfsmc" data-type="String">
    <label language="zh_CN">dkfsmc</label> 
  </relation>  
  <relation name="zjk" data-type="Decimal">
    <label language="zh_CN">zjk</label> 
  </relation>  
  <relation name="ysxkzh" data-type="String">
    <label language="zh_CN">ysxkzh</label> 
  </relation>  
  <relation name="xmbm" data-type="String">
    <label language="zh_CN">xmbm</label> 
  </relation>  
  <relation name="yk" data-type="Decimal">
    <label language="zh_CN">yk</label> 
  </relation>  
  <relation name="djyu" data-type="String">
    <label language="zh_CN">djyu</label> 
  </relation>  
  <relation name="qkzzzfrq" data-type="DateTime">
    <label language="zh_CN">qkzzzfrq</label> 
  </relation>  
  <relation name="basj" data-type="DateTime">
    <label language="zh_CN">basj</label> 
  </relation>  
  <relation name="htid" data-type="String">
    <label language="zh_CN">htid</label> 
  </relation>  
    
  <concept name="HT_Buyer" default-value-expr="guid()" keys="id">
    <has-relation relation="htbh" size="50"/>  
    <has-relation relation="buyer" size="300"/>  
    <has-relation relation="ht_id">
      <label language="zh_CN">ht_id</label> 
    </has-relation>  
    <has-relation relation="booktype" size="250">
      <label language="zh_CN">booktype</label> 
    </has-relation>  
    <has-relation relation="bookno" size="250">
      <label language="zh_CN">bookno</label> 
    </has-relation>  
    <has-relation relation="sex" data-type="Integer">
      <label language="zh_CN">sex</label> 
    </has-relation>  
    <has-relation relation="national" size="100">
      <label language="zh_CN">national</label> 
    </has-relation>  
    <has-relation relation="nativeplace" size="50">
      <label language="zh_CN">nativeplace</label> 
    </has-relation>  
    <has-relation relation="part" size="50"/>  
    <has-relation relation="address" size="500">
      <label language="zh_CN">address</label> 
    </has-relation>  
    <has-relation relation="tel" size="200"/>  
    <has-relation relation="Code" size="20">
      <label language="zh_CN">Code</label> 
    </has-relation>  
    <has-relation relation="LoginUnitID" required="false" size="50"/>  
    <has-relation relation="msrepl_tran_version" required="false" size="50"/>  
    <has-relation relation="st_flag" required="false"/>  
    <has-relation relation="oldid" size="50">
      <label language="zh_CN">oldid</label> 
    </has-relation>  
    <has-relation relation="id" data-type="String" size="50"/> 
  </concept>  
  <relation name="bookno" data-type="String">
    <label language="zh_CN">bookno</label> 
  </relation>  
  <relation name="sex">
    <label language="zh_CN">sex</label> 
  </relation>  
  <relation name="address" data-type="String">
    <label language="zh_CN">address</label> 
  </relation>  
  <relation name="nativeplace" data-type="String">
    <label language="zh_CN">nativeplace</label> 
  </relation>  
  <relation name="national" data-type="String">
    <label language="zh_CN">national</label> 
  </relation>  
  <relation name="oldid" data-type="String">
    <label language="zh_CN">oldid</label> 
  </relation>  
  <relation name="buyer" data-type="String">
    <label language="zh_CN">buyer</label> 
  </relation>  
  <relation name="booktype" data-type="String">
    <label language="zh_CN">booktype</label> 
  </relation>  
  <relation name="part" data-type="String">
    <label language="zh_CN">part</label> 
  </relation>  
  <relation name="ht_id" data-type="Integer">
    <label language="zh_CN">ht_id</label> 
  </relation>  
  <relation name="Code" data-type="String">
    <label language="zh_CN">Code</label> 
  </relation>  
  <relation name="id" data-type="Integer">
    <label language="zh_CN">id</label> 
  </relation> 
</model>
