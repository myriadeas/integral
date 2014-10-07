package my.com.myriadeas.integral.circulation.validation.impl;

import my.com.myriadeas.integral.circulation.request.ReserveRequest;
import my.com.myriadeas.integral.circulation.validation.ReservePolicyPatronValidator;
import my.com.myriadeas.integral.data.jpa.domain.Patron;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service(value = "reservePatronValidator")
public class ReservePolicyPatronValidatorImpl extends
		AbstractCirculationReservePatronValidator implements
		ReservePolicyPatronValidator {

	private static Logger logger = LoggerFactory
			.getLogger(ReservePolicyPatronValidatorImpl.class);

	@Override
	public void validate(ReserveRequest reserveRequest) {
		logger.debug("Entering validate(reserveRequest={})", reserveRequest);
		Patron patron = patronRepository.findByUsername(reserveRequest
				.getPatronIdentifier());
		validate(patron);

		logger.debug("Leaving validate(). ");
	}

}
