import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.QName;

import com.justep.client.ClientUtils;
import com.justep.exception.BusinessException;
import com.justep.message.CommonMessages;
import com.justep.message.SystemMessages;
import com.justep.model.Action;
import com.justep.model.Activity;
import com.justep.model.Config;
import com.justep.model.ModelUtils;
import com.justep.system.context.ActionContext;
import com.justep.system.context.ContextHelper;
import com.justep.system.context.User;
import com.justep.system.context.UserManager;
import com.justep.system.data.BizData;
import com.justep.system.data.ColumnTypes;
import com.justep.system.data.DataPermission;
import com.justep.system.data.KSQL;
import com.justep.system.data.ModifyState;
import com.justep.system.data.Row;
import com.justep.system.data.Table;
import com.justep.system.data.TableUtils;
import com.justep.system.opm.Operator;
import com.justep.system.opm.OrgCache;
import com.justep.system.opm.OrgConstants;
import com.justep.system.opm.OrgKinds;
import com.justep.system.opm.OrgQuery;
import com.justep.system.opm.OrgUtils;
import com.justep.system.opm.Person;
import com.justep.system.opm.PersonMember;
import com.justep.system.opm.api.OpmUtils;
import com.justep.system.opm.api.PersonHelper;
import com.justep.system.process.ExpressEngine;
import com.justep.system.process.ProcessUtils;
import com.justep.system.util.BizSystemException;
import com.justep.system.util.CommonUtils;
import com.justep.util.Utils;

public class OPMProcedure {
	// public static Object login(String name, Date loginDate, String pwd,
	// String lang) {
	// return OrgSystem.login(name, loginDate, pwd, lang);
	// }
	//
	// public static Object ntLogin(String name, Date loginDate, String lang) {
	// return OrgSystem.ntLogin(name, loginDate, lang);
	// }

	public static void loadRegistUser() {
		OrgUtils.loadRegistUser();
	}

	private static String getLoginNameByMail(String mail) {
		String loginName = null;
		if (Utils.isNotEmptyString(mail) && mail.contains("@")) {
			String query = "select p.sLoginName from SA_OPPerson p where UPPER(p.sMail)=:mail";
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("mail", mail.toUpperCase());
			Table table = KSQL.select(query, params, ProcessUtils.CORE_MODEL, null);
			Iterator<Row> it = table.iterator();
			if (it.hasNext()) {
				Row r = it.next();
				loginName = r.getString("sLoginName");
			} else {
				loginName = mail;
			}

		} else {
			loginName = mail;
		}

		return loginName;
	}

	public static boolean changePassword(String name, String opwd, String npwd) {
		name = getLoginNameByMail(name);
		return OrgUtils.changePassword(name, opwd, npwd);
	}

	//更新当前登录人的num，用于同步message组织UID
	public static boolean updatePersonNum(int num, String pid) {
		String id = Utils.isNotEmptyString(pid) ? pid : ContextHelper.getPerson().getID();
		String ksql = "UPDATE SA_OPPerson SA_OPPerson " + "	SET SA_OPPerson.sNumb = :num " + "	WHERE SA_OPPerson = :ID ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("num", num);
		params.put("ID", id);
		return 1 == KSQL.executeUpdate(ksql, params, OrgConstants.OPM_DATA_MODEL, null);
	}

	public static boolean recoverPassword(String code) {
		return OrgUtils.recoverPassword(code);
	}

