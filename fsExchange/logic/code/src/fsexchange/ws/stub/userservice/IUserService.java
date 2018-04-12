package fsexchange.ws.stub.userservice;

import java.net.URL;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;

public class IUserService extends Service {

	public static QName _getServiceName(){
		return new QName(
				"http://service.tjsoft.com", "IUserService");
	}
	
	public static JAXBElement<String> getElement(String name,String value){
		return new JAXBElement<String>(new QName(
			     "http://model.tjsoft.com", name), String.class, value);
	}

	public IUserService(URL wsdlLocation, QName serviceName) {
		super(wsdlLocation, serviceName);
	}

	/*
	public IUserService() {
		super(IUSERSERVICE_WSDL_LOCATION, new QName(
				"http://service.tjsoft.com", "IUserService"));
	}*/

	/**
	 * 
	 * @return returns IUserServicePortType
	 */
	@WebEndpoint(name = "IUserServiceHttpPort")
	public IUserServicePortType getIUserServiceHttpPort() {
		return super.getPort(new QName("http://service.tjsoft.com",
				"IUserServiceHttpPort"), IUserServicePortType.class);
	}

}

