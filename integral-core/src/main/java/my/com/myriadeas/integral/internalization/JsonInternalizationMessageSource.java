package my.com.myriadeas.integral.internalization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;

@Service("jsonInternalizationMessageSource")
public class JsonInternalizationMessageSource extends
		ReloadableResourceBundleMessageSource implements
		InternalizationMessageSource {

	private static final String MESSAGE_NAME = "messages";

    private static final String BASENAME = "WEB-INF/i18n/" + MESSAGE_NAME;
	
	private boolean cached = false;

	private Map<String, List<Message>> messagesHolder = new HashMap<String, List<Message>>();

	public JsonInternalizationMessageSource() {
		this.setBasename(BASENAME);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see my.com.myriadeas.integral.internalization.InternalizationMessageSource#
	 * getMessages(java.util.Locale)
	 */
	@Override
	public List<Message> getMessages(Locale locale) {

		String messageName = BASENAME + "_" + locale;		
		List<Message> messages = null;
		if (isCached()) {
			messages = messagesHolder.get(messageName);
		}
		if (messages == null) {
			messages = new ArrayList<Message>();
			PropertiesHolder propertiesHolder = this.getProperties(messageName);
			if (propertiesHolder.getProperties() == null) {
				propertiesHolder = this.getProperties(BASENAME);
			}
			for (Entry<Object, Object> entry : propertiesHolder.getProperties()
					.entrySet()) {
				messages.add(new Message(entry.getKey().toString(), entry
						.getValue().toString()));
			}
			messagesHolder.put(messageName, messages);
		}
		return messages;
	}
	
	

	public Boolean isCached() {
		return cached;
	}

	@Value("${internalization.jsonMessageSource.cached:true}")
	public void setCached(Boolean cached) {
		this.cached = cached;
	}

	@Override
	@Value("${internalization.jsonMessageSource.cacheSeconds}")
	public void setCacheSeconds(int cacheSeconds) {
		super.setCacheSeconds(cacheSeconds);
	}

	
	
}
