package my.com.myriadeas.integral.circulation.validation;

import my.com.myriadeas.integral.circulation.request.ReserveRequest;

public interface ReservePolicyValidator {
 
	public void validate(ReserveRequest reserveRequest);
}
