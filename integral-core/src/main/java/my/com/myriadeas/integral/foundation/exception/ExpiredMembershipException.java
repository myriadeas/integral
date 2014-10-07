package my.com.myriadeas.integral.foundation.exception;

import my.com.myriadeas.integral.core.IntegralCoreException;


public class ExpiredMembershipException extends IntegralCoreException {
	
	private static final long serialVersionUID = 1L;
	
	private static final String messageId = "patron.expiredMembership";
	
	public ExpiredMembershipException(String message, Object... arguments) {
		super(message, messageId, arguments);
	}

	public ExpiredMembershipException(String message, Throwable cause,
			Object... arguments) {
		super(message, cause, messageId, arguments);
	}
}
