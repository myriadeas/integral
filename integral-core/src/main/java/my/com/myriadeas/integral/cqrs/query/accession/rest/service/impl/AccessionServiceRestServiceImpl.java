package my.com.myriadeas.integral.cqrs.query.accession.rest.service.impl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
	public Response listAccessions(@PathParam("rdId") String rdId) {
		return null;
	}

}
