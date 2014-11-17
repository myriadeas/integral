package my.com.myriadeas.integral.index.domain.model;

import my.com.myriadeas.integral.core.IntegralBusinessLogicException;

public class IndexException extends IntegralBusinessLogicException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IndexException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public IndexException(String message, String messageId, Object[] arguments) {
		super(message, messageId, arguments);
		// TODO Auto-generated constructor stub
	}

	public IndexException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public IndexException(String message, Object[] arguments) {
		super(message, arguments);
		// TODO Auto-generated constructor stub
	}

	public IndexException(String message, Throwable cause, String msgId,
			Object[] arguments) {
		super(message, cause, msgId, arguments);
		// TODO Auto-generated constructor stub
	}

}
