package my.com.myriadeas.integral.circulation.validation;

import my.com.myriadeas.integral.data.jpa.domain.Item;

public interface CheckOutPolicyItemValidator extends CheckOutPolicyValidator {

	public void validate(Item item);
}
