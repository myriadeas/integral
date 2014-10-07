package my.com.myriadeas.integral.core;

public class IntegralAcquisitionException extends IntegralBusinessLogicException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IntegralAcquisitionException(String message, String messageId,
			Object... arguments) {
		super(message, messageId, arguments);
	}

	public IntegralAcquisitionException(String message, Throwable cause,
			String messageId, Object... arguments) {
		super(message, cause, messageId, arguments);
	}

}
