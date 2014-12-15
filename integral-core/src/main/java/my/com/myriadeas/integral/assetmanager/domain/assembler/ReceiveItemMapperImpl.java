package my.com.myriadeas.integral.assetmanager.domain.assembler;

import my.com.myriadeas.integral.assetmanager.domain.http.ReceiveItemResponse;

import org.springframework.stereotype.Service;

@Service("receiveItemMapper")
public class ReceiveItemMapperImpl implements ReceiveItemMapper {

	public ReceiveItemResponse convertTo(String title, boolean isSuccessful,
			String message) {

		ReceiveItemResponse receiveItemResponse = new ReceiveItemResponse();
		receiveItemResponse.setTitle(title);
		receiveItemResponse.setMessage(message);
		receiveItemResponse.setSuccessful(isSuccessful);

		return receiveItemResponse;
	}


}
