import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;

import me.chanjar.weixin.common.bean.WxMenu;
import me.chanjar.weixin.common.bean.WxMenu.WxMenuButton;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpDepart;
import me.chanjar.weixin.cp.bean.WxCpUser;

import org.apache.log4j.Logger;

import com.justep.system.context.ContextHelper;
import com.justep.system.data.BizData;
import com.justep.system.data.KSQL;
import com.justep.system.data.Row;
import com.justep.system.data.Table;
import com.justep.weixin.cp.JustepCpServiceInstance;
import com.justep.weixin.cp.WxCpServiceInstance;

//import me.chanjar.weixin.common.bean.WxMenu;

public class orgSynchronizeToWeixin {

	static WxCpServiceInstance instance = JustepCpServiceInstance.getInstance();

	static Logger logger = Logger.getLogger("orgSynchronizeToWeixin.class");
	static String errInfo = "";
	static String psnerrInfo = "";
	static String successPsnInfo = "\n";
	static String successDeptInfo = "\n";
	static String TopOrgFID = "";

	public static void psnSynchToWeixin(String AccountType) throws ServletException {
		psnerrInfo = "";
		successPsnInfo = "\n";
		Table tbTopOrg = KSQL.select("select SA_OPOrg.sFID from SA_OPOrg SA_OPOrg where SA_OPOrg.sValidState = 1 and SA_OPOrg.sParent is null ", null, "/system/data", null);

		String keepTopOrg = "0";
		if (tbTopOrg.size() > 1)
			keepTopOrg = "1";
		else {
			Iterator<Row> tbTopOrgRows = tbTopOrg.iterator();

			Row tbTopOrgRow;
			while (tbTopOrgRows.hasNext()) {
				tbTopOrgRow = tbTopOrgRows.next();

				TopOrgFID = tbTopOrgRow.getString("sFID");

			}
		}

		WxCpService wxCpService = instance.getWxCpService();
		List<WxCpDepart> departList = wxCpService.departGet();
		String invalidNames = invalidUser();

		for (Integer i = 0; i < departList.size(); i++) {
			WxCpDepart wxDept = departList.get(i);
			Integer departID = wxDept.getId();
			// String departName = departList.get(i).getName();
			// 此处需要判断传入的微信部门ID是否在组织机构中存在，如果不存在，则不进行处理
			Table tbWxDept = KSQL.select("select SA_OPOrg.sFID from SA_OPOrg SA_OPOrg where SA_OPOrg.sValidState = 1 and SA_OPOrg.sWxDeptID =" + departID, null, "/system/data", null);
			if (tbWxDept.size() == 0 && departID != 1)
				continue;
			// 先对两边都存在的用户修复在terminal中的数据

			checkTerminal(wxCpService, departID, AccountType, keepTopOrg);
			if (keepTopOrg.equals("1")) {

				if (departID != 1) {
					deleteWxUser(wxCpService, departID, AccountType, keepTopOrg);
					createWxUser(wxCpService, departID, AccountType, keepTopOrg);
				}
			} else {
				deleteWxUser(wxCpService, departID, AccountType, keepTopOrg);
				createWxUser(wxCpService, departID, AccountType, keepTopOrg);
			}
		}

		//

		if (!invalidNames.equals(""))
			psnerrInfo = psnerrInfo + " 以下人员邮箱和手机号均为空，不能同步：" + invalidNames;

		String parameters = "";
		Table tblog = KSQL.select("select SA_Log.* from SA_Log SA_Log where 1<>1", null, "/system/data", null);
		Row row = null;
		row = tblog.appendRow();
		row.setString("SA_Log", com.justep.system.util.CommonUtils.createGUID());
		row.setInt("version", 0);
		row.setString("sTypeName", "操作日志");
		row.setString("sProcess", "/SA/weixin/process/weixinProcess/weixinProcess");
		row.setString("sProcessName", "微信同步");
		row.setString("sActivity", "mainActivity");
		row.setString("sActivityName", "微信同步");
		row.setString("sAction", "psnSynchToWeixinAction");
		row.setString("sActionName", "微信同步人员");
		row.setString("sParameters", parameters);//
		row.setString("sResult", successPsnInfo + psnerrInfo);
		String personID = ContextHelper.getOperator().getID();
		String presonName = ContextHelper.getOperator().getName();
		row.setString("sCreatorPersonID", personID);//
		row.setString("sCreatorPersonName", presonName);

		tblog = BizData.create(tblog, "SA_Log", null, null);

		tblog.save("/system/data");

	}

