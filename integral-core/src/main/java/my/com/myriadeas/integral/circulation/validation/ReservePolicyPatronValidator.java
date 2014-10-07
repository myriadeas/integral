package my.com.myriadeas.integral.circulation.validation;

import my.com.myriadeas.integral.circulation.exception.PatronEligibilityExceededMaximumReservationAllowedException;
import my.com.myriadeas.integral.circulation.exception.PatronEligibilityNotAllowReserveException;
import my.com.myriadeas.integral.core.PatronNotFoundException;
import my.com.myriadeas.integral.data.jpa.domain.Patron;
import my.com.myriadeas.integral.foundation.exception.ExpiredMembershipException;
import my.com.myriadeas.integral.foundation.exception.FutureMembershipException;

public interface ReservePolicyPatronValidator extends ReservePolicyValidator {

	public void validate(Patron patron) throws PatronNotFoundException,
			FutureMembershipException, ExpiredMembershipException,
			PatronEligibilityNotAllowReserveException,
			PatronEligibilityExceededMaximumReservationAllowedException;

}