package my.com.myriadeas.integral.eligibility.services.impl;

import java.util.HashMap;
import java.util.Map;

import my.com.myriadeas.integral.core.EligibilityNotFoundException;
import my.com.myriadeas.integral.data.jpa.domain.Patron;
import my.com.myriadeas.integral.data.jpa.domain.PatronEligibility;
import my.com.myriadeas.integral.data.jpa.repositories.impl.PatronEligibilityRepositoryImpl;
import my.com.myriadeas.integral.eligibility.Eligibility;
import my.com.myriadeas.integral.eligibility.EligibilityRuleCommand;
import my.com.myriadeas.integral.eligibility.EligibilityTableFactory;
import my.com.myriadeas.integral.eligibility.LookupStrategy;
import my.com.myriadeas.integral.eligibility.PatronEligibilityLookup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service("patronEligibilityLookup")
public class PatronEligibilityLookupImpl extends AbstractEligibilityLookupImpl
		implements PatronEligibilityLookup {

	private static final Logger logger = LoggerFactory
			.getLogger(PatronEligibilityLookupImpl.class);

	public LookupStrategy getLookupStrategy() {
		return lookupStrategy;
	}

	@Autowired
	PatronEligibilityRepositoryImpl glpatreligsRepo;

	public PatronEligibilityLookupImpl() {
	}

	@Override
	public Eligibility<PatronEligibility> lookup(Patron identity)
			throws EligibilityNotFoundException {
		logger.debug("Identity={}", identity);
		Map<String, String> criterias = buildCriteria(identity);
		logger.debug("criterias={}", criterias);
		EligibilityRuleCommand command = lookupStrategy.lookup(criterias);
		logger.debug("command={}", command);
		PatronEligibility glpatreligs = getGlpatrelig(command);
		if (glpatreligs == null) {
			throw new EligibilityNotFoundException(
					"Eligibility not found for criterias= " + criterias);
		}
		Eligibility<PatronEligibility> eligibility = new Eligibility<PatronEligibility>(
				command, glpatreligs);
		logger.debug("Eligibility={}", eligibility);

		return eligibility;
	}

	protected PatronEligibility getGlpatrelig(EligibilityRuleCommand command) {
		return glpatreligsRepo.findByCode(command.getEligibility());
	}

	// TODO - Dynamic way to build criteria;
	public Map<String, String> buildCriteria(Patron identity) {
		Assert.notNull(identity);
		Assert.notNull(identity.getPatronCategory());
		Map<String, String> criterias = new HashMap<String, String>();
		criterias.put("pc", identity.getPatronCategory().getCode());
		logger.debug("criterias={}", criterias);
		return criterias;
	}

	@Autowired
	@Qualifier("patronEligibilityTableFactoryBean")
	public void setLookupTableFactoryBean(
			EligibilityTableFactory lookupTableFactoryBean) {
		super.setLookupTableFactoryBean(lookupTableFactoryBean);
	};

}
