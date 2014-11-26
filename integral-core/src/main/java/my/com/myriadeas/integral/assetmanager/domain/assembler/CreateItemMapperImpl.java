package my.com.myriadeas.integral.assetmanager.domain.assembler;

import org.springframework.stereotype.Service;

import my.com.myriadeas.integral.assetmanager.domain.http.CreateItemResponse;

@Service("createItemMapper")
public class CreateItemMapperImpl implements CreateItemMapper {

	public CreateItemResponse convertTo(Long id, boolean isSuccessful,
			String message) {

		CreateItemResponse createItemResponse = new CreateItemResponse();
		createItemResponse.setId(id);
		createItemResponse.setMessage(message);
		createItemResponse.setSuccessful(isSuccessful);

		return createItemResponse;
	}


}
