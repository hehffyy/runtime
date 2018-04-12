import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.butone.data.SQLUtils;
import com.butone.utils.CompareCallBack;
import com.butone.utils.SortUtil;
import com.justep.model.Config;
import com.justep.model.Model;
import com.justep.model.ModelUtils;
import com.justep.system.context.ContextHelper;
import com.justep.system.data.BizData;
import com.justep.system.data.DatabaseProduct;
import com.justep.system.data.KSQL;
import com.justep.system.data.Row;
import com.justep.system.data.SQL;
import com.justep.system.data.Table;
import com.justep.system.opm.OrgNode;
import com.justep.system.opm.OrgUnit;
import com.justep.system.opm.OrgUtils;
import com.justep.system.opm.api.Person;
import com.justep.system.opm.api.PersonHelper;
import com.justep.system.process.ExpressEngine;
import com.justep.system.util.CommonUtils;
import com.justep.util.Utils;

public class Gzzx {
	/**
	 * 阅批领导人员ID
	 */
	private static Set<String> yuePiLingDaoIds = new HashSet<String>();
	private static String ldDeptID;
	/**
	 * 阅批领导业务列表
	 */
	private static List<String> yuePiProcessList = new ArrayList<String>();
	static {
		Config config = (Config) ModelUtils.getModelObjectByFullName("/common/gzzx/logic/action/yuePiLingDaoRange", Config.TYPE);
		String yuePiLingDaoRange = config.getValue();
		if (Utils.isNotEmptyString(yuePiLingDaoRange)) {
			@SuppressWarnings("unchecked")
			List<OrgUnit> list = (List<OrgUnit>) ExpressEngine.calculate(yuePiLingDaoRange, null, ModelUtils.getModel("/base/core/logic/fn"));
			for (OrgUnit o : list) {
				yuePiLingDaoIds.add(OrgUtils.getPersonIDByFID(o.getFID()));
			}
		}
		config = (Config) ModelUtils.getModelObjectByFullName("/common/gzzx/logic/action/yuePiProcessList", Config.TYPE);
		yuePiProcessList.addAll(config.getNames());

		config = (Config) ModelUtils.getModelObjectByFullName("/common/gzzx/logic/action/yuePiLingDaoDeptID", Config.TYPE);
		ldDeptID = config.getValue();
	}

	private static String levelIdToName(String id) {
		if ("1".equals(id))
			return "一般关注";
		else if ("2".equals(id))
			return "特别关注";
		else
			return null;

	}

