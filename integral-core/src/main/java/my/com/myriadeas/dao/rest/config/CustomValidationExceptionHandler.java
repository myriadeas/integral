package my.com.myriadeas.dao.rest.config;

import java.util.Locale;

import javax.validation.ConstraintViolationException;

import org.springframework.context.MessageSource;
import org.springframework.data.rest.webmvc.support.ConstraintViolationExceptionMessage;
import org.springframework.data.rest.webmvc.support.ValidationExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

public class CustomValidationExceptionHandler extends
		ValidationExceptionHandler {

	public ResponseEntity<?> handleValidationException(RuntimeException ex,
			MessageSource msgsrc, Locale locale) {
		Throwable cause = ex;
		/*
		 * Must get the root cause.
		 */
		while (cause.getCause() != null) {
			cause = cause.getCause();
		}
		Assert.isAssignable(ConstraintViolationException.class,
				cause.getClass());
		return new ResponseEntity<ConstraintViolationExceptionMessage>(
				new ConstraintViolationExceptionMessage(
						(ConstraintViolationException) cause, msgsrc, locale),
				HttpStatus.BAD_REQUEST

		);
	}
}
