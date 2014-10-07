package my.com.myriadeas.integral.circulation.validation;

import my.com.myriadeas.integral.circulation.exception.ItemStatusNotAllowedForReleaseException;
import my.com.myriadeas.integral.circulation.request.ReleaseRequest;
import my.com.myriadeas.integral.core.ItemNotFoundException;

public interface ReleasePolicyValidator {

	void validate(ReleaseRequest releaseRequest) throws ItemNotFoundException,
			ItemStatusNotAllowedForReleaseException;

}
