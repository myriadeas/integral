package my.com.myriadeas.integral.assetmanagement.domain.assembler;

import my.com.myriadeas.integral.assetmanagement.domain.http.ReleaseItemResponse;

public interface ReleaseItemMapper {
	
	public ReleaseItemResponse convertTo(Long id, boolean isSuccessful, String message);

}
