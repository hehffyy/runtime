import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.butone.data.SQLUtils;
import com.butone.extend.BizInfo;
import com.butone.extend.CacheManager;
import com.butone.logic.impl.BizExprHelper;
import com.butone.logic.impl.ProcessLogicPluginContext;
import com.butone.model.utils.ValueHelper;
import com.butone.utils.SysUtils;
import com.butone.utils.UUIDMaker;
import com.butone.x5.extend.BizMaterial;
import com.butone.x5.extend.MaterialGroup;
import com.justep.model.ModelUtils;
import com.justep.system.context.ActionContext;
import com.justep.system.context.ContextHelper;
import com.justep.system.data.BizData;
import com.justep.system.data.DataPermission;
import com.justep.system.data.DatabaseProduct;
import com.justep.system.data.Expression;
import com.justep.system.data.KSQL;
import com.justep.system.data.ModifyState;
import com.justep.system.data.Row;
import com.justep.system.data.SQL;
import com.justep.system.data.Table;
import com.justep.system.data.TableUtils;
import com.justep.system.data.UpdateMode;
import com.justep.system.util.CommonUtils;
import com.justep.util.Utils;

public class Material {
	private final static String FLOWFILE_DATAMODEL = "/base/core/material/data";
	private final static String BASE_FNMODEL = "/base/core/logic/fn";
	private static HashMap<String, Table> tableMap = new HashMap<String, Table>();

	/**
	 * 获得当前案卷的必要材料
	 */
	public static Table getNeedMaterials(String concept, String select, String from, String aggregate, String dataModel, String fnModel,
			String condition, List<Object> range, Boolean distinct, String idColumn, String filter, Integer limit, Integer offset, String columns,
			String orderBy, String aggregateColumns, Map<String, Object> variables) {
		String fBizRecId = variables.get("fBizRecId").toString();
		String materialTemplate = variables.get("materialTemplate") == null ? "default" : variables.get("materialTemplate").toString();
		String userProcessId_choiced = variables.get("userProcessId_choiced") == null ? "" : variables.get("userProcessId_choiced").toString();
		String userProcessName_choiced = variables.get("userProcessName_choiced") == null ? "" : variables.get("userProcessName_choiced").toString();

		Table tab = hasUpLoadNeedMaterial(fBizRecId);
		if (materialTemplate.equals("userCustom")) {
			//从用户自定义要件分组中加载要件数据
			loadDefaultMaterial(fBizRecId, tab, userProcessId_choiced, userProcessName_choiced);
			return sortTable(tab);
		}

		//		boolean isHasRecord = false;
		//		if (tab.size() > 0)
		//			isHasRecord = true;
		BizInfo bizInfo = CacheManager.getBizInfoByProcess(ContextHelper.getActionContext().getProcess().getFullName());
		MaterialGroup materialGroup = bizInfo.getMaterialGroup();

		ActionContext actionContext = ContextHelper.getActionContext();
		ProcessLogicPluginContext context = ProcessLogicPluginContext.findLogicPluginContext(actionContext.getActivity(), fBizRecId);
		boolean releaseContext = context == null;
		if (releaseContext)
			context = ProcessLogicPluginContext.createLogicPluginContext(actionContext.getProcess(), actionContext.getActivity(), fBizRecId);
		try {
			if (materialGroup != null) {
				int i = 0;
				if (excuteExpression(context, materialGroup.getCondition(), variables, false)) {
					addTreeNode(tab, materialGroup, fBizRecId/*, isHasRecord*/);
					addListChirlds(context, materialGroup.getSubGroups(), i, tab, fBizRecId, variables, /*isHasRecord,*/false);
				}
			}

			return sortTable(tab);
		} finally {
			if (releaseContext)
				ProcessLogicPluginContext.removeLogicPluginContext(context, false);
		}
	}

	/**
	 * 获得当前案卷的必要材料树，即级联案卷编号
	 */
	public static Table getNeedTreeMaterials(String concept, String select, String from, String aggregate, String dataModel, String fnModel,
			String condition, List<Object> range, Boolean distinct, String idColumn, String filter, Integer limit, Integer offset, String columns,
			String orderBy, String aggregateColumns, Map<String, Object> variables) {
		String fBizRecId = (String) variables.get("fBizRecId");
		String relBizRecIds = (String) variables.get("relBizRecIds");
		String materialTemplate = variables.containsKey("materialTemplate") ? (String) variables.get("materialTemplate") : "default";
		String userProcessId_choiced = variables.containsKey("userProcessId_choiced") ? (String) variables.get("userProcessId_choiced") : "";
		String userProcessName_choiced = variables.containsKey("userProcessName_choiced") ? (String) variables.get("userProcessName_choiced") : "";

		List<String> bizrecIds = new ArrayList<String>();
		bizrecIds.add(fBizRecId);
		if (relBizRecIds != null) {
			bizrecIds.addAll(Arrays.asList(relBizRecIds.split(",")));
		}
		// 加载主案卷和关联案卷的已上传的材料
		Table tab = hasUpLoadNeedMaterial(bizrecIds.toArray(new String[0]));
		if (materialTemplate.equals("userCustom")) {
			//从用户自定义要件分组中加载要件数据
			loadDefaultMaterial(fBizRecId, tab, userProcessId_choiced, userProcessName_choiced);
			return sortTable(tab);
		}

		//		boolean isHasRecord = false;
		//		if (tab.size() > 0)
		//			isHasRecord = true;
		BizInfo bizInfo = CacheManager.getBizInfoByProcess(ContextHelper.getActionContext().getProcess().getFullName());
		MaterialGroup materialGroup = bizInfo.getMaterialGroup();

		ActionContext actionContext = ContextHelper.getActionContext();
		ProcessLogicPluginContext context = ProcessLogicPluginContext.findLogicPluginContext(actionContext.getActivity(), fBizRecId);
		boolean releaseContext = context == null;
		if (releaseContext)
			context = ProcessLogicPluginContext.createLogicPluginContext(actionContext.getProcess(), actionContext.getActivity(), fBizRecId);
		try {
			if (materialGroup != null) {
				int i = 0;
				if (excuteExpression(context, materialGroup.getCondition(), variables, false)) {
					addTreeNode(tab, materialGroup, fBizRecId/*, isHasRecord*/);
					addListChirlds(context, materialGroup.getSubGroups(), i, tab, fBizRecId, variables, /*, isHasRecord*/false);
				}
			}
			return sortTable(tab);
		} finally {
			if (releaseContext)
				ProcessLogicPluginContext.removeLogicPluginContext(context, false);
		}
	}

