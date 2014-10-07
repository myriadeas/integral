package my.com.myriadeas.integral.circulation.validation.impl;

import my.com.myriadeas.integral.circulation.exception.AvailableItemStatusForCheckOutException;
import my.com.myriadeas.integral.circulation.exception.ItemStatusNotAllowedForCheckInException;
import my.com.myriadeas.integral.data.jpa.domain.Item;
import my.com.myriadeas.integral.data.jpa.repositories.impl.ItemRepositoryImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

public abstract class AbstractCirculationItemValidator {

	private static Logger logger = LoggerFactory
			.getLogger(AbstractCirculationItemValidator.class);

	@Autowired
	protected ItemRepositoryImpl itemRepository;

	public void validate(Item item)
			throws ItemStatusNotAllowedForCheckInException,
			AvailableItemStatusForCheckOutException {
		logger.debug("Entering validate(item={})", item);

		Assert.notNull(item);
		validateItemStatus(item);

		logger.debug("Leaving validate(). ");
	}

	protected abstract void validateItemStatus(Item item)
			throws AvailableItemStatusForCheckOutException,
			ItemStatusNotAllowedForCheckInException;

}
