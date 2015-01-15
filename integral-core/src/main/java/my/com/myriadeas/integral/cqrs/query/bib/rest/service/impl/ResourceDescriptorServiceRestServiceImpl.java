package my.com.myriadeas.integral.cqrs.query.bib.rest.service.impl;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

public class ResourceDescriptorServiceRestServiceImpl {

	@GET
	@Path("/welcome")
	@Produces("application/json;charset=UTF-8")
	public Response welcome() {
		return null;
	}

	@GET
	@Path("/query/{rdId}")
	@Produces("application/json;charset=UTF-8")
	public Response getBibDetailsByRDId(@PathParam("rdId") String rdId) {
		return null;
	}

	@GET
	@Path("/query")
	@Produces("application/json;charset=UTF-8")
	public Response getBibDetailsByRDIds(@QueryParam("rdId") List<String> rdId) {
		return null;
	}
	
	@GET
	@Path("/query/id/{id}")
	@Produces("application/json;charset=UTF-8")
	public Response getBibDetailsById(@PathParam("id") Long id) {
		return null;
	}

	@GET
	@Path("/query")
	@Produces("application/json;charset=UTF-8")
	public Response getBibDetailsByIds(@QueryParam("id") List<Long> id) {
		return null;
	}

}
