package my.com.myriadeas.integral.beanvalidation;

import java.util.Set;

import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.ValidatorFactory;
import javax.validation.metadata.PropertyDescriptor;

import my.com.myriadeas.integral.beanvalidation.exception.ConstrainedPropertiesNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("integralBeanValidatorFactory")
public class IntegralBeanValidatorFactory {

	private static final Logger logger = LoggerFactory.getLogger(IntegralBeanValidatorFactory.class);

	@Value("${beanvalidation.beanPackage}")
	private String beanPackage;

	// TODO should autowired from bean
	private ValidatorFactory validatorFactory = Validation
			.buildDefaultValidatorFactory();

	private Class<?> getDomainClass(String domainName)
			throws ClassNotFoundException {
		String fullDomainName = this.beanPackage + "." + domainName;
		return Class.forName(fullDomainName);
	}

	public Set<PropertyDescriptor> getDomainConstrainedProperties(
			String domainName) throws ConstrainedPropertiesNotFoundException {
		Class<?> domain = null;
		String fullDomainName = this.beanPackage + "." + domainName;
		logger.info("Full Domain Name={}", fullDomainName);
		try {
			domain = getDomainClass(domainName);
			logger.debug("Domain class={}", domain);
			return getDomainConstrainedProperties(domain);
		} catch (ClassNotFoundException e) {
			logger.error("Class not found for {}", fullDomainName, e);
			throw new ConstrainedPropertiesNotFoundException(
					"Class not found for " + fullDomainName, e);
		}
	}

	public Set<PropertyDescriptor> getDomainConstrainedProperties(
			Class<?> domain) throws ConstrainedPropertiesNotFoundException {
		try {
			Set<PropertyDescriptor> constrainedProperties = validatorFactory
					.getValidator().getConstraintsForClass(domain)
					.getConstrainedProperties();
			logger.debug("constrained properties={}", constrainedProperties);
			return constrainedProperties;
		} catch (IllegalArgumentException e) {
			throw new ConstrainedPropertiesNotFoundException(
					"Class not found for " + domain.getName(), e);
		} catch (ValidationException e) {
			throw new ConstrainedPropertiesNotFoundException(
					"Class not found for " + domain.getName(), e);
		}
	}

	public String getBeanPackage() {
		return beanPackage;
	}

	public void setBeanPackage(String beanPackage) {
		this.beanPackage = beanPackage;
	}

	public ValidatorFactory getValidatorFactory() {
		return validatorFactory;
	}

	public void setValidatorFactory(ValidatorFactory validatorFactory) {
		this.validatorFactory = validatorFactory;
	}

}