	/**
	 * 获取指定业务下的所有材料信息 //TODO 该方法应该是为审批事项使用，定义审批事项时，默认表达式执行都为true
	 */
	public static Table getBusinessMaterials(String concept, String select, String from, String aggregate, String dataModel, String fnModel,
			String condition, List<Object> range, Boolean distinct, String idColumn, String filter, Integer limit, Integer offset, String columns,
			String orderBy, String aggregateColumns, Map<String, Object> variables) {

		ProcessLogicPluginContext context = null;// context 为null 代表在定义审批事项时
													// 提取到所有材料，不需要表达式判断
		String fProcess = variables.get("fProcess").toString();
		Table tab = KSQL.select("SELECT B_Material.* FROM B_Material B_Material where 1=2 ", null, FLOWFILE_DATAMODEL, null);
		tab.getMetaData().setKeyColumn("B_Material");
		BizInfo bizInfo = CacheManager.getBizInfoByProcess(fProcess);
		MaterialGroup materialGroup = bizInfo.getMaterialGroup();

		if (materialGroup != null) {
			int i = 0;
			addTreeNode(tab, materialGroup, ""/*, true*/);
			addListChirlds(context, materialGroup.getSubGroups(), i, tab, "", variables/*, true*/, true);
		}

		return tab;

	}

	/**
	 * 计算要件个数
	 * 
	 * @return
	 */
	public static int computeMaterialCount(String fBizRecId) {

		ActionContext actionContext = ContextHelper.getActionContext();
		ProcessLogicPluginContext context = null;
		if (!ValueHelper.isEmpty(fBizRecId))
			context = ProcessLogicPluginContext.createLogicPluginContext(actionContext.getProcess(), actionContext.getActivity(), fBizRecId);

		String fProcess = actionContext.getProcess().getFullName();
		Table tab = KSQL.select("SELECT B_Material.* FROM B_Material B_Material where 1=2 ", null, FLOWFILE_DATAMODEL, null);
		tab.getMetaData().setKeyColumn("B_Material");

		BizInfo bizInfo = CacheManager.getBizInfoByProcess(fProcess);
		MaterialGroup materialGroup = bizInfo.getMaterialGroup();

		if (materialGroup != null) {
			int i = 0;
			addTreeNode(tab, materialGroup, fBizRecId/*, true*/);
			addListChirlds(context, materialGroup.getSubGroups(), i, tab, fBizRecId, null/*, true*/, false);
		}

		return tab.size();

	}

	/**
	 * 从xml读取过来的数据时无序的，要对table进行排序
	 * 
	 * @param tab
	 * @return
	 */
	private static Table sortTable(Table tab) {

		List<RowSort> sortRows = new ArrayList<RowSort>();
		sortRows.clear();

		Iterator<Row> rows = tab.iterator();
		while (rows.hasNext()) {
			Row row = (Row) rows.next();
			RowSort rowSort = new RowSort();
			rowSort.setOrder(row.getInteger("fDispOrder"));
			rowSort.setRow(row);
			sortRows.add(rowSort);
		}

		Collections.sort(sortRows, new ComparatorRowSort());

		Table table = KSQL.select("SELECT B_Material.* FROM B_Material B_Material where 1=2 ", null, FLOWFILE_DATAMODEL, null);

		for (RowSort lastRow : sortRows) {
			Row copy = lastRow.getRow();
			if (copy.getState().isDelete())
				continue;
			Row rec = table.appendRow();
			rec.setString("B_Material", copy.getString("B_Material"));
			rec.setString("fMaterialId", copy.getString("fMaterialId"));
			rec.setString("fParentId", copy.getString("fParentId"));
			rec.setString("fMaterialName", copy.getString("fMaterialName"));
			rec.setString("fRemark", copy.getString("fRemark"));
			rec.setString("fMaterialType", copy.getString("fMaterialType"));
			rec.setString("fBizRecId", copy.getString("fBizRecId"));
			rec.setString("fDocIds", copy.getString("fDocIds"));
			rec.setString("fphysical", copy.getString("fphysical"));
			rec.setString("fOperatorId", copy.getString("fOperatorId"));
			rec.setInteger("fDispOrder", copy.getInteger("fDispOrder"));
			rec.setString("fOperatorId", copy.getString("fOperatorId"));
			rec.setString("fOriginalRequired", copy.getString("fOriginalRequired"));
			rec.setString("fIsDefSelect", copy.getString("fIsDefSelect"));
			rec.setString("fRequired", copy.getString("fRequired"));
			rec.setInteger("fMtNums", copy.getInteger("fMtNums"));
			rec.setString("fMedium", copy.getString("fMedium"));
			rec.setString("fMaterialNav", copy.getString("fMaterialNav"));
			rec.setString("fBusinessId", copy.getString("fBusinessId"));
			rec.setString("fBusinessName", copy.getString("fBusinessName"));
			rec.setState(ModifyState.NONE);
		}
		table.getProperties().put(Table.PROP_NAME_ROWID, "B_Material");
		table.getProperties().put(Table.PROP_DB_COUNT, sortRows.size());
		return table;

	}

	// 递归构造查看树
	private static void addListChirlds(ProcessLogicPluginContext context, List<MaterialGroup> list, int i, Table tab, String fBizRecId,
			Map<String, Object> variables, /*boolean isHasRecord,*/boolean isIngnoreExpr) {
		for (MaterialGroup c : list) {
			if (excuteExpression(context, c.getCondition(), variables, isIngnoreExpr)) {
				addTreeNode(tab, c, fBizRecId/*, isHasRecord*/);
				if (c.getSubGroups() == null)
					continue;
				if (c.getSubGroups().size() != 0) {
					addListChirlds(context, c.getSubGroups(), i, tab, fBizRecId, variables,/* isHasRecord,*/isIngnoreExpr);
				}
			}
		}
	}

	private static void addTreeNode(Table tab, MaterialGroup c, String fBizRecId/*, boolean isHasRecord*/) {
		if (c.getMaterials() != null && c.getMaterials().size() > 0) {
			addTreeSon(tab, c, fBizRecId/*, isHasRecord*/);
		}
	}

