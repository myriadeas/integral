package my.com.myriadeas.integral.core;

public class IntegralBusinessLogicException extends IntegralRuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String messageId;

	private Object[] arguments;
	
	public IntegralBusinessLogicException(String message) {
		super(message);
	}

	public IntegralBusinessLogicException(String message, String messageId,
			Object[] arguments) {
		super(message);
		this.setArguments(arguments);
		this.setMessageId(messageId);
	}

	public IntegralBusinessLogicException(String message, Throwable cause) {
		super(message, cause);
	}

	public IntegralBusinessLogicException(String message, Object[] arguments) {
		super(message);
		this.setArguments(arguments);
	}

	public IntegralBusinessLogicException(String message, Throwable cause,
			String msgId, Object[] arguments) {
		super(message, cause);
		this.setMessageId(msgId);
		this.setArguments(arguments);
	}

	public Object[] getArguments() {
		return arguments;
	}

	public void setArguments(Object[] arguments) {
		this.arguments = arguments;
	}

	public String getMessageId() {
		return this.messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

}
