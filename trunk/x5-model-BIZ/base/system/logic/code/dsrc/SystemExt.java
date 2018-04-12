import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.butone.flowbiz.FlowBizConsts;
import com.justep.exception.BusinessException;
import com.justep.filesystem.FileSystemWrapper;
import com.justep.model.Config;
import com.justep.model.ModelUtils;
import com.justep.system.data.DatabaseProduct;
import com.justep.system.data.Row;
import com.justep.system.data.SQL;
import com.justep.system.data.Table;
import com.justep.system.util.CommonUtils;
import com.justep.util.Utils;

public class SystemExt {

	/**
	 * 首页获取个人信息
	 * 
	 * @param userName
	 */
	public static Map<String, String> getPersonInfo(String userName) {
		Map<String, String> result = new HashMap<String, String>();
		String sql = "select t.sOfficePhone, t.sMobilePhone ,t.sZip from SA_OPPerson t where t.sCode=?";
		Map<String, String> sqlMap = new HashMap<String, String>();
		List<Object> list = new ArrayList<Object>();
		list.add(userName);
		sqlMap.put(DatabaseProduct.DEFAULT.name(), sql);
		Table tab = SQL.select(sqlMap, list, FlowBizConsts.DATA_MODEL_SYSTEM);
		if (tab.size() == 1) {
			Row r = tab.iterator().next();
			result.put("telPhone", r.getString(0));
			result.put("mobilePhone", r.getString(1));
			result.put("postalCode", r.getString(2));
		}
		return result;
	}

	/**
	 * 首页修改个人信息
	 * 
	 * @param userName
	 * @param telPhone
	 * @param mobilePhone
	 * @param postalCode
	 */
	public static void changePersonInfo(String userName, String telPhone, String mobilePhone, String postalCode) {
		String sql = "update SA_OPPerson t set t.sOfficePhone=?,t.sMobilePhone=?,t.sZip=? where t.sCode=?";
		Map<String, String> sqlMap = new HashMap<String, String>();
		List<Object> list = new ArrayList<Object>();
		list.add(telPhone);
		list.add(mobilePhone);
		list.add(postalCode);
		list.add(userName);
		sqlMap.put(DatabaseProduct.DEFAULT.name(), sql);
		SQL.executeUpdate(sqlMap, list, FlowBizConsts.DATA_MODEL_SYSTEM);
	}

	public synchronized static List getSystemExtConfig() {
		JSONArray ret = new JSONArray();
		Config c = (Config) ModelUtils.getModel("/base/system/logic/code").getLocalObject("systemExtConfig", Config.TYPE);
		for (String name : c.getNames()) {
			JSONObject file = new JSONObject();
			file.put("name", name);
			String path = c.getValue(name);
			file.put("path", path);
			path = FileSystemWrapper.instance().getRealPath(path);
			file.put("config", parseConfig(path));
			ret.add(file);
		}
		return ret;
	}

	private static JSONArray parseConfig(String configFile) {
		JSONArray ret = new JSONArray();
		SAXReader r = new SAXReader();
		try {
			Document doc = r.read(new File(configFile));
			List<Element> configList = doc.getRootElement().elements();
			for (Element e : configList) {
				JSONObject config = new JSONObject();
				config.put("name", e.attributeValue("name"));
				config.put("value", e.attributeValue("value"));
				Node labelN = e.selectSingleNode("*[local-name()='label']");
				if (labelN != null)
					config.put("label", labelN.getText());
				config.put("butone:readonly", e.attributeValue("readonly"));
				config.put("butone:list", e.attributeValue("list"));
				config.put("butone:enableName", e.attributeValue("enableName"));
				config.put("lock", e.attributeValue("lock"));
				JSONArray items = parseConfigItems(e);
				if (items.size() > 0)
					config.put("items", items);
				ret.add(config);
			}
			return ret;
		} catch (DocumentException e) {
			throw new BusinessException("读取配置文件异常:" + configFile, e);
		}
	}

	private static JSONArray parseConfigItems(Element itemEle) {
		JSONArray ret = new JSONArray();
		List<Element> itemList = itemEle.elements();
		for (Element e : itemList) {
			if (e.getName() == "label")
				continue;
			JSONObject item = new JSONObject();
			ret.add(item);
			item.put("name", e.attributeValue("name"));
			item.put("value", e.attributeValue("value"));
			Node labelN = e.selectSingleNode("*[local-name()='label']");
			if (labelN != null)
				item.put("label", labelN.getText());
			item.put("butone:list", e.attributeValue("list"));
			JSONArray items = parseConfigItems(e);
			if (items.size() > 0)
				item.put("items", items);
		}
		return ret;
	}

