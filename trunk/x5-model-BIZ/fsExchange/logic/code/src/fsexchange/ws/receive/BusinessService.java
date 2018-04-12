package fsexchange.ws.receive;

import javax.jws.WebService;

@WebService(targetNamespace = "http://service.city.wonders.com/", serviceName = "XZSP_BUSINESS_BMP_BusinessService", portName = "XZSP_BUSINESS_BMP_BusinessServiceHttpPort", wsdlLocation = "WEB-INF/wsdl/BusinessService.wsdl")
public interface BusinessService {
	public String addBusinessdata(String orderid, String cid, String cbsnum, String permid, String subpermid, String areaid, String bsnum,
			String commitdate, String xmldoc, String busistatus, String bstype, String changedate, String archangedate);
}
