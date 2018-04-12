package fsexchange.exception;

public class SoapException extends Exception {

	private static final long serialVersionUID = 1L;

	public SoapException(String msg) {
		super(msg);
	}

	public SoapException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
