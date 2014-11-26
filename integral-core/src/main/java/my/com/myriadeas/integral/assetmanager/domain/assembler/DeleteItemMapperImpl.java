package my.com.myriadeas.integral.assetmanager.domain.assembler;

import org.springframework.stereotype.Service;

import my.com.myriadeas.integral.assetmanager.domain.http.DeleteItemResponse;

@Service("deleteItemMapper")
public class DeleteItemMapperImpl implements DeleteItemMapper {

	@Override
	public DeleteItemResponse convertTo(Long id, boolean isSuccessful,
			String message) {

		DeleteItemResponse deleteItemResponse = new DeleteItemResponse();
		deleteItemResponse.setId(id);
		deleteItemResponse.setSuccessful(isSuccessful);
		deleteItemResponse.setMessage(message);

		return deleteItemResponse;
	}


}