	public static void setGaunZhu(String guid, String level) {
		Model dataModel = ModelUtils.getModel("/common/gzzx/data");
		Map<String, Object> params = new HashMap<String, Object>();
		String personID = ContextHelper.getPerson().getID();
		params.put("zhuXian", guid);
		params.put("creator", personID);
		Table table = KSQL.select("select b.* from B_GuanZhuZhuXian b where b.fZhuXian=:zhuXian and b.fCreator=:creator", params, dataModel, null);
		table.getMetaData().setKeyColumn("FGUID");
		if (table.size() == 1) {
			Row r = table.iterator().next();
			if (Utils.isEmptyString(level)) {
				table.deleteRows(r.getString("FGUID"));
			} else if (level.equals(r.getString("fLevelId"))) {
				return;
			} else {
				r.setString("fLevelId", level);
				r.setString("fLevelName", levelIdToName(level));
			}
		} else if (Utils.isNotEmptyString(level)) {
			Row r = table.appendRow(CommonUtils.createGUID());
			r.setString("fZhuXian", guid);
			r.setString("fCreator", personID);
			r.setString("fLevelId", level);
			r.setString("fLevelName", levelIdToName(level));
			r.setInt("version", 0);
		}
		table.save(dataModel);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static int increaseHuoDongViewCount(String huodong, boolean isView) {
		Model dataModel = ModelUtils.getModel("/common/gzzx/data");
		Map<String, String> sql = new HashMap<String, String>();
		String cloumnName = "fChuLiCount";
		if (isView)
			cloumnName = "fViewCount";
		sql.put(DatabaseProduct.DEFAULT.name(), "update B_GongZuoHuoDong B set B." + cloumnName + "=nvl(B." + cloumnName + ",0)+1 where B.FGuid=?");
		List binds = new ArrayList<Object>();
		binds.add(huodong);
		SQL.executeUpdate(sql, binds, dataModel);
		sql.clear();
		sql.put(DatabaseProduct.DEFAULT.name(), "select " + cloumnName + " from B_GongZuoHuoDong B  where B.FGuid=?");
		Table table = SQL.select(sql, binds, dataModel);
		if (table.size() > 0)
			return Integer.parseInt(table.iterator().next().getValue(0).toString());
		else
			return 0;
	}

	public static int getPermission() {
		int result = 2;
		String sCode = ContextHelper.getOperator().getCode();
		Table tab = KSQL.select("select t.sCardNO from SA_OPPerson t where t.sCode='" + sCode + "'", null, "/system/data", null);
		String sCardNO = tab.iterator().next().getString("sCardNO");
		if (Utils.isNotNull(sCardNO) && Utils.isNotEmptyString(sCardNO)) {
			Integer s = Integer.parseInt(sCardNO);
			if (s <= Integer.parseInt("0700010010"))
				result = 0;
			else if (s <= Integer.parseInt("0800180000"))
				result = 1;
			else
				result = 2;
		} else {
			result = 2;
		}
		return result;
	}

	/**
	 * 获得指定活动的处理类型
	 * 
	 * @param huodong
	 * @return
	 */
	public static String getHuoDongHandleKind(String huodong) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", huodong);
		Table t;
		if (yuePiLingDaoIds.contains(ContextHelper.getPerson().getID())) {
			StringBuffer sb = new StringBuffer();
			Iterator<String> itor = yuePiProcessList.iterator();
			while (itor.hasNext()) {
				sb.append("'").append(itor.next()).append("'");
				if (itor.hasNext())
					sb.append(",");
			}
			// B_GongZuoHuoDong.fActivityItem 领导批示的fActivityItem 为来文登记的案卷编号
			String sql = "select T,T.sTypeName from SA_Task T\n"
					//
					+ "join B_GongZuoHuoDong A on A=:id\n"
					//
					+ "join B_GongZuoHuoDong B on A.fZhuXian=B.fZhuXian and B.fItemType='领导批示'\n"
					//
					+ "join B_BizRecRelation R on R.fParentID=B.fActivityItem\n"
					+ "where T.sData1=R.fBizRecId and T.sKindID in ('tkTask','tkExecutor') and T.sExecutorPersonID=:currentPersonID() and T.sProcess in ("
					+ sb.toString() + ")";
			t = KSQL.select(sql, params, "/common/gzzx/data", null);
			if (t.size() > 0)
				return t.iterator().next().getString("sTypeName");
		}

		t = KSQL.select(
				"select B.fHandleKind as kind from B_HuoDongRange B join SA_OPOrg SA_OPOrg on SA_OPOrg.sPersonID = :currentPersonID() and instr(SA_OPOrg.sFID, B.fOrgUnitFID)>0 where B.fHuoDong=:id",
				params, "/common/gzzx/data", null);
		if (t.size() == 0) {
			return "公告";
		} else if (t.getRow("kind", "阅办") != null) {
			return "阅办";
		} else {
			return "阅知";
		}
	}