	public static String orgSynchToWeixin(String AccountType) throws ServletException {

		// 获取action参数
		errInfo = "\n";
		successDeptInfo = "\n";
		Table tbTopOrg = KSQL.select("select SA_OPOrg.sFID from SA_OPOrg SA_OPOrg where SA_OPOrg.sValidState = 1 and SA_OPOrg.sParent is null ", null, "/system/data", null);
		String keepTopOrg = "0";
		if (tbTopOrg.size() > 1)
			keepTopOrg = "1";
		else {
			Iterator<Row> tbTopOrgRows = tbTopOrg.iterator();

			Row tbTopOrgRow;
			while (tbTopOrgRows.hasNext()) {
				tbTopOrgRow = tbTopOrgRows.next();

				TopOrgFID = tbTopOrgRow.getString("sFID");

			}
		}

		WxCpService wxCpService = instance.getWxCpService();
		List<WxCpDepart> departList = wxCpService.departGet();
		Integer order = departList.size();
		modifyChangeofDepart(wxCpService);

		InsertwxDepart(wxCpService, keepTopOrg, order);

		String parameters = "";
		Table tblog = KSQL.select("select SA_Log.* from SA_Log SA_Log where 1<>1", null, "/system/data", null);
		Row row = null;
		row = tblog.appendRow();
		row.setString("SA_Log", com.justep.system.util.CommonUtils.createGUID());
		row.setInt("version", 0);
		row.setString("sTypeName", "操作日志");
		row.setString("sProcess", "/SA/weixin/process/weixinProcess/weixinProcess");
		row.setString("sProcessName", "微信同步");
		row.setString("sActivity", "mainActivity");
		row.setString("sActivityName", "微信同步");
		row.setString("sAction", "orgSynchToWeixinAction");
		row.setString("sActionName", "微信同步组织机构");
		row.setString("sParameters", parameters);//
		row.setString("sResult", successDeptInfo + errInfo);

		tblog = BizData.create(tblog, "SA_Log", null, null);

		tblog.save("/system/data");

		return errInfo;

	}

	public static String invalidUser() {
		String ksql = "select SA_OPOrg.sName,SA_OPOrg.sCode from SA_OPOrg SA_OPOrg  where SA_OPOrg.sValidState = 1 and SA_OPOrg.sOrgKindID = 'psm'"
				+ "  and SA_OPOrg.sPersonID in (select SA_OPPerson from SA_OPPerson SA_OPPerson where SA_OPPerson.sMobilePhone is  null and SA_OPPerson.sMail is  null) ";// +testpsncond;

		String invalidNames = "";
		Table table = KSQL.select(ksql, null, "/system/data", null);
		Iterator<Row> rows = table.iterator();
		Row row;

		while (rows.hasNext()) {
			row = rows.next();
			invalidNames = invalidNames + row.getString("sName") + " ";

		}
		return invalidNames;
	}

	// 判断用户是否存在于微信中
	public static boolean userIsExist(WxCpService wxCpService, String userID) {
		boolean userExist = false;

		try {
			WxCpUser user = wxCpService.userGet(userID);
			if (user.getUserId() != null && user.getUserId().toLowerCase().equals(userID.toLowerCase())) {
				userExist = true;
			}
			return userExist;

		} catch (WxErrorException e) {
			return false;
		}
	}

	// 增加用户
	public static void newWxUser(WxCpService wxCpService, String userID, String userName, String email, String gender, String mobile, Integer departIDs[], String Tel) throws ServletException {

		WxCpUser user = new WxCpUser();

		user.setUserId(userID);
		user.setName(userName);
		user.setEmail(email);
		user.setGender(gender);
		user.setMobile(mobile);
		user.setDepartIds(departIDs);

		user.setTel(Tel);

		wxCpService.userCreate(user);

	}

	// 修改用户
	public static void modifyWxUser(WxCpService wxCpService, String userCode, Integer departIDs[]) throws ServletException {
		WxCpUser user = new WxCpUser();
		user.setUserId(userCode);
		user.setDepartIds(departIDs);
		wxCpService.userUpdate(user);

	}

