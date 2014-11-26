package my.com.myriadeas.integral.assetmanager.domain.assembler;

import my.com.myriadeas.integral.assetmanager.domain.http.CreateItemResponse;

public interface CreateItemMapper {

	public CreateItemResponse convertTo(Long id,
			boolean isSuccessful, String message);

}
