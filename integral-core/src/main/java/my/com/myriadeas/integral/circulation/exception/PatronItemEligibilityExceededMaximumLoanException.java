package my.com.myriadeas.integral.circulation.exception;

import my.com.myriadeas.integral.core.IntegralCirculationException;

public class PatronItemEligibilityExceededMaximumLoanException extends IntegralCirculationException {

	private static final long serialVersionUID = 1L;
	
	private static final String messageId = "patronItemEligibility.exceededMaximumLoan";

	public PatronItemEligibilityExceededMaximumLoanException(String message, Object... arguments) {
		super(message, messageId, arguments);
	}

	public PatronItemEligibilityExceededMaximumLoanException(String message, Throwable cause,
			Object... arguments) {
		super(message, cause, messageId, arguments);
	}
}
