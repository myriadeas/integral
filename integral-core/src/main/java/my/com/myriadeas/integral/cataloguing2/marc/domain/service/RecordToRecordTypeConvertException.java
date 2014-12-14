package my.com.myriadeas.integral.cataloguing2.marc.domain.service;

import org.springframework.validation.Errors;

import my.com.myriadeas.integral.cataloguing2.CataloguingException;

public class RecordToRecordTypeConvertException extends CataloguingException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RecordToRecordTypeConvertException(String message, String messageId,
			Errors result) {
		super(message, messageId, result);
	}

	public RecordToRecordTypeConvertException(String message, String messageId,
			Object[] arguments) {
		super(message, messageId, arguments);
	}

	public RecordToRecordTypeConvertException(String message, Throwable cause,
			String msgId, Object[] arguments) {
		super(message, cause, msgId, arguments);
	}

	public RecordToRecordTypeConvertException(String message, Throwable cause) {
		super(message, cause);
	}

	public RecordToRecordTypeConvertException(String message) {
		super(message);
	}

}
