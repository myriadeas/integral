package my.com.myriadeas.integral.core;

public class MaterialNotFoundException extends IntegralCoreException {

	private static final long serialVersionUID = 1L;
	
	private static final String messageId = "material.notFound";

	public MaterialNotFoundException(String message, Object... arguments) {
		super(message, messageId, arguments);	
	}

	public MaterialNotFoundException(String message, Throwable cause,
			Object... arguments) {
		super(message, cause, messageId, arguments);
	}
}
