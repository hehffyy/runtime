import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.butone.extend.BizRuleExt;
import com.butone.extend.CacheManager;
import com.butone.flowbiz.BizRuleRuntime;
import com.butone.flowbiz.FlowBizConsts;
import com.butone.logic.impl.ProcessLogicPluginContext;
import com.butone.logic.impl.TableControlObject;
import com.butone.model.BizRule;
import com.justep.exception.BusinessException;
import com.justep.model.Activity;
import com.justep.model.ModelUtils;
import com.justep.system.context.ActionContext;
import com.justep.system.context.ContextHelper;
import com.justep.system.data.Row;
import com.justep.system.data.Table;
import com.justep.util.Utils;

/**
 * 逻辑插件环境工具
 * 
 * @author Administrator
 * 
 */
public class LogicPluginContextUtils {
	/**
	 * 获得表数据
	 * 
	 * @param objectId
	 * @param propName
	 * @return
	 */
	public static Object tableData(String objectId, String propName) {
		return ProcessLogicPluginContext.getTableControlObjectCurrentValue(objectId, propName);
	}

	/**
	 * 清空工作表数据
	 * 
	 * @param objectId
	 * @return
	 */
	public static long clearTableData(String objectId) {
		return ProcessLogicPluginContext.findTableControlObject(objectId).clear();
	}

	/**
	 * 获得表中记录数
	 * 
	 * @param objectId
	 * @return
	 */
	public static Integer tableRecordCount(String objectId) {
		return ProcessLogicPluginContext.getTableControlObjectRecordCount(objectId);
	}

	/**
	 * 执行当前业务逻辑组件
	 * 
	 * @param uri
	 * @param task
	 * @param variants
	 * @return
	 */
	public static Object executeCurrentBizLogicPlugin(String uri, String bizRecId, Map<String, Object> variants, Map<String, String> filters) {
		ActionContext actionContext = ContextHelper.getActionContext();
		return ProcessLogicPluginContext.executeBizLogicPlugin(uri, actionContext.getProcess().getFullName(), actionContext.getActivity().getName(),
				bizRecId, variants, filters);
	}

	/**
	 * 执行目标业务逻辑组件
	 * 
	 * @param uri
	 * @param task
	 * @param variants
	 * @return
	 */
	public static Object executeTargetBizLogicPlugin(String uri, String targetProcess, String targetActivity, String bizRecId,
			Map<String, Object> variants, Map<String, String> filters) {
		return ProcessLogicPluginContext.executeBizLogicPlugin(uri, targetProcess, targetActivity, bizRecId, variants, filters);
	}

	/**
	 * 获得Table实例对象
	 * 
	 * @param objectId
	 * @return
	 */
	public static Table tableInstance(String objectId) {
		return ProcessLogicPluginContext.getTableControlObjectTarget(objectId);
	}

	/**
	 * 设置Table游标
	 * @param objectId
	 * @param index
	 */
	public static void setTableCursor(String objectId, int index) {
		ProcessLogicPluginContext.setTableControlObjectCursor(objectId, index);
	}

	public static void printTableState(String objectId) {
		TableControlObject obj = ProcessLogicPluginContext.findTableControlObject(objectId);
		obj.rowInfoOut();
	}

	public static String getPluginContextBizRecId() {
		return ProcessLogicPluginContext.getCurrentPluginContext().getBizRecId();
	}

	/**
	 * 获得表当前行
	 * 
	 * @param objectId
	 * @return
	 */
	public static Row tableRow(String objectId) {
		return ProcessLogicPluginContext.getTableControlObjectCurrentRow(objectId);
	}

	/**
	 * 获得表当前行状态
	 * 
	 * @param objectId
	 * @return
	 */
	public static String tableRowState(String objectId) {
		Row r = ProcessLogicPluginContext.getTableControlObjectCurrentRow(objectId);
		return r != null ? r.getState().name() : null;
	}

	/**
	 * 给当前行字段设置值
	 * 
	 * @param objectId
	 * @param propName
	 * @param propValue
	 */
	public static void setTableRowValue(String objectId, String propName, String propValue) {
		if (tableInstance(objectId) != null && tableRow(objectId) == null) {
			Row row = tableInstance(objectId).iterator().next();
			row.setValue(propName, propValue);
		} else {
			tableRow(objectId).setValue(propName, propValue);
		}
	}

	/**
	 * 刷新LogicProcess内部数据集
	 * 
	 * @param objectId
	 */
	public static void refreshLogicProcessInnerTable(String objectId) {
		ProcessLogicPluginContext.refreshLogicProcessInnerControlObject(objectId);
	}

	/**
	 * 获得插件执行上下文参数
	 * 
	 * @param name
	 * @param parent
	 * @return
	 */
	public static Object getContextParameter(String name, boolean parent) {
		return ProcessLogicPluginContext.getContextParameter(name, parent);
	}

	/**
	 * 设置插件执行上下文参数
	 * 
	 * @param name
	 * @param value
	 */
	public static void setContextParameter(String name, Object value) {
		ProcessLogicPluginContext.setContextParameter(name, value);
	}

