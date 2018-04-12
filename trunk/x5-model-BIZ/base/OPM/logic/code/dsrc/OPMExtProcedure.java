import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.justep.model.Config;
import com.justep.model.ConfigItem;
import com.justep.model.ModelUtils;
import com.justep.system.data.DataPermission;
import com.justep.system.data.Row;
import com.justep.system.data.Table;
import com.justep.system.data.TableUtils;
import com.justep.system.opm.OrgCache;
import com.justep.system.opm.OrgQuery;

public class OPMExtProcedure {

	/**
	 * 查询人员级别字典
	 * 
	 * @return
	 */
	public static Table queryPersonLevel() {
		List<String> names = new ArrayList<String>();
		names.add("name");
		names.add("code");
		List<String> types = new ArrayList<String>();
		types.add("String");
		types.add("String");
		// 创建table，names代表列，types代表列的类型
		Table personLevel = TableUtils.createTable(null, names, types);
		personLevel.getMetaData().setKeyColumn("name");
		Config config = (Config) ModelUtils.getModelObjectByFullName("/system/config/personLevel", Config.TYPE);
		for (String name : config.getNames()) {
			ConfigItem item = config.getItem(name);
			ConfigItem disable = item.getChildren("disable");
			if (disable == null || !"false".equals(disable.getValue())) {
				Row r = personLevel.appendRow(name);
				r.setString("code", item.getValue());
			}
		}
		return personLevel;
	}

}