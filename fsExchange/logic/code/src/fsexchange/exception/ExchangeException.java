package fsexchange.exception;

public class ExchangeException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2938987140291633438L;
	

	public ExchangeException(String msg) {
		super(msg);
	}

	public ExchangeException(String msg, Throwable cause) {
		super(msg, cause);
	}

	
}
