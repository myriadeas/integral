package my.com.myriadeas.integral.assetmanager.domain.assembler;

import my.com.myriadeas.integral.assetmanager.domain.http.CreateItemResponse;

public interface CreateItemMapper {

	public CreateItemResponse convertTo(String itemIdentifier,
			boolean isSuccessful, String message);

}
