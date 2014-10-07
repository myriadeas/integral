package my.com.myriadeas.integral.data.populator;

import java.util.Date;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "checkoutDatabasePopulator")
public class CheckoutDatabasePopulator extends AbstractDatabasePopulator implements CheckoutDatabasePopulatorIntf {

	@Autowired
	PatronDatabasePopulatorIntf glpatrDatabasePopulator;

	@Autowired
	PatronEligibilityDatabasePopulatorIntf glpatreligDatabasePopulator;

	@Autowired
	ItemDatabasePopulatorIntf ctdocmDatabasePopulator;

	@Autowired
	PatronItemEligibilityDatabasePopulatorIntf glpatritemeligDatabasePopulator;

	@Autowired
	ReservationTransactionDatabasePopulatorIntf ciresvDatabasePopulator;
	

	@Autowired
	CirculationTransactionDatabasePopulatorIntf cicircDatabasePopulator;

	public void init() {
		DateTime membershipInFuture = new DateTime(new Date());
		ARLINA.setMembershipDate(membershipInFuture.plusYears(1).toDate());
		DateTime membershipExpired = new DateTime(new Date());
		CHIAKAIYONG.setMembershipExpiryDate(membershipExpired.plusHours(1).toDate());
		HUTINGUNG.setMembershipDate(new DateTime(new Date()).minusYears(1).toDate());
		LIMSYENIE.setMembershipDate(new DateTime(new Date()).minusYears(1).toDate());
		SIEWMEEYEE.setMembershipExpiryDate(membershipExpired.plusDays(2).toDate());
		
		// 4 loan item for hutingung
		CIRCULATED_ITEM_1.setPatron(HUTINGUNG);
		CIRCULATED_ITEM_1.setDueDateTime(new DateTime(new Date()).plusDays(5).toDate());//TODO- Add ddatetime for all circulated data.
		CIRCULATED_ITEM_2.setPatron(HUTINGUNG);
		CIRCULATED_ITEM_3.setPatron(HUTINGUNG);
		CIRCULATED_ITEM_4.setPatron(HUTINGUNG);

		// 5 loan item for limsyenie
		CIRCULATED_ITEM_RED_SPOT_BOOK_1.setPatron(LIMSYENIE);
		CIRCULATED_ITEM_RED_SPOT_BOOK_1.setDueDateTime(new DateTime(new Date()).plusDays(5).toDate());//reserved by other
		CIRCULATED_ITEM_RED_SPOT_BOOK_2.setPatron(LIMSYENIE);
		CIRCULATED_ITEM_RED_SPOT_BOOK_2.setDueDateTime(new DateTime(new Date()).minus(4).toDate());//overdue, not allow to renew
		CIRCULATED_ITEM_RED_SPOT_BOOK_3.setPatron(LIMSYENIE);
		CIRCULATED_ITEM_RED_SPOT_BOOK_3.setDueDateTime(new DateTime(new Date()).plusDays(1).toDate());
		CIRCULATED_ITEM_RED_SPOT_BOOK_4.setPatron(LIMSYENIE);
		CIRCULATED_ITEM_RED_SPOT_BOOK_4.setDueDateTime(new DateTime(new Date()).plusDays(3).toDate());
		CIRCULATED_ITEM_RED_SPOT_BOOK_5.setPatron(LIMSYENIE);
		CIRCULATED_ITEM_RED_SPOT_BOOK_5.setDueDateTime(new DateTime(new Date()).plusDays(2).toDate());
		CIRCULATED_ITEM_OPEN_SHELF_BOOK.setPatron(LIMSYENIE);
		CIRCULATED_ITEM_OPEN_SHELF_BOOK.setDueDateTime(new DateTime(new Date()).plusDays(2).toDate());
		
		CIRCULATIONTRANSACTION_RENEW_CIRCULATED_ITEM_RED_SPOT_BOOK_3_1.setNoOfRenew(2);
		CIRCULATIONTRANSACTION_RENEW_CIRCULATED_ITEM_RED_SPOT_BOOK_3_1.setChargeDateTime(new Date());
		CIRCULATIONTRANSACTION_CIRCULATED_ITEM_OPEN_SHELF_BOOK_SIEWMEEYEE.setNoOfRenew(0);
		CIRCULATIONTRANSACTION_CIRCULATED_ITEM_OPEN_SHELF_BOOK_SIEWMEEYEE.setChargeDateTime(new Date());
		CIRCULATIONTRANSACTION_CIRCULATED_ITEM_OPEN_SHELF_BOOK.setNoOfRenew(0);
		CIRCULATIONTRANSACTION_CIRCULATED_ITEM_OPEN_SHELF_BOOK.setChargeDateTime(new Date());
		
		CIRCULATIONTRANSACTION_RENEW_CIRCULATED_ITEM_RED_SPOT_BOOK_1.setNoOfRenew(0);
		CIRCULATIONTRANSACTION_RENEW_CIRCULATED_ITEM_RED_SPOT_BOOK_1.setChargeDateTime(new Date());
		CIRCULATIONTRANSACTION_RENEW_CIRCULATED_ITEM_RED_SPOT_BOOK_2.setNoOfRenew(0);
		CIRCULATIONTRANSACTION_RENEW_CIRCULATED_ITEM_RED_SPOT_BOOK_2.setChargeDateTime(new Date());
		CIRCULATIONTRANSACTION_RENEW_CIRCULATED_ITEM_RED_SPOT_BOOK_5.setNoOfRenew(0);
		CIRCULATIONTRANSACTION_RENEW_CIRCULATED_ITEM_RED_SPOT_BOOK_5.setChargeDateTime(new Date());
		CIRCULATIONTRANSACTION_RENEW_CIRCULATED_ITEM_RED_SPOT_BOOK_4.setNoOfRenew(0);
		CIRCULATIONTRANSACTION_RENEW_CIRCULATED_ITEM_RED_SPOT_BOOK_4.setChargeDateTime(new Date());
		
		CIRCULATED_ITEM_OPEN_SHELF_BOOK_SIEWMEEYEE.setPatron(SIEWMEEYEE);
		
		CIRCULATED_OVERDUE_ITEM_01.setPatron(SIEWMEEYEE);
		CIRCULATED_OVERDUE_ITEM_02.setPatron(SIEWMEEYEE);
	}

}
