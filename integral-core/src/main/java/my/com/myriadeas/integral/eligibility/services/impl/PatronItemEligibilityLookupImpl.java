package my.com.myriadeas.integral.eligibility.services.impl;

import java.util.HashMap;
import java.util.Map;

import my.com.myriadeas.integral.core.EligibilityNotFoundException;
import my.com.myriadeas.integral.data.jpa.domain.Item;
import my.com.myriadeas.integral.data.jpa.domain.Patron;
import my.com.myriadeas.integral.data.jpa.domain.PatronItemEligibility;
import my.com.myriadeas.integral.data.jpa.repositories.impl.PatronItemEligibilityRepositoryImpl;
import my.com.myriadeas.integral.eligibility.Eligibility;
import my.com.myriadeas.integral.eligibility.EligibilityRuleCommand;
import my.com.myriadeas.integral.eligibility.EligibilityTableFactory;
import my.com.myriadeas.integral.eligibility.PatronItemEligibilityLookup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service("patronItemEligibilityLookup")
public class PatronItemEligibilityLookupImpl extends AbstractEligibilityLookupImpl
		implements PatronItemEligibilityLookup {

	private static final Logger logger = LoggerFactory
			.getLogger(PatronItemEligibilityLookupImpl.class);

	@Autowired
	private PatronItemEligibilityRepositoryImpl glpatreligRepo;
	
	public PatronItemEligibilityLookupImpl() {
	}

	@Override
	public Eligibility<PatronItemEligibility> lookup(
			Patron securityIdentity, Item objectIdentity)
			throws EligibilityNotFoundException {
		logger.debug("Security Identity={}, Object Identity={}",
				securityIdentity, objectIdentity);
		Map<String, String> criterias = buildCriteria(securityIdentity,
				objectIdentity);
		logger.debug("criterias={}", criterias);
		EligibilityRuleCommand command = lookupStrategy.lookup(criterias);
		logger.debug("command={}", command);
		PatronItemEligibility glelig = getGlelig(command);
		if (glelig == null) {
			throw new EligibilityNotFoundException(
					"Eligibility not found for criterias= " + criterias);
		}
		Eligibility<PatronItemEligibility> eligibility = new Eligibility<PatronItemEligibility>(
				command, glelig);
		logger.debug("Eligibility={}", eligibility);
		return eligibility;
	}

	protected PatronItemEligibility getGlelig(EligibilityRuleCommand command)
			throws EligibilityNotFoundException {
		return glpatreligRepo.findByCode(command.getEligibility());
	}

	public Map<String, String> buildCriteria(Patron securityIdentity,
			Item objectIdentity) {
		Assert.notNull(securityIdentity);
		Assert.notNull(securityIdentity.getPatronCategory());
		Assert.notNull(objectIdentity);
		Assert.notNull(objectIdentity.getItemCategory());
		Assert.notNull(objectIdentity.getSmd());
		Map<String, String> criterias = new HashMap<String, String>();
		criterias.put(PATRON_CATEGORY, securityIdentity.getPatronCategory()
				.getCode());
		criterias
				.put(ITEM_CATEGORY, objectIdentity.getItemCategory().getCode());
		criterias.put(SMD, objectIdentity.getSmd().getCode());
		// TODO - hold for location
		return criterias;
	}

	@Autowired
	@Qualifier("patronItemEligibilityTableFactoryBean")
	public void setLookupTableFactoryBean(
			EligibilityTableFactory lookupTableFactoryBean) {
		super.setLookupTableFactoryBean(lookupTableFactoryBean);
	};
}
