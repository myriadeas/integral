package my.com.myriadeas.integral.circulation.vufind.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import my.com.myriadeas.integral.circulation.ReservationStatus;
import my.com.myriadeas.integral.circulation.facade.AbstractFacadeService;
import my.com.myriadeas.integral.circulation.service.CallNumberRetrieverIntf;
import my.com.myriadeas.integral.circulation.validation.RecallPolicyPatronItemValidator;
import my.com.myriadeas.integral.circulation.validation.ReservePolicyItemValidator;
import my.com.myriadeas.integral.circulation.validation.ReservePolicyPatronItemValidator;
import my.com.myriadeas.integral.circulation.validation.ReservePolicyPatronValidator;
import my.com.myriadeas.integral.circulation.vufind.Holding;
import my.com.myriadeas.integral.circulation.vufind.mapper.HoldingMapper;
import my.com.myriadeas.integral.circulation.vufind.mapper.VufindUtil;
import my.com.myriadeas.integral.data.jpa.domain.Item;
import my.com.myriadeas.integral.data.jpa.domain.Patron;
import my.com.myriadeas.integral.data.jpa.domain.ReservationTransaction;
import my.com.myriadeas.integral.data.jpa.repositories.impl.ReservationTransactionRepositoryImpl;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("holdingMapper")
public class HoldingMapperImpl extends AbstractFacadeService implements HoldingMapper{

	private static final Logger logger = org.slf4j.LoggerFactory
			.getLogger(HoldingMapperImpl.class);
	
	private VufindUtil vufindUtil = new VufindUtil();
	//private HoldsMode holdsMode = new HoldsMode();
	private CallNumberRetrieverIntf callNumberRetriever;
	
	private ReservePolicyItemValidator reserveItemValidator;
	private ReservePolicyPatronItemValidator reservePatronItemValidator;
	private ReservePolicyPatronValidator reservePatronValidator;
	
	private ReservationTransactionRepositoryImpl reservationTransactionRepo;
	
	
	@Autowired
	public void setCiresvRepo(ReservationTransactionRepositoryImpl reservationTransactionRepo) {
		this.reservationTransactionRepo = reservationTransactionRepo;
	}



	@Autowired
	public void setCallNumberRetriever(CallNumberRetrieverIntf callNumberRetriever) {
		this.callNumberRetriever = callNumberRetriever;
	}
	
	

	@Autowired
	public void setReserveItemValidator(ReservePolicyItemValidator reserveItemValidator) {
		this.reserveItemValidator = reserveItemValidator;
	}



	@Autowired
	public void setReservePatronItemValidator(
			ReservePolicyPatronItemValidator reservePatronItemValidator) {
		this.reservePatronItemValidator = reservePatronItemValidator;
	}



	@Autowired
	public void setReservePatronValidator(
			ReservePolicyPatronValidator reservePatronValidator) {
		this.reservePatronValidator = reservePatronValidator;
	}


	@Override
	public Holding convertTo(Patron patron, Item item) {
		
		logger.debug("Entering convertTo(item={})", item);
		
		List<String> notes = new ArrayList<String>();
		
		Holding holding = new Holding();
		holding.setAvailability(item.isAvailable());
		holding.setBarcode("XXXXXXX");
		holding.setCallnumber(callNumberRetriever.getCallNumber(item));
		holding.setHoldtype("hold");
		
		if (item.isAvailable()) {			
			//TODO want to allow this for BOOKING???
			holding.setIs_holdable(false);			
			String msg = getMessage("vufindServiceFacade.holding.note.itemIsAvailable", new Object[]{item.getItemStatus().toString()},
					"The status of the item is " +  item.getItemStatus().toString() + ". You may get the item from the shelf.");
			logger.debug(msg);
			notes.add(msg);
			
		} else if (item.isCirculated() || item.isRenew()){
			try{
				holding.setDuedate(vufindUtil.getDateTimeString(item.getDueDateTime()));
				logger.debug("reservePatronValidator ...");
				reservePatronValidator.validate(patron);	
				logger.debug("reserveItemValidator ...");
				reserveItemValidator.validate(item);
				logger.debug("recallPatronItemValidator ...");
				reservePatronItemValidator.validate(patron, item);
				holding.setIs_holdable(true);
				logger.debug("holding.setIs_holdable(true)");
			} catch (Exception e){
				holding.setIs_holdable(false);
				logger.debug("holding.setIs_holdable(false)");
				logger.debug(e == null ? null: e.toString());
				String msg = getMessage("vufindServiceFacade.holding.note.exception", new Object[]{e.getMessage()},
						e.getMessage());
				notes.add(msg);
			}		
			
		} else {
			holding.setIs_holdable(false);
			String msg = getMessage("vufindServiceFacade.holding.note.itemIsNotForHold", new Object[]{item.getItemStatus().toString()},
					"The status of the item is " +  item.getItemStatus().toString() + ". Hold/Recall action is not allowed.");
			logger.debug(msg);
			notes.add(msg);
			
		}
		
		
		holding.setId(item.getMaterial().getMaterialNo());		
		holding.setItem_id(item.getItemIdentifier());
		holding.setLocation(item.getBranch().getDescription());		
		holding.setNumber(item.getCopyNumber());	
		if (item.isHold()){
			List<ReservationTransaction> reservationTransactions = reservationTransactionRepo.findByStatusAndItem(ReservationStatus.AWAITING_COLLECTION, item);
			if (reservationTransactions.isEmpty()){
				holding.setReserve("N");
			} else {
				if (reservationTransactions.get(0).getPatron().equals(patron)){
					holding.setReserve("Y");
				} else {
					holding.setReserve("N");
				}
			}
		} else {
			holding.setReserve("N");
		} 
			
		holding.setReturnDate("false");		
		holding.setStatus(item.getItemStatus().toString());
		holding.setNotes(notes);
		//holding.setSummary(summaries);
		//holding.setSupplements(notes);		
		//holding.setIndexes(notes);
		//holding.setHoldOverride(holdsMode.AVAILABILITY);
		
		
		
		logger.debug(
				"Leaving convertTo(). holding={}",
				holding);

		return holding;
		
	}

	
	
}
