package my.com.myriadeas.integral.circulation.vufind.mapper.impl;

import my.com.myriadeas.integral.circulation.vufind.Profile;
import my.com.myriadeas.integral.circulation.vufind.mapper.ProfileMapper;
import my.com.myriadeas.integral.data.jpa.domain.Patron;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service("profileMapper")
public class ProfileMapperImpl implements ProfileMapper {

	private static final Logger logger = org.slf4j.LoggerFactory
			.getLogger(ProfileMapperImpl.class);

	
	@Override
	public Profile convertTo(Patron patron) {
		logger.debug("Entering convertTo(patron={})", patron);
		Profile profile = new Profile();
		profile.setAddress1(patron.getHomeAddress().getAddress1());
		profile.setAddress2(patron.getHomeAddress().getAddress2());
		if (patron.getHomeAddress().getTown() != null){
			profile.setCity(patron.getHomeAddress().getTown().getDescription());
		} 
		profile.setCountry("Malaysia");
		profile.setFirstname(patron.getFirstname());
		profile.setLastname(patron.getLastname());
		profile.setPhone(patron.getHomePhone());
		profile.setZip(patron.getHomeAddress().getPostcode());
		
		logger.debug(
				"Leaving convertTo(). profile={}",
				profile);

		return profile;

	}

	
	
	
}