	// 判断部门是否存在,同级部门名称不能重复
	public static boolean departIsExist(WxCpService wxCpService, Integer ParentID, String departName) {
		boolean departExist = false;

		List<WxCpDepart> departList = wxCpService.departGet();

		Integer i = 0;
		if (departList != null) {
			for (i = 0; i < departList.size(); i++) {

				if (departName.equals(departList.get(i).getName()) && departList.get(i).getParentId().intValue() == ParentID.intValue()) {//

					departExist = true;
					break;
				}
			}
		}
		return departExist;
	}

	public static Integer getDepartID(WxCpService wxCpService, Integer ParentID, String departName) {
		Integer departID = null;

		List<WxCpDepart> departList = wxCpService.departGet();

		Integer i = 0;

		if (departList != null) {
			for (i = 0; i < departList.size(); i++) {

				if (departName.equals(departList.get(i).getName()) && departList.get(i).getParentId().intValue() == ParentID.intValue()) {
					departID = departList.get(i).getId();
					break;
				}
			}
		}
		return departID;
	}

	// 修改排序（临时）
	public static void modifywxorder(WxCpService wxCpService, String keepTopOrg) throws ServletException {
		// 获取部门序号
		String ksql = "select  max(length(SA_OPOrg.sSequence)) AS maxlen FROM SA_OPOrg SA_OPOrg  where  (SA_OPOrg.sWxDeptID  is not null ) and SA_OPOrg.sValidState = 1 ";// +
																																											// testcond;

		Table table = KSQL.select(ksql, null, "/system/data", null);
		Integer maxlen = 0;
		if (table.size() > 0) {
			Row r = table.iterator().next();
			// r.getValue(arg0)
			if (r.getValue("maxlen") == null)
				maxlen = null;
			else {
				String smaxlen = r.getValue("maxlen").toString();
				maxlen = Integer.parseInt(smaxlen);
			}

		}
		if (maxlen == null)
			return;
		Integer i = null;
		if (keepTopOrg.equals("1"))
			i = 4;
		else
			i = 8;
		while (i <= maxlen) {
			ksql = "select  SA_OPOrg,SA_OPOrg.sName,SA_OPOrg.sWxDeptID ,SA_OPOrg.sParent" + " FROM SA_OPOrg SA_OPOrg where  (SA_OPOrg.sWxDeptID  is not null ) "
					+ "  and SA_OPOrg.sValidState = 1 and SA_OPOrg.sOrgKindID in ('ogn','dpt') and length(SA_OPOrg.sSequence) = " + i // +testcond
					+ " order by SA_OPOrg.sSequence";

			table = KSQL.select(ksql, null, "/system/data", null);
			Iterator<Row> rows = table.iterator();
			Row row;

			Integer Sequence = 1;
			while (rows.hasNext()) {

				row = rows.next();

				modifyWxDepartOrder(wxCpService, Integer.parseInt(row.getValue("sWxDeptID").toString()), Sequence);
				Sequence = Sequence + 1;
			}
			i = i + 4;
		}

	}

	// 新增部门

