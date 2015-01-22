package my.com.myriadeas.integral.cqrs.query.accession.rest.service.impl;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

public class AccessionServiceRestServiceImpl {

	@GET
	@Path("/welcome")
	@Produces("application/json;charset=UTF-8")
	public Response welcome() {
		return null;
	}

	@GET
	@Path("/query/{rdId}")
	@Produces("application/json;charset=UTF-8")
	public Response listAccessionsByRDId(@PathParam("rdId") String rdId) {
		return null;
	}

	@GET
	@Path("/query")
	@Produces("application/json;charset=UTF-8")
	public Response listAccessionsByRDIds(@QueryParam("rdId") List<String> rdId) {
		return null;
	}

	@GET
	@Path("/{bibliographicId}/holding")
	@Produces("application/json")
	public Response getHolding(
			@PathParam("bibliographicId") String bibliographicId) {
		return null;
	}

	@POST
	@Path("/getStatuses")
	@Consumes("application/json")
	@Produces("application/json")
	public Response getStatuses(List<String> bibliographicIds) {
		return null;
	}

}
