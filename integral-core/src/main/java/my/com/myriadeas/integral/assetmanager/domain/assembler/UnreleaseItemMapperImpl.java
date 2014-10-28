package my.com.myriadeas.integral.assetmanager.domain.assembler;

import my.com.myriadeas.integral.assetmanager.domain.http.UnreleaseItemResponse;

public class UnreleaseItemMapperImpl implements UnreleaseItemMapper {

	@Override
	public UnreleaseItemResponse convertTo(String itemIdentifier, boolean isSuccessful,
			String message) {

		UnreleaseItemResponse unreleaseItemResponse = new UnreleaseItemResponse();
		unreleaseItemResponse.setItemIdentifier(itemIdentifier);
		unreleaseItemResponse.setSuccessful(isSuccessful);
		unreleaseItemResponse.setMessage(message);

		return unreleaseItemResponse;
	}

}
