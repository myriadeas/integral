package my.com.myriadeas.integral.circulation.mapper.impl;

import my.com.myriadeas.integral.circulation.mapper.RecallMapper;
import my.com.myriadeas.integral.circulation.response.RecallResponse;
import my.com.myriadeas.integral.data.jpa.domain.ReservationTransaction;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service("recallMapper")
public class RecallMapperImpl implements RecallMapper {

	private static final Logger logger = org.slf4j.LoggerFactory
			.getLogger(RecallMapperImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * my.com.myriadeas.integral.circulation.facade.impl.CHeck#constructCheckOutResponse
	 * (my.com.myriadeas.integral.data.jpa.domain.Ctdocm,
	 * my.com.myriadeas.integral.data.jpa.domain.Cicirc,
	 * my.com.myriadeas.integral.data.jpa.domain.Glpatr, java.util.Date)
	 */
	@Override
	public RecallResponse convertTo(
			ReservationTransaction reservationTransaction) {
		logger.debug("Entering convertTo(reservationTransaction={})",
				reservationTransaction);
		Assert.notNull(reservationTransaction.getPriorityWeight());
		Assert.notNull(reservationTransaction.getBranch());
		Assert.notNull(reservationTransaction.getPatron());

		RecallResponse recallResponse = new RecallResponse();
		recallResponse.setItem(reservationTransaction.getItem());
		recallResponse.setLoginUser(reservationTransaction.getOfficer());
		recallResponse.setRecallDateTime(reservationTransaction
				.getReserveDateTime());
		recallResponse.setBranch(reservationTransaction.getBranch());
		recallResponse.setUser(reservationTransaction.getPatron());
		recallResponse.setReservationDetail(reservationTransaction);

		logger.debug("Leaving convertTo(). recallResponse={}", recallResponse);

		return recallResponse;

	}

}
