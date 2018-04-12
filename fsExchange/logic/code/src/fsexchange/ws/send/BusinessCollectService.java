package fsexchange.ws.send;

import fsexchange.exception.SoapException;

public interface BusinessCollectService extends ServicePort {

	String postData(String in0, String in1, String in2) throws SoapException;
}
