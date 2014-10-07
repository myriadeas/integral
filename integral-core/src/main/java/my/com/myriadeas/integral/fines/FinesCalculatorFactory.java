package my.com.myriadeas.integral.fines;

import java.math.BigDecimal;
import java.util.List;

import my.com.myriadeas.integral.data.jpa.domain.CirculationTransaction;
import my.com.myriadeas.integral.data.jpa.domain.Fine;
import my.com.myriadeas.integral.data.jpa.domain.Item;
import my.com.myriadeas.integral.data.jpa.domain.Patron;
import my.com.myriadeas.integral.data.jpa.domain.PatronEligibility;
import my.com.myriadeas.integral.data.jpa.domain.PatronItemEligibility;
import my.com.myriadeas.integral.eligibility.Eligibility;
import my.com.myriadeas.integral.eligibility.PatronEligibilityLookup;
import my.com.myriadeas.integral.eligibility.PatronItemEligibilityLookup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service("finesCalculatorFactory")
public class FinesCalculatorFactory {
	private static final Logger logger = LoggerFactory
			.getLogger(FinesCalculatorFactory.class);
		
	private PatronItemEligibilityLookup patronItemEligibilityLookup;
	private PatronEligibilityLookup patronEligibilityLookup;
	
	
	@Autowired
	public void setPatronItemEligibilityLookup(
			PatronItemEligibilityLookup patronItemEligibilityLookup) {
		this.patronItemEligibilityLookup = patronItemEligibilityLookup;
	}
	
	@Autowired
	public void setPatronEligibilityLookup(
			PatronEligibilityLookup patronEligibilityLookup) {
		this.patronEligibilityLookup = patronEligibilityLookup;
	}

	public FinesCalculator getFinesCalculator(CirculationTransaction cicirc){
		logger.debug("Entering getFinesCalculator(cicirc={})", cicirc);
		Eligibility<PatronItemEligibility> eligibility = this.patronItemEligibilityLookup
				.lookup(cicirc.getPatron(), cicirc.getItem());
		PatronItemEligibility glpatritemelig = eligibility.getDomain();
		List<Fine> fines = glpatritemelig.getFines();
		BigDecimal patronItemEligibilityMaxFines = getPatronItemEligibilityMaxFines(
				cicirc.getPatron(), cicirc.getItem());
		BigDecimal patronEligibilityMaxFines = getPatronEligibilityMaxFines(
				cicirc.getPatron());
		FinesCalculator finesCalculator = new FinesCalculator(fines, patronItemEligibilityMaxFines, patronEligibilityMaxFines);
		logger.debug("Leaving getFinesCalculator(finesCalculator={})", finesCalculator);
		return finesCalculator;
	}	
	
	private BigDecimal getPatronItemEligibilityMaxFines(Patron patron, Item item){
		Assert.notNull(patron);
		Assert.notNull(item);
		PatronItemEligibility patronItemEligibility = patronItemEligibilityLookup
				.lookup(patron, item).getDomain();
		BigDecimal patronItemEligibilityMaxFines = patronItemEligibility
				.getMaxFines();
		return patronItemEligibilityMaxFines;
	}
	
	private BigDecimal getPatronEligibilityMaxFines(Patron patron){
		Assert.notNull(patron);
		PatronEligibility patronEligibility = patronEligibilityLookup
				.lookup(patron).getDomain();
		BigDecimal patronEligibilityMaxFines = patronEligibility.getMaxFines();
		return patronEligibilityMaxFines;
	}
		
}
