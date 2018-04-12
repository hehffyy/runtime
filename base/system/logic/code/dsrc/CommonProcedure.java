import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.butone.data.BizDataUtils;
import com.butone.data.SQLUtils;
import com.butone.extend.BizInfo;
import com.butone.extend.CacheManager;
import com.butone.extend.ModelPathHelper;
import com.butone.extend.TableInfo;
import com.butone.extend.TableLogicPluginEx;
import com.butone.logic.impl.BizExprHelper;
import com.butone.logic.impl.ProcessLogicPluginContext;
import com.butone.logic.impl.SystemParams;
import com.butone.logic.impl.TableControlObject;
import com.butone.model.xmlconfig.TableConfig;
import com.butone.model.xmlconfig.TableConfig.Parameter;
import com.butone.utils.CompareCallBack;
import com.butone.utils.ModelExtUtils;
import com.butone.utils.SortUtil;
import com.butone.x5Impl.TableImpl;
import com.butone.x5Impl.TableImpl.ConceptSaveInfo;
import com.justep.exception.BusinessException;
import com.justep.model.Action;
import com.justep.model.Activity;
import com.justep.model.Concept;
import com.justep.model.Listener;
import com.justep.model.Model;
import com.justep.model.ModelUtils;
import com.justep.model.Relation;
import com.justep.system.context.ActionContext;
import com.justep.system.context.ContextHelper;
import com.justep.system.data.BizData;
import com.justep.system.data.DataPermission;
import com.justep.system.data.Expression;
import com.justep.system.data.KSQL;
import com.justep.system.data.ModifyState;
import com.justep.system.data.Row;
import com.justep.system.data.Table;
import com.justep.system.data.TableUtils;
import com.justep.system.opm.OrgKinds;
import com.justep.system.opm.OrgUnit;
import com.justep.system.opm.OrgUtils;
import com.justep.system.opm.api.Org;
import com.justep.system.opm.api.OrgHelper;
import com.justep.system.opm.api.Person;
import com.justep.system.opm.api.PersonHelper;
import com.justep.system.process.ExpressEngine;
import com.justep.system.util.CommonUtils;
import com.justep.util.Utils;

/**
 * 字典处理过程
 * 
 * @author Administrator
 * 
 */
public class CommonProcedure {

	public static void main(String[] agrs) {
		List<String> s = new ArrayList<String>();
		s.addAll(Arrays.asList(new String[] { "abc" }));
		Object[] o = s.toArray();
		System.out.println(o.length);
		String filter = "V_TMIS_A.f1 = :p1 and V_TMIS_A.f2=:p2";
		System.out.println(fixKSQLFilter(filter, "V_TMIS_A", Arrays.asList("f1", "f2"), Arrays.asList("TB1.f1", "TB2.f2")));
	}

	public static final String DATA_MODEL = "/base/system/data";
	public static final HashSet<String> BaseDataTypes = new HashSet<String>();
	static {
		BaseDataTypes.addAll(Arrays.asList(new String[] { "String", "Integer", "Float", "Double", "Date", "Time", "DateTime", "Decimal", "Text", "Object", "Blob" }));
	}

	/**
	 * 保存概念，对于bizSaveProcedure的扩展，增加一个variants参数，允许保存时传递其他数据。例如通用编码的节点值。
	 * 
	 * @param table
	 * @param concept
	 * @param insertRange
	 * @param deleteRange
	 * @param updateRange
	 * @param readOnly
	 * @param notNull
	 * @param dataModel
	 * @param fnModel
	 * @param variants
	 * @return
	 */
	public static int save(Table table, String concept, List<DataPermission> insertRange, List<DataPermission> deleteRange, List<DataPermission> updateRange, String readOnly, String notNull,
			String dataModel, String fnModel, Map<String, Object> variants) {
		int ret = BizData.save(table, concept, insertRange, deleteRange, updateRange, readOnly, notNull, dataModel, fnModel);
		TableImpl proxy = new TableImpl(table);
		ConceptSaveInfo conceptSaveInfo = proxy.normalizeSaveInfo(proxy.extractSaveColumn(), ModelUtils.getModel(dataModel)).get(0);
		Action action = ContextHelper.getActionContext().getAction();
		BizInfo bizInfo = CacheManager.getBizInfo(action);
		Iterator<Row> itor_row = table.iterator(new ModifyState[] { ModifyState.DELETE });
		while (itor_row.hasNext()) {
			Row r = itor_row.next();
			cascade(action.getModel(), bizInfo, (String) table.getProperties().get(Table.PROP_NAME_ROWID), r, conceptSaveInfo.getColumnName());
		}
		return ret;
	}

