package my.com.myriadeas.integral.data.populator;

import java.util.ArrayList;
import java.util.List;

import my.com.myriadeas.integral.data.jpa.domain.ReservationTransaction;
import my.com.myriadeas.integral.data.jpa.repositories.impl.ReservationTransactionRepositoryImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "reservationTransactionDatabasePopulator")
public class ReservationTransactionDatabasePopulator extends AbstractDatabasePopulator implements ReservationTransactionDatabasePopulatorIntf {

	@Autowired
	ReservationTransactionRepositoryImpl reservationTransactionRepository;
	
	@Autowired
	MaterialDatabasePopulatorIntf materialDatabasePopulator;

	public void init() {
		List<ReservationTransaction> reservationTransactions = new ArrayList<ReservationTransaction>();
		reservationTransactions.add(MATERIAL_RESERVED_BY_LIMSYENIE);
		reservationTransactions.add(MATERIAL_RESERVED_BY_SIEWMEEYEE);
		reservationTransactions.add(MATERIAL_CIRCULATED_RESERVED);
		reservationTransactionRepository.save(reservationTransactions);
	}

}
