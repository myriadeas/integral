package my.com.myriadeas.integral.circulation.validation.impl;

import java.util.List;

import my.com.myriadeas.integral.circulation.exception.PatronHasBorrowedItemException;
import my.com.myriadeas.integral.circulation.exception.PatronHasReservedItemException;
import my.com.myriadeas.integral.circulation.request.RecallRequest;
import my.com.myriadeas.integral.circulation.validation.RecallPolicyPatronItemValidator;
import my.com.myriadeas.integral.data.jpa.domain.Item;
import my.com.myriadeas.integral.data.jpa.domain.Patron;
import my.com.myriadeas.integral.data.jpa.domain.ReservationTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service(value = "recallPatronItemValidator")
public class RecallPolicyPatronItemValidatorImpl extends
		AbstractCirculationReservePatronItemValidator implements
		RecallPolicyPatronItemValidator {

	private static Logger logger = LoggerFactory
			.getLogger(RecallPolicyPatronItemValidatorImpl.class);

	@Override
	public void validate(RecallRequest recallRequest) {
		logger.debug("Entering validate(recallRequest={})", recallRequest);

		Item item = itemRepository.findByItemIdentifier(recallRequest
				.getItemIdentifier());
		Patron patron = patronRepository.findByUsername(recallRequest
				.getPatronIdentifier());
		validate(patron, item);

		logger.debug("Leaving validate(). ");
	}


	@Override
	protected void validateReserveCustomPolicyPatronItemValidator(Patron patron, Item item) {
		logger.debug(
				"Entering validateReserveCustomPolicyPatronItemValidator(patron={}, item={})",
				new Object[] { patron, item });

		validatePatronHasReservedItem(patron, item);
		validatePatronHasBorrowedItem(patron, item);

		logger.debug("Leaving validateReserveCustomPolicyPatronItemValidator(). ");	
	}

	private void validatePatronHasReservedItem(Patron patron, Item item)
			throws PatronHasReservedItemException {
		logger.debug(
				"Entering validatePatronHasReservedItem(patron={}, item={})",
				new Object[] { patron, item });

		List<ReservationTransaction> reservationTransactionList = item
				.findAllReservations();
		logger.debug("ReservationTransaction list={}", reservationTransactionList);
		for (ReservationTransaction reservationTransaction : reservationTransactionList) {
			validatePatronHasReservedItem(patron, reservationTransaction);
		}

		logger.debug("Leaving validatePatronHasReservedItem(). ");
	}

	private void validatePatronHasReservedItem(Patron patron, ReservationTransaction reservationTransaction)
			throws PatronHasReservedItemException {
		logger.debug(
				"Entering validatePatronHasReservedItem(patron={}, reservationTransaction={})",
				new Object[] { patron, reservationTransaction });

		validatePatronHasReservedItem(patron.getUsername(), reservationTransaction
				.getPatron().getUsername());

		logger.debug("Leaving validatePatronHasReservedItem(). ");

	}

	protected void validatePatronHasReservedItem(String patronId,
			String reserverId) throws PatronHasReservedItemException {
		logger.debug(
				"Entering validatePatronHasReservedItem(patronId={}, reserverId={})",
				new Object[] { patronId, reserverId });

		if (patronId.equals(reserverId)) {
			throw new PatronHasReservedItemException(
					"Patron already reserved item.");
		}

		logger.debug("Leaving validatePatronHasReservedItem(). ");
	}

	private void validatePatronHasBorrowedItem(Patron patron, Item item)
			throws PatronHasBorrowedItemException {
		logger.debug(
				"Entering validatePatronHasBorrowedItem(patron={}, item={})",
				new Object[] { patron, item });

		validatePatronHasBorrowedItem(patron, item.getPatron());

		logger.debug("Leaving validatePatronHasBorrowedItem(). ");
	}

	private void validatePatronHasBorrowedItem(Patron patron, Patron borrower)
			throws PatronHasBorrowedItemException {
		logger.debug(
				"Entering validatePatronHasBorrowedItem(patron={}, borrower={})",
				new Object[] { patron, borrower });

		validatePatronHasBorrowedItem(patron.getUsername(),
				borrower.getUsername());

		logger.debug("Leaving validatePatronHasBorrowedItem(). ");
	}

	protected void validatePatronHasBorrowedItem(String patronId,
			String borrowerId) throws PatronHasBorrowedItemException {
		logger.debug(
				"Entering validatePatronHasBorrowedItem(patronId={}, borrowerId={})",
				new Object[] { patronId, borrowerId });

		if (patronId.equals(borrowerId)) {
			throw new PatronHasBorrowedItemException(
					"Patron already borrowed item.");
		}

		logger.debug("Leaving validatePatronHasBorrowedItem(). ");
	}


}
