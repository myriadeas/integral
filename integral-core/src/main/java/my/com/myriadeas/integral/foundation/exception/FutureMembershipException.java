package my.com.myriadeas.integral.foundation.exception;

import my.com.myriadeas.integral.core.IntegralCoreException;


public class FutureMembershipException extends IntegralCoreException {

	private static final long serialVersionUID = 1L;
	
	private static final String messageId = "patron.futureMembership";

	public FutureMembershipException(String message, Object... arguments) {
		super(message, messageId, arguments);
	}

	public FutureMembershipException(String message, Throwable cause,
			Object... arguments) {
		super(message, cause, messageId, arguments);
	}

}
