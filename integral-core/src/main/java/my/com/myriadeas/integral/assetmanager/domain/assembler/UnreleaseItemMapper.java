package my.com.myriadeas.integral.assetmanager.domain.assembler;

import my.com.myriadeas.integral.assetmanager.domain.http.UnreleaseItemResponse;

public interface UnreleaseItemMapper {

	public UnreleaseItemResponse convertTo(Long id, boolean isSuccessful,
			String message);

}