	// 逐级处理需要增加的部门列表数据(没有微信部门ID的数据认为需要新增)
	public static void InsertwxDepart(WxCpService wxCpService, String keepTopOrg, Integer order) throws ServletException {
		// 获取部门序号
		String ksql = "select  max(length(SA_OPOrg.sSequence)) AS maxlen FROM SA_OPOrg SA_OPOrg  where  (SA_OPOrg.sWxDeptID  is null or SA_OPOrg.sWxDeptID = '') and SA_OPOrg.sValidState = 1 ";// +
																																																// testcond;

		Table table = KSQL.select(ksql, null, "/system/data", null);
		Integer maxlen = 0;
		if (table.size() > 0) {
			Row r = table.iterator().next();
			if (r.getValue("maxlen") == null)
				maxlen = null;
			else {
				String smaxlen = r.getValue("maxlen").toString();
				maxlen = Integer.parseInt(smaxlen);
			}
		}
		if (maxlen == null)
			return;
		Integer i = null;
		if (keepTopOrg.equals("1"))
			i = 4;
		else
			i = 8;
		// 序获取部门数量作为当前排序开始值
		Integer Sequence = order;

		while (i <= maxlen) {
			ksql = "select  SA_OPOrg,SA_OPOrg.sName,SA_OPOrg.sParent,(select a.sWxDeptID from SA_OPOrg a where a = SA_OPOrg.sParent) AS wxParentDeptID"
					+ " FROM SA_OPOrg SA_OPOrg where  (SA_OPOrg.sWxDeptID  is null or SA_OPOrg.sWxDeptID = '') "
					+ "  and SA_OPOrg.sValidState = 1 and SA_OPOrg.sOrgKindID in ('ogn','dpt') and length(SA_OPOrg.sSequence) = " + i // +testcond
					+ " order by SA_OPOrg.sSequence";

			table = KSQL.select(ksql, null, "/system/data", null);
			Iterator<Row> rows = table.iterator();
			Row row;
			Integer parentID = 1;

			while (rows.hasNext()) {
				String errMsg = "";
				row = rows.next();
				if (keepTopOrg.equals("1") && i != 4) {
					parentID = Integer.parseInt(row.getValue("wxParentDeptID").toString());
				} else {
					if (keepTopOrg.equals("0") && i != 8)
						parentID = Integer.parseInt(row.getValue("wxParentDeptID").toString());
				}
				if (departIsExist(wxCpService, parentID, row.getString("sName"))) {
					Integer wxdepartID = getDepartID(wxCpService, parentID, row.getString("sName"));
					ksql = "update SA_OPOrg SA_OPOrg set SA_OPOrg.sWxDeptID = " + wxdepartID + " where SA_OPOrg = '" + row.getString("SA_OPOrg") + "'";
					KSQL.executeUpdate(ksql, null, "/system/data", null);
					Sequence = Sequence + 1;
					continue;
				}
				try {
					Integer id = newWxDepart(wxCpService, parentID, row.getString("sName"), Sequence);

					// 更新微信部门ID
					successDeptInfo = successDeptInfo + row.getString("sName") + "  同步成功\n";
					ksql = "update SA_OPOrg SA_OPOrg set SA_OPOrg.sWxDeptID = " + id + " where SA_OPOrg = '" + row.getString("SA_OPOrg") + "'";
					KSQL.executeUpdate(ksql, null, "/system/data", null);
					Sequence = Sequence + 1;
				} catch (WxErrorException e) {
					errMsg = e.getError().getErrorMsg();
				} finally {
					if (!errMsg.equals("")) {
						errInfo = errInfo + errMsg + "\n";
						logger.info(errMsg);
						continue;
					}
				}
			}
			i = i + 4;
		}

	}

	public static Integer newWxDepart(WxCpService wxCpService, Integer parentID, String departName, Integer order) throws ServletException {
		WxCpDepart depart = new WxCpDepart();

		depart.setName(departName);
		depart.setParentId(parentID);
		depart.setOrder(order);
		Integer id = wxCpService.departCreate(depart);
		return id;
	}

	// 对于已经存在的部门，检查其上级部门或者名称是否发生变化，如果发生变化则修改
	public static void modifyChangeofDepart(WxCpService wxCpService) throws ServletException {

		String ksql = "select  SA_OPOrg,SA_OPOrg.sName,SA_OPOrg.sWxDeptID,SA_OPOrg.sParent,(select a.sWxDeptID FROM SA_OPOrg a where a = SA_OPOrg.sParent) AS wxParentDeptID"
				+ " From SA_OPOrg SA_OPOrg where  (SA_OPOrg.sWxDeptID  is not null ) and SA_OPOrg.sValidState = 1  order by SA_OPOrg.sWxDeptID ";

		Table table = KSQL.select(ksql, null, "/system/data", null);
		Iterator<Row> rows = table.iterator();
		Row row;
		Integer parentID = 1;
		List<WxCpDepart> departList = wxCpService.departGet();
		while (rows.hasNext()) {
			row = rows.next();
			Integer wxDeptID = Integer.parseInt(row.getValue("sWxDeptID").toString());
			if (row.getValue("wxParentDeptID") != null)
				// parentID = row.getDecimal("wxParentDeptID").intValue();
				parentID = Integer.parseInt(row.getValue("wxParentDeptID").toString());

			String departName = row.getString("sName");
			Boolean exist = false;
			if (departList != null) {
				for (Integer i = 0; i < departList.size() - 1; i++) {
					// 找到ID 但是名称或者父部门ID发生变化，则更改微信部门信息
					// && (!departList.get(i).getName().equals(departName) ||
					// departList.get(i).getParentId() != parentID)
					if (departList.get(i).getId().intValue() == wxDeptID.intValue()) {
						exist = true;
						modifyWxDepart(wxCpService, wxDeptID, departName, parentID);
						successDeptInfo = successDeptInfo + departName + "  同步成功 \n";
						break;
					}
				}
			}
			// 没有找到相关ID，微信部门为空，执行新建操作
			if (!exist) {
				// 更新微信部门ID
				ksql = "update SA_OPOrg SA_OPOrg set SA_OPOrg.sWxDeptID = null where SA_OPOrg = '" + row.getString("SA_OPOrg") + "'";
				KSQL.executeUpdate(ksql, null, "/system/data", null);

			}
		}
	}

