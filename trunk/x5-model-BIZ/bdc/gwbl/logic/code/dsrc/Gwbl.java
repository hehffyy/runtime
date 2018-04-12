import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.butone.codeinf.model.CodeDef;
import com.butone.codeinf.util.CodeGenerator;
import com.butone.data.SQLUtils;
import com.butone.extend.CacheManager;
import com.butone.extend.DataReplication;
import com.butone.sequencecode.SequenceCodeImpl;
import com.butone.spi.FlowControlUtils;
import com.justep.exception.BusinessException;
import com.justep.model.Activity;
import com.justep.model.ModelUtils;
import com.justep.system.context.ContextHelper;
import com.justep.system.data.DatabaseProduct;
import com.justep.system.data.Expression;
import com.justep.system.data.KSQL;
import com.justep.system.data.ModifyState;
import com.justep.system.data.Row;
import com.justep.system.data.SQL;
import com.justep.system.data.Table;
import com.justep.system.opm.OrgUnit;
import com.justep.system.opm.OrgUtils;
import com.justep.system.transform.Utils;
import com.justep.system.util.CommonUtils;

public class Gwbl {

	public static Map<String, Object> shoujian() {

		Map<String, String> authPermession = new HashMap<String, String>();
		StringBuffer sql_info = new StringBuffer();

		sql_info.append("Select distinct  a.fname , b.flengthname,d.cnt ,a.fACTIVITY , a.fProcess,b.fkzy From  B_Receive a Inner Join ( ")
				.append("select a.fid, SUBSTR(SYS_CONNECT_BY_PATH(fcatalogname || '_' || fid ,'/'),0) As flengthname  , Level As lev, cast(nvl(fkzy,100) As Integer)  fkzy from B_catalog a Connect by prior fid=fparent ")
				.append(") b On a.fwjid = b.fid Left Join (Select fwjid,  Count(fwjid) cnt  From  B_Receive  Group By fwjid  ) d On a.fwjid = d.fwjid  ")
				.append(" inner join SA_OPPERMISSION  op on op.sprocess =a.fprocess and op.sactivity = a.fActivity  ")
				.append("Where  Exists  ( Select * From  (Select a.fid , max(Level) As lev From B_catalog a Connect by prior fid=fparent  Group By a.fid ")
				.append(") c Where  c.lev = b.lev And c.fid = b.fid ) order by  b.fkzy asc , b.flengthname asc ");

		System.out.println("sql:" + sql_info.toString());

		authPermession.put("ORACLE", sql_info.toString());
		Table table = SQL.select(authPermession, null, "/system/data");

		Map<String, String> newPathParams = null;

		MenuGroup root = new MenuGroup();
		root.setName("root");
		List<MenuGroup> parentList = new ArrayList<MenuGroup>();
		parentList.add(root);
		setFuncTreeList(root, parentList, table, newPathParams);

		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(root);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("jsonObject", jsonObject.get("childNodes"));

		return result;
	}

	// 收件维护的配置
	// private static Map<String, String> getReciveConfig() {
	//
	// Map<String, String> params = new HashMap<String, String>();
	// params.clear();
	// StringBuffer sql_info = new StringBuffer();
	// sql_info.append("Select distinct a.flengthname,  b.flengthname,d.cnt,b.fkzy From  B_Receive a Inner Join ( ")
	// .append("select  SUBSTR(SYS_CONNECT_BY_PATH(fcatalogname || '_' || fid ,'/'),0) As flengthname  , Level As lev ,cast(nvl(fkzy,100) As Integer)  fkzy from B_catalog a Connect by prior fid=fparent ")
	// .append(") b On a.fwjid = b.fid Left Join (Select fwjid,  Count(fwjid) cnt  From  B_Receive  Group By fwjid  ) d On a.fwjid = d.fwjid  ")
	// .append("Where  Exists  ( Select * From  (Select a.fid , max(Level) As lev From B_catalog a Connect by prior fid=fparent  Group By a.fid ")
	// .append(") c Where  c.lev = b.lev And c.fid = b.fid ) order by   b.fkzy asc , flengthname asc  ");
	// System.out.println("配置sql====>" + sql_info.toString());
	//
	// params.put("ORACLE", sql_info.toString());
	// Table table = SQL.select(params, null, "/system/data");
	//
	// Iterator<Row> it = table.iterator();
	// boolean ismultLevel = false;
	// while (it.hasNext()) {
	// Row r = it.next();
	//
	// params.put(r.getString("FPROCESS").toString(),
	// r.getString("FLENGTHNAME").toString() + "_1" + "/" +
	// r.getString("FNAME").toString()
	// + "_1" + (!String.valueOf(r.getDecimal("CNT")).equals("1") ? "/环节_1" :
	// ""));
	//
	// if (!String.valueOf(r.getDecimal("CNT")).equals("1"))
	// ismultLevel = true;
	// System.out.println("name=========" +
	// r.getString("FLENGTHNAME").toString() + "_1" + "/" +
	// r.getString("FNAME").toString() + "_1"
	// + (!String.valueOf(r.getDecimal("CNT")).equals("1") ? "/环节_1" : ""));
	// }
	//
	// return params;
	// }

