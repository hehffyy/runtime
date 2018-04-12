import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import com.butone.wsdl.DynamicInvoker;
import com.justep.exception.BusinessException;

public class CallWebService {

	private static final Map<String, DynamicInvoker> invokers = new ConcurrentHashMap<String, DynamicInvoker>();

	public static void main(String[] args) throws Exception {
		String endpoint = "http://192.168.210.253/FSArcgisWserver/Service1.asmx?WSDL";
		String serviceName = "Service1";
		String operationName = "GetSDEFeatureAttrInfo";
		List<String> paramKeys = Arrays.asList(new String[] { "LayerName", "ReturnFields", "condition" });
		List<Object> paramValues = Arrays.asList(new Object[] { "ShiYQZD_DB_500", "", "Yewbh = '201801030000001'" });

		Map<String, Object> map = new HashMap<String, Object>();
		map.put(DynamicInvoker.OPT_USESOAPACTION, true);

		Object ret = invokeWebService3(endpoint, serviceName, operationName, paramKeys, paramValues, map);
		System.out.println("str------" + ret);
		//List<String> paramTypes = Arrays.asList(new String[] { "String", "String", "String" });
	}

	/**
	 * 
	 * @param endpoint webservice地址
	 * @param nameSpace webservice的命名空间
	 * @param operationName 操作名称
	 * @param paramKeys 参数名称
	 * @param paramTypes 参数类型
	 * @param paramValues 参数值
	 * @return 返回字符串
	 * @throws ServiceException 
	 * @throws MalformedURLException 
	 * @throws Exception 
	 */
	public static Object invokeWebService(String endpoint, String nameSpace, String operationName, ArrayList<String> paramKeys,
			ArrayList<String> paramTypes, ArrayList<String> paramValues) throws Exception {
		Service service = new Service();
		Call call = (Call) service.createCall();// 通过service创建call对象
		// 设置service所在URL
		call.setTargetEndpointAddress(new java.net.URL(endpoint));
		call.setOperationName(new QName(nameSpace, operationName));
		for (int i = 0; i < paramKeys.size(); i++) {
			if (paramTypes.get(i).equals("String"))
				call.addParameter(new QName(nameSpace, paramKeys.get(i)), org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
			else if (paramTypes.get(i).equals("int"))
				call.addParameter(new QName(nameSpace, paramKeys.get(i)), org.apache.axis.encoding.XMLType.XSD_INT, javax.xml.rpc.ParameterMode.IN);
		}
		call.setUseSOAPAction(true);
		call.setReturnType(org.apache.axis.encoding.XMLType.SOAP_STRING); // 返回参数的类型(不能用Array，否则报错)
		call.setSOAPActionURI(nameSpace + operationName);
		return call.invoke(paramValues.toArray());

	}

	public static Object invokeWebService2(String endpoint, String serviceName, String operationName, List<String> paramKeys, List<Object> paramValues)
			throws Exception {
		DynamicInvoker invoker = invokers.get(endpoint);
		if (invoker == null) {
			invoker = new DynamicInvoker(endpoint);
			invokers.put(endpoint, invoker);
		}
		String portName = (String) invoker.enumPortNames(serviceName).get(0);
		return invoker.invoke(serviceName, portName, operationName, paramKeys, paramValues);
	}

	public static Object invokeWebService3(String endpoint, String serviceName, String operationName, List<String> paramKeys,
			List<Object> paramValues, Map<String, Object> options) throws Exception {
		DynamicInvoker invoker = invokers.get(endpoint);
		if (invoker == null) {
			invoker = new DynamicInvoker(endpoint);
			invokers.put(endpoint, invoker);
		}
		String portName;
		Vector<String> portNames = invoker.enumPortNames(serviceName);
		if (portNames.size() == 0) {
			throw new BusinessException(endpoint + "的服务" + serviceName + "中没有定义任何wsdl:port");
		}
		if (options.containsKey("portName")) {
			portName = (String) options.get("portName");
			if (!portNames.contains(portName))
				throw new BusinessException(endpoint + "的服务" + serviceName + "中不包含名为" + portName + "的wsdl:port");
		} else {
			portName = portNames.get(0);
		}
		if (options.containsKey(DynamicInvoker.OPT_USESOAPACTION)) {
			invoker.setUseSOAPAction((Boolean) options.get(DynamicInvoker.OPT_USESOAPACTION));
		}
		if (options.containsKey(DynamicInvoker.OPT_TIMEOUT)) {
			invoker.setTimeout((Integer) options.get(DynamicInvoker.OPT_TIMEOUT));
		}
		return invoker.invoke(serviceName, portName, operationName, paramKeys, paramValues);
	}
}