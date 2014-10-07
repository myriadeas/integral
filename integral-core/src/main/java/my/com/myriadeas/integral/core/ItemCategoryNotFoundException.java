package my.com.myriadeas.integral.core;

public class ItemCategoryNotFoundException extends IntegralCoreException {

	private static final long serialVersionUID = 1L;
	
	private static final String messageId = "itemCategory.notFound";

	public ItemCategoryNotFoundException(String message, Object... arguments) {
		super(message, messageId, arguments);	
	}

	public ItemCategoryNotFoundException(String message, Throwable cause,
			Object... arguments) {
		super(message, cause, messageId, arguments);
	}
}
