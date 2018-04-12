import java.sql.*;
import java.util.*;

import javax.naming.NamingException;

//import com.justep.doc.DocUtils;
import org.apache.commons.lang.StringUtils;
import com.justep.system.context.ContextHelper;
import com.justep.system.data.*;
import com.justep.util.Utils;

public class Receive {

	private final static String DATAMODEL = "/common/receive/data";

	/**
	 * 获取widget内容信息
	 * 
	 * @param personId
	 * @param title
	 * @return
	 * @throws NamingException
	 * @throws SQLException
	 */
	public static Table getRecInfo(String personId, String title) throws NamingException, SQLException {

		Map<String, String> map = new HashMap<String, String>();
		String sql = "select ca.fkzy, ca.fcatalogname,re.fName,re.fUrl,re.fprocess,re.factivity,re.fpicture from sa_catalog ca,SA_Receive re,SA_OPPermission op\n" + " where op.spermissionroleid in\n"
				+ "(select distinct sauthorizeroleid from Sa_Oporg a,SA_OPAuthorize b where a.spersonid='" + personId + "'\n" + "and sFID like (b.sorgfid||'%'))\n"
				+ "and op.sprocess = re.fprocess and op.sActivity = re.fActivity and ca.fid=re.fwjid and ca.fcatalogname ='" + title + "'\n" + // and rownum <= 5
				"order by ca.fkzy";

		map.put(DatabaseProduct.ORACLE.name(), sql);
		return SQL.select(map, null, "/SA/receive/data");
	}

	/**
	 * 获取标题信息
	 * 
	 * @param personId
	 * @return
	 */
	public static Table getRecTitle(String personId) {

		Map<String, String> map = new HashMap<String, String>();
		String sql = "select ca.fkzy, ca.fcatalogname from sa_catalog ca,SA_Receive re,SA_OPPermission op\n" + " where op.spermissionroleid in\n"
				+ "(select distinct sauthorizeroleid from Sa_Oporg a,SA_OPAuthorize b where a.spersonid='" + personId + "'\n" + "and sFID like (b.sorgfid||'%'))\n"
				+ "and op.sprocess = re.fprocess and op.sActivity = re.fActivity and ca.fid=re.fwjid\n" + "group by ca.fkzy, ca.fcatalogname\n" + "order by ca.fkzy";
		map.put(DatabaseProduct.ORACLE.name(), sql);
		return SQL.select(map, null, "/SA/receive/data");
	}

	/**
	 * 获取图片路径
	 * 
	 * @param docPath
	 * @param fileID
	 */
	public static String getImageUrl(String docPath, String fileID) {
		if (Utils.isEmptyString(docPath) || Utils.isEmptyString(fileID)) {
			return "/x5/UI/SA/receive/process/receive/images/chart-up.gif";
		}
		return "/x5/UI/SA/receive/process/receive/images/chart-up.gif"; // "/x5"+DocUtils.getDocViewUrl(docPath,
																		// fileID);
	}

	/**
	 * 目录添加业务功能
	 * 
	 * @param processes
	 * @param activitiesFNames
	 * @param fBusinessGroupId
	 */
	public static void setTreeCatalog(List activities, List processes, List activitiesFNames, String fBusinessGroupId, Integer flevel) {

		for (int i = 0; i < processes.size(); i++) {
			String activity = activities.get(i).toString();
			String process = processes.get(i).toString();
			String activityFName = activitiesFNames.get(i).toString();
			String[] array = activityFName.split("/");

			Table funcTable = KSQL.select("SELECT p.*  FROM B_Catalog p where  1=2  ", null, DATAMODEL, null);
			funcTable.getMetaData().setStoreByConcept("B_Catalog", true);
			funcTable = BizData.create(funcTable, "B_Catalog", null, DATAMODEL);
			Row rec = funcTable.iterator().next();
			rec.setString("fParent", fBusinessGroupId);
			rec.setInteger("fLevel", flevel + 1);
			rec.setString("fCatalogName", array[array.length - 1]);

			int count = funcTable.save(DATAMODEL);
			if (count > 0) {
				String ksql = "select t as FID from B_Catalog t where t.fParent = '" + fBusinessGroupId + "'";
				Table listTable = KSQL.select(ksql, null, DATAMODEL, null);
				Iterator<Row> it = listTable.iterator();
				Table receiveTable = null;
				while (it.hasNext()) {
					String zID = it.next().getString("FID");

					receiveTable = KSQL.select("SELECT p.*  FROM B_Receive p where  1=2  ", null, DATAMODEL, null);
					receiveTable.getMetaData().setStoreByConcept("B_Receive", true);
					receiveTable = BizData.create(receiveTable, "B_Receive", null, DATAMODEL);
					Row rt = receiveTable.iterator().next();
					rt.setString("fActivity", activity);
					rt.setString("fProcess", process);
					rt.setString("fName", array[array.length - 1]);
					rt.setString("fUrl", getURL(process, activity));
					rt.setString("fWJID", zID);
				}
				receiveTable.save(DATAMODEL);
			}
		}
	}

	/**
	 * 收件添加业务功能
	 * 
	 * @param processes
	 * @param activitiesFNames
	 * @param fBusinessGroupId
	 * @param activities
	 */
	public static void setTreeReceive(String fBusinessGroupId, List processes, List activitiesFNames, List activities) {
		for (int i = 0; i < processes.size(); i++) {
			String activity = activities.get(i).toString();
			String process = processes.get(i).toString();
			String activityFName = activitiesFNames.get(i).toString();
			String[] array = activityFName.split("/");

			Table funcTable = KSQL.select("SELECT p.*  FROM B_Receive p where  1=2  ", null, DATAMODEL, null);
			funcTable.getMetaData().setStoreByConcept("B_Receive", true);
			funcTable = BizData.create(funcTable, "B_Receive", null, DATAMODEL);
			Row rec = funcTable.iterator().next();
			rec.setString("fActivity", activity);
			rec.setString("fProcess", process);
			rec.setString("fName", array[array.length - 1]);
			rec.setString("fUrl", getURL(process, activity));
			rec.setString("fWJID", fBusinessGroupId);

			funcTable.save(DATAMODEL);

		}
	}

	private static String getURL(String sprocess, String activity) {
		String[] sprocessArray = sprocess.split("/");
		sprocessArray[0] = "/UI";
		sprocessArray[sprocessArray.length - 1] = activity + ".w";
		return StringUtils.join(sprocessArray, "/");

	}

}