package my.com.myriadeas.integral.core.handler;

import java.util.Locale;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import my.com.myriadeas.integral.core.IntegralBusinessLogicException;
import my.com.myriadeas.integral.core.error.IntegralError;
import my.com.myriadeas.integral.internalization.InternalizationMessageSource;

import org.apache.camel.ExchangeException;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("integralBusinessLogicExceptionRestHandler")
public class IntegralBusinessLogicExceptionRestHandler implements
		IntegralBusinessLogicExceptionHandler {

	private static final Logger logger = LoggerFactory
			.getLogger(IntegralBusinessLogicExceptionRestHandler.class);
	
	private InternalizationMessageSource messageSource;

	public InternalizationMessageSource getMessageSource() {
		return messageSource;
	}
	
	@Autowired
	public void setMessageSource(InternalizationMessageSource messageSource) {
		this.messageSource = messageSource;
	}
	

	public void handle(@ExchangeException IntegralBusinessLogicException exception) {
		logger.error("integral business logic exception", exception);
		System.out.println("exceptionn happended=" + exception);
	
		IntegralError integralError = new IntegralError();
		integralError.setMessageId(exception.getMessageId());
		if (exception.getArguments() != null) {
			integralError.setArguments(exception.getArguments());
		}
		integralError.setCause(ExceptionUtils.getRootCauseMessage(exception));

		integralError.setMessage(messageSource.getMessage(exception.getMessageId(),
				exception.getArguments(), exception.getMessage(), Locale.US));

		// TODO get locale from session
		// TODO do we want to provide message template?
		integralError.setMessageTemplate("MessageTemplate");

		logger.error("integral error", integralError);
		Response response = Response.status(Response.Status.BAD_REQUEST)
				.entity(integralError).build();
		WebApplicationException webApplicationException = new WebApplicationException(
				exception, response);
		logger.error("web application exception", webApplicationException);
		throw webApplicationException;
	}

}
