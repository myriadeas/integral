package my.com.myriadeas.integral.internalization.rest.service.impl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class IntegralInternalizationRestServiceImpl {

	@GET
	@Path("/{resourceName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMessages(@PathParam("resourceName") String resourceName) {
		return null;
	}

}
