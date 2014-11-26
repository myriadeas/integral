package my.com.myriadeas.integral.assetmanager.domain.assembler;

import org.springframework.stereotype.Service;

import my.com.myriadeas.integral.assetmanager.domain.http.UnreleaseItemResponse;

@Service("unreleaseItemMapper")
public class UnreleaseItemMapperImpl implements UnreleaseItemMapper {

	@Override
	public UnreleaseItemResponse convertTo(Long id, boolean isSuccessful,
			String message) {

		UnreleaseItemResponse unreleaseItemResponse = new UnreleaseItemResponse();
		unreleaseItemResponse.setId(id);
		unreleaseItemResponse.setSuccessful(isSuccessful);
		unreleaseItemResponse.setMessage(message);

		return unreleaseItemResponse;
	}

}
