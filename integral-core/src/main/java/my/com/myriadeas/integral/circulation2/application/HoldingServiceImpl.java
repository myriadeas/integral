package my.com.myriadeas.integral.circulation2.application;

import my.com.myriadeas.integral.circulation2.domain.model.Holding;
import my.com.myriadeas.integral.circulation2.domain.model.HoldingGroup;
import my.com.myriadeas.integral.circulation2.domain.model.HoldingGroupRepository;
import my.com.myriadeas.integral.circulation2.domain.model.HoldingRepository;
import my.com.myriadeas.integral.circulation2.domain.model.ItemCategory;
import my.com.myriadeas.integral.circulation2.domain.model.ItemCategoryRepository;
import my.com.myriadeas.integral.circulation2.domain.service.HoldingGroupService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

public class HoldingServiceImpl implements HoldingService {

	private static final Logger logger = LoggerFactory
			.getLogger(HoldingServiceImpl.class);

	private HoldingGroupRepository holdingGroupRepository;

	private HoldingGroupService holdingGroupService;

	private ItemCategoryRepository itemCategoryRepository;

	private HoldingRepository holdingRepository;

	@Transactional
	public void newHolding(NewHoldingCommand command) {
		logger.debug("Entering newHolding(command={})", command);
		ItemCategory itemCategory = itemCategoryRepository.findByCode(command
				.getItemCategoryCode());
		HoldingGroup holdingGroup = this.holdingGroupService
				.findOrCreate(itemCategory);
		Holding holding = new Holding(command.getItemIdentifier(), holdingGroup);
		holdingRepository.save(holding);
		logger.debug("Leaving newHolding()");
	}
}