	public static Table query(String concept, String idColumn, String select, String from, String condition, List<DataPermission> range,
			String filter, Boolean distinct, int offset, int limit, String columns, String orderBy, String aggregate, String aggregateColumns,
			Map<String, Object> variables, String dataModel, String fnModel, String manageCodes, boolean includeChildrenOfManage,
			boolean includePersonPhotoLastModified, Map<String, List<Object>> cacheFilter, int includeChildren) {
		if (includePersonPhotoLastModified) {
			// TODO tkj 2016-10-12，getOrgs.j 返回人员的全局顺序号
			select = select
					+ ",SA_OPPerson.sPhotoLastModified as sPhotoLastModified,SA_OPPerson.sNumb as sNumb,SA_OPPerson.sGlobalSequence as sGlobalSequence";
			from = from + " optional join SA_OPPerson SA_OPPerson on SA_OPOrg.sPersonID = SA_OPPerson";
		}
		if (null != cacheFilter) {
			OrgCache orgCache = OrgCache.getOrgCache();
			return orgCache.filter(cacheFilter, includeChildren);
		} else
			return OrgQuery.query(concept, idColumn, select, from, condition, range, filter, distinct, offset, limit, columns, orderBy, aggregate,
					aggregateColumns, variables, dataModel, fnModel, manageCodes, includeChildrenOfManage);
	}

	public static void logout(String sessionID) {
		UserManager.instance().removeUser(ContextHelper.getSessionContext().getSessionID());
	}

	public static Object login(String name, Date loginDate, String pwd, String lang, String ip, Map<Object, Object> options) {
		return OPMProcedure.login(name, loginDate, pwd, lang, true, ip, options);
	}

	public static Object ntLogin(String name, Date loginDate, String lang, String ip, Map<Object, Object> options) {
		return OPMProcedure.login(name, loginDate, null, lang, false, ip, options);
	}

