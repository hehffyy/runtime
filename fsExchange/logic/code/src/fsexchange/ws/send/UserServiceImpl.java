package fsexchange.ws.send;

import java.util.Date;
import java.util.Map;

import javax.xml.ws.BindingProvider;

import com.sun.xml.ws.client.BindingProviderProperties;

import fsexchange.exception.SoapException;
import fsexchange.util.TimeUtil;
import fsexchange.ws.stub.userservice.IUserService;
import fsexchange.ws.stub.userservice.IUserServicePortType;
import fsexchange.ws.stub.userservice.UserInformation;
import fsexchange.ws.stub.userservice.UserlogOn;

public class UserServiceImpl extends Service implements UserService {
	public static void main(String[] args) {
		UserServiceImpl service = new UserServiceImpl();
		service.setWsUrl("http://wssp.fsxzfw.gov.cn/service");
		service.setIntferfaceName("IUserService");
		try {
			System.out.println(service.registerUser("test", "000000000000000001", "测试1", "c80a5a20b54aa1148a00d36786a41a66", null));
			// System.out.println(service.encodePassword("888888"));
		} catch (SoapException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private IUserService service;
	private String wsUrl;
	private String intferfaceName;

	public UserServiceImpl() {
		super();
	}

	public UserServiceImpl(String wsdl, String intferfaceName) {
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

	protected IUserServicePortType getServicePort() {
		if (service == null) {
			this.service = new IUserService(this.getWsdlLocation(wsUrl, intferfaceName), IUserService._getServiceName());
		}
		IUserServicePortType result = service.getIUserServiceHttpPort();
		Map<String, Object> ctxt = ((BindingProvider) result).getRequestContext();
		// 连接超时(10秒)
		ctxt.put(BindingProviderProperties.CONNECT_TIMEOUT, 1000 * 10);
		// 请求超时(60秒)
		ctxt.put(BindingProviderProperties.REQUEST_TIMEOUT, 1000 * 60);
		return result;
	}

	public boolean checkName(String name) throws SoapException {
		IUserServicePortType port = getServicePort();
		if (port == null) {
			throw new SoapException("无法建立连接:" + wsUrl);
		} else {
			return port.checkName(name);
		}
	}

	public String encodePassword(String password) throws SoapException {
		IUserServicePortType port = getServicePort();
		if (port == null) {
			throw new SoapException("无法建立连接:" + wsUrl);
		} else {
			return port.encodePassword(password);
		}
	}

	public String getIdByUserLogon(String logLoginname, String password) throws SoapException {
		IUserServicePortType port = getServicePort();
		if (port == null) {
			throw new SoapException("无法建立连接:" + wsUrl);
		}
		String ecodePS = this.encodePassword(password);
		return port.getIdByUserLogon(logLoginname, ecodePS);
	}

	public String registerUser(String userPid, String logLoginname, String userName, String password, String userAccount) throws SoapException {
		IUserServicePortType port = getServicePort();
		if (port == null) {
			throw new SoapException("无法建立连接:" + wsUrl);
		} else {
			String id = userAccount != null ? userAccount : createNewGuid("FSGT", 30);
			String ecodePS = this.encodePassword(password);
			UserInformation info = new UserInformation();
			info.setId(IUserService.getElement("id", id));
			info.setUserUid(IUserService.getElement("userUid", id));
			if (userName == null) {
				info.setUserName(IUserService.getElement("userName", logLoginname));
			} else {
				info.setUserName(IUserService.getElement("userName", userName));
			}
			info.setUserClew(IUserService.getElement("userClew", logLoginname));
			info.setUserAnswer(IUserService.getElement("userAnswer", logLoginname));

			info.setUserGender(IUserService.getElement("userGender", "0"));
			info.setUserPid(IUserService.getElement("userPid", userPid));
			info.setUserMobile(IUserService.getElement("userMobile", "无"));
			info.setUserAddress(IUserService.getElement("userAddress", "无"));
			info.setUserDate(TimeUtil.convertToXMLGregorianCalendar(new Date()));
			info.setChangetype(IUserService.getElement("changetype", "C"));
			info.setExchange(IUserService.getElement("exchange", "-1"));
			UserlogOn logon = new UserlogOn();
			logon.setLogUid(IUserService.getElement("logUid", id));
			logon.setLogLoginname(IUserService.getElement("logLoginname", logLoginname));

			logon.setLogPwd(IUserService.getElement("logPwd", ecodePS));
			logon.setLogSort(IUserService.getElement("logSort", "1"));
			logon.setLogProvetype(IUserService.getElement("logProvetype", "0"));
			logon.setCreateId(IUserService.getElement("createId", id));
			logon.setStatus(IUserService.getElement("status", "1"));
			logon.setChangetype(IUserService.getElement("changetype", "C"));
			logon.setExchange(IUserService.getElement("exchange", "-1"));
			int r = port.saveUserInformation(info, logon);
			if (r == 0) {
				return id;
			} else {
				return null;
			}
		}
	}
}
