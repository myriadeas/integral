package my.com.myriadeas.integral.foundation.rest.service.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.multipart.Multipart;

import com.sun.istack.NotNull;

public class IntegralFoundationRestServiceImpl {

	@GET
	@Path("/helloWorld")
	public String helloWorld(@QueryParam("name") String name) {
		return null;
	}

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Path("/upload/image/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response uploadImage(@Multipart("file") @NotNull Object image,
			@PathParam("id") @NotNull String id) {
		return null;
	}

	@GET
	@Path("/get/image/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getImage(@PathParam("id") @NotNull String id) {
		return null;
	}

	@GET
	@Path("/get/image/binary/{id}")
	@Produces("image/png")
	public Response getImageBinary(@PathParam("id") @NotNull String id) {
		return null;
	}

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	@Path("/integral/{table}")
	public Response createRecord(String request,
			@PathParam("table") String table) {
		return null;
	}

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/integral/csv/{table}")
	public Response uploadCsv(@Multipart("file") @NotNull Object csv,
			@PathParam("table") String table) {
		return null;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/integral/process/{table}")
	public Response process(String request, @PathParam("table") String table) {
		return null;
	}

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/integral/csv/lookup/{table}")
	public Response uploadCsvWithLookup(@Multipart("file") @NotNull Object csv,
			@PathParam("table") String table) {
		return null;
	}

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/integral/marc/{library}/{userid}")
	public Response uploadMarc(@Multipart("file") @NotNull Object csv,
			@PathParam("library") String library,
			@PathParam("library") String userid) {
		return null;
	}

}