	// 添加材料
	private static void addTreeSon(Table tab, MaterialGroup c, String fBizRecId/*, boolean isHasRecord*/) {

		for (BizMaterial m : c.getMaterials()) {
			if (isExistFromTable(tab, m.getId().trim()))
				continue;
			Row rec = tab.appendRow(CommonUtils.createGUID());
			rec.setString("fMaterialId", m.getId());
			rec.setString("fParentId", c.getId());
			rec.setString("fMaterialName", m.getName());
			rec.setString("fRemark", "");
			rec.setString("fMaterialType", "必要材料");
			rec.setString("fBizRecId", fBizRecId);
			rec.setString("fOriginalRequired", m.getOriginalRequired() != null && m.getOriginalRequired() ? "是" : "否");
			rec.setString("fOperatorId", "");
			rec.setInteger("fDispOrder", m.getDispOrder());
			// 材料第一次确认后，xml文件中定义的默认值无效，全部是否
			//			if (isHasRecord)
			//				rec.setString("fIsDefSelect", "否");
			//			else
			rec.setString("fIsDefSelect", m.getIsDefSelect() != null && m.getIsDefSelect() ? "是" : "否");
			rec.setString("fRequired", m.getRequired() != null && m.getRequired() ? "是" : "否");
			rec.setInteger("fMtNums", m.getMtNums() != null ? m.getMtNums() : 0);
			rec.setString("fMedium", m.getMedium() != null ? m.getMedium() : "");
			rec.setString("fMaterialNav", m.getMatNav());
			rec.setInt("isVirtual", 1);
			rec.setInt("choice", 0);
			rec.setState(ModifyState.NONE);

		}
	}

	/**
	 * 判断材料是否已经存在
	 * 
	 * @param tab
	 * @param compareName
	 *            *@param IdOrName true 代表以材料ID比较, false 代表以材料名称比较
	 * @return
	 */
	private static boolean isExistFromTable(Table tab, String compareId) {
		Iterator<Row> rows = tab.iterator();
		while (rows.hasNext()) {
			Row row = rows.next();
			if (row.getString("fMaterialId").trim().equals(compareId))
				return true;
		}
		return false;
	}

	/**
	 * 执行表达式
	 * 
	 * @param materialGroup
	 * @return
	 */
	private static boolean excuteExpression(ProcessLogicPluginContext context, String boolExpr, Map<String, Object> variables, boolean isIngnoreExpr) {
		if (isIngnoreExpr || Utils.isEmptyString(boolExpr)) {
			return true;
		}
		Set<String> relTables = BizExprHelper.parseObjectIdOfTableFunction(boolExpr);
		for (String tableName : relTables) {
			context.loadBizTable(tableName);
		}
		Object expressValue = Expression.evaluate(boolExpr, variables, ModelUtils.getModel(BASE_FNMODEL));
		return Boolean.TRUE.equals(expressValue);
	}

	public static Table getBizMaterialBrowse(String concept, String select, String from, String aggregate, String dataModel, String fnModel,
			String condition, List<Object> range, Boolean distinct, String idColumn, String filter, Integer limit, Integer offset, String columns,
			String orderBy, String aggregateColumns, Map<String, Object> variables) {

		String fBizRecId = variables.get("fBizRecId").toString();
		// 查询B_Material，转换为MaterialObject ，即返回当前已维护的材料树
		// TODO 为罗定项目 去掉 fDocIds is not null and 

		Table materialTab = KSQL.select("SELECT m.* FROM B_Material m where fBizRecId='" + fBizRecId
				+ "' and fDocIds is not null order by fMaterialNav  , fDispOrder asc ", null, FLOWFILE_DATAMODEL, null);
		Iterator<Row> rows = materialTab.iterator();
		String fDocIds = "", fMaterialNav = "", fmateriaNavId = "";
		boolean isNew = false;

		Table treeTable = createBrowseTree();
		while (rows.hasNext()) {
			Row row = rows.next();
			fDocIds = row.getString("fDocIds");
			String thisNav = row.getString("fMaterialNav") == null ? "" : row.getString("fMaterialNav");
			if (!thisNav.equals(fMaterialNav)) {
				fMaterialNav = thisNav;
				isNew = true;
			} else
				isNew = false;
			if (fDocIds != null && fDocIds.length() > 2) {
				com.alibaba.fastjson.JSONArray objA = com.alibaba.fastjson.JSONArray.parseArray(fDocIds);
				fmateriaNavId = addTreeNodeForBrowse(treeTable, row, objA, isNew, fmateriaNavId);
			}

		}
		return treeTable;
	}

	public static Table createBrowseTree() {

		String[] name_array = { "fID", "fParentId", "fMaterialName", "fDocID", "fDocPath", "fDocName", "fFileID" };
		String[] type_array = { "String", "String", "String", "String", "String", "String", "String" };
		List<String> names = new ArrayList<String>();
		List<String> types = new ArrayList<String>();

		for (int i = 0; i < name_array.length; i++) {
			names.add(name_array[i]);
			types.add(type_array[i]);
		}
		// 创建table，names代表列，Types代表列的类型
		Table tab = TableUtils.createTable(ModelUtils.getModel(FLOWFILE_DATAMODEL), names, types);
		tab.getProperties().put(Table.PROP_NAME_ROWID, "fID");
		tab.getMetaData().setKeyColumn("fID");
		return tab;
	}

