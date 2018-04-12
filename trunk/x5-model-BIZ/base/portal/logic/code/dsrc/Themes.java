import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.w3c.css.sac.InputSource;
import org.w3c.dom.css.CSSRuleList;
import org.w3c.dom.css.CSSStyleRule;
import org.w3c.dom.css.CSSStyleSheet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.justep.system.data.KSQL;
import com.justep.system.data.Row;
import com.justep.system.data.Table;
import com.justep.system.util.CommonUtils;
import com.steadystate.css.parser.CSSOMParser;
import com.steadystate.css.parser.SACParserCSS3;

public class Themes {
	private static final String DATA_MODEL_P2 = "/portal2/data";
	// 缓存文件目录
	private static final String NAV_CACHE_DIR = ".release";
	// 导航文件-属性
	private static final String NAV_ICON = "cname"; 
	private static final String NAV_LABEL = "label";
	/**
	 * 解析css文件
	 */
	private static final String ICON_CSS_PATH = "model\\UI\\system_X\\icons\\system.page.css";
	private static final String ICON_PREFIX = "nav-icon-";
	
	public static Document document = DocumentHelper.createDocument();
	/**
	 *目标XML-属性节点需要移除的对象
	 */
	private static final String[] NODE_DETACH_ATTR = {"icon","icon16","icon64"};
	/**
	 * 目标XML-过滤-是否开启一级节点标签名称过滤 [非<item>元素]
	 */
	private static final boolean IS_NODE_FILTER = true;
	
	// 导航文件目录
	private static  String NAV_DIR_PATH  ;
	private static  String CONF_DIR_PATH ;
	
