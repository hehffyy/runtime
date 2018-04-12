import com.justep.system.data.KSQL;
import com.justep.system.data.Table;

public class sysDictProcedure {


	public static int checkIfExist(String FNAME){
		String ksql = "select e from SysDictType e where e.FNAME ='"+ FNAME +"'";
		Table table = KSQL.select(ksql, null, "/base/system/data", null);
		return table.size();
	}
	
	public static int deleteDataByID(String mainID){
		String ksql = "delete from SysDictItem m where m.FBILLID ='"+ mainID +"'";
		int result = KSQL.executeUpdate(ksql, null, "/base/system/data", null);
		return result;
	}
 
	
}