	// 修改部门
	public static void modifyWxDepart(WxCpService wxCpService, Integer departID, String departName, Integer parentId) throws ServletException {
		WxCpDepart depart = new WxCpDepart();
		depart.setId(departID);
		depart.setName(departName);
		depart.setParentId(parentId);

		wxCpService.departUpdate(depart);
	}

	// 修改部门排序
	public static void modifyWxDepartOrder(WxCpService wxCpService, Integer departID, Integer order) throws ServletException {
		WxCpDepart depart = new WxCpDepart();
		depart.setId(departID);

		depart.setOrder(order);

		wxCpService.departUpdate(depart);
	}

	public static void deleteWxDepart(WxCpService wxCpService) throws ServletException {
		Integer departID = null;
		List<WxCpDepart> departList = wxCpService.departGet();
		if (departList != null) {
			for (Integer i = 0; i < departList.size(); i++) {

				departID = departList.get(i).getId();
				Integer departParent = departList.get(i).getParentId();
				if (departParent == 133)
					wxCpService.departDelete(departID);

			}
		}

	}

	// 获取集合交集
	public static List<String> listRemoveAll(WxCpService wxCpService, Integer departID, Integer listtype, String AccountType, String keepTopOrg) {

		List<String> returnUser = null;
		List<String> flatUser = new ArrayList<String>();
		String ksql = "";
		// 获取指定部门的人员，形成list列表

		if (departID != 1) {
			ksql = "select SA_OPOrg.sPersonID,SA_OPOrg.sCode from SA_OPOrg SA_OPOrg  where SA_OPOrg.sValidState = 1 and SA_OPOrg.sOrgKindID = 'psm'"
					+ "  and SA_OPOrg.sFID  like concat((select a.sFID from SA_OPOrg a   where   a.sWxDeptID = " + departID + "),'%') "
					+ "  and SA_OPOrg.sPersonID in (select SA_OPPerson from SA_OPPerson SA_OPPerson where SA_OPPerson.sMobilePhone is not null or SA_OPPerson.sMail is not null) ";// +testpsncond;

			Table table = KSQL.select(ksql, null, "/system/data", null);
			Iterator<Row> rows = table.iterator();

			Row row;

			while (rows.hasNext()) {
				row = rows.next();
				String id = AccountType.equals("人员ID") ? row.getString("sPersonID") : row.getString("sCode");
				if (!flatUser.contains(id))
					flatUser.add(id);
				// if(AccountType.equals("人员ID"))
				// flatUser.add(row.getString("sPersonID"));
				// else
				// flatUser.add(row.getString("sCode"));
			}

		}
		// 判断是否保留根节点，如果不保留，则需要单独处理根节点下的人员
		if (keepTopOrg.equals("0") && departID == 1) {

			ksql = "select SA_OPOrg.sPersonID,SA_OPOrg.sCode from SA_OPOrg SA_OPOrg  where SA_OPOrg.sValidState = 1 and SA_OPOrg.sOrgKindID = 'psm'" + " and SA_OPOrg.sFID  like  '%" + TopOrgFID
					+ "%'" + " and not(replace(SA_OPOrg.sFID,'" + TopOrgFID + "','') like '%.dpt%') " + " and (not replace(SA_OPOrg.sFID,'" + TopOrgFID + "','') like '%.ogn%')";

			Table table1 = KSQL.select(ksql, null, "/system/data", null);
			Iterator<Row> rows1 = table1.iterator();

			Row row1;

			while (rows1.hasNext()) {
				row1 = rows1.next();
				String id = AccountType.equals("人员ID") ? row1.getString("sPersonID") : row1.getString("sCode");
				if (!flatUser.contains(id))
					flatUser.add(id);
				// if(AccountType.equals("人员ID")){
				// if(flatUser.indexOf(row1.getString("sPersonID")) == -1)
				// flatUser.add(row1.getString("sPersonID"));
				// }
				// else{
				// if(flatUser.indexOf(row1.getString("sCode")) == -1)
				// flatUser.add(row1.getString("sCode"));
				// }
			}

		}

		List<WxCpUser> wxUserlist = wxCpService.departGetUsers(departID, true, 0);
		List<String> wxUser = new ArrayList<String>();
		for (Integer i = 0; i < wxUserlist.size(); i++) {
			wxUser.add(wxUserlist.get(i).getUserId());
		}
		// 获取平台存在而微信不存在的，准备插入微信
		if (listtype == 0) {
			flatUser.removeAll(wxUser);
			returnUser = flatUser;

		} else if (listtype == 1) {
			wxUser.removeAll(flatUser);
			returnUser = wxUser;
		} else if (listtype == 2) {
			wxUser.retainAll(flatUser);
			returnUser = wxUser;
		}

		return returnUser;
	}

