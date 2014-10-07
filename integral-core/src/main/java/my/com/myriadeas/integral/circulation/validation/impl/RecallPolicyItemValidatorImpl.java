package my.com.myriadeas.integral.circulation.validation.impl;

import my.com.myriadeas.integral.circulation.exception.InvalidItemStatusException;
import my.com.myriadeas.integral.circulation.request.RecallRequest;
import my.com.myriadeas.integral.circulation.validation.RecallPolicyItemValidator;
import my.com.myriadeas.integral.data.jpa.domain.Item;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service(value = "recallItemValidator")
public class RecallPolicyItemValidatorImpl extends
		AbstractCirculationItemValidator implements RecallPolicyItemValidator {

	private static Logger logger = LoggerFactory
			.getLogger(RecallPolicyItemValidatorImpl.class);

	@Override
	public void validate(RecallRequest recallRequest) {
		logger.debug("Entering validate(recallRequest={})", recallRequest);
		
		Item item = itemRepository.findByItemIdentifier(recallRequest
				.getItemIdentifier());
		validate(item);
		
		logger.debug("Leaving validate(). ");
	}

	@Override
	protected void validateItemStatus(Item item) {
		logger.debug("Entering validateIsAccessionStatusValid(item={})", item);
	
		if (!(item.isCirculated() || item.isRenew())) {
			logger.info("Item status '{}' is invalid for recall.", item
					.getItemStatus().getCode());
			throw new InvalidItemStatusException(
					"Recall is not allowed for item with status '" + item.getItemStatus().getCode() + "'.",
					item.getItemStatus().getCode());
		}
		
		logger.debug("Leaving validateIsAccessionStatusValid(). ");
	}

}
