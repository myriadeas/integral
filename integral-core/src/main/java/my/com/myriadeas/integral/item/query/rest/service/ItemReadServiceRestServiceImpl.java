package my.com.myriadeas.integral.item.query.rest.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import my.com.myriadeas.integral.item.query.domain.ItemView;

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
