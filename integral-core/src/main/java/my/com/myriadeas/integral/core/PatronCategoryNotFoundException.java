package my.com.myriadeas.integral.core;

public class PatronCategoryNotFoundException extends IntegralCoreException {

	private static final long serialVersionUID = 1L;
	
	private static final String messageId = "patronCategory.notFound";
	
	public PatronCategoryNotFoundException(String message, Object... arguments) {
		super(message, messageId, arguments);
	}

	public PatronCategoryNotFoundException(String message, Throwable cause,
			Object... arguments) {
		super(message, cause, messageId, arguments);
	}	

}
