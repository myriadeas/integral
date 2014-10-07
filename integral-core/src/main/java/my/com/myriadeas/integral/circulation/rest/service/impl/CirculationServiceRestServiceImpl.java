package my.com.myriadeas.integral.circulation.rest.service.impl;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import my.com.myriadeas.integral.circulation.facade.CirculationServiceFacade;
import my.com.myriadeas.integral.circulation.request.CheckInRequest;
import my.com.myriadeas.integral.circulation.request.CheckOutRequest;
import my.com.myriadeas.integral.circulation.request.ItemInformationRequest;
import my.com.myriadeas.integral.circulation.request.RecallRequest;
import my.com.myriadeas.integral.circulation.request.ReleaseRequest;
import my.com.myriadeas.integral.circulation.request.RenewRequest;
import my.com.myriadeas.integral.circulation.request.ReserveRequest;
import my.com.myriadeas.integral.circulation.response.CheckInResponse;
import my.com.myriadeas.integral.circulation.response.CheckOutResponse;
import my.com.myriadeas.integral.circulation.response.ItemInformationResponse;
import my.com.myriadeas.integral.circulation.response.RecallResponse;
import my.com.myriadeas.integral.circulation.response.ReleaseResponse;
import my.com.myriadeas.integral.circulation.response.RenewResponse;
import my.com.myriadeas.integral.circulation.response.ReserveResponse;
import my.com.myriadeas.integral.data.jpa.domain.AbstractUser;
import my.com.myriadeas.integral.data.jpa.domain.CirculationTransaction;

//@Path("/circulation")
public class CirculationServiceRestServiceImpl implements
		CirculationServiceFacade {

	@POST
	@Path("/checkout")
	@Consumes("application/json")
	@Produces("application/json")
	public CheckOutResponse checkout(CheckOutRequest checkOutRequest) {
		return null;
	}

	@POST
	@Path("/checkin")
	@Consumes("application/json")
	@Produces("application/json")
	public CheckInResponse checkin(CheckInRequest checkInRequest) {
		return null;
	}

	@POST
	@Path("/release")
	@Consumes("application/json")
	@Produces("application/json")
	public ReleaseResponse release(ReleaseRequest releaseRequest) {
		return null;
	}

	@POST
	@Path("/renew")
	@Consumes("application/json")
	@Produces("application/json")
	public RenewResponse renew(RenewRequest renewRequest) {
		return null;
	}

	@POST
	@Path("/reserve")
	@Consumes("application/json")
	@Produces("application/json")
	public ReserveResponse reserve(ReserveRequest reserveRequest) {
		return null;
	}

	@Override
	public ReserveResponse reserveByMaterial(ReserveRequest reserveRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReserveResponse reserveByAccession(ReserveRequest reserveRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@POST
	@Path("/recall")
	@Consumes("application/json")
	@Produces("application/json")
	public RecallResponse recall(RecallRequest recallRequest) {
		return null;
	}

	@GET
	@Path("/{patronIdentifier}/checkoutTransactionHistory")
	@Produces("application/json")
	public Response getCheckoutTransactionHistory(
			@PathParam("patronIdentifier") String patronId) {
		return null;
	}

	@GET
	@Path("/{patronIdentifier}/circulationTransactionHistory")
	@Produces("application/json")
	public List<CirculationTransaction> getCirculationTransactionHistory(
			@PathParam("patronIdentifier") String patronId) {
		return null;
	}

	@GET
	@Path("/{patronIdentifier}/onLoanItems")
	@Produces("application/json")
	public List<CirculationTransaction> getOnLoanItems(
			@PathParam("patronIdentifier") String patronId) {
		return null;
	}

	@GET
	@Path("/checkout/validate/patron")
	@Produces(MediaType.APPLICATION_JSON)
	public AbstractUser checkoutValidatePatron(
			@QueryParam("patronIdentifier") String patronId) {
		return null;
	}

	/*
	@GET
	@Path("/checkin/item/information")
	@Produces(MediaType.APPLICATION_JSON)
	public ItemInformationResponse getCheckinItemInformation(
			@QueryParam("itemIdentifier") String itemIdentifier,
			@QueryParam("cicircIdentifier") Long cicircIdentifier) {
		return null;
	}
	*/
	
	@Override
	public ItemInformationResponse getItemInformation(
			ItemInformationRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@GET
	@Path("/checkin/item/information")
	@Produces(MediaType.APPLICATION_JSON)
	public ItemInformationResponse getItemInformation(
			@QueryParam("itemIdentifier") String itemIdentifier,
			@QueryParam("circulationTransactionId")Long circulationTransactionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@POST
	@Path("/policy/reload")
	@Produces(MediaType.APPLICATION_JSON)
	public Object reloadPolicy() {
		return null;
	}

	@GET
	@Path("/item/{itemIdentifier}/patronitemeligibility")
	@Produces(MediaType.APPLICATION_JSON)
	public Object getPatronItemEligibility(
			@PathParam("itemIdentifier") String itemIdentifier,
			@QueryParam("username") String username) {
		return null;
	}
}
