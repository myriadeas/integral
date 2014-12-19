package my.com.myriadeas.integral.assetmanagement.domain.assembler;

import my.com.myriadeas.integral.assetmanagement.domain.http.UnreleaseItemResponse;

public interface UnreleaseItemMapper {

	public UnreleaseItemResponse convertTo(Long id, boolean isSuccessful,
			String message);

}
