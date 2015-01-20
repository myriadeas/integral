package my.com.myriadeas.integral.assetmanagement.query.rest.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
import my.com.myriadeas.integral.assetmanagement.query.domain.ItemView;

public class ItemReadServiceRestServiceImpl {

	@GET
	@Path("/welcome")
	@Produces("application/json;charset=UTF-8")
	public Response welcome() {
		return null;
	}

	@GET
	@Path("/getItemListByTitle/{title}")
	@Produces("application/json;charset=UTF-8")
	public List<ItemView> getItemListByTitle(@PathParam("title") String title) {
		
		return null;
	}
	
}
