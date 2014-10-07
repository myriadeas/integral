package my.com.myriadeas.integral.core.error;

public class IntegralError {

	private String message;
	private String messageId;
	private Object[] arguments;
	private String messageTemplate;
	private String cause;

	public IntegralError(String messageTemplate, String message, String messageId,
			String cause, Object... objects) {
		this.messageTemplate = messageTemplate;
		this.message = message;
		this.arguments = objects;
		this.messageId = messageId;
		this.cause = cause;
	}
	
	public IntegralError() {
		
	}

	public IntegralError(String messageId) {
		this.messageId = messageId;

	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public Object[] getArguments() {
		return arguments;
	}

	public void setArguments(Object[] arguments) {
		this.arguments = arguments;
	}

	public String getMessageTemplate() {
		return messageTemplate;
	}

	public void setMessageTemplate(String messageTemplate) {
		this.messageTemplate = messageTemplate;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

}