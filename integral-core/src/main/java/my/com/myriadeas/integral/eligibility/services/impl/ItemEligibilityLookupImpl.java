package my.com.myriadeas.integral.eligibility.services.impl;

import java.util.HashMap;
import java.util.Map;

import my.com.myriadeas.integral.core.EligibilityNotFoundException;
import my.com.myriadeas.integral.data.jpa.domain.Item;
import my.com.myriadeas.integral.data.jpa.domain.ItemEligibility;
import my.com.myriadeas.integral.data.jpa.repositories.impl.ItemEligibilityRepositoryImpl;
import my.com.myriadeas.integral.eligibility.Eligibility;
import my.com.myriadeas.integral.eligibility.EligibilityRuleCommand;
import my.com.myriadeas.integral.eligibility.EligibilityTableFactory;
import my.com.myriadeas.integral.eligibility.ItemEligibilityLookup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service("itemEligibilityLookup")
public class ItemEligibilityLookupImpl extends AbstractEligibilityLookupImpl
		implements ItemEligibilityLookup {

	private static final Logger logger = LoggerFactory
			.getLogger(ItemEligibilityLookupImpl.class);

	@Autowired
	ItemEligibilityRepositoryImpl glitemeligRepo;

	public ItemEligibilityLookupImpl() {
	}

	@Autowired
	@Qualifier("itemEligibilityTableFactoryBean")
	public void setLookupTableFactoryBean(
			EligibilityTableFactory lookupTableFactoryBean) {
		super.setLookupTableFactoryBean(lookupTableFactoryBean);
	};

	@Override
	public Eligibility<ItemEligibility> lookup(Item objectIdentity)
			throws EligibilityNotFoundException {
		logger.debug("Object Identity={}", objectIdentity);
		Map<String, String> criterias = buildCriteria(objectIdentity);
		logger.debug("criterias={}", criterias);
		EligibilityRuleCommand command = lookupStrategy.lookup(criterias);
		logger.debug("command={}", command);
		ItemEligibility glitemelig = getGlitemelig(command);
		if (glitemelig == null) {
			throw new EligibilityNotFoundException(
					"Eligibility not found for criterias= " + criterias);
		}
		Eligibility<ItemEligibility> eligibility = new Eligibility<ItemEligibility>(
				command, glitemelig);
		logger.debug("Eligibility={}", eligibility);
		return eligibility;
	}

	protected ItemEligibility getGlitemelig(EligibilityRuleCommand command) {
		return glitemeligRepo.findByCode(command.getEligibility());
	}

	@Override
	public Map<String, String> buildCriteria(Item objectIdentity) {
		Assert.notNull(objectIdentity);
		Assert.notNull(objectIdentity.getItemCategory());
		Assert.notNull(objectIdentity.getSmd());
		Map<String, String> criterias = new HashMap<String, String>();
		criterias
				.put(ITEM_CATEGORY, objectIdentity.getItemCategory().getCode());
		return criterias;
	}

}
