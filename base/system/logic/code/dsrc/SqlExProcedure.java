import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
import com.butone.data.SQLUtils;
import com.justep.system.data.DatabaseProduct;
import com.justep.system.data.Row;
import com.justep.system.data.SQL;
import com.justep.system.data.Table;
import com.justep.system.data.TableMetaData;
import com.alibaba.fastjson.JSONArray;
import com.butone.utils.SysUtils;


public class SqlExProcedure {

	public static Object selectOneFld(String table, String filter, String returnFld) {
		String sql = "select " + returnFld + " from " + table + " where " + filter;
		List<Object> param = new ArrayList<Object>();
		Table tableResult = SQLUtils.select(sql, param, "/base/system/data");
		if (tableResult.size()>0) {
			Row row = tableResult.iterator().next();
			return row.getValue(0);
		}
		return null;
	}

	/**
	 * Sql查询可返回多个字段的查询结果集
	 * 为前台建立查询数据库的通道，并返回数据,支持多表关联查询
	 * @param virtualTable 查询结果集，可以是单表也可以是一个多表关联查询结果
	 * @param filter  过滤条件
	 * @param fields  返回字段名称，多个字段以逗号隔开，字段名称建议全部大写
	 * @return json数组
	 */
	public static JSONArray selectFieldValue(String virtualTable, String filter, String fields) {
		String sql = "select " + fields + " from  (" + virtualTable + ") where " + filter;
		List<Object> param = new ArrayList<Object>();
		Table tableResult = SQLUtils.select(sql, param, "/base/system/data");
		//
		return tableToJsonArray(tableResult);
	}
	/**
	 * table转json数组
	 * @param tableResult  
	 * @return
	 */
	public static JSONArray tableToJsonArray(Table tableResult){
		JSONArray resultData = new JSONArray();
		if (tableResult.size()>0) {
			TableMetaData metaData = tableResult.getMetaData();
			Iterator<Row> it = tableResult.iterator();
			// 遍历Tablet中的每条数据
			while (it.hasNext()) {
				Row r = it.next();
				JSONObject jsonObj = new JSONObject();
				// 遍历每一列
				for (int i = 0; i < metaData.getColumnCount(); i++) {
					String columnName = metaData.getColumnMetaData(i).getName();
					String colType = metaData.getColumnMetaData(i).getType();
					if ("Date".equals(colType)) {
						jsonObj.put(columnName, r.getValue(columnName) == null ? "" : r.getDate(columnName));
					} else if ("DateTime".equals(colType)) {
						jsonObj.put(columnName, r.getValue(columnName) == null ? "" : r.getDateTime(columnName));
					} else {
						jsonObj.put(columnName, r.getValue(columnName) == null ? "" : r.getValue(columnName));
					}
				}
				resultData.add(jsonObj);
			}
		}
		return resultData;
	}
	
	public static Table sqlQueryEx(String sql, String variables) {
		Map<String, String> sqlMap = new HashMap<String, String>();
		List<Object> list = new ArrayList<Object>();
		if (variables != null) {
			String[] args = variables.split("\\|");
			for (int i = 0; i < args.length; i++) {
				if (args[i].startsWith("{")) {
					JSONObject obj = JSONObject.parseObject(args[i]);
					Iterator<String> keys= obj.keySet().iterator();
					while(keys.hasNext()){
						String key=keys.next();
						sql = sql.replaceAll(":"+key, obj.get(key).toString());
					}
				} else
					list.add(args[i]);
			}
		}
		sqlMap.put(DatabaseProduct.DEFAULT.name(), sql);
		return SQL.select(sqlMap, list, "/base/system/data");
	}
	
	
	/**
	 * 报表查询
	 * @param sqlDefs  [{id:'main',isMain:true,sql:'select 1 from dual',clds:[{id:'detail',fld:'',parentFld:''}]},{id:'detail',sql:'select 1 from dual'}]
	 * @param variables
	 * @return
	 */
	public static JSONArray reportQuery(String dataDefs, Map<String, Object> variables) {
		JSONArray defsAry = JSONArray.parseArray(dataDefs);
		//加载数据
		Map<String, JSONArray> dataMap = new HashMap<String, JSONArray>();
		for (int i = 0; i < defsAry.size(); i++) {
			JSONObject def = defsAry.getJSONObject(i);
			String id = def.getString("id");
			String sql = def.getString("sql");
			//替换变量
			List params = new ArrayList<Object>();
			Iterator<String> iter = variables.keySet().iterator();
			while (iter.hasNext()) {
				String key = iter.next();
				sql = sql.replaceAll(":" + key, variables.get(key).toString());
			}
			Table table = SysUtils.query(sql);
			Iterator<Row> rowIter = table.iterator();

			JSONArray items = new JSONArray();
			while (rowIter.hasNext()) {
				Row row = rowIter.next();
				JSONObject item = new JSONObject();
				for (int j = 0; j < table.getMetaData().getColumnCount(); j++) {
					String column = table.getMetaData().getColumnName(j);
					Object value = row.getValue(j);
					item.put(column, value);
				}
				items.add(item);
			}
			dataMap.put(id, items);
		}
		//构造数据关系
		JSONArray resultData = new JSONArray();
		//parseReportDef(defsAry, dataMap, null,resultData);
		for (int i = 0; i < defsAry.size(); i++) {
			JSONObject def = defsAry.getJSONObject(i);
			if(!def.containsKey("isMain"))
				continue;
			String id = def.getString("id");
			//构造OneData
			JSONObject oneData = new JSONObject();
			oneData.put("id", id);
			JSONArray items = JSONArray.parseArray(dataMap.get(id).toJSONString());
			if (def.containsKey("clds")) {
				for (int j = 0; j < items.size(); j++) {
					parseReportDef(def.getJSONArray("clds"), dataMap, items.getJSONObject(j));
				}
			}
			oneData.put("items", items);
			resultData.add(oneData);
		}
		return resultData;
	}

	private static void parseReportDef(JSONArray defs, Map<String, JSONArray> dataMap, JSONObject parentData) {
		JSONArray clds= new JSONArray();
		for (int i = 0; i < defs.size(); i++) {
			JSONObject def = defs.getJSONObject(i);
			String id = def.getString("id");
			//产生OneData
			JSONObject oneData = new JSONObject();
			oneData.put("id", id);
			String fld = def.getString("fld");
			String pFld = def.getString("parentFld");
			JSONArray _items = JSONArray.parseArray(dataMap.get(id).toJSONString());
			JSONArray items = new JSONArray();
			for (int j = 0; j < _items.size(); j++) {
				if (!_items.getJSONObject(j).get(fld).equals(parentData.get(pFld)))
					continue;
				items.add(JSONObject.parseObject(_items.getJSONObject(j).toJSONString()));
			}
			if (def.containsKey("clds")) {
				for (int j = 0; j < items.size(); j++) {
					parseReportDef(def.getJSONArray("clds"), dataMap, items.getJSONObject(j));
				}
			}
			oneData.put("items", items);
			clds.add(oneData);
		}
		parentData.put("clds", clds);
	}

}
