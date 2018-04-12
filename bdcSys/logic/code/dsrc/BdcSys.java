import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.butone.data.BizDataUtils;
import com.butone.data.SQLUtils;
import com.butone.extend.CacheManager;
import com.butone.model.xmlconfig.TableConfig;
import com.butone.model.xmlconfig.TableConfig.Parameter;
import com.justep.model.Concept;
import com.justep.model.Model;
import com.justep.model.ModelUtils;
import com.justep.model.Relation;
import com.justep.system.data.BizData;
import com.justep.system.data.Table;
import com.justep.system.data.TableUtils;
import com.justep.system.process.ExpressEngine;
import com.justep.util.Utils;

public class BdcSys {

	private static final String fnModel = "/base/core/logic/fn";
	private static final String dataModel = "/bdcSys/data";

	public static Table queryBDC_H(String zrzid) throws Exception {
		Model model = ModelUtils.getModel(dataModel);
		Concept concept = model.getUseableConcept("BDC_H");
		TableConfig config = CacheManager.getConceptTableConfig(concept);
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("zrzid", zrzid);
		return query(config, "BDC_H", "HID", variables);
	}

	private static Table query(TableConfig config, String conceptName, String idColumn, Map<String, Object> variables) {
		Model model = ModelUtils.getModel(dataModel);
		Concept concept = model.getUseableConcept(conceptName);
		Map<String, Object> sqlParams = new HashMap<String, Object>();
		sqlParams.putAll(variables);
		// 计算参数表达式
		for (Parameter param : config.getParameters()) {
			if (Utils.isNotEmptyString(param.getExpr()) && !sqlParams.containsKey(param.getName())) {
				sqlParams.put(param.getName(), ExpressEngine.calculate(param.getExpr(), null, ModelUtils.getModel(fnModel)));
			}
		}

		// 字段解析
		String realColumns = "";
		List<String> names = new ArrayList<String>();
		List<String> types = new ArrayList<String>();
		for (Relation r : concept.getRelations()) {
			String dataType = r.getDataType();
			realColumns += r.getName() + ",";
			names.add(r.getName());
			types.add(dataType);
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
			String sql = "select " + (realColumns.length() > 0 ? realColumns : "*") + " from (" + config.getSelect() + ") " + conceptName;
			if (Utils.isNotEmptyString(config.getOrderBy())) {
				sql += "\n" + config.getOrderBy();
			}

			List<Object> paramList = SQLUtils.parseSqlParameters(sql, sqlParams);
			String fixSql = SQLUtils.fixSQL(sql, sqlParams, true);
			table = SQLUtils.select(fixSql, paramList, model, 0, -1, table);
		} else {
			String orderBy = config.getOrderBy();
			String condition = config.getCondition();
			String ksqlSelect = config.getSelect();
			if (!("," + ksqlSelect + ",").contains("," + idColumn + ",")) {
				ksqlSelect = idColumn + "," + ksqlSelect;
			}
			if (realColumns.length() > 0 && !("," + realColumns + ",").contains("," + idColumn + ",")) {
				realColumns = idColumn + "," + realColumns;
			}
			table = BizData.query(conceptName, idColumn, SQLUtils.fixSQL(ksqlSelect, sqlParams, false),
					SQLUtils.fixSQL(config.getFrom(), sqlParams, false), SQLUtils.fixSQL(condition, sqlParams, false), null, null, false, 0, -1,
					realColumns, orderBy, null, null, sqlParams, dataModel, fnModel);
			String keyColumn = BizDataUtils.getTableKeyColumns(idColumn, table);
			if (Utils.isNotEmptyString(keyColumn)) {
				table.getMetaData().setKeyColumn(keyColumn);
			}
		}
		return table;
	}
}