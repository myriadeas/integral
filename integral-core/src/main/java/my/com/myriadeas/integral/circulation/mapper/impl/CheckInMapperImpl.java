package my.com.myriadeas.integral.circulation.mapper.impl;

import java.util.Date;

import my.com.myriadeas.integral.circulation.facade.AbstractFacadeService;
import my.com.myriadeas.integral.circulation.mapper.CheckInMapper;
import my.com.myriadeas.integral.circulation.response.CheckInResponse;
import my.com.myriadeas.integral.circulation.service.TitleRetrieverIntf;
import my.com.myriadeas.integral.data.jpa.domain.CirculationTransaction;
import my.com.myriadeas.integral.data.jpa.domain.Item;
import my.com.myriadeas.integral.data.jpa.domain.Patron;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service("checkInMapper")
public class CheckInMapperImpl extends AbstractFacadeService implements CheckInMapper {

	private static final Logger logger = org.slf4j.LoggerFactory
			.getLogger(CheckInMapperImpl.class);

	private TitleRetrieverIntf titleRetriever;

	@Autowired
	public void setTitleRetriever(TitleRetrieverIntf titleRetriever) {
		this.titleRetriever = titleRetriever;
	}

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
	public CheckInResponse convertTo(CirculationTransaction circulationTransaction) {
		logger.debug("Entering convertTo(circulationTransaction={})", circulationTransaction);
		Item item = circulationTransaction.getItem();
		Patron patron = circulationTransaction.getPatron();
		Date checkInDateTime = circulationTransaction.getDischargeDateTime();
		Assert.notNull(item);
		Assert.notNull(circulationTransaction);
		Assert.notNull(patron);
		Assert.notNull(checkInDateTime);
		Assert.notNull(item.getBranch());

		// TODO to be removed...
		CheckInResponse checkInResponse = new CheckInResponse();
		checkInResponse.setOk(true);
		checkInResponse.setMagneticMedia(false);
		checkInResponse.setTransactionDate(checkInDateTime);
		checkInResponse.setInstitutionId(item.getBranch().getDescription());
		checkInResponse.setPatronIdentifier(patron.getUsername());
		checkInResponse.setItemIdentifier(item.getItemIdentifier());
		String title = titleRetriever.getTitle(item);
		checkInResponse.setTitleIdentifier(title);
		String message = getMessage("circulationServiceFacade.checkIn.success", new Object[]{title},
				title + " is successfully checked-in.");
		checkInResponse.setScreenMessage(message);
		// to be removed...

		checkInResponse.setItem(item);
		checkInResponse.setCirculationDetail(circulationTransaction);
		checkInResponse.setUser(patron);

		logger.debug(
				"Leaving convertTo(). checkInResponse={}",
				checkInResponse);

		return checkInResponse;

	}
	
	
}
