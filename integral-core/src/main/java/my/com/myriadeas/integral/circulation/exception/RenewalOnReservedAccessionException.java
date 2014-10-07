package my.com.myriadeas.integral.circulation.exception;

import my.com.myriadeas.integral.core.IntegralCirculationException;

public class RenewalOnReservedAccessionException extends IntegralCirculationException {

	private static final long serialVersionUID = 1L;
	
	private static final String messageId = "item.renewOnReserveItem";

	public RenewalOnReservedAccessionException(String message, Object... arguments) {
		super(message, messageId, arguments);	
	}

	public RenewalOnReservedAccessionException(String message, Throwable cause,
			Object... arguments) {
		super(message, cause, messageId, arguments);
	}
}
