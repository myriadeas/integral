package my.com.myriadeas.integral.circulation2.application;

import java.util.Map;

import my.com.myriadeas.integral.circulation2.domain.model.Holding;
import my.com.myriadeas.integral.circulation2.domain.model.HoldingRepository;
import my.com.myriadeas.integral.circulation2.domain.model.ItemCategory;
import my.com.myriadeas.integral.circulation2.domain.model.ItemCategoryRepository;
import my.com.myriadeas.integral.core.domain.model.DomainEvent;
import my.com.myriadeas.integral.core.publisher.Publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service("holdingServiceImpl")
public class HoldingServiceImpl implements HoldingService {

	private static final Logger logger = LoggerFactory
			.getLogger(HoldingServiceImpl.class);

	private HoldingRepository holdingRepository;

	private Publisher publisher;

	private ItemCategoryRepository itemCategoryRepository;

	@Transactional
	public Long newHolding(NewHoldingCommand command) {
		logger.debug("Entering newHolding(command={})", command);
		Holding holding = new Holding(command.getItemIdentifier());
		try {
			holdingRepository.save(holding);
		} catch (DataIntegrityViolationException dive) {
			throw new DuplicatedNewHoldingException("Duplicated New Holding",
					dive);
		}
		publisher.publish(holding.getNewHoldingCreatedEvent());
		logger.debug("Leaving newHolding()");
		return holding.getId();
	}

	@Transactional
	public Long release(ReleaseHoldingCommand command) {
		logger.debug("Entering release(command={})", command);
		Holding holding = this.holdingRepository.findByItemIdentifier(command
				.getItemIdentifier());
		Assert.notNull(holding);
		ItemCategory itemCategory = this.itemCategoryRepository
				.findByCode(command.getItemCategoryCode());
		Map<String, DomainEvent> events = holding.release(itemCategory);
		this.holdingRepository.save(holding);
		this.publisher.publish(events);
		logger.debug("Leaving release()");
		return holding.getId();
	}

	@Autowired
	public void setHoldingRepository(HoldingRepository holdingRepository) {
		this.holdingRepository = holdingRepository;
	}

	@Autowired
	public void setItemCategoryRepository(
			ItemCategoryRepository itemCategoryRepository) {
		this.itemCategoryRepository = itemCategoryRepository;
	}

	@Autowired
	@Qualifier("circulationPublisher")
	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

}
