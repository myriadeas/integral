package my.com.myriadeas.integral.assetmanager.domain.assembler;

import my.com.myriadeas.integral.assetmanager.domain.http.ReleaseItemResponse;
import my.com.myriadeas.integral.assetmanager.domain.model.Item;

import org.slf4j.Logger;


public class ReleaseItemMapperImpl implements ReleaseItemMapper{
	
	private static final Logger logger = org.slf4j.LoggerFactory
			.getLogger(ReleaseItemMapperImpl.class);

	@Override
	public ReleaseItemResponse convertTo(String itemIdentifier, boolean isSuccessful,
			String message) {
			
		ReleaseItemResponse releaseItemResponse = new ReleaseItemResponse();
		releaseItemResponse.setItemIdentifier(itemIdentifier);
		releaseItemResponse.setSuccessful(isSuccessful);
		releaseItemResponse.setMessage(message);
		
		return releaseItemResponse;
	}

}
