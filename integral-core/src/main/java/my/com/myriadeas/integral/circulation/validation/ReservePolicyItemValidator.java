package my.com.myriadeas.integral.circulation.validation;

import my.com.myriadeas.integral.circulation.exception.InvalidItemStatusException;
import my.com.myriadeas.integral.data.jpa.domain.Item;

public interface ReservePolicyItemValidator extends ReservePolicyValidator{

	public void validate(Item item) throws InvalidItemStatusException;

}