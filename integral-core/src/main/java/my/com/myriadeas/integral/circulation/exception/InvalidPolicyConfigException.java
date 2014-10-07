package my.com.myriadeas.integral.circulation.exception;

import my.com.myriadeas.integral.core.IntegralCirculationException;

public class InvalidPolicyConfigException extends IntegralCirculationException {

	private static final long serialVersionUID = 1L;

	private static final String messageId = "itemStatus.availableStatus";

	public InvalidPolicyConfigException(String message,
			Object... arguments) {
		super(message, messageId, arguments);
	}

	public InvalidPolicyConfigException(String message, Throwable cause,
			Object... arguments) {
		super(message, cause, messageId, arguments);
	}
}
