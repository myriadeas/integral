package my.com.myriadeas.integral.circulation.service.impl;

import my.com.myriadeas.integral.data.jpa.domain.CirculationTransaction;
import my.com.myriadeas.integral.data.jpa.domain.PatronItemEligibility;
import my.com.myriadeas.integral.eligibility.PatronItemEligibilityLookup;
import my.com.myriadeas.integral.fines.FinesCalculatorFactory;
import my.com.myriadeas.integral.holiday.LibraryCalendarService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("overdueLoanFactory")
public class OverdueLoanFactory {
	private static final Logger logger = LoggerFactory
			.getLogger(OverdueLoanFactory.class);	
	
	private LibraryCalendarService libraryCalendarService; 
	
	private FinesCalculatorFactory finesCalculatorFactory;
	
	private PatronItemEligibilityLookup patronItemEligibilityLookup;	
	
	private FinesTransactionFactory finesTransactionFactory;
	
		
	@Autowired
	public void setPatronItemEligibilityLookup(
			PatronItemEligibilityLookup patronItemEligibilityLookup) {
		this.patronItemEligibilityLookup = patronItemEligibilityLookup;
	}
	
	
	@Autowired
	public void setLibraryCalendarService(
			LibraryCalendarService libraryCalendarService) {
		this.libraryCalendarService = libraryCalendarService;
	}
		

	@Autowired
	public void setFinesCalculatorFactory(
			FinesCalculatorFactory finesCalculatorFactory) {
		this.finesCalculatorFactory = finesCalculatorFactory;
	}
	
	@Autowired
	public void setFinesTransactionFactory(
			FinesTransactionFactory finesTransactionFactory) {
		this.finesTransactionFactory = finesTransactionFactory;
	}


	public OverdueItem createOverdueItem(CirculationTransaction circulationTransaction){
		logger.debug(
				"Entering createOverdueItem(circulationTransaction={}", circulationTransaction);
		PatronItemEligibility glpatritemelig = patronItemEligibilityLookup.lookup(circulationTransaction.getPatron(), circulationTransaction.getItem
				()).getDomain();
		OverdueItem overdueItem = null;
		if (glpatritemelig.isDailyLoan()) {
			logger.debug("Creating DailyLoanOverdueItem.");
			overdueItem = createDailyLoanOverdueItem(circulationTransaction);
			
		} else if (glpatritemelig.isHourlyLoan()) {
			logger.debug("Creating HourlyLoanOverdueItem.");
			overdueItem = createHourlyLoanOverdueItem(circulationTransaction);
		}

		logger.debug(
				"Leaving createOverdueItem(). overdueItem={}", overdueItem);
		return overdueItem;
	}
	
		
	private DailyLoanOverdueItem createDailyLoanOverdueItem(CirculationTransaction circulationTransaction){
		DailyLoanOverdueItem dailyLoanOverdueItem = new DailyLoanOverdueItem(circulationTransaction.getChargeDateTime(), circulationTransaction.getDischargeDateTime(), 
				circulationTransaction.getDueDateTime(), circulationTransaction.getItem().getBranch(),
				circulationTransaction.getPatron(), circulationTransaction.getItem(),
				circulationTransaction.getDischargeOfficer(), circulationTransaction.getCounter());
		
		dailyLoanOverdueItem.setLibraryCalendarService(libraryCalendarService);
		dailyLoanOverdueItem.setFinesCalculator(finesCalculatorFactory.getFinesCalculator(circulationTransaction));
		dailyLoanOverdueItem.setFinesTransactionFactory(finesTransactionFactory);
		return dailyLoanOverdueItem;
	}
	
		
	private HourlyLoanOverdueItem createHourlyLoanOverdueItem(CirculationTransaction circulationTransaction){
		HourlyLoanOverdueItem hourlyLoanOverdueItem = new HourlyLoanOverdueItem(circulationTransaction.getChargeDateTime(), circulationTransaction.getDischargeDateTime(), 
				circulationTransaction.getDueDateTime(), circulationTransaction.getItem().getBranch(),
				circulationTransaction.getPatron(), circulationTransaction.getItem(),
				circulationTransaction.getDischargeOfficer(), circulationTransaction.getCounter());
		
		hourlyLoanOverdueItem.setLibraryCalendarService(libraryCalendarService);
		hourlyLoanOverdueItem.setFinesCalculator(finesCalculatorFactory.getFinesCalculator(circulationTransaction));
		hourlyLoanOverdueItem.setFinesTransactionFactory(finesTransactionFactory);
		return hourlyLoanOverdueItem;
	}
	
}
