package my.com.myriadeas.integral.core;


public class PatronNotFoundException extends IntegralCoreException {

	private static final long serialVersionUID = 1L;
	
	private static final String messageId = "patron.notFound";

	public PatronNotFoundException(String message, Object... arguments){
		super(message, messageId, arguments);		
	}
	
	public PatronNotFoundException(String message, Throwable cause,
			Object[] arguments) {
		super(message, cause, messageId, arguments);	
	}

}
