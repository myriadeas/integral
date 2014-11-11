package my.com.myriadeas.integral.circulation2.domain.service;

import my.com.myriadeas.integral.circulation2.domain.model.HoldingGroup;
import my.com.myriadeas.integral.circulation2.domain.model.HoldingGroupRepository;
import my.com.myriadeas.integral.circulation2.domain.model.ItemCategory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("holdingGroupServiceImpl")
public class HoldingGroupServiceImpl implements HoldingGroupService {

	private static final Logger logger = LoggerFactory
			.getLogger(HoldingGroupServiceImpl.class);

	private HoldingGroupRepository holdingGroupRepository;

	@Override
	public HoldingGroup findOrCreate(ItemCategory itemCategory) {
		logger.debug("Entering findOrCreate(itemCategory={})", itemCategory);

		HoldingGroup holdingGroup = holdingGroupRepository
				.findByItemCategory(itemCategory);
		if (holdingGroup == null) {
			holdingGroup = new HoldingGroup(itemCategory);
			holdingGroup = holdingGroupRepository.save(holdingGroup);
		}
		logger.debug("Leaving findOrCreate(). HoldingGroup={}", holdingGroup);
		return holdingGroup;
	}

	@Autowired
	public void setHoldingGroupRepository(
			HoldingGroupRepository holdingGroupRepository) {
		this.holdingGroupRepository = holdingGroupRepository;
	}

}
