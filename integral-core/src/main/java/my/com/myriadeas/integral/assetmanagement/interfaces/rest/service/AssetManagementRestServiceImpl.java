package my.com.myriadeas.integral.assetmanagement.interfaces.rest.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

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
import my.com.myriadeas.integral.assetmanagement.interfaces.facade.ItemFacade;

public class AssetManagementRestServiceImpl implements ItemFacade {

	@GET
	@Path("/welcome")
	@Produces("application/json;charset=UTF-8")
	public Response welcome() {
		return null;
	}

	@POST
	@Path("/receive")
	@Consumes("application/json")
	@Produces("application/json")
	public ReceiveItemResponse receiveItem(ReceiveItemRequest receiveItemRequest) {
		
		return null;
	}
	
	@POST
	@Path("/create")
	@Consumes("application/json")
	@Produces("application/json")
	public CreateItemResponse createItem(CreateItemRequest createItemRequest) {

		return null;
	}

	@POST
	@Path("/release")
	@Consumes("application/json")
	@Produces("application/json")
	public ReleaseItemResponse releaseItem(ReleaseItemRequest releaseItemRequest) {

		return null;
	}

	@POST
	@Path("/unrelease")
	@Consumes("application/json")
	@Produces("application/json")
	public UnreleaseItemResponse unreleaseItem(
			UnreleaseItemRequest unreleaseItemRequest) {

		return null;
	}

	@POST
	@Path("/delete")
	@Consumes("application/json")
	@Produces("application/json")
	public DeleteItemResponse deleteItem(DeleteItemRequest deleteItemRequest) {

		return null;
	}



}