	private static String addTreeNodeForBrowse(Table tab, Row row, JSONArray jsonArray, boolean isNew, String fmateriaNavId) {

		if (isNew) {
			fmateriaNavId = CommonUtils.createGUID();
			Row parentRec = tab.appendRow(CommonUtils.createGUID());
			parentRec.setString("fID", fmateriaNavId);
			parentRec.setString("fParentId", null);
			parentRec.setString("fMaterialName", row.getString("fMaterialNav") == null ? "其他" : row.getString("fMaterialNav"));
			parentRec.setString("fDocID", "");
			parentRec.setString("fDocName", "");
			parentRec.setString("fDocPath", "");
			parentRec.setString("fFileID", "");
			parentRec.setState(ModifyState.NONE);
		}

		String fParentId = "";
		if (jsonArray.size() > 1) {
			fParentId = CommonUtils.createGUID();
			Row parentRec = tab.appendRow(CommonUtils.createGUID());
			parentRec.setString("fID", fParentId);
			parentRec.setString("fParentId", fmateriaNavId);
			parentRec.setString("fMaterialName", row.getString("fMaterialName"));
			parentRec.setString("fDocID", "");
			parentRec.setString("fDocName", "");
			parentRec.setString("fDocPath", "");
			parentRec.setString("fFileID", "");
			parentRec.setState(ModifyState.NONE);
		} else {
			fParentId = fmateriaNavId;
		}

		Iterator<Object> objs = jsonArray.iterator();
		while (objs.hasNext()) {
			MaterialFileObject materialObject = new MaterialFileObject();
			materialObject.readerFromJson(objs.next());
			Row rec = tab.appendRow(CommonUtils.createGUID());
			rec.setString("fID", UUIDMaker.generate());
			rec.setString("fParentId", fParentId);
			rec.setString("fMaterialName", jsonArray.size() > 1 ? materialObject.getDocName() : row.getString("fMaterialName"));
			rec.setString("fDocID", materialObject.getDocID());
			rec.setString("fDocName", materialObject.getDocName());
			rec.setString("fDocPath", materialObject.getDocPath());
			rec.setString("fFileID", materialObject.getFileID());
			rec.setState(ModifyState.NONE);
		}

		return fmateriaNavId;

	}

	/**
	 * 获得案卷已存在的材料列表，用于构造附件快速浏览列表
	 * 
	 * @return JSON结构 [{Material1},{Material2},{Material3}]
	 */
	public static Map<String, Object> getBizRecMaterialList(String bizRecId) {
		// 查询B_Material，转换为MaterialObject ，即返回当前已维护的材料树 //length(fDocIds)>2
		// 是指fDocIds值不等于[]的情况，fDocIds等于[]代表添加附件又删除掉
		// 为罗定项目，去掉 and fDocIds is not null and length(fDocIds)>2
		Table materialTab = KSQL.select("SELECT B_Material.* FROM B_Material B_Material   where  fBizRecId='" + bizRecId
				+ "' order by fMaterialNav  , fDispOrder asc ", null, FLOWFILE_DATAMODEL, null);
		materialTab.getMetaData().setKeyColumn("B_Material");
		Iterator<Row> rows = materialTab.iterator();
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("materials", "");
		JSONArray array = new JSONArray();
		String fDocIds = "";
		com.alibaba.fastjson.JSONArray docs = new com.alibaba.fastjson.JSONArray();
		while (rows.hasNext()) {
			Row row = rows.next();
			JSONObject object = new JSONObject();
			fDocIds = row.getString("fDocIds");
			docs = JSON.parseArray(fDocIds);
			object.put("fMaterialId", row.getString("fMaterialId"));
			object.put("fMaterialName", row.getString("fMaterialName"));
			object.put("fMaterialNav", row.getString("fMaterialNav"));
			object.put("fDocIds", row.getString("fDocIds"));
			object.put("numb", docs == null ? 0 : docs.size());
			object.put("fDispOrder", row.getInteger("fDispOrder"));
			array.add(object);
		}
		result.put("materials", array.toString());
		result.put("total", computeMaterialCount(bizRecId));
		result.put("had", materialTab.size());
		return result;
	}

	/**
	 * 获得案卷材料权限，包含已维护的及业务定义的材料树的权限，用户材料维护。
	 * 
	 * @return JSON结构 [{Material1},{Material2},{Material3}]
	 */
	public static String getBizRecMaterialPermissions(String fBizRecId, String fMaterialId, String fOperatorId) {
		// 1.获得当前操作者ID，不等于已上传材料的附件上传人，附件不允许删除(替换)。
		String pid = ContextHelper.getOperator().getID();
		// BizInfo bizInfo =
		// CacheManager.getBizInfo(ContextHelper.getActionContext().getProcess());

		// 2.获得当前环节的材料权限，key=材料ID ,value含义如下
		// "U|D|FD" 上传、删除自己、删除所有
		// 如果permissions中不包含此某个材料，默认为U|D 即允许上传、删除自己
		// Map<String, String> permissions =
		// bizInfo.getActivityMaterialPermissions(ContextHelper.getActionContext().getActivity().getName());
		// 查询B_Material，转换为MaterialObject ，即返回当前已维护的材料树
		// List<MaterialObject> ret = new ArrayList<MaterialObject>();

		// 提取当前环节的权限，权限控制到所有材料，不按材料划分
		String ret = "D";
		// 1:列表 3:读取 7:下载 263:下载 上传 519:下载 修改 775:下载 上传 修改 1543:下载 修改 删除 1799:下载
		// 上传 修改 删除

		// me 上传、删除 1、对别
		// me 读取 or 下载
		// other : 读取 or 下载 ,不能删除其他人上传的文件

		if ("".equals(fOperatorId))
			return "1799";

		if (fOperatorId.equals(pid)) {
			if (ret.contains("U")) {
				return "1799";
			} else {
				return "1543";
			}
		} else {

			if (ret.contains("FD") && ret.contains("U")) {
				return "1799";
			} else if (ret.contains("FD") && !ret.contains("U")) {
				return "1543";
			} else if (ret.contains("U")) {
				return "263";
			}
		}

		return "1799";
	}

	/**
	 * 给材料上传附件
	 * 
	 * @param fBizRecId
	 * @param map
	 * @return
	 */
	public static Map<String, Object> upLoadNeedMaterials(Map<String, Object> map, String fBizRecId) {

		Map<String, Object> result = new HashMap<String, Object>();
		Object jsonStr = map.get("fDocIds");
		String fID = map.get("fID").toString();

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("fBizRecId", fBizRecId);
		params.put("fID", fID);
		params.putAll(map);

		// 根据在附件表中是否存在记录来判断新增、修改
		String ksql = "SELECT p.* FROM B_Material p where fBizRecId='" + params.get("fBizRecId") + "' and fMaterialId='" + params.get("fMaterialId")
				+ "'";

		Table tempTab = KSQL.select(ksql, null, FLOWFILE_DATAMODEL, null);
		if (tempTab.size() == 0) {
			addFile(params, jsonStr);
		} else {
			updateFile(tempTab, jsonStr, params);
		}

		return result;

	}

