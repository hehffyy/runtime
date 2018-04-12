import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.justep.system.action.ActionUtils;
import com.justep.system.context.ActionContext;
import com.justep.system.context.ContextHelper;
import com.justep.system.data.KSQL;
import com.justep.system.data.Row;
import com.justep.system.data.Table;
import com.justep.system.opm.Operator;
import com.justep.system.util.CommonUtils;

public class Portal2 {
	private static final String DATA_MODEL = "/portal2/data";
	private static final String SYSTEM_DATA_MODEL = "/system/data";
	/**
	 * 获取首页widget信息
	 * @param
	 * @return
	 */
	public static JSONArray getHomeInfo(){
		Map<String, Object> p = new HashMap<String, Object>();
		ActionContext actionContext = ActionUtils.getRequestContext().getActionContext();
		String process = "/SA_X/theme/widgetMG/widgetMGProcess";
		String activity = "mainActivity";
		String action = "getHomeInfoAction";
		JSONArray arr =  (JSONArray) ActionUtils.invokeAction(process, activity, action, actionContext.getExecutor(),p);
		return arr;
	}
	
	/**
	 * 保存系统设置-sportal字段/ 系统名称
	 * @param value
	 * @return
	 */
	public static boolean saveSystemConfig(String config){
		String updateksql = "UPDATE Portal2Profiles s1 SET s1.sPortal=:value where s1.sPublishType='theme' and s1.sThemeActivity='activity'";
		Map<String,Object> updatevars = new HashMap<String,Object>();
		updatevars.put("value", config);
		KSQL.executeUpdate(updateksql, updatevars, DATA_MODEL, null);
		return true;
	}
	/**
	 * 初始化主题列表
	 * @param value
	 * @return
	 */
	public static boolean addThemeList(){
		String insertksql = "INSERT INTO Portal2Profiles s1(s1,s1.version,s1.sPortal,s1.sOther, s1.sThemeName, s1.sThemeActivity, s1.sPublishType) values (:id,:version,:p,:value,'default','activity', 'theme')";
		Map<String,Object> insertvars = new HashMap<String,Object>();
		insertvars.put("id",CommonUtils.createGUID());
		insertvars.put("version", 0);
		insertvars.put("p", "{\"logo\":{\"type\":\"l_2\",\"title_zh\":\"后台管理系统\",\"title_en\":\"\"}}");
		insertvars.put("value", "");
		KSQL.executeUpdate(insertksql, insertvars, DATA_MODEL, null);
		return true;
	}
	
	/**
	 *  
	 * @param value
	 * @return
	 */
	private static boolean removeThemeList(){
		String updateksql = "DELETE from Portal2Profiles s1 where s1.sThemeName=:themeName and s1.sPublishType='theme'";
		Map<String,Object> updatevars = new HashMap<String,Object>();
		updatevars.put("themeName", "default");
		KSQL.executeUpdate(updateksql, updatevars, DATA_MODEL, null);
		return true;
	}
	/**
	 * 更新全部状态
	 */
	private static void updateALLTheme(){
		String updateksql = "UPDATE Portal2Profiles s1 SET s1.sThemeActivity='' where s1.sPublishType='theme' and s1.sThemeActivity='activity'";
		KSQL.executeUpdate(updateksql, null, DATA_MODEL, null);
	}
	/**
	 * 修改当前主题状态 activity
	 * @param value 
	 * @return
	 */
	public static boolean updataThemeState(String value){
		updateALLTheme();
		String updateksql = "UPDATE Portal2Profiles s1 SET s1.sThemeActivity='activity' where s1.sPublishType='theme' and s1.sThemeName=:value";
		Map<String,Object> updatevars = new HashMap<String,Object>();
		updatevars.put("value", value);
		KSQL.executeUpdate(updateksql, updatevars, DATA_MODEL, null);
		return true;
	}
	/**
	 * 获取主题列表
	 * @param value
	 * @return
	 */
	public static Table getThemeList(){
		String ksql = "SELECT s1.sThemeName,s1.sThemeActivity FROM Portal2Profiles s1 where s1.sPublishType='theme'";
		Table table = KSQL.select(ksql, null, DATA_MODEL, null);
		return table;
	}
	/**
	 * 初始化主题列表
	 * @param value
	 * @return
	 */
	public static boolean resetThemeList(){
		String ksql = "SELECT s1.* FROM Portal2Profiles s1 where s1.sThemeName='default' and s1.sPublishType='theme'";
		Table table = KSQL.select(ksql, null, DATA_MODEL, null);
		if(table.size()>0){
			removeThemeList();
		}
		addThemeList();
		return true;
	}
	/**
	 * 获取系统设置内容-portal字段/系统名称
	 * @return
	 */
	public static Map<String,String> getSystemConfig(){
		Map<String,String> result = new HashMap<String,String>();
		String ksql = "SELECT s1.* FROM Portal2Profiles s1 where s1.sPublishType='theme' and s1.sThemeActivity='activity'";
		Table table = KSQL.select(ksql, null, DATA_MODEL, null);
		
		if(table.size()>0){
			Iterator<Row> it = table.iterator();
			if (it.hasNext()) {
				Row row = it.next();
				String val = row.getString("sPortal");
				if (val == null) val = ""; //解决用了fast json之后, null域会清除的问题
				result.put("sPortal", val);
				String sOther = row.getString("sOther");
				if (sOther == null) sOther = ""; //解决用了fast json之后, null域会清除的问题
				result.put("sOther", sOther);
			}
		}
		return result;
	}
	

