import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import com.butone.extend.ArchivesQuery;
import com.butone.extend.BizInfo;
import com.butone.extend.CacheManager;
import com.butone.extend.ModelPathHelper;
import com.butone.extend.TableInfo;
import com.butone.extend.TaskQuery;
import com.butone.utils.ModelExtUtils;
import com.justep.client.DatabaseProduct;
import com.justep.model.Concept;
import com.justep.model.Config;
import com.justep.model.Model;
import com.justep.model.ModelUtils;
import com.justep.model.Relation;
import com.justep.system.action.ActionUtils;
import com.justep.system.context.ContextHelper;
import com.justep.system.data.BizData;
import com.justep.system.data.DataPermission;
import com.justep.system.data.KSQL;
import com.justep.system.data.ModifyState;
import com.justep.system.data.Row;
import com.justep.system.data.SQL;
import com.justep.system.data.Table;
import com.justep.util.Utils;

public class Archives {

	private final static String DATAMODEL = "/common/archives/data";

	public static Table queryArchives(String orderBy, Integer offset, Integer limit, Map variables, Map filterMap, List range) {
		if (range != null) {
			String result = "";
			for (Object item : range) {
				if (item == null)
					continue;
				if (result.equals("")) {
					result = item.toString();
				} else {
					result += " and " + item.toString();
				}
			}
			if (!result.equals("")) {
				result = "(" + result + ")";
				filterMap.put(ArchivesQuery.ARCHIVE_PermissionFilter, result);
			}
		}
		return ArchivesQuery.queryArchives(orderBy, offset, limit, variables, filterMap);
	}

	public static Table queryArchivesInfo(String concept, String idColumn, String select, String from, String condition, List<DataPermission> range, String filter, Boolean distinct, Integer offset,
			Integer limit, String columns, String orderBy, String aggregate, String aggregateColumns, Map<String, Object> variables, String dataModel, String fnModel) {
		return BizData.query(concept, idColumn, select, from, condition, range, filter, distinct, offset, limit, columns, orderBy, aggregate, aggregateColumns, variables, dataModel, fnModel);
	}

	public static List<Map<String, Object>> queryBizGroup(Map filterMap, Map variables) {
		return ArchivesQuery.queryBizGroup(filterMap, variables);
	}

	public static Table query_BusinessTables(String concept, String select, String from, String aggregate, String dataModel, String fnModel, String condition, List<Object> range, Boolean distinct,
			String idColumn, String filter, Integer limit, Integer offset, String columns, String orderBy, String aggregateColumns, Map<String, Object> variables) {
		String groupId=variables.get("groupId").toString();
		String queryType=variables.get("queryType").toString();
		System.out.println(queryType);
		//TODO 分两类字段定义类型（交集、并集）
		//交集
		if(queryType.equals("commonType")){
			return getCommonBusinessTables(groupId);
		}//并集
		else if(queryType.equals("unionType")){
			return getUnionBusinessTables(groupId);
		}else{
			throw new RuntimeException("未知的查询类型："+queryType);
		}
	}

	/**
	 * List去重
	 * 
	 * @param list
	 * @return
	 */
	public static List<String> removeDuplicate(List<String> list) {
		HashSet<String> h = new HashSet<String>(list);
		list.clear();
		list.addAll(h);
		return list;
	}

	/**
	 * 两个数组的交集
	 * 
	 * @param arr1
	 * @param arr2
	 * @return
	 */
	public static String[] intersect(String[] arr1, String[] arr2) {
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		LinkedList<String> list = new LinkedList<String>();
		for (String str : arr1) {
			if (!map.containsKey(str)) {
				map.put(str, Boolean.FALSE);
			}
		}
		for (String str : arr2) {
			if (map.containsKey(str)) {
				map.put(str, Boolean.TRUE);
			}
		}

		for (Entry<String, Boolean> e : map.entrySet()) {
			if (e.getValue().equals(Boolean.TRUE)) {
				list.add(e.getKey());
			}
		}

		String[] result = {};
		return list.toArray(result);
	}

	private static void addRow(Table table, String fBusinessGroupId, String fTableName, String fFieldName, String fField, String fFieldAlias, String fDataType) {
		Row rec = table.appendRow();
		rec.setString("fBusinessGroupId", fBusinessGroupId);
		rec.setString("fTableName", fTableName);
		rec.setString("fFieldName", fFieldName);
		rec.setString("fField", fField);
		rec.setString("fFieldAlias", fFieldAlias);
		rec.setInteger("fFieldOrder", 1);
		rec.setString("fDataType", fDataType);
		rec.setState(ModifyState.NONE);
	}

	private static String getFieldAlias(Table table, String fFieldAlias) {
		String ret = fFieldAlias;
		int n = 0;
		while (isExistFieldAlias(table, ret)) {
			ret = fFieldAlias + "_" + n;
			n++;
		}
		return ret;
	}

