package my.com.myriadeas.integral.circulation.exception;

import my.com.myriadeas.integral.core.IntegralCirculationException;

public class PatronItemEligibilityNotAllowReserveException extends
		IntegralCirculationException {
	private static final long serialVersionUID = 1L;

	private static final String messageId = "patronItemEligibility.notAllowedReserve";

	public PatronItemEligibilityNotAllowReserveException(String message,
			Object... arguments) {
		super(message, messageId, arguments);
	}

	public PatronItemEligibilityNotAllowReserveException(String message, Throwable cause,
			Object... arguments) {
		super(message, cause, messageId, arguments);
	}
}
