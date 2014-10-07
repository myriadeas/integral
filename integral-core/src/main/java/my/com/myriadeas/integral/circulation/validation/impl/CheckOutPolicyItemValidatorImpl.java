package my.com.myriadeas.integral.circulation.validation.impl;

import my.com.myriadeas.integral.circulation.exception.AvailableItemStatusForCheckOutException;
import my.com.myriadeas.integral.circulation.exception.InvalidItemStatusException;
import my.com.myriadeas.integral.circulation.exception.ItemStatusNotAllowedForCheckInException;
import my.com.myriadeas.integral.circulation.request.CheckOutRequest;
import my.com.myriadeas.integral.circulation.validation.CheckOutPolicyItemValidator;
import my.com.myriadeas.integral.core.ItemNotFoundException;
import my.com.myriadeas.integral.data.jpa.domain.Item;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service(value = "checkOutItemValidator")
public class CheckOutPolicyItemValidatorImpl extends
		AbstractCirculationItemValidator implements CheckOutPolicyItemValidator {

	private static Logger logger = LoggerFactory
			.getLogger(CheckOutPolicyItemValidatorImpl.class);

	@Override
	public void validate(CheckOutRequest checkOutRequest)
			throws ItemStatusNotAllowedForCheckInException,
			ItemNotFoundException, AvailableItemStatusForCheckOutException,
			InvalidItemStatusException {
		logger.debug("Entering validate(checkOutRequest={})", checkOutRequest);

		Item item = itemRepository.findByItemIdentifier(checkOutRequest
				.getItemIdentifier());
		validate(item);

		logger.debug("Leaving validate(). ");

	}

	@Override
	protected void validateItemStatus(Item item)
			throws AvailableItemStatusForCheckOutException,
			ItemStatusNotAllowedForCheckInException {
		logger.debug("Entering validateItemStatus(item={})", item);

		if (!(item.isAvailable() || item.isHold())) {
			throw new ItemStatusNotAllowedForCheckInException(
					"Checkout is not allowed because invalid item status.",
					item.getItemStatus().getCode());
		}

		logger.debug("Leaving validateItemStatus().");
	}

}