	/**
	 * 设置案卷字段值
	 * 
	 * @param name
	 * @param value
	 */
	public static void setBizRecValue(String name, Object value) {
		Table t = tableInstance(FlowBizConsts.CONCEPT_BizRec);
		if (t == null) {
			throw new BusinessException("当前插件环境中未加载案卷表");
		}
		if (t.size() == 0) {
			throw new BusinessException("案卷表记录数为0，无法设置值");
		}
		Row r = t.iterator().next();
		Collection<String> fields = t.getMetaData().getColumnNames();
		if (fields.contains(name)) {
			r.setValue(name, value);
		} else {
			for (String f : fields) {
				if (f.equalsIgnoreCase(name)) {
					r.setValue(f, value);
					return;
				}
			}
			throw new BusinessException("案卷表不存在字段" + name);
		}
	}

	/**
	 * 设置案卷多个字段值
	 * 
	 * @param name
	 * @param value
	 */
	@SuppressWarnings("rawtypes")
	public static void setBizRecValues(List names, List values) {
		if (names == null || values == null) {
			throw new BusinessException("setBizRecValues参数不允许为空");
		} else if (names.size() != names.size()) {
			throw new BusinessException("setBizRecValues 字段个数与字段值个数不匹配");
		}
		for (int i = 0; i < names.size(); i++) {
			setBizRecValue((String) names.get(i), values.get(i));
		}
	}

	/**
	 * 获得当前案卷字段值
	 * 
	 * @param name
	 */
	public static Object getBizRecValue(String name) {
		Table t = tableInstance(FlowBizConsts.CONCEPT_BizRec);
		if (t.size() == 0) {
			throw new BusinessException("案卷表记录数为0，无法设置值");
		}
		Collection<String> fields = t.getMetaData().getColumnNames();
		Row r = t.iterator().next();
		if (fields.contains(name))
			return r.getValue(name);
		else {
			for (String f : fields) {
				if (f.equalsIgnoreCase(name)) {
					return r.getValue(f);
				}
			}
			throw new BusinessException("案卷表不存在字段" + name);
		}
	}

	@SuppressWarnings("unchecked")
	public static void saveTables(boolean ignoreException, boolean emptyData, Object tableNames) throws Exception {
		Utils.check(tableNames != null, "saveTable函数参数tableNames不允许为空");
		List<String> tables;
		if (tableNames instanceof List) {
			tables = (List<String>) tableNames;
		} else if (tableNames instanceof String) {
			tables = Arrays.asList(((String) tableNames).split(","));
		} else if (tableNames instanceof String[]) {
			tables = Arrays.asList((String[]) tableNames);
		} else {
			throw new IllegalArgumentException("saveTables函数参数tableNames不支持类型" + tableNames.getClass().getName());
		}
		ProcessLogicPluginContext.saveTables(tables, ignoreException, emptyData);
	}

	/**
	 * 检查业务规则
	 * @param targetProcess
	 * @param targetActivity
	 * @param bizRecId
	 * @param variants
	 * @param filters
	 * @param urls
	 * @return
	 */
	public static List<Map<String, Object>> checkTargetBizRules(String targetProcess, String targetActivity, String bizRecId,
			Map<String, Object> variants, Map<String, String> filters, String... urls) {

		com.justep.model.Process bizProcess = Utils.isEmptyString(targetProcess) ? ContextHelper.getActionContext().getProcess() : ModelUtils
				.getProcess(targetProcess);
		Activity bizActivity = Utils.isEmptyString(targetProcess) ? ContextHelper.getActionContext().getActivity() : bizProcess
				.getActivity(targetActivity);
		ProcessLogicPluginContext context = ProcessLogicPluginContext.findLogicPluginContext(bizActivity, bizRecId);
		boolean doRelease = context == null;
		if (doRelease) {
			context = ProcessLogicPluginContext.createLogicPluginContext(bizProcess, bizActivity, bizRecId);
		}
		try {
			// 设置过滤
			if (filters != null)
				context.addBizDataFilters(filters);
			// 添加变量
			if (variants != null)
				context.addParameters(variants);
			return checkBizRules(context, urls);
		} finally {
			if (doRelease) {
				ProcessLogicPluginContext.removeLogicPluginContext(context, true);
			}
		}
	}

	private static List<Map<String, Object>> checkBizRules(ProcessLogicPluginContext pluginContext, String... urls) {
		List<Map<String, Object>> ret = new ArrayList<Map<String, Object>>();
		BizRuleRuntime bizRuleRuntime = new BizRuleRuntime(pluginContext);
		for (String url : urls) {
			BizRuleExt ext = CacheManager.getBizInfo(url.substring(0, url.indexOf("/bizRule/"))).getBizRule(url);
			bizRuleRuntime.setBizRuleEx(ext);
			if (bizRuleRuntime.checkBizRule()) {
				BizRule rule = ext.getBizRule();
				Map<String, Object> ruleRsult = new HashMap<String, Object>();
				ruleRsult.put("guid", rule.getGuid());
				ruleRsult.put("type", rule.getKind());
				ruleRsult.put("name", rule.getName());
				boolean stop = "提示规则".equals(rule.getKind()) ? false : true;
				ruleRsult.put("stop", stop);
				ruleRsult.put("message", bizRuleRuntime.getBizRuleTipInfo());
				ret.add(ruleRsult);
			}
		}
		return ret;
	}

	public static String joinBizRuleMessages(List<Map<String, Object>> ruleInfos, String separator) {
		StringBuffer sb = new StringBuffer();
		for (Map<String, Object> info : ruleInfos) {
			if (info.get("message") != null) {
				sb.append(separator).append((String) info.get("message"));
			}
		}
		if (sb.length() > separator.length())
			return sb.substring(sb.length());
		return "";
	}
}