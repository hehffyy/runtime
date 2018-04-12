import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.butone.codeinf.model.CodeDef;
import com.butone.codeinf.util.CodeGenerator;
import com.butone.data.SQLUtils;
import com.butone.extend.CacheManager;
import com.butone.extend.DataReplication;
import com.butone.flowbiz.FlowBizConsts;
import com.butone.sequencecode.SequenceCodeImpl;
import com.butone.spi.FlowControlUtils;
import com.justep.exception.BusinessException;
import com.justep.model.Activity;
import com.justep.model.ModelUtils;
import com.justep.system.context.ContextHelper;
import com.justep.system.data.DatabaseProduct;
import com.justep.system.data.Expression;
import com.justep.system.data.KSQL;
import com.justep.system.data.ModifyState;
import com.justep.system.data.Row;
import com.justep.system.data.SQL;
import com.justep.system.data.Table;
import com.justep.system.opm.OrgUnit;
import com.justep.system.opm.OrgUtils;
import com.justep.system.transform.Utils;
import com.justep.system.util.CommonUtils;

public class Gwbl {

	public static Map<String, Object> shoujian() {

		Map<String, String> authPermession = new HashMap<String, String>();
		StringBuffer sql_info = new StringBuffer();

		sql_info.append("Select distinct  a.fname , b.flengthname,d.cnt ,a.fACTIVITY , a.fProcess,b.fkzy From  B_Receive a Inner Join ( ")
				.append("select a.fid, SUBSTR(SYS_CONNECT_BY_PATH(fcatalogname || '_' || fid ,'/'),0) As flengthname  , Level As lev, cast(nvl(fkzy,100) As Integer)  fkzy from B_catalog a Connect by prior fid=fparent ")
				.append(") b On a.fwjid = b.fid Left Join (Select fwjid,  Count(fwjid) cnt  From  B_Receive  Group By fwjid  ) d On a.fwjid = d.fwjid  ")
				.append(" inner join SA_OPPERMISSION  op on op.sprocess =a.fprocess and op.sactivity = a.fActivity  ")
				.append("Where  Exists  ( Select * From  (Select a.fid , max(Level) As lev From B_catalog a Connect by prior fid=fparent  Group By a.fid ")
				.append(") c Where  c.lev = b.lev And c.fid = b.fid ) order by  b.fkzy asc , b.flengthname asc ");
		authPermession.put(DatabaseProduct.DEFAULT.name(), sql_info.toString());
		Table table = SQL.select(authPermession, null, FlowBizConsts.DATA_MODEL_SYSTEM);
		Map<String, String> newPathParams = null;
		MenuGroup root = new MenuGroup();
		root.setName("root");
		List<MenuGroup> parentList = new ArrayList<MenuGroup>();
		parentList.add(root);
		setFuncTreeList(root, parentList, table, newPathParams);
		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(root);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("jsonObject", jsonObject.get("childNodes"));
		return result;
	}

	private static void setFuncTreeList(MenuGroup root, List<MenuGroup> parentList, Table table, Map<String, String> newPathParams) {

		Iterator<Row> it = table.iterator();
		String sactivityfname = "", preName = "", sprocess = "", activity = "", precatologId = "", catologId = "", catologName = "", precatologName = "";
		int cnt = 1, k = 0;

		while (it.hasNext()) {
			Row r = it.next();
			sactivityfname = r.getString("FLENGTHNAME");
			cnt = Integer.parseInt(r.getDecimal("CNT").toString());

			String[] fnameArray = sactivityfname.split("/");

			catologName = "";
			precatologName = "";
			int m = 1;
			if (fnameArray.length == 3)
				m = 2;
			else if (fnameArray.length > 3)
				m = 3;
			else if (cnt == 2)
				m = 3;

			for (int i = fnameArray.length - m; i < fnameArray.length; i++) {
				k = i;
				if (k != (fnameArray.length - m)) {
					precatologId = fnameArray[k - 1].split("_")[1];
					precatologName = fnameArray[k - 1].split("_")[0];
				}

				catologName = fnameArray[k].split("_")[0];
				catologId = fnameArray[k].split("_")[1];

				MenuGroup parentNode = null;
				if (precatologName.equals(""))
					parentNode = root;
				else
					parentNode = getNode(root, parentList, precatologName, precatologId);

				if (getNode(root, parentList, catologName, catologId) == null && i != fnameArray.length - 1) {
					if (!fnameArray[k].equals(preName)) {
						MenuGroup child = new MenuGroup();
						child.setId(catologId);
						child.setName(catologName);
						parentNode.addChildNode(child);
						parentList.add(child);
						preName = fnameArray[k];
					}
				} else if (cnt == 2 && i == fnameArray.length - 1) {

					if (getNode(root, parentList, catologName, catologId) == null) {
						MenuGroup child = new MenuGroup();
						child.setId(catologId);
						child.setName(catologName);
						parentNode.addChildNode(child);
						parentList.add(child);

						parentNode = child;
					}

					parentNode = getNode(root, parentList, catologName, catologId);

					sprocess = r.getString("FPROCESS");
					activity = r.getString("FACTIVITY");

					MenuCollection collection = new MenuCollection();
					collection.setId(catologId);
					collection.setName(r.getString("FNAME"));
					collection.setProcess(sprocess);
					collection.setActivity(activity);
					collection.setUrl(getURL(sprocess, activity));
					parentNode.addChildNode(collection);
					if (cnt == 2)
						preName = r.getString("FNAME");
					else
						preName = fnameArray[k];
				} else if (!fnameArray[k].equals(preName) && i == fnameArray.length - 1 && cnt == 1) {

					sprocess = r.getString("FPROCESS");
					activity = r.getString("FACTIVITY");
					MenuCollection collection = new MenuCollection();
					collection.setId(catologId);
					collection.setName(catologName);
					collection.setProcess(sprocess);
					collection.setActivity(activity);
					collection.setUrl(getURL(sprocess, activity));
					parentNode.addChildNode(collection);
					preName = fnameArray[k];
				}
			}

		}

	}

	private static String getURL(String sprocess, String activity) {
		String[] sprocessArray = sprocess.split("/");
		sprocessArray[0] = "/UI";
		sprocessArray[sprocessArray.length - 1] = activity + ".w";
		return StringUtils.join(sprocessArray, "/");

	}

	// TODO目前是根据名称判断父节点的,如此存在重新的情况,就有问题,这块代码需要优化
	private static MenuGroup getNode(MenuGroup root, List<MenuGroup> parentList, String name, String id) {

		for (MenuGroup child : parentList) {
			if (child.getName().equals(name) && child.getId().equals(id)) //
				return child;
		}
		return null;
	}
	
}