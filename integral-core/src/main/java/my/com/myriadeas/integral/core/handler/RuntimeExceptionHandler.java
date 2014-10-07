package my.com.myriadeas.integral.core.handler;

import org.apache.camel.ExchangeException;

public interface RuntimeExceptionHandler {
	public void handle(@ExchangeException RuntimeException exception);
}
