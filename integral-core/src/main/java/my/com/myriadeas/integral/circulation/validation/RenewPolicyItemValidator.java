package my.com.myriadeas.integral.circulation.validation;

import my.com.myriadeas.integral.data.jpa.domain.Item;

public interface RenewPolicyItemValidator extends RenewPolicyValidator {
	
	public void validate(Item item);

}
