package my.com.myriadeas.integral.circulation.validation.impl;

import my.com.myriadeas.integral.circulation.exception.ItemStatusNotAllowedForReleaseException;
import my.com.myriadeas.integral.circulation.request.ReleaseRequest;
import my.com.myriadeas.integral.circulation.validation.ReleasePolicyItemValidator;
import my.com.myriadeas.integral.core.ItemNotFoundException;
import my.com.myriadeas.integral.data.jpa.domain.Item;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service(value = "releaseItemValidator")
public class ReleasePolicyItemValidatorImpl extends
		AbstractCirculationItemValidator implements ReleasePolicyItemValidator {

	private static Logger logger = LoggerFactory
			.getLogger(ReleasePolicyItemValidatorImpl.class);

	public void validate(ReleaseRequest releaseRequest)
			throws ItemNotFoundException, ItemStatusNotAllowedForReleaseException {
		logger.debug("Entering validate(releaseRequest={})", releaseRequest);

		Item item = itemRepository.findByItemIdentifier(releaseRequest
				.getItemIdentifier());
		if (item == null){
			throw new ItemNotFoundException("Item not found.");
		}
		logger.info("Item={}", item);
		validate(item);

		logger.debug("Leaving validate(). ");
	}

	@Override
	protected void validateItemStatus(Item item)
			throws ItemStatusNotAllowedForReleaseException {
		logger.debug("Entering validateItemStatus(item={})", item);
		logger.info("Item identifier={}", item.getItemIdentifier());
		logger.info("Item status={}", item.getItemStatus().getCode());

		if (!item.isFinalProcessing()) {			
			logger.info("Item status '{}' is invalid for release.", item
					.getItemStatus().getCode());
			throw new ItemStatusNotAllowedForReleaseException(
					"Release is not allowed for item with status '"
							+ item.getItemStatus().getCode() + "'.", item
							.getItemStatus().getCode());

		}

		logger.debug("Leaving validateItemStatus(). ");
	}

}
