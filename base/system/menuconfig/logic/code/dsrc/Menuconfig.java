import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.dom4j.tree.DefaultAttribute;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.justep.system.data.BizData;
import com.justep.system.data.DatabaseProduct;
import com.justep.system.data.KSQL;
import com.justep.system.data.Row;
import com.justep.system.data.SQL;
import com.justep.system.data.Table;
import com.justep.system.util.CommonUtils;
import com.justep.util.Utils;

public class Menuconfig {
	public static String xml = "";
	public static final String dataModel = "/base/system/menuconfig/data";
	public static Document document = DocumentHelper.createDocument();

	public static String readXml(Map<String, String> variables)
			throws DocumentException {
		String type = variables.get("type");
		String fName = variables.get("fName");
		String str = getTempDir();
		String url = str.substring(0, (str.lastIndexOf("\\") - 13));
		
		String menuUrl = "UI\\appConfig\\"+fName+"\\default.function.xml";
		int addOrder = 0;
		ArrayList<String> list = new ArrayList<String>();
		if (type.equals("parseMenu")) {
			File file = new File(url + "model\\"+menuUrl);
			if (!file.exists())
				return "系统中不存在此文件："+ menuUrl;
			list.add("model\\UI\\appConfig\\default\\default.function.xml");
		} else {
			list.add("model\\UI\\appConfig\\default\\common.function.xml");
			list.add("model\\UI\\appConfig\\default\\base.function.xml");
			list.add("model\\UI\\appConfig\\default\\sys.function.xml");
		}
		deleteTreeRoot(variables);
		for (int n = 0; n < list.size(); n++) {
			String newUrl = "";
			newUrl = url + list.get(n);
			String path = newUrl;
			SAXReader reader = new SAXReader();
			Document doc = reader.read(path);
			Element root = doc.getRootElement(); // 取得根节点
			xml = "";
			xml = getChildAllText(doc, root, variables);
			String[] result = xml.split("@");
			String rootFID = "@@@";
			String fID = variables.get("fID").toString();
			for (int i = 0; i < result.length; i++) {
				JSONObject jsonObject = JSON.parseObject(result[i]);
				String fid = jsonObject.getString("fid");
				String pid = jsonObject.getString("pid") == null ? ""
						: jsonObject.getString("pid");
				String lable = jsonObject.getString("label");
				if (Utils.isEmptyString(lable)) {
					rootFID = fid;
					continue;
				} else if (rootFID.equals(pid)) {
					pid = fID;
				}
				String icon16 = jsonObject.getString("icon16") == null ? ""
						: jsonObject.getString("icon16");
				String icon32 = jsonObject.getString("icon32") == null ? ""
						: jsonObject.getString("icon32");
				String icon64 = jsonObject.getString("icon64") == null ? ""
						: jsonObject.getString("icon64");
				String display = jsonObject.getString("display") == null ? ""
						: jsonObject.getString("display");
				String activity = jsonObject.getString("activity") == null ? ""
						: jsonObject.getString("activity");
				String process = jsonObject.getString("process") == null ? ""
						: jsonObject.getString("process");
				String urlAdd = jsonObject.getString("url") == null ? ""
						: jsonObject.getString("url");
				List<Object> params = new ArrayList<Object>();
				String sql = "insert into B_Menu(FID,VERSION,FNAME,FICON16,FICON32,FICON64,FDISPLAY,FACTIVITY,FPROCESS"
						+ ",FACTIVITYURL,FPARENTID,FORDER) values(?,0,?,?,?,?,?,?,?,?,?,?)";
				params.add(fid);
				params.add(lable);
				params.add(icon16);
				params.add(icon32);
				params.add(icon64);
				params.add(display);
				params.add(activity);
				params.add(process);
				params.add(urlAdd);
				params.add(pid);
				params.add(addOrder);
				SQL.executeUpdate(sql, params, dataModel);
			}
		}
		return "成功";
	}

	/**
	 * 获取节点所有属性值 <功能详细描述>
	 * 
	 * @param element
	 * @return
	 * @throws JSONException
	 * @see [类、类#方法、类#成员]
	 */
	public static String getNoteAttribute(Element element) {
		DefaultAttribute e = null;
		List list = element.attributes();
		JSONObject json = new JSONObject();
		for (int i = 0; i < list.size(); i++) {
			e = (DefaultAttribute) list.get(i);
			json.put(e.getName(), e.getText());
		}
		return json.toString();
	}

	private static String getTempDir() {
		return System.getProperty("java.io.tmpdir");
	}

