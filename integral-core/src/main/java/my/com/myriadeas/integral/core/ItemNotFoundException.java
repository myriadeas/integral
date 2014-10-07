package my.com.myriadeas.integral.core;

public class ItemNotFoundException extends IntegralCoreException {

	private static final long serialVersionUID = 1L;
	
	private static final String messageId = "item.notFound";

	public ItemNotFoundException(String message, Object... arguments) {
		super(message, messageId, arguments);	
	}

	public ItemNotFoundException(String message, Throwable cause,
			Object... arguments) {
		super(message, cause, messageId, arguments);
	}
}
