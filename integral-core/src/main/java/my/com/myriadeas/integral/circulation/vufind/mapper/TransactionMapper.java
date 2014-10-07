package my.com.myriadeas.integral.circulation.vufind.mapper;

import my.com.myriadeas.integral.circulation.vufind.Transaction;
import my.com.myriadeas.integral.data.jpa.domain.CirculationTransaction;

public interface TransactionMapper {
	public Transaction convertTo(CirculationTransaction circulationTransaction);
}