	private static void cascade(Model model, BizInfo bizInfo, String masterTable, Row masterRow, String masterKeyColumn) {
		Map<String, Object> varMap = new HashMap<String, Object>();
		varMap.put("fkValue", masterRow.getOldValue(masterKeyColumn));
		Iterator<TableInfo> itor = bizInfo.getTableInfos().iterator();
		while (itor.hasNext()) {
			TableInfo tableInfo = itor.next();
			if (Utils.isEmptyString(tableInfo.getSaveAction()))
				continue;
			if (masterTable.equals(tableInfo.getMasterTable())) {
				Action saveAction = (Action) model.getUseableObject(tableInfo.getSaveAction(), Action.TYPE);
				Model dataModel = ModelUtils.getModel((String) saveAction.getPrivateParam("dataModel").getValue());
				if ("delete".equals(tableInfo.getCascade())) {
					String keySelect = "select " + tableInfo.getName() + " from " + tableInfo.getConcept() + " " + tableInfo.getName() + " where " + tableInfo.getName() + "."
							+ tableInfo.getForeignKeys() + "=:fkValue";
					deleteCascade(model, bizInfo, tableInfo.getName(), keySelect, varMap);
					String deleteKSQL = "delete from " + tableInfo.getConcept() + " " + tableInfo.getName() + " where " + tableInfo.getName() + "." + tableInfo.getForeignKeys() + "=:fkValue";
					KSQL.executeUpdate(deleteKSQL, varMap, dataModel, null);
				} else if ("setNull".equals(tableInfo.getCascade())) {
					String ksql = "update " + tableInfo.getConcept() + " " + tableInfo.getName() + " set " + tableInfo.getName() + "." + tableInfo.getForeignKeys() + "=null where "
							+ tableInfo.getName() + "." + tableInfo.getForeignKeys() + "=:fkValue";
					KSQL.executeUpdate(ksql, varMap, dataModel, null);
				}
			}
		}
	}

	private static void deleteCascade(Model model, BizInfo bizInfo, String masterTable, String masterKeySelect, Map<String, Object> varMap) {
		Iterator<TableInfo> itor = bizInfo.getTableInfos().iterator();
		while (itor.hasNext()) {
			TableInfo tableInfo = itor.next();
			if (Utils.isEmptyString(tableInfo.getSaveAction()))
				continue;
			if (masterTable.equals(tableInfo.getMasterTable())) {
				Action saveAction = (Action) model.getUseableObject(tableInfo.getSaveAction(), Action.TYPE);
				Model dataModel = ModelUtils.getModel((String) saveAction.getPrivateParam("dataModel").getValue());
				if ("delete".equals(tableInfo.getCascade())) {
					String keySelect = "select " + tableInfo.getName() + " from " + tableInfo.getConcept() + " " + tableInfo.getName() + " where " + tableInfo.getName() + "."
							+ tableInfo.getForeignKeys() + " in ( " + masterKeySelect + ")";
					deleteCascade(model, bizInfo, tableInfo.getName(), keySelect, varMap);
					String ksql = "delete from " + tableInfo.getConcept() + " " + tableInfo.getName() + " where " + tableInfo.getName() + "." + tableInfo.getForeignKeys() + " in (" + masterKeySelect
							+ ")";
					KSQL.executeUpdate(ksql, varMap, dataModel, null);
				}
			}
		}
	}

