package my.com.myriadeas.integral.core;

public class IntegralSerialsException extends IntegralBusinessLogicException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IntegralSerialsException(String message, String messageId,
			Object... arguments) {
		super(message, messageId, arguments);
	}

	public IntegralSerialsException(String message, Throwable cause,
			String messageId, Object... arguments) {
		super(message, cause, messageId, arguments);
	}

}
