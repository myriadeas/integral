package my.com.myriadeas.integral.circulation.validation.impl;

import my.com.myriadeas.integral.circulation.exception.InvalidItemStatusException;
import my.com.myriadeas.integral.circulation.exception.ItemStatusNotAllowedForCheckInException;
import my.com.myriadeas.integral.circulation.request.ReserveRequest;
import my.com.myriadeas.integral.circulation.validation.ReservePolicyItemValidator;
import my.com.myriadeas.integral.data.jpa.domain.Item;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service(value = "reserveItemValidator")
public class ReservePolicyItemValidatorImpl extends
		AbstractCirculationItemValidator implements ReservePolicyItemValidator {

	private static Logger logger = LoggerFactory
			.getLogger(ReservePolicyItemValidatorImpl.class);

	@Override
	public void validate(ReserveRequest reserveRequest) {
		logger.debug("Entering validate(reserveRequest={})", reserveRequest);

		Item item = itemRepository.findByItemIdentifier(reserveRequest
				.getItemIdentifier());
		validate(item);

		logger.debug("Leaving validate(). ");
	}

	@Override
	protected void validateItemStatus(Item item)
			throws ItemStatusNotAllowedForCheckInException {
		logger.debug("Entering validateIsAccessionStatusValid(item={})", item);

		// Item status allowed is Circulated, Recall and Hold
		if (!(item.isCirculated() || item.isRenew() || item.isRecalled() || item
				.isHold())) {
			logger.info("Item status '{}' is invalid for reservation.", item
					.getItemStatus().getCode());
			throw new InvalidItemStatusException(
					"Reservation is not allowed for item with status '"
							+ item.getItemStatus().getCode() + "'.", item
							.getItemStatus().getCode());
		}

		logger.debug("Leaving validateIsAccessionStatusValid(). ");
	}

}
