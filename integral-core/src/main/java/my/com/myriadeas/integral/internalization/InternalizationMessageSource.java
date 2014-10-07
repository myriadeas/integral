package my.com.myriadeas.integral.internalization;

import java.util.List;
import java.util.Locale;

import org.springframework.context.HierarchicalMessageSource;

public interface InternalizationMessageSource extends HierarchicalMessageSource{

	public abstract List<Message> getMessages(Locale locale);
	
}