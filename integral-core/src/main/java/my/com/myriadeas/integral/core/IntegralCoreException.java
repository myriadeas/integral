package my.com.myriadeas.integral.core;

public class IntegralCoreException extends IntegralBusinessLogicException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IntegralCoreException(String message, String messageId,
			Object[] arguments) {
		super(message, messageId, arguments);
		// TODO Auto-generated constructor stub
	}

	public IntegralCoreException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public IntegralCoreException(String message, Throwable cause,
			String msgId, Object... arguments) {
		super(message, cause);
		this.setMessageId(msgId);
		this.setArguments(arguments);
	}
}
