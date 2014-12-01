package my.com.myriadeas.integral.identityaccess.domain.model;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service("registerUserValidator")
public class RegisterUserValidator implements Validator {

	public RegisterUserValidator() {
	}

	public void validate(Object target, Errors errors) {
		User user = (User) target;
		if (StringUtils.isBlank(user.getUsername()))
			errors.rejectValue(null, "registerUser.error.username.blank",
					new Object[] { user.getUsername() }, "Username is blank");
		if (StringUtils.isBlank(user.getPassword()))
			errors.rejectValue(null, "registerUser.error.username.password",
					new Object[] { user.getPassword() }, "Password is blank");
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}
}