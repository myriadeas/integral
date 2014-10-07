package my.com.myriadeas.integral.data.populator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import my.com.myriadeas.integral.data.jpa.domain.CirculationTransaction;
import my.com.myriadeas.integral.data.jpa.repositories.impl.CirculationTransactionRepositoryImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "circulationTransactionDatabasePopulator")
public class CirculationTransactionDatabasePopulator extends AbstractDatabasePopulator
		implements CirculationTransactionDatabasePopulatorIntf {

	@Autowired
	CirculationTransactionRepositoryImpl circulationTransactionRepository;

	@Autowired
	MaterialDatabasePopulatorIntf ctmatmDatabasePopulator;

	public void init() {
		List<CirculationTransaction> circulationTransactions = new ArrayList<CirculationTransaction>();
		circulationTransactions.add(CIRCULATIONTRANSACTION_RENEW_CIRCULATED_ITEM_RED_SPOT_BOOK_3_1);
		circulationTransactions.add(CIRCULATIONTRANSACTION_RENEW_CIRCULATED_ITEM_RED_SPOT_BOOK_1);
		circulationTransactions.add(CIRCULATIONTRANSACTION_RENEW_CIRCULATED_ITEM_RED_SPOT_BOOK_2);
		circulationTransactions.add(CIRCULATIONTRANSACTION_RENEW_CIRCULATED_ITEM_RED_SPOT_BOOK_4);
		circulationTransactions.add(CIRCULATIONTRANSACTION_RENEW_CIRCULATED_ITEM_RED_SPOT_BOOK_5);
		circulationTransactions.add(CIRCULATIONTRANSACTION_CIRCULATED_ITEM_OPEN_SHELF_BOOK_SIEWMEEYEE);
		circulationTransactions.add(CIRCULATIONTRANSACTION_CIRCULATED_ITEM_OPEN_SHELF_BOOK);
		setupOverdueItem(CIRCULATIONTRANSACTION_CIRCULATED_OVERDUE_ITEM_01);
		circulationTransactions.add(CIRCULATIONTRANSACTION_CIRCULATED_OVERDUE_ITEM_01);
		setupOverdueItemExceedMaximumFines(CIRCULATIONTRANSACTION_CIRCULATED_OVERDUE_ITEM_01);
		circulationTransactions.add(CIRCULATIONTRANSACTION_CIRCULATED_OVERDUE_ITEM_02);
		circulationTransactionRepository.save(circulationTransactions);
	}

	private void setupOverdueItem(CirculationTransaction circulationTransaction) {
		Date dueDate = getCurrentDateMinusDays(4);
		Date borrowDate = getCurrentDateMinusDays(10);
		circulationTransaction.setDueDateTime(dueDate);
		circulationTransaction.setChargeDateTime(borrowDate);
	}
	
	private void setupOverdueItemExceedMaximumFines(CirculationTransaction circulationTransaction) {
		Date dueDate = getCurrentDateMinusDays(100);
		Date borrowDate = getCurrentDateMinusDays(110);
		circulationTransaction.setDueDateTime(dueDate);
		circulationTransaction.setChargeDateTime(borrowDate);
	}
}
