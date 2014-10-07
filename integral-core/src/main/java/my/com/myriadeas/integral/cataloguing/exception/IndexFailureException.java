package my.com.myriadeas.integral.cataloguing.exception;

import my.com.myriadeas.integral.core.IntegralCataloguingException;

public class IndexFailureException extends IntegralCataloguingException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String messageId = "indexing.failed";

	public IndexFailureException(String message, Object... arguments) {
		super(message, messageId, arguments);
	}

	public IndexFailureException(String message, Throwable cause,
			Object... arguments) {
		super(message, cause, messageId, arguments);
	}

}
