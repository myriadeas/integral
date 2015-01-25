package my.com.myriadeas.integral.item.query.rest.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import my.com.myriadeas.integral.assetmanagement.domain.model.Item;

public class ItemServiceRestServiceImpl {

	@GET
	@Path("/welcome")
	@Produces("application/json;charset=UTF-8")
	public Response welcome() {
		return null;
	}

	@GET
	@Path("/getItemListByTitleAuthorIsbn/{title}/{author}/{isbn}")
	@Produces("application/json;charset=UTF-8")
	public List<Item> getItemListByTitleAuthorIsbn(@PathParam("title") String title,
			@PathParam("author") String author, @PathParam("isbn") String isbn) {

		return null;
	}

}
