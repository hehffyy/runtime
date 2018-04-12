package fsexchange.ws.send;

import java.util.Map;

import javax.xml.ws.BindingProvider;

import com.sun.xml.ws.client.BindingProviderProperties;

import fsexchange.exception.SoapException;
import fsexchange.ws.stub.businesscollect.BusinessCollectWebService;
import fsexchange.ws.stub.businesscollect.BusinessCollectWebServicePortType;

public class BusinessCollectServiceImpl extends Service implements BusinessCollectService {
	private BusinessCollectWebService service;
	private String wsUrl;
	private String intferfaceName;

	public BusinessCollectServiceImpl() {
		super();
	}

	public BusinessCollectServiceImpl(String wsdl, String intferfaceName) {
		super();
		this.setWsUrl(wsdl);
		this.setIntferfaceName(intferfaceName);
	}

	public String getWsUrl() {
		return wsUrl;
	}

	public void setWsUrl(String wsUrl) {
		this.wsUrl = wsUrl;
	}

	public String getIntferfaceName() {
		return intferfaceName;
	}

	public void setIntferfaceName(String intferfaceName) {
		this.intferfaceName = intferfaceName;
	}

	private BusinessCollectWebServicePortType getPort() {
		if (service == null) {
			this.service = new BusinessCollectWebService(getWsdlLocation(wsUrl, intferfaceName), BusinessCollectWebService._getServiceName());
		}
		BusinessCollectWebServicePortType result = service.getBusinessCollectWebServiceHttpPort();
		Map<String, Object> ctxt = ((BindingProvider) result).getRequestContext();
		// 连接超时(10秒)
		ctxt.put(BindingProviderProperties.CONNECT_TIMEOUT, 1000 * 10);
		// 请求超时(60秒)
		ctxt.put(BindingProviderProperties.REQUEST_TIMEOUT, 1000 * 60);
		return result;
	}

	public String postData(String in0, String in1, String in2) throws SoapException {
		BusinessCollectWebServicePortType port = getPort();
		if (port == null) {
			throw new SoapException("无法建立连接");
		} else {
			return port.saveOrUpdateBusinessCollect(in0, in1, in2);
		}
	}

}
