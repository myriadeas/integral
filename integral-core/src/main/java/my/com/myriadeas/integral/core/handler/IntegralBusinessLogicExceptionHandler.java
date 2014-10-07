package my.com.myriadeas.integral.core.handler;

import my.com.myriadeas.integral.core.IntegralBusinessLogicException;

import org.apache.camel.ExchangeException;
public interface IntegralBusinessLogicExceptionHandler {

	

	public void handle(@ExchangeException IntegralBusinessLogicException exception);

}
