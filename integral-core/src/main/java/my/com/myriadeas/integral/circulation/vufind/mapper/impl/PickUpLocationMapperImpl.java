package my.com.myriadeas.integral.circulation.vufind.mapper.impl;

import my.com.myriadeas.integral.circulation.vufind.PickUpLocation;
import my.com.myriadeas.integral.circulation.vufind.mapper.PickUpLocationMapper;
import my.com.myriadeas.integral.data.jpa.domain.Branch;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service("pickUpLocationMapper")
public class PickUpLocationMapperImpl implements PickUpLocationMapper {

	private static final Logger logger = org.slf4j.LoggerFactory
			.getLogger(PickUpLocationMapperImpl.class);
	
	
	@Override
	public PickUpLocation convertTo(Branch branch) {
		logger.debug("Entering convertTo(branch={})", branch);
		PickUpLocation pickUpLocation = new PickUpLocation();
		
		pickUpLocation.setLocationId(branch.getCode());
		pickUpLocation.setLocationDisplay(branch.getDescription());
		
		logger.debug(
				"Leaving convertTo(). PickUpLocation={}",
				pickUpLocation);

		return pickUpLocation;
		
	}

	
	
	
}
