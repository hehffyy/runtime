import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.justep.model.Config;
import com.justep.model.ConfigItem;
import com.justep.model.Model;
import com.justep.model.ModelUtils;
import com.justep.system.action.ActionUtils;
import com.justep.system.context.ContextHelper;
import com.justep.system.data.Row;
import com.justep.system.data.Table;
import com.justep.system.data.TableUtils;
import com.justep.system.opm.Operator;
import com.justep.util.Utils;

public class Portal {

	public static Table queryMessage(List range, String concept, String select, String from, String aggregate, String dataModel, String fnModel,
			String condition, Boolean distinct, String idColumn, String filter, Integer limit, Integer offset, String columns, String orderBy,
			String aggregateColumns, Map variables) {
		Model m = ModelUtils.getModel("/portal/logic/action");
		Config cfg = m.getUseableConfig("messageConfig");
		ConfigItem items = cfg.getItem("items");

		Table result = TableUtils.createTable(concept, dataModel);
		result.getProperties().put(Table.PROP_NAME_ROWID, concept);
		try {
			for (String name : items.getNames()) {
				ConfigItem item = items.getChildren(name);
				String process = item.getChildren("process").getValue();
				String activity = item.getChildren("activity").getValue();
				String action = item.getChildren("action").getValue();
				String executor = ActionUtils.getRequestContext().getActionContext().getExecutor();
				if (Utils.isEmptyString(executor))
					executor = "*";
				if (canAccess(process, activity)) {
					try {
						@SuppressWarnings("unchecked")
						// long t = System.currentTimeMillis();
						Map<String, Object> data = (Map<String, Object>) ActionUtils.invokeAction(process, activity, action, executor,
								new HashMap<String, Object>());
						if (data != null) {
							Row row = result.appendRow(name);
							row.setString("fLabel", (String) data.get("fLabel"));
							row.setString("fSubLabel", (String) data.get("fSubLabel"));
							row.setString("fContent", (String) data.get("fContent"));
							row.setString("fTag", (String) data.get("fTag"));
							row.setDateTime("fDate", (Timestamp) data.get("fDate"));
							row.setInt("fCount", (Integer) data.get("fCount"));
							row.setString("fProcess", (String) data.get("fProcess"));
							row.setString("fActivity", (String) data.get("fActivity"));
							row.setString("fUrl", (String) data.get("fUrl"));
						}
						//System.out.println(action + " : " + (System.currentTimeMillis() - t));
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			}
		} catch (Exception e) {
			Logger.getLogger(Portal.class).error(e.getMessage() + "", e);
		}

		return result;
	}

	public static Table queryWaitMessage(List range, String concept, String select, String from, String aggregate, String dataModel, String fnModel,
			String condition, Boolean distinct, String idColumn, String filter, Integer limit, Integer offset, String columns, String orderBy,
			String aggregateColumns, Map variables) {

		Model m = ModelUtils.getModel("/portal/logic/action");
		Config cfg = m.getUseableConfig("messageConfig");
		ConfigItem items = cfg.getItem("items");

		Table result = TableUtils.createTable(concept, dataModel);
		
		try {
			ConfigItem item = items.getChildren("TaskWaiting");
			if (null != item) {
				String process = item.getChildren("process").getValue();
				String activity = item.getChildren("activity").getValue();
				String action = item.getChildren("action").getValue();
				String executor = ActionUtils.getRequestContext().getActionContext().getExecutor();
				if (Utils.isEmptyString(executor))
					executor = "*";

				if (canAccess(process, activity)) {
					@SuppressWarnings("unchecked")
					Map<String, Object> data = (Map<String, Object>) ActionUtils.invokeAction(process, activity, action, executor,
							new HashMap<String, Object>());
					if (data != null) {
						Row row = result.appendRow();
						row.setString("fLabel", (String) data.get("fLabel"));
						row.setString("fSubLabel", (String) data.get("fSubLabel"));
						row.setString("fContent", (String) data.get("fContent"));
						row.setString("fTag", (String) data.get("fTag"));
						row.setDateTime("fDate", (Timestamp) data.get("fDate"));
						row.setInt("fCount", (Integer) data.get("fCount"));
						row.setString("fProcess", (String) data.get("fProcess"));
						row.setString("fActivity", (String) data.get("fActivity"));
						row.setString("fUrl", (String) data.get("fUrl"));
					}
				}
			}
		} catch (Exception e) {
			Logger.getLogger(Portal.class).error(e.getMessage() + "", e);
		}

		return result;
	}

	private static boolean canAccess(String process, String activity) {
		try {
			Operator op = ContextHelper.getOperator();
			return op.hasActivityPermission(process, activity, "*");
		} catch (Exception e) {
			return false;
		}
	}
}