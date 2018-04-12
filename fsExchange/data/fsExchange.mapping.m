<?xml version="1.0" encoding="utf-8" standalone="no"?><model xmlns="http://www.justep.com/model">
	<store name="Ex_MaterialLog"/>
	<store name="Ex_Rec"/>
	<store name="Ex_View_Apply"/>
	<store name="Ex_View_SPB"/>
	<store name="Ex_View_ShouLi"/>
	<store name="Ex_Task"/>
	<mapping concept="Ex_Task">
		<table name="Ex_Task" type="owner-table">
			<index fields="fBizRecId" name="IDX_Ex_Task_fBizRecId" type="NORMAL"/>
		</table>
	</mapping>
</model>