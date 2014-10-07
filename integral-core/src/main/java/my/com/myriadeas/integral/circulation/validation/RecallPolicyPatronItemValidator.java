package my.com.myriadeas.integral.circulation.validation;

import my.com.myriadeas.integral.data.jpa.domain.Item;
import my.com.myriadeas.integral.data.jpa.domain.Patron;

public interface RecallPolicyPatronItemValidator extends RecallPolicyValidator {
	
	public void validate(Patron patron, Item item);

}
