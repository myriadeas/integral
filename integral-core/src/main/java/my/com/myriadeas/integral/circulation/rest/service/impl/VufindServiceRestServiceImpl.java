package my.com.myriadeas.integral.circulation.rest.service.impl;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import my.com.myriadeas.integral.circulation.facade.VufindServiceFacade;
import my.com.myriadeas.integral.circulation.vufind.CancelHoldRequest;
import my.com.myriadeas.integral.circulation.vufind.Fines;
import my.com.myriadeas.integral.circulation.vufind.Hold;
import my.com.myriadeas.integral.circulation.vufind.Holding;
import my.com.myriadeas.integral.circulation.vufind.PatronLoginRequest;
import my.com.myriadeas.integral.circulation.vufind.PickUpLocation;
import my.com.myriadeas.integral.circulation.vufind.Profile;
import my.com.myriadeas.integral.circulation.vufind.RenewMyItemsRequest;
import my.com.myriadeas.integral.circulation.vufind.Status;
import my.com.myriadeas.integral.circulation.vufind.Transaction;
import my.com.myriadeas.integral.circulation.vufind.VufindPatron;
import my.com.myriadeas.integral.data.jpa.domain.Officer;

import org.slf4j.Logger;

//@Path("/vufind")
public class VufindServiceRestServiceImpl implements VufindServiceFacade {
	private static final Logger logger = org.slf4j.LoggerFactory
			.getLogger(VufindServiceRestServiceImpl.class);
	
	@GET
	@Path("/getUser")
	@Produces("application/json")
	public Officer getUser() {
		return null;
	}
	
	@GET
	@Path("/{bibliographicId}/status")
	@Produces("application/json")
	public List<Status> getStatus(
			@PathParam("bibliographicId") String bibliographicId) {
		return null;
	}
	
	@GET
	@Path("/{username}/{bibliographicId}/holding")
	@Produces("application/json")
	public List<Holding> getHolding(
			@PathParam("username") String username,
			@PathParam("bibliographicId") String bibliographicId) {
		return null;
	}

	@POST
	@Path("/getStatuses")
	@Consumes("application/json")
	@Produces("application/json")
	public List<List<Status>> getStatuses(List<String> bibliographicIds) {
		return null;
	}
	
	@GET
	@Path("/{username}/myProfile")
	@Produces("application/json")
	public Profile getMyProfile(
			@PathParam("username") String username) {
		return null;
	}

	@GET
	@Path("/{username}/myTransactions")
	@Produces("application/json")
	public List<Transaction> getMyTransactions(
			@PathParam("username") String username) {
		return null;
	}
	
	@GET
	@Path("/{username}/myFines")
	@Produces("application/json")
	public List<Fines> getMyFines(
			@PathParam("username") String username) {
		return null;
	}

	@GET
	@Path("/{username}/myHolds")
	@Produces("application/json")
	public List<Hold> getMyHolds(
			@PathParam("username") String username) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@GET
	@Path("/{itemId}/renewDetails")
	@Produces("application/json")
	public String getRenewDetails(
			@PathParam("itemId") String itemId) {
		return null;
	}

	@POST
	@Path("/renewMyItems")
	@Consumes("application/json")
	@Produces("application/json")
	public Map<String, Object> renewMyItems(RenewMyItemsRequest request) {
		logger.debug("Entering  rest service renewMyItems()(body={})", request);
		
		// TODO Auto-generated method stub
		return null;
	}


	@GET
	@Path("/placeHold")
	@Produces("application/json")
	public Map<String, Object> placeHold(
			@QueryParam("username") String username, 
			@QueryParam("itemId") String itemId,
			@QueryParam("pickUpBranch") String pickUpBranch) {
		// TODO Auto-generated method stub
		return null;
	}

	@GET
	@Path("/pickUpLocations")
	@Produces("application/json")
	public List<PickUpLocation> getPickUpLocations() {
		return null;
	}

	@POST
	@Path("/cancelHolds")
	@Consumes("application/json")
	@Produces("application/json")
	public Map<String, Object> cancelHolds(CancelHoldRequest request) {		
		return null;
	}

	@POST
	@Path("/patronLogin")
	@Consumes("application/json")
	@Produces("application/json")
	public VufindPatron patronLogin(PatronLoginRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
