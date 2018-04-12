<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<model xmlns="http://www.justep.com/model">
	<store name="B_HuoDongRange_H" />
	<store name="B_HuoDongChuLi_H" />
	<store name="B_GongZuoHuoDong_H" />
	<store name="B_HuoDongRange" />

	<store name="B_HuoDongChuLi" />




	<store name="B_HuoDongCuiBan" />
	<store name="B_GuanZhuZhuXian" />
	<store name="B_GongZuoHuoDong" />
	<store name="B_GongZuoZhuXian" />
	<mapping concept="B_GuanZhuZhuXian">
		<table name="B_GuanZhuZhuXian" type="owner-table">
			<index fields="fZhuXian,fCreator" name="IDX_GuanZhu_fCreator"
				type="UNIQUE" />
		</table>
	</mapping>
	<mapping concept="B_HuoDongCuiBan">
		<table name="B_HuoDongCuiBan" type="owner-table">


			<index fields="fHuoDong" name="IDX_HuoDongCB_fHuoDong" type="NORMAL" />
			<index fields="fZhuXian" name="IDX_HuoDongCB_fZhuXian" type="NORMAL" />
			<index fields="fActivityItem" name="IDX_HuoDongCB_fActivityItem"
				type="NORMAL" />
		</table>
	</mapping>
	<mapping concept="B_GongZuoZhuXian">
		<table name="B_GongZuoZhuXian" type="owner-table">
			<index fields="fCreator" name="IDX_ZhuXian_fCreator" type="NORMAL" />
			<index fields="fSourceType" name="IDX_ZhuXian_fSourceType"
				type="NORMAL" />
			<index fields="fStatus" name="IDX_ZhuXian_fStatus" type="NORMAL" />
			<index fields="fSourceGuid,fCreator" name="IDX_ZHUXIAN_SOURCECREATOR"
				type="UNIQUE" />
		</table>
	</mapping>
	<mapping concept="B_GongZuoHuoDong">
		<table name="B_GongZuoHuoDong" type="owner-table">
			<index fields="fZhuXian" name="IDX_HuoDong_fZhuXian" type="NORMAL" />
			<index fields="fActivityItem" name="IDX_HuoDong_fActivityItem"
				type="NORMAL" />
			<index fields="fActivityItem,fZhuXian,fItemType" name="IDX_HuoDong_UNI_AZI"
				type="UNIQUE" />
		</table>
	</mapping>

	<mapping concept="B_GongZuoHuoDong_H">
		<table name="B_GongZuoHuoDong_H" type="owner-table">
			<index fields="fZhuXian" name="IDX_HuoDong_H_fZhuXian" type="NORMAL" />
			<index fields="fActivityItem" name="IDX_HuoDong_H_fActivityItem"
				type="NORMAL" />
			<index fields="fActivityItem,fZhuXian,fItemType" name="IDX_HuoDong_H_UNI_AZI"
				type="UNIQUE" />
		</table>
	</mapping>


	<mapping concept="B_HuoDongChuLi">
		<table name="B_HuoDongChuLi" type="owner-table">
			<index fields="fHuoDong" name="IDX_HuoDongCL_HuoDong" type="NORMAL" />
			<index fields="fActivityItem" name="IDX_HuoDongCL_ActivityItem"
				type="NORMAL" />
			<index fields="fHuoDong,fFinishTime,fOrgUnitID" name="IDX_HUODONGCL_CLR"
				type="NORMAL" />
		</table>

	</mapping>
	<mapping concept="B_HuoDongChuLi_H">
		<table name="B_HuoDongChuLi_H" type="owner-table">
			<index fields="fHuoDong" name="IDX_HuoDongCL_H_HuoDong" type="NORMAL" />
			<index fields="fActivityItem" name="IDX_HuoDongCL_H_ActivityItem"
				type="NORMAL" />
			<index fields="fHuoDong,fFinishTime,fOrgUnitID" name="IDX_HUODONGCL_H_CLR"
				type="NORMAL" />
		</table>

	</mapping>

	<mapping concept="B_HuoDongRange">
		<table name="B_HuoDongRange" type="owner-table">

			<index fields="fHuoDong,fOrgUnitFID" name="IDX_HuoDongRange_UNI"
				type="UNIQUE" />
		</table>
	</mapping>
	<mapping concept="B_HuoDongRange_H">
		<table name="B_HuoDongRange_H" type="owner-table">
			<index fields="fHuoDong,fOrgUnitFID" name="IDX_HuoDongRange_H_UNI"
				type="UNIQUE" />
		</table>
	</mapping>
</model>