	/**
	 * 新增附件记录
	 * 
	 * @param params
	 * @param dataModel
	 * @param jsonStr
	 */
	private static void addFile(HashMap<String, Object> params, Object jsonStr) {
		// 规避二次提交,等待前台解决Datachanged事件后，可去掉这段代码 cut_start

		if (jsonStr.equals("") && params.get("updateType").toString().equals("file"))
			return;

		Table tab = KSQL.select("SELECT p.* FROM B_Material p where 1=2 ", null, FLOWFILE_DATAMODEL, null);
		tab.getMetaData().setStoreByConcept("B_Material", true);
		tab = BizData.create(tab, "B_Material", null, FLOWFILE_DATAMODEL);
		Row rec = tab.iterator().next();
		rec.setValue("fBizRecId", params.get("fBizRecId"));
		rec.setValue("fMaterialId", params.get("fMaterialId"));
		rec.setValue("fParentId", rec.getValue("p"));
		rec.setValue("fMaterialName", params.get("fMaterialName"));
		rec.setValue("fDocIds", jsonStr);
		rec.setValue("fphysical", params.get("fPhysical"));
		rec.setValue("fMaterialType", params.get("fMaterialType"));
		rec.setValue("fOperatorId", ContextHelper.getOperator().getID());
		rec.setInteger("fDispOrder", Integer.parseInt(params.get("fDispOrder").toString()));
		rec.setValue("fOriginalRequired", params.get("fOriginalRequired"));
		rec.setValue("fIsDefSelect", "是");
		rec.setValue("fRequired", params.get("fRequired"));
		rec.setInteger("fMtNums", Integer.parseInt(params.get("fMtNums").toString()));
		rec.setValue("fMedium", params.get("fMedium"));
		rec.setValue("fMaterialNav", params.get("fMaterialNav"));
		rec.setValue("fBusinessId", params.get("fBusinessId"));
		rec.setValue("fBusinessName", params.get("fBusinessName"));

		tab.save(FLOWFILE_DATAMODEL);

	}

	private static void updateFile(Table tab, Object jsonStr, HashMap<String, Object> params) {
		Row rec = tab.iterator().next();

		if (params.get("updateType").toString().equals("file"))
			rec.setValue("fDocIds", jsonStr);
		rec.setInteger("fMtNums", Integer.parseInt(params.get("fMtNums").toString()));
		rec.setValue("fOriginalRequired", params.get("fOriginalRequired").toString());
		if (jsonStr.toString().length() > 2)
			rec.setValue("fIsDefSelect", "是");

		tab.save(FLOWFILE_DATAMODEL);
	}

	/**
	 * 保存材料预处理
	 * @param concept
	 * @param dataModel
	 * @param fnModel
	 * @param insertRange
	 * @param deleteRange
	 * @param updateRange
	 * @param readOnly
	 * @param notNull
	 * @param table
	 */
	public static void saveB_MaterialAll(String concept, String dataModel, String fnModel, List<DataPermission> insertRange,
			List<DataPermission> deleteRange, List<DataPermission> updateRange, String readOnly, String notNull, Table table) {
		// TODO 
		Iterator<Row> itor = table.iterator();
		List<String> removeList = new ArrayList<String>();
		List<String> deleteList = new ArrayList<String>();
		while (itor.hasNext()) {
			Row r = itor.next();
			String fID = r.getString(concept);
			if (r.getInt("choice") == 1) {
				if (r.getInt("isVirtual") == 1) {
					// 虚拟并且选中的，新增
					r.setState(ModifyState.NEW);
				}
			} else {
				// 非选中
				if (r.getInt("isVirtual") == 0) {
					// 已存在的数据,删除
					deleteList.add(fID);
				} else {
					// 虚拟的数据，移除
					removeList.add(fID);
				}
			}
		}
		// 关闭记录状态，remove row
		table.setRecordState(false);
		for (String fID : removeList) {
			table.deleteRows(fID);
		}

		// 开启记录状态，delete row
		table.setRecordState(true);
		for (String fID : deleteList) {
			table.deleteRows(fID);
		}
		BizData.save(table, concept, insertRange, deleteRange, updateRange, readOnly, notNull, dataModel, fnModel);
	}

	/**
	 * 已经上传的必要要件
	 * 
	 * @param fBizRecId
	 * @return
	 */
	private static Table hasUpLoadNeedMaterial(String... bizrecIds) {
		String condition = null;
		if (bizrecIds.length == 1)
			condition = "fBizRecId='" + bizrecIds[0] + "'";
		else {
			for (String id : bizrecIds) {
				if (condition == null)
					condition = "'" + id + "'";
				else
					condition += ",'" + id + "'";
			}
			condition = "fBizRecId in (" + condition + ")";
		}
		Table tab = KSQL.select("SELECT B_Material.*,0 as isVirtual,1 as choice FROM B_Material B_Material where  fMaterialType = '必要材料' and "
				+ condition, null, FLOWFILE_DATAMODEL, null);
		tab.getProperties().put(Table.PROP_NAME_ROWID, "B_Material");
		tab.getMetaData().setKeyColumn("B_Material");
		return tab;
	}