    /**
	 * 系统设置-工具栏信息
	 * @param setInfo
	 * @param personID
	 * @return
	 */
	public static boolean saveSettingProfiles(String personID,String setInfo){
		String ksql = "SELECT s1.* FROM Portal2Profiles s1 where s1.sPublishType='theme' and s1.sThemeActivity='activity'";
		Map<String,Object> vars = new HashMap<String,Object>();
		vars.put("personID", personID);
		Table table = KSQL.select(ksql, vars, DATA_MODEL, null);
		if(table.size()>0){
			String updateksql = "UPDATE Portal2Profiles s1 SET s1.sOther=:value where s1.sPublishType='theme' and s1.sThemeActivity='activity'";
			Map<String,Object> updatevars = new HashMap<String,Object>();
			updatevars.put("value", setInfo);
			updatevars.put("personID", personID);
			KSQL.executeUpdate(updateksql, updatevars, DATA_MODEL, null);
		}else{
			String insertksql = "INSERT INTO Portal2Profiles s1(s1,s1.version,s1.sPersonID,s1.sOther, s1.sThemeActivity, s1.sPublishType) values (:id,:version,:personID,:value,'activity', 'theme')";
			Map<String,Object> insertvars = new HashMap<String,Object>();
			insertvars.put("id",CommonUtils.createGUID());
			insertvars.put("version", 0);
			insertvars.put("personID", personID);
			insertvars.put("value", setInfo);
			KSQL.executeUpdate(insertksql, insertvars, DATA_MODEL, null);
		}
		return true;

	}
	/**
	 * 系统加载-获取应用的主题信息 (activity)
	 * @param personID
	 * @return
	 */
	public static Map<String,String> selectPortalProfiles(String personID){
		String ksql = "SELECT s1.* FROM Portal2Profiles s1 where s1.sThemeActivity='activity' and s1.sPublishType='theme'";
		Map<String,Object> vars = new HashMap<String,Object>();
		vars.put("personID", personID);
		Table table = KSQL.select(ksql, vars, DATA_MODEL, null);
		Iterator<Row> it = table.iterator();
		
		Map<String,String> result = new HashMap<String,String>();
		String[] relations = new String[]{"sOther"};
		if (it.hasNext()) {
			Row row = it.next();
			for(String rel : relations){
				String val = row.getString(rel);
				if (val == null) val = ""; //解决用了fast json之后, null域会清除的问题
				result.put(rel, val);
			}
		}else{
			result = selectThemeByOrg(ContextHelper.getPersonMember().getID(), null);
		}
		// 是否拥有设置主题的权限
		result.put("layoutPermission", hasSetLayoutPermission(personID));
		// 获取首页设置
		Map<String,String> portalMap = getToolsInfo(personID);
		result.put("sPortal", portalMap.get("sPortal"));
		result.put("sFunctree", portalMap.get("sFunctree"));
		return result;
	}
	/**
	 * 获取首页信息-类型person
	 * 系统加载阶段
	 * @param personID
	 * @return
	 */
	private static Map<String,String> getToolsInfo(String personID){
		String ksql = "SELECT s1.* FROM Portal2Profiles s1 where s1.sPersonID=:personID and s1.sPublishType='person'";
		Map<String,Object> vars = new HashMap<String,Object>();
		vars.put("personID", personID);
		Table table = KSQL.select(ksql, vars, DATA_MODEL, null);
		Iterator<Row> it = table.iterator();
		
		Map<String,String> result = new HashMap<String,String>();
		String[] relations = new String[]{"sFunctree", "sPortal"};
		if (it.hasNext()) {
			Row row = it.next();
			for(String rel : relations){
				String val = row.getString(rel);
				if (val == null) val = ""; //解决用了fast json之后, null域会清除的问题
				result.put(rel, val);
			}
		}else{
			result = selectThemeByOrg(ContextHelper.getPersonMember().getID(), null);
		}
		return result;
	}
	/**
	 * 判断系统设置功能权限
	 * @param executor
	 * @return
	 */
	private static String hasSetLayoutPermission(String executor){
		Operator operator = ContextHelper.getOperator();
		String process = "/SA/theme/themeManager/themeManagerProcess";
		String activity = "mainActivity";
		return operator.hasActivityPermission(process, activity, executor)+ "";
	}
	/**
	 * 
	 * 查询当前组织应用的主题
	 * @param orgID
	 * @param type
	 * @return
	 */
	private static Map<String,String> selectThemeByOrg(String orgID, String type){
		String sql = "SELECT t1.* FROM Portal2Profiles t1 where t1.sThemeActivity='activity' and t1 IN (SELECT s1.sThemeID FROM Portal2ProfileManager s1 where s1.sOrgID=:orgid and s1.sPublishType is null)";
		Map<String,Object> vars = new HashMap<String,Object>();
		vars.put("orgid", orgID);
		if(type != null){
			sql = "SELECT t1.* FROM Portal2Profiles t1 where t1.sThemeActivity='activity' and t1 IN (SELECT s1.sThemeID FROM Portal2ProfileManager s1 where s1.sOrgID=:orgid and s1.sPublishType=:type)";	
			vars.put("type", type);
		}
		Table table = KSQL.select(sql, vars, DATA_MODEL, null);
		Iterator<Row> it = table.iterator();
		Map<String,String> result = new HashMap<String,String>();
		String[] relations = new String[]{"sFunctree", "sPortal", "sOther"};
		if (it.hasNext()) {
			Row row = it.next();
			for(String rel : relations){
				result.put(rel, row.getString(rel));
			}
		}else{	
			String sql2 = "SELECT s1.sParent FROM SA_OPOrg s1 where s1='"+orgID+"'";
			Table table2 = KSQL.select(sql2, null, SYSTEM_DATA_MODEL, null);
			Iterator<Row> it2 = table2.iterator();
			if(it2.hasNext()){
				result = selectThemeByOrg(it2.next().getString("sParent"), type);
				
			}else{
				for(String rel : relations){
					result.put(rel, "");
				}
			}
		}
		return result;
	}
	

