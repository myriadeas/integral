package my.com.myriadeas.integral.assetmanager.domain.assembler;

import org.springframework.stereotype.Service;

import my.com.myriadeas.integral.assetmanager.domain.http.CreateItemResponse;

@Service("createItemMapper")
public class CreateItemMapperImpl implements CreateItemMapper {

	public CreateItemResponse convertTo(String itemIdentifier, boolean isSuccessful,
			String message) {

		CreateItemResponse createItemResponse = new CreateItemResponse();
		createItemResponse.setItemIdentifier(itemIdentifier);
		createItemResponse.setMessage(message);
		createItemResponse.setSuccessful(isSuccessful);

		return createItemResponse;
	}


}