	/**
	 * 查询当前阅件/网上舆情相同主线的领导批示流程任务
	 * 
	 * @param zhuxian
	 * @return
	 */
	public static Map<String, Object> queryLingDaoYuePiTask(String zhuxian) {
		StringBuffer sb = new StringBuffer();
		Iterator<String> itor = yuePiProcessList.iterator();
		while (itor.hasNext()) {
			sb.append("'").append(itor.next()).append("'");
			if (itor.hasNext())
				sb.append(",");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("zhuxian", zhuxian);
		// B_GongZuoHuoDong.fActivityItem 领导批示的fActivityItem 为来文登记的案卷编号
		String sql = "select T,T.sEURL as sEURL,T.sProcess as sProcess,T.sActivity as sActivity from SA_Task T\n"
				//
				+ "join B_GongZuoHuoDong B on B.fZhuXian=:zhuxian and B.fItemType='领导批示'\n"
				//
				+ "join B_BizRecRelation R on R.fParentID=B.fActivityItem\n"
				+ "where T.sData1=R.fBizRecId and T.sKindID in ('tkTask','tkExecutor') and T.sExecutorPersonID=:currentPersonID() and T.sStatusID in ('tesReady','tesExecuting') and T.sProcess in ("
				+ sb.toString() + ")";
		Table t = KSQL.select(sql, params, "/common/gzzx/data", null);
		if (t.size() > 0) {
			Map<String, Object> ret = new HashMap<String, Object>();
			Row r = t.iterator().next();
			ret.put("sID", r.getString("T"));
			ret.put("sEURL", r.getString("sEURL"));
			ret.put("sProcess", r.getString("sProcess"));
			ret.put("sActivity", r.getString("sActivity"));
			return ret;
		}
		return null;
	}

	private static final String MAX_ORG_SEQUENCE = "ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ";

	public static List<Map<String, Object>> getPersonsOfDept() {
		List<Map<String, Object>> ret = new ArrayList<Map<String, Object>>();
		@SuppressWarnings("unchecked")
		List<OrgUnit> list = (List<OrgUnit>) ExpressEngine
				.calculate("findOrgChildren2(currentDeptFID(),'org.sOrgKindID=''psm''',null,false,false,true)", null,
						ModelUtils.getModel("/base/core/logic/fn"));
		for (OrgUnit o : list) {
			Map<String, Object> m = new HashMap<String, Object>();
			String id = OrgUtils.getPersonIDByFID(o.getFID());
			if (id.equals(ContextHelper.getPerson().getID()))
				continue;
			m.put("id", id);
			m.put("name", OrgUtils.getPersonNameByFName(o.getFID(), o.getFName()));
			Person p = PersonHelper.loadPerson((String) m.get("id"), Arrays.asList("sGlobalSequence"));
			m.put("sequence", p.getExtValue("sGlobalSequence"));
			ret.add(m);
		}
		if (ret.size() > 2) {
			@SuppressWarnings("unchecked")
			Map<String, Object>[] arr = ret.toArray(new Map[0]);
			SortUtil.sort(arr, new CompareCallBack<Map<String, Object>>() {
				@Override
				public int compare(Map<String, Object> org1, Map<String, Object> org2) {
					String key1 = (String) org1.get("sequence");
					if (key1 == null) {
						key1 = MAX_ORG_SEQUENCE;
					}
					String key2 = (String) org2.get("sequence");
					if (key2 == null) {
						key2 = MAX_ORG_SEQUENCE;
					}
					return key1.compareTo(key2);
				}
			});
			return Arrays.asList(arr);
		} else {
			return ret;
		}
	}

	/**
	 * 移动端阅件查询
	 * 
	 * @param itemType
	 *            阅件类型
	 * @param state
	 *            状态
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Table queryMyYuejian2(String concept, String select, String from, String aggregate, String dataModel, String fnModel,
			String condition, List range, Boolean distinct, String idColumn, String filter, Integer limit, Integer offset, String columns,
			String orderBy, String aggregateColumns, Map<String, Object> variables, String state, String itemType) {
		if (variables == null)
			variables = new HashMap<String, Object>();
		boolean isYuePiLingDao = yuePiLingDaoIds.contains(ContextHelper.getPerson().getID());
		if (Utils.isEmptyString(itemType) || "null".equals(itemType) || "undefined".equals(itemType)) {
			condition = "B_GongZuoHuoDong.fItemType in ('阅件','网上舆情') and (B_GongZuoHuoDong.fPublish='是' or exists (select  1 from B_HuoDongRange B_HuoDongRange join SA_OPOrg SA_OPOrg on  SA_OPOrg.sPersonID = :currentPersonID() AND instr(SA_OPOrg.sFID, B_HuoDongRange.fOrgUnitFID) > 0   where B_GongZuoHuoDong = B_HuoDongRange.fHuoDong))";
		} else if ("公告信息".equals(itemType)) {
			variables.put("itemType", "阅件");
			condition = "B_GongZuoHuoDong.fItemType='阅件' and B_GongZuoHuoDong.fPublish = '是'";
		} else if ("网上舆情".equals(itemType)) {
			variables.put("itemType", itemType);
			condition = "B_GongZuoHuoDong.fItemType= :itemType and exists (select  1 from B_HuoDongRange B_HuoDongRange join SA_OPOrg SA_OPOrg on  SA_OPOrg.sPersonID = :currentPersonID() AND instr(SA_OPOrg.sFID, B_HuoDongRange.fOrgUnitFID) > 0   where B_GongZuoHuoDong = B_HuoDongRange.fHuoDong)";
		} else {
			variables.put("itemType", "阅件");
			condition = "B_GongZuoHuoDong.fItemType= :itemType";
			if ("阅处件".equals(itemType)) {
				variables.put("handleKind", "阅办");
				condition = SQLUtils
						.appendCondition(
								condition,
								"and",
								"exists (select 1 from B_HuoDongRange B_HuoDongRange join SA_OPOrg SA_OPOrg on  SA_OPOrg.sPersonID = :currentPersonID() AND instr(SA_OPOrg.sFID, B_HuoDongRange.fOrgUnitFID) > 0   where B_GongZuoHuoDong = B_HuoDongRange.fHuoDong and B_HuoDongRange.fHandleKind=:handleKind)");
			} else {
				variables.put("handleKind", "阅知");
				condition = SQLUtils
						.appendCondition(
								condition,
								"and",
								"exists (select  1 from B_HuoDongRange B_HuoDongRange join SA_OPOrg SA_OPOrg on  SA_OPOrg.sPersonID = :currentPersonID() AND instr(SA_OPOrg.sFID, B_HuoDongRange.fOrgUnitFID) > 0   where B_GongZuoHuoDong = B_HuoDongRange.fHuoDong and B_HuoDongRange.fHandleKind=:handleKind)");
			}

		}

		from = "B_GongZuoHuoDong B_GongZuoHuoDong optional join B_HuoDongChuLi CL on CL.fHuoDong=B_GongZuoHuoDong and CL.fOrgUnitID=:currentPersonID()";
		if (isYuePiLingDao && !"网上舆情".equals(itemType)) {
			from += "\noptional join B_GongZuoHuoDong PS on PS.fActivityItem=B_GongZuoHuoDong.fActivityItem and PS.fItemType='领导批示'";
			from += "\noptional join B_HuoDongChuLi PSYJ on PSYJ.fHuoDong=PS and PSYJ.fOrgUnitID=:currentPersonID()";
		}

		if ("已阅".equals(state)) {
			condition = SQLUtils.appendCondition(condition, "and", "CL.fFinishTime is not null"
					+ (isYuePiLingDao && !"网上舆情".equals(itemType) ? " or PSYJ.fFinishTime is not null" : ""));
		} else {
			condition = SQLUtils.appendCondition(condition, "and",
					"(CL is null or CL.fFinishTime is null)"
							+ (isYuePiLingDao && !"网上舆情".equals(itemType) ? " and (PSYJ is null or PSYJ.fFinishTime is null)" : ""));
		}
		variables.put("ld", isYuePiLingDao ? 1 : 0);
		Table t = BizData.query(concept, idColumn, select, from, condition, range, filter, distinct, offset, limit, columns, orderBy, aggregate,
				aggregateColumns, variables, dataModel, fnModel);
		return t;
	}

	/**
	 * mobile portal查询未处理阅件消息
	 * 
	 * @return
	 */
	public static Map<String, Object> queryNoHandleYueJianMessage() {
		Map<String, Object> variables = new HashMap<String, Object>();
		boolean isYuePiLingDao = yuePiLingDaoIds.contains(ContextHelper.getPerson().getID());
		String personId = ContextHelper.getPerson().getID();
		String condition = "B_GongZuoHuoDong.fItemType in ('阅件','网上舆情') and (B_GongZuoHuoDong.fPublish='是' or exists (select  1 from B_HuoDongRange B_HuoDongRange join SA_OPOrg SA_OPOrg on  SA_OPOrg.sPersonID = '"+personId+"' AND instr(SA_OPOrg.sFID, B_HuoDongRange.fOrgUnitFID) > 0   where B_GongZuoHuoDong = B_HuoDongRange.fHuoDong))";
		String from = "\nB_GongZuoHuoDong B_GongZuoHuoDong optional join B_HuoDongChuLi CL on CL.fHuoDong=B_GongZuoHuoDong and CL.fOrgUnitID='"+personId+"'";
		if (isYuePiLingDao) {
			from += "\noptional join B_GongZuoHuoDong PS on PS.fActivityItem=B_GongZuoHuoDong.fActivityItem and PS.fItemType='领导批示'";
			from += "\noptional join B_HuoDongChuLi PSYJ on PSYJ.fHuoDong=PS and PSYJ.fOrgUnitID='"+personId+"'";
		}
//		String condition = "B_GongZuoHuoDong.fItemType in ('阅件','网上舆情') and (B_GongZuoHuoDong.fPublish='是' or exists (select  1 from B_HuoDongRange B_HuoDongRange join SA_OPOrg SA_OPOrg on  SA_OPOrg.sPersonID = :currentPersonID() AND instr(SA_OPOrg.sFID, B_HuoDongRange.fOrgUnitFID) > 0   where B_GongZuoHuoDong = B_HuoDongRange.fHuoDong))";
//		String from = "\nB_GongZuoHuoDong B_GongZuoHuoDong optional join B_HuoDongChuLi CL on CL.fHuoDong=B_GongZuoHuoDong and CL.fOrgUnitID=:currentPersonID()";
//		if (isYuePiLingDao) {
//			from += "\noptional join B_GongZuoHuoDong PS on PS.fActivityItem=B_GongZuoHuoDong.fActivityItem and PS.fItemType='领导批示'";
//			from += "\noptional join B_HuoDongChuLi PSYJ on PSYJ.fHuoDong=PS and PSYJ.fOrgUnitID=:currentPersonID()";
//		}
		condition = SQLUtils.appendCondition(condition, "and", "(CL is null or CL.fFinishTime is null)"
				+ (isYuePiLingDao ? " and (PSYJ is null or PSYJ.fFinishTime is null)" : ""));
		Table data = KSQL.select("select count(B_GongZuoHuoDong) as fCount from " + from + " where " + condition, variables, "/common/gzzx/data",
				null);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("fLabel", "阅件");
		result.put("fProcess", "/common/gzzx/process/gzzx/gzzxProcess");
		result.put("fActivity", "yueJianCenter");
		result.put("fUrl", "$model/UI2/SA/task/taskView/yueJianCenter.m.w");
		int size = data.iterator().next().getDecimal("fCount").intValue();
		if (size > 0) {
			result.put("fSubLabel", size + "条未处理");
			result.put("fContent", "");
			result.put("fTag", "");
			result.put("fDate", null);
			result.put("fCount", size);

		} else {
			result.put("fSubLabel", "无");
			result.put("fContent", "");
			result.put("fTag", "");
			result.put("fDate", null);
			result.put("fCount", 0);
		}

		return result;
	}

	public static Map<String, Object> statisticsNoHandleYueJianCount(String itemTypes) {
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("personID", ContextHelper.getPerson().getID());
		boolean isYuePiLingDao = yuePiLingDaoIds.contains(ContextHelper.getPerson().getID());
		String from = "B_GongZuoHuoDong B left join B_HuoDongChuLi CL on CL.fHuoDong=B.FGUID and CL.fOrgUnitID=:personID";
		if (isYuePiLingDao) {
			from += "\nleft join B_GongZuoHuoDong PS on PS.fActivityItem=B.fActivityItem and PS.fItemType='领导批示'";
			from += "\nleft join B_HuoDongChuLi PSYJ on PSYJ.fHuoDong=PS.FGUID and PSYJ.fOrgUnitID=:personID";
		}
		// 未阅
		String condition = "(CL.FGUID is null or CL.fFinishTime is null)"
				+ (isYuePiLingDao ? " and (PSYJ.FGUID is null or PSYJ.fFinishTime is null)" : "");
		List<String> args = Arrays.asList(itemTypes.split(","));
		Map<String, String> sqls = new HashMap<String, String>();
		Table t;
		Row r;
		if (args.contains("网上舆情")) {
			String sql = "select count(B.FGUID) FCNT from " + from + " where " + SQLUtils.appendCondition(condition, " and ", "B.fItemType='网上舆情'");
			sqls.put(DatabaseProduct.DEFAULT.name(), SQLUtils.fixSQL(sql, variables, true));
			t = SQL.select(sqls, SQLUtils.parseSqlParameters(sql, variables), "/common/gzzx/data");
			r = t.iterator().next();
			ret.put("网上舆情", r.getValue(0));
		}
		if (args.contains("公告信息")) {
			String sql = "select count(B.FGUID) FCNT from " + from + " where "
					+ SQLUtils.appendCondition(condition, " and ", "B.fItemType='阅件' and B.fPublish='是'");
			sqls.put(DatabaseProduct.DEFAULT.name(), SQLUtils.fixSQL(sql, variables, true));
			t = SQL.select(sqls, SQLUtils.parseSqlParameters(sql, variables), "/common/gzzx/data");
			r = t.iterator().next();
			ret.put("公告信息", r.getValue(0));
		}
		if (args.contains("阅处件")) {
			String where = SQLUtils
					.appendCondition(
							condition,
							" and ",
							"B.fItemType='阅件' and exists (select 1 from B_HuoDongRange B_HuoDongRange join SA_OPOrg SA_OPOrg on  SA_OPOrg.sPersonID = :personID AND instr(SA_OPOrg.sFID, B_HuoDongRange.fOrgUnitFID) > 0 where B.FGUID = B_HuoDongRange.fHuoDong %s)");
			String sql = "select count(B.FGUID) FCNT from " + from + " where " + String.format(where, " and B_HuoDongRange.fHandleKind='阅办'");
			sqls.put(DatabaseProduct.DEFAULT.name(), SQLUtils.fixSQL(sql, variables, true));
			t = SQL.select(sqls, SQLUtils.parseSqlParameters(sql, variables), "/common/gzzx/data");
			r = t.iterator().next();
			ret.put("阅处件", r.getValue(0));
		}
		if (args.contains("普通阅件")) {
			String where = SQLUtils
					.appendCondition(
							condition,
							" and ",
							"B.fItemType='阅件' and exists (select 1 from B_HuoDongRange B_HuoDongRange join SA_OPOrg SA_OPOrg on  SA_OPOrg.sPersonID = :personID AND instr(SA_OPOrg.sFID, B_HuoDongRange.fOrgUnitFID) > 0 where B.FGUID = B_HuoDongRange.fHuoDong %s)");
			String sql = "select count(B.FGUID) FCNT from " + from + " where "
					+ String.format(where, " and (B_HuoDongRange.fHandleKind is null or B_HuoDongRange.fHandleKind<>'阅办')");
			sqls.put(DatabaseProduct.DEFAULT.name(), SQLUtils.fixSQL(sql, variables, true));
			t = SQL.select(sqls, SQLUtils.parseSqlParameters(sql, variables), "/common/gzzx/data");
			r = t.iterator().next();
			ret.put("普通阅件", r.getValue(0));
		}
		return ret;
	}

	/**
	 * @param huodong
	 * @return
	 */
	public static Table queryHuoDongChuLiDeptList(String huodong, Boolean includeCurrentDept, Boolean includeLD) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("huodong", huodong);
		Table t = KSQL
				.select("SELECT DISTINCT B.fDeptID as id,B.fDeptName as name,org.sSequence from B_HuoDongChuLi B join SA_OPOrg org on org=B.fDeptID where B.fHuoDong=:huodong order by org.sSequence",
						params, "/common/gzzx/data", null);
		t.getMetaData().setKeyColumn("id");
		t.getProperties().put(Table.PROP_NAME_ROWID, "id");
		if (includeCurrentDept != null && Boolean.FALSE.equals(includeCurrentDept) || includeLD != null && Boolean.FALSE.equals(includeLD)) {
			String deptId = ContextHelper.getPersonMember().getDept() != null ? ContextHelper.getPersonMember().getDept().getID() : ContextHelper
					.getPersonMember().getOgn().getID();
			Iterator<Row> itor = t.iterator();
			List<String> ids = new ArrayList<String>();
			while (itor.hasNext()) {
				Row r = itor.next();
				if (!includeCurrentDept && r.getString("id").equals(deptId) || !includeLD
						&& ("," + ldDeptID + ",").contains("," + r.getString("id") + ",")) {
					ids.add(r.getString("id"));
				}
			}
			for (String id : ids)
				t.deleteRows(id);
		}
		return t;
	}

