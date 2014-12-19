package my.com.myriadeas.integral.assetmanagement.domain.assembler;

import my.com.myriadeas.integral.assetmanagement.domain.http.DeleteItemResponse;
import my.com.myriadeas.integral.assetmanagement.domain.model.Item;

public interface DeleteItemMapper {

	public DeleteItemResponse convertTo(Long id,
			boolean isSuccessful, String message);

}
