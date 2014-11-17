package my.com.myriadeas.integral.circulation2.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

public class CirculationRestApi {

	@POST
	@Path("/holding/newHolding")
	@Consumes("application/json")
	@Produces("application/json")
	public NewHoldingResponseDTO holding_newHolding(NewHoldingRequestDTO request) {
		return null;
	}
}