	private static void setFuncTreeList(MenuGroup root, List<MenuGroup> parentList, Table table, Map<String, String> newPathParams) {

		Iterator<Row> it = table.iterator();
		String sactivityfname = "", preName = "", sprocess = "", activity = "", precatologId = "", catologId = "", catologName = "", precatologName = "";
		int cnt = 1, k = 0;

		while (it.hasNext()) {
			Row r = it.next();
			sactivityfname = r.getString("FLENGTHNAME");
			cnt = Integer.parseInt(r.getDecimal("CNT").toString());

			String[] fnameArray = sactivityfname.split("/");

			catologName = "";
			precatologName = "";
			int m = 1;
			if (fnameArray.length == 3)
				m = 2;
			else if (fnameArray.length > 3)
				m = 3;
			else if (cnt == 2)
				m = 3;

			for (int i = fnameArray.length - m; i < fnameArray.length; i++) {
				k = i;
				if (k != (fnameArray.length - m)) {
					precatologId = fnameArray[k - 1].split("_")[1];
					precatologName = fnameArray[k - 1].split("_")[0];
				}

				catologName = fnameArray[k].split("_")[0];
				catologId = fnameArray[k].split("_")[1];

				MenuGroup parentNode = null;
				if (precatologName.equals(""))
					parentNode = root;
				else
					parentNode = getNode(root, parentList, precatologName, precatologId);

				if (getNode(root, parentList, catologName, catologId) == null && i != fnameArray.length - 1) {
					if (!fnameArray[k].equals(preName)) {
						MenuGroup child = new MenuGroup();
						child.setId(catologId);
						child.setName(catologName);
						parentNode.addChildNode(child);
						parentList.add(child);
						preName = fnameArray[k];
					}
				} else if (cnt == 2 && i == fnameArray.length - 1) {

					if (getNode(root, parentList, catologName, catologId) == null) {
						MenuGroup child = new MenuGroup();
						child.setId(catologId);
						child.setName(catologName);
						parentNode.addChildNode(child);
						parentList.add(child);

						parentNode = child;
					}

					parentNode = getNode(root, parentList, catologName, catologId);

					sprocess = r.getString("FPROCESS");
					activity = r.getString("FACTIVITY");

					MenuCollection collection = new MenuCollection();
					collection.setId(catologId);
					collection.setName(r.getString("FNAME"));
					collection.setProcess(sprocess);
					collection.setActivity(activity);
					collection.setUrl(getURL(sprocess, activity));
					parentNode.addChildNode(collection);
					if (cnt == 2)
						preName = r.getString("FNAME");
					else
						preName = fnameArray[k];
				} else if (!fnameArray[k].equals(preName) && i == fnameArray.length - 1 && cnt == 1) {

					sprocess = r.getString("FPROCESS");
					activity = r.getString("FACTIVITY");
					MenuCollection collection = new MenuCollection();
					collection.setId(catologId);
					collection.setName(catologName);
					collection.setProcess(sprocess);
					collection.setActivity(activity);
					collection.setUrl(getURL(sprocess, activity));
					parentNode.addChildNode(collection);
					preName = fnameArray[k];
				}
			}

		}

	}

	private static String getURL(String sprocess, String activity) {
		String[] sprocessArray = sprocess.split("/");
		sprocessArray[0] = "/UI";
		sprocessArray[sprocessArray.length - 1] = activity + ".w";
		return StringUtils.join(sprocessArray, "/");

	}

