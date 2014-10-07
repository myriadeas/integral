package my.com.myriadeas.integral.core;

public abstract class IntegralCirculationException extends IntegralBusinessLogicException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IntegralCirculationException(String message, String messageId,
			Object... arguments) {
		super(message, messageId, arguments);
	}

	public IntegralCirculationException(String message, Throwable cause,
			String messageId, Object... arguments) {
		super(message, cause, messageId, arguments);
	}

}
