package my.com.myriadeas.integral.circulation.mapper;

import my.com.myriadeas.integral.circulation.response.CheckInResponse;
import my.com.myriadeas.integral.data.jpa.domain.CirculationTransaction;

public interface CheckInMapper {

	public abstract CheckInResponse convertTo(CirculationTransaction circulationTransaction);

}