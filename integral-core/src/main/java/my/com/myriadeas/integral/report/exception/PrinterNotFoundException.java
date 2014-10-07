package my.com.myriadeas.integral.report.exception;

import my.com.myriadeas.integral.core.IntegralRuntimeException;

public class PrinterNotFoundException extends IntegralRuntimeException {

	private static final long serialVersionUID = 1L;

	public PrinterNotFoundException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

	public PrinterNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
		// TODO Auto-generated constructor stub
	}

}