	// TODO目前是根据名称判断父节点的,如此存在重新的情况,就有问题,这块代码需要优化
	private static MenuGroup getNode(MenuGroup root, List<MenuGroup> parentList, String name, String id) {

		for (MenuGroup child : parentList) {
			if (child.getName().equals(name) && child.getId().equals(id)) //
				return child;
		}
		return null;
	}

	/**
	 * 收件启动流程
	 * 
	 * @return
	 */
	public static String preBizStartFlow(String fBizRecId, String fFlowBizRecId, String fBizProcess, String fActivity, String fExecutorexpr) {

		// TODO list中的第一个环节为首环节，这个需要确定
		if (StringUtils.isEmpty(fActivity)) {
			List<Activity> listActivity = ModelUtils.getProcess(fBizProcess).getActivities();
			if (listActivity.size() == 0)
				return "启动流程不是正确的流程信息,请与管理员联系！";
			Activity activity = listActivity.get(0);
			fActivity = activity.getName();
		}

		List<OrgUnit> executors = OrgUtils.findOrgUnitsByFID(Arrays.asList(fExecutorexpr.split(",")));

		try {
			FlowControlUtils.startFlow(fFlowBizRecId, fBizProcess, fActivity, executors);
		} catch (Exception e) {
			e.printStackTrace();
			return "启动流程出现异常!";
		}
		return "ok";
	}
 

	public static Map<String, Object> getActivityMapping(String fdjdlbh, String fdjxlbh, String fzmlmc) throws ParseException {

		StringBuffer sql_info = new StringBuffer();
		sql_info.append(" Select b.*  From V_ActivityMapping  b where  sAreaId = :currentAreaIdOrName(true)  and  fBrowseProcess is not null ").append(" and fdjdlbh='")
				.append(fdjdlbh).append("'").append(" and fdjxlbh='").append(fdjxlbh).append("' and fzmlmc='").append(fzmlmc).append("'");

		Table mappingTable = KSQL.select(sql_info.toString(), null, "/bdc/gwbl/data", null);

		Map<String, Object> result = new HashMap<String, Object>();

		if (mappingTable.size() == 2) {

			result.put("error", "同时启动了多个流程版本，请与管理员联系，确定生效的流程");
			return result;
		} else if (mappingTable.size() == 0) {
			result.put("error", "当前选择的登记大类和登记小类找不到对应的启动流程配置，请与管理员联系!");
			return result;

		}

		if (mappingTable.size() > 0) {
			Row row = mappingTable.iterator().next();
			result.put("fBrowseProcess", row.getValue("fBrowseProcess"));
			result.put("fBrowseActivity", row.getValue("fBrowseActivity"));// 启动环节
			result.put("fywdm", row.getValue("fywdm"));
			result.put("fywmc", row.getValue("fywdmmc"));
			result.put("fxbts", row.getValue("fxbts"));
			result.put("ftype", row.getValue("fGisType"));

			String fSfxyrt = row.getValue("fSfxyrt") == null ? "不确定" : row.getValue("fSfxyrt").toString().trim();
			result.put("kzsfxyrt", fSfxyrt);

			if ("不确定".equals(fSfxyrt))
				fSfxyrt = "否";
			result.put("sfxyrt", fSfxyrt);

			Integer xbts = Utils.isEmptyString(row.getValue("fxbts").toString()) ? 0 : Integer.parseInt(row.getValue("fxbts").toString());

			String expr = "getDateAfterWorkDays('" + CommonUtils.getCurrentDateTime() + "Z'," + xbts + ", true)";
			Object xbrq = Expression.evaluate(expr, null, ModelUtils.getModel("/base/core/logic/fn")).toString();
			SimpleDateFormat datetime = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);

			Date xbrqdate = datetime.parse(xbrq.toString());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			result.put("xbrq", sdf.format(xbrqdate));

			String fExecutorexpr = row.getValue("fExecutorexpr") == null ? "" : row.getValue("fExecutorexpr").toString().trim();
			String executorId = "", executorName = "", fName = "";
			// 表达式 允许自定义的执行者表达式
			if (Utils.isNotEmptyString(fExecutorexpr)) {

				List<OrgUnit> executors = (List<OrgUnit>) Expression.evaluate(fExecutorexpr, null, ModelUtils.getModel("/base/core/logic/fn"));
				for (OrgUnit executor : executors) {
					executorId = executorId.length() == 0 ? executor.getFID() : (executorId + "," + executor.getFID());
					fName = executor.getFName();
					executorName = executorName.length() == 0 ? fName.split("/")[fName.split("/").length - 1] : (executorName + "," + fName
							.split("/")[fName.split("/").length - 1]);
				}
			}

			result.put("executorId", executorId);
			result.put("executorName", executorName);

			try {

				Activity localActivity = ModelUtils.getProcess(row.getValue("fBrowseProcess").toString()).getActivity(
						row.getValue("fBrowseActivity").toString());
				result.put("fURL", localActivity.getFullName() + ".w");
				result.put("ftysjActivity", "tysj");// 统一收件环节，固定为tysj
			} catch (Exception e) {
				result.put("error", "请确定流程环节的资源是否已经生成!");
				return result;
			}

			// String fExecutorexpr = "";//表达式在配置映射中定义
			// Expression.evaluate(fExecutorexpr, null,
			// ModelUtils.getModel("/base/core/logic/fn"));

			Activity tydjActivity = ModelUtils.getProcess(row.getValue("fBrowseProcess").toString()).getActivity("tysj");
			result.put("ftysjURL", tydjActivity.getFullName() + ".w");// 统一收件环节，固定为tysj

			result.put("fExecutorexpr", ContextHelper.getOperator().getMainOrg().getFID());

		}