	public static void removePortalProfiles(String personID){
		
	}
	
	public static boolean saveFunctree(String functree,String personID){
		String ksql = "SELECT s1.* FROM Portal2Profiles s1 where s1.sPersonID=:personID and s1.sPublishType='person'";
		Map<String,Object> vars = new HashMap<String,Object>();
		vars.put("personID", personID);
		Table table = KSQL.select(ksql, vars, DATA_MODEL, null);
		if(table.size()>0){
			String updateksql = "UPDATE Portal2Profiles s1 SET s1.sFunctree=:functree where s1.sPersonID=:personID and s1.sPublishType='person'";
			Map<String,Object> updatevars = new HashMap<String,Object>();
			updatevars.put("functree", functree);
			updatevars.put("personID", personID);
			KSQL.executeUpdate(updateksql, updatevars, DATA_MODEL, null);
		}else{
			Map<String,String> defaultValue = selectPortalProfiles(personID);
			String insertksql = "INSERT INTO Portal2Profiles s1(s1,s1.version, s1.sPersonID,s1.sOther, s1.sPortal, s1.sFunctree, s1.sPublishType) values (:id,:version,:personID,:other, :portal, :functree,'person')";
			Map<String,Object> insertvars = new HashMap<String,Object>();
			insertvars.put("id",CommonUtils.createGUID());
			insertvars.put("version", 0);
			insertvars.put("functree", functree);
			insertvars.put("portal", defaultValue.get("sPortal"));
			insertvars.put("other", defaultValue.get("sOther"));
			insertvars.put("personID", personID);
			KSQL.executeUpdate(insertksql, insertvars, DATA_MODEL, null);
		}
		return true;

	}
	
