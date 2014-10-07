package my.com.myriadeas.integral.circulation.mapper.impl;

import java.util.Date;

import my.com.myriadeas.integral.circulation.facade.AbstractFacadeService;
import my.com.myriadeas.integral.circulation.mapper.CheckOutMapper;
import my.com.myriadeas.integral.circulation.response.CheckOutResponse;
import my.com.myriadeas.integral.circulation.service.TitleRetrieverIntf;
import my.com.myriadeas.integral.data.jpa.domain.CirculationTransaction;
import my.com.myriadeas.integral.data.jpa.domain.Item;
import my.com.myriadeas.integral.data.jpa.domain.Patron;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service("checkOutMapper")
public class CheckOutMapperImpl extends AbstractFacadeService implements CheckOutMapper {

	private static final Logger logger = org.slf4j.LoggerFactory
			.getLogger(CheckOutMapperImpl.class);

	private TitleRetrieverIntf titleRetriever;

	@Autowired
	public void setTitleRetriever(TitleRetrieverIntf titleRetriever) {
		this.titleRetriever = titleRetriever;
	}

	@Override
	public CheckOutResponse convertTo(CirculationTransaction circulationTransaction) {
		// TODO Auto-generated method stub
		return convertTo(circulationTransaction.getItem(), circulationTransaction, circulationTransaction.getPatron(), circulationTransaction.getChargeDateTime());
	}

	/* (non-Javadoc)
	 * @see my.com.myriadeas.integral.circulation.facade.impl.CHeck#constructCheckOutResponse(my.com.myriadeas.integral.data.jpa.domain.Ctdocm, my.com.myriadeas.integral.data.jpa.domain.Cicirc, my.com.myriadeas.integral.data.jpa.domain.Glpatr, java.util.Date)
	 */
	protected CheckOutResponse convertTo(Item item,
			CirculationTransaction circulationTransaction, Patron patron, Date checkOutDateTime) {
		logger.debug(
				"Entering convertTo(item={}, circulationTransaction={}, patron={}, "
						+ "checkOutDateTime={})", new Object[] { item,
						circulationTransaction, patron, checkOutDateTime });
		Assert.notNull(item);
		Assert.notNull(circulationTransaction);
		Assert.notNull(patron);
		Assert.notNull(checkOutDateTime);
		Assert.notNull(item.getBranch());

		// TODO to be removed...
		CheckOutResponse checkOutResponse = new CheckOutResponse();
		checkOutResponse.setOk(true);
		checkOutResponse.setRenewalOk(true);
		checkOutResponse.setMagneticMedia(false);
		checkOutResponse.setDesensitize(false);
		checkOutResponse.setTransactionDate(checkOutDateTime);
		checkOutResponse.setInstitutionId(item.getBranch().getDescription());
		checkOutResponse.setPatronIdentifier(patron.getUsername());
		checkOutResponse.setItemIdentifier(item.getItemIdentifier());
		String title = titleRetriever.getTitle(item);
		checkOutResponse.setTitleIdentifier(title);
		String message = getMessage("circulationServiceFacade.checkOut.success", new Object[]{title},
				title + " is successfully checked-out.");
		checkOutResponse.setScreenMessage(message);
		checkOutResponse.setDueDate(circulationTransaction.getDueDateTime());
		// to be removed...

		checkOutResponse.setItem(item);
		checkOutResponse.setCirculationDetail(circulationTransaction);
		checkOutResponse.setUser(patron);
		checkOutResponse.setCheckOutDateTime(checkOutDateTime);

		logger.debug(
				"Leaving convertTo(). checkOutResponse={}",
				checkOutResponse);

		return checkOutResponse;

	}


}
