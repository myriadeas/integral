package my.com.myriadeas.integral.circulation.vufind.mapper;

import my.com.myriadeas.integral.circulation.vufind.Hold;
import my.com.myriadeas.integral.data.jpa.domain.ReservationTransaction;

public interface HoldMapper {
	public Hold convertTo(ReservationTransaction reservationTransaction, int holdPosition);
}
