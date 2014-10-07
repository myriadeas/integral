package my.com.myriadeas.integral.beanvalidation.rest.service.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import my.com.myriadeas.integral.beanvalidation.UniquenessConstraintRequest;

import com.sun.istack.NotNull;

//@Path("/beanvalidation/")
public class BeanValidationRestServiceImpl {

	@GET
	@Path("/constrainedProperties/{domain}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDomainConstrainedProperties(
			@PathParam("domain") @NotNull String domain) {
		return null;
	}

	@GET
	@Path("/schema/{domain}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDomainSchema(@PathParam("domain") @NotNull String domain) {
		return null;
	}

	@POST
	@Path("/uniqueness")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response validateUniqueness(UniquenessConstraintRequest request) {
		return null;
	}

}
