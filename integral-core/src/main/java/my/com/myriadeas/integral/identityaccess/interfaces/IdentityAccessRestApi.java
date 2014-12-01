package my.com.myriadeas.integral.identityaccess.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

public class IdentityAccessRestApi {

	public IdentityAccessRestApi() {
	}

	@POST
	@Path("/identity/user/register")
	@Consumes("application/json")
	@Produces("application/json")
	public RegisterUserResponseDTO identity_registerUser(
			RegisterUserRequestDTO request) {
		return null;
	}
}