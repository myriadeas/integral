package my.com.myriadeas.integral.core;


public class PatronEligibilityNotFoundException extends IntegralCirculationException {

	private static final long serialVersionUID = 1L;
	
	private static final String messageId = "patronEligibility.notFound";
	
	public PatronEligibilityNotFoundException(String message, Object... arguments) {
		super(message, messageId, arguments);
	}

	public PatronEligibilityNotFoundException(String message, Throwable cause,
			Object... arguments) {
		super(message, cause, messageId, arguments);
	}

}