	public synchronized static JSONArray syncSystemExtConfig(String files) {
		JSONArray ret = new JSONArray();
		JSONArray arr = JSON.parseArray(files);
		for (int i = 0; i < arr.size(); i++) {
			JSONObject file = arr.getJSONObject(i);
			JSONObject e = writeSystemExtConfig(file.getString("path"), JSON.parseArray(file.getString("config")));
			e.put("name", file.getString("name"));
			ret.add(e);
		}
		return ret;
	}

	private static JSONObject writeSystemExtConfig(String path, JSONArray configs) {
		File file = new File(FileSystemWrapper.instance().getRealPath(path));
		JSONObject ret = new JSONObject();
		ret.put("success", true);
		if (configs == null || configs.size() == 0)
			return ret;

		SAXReader r = new SAXReader();
		JSONObject locks = new JSONObject();
		try {
			Document doc = r.read(file);
			List<Element> configList = doc.getRootElement().elements();
			for (int i = 0; i < configs.size(); i++) {
				JSONObject c = configs.getJSONObject(i);
				Element configE = (Element) doc.selectSingleNode("//*[local-name()='config' and @name='" + c.getString("name") + "']");
				if (configE == null) {
					ret.put("success", false);
					ret.put("error", path + "中不存在名称为" + c.getString("name") + "config配置项");
				}
				String v1 = configE.attributeValue("lock");
				if (v1 == null)
					v1 = "";
				String v2 = c.getString("lock");
				if (v2 == null)
					v2 = "";
				if (!v1.equals(v2)) {
					ret.put("success", false);
					ret.put("error", path + "/" + c.getString("name") + "配置项被他人修改，请刷新数据");
				} else {
					// lock
					Node lock = configE.attribute("lock");
					if (lock == null) {
						lock = configE.addAttribute("lock", CommonUtils.createGUID());
					} else {
						lock.setText(CommonUtils.createGUID());
					}
					locks.put(c.getString("name"), lock.getText());
					// value
					setElementAttrValue(configE, "value", c.getString("value"));
					// items
					syncItemsToElement(configE, JSONArray.parseArray(c.getString("items")));
				}
			}

			BufferedOutputStream bufos = new BufferedOutputStream(new FileOutputStream(file));
			bufos.write(doc.asXML().getBytes("utf-8"));
			bufos.flush();
			bufos.close();

			File m = new File(FileSystemWrapper.instance().getRealPath("/.modified"));
			if (!m.exists()) {
				m.getParentFile().mkdirs();
				m.createNewFile();
			}
			FileWriter writer = new FileWriter(m, true);
			int pos = path.indexOf("_X/");
			if (pos > 0 && !path.substring(1, pos).contains("/")) {
				writer.write(new File(path.substring(0, pos) + path.substring(pos + 2)).getParent().replace('\\', '/') + "\n");
			} else {
				writer.write(new File(path).getParent().replace('\\', '/') + "\n");
			}
			writer.flush();
			writer.close();

			ret.put("locks", locks);
		} catch (Exception e) {
			ret.put("success", false);
			ret.put("eror", e.getMessage());
		}
		return ret;
	}

	private static void syncItemsToElement(Element p, JSONArray items) {
		if (items == null)
			return;
		for (int i = 0; i < items.size(); i++) {
			JSONObject c = items.getJSONObject(i);
			Element item = (Element) p.selectSingleNode("*[local-name()='item' and @name='" + c.getString("name") + "']");
			if (item == null) {
				item = p.addElement("item");
				// name
				setElementAttrValue(item, "name", c.getString("name"));
			}
			// label
			Element labelN = (Element) item.selectSingleNode("*[local-name()='label']");
			if (Utils.isNotEmptyString(c.getString("label")) && labelN == null) {
				labelN = item.addElement("label");
				labelN.addAttribute("language", "zh_CN");
				labelN.setText(c.getString("label"));
			} else if (labelN != null && Utils.isNotEmptyString(c.getString("label"))) {
				labelN.setText(c.getString("label"));
			}
			// value
			setElementAttrValue(item, "value", c.getString("value"));
			// items
			syncItemsToElement(item, JSONArray.parseArray(c.getString("items")));
		}
	}

	private static void setElementAttrValue(Element e, String name, String value) {
		Attribute a = e.attribute(name);
		if (a == null) {
			a = (Attribute) e.addAttribute(name, value == null ? "" : value);
		} else {
			a.setText(value == null ? "" : value);
		}
	}
}