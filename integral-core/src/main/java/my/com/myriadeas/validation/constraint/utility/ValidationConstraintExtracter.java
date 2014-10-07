package my.com.myriadeas.validation.constraint.utility;

import java.io.IOException;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class ValidationConstraintExtracter {

	ValidatorFactory validatorFactory = Validation
			.buildDefaultValidatorFactory();

	ObjectMapper mapper = new ObjectMapper();

	public String extract(String packageName, String domainName)
			throws ClassNotFoundException, JsonGenerationException,
			JsonMappingException, IOException {

		Class<?> domain = Class.forName(packageName + "." + domainName);
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		return mapper.writeValueAsString(validatorFactory.getValidator()
				.getConstraintsForClass(domain).getConstrainedProperties());
	}

	public String extract(String packageName, String domainName, String property)
			throws ClassNotFoundException, JsonGenerationException,
			JsonMappingException, IOException {

		Class<?> domain = Class.forName(packageName + "." + domainName);

		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		return mapper.writeValueAsString(validatorFactory.getValidator()
				.getConstraintsForClass(domain)
				.getConstraintsForProperty(property));
	}
}