	/**
	 * 标记打印材料
	 * 
	 * @param fMaterialIds
	 */
	public static void targMaterials(String fMaterialIds, String fBizRecId) {

		JSONArray matArray = JSON.parseArray(fMaterialIds);
		String default_materiIds = "";
		for (int i = 0; i < matArray.size(); i++) {
			JSONObject mat = matArray.getJSONObject(i);
			String fMaterialId = mat.getString("fMaterialId");
			default_materiIds += ",'" + fMaterialId + "'";

			Table tab = KSQL.select("SELECT p.* FROM B_Material p where  fBizRecId='" + fBizRecId + "' and fMaterialId='" + fMaterialId + "'", null,
					FLOWFILE_DATAMODEL, null);

			if (tab.size() > 0) {
				Row rec = tab.iterator().next();
				rec.setString("fIsDefSelect", "是");
				rec.setInteger("fMtNums", mat.getInteger("fMtNums"));
				rec.setString("fMaterialName", mat.getString("fMaterialName"));
				rec.setString("fOriginalRequired", mat.getString("fOriginalRequired"));
			} else {
				tab.getMetaData().setStoreByConcept("B_Material", true);
				tab = BizData.create(tab, "B_Material", null, FLOWFILE_DATAMODEL);
				Row rec = tab.iterator().next();

				rec.setString("fBizRecId", fBizRecId);
				rec.setString("fMaterialId", fMaterialId);
				rec.setString("fParentId", "");
				rec.setString("fMaterialName", mat.getString("fMaterialName"));
				rec.setInteger("fDispOrder", mat.getInteger("fDispOrder"));
				rec.setString("fDocIds", "");
				rec.setString("fOperatorId", ContextHelper.getOperator().getID());
				rec.setString("fMaterialType", "必要材料");
				rec.setString("fIsDefSelect", "是");

				rec.setString("fOriginalRequired", mat.getString("fOriginalRequired"));
				rec.setString("fRequired", mat.getString("fRequired"));
				rec.setInteger("fMtNums", mat.getInteger("fMtNums"));
				rec.setString("fMedium", mat.getString("fMedium"));
				rec.setString("fMaterialNav", mat.getString("fMaterialNav"));
				rec.setString("fBusinessId", mat.getString("fBusinessId"));
				rec.setString("fBusinessName", mat.getString("fBusinessName"));

			}
			tab.save(FLOWFILE_DATAMODEL);
		}

		StringBuffer sql_delete = new StringBuffer();
		if (default_materiIds.length() > 0) {
			sql_delete
					.append("delete B_Material p  where   fMaterialType ='必要材料' and  (p.fDocIds is null  or length(p.fDocIds)<=2 )   and  p.fBizRecId='")
					.append(fBizRecId).append("' and p.fMaterialId not  in(").append(default_materiIds.substring(1, default_materiIds.length()))
					.append(")");
		} else {
			sql_delete
					.append("delete B_Material p   where  fMaterialType ='必要材料'  and   (p.fDocIds is   null  or length(p.fDocIds)<=2 )  and  p.fBizRecId='")
					.append(fBizRecId).append("'  ");

		}
		Map<String, String> map = new HashMap<String, String>();
		map.put(DatabaseProduct.DEFAULT.name(), sql_delete.toString());
		SQLUtils.executeUpdate(map, null, FLOWFILE_DATAMODEL);

	}

	// 改方法保存所有必要要件，暂不使用
	public static void targMaterialsOld(String fMaterialIds, String fBizRecId) {

		Table matTable = tableMap.get(fBizRecId);
		Table tab = hasUpLoadNeedMaterial(fBizRecId);

		Iterator<Row> rows = matTable.iterator();
		String fMaterialId = "";
		while (rows.hasNext()) {

			Row row = rows.next();
			fMaterialId = row.getString("fMaterialId");

			if (tab.size() > 0) {
				Row rec = tab.iterator().next();
				if (fMaterialIds.contains(fMaterialId)) {
					rec.setValue("fIsDefSelect", "是");
				} else {
					rec.setValue("fIsDefSelect", "否");
				}
			} else {
				tab.getMetaData().setStoreByConcept("B_Material", true);
				tab = BizData.create(tab, "B_Material", null, FLOWFILE_DATAMODEL);
				Row rec = tab.iterator().next();
				rec.setValue("fBizRecId", fBizRecId);
				rec.setValue("fMaterialId", fMaterialId);
				rec.setValue("fParentId", "");
				rec.setValue("fMaterialName", row.getValue("fMaterialName"));
				rec.setInteger("fDispOrder", row.getInteger("fDispOrder"));
				rec.setValue("fDocIds", "");
				rec.setValue("fOperatorId", ContextHelper.getOperator().getID());
				rec.setValue("fMaterialType", "必要材料");

				if (fMaterialIds.contains(fMaterialId)) {
					rec.setValue("fIsDefSelect", "是");
				} else {
					rec.setValue("fIsDefSelect", "否");
				}

				rec.setValue("fOriginalRequired", row.getValue("fOriginalRequired"));
				rec.setValue("fRequired", row.getValue("fRequired"));
				rec.setInteger("fMtNums", row.getInteger("fMtNums"));
				rec.setValue("fMedium", row.getValue("fMedium"));
				rec.setValue("fMaterialNav", row.getValue("fMaterialNav"));

			}
			tab.save(FLOWFILE_DATAMODEL);

		}

	}

	/**
	 * 查询案卷的材料分组
	 * 
	 * @param variables
	 */
	public static Table queryV_MaterialGroup(String concept, String select, String from, String aggregate, String dataModel, String fnModel,
			String condition, List range, Boolean distinct, String idColumn, String filter, Integer limit, Integer offset, String columns,
			String orderBy, String aggregateColumns, Map variables) {
		String fBizRecId = variables.get("fBizRecId").toString();
		BizInfo bizInfo = CacheManager.getBizInfoByProcess(ContextHelper.getActionContext().getProcess().getFullName());

		Table tab = KSQL.select("select distinct b.fMaterialNav as fMaterialNav,(0) as fDispOrder from B_Material b where b.fBizRecId='" + fBizRecId
				+ "' and b.fMaterialType='必要材料' order by b.fMaterialNav", null, FLOWFILE_DATAMODEL, null);
		/* and b.fDocIds is not null */
		tab.getMetaData().setKeyColumn("fMaterialNav");
		tab.getProperties().put(Table.PROP_NAME_ROWID, "fMaterialNav");
		tab.setRecordState(false);
		if (tab.size() > 0) {
			Iterator<Row> i = tab.iterator();
			while (i.hasNext()) {
				Row r = i.next();
				if (Utils.isEmptyString(r.getString("fMaterialNav")))
					r.setString("fMaterialNav", "其他");
			}
		}

		MaterialGroup materialGroup = bizInfo.getMaterialGroup();
		if (materialGroup != null) {
			ActionContext actionContext = ContextHelper.getActionContext();
			ProcessLogicPluginContext context = ProcessLogicPluginContext.findLogicPluginContext(actionContext.getActivity(), fBizRecId);
			boolean releaseContext = context == null;
			if (releaseContext)
				context = ProcessLogicPluginContext.createLogicPluginContext(actionContext.getProcess(), actionContext.getActivity(), fBizRecId);
			try {
				addMaterialNavToTable(tab, materialGroup, context, variables);
			} finally {
				if (releaseContext)
					ProcessLogicPluginContext.removeLogicPluginContext(context, false);
			}
		}
		return tab;
	}

