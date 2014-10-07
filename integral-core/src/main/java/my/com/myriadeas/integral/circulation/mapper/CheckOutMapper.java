package my.com.myriadeas.integral.circulation.mapper;

import my.com.myriadeas.integral.circulation.response.CheckOutResponse;
import my.com.myriadeas.integral.data.jpa.domain.CirculationTransaction;

public interface CheckOutMapper {

	public CheckOutResponse convertTo(CirculationTransaction circulationTransaction);

}