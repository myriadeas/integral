package my.com.myriadeas.integral.circulation.service.impl;

import java.util.Date;

import my.com.myriadeas.integral.circulation.ReservationStatus;
import my.com.myriadeas.integral.circulation.service.AbstractCirculationService;
import my.com.myriadeas.integral.circulation.service.RecallService;
import my.com.myriadeas.integral.data.jpa.domain.Branch;
import my.com.myriadeas.integral.data.jpa.domain.Item;
import my.com.myriadeas.integral.data.jpa.domain.ItemStatus;
import my.com.myriadeas.integral.data.jpa.domain.Officer;
import my.com.myriadeas.integral.data.jpa.domain.Patron;
import my.com.myriadeas.integral.data.jpa.domain.ReservationTransaction;
import my.com.myriadeas.integral.data.jpa.repositories.impl.ItemRepositoryImpl;
import my.com.myriadeas.integral.data.jpa.repositories.impl.ReservationTransactionRepositoryImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service("recallService")
public class RecallServiceImpl extends AbstractCirculationService implements
		RecallService {
	private static final Logger logger = LoggerFactory
			.getLogger(RecallServiceImpl.class);

	@Autowired
	private ItemRepositoryImpl itemRepo;
	@Autowired
	private ReservationTransactionRepositoryImpl reservationTransactionRepo;
	
	@Override
	public ReservationTransaction recall(Patron patron, Item item, Branch recallBranch,
			Date recallDateTime) {
		logger.debug("Entering recall(patron={}, item={}, recallBranch={}, "
				+ "recallDateTime={})", new Object[] { patron, item,
				recallBranch, recallDateTime });

		Assert.notNull(patron);
		Assert.notNull(item);
		Assert.notNull(recallBranch);
		Assert.notNull(recallDateTime);

		Officer officer = getOfficer();
		ReservationTransaction reservationTransaction = updateCiresv(patron, item, recallBranch, officer,
				recallDateTime);
		updateCtdocm(item);

		logger.debug("Leaving recall(). reservationTransaction={}", reservationTransaction);
		return reservationTransaction;
	}

	private ReservationTransaction updateCiresv(Patron patron, Item item, Branch branch,
			Officer officer, Date recallDateTime) {
		logger.debug("Entering updateCiresv(patron={}, item={}, branch={}, "
				+ "officer={}, recallDateTime={})", new Object[] { patron,
				item, branch, officer, recallDateTime });

		Assert.notNull(patron);
		Assert.notNull(item);
		Assert.notNull(branch);
		Assert.notNull(officer);
		Assert.notNull(recallDateTime);
		Assert.notNull(item.getMaterial());

		Integer priority = new Integer("1");
		reservationTransactionRepo.increasePriorityByOneForPriorityMoreThanEquals(
				item.getMaterial(), priority);

		// add recaller to the top of reservation list
		ReservationTransaction reservationTransaction = new ReservationTransaction();
		reservationTransaction.setPatron(patron);
		reservationTransaction.setMaterial(item.getMaterial());
		reservationTransaction.setItem(item);
		reservationTransaction.setBranch(branch);
		reservationTransaction.setReserveDateTime(recallDateTime);
		reservationTransaction.setPriorityWeight(priority);
		reservationTransaction.setOfficer(officer);
		reservationTransaction.setStatus(ReservationStatus.RESERVE);
		reservationTransactionRepo.save(reservationTransaction);

		logger.debug("Leaving recall(). reservationTransaction={}", reservationTransaction);
		return reservationTransaction;
	}

	private void updateCtdocm(Item item) {
		logger.debug("Entering updateCtdocm(item={})", item);

		Assert.notNull(item);
		
		item.setItemStatus(ItemStatus.RECALLED);
		itemRepo.save(item);
		logger.debug("Leaving updateCtdocm(). item={}", item);
	}

}
