package my.com.myriadeas.integral.circulation2;

import org.springframework.validation.Errors;

import my.com.myriadeas.integral.core.IntegralBusinessLogicException;

public class CirculationException extends IntegralBusinessLogicException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CirculationException(String message, String messageId, Errors result) {
		super(message, messageId, result);
	}

	public CirculationException(String message, String messageId,
			Object[] arguments) {
		super(message, messageId, arguments);
		// TODO Auto-generated constructor stub
	}

	public CirculationException(String message, Throwable cause, String msgId,
			Object[] arguments) {
		super(message, cause, msgId, arguments);
		// TODO Auto-generated constructor stub
	}

	public CirculationException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public CirculationException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
