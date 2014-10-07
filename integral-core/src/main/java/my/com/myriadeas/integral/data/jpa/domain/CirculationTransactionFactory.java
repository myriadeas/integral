package my.com.myriadeas.integral.data.jpa.domain;

import my.com.myriadeas.integral.circulation.CirculationTransactionFlag;
import my.com.myriadeas.integral.circulation.service.AbstractCirculationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("circulationTransactionFactory")
public class CirculationTransactionFactory extends AbstractCirculationService {
	private static final Logger logger = LoggerFactory
			.getLogger(CirculationTransactionFactory.class);
	
	
	public CirculationTransaction createCheckOutCirculationTransaction(Item item){
		logger.debug(
				"Entering createCheckOutCirculationTransaction(item={})", new Object[] {
						item});
		CirculationTransaction circulationTransaction = populateCheckOutCirculationTransaction(item);
		circulationTransactionRepo.save(circulationTransaction);		
		logger.debug("Leaving createCheckOutCirculationTransaction(). circulationTransaction={}", circulationTransaction);
		return circulationTransaction;
	}
	
	public CirculationTransaction createRenewCirculationTransaction(Item item, CirculationTransaction parentCirculationTransaction){
		logger.debug(
				"Entering createRenewCirculationTransaction(item={})", new Object[] {
						item});
		CirculationTransaction circulationTransaction = populateRenewNewCirculationTransaction(item, parentCirculationTransaction);
		circulationTransactionRepo.save(circulationTransaction);	
		logger.debug("Leaving createRenewCirculationTransaction(). circulationTransaction={}", circulationTransaction);
		return circulationTransaction;
	}
	
	public CirculationTransaction updateCheckInCirculationTransaction(Item item){
		logger.debug(
				"Entering updateCheckInCirculationTransaction(item={})", new Object[] {
						item});
		CirculationTransaction circulationTransaction = populateCheckInCirculationTransaction(item);
		circulationTransactionRepo.save(circulationTransaction);	
		logger.debug("Leaving updateCheckInCirculationTransaction(). circulationTransaction={}", circulationTransaction);
		return circulationTransaction;
	}
	
	public CirculationTransaction updateRenewCirculationTransaction(Item item){
		logger.debug(
				"Entering updateRenewCirculationTransaction(item={})", new Object[] {
						item});
		CirculationTransaction circulationTransaction = populateRenewCirculationTransaction(item);
		circulationTransactionRepo.save(circulationTransaction);		
		logger.debug("Leaving updateRenewCirculationTransaction(). circulationTransaction={}", circulationTransaction);
		return circulationTransaction;
	}
	
	
	protected CirculationTransaction populateCheckInCirculationTransaction(Item item) {
		logger.debug(
				"Entering populateCheckInCirculationTransaction(item={})", new Object[] {
						item});
		CirculationTransaction circulationTransaction = item.getCirculationTransactionWithFlagCharged();
		
		circulationTransaction.setFlag(CirculationTransactionFlag.DISCHARGED);
		circulationTransaction.setDischargeDateTime(item.getDischargeDateTime());
		circulationTransaction.setDischargeOfficer(item.getCirculationOfficer());
		logger.debug("Leaving populateCheckInCirculationTransaction(). circulationTransaction={}", circulationTransaction);
		
		return circulationTransaction;
		
	}
	
	

	protected CirculationTransaction populateCheckOutCirculationTransaction(Item item) {
		logger.debug(
				"Entering populateCheckOutCirculationTransaction(item={})", new Object[] {
						item});
		
		CirculationTransaction circulationTransaction = new CirculationTransaction();
		circulationTransaction.setPatron(item.getPatron());
		circulationTransaction.setItem(item);
		circulationTransaction.setChargeOfficer(item.getCirculationOfficer());
		circulationTransaction.setDueDateTime(item.getDueDateTime());
		circulationTransaction.setChargeDateTime(item.getChargeDateTime());
		circulationTransaction.setFlag(CirculationTransactionFlag.CHARGED);
		circulationTransaction.setNoOfRenew(0);
				
		logger.debug("Leaving populateCheckOutCirculationTransaction(). circulationTransaction={}", circulationTransaction);
		
		
		return circulationTransaction;
	}
	
	protected CirculationTransaction populateRenewCirculationTransaction(Item item) {
		logger.debug(
				"Entering populateRenewCirculationTransaction(item={})", new Object[] {
						item});
						
		CirculationTransaction circulationTransaction = item.getCirculationTransactionWithFlagCharged();
		circulationTransaction.setFlag(CirculationTransactionFlag.DISCHARGED);
		circulationTransaction.setDischargeDateTime(item.getRenewDateTime());
		circulationTransaction.setDischargeOfficer(item.getCirculationOfficer());
		logger.debug("Leaving populateRenewCirculationTransaction(). circulationTransaction={}", circulationTransaction);
		
		return circulationTransaction;
	}
	
	protected CirculationTransaction populateRenewNewCirculationTransaction(Item item, CirculationTransaction parentCirculationTransaction) {
		logger.debug(
				"Entering populateRenewNewCirculationTransaction(item={})", new Object[] {
						item});
		
		CirculationTransaction circulationTransaction = new CirculationTransaction();		
		circulationTransaction.setPatron(item.getPatron());
		circulationTransaction.setItem(item);
		circulationTransaction.setDueDateTime(item.getDueDateTime());
		circulationTransaction.setChargeDateTime(item.getRenewDateTime());
		circulationTransaction.setChargeOfficer(item.getCirculationOfficer());
		circulationTransaction.setFlag(CirculationTransactionFlag.CHARGED);
		circulationTransaction.setNoOfRenew(parentCirculationTransaction.getNoOfRenew() + 1);
		circulationTransaction.setCirculationTransactionParentForRenew(parentCirculationTransaction);
				
		logger.debug("Leaving populateRenewNewCirculationTransaction(). circulationTransaction={}", circulationTransaction);
		
		
		return circulationTransaction;
	}
	
	
	

	
}
