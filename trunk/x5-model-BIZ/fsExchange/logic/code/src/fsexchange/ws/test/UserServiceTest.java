package fsexchange.ws.test;

import fsexchange.exception.SoapException;
import fsexchange.ws.send.Service;
import fsexchange.ws.send.UserService;

public class UserServiceTest implements UserService {

	@Override
	public void setWsUrl(String wsUrl) {
		// TODO 自动生成的方法存根

	}

	@Override
	public void setIntferfaceName(String intferfaceName) {
		// TODO 自动生成的方法存根

	}

	@Override
	public boolean checkName(String name) throws SoapException {
		return true;
	}

	@Override
	public String encodePassword(String password) throws SoapException {
		return password;
	}

	@Override
	public String getIdByUserLogon(String logLoginname, String password) throws SoapException {
		return logLoginname;
	}

	@Override
	public String registerUser(String userPid, String logLoginname, String userName, String password, String userAccount) throws SoapException {
		return userAccount != null ? userAccount : Service.createNewGuid("FSGT", 30);
	}
}
