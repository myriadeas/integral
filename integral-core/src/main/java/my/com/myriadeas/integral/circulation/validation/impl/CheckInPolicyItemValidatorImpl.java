package my.com.myriadeas.integral.circulation.validation.impl;

import my.com.myriadeas.integral.circulation.exception.AvailableItemStatusForCheckOutException;
import my.com.myriadeas.integral.circulation.exception.ItemStatusNotAllowedForCheckInException;
import my.com.myriadeas.integral.circulation.request.CheckInRequest;
import my.com.myriadeas.integral.circulation.validation.CheckInPolicyItemValidator;
import my.com.myriadeas.integral.data.jpa.domain.Item;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service(value = "checkInItemValidator")
public class CheckInPolicyItemValidatorImpl extends
		AbstractCirculationItemValidator implements CheckInPolicyItemValidator {

	private static Logger logger = LoggerFactory
			.getLogger(CheckInPolicyItemValidatorImpl.class);

	public void validate(CheckInRequest checkInRequest)
			throws AvailableItemStatusForCheckOutException,
			ItemStatusNotAllowedForCheckInException,
			ItemStatusNotAllowedForCheckInException {
		logger.debug("Entering validate(checkInRequest={})", checkInRequest);

		Item item = itemRepository.findByItemIdentifier(checkInRequest
				.getItemIdentifier());
		logger.info("Item={}", item);
		validate(item);

		logger.debug("Leaving validate(). ");
	}

	@Override
	protected void validateItemStatus(Item item)
			throws AvailableItemStatusForCheckOutException,
			ItemStatusNotAllowedForCheckInException {
		logger.debug("Entering validateItemStatus(item={})", item);
		logger.info("Item identifier={}", item.getItemIdentifier());
		logger.info("Item status={}", item.getItemStatus().getCode());

		if (item.isAvailable()) {
			logger.info(
					"Item status '{}' is invalid for checkin but valid for checkout.",
					item.getItemStatus().getCode());
			throw new AvailableItemStatusForCheckOutException(
					"Item status is available.", item.getItemStatus().getCode());
		} else if (!(item.isCirculated() || item.isRenew() || item.isRecalled())) {
			logger.info("Item status '{}' is invalid for checkin.", item
					.getItemStatus().getCode());
			throw new ItemStatusNotAllowedForCheckInException(
					"Check in is not allowed for item with status '"
							+ item.getItemStatus().getCode() + "'.", item
							.getItemStatus().getCode());

		}

		logger.debug("Leaving validateItemStatus(). ");
	}

}
