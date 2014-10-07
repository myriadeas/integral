package my.com.myriadeas.integral.eligibility.exception;

import my.com.myriadeas.integral.core.IntegralBusinessLogicException;

public class EligibilityNotFoundException extends IntegralBusinessLogicException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2848749076963037074L;

	private static final String messageId = "Glelig.notFound";

	public EligibilityNotFoundException(String message, Object... arguments) {
		super(message, messageId, arguments);
	}

	public EligibilityNotFoundException(String message, Throwable cause,
			Object... arguments) {
		super(message, cause, messageId, arguments);
	}

}
