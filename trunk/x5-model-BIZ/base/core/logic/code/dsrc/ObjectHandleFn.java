import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.butone.logic.impl.ProcessLogicPluginContext;
import com.justep.security.decrypt.Decrypt;
import com.justep.util.Utils;

public class ObjectHandleFn {

	public static Object ifNull(Object value, Object value1) {
		if (value == null) {
			return value1;
		} else if (value instanceof String && Utils.isEmptyString((String)value)) {
			return value1;
		} else
			return value;
	}

	public static Object decode(Object... params) {
		Object param1 = params[0];
		Object result = params[params.length - 1];
		for (int i = 1; i < params.length; i++) {
			if (i % 2 != 0 && param1.equals(params[i]) && i < params.length) {
				result = params[i + 1];
				break;
			}
		}
		return result;
	}

	public static boolean unEqual(Object obj1, Object obj2) {
		if (obj1 == null || obj2 == null)
			return false;
		else
			return !obj1.equals(obj2);
	}

	/**
	 * 判断对象是否为空 字符特殊判断
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isNull(Object obj) {
		if (obj == null)
			return true;
		else {
			if (obj instanceof String) {
				if (obj.toString().trim().equals(""))
					return true;
			}
		}
		return false;
	}

	/**
	 * 必填规则表达式
	 * 
	 * @param tableId
	 * @param fields
	 * @return
	 */
	public static boolean requireExpre(String tableId, String fields) {
		boolean result = false;
		fields = fields.trim();
		String[] fieldsArray = fields.split(",");
		for (int i = 0; i < fieldsArray.length; i++) {
			String field = fieldsArray[i].trim();
			Object value = ProcessLogicPluginContext.getTableControlObjectCurrentValue(tableId, field);
			result = result || isNull(value);
		}
		return result;

	}

	/**
	 * 必填规则提示
	 * 
	 * @param tableId
	 * @param fields
	 * @param fieldNames
	 * @return
	 */
	// 存在一个表单有多个工作表
	// concat('在函数测试表单中',requireRuleTip('t_zbcsb','dwzd2,dwzd3','单位字段2,单位字段3'),+requireRuleTip('t_sjjcb','mc','名称')
	// ,'不能为空！')
	public static String requireRuleTip(String tableId, String fields, String fieldNames) {
		String result = "";
		String[] fieldsArray = fields.split(",");
		String[] fieldNamesArray = fieldNames.split(",");
		for (int i = 0; i < fieldsArray.length; i++) {
			String field = fieldsArray[i].trim();
			Object value = ProcessLogicPluginContext.getTableControlObjectCurrentValue(tableId, field);
			if (isNull(value)) {
				result += "<font color=\"red\">【" + fieldNamesArray[i] + "】</font>";
			}
		}
		return result;
	}

	public static String newLine() {
		return "\r\n";
	}

	public static String quotedStr(String str) {
		return "'" + str + "'";
	}

	public static String dQuotedStr(String str) {
		return "\"" + str + "\"";
	}

	public static String attachNames(String docIds) {
		String result = "";
		if (docIds == null || docIds.equals(""))
			return "";
		JSONArray ary = JSONArray.parseArray(docIds);
		for (int i = 0; i < ary.size(); i++) {
			JSONObject json = ary.getJSONObject(i);
			if (result.equals(""))
				result = json.getString("docName");
			else
				result = result + ";" + json.getString("docName");
		}
		return result;
	}

	public static Object invokeStaticMethod(String className, String method, List<String> argumentTypes, List<Object> argumentValues)
			throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Class cls = Decrypt.instance().getClass(className);
		Class[] paramTypes = null;
		if (argumentTypes != null) {
			paramTypes = new Class[argumentTypes.size()];
			for (int n = 0; n < argumentTypes.size(); n++) {
				paramTypes[n] = Decrypt.instance().getClass(argumentTypes.get(n));
			}
		}
		Method m = cls.getDeclaredMethod(method, paramTypes);
		if (argumentValues == null)
			return m.invoke(cls);
		else
			return m.invoke(cls, argumentValues.toArray(new Object[] {}));
	}

	public static void main(String[] args) {
		Decrypt.init(Thread.currentThread().getContextClassLoader(), null, false);
		try {
			List<Object> pValues = new ArrayList<Object>();
			pValues.add("1");
			System.out.println(invokeStaticMethod("com.justep.util.Utils", "isNotEmptyString", Arrays.asList("java.lang.String"), pValues));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
