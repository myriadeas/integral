package my.com.myriadeas.integral.fines;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import my.com.myriadeas.integral.data.jpa.domain.Fine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FinesCalculator {
	private static final Logger logger = LoggerFactory
			.getLogger(FinesCalculator.class);

	private List<Fine> fines = new ArrayList<Fine>();
	private BigDecimal patronItemEligibilityMaxFines;
	private BigDecimal patronEligibilityMaxFines;

	public FinesCalculator() {
		super();
	}

	public FinesCalculator(List<Fine> fines, BigDecimal patronItemEligibilityMaxFines, BigDecimal patronEligibilityMaxFines) {
		super();
		this.fines = fines;
		this.patronItemEligibilityMaxFines = patronItemEligibilityMaxFines;
		this.patronEligibilityMaxFines = patronEligibilityMaxFines;
	}

	public List<Fine> getFines() {
		return fines;
	}
	
	public BigDecimal getPatronItemEligibilityMaxFines() {
		return patronItemEligibilityMaxFines;
	}

	public void setPatronItemEligibilityMaxFines(
			BigDecimal patronItemEligibilityMaxFines) {
		this.patronItemEligibilityMaxFines = patronItemEligibilityMaxFines;
	}

	public BigDecimal getPatronEligibilityMaxFines() {
		return patronEligibilityMaxFines;
	}

	public void setPatronEligibilityMaxFines(BigDecimal patronEligibilityMaxFines) {
		this.patronEligibilityMaxFines = patronEligibilityMaxFines;
	}

	public BigDecimal calculateFines(int lateBy) {
		logger.debug("Entering calculateFines(lateBy={})", lateBy);
		
		BigDecimal finesAmount = getAmount(lateBy);
		logger.debug("Actual fines amount based on lateBy = " + finesAmount);
		finesAmount = getFinesBasedOnEligibilityMaxFines(finesAmount);
		logger.debug("Final fines amount based on EligibilityMaxFines = " + finesAmount);
		
		logger.debug("Leaving calculateFines(). finesAmount={}", finesAmount);
		return finesAmount;
	}	
		
	protected BigDecimal getFinesBasedOnEligibilityMaxFines(BigDecimal actualFines){
		BigDecimal patronItemEligibilityMaxFines = this.getPatronItemEligibilityMaxFines();		
		if(patronItemEligibilityMaxFines != null && isMaxFinesDefined(patronItemEligibilityMaxFines)){
			return getFinesBasedOnMaxFines(actualFines, patronItemEligibilityMaxFines);
		} else {
			BigDecimal patronEligibilityMaxFines = this.getPatronEligibilityMaxFines();
			if (patronEligibilityMaxFines != null &&isMaxFinesDefined(patronEligibilityMaxFines)){
				return getFinesBasedOnMaxFines(actualFines, patronEligibilityMaxFines);
			}
		}
		return actualFines;
	}
	
	protected boolean isMaxFinesDefined(BigDecimal maxFines){
		if (maxFines.compareTo(new BigDecimal(0)) == 1) {
			return true;
		}
		return false;
	}
	
	
	protected BigDecimal getFinesBasedOnMaxFines(BigDecimal actualFines, BigDecimal maxFines){
		if (actualFines.compareTo(maxFines) == 1) {
			return maxFines;
		} else {
			return actualFines;
		}
	}

	protected Fine getRates(int position) {
		logger.debug("Entering getRates(position={})", position);
		for (Fine fine : this.fines) {
			if (fine.getRange().isInRange(position)) {
				logger.debug("Leaving getRates(). fine={}", fine);
				return fine;
			}
		}
		logger.error("Fine not found.");
		throw new FineNotFoundException("Fine not found.", null);
	}

	protected BigDecimal getAmount(int lateBy) {
		logger.debug("Entering getAmount(lateBy={})", lateBy);
		BigDecimal amount = new BigDecimal("0.00");
		try{
			for (int i = 1; i <= lateBy; i++) {
				amount = getRates(i).getRate().add(amount);
			}
		}catch(FineNotFoundException e){
			
		}
		logger.debug("Leaving getAmount(). amount={}", amount);
		return amount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fines == null) ? 0 : fines.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FinesCalculator other = (FinesCalculator) obj;
		if (fines == null) {
			if (other.fines != null)
				return false;
		} else if (!fines.equals(other.fines))
			return false;
		return true;
	}

}