	public static boolean saveOther(String other,String personID){
		String ksql = "SELECT s1.* FROM Portal2Profiles s1 where s1.sPersonID=:personID and s1.sPublishType='person'";
		Map<String,Object> vars = new HashMap<String,Object>();
		vars.put("personID", personID);
		Table table = KSQL.select(ksql, vars, DATA_MODEL, null);
		if(table.size()>0){
			String updateksql = "UPDATE Portal2Profiles s1 SET s1.sOther=:other where s1.sPersonID=:personID and s1.sPublishType='person'";
			Map<String,Object> updatevars = new HashMap<String,Object>();
			updatevars.put("other", other);
			updatevars.put("personID", personID);
			KSQL.executeUpdate(updateksql, updatevars, DATA_MODEL, null);
		}else{
			Map<String,String> defaultValue = selectPortalProfiles(personID);
			String insertksql = "INSERT INTO Portal2Profiles s1(s1,s1.version, s1.sPersonID,s1.sOther, s1.sPortal, s1.sFunctree, s1.sPublishType) values (:id,:version,:personID,:other, :portal, :functree,'person')";
			Map<String,Object> insertvars = new HashMap<String,Object>();
			insertvars.put("id",CommonUtils.createGUID());
			insertvars.put("version", 0);
			insertvars.put("functree", defaultValue.get("sFunctree"));
			insertvars.put("portal", defaultValue.get("sPortal"));
			insertvars.put("other", other);
			insertvars.put("personID", personID);
			KSQL.executeUpdate(insertksql, insertvars, DATA_MODEL, null);
		}
		return true;

	}
	
	public static boolean saveOther3(String other,String personID){
		String ksql = "SELECT s1.* FROM Portal2Profiles s1 where s1.sPersonID=:personID and s1.sPublishType='person4portal3'";
		Map<String,Object> vars = new HashMap<String,Object>();
		vars.put("personID", personID);
		Table table = KSQL.select(ksql, vars, DATA_MODEL, null);
		if(table.size()>0){
			String updateksql = "UPDATE Portal2Profiles s1 SET s1.sOther=:other where s1.sPersonID=:personID and s1.sPublishType='person4portal3'";
			Map<String,Object> updatevars = new HashMap<String,Object>();
			updatevars.put("other", other);
			updatevars.put("personID", personID);
			KSQL.executeUpdate(updateksql, updatevars, DATA_MODEL, null);
		}else{
			Map<String,String> defaultValue = selectPortal3Profiles(personID);
			String insertksql = "INSERT INTO Portal2Profiles s1(s1,s1.version, s1.sPersonID,s1.sOther, s1.sPortal, s1.sFunctree, s1.sPublishType) values (:id,:version,:personID,:other, :portal, :functree,'person4portal3')";
			Map<String,Object> insertvars = new HashMap<String,Object>();
			insertvars.put("id",CommonUtils.createGUID());
			insertvars.put("version", 0);
			insertvars.put("functree", defaultValue.get("sFunctree"));
			insertvars.put("portal", defaultValue.get("sPortal"));
			insertvars.put("other", other);
			insertvars.put("personID", personID);
			KSQL.executeUpdate(insertksql, insertvars, DATA_MODEL, null);
		}
		return true;

	}
	public static boolean savePortal(String portal,String personID){
		String ksql = "SELECT s1.* FROM Portal2Profiles s1 where s1.sPersonID=:personID and s1.sPublishType='person'";
		Map<String,Object> vars = new HashMap<String,Object>();
		vars.put("personID", personID);
		Table table = KSQL.select(ksql, vars, DATA_MODEL, null);
		if(table.size()>0){
			String updateksql = "UPDATE Portal2Profiles s1 SET s1.sPortal=:portal where s1.sPersonID=:personID and s1.sPublishType='person'";
			Map<String,Object> updatevars = new HashMap<String,Object>();
			updatevars.put("portal", portal);
			updatevars.put("personID", personID);
			KSQL.executeUpdate(updateksql, updatevars, DATA_MODEL, null);
		}else{
			//Map<String,String> defaultValue = selectPortalProfiles(personID);
			String insertksql = "INSERT INTO Portal2Profiles s1(s1,s1.version, s1.sPersonID,s1.sOther, s1.sPortal, s1.sFunctree, s1.sPublishType) values (:id,:version,:personID,:other, :portal, :functree,'person')";
			Map<String,Object> insertvars = new HashMap<String,Object>();
			insertvars.put("id",CommonUtils.createGUID());
			insertvars.put("version", 0);
			insertvars.put("portal", portal);
			insertvars.put("functree", null);
			insertvars.put("other", null);
			insertvars.put("personID", personID);
			KSQL.executeUpdate(insertksql, insertvars, DATA_MODEL, null);
		}
		return true;

	}
	
