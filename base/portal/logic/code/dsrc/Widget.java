import java.io.File;
import java.util.*;
import org.dom4j.*;
import org.dom4j.io.SAXReader;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.justep.system.context.ContextHelper;
import com.justep.system.data.*;
import com.justep.system.opm.OrgUnit;
import com.justep.system.opm.OrgUtils;
import com.justep.system.util.CommonUtils;

public class Widget {
	private static final String DATA_MODEL = "/base/portal/data";
	private static final String SYSTEM_DATA_MODEL = "/system/data";
	// 系统widget文件路径
	private static  String NAV_DIR_PATH  ;

	private static void setNAV_DIR_PATH(String portal){	
		NAV_DIR_PATH =  "model\\"+portal+"\\appConfig\\default\\";
	}
	/**
	 * 获取系统路径
	 * @return
	 */
	private static String getTempDir() {
		return System.getProperty("java.io.tmpdir");
	}
	/**
	 * 获取解析文件来源目录
	 * @return
	 */
	private static String getFileDir(String path){
		String str = getTempDir();
		String url = str.substring(0, (str.lastIndexOf("\\") - 13));
		return url.concat(path);
	} 
	/**
	 * 
	 * @param fileName
	 * @return "model\\UI\\appConfig\\default\\default.function.xml";
	 */
	private static String getFilePath(String fileName){
		return getFileDir(NAV_DIR_PATH).concat(fileName);
	}
	/**
	 * 获取widget列表信息
	 * @return
	 */
	public static Map<String,Object> getWidgetList(String portal){
		setNAV_DIR_PATH(portal);
		Map<String,Object> result = new HashMap<String,Object>();
		// 所有widget列表-DOC
		JSONArray widgetList = getWidgetsList();
		result.put("widgets",widgetList);
		return result;
	}
	
	/**
	 * 获取布局列表，xml配置-后期开发-目前页面静态配置
	 * @return
	 */
	public static String processEmpty(String val){
		if (val == null) val = "";
		return val;
	}
	
	/**
	 * 获取Widget列表，in xml
	 * param baseModel 基础模块的对象包含:布局-widget等
	 * @return
	 */
	private static JSONArray getWidgetsList() {
		Document resultDoc = getWidgets();
		if (resultDoc != null) {
			Element root = resultDoc.getRootElement();
			// 删除空的菜单项
			deleteNullMenu(root);
			JSONArray result = getWidgetsData(root);
			return result;
		} else {
			return new JSONArray();
		}
	}

