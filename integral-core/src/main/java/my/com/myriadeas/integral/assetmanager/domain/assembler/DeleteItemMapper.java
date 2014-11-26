package my.com.myriadeas.integral.assetmanager.domain.assembler;

import my.com.myriadeas.integral.assetmanager.domain.http.DeleteItemResponse;
import my.com.myriadeas.integral.assetmanager.domain.model.Item;

public interface DeleteItemMapper {

	public DeleteItemResponse convertTo(Long id,
			boolean isSuccessful, String message);

}