	private static void setNAV_DIR_PATH(String portal,String subSystem){	
		NAV_DIR_PATH =  "model\\"+portal+"\\appConfig\\"+subSystem+"\\";
		//CONF_DIR_PATH =  "model\\"+portal+"\\appConfig\\";
		CONF_DIR_PATH = NAV_DIR_PATH;
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
	 * 获取导航文件目录
	 * @param fileName
	 * @return "model\\UI\\appConfig\\default\\default.function.xml";
	 */
	private static String getFilePath(String fileName){
		return getFileDir(NAV_DIR_PATH).concat(fileName);
	}
	
	/**  
	 * 获取节点属性值 
	 * <功能详细描述> 
	 * @param element 
	 * @return 
	 * @throws JSONException 
	 * @see [类、类#方法、类#成员] 
	 */
	private static String getNodeAttribute(Element element) {
		JSONObject json = new JSONObject();
		String iconName = element.attributeValue(NAV_ICON);
		String labelName = element.attributeValue(NAV_LABEL);
		if(iconName == null) iconName = "";
		if(labelName == null) labelName = "空菜单项";
		json.put(NAV_ICON, iconName);
		json.put(NAV_LABEL, labelName);
		return json.toString();
	}
	
	/**  
	 * 遍历子节点的内容
	 * <功能详细描述> 
	 * @param element 
	 * @throws JSONException 
	 * @see [类、类#方法、类#成员] 
	 */
	private static String getChildrenNodeText(Document doc, Element e) {
		String xml = "";
		if (e != null) {
			if (!e.elements().isEmpty()) {
				List<Element> list = e.elements();
				if (e.getName() != "root") {
					Element elementParent = e.getParent();
					String parentid = elementParent.attributeValue("fid");
					e.setAttributeValue("pid", parentid);
				}

				for (Element el : list) {
					// 有机会嵌入了。是否开启过滤一级节点
					if(!!IS_NODE_FILTER && !(el.getName()).equals("item")){
						continue;
					};
					xml += getNodeAttribute(el) + "@";
				}
			}
		}
		return xml;
	}
	/**
	 * 获取主题列表
	 * @param value
	 * @return
	 */
	public static Table getThemeList(){
		String ksql = "SELECT s1.sThemeName,s1.sThemeActivity FROM Portal2Profiles s1 where s1.sPublishType='theme'";
		Table table = KSQL.select(ksql, null, DATA_MODEL_P2, null);
		return table;
	}
	/**
	 * 解析xml文件
	 * @param file
	 * @return
	 * @throws DocumentException
	 */
	public static JSONObject readFileElementsFromXMLDocument(File file) throws DocumentException{
		String path = file.getAbsolutePath();
		String fileName = file.getName();
		SAXReader reader = new SAXReader();
		JSONObject result = new JSONObject();
		JSONArray jArr = new JSONArray();
		
		Document doc = reader.read(path);
		Element root = doc.getRootElement(); // 取得根节点  
		String xml = getChildrenNodeText(doc, root);
		// 空内容的处理
		if(xml.equals("")) return result;
		String[] data = xml.split("@");
		for (int i = 0; i < data.length; i++) {
			JSONObject jsonObject = JSON.parseObject(data[i]);
			jArr.add(jsonObject);
			
		}
		
		if(data.length >0){
			result.put("fileName", fileName);
			result.put("items",jArr);
		};
		
		return result;
	}

	/**
	 * 解析目标目录下的所有xml文件
	 * @return
	 * @throws DocumentException
	 */
	private static String readXml() {
		File filesList=new File(getFileDir(NAV_DIR_PATH));
		JSONArray jsonArr = new JSONArray();
		File[] fs = filesList.listFiles();
		for (File file : fs) {
			if(file.getAbsolutePath().endsWith(".function.xml")){
				try {
					JSONObject xmlObj = readFileElementsFromXMLDocument(file);
					if(xmlObj.size() >0) jsonArr.add(xmlObj);
				} catch (DocumentException e) {
					e.printStackTrace();
				}
			}
		}
		return jsonArr.toJSONString();
	}
	
	/**
	 * 保存导航-icon
	 * @param data
	 */
    public static void saveMenuOfIcon(String config,String data) {
    	// 菜单导航图标保存-xml文件操作
		if(config != null && !("").equals(config)){
			JSONObject logoObj = JSON.parseObject(config);
			String logoStr = logoObj.getString("logo");
			JSONObject obj = JSON.parseObject(logoStr);
			ModifySysXMLFile("main.config.xml",obj);
		}
		// 菜单导航图标保存-xml文件操作
		if(data != null && !("").equals(data)){
			JSONArray jsonArr = JSON.parseArray(data);
			Iterator<Object> it = jsonArr.iterator();
			while (it.hasNext()) {
                JSONObject obj = (JSONObject) it.next();
                String fileName = obj.getString("fileName");
                if(fileName != null){
                   String items = obj.getString("items");
                   boolean isCreate = ModifyInformationOfFile(fileName,items);
                   if(isCreate) DeleteFolder(getFilePath(NAV_CACHE_DIR));
                }
			}
		}
	}
	/**
	 * 保存系统设置-sportal字段/ 系统名称
	 * @param value
	 * @return
	 */
	public static boolean saveSystemConfig(String config){
		String updateksql = "UPDATE Portal2Profiles s1 SET s1.sPortal=:value where s1.sPublishType='theme' and s1.sThemeActivity='activity'";
		Map<String,Object> updatevars = new HashMap<String,Object>();
		updatevars.put("value", config);
		KSQL.executeUpdate(updateksql, updatevars, DATA_MODEL_P2, null);
		return true;
	}
	/**
	 * 获取系统设置信息
	 * 系统名称以及导航配置
	 * @return
	 * @throws DocumentException
	 */
	public static Map<String,Object> getSysThemeConfig(String portal,String subSystem) {
		setNAV_DIR_PATH(portal,subSystem);
		// 主题配置
		Map<String,Object> result = new HashMap<String,Object>();
		Map<String,String> sThemeConf = getSystemConfig();
		// 菜单配置
		String sNavConf = readXml();
		String sSysConf = getSysXMLFileValue("main.config.xml");
		System.out.println("主题配置==>"+sSysConf);
		System.out.println("菜单配置==>"+sNavConf);
		// icon配置
		String iconList = getSelectorCss(getFileDir(ICON_CSS_PATH),ICON_PREFIX);
		System.out.println("ICON配置==>"+iconList);
		//result.put("sSysConf", sThemeConf.get("sPortal"));
		result.put("sSysConf",sSysConf);
		result.put("sNavIcon", sNavConf);
		result.put("iconList", iconList);
		result.put("toolbar", sThemeConf.get("sOther"));
		return result;
	}
	
	/**
	 * 单文件解析，生成
	 * 该方法的作用是修改document中的内容
	 * @param fileName
	 * @param targetData
	 * @return 
	 */
	private static boolean ModifySysXMLFile(String fileName,JSONObject obj){
		String filePath = getFileDir(CONF_DIR_PATH).concat(fileName);
		// 寻找目标文件进行解析
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(filePath);
			processSystemXMLDocument(document, obj);
            writeToNewXMLDocument(document,fileName);
        }catch (DocumentException e1) {
			e1.printStackTrace();
			return false;
		}catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
		return true;
	}
	/**
	 * 处理docment数据-系统配置文件
	 * @param document
	 * @param data
	 * @return
	 */
	private static Document processSystemXMLDocument(Document document,JSONObject obj){
		Element root = document.getRootElement(); // 取得根节点  
		List<Element> firstElem = root.elements();
		String zhName = obj.getString("title_zh");
		String enName = obj.getString("title_en");
		// 遍历原文档中的信息，寻找机会进行修改
		for (int i = 0; i < firstElem.size(); i++) {
            Element node = (Element) firstElem.get(i);
            Attribute name = node.attribute("name");
            if(name == null){
            	continue;
            }
            if(("title_zh").equals(name.getText())){
            	node.setText(zhName);
            }
            if(("title_en").equals(name.getText())){
            	node.setText(enName);
            }
            // 原始菜单中，当前节点名称不存在
            detachFromNodeAttr(node);
    
        }
		return document;
	}
	/**
	 * 单文件解析，获取
	 * 该方法的作用是修改document中的内容
	 * @param fileName
	 * @param targetData
	 * @return 
	 */
	private static String getSysXMLFileValue(String fileName){
		String filePath = getFileDir(CONF_DIR_PATH).concat(fileName);
		// 寻找目标文件进行解析
		SAXReader reader = new SAXReader();
		JSONArray arr = new JSONArray();
		JSONObject itemObj = new JSONObject();
		try {
			Document document = reader.read(filePath);
			Element root = document.getRootElement(); // 取得根节点  
			List<Element> firstElem = root.elements();
			// 遍历原文档中的信息，寻找机会进行修改
			for (int i = 0; i < firstElem.size(); i++) {
	            Element node = (Element) firstElem.get(i);
	            Attribute name = node.attribute("name");
	            if(name == null){
	            	continue;
	            }
	            if(("title_zh").equals(name.getText())){
	            	itemObj.put("title_zh",node.getText());
	            }
	            if(("title_en").equals(name.getText())){
	            	itemObj.put("title_en",node.getText());
	            }  
	        }
			arr.add(itemObj);
        }catch (DocumentException e1) {
			e1.printStackTrace();
			return arr.toString();
		}catch (Exception e) {
            e.printStackTrace();
            return arr.toString();
        }
        System.out.println(arr.toString());
		return arr.toString();
	}
	/**
	 * 获取系统设置内容-portal字段/系统名称
	 * @return
	 */
	public static Map<String,String> getSystemConfig(){
		Map<String,String> result = new HashMap<String,String>();
		String ksql = "SELECT s1.* FROM Portal2Profiles s1 where s1.sPublishType='theme' and s1.sThemeActivity='activity'";
		Table table = KSQL.select(ksql, null, DATA_MODEL_P2, null);
		
		if(table.size()>0){
			Iterator<Row> it = table.iterator();
			if (it.hasNext()) {
				Row row = it.next();
				String val = row.getString("sPortal");
				if (val == null) val = ""; //解决用了fast json之后, null域会清除的问题
				result.put("sPortal", val);
				
				String sO = row.getString("sOther");
				if (sO == null) sO = ""; //解决用了fast json之后, null域会清除的问题
				result.put("sOther", sO);
			}
		}
		return result;
	}
	/**
	 * 移除节点属性
	 * @param node
	 */
	private static void detachFromNodeAttr(Element node){
		//需要移除的节点属性
		String[] list = NODE_DETACH_ATTR;
		if(node != null && list.length > 0){
			for(int i = 0; i < list.length; i++){
			    if(node.attribute(list[i]) != null){
			    	node.attribute(list[i]).detach();
			    }
			}
		}
	}
	
