package my.com.myriadeas.integral.circulation.validation;

import my.com.myriadeas.integral.data.jpa.domain.Patron;

public interface RenewPolicyPatronValidator extends RenewPolicyValidator {
	
	public void validate(Patron patron);

}
