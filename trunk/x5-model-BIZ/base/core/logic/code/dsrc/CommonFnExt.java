import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.butone.codeinf.model.CodeDef;
import com.butone.codeinf.util.CodeGenerator;
import com.butone.data.BizDataUtils;
import com.butone.extend.CacheManager;
import com.butone.extend.DataReplication;
import com.butone.extend.ModelPathHelper;
import com.butone.flowbiz.FlowBizConsts;
import com.butone.logic.impl.ProcessLogicPluginContext;
import com.butone.sequencecode.SequenceCodeImpl;
import com.butone.spi.FlowControlUtils;
import com.butone.x5Impl.TableImpl;
import com.jayway.jsonpath.JsonPath;
import com.justep.exception.BusinessException;
import com.justep.model.Model;
import com.justep.model.ModelUtils;
import com.justep.system.context.ContextHelper;
import com.justep.system.data.BizData;
import com.justep.system.data.DatabaseProduct;
import com.justep.system.data.KSQL;
import com.justep.system.data.Row;
import com.justep.system.data.SQL;
import com.justep.system.data.Table;
import com.justep.system.opm.OrgUnit;
import com.justep.system.opm.OrgUtils;
import com.justep.system.opm.Person;
import com.justep.system.process.ProcessUtils;
import com.justep.system.transform.Utils;
import com.justep.system.util.CommonUtils;

public class CommonFnExt {
	private static final Log logger = LogFactory.getLog(CommonFnExt.class);

