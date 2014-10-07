package my.com.myriadeas.integral.circulation.vufind.mapper.impl;

import java.util.HashMap;
import java.util.Map;

import my.com.myriadeas.integral.circulation.facade.AbstractFacadeService;
import my.com.myriadeas.integral.circulation.vufind.mapper.CancelHoldMapper;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service("cancelHoldMapper")
public class CancelHoldMapperImpl extends AbstractFacadeService implements CancelHoldMapper {

	private static final Logger logger = org.slf4j.LoggerFactory
			.getLogger(CancelHoldMapperImpl.class);
	
	
	@Override
	public Map<String, Object> convertTo(boolean isSuccessful, String message) {
		logger.debug("Entering convertTo(isSuccessful={}, message={})", isSuccessful, message);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", isSuccessful);
		if (isSuccessful){			
			map.put("status", "hold_cancel_success");			
		} else {
			map.put("status", "hold_cancel_fail");
		}
		map.put("sysMessage", message);
		
		logger.debug(
				"Leaving convertTo(). Map<String, Object>={}",
				map);

		return map;
		
	}

	
	
}
