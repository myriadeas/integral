package my.com.myriadeas.integral.circulation.exception;

import my.com.myriadeas.integral.core.IntegralCirculationException;

public class HoldItemStatusNotAllowedForCheckOutException extends
		IntegralCirculationException {

	private static final long serialVersionUID = 1L;

	private static final String messageId = "circulation.holdItemStatusNotAllowedForCheckOut";

	public HoldItemStatusNotAllowedForCheckOutException(String message,
			Object... arguments) {
		super(message, messageId, arguments);
	}

	public HoldItemStatusNotAllowedForCheckOutException(String message,
			Throwable cause, Object... arguments) {
		super(message, cause, messageId, arguments);
	}
}
