package my.com.myriadeas.integral.fines;

import my.com.myriadeas.integral.core.IntegralCirculationException;

public class FineNotFoundException extends IntegralCirculationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FineNotFoundException(String message, String messageId,
			Object... arguments) {
		super(message, messageId, arguments);
	}
	
	public FineNotFoundException(String message,Throwable cause,  String messageId, 
			Object... arguments) {
		super(message, cause,messageId,  arguments);
	}

}
