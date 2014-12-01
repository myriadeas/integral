package my.com.myriadeas.integral.core.handler;

import java.util.Arrays;
import java.util.Locale;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import my.com.myriadeas.integral.core.IntegralBusinessLogicException;
import my.com.myriadeas.integral.core.error.IntegralError;
import my.com.myriadeas.integral.core.error.IntegralErrors;
import my.com.myriadeas.integral.internalization.InternalizationMessageSource;

import org.apache.camel.ExchangeException;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.ObjectError;

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

	public void handle(
			@ExchangeException IntegralBusinessLogicException exception) {
		logger.error("integral business logic exception", exception);
		IntegralErrors errors = new IntegralErrors();
		IntegralError exceptionError = new IntegralError();
		exceptionError.setMessageId(exception.getMessageId());
		if (exception.getArguments() != null)
			exceptionError
					.setArguments(Arrays.asList(exception.getArguments()));
		exceptionError.setCause(ExceptionUtils.getRootCauseMessage(exception));
		exceptionError.setMessage(messageSource.getMessage(
				exception.getMessageId(), exception.getArguments(),
				exception.getMessage(), Locale.US));
		exceptionError.setMessageTemplate("MessageTemplate");
		errors.addError(exceptionError);
		if (exception.hasErrors()) {
			IntegralError error;
			for (ObjectError objectError : exception.getErrors().getAllErrors()) {
				error = new IntegralError();
				error.setMessage(messageSource.getMessage(
						objectError.getCode(), objectError.getArguments(),
						objectError.getDefaultMessage(), Locale.US));
				error.setArguments(Arrays.asList(objectError.getArguments()));
				error.setMessageId(objectError.getCode());
			}

		}
		logger.debug("Errors={}", errors);
		Response response = Response
				.status(javax.ws.rs.core.Response.Status.BAD_REQUEST)
				.entity(errors).build();
		WebApplicationException webApplicationException = new WebApplicationException(
				exception, response);
		logger.error("web application exception", webApplicationException);
		throw webApplicationException;
	}

}
