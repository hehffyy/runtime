import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.justep.system.context.ContextHelper;
import com.justep.system.data.DatabaseProduct;
import com.justep.system.data.Row;
import com.justep.system.data.SQL;
import com.justep.system.data.Table;
import com.justep.system.opm.OrgNode;
import com.justep.system.opm.OrgUnit;
import com.justep.system.opm.OrgUtils;
import com.justep.system.opm.api.PersonHelper;
import com.justep.system.transform.Utils;
import com.justep.system.util.CommonUtils;

public class OrgExtFn {

	public static List<OrgUnit> findOrgChildren3(Object org, String childCondition, Map<String, Object> params, boolean includeCurrentPerson,
			boolean includeAllChildren, boolean includePersonMember) {
		List<OrgUnit> list = OrgUtils.findOrgChildren2(org, childCondition, params, false, includeAllChildren, includePersonMember);
		if (!includeCurrentPerson) {
			String personFID = ContextHelper.getPersonMember().getFID();
			List<OrgUnit> orgUnits=new ArrayList<OrgUnit>();
			
			for (OrgUnit orgUnit : list) {
				if (!(orgUnit.getFID().equalsIgnoreCase(personFID)))
					orgUnits.add(orgUnit);
			}
			return orgUnits;			
		}
		return list;
	}

	/**
	 * 获取人员指定信息
	 * @param colName 人员表字段
	 * @return 字段值（String）
	 */
	public static String getCurrentPersonInfo(String colName) {
		Object o = PersonHelper.loadPerson(ContextHelper.getPerson().getID(), Arrays.asList(colName)).getExtValue(colName);
		if (Utils.isNull(o)) {
			return null;
		}
		return o.toString();
	}

	/**
	 * 获取当前人员职务
	 * @return 职务（String）
	 */
	public static String getCurrentPersonPostName() {
		return (String) ContextHelper.getPerson().getAttribute("sTitle");
	}

	/**
	 * 获取人员全局顺序
	 * @return 全局顺序（String）
	 */
	public static String getCurrentPersonGlobalSequence() {
		return (String) ContextHelper.getPerson().getAttribute("sGlobalSequence");
	}

	/**
	 * 取上级区域
	 * 说明：上级区域是指当前区域在区域表里的上级区域，不是当前区域所在部门或机构的上级部门的区域(严格上讲两者应该是一致的，机构定义区域是并未严格限制)
	 * @param idOrName
	 * @return
	 */
	public static String getHigherLevelArea(boolean idOrName) {

		String sql_info = "Select * From B_Area ";
		Map<String, String> sqlmap = new HashMap<String, String>();
		List<Object> params = new ArrayList<Object>();
		sqlmap.put(DatabaseProduct.DEFAULT.name(), sql_info);
		Table tab = SQL.select(sqlmap, params, "/system/data");
		Iterator<Row> rows = tab.iterator();
		HashMap<String, JSONObject> areaMap = new HashMap<String, JSONObject>();
		while (rows.hasNext()) {
			Row row = rows.next();
			JSONObject object = new JSONObject();
			object.put("id", row.getValue("FID"));
			object.put("code", row.getValue("FAREACODE"));
			object.put("name", row.getValue("FAREANAME"));
			object.put("parent", row.getValue("FPARENTID") == null ? "" : row.getValue("FPARENTID"));
			areaMap.put(row.getValue("FAREACODE").toString(), object);
			areaMap.put(row.getValue("FID").toString(), object);
		}

		String acreaId = currentAreaIdOrName(true);
		JSONObject object = areaMap.get(acreaId);
		if (object != null)//处理未定义区域或区域数据不完整
		{
			String parent = object.get("parent").toString();
			if (StringUtils.isEmpty(parent) || "-1".equals(parent))
				return idOrName ? object.get("code").toString() : object.get("name").toString();

			object = areaMap.get(object.get("parent").toString());
			return idOrName ? object.get("code").toString() : object.get("name").toString();
		}

		return "";
	}

	/**
	 * 获取当前区域
	 * 说明：当前人员所属区域，区域即可以是机构的属性，也可以是部门的属性，如果当前机构或部门不存在区域值，取上一级的值，直到取到值
	 * @param idOrName true 返回区域ID,false返回区域名称
	 * @return
	 */
	public static String currentAreaIdOrName(boolean idOrName) {
		HashMap<String, JSONObject> areaMap = getAreaMap();
		String returnValue = "";
		OrgNode ogn = ContextHelper.getPersonMember().getOgn();
		OrgNode dpt = ContextHelper.getPersonMember().getDept();
		ogn = (dpt != null) ? dpt : ogn;

		while (ogn != null) {
			if (areaMap.get(ogn.getID()) != null) {
				returnValue = idOrName ? areaMap.get(ogn.getID()).get("id").toString() : areaMap.get(ogn.getID()).get("name").toString();
				break;
			}
			ogn = ogn.getParent();
		}
		return returnValue;
	}

	private static HashMap<String, JSONObject> getAreaMap() {
		String sql_info = "Select SPARENT,SID,SFID ,SAREAID, SAREANAME From SA_OPOrg where SAREAID is not null ";
		Map<String, String> sqlmap = new HashMap<String, String>();
		List<Object> params = new ArrayList<Object>();
		sqlmap.put(DatabaseProduct.DEFAULT.name(), sql_info);
		Table tab = SQL.select(sqlmap, params, "/system/data");
		Iterator<Row> rows = tab.iterator();

		HashMap<String, JSONObject> areaMap = new HashMap<String, JSONObject>();
		while (rows.hasNext()) {
			Row row = rows.next();
			JSONObject object = new JSONObject();
			object.put("id", row.getValue("SAREAID"));
			object.put("name", row.getValue("SAREANAME"));
			areaMap.put(row.getValue("SID").toString(), object);
		}

		return areaMap;
	}

	/**
	 * 获取组织机构下具有某个脚色的人员，不包含子机构的人员
	 * @param roleCode
	 * @param inOrg
	 * @param isPersonMember
	 * @return List<OrgUnit>
	 */
	public static List<OrgUnit> findOrgUnitsHasRoleByCodeExt(String roleCode, Object inOrg, boolean isPersonMember) {
		List<OrgUnit> ret = OrgUtils.findOrgUnitsHasRoleByCode(roleCode, inOrg, isPersonMember);
		Iterator<OrgUnit> itor = ret.iterator();
		while (itor.hasNext()) {
			String orgKindID = CommonUtils.getExtOfFile(itor.next().getFID());
			if ("ogn".equals(orgKindID))
				itor.remove();
		}
		return ret;
	}

}