	private static void insertTerminal(String AccountType, String sIDs) {
		String scond = "";
		if (AccountType.equals("人员ID"))
			scond = " p in (" + sIDs + ")";
		else
			scond = " p.sCode in (" + sIDs + ")";

		String ksql = "insert into SA_Terminal a(a, a.sPersonID, a.sTypeID, a.sTypeName, a.sAccountNumber, a.sProtocol, a.sValidState, a.sIsDefault, a.version)"
				+ " (select guid(),p,'weixin','微信',p.sCode,'weixin', 1, 1, 0 " + " from SA_OPPerson p " + " where " + scond + " and (not p in ( select t.sPersonID from SA_Terminal t)))";

		KSQL.executeUpdate(ksql, null, "/system/data", null);
	}

	// 检查微信中存在平台中也存在的用户terminal中是否存在数据，不存在则插入
	public static void checkTerminal(WxCpService wxCpService, Integer departID, String AccountType, String keepTopOrg) throws ServletException {
		List<String> AllExistUserID = listRemoveAll(wxCpService, departID, 2, AccountType, keepTopOrg);
		if (AllExistUserID == null || AllExistUserID.size() == 0)
			return;
		String sIDs = "";
		for (int i = 0; i < AllExistUserID.size(); i++) {
			if (sIDs.equals(""))
				sIDs = "'" + AllExistUserID.get(i) + "'";
			else
				sIDs = sIDs + ",'" + AllExistUserID.get(i) + "'";
			if ((i + 1) % 100 == 0) {
				insertTerminal(AccountType, sIDs);
				sIDs = "";
			}
		}
		if (sIDs.length() > 0) {
			insertTerminal(AccountType, sIDs);
		}
	}

	// 根据获得的集合（平台存在，微信不存在）插入数据
	public static void createWxUser(WxCpService wxCpService, Integer departID, String AccountType, String keepTopOrg) throws ServletException {
		List<String> createUserID = listRemoveAll(wxCpService, departID, 0, AccountType, keepTopOrg);
		if (createUserID == null || createUserID.size() == 0)
			return;
		String sIDs = ListToString(createUserID);

		String ksql = "";
		if (AccountType.equals("人员ID"))
			ksql = "select  SA_OPPerson.* from SA_OPPerson SA_OPPerson" + " where SA_OPPerson in (" + sIDs + ")";
		else
			ksql = "select  SA_OPPerson.* from SA_OPPerson SA_OPPerson" + " where SA_OPPerson.sCode in (" + sIDs + ")";

		Table table = KSQL.select(ksql, null, "/system/data", null);
		Iterator<Row> rows = table.iterator();
		Row row;

		while (rows.hasNext()) {
			String errMsg = "";
			row = rows.next();
			List<Integer> list = new ArrayList<Integer>();
			getwxDepartIDs(row.getString("SA_OPPerson"), list, keepTopOrg);

			// 判断当前人员是否是根节点人员
			if (list.size() == 0)
				continue;
			Integer[] departids = list.toArray(new Integer[0]);

			String userID = "";
			if (AccountType.equals("人员ID"))
				userID = row.getString("SA_OPPerson");
			else
				userID = row.getString("sCode");

			// 此处不能删除，应该是修改
			if (userIsExist(wxCpService, userID)) {

				modifyWxUser(wxCpService, userID, departids);
				insertToTerminal(row.getString("SA_OPPerson"), userID);
				continue;
			}
			// 判断表 SA_Terminal中是否有微信数据，如果没有则插入
			try {

				newWxUser(wxCpService, userID, row.getString("sName"), row.getString("sMail"), row.getString("sSex"), row.getString("sMobilePhone"), departids, row.getString("sOfficePhone"));
				successPsnInfo = successPsnInfo + row.getString("sName") + " 同步成功\n";
				// 插入数据到SA_Terminal
				insertToTerminal(row.getString("SA_OPPerson"), userID);

			} catch (WxErrorException e) {

				errMsg = row.getString("sName") + ": " + e.getError().getErrorMsg();
			} finally {
				if (!errMsg.equals("")) {
					psnerrInfo = psnerrInfo + errMsg + "\n";
					logger.info(errMsg);
					continue;
				}
			}

		}

	}

