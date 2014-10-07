package my.com.myriadeas.integral.circulation.vufind.mapper.impl;

import java.util.List;

import my.com.myriadeas.integral.circulation.facade.AbstractFacadeService;
import my.com.myriadeas.integral.circulation.service.PublicationYearRetrieverIntf;
import my.com.myriadeas.integral.circulation.service.TitleRetrieverIntf;
import my.com.myriadeas.integral.circulation.validation.RenewPolicyItemValidator;
import my.com.myriadeas.integral.circulation.validation.RenewPolicyPatronItemValidator;
import my.com.myriadeas.integral.circulation.validation.RenewPolicyPatronValidator;
import my.com.myriadeas.integral.circulation.vufind.Transaction;
import my.com.myriadeas.integral.circulation.vufind.mapper.TransactionMapper;
import my.com.myriadeas.integral.circulation.vufind.mapper.VufindUtil;
import my.com.myriadeas.integral.data.jpa.domain.CirculationTransaction;
import my.com.myriadeas.integral.data.jpa.domain.Item;
import my.com.myriadeas.integral.data.jpa.domain.Patron;
import my.com.myriadeas.integral.data.jpa.domain.PatronItemEligibility;
import my.com.myriadeas.integral.data.jpa.domain.ReservationTransaction;
import my.com.myriadeas.integral.eligibility.PatronItemEligibilityLookup;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("transactionMapper")
public class TransactionMapperImpl extends AbstractFacadeService implements TransactionMapper {

	private static final Logger logger = org.slf4j.LoggerFactory
			.getLogger(TransactionMapperImpl.class);
	
	private VufindUtil vufindUtil = new VufindUtil();
	private TitleRetrieverIntf titleRetriever;
	private PublicationYearRetrieverIntf publicationYearRetriever;	

	private RenewPolicyItemValidator renewItemValidator;	
	private RenewPolicyPatronItemValidator renewPatronItemValidator;
	private RenewPolicyPatronValidator renewPatronValidator;
	
	private PatronItemEligibilityLookup patronItemEligibilityLookup;
	
	@Autowired
	public void setPatronItemEligibilityLookup(
			PatronItemEligibilityLookup patronItemEligibilityLookup) {
		this.patronItemEligibilityLookup = patronItemEligibilityLookup;
	}

	@Autowired
	public void setRenewItemValidatorImpl(RenewPolicyItemValidator renewItemValidator) {
		this.renewItemValidator = renewItemValidator;
	}
	
	@Autowired
	public void setRenewPatronItemValidatorImpl(RenewPolicyPatronItemValidator renewPatronItemValidator) {
		this.renewPatronItemValidator = renewPatronItemValidator;
	}
	
	@Autowired
	public void setRenewPatronValidatorImpl(RenewPolicyPatronValidator renewPatronValidator) {
		this.renewPatronValidator = renewPatronValidator;
	}

	@Autowired
	public void setTitleRetriever(TitleRetrieverIntf titleRetriever) {
		this.titleRetriever = titleRetriever;
	}

	@Autowired
	public void setPublicationYearRetriever(
			PublicationYearRetrieverIntf publicationYearRetriever) {
		this.publicationYearRetriever = publicationYearRetriever;
	}



	@Override
	public Transaction convertTo(CirculationTransaction circulationTransaction) {
		logger.debug("Entering convertTo(circulationTransaction={})", circulationTransaction);
		
		Item item = circulationTransaction.getItem();
		Patron patron = circulationTransaction.getPatron();
		Transaction transaction = new Transaction();
		transaction.setBarcode("");
		transaction.setDuedate(vufindUtil.getDateTimeString(circulationTransaction.getDueDateTime()));
		transaction.setId(item.getMaterial().getMaterialNo());
		if (item.getLocation() != null && item.getLocation().getBranch() != null){
			transaction.setInstitution_name(item.getLocation().getBranch().getDescription());
		}
		transaction.setItem_id(item.getItemIdentifier());
		transaction.setMessage("");
		transaction.setPublication_year(publicationYearRetriever.getPublicationYear(item));
		transaction.setRenew(circulationTransaction.getNoOfRenew());
		
		
		try{
			logger.debug("renewItemValidator.validate()");
			renewItemValidator.validate(item);
			logger.debug("renewPatronItemValidator.validate()");
			renewPatronItemValidator.validate(patron, item);
			logger.debug("renewPatronValidator.validate()");
			renewPatronValidator.validate(patron);
			transaction.setRenewable(true);
		} catch (Exception e){
			logger.debug("Renew validation exception = " + e.getMessage());
			transaction.setRenewable(false);
			String message = getMessage("vufindServiceFacade.transaction.exception", new Object[]{e.getMessage()},
					e.getMessage());
			transaction.setMessage(message);
		}
				
		PatronItemEligibility patronItemEligibility = this.patronItemEligibilityLookup
				.lookup(patron, item).getDomain();		
		transaction.setRenewLimit(patronItemEligibility.getMaxRenewAllowed());
		
		transaction.setRequest(0);
		List<ReservationTransaction> reservationTransactions = item.findAllReservations();
		if (reservationTransactions != null && !reservationTransactions.isEmpty()){
			transaction.setRequest(reservationTransactions.size());
		}
		
		transaction.setTitle(titleRetriever.getTitle(item));
		transaction.setVolume(item.getVolume());
				
		logger.debug(
				"Leaving convertTo(). transaction={}",
				transaction);

		return transaction;

	}

	
	
	
	
}
