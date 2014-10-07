package my.com.myriadeas.integral.circulation.vufind.mapper;

import my.com.myriadeas.integral.circulation.vufind.Fines;
import my.com.myriadeas.integral.data.jpa.domain.CirculationTransaction;
import my.com.myriadeas.integral.data.jpa.domain.FinesTransaction;

public interface FinesMapper {
	//public Fines convertTo(Cicirc cicirc, OverdueItem overdueItem);
	public Fines convertTo(CirculationTransaction circulationTransaction, FinesTransaction retrxn);
}
