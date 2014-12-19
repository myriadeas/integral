package my.com.myriadeas.integral.assetmanagement.interfaces.facade;

import my.com.myriadeas.integral.assetmanagement.domain.http.CreateItemRequest;
import my.com.myriadeas.integral.assetmanagement.domain.http.CreateItemResponse;
import my.com.myriadeas.integral.assetmanagement.domain.http.DeleteItemRequest;
import my.com.myriadeas.integral.assetmanagement.domain.http.DeleteItemResponse;
import my.com.myriadeas.integral.assetmanagement.domain.http.ReceiveItemRequest;
import my.com.myriadeas.integral.assetmanagement.domain.http.ReceiveItemResponse;
import my.com.myriadeas.integral.assetmanagement.domain.http.ReleaseItemRequest;
import my.com.myriadeas.integral.assetmanagement.domain.http.ReleaseItemResponse;
import my.com.myriadeas.integral.assetmanagement.domain.http.UnreleaseItemRequest;
import my.com.myriadeas.integral.assetmanagement.domain.http.UnreleaseItemResponse;

public interface ItemFacade {
	
	public ReceiveItemResponse receiveItem(ReceiveItemRequest receiveItemRequest);
	
	public CreateItemResponse createItem(CreateItemRequest createItemRequest);
	
	public ReleaseItemResponse releaseItem(ReleaseItemRequest releaseItemRequest);
	
	public UnreleaseItemResponse unreleaseItem(UnreleaseItemRequest unreleaseItemRequest);
	
	public DeleteItemResponse deleteItem(DeleteItemRequest deleteItemRequest);

}