	private static boolean isExistFieldAlias(Table table, String fFieldAlias) {
		Iterator<Row> rows = table.iterator();
		while (rows.hasNext()) {
			Row row = rows.next();
			if (row.getString("fFieldAlias").equals(fFieldAlias)) {
				return true;
			}
		}
		return false;
	}

	public static void setTreeFucn(List<String> processes, List<String> activitiesFNames, String fBusinessGroupId) {
		for (int i = 0; i < processes.size(); i++) {
			String process = processes.get(i).toString();
			String activityFName = activitiesFNames.get(i).toString();
			String[] array = activityFName.split("/");

			Table funcTable = KSQL.select("SELECT p.*  FROM B_GroupFunc p where  1=2  ", null, DATAMODEL, null);
			funcTable.getMetaData().setStoreByConcept("B_GroupFunc", true);
			funcTable = BizData.create(funcTable, "B_GroupFunc", null, DATAMODEL);
			Row rec = funcTable.iterator().next();
			rec.setString("fBusinessGroupId", fBusinessGroupId);
			rec.setString("fFuncLongName", activityFName);
			rec.setString("fFuncName", array[array.length - 1]);
			rec.setString("fProcess", process);

			String tables;
			try {
				tables = getProcesTables(process);
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}

			if ("".equals(tables))
				continue;
			rec.setString("fFlowTables", tables);

			funcTable.save(DATAMODEL);
		}
	}

	private static String getProcesTables(String process) {
		BizInfo bizInfo = CacheManager.getBizInfoByProcess(process);
		StringBuffer sb = new StringBuffer();
		for (TableInfo tableInfo : bizInfo.getTableInfos()) {
			// 只显示主表信息，流程业务所有主表的外键字段都是fBizRecId
			if (Utils.isNotEmptyString(tableInfo.getForeignKeys()) && "fBizRecId".equals(tableInfo.getForeignKeys())) {
				sb.append(tableInfo.getConcept()).append(",");
			}
		}
		if (sb.length() > 0)
			return sb.substring(0, sb.length() - 1);
		else
			return "";
	}
	
