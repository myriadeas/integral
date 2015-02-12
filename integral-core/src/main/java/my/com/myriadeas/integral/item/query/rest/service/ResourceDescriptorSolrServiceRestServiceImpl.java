package my.com.myriadeas.integral.item.query.rest.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import my.com.myriadeas.integral.item.query.domain.ItemDisplay;

public class ResourceDescriptorSolrServiceRestServiceImpl {

	@GET
	@Path("/welcome")
	@Produces("application/json;charset=UTF-8")
	public Response welcome() {
		return null;
	}
	
	@GET
	@Path("/getResourceDescriptorSolrById")
	@Produces("application/json;charset=UTF-8")
	public Response getResourceDescriptorSolrById(@QueryParam("id")String id) {

		return null;
	}
	
	@GET
	@Path("/getResourceDescriptorSolrListByTitle/{title}")
	@Produces("application/json;charset=UTF-8")
	public Response getResourceDescriptorSolrListByTitle(@PathParam("title") String title) {

		return null;
	}

	@GET
	@Path("/getResourceDescriptorSolrListByAuthor/{author}")
	@Produces("application/json;charset=UTF-8")
	public Response getResourceDescriptorSolrListByAuthor(
			@PathParam("author") String author) {

		return null;
	}

	@GET
	@Path("/getResourceDescriptorSolrListByIsbn/{isbn}")
	@Produces("application/json;charset=UTF-8")
	public Response getResourceDescriptorSolrListByIsbn(@PathParam("isbn") String isbn) {

		return null;
	}

}