	private static void checkPersonValidDate(String name) {
		Map<String, Object> varMap = new HashMap<String, Object>();
		varMap.put("name", name.toUpperCase());
		Table t = KSQL.select("select p.sJoinDate from SA_OPPerson p where upper(p.sLoginName)=:name", varMap, "/system/data", null);
		if (t.size() > 0) {
			Date d = t.iterator().next().getDate("sJoinDate");
			if (d != null && d.before(CommonUtils.getCurrentDate())) {
				throw new BusinessException("您登录系统的有效日期截止到" + new SimpleDateFormat("yyyy年MM月dd日").format(d));
			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static Object login(String name, Date loginDate, String pwd, String lang, boolean checkPwd, String ip, Map<Object, Object> options) {
		checkPersonValidDate(String name);
		
		ContextHelper.getSessionContext().put(OrgConstants.SYSTEM_LOGIN_IP, ip);
		name = getLoginNameByMail(name);
		// TODO: 默认语言是中文，将来这个点应该可配置
		if (Utils.isEmptyString(lang)) {
			lang = "zh_CN";
		}
		List result = (List) OrgUtils.login1(name, loginDate, pwd, lang, checkPwd, ip, options);

		if ("true".equals(result.get(0))) {
			Operator operator = ContextHelper.getOperator();
			// TODO 查询人员扩展信息
			Person p = ContextHelper.getPerson();
			Map<String, Object> d = PersonHelper.loadPerson(p.getID(), Arrays.asList("sTitle", "sGlobalSequence")).getExtValues();
			for (String k : d.keySet()) {
				p.setAttribute(k, d.get(k));
			}
			p.setAttribute("sysParams", true);
			// 设置任务中心签收模式
			Config c = (Config)ModelUtils.getModel("/system/config").getLocalObject("signMode", Config.TYPE);
			String expr = c==null ? "":c.getValue();
			try{
				p.setAttribute("signMode", ExpressEngine.calculateBoolean(expr, null, true, ModelUtils.getModel("/base/core/logic/fn")));
			}catch(Exception e){
			}
			UserManager.instance().addUser(
					new User(ContextHelper.getSessionContext().getSessionID(), operator.getName(), operator.getID(),
							CommonUtils.getCurrentDateTime(), operator.getLoginIP()));
		}

		String deviceType = "未知";
		String operatingSystem = "未知";
		if (options != null) {
			deviceType = (String) options.get("DeviceType");
			if (Utils.isEmptyString(deviceType)) {
				deviceType = "未知";
			}

			operatingSystem = (String) options.get("OperatingSystem");
			if (Utils.isEmptyString(operatingSystem)) {
				operatingSystem = "未知";
			}
		}
		ContextHelper.getSessionContext().put("DeviceType", deviceType);

		ContextHelper.getSessionContext().put("OperatingSystem", operatingSystem);

		checkClientApp();
		// cache orgVersion
		String orgVersion = OrgCache.getOrgVersion();
		result.add(orgVersion);
		return result;
	}

	private static void checkClientApp() {
		if (ClientUtils.enabledClient()) {
			String client = ClientUtils.getClientValue();
			try {
				ClientUtils.closeClient();
				String query = "select cp.sRentEndTime, cp.sRentStartTime, cp.sRentNumber, cp.sValidState from SA_ClientApp cp "
						+ "where cp.sClientID=:client";
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("client", client);
				Table table = KSQL.select(query, params, "/system/data", null);
				Iterator<Row> it = table.iterator();

				boolean dayOut = false;
				Timestamp now = new Timestamp(System.currentTimeMillis());
				while (it.hasNext()) {
					Row r = it.next();
					Timestamp end = r.getDateTime("sRentEndTime");
					if ((end == null) || (end.after(now))) {
						dayOut = false;
						break;
					} else {
						dayOut = true;
					}
				}

				if (dayOut) {
					throw BizSystemException.create(SystemMessages.ALL_RENT_DAY_ERROR);
				}
			} finally {
				ClientUtils.resetClient();
			}
		}
	}

	/*
	 * private static Object login(String name, Date loginDate, String pwd,
	 * String lang, boolean checkPwd, String ip, Map<Object, Object> options) {
	 * RequestContext requestContext = ModelUtils.getRequestContext();
	 * Utils.checkNotExist("RequestContext", requestContext); SessionContext
	 * sessionContext = requestContext.getSessionContext();
	 * Utils.checkNotExist("SessionContext", sessionContext); Person psn =
	 * OrgUtils.getPerson(name, pwd, checkPwd); Utils.check(psn != null,
	 * "未找到登录名为：", name, "的 人员！");
	 * 
	 * Operator operator = new Operator(psn, loginDate, lang);
	 * sessionContext.put(OrgConstants.ORG_CACHE, operator); // SystemCache
	 * systemCache = new SystemCache(loginDate, lang); //
	 * sessionContext.put(OrgConstants.SYSTEM_CACHE, systemCache);
	 * 
	 * List<Object> result = new ArrayList<Object>(); result.add(Boolean.TRUE);
	 * result.add(operator.getID()); result.add(operator.getName());
	 * result.add(operator.getMainOrg().getID());
	 * result.add(operator.getMainOrg().getName());
	 * result.add(operator.getMainOrg().getFID());
	 * result.add(operator.getCode()); result.add(ip); return result; }
	 */
	public static Document queryOrgsByResource(String resourceID, String typeID, String process, String activity, String action) {
		Utils.check(Utils.isNotEmptyString(resourceID), CommonMessages.class, CommonMessages.PARAM_NULL1, "resourceID");
		Utils.check(Utils.isNotEmptyString(typeID), CommonMessages.class, CommonMessages.PARAM_NULL1, "typeID");
		Document result = DocumentHelper.createDocument();
		Element items = result.addElement(new QName("items"));
		String query = "select t.sOrgFID, t.sOrgFName, t.sOrgName from SA_ResourceControl t where t.sResourceID=:resource and " + "t.sTypeID=:type";
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("type", typeID);
		params.put("resource", resourceID);
		Table table = KSQL.select(query, params, "/system/data", null);
		for (Iterator<Row> it = table.iterator(); it.hasNext();) {
			Row r = it.next();
			Element item = items.addElement(new QName("item"));
			item.addElement(new QName("sFID")).addText(r.getString("sOrgFID"));
			item.addElement(new QName("sFName")).addText(r.getString("sOrgFName"));
			item.addElement(new QName("sName")).addText(r.getString("sOrgName"));
		}

		return result;
	}

	public static void saveResourceControl2(String resourceID, String typeID, String typeName, Map<String, String> newOrgs, String process,
			String activity, String action) {
		Utils.check(Utils.isNotEmptyString(resourceID), CommonMessages.class, CommonMessages.PARAM_NULL1, "resourceID");
		Utils.check(Utils.isNotEmptyString(typeID), CommonMessages.class, CommonMessages.PARAM_NULL1, "typeID");

		// 删除旧的ResourceControl
		String delete = "delete from SA_ResourceControl t where t.sResourceID='" + resourceID + "' and t.sTypeID='" + typeID + "'";
		KSQL.executeUpdate(delete, new HashMap<String, Object>(), "/system/data", null);

		// 添加新的ResourceControl
		for (String fid : newOrgs.keySet()) {
			String fname = newOrgs.get(fid);
			String id = OrgUtils.getOrgID(fid);
			String name = CommonUtils.getNameOfFile(fname);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id", CommonUtils.createGUID());
			params.put("resourceID", resourceID);
			params.put("typeID", typeID);
			params.put("typeName", typeName);
			params.put("orgFID", fid);
			params.put("orgFName", fname);
			params.put("orgID", id);
			params.put("orgName", name);
			PersonMember p = ContextHelper.getPersonMemberWithAgent();
			params.put("creatorFID", p.getFID());
			params.put("creatorFName", p.getFName());
			params.put("creatorID", p.getPerson().getID());
			params.put("creatorName", p.getName());
			params.put("createTime", CommonUtils.getCurrentDate());

			String insert = "insert into SA_ResourceControl t (t, t.sResourceID, t.sTypeID, t.sTypeName, "
					+ "t.sOrgFID, t.sOrgFName, t.sOrgID, t.sOrgName, " + "t.sCreatorFID, t.sCreatorFName, t.sCreatorID, t.sCreatorName,"
					+ "t.sCreateTime, t.version) values (:id, :resourceID, :typeID, :typeName, " + ":orgFID, :orgFName, :orgID, :orgName,"
					+ ":creatorFID, :creatorFName, :creatorID, :creatorName, :createTime, 0)";
			KSQL.executeUpdate(insert, params, "/system/data", null);
		}
	}

	@SuppressWarnings("rawtypes")
	public static List checkPermission(List permissions) {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		Operator operator = ContextHelper.getOperator();
		for (Object o : permissions) {
			Map<String, String> item = new HashMap<String, String>();
			Map permission = (Map) o;
			String process = (String) permission.get("process");
			Utils.check(Utils.isNotEmptyString(process), SystemMessages.class, SystemMessages.PROCESS_OF_PERMISSION);
			item.put("process", process);

			String activity = (String) permission.get("activity");
			Utils.check(Utils.isNotEmptyString(activity), SystemMessages.class, SystemMessages.ACTIVITY_OF_PERMISSION);

			String executor = (String) permission.get("executor");
			if (Utils.isEmptyString(executor)) {
				executor = "*";
			}
			item.put("executor", executor);

			String hasPermission = "";
			item.put("activity", activity);
			String action = (String) permission.get("action");
			if (Utils.isNotEmptyString(action)) {
				item.put("action", action);
				hasPermission = operator.hasActionPermission(process, activity, action, executor) + "";
			} else {
				hasPermission = operator.hasActivityPermission(process, activity, executor) + "";
			}

			item.put("hasPermission", hasPermission);

			result.add(item);
		}

		return result;
	}

	public static Table queryOnlineUser() {
		String users = OrgUtils.queryOnlineUser();
		Table table = TableUtils.createTable(null);
		table.addColumn("name", ColumnTypes.STRING);
		table.addColumn("loginIP", ColumnTypes.STRING);
		table.addColumn("loginDate", ColumnTypes.STRING);
		table.addColumn("sessionid", ColumnTypes.STRING);
		if (Utils.isNotEmptyString(users)) {
			String[] items = users.split(";");
			for (int i = 0; i < items.length; i++) {
				if (Utils.isNotEmptyString(items[i])) {
					if (items[i].contains("/")) {
						String[] strs = items[i].split("/");

						if (strs.length == 5) {
							Row r = table.appendRow();
							r.setString("loginIP", strs[1]);
							r.setString("loginDate", strs[2]);
							r.setString("name", strs[3]);
							r.setString("sessionid", strs[4]);
						} else {
							Row r = table.appendRow();
							r.setString("loginIP", "");
							r.setString("loginDate", "");
							r.setString("name", items[i]);
							r.setString("sessionid", "");
						}
					}
				}
			}
		}
		return table;
	}

	/*
	 * 常用组树形数据格式 虚根 _group_: 常用组 自定义组 groupID: groupName 组织
	 * /_group_/groupID/memberID/orgID: orgName
	 */

	private static final String GROUP_ROOT_ID = "_group_";
	private static final String GROUP_ROOT_NAME = "常用组";
	private static final String GROUP_ORGKIND = "group";

	private static Table queryOrgCommonGroupRoot(String concept, String idColumn, String select, String from, String condition,
			List<DataPermission> range, String filter, Boolean distinct, int offset, int limit, String columns, String orderBy, String aggregate,
			String aggregateColumns, Map<String, Object> variables, String dataModel, String fnModel) {
		String orgFID = ContextHelper.getPersonMember().getFID();
		String publicRange = OpmUtils.createParentPathFilter("range1.sOrgFID", orgFID);

		// 获取常用组
		String groupSQL = "select customGroup as sID, customGroup.sName, customGroup.sTypeID, customGroup.sSequence "
				+ "	from SA_OPCustomGroup customGroup " + "		optional join SA_ResourceControl range1 "
				+ "			on range1.sTypeID = 'customGroup_range' and range1.sResourceID = customGroup "
				+ "	where ((customGroup.sTypeID = 'private' and customGroup.sCreatorID = :currentPersonID()) "
				+ "			or (customGroup.sTypeID = 'public' and (range1 is null or (" + publicRange + ")))) "
				+ "	group by customGroup, customGroup.sName, customGroup.sTypeID, customGroup.sSequence "
				+ "	order by customGroup.sTypeID, customGroup.sSequence ";
		Table groupTable = KSQL.select(groupSQL, null, OrgConstants.OPM_DATA_MODEL, null);
		List<String> groups = new ArrayList<String>();
		Iterator<Row> groupRows = groupTable.iterator();
		while (groupRows.hasNext()) {
			Row row = groupRows.next();
			groups.add(row.getString("sID"));
		}

		// 扩展select、from、columns部分，获取组织
		select = select + ", member1.sResourceID as groupID, member1 as memberID ";
		from = from + " join SA_ResourceControl member1 on SA_OPOrg.sFID = member1.sOrgFID " + " and member1.sTypeID = 'customGroup_member' "
				+ "	and member1.sResourceID in ('" + OpmUtils.arrayToString(groups.toArray(), "%s", "','") + "')";
		Table orgTable = BizData.query(concept, idColumn, select, from, condition, range, filter, distinct, offset, limit, columns, orderBy,
				aggregate, aggregateColumns, variables, dataModel, fnModel);

		// 构造树形数据
		// 转换ID和parentID
		Iterator<Row> orgRows = orgTable.iterator();
		while (orgRows.hasNext()) {
			Row row = orgRows.next();
			row.setString("SA_OPOrg",
					"/" + GROUP_ROOT_ID + "/" + row.getString("groupID") + "/" + row.getString("memberID") + "/" + row.getString("SA_OPOrg"));
			row.setString("sParent", row.getString("groupID"));
			row.setState(ModifyState.NONE);
		}

		if (orgTable.size() > 0) {
			// 构造根
			Row rootRow = orgTable.appendRow();
			rootRow.setString("SA_OPOrg", GROUP_ROOT_ID);
			rootRow.setString("sName", GROUP_ROOT_NAME);
			rootRow.setString("sOrgKindID", GROUP_ORGKIND);
			rootRow.setInteger("sValidState", 1);
			rootRow.setState(ModifyState.NONE);

			orgTable.getMetaData().setKeyColumn("groupID");
			// 构造分组
			groupRows = groupTable.iterator();
			while (groupRows.hasNext()) {
				Row row = groupRows.next();
				String groupID = row.getString("sID");
				String groupName = row.getString("sName");
				// 过滤空的分组
				if (orgTable.getRow(groupID) != null) {
					Row groupRow = orgTable.appendRow();
					groupRow.setString("SA_OPOrg", groupID);
					groupRow.setString("sName", groupName);
					groupRow.setString("sParent", GROUP_ROOT_ID);
					groupRow.setString("sOrgKindID", GROUP_ORGKIND);
					groupRow.setInteger("sValidState", 1);
					groupRow.setState(ModifyState.NONE);
				}
			}
		}

		orgTable.getProperties().put(Table.PROP_NAME_ROWID, "SA_OPOrg");
		return orgTable;
	}

	public static Table queryOrgCommonGroup(String concept, String idColumn, String select, String from, String condition,
			List<DataPermission> range, String filter, Boolean distinct, int offset, int limit, String columns, String orderBy, String aggregate,
			String aggregateColumns, Map<String, Object> variables, String dataModel, String fnModel, String parentID) {
		if (Utils.isEmptyString(parentID)) {
			return queryOrgCommonGroupRoot(concept, idColumn, select, from, condition, range, filter, distinct, offset, limit, columns, orderBy,
					aggregate, aggregateColumns, variables, dataModel, fnModel);
		}
		String parentOrgID = CommonUtils.getNameOfFile(parentID);
		String parentPath = CommonUtils.getPathOfFile(parentID);
		filter = filter.replaceAll(parentID, parentOrgID);
		Table table = BizData.query(concept, idColumn, select, from, condition, range, filter, distinct, offset, limit, columns, orderBy, aggregate,
				aggregateColumns, variables, dataModel, fnModel);
		Iterator<Row> rows = table.iterator();
		while (rows.hasNext()) {
			Row row = rows.next();
			row.setString("SA_OPOrg", parentPath + "/" + row.getString("SA_OPOrg"));
			row.setString("sParent", parentID);
			row.setState(ModifyState.NONE);
		}
		return table;
	}

	public static OrgKinds getOrgKinds() {
		return OrgKinds.getInstance();
	}

	public static void updateOrgVersion() {
		OrgCache.updateOrgVersion();
		OrgCache.clearCache();
	}

	public static List<String> getDisabledActions(String process, String activity) {
		ActionContext actionContext = ContextHelper.getActionContext();
		process = Utils.isEmptyString(process) ? actionContext.getProcess().getFullName() : process;
		activity = Utils.isEmptyString(activity) ? actionContext.getActivity().getName() : activity;

		List<String> result = new ArrayList<String>();

		List<String> actions = getProtectedActions(process, activity);
		Operator op = ContextHelper.getOperator();
		for (String actionFullName : actions) {
			String actionName = CommonUtils.getNameOfFile(actionFullName);
			String activityFullName = CommonUtils.getPathOfFile(actionFullName);
			String activityName = CommonUtils.getNameOfFile(activityFullName);
			String processFullName = CommonUtils.getPathOfFile(activityFullName);
			if (!op.hasActionPermission(processFullName, activityName, actionName, "*")) {
				result.add(actionFullName);
			}
		}
		return result;
	}

	private static List<String> getProtectedActions(String process, String activity) {
		List<String> result = new ArrayList<String>();

		com.justep.model.Process pi = com.justep.model.ModelUtils.getProcess(process);
		List<Activity> ais = new ArrayList<Activity>();
		if ("*".equals(activity)) {
			ais.addAll(pi.getActivities());
		} else {
			ais.add(pi.getActivity(activity));
		}
		for (Activity ai : ais) {
			for (Action action : ai.getActions().values()) {
				if (action.isProtectedPermission()) {
					String actionFullName = String.format("%s/%s/%s", pi.getFullName(), ai.getName(), action.getName());
					result.add(actionFullName);
				}
			}
		}
		return result;
	}

}
