package my.com.myriadeas.integral.identityaccess.interfaces;

import my.com.myriadeas.integral.identityaccess.application.IdentityService;
import my.com.myriadeas.integral.identityaccess.application.RegisterUserCommand;
import my.com.myriadeas.integral.identityaccess.domain.model.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("identityFacadeImpl")
public class IdentityFacadeImpl implements IdentityFacade {

	private static final Logger logger = LoggerFactory
			.getLogger(IdentityFacadeImpl.class);
	private IdentityService identityService;

	public IdentityFacadeImpl() {
	}

	public RegisterUserResponseDTO registerUser(RegisterUserRequestDTO request) {
		logger.debug("Entering registerUser(request={})", request);
		RegisterUserCommand command = new RegisterUserCommand(
				request.getUsername(), request.getPassword());
		User user = identityService.registerUser(command);
		RegisterUserResponseDTO response = new RegisterUserResponseDTO(
				(Long) user.getId());
		logger.debug("Leaving registerUser().response={}", response);
		return response;
	}

	public RegisterUserResponseDTO registerOfficer(
			RegisterUserRequestDTO request) {
		logger.debug("Entering registerOfficer(request={})", request);
		RegisterUserCommand command = new RegisterUserCommand(
				request.getUsername(), request.getPassword());
		User user = identityService.registerOfficer(command);
		RegisterUserResponseDTO response = new RegisterUserResponseDTO(
				(Long) user.getId());
		logger.debug("Leaving registerOfficer().response={}", response);
		return response;
	}

	public RegisterUserResponseDTO registerPatron(RegisterUserRequestDTO request) {
		logger.debug("Entering registerPatron(request={})", request);
		RegisterUserCommand command = new RegisterUserCommand(
				request.getUsername(), request.getPassword());
		User user = identityService.registerPatron(command);
		RegisterUserResponseDTO response = new RegisterUserResponseDTO(
				(Long) user.getId());
		logger.debug("Leaving registerPatron().response={}", response);
		return response;
	}

	@Autowired
	public void setIdentityService(IdentityService identityService) {
		this.identityService = identityService;
	}

}