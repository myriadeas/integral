package my.com.myriadeas.integral.cataloguing2.rest.service.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import my.com.myriadeas.integral.cataloguing2.interfaces.facade.request.CreateResourceDescriptorRequest;
import my.com.myriadeas.integral.cataloguing2.interfaces.facade.request.FinalizeResourceDescriptorRequest;

import org.marc4j.marc.Record;

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
	public Response create(
			CreateResourceDescriptorRequest createResourceDescriptorRequest) {
		return null;
	}

	@POST
	@Path("/marc")
	@Consumes("application/json")
	@Produces("application/json;charset=UTF-8")
	public Response create(Record record) {
		return null;
	}

	@PUT
	@Path("/marc")
	@Consumes("application/json")
	@Produces("application/json;charset=UTF-8")
	public Response finalize(
			FinalizeResourceDescriptorRequest finalizeResourceDescriptorRequest) {
		return null;
	}

	@PUT
	@Path("/marc")
	@Consumes("application/json")
	@Produces("application/json;charset=UTF-8")
	public Response finalizeByRecord(Record record,
			@PathParam("resourceDescriptorId") String resourceDescriptorId) {
		return null;
	}

	@POST
	@Path("/marc/verify")
	@Consumes("application/json")
	@Produces("application/json;charset=UTF-8")
	public Response verify(Record record) {
		return null;
	}

}