	private static void addMaterialNavToTable(Table tab, MaterialGroup g, ProcessLogicPluginContext context, Map<String, Object> variables) {
		if (!excuteExpression(context, g.getCondition(), variables, true))
			return;
		if (g.getMaterials() != null)
			for (BizMaterial m : g.getMaterials()) {
				Row r = tab.getRow(m.getMatNav());
				if (r == null)
					tab.appendRow(m.getMatNav());
			}
		if (g.getSubGroups() != null)
			for (MaterialGroup c : g.getSubGroups())
				addMaterialNavToTable(tab, c, context, variables);
	}

	public static Table getFlowBZCL(String bizrecid) {
		String sql = "select nf.fbizrecid, nvl(fm.fywclmc,'补充材料') ffilename, nvl(fm.fywclbh,sys_guid()) as fywclbh,nf.fdocids ,m.fdocids as FOldFiles from net_files nf join B_PREPBIZREC pb on nf.fbizrecid=pb.fbizrecid "
				// /common/spsxgl 审批事项信息 
				+ "left join T_SYS_SXCL_YWCL fm on pb.fbizmappingid= fm.fsxlcid and fm.fspclbh=nf.ftype "
				+ "left join  B_Material m on fm.fywclbh=m.fmaterialid and nf.fbizrecid=m.fbizrecid where nf.fbizrecid=? and nf.status='补正'";
		return SysUtils.query(sql, bizrecid);

	}

	/**
	 * 判断是否选择
	 * @param fBizRecId
	 * @return
	 */
	public static Map<String, Object> isHasChoiceMaterial(String fBizRecId) {

		//判断是否已经添加过材料，且添加的材料已经上传过附件
		StringBuffer sql_info = new StringBuffer();
		sql_info.append("SELECT B_Material.* FROM B_Material B_Material where  length(B_Material.fDocIds)>2 and  fBizRecId='").append(fBizRecId)
				.append("'");
		Table hasTab = KSQL.select(sql_info.toString(), null, FLOWFILE_DATAMODEL, null);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("isHasMaterial", false);

		if (hasTab.size() > 0) {

			result.put("isHasMaterial", true);
		}
		return result;

	}

	/*
	 * 用户自定义选择的业务分组
	 */
	public static Map<String, Object> choiceMaterialGroup(String fBizRecId) {
		String personID = ContextHelper.getPerson().getID();
		StringBuffer sql_info = new StringBuffer();
		sql_info.append("SELECT B_Material.* FROM B_Material B_Material where  fMaterialType='必要材料' and  fBizRecId='").append(fBizRecId).append("'");
		Table hasTab = KSQL.select(sql_info.toString(), null, FLOWFILE_DATAMODEL, null);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("fBusinessId", "");
		result.put("fBusinessName", "");
		//已经选择的分组
		if (hasTab.size() > 0) {
			Row row = hasTab.iterator().next();
			String fBusinessName = row.getValue("fBusinessName") == null ? "" : row.getValue("fBusinessName").toString();
			String fBusinessId = row.getValue("fBusinessId") == null ? "" : row.getValue("fBusinessId").toString();
			result.put("fBusinessId", fBusinessId);
			result.put("fBusinessName", fBusinessName);
		} else {
			//首次加载默认分组
			sql_info.delete(0, sql_info.length());
			sql_info.append("  SELECT B_UserProcess.* FROM B_UserProcess  B_UserProcess where  fIsDefault = '是'  and  fProcess='")
					.append(ContextHelper.getActionContext().getProcess().getFullName()).append("' and fUserID='").append(personID).append("'")
					.append(" union all ")
					.append("SELECT B_UserProcess.* FROM B_UserProcess  B_UserProcess where  fUserID  is null  and fDispOrder=1  and  fProcess='")
					.append(ContextHelper.getActionContext().getProcess().getFullName()).append("'  ");
			Table tab = SQL.select(sql_info.toString(), null, FLOWFILE_DATAMODEL, null);

			Iterator<Row> rows = tab.iterator();
			if (tab.size() > 0) {
				Row row = rows.next();
				result.put("fBusinessId", row.getValue("FID").toString());
				result.put("fBusinessName", row.getValue("FBUSINESSNAME").toString());
			}
		}

		return result;
	}

	private static void loadDefaultMaterial(String fBizRecId, Table toTab, String fUserProcessId, String fBusinessName) {

		toTab.getMetaData().setKeyColumn("B_Material");
		toTab.getMetaData().setStoreByConcept("B_Material", true);
		toTab.setUpdateMode(UpdateMode.WHERE_ALL);

		Map<String, Object> choiceMaterialGroup = choiceMaterialGroup(fBizRecId);
		String fBusinessId = choiceMaterialGroup.get("fBusinessId").toString();

		if (!fUserProcessId.equals(fBusinessId)) {
			//删除已经存在的记录
			Iterator<Row> toRows = toTab.iterator();
			while (toRows.hasNext()) {
				Row toRow = toRows.next();
				toRow.setState(ModifyState.DELETE);
				toTab.deleteRows(toRow);
			}
			toTab.save(FLOWFILE_DATAMODEL);
		}

		StringBuffer sql_info = new StringBuffer();
		sql_info.append(
				" select B_UserBusinessMaterial.* from B_UserBusinessMaterial B_UserBusinessMaterial where B_UserBusinessMaterial.fUserProcessID='")
				.append(fUserProcessId)
				.append("' and not exists (select 1 from B_Material B_Material where B_Material.fMaterialId = B_UserBusinessMaterial.fMaterialId) ");
		Table fromTab = KSQL.select(sql_info.toString(), null, FLOWFILE_DATAMODEL, null);
		Iterator<Row> fromRows = fromTab.iterator();
		while (fromRows.hasNext()) {
			Row fromRow = fromRows.next();
			userMaterialAdd(toTab, fBizRecId, fromRow, fUserProcessId, fBusinessName);
		}
	}

