package my.com.myriadeas.integral.circulation.mapper.impl;

import my.com.myriadeas.integral.circulation.mapper.ReleaseMapper;
import my.com.myriadeas.integral.circulation.response.ReleaseResponse;
import my.com.myriadeas.integral.data.jpa.domain.Item;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service("releaseMapper")
public class ReleaseMapperImpl implements ReleaseMapper {

	private static final Logger logger = org.slf4j.LoggerFactory
			.getLogger(ReleaseMapperImpl.class);

	@Override
	public ReleaseResponse convertTo(Item item, boolean isSuccessful, String message) {
		logger.debug("Entering convertTo(item={}, isSuccessful={}, message={})",
				new Object[] {item, isSuccessful, message});

		ReleaseResponse releaseResponse = new ReleaseResponse();
		releaseResponse.setSuccessful(isSuccessful);
		releaseResponse.setMessage(message);
		releaseResponse.setItem(item);

		logger.debug("Leaving convertTo(). releaseResponse={}", releaseResponse);

		return releaseResponse;

	}

}