	/**
	 * 保存概念，对于bizQueryProcedure的扩展，用于自定义SQL查询。
	 * 
	 * @param conceptName
	 * @param idColumn
	 * @param select
	 * @param from
	 * @param condition
	 * @param range
	 * @param filter
	 * @param distinct
	 * @param offset
	 * @param limit
	 * @param columns
	 * @param orderBy
	 * @param aggregate
	 * @param aggregateColumns
	 * @param variables
	 * @param dataModel
	 * @param fnModel
	 * @return
	 */
	public static Table query(String conceptName, String idColumn, String select, String from, String condition, List<DataPermission> range, String filter, Boolean distinct, int offset, int limit,
			String columns, String orderBy, String aggregate, String aggregateColumns, Map<String, Object> variables, String dataModel, String fnModel) {
		Map<String, Object> sqlParams = new HashMap<String, Object>();
		// TODO tangkejie 2017-05-08 屏蔽，实现不合理 !!! SQL KSQL 均支持函数，增加这个做什么?
		// 其次，这种参数加载应在登陆时完成。
		 sqlParams.putAll(SystemParams.getSysParams());//暂且放开
		if (variables != null)
			sqlParams.putAll(variables);

		if (fnModel == null)
			fnModel = "/base/core/logic/fn";
		Model model = ModelUtils.getModel(dataModel);
		Concept concept = model.getUseableConcept(conceptName);
		TableConfig config = CacheManager.getConceptTableConfig(concept);
		// 计算参数表达式
		for (Parameter param : config.getParameters()) {
			if (Utils.isNotEmptyString(param.getExpr()) && !sqlParams.containsKey(param.getName())) {
				sqlParams.put(param.getName(), ExpressEngine.calculate(param.getExpr(), null, ModelUtils.getModel(fnModel)));
			}
		}

		// 字段解析
		String realColumns = "";
		boolean allColumn = Utils.isEmptyString(columns) || columns.contains("*");
		List<String> columnArray = new ArrayList<String>();
		if (!allColumn)
			columnArray = Arrays.asList(columns.split(","));

		List<String> names = new ArrayList<String>();
		List<String> types = new ArrayList<String>();
		for (Relation r : concept.getRelations()) {
			if (columnArray.contains(r.getName()) || allColumn) {
				String dataType = r.getDataType();
				if (config.isQuery() && !BaseDataTypes.contains(dataType))
					dataType = "String";
				realColumns += r.getName() + ",";
				names.add(r.getName());
				types.add(dataType);
			}
		}
		if (realColumns.length() > 0)
			realColumns = realColumns.substring(0, realColumns.length() - 1);

		String keyColumns = "";
		for (Relation r : concept.getKeyRelations()) {
			keyColumns += r.getName() + ":";
		}
		if (keyColumns.length() > 0)
			keyColumns = keyColumns.substring(0, keyColumns.length() - 1);

		Table table = null;
		if (config.isQuery()) {
			table = TableUtils.createTable(model, names, types);
			if (!"".equals(keyColumns)) {
				// TODO 未处理联合主键的情况，确认后处理
				table.getMetaData().setKeyColumn(keyColumns);
				table.getProperties().put(Table.PROP_NAME_ROWID, keyColumns);
			}

			String sql = "select " + (distinct ? "distinct " : "") + (realColumns.length() > 0 ? realColumns : "*") + " from (" + config.getSelect() + ") " + conceptName;
			String where = SQLUtils.appendCondition(condition, "and", filter);
			if (Utils.isNotEmptyString(where)) {
				sql += " where " + where;
			}
			// 处理日期过滤函数
			// sql = sql.replace("stringToDate('AAAA')", "to_Date");
			// TODD 处理日期过滤函数 暂时先这么处理,后面优化 to_data('','')
			int startIdx = -1;
			while ((startIdx = sql.indexOf("stringToDate(")) >= 0) {
				String str = sql.substring(startIdx, sql.indexOf(")", startIdx) + 1);
				String newStr = str.replace("stringToDate", "to_date").replace(")", ",'yyyy-mm-dd')");
				sql = sql.replace(str, newStr);
			}

			List<Object> paramList = SQLUtils.parseSqlParameters(sql, sqlParams);
			String fixSql = SQLUtils.fixSQL(sql, sqlParams, true);
			// JDBC带参执行和实际SQL执行有差异的情况下，特殊处理 (唐科杰
			// 注：不建议这种处理方式，cursor_sharing如果不设置为force，不同条件值SQL都将硬解析，且执行计划不可控。force所有谓词的常量自动转换为:sysN处理)
			if (paramList.size() > 0 && "true".equals(variables.get("replaceParam"))) {
				for (Object param : paramList) {
					if (param == null)
						fixSql = fixSql.replaceFirst("\\?", "null");
					else if (param instanceof String)
						fixSql = fixSql.replaceFirst("\\?", "'" + param.toString() + "'");
					else if (param instanceof Date) {
						param = "to_date('" + (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format((Date) param) + "','yyyy-MM-DD HH24:MI:SS')";
						fixSql = fixSql.replaceFirst("\\?", "'" + param.toString() + "'");
					} else
						fixSql = fixSql.replaceFirst("\\?", param.toString());
				}
				paramList.clear();
			}
			table = SQLUtils.select(fixSql, paramList, model, offset, limit, table);
		} else {

			List<String> fieldAlias = Utils.isEmptyString(config.getFieldAlias()) ? new ArrayList<String>() : Arrays.asList(config.getFieldAlias().split(","));
			List<String> fieldNames = Utils.isEmptyString(config.getFieldNames()) ? new ArrayList<String>() : Arrays.asList(config.getFieldNames().split(","));
			if (Utils.isEmptyString(orderBy)) {
				// 默认排序
				if (distinct)
					orderBy = realColumns;
				else
					orderBy = config.getOrderBy();
			} else {
				// 前台的请求排序请求
				String[] orders = orderBy.split(",");
				orderBy = "";

				for (String order : orders) {
					String[] args = order.trim().split("[.,\\s]");
					String fieldName = args[1];
					if (fieldAlias.contains(fieldName)) {
						String asc = args[2];
						fieldName = fieldNames.get(fieldAlias.indexOf(fieldName));
						orderBy += fieldName + " " + asc + ",";
					}
				}
				if (orderBy.length() > 0)
					orderBy = orderBy.substring(0, orderBy.length() - 1);
			}
			condition = SQLUtils.appendCondition(condition, "and", config.getCondition());
			String ksqlSelect = config.getSelect();
			if (!("," + ksqlSelect + ",").contains("," + idColumn + ",")) {
				ksqlSelect = idColumn + "," + ksqlSelect;
			}
			if (realColumns.length() > 0 && !("," + realColumns + ",").contains("," + idColumn + ",")) {
				realColumns = idColumn + "," + realColumns;
			}

			// filter 中可能包含视图名称，需要替换为真是的fieldName
			filter = fixKSQLFilter(filter, conceptName, fieldAlias, fieldNames);

			table = BizData.query(conceptName, idColumn, SQLUtils.fixSQL(ksqlSelect, sqlParams, false), SQLUtils.fixSQL(config.getFrom(), sqlParams, false),
					SQLUtils.fixSQL(condition, sqlParams, false), range, SQLUtils.fixSQL(filter, sqlParams, false), distinct, offset, limit, realColumns, orderBy, aggregate, aggregateColumns,
					sqlParams, dataModel, fnModel);

			String keyColumn = BizDataUtils.getTableKeyColumns(idColumn, table);
			if (Utils.isNotEmptyString(keyColumn)) {
				table.getMetaData().setKeyColumn(keyColumn);
			}
		}
		return table;
	}

	/**
	 * 将KSQL的filter中的视图名.字段名，根据[别名]-[真实表名.字段名] 进行替换
	 * 
	 * @param filter
	 * @param tableName
	 * @param fieldAlias
	 * @param fieldNames
	 * @return
	 */
	private static String fixKSQLFilter(String filter, String tableName, List<String> fieldAlias, List<String> fieldNames) {
		if (Utils.isEmptyString(filter))
			return filter;
		Pattern p = Pattern.compile(tableName + "\\.([\\w\u4e00-\u9fa5]+)\\b");
		Matcher m = p.matcher(filter);
		while (m.find()) {
			String alias = m.group();
			alias = alias.substring(alias.indexOf(".") + 1);
			filter = filter.replaceAll(m.group(), fieldNames.get(fieldAlias.indexOf(alias)));
		}
		return filter;
	}

	/**
	 * 获得指定字典类型的字典列表
	 * 
	 * @param dictType
	 * @return
	 */
	public static Table getDictTableByDictType(String dictType) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pType", dictType);
		return KSQL.select("select SysDictItem.FCODE as FCODE,SysDictItem.FNAME as FNAME from SysDictItem SysDictItem where SysDictItem.FTYPE  = :pType order by SysDictItem.FDISPORDER asc", params,
				DATA_MODEL, null);
	}

