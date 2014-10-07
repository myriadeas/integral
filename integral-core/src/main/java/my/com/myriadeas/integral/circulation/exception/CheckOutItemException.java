package my.com.myriadeas.integral.circulation.exception;

import my.com.myriadeas.integral.core.IntegralCirculationException;

public class CheckOutItemException extends
		IntegralCirculationException {

	private static final long serialVersionUID = 1L;

	private static final String messageId = "circulation.checkout.error";

	public CheckOutItemException(String message,
			Object... arguments) {
		super(message, messageId, arguments);
	}

	public CheckOutItemException(String message,
			Throwable cause, Object... arguments) {
		super(message, cause, messageId, arguments);
	}
}