		return result;
	}

	public static String shengchengsj(String ywh) {

		Map<String, String> params = new HashMap<String, String>();
		params.clear();
		StringBuffer sql_info = new StringBuffer();
		sql_info.append(" Select b.*, c.fFlowBizRecId From b_bizrec a  Inner Join t_ywslb b On a.fbizrecid = b.fbizrecid  ")
				.append(" left join B_PrepBizRec c on b.Fbizrecid = c.fbizrecid  Where a.fstatus ='bsFinished' ").append(" and b.ywh='" + ywh + "'");
		params.put(DatabaseProduct.ORACLE.name(), sql_info.toString());

		// 权籍受理表
		Table qjTable = SQLUtils.select(params, null, "/system/data");
		if (qjTable.size() == 0)
			return "找不对业务编号为" + ywh + "的权籍调查数据，或该权籍调查还未办结完成,请确定后再生成！";
		Row qjRow = qjTable.iterator().next();
		String fFlowBizRecId = CommonUtils.createGUID();

		// 判断是否已经产生过业务
		sql_info.delete(0, sql_info.length());
		sql_info.append(" Select b.* From B_PrepBizRec a  Inner Join t_ywslb b On a.fbizrecid = b.fbizrecid  ").append(
				"  Where a.fstatus <> '作废' and b.qjywhglbs='" + ywh + "'");
		params.put(DatabaseProduct.ORACLE.name(), sql_info.toString());

		Table historyTable = SQLUtils.select(params, null, "/system/data");
		if (historyTable.size() > 0)
			return "该权籍编号已经做过生成收件，不能重复收件！";

		// 新增预收件表
		Table prepBizRectab = KSQL.select("SELECT b.* FROM B_PrepBizRec b where 1=2 ", null, "/base/core/flow/data", null);
		prepBizRectab.getMetaData().setKeyColumn("b");
		Row prepRecRow = prepBizRectab.appendRow(fFlowBizRecId);
		prepRecRow.setValue("fBizRecId", fFlowBizRecId);
		prepRecRow.setValue("version", 0);
		prepRecRow.setValue("fBizId", "ywtysj");
		prepRecRow.setValue("fBizName", "业务统一收件");
		prepRecRow.setValue("fCreatePerson", ContextHelper.getPerson().getCode());
		prepRecRow.setValue("fCreatePersonName", ContextHelper.getPerson().getName());
		prepRecRow.setValue("fStatus", "未收件");
		prepRecRow.setValue("fFlowBizRecId", CommonUtils.createGUID());
		prepRecRow.setValue("fCreateTime", CommonUtils.getCurrentDateTime());
		prepRecRow.setValue("fBelongOrg", ContextHelper.getPerson().getMainOrg().getCode());
		prepRecRow.setValue("fBelongOrgName", ContextHelper.getPerson().getMainOrg().getName());
		prepRecRow.setState(ModifyState.NEW);

		prepBizRectab.save(ModelUtils.getModel("/base/core/flow/data"));

		Table ywslbTab = KSQL.select("SELECT b.* FROM t_ywslb b where 1=2 ", null, "/EstateSys/BizDB/CommonTables/data", null);
		ywslbTab.getMetaData().setKeyColumn("b");

		String slID = CommonUtils.createGUID();

		// 生成通用编码
		Map<String, String> nodeValues = new HashMap<String, String>();

		nodeValues.put("C931AF974A3F4E70B275C1702098AE26", qjRow.getValue("YWDM").toString());
		nodeValues.put("25DF7CBA52D74FB584D20DDE3D5EEEF8", "1");
		nodeValues.put("7689470265D94ACB9C18191EAE3F061B", qjRow.getValue("QXDM").toString());

		String concept = "t_ywslb", relation = "ywh";
		String idValue = slID;
		CodeDef codeDef = CacheManager.getCodeDef("435A8C6448AC4F02B69EAC4087DCB833");
		if (codeDef == null) {
			throw new BusinessException("未生成" + concept + "." + relation + "的编码定义");
		}

		CodeGenerator generator = new CodeGenerator();
		generator.setSequenceCode(new SequenceCodeImpl());
		String newYwh = generator.makeCodeValue(codeDef, nodeValues, concept, relation, idValue);

		DataReplication.copyRow(qjRow, null, ywslbTab, "fBizRecId", fFlowBizRecId);
		Row ywslbRow = ywslbTab.iterator().next();
		ywslbRow.setValue("FGUID", slID);
		ywslbRow.setValue("qjywhglbs", ywh);
		ywslbRow.setValue("ywh", newYwh);
		ywslbRow.setValue("qjajbh", qjRow.getValue("FBIZRECID").toString());
		ywslbRow.setValue("xbrq", null);
		ywslbRow.setValue("slry", ContextHelper.getPerson().getName());
		ywslbRow.setValue("slsj", CommonUtils.getCurrentDateTime());
		ywslbRow.setValue("zxz_DATA", null);
		ywslbRow.setValue("zxz", "");
		ywslbRow.setValue("status", "草拟");
		ywslbRow.setState(ModifyState.NEW);

		String fromslID = qjRow.getValue("FGUID").toString();

		Table sqrTab = KSQL.select("SELECT b.* FROM t_appler b where   slID='" + fromslID + "'", null, "/EstateSys/BizDB/CommonTables/data", null);

		Iterator<Row> sqrRows = sqrTab.iterator();

		Table sqrToTab = KSQL.select("SELECT b.* FROM t_appler b where 1=2 ", null, "/EstateSys/BizDB/CommonTables/data", null);
		sqrToTab.getMetaData().setKeyColumn("b");

		while (sqrRows.hasNext()) {
			Row sqrRow = sqrRows.next();
			Row toRow = sqrToTab.appendRow(CommonUtils.createGUID());
			DataReplication.copyRow(sqrRow, toRow, sqrTab, "slID", slID);
			toRow.setValue("sqrID", CommonUtils.createGUID());
			toRow.setValue("fBizRecId", fFlowBizRecId);
			toRow.setState(ModifyState.NEW);

		}

		Table ywrTab = KSQL.select("SELECT b.* FROM t_dutor b where slID='" + fromslID + "'", null, "/EstateSys/BizDB/CommonTables/data", null);
		Iterator<Row> ywrRows = ywrTab.iterator();

		Table ywrToTab = KSQL.select("SELECT b.* FROM t_dutor b where 1=2 ", null, "/EstateSys/BizDB/CommonTables/data", null);
		ywrToTab.getMetaData().setKeyColumn("b");

		while (ywrRows.hasNext()) {
			Row ywrRow = ywrRows.next();
			Row toRow = ywrToTab.appendRow(CommonUtils.createGUID());
			DataReplication.copyRow(ywrRow, toRow, sqrTab, "slID", slID);
			toRow.setValue("ywrID", CommonUtils.createGUID());
			toRow.setValue("fBizRecId", fFlowBizRecId);
			toRow.setState(ModifyState.NEW);

		}

		Table yqlTab = KSQL.select("SELECT b.* FROM t_yqlb b where slID='" + fromslID + "'", null, "/EstateSys/BizDB/CommonTables/data", null);
		Iterator<Row> yqlRows = yqlTab.iterator();

		Table yqlToTab = KSQL.select("SELECT b.* FROM t_yqlb b where 1=2 ", null, "/EstateSys/BizDB/CommonTables/data", null);
		yqlToTab.getMetaData().setKeyColumn("b");

		while (yqlRows.hasNext()) {
			Row yqlRow = yqlRows.next();
			Row toRow = yqlToTab.appendRow(CommonUtils.createGUID());
			DataReplication.copyRow(yqlRow, toRow, yqlToTab, "slID", slID);
			toRow.setValue("FGUID", CommonUtils.createGUID());
			toRow.setValue("fBizRecId", fFlowBizRecId);
			toRow.setState(ModifyState.NEW);

		}

		ywslbTab.save(ModelUtils.getModel("/EstateSys/BizDB/CommonTables/data"));
		sqrToTab.save(ModelUtils.getModel("/EstateSys/BizDB/CommonTables/data"));
		ywrToTab.save(ModelUtils.getModel("/EstateSys/BizDB/CommonTables/data"));
		yqlToTab.save(ModelUtils.getModel("/EstateSys/BizDB/CommonTables/data"));

		return "ok";

	}

	public static void scActivityMapping() {
		Map<String, String> params = new HashMap<String, String>();
		params.clear();
		StringBuffer sql_info = new StringBuffer();

		sql_info.append(" Select * From b_v_djqllx a  ").append(" where not exists ( select * from B_ActivityMapping b where  fFlowType='市局流程'    ")
				.append(" and b.fdjdlbh = a.djlxbm and b.fdjxlbh =  a.qllxbzdm and b.fzmlmc = a.fzmlmc  ) ");

		params.put(DatabaseProduct.ORACLE.name(), sql_info.toString());

		Table fromTable = SQLUtils.select(params, null, "/system/data");

		if (fromTable.size() == 0)
			return;

		Table toTable = KSQL.select("SELECT b.* FROM B_ActivityMapping b where 1=2 ", null, "/base/core/flow/data", null);
		toTable.getMetaData().setKeyColumn("b");

		Iterator<Row> rows = fromTable.iterator();

		while (rows.hasNext()) {

			Row row = rows.next();

			Row toRow = toTable.appendRow(CommonUtils.createGUID());
			toRow.setValue("fBrowseActivity", "act1");
			toRow.setValue("fExecutorexpr", "");
			toRow.setValue("fzmlbh", row.getValue("FZMLBH"));
			toRow.setValue("fzmlmc", row.getValue("FZMLMC"));
			toRow.setValue("fdjdlbh", row.getValue("DJLXBM"));
			toRow.setValue("fdjdlmc", row.getValue("DJLXMC"));
			toRow.setValue("fdjxlbh", row.getValue("QLLXBZDM"));
			toRow.setValue("fdjxlmc", row.getValue("QLLXMC"));
			String ywdm = (row.getValue("QLLXBZDM") == null ? "" : row.getValue("QLLXBZDM").toString().trim())
					+ (row.getValue("FZMLBH") == null ? "" : row.getValue("FZMLBH").toString().trim())
					+ (row.getValue("DJLXBM") == null ? "" : row.getValue("DJLXBM").toString().trim());
			toRow.setValue("fywdm", ywdm);

			String ywdmmc = (row.getValue("QLLXMC") == null ? "" : row.getValue("QLLXMC").toString().trim())
					+ (row.getValue("FZMLBH") == null ? "" : row.getValue("FZMLMC").toString().trim())
					+ (row.getValue("DJLXMC") == null ? "" : row.getValue("DJLXMC").toString().trim());
			toRow.setValue("fywdmmc", ywdmmc);

			toRow.setValue("fxbts", row.getValue("FZMLBH") == null ? 30 : 10);
			toRow.setValue("fStatus", "启用");
			toRow.setValue("fFlowType", "市局流程");
			toRow.setValue("fDjType", row.getValue("FDJTYPE"));
			toRow.setValue("version", 0);

		}
		toTable.save("/base/core/flow/data");

	}
}