	/**
	 * 获得指定字典类型的字典列表
	 * 
	 * @param dictType
	 * @return 返回list
	 */
	public static List<String> getDictListByDictType(String dictType) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pType", dictType);
		Table table = KSQL.select("select SysDictItem.FCODE as FCODE,SysDictItem.FNAME as FNAME from SysDictItem SysDictItem where SysDictItem.FTYPE  = :pType order by SysDictItem.FDISPORDER asc",
				params, DATA_MODEL, null);

		List<String> list = new ArrayList<String>();

		Iterator<Row> row = table.iterator();
		while (row.hasNext()) {
			Row r = row.next();
			list.add(r.getString("FCODE") + ":" + r.getString("FNAME"));
		}
		return list;

	}

	/**
	 * 获得指定字典类型的字典列表
	 * 
	 * @param dictType
	 * @return
	 */
	public static Table querySysDictItemByType(String concept, String idColumn, String select, String from, String condition, List<DataPermission> range, String filter, Boolean distinct, int offset,
			int limit, String columns, String orderBy, String aggregate, String aggregateColumns, Map<String, Object> variables, String dataModel, String fnModel) {
		String dictType = (String) variables.get("dictType");
		if (Utils.isNotEmptyString(dictType))
			if (Utils.isNotEmptyString(filter)) {
				filter = "(" + filter + ") and (SysDictItem.FTYPE='" + dictType + "')";
			} else {
				filter = "(  SysDictItem.FTYPE='" + dictType + "')";
			}

		return BizData.query(concept, idColumn, select, from, condition, range, filter, distinct, offset, limit, columns, orderBy, aggregate, aggregateColumns, variables, dataModel, fnModel);
	}

	/**
	 * 单位名称of unitType
	 * 
	 * @param unitType
	 * @return
	 */
	public static List<String> getUnitsByType(String unitType) {
		/** KSQL */
		List<String> list = new ArrayList<String>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("uType", unitType);
		Table table = KSQL.select("select B_UnitType.fUnitName from B_UnitType B_UnitType where B_UnitType.fUnitType = :uType", params, DATA_MODEL, null);
		Iterator<Row> row = table.iterator();
		while (row.hasNext()) {
			Row r = row.next();
			list.add(r.getString("fUnitName"));
		}
		return list;
	}

	/**
	 * 单位类型 distinct unitType
	 * 
	 * @return
	 */
	public static List<String> getUnitType() {
		/** KSQL */
		List<String> list = new ArrayList<String>();
		Table table = KSQL.select("select distinct B_UnitType.fUnitType from B_UnitType B_UnitType", null, DATA_MODEL, null);
		Iterator<Row> row = table.iterator();
		while (row.hasNext()) {
			Row r = row.next();
			list.add(r.getString("fUnitType"));
		}
		return list;
	}

	/**
	 * 执行表达式
	 * 
	 * @param materialGroup
	 * @return
	 */
	private static boolean calcBooleanExpr(ProcessLogicPluginContext context, String boolExpr, Map<String, Object> variables, Model fnModel) {
		if (Utils.isEmptyString(boolExpr)) {
			return true;
		}
		Set<String> relTables = BizExprHelper.parseObjectIdOfTableFunction(boolExpr);
		for (String tableName : relTables) {
			context.loadBizTable(tableName);
		}
		return ExpressEngine.calculateBoolean(boolExpr, variables, true, fnModel);
	}

	private static List<OrgUnit> calcOrgExpr(ProcessLogicPluginContext context, String orgExpr, Map<String, Object> variables, Model fnModel) {
		if (Utils.isEmptyString(orgExpr)) {
			return new ArrayList<OrgUnit>();
		}
		Set<String> relTables = BizExprHelper.parseObjectIdOfTableFunction(orgExpr);
		for (String tableName : relTables) {
			context.loadBizTable(tableName);
		}
		@SuppressWarnings("unchecked")
		List<OrgUnit> ret = (List<OrgUnit>) ExpressEngine.calculate(orgExpr, variables, fnModel);
		return ret;
	}

	private static final String MAX_ORG_SEQUENCE = "ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ";

	/**
	 * 获取组织机构字段扩展属性信息
	 * 
	 * @param modelName
	 * @param conceptName
	 * @param relationName
	 * @return
	 */
	public static List<Map<String, Object>> getOrgSelectExtInfo(String conceptName, String relationName, String bizRecId, Map<String, Object> variants, Map<String, String> filters, String expr) {
		if (Utils.isNotEmptyString(expr)) {
			return getOrgSelectExtRange(bizRecId, variants, expr);
		}
		List<Map<String, Object>> ret = new ArrayList<Map<String, Object>>();
		com.justep.model.Process bizProcess = ContextHelper.getActionContext().getProcess();
		Activity bizActivity = ContextHelper.getActionContext().getActivity();
		Model ontologyModel = ModelUtils.getModel(ModelPathHelper.getBizPath(bizProcess) + "/ontology");
		Model fnModel = ModelUtils.getModel("/base/core/logic/fn");
		Concept concept = ontologyModel.getUseableConcept(conceptName);
		if (relationName.indexOf(".") > 0)
			relationName = relationName.substring(relationName.indexOf(".") + 1);
		String orgFieldProp = (String) concept.getRelation(relationName).getExtAttributeValue(ModelExtUtils.MODEL_EXT_URI, "orgSelectExtInfo");
		if (Utils.isEmptyString(orgFieldProp)) {
			new BusinessException("机构字段" + conceptName + "." + relationName + "未设置业务属性").printStackTrace();
			return ret;
		}
		ProcessLogicPluginContext context = ProcessLogicPluginContext.findLogicPluginContext(bizActivity, bizRecId);
		boolean releaseContext = context == null;
		if (releaseContext)
			context = ProcessLogicPluginContext.createLogicPluginContext(bizProcess, bizActivity, bizRecId);
		try {
			JSONObject orgField = JSON.parseObject(orgFieldProp);
			String orgKinds = orgField.containsKey("orgKinds") ? ("," + orgField.getString("orgKinds") + ",") : "";
			JSONArray ranges = orgField.containsKey("ranges") ? orgField.getJSONArray("ranges") : new JSONArray();
			for (int i = 0; i < ranges.size(); i++) {
				JSONObject range = ranges.getJSONObject(i);
				String condition = range.containsKey("condition") ? range.getString("condition") : "";
				String expression = range.containsKey("expression") ? range.getString("expression") : "";
				// 计算条件表达式
				if (calcBooleanExpr(context, condition, variants, fnModel)) {
					List<OrgUnit> orgs = calcOrgExpr(context, expression, variants, fnModel);
					for (OrgUnit orgUnit : orgs) {
						String orgKind = orgUnit.getFID().substring(orgUnit.getFID().lastIndexOf(".") + 1);
						boolean kindMatch = Utils.isEmptyString(orgKinds) || orgKinds.contains("," + orgKind + ",");
						if (kindMatch) {
							Map<String, Object> orgMap = new HashMap<String, Object>();
							orgMap.put("@@tag", "org-unit");
							orgMap.put("fid", orgUnit.getFID());
							orgMap.put("fname", orgUnit.getFName());
							orgMap.put("name", OrgUtils.getNameByFName(orgUnit.getFID(), orgUnit.getFName(), orgKind));
							String str = orgUnit.getAttributeValue("responsible") + "";
							if (str.trim().equals("true"))
								str = "true";
							else
								str = "false";
							orgMap.put("responsible", str);
							// TODO 排序全局顺序，先这样处理，后面修改OrgUtils的方法
							if (OrgKinds.isPersonMember(orgKind)) {
								Person p = PersonHelper.loadPerson(OrgUtils.getPersonIDByFID(orgUnit.getFID()), Arrays.asList("sGlobalSequence"));
								orgMap.put("sequence", p.getExtValue("sGlobalSequence"));
							} else {
								Org org = OrgHelper.loadOrg(OrgUtils.getOrgID(orgUnit.getFID()), null);
								orgMap.put("sequence", org.getSequence());
							}
							ret.add(orgMap);
						}
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
								return key1.compareToIgnoreCase(key2);
							}
						});
						return Arrays.asList(arr);
					} else {
						return ret;
					}

				}
			}
		} finally {
			if (releaseContext)
				ProcessLogicPluginContext.removeLogicPluginContext(context, false);
		}
		return ret;
	}

	private static String upperCaseFirstLetter(String name) {
		return name.substring(0, 1).toUpperCase() + name.substring(1);
	}

	public static List<Map<String, Object>> getOrgSelectExtRange(String bizRecId, Map<String, Object> variants, String expression) {
		List<Map<String, Object>> ret = new ArrayList<Map<String, Object>>();
		com.justep.model.Process bizProcess = ContextHelper.getActionContext().getProcess();
		Activity bizActivity = ContextHelper.getActionContext().getActivity();
		Model fnModel = ModelUtils.getModel("/base/core/logic/fn");
		ProcessLogicPluginContext context = ProcessLogicPluginContext.findLogicPluginContext(bizActivity, bizRecId);
		boolean releaseContext = context == null;
		if (releaseContext)
			context = ProcessLogicPluginContext.createLogicPluginContext(bizProcess, bizActivity, bizRecId);

		List<OrgUnit> orgs = calcOrgExpr(context, expression, variants, fnModel);
		for (OrgUnit orgUnit : orgs) {
			boolean kindMatch = true;
			if (kindMatch) {
				Map<String, Object> orgMap = new HashMap<String, Object>();
				orgMap.put("@@tag", "org-unit");
				orgMap.put("fid", orgUnit.getFID());
				orgMap.put("fname", orgUnit.getFName());
				String str = orgUnit.getAttributeValue("responsible") + "";
				if (str.trim().equals("true"))
					str = "true";
				else
					str = "false";
				orgMap.put("responsible", str);
				String orgKind = orgUnit.getFID().substring(orgUnit.getFID().lastIndexOf(".") + 1);
				// TODO 排序全局顺序，先这样处理，后面修改OrgUtils的方法
				if (OrgKinds.isPersonMember(orgKind)) {
					Person p = PersonHelper.loadPerson(OrgUtils.getPersonIDByFID(orgUnit.getFID()), Arrays.asList("sGlobalSequence"));
					orgMap.put("sequence", p.getExtValue("sGlobalSequence"));
				} else {
					Org org = OrgHelper.loadOrg(OrgUtils.getOrgID(orgUnit.getFID()), null);
					orgMap.put("sequence", org.getSequence());
				}
				ret.add(orgMap);
			}
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
	 * 如果使用图片签名，记录签名使用情况
	 */
	public static void checkSignDataBeforeSaveHandlerProcedure() {
		ActionContext ac = ContextHelper.getActionContext();
		String signDataFields = ModelExtUtils.getActionSignDataFields(ac.getAction());
		if (Utils.isEmptyString(signDataFields))
			return;
		String tableName = (String) ac.getParameter("concept");
		Table table = (Table) ac.getParameter("table");
		String[] flds = signDataFields.split(",");
		Model dataModel = ModelUtils.getModel("/base/personInfo/data");
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("tableName", tableName);
		if (table.size() > 0) {
			Iterator<Row> i = table.iterator();
			while (i.hasNext()) {
				Row r = i.next();
				String rowid = r.getString(tableName);
				variables.put("rowid", rowid);
				if (ModifyState.DELETE.equals(r.getState())) {
					// 删除本表、本行的所有签名字段日志
					KSQL.executeUpdate("DELETE FROM B_SignImageUseLog B WHERE B.fTableName=:tableName and B.fRowID=:rowid", variables, dataModel, null);
				} else {
					for (String fieldName : flds) {
						if (Utils.isEmptyString(fieldName))
							continue;
						String newValue = r.getString(fieldName);
						variables.put("fieldName", fieldName);
						if (Utils.isNotEmptyString(newValue)
								&& (ModifyState.NEW.equals(r.getState()) || ModifyState.EDIT.equals(r.getState()) && !StringUtils.equals(newValue, (String) r.getOldValue(fieldName)))) {
							// 新增或者签名改变
							JSONObject signImage = JSON.parseObject(newValue).getJSONObject("signImage");
							String signImageID = signImage.getString("rowid");
							Table logTable = BizData.query("B_SignImageUseLog", "B", "B.*", "B_SignImageUseLog B", "B.fTableName=:tableName and B.fFieldName=:fieldName and B.fRowID=:rowid", null,
									null, false, 0, -1, null, null, null, null, variables, "/base/personInfo/data", null);
							logTable.getMetaData().setKeyColumn("fGUID");
							if (logTable.size() == 0) {
								Row log = logTable.appendRow(CommonUtils.createShortGUID());
								log.setInt("version", 0);
								log.setString("fTableName", tableName);
								log.setString("fFieldName", fieldName);
								log.setString("fRowID", rowid);
								log.setString("fSignImageID", signImageID);
							} else {
								Row log = logTable.iterator().next();
								log.setString("fSignImageID", signImageID);
							}
							logTable.save("/base/personInfo/data");
						} else if (ModifyState.EDIT.equals(r.getState()) && Utils.isEmptyString(newValue) && Utils.isNotEmptyString((String) r.getOldValue(fieldName))) {
							// 删除本表、本行、本字段签名记录
							KSQL.executeUpdate("DELETE FROM B_SignImageUseLog B WHERE B.fTableName=:tableName and B.fFieldName=:fieldName and B.fRowID=:rowid", variables, dataModel, null);
						}
					}
				}
			}
		}

	}

	/**
	 * 概念保存前监听处理过程
	 */
	public static void beforeSaveEventHandlerProcedure() {
		ActionContext ac = ContextHelper.getActionContext();
		Stack<ActionContext> stack = ContextHelper.getRequestContext().getActionContextStack();
		// 排除逻辑插件的2次保存
		for (int i = stack.size() - 2; i >= 0; i--) {
			if (ac.getAction().equals(stack.get(i).getAction()))
				return;
		}

		@SuppressWarnings("unchecked")
		Map<String, Object> variants = (Map<String, Object>) ac.getParameter("variants");
		if (variants == null)
			return;
		String thisTableName = (String) variants.get("tableName");
		if (thisTableName == null)
			return;
		String actionName = "save" + upperCaseFirstLetter(thisTableName) + "Action";
		Activity bizActivity = ac.getActivity();
		List<Listener> list = bizActivity.getListeners(actionName);
		String urls = null;
		if (list.size() > 0) {
			for (Listener listener : list) {
				if (listener.getHandler().getName().equals("beforeSaveEventHandlerProcedure")) {
					urls = ModelExtUtils.getListenerPluginUrls(listener);
					if (urls != null)
						break;
				}
			}
		}
		if (urls == null)
			return;
		String concept = (String) ac.getParameter("concept");
		String dataModel = (String) ac.getParameter("dataModel");
		String bizRecId = (String) variants.get("bizRecId");
		Table table = (Table) ac.getParameter("table");
		com.justep.model.Process process = ac.getProcess();
		ProcessLogicPluginContext context = ProcessLogicPluginContext.findLogicPluginContext(bizActivity, bizRecId);
		boolean releaseContext = context == null;
		if (releaseContext)
			context = ProcessLogicPluginContext.createLogicPluginContext(process, bizActivity, bizRecId);
		TableControlObject tableControlObject = null;
		try {
			tableControlObject = context.addTableControlObject(thisTableName, concept, dataModel, table);
			@SuppressWarnings("unchecked")
			Map<String, String> filters = (Map<String, String>) variants.get("filters");
			if (filters != null) {
				context.addBizDataFilters(filters);
			}
			BizInfo bizInfo = context.getBizInfo();
			String[] args = urls.split(",");
			for (String url : args) {
				TableLogicPluginEx pluginEx = bizInfo.getTableLogicPlugin(url);
				String relBizDatas = pluginEx.getTableLogicPlugin().getRelBizDatas();
				if (relBizDatas == null) {
					// TODO 兼容老的资源
					context.loadAllBizTable();
				} else {
					String[] tableNames = relBizDatas.split(",");
					for (String name : tableNames) {
						context.loadBizTable(name);
					}
				}
				context.execute(pluginEx.getCalcLogicConfig(), variants, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("执行保存后插件失败", e);
		} finally {
			context.removeTableControlObject(tableControlObject);
			if (releaseContext)
				ProcessLogicPluginContext.removeLogicPluginContext(context, true);
		}
	}

	/**
	 * 概念保存后监听处理过程，由于数据已经保存，需要合并保存状态
	 */
	public static void afterSaveEventHandlerProcedure() {
		ActionContext ac = ContextHelper.getActionContext();
		Stack<ActionContext> stack = ContextHelper.getRequestContext().getActionContextStack();
		// 排除逻辑插件的2次保存
		for (int i = stack.size() - 2; i >= 0; i--) {
			if (ac.getAction().equals(stack.get(i).getAction()))
				return;
		}
		@SuppressWarnings("unchecked")
		Map<String, Object> variants = (Map<String, Object>) ac.getParameter("variants");
		if (variants == null)
			return;
		String thisTableName = (String) variants.get("tableName");
		if (thisTableName == null)
			return;
		String actionName = "save" + upperCaseFirstLetter(thisTableName) + "Action";
		Activity bizActivity = ac.getActivity();
		List<Listener> list = bizActivity.getListeners(actionName);
		String urls = null;
		if (list.size() > 0) {
			for (Listener listener : list) {
				if (listener.getHandler().getName().equals("afterSaveEventHandlerProcedure")) {
					urls = ModelExtUtils.getListenerPluginUrls(listener);
					if (urls != null)
						break;
				}
			}
		}
		if (urls == null)
			return;
		String concept = (String) ac.getParameter("concept");
		String dataModel = (String) ac.getParameter("dataModel");
		String bizRecId = (String) variants.get("bizRecId");
		Table table = (Table) ac.getParameter("table");
		com.justep.model.Process process = ac.getProcess();
		// 合并保存后的状态
		BizDataUtils.mergeStateAfterSave(table);
		ProcessLogicPluginContext context = ProcessLogicPluginContext.findLogicPluginContext(bizActivity, bizRecId);
		boolean releaseContext = context == null;
		if (releaseContext)
			context = ProcessLogicPluginContext.createLogicPluginContext(process, bizActivity, bizRecId);
		try {
			context.addTableControlObject(thisTableName, concept, dataModel, table);
			@SuppressWarnings("unchecked")
			Map<String, String> filters = (Map<String, String>) variants.get("filters");
			if (filters != null) {
				context.addBizDataFilters(filters);
			}
			BizInfo bizInfo = context.getBizInfo();
			String[] args = urls.split(",");
			for (String url : args) {
				TableLogicPluginEx pluginEx = bizInfo.getTableLogicPlugin(url);
				String relBizDatas = pluginEx.getTableLogicPlugin().getRelBizDatas();
				if (relBizDatas == null) {
					// TODO 兼容老的资源
					context.loadAllBizTable();
				} else {
					String[] tableNames = relBizDatas.split(",");
					for (String name : tableNames) {
						context.loadBizTable(name);
					}
				}
				context.execute(pluginEx.getCalcLogicConfig(), variants, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("执行保存插件失败", e);
		} finally {
			if (releaseContext)
				ProcessLogicPluginContext.removeLogicPluginContext(context, true);
		}
	}

	/**
	 * 执行java函数
	 * 
	 * @param func
	 * @return
	 */
	public static Object executeJavaExpr(String expr, Map<String, Object> variables) {
		return Expression.evaluate(expr, variables, ModelUtils.getModel("/base/core/logic/fn"));
	}
}