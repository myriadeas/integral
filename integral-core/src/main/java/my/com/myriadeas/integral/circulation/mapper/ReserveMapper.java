package my.com.myriadeas.integral.circulation.mapper;

import my.com.myriadeas.integral.circulation.response.ReserveResponse;
import my.com.myriadeas.integral.data.jpa.domain.ReservationTransaction;

public interface ReserveMapper {

	public ReserveResponse convertTo(ReservationTransaction reservationTransaction);

}