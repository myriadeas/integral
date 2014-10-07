package my.com.myriadeas.integral.circulation.vufind.mapper.impl;

import java.util.HashMap;
import java.util.Map;

import my.com.myriadeas.integral.circulation.vufind.mapper.RenewItemMapper;
import my.com.myriadeas.integral.circulation.vufind.mapper.VufindUtil;
import my.com.myriadeas.integral.data.jpa.domain.CirculationTransaction;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service("renewItemMapper")
public class RenewItemMapperImpl implements RenewItemMapper {

	private static final Logger logger = org.slf4j.LoggerFactory
			.getLogger(RenewItemMapperImpl.class);
	
	private VufindUtil vufindUtil = new VufindUtil();

	@Override
	public Map<String, Object> convertTo(CirculationTransaction circulationTransaction, boolean isSuccessful, String message) {
		logger.debug("Entering convertTo(cicirc={})", circulationTransaction);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", isSuccessful);
		if (isSuccessful){			
			map.put("new_date", vufindUtil.getDateString(circulationTransaction.getDueDateTime()));
			map.put("new_time", vufindUtil.getTimeString(circulationTransaction.getDueDateTime()));
			
		} else {
			map.put("new_date", "");
			map.put("new_time", "");
			
		}
		map.put("item_id", circulationTransaction.getItem().getItemIdentifier());
		map.put("sysMessage", message);
		
		logger.debug(
				"Leaving convertTo(). Map<String, Object>={}",
				map);

		return map;
		
	}

	/*
	@Override
	public Fines convertTo(Cicirc cicirc, OverdueItem overdueItem) {
		logger.debug("Entering convertTo(overdueItem={})", overdueItem);
		Fines fines = new Fines();
		fines.setAmount(vufindUtil.convertToPenny(overdueItem.getFines()));
		fines.setBalance(vufindUtil.convertToPenny(overdueItem.getBalance()));
		fines.setCheckout(vufindUtil.getDateTimeString(overdueItem.getCheckOutDateTime()));
		fines.setCreatedate(vufindUtil.getDateTimeString(overdueItem.getCreationDateTime()));
		fines.setDuedate(vufindUtil.getDateTimeString(overdueItem.getDueDateTime()));
		fines.setFine("Fines for late return.");
		fines.setId(cicirc.getCi02docno().getCt03matno().getCt02matno());
		
		logger.debug(
				"Leaving convertTo(). fines={}",
				fines);

		return fines;

	}
	*/
	
	
	
	
}
