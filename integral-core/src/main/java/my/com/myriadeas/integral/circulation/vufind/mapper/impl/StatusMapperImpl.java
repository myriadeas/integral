package my.com.myriadeas.integral.circulation.vufind.mapper.impl;

import my.com.myriadeas.integral.circulation.service.CallNumberRetrieverIntf;
import my.com.myriadeas.integral.circulation.vufind.Status;
import my.com.myriadeas.integral.circulation.vufind.mapper.StatusMapper;
import my.com.myriadeas.integral.data.jpa.domain.Item;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("statusMapper")
public class StatusMapperImpl implements StatusMapper {

	private static final Logger logger = org.slf4j.LoggerFactory
			.getLogger(StatusMapperImpl.class);

	private CallNumberRetrieverIntf callNumberRetriever;
	
	@Autowired
	public void setCallNumberRetriever(CallNumberRetrieverIntf callNumberRetriever) {
		this.callNumberRetriever = callNumberRetriever;
	}

	@Override
	public Status convertTo(Item item) {
		logger.debug("Entering convertTo(item={})", item);
			
		Status status = new Status();
		status.setAvailability(item.isAvailable());
		status.setCallnumber(callNumberRetriever.getCallNumber(item));
		status.setId(item.getMaterial().getMaterialNo());
		status.setItem_id(item.getItemIdentifier());
		if (item.getLocation() != null){
			status.setLocation(item.getLocation().getDescription());
		}
		status.setReserve(item.isHold() ? "Y" : "N");
		
		if (item.getItemStatus() != null){
			status.setStatus(item.getItemStatus().toString());
		}
		
		logger.debug(
				"Leaving convertTo(). status={}",
				status);

		return status;

	}

	
	
	
}