	public static String getfBizRecId(String fParentID) {
		HashMap<String, String> sqlMap = new HashMap<String, String>();
		String sql = "select wm_concat(fBizRecId) from B_BizRecRelation where fParentID = '" + fParentID + "' and fKind in('来文管理-领导批示','来文管理-领导批阅')";
		sqlMap.put(DatabaseProduct.DEFAULT.name(), sql);
		Table tab = SQL.select(sqlMap, null, "/system/data");
		if (tab.size() > 0)
			return tab.iterator().next().getValue(0).toString();
		return "";
	}

	public static void batchRead(String fHuoDong, Boolean keepUnRead, String type) {
		String fHuoDongs[] = fHuoDong.split(",");
		String personID = ContextHelper.getPersonMember().getPerson().getID();
		String personName = ContextHelper.getPersonMember().getPerson().getName();
		Person p = PersonHelper.loadPerson(personID, Arrays.asList("sGlobalSequence"));
		String sGlobalSequence = String.valueOf(p.getExtValue("sGlobalSequence"));
		// 第一步 查询是否有处理信息
		for (int i = 0; i < fHuoDongs.length; i++) {
			String sql = "";
			ArrayList<Object> list = new ArrayList<Object>();
			HashMap<String, String> sqlMap = new HashMap<String, String>();
			if (keepUnRead) {
				sql = "update B_HuoDongChuLi cl set cl.ffinishtime=null where cl.forgunitid=? and cl.fhuodong=? and cl.ffinishtime is not null";
				sqlMap.clear();
				list.clear();
				list.add(personID);
				list.add(fHuoDongs[i]);
				sqlMap.put(DatabaseProduct.DEFAULT.name(), sql);
				SQL.executeUpdate(sql, list, "/common/gzzx/data");
			} else {
				sql = "select cl.fcontent,cl.ffinishtime from B_HuoDongChuLi cl where cl.fhuodong=? and cl.forgunitid=?";
				sqlMap.clear();
				list.clear();
				list.add(fHuoDongs[i]);
				list.add(personID);
				sqlMap.put(DatabaseProduct.DEFAULT.name(), sql);
				Table tab = SQL.select(sqlMap, list, "/common/gzzx/data");
				// 第二步 如果有的话 直接更新 没有的话 直接插入
				if (tab.size() > 0) {
					Row r = tab.iterator().next();
					if (Utils.isNull(r.getValue(0)) || Utils.isNull(r.getValue(1))) {
						sql = "update B_HuoDongChuLi cl set cl.ffinishtime=sysdate where cl.forgunitid=? and cl.fhuodong=? and (cl.fcontent is null or cl.ffinishtime is null)";
						sqlMap.clear();
						list.clear();
						list.add(personID);
						list.add(fHuoDongs[i]);
						sqlMap.put(DatabaseProduct.DEFAULT.name(), sql);
						SQL.executeUpdate(sql, list, "/common/gzzx/data");
					}
				} else {
					sql = "insert into B_HuoDongChuLi(FGUID,version,fhuodong,fActivityItem,fOrgUnitID,fOrgUnitName,fContent,fCreateTime,fFinishTime,fGlobalOrder,fDeptID,fDeptName)"
							+ " select sys_guid(),0,fguid,fActivityItem,?,?,'已阅',sysdate,sysdate,?,?,? from B_GongZuoHuoDong where fguid=?";
					sqlMap.clear();
					list.clear();
					list.add(personID);
					list.add(personName);
					list.add(sGlobalSequence);
					OrgNode o = ContextHelper.getPersonMember().getDept();
					if (o == null)
						o = ContextHelper.getPersonMember().getOgn();
					list.add(o.getID());
					list.add(o.getName());

					list.add(fHuoDongs[i]);
					sqlMap.put(DatabaseProduct.DEFAULT.name(), sql);
					SQL.executeUpdate(sql, list, "/common/gzzx/data");
				}
			}

		}
	}

}