	public static Table getBusinessTables(String groupId) {
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("groupId", groupId);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("limit", -1);
		params.put("offset", 0);
		params.put("variables", variables);
		params.put("filter", "fBusinessGroupId=:groupId  and fTableName<>'sys'");
		String executor = ActionUtils.getRequestContext().getActionContext().getExecutor();
		if (Utils.isEmptyString(executor))
			executor = "*";
		Table fieldtable = (Table) ActionUtils.invokeAction(ContextHelper.getActionContext().getProcess().getFullName(), ContextHelper.getActionContext().getActivity().getName(),
				"queryB_GroupFieldAction", executor, params);
		Table functable = KSQL.select("SELECT m.* FROM B_GroupFunc m where fBusinessGroupId=:groupId", variables, DATAMODEL, null);
		Iterator<Row> rows = functable.iterator();
		while (rows.hasNext()) {
			Row row = rows.next();
			String bizPath = ModelPathHelper.getProcessBizPath(row.getString("fProcess"));
			try {
				BizInfo bizInfo = CacheManager.getBizInfo(bizPath);
				Model model = ModelUtils.getModel(bizPath + "/data");
				for (TableInfo t : bizInfo.getTableInfos()) {
					if (Utils.isEmptyString(t.getMasterTable())) {
						Concept conceptTable = model.getUseableConcept(t.getConcept());
						if (model.getStore(t.getConcept()) == null) {
							continue;
						}
						for (Relation r : conceptTable.getRelations()) {
							String fFieldAlias = getFieldAlias(fieldtable, r.getName());
							addRow(fieldtable, groupId, t.getConcept(), r.getLabel("zh_CN"), r.getName(), fFieldAlias, r.getDataType());
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		fieldtable.getProperties().put(Table.PROP_NAME_ROWID, "B_GroupField");
		return fieldtable;

	}

	private static Map<String, String> getProcessInfo(String fBizRecId) {
		Map<String, String> map = new HashMap<String, String>();
		StringBuffer sql_info = new StringBuffer();
		sql_info.append("select SA_task.SPROCESS,SA_task.STYPENAME  from SA_task where sData1='" + fBizRecId + "'");
		map.put(DatabaseProduct.DEFAULT.name(), sql_info.toString());
		Table table = SQL.select(map, null, "/common/archives/data");
		Iterator<Row> rows = table.iterator();
		Row row = rows.next();
		map.put("process", row.getString("SPROCESS"));
		map.put("processName", row.getString("STYPENAME"));
		return map;
	}

	public static void reloadTaskCenterBizGroup(String groupId, String type) {
		if ("案卷中心".equals(type) || "移动案卷中心".equals(type))
			TaskQuery.initBizGroupDefine(groupId);
		else if ("案卷查询".equals(type))
			ArchivesQuery.initBizGroupDefine(groupId);
	}

	public static Table querySysGroupField(String concept, String select, String from, String aggregate, String dataModel, String fnModel, String condition, List range, Boolean distinct,
			String idColumn, String filter, Integer limit, Integer offset, String columns, String orderBy, String aggregateColumns, Map variables) {
		String type = (String) variables.get("type");
		String groupId = (String) variables.get("groupId");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("limit", -1);
		params.put("offset", 0);
		params.put("variables", variables);
		params.put("filter", "fBusinessGroupId=:groupId  and fTableName='sys'");
		String executor = ActionUtils.getRequestContext().getActionContext().getExecutor();
		if (Utils.isEmptyString(executor))
			executor = "*";
		Table fieldtable = (Table) ActionUtils.invokeAction(ContextHelper.getActionContext().getProcess().getFullName(), ContextHelper.getActionContext().getActivity().getName(),
				"queryB_GroupFieldAction", executor, params);
		Map<String, Map<String, Object>> sysFields = null;

		if ("案卷中心".equals(type) || "移动案卷中心".equals(type)) {
			sysFields = TaskQuery.getSysFields();
		} else if ("案卷查询".equals(type))
			sysFields = ArchivesQuery.getSysFields();
		if (sysFields != null) {
			for (Map<String, Object> field : sysFields.values()) {
				addRow(fieldtable, groupId, "sys", (String) field.get("label"), (String) field.get("alias"), (String) field.get("alias"), (String) field.get("dataType"));
			}
		}
		fieldtable.getProperties().put(Table.PROP_NAME_ROWID, "B_GroupField");
		return fieldtable;
	}

	public static String getArchiveQueryFuncUrl() {
		Config c = (Config) ModelUtils.getModel("/system/config").getLocalObject("arhiveQueryFuncUrl", Config.TYPE);
		return c != null ? c.getValue() : null;
	}
	
	/**
	 * 获取案卷分组下多个流程业务的表（包含字段）的并集
	 * @param groupId  案卷分组id
	 * @return
	 */
	public static Table getUnionBusinessTables(String groupId) {
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("groupId", groupId);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("limit", -1);
		params.put("offset", 0);
		params.put("variables", variables);
		params.put("filter", "fBusinessGroupId=:groupId  and fTableName<>'sys'");
		String executor = ActionUtils.getRequestContext().getActionContext().getExecutor();
		if (Utils.isEmptyString(executor))
			executor = "*";
		Table fieldtable = (Table) ActionUtils.invokeAction(ContextHelper.getActionContext().getProcess().getFullName(), ContextHelper.getActionContext().getActivity().getName(),
				"queryB_GroupFieldAction", executor, params);
		
		Map<String, Object> params2 = new HashMap<String, Object>();
		params2.put("limit", -1);
		params2.put("offset", 0);
		params2.put("variables", variables);
		params2.put("filter", "1<0");
		//空表结构
		Table fieldtable2 = (Table) ActionUtils.invokeAction(ContextHelper.getActionContext().getProcess().getFullName(), ContextHelper.getActionContext().getActivity().getName(),
				"queryB_GroupFieldAction", executor, params2);
		
		Table functable = KSQL.select("SELECT m.* FROM B_GroupFunc m where fBusinessGroupId=:groupId", variables, DATAMODEL, null);
		Iterator<Row> rows = functable.iterator();
		//
		List<String> conceptNameList=new ArrayList<String>();
		while (rows.hasNext()) {
			Row row = rows.next();
			String bizPath = ModelPathHelper.getProcessBizPath(row.getString("fProcess"));
			try {
				BizInfo bizInfo = CacheManager.getBizInfo(bizPath);
				Model model = ModelUtils.getModel(bizPath + "/data");
				for (TableInfo t : bizInfo.getTableInfos()) {
					if (Utils.isEmptyString(t.getMasterTable())) {
						Concept conceptTable = model.getUseableConcept(t.getConcept());
						//System.out.println(t.getConcept());
						if (model.getStore(t.getConcept()) == null) {
							continue;
						}
						//已添加的概念名不再重复添加
						if(conceptNameList.contains(t.getConcept())){
							continue;
						}
						//
						conceptNameList.add(t.getConcept());
						
						for (Relation r : conceptTable.getRelations()) {
							//字段若存在，则不再添加
							if(isExistField(fieldtable,r.getName(),t.getConcept())){
								continue;
							}
							String fFieldAlias = getFieldAlias(fieldtable2, r.getName());
							addRow(fieldtable2, groupId, t.getConcept(), r.getLabel("zh_CN"), r.getName(), fFieldAlias, r.getDataType());
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		fieldtable2.getProperties().put(Table.PROP_NAME_ROWID, "B_GroupField");
		return fieldtable2;

	}
	/**
	 * 获取案卷分组下多个流程业务的表（包含字段）的交集
	 * @param groupId  案卷分组id
	 * @return
	 */
	public static Table getCommonBusinessTables(String groupId) {
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("groupId", groupId);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("limit", -1);
		params.put("offset", 0);
		params.put("variables", variables);
		params.put("filter", "fBusinessGroupId=:groupId  and fTableName<>'sys'");
		String executor = ActionUtils.getRequestContext().getActionContext().getExecutor();
		if (Utils.isEmptyString(executor))
			executor = "*";
		Table fieldtable = (Table) ActionUtils.invokeAction(ContextHelper.getActionContext().getProcess().getFullName(), ContextHelper.getActionContext().getActivity().getName(),
				"queryB_GroupFieldAction", executor, params);
		
		Map<String, Object> params2 = new HashMap<String, Object>();
		params2.put("limit", -1);
		params2.put("offset", 0);
		params2.put("variables", variables);
		params2.put("filter", "1<0");
		//空表结构
		Table fieldtable2 = (Table) ActionUtils.invokeAction(ContextHelper.getActionContext().getProcess().getFullName(), ContextHelper.getActionContext().getActivity().getName(),
				"queryB_GroupFieldAction", executor, params2);
		
		Table functable = KSQL.select("SELECT m.* FROM B_GroupFunc m where fBusinessGroupId=:groupId", variables, DATAMODEL, null);
		Iterator<Row> rows = functable.iterator();
		//map<流程path，List<概念名>>
		Map<String,List<String>> mapFlowConceptTableList=new HashMap<String,List<String>>();
		//因为是取交集，所以其中任意一个流程都可以
		String firstProcess=null;
		while (rows.hasNext()) {
			Row row = rows.next();
			String bizPath = ModelPathHelper.getProcessBizPath(row.getString("fProcess"));
			try {
				if(firstProcess==null){
					firstProcess=bizPath;
				}
				//当前记录流程下的主表概念集合
				List<String> conceptTableList=new ArrayList<String>();
				
				BizInfo bizInfo = CacheManager.getBizInfo(bizPath);
				
				for (TableInfo t : bizInfo.getTableInfos()) {
					if (Utils.isEmptyString(t.getMasterTable())) {
						//表去重
						if(!conceptTableList.contains(t.getName())){
							conceptTableList.add(t.getName());
						}
					}
				}
				mapFlowConceptTableList.put(row.getString("fProcess"), conceptTableList);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//不同流程中交集表概念定义
		String[] tableArray =getSameConcept(mapFlowConceptTableList);
		
		Model model = ModelUtils.getModel(firstProcess + "/ontology");
		for (String conceptName : tableArray) {
			Concept conceptTable = model.getUseableConcept(conceptName);
			if (conceptTable == null)
				continue;
			String fTableName = ModelExtUtils.getTableName(conceptTable);
			for (Relation r : conceptTable.getRelations()) {
				//字段若存在，则不再添加
				if(isExistField(fieldtable,r.getName(),conceptName)){
					continue;
				}
				
				String fFieldAlias = getFieldAlias(fieldtable2, r.getName());
				addRow(fieldtable2, groupId, fTableName, r.getLabel("zh_CN"), r.getName(), fFieldAlias, r.getDataType());
			}
		}
		fieldtable2.getProperties().put(Table.PROP_NAME_ROWID, "B_GroupField");
		return fieldtable2;

	}
	
	/**
	 * 获取不同流程中交集表概念定义
	 * @param mapFlowConceptTableList  map<流程path，List<概念名>>
	 * @return
	 */
	private static String[] getSameConcept(Map<String,List<String>> mapFlowConceptTableList) {
		List<String> listTab = new ArrayList<String>();
		String[] concept_array = null,temp_array=null;
		for(String key:mapFlowConceptTableList.keySet()){
			List<String> conceptList=mapFlowConceptTableList.get(key);
			concept_array=(String[])conceptList.toArray(new String[conceptList.size()]);
			if(temp_array==null)
				temp_array = concept_array;
			temp_array = intersect(temp_array,concept_array);
		}
		listTab= Arrays.asList(temp_array);
		return (String[]) listTab.toArray(new String[listTab.size()]);

	}
	/**
	 * 判断table中是否存在概念名与字段名两字段为指定值的行
	 * @param table   表对象 
	 * @param fField  字段名
	 * @param tableName  概念名
	 * @return
	 */
	private static boolean isExistField(Table table, String fField,String tableName) {
		Iterator<Row> rows = table.iterator();
		while (rows.hasNext()) {
			Row row = rows.next();
			if (row.getString("fField").equals(fField) && row.getString("fTableName").equals(tableName)) {
				return true;
			}
		}
		return false;
	}
	
}