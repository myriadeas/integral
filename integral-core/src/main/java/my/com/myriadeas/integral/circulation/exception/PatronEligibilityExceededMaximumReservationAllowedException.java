package my.com.myriadeas.integral.circulation.exception;

import my.com.myriadeas.integral.core.IntegralCirculationException;

public class PatronEligibilityExceededMaximumReservationAllowedException extends
		IntegralCirculationException {
	private static final long serialVersionUID = 1L;
	
	private static final String messageId = "patronEligibility.exceededMaximumReservationAllowed";

	public PatronEligibilityExceededMaximumReservationAllowedException(String message, Object... arguments) {
		super(message, messageId, arguments);	
	}

	public PatronEligibilityExceededMaximumReservationAllowedException(String message, Throwable cause,
			Object... arguments) {
		super(message, cause, messageId, arguments);
	}
}
