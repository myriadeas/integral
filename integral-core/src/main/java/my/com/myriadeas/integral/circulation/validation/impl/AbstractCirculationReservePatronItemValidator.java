package my.com.myriadeas.integral.circulation.validation.impl;

import my.com.myriadeas.integral.circulation.exception.PatronBranchNotMatchedItemLocationException;
import my.com.myriadeas.integral.circulation.exception.PatronItemEligibilityNotAllowReserveException;
import my.com.myriadeas.integral.data.jpa.domain.Branch;
import my.com.myriadeas.integral.data.jpa.domain.Item;
import my.com.myriadeas.integral.data.jpa.domain.Patron;
import my.com.myriadeas.integral.data.jpa.domain.PatronItemEligibility;
import my.com.myriadeas.integral.eligibility.exception.EligibilityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractCirculationReservePatronItemValidator extends
		AbstractCirculationPatronItemValidator {

	private static Logger logger = LoggerFactory
			.getLogger(AbstractCirculationReservePatronItemValidator.class);

	@Override
	public void validateCustomPolicyPatronItemValidator(Patron patron,
			Item item) {
		logger.debug(
				"Entering validateCustomPolicyPatronItemValidator(patron={}, item={})",
				new Object[] { patron, item });

		validatePatronItemAllowedReserve(patron, item);
		validatePatronBranchMatchedItemLocation(patron, item);
		validateReserveCustomPolicyPatronItemValidator(patron, item);

		logger.debug("validateCustomPolicyPatronItemValidator(). ");
	}

	protected abstract void validateReserveCustomPolicyPatronItemValidator(Patron patron,
			Item item);

	private void validatePatronItemAllowedReserve(Patron patron, Item item)
			throws PatronItemEligibilityNotAllowReserveException,
			EligibilityNotFoundException {
		logger.debug(
				"Entering validatePatronItemAllowedReserve(patron={}, item={})",
				new Object[] { patron, item });

		PatronItemEligibility patronItemEligibility = this.patronItemEligibilityLookup
				.lookup(patron, item).getDomain();
		logger.debug("patronItemEligibility={}", patronItemEligibility);

		validatePatronItemAllowedReserve(patronItemEligibility
				.isAllowReserve());

		logger.debug("Leaving validatePatronItemAllowedReserve().");
	}

	protected void validatePatronItemAllowedReserve(boolean isAllowedReserve)
			throws PatronItemEligibilityNotAllowReserveException {
		logger.debug(
				"Entering validatePatronItemAllowedReserve(isAllowedReserve={})",
				isAllowedReserve);

		if (!isAllowedReserve) {
			throw new PatronItemEligibilityNotAllowReserveException(
					"Patron item eligibility not allowed reserve.");
		}

		logger.debug("Leaving validatePatronItemAllowedReserve(). ");
	}

	private void validatePatronBranchMatchedItemLocation(Patron patron, Item item)
			throws PatronBranchNotMatchedItemLocationException {
		logger.debug(
				"Entering validatePatronBranchMatchItemLocation(patron={}, item={})",
				new Object[] { patron, item });

		if (circulationPolicyService.getSatelliteCirc()
				&& circulationPolicyService.getRsvByBranch()) {

			validatePatronBranchMatchedItemLocation(patron.getBranch(),
					item.getBranch());
		}

		logger.debug("Leaving validatePatronBranchMatchItemLocation(). ");
	}

	private void validatePatronBranchMatchedItemLocation(Branch patronBranch,
			Branch itemBranch)
			throws PatronBranchNotMatchedItemLocationException {
		logger.debug(
				"Entering validatePatronBranchMatchItemLocation(patronBranch={}, itemBranch={})",
				new Object[] { patronBranch, itemBranch });

		validatePatronBranchMatchedItemLocation(patronBranch.getCode(),
				itemBranch.getCode());

		logger.debug("Leaving validatePatronBranchMatchItemLocation(). ");
	}

	protected void validatePatronBranchMatchedItemLocation(
			String patronBranchCode, String itemBranchCode)
			throws PatronBranchNotMatchedItemLocationException {
		logger.debug(
				"Entering validatePatronBranchMatchItemLocation(patronBranchCode={}, itemBranchCode={})",
				new Object[] { patronBranchCode, itemBranchCode });

		if (!patronBranchCode.equals(itemBranchCode)) {
			throw new PatronBranchNotMatchedItemLocationException(
					"Patron branch not match with item location.",
					patronBranchCode, itemBranchCode);
		}

		logger.debug("Leaving validatePatronBranchMatchItemLocation(). ");
	}

}
