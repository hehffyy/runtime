import java.util.Map;

import com.butone.logic.impl.ProcessLogicPluginContext;

public class BizLogicPlugin {

	public static Object executeBizLogicPlugin(String url, String bizRecId, Map<String, Object> variants, Map<String, String> filters) {
		return ProcessLogicPluginContext.executeBizLogicPlugin(url, null, null, bizRecId, variants, filters);
	}
}