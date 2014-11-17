package my.com.myriadeas.integral.index.domain.service;

import my.com.myriadeas.integral.core.IntegralBusinessLogicException;

public class IndexerTransformationException extends
		IntegralBusinessLogicException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IndexerTransformationException(String message, Object[] arguments) {
		super(message, arguments);
	}

	public IndexerTransformationException(String message, String messageId,
			Object[] arguments) {
		super(message, messageId, arguments);
	}

	public IndexerTransformationException(String message, Throwable cause,
			String msgId, Object[] arguments) {
		super(message, cause, msgId, arguments);
	}

	public IndexerTransformationException(String message, Throwable cause) {
		super(message, cause);
	}

	public IndexerTransformationException(String message) {
		super(message);
	}

}
