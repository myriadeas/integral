package my.com.myriadeas.integral.circulation.mapper;

import my.com.myriadeas.integral.circulation.response.RenewResponse;
import my.com.myriadeas.integral.data.jpa.domain.CirculationTransaction;

public interface RenewMapper {

	public RenewResponse convertTo(CirculationTransaction circulationTransaction);

}