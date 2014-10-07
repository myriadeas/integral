package my.com.myriadeas.integral.circulation.validation.impl;

import my.com.myriadeas.integral.circulation.service.CirculationPolicyService;
import my.com.myriadeas.integral.circulation.service.DueDateCalculationService;
import my.com.myriadeas.integral.data.jpa.domain.Item;
import my.com.myriadeas.integral.data.jpa.domain.Patron;
import my.com.myriadeas.integral.data.jpa.repositories.PatronRepository;
import my.com.myriadeas.integral.data.jpa.repositories.impl.ItemRepositoryImpl;
import my.com.myriadeas.integral.eligibility.PatronEligibilityLookup;
import my.com.myriadeas.integral.eligibility.PatronItemEligibilityLookup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

public abstract class AbstractCirculationPatronItemValidator {

	private static Logger logger = LoggerFactory
			.getLogger(AbstractCirculationPatronItemValidator.class);

	@Autowired
	protected PatronItemEligibilityLookup patronItemEligibilityLookup;

	@Autowired
	protected DueDateCalculationService dueDateCalculationService;

	@Autowired
	protected CirculationPolicyService circulationPolicyService;

	@Autowired
	protected ItemRepositoryImpl itemRepository;

	@Autowired
	protected PatronRepository patronRepository;

	@Autowired
	protected PatronEligibilityLookup patronEligibilityLookup;

	public void validate(Patron patron, Item item) {
		logger.debug("Entering validate(patron={}, item={})", new Object[] {
				patron, item });

		Assert.notNull(patron);
		Assert.notNull(item);
		validateCustomPolicyPatronItemValidator(patron, item);

		logger.debug("Leaving validate(). ");
	}

	protected abstract void validateCustomPolicyPatronItemValidator(Patron patron,
			Item item);

}