	public static void insertToTerminal(String personID, String wxAccount) {
		// 判断是否存在
		String ksql = " select a from SA_Terminal a where a.sPersonID = '" + personID + "' and a.sAccountNumber = '" + wxAccount + "'";
		Table table = KSQL.select(ksql, null, "/system/data", null);
		if (table.size() <= 0) {

			ksql = "insert into SA_Terminal a(a, a.sPersonID, a.sTypeID, a.sTypeName, a.sAccountNumber, a.sProtocol, a.sValidState, a.sIsDefault, a.version)" + " values (guid(), '" + personID
					+ "', 'weixin', '微信', '" + wxAccount + "', 'weixin', 1, 1, 0)";
			KSQL.executeUpdate(ksql, null, "/system/data", null);
		}

	}

	// 获取微信存在，平台不存在的删除微信用户 (先执行，可以解决移动部门产生的问题)
	public static void deleteWxUser(WxCpService wxCpService, Integer departID, String AccountType, String keepTopOrg) throws ServletException {
		List<String> deleteUserID = listRemoveAll(wxCpService, departID, 1, AccountType, keepTopOrg);

		if (deleteUserID == null || deleteUserID.size() == 0)
			return;

		for (Integer i = 0; i < deleteUserID.size(); i++) {
			// 判断在当前部门中，微信存在，但是在平台中所属当前部门不存在，则需要判断当前用户是否存在于其他部门，
			// 如果存在，则需要修改当前用户的所属部门，否则删除
			List<Integer> list = new ArrayList<Integer>();
			getwxDepartIDs(deleteUserID.get(i), list, keepTopOrg);
			if (list.size() > 0) {
				Integer[] departids = list.toArray(new Integer[0]);

				modifyWxUser(wxCpService, deleteUserID.get(i), departids);
				successPsnInfo = successPsnInfo + deleteUserID.get(i) + " 同步成功" + "\n";
			}// else
				// wxCpService.userDelete(deleteUserID.get(i));
		}
	}

	public static String ListToString(List<String> list) {
		String returnString = "";
		for (Integer i = 0; i < list.size(); i++) {

			if (returnString == null || returnString.equals(""))
				returnString = "'" + list.get(i) + "'";
			else
				returnString = returnString + ",'" + list.get(i) + "'";
		}
		return returnString;
	}

	// 获取都平台和微信存在的用户进行更改操作

