package my.com.myriadeas.integral.cataloguing2.rest.service.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

public class CataloguingServiceRestServiceImpl {

	@GET
	@Path("/welcome")
	@Produces("application/json;charset=UTF-8")
	public Response welcome() {
		return null;
	}

	@POST
	@Path("/marc")
	@Consumes("application/json")
	@Produces("application/json;charset=UTF-8")
	public Response create(String request) {
		return null;
	}

	@PUT
	@Path("/marc/{resourceDescriptorId}")
	@Consumes("application/json")
	@Produces("application/json;charset=UTF-8")
	public Response update(String request,
			@PathParam("resourceDescriptorId") String resourceDescriptorId) {
		return null;
	}

	@PUT
	@Path("/marc/finalize/{id}")
	@Consumes("application/json")
	@Produces("application/json;charset=UTF-8")
	public Response finalize(String request, @PathParam("id") Long id) {
		return null;
	}
	
	@PUT
	@Path("/marc/revise/{id}")
	@Consumes("application/json")
	@Produces("application/json;charset=UTF-8")
	public Response revise(String request, @PathParam("id") Long id) {
		return null;
	}

	@POST
	@Path("/marc/verify")
	@Consumes("application/json")
	@Produces("application/json;charset=UTF-8")
	public Response verify(String request) {
		return null;
	}

	// Template
	@GET
	@Path("/template/{library}/{userid}/list")
	@Produces("application/json;charset=UTF-8")
	public Response listTemplates(@PathParam("library") String library,
			@PathParam("userid") String userid) {
		return null;
	}

	@POST
	@Path("/template/{library}/{userid}/{templateid}")
	@Consumes("application/json")
	@Produces("application/json;charset=UTF-8")
	public Response createTemplate(String request,
			@PathParam("library") String library,
			@PathParam("userid") String userid,
			@PathParam("templateid") String templateid) {
		return null;
	}

	@GET
	@Path("/template/{library}/{userid}/{templateid}")
	@Produces("application/json;charset=UTF-8")
	public Response retrieveTemplate(@PathParam("library") String library,
			@PathParam("userid") String userid,
			@PathParam("templateid") String templateid) {
		return null;
	}

	@PUT
	@Path("/template/{library}/{userid}/{templateid}")
	@Consumes("application/json")
	@Produces("application/json;charset=UTF-8")
	public Response updateTemplate(String request,
			@PathParam("library") String library,
			@PathParam("userid") String userid,
			@PathParam("templateid") String templateid) {
		return null;
	}

	@DELETE
	@Path("/template/{library}/{userid}/{templateid}")
	@Produces("application/json;charset=UTF-8")
	public Response deleteTemplate(@PathParam("library") String library,
			@PathParam("userid") String userid,
			@PathParam("templateid") String templateid) {
		return null;
	}

}
