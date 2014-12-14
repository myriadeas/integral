package my.com.myriadeas.integral.circulation2.application;

import my.com.myriadeas.integral.circulation2.domain.model.Holding;
import my.com.myriadeas.integral.circulation2.domain.model.HoldingGroup;
import my.com.myriadeas.integral.circulation2.domain.model.HoldingRepository;
import my.com.myriadeas.integral.circulation2.domain.model.ItemCategory;
import my.com.myriadeas.integral.circulation2.domain.model.ItemCategoryRepository;
import my.com.myriadeas.integral.circulation2.domain.service.HoldingGroupService;
import my.com.myriadeas.integral.core.publisher.Publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("holdingServiceImpl")
public class HoldingServiceImpl implements HoldingService {

	private static final Logger logger = LoggerFactory
			.getLogger(HoldingServiceImpl.class);

	private HoldingGroupService holdingGroupService;

	private ItemCategoryRepository itemCategoryRepository;

	private HoldingRepository holdingRepository;

	private Publisher publisher;

	@Transactional
	public Long newHolding(NewHoldingCommand command) {
		logger.debug("Entering newHolding(command={})", command);
		ItemCategory itemCategory = itemCategoryRepository.findByCode(command
				.getItemCategoryCode());
		HoldingGroup holdingGroup = this.holdingGroupService
				.findOrCreate(itemCategory);
		Holding holding = new Holding(command.getItemIdentifier(), holdingGroup);
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

	@Autowired
	public void setHoldingGroupService(HoldingGroupService holdingGroupService) {
		this.holdingGroupService = holdingGroupService;
	}

	@Autowired
	public void setItemCategoryRepository(
			ItemCategoryRepository itemCategoryRepository) {
		this.itemCategoryRepository = itemCategoryRepository;
	}

	@Autowired
	public void setHoldingRepository(HoldingRepository holdingRepository) {
		this.holdingRepository = holdingRepository;
	}

	@Autowired
	@Qualifier("circulationPublisher")
	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

}
