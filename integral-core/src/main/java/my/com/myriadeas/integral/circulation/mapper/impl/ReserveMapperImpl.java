package my.com.myriadeas.integral.circulation.mapper.impl;

import my.com.myriadeas.integral.circulation.facade.AbstractFacadeService;
import my.com.myriadeas.integral.circulation.mapper.ReserveMapper;
import my.com.myriadeas.integral.circulation.response.ReserveResponse;
import my.com.myriadeas.integral.data.jpa.domain.ReservationTransaction;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service("reserveMapper")
public class ReserveMapperImpl extends AbstractFacadeService implements ReserveMapper {

	private static final Logger logger = org.slf4j.LoggerFactory
			.getLogger(ReserveMapperImpl.class);

	
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
	public ReserveResponse convertTo(ReservationTransaction reservationTransaction) {
		logger.debug(
				"Entering convertTo(reservationTransaction={})",
				reservationTransaction);
		Assert.notNull(reservationTransaction.getPriorityWeight());
		Assert.notNull(reservationTransaction.getBranch());
		Assert.notNull(reservationTransaction.getPatron());

		ReserveResponse reserveResponse = new ReserveResponse();
		// TODO tobe removed
		reserveResponse.setOk(true);
		reserveResponse.setAvailable(false);
		reserveResponse.setTransactionDate(reservationTransaction.getReserveDateTime());
		reserveResponse.setQueuePosition(reservationTransaction.getPriorityWeight().toString());
		reserveResponse
				.setPickupLocation(reservationTransaction.getBranch().getDescription());
		reserveResponse.setInstitutionId(reservationTransaction.getBranch().getDescription());
		reserveResponse.setPatronIdentifier(reservationTransaction.getPatron().getUsername());
		// reserveResponse.setItemIdentifier();
		// reserveResponse.setTitleIdentifier(titleIdentifier);
		String message = getMessage("circulationServiceFacade.reserve.success", 
				"Item is successfully reserved.");		
		reserveResponse.setScreenMessage(message);
		reserveResponse.setPrintLine(message);
		// to be removed

		reserveResponse.setReservationDetail(reservationTransaction);
		reserveResponse.setMaterial(reservationTransaction.getMaterial());

		logger.debug("Leaving convertTo(). reserveResponse={}",
				reserveResponse);

		return reserveResponse;

	}
	
	
}
