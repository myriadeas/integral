package my.com.myriadeas.integral.cataloguing.exception;

import my.com.myriadeas.integral.core.IntegralRuntimeException;

/**
 * Unrecoverable exception. Consumer of dao unable to recover from the errors. 
 * @author hutingung
 *
 */
public class IntegralDaoException extends IntegralRuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6950621521118761653L;

	public IntegralDaoException(String msg) {
		super(msg);
	}

	public IntegralDaoException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
