package my.com.myriadeas.integral.assetmanager.interfaces.rest.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import my.com.myriadeas.integral.assetmanager.domain.http.DeleteItemRequest;
import my.com.myriadeas.integral.assetmanager.domain.http.DeleteItemResponse;
import my.com.myriadeas.integral.assetmanager.domain.http.ReleaseItemRequest;
import my.com.myriadeas.integral.assetmanager.domain.http.ReleaseItemResponse;
import my.com.myriadeas.integral.assetmanager.domain.http.UnreleaseItemRequest;
import my.com.myriadeas.integral.assetmanager.domain.http.UnreleaseItemResponse;
import my.com.myriadeas.integral.assetmanager.interfaces.facade.AssetManagerFacade;

public class AssetManagementRestServiceImpl implements AssetManagerFacade {
	
	@POST
	@Path("/release")
	@Consumes("application/json")
	@Produces("application/json")
	public ReleaseItemResponse releaseItem(ReleaseItemRequest releaseItemRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@POST
	@Path("/unrelease")
	@Consumes("application/json")
	@Produces("application/json")
	public UnreleaseItemResponse unreleaseItem(
			UnreleaseItemRequest unreleaseItemRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@POST
	@Path("/delete")
	@Consumes("application/json")
	@Produces("application/json")
	public DeleteItemResponse unreleaseItem(DeleteItemRequest deleteItemRequest) {
		// TODO Auto-generated method stub
		return null;
	}

}
