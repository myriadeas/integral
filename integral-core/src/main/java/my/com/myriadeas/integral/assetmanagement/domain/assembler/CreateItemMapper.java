package my.com.myriadeas.integral.assetmanagement.domain.assembler;

import my.com.myriadeas.integral.assetmanagement.domain.http.CreateItemResponse;

public interface CreateItemMapper {

	public CreateItemResponse convertTo(Long id,
			boolean isSuccessful, String message);

}