	/**
	 * 递归遍历方法 <功能详细描述>
	 * 
	 * @param element
	 * @throws JSONException
	 * @see [类、类#方法、类#成员]
	 */
	public static String getChildAllText(Document doc, Element e, Map variables) {
		if (e != null) {
			if (!e.elements().isEmpty()) {
				List<Element> list = e.elements();
				e.setAttributeValue("fid", CommonUtils.createGUID());

				if (e.getName() != "root") {
					Element elementParent = e.getParent();
					String parentid = elementParent.attributeValue("fid");
					e.setAttributeValue("pid", parentid);
				}

				xml += getNoteAttribute(e) + "@";

				for (Element el : list) {
					if (el.elements().size() > 0) {
						getChildAllText(doc, el, variables);
					} else {
						el.setAttributeValue("fid", CommonUtils.createGUID());
						Element elementParent = el.getParent();
						String parentid = elementParent.attributeValue("fid");
						el.setAttributeValue("pid", parentid);
						xml += getNoteAttribute(el) + "@";
					}
				}
			} else {

			}
		}
		return xml;
	}

	public static void insertData(String sql, List<Object> binds) {
		HashMap<String, String> sqlMap = new HashMap<String, String>();
		sqlMap.put(DatabaseProduct.ORACLE.name(), sql);
		SQL.executeUpdate(sqlMap, binds, dataModel);
	}

	public static void deletetData(String sql) {
		HashMap<String, String> sqlMap = new HashMap<String, String>();
		sqlMap.put(DatabaseProduct.ORACLE.name(), sql);
		SQL.executeUpdate(sqlMap, null, dataModel);
	}

	public static void setChildAllText(String ParentID, Element el) {
		String fName = null, fIcon16 = null, fIcon32 = null, fIcon64 = null, fDisplay = null, fActivity = null, fProcess = null, fActivityUrl = null;
		// 判断根目录下是否有子目录
		String sql = "select * from B_Menu where fParentID='" + ParentID
				+ "' order by  fOrder";
		HashMap<String, String> sqlMap = new HashMap<String, String>();
		sqlMap.put(DatabaseProduct.ORACLE.name(), sql);
		Table table = SQL.select(sqlMap, null, dataModel);
		Iterator<Row> rows = table.iterator();

		// 查询当前所有的根菜单
		String sql_1 = "select * from B_Menu  where fID='" + ParentID
				+ "' order by  fOrder";
		HashMap<String, String> sqlMap_1 = new HashMap<String, String>();
		sqlMap_1.put(DatabaseProduct.ORACLE.name(), sql_1);
		Table table_1 = SQL.select(sqlMap_1, null, dataModel);
		Iterator<Row> rows_1 = table_1.iterator();
		if (rows_1.hasNext()) {
			Row rs_1 = rows_1.next();
			fName = rs_1.getString("FNAME");
			fIcon16 = rs_1.getString("FICON16");
			fIcon32 = rs_1.getString("FICON32");
			fIcon64 = rs_1.getString("FICON64");
			fDisplay = rs_1.getString("FDISPLAY");
			fActivity = rs_1.getString("FACTIVITY");
			fProcess = rs_1.getString("FPROCESS");
			fActivityUrl = rs_1.getString("FACTIVITYURL");
		}
		if (rows.hasNext()) {
			if (el == null) {
				Element elements = document.addElement("root");
				while (rows.hasNext()) {
					Row rs1 = rows.next();
					String fid = rs1.getString("FID");
					setChildAllText(fid, elements);
				}
			} else {
				Element study = el.addElement("item");
				study.addAttribute("label", fName);
				study.addAttribute("icon16", fIcon16);
				study.addAttribute("icon32", fIcon32);
				study.addAttribute("icon64", fIcon64);
				study.addAttribute("display", fDisplay);
				study.addAttribute("process", fProcess);
				study.addAttribute("activity", fActivity);
				study.addAttribute("url", fActivityUrl);
				while (rows.hasNext()) {
					Row rs1 = rows.next();
					String fid = rs1.getString("FID");
					setChildAllText(fid, study);
				}
			}
		} else {
			Element study = el.addElement("item");
			study.addAttribute("label", fName);
			study.addAttribute("icon16", fIcon16);
			study.addAttribute("icon32", fIcon32);
			study.addAttribute("icon64", fIcon64);
			study.addAttribute("display", fDisplay);
			study.addAttribute("process", fProcess);
			study.addAttribute("activity", fActivity);
			study.addAttribute("url", fActivityUrl);
		}
	}

	// 获取根目录的FID
	public static String getRootID() {
		String result_Fid = "";
		String sql = "select * from B_Menu where B_Menu.fParentId is null";
		HashMap<String, String> sqlMap = new HashMap<String, String>();
		sqlMap.put(DatabaseProduct.ORACLE.name(), sql);
		Table table = SQL.select(sqlMap, null, dataModel);
		Iterator<Row> rows = table.iterator();
		if (rows.hasNext()) {
			Row rs = rows.next();
			result_Fid = rs.getString("FID");
		}
		return result_Fid;
	}

