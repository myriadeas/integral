package my.com.myriadeas.integral.core;


public class PatronStatusNotFoundException extends IntegralCoreException {

	private static final long serialVersionUID = 1L;
	
	private static final String messageId = "patronStatus.notFound";

	public PatronStatusNotFoundException(String message, Object... arguments){
		super(message, messageId, arguments);		
	}
	
	public PatronStatusNotFoundException(String message, Throwable cause,
			Object[] arguments) {
		super(message, cause, messageId, arguments);	
	}

}