	public static void println(Object object) {
		System.out.println(object);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List listAdd(List list, Object other) {
		if (list == null)
			list = new ArrayList();
		list.add(other);
		return list;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Set hashset(Set set, Object value) {
		if (set == null)
			set = new HashSet();
		if (value != null)
			set.add(value);
		return set;
	}

	public static boolean contains(@SuppressWarnings("rawtypes") Collection coll, Object value) {
		return coll.contains(value);
	}

	public static String lpad(String str, int len, String pad) {
		if (Utils.isEmptyString(pad))
			throw new RuntimeException("lpad函数pad参数不允许为空");
		if (str == null)
			str = "";
		int n = len - str.length();
		for (int i = 0; i < n; i++)
			str = pad + str;
		return str.substring(0, len);
	}

	/**
	 * 替换SQL\KSQL中的in子句
	 * @param in
	 * @param values
	 * @return
	 */
	public static String inClause(String in, String values) {
		return in + " in (" + values + ")";
	}

	/**
	 * 更新业务数据
	 * @param updateSQL 更新语句
	 * @return
	 * @throws SQLException 
	 */
	public static boolean updateBizData(String updateSQL, String dataModel) throws Exception {
		SQL.executeUpdate(updateSQL, null, Utils.isNotEmptyString(dataModel) ? dataModel : FlowBizConsts.DATA_MODEL_SYSTEM);
		return true;
	}

	/**
	 * 更新业务数据
	 * @param updateStatement 更新语句
	 * @return
	 * @throws SQLException 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static int updateSQL(String updateStatement, String dataModel, List binds) {
		return SQL.executeUpdate(updateStatement, binds, dataModel != null ? dataModel : "/system/data");
	}

	public Object getBizRecData(String concpet, String relation) {
		Model dataModel = ModelUtils.getModel(ModelPathHelper.getProcessDataModel(ContextHelper.getActionContext().getProcess()));
		String[] list = relation.split(",");
		StringBuffer sb = new StringBuffer();
		for (String f : list) {
			sb.append("t.").append(f).append(",");
		}
		String sql = sb.toString().toString();
		sql = sql.substring(0, sql.length() - 1);
		sql = "select " + sql + " from " + concpet + " t where t.fBizRecId=:bizRecId";
		Map<String, Object> varMap = new HashMap<String, Object>();
		varMap.put("bizrecId", ProcessUtils.getProcessData1());
		Table table = KSQL.select(sql, varMap, dataModel, null);
		Row r = table.iterator().next();
		if (list.length == 1) {
			return r == null ? null : r.getValue(relation);
		} else {
			List<Object> ret = new ArrayList<Object>();
			for (String f : list) {
				if (r == null)
					ret.add(null);
				else
					ret.add(r.getValue(f));
			}
			return ret;
		}
	}

	public static Object jsonPut(Object obj, Object... key_value) {
		JSONObject ret;
		if (obj instanceof JSONObject) {
			ret = (JSONObject) obj;
		} else {
			ret = new JSONObject();
			if (obj != null)
				ret.putAll(JSONObject.parseObject(obj.toString()));
		}
		int cnt = key_value.length / 2;
		for (int i = 0; i < cnt; i++) {
			ret.put((String) key_value[2 * i], key_value[2 * i + 1]);
		}
		return ret;
	}

	public static Object jsonGetAttr(Object obj, String key) {
		if (obj instanceof JSONObject) {
			return ((JSONObject) obj).get(key);
		} else {
			return JSONObject.parseObject(obj.toString()).get(key);
		}
	}

	public static Object jsonArrayAdd(Object obj, Object child) {
		JSONArray ja = new JSONArray();
		if (obj != null) {
			String objstr = obj.toString();
			ja = JSONArray.parseArray(objstr);
		}
		ja.add(child);
		return ja;
	}

	/**
	 * 判断JSONArray长度
	 * @author yipu
	 * @return int
	 */
	public static int getJSONArraySize(Object obj) {
		JSONArray ja = new JSONArray();
		if (obj != null) {
			String objstr = obj.toString();
			ja = JSONArray.parseArray(objstr);
		}
		return ja.size();
	}

	public static List<String> strToList(String str, String spliter) {
		if (str != null) {
			String[] strAry = str.split(spliter);
			List<String> list = new ArrayList<String>();
			for (int i = 0; i < strAry.length; i++) {
				list.add(strAry[i]);
			}
			return list;
		}
		return new ArrayList<String>();
	}

	public static Object jsonArrayGet(Object obj, int index) {
		JSONArray ja = new JSONArray();
		if (obj != null) {
			String objstr = obj.toString();
			ja = JSONArray.parseArray(objstr);
		}
		Object obj_ = ja.get(index);
		return obj_;
	}

	/**
	 * 启动流程,返回启动后的环节实例ID(ActivityInstance)
	 */
	public static String startFlow(String bizRecId, String process, String activity, List<OrgUnit> executors) {
		return FlowControlUtils.startFlow(bizRecId, process, activity, executors);
	}

	/**
	 * 创建Map对象
	 * @return
	 */
	public static Map<String, Object> newMap(Object... keyValues) {
		Map<String, Object> ret = new HashMap<String, Object>();
		if (keyValues != null) {
			int n = 0;
			while (n < keyValues.length) {
				String key = keyValues[n] + "";
				Object value = null;
				if (n < keyValues.length - 1)
					value = keyValues[++n];
				ret.put(key, value);
				n++;
			}
		}
		return ret;
	}

	/**
	 * 设置Map属性值
	 * @param obj
	 * @param name
	 * @param value
	 * @return
	 */
	public static Map<String, Object> mapPut(Map<String, Object> obj, Object... keyValues) {
		if (obj == null)
			return newMap(keyValues);
		else
			obj.putAll(newMap(keyValues));
		return obj;
	}

	//获取Map的指定属性的值
	public static Object mapGet(Map<String, Object> obj, String key) {
		if (obj == null)
			return null;
		else
			return obj.get(key);
	}

	public static JSONObject toJsonObj(Object obj) {
		if (obj instanceof JSONObject)
			return (JSONObject) obj;
		else {
			return JSON.parseObject(obj == null ? "{}" : obj.toString());
		}
	}

	//获取List的元素个数
	@SuppressWarnings("rawtypes")
	public static Integer getListSize(List plist) {
		Integer i = 0;
		if (plist != null) {
			i = plist.size();
		}
		return i;
	}

	//获取List的元素
	@SuppressWarnings("rawtypes")
	public static Object getListValueByIndex(List plist, int index) {
		Object obj = null;
		if (plist != null && plist.size() > 0) {
			obj = plist.get(index);
		}
		return obj;
	}

	public static Object readByJSONpath(Object sourceJson, String jsonPath) throws ParseException {
		//Object obj = JsonPath.read(sourceJson, jsonPath);
		String jsonText;
		if (sourceJson instanceof JSONObject)
			jsonText = ((JSONObject) sourceJson).toJSONString();
		else
			jsonText = sourceJson.toString();
		Object o = JsonPath.read(jsonText, jsonPath);
		return o;
	}

	public static InputStream toInputStream(Object obj, String charset) throws UnsupportedEncodingException {
		byte[] buff;
		if (obj == null) {
			buff = new byte[0];
		} else if (obj instanceof byte[]) {
			buff = (byte[]) obj;
		} else {
			buff = obj.toString().getBytes(charset == null ? "utf-8" : charset);
		}
		InputStream in = new ByteArrayInputStream(buff);
		return in;
	}

	public static boolean loadJsonToTables(JSONObject srcData, Map<String, Table> tableMap) {

		boolean ret = false;
		if (!srcData.getBooleanValue("success"))
			return ret;
		JSONObject data = srcData.getJSONObject("results");
		int count_layers = data.getJSONArray("overlayResults").size();
		int count_features = 0;
		String targetLayerName = "";
		Table memTable = null;
		try {
			for (int i = 0; i < count_layers; i++) {
				targetLayerName = data.getJSONArray("overlayResults").getJSONObject(i).getString("targetBizlayer");
				memTable = tableMap.get(targetLayerName);
				if (memTable == null) //如果目标图层没有传响应的内存表，则不予处理
					continue;
				count_features = data.getJSONArray("overlayResults").getJSONObject(i).getJSONArray("overlayInfos").size();
				for (int j = 0; j < count_features; j++) {
					Row row = memTable.appendRow();
					Collection<String> cols = memTable.getMetaData().getColumnNames();
					for (String colName : cols) {

						if (colName.equalsIgnoreCase("_innerId")) { //主键字段赋值
							row.setString("_innerId", CommonUtils.createGUID());
						} else if (colName.equalsIgnoreCase("overlayArea")) { //overlayArea 为的节点位置不同，特殊处理返回相交面积
							row.setDecimal(colName, data.getJSONArray("overlayResults").getJSONObject(i).getJSONArray("overlayInfos")
									.getJSONObject(j).getBigDecimal("overlayArea"));
						} else {
							row.setValue(colName, data.getJSONArray("overlayResults").getJSONObject(i).getJSONArray("overlayInfos").getJSONObject(j)
									.getJSONObject("targetFeature").getJSONObject("attributes").get(colName));
						}
					}
				}
			}
			ret = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@SuppressWarnings("unchecked")
	public static boolean loadJsonToTables2(JSONObject srcData, Map<String, Table> tableMap, Object foreignKey) {

		boolean ret = false;
		if (tableMap.size() < 2) {
			logger.info("级联占压结果转内存时，传入的内存表个数必须>=2！，当前传入个数为：" + tableMap.size());
			return ret;
		}
		if (!srcData.getBooleanValue("success"))
			return ret;
		Map<String, String> foreign_Key = new HashMap<String, String>();
		if (foreignKey instanceof Map) {
			foreign_Key = (Map<String, String>) foreignKey;
		} else if (foreignKey instanceof String) {
			String str_foreignKey = (String) foreignKey;
			String[] table_keys = str_foreignKey.split(";");
			for (String table_key : table_keys) {
				String[] list_table_key = table_key.split("\\.");
				foreign_Key.put(list_table_key[0], list_table_key[1]);
			}
		}

		if (foreign_Key.size() != tableMap.size() - 1) {
			logger.info("级联占压结果转内存时，外键参数传入的个数不正确！");
			return ret;
		}

		JSONObject data = srcData.getJSONObject("results");
		int count_layers = data.getJSONArray("cascadeOverlayInfos").size();
		int count_features = 0;
		String targetLayerName = "";

		try {
			for (int i = 0; i < count_layers; i++) {
				targetLayerName = data.getJSONArray("cascadeOverlayInfos").getJSONObject(i).getString("targetBizLayer");
				TableImpl memTable = new TableImpl(tableMap.get(targetLayerName));
				if (memTable.getTarget() == null) //如果目标图层没有传响应的内存表，则不予处理
					continue;
				count_features = data.getJSONArray("cascadeOverlayInfos").getJSONObject(i).getJSONArray("cascadeOverlayInfo").size();
				for (int j = 0; j < count_features; j++) {
					Row row = memTable.getTarget().appendRow();
					Collection<String> cols = memTable.getTarget().getMetaData().getColumnNames();
					for (String colName : cols) {

						if (colName.equalsIgnoreCase(BizDataUtils.MemoryTableKeyColumnName)) { //主键字段赋值
							String id = CommonUtils.createGUID();
							row.setString(BizDataUtils.MemoryTableKeyColumnName, id);
						} else if (colName.equalsIgnoreCase("overlayArea")) { //overlayArea 为的节点位置不同，特殊处理返回相交面积
							row.setDecimal(colName, data.getJSONArray("cascadeOverlayInfos").getJSONObject(i).getJSONArray("cascadeOverlayInfo")
									.getJSONObject(j).getBigDecimal("overlayArea"));
						} else if (i > 0 && foreign_Key.get(targetLayerName) != null
								&& colName.equalsIgnoreCase((String) foreign_Key.get(targetLayerName))) {
							//这里处理明细表的外键赋值  i>0 表示为明细表， 并且传入的外键不为空，并且在内存表中找到该字段
							int parentId = data.getJSONArray("cascadeOverlayInfos").getJSONObject(i).getJSONArray("cascadeOverlayInfo")
									.getJSONObject(j).getIntValue("parentId");
							String parentTableName = data.getJSONArray("cascadeOverlayInfos").getJSONObject(i - 1).getString("targetBizLayer");
							TableImpl parentTable = new TableImpl(tableMap.get(parentTableName));
							Row r = (Row) parentTable.getRow(parentId);
							String fkeyValue = r.getString(BizDataUtils.MemoryTableKeyColumnName);
							row.setValue((String) foreign_Key.get(targetLayerName), fkeyValue);
						}

						else {
							row.setValue(colName, data.getJSONArray("cascadeOverlayInfos").getJSONObject(i).getJSONArray("cascadeOverlayInfo")
									.getJSONObject(j).getJSONObject("targetFeature").getJSONObject("attributes").get(colName));
						}
					}
				}
			}
			ret = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	/**
	 * 预收件操作函数
	 * 执行时机:1、交换平台上报时; 2、直接在省厅系统中上报时; 3、收件和退件操作
	 * @param fPreBizRecId  预收件ID
	 * @param personName  上传人
	 * @param fStatus   状态
	 * @param fRemark   备注
	 * @return
	 */
	public static String updatePreLog(String fPreBizRecId, String personName, String fStatus, String fRemark) {

		String dataModel = "/base/core/flow/data";
		Person person = ContextHelper.getPersonMember().getPerson();
		if (Utils.isNotEmptyString(personName))
			personName = person.getName();

		Table tab = KSQL.select("SELECT p.* FROM B_PrepLog p where 1=2", null, dataModel, null);
		tab.getMetaData().setStoreByConcept("B_PrepLog", true);
		tab = BizData.create(tab, "B_PrepLog", null, dataModel);
		Row rec = tab.iterator().next();
		rec.setValue("fLoger", personName);
		rec.setDateTime("fLogTime", CommonUtils.getCurrentDateTime());
		rec.setValue("fStatus", fStatus);
		rec.setValue("fRemark", fRemark == null ? "" : fRemark);
		rec.setValue("fPreBizRecId", fPreBizRecId);
		return String.valueOf(tab.save(dataModel));

	}

	public static String preStartFlow(String bizRecId, String process, String activity, String fExecutorexpr) {

		List<OrgUnit> executors = OrgUtils.findOrgUnitsByFID(Arrays.asList(fExecutorexpr.split(",")));
		try {
			FlowControlUtils.startFlow(bizRecId, process, activity, executors);
		} catch (Exception e) {
			e.printStackTrace();
			return "启动流程出现异常!";
		}
		return "ok";
	}

	/**
	 * Table复制
	 * @param fromTable
	 * @param toTable
	 */
	public static void copyTable(Table fromTable, Table toTable) {
		DataReplication.copyTable(fromTable, toTable);
	}

	/**
	 * 行数据复制 
	 * @param fromRow
	 * @param toRow
	 */
	public static void copyRow(Row fromRow, Row toRow, Table toTable, String column, String value) {
		DataReplication.copyRow(fromRow, toRow, toTable, column, value);
	}

	public static void copyRowEx(Row fromRow, Row toRow, Table toTable, Object fields, Map<String, Object> mapValue) {
		DataReplication.copyRowEx(fromRow, toRow, toTable, fields, mapValue);
	}

	/**
	 * 材料复制
	 * @param fromId
	 * @param toId
	 */
	public static void copyMaterial(String fromId, String toId) {
		DataReplication.copyMaterial(fromId, toId);
	}

	/**
	 * 获取指定范围的随机整数
	 * 
	 * @param start
	 * @param end
	 * @return
	 */

	public static Integer getRandomInteger(Integer start, Integer end) {
		start = (end > start) ? start : end;
		Random random = new Random();
		Integer ret = null;
		ret = random.nextInt(Math.abs(end - start + 1)) + start;
		return ret;
	}

	/**
	 * 随机日期
	 * 
	 * @param start
	 * @param end
	 * @return
	 */

	public static Date getRandomDate(Date start, Date end) {
		int d = end.compareTo(start);
		start = (d > 0) ? start : end;
		long n = Math.abs(CommonUtils.dateDiff("day", start, end));
		Random random = new Random();
		Date ret = null;
		int diffDays = random.nextInt((int) n);
		ret = CommonUtils.addDays(start, diffDays);
		return ret;
	}

	public static JSONArray toJSONArray(String list) {
		if (list != null) {
			return JSONArray.parseArray(list);
		} else
			return new JSONArray();
	}

	/**
	 * 系统参数
	 * @param sysName
	 * @return
	 */
	public static String getSysPara(String sysName) {

		String sql_info = "Select * From B_sysPara  where sysName='" + sysName + "'";
		Map<String, String> sqlmap = new HashMap<String, String>();
		sqlmap.put(DatabaseProduct.DEFAULT.name(), sql_info);
		List<Object> params = new ArrayList<Object>();
		Table tab = SQL.select(sqlmap, params, "/system/data");

		if (tab.size() > 0)
			return tab.iterator().next().getValue("SYSVALUE").toString().trim();
		return "没有参数名对应的值";
	}

	public static boolean containStr(String srcString, String cldString) {
		if (srcString == null || cldString == null)
			return false;
		else
			return srcString.contains(cldString);
	}

	public static BigDecimal divide(Object num1, Object num2) {
		if (num1 == null || num2 == null)
			return null;
		else {
			BigDecimal n1 = CommonUtils.toDecimal(num1);
			BigDecimal n2 = CommonUtils.toDecimal(num2);
			return n1.divide(n2);
		}
	}

	public static String makeSequenceCode(String codeGuid, String concept, Map<String, String> nodeValues, String relation, String idValue) {
		CodeDef def = CacheManager.getCodeDef(codeGuid);
		if (def == null) {
			throw new BusinessException("未生成" + concept + "." + relation + "的编码定义");
		}
		CodeGenerator generator = new CodeGenerator();
		generator.setSequenceCode(new SequenceCodeImpl());
		String ret = generator.makeCodeValue(def, nodeValues, concept, relation, idValue);
		return ret;
	}

	/**
	 * 格式化数字
	 * @param val
	 * @param scal
	 * @return
	 */
	public static String formatNumber(Object val, int scal) {
		BigDecimal b = new BigDecimal(val.toString());
		BigDecimal b1 = b.setScale(scal, BigDecimal.ROUND_HALF_UP);
		String formatStr = "######0.";
		for (int i = 0; i < scal; i++)
			formatStr = formatStr + "0";
		DecimalFormat df = new DecimalFormat(formatStr);
		return df.format(b1);
	}
	
	/**
	 * 判断会签是否已签名
	 * @param val
	 * @param scal
	 * @return
	 */
	public static boolean signInfoIsNull(String objectId, Object... key_value) {
		Table tab = ProcessLogicPluginContext.getTableControlObjectTarget(objectId);
		Iterator<Row> it = tab.iterator();
		while (it.hasNext()) {
			String flag = "true";
			Row r = it.next();
			int cnt = key_value.length / 2;
			if (Utils.isNotEmptyString(r.getString("fSignInfo")) && r.getString("fPersonId").equals(ContextHelper.getPerson().getID())) {
				flag = "false";
				for (int i = 0; i < cnt; i++) {
					if (r.getString((String) key_value[2 * i]).equals(key_value[2 * i + 1]))
						flag += "false";
					else
						flag += "true";
				}
			}
			if(!flag.contains("true"))
				return false;
		}
		return true;
	}
	
}
