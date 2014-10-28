package my.com.myriadeas.integral.cataloguing2.exception;

import my.com.myriadeas.integral.core.IntegralCataloguingException;

public class UnsupportedStatusTransitionException extends
		IntegralCataloguingException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2966182063840219774L;

	private static final String messageId = "cataloguing.unsupported.status.error";

	public UnsupportedStatusTransitionException(String message,
			Object... arguments) {
		super(message, messageId, arguments);
	}

	public UnsupportedStatusTransitionException(String message,
			Throwable cause, Object... arguments) {
		super(message, cause, messageId, arguments);
	}
}
