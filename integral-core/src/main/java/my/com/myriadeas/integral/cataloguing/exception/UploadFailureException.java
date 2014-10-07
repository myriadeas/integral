package my.com.myriadeas.integral.cataloguing.exception;

import my.com.myriadeas.integral.core.IntegralCataloguingException;

public class UploadFailureException extends IntegralCataloguingException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2966182063840219774L;

	private static final String messageId = "upload.failed";

	public UploadFailureException(String message, Object... arguments) {
		super(message, messageId, arguments);
	}

	public UploadFailureException(String message, Throwable cause,
			Object... arguments) {
		super(message, cause, messageId, arguments);
	}
}
