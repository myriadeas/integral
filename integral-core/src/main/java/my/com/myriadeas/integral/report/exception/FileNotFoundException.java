package my.com.myriadeas.integral.report.exception;

import my.com.myriadeas.integral.core.IntegralRuntimeException;

public class FileNotFoundException extends IntegralRuntimeException {

	private static final long serialVersionUID = 1L;

	public FileNotFoundException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

	public FileNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
		// TODO Auto-generated constructor stub
	}

}
