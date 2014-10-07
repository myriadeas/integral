package my.com.myriadeas.integral.internalization.services;

import java.util.List;
import java.util.Locale;

import my.com.myriadeas.integral.internalization.InternalizationMessageSource;
import my.com.myriadeas.integral.internalization.JsonInternalizationMessageSource;
import my.com.myriadeas.integral.internalization.Message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service(value = "localizationServiceImpl")
public class LocalizationServiceImpl {

	private static final String MESSAGE_PREFIX = "resources-locale_";

	private static final Logger logger = LoggerFactory
			.getLogger(LocalizationServiceImpl.class);

	@Autowired
	private InternalizationMessageSource messageSource;
	
	public LocalizationServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	public List<Message> getMessages(String resourceName) {
		Locale locale = getLocale(resourceName);
		List<Message> messages = messageSource.getMessages(locale);
		
		logger.debug("messages={}", messages);

		return messages;
	}
	
	
	public String getMessage(String code,  Object[] argument, String defaultMessage){
				
		return messageSource.getMessage(
				code, 
				argument, 
				defaultMessage, 
				LocaleContextHolder.getLocale());
	}
	
	public String getMessage(String code, String defaultMessage){
		return getMessage(code, null, defaultMessage);
	}
	

	protected Locale getLocale(String resourceName) {
		Assert.notNull(resourceName);
		logger.debug("resourceName={}", resourceName);
		String szLocale = resourceName.substring(MESSAGE_PREFIX.length(),
				resourceName.length());
		String language = szLocale.substring(0,2);
		String country = szLocale.substring(3,5);
		Locale locale = new Locale(language, country);
		logger.debug("locale={}", locale);
		return locale;
	}

}
