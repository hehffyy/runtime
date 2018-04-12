package fsexchange.ws.send;

import fsexchange.exception.SoapException;

public interface UserService extends ServicePort {

	boolean checkName(String name) throws SoapException;

	String encodePassword(String password) throws SoapException;

	String getIdByUserLogon(String logLoginname, String password) throws SoapException;

	String registerUser(String userPid, String logLoginname, String userName, String password, String userAccount) throws SoapException;
}
