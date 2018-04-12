import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.butone.utils.GDCAUtils;
import com.justep.client.ClientUtils;
import com.justep.exception.BusinessException;
import com.justep.message.SystemMessages;
import com.justep.model.Config;
import com.justep.model.ModelUtils;
import com.justep.system.context.ContextHelper;
import com.justep.system.context.User;
import com.justep.system.context.UserManager;
import com.justep.system.data.KSQL;
import com.justep.system.data.Row;
import com.justep.system.data.Table;
import com.justep.system.opm.Operator;
import com.justep.system.opm.OrgCache;
import com.justep.system.opm.OrgConstants;
import com.justep.system.opm.OrgUtils;
import com.justep.system.opm.Person;
import com.justep.system.opm.api.PersonHelper;
import com.justep.system.process.ExpressEngine;
import com.justep.system.util.BizSystemException;
import com.justep.system.util.CommonUtils;
import com.justep.util.Utils;

public class ThirdLogin {
	/**
	* Portal2 GDCA直接登陆
	* 
	* @param CAKeyId
	* @param certData
	* @param loginTime
	* @param ip
	* @param options
	* @param lang
	* @return
	*/
	@SuppressWarnings("unchecked")
	public static List<Object> GDCALogin(String CAKeyId, String lang, String certData, java.sql.Date loginTime, String ip,
			@SuppressWarnings("rawtypes") Map options, Boolean inApp) {
		// if (!GDCAUtils.checkGDCACert(certData)) {
		// throw new BusinessException("证书无效");
		// }
		ContextHelper.getSessionContext().put(OrgConstants.SYSTEM_LOGIN_IP, ip);
		// TODO: 默认语言是中文，将来这个点应该可配置
		if (Utils.isEmptyString(lang)) {
			lang = "zh_CN";
		}
		Map<String, Object> varMap = new HashMap<String, Object>();
		varMap.put("CAKeyId", CAKeyId);
		Table t = null;
		try {
			if (Boolean.TRUE.equals(inApp)) {
				t = KSQL.select("select p.sPassword,p.sCode,p.sName,p.sJoinDate from SA_OPPerson p where p.sQQ like :concat('%',(:CAKeyId) ,'%')",
						varMap, "/system/data", null);
			} else {
				t = KSQL.select("select p.sPassword,p.sCode,p.sName,p.sJoinDate from SA_OPPerson p where p.sMsn = :CAKeyId", varMap, "/system/data",
						null);
			}
		} catch (Exception e) {
			new BusinessException("查询登陆用户信息失败，请联系系统管理员");
		}
		if (t.size() == 0) {
			throw new BusinessException("设备[" + CAKeyId + "]未关联任何人员");
		}

		Row r = t.iterator().next();
		Date v = r.getDate("sJoinDate");
		if (v != null && v.before(CommonUtils.getCurrentDate())) {
			throw new BusinessException("您登录系统的有效日期截止到" + new SimpleDateFormat("yyyy年MM月dd日").format(v));
		}

		@SuppressWarnings({ "rawtypes" })
		List result = (List) OrgUtils.login1(r.getString("sCode"), loginTime, r.getString("sPassword"), lang, false, ip, options);
		if ("true".equals(result.get(0))) {
			Operator operator = ContextHelper.getOperator();
			Person p = ContextHelper.getPerson();
			Map<String, Object> d = PersonHelper.loadPerson(p.getID(), Arrays.asList("sTitle", "sGlobalSequence")).getExtValues();
			for (String k : d.keySet()) {
				p.setAttribute(k, d.get(k));
			}
			p.setAttribute("sysNoticeInfo", isShowSysNotice());
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

	/**
	 * Portal 查询GDCA用户信息
	 * 
	 * @param CAKeyId
	 * @return
	 */
	public static Map<String, Object> queryGDCALoginUser(String CAKeyId, String certData, Boolean inApp) {
		// if (!GDCAUtils.checkGDCACert(certData)) {
		// throw new BusinessException("证书无效");
		// }
		Map<String, Object> varMap = new HashMap<String, Object>();
		varMap.put("CAKeyId", CAKeyId);
		Table t = null;
		try {
			if (Boolean.TRUE.equals(inApp)) {
				t = KSQL.select("select p.sPassword,p.sCode,p.sName from SA_OPPerson p where p.sQQ like :concat('%',(:CAKeyId) ,'%')", varMap,
						"/system/data", null);
			} else {
				t = KSQL.select("select p.sPassword,p.sCode,p.sName from SA_OPPerson p where p.sMsn = :CAKeyId", varMap, "/system/data", null);
			}
		} catch (Exception e) {
			throw new BusinessException("查询用户信息失败，请联系系统管理员", e);
		}
		if (t.size() == 0) {
			throw new BusinessException("设备[" + CAKeyId + "]未关联任何人员");
		}
		Row r = t.iterator().next();
		varMap.clear();
		varMap.put("c", r.getString("sCode"));
		varMap.put("p", r.getString("sPassword"));
		varMap.put("n", r.getString("sName"));
		return varMap;
	}
	
	private static String isShowSysNotice() {
		JSONObject result = new JSONObject();
		result.put("showSysNotice", false);
		result.put("isCanEnter", true);
		String sql = "select t.fDeviceKind from B_SystemUpdateLog t where t.fType = '系统停用' and t.fStatus='已发布'" +
				" and :currentDateTime() between t.fStartTime and t.fEndTime order by t.fPublishDate desc";
		Table t = KSQL.select(sql, null, "/base/system/data", null);
		if(t.size()>0){
			result.put("showSysNotice", true);
			result.put("isCanEnter", false);
			result.put("deviceKind", t.iterator().next().getString(0));
		} else {
			sql = "select t,t.fDeviceKind from B_SystemUpdateLog t where t.fType = '更新日志' and t.fStatus='已发布'" +
					" and :currentDateTime() between t.fStartTime and t.fEndTime order by t.fPublishDate desc";
			t = KSQL.select(sql, null, "/base/system/data", null);
			if(t.size()>0) {
				String fID = t.iterator().next().getString(0);
				result.put("deviceKind", t.iterator().next().getString(1));
				sql = "select 1 from B_MessageRecord t where t.fMessageID='"+fID+"' and t.fTargetID=:currentPersonID() and t.fStatusID='rsFinished'";
				t = KSQL.select(sql, null, "/base/system/data", null);
				// 如果消息表不存在记录，则提示
				if(t.size()==0){
					result.put("showSysNotice", true);
					result.put("isCanEnter", true);
				}
			}
		}
		return result.toJSONString();
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

	public static Map<String, Object> queryLoginUserInfo(String account) {
		// 验证
		Map<String, Object> varMap = new HashMap<String, Object>();
		varMap.put("account", account);
		Table t = null;
		try {
			t = KSQL.select("select p.sPassword,p.sCode,p.sName from SA_OPPerson p where sql.upper(p.sCode) = sql.upper(:account)", varMap,
					"/system/data", null);
		} catch (Exception e) {
			throw new BusinessException("查询登陆用户信息失败，请联系系统管理员");
		}
		if (t.size() == 0) {
			throw new BusinessException("用户[" + account + "]不存在");
		}
		Row r = t.iterator().next();
		varMap.clear();
		varMap.put("c", r.getString("sCode"));
		varMap.put("p", r.getString("sPassword"));
		varMap.put("n", r.getString("sName"));
		return varMap;
	}

	public static boolean gdcaVerifySignData(String signCert, String inData, String encData) {
		try {
			return GDCAUtils.verifySignData(signCert, inData, encData);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}
}