	private static void userMaterialAdd(Table toTab, String fBizRecId, Row fromRow, String fBusinessId, String fBusinessName) {

		Row rec = toTab.appendRow(CommonUtils.createGUID());
		rec.setString("fMaterialId", fromRow.getValue("fMaterialId").toString());
		rec.setString("fParentId", "");
		rec.setString("fMaterialName", fromRow.getValue("fMaterialName").toString());
		rec.setString("fRemark", "");
		rec.setString("fMaterialType", "必要材料");
		rec.setString("fBizRecId", fBizRecId);
		rec.setString("fOriginalRequired", fromRow.getValue("fMaterialAttribute").toString());
		rec.setString("fOperatorId", "");
		rec.setInteger("fDispOrder", Integer.parseInt(fromRow.getValue("fDispOrder").toString()));
		// 材料第一次确认后，xml文件中定义的默认值无效，全部是否

		rec.setString("fIsDefSelect", "是");
		rec.setInteger("version", 0);
		rec.setString("fRequired", "否");
		rec.setInteger("fMtNums", Integer.parseInt(fromRow.getValue("fMtNums").toString()));
		rec.setString("fMedium", "");
		rec.setString("fMaterialNav", "");
		rec.setString("fBusinessId", fBusinessId);
		rec.setString("fBusinessName", fBusinessName);

		rec.setState(ModifyState.NEW);

	}

	public static void copyMaterial(String concept, String select, String from, String aggregate, String dataModel, String fnModel, String condition,
			List<Object> range, Boolean distinct, String idColumn, String filter, Integer limit, Integer offset, String columns, String orderBy,
			String aggregateColumns, Map<String, Object> variables) {
		//查找最大顺序号，加1；
		String rowid = (String) variables.get("rowid");
		String personName = (String) variables.get("personName");
		String personID = (String) variables.get("personID");
		String maxSql = "  select max(p.fdisporder) as fdisporder  from  B_UserProcess p  ";
		int i = 0;
		Map<String, String> maxMap = new HashMap<String, String>();
		maxMap.put(DatabaseProduct.DEFAULT.name(), maxSql);
		Table table = SQL.select(maxMap, null, FLOWFILE_DATAMODEL);
		Iterator<Row> row = table.iterator();
		while (row.hasNext()) {
			Row rows = row.next();
			BigDecimal FDISPORDER = rows.getDecimal("FDISPORDER");
			i = FDISPORDER.intValue() + 1;
		}
		String sql = "  select *  from  B_UserProcess p where p.fid='" + rowid + "'  ";
		Map<String, String> selectMap = new HashMap<String, String>();
		selectMap.put(DatabaseProduct.DEFAULT.name(), sql);
		Table stable = SQL.select(selectMap, null, FLOWFILE_DATAMODEL);
		Iterator<Row> srow = stable.iterator();
		while (srow.hasNext()) {
			Row srows = srow.next();
			String FBUSINESSNAME = srows.getString("FBUSINESSNAME");
			String FPROCESS = srows.getString("FPROCESS");
			String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
			StringBuffer sb = new StringBuffer();
			sb.append("   INSERT INTO B_UserProcess p (fid,version, p.fbusinessname,p.fprocess,p.fuserid,p.fusername,   ")
					.append("   p.fcreatedatetime,p.fdisporder,p.fisdefault)   VALUES  ")
					.append("   ('" + uuid + "',0,'" + FBUSINESSNAME + "&','" + FPROCESS + "','" + personID + "','" + personName + "',sysdate," + i
							+ ",'否')   ");
			String csql = sb.toString();
			SQL.executeUpdate(csql, null, FLOWFILE_DATAMODEL);

			String dsql = "  select * from B_UserBusinessMaterial m where m.fuserprocessid ='" + rowid + "'";
			Map<String, String> dselectMap = new HashMap<String, String>();
			dselectMap.put(DatabaseProduct.DEFAULT.name(), dsql);
			Table dtable = SQL.select(dselectMap, null, FLOWFILE_DATAMODEL);
			Iterator<Row> drow = dtable.iterator();
			while (drow.hasNext()) {
				Row drows = drow.next();
				String FMATERIALNAME = drows.getString("FMATERIALNAME");
				BigDecimal FDISPORDER = drows.getDecimal("FDISPORDER");
				BigDecimal FMTNUMS = drows.getDecimal("FMTNUMS");
				String FMATERIALATTRIBUTE = drows.getString("FMATERIALATTRIBUTE");
				String newId = UUID.randomUUID().toString().trim().replaceAll("-", "");
				StringBuffer dsb = new StringBuffer();
				dsb.append("   INSERT INTO B_UserBusinessMaterial m (m.fmaterialid,m.version,m.fmaterialname,m.fdisporder,m.fmtnums,   ")
						.append("   m.fmaterialattribute,m.fuserprocessid) VALUES   ")
						.append("   ('" + newId + "',0,'" + FMATERIALNAME + "','" + FDISPORDER + "','" + FMTNUMS + "','" + FMATERIALATTRIBUTE + "','"
								+ uuid + "')   ");

				SQL.executeUpdate(dsb.toString(), null, FLOWFILE_DATAMODEL);
			}

		}
	}

	public static void materialProcess(List<String> processes, List<String> activitiesFNames) {
		for (int i = 0; i < processes.size(); i++) {
			String process = processes.get(i).toString();
			String activityFName = activitiesFNames.get(i).toString();
			String[] array = activityFName.split("/");

			Table funcTable = KSQL.select("SELECT p.*  FROM  B_BusinessProcess  p where  1=2  ", null, FLOWFILE_DATAMODEL, null);
			funcTable.getMetaData().setStoreByConcept("B_BusinessProcess", true);
			funcTable = BizData.create(funcTable, "B_BusinessProcess", null, FLOWFILE_DATAMODEL);
			Row rec = funcTable.iterator().next();
			rec.setString("fFuncLongName", activityFName);
			rec.setString("fFuncName", array[array.length - 1]);
			rec.setString("fProcess", process);
			funcTable.save(FLOWFILE_DATAMODEL);
		}
	}

	public static void deleteMaterial(String concept, String select, String from, String aggregate, String dataModel, String fnModel,
			String condition, List range, Boolean distinct, String idColumn, String filter, Integer limit, Integer offset, String columns,
			String orderBy, String aggregateColumns, Map variables) {
		String fUserProcessID = (String) variables.get("fUserProcessID");
		String sql = "    delete from B_UserBusinessMaterial m  where m.fuserprocessid='" + fUserProcessID + "'   ";
		SQL.executeUpdate(sql, null, FLOWFILE_DATAMODEL);

	}
}