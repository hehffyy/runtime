import java.util.HashMap;
import java.util.Map;

import com.justep.system.data.DatabaseProduct;
import com.justep.system.data.SQL;
import com.justep.system.data.Table;

public class UnitTypeProcedure {

	
	public static Table selectSysDictInfo(){
		String ksql = "select  B_SysDictItem.FNAME as FNAME from B_SysDictItem B_SysDictItem where B_SysDictItem.FTYPE='单位字段类型'";
		Map<String, String> map = new HashMap<String, String>();
		map.put(DatabaseProduct.DEFAULT.name(), ksql);
		Table listTable = SQL.select(map, null, "/system/data");
		return  listTable;
	}
 
}