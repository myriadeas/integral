package my.com.myriadeas.integral.circulation2.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

public class CirculationRestApi {

	@POST
	@Path("/holding/release")
	@Consumes("application/json")
	@Produces("application/json")
	public ReleaseHoldingResponseDTO holding_release(ReleaseHoldingRequestDTO request) {
		return null;
	}
	
	@POST
	@Path("/borrower/register")
	@Consumes("application/json")
	@Produces("application/json")
	public RegisterBorrowerResponseDTO registerBorrower(RegisterBorrowerRequestDTO request) {
		return null;
	}

}
