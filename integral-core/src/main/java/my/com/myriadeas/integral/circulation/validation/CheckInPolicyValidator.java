package my.com.myriadeas.integral.circulation.validation;

import my.com.myriadeas.integral.circulation.exception.AvailableItemStatusForCheckOutException;
import my.com.myriadeas.integral.circulation.exception.ItemStatusNotAllowedForCheckInException;
import my.com.myriadeas.integral.circulation.request.CheckInRequest;
import my.com.myriadeas.integral.core.ItemNotFoundException;

public interface CheckInPolicyValidator {

	void validate(CheckInRequest checkInRequest) throws ItemNotFoundException,
			AvailableItemStatusForCheckOutException,
			ItemStatusNotAllowedForCheckInException,
			ItemStatusNotAllowedForCheckInException;

}
