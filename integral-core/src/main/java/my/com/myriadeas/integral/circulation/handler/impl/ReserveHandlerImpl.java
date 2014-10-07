package my.com.myriadeas.integral.circulation.handler.impl;

import java.util.Date;

import my.com.myriadeas.integral.circulation.ReservationStatus;
import my.com.myriadeas.integral.circulation.command.CheckInEventCommand;
import my.com.myriadeas.integral.circulation.command.ReleaseEventCommand;
import my.com.myriadeas.integral.circulation.exception.ReservationNotFoundException;
import my.com.myriadeas.integral.core.command.handler.CommandHandler;
import my.com.myriadeas.integral.data.jpa.domain.CirculationTransaction;
import my.com.myriadeas.integral.data.jpa.domain.Item;
import my.com.myriadeas.integral.data.jpa.domain.ItemStatus;
import my.com.myriadeas.integral.data.jpa.domain.ReservationTransaction;
import my.com.myriadeas.integral.data.jpa.repositories.impl.CirculationTransactionRepositoryImpl;
import my.com.myriadeas.integral.data.jpa.repositories.impl.ItemRepositoryImpl;
import my.com.myriadeas.integral.data.jpa.repositories.impl.ReservationTransactionRepositoryImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service("reserveHandler")
public class ReserveHandlerImpl implements CommandHandler<CheckInEventCommand>{
	private static final Logger logger = LoggerFactory
			.getLogger(ReserveHandlerImpl.class);

	@Autowired
	private ItemRepositoryImpl itemRepo;
	
	private ReservationTransactionRepositoryImpl reservationTransactionRepo;

	private CirculationTransactionRepositoryImpl circulationTransactionRepo;

	@Autowired
	public void setCirculationTransaction(CirculationTransactionRepositoryImpl circulationTransactionRepo) {
		this.circulationTransactionRepo = circulationTransactionRepo;
	}
		
	@Autowired
	public void setReservationTransactionRepo(
			ReservationTransactionRepositoryImpl reservationTransactionRepo) {
		this.reservationTransactionRepo = reservationTransactionRepo;
	}

	@Override
	public void handle(CheckInEventCommand command) {
		logger.debug("Entering handle for CheckInEvent (CheckInEventCommand={})",
				command);
		Item item = itemRepo.findOne(command.getItem().getId());
		CirculationTransaction circulationTransaction = circulationTransactionRepo.findOne(command.getCirculationTransaction().getId());
		handleReserve(item, circulationTransaction.getDischargeDateTime());
		logger.debug("Leaving handle for CheckInEvent (). ");
	}
	
	
	public void handleForReleaseEvent(ReleaseEventCommand command) {
		logger.debug("Entering handleForReleaseEvent (ReleaseEventCommand={})",
				command);
		Item ctdocm = itemRepo.findOne(command.getItem().getId());
		handleReserve(ctdocm, new Date());
		logger.debug("Leaving handleForReleaseEvent(). ");
	}

	public void handleReserve(Item item, Date checkInDateTime) {
		logger.debug("Entering handleReturn(item={}, checkInDateTime={}",
				item, checkInDateTime);
		Assert.notNull(item);
		Assert.notNull(checkInDateTime);
		reserveHandler(item, checkInDateTime);
		logger.debug("Leaving handleReturn(). ");
	}

	private void reserveHandler(Item item, Date checkInDateTime) {
		logger.debug("Entering reserveHandler(item={}, checkInDateTime={}",
				item, checkInDateTime);
		
		if (item.isThereAnyReservation()) {
			try {
				ReservationTransaction reservationTransaction = item
						.findFirstQualifyReserver();

				// TODO Update Circulation Notices table [CINOTC]

				// Mark document status as being (H)eld for collection in CTDOCM
				populateItemToHold(item, checkInDateTime);
				itemRepo.save(item);
				
				// Mark document status as awaiting for collection in CIRESV
				Date scrutinyDateTime = null;// scrutinyDateCalculationService
				// .calculateScrutinyDateTime(checkInDateTime);
				populateReservationTransactionToAwaitingCollection(reservationTransaction, item,
						checkInDateTime, scrutinyDateTime);
				reservationTransactionRepo.save(reservationTransaction);
				
			} catch (ReservationNotFoundException reservationNotFound) {
				populateItemToAvailable(item, checkInDateTime);
				itemRepo.save(item);
			}
		} else {
			// '-- No Reservation exists, so mark document as (A)vailable in
			// CTDOCM
			populateItemToAvailable(item, checkInDateTime);
			itemRepo.save(item);
		}
		
		logger.debug("Leaving reserveHandler(). item={}", item);
	}

	
	protected void populateItemToHold(Item item, Date checkInDateTime) {
		logger.debug(
				"Entering populateCtdocmToHold(item={}, checkInDateTime={})",
				item, checkInDateTime);
		item.setChargeDateTime(null);
		item.setDueDateTime(null);
		item.setPatron(null);
		item.setItemStatus(ItemStatus.HOLD);
		
		logger.debug("Leaving populateCtdocmToHold (). item={}", item);
	}


	
	
	protected void populateItemToAvailable(Item item, Date checkInDateTime) {
		logger.debug(
				"Entering populateCtdocmToAvailable(item={}, checkInDateTime={})",
				item, checkInDateTime);
		String ct03lastid = "";
		if (item.getPatron() != null){
			ct03lastid = item.getPatron().getUsername();
		}
		item.setChargeDateTime(null);
		item.setDueDateTime(null);
		item.setPatron(null);
		item.setItemStatus(ItemStatus.AVAILABLE);
		
		logger.debug("Leaving populateCtdocmToAvailable (). item={}", item);
	}

	
	
	protected void populateReservationTransactionToAwaitingCollection(ReservationTransaction reservationTransaction, Item item,
			Date checkInDateTime, Date scrutinyDateTime){
		logger.debug(
				"Entering populateCiresvToAwaitingCollection(ciresv={}, item={}, checkInDateTime={}, scrutinyDateTime={})",
				new Object[] { reservationTransaction, item, checkInDateTime,
						scrutinyDateTime });
		reservationTransaction.setStatus(ReservationStatus.AWAITING_COLLECTION);
		reservationTransaction.setOnReserveForPatronDate(checkInDateTime);
		reservationTransaction.setItem(item);
		reservationTransaction.setScruitinyDateTime(scrutinyDateTime);		
		logger.debug("Leaving populateCiresvToAwaitingCollection (). ciresv={}",
				reservationTransaction);
	}


}
