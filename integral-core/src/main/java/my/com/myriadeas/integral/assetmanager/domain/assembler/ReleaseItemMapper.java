package my.com.myriadeas.integral.assetmanager.domain.assembler;

import my.com.myriadeas.integral.assetmanager.domain.http.ReleaseItemResponse;

public interface ReleaseItemMapper {
	
	public ReleaseItemResponse convertTo(Long id, boolean isSuccessful, String message);

}
