package my.com.myriadeas.integral.core;

public class IntegralCataloguingException extends IntegralBusinessLogicException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IntegralCataloguingException(String message, String messageId,
			Object... arguments) {
		super(message, messageId, arguments);
	}

	public IntegralCataloguingException(String message, Throwable cause,
			String messageId, Object... arguments) {
		super(message, cause, messageId, arguments);
	}

}
