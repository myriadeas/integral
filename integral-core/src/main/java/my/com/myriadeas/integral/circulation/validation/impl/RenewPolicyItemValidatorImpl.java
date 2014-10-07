package my.com.myriadeas.integral.circulation.validation.impl;

import my.com.myriadeas.integral.circulation.exception.AvailableItemStatusForCheckOutException;
import my.com.myriadeas.integral.circulation.exception.InvalidItemStatusException;
import my.com.myriadeas.integral.circulation.exception.ItemStatusNotAllowedForCheckInException;
import my.com.myriadeas.integral.circulation.exception.RenewalOnReservedAccessionException;
import my.com.myriadeas.integral.circulation.exception.ReservationNotFoundException;
import my.com.myriadeas.integral.circulation.request.RenewRequest;
import my.com.myriadeas.integral.circulation.validation.RenewPolicyItemValidator;
import my.com.myriadeas.integral.data.jpa.domain.Item;
import my.com.myriadeas.integral.data.jpa.domain.ReservationTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service(value = "renewItemValidator")
public class RenewPolicyItemValidatorImpl extends
		AbstractCirculationItemValidator implements RenewPolicyItemValidator {

	private static Logger logger = LoggerFactory
			.getLogger(RenewPolicyItemValidatorImpl.class);

	@Override
	public void validate(RenewRequest renewRequest) {
		logger.debug("Entering validate(renewrequest={})", renewRequest);

		Item item = itemRepository.findByItemIdentifier(renewRequest
				.getItemIdentifier());
		validate(item);
		validateRenewalOnReservedAccession(item);

		logger.debug("Leaving validate(). ");

	}

	@Override
	protected void validateItemStatus(Item item)
			throws AvailableItemStatusForCheckOutException,
			ItemStatusNotAllowedForCheckInException {
		logger.debug("Entering validateItemStatus(item={})", item);

		if (!(item.isCirculated() || item.isRenew())) {
			logger.info("Item status '{}' is invalid for checkin.", item
					.getItemStatus().getCode());
			throw new InvalidItemStatusException(
					"Renewal is not allowed for item with status '"
							+ item.getItemStatus().getCode() + "'.", item
							.getItemStatus().getCode());
		}

		logger.debug("Leaving validateItemStatus(). ");
	}

	private void validateRenewalOnReservedAccession(Item item) {
		logger.debug("Entering validateRenewalOnReservedAccession(item={})",
				item);

		boolean isItemReserved = item
				.isThereAnyReservation();
		try {
			ReservationTransaction reservationTransaction = item
					.findFirstQualifyReserver();
			logger.debug("reservationTransaction={}", reservationTransaction);

			if (isItemReserved) {
				logger.info("Item is reserved.");
				throw new RenewalOnReservedAccessionException(
						"Item has been reserved.", reservationTransaction
								.getPatron().getUsername());
			}
		} catch (ReservationNotFoundException e) {
			logger.error("Reservation not found.");
		}

		logger.debug("Leaving validateRenewalOnReservedAccession(). ");
	}

}
