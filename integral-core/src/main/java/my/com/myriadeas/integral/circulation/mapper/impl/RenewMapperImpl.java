package my.com.myriadeas.integral.circulation.mapper.impl;

import java.util.Date;

import my.com.myriadeas.integral.circulation.facade.AbstractFacadeService;
import my.com.myriadeas.integral.circulation.mapper.RenewMapper;
import my.com.myriadeas.integral.circulation.response.RenewResponse;
import my.com.myriadeas.integral.circulation.service.TitleRetrieverIntf;
import my.com.myriadeas.integral.data.jpa.domain.CirculationTransaction;
import my.com.myriadeas.integral.data.jpa.domain.Item;
import my.com.myriadeas.integral.data.jpa.domain.Patron;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service("renewMapper")
public class RenewMapperImpl extends AbstractFacadeService implements RenewMapper {

	private static final Logger logger = org.slf4j.LoggerFactory
			.getLogger(RenewMapperImpl.class);

	private TitleRetrieverIntf titleRetriever;

	@Autowired
	public void setTitleRetriever(TitleRetrieverIntf titleRetriever) {
		this.titleRetriever = titleRetriever;
	}

	@Override
	public RenewResponse convertTo(CirculationTransaction circulationTransaction) {
		// TODO Auto-generated method stub
		return convertTo(circulationTransaction.getItem(),
				circulationTransaction, circulationTransaction.getPatron(),
				circulationTransaction.getChargeDateTime());
	}

	protected RenewResponse convertTo(Item item,
			CirculationTransaction circulationTransaction, Patron patron,
			Date renewDateTime) {
		logger.debug(
				"Entering convertTo(item={}, circulationTransaction={}, patron={}, "
						+ "checkOutDateTime={})", new Object[] { item,
						circulationTransaction, patron, renewDateTime });

		Assert.notNull(item.getBranch());
		RenewResponse renewResponse = new RenewResponse();

		// TODO tobe removed...
		renewResponse.setOk(true);
		renewResponse.setRenewalOk(true);
		renewResponse.setMagneticMedia(false);
		renewResponse.setDesensitize(false);
		renewResponse.setTransactionDate(renewDateTime);
		renewResponse.setInstitutionId(item.getBranch().getDescription());
		renewResponse.setPatronIdentifier(patron.getUsername());
		renewResponse.setItemIdentifier(item.getItemIdentifier());
		// use to store circulationTransaction id to be used by web to make
		// getRenewItemInformation call
		renewResponse.setItemProperties(circulationTransaction.getId()
				.toString());
		String title = item.getTitle();
		renewResponse.setTitleIdentifier(title);
		renewResponse.setDueDate(circulationTransaction.getDueDateTime());
		String message = getMessage("circulationServiceFacade.renew.success", new Object[]{title},
				title + " is successfully renewed.");
		renewResponse.setScreenMessage(message);
		// to be removed...

		renewResponse.setItem(item);
		renewResponse.setCirculationDetail(circulationTransaction);
		renewResponse.setParentCirculationDetail(circulationTransaction
				.getCirculationTransactionParentForRenew());
		renewResponse.setUser(patron);
		renewResponse.setRenewDateTime(renewDateTime);

		logger.debug("Leaving convertTo(). renewResponse={}", renewResponse);
		return renewResponse;

	}

}
