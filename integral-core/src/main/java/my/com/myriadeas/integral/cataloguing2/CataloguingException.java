package my.com.myriadeas.integral.cataloguing2;

import org.springframework.validation.Errors;

import my.com.myriadeas.integral.core.IntegralBusinessLogicException;

public class CataloguingException extends IntegralBusinessLogicException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CataloguingException(String message, String messageId, Errors result) {
		super(message, messageId, result);
	}

	public CataloguingException(String message, String messageId,
			Object[] arguments) {
		super(message, messageId, arguments);
		// TODO Auto-generated constructor stub
	}

	public CataloguingException(String message, Throwable cause, String msgId,
			Object[] arguments) {
		super(message, cause, msgId, arguments);
		// TODO Auto-generated constructor stub
	}

	public CataloguingException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public CataloguingException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
