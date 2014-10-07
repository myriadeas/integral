package my.com.myriadeas.integral.circulation.validation;

import my.com.myriadeas.integral.circulation.exception.AvailableItemStatusForCheckOutException;
import my.com.myriadeas.integral.circulation.exception.InvalidItemStatusException;
import my.com.myriadeas.integral.circulation.exception.ItemStatusNotAllowedForCheckInException;
import my.com.myriadeas.integral.circulation.exception.PatronEligibilityExceededMaximumLoanException;
import my.com.myriadeas.integral.circulation.request.CheckOutRequest;
import my.com.myriadeas.integral.core.ItemNotFoundException;
import my.com.myriadeas.integral.core.PatronNotFoundException;
import my.com.myriadeas.integral.foundation.exception.ExpiredMembershipException;
import my.com.myriadeas.integral.foundation.exception.FutureMembershipException;

public interface CheckOutPolicyValidator {

	public void validate(CheckOutRequest checkOutRequest)
			throws ItemStatusNotAllowedForCheckInException, ItemNotFoundException,
			AvailableItemStatusForCheckOutException,
			InvalidItemStatusException, PatronNotFoundException,
			FutureMembershipException, ExpiredMembershipException,
			PatronEligibilityExceededMaximumLoanException;
}
