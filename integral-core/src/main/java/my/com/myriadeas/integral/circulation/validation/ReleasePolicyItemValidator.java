package my.com.myriadeas.integral.circulation.validation;

import my.com.myriadeas.integral.data.jpa.domain.Item;


public interface ReleasePolicyItemValidator extends ReleasePolicyValidator {
	
	public void validate(Item item);

}