	// 不是存储的路径，而是存储的多部门的ID，即一人多岗情况
	public static void getwxDepartIDs(String personID, List<Integer> list, String keepTopOrg) {

		String ksql = "select  SA_OPOrg.* " + " from SA_OPOrg SA_OPOrg where SA_OPOrg.sPersonID = '" + personID + "' and SA_OPOrg.sValidState = 1";

		Table table = KSQL.select(ksql, null, "/system/data", null);
		Iterator<Row> rows = table.iterator();
		Row row;

		while (rows.hasNext()) {
			row = rows.next();
			ksql = "select  SA_OPOrg.* " + " From SA_OPOrg SA_OPOrg where '" + row.getString("sFID") + "' like concat(SA_OPOrg.sFID ,'%') "
					+ " and SA_OPOrg.sOrgKindID in ('ogn','dpt') and SA_OPOrg.sValidState = 1 and SA_OPOrg.sWxDeptID is not null" + " order by SA_OPOrg.sFID desc";

			Table table1 = KSQL.select(ksql, null, "/system/data", null);
			Iterator<Row> rows1 = table1.iterator();
			Row row1;
			if (rows1.hasNext()) {
				row1 = rows1.next();
				list.add(Integer.parseInt(row1.getValue("sWxDeptID").toString()));
			}
		}
		// 如果不保留一级机构，判断当前人员是否为根节点组织机构人员，如果是，则把deptID置为微信根节点ID
		if (keepTopOrg.equals("0")) {
			ksql = "select SA_OPOrg.sPersonID,SA_OPOrg.sCode from SA_OPOrg SA_OPOrg  where SA_OPOrg.sValidState = 1 and SA_OPOrg.sOrgKindID = 'psm'" + " and SA_OPOrg.sFID  like  '%" + TopOrgFID
					+ "%'" + " and (not replace(SA_OPOrg.sFID,'" + TopOrgFID + "','') like '%.dpt%') " + " and (not replace(SA_OPOrg.sFID,'" + TopOrgFID
					+ "','') like '%.ogn%') and SA_OPOrg.sPersonID = '" + personID + "'";

			Table tbTopOrgPsn = KSQL.select(ksql, null, "/system/data", null);

			if (tbTopOrgPsn.size() > 0)
				list.add(1);
		}

	}

	// 保存菜单配置文件

	// 创建菜单menuSynchToWx
	public static void menuSynchToWx(String AppID, String isSingle) {
		WxCpService wxCpService = instance.getWxCpService();
		CreateWxMenu(wxCpService, AppID, isSingle);
	}

	public static void CreateWxMenu(WxCpService wxCpService, String AppID, String isSingle) {
		// 创建应用菜单前，先删除菜单
		try {
			System.out.println(instance.getWxCpConfigStorage().getAgentId());
			wxCpService.menuDelete();
			// json格式获取菜单
			// 获取json字符串
			String sConfig = Menuconfig.getMenuConfig(AppID, isSingle);

			if (sConfig == null || sConfig.equals("") || sConfig.equals("invalid")) {
				throw new RuntimeException("无效菜单文件 ");
			}

			sConfig = "[{\"Name\":{\"value\":\"应用\"},\"URL\":{\"value\":\"\"},\"funcURL\":{\"value\":\"\"},\"id\":{\"value\":\"\"},\"level\":{\"value\":\"应用\"},\"parent\":{\"value\":\"\"},\"path\":{\"value\":\"\"},\"rows\":"
					+ sConfig + "}]";

			List<RowModel> data = new ArrayList<RowModel>();
			data = MenuMethod.GetData(sConfig);

			List<WxMenuButton> BtnList = new ArrayList<WxMenuButton>();
			for (RowModel item : data) {
				List<RowModel> fdata = item.rows;

				// 循环一级菜单获取二级菜单
				for (RowModel fitem : fdata) {

					List<WxMenuButton> subBtnList = new ArrayList<WxMenuButton>();

					List<RowModel> sdata = fitem.rows;

					for (RowModel sitem : sdata) {
						String subname = sitem.Name.value; // 拿到head下的子节点script下的字节点username的值

						String URL = sitem.URL.value;

						WxMenuButton subbutton = new WxMenuButton();
						if (URL != null && !URL.equals("")) {
							subbutton.setType("view");
							subbutton.setUrl(URL);

						}
						subbutton.setName(subname);
						subBtnList.add(subbutton);
					}
					// 增加一级菜单
					WxMenuButton button = new WxMenuButton();
					if (fitem.URL.value != null && !fitem.URL.value.equals("")) {
						button.setType("view");
						button.setUrl(fitem.URL.value);
					}

					button.setName(fitem.Name.value);

					button.setSubButtons(subBtnList);

					BtnList.add(button);
				}

			}

			WxMenu wxMenu1 = new WxMenu();

			wxMenu1.setButtons(BtnList);
			wxCpService.menuCreate(wxMenu1);

		} catch (WxErrorException e) {

			throw new RuntimeException("同步菜单失败: [" + e.getError().getErrorCode() + " : " + e.getError().getErrorMsg() + "]");

		}

	}

}
