package my.com.myriadeas.integral.core;

public abstract class IntegralReceiptingException extends IntegralBusinessLogicException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IntegralReceiptingException(String message, String messageId,
			Object... arguments) {
		super(message, messageId, arguments);
	}

	public IntegralReceiptingException(String message, Throwable cause,
			String messageId, Object... arguments) {
		super(message, cause, messageId, arguments);
	}

}
