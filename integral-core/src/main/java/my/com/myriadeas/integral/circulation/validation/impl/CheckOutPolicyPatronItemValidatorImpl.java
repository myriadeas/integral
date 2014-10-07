package my.com.myriadeas.integral.circulation.validation.impl;

import java.util.ArrayList;
import java.util.List;

import my.com.myriadeas.integral.circulation.exception.AvailableItemStatusForCheckOutException;
import my.com.myriadeas.integral.circulation.exception.HoldItemStatusNotAllowedForCheckOutException;
import my.com.myriadeas.integral.circulation.exception.InvalidItemStatusException;
import my.com.myriadeas.integral.circulation.exception.ItemStatusNotAllowedForCheckInException;
import my.com.myriadeas.integral.circulation.exception.PatronEligibilityExceededMaximumLoanException;
import my.com.myriadeas.integral.circulation.exception.PatronItemEligibilityExceededMaximumLoanException;
import my.com.myriadeas.integral.circulation.request.CheckOutRequest;
import my.com.myriadeas.integral.circulation.validation.CheckOutPolicyPatronItemValidator;
import my.com.myriadeas.integral.core.ItemNotFoundException;
import my.com.myriadeas.integral.core.PatronNotFoundException;
import my.com.myriadeas.integral.data.jpa.domain.Item;
import my.com.myriadeas.integral.data.jpa.domain.ItemCategory;
import my.com.myriadeas.integral.data.jpa.domain.Location;
import my.com.myriadeas.integral.data.jpa.domain.Patron;
import my.com.myriadeas.integral.data.jpa.domain.PatronItemEligibility;
import my.com.myriadeas.integral.data.jpa.domain.ReservationTransaction;
import my.com.myriadeas.integral.data.jpa.domain.SMD;
import my.com.myriadeas.integral.eligibility.Eligibility;
import my.com.myriadeas.integral.eligibility.exception.EligibilityNotFoundException;
import my.com.myriadeas.integral.foundation.exception.ExpiredMembershipException;
import my.com.myriadeas.integral.foundation.exception.FutureMembershipException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service(value = "checkOutPatronItemValidator")
public class CheckOutPolicyPatronItemValidatorImpl extends
		AbstractCirculationPatronItemValidator implements
		CheckOutPolicyPatronItemValidator {

	private static Logger logger = LoggerFactory
			.getLogger(CheckOutPolicyPatronItemValidatorImpl.class);

	@Override
	public void validate(CheckOutRequest checkOutRequest)
			throws ItemStatusNotAllowedForCheckInException,
			ItemNotFoundException,
			AvailableItemStatusForCheckOutException,
			InvalidItemStatusException, PatronNotFoundException,
			FutureMembershipException, ExpiredMembershipException,
			PatronEligibilityExceededMaximumLoanException {
		logger.debug("Entering validate(checkOutRequest={})", checkOutRequest);

		Item item = itemRepository.findByItemIdentifier(checkOutRequest
				.getItemIdentifier());
		Patron patron = patronRepository.findByUsername(checkOutRequest
				.getPatronIdentifier());
		validate(patron, item);

		logger.debug("Leaving validate(). ");

	}

	@Override
	public void validateCustomPolicyPatronItemValidator(Patron patron,
			Item item) {
		logger.debug(
				"Entering validateCustomPolicyPatronItemValidator(patron={}, item={})",
				new Object[] { patron, item });

		validateAccessionWithOnHoldStatus(patron, item);
		validateExceededMaxLoanInPatronItemEligibility(patron, item);

		logger.debug("Leaving validateCustomPolicyPatronItemValidator(). ");

	}

	private void validateAccessionWithOnHoldStatus(Patron patron, Item item)
			throws InvalidItemStatusException {

		logger.debug(
				"Entering validateAccessionWithOnHoldStatus(patron={}, item={})",
				new Object[] { patron, item });

		if (item.isHold()) {
			ReservationTransaction reservationTransaction = item
					.findFirstQualifyReserver();
			Assert.notNull(reservationTransaction);
			validateAccessionWithOnHoldStatus(patron, reservationTransaction);
		}

		logger.debug("Leaving validateAccessionWithOnHoldStatus(). ");
	}

	private void validateAccessionWithOnHoldStatus(Patron patron, ReservationTransaction reservationTransaction)
			throws InvalidItemStatusException {
		logger.debug(
				"Entering validateAccessionWithOnHoldStatus(patron={}, reservationTransaction={})",
				new Object[] { patron, reservationTransaction });

		if (!patron.getUsername().equals(reservationTransaction.getPatron().getUsername())) {
			throw new HoldItemStatusNotAllowedForCheckOutException(
					"Checkout is not allowed because accession status is reserved.",
					reservationTransaction.getPatron().getFirstname(), reservationTransaction.getPatron()
							.getUsername(), reservationTransaction.getBranch()
							.getDescription());
		}

		logger.debug("Leaving validateAccessionWithOnHoldStatus(). ");
	}

	private void validateExceededMaxLoanInPatronItemEligibility(Patron patron,
			Item item) throws EligibilityNotFoundException,
			PatronItemEligibilityExceededMaximumLoanException {

		logger.debug(
				"Entering validateExceededMaxLoanInPatronItemEligibility(patron={}, item={})",
				new Object[]{patron, item});

		Eligibility<PatronItemEligibility> eligibility = this.patronItemEligibilityLookup
				.lookup(patron, item);
		PatronItemEligibility patronItemEligibility = eligibility.getDomain();
		logger.debug("patronItemEligibility={}", patronItemEligibility);

		List<ItemCategory> itemCategoryList = new ArrayList<ItemCategory>();
		if (eligibility.isItemCategory())
			logger.info("Eligibility is item category.");
		itemCategoryList.add(item.getItemCategory());

		List<SMD> smdList = new ArrayList<SMD>();
		if (eligibility.isSmd())
			logger.info("Eligibility is smd.");
		smdList.add(item.getSmd());

		List<Location> locationList = new ArrayList<Location>();
		if (eligibility.isBranch())
			logger.info("Eligibility is branch.");
		locationList.add(item.getLocation());

		/*- TODO -
		 *  On loan item should check based on eligibility critieria
		 */
		List<Item> patronItemOnLoanItem = patron.getOnLoanItemsFilterByItemCategoriesAndSmdsAndLocations(
				itemCategoryList, smdList, locationList);
		int patronItemOnLoanItemCount = patronItemOnLoanItem.size();

		validateExceededMaxLoanInPatronItemEligibility(
				patronItemEligibility.getMaxLoanAllowed(), patronItemOnLoanItemCount);

		logger.debug("Leaving validateExceededMaxLoanInPatronItemEligibility(). ");
	}

	protected void validateExceededMaxLoanInPatronItemEligibility(
			int maxLoanAllowed, int patronItemOnLoanItemCount)
			throws PatronItemEligibilityExceededMaximumLoanException {
		logger.debug(
				"Entering validateExceededMaxLoanInPatronItemEligibility(maxLoanAllowed={}, patronItemOnLoanItemCount={})",
				new Object[]{maxLoanAllowed, patronItemOnLoanItemCount});

		if (patronItemOnLoanItemCount >= maxLoanAllowed) {
			throw new PatronItemEligibilityExceededMaximumLoanException(
					"Maximum loan for patron item eligibility already exceeded.",
					patronItemOnLoanItemCount, maxLoanAllowed);
		}

		logger.debug("Leaving validateExceededMaxLoanInPatronItemEligibility(). ");
	}
}
