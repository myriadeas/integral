package my.com.myriadeas.integral.assetmanager.interfaces.facade;

import my.com.myriadeas.integral.assetmanager.domain.http.CreateItemRequest;
import my.com.myriadeas.integral.assetmanager.domain.http.CreateItemResponse;
import my.com.myriadeas.integral.assetmanager.domain.http.DeleteItemRequest;
import my.com.myriadeas.integral.assetmanager.domain.http.DeleteItemResponse;
import my.com.myriadeas.integral.assetmanager.domain.http.ReleaseItemRequest;
import my.com.myriadeas.integral.assetmanager.domain.http.ReleaseItemResponse;
import my.com.myriadeas.integral.assetmanager.domain.http.UnreleaseItemRequest;
import my.com.myriadeas.integral.assetmanager.domain.http.UnreleaseItemResponse;

public interface AssetManagerFacade {
	
	public CreateItemResponse createItem(CreateItemRequest createItemRequest);
	
	public ReleaseItemResponse releaseItem(ReleaseItemRequest releaseItemRequest);
	
	public UnreleaseItemResponse unreleaseItem(UnreleaseItemRequest unreleaseItemRequest);
	
	public DeleteItemResponse deleteItem(DeleteItemRequest deleteItemRequest);

}
