import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.justep.system.data.DatabaseProduct;
import com.justep.system.data.SQL;
import com.justep.system.data.Table;
import com.justep.util.Utils;

/**
 * @author rencj 查询流程主从关系
 */

public class ProcessMDRelation {

	/**
	 * @author rencj 查询流程关联关系的主
	 */
	public static Table queryMasterRelation(String taskID, String bizrecID) {
		if (Utils.isEmptyString(taskID) && Utils.isEmptyString(bizrecID))
			return null;
		String sql = " with queryBiz as(select * from B_BizRecRelation connect by prior fbizrecid = fparentid"
				+ " start with fbizrecid =(select decode(NodeLEVEL ,1,fbizrecid,fparentid) as fParent"
				+ " from (SELECT fbizrecid,fparentid, LEVEL as NodeLEVEL, CONNECT_BY_ROOT t.fparentid as root"
				+ " from B_BizRecRelation t CONNECT BY PRIOR t.fbizrecid = t.fparentid start with t.fbizrecid = %s"
				+ " order by level desc) getRoot where rownum =1))"
				+ " select task.sactivity, task.sactivityname,task.sactivitynames, task.sCreatorFID,task.sCreatorFName,"
				+ " task.sExecutorFID,task.sExecutorFName, sExecutorPersonID,sExecutorPersonName,task.sLastModifyTime,"
				+ " task.sCreateTime,sResponsible, skindid, task.sparentid, task.sid,task.sStatusID,rec.fbizname,"
				+ " rec.fbizid ,rec.fbizrecid,rec.fstatus,rec.fstatusname ,task.sCreatorPersonID,task.sCreatorPersonName,task.sExecutorDeptName"
				+ " from queryBiz left join B_bizrec rec on queryBiz.fbizrecid = rec.fbizrecid"
				+ " left join  sa_task task on task.sdata1=rec.fbizrecid"
				+ " where (task.sStatusID='tesReady' or task.sStatusID='tesExecuting') and  skindid='tkTask'";
		List<Object> params = new ArrayList<Object>();
		if (!Utils.isEmptyString(taskID)) {
			sql = String.format(sql,
					"(select sData1 from sa_Task where sid = ?) ");
			params.add(taskID);
		}
		if (!Utils.isEmptyString(bizrecID)) {
			sql = String.format(sql, "?");
			params.add(bizrecID);
		}
		System.out.println(sql);
		System.out.println(params.toString());
		Map<String, String> sqlmap = new HashMap<String, String>();
		sqlmap.put(DatabaseProduct.DEFAULT.name(), sql);
		Table tab = SQL.select(sqlmap, params, "/system/data");
		return tab;
	}

	/**
	 * @author rencj 查询流程关联关系的子关系
	 */
	public static Table queryDetailRelation(String taskID) {
		String sql = " SELECT SEXECUTORDEPTNAME,SEXECUTORPOSNAME,SEXECUTORPERSONNAME,SACTUALSTARTTIME,SACTUALFINISHTIME,SSTATUSNAME FROM sa_task t\n"
				+ "  where   sid in  ( select sid from sa_task where (sparentId = ? or sid=?)\n"
				+ "   and skindId <>'tkProcessInstance'  and sExecutorPersonID is not null and  sExecutorPersonName  is not null )";
		Map<String, String> sqlmap = new HashMap<String, String>();
		List<Object> params = new ArrayList<Object>();
		params.add(taskID);
		params.add(taskID);
		sqlmap.put(DatabaseProduct.DEFAULT.name(), sql);
		Table tab = SQL.select(sqlmap, params, "/system/data");
		return tab;
	}

	/**
	 * 查询关联案卷
	 * 
	 * @param bizRecId
	 * @param taskID
	 * @return
	 */
	public static Table queryRelationBizRec(String bizRecId, String taskID) {
		if (Utils.isEmptyString(taskID) && Utils.isEmptyString(bizRecId))
			return null; 
		String whereStr = " where 1=2";
		if (Utils.isNotEmptyString(taskID))
			whereStr = " where exists (select 1 from sa_Task where rec.fbizrecid=sdata1 and sid = '" + taskID + "')" +
				" or exists (select 1 from B_BizRecRelation t where rec.fbizrecid = t.fbizrecid" +
				" and exists (select 1 from sa_Task where t.fparentid=sdata1 and sid = '" + taskID + "'))";
		if (Utils.isNotEmptyString(bizRecId))
			whereStr = " where rec.fbizrecid = '" + bizRecId + "' or exists ( select 1 from B_BizRecRelation t" +
					" where rec.fbizrecid= t.fbizrecid and t.fparentid = '" + bizRecId + "')"; 
		String sql = "select rec.fbizrecid,rec.fbizname,attr.fincomedocname as frectitle,rec.fflowid,rec.fstatusname,attr.fmaindept,attr.fmainperson,"
				+ "case when rec.FFINISHKIND is null  then "
				+ "(select to_char(wm_concat(task.sExecutorDeptName||'['||task.sActivityName|| ' : '||nvl(task.sExecutorPersonName, '无')||']')) "
				+ "from sa_task task where task.sdata1 = rec.fbizrecid and task.sActivityName is not null "
				+ "and task.sStatusID in ('tesExecuting', 'tesPaused', 'tesReady') and task.sexecutordeptname is not null) "
				+ " else (select  sExecutorDeptName||'['||sActivityName|| ' : '||nvl(sExecutorPersonName, '无')||']' from (select t1.*,row_number() over  "
				+ "(partition  by t1.sdata1 order by t1.sactualfinishtime desc ) rw  from "
				+ "sa_task t1   where t1.sexecutordeptname is not null)  t2 where t2.sdata1=rec.fbizrecid and rw=1) end"
				+ " as BLXX from B_bizrec rec left join b_bizrecattr attr"
				+ " on rec.fbizrecid = attr.fbizrecid " + whereStr;
		List<Object> params = new ArrayList<Object>();
		Map<String, String> sqlmap = new HashMap<String, String>();
		sqlmap.put(DatabaseProduct.DEFAULT.name(), sql);
		Table tab = SQL.select(sqlmap, params, "/system/data");
		return tab;
	}
}