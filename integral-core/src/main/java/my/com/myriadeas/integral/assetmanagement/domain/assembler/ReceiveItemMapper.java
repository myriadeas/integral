package my.com.myriadeas.integral.assetmanagement.domain.assembler;

import my.com.myriadeas.integral.assetmanagement.domain.http.ReceiveItemResponse;

public interface ReceiveItemMapper {

	public ReceiveItemResponse convertTo(String title,
			boolean isSuccessful, String message);

}
