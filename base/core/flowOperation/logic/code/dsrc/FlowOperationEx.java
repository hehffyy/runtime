import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.butone.extend.TaskUtils;
import com.butone.utils.StringUtils;
import com.butone.utils.SysUtils;
import com.justep.exception.BusinessException;
import com.justep.model.ModelUtils;
import com.justep.system.context.ContextHelper;
import com.justep.system.data.Expression;
import com.justep.system.data.KSQL;
import com.justep.system.data.Row;
import com.justep.system.data.Table;
import com.justep.system.util.BizUtils;
import com.justep.system.util.CommonUtils;

public class FlowOperationEx {
	public static Table queryB_RecHandDetailEx(String concept, String idColumn, String select, String from, String condition, List range,
			String filter, Boolean distinct, Integer offset, Integer limit, String columns, String orderBy, String aggregate,
			String aggregateColumns, Map variables, String dataModel, String fnModel) {
		Map<String, Object> vars = new HashMap<String, Object>();
		String executorFilter = TaskUtils.getExecutorCondition("t", ContextHelper.getPerson().getPersonMembers(), true, vars);
		String query = "select d,d.fBizRecId,d.fYwh,d.fSqr,d.fZl,t as fTaskId"
				+ " from B_RecHandDetail d  optional join SA_Task t  on t.sData1=d.fBizRecId " + " and "
				+ " (t.sStatusID='tesReady' or t.sStatusID='tesExecuting') " + " and " + executorFilter + " and t.sESField07 is  null  ";
		if (filter != null)
			query = query + " where  " + filter;
		Table table = KSQL.select(query, vars, dataModel, null);
		return table;
	}

	public static Map<String, String> handRecs(String bizgroup, List<Map<String, Object>> list, String handcode, String kind) {
		String qxdm = (String) Expression.evaluate("currentAreaIdOrName(true)", null, ModelUtils.getModel("/base/core/logic/fn"));
		String qxdmsx = (String) SysUtils.queryFldValue("select FAreaCode1 from B_Area where FAreaCode=?", qxdm);
		SimpleDateFormat frm = new SimpleDateFormat("yyMMdd");
		String code=null;
		if(kind.equals("全局"))
			code = handcode;
		else{
			kind="环节";
			String time = frm.format(new Date());
			String key = "H" + qxdmsx + time;
			code = BizUtils.createNextSequenceString(key, "000");
		}
		String handid = StringUtils.getNewGuid32();
		SysUtils.executeSql("insert into b_rechand(fhandid,fhandperson,fhandpersonid,fhandTIme,fHandCode,fHandNum,fBizGroup,fhandKind,version)"
				+ " values(?,?,?,?,?,?,?,?,0)", handid, ContextHelper.getPerson().getName(), ContextHelper.getPerson().getID(),
				CommonUtils.getCurrentDateTime(), code, list.size(), bizgroup,kind);
		for (Map<String, Object> m : list) {
			String handActivity = (String) SysUtils.queryFldValue(
					"select sActivityName from sa_task t,sa_taskrelation r where t.sid=r.sTaskID1 and r.sTaskID2=?", m.get("task"));
			SysUtils.executeSql("insert into b_rechanddetail(fid,fhandid,fbizrecid,ftaskid,fywh,fsqr,fzl,fHandActivity,version)"
					+ " values(?,?,?,?,?,?,?,?,0)", StringUtils.getNewGuid32(), handid, m.get("fBizRecID"), m.get("task"), m.get("ywh"),
					m.get("sqr"), m.get("zl"), handActivity);
		}
		Map<String, String> m = new HashMap<String, String>();
		m.put("handID", handid);
		m.put("handCode", code);
		return m;
	}

	public static Map<String, Object> queryBatchRecInfo(String bizRecId) {
		Map<String, Object> queryResult = SysUtils
				.queryFldsValue(
						"select * from b_rechand h where h.fhandkind='全局' and exists (select 1 from b_rechanddetail d where d.fhandid=h.fhandid and d.fbizrecid=?) order by FHANDTIME desc",
						bizRecId);
		if (queryResult == null)
			return null;
		else {
			Map<String, Object> batchRecInfo = new HashMap<String, Object>();
			batchRecInfo.put("handCode", queryResult.get("FHANDCODE"));
			batchRecInfo.put("handNum", queryResult.get("FHANDNUM"));
			return batchRecInfo;
		}
	}

	public static Map<String, Object> queryHandListByCode(String handCode) {
		Map<String, Object> flds = SysUtils.queryFldsValue(
				"select FHANDID,fHandKind,FHANDNUM from b_rechand where fhandcode=? order by FHANDTIME desc", handCode);
		if (flds == null)
			throw new BusinessException("该移交清单号不存在");
		String handid = (String) flds.get("FHANDID");
		Table table = SysUtils
				.query("select fbizrecid,fYWH,FSQR,FZL,t.sid,t.sActivityName from b_rechanddetail d   join sa_task t on d.fBizrecid=t.sData1 "
						+ "where  t.sKindID='tkTask' and t.sCreatorPersonID =? and (t.sExecutorPersonID is null or t.sExecutorPersonID<>?) and t.sStatusID='tesReady'"
						+" and not exists (select 1 from b_rechanddetail d1 where d1.ftaskid=t.sid)"
						+ "  and fhandid=? order by fYWH", ContextHelper.getPerson().getID(), ContextHelper.getPerson().getID(), handid);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Iterator<Row> iters = table.iterator();
		while (iters.hasNext()) {
			Row row = iters.next();
			Map<String, String> m = new HashMap<String, String>();
			m.put("fBizRecID", row.getString("FBIZRECID"));
			m.put("task", row.getString("SID"));
			m.put("ywh", row.getString("FYWH"));
			m.put("zl", row.getString("FZL"));
			m.put("sqr", row.getString("FSQR"));
			m.put("handActivity", row.getString("SACTIVITYNAME"));
			list.add(m);
		}
		flds.put("list", list);
		return flds;
	}
}