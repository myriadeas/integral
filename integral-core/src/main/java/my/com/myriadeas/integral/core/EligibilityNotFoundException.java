package my.com.myriadeas.integral.core;

public class EligibilityNotFoundException extends IntegralCoreException {

	private static final long serialVersionUID = 1L;
	
	private static final String messageId = "eligibility.notFound";
	
	public EligibilityNotFoundException(String message, Object... arguments) {
		super(message, messageId, arguments);
	}

	public EligibilityNotFoundException(String message, Throwable cause,
			Object... arguments) {
		super(message, cause, messageId, arguments);
	}	

}