	private static JSONObject getWidgetsDataCategory(Element root) {
		int typeIndex = 0;
		JSONObject result = new JSONObject();
		JSONArray arr = new JSONArray();
		List<Element> typelist = root.elements();
		for (int n = 0; n < typelist.size(); n++) {

			Element te = (Element) typelist.get(n);
			typeIndex++;
			JSONObject widgetType = new JSONObject();
			result.put("widgetType" + typeIndex, widgetType);
			widgetType.put("category", te.attributeValue("label"));
			JSONArray widgets = new JSONArray();
			widgetType.put("widgets", widgets);
			List<Element> widgetList = te.elements();
			for (int j = 0; j < widgetList.size(); j++) {
				Element widgetElement = (Element) widgetList.get(j);
				JSONObject widget = new JSONObject();
				widgets.add(widget);
				arr.add(widget);
				String type = widgetElement.attribute("type").getValue();
				if (type.equals("func")) {
					List<Attribute> attrs = widgetElement.attributes();
					for (int k = 0; k < attrs.size(); k++) {
						Attribute attr = (Attribute) attrs.get(k);
						widget.put(attr.getName(), attr.getValue());
					}
				} else if (type.equals("link")) {
					List<Attribute> attrs = widgetElement.attributes();
					for (int k = 0; k < attrs.size(); k++) {
						Attribute attr = (Attribute) attrs.get(k);
						widget.put(attr.getName(), attr.getValue());
					}
				}
				if (widgetElement.elements("more").size() > 0) {
					Element me = widgetElement.element("more");
					JSONObject more = new JSONObject();
					widget.put("more", more);
					List<Attribute> mattrs = me.attributes();
					for (int k = 0; k < mattrs.size(); k++) {
						Attribute mattr = (Attribute) mattrs.get(k);
						more.put(mattr.getName(), mattr.getValue());
					}
				}
				
			}
		}
		return result;
	}
	/**
	 * 清除doc为空的节点
	 * @param paramElement
	 */
	private static JSONArray getWidgetsData(Element root) {
		int typeIndex = 0;
		JSONArray result = new JSONArray();
		List<Element> typelist = root.elements();
		// 开始遍历文档节点 ROOT
		for (int n = 0; n < typelist.size(); n++) {

			Element te = (Element) typelist.get(n);
			typeIndex++;
			List<Element> widgetList = te.elements();
			// 遍历子节点===二级
			for (int j = 0; j < widgetList.size(); j++) {
				Element widgetElement = (Element) widgetList.get(j);
				JSONObject widget = new JSONObject();
				result.add(widget);
				String type = widgetElement.attribute("type").getValue();
				if (type.equals("func")) {
					// 遍历子节点-属性 ==》值-然后写入到集合中
					List<Attribute> attrs = widgetElement.attributes();
					for (int k = 0; k < attrs.size(); k++) {
						Attribute attr = (Attribute) attrs.get(k);
						widget.put(attr.getName(), attr.getValue());
					}
				} else if (type.equals("link")) {
					List<Attribute> attrs = widgetElement.attributes();
					for (int k = 0; k < attrs.size(); k++) {
						Attribute attr = (Attribute) attrs.get(k);
						widget.put(attr.getName(), attr.getValue());
					}
				}
				if (widgetElement.elements("more").size() > 0) {
					Element me = widgetElement.element("more");
					JSONObject more = new JSONObject();
					widget.put("more", more);
					List<Attribute> mattrs = me.attributes();
					for (int k = 0; k < mattrs.size(); k++) {
						Attribute mattr = (Attribute) mattrs.get(k);
						more.put(mattr.getName(), mattr.getValue());
					}
				}
			}
		}
		return result;
	}
	/**
	 * 清除doc为空的节点
	 * @param paramElement
	 */
	public static void deleteNullMenu(Element paramElement) {
		List localList = paramElement.selectNodes("//item[not(./@url or .//item/@url)]");
		Iterator localIterator = localList.iterator();
		while (localIterator.hasNext()) {
			Element localElement1 = (Element) localIterator.next();
			Element localElement2 = localElement1.getParent();
			if (localElement2 != null) {
				localElement2.remove(localElement1);
			} else {
				Document localDocument = localElement1.getDocument();
				if (localDocument != null)
					localDocument.remove(localElement1);
			}
		}
	}
	/**
	 * 获取XML文档对象，复制到新的文档中，并返回新的文档对象
	 * @return
	 */
	public static Document getWidgets() {
		ArrayList<Element> localArrayList = new ArrayList<Element>();
		// 获取widget文件对象
		List<String> localList1 = getWidgetFiles("");
		Iterator<String> itor = localList1.iterator();
		while (itor.hasNext()) {
			List<Element> localList2 = getWidget(itor.next());
			addWidget(localArrayList, localList2);
		}
		Document ret = DocumentHelper.createDocument();
		Element root = ret.addElement("root");
		root.setContent(localArrayList);
		return ret;
	}
	/**
	 * 删除DOC空项
	 * @param paramElement
	 */
	private static void deleteItem(Element paramElement) {
		if (paramElement == null)
			return;
		Element localElement = paramElement.getParent();
		if (localElement == null)
			paramElement.getDocument().remove(paramElement);
		else
			localElement.remove(paramElement);
	}
	/**
	 * 复杂文档DOC对象
	 * @param paramList1
	 * @param paramList2
	 */
	private static void addWidget(List<Element> paramList1, List<Element> paramList2) {
		if ((paramList1 != null) && (paramList2 != null)) {
			Iterator localIterator = paramList2.iterator();
			while (localIterator.hasNext()) {
				Element localElement1 = (Element) localIterator.next();
				Element localElement2 = findWidget(paramList1, localElement1);
				if (localElement2 == null)
					paramList1.add((Element) localElement1.clone());
				else
					addWidget(localElement2.elements(), localElement1.elements());
			}
		}
	}
	/**
	 * 
	 * @param paramList
	 * @param paramElement
	 * @return
	 */
	private static Element findWidget(List<Element> paramList, Element paramElement) {
		if ((paramList != null) && (paramElement != null)) {
			String str = paramElement.attributeValue("label");
			if (str == null)
				str = "";
			Iterator localIterator = paramList.iterator();
			while (localIterator.hasNext()) {
				Element localElement = (Element) localIterator.next();
				if (str.equals(localElement.attributeValue("label")))
					return localElement;
			 }
		}
		return null;
	}
	/**
	 * widget转换文档对象 String==》Object
	 * @param paramString
	 * @return
	 */
	private static List<Element> getWidget(String paramString) {
		SAXReader localSAXReader = new SAXReader();
		try {
			Document localDocument = localSAXReader.read(paramString);
			Element localElement = localDocument.getRootElement();
			return localElement.elements();
		} catch (Exception localException) {
			throw new RuntimeException("url: " + paramString, localException);
		}
	}
	/**
	 * 解析目标目录下的所有xml文件
	 * @return
	 * @throws DocumentException
	 */
	private static List<String> getWidgetFiles(String appName) {
		ArrayList<String> localArrayList = new ArrayList<String>();
		File filesList=new File(getFileDir(NAV_DIR_PATH));
		File[] fs = filesList.listFiles();
		for (File localFile : fs) {
			if(localFile.getAbsolutePath().endsWith(".widget.xml")){
				String str4 =  localFile.getAbsolutePath();
				localArrayList.add(str4);
			}
		}
		return localArrayList;
	}
	/**
	 * @return
	 * @throws DocumentException
	 */
	public static String getCurrentWTemplatInfo(String id) {
		String ksql = "SELECT s1.* FROM B_WIDGET_GL s1 where s1 =:id";
		Map<String,Object> vars = new HashMap<String,Object>();
		vars.put("id", id);
		Table table = KSQL.select(ksql, vars, DATA_MODEL, null);
		Iterator<Row> it = table.iterator();
		String fMBBJXX = "";
		if (it.hasNext()) {
			Row row = it.next();
			fMBBJXX = row.getString("fMBBJXX"); // 当前布局信息
		}
		return  fMBBJXX;
	}
	/**
	 * 首页获取widget列表
	 * @return
	 * @throws DocumentException
	 */
	public static Map<String,String> getWidgetsToHome() {
		//Operator operator = ContextHelper.getOperator();
		//String PID = operator.getID();
		Map<String,String> result = new HashMap<String,String>();
		String personID = ContextHelper.getPersonMember().getID();
		Map<String,String> pLayoutInfo = selectWidgetLayout(personID);
		result.put("layoutInfo",pLayoutInfo.get("fMBBJXX"));
		return result;
	}
	/**
	 * 根据当前用户，向上进行查询首页布局信息
	 * @return
	 * @throws DocumentException
	 */
	public static Map<String,String> selectWidgetLayout(String personID) {
		String ksql = "SELECT distinct t1.* FROM B_WIDGET_GL t1 where t1 IN (SELECT s1.fMBID FROM B_WIDGET_GLRY s1 where s1.sID=:personID)";
		Map<String,Object> vars = new HashMap<String,Object>();
		vars.put("personID", personID);
		Table table = KSQL.select(ksql, vars, DATA_MODEL, null);
		Iterator<Row> it = table.iterator();
		Map<String,String> result = new HashMap<String,String>();
		if (it.hasNext()) {
			Row row = it.next();
			String fMBBJXX = row.getString("fMBBJXX");
			result.put("fMBBJXX",fMBBJXX);
		}else{
			result = selectWidgetLayoutByOrg(ContextHelper.getPersonMember().getID());
		}
		return result;
	}
	/**根据ID查询，向上级联查询
	 * @return
	 * @throws DocumentException
	 */
	public static Map<String,String> selectWidgetLayoutByOrg(String orgID) {
		String ksql = "SELECT distinct t1.* FROM B_WIDGET_GL t1 where t1 IN (SELECT s1.fMBID FROM B_WIDGET_GLRY s1 where s1.sID=:personID)";
		Map<String,Object> vars = new HashMap<String,Object>();
		vars.put("personID", orgID);
		Table table = KSQL.select(ksql, vars, DATA_MODEL, null);
		Iterator<Row> it = table.iterator();
		Map<String,String> result = new HashMap<String,String>();
		if (it.hasNext()) {
			Row row = it.next();
			String fMBBJXX = row.getString("fMBBJXX");
			result.put("fMBBJXX",fMBBJXX);
		}else{	
			String sql2 = "SELECT s1.sParent FROM SA_OPOrg s1 where s1='"+orgID+"'";
			Table table2 = KSQL.select(sql2, null, SYSTEM_DATA_MODEL, null);
			Iterator<Row> it2 = table2.iterator();
			if(it2.hasNext()){
				result = selectWidgetLayoutByOrg(it2.next().getString("sParent"));
			}else{
				result.put("fMBBJXX", "");
			}
		}
		return result;
	}
	/**
	 * 人员关联表插入数据
	 * @param fMBID 关联模板ID
	 * @param orgInfo 组织机构信息
	 * @return
	 */
	public static Map<String,Object> addWidgetRelation(String fMBID,String orgInfo){
		Map<String,Object> result = new HashMap<String,Object>();
		JSONArray orgList = JSON.parseArray(orgInfo);
		JSONArray arr = new JSONArray();
		// 对组织机构进行单个拆分查询
		for (int i = 0; i < orgList.size(); i++){
			JSONObject orgObj = orgList.getJSONObject(i);
			String mID = fMBID;
			String sID = orgObj.getString("rowid");
			String sFID = orgObj.getString("sFID");
			String sName = orgObj.getString("sName");
			String sFName = orgObj.getString("sFName");
			String sOrgKindID = orgObj.getString("sOrgKindID");
			String ksql = "SELECT s1.* FROM B_WIDGET_GLRY s1 where s1.sID=:sID";
			Map<String,Object> vars = new HashMap<String,Object>();
			vars.put("fMBID", mID);
			vars.put("sID", sID);
			vars.put("sFID", sFID);
			vars.put("sName", sName);
			vars.put("sFName", sFName);
			if(sOrgKindID.equals("psm")){
				sOrgKindID = "人员";
			}else if(sOrgKindID.equals("pos")){
				sOrgKindID = "岗位";
			}else if(sOrgKindID.equals("dpt")){
				sOrgKindID = "部门";
			}else if(sOrgKindID.equals("ogn")){
				sOrgKindID = "机构";
			}
			vars.put("fLevel", sOrgKindID);
			Table table = KSQL.select(ksql, vars, DATA_MODEL, null);
			// 查询当前对象，不存在插入
			if(table.size() < 1){
				String insertksql = "INSERT INTO B_WIDGET_GLRY s1(s1,s1.version,s1.fMBID,s1.sID,s1.sName,s1.sFID,s1.sFName,s1.fLevel) values (:id,:version,:fMBID,:sID,:sName,:sFID,:sFName,:fLevel)";
				vars.put("id",CommonUtils.createGUID());
				vars.put("version", 0);
				KSQL.executeUpdate(insertksql, vars, DATA_MODEL, null);
			}else{
				// 查询当前对象，存在则返回，进行提示
				JSONObject json = new JSONObject();
				json.put("fMBID", mID);
				json.put("sID", sID);
				json.put("sFID", sFID);
				json.put("sName", sName);
				json.put("sFName", sFName);
				json.put("fLevel", sOrgKindID);
				arr.add(json);
			}
		}
		result.put("state", "ok");
		result.put("result", arr);
		return result;
	}
	/**
	 * 判断系统设置功能权限
	 * @param executor
	 * @return
	 */
	public static Map<String,Object> hasWidgetPermission(String wid,String fMBNR){
		String ksql = "SELECT s1.*  FROM B_WIDGET_GLRY s1 where s1.fMBID=:fMBID";
		Map<String,Object> vars = new HashMap<String,Object>();
		vars.put("fMBID", wid);
		Table table = KSQL.select(ksql, vars, DATA_MODEL, null);
		JSONArray widgetList = JSON.parseArray(fMBNR);
		Iterator<Object> ws = widgetList.iterator();
		Map<String,Object> noPermission = new HashMap<String,Object>();
		// 查询当前模板下的所有人员，进行遍历
		while (ws.hasNext()) {
			JSONObject widget = (JSONObject) ws.next();
			String title = widget.getString("title");
			String process = widget.getString("process");
			String activity = widget.getString("activity");
			List<String> list = new ArrayList<String>();
			if(table.size()>0){
				Iterator<Row> it = table.iterator();
				List<OrgUnit> orgs = OrgUtils.findOrgUnitsHasActivity(process, activity, null, false);
				// 人员
				while (it.hasNext()) {
					boolean isHas = false;
					Row row = it.next();
					String sFID = row.getString("sFID");
					String sName = row.getString("sName");
					String fLevel = row.getString("fLevel");
					for (int index = 0; index < orgs.size(); index++){
						if(sFID.equals(orgs.get(index).getFID())){
							// 有权限
							isHas = true;
							continue;
						}
					}
					if(!isHas){
						// 无权限
						list.add(sName+"（"+fLevel+"）");
					}
				}
			}
			if(list.size()>0){
				noPermission.put(title, list);
			}else{
				noPermission.put(title,"通过！");
			}
			
		}
		return noPermission;
	}
}