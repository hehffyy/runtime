import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.butone.data.SQLUtils;
import com.butone.extend.CacheManager;
import com.butone.flowbiz.FlowBizConsts;
import com.butone.logic.impl.ProcessLogicPluginContext;
import com.butone.utils.ModelExtUtils;
import com.butone.xml.JaxbUtils;
import com.justep.design.model.ksql.KSQLParser;
import com.justep.design.model.ksql.node.MemFunctionNode;
import com.justep.design.model.ksql.node.Node;
import com.justep.design.model.ksql.node.VariableNode;
import com.justep.filesystem.FileSystemWrapper;
import com.justep.model.Activity;
import com.justep.model.Model;
import com.justep.model.ModelUtils;
import com.justep.system.context.ActionContext;
import com.justep.system.context.ContextHelper;
import com.justep.system.data.ColumnMetaData;
import com.justep.system.data.DatabaseProduct;
import com.justep.system.data.KSQL;
import com.justep.system.data.Row;
import com.justep.system.data.SQL;
import com.justep.system.data.Table;
import com.justep.system.data.TableMetaData;
import com.justep.system.process.ExpressEngine;
import com.justep.system.util.CommonUtils;
import com.justep.util.Utils;

public class Core {

	public static void main(String[] args) {
		String fileName = "c:\\123.config.xml";
		File file = new File(fileName);
		try {
			OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file), "utf-8");
			osw.write("123");
			osw.flush();
			osw.close();
		} catch (Exception e) {
		} finally {
			file.delete();
		}
	}

	/**
	 * 重新加载业务信息
	 * 
	 * @param process
	 */
	public static void reloadBizInfo(String process) {
		CacheManager.reloadBizInfo(process, true, false, false, false, false, false);
	}

	public static Map<String, Object> queryParse(String sql, String dataModel) {
		Model model = ModelUtils.getModel(dataModel);
		Map<String, Object> ret = new HashMap<String, Object>();
		List<String> paramNames = parseSQLParameters(sql);
		ret.put("paramNames", paramNames);
		List<Object> paramList = new ArrayList<Object>();
		for (int i = 0; i < paramNames.size(); i++) {
			paramList.add(null);
		}
		Table table = SQLUtils.select(sql, paramList, model, 0, 1);
		TableMetaData tableMeta = table.getMetaData();
		List<Map<String, Object>> columns = new ArrayList<Map<String, Object>>();

		for (ColumnMetaData columnMeta : tableMeta.getColumnMetaDatas()) {
			Map<String, Object> column = new HashMap<String, Object>();
			column.put("name", columnMeta.getName());
			column.put("type", columnMeta.getType());
			columns.add(column);
		}
		ret.put("columns", columns);
		return ret;
	}

	/**
	 * 解析SQL中的参数
	 * 
	 * @param sql
	 * @return
	 */
	private static List<String> parseSQLParameters(String sql) {
		List<String> ret = new ArrayList<String>();
		Pattern p = Pattern.compile(":([\\w\u4e00-\u9fa5]+)\\b", Pattern.MULTILINE);
		Matcher m = p.matcher(sql);
		while (m.find()) {
			if (!SQLUtils.isSQLFunction(sql, m.end())) {
				String paramName = m.group().substring(1);
				ret.add(paramName);
			}
		}
		return ret;
	}

	private static Map<String, Object> peekVarinatNode(Iterator<Node> itor, List<VariableNode> nodes) {
		Map<String, Object> ret = new HashMap<String, Object>();
		while (itor.hasNext()) {
			Node node = itor.next();
			if (node instanceof VariableNode) {
				nodes.add((VariableNode) node);
			} else if (node instanceof MemFunctionNode) {
				String image = ((MemFunctionNode) node).getImage();
				boolean inClause = image.contains("inClause(");
				Pattern p = Pattern.compile(":([\\w\u4e00-\u9fa5]+)\\b", Pattern.MULTILINE);
				Matcher m = p.matcher(image);
				while (m.find()) {
					String paramName = m.group().substring(1);
					if (inClause) {
						ret.put(paramName, "''");
					} else
						ret.put(paramName, "");
				}
			}
			// else if (node instanceof InNode) {
			// if (node.childrenCount() == 1) {
			// Node subNode = node.childrenIterator().next();
			// if (subNode instanceof MemFunctionNode) {
			// String image = ((MemFunctionNode) node).getImage();
			// if (image.contains(":toString(")) {
			// Pattern p = Pattern.compile(":([\\w\u4e00-\u9fa5]+)\\b",
			// Pattern.MULTILINE);
			// Matcher m = p.matcher(image);
			// while (m.find()) {
			// String paramName = m.group().substring(1);
			// ret.put(paramName, "''");
			// }
			// continue;
			// }
			// }
			// }
			// }
			ret.putAll(peekVarinatNode(node.childrenIterator(), nodes));
		}
		return ret;
	}

	public synchronized static Map<String, Object> ksqlParse(String sql, String dataModels) {
		String dataModel = "/system/data";
		File file = null;
		try {
			if (Utils.isNotEmptyString(dataModels)) {
				String[] args = dataModels.split(",");
				com.butone.x5.model.element.Model model = new com.butone.x5.model.element.Model();
				for (String arg : args) {
					if (!model.getIncludeList().contains(arg))
						model.getIncludeList().add(arg);
				}
				dataModel = "/base/test/data/" + CommonUtils.createGUID();
				String fileName = FileSystemWrapper.instance().getRealPath(dataModel + "/model.config.xml");
				file = new File(fileName);
				file.getParentFile().mkdirs();

				OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file), "utf-8");
				osw.write(new String(JaxbUtils.marshal(model, "utf-8"), "utf-8"));
				osw.flush();
				osw.close();

			}

			Map<String, Object> ret = new HashMap<String, Object>();
			KSQLParser parser = new KSQLParser();
			Node node = parser.parse(sql);
			List<VariableNode> nodes = new ArrayList<VariableNode>();
			Map<String, Object> paramValues = peekVarinatNode(node.childrenIterator(), nodes);
			Map<String, Object> varMap = new HashMap<String, Object>();
			List<String> paramNames = new ArrayList<String>();
			for (VariableNode p : nodes) {
				varMap.put(p.getName(), "");
				paramNames.add(p.getName());
			}
			paramNames.addAll(paramValues.keySet());
			ret.put("paramNames", paramNames);

			varMap.putAll(paramValues);
			while (sql.contains(":inClause(")) {
				int i = sql.indexOf(":inClause(");
				String expr = sql.substring(i + 1, sql.indexOf(")", i) + 1);
				String in = (String) ExpressEngine.calculate(expr, paramValues, ModelUtils.getModel("/base/core/logic/fn"));
				sql = sql.substring(0, i) + in + sql.substring(sql.indexOf(")", i) + 1);
			}

			Table table = KSQL.select(sql, varMap, dataModel, ModelUtils.getModel("/base/core/logic/fn"));
			TableMetaData tableMeta = table.getMetaData();
			List<Map<String, Object>> columns = new ArrayList<Map<String, Object>>();
			for (ColumnMetaData columnMeta : tableMeta.getColumnMetaDatas()) {
				Map<String, Object> column = new HashMap<String, Object>();
				column.put("name", columnMeta.getName());
				column.put("type", columnMeta.getType());
				if (columnMeta.getDefineModelObject() != null)
					column.put("label", columnMeta.getDefineModelObject().getLabel("zh_CN"));
				else
					column.put("label", columnMeta.getName());
				columns.add(column);
			}
			ret.put("columns", columns);

			return ret;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			if (!"/system/data".equals(dataModel)) {
				try {
					ModelUtils.unloadModel(dataModel);
				} catch (Exception e) {
				}
				clearDirectory(new File(FileSystemWrapper.instance().getRealPath(dataModel)));
			}
		}
	}

	private static void clearDirectory(File dir) {
		File[] files = dir.listFiles();
		if (files != null)
			for (File file : files) {
				if (file.isDirectory()) {
					clearDirectory(file);
				}
				forceDelete(file);
			}
		forceDelete(dir);
	}

	private static void forceDelete(File f) {
		int tryCount = 0;
		while (tryCount < 10 && f.exists()) {
			if (f.delete()) {
				System.out.println("delete test file : " + f.getPath());
				return;
			} else
				System.gc();
			tryCount++;
		}
	}

	public static List<String> queryActivityVisibleForms(String bizRecId, List<String> tableNames, Map<String, Object> variables) {
		ActionContext actionContext = ContextHelper.getActionContext();
		Activity activity = actionContext.getActivity();
		JSONObject formsConfig = ModelExtUtils.getActivityForms(activity);
		if (formsConfig == null)
			return null;
		List<String> ret = new ArrayList<String>();
		ProcessLogicPluginContext context = ProcessLogicPluginContext.findLogicPluginContext(actionContext.getActivity(), bizRecId);
		boolean releaseContext = context == null;
		if (releaseContext)
			context = ProcessLogicPluginContext.createLogicPluginContext(actionContext.getProcess(), actionContext.getActivity(), bizRecId);
		try {
			JSONArray dependBizTables = formsConfig.getJSONArray("dependBizTables");
			if (tableNames != null)
				dependBizTables.addAll(tableNames);

			for (int i = 0; i < dependBizTables.size(); i++) {
				String tableName = dependBizTables.getString(i);
				context.loadBizTable(tableName);
			}

			JSONArray forms = formsConfig.getJSONArray("forms");
			Model fnModel = ModelUtils.getModel("/base/core/logic/fn");
			for (int i = 0; i < forms.size(); i++) {
				JSONObject obj = forms.getJSONObject(i);
				String visibleExpr = obj.getString("visibleExpr");
				if (Utils.isEmptyString(visibleExpr) || ExpressEngine.calculateBoolean(visibleExpr, variables, false, fnModel)) {
					ret.add(obj.getString("id"));
				}
			}
		} finally {
			if (releaseContext)
				ProcessLogicPluginContext.removeLogicPluginContext(context, false);
		}
		return ret;
	}

	/**
	 * 判断
	 * 
	 * @param bizRecId
	 * @param tableNames
	 * @param variables
	 * @return
	 */
	public static List<String> queryActivityVisibleTrigger(String bizRecId, Map<String, Object> variables) {
		ActionContext actionContext = ContextHelper.getActionContext();
		Activity activity = actionContext.getActivity();
		Map<String, Object> variants = new HashMap<String, Object>();
		if (variables != null)
			variants.putAll(variables);
		variants.put("bizRecId", bizRecId);

		List<String> listRet = new ArrayList<String>();
		JSONObject uiLogicPluginURIs = ModelExtUtils.getActivityUiLogicPluginURIs(activity);
		if (uiLogicPluginURIs == null)
			return null;

		ProcessLogicPluginContext context = ProcessLogicPluginContext.findLogicPluginContext(actionContext.getActivity(), bizRecId);
		boolean releaseContext = context == null;
		if (releaseContext)
			context = ProcessLogicPluginContext.createLogicPluginContext(actionContext.getProcess(), actionContext.getActivity(), bizRecId);
		try {
			JSONArray dependBizTables = uiLogicPluginURIs.getJSONArray("dependBizTables");
			for (int i = 0; i < dependBizTables.size(); i++) {
				String tableName = dependBizTables.getString(i);
				context.loadBizTable(tableName);
			}

			JSONArray uiLogicPlugins = uiLogicPluginURIs.getJSONArray("uiLogicPlugins");
			Model fnModel = ModelUtils.getModel("/base/core/logic/fn");

			for (int i = 0; i < uiLogicPlugins.size(); i++) {
				JSONObject obj = uiLogicPlugins.getJSONObject(i);
				String visibleExpr = obj.getString("visibleExpr");
				if (Utils.isEmptyString(visibleExpr) || ExpressEngine.calculateBoolean(visibleExpr, variables, false, fnModel)) {
					listRet.add(obj.getString("id"));
				}
			}
		} finally {
			if (releaseContext)
				ProcessLogicPluginContext.removeLogicPluginContext(context, false);
		}
		return listRet;
	}

	public static String getConceptStoreInModel(String path, Boolean checkStatus) throws DocumentException {
		File file = new File(FileSystemWrapper.instance().getRealPath(path));
		List<String> tables = new ArrayList<String>();
		loadAllConceptStore(file, tables, checkStatus);
		if (file.getName().endsWith("_X")) {
			loadAllConceptStore(new File(file.getPath().substring(0, file.getPath().length() - 2)), tables, checkStatus);
		}
		StringBuffer sb = new StringBuffer();
		for (String t : tables) {
			sb.append("<table>").append(t).append("</table>").append("\n");
		}
		return sb.toString();

	}

	private static void loadAllConceptStore(File file, List<String> tables, Boolean checkStatus) throws DocumentException {
		List<String> waitCheckTables = new ArrayList<String>();
		if (file.isFile()) {
			if (file.getName().endsWith(".mapping.m")) {
				SAXReader reader = new SAXReader();
				Document doc = reader.read(file);
				@SuppressWarnings("rawtypes")
				Iterator itor = doc.getRootElement().elementIterator("store");
				while (itor.hasNext()) {
					Element store = (Element) itor.next();
					String tableName = store.attributeValue("name");
					Element mapping = (Element) doc.getRootElement().selectSingleNode(
							"*[local-name()='mapping' and @concept='" + store.attributeValue("name") + "']");

					if (mapping != null) {
						Element table = (Element) mapping.selectSingleNode("*[local-name()='table' and @type='owner-table']");
						if (table != null) {
							tableName = table.attributeValue("name");
						}
					}
					if (!tables.contains(tableName)){
						if(checkStatus)
							waitCheckTables.add(tableName);
						else
							tables.add(tableName);
					}
				}
				if (checkStatus && !waitCheckTables.isEmpty())
					checkTableIsCreated(waitCheckTables, tables);
			}
		} else {
			if (file.getName().endsWith("_Deprecated"))
				return;
			File[] subFiles = file.listFiles();
			for (File subFile : subFiles) {
				loadAllConceptStore(subFile, tables, checkStatus);
			}
		}
	}

	/**
	 * 检查工作表是否已创建物理表
	 * @return
	 */
	private static void checkTableIsCreated(List<String> waitCheckTables,List<String> tables){
		HashMap<String, String> sqlMap = new HashMap<String, String>();
		String sql = "select wm_concat(table_name) from all_tables where table_name in ('"+StringUtils.join(waitCheckTables.toArray(),"','").toUpperCase()+"')";
		sqlMap.put(DatabaseProduct.DEFAULT.name(), sql);
		Table tab = SQL.select(sqlMap, null, FlowBizConsts.DATA_MODEL_SYSTEM);
		String tableNames = tab.iterator().next().getString(0)+",";
		if(Utils.isNotEmptyString(tableNames)){
			for(String tableName:waitCheckTables){
				if(tableNames.indexOf(tableName.toUpperCase()+",")==-1)
					tables.add(tableName);
			}
		} else 
			tables.addAll(waitCheckTables);
	}
}