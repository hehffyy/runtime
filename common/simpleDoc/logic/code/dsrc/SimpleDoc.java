import java.math.*;
import java.sql.*;
import java.util.*;
import org.dom4j.*;

import com.butone.utils.SysUtils;
import com.justep.system.data.*;

public class SimpleDoc {


	/**
	 * 生成水印文档
	 */
	public static Object genWmDoc(String fileName,String filePath,String wmFileId) throws Exception {
		String sql="select fDocPath from B_SimpleDoc where FDocId=?";
		Object docPath = SysUtils.queryFldValue(sql, wmFileId);
		if(docPath!=null){
			SysUtils.executeSql("delete from B_SimpleDoc where FDocId=?",wmFileId);
		}
		sql = "insert into B_SimpleDoc(FDocID,FDocName,FDocPath,FCreateTime) values(?,?,?,sysdate)";
		SysUtils.executeSql(sql,wmFileId,fileName,filePath);
		return docPath;
	}
}