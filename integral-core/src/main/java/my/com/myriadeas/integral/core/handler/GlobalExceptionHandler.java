package my.com.myriadeas.integral.core.handler;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import my.com.myriadeas.integral.core.error.IntegralError;

import org.apache.camel.ExchangeException;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("globalExceptionHandler")
public class GlobalExceptionHandler implements RuntimeExceptionHandler {

	private static final Logger logger = LoggerFactory
			.getLogger(GlobalExceptionHandler.class);

	public void handle(@ExchangeException RuntimeException exception) {
		logger.error("integral system error", exception);
		IntegralError integralError = new IntegralError();
		integralError.setMessageId("system.error");
		integralError.setCause(ExceptionUtils.getRootCauseMessage(exception));
		integralError.setMessage(exception.getMessage());
		integralError.setMessageTemplate("MessageTemplate");

		logger.error("integral error", integralError);
		Response response = Response.status(Response.Status.INTERNAL_SERVER_ERROR)
				.entity(integralError).build();
		WebApplicationException webApplicationException = new WebApplicationException(
				exception, response);
		logger.error("web application exception", webApplicationException);
		throw webApplicationException;
	}

}
