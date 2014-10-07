package my.com.myriadeas.integral.circulation.mapper;

import my.com.myriadeas.integral.circulation.response.RecallResponse;
import my.com.myriadeas.integral.data.jpa.domain.ReservationTransaction;

public interface RecallMapper {

	public RecallResponse convertTo(ReservationTransaction reservationTransaction);

}