	// 生成菜单XML
	public static boolean generateXML(String fName, String rowID)
			throws UnsupportedEncodingException, FileNotFoundException {
		// 第一步判断是否存在文件夹
		String str = getTempDir();
		String url = str.substring(0, (str.lastIndexOf("\\") - 13));
		String newurl = url + "model\\UI\\appConfig\\" + fName + "";
		url = url + "model\\UI\\appConfig\\" + fName + "\\default.function.xml";
		File file = new File(newurl);
		if (!file.exists())
			file.mkdir();
		File xmlfile = new File(url);
		try {
			// 判断XMl是否存在
			if (!xmlfile.exists()) {
				xmlfile.createNewFile();
			}
			XMLWriter write = new XMLWriter(new FileOutputStream(new File(url)));
			document.clearContent();
			setChildAllText(rowID, null);
			write.write(document);
			write.close();
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	// 判断当前目录是否有子目录
	public static boolean hasChild(String fParentID) {
		String sql = "select * from B_Menu where B_Menu.fParentId = '"
				+ fParentID + "'";
		HashMap<String, String> sqlMap = new HashMap<String, String>();
		sqlMap.put(DatabaseProduct.ORACLE.name(), sql);
		Table table = SQL.select(sqlMap, null, dataModel);
		if (table.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public static void setTreeReceive(String fParentID, List<String> processes,
			List<String> activitiesFNames, List<String> activities,
			List<String> fIcon16, List<String> fIcon32, List<String> fIcon64,
			List<String> fDisplay) {
		for (int i = 0; i < processes.size(); i++) {
			String maxSql = "select case when max(m.forder) is null then 0 else  max(m.forder) end as fmax  from b_menu m where m.fparentid='"
					+ fParentID + "'";
			Map<String, String> maxMap = new HashMap<String, String>();
			maxMap.put(DatabaseProduct.DEFAULT.name(), maxSql);
			Table maxTable = SQL.select(maxMap, null, dataModel);
			Iterator<Row> rows = maxTable.iterator();
			int fmax = 0;
			while (rows.hasNext()) {
				Row row = rows.next();
				fmax = row.getDecimal("FMAX").intValue();
			}
			String uuid = UUID.randomUUID().toString().trim()
					.replaceAll("-", "");
			String fIcon16Rce = fIcon16.get(i).toString().length() == 0 ? ""
					: fIcon16.get(i).toString();
			String fIcon32Rce = fIcon32.get(i).toString().length() == 0 ? ""
					: fIcon32.get(i).toString();
			String fIcon64Rce = fIcon64.get(i).toString().length() == 0 ? ""
					: fIcon64.get(i).toString();
			String display = fDisplay.get(i).toString();
			String activity = activities.get(i).toString();
			String process = processes.get(i).toString();
			String activityFName = activitiesFNames.get(i).toString();
			String[] array = activityFName.split("/");

			Table funcTable = KSQL.select("SELECT p.* FROM B_Menu p where 1=2",
					null, dataModel, null);
			funcTable.getMetaData().setStoreByConcept("B_Menu", true);
			funcTable = BizData.create(funcTable, "B_Menu", null, dataModel);
			Row rec = funcTable.iterator().next();
			rec.setString("fID", uuid);
			rec.setString("fActivity", activity);
			rec.setString("fProcess", process);
			rec.setString("fIcon16", fIcon16Rce);
			rec.setString("fIcon32", fIcon32Rce);
			rec.setString("fIcon64", fIcon64Rce);
			rec.setString("fDisplay", display);
			rec.setString("fName", array[array.length - 1]);
			rec.setString("fActivityUrl", getURL(process, activity));
			rec.setString("fParentID", fParentID);
			rec.setInteger("fOrder", (fmax + 1));
			funcTable.save(dataModel);
		}
	}

	private static String getURL(String sprocess, String activity) {
		String[] sprocessArray = sprocess.split("/");
		sprocessArray[0] = "/UI";
		sprocessArray[sprocessArray.length - 1] = activity + ".w";
		return StringUtils.join(sprocessArray, "/");
	}

	// 删除菜单配置
	private static void deleteTreeRoot(Map<String, String> variables) {
		String fID = variables.get("fID").toString();
		String hasChildsql = "select * from B_Menu where B_Menu.fParentId='" + fID + "'";
		Map<String, String> hasChildMap = new HashMap<String, String>();
		hasChildMap.put(DatabaseProduct.DEFAULT.name(), hasChildsql);
		Table hasChildTable = SQL.select(hasChildMap, null, dataModel);
		Iterator<Row> rows = hasChildTable.iterator();
		while (rows.hasNext()) {
			Row row = rows.next();
			Map<String, String> map = new HashMap<String, String>();
			map.put("fID", row.getString("FID"));
			deleteTreeRoot(map);
		}
		String deleteSql = "delete from B_Menu m  where m.fID='" + fID
				+ "' and  m.fParentId is not null";
		SQL.executeUpdate(deleteSql, null, dataModel);
	}
}