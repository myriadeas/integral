package my.com.myriadeas.integral.circulation.vufind.mapper.impl;

import my.com.myriadeas.integral.circulation.facade.AbstractFacadeService;
import my.com.myriadeas.integral.circulation.vufind.Fines;
import my.com.myriadeas.integral.circulation.vufind.mapper.FinesMapper;
import my.com.myriadeas.integral.circulation.vufind.mapper.VufindUtil;
import my.com.myriadeas.integral.data.jpa.domain.CirculationTransaction;
import my.com.myriadeas.integral.data.jpa.domain.FinesTransaction;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service("finesMapper")
public class FinesMapperImpl extends AbstractFacadeService implements FinesMapper {

	private static final Logger logger = org.slf4j.LoggerFactory
			.getLogger(FinesMapperImpl.class);
	
	private VufindUtil vufindUtil = new VufindUtil();;

	@Override
	public Fines convertTo(CirculationTransaction circulationTransaction, FinesTransaction retrxn) {
		logger.debug("Entering convertTo(circulationTransaction={}, retrxn={})", circulationTransaction, retrxn);
		Fines fines = new Fines();
		fines.setAmount(vufindUtil.convertToPenny(retrxn.getAmount()));
		//TODO balance calculation
		fines.setBalance(vufindUtil.convertToPenny(retrxn.getAmount()));
		fines.setCheckout(vufindUtil.getDateTimeString(circulationTransaction.getChargeDateTime()));
		fines.setCreatedate(vufindUtil.getDateTimeString(retrxn.getDate()));
		fines.setDuedate(vufindUtil.getDateTimeString(circulationTransaction.getDueDateTime()));		
		fines.setFine(getMessage("vufindServiceFacade.fines.finesTypeDescription", "Fines for late return."));
		fines.setId(circulationTransaction.getItem().getMaterial().getMaterialNo());
		
		logger.debug(
				"Leaving convertTo(). fines={}",
				fines);

		return fines;
		
	}

	
	
	
}