	/**
	 * 处理docment数据
	 * @param document
	 * @param data
	 * @return
	 */
	private static Document processFileFromDocument(Document document,String data){
		JSONArray jsonArr = JSON.parseArray(data);
		Element root = document.getRootElement(); // 取得根节点  
		List<Element> firstElem = root.elements();
		// 遍历原文档中的信息，寻找机会进行修改
		for (int i = 0; i < firstElem.size(); i++) {
			Iterator<Object> it = jsonArr.iterator();
            Element node = (Element) firstElem.get(i);
            Attribute originalLabel = node.attribute(NAV_LABEL);
            Attribute iconObj = node.attribute(NAV_ICON);
            if(!!IS_NODE_FILTER && !(node.getName()).equals("item")){
            	node.detach();
				continue;
			};
            // 原始菜单中，当前节点名称不存在
            if(originalLabel == null){
            	node.addAttribute(NAV_LABEL,"空菜单项");
            	if(iconObj == null){
            		node.addAttribute(NAV_ICON,"");
            	}
            	continue;
            }
            detachFromNodeAttr(node);
    		while (it.hasNext()) {
                JSONObject obj = (JSONObject) it.next();
                String itemName = obj.getString(NAV_LABEL);
                String iconName = obj.getString(NAV_ICON);
                // 相等说明这个要进行修改 NAV_ICON
                if(itemName.equals(originalLabel.getText())){
                   if(iconObj == null || !iconName.equals(iconObj.getText())){
                	   node.addAttribute(NAV_ICON,iconName);
                   }
                }
    		}
        }
		return document;
	}
	/**
	 * 单文件解析，生成
	 * 该方法的作用是修改document中的内容
	 * @param fileName
	 * @param targetData
	 * @return 
	 */
	private static boolean ModifyInformationOfFile(String fileName,String targetData){
		String filePath = getFilePath(fileName);
		// 寻找目标文件进行解析
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(filePath);
			processFileFromDocument(document, targetData);
			
            writeToNewXMLDocument(document,fileName);
        }catch (DocumentException e1) {
			e1.printStackTrace();
			return false;
		}catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
		return true;
	}
    /**
     * 通过document对象将内存中的dom树保存到新的xml文档。
     * 
     * @param document
     * @throws Exception
     */
    private static void writeToNewXMLDocument(Document document,String fileName)
            throws Exception {
    	String path = document.getName();
    	OutputFormat format = OutputFormat.createPrettyPrint();
    	format.setEncoding("UTF-8"); // 设置XML文档的编码类型
        format.setIndent(true);      // 设置是否缩进
        format.setIndent("   ");     // 以空格方式实现缩进
    	format.setNewlines(true);    // 设置是否换行
    	//format.setNewLineAfterDeclaration(false); //不让声明后有换行，关键一句  
		//String path = getFilePath(fileName); String path = document.getName()
		XMLWriter write = new XMLWriter(new FileOutputStream(new File(path)),format);
		try {
			write.write(document);
			write.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    /**
     * 获取icon列表-解析css文件
     * @param url css文件的地址
     * @param cName 需要解析的前缀 例如：icon-xx
     * @return
     */
	public static String getSelectorCss(String url,String cName){
		if(cName == null || ("").equals(cName)) cName = "icon-";
		List<String> list = new ArrayList<String>();
		File f = new File(url);
		if(!f.exists()){
			System.out.println(f.getAbsolutePath()+"文件不存在");
			return "";
		}
        try {
        	InputStream inStream = new FileInputStream(f);
            InputSource source = new InputSource();
            source.setByteStream(inStream);
            source.setEncoding("UTF-8");
            final CSSOMParser parser = new CSSOMParser(new SACParserCSS3());
            CSSStyleSheet sheet = parser.parseStyleSheet(source, null, null);
            CSSRuleList rules = sheet.getCssRules();
            if(rules.getLength() == 0 ){
                return "";
            }
            for (int i = 0; i < rules.getLength(); i++) {
                final CSSStyleRule rule = (CSSStyleRule) rules.item(i);
                Pattern pattern = Pattern.compile("\\."+cName+".*");
    			Matcher matcher = pattern.matcher(rule.getSelectorText());
    			while(matcher.find()){
    				String scName = matcher.group();
    				if(scName !=null && !("").equals(scName)) list.add(scName);
    			}
            }
        } catch (IOException e) {
            return "";
        }
         return listToString(list, ",");
	}
	
	private static String listToString(List<String> list, String string) {  
		  StringBuilder sb = new StringBuilder();  
		  for (int i = 0; i < list.size(); i++) {  
			  String v = list.get(i).trim().replace(".", "");
		      if (i == list.size() - 1) {  
		      sb.append(v);  
		      } else {  
		      sb.append(v);  
		      sb.append(string);  
		      }  
		  }  
		  return sb.toString();  
	}  
	
    /** 
     *根据路径删除指定的目录或文件，无论存在与否 
     *@param sPath  要删除的目录或文件 
     *@return 删除成功返回 true，否则返回 false。 
     */  
	private static boolean DeleteFolder(String sPath) {  
        boolean flag = false;  
        File file = new File(sPath);  
        // 判断目录或文件是否存在  
        if (!file.exists()) {  // 不存在返回 false  
            return flag;  
        } else {  
            // 判断是否为文件  
            if (file.isFile()) {  // 为文件时调用删除文件方法  
                return deleteFile(sPath);  
            } else {  // 为目录时调用删除目录方法  
                return deleteDirectory(sPath);  
            }  
        }  
    }  
	
    /** 
     * 删除单个文件 
     * @param   sPath    被删除文件的文件名 
     * @return 单个文件删除成功返回true，否则返回false 
     */  
	private static boolean deleteFile(String sPath) {  
        boolean flag = false;  
        File file = new File(sPath);  
        // 路径为文件且不为空则进行删除  
        if (file.isFile() && file.exists()) {  
            file.delete();  
            flag = true;  
        }  
        return flag;  
    }  
    
    /** 
     * 删除目录（文件夹）以及目录下的文件 
     * @param   sPath 被删除目录的文件路径 
     * @return  目录删除成功返回true，否则返回false 
     */  
	private static boolean deleteDirectory(String sPath) {  
        //如果sPath不以文件分隔符结尾，自动添加文件分隔符  
        if (!sPath.endsWith(File.separator)) {  
            sPath = sPath + File.separator;  
        }  
        File dirFile = new File(sPath);  
        //如果dir对应的文件不存在，或者不是一个目录，则退出  
        if (!dirFile.exists() || !dirFile.isDirectory()) {  
            return false;  
        }  
        boolean flag = true;  
        //删除文件夹下的所有文件(包括子目录)  
        File[] files = dirFile.listFiles();  
        for (int i = 0; i < files.length; i++) {  
            //删除子文件  
            if (files[i].isFile()) {  
                flag = deleteFile(files[i].getAbsolutePath());  
                if (!flag) break;  
            } //删除子目录  
            else {  
                flag = deleteDirectory(files[i].getAbsolutePath());  
                if (!flag) break;  
            }  
        }  
        if (!flag) return false;  
        //删除当前目录  
        if (dirFile.delete()) {  
            return true;  
        } else {  
            return false;  
        }  
    }
	/**
	 * 初始化主题列表
	 * @param value
	 * @return
	 */
	public static boolean resetThemeList(String value){
		String ksql = "SELECT s1.* FROM Portal2Profiles s1 where s1.sThemeName='default' and s1.sPublishType='theme'";
		Table table = KSQL.select(ksql, null, DATA_MODEL_P2, null);
		if(table.size()>0){
			removeThemeList();
		}
		addThemeList();
		return true;
	}
	
	/**
	 * 添加主题列表
	 * @param value
	 * @return
	 */
	public static boolean addThemeList(){
		String insertksql = "INSERT INTO Portal2Profiles s1(s1,s1.version,s1.sPortal,s1.sOther, s1.sThemeName, s1.sThemeActivity, s1.sPublishType) values (:id,:version,:p,:value,'default','activity', 'theme')";
		Map<String,Object> insertvars = new HashMap<String,Object>();
		insertvars.put("id",CommonUtils.createGUID());
		insertvars.put("version", 0);
		insertvars.put("p", "{\"logo\":{\"type\":\"l_2\",\"title_zh\":\"后台管理系统\",\"title_en\":\"\"}}");
		insertvars.put("value", "");
		KSQL.executeUpdate(insertksql, insertvars, DATA_MODEL_P2, null);
		return true;
	}
	
	/**
	 * 移除主题
	 * @param value
	 * @return
	 */
	private static boolean removeThemeList(){
		String updateksql = "DELETE from Portal2Profiles s1 where s1.sThemeName=:themeName and s1.sPublishType='theme'";
		Map<String,Object> updatevars = new HashMap<String,Object>();
		updatevars.put("themeName", "default");
		KSQL.executeUpdate(updateksql, updatevars, DATA_MODEL_P2, null);
		return true;
	}
	
	public static boolean updataThemeState(String value){
		updateALLTheme();
		String updateksql = "UPDATE Portal2Profiles s1 SET s1.sThemeActivity='activity' where s1.sPublishType='theme' and s1.sThemeName=:value";
		Map<String,Object> updatevars = new HashMap<String,Object>();
		updatevars.put("value", value);
		KSQL.executeUpdate(updateksql, updatevars, DATA_MODEL_P2, null);
		return true;
	}
	/**
	 * 更新全部状态
	 */
	private static void updateALLTheme(){
		String updateksql = "UPDATE Portal2Profiles s1 SET s1.sThemeActivity='' where s1.sPublishType='theme' and s1.sThemeActivity='activity'";
		KSQL.executeUpdate(updateksql, null, DATA_MODEL_P2, null);
	}
	
}