	public static boolean clearPortal(String personID){
		String ksql = "SELECT s1.* FROM Portal2Profiles s1 where s1.sPersonID=:personID";
		Map<String,Object> vars = new HashMap<String,Object>();
		vars.put("personID", personID);
		Table table = KSQL.select(ksql, vars, DATA_MODEL, null);
		if(table.size()>0){
			String updateksql = "DELETE from Portal2Profiles s1 where s1.sPersonID=:personID and s1.sPublishType='person'";
			Map<String,Object> updatevars = new HashMap<String,Object>();
			updatevars.put("personID", personID);
			KSQL.executeUpdate(updateksql, updatevars, DATA_MODEL, null);
		}
		return true;

	}
	
	private static Map<String,String> selectProfiles(String personID, String type, String type2){
		String ksql = "SELECT s1.* FROM Portal2Profiles s1 where s1.sPersonID=:personID and s1.sPublishType=:type";
		Map<String,Object> vars = new HashMap<String,Object>();
		vars.put("personID", personID);
		vars.put("type", type);
		Table table = KSQL.select(ksql, vars, DATA_MODEL, null);
		Iterator<Row> it = table.iterator();
		
		Map<String,String> result = new HashMap<String,String>();
		String[] relations = new String[]{"sFunctree", "sPortal", "sOther"};
		if (it.hasNext()) {
			Row row = it.next();
			for(String rel : relations){
				result.put(rel, row.getString(rel));
			}
		}else{
			result = selectThemeByOrg(ContextHelper.getPersonMember().getID(), type2);
		}
		return result;
	}

	public static Map<String,String> selectPortal3Profiles(String personID){
		return selectProfiles(personID, "person4portal3", "portal3");
	}

	public static boolean savePortalProfiles(String portal,String personID, String type){
		String ksql = "SELECT s1.* FROM Portal2Profiles s1 where s1.sPersonID=:personID and s1.sPublishType=:type";
		Map<String,Object> vars = new HashMap<String,Object>();
		vars.put("personID", personID);
		vars.put("type", type);
		Table table = KSQL.select(ksql, vars, DATA_MODEL, null);
		if(table.size()>0){
			String updateksql = "UPDATE Portal2Profiles s1 SET s1.sPortal=:portal where s1.sPersonID=:personID and s1.sPublishType=:type";
			Map<String,Object> updatevars = new HashMap<String,Object>();
			updatevars.put("portal", portal);
			updatevars.put("personID", personID);
			updatevars.put("type", type);
			KSQL.executeUpdate(updateksql, updatevars, DATA_MODEL, null);
		}else{
			String insertksql = "INSERT INTO Portal2Profiles s1(s1,s1.version, s1.sPersonID,s1.sOther, s1.sPortal, s1.sFunctree, s1.sPublishType) values (:id,:version,:personID,:other, :portal, :functree, :type)";
			Map<String,Object> insertvars = new HashMap<String,Object>();
			insertvars.put("id",CommonUtils.createGUID());
			insertvars.put("version", 0);
			insertvars.put("portal", portal);
			insertvars.put("functree", "");//TODO
			insertvars.put("other", "");//TODO
			insertvars.put("personID", personID);
			insertvars.put("type", type);
			KSQL.executeUpdate(insertksql, insertvars, DATA_MODEL, null);
		}
		return true;

	}
	
	public static boolean savePortal3Profiles(String portal,String personID){
		return savePortalProfiles(portal, personID, "person4portal3");
	}
}