package my.com.myriadeas.integral.cataloguing.rest.service.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import my.com.myriadeas.integral.cataloguing.exception.IndexFailureException;

import org.apache.cxf.jaxrs.ext.multipart.Multipart;

import com.sun.istack.NotNull;

//@Path("/cataloguing")
public class CataloguingServiceRestServiceImpl {

	@GET
	@Path("/welcome")
	@Produces("application/json;charset=UTF-8")
	public Response welcome() {
		return null;
	}

	@GET
	@Path("/marc/{library}/{userid}/list")
	@Produces("application/json;charset=UTF-8")
	public Response list(@PathParam("library") String library,
			@PathParam("userid") String userid) {
		return null;
	}

	@POST
	@Path("/marc/{library}/{userid}")
	@Consumes("application/json")
	@Produces("application/json;charset=UTF-8")
	public Response create(String request,
			@PathParam("library") String library,
			@PathParam("userid") String userid) throws IndexFailureException {
		return null;
	}

	@GET
	@Path("/marc/{library}/{userid}/{marcid}")
	@Produces("application/json;charset=UTF-8")
	public Response retrieve(@PathParam("library") String library,
			@PathParam("userid") String userid,
			@PathParam("marcid") String marcid) {
		return null;
	}

	@PUT
	@Path("/marc/{library}/{userid}/{marcid}")
	@Consumes("application/json")
	@Produces("application/json;charset=UTF-8")
	public Response update(String request,
			@PathParam("library") String library,
			@PathParam("userid") String userid,
			@PathParam("marcid") String marcid) throws IndexFailureException {
		return null;
	}

	@DELETE
	@Path("/marc/{library}/{userid}/{marcid}")
	@Produces("application/json;charset=UTF-8")
	public Response delete(@PathParam("library") String library,
			@PathParam("userid") String userid,
			@PathParam("marcid") String marcid) throws IndexFailureException {
		return null;
	}

	@POST
	@Path("/marc/verify/{library}/{userid}")
	@Consumes("application/json")
	@Produces("application/json;charset=UTF-8")
	public Response verify(String request,
			@PathParam("library") String library,
			@PathParam("userid") String userid) {
		return null;
	}

	@POST
	@Path("/marc/convert/{library}/{userid}")
	@Consumes("application/json")
	@Produces("application/json;charset=UTF-8")
	public Response convert(String request,
			@PathParam("library") String library,
			@PathParam("userid") String userid) {
		return null;
	}

	@POST
	@Path("/marc/duplicate/{library}/{userid}")
	@Consumes("application/json")
	@Produces("application/json;charset=UTF-8")
	public Response checkDuplicate(String request,
			@PathParam("library") String library,
			@PathParam("userid") String userid, @QueryParam("tag") String tag) {
		return null;
	}

	@GET
	@Path("/marc/get/solr/{id}")
	@Produces("application/json;charset=UTF-8")
	public Response getFromSolr(@PathParam("id") String id) {
		return null;
	}

	@GET
	@Path("/marc/get/solr/title/{id}")
	@Produces("application/json;charset=UTF-8")
	public Response getTitleFromSolr(@PathParam("id") String id) {
		return null;
	}

	@GET
	@Path("/marc/get/solr/bib/{id}/{tag}")
	@Produces("application/json;charset=UTF-8")
	public Response getBibFromSolr(@PathParam("id") String id,
			@PathParam("tag") String tag) {
		return null;
	}

	@GET
	@Path("/marc/query/solr/{query}")
	@Produces("application/json;charset=UTF-8")
	public Response querySolr(@PathParam("query") String query) {
		return null;
	}

	@GET
	@Path("/marc/query/solrOri")
	@Produces("application/json;charset=UTF-8")
	public Response querySolrOri(@QueryParam("q") String query) {
		return null;
	}
	
	@GET
	@Path("/marc/query/solrGetMaterials")
	@Produces("application/json;charset=UTF-8")
	public Response querySolrGetMaterials(@QueryParam("q") String query) {
		return null;
	}

	@GET
	@Path("/marc/get/material/title/{id}")
	@Produces("application/json;charset=UTF-8")
	public Response getTitleFromMaterial(@PathParam("id") String id) {
		return null;
	}

	@GET
	@Path("/marc/get/material/bib/{id}/{tag}")
	@Produces("application/json;charset=UTF-8")
	public Response getBibFromMaterial(@PathParam("id") String id,
			@PathParam("tag") String tag) {
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

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/marc/upload/{library}/{userid}")
	public Response uploadMarc(@Multipart("file") @NotNull Object csv,
			@PathParam("library") String library,
			@PathParam("library") String userid) {
		return null;
	}

}
