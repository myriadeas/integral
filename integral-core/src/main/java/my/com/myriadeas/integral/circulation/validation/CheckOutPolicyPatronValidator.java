package my.com.myriadeas.integral.circulation.validation;

import my.com.myriadeas.integral.data.jpa.domain.Patron;

public interface CheckOutPolicyPatronValidator extends CheckOutPolicyValidator {

	public void validate(Patron patron);
}
