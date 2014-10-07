package my.com.myriadeas.dao.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.domain.AbstractAuditable;
import org.springframework.data.rest.core.UriToEntityConverter;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.data.rest.webmvc.support.ValidationExceptionHandler;
import org.springframework.util.ClassUtils;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class IntegralRepositoryRestMvcConfiguration extends
		RepositoryRestMvcConfiguration {
	private static final boolean IS_JAVAX_VALIDATION_AVAILABLE = ClassUtils
			.isPresent("javax.validation.ConstraintViolationException",
					RepositoryRestMvcConfiguration.class.getClassLoader());

	/**
	 * Main configuration for the REST exporter.
	 */
	@Bean
	public RepositoryRestConfiguration config() {

		IntegralRepositoryRestConfiguration config = new IntegralRepositoryRestConfiguration();
		config.setDefaultPageSize(10);
		config.setReturnBodyOnCreate(true);
		config.setReturnBodyOnUpdate(true);
		config.setExposeIdsForAll(true);
		configureRepositoryRestConfiguration(config);
		return config;
	}

	@Bean
	@Lazy
	public ValidationExceptionHandler validationExceptionHandler() {
		if (IS_JAVAX_VALIDATION_AVAILABLE) {
			return new CustomValidationExceptionHandler();
		} else {
			return null;
		}
	}

	@Bean
	public Validator validator() {
		return new LocalValidatorFactoryBean();
	}

	@Override
	protected void configureValidatingRepositoryEventListener(
			ValidatingRepositoryEventListener listener) {
		listener.addValidator("afterCreate", validator());
		listener.addValidator("beforeCreate", validator());
		listener.addValidator("beforeSave", validator());
		listener.addValidator("afterSave", validator());
	}

	@Override
	protected void configureJacksonObjectMapper(ObjectMapper objectMapper) {
		objectMapper.addMixInAnnotations(AbstractAuditable.class,
				AbstractAuditableJsonProperties.class);
		objectMapper.disable(MapperFeature.USE_GETTERS_AS_SETTERS);
		
	}

	@Bean
	public UriToEntityConverter uriToEntityConverter() {
		return new UriToEntityConverter(repositories(), domainClassConverter());
	}
	
	
}
