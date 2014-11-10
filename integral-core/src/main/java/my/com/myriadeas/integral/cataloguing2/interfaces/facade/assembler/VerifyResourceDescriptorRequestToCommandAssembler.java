package my.com.myriadeas.integral.cataloguing2.interfaces.facade.assembler;

import my.com.myriadeas.integral.cataloguing2.application.service.command.VerifyResourceDescriptorCommand;
import my.com.myriadeas.integral.cataloguing2.interfaces.facade.request.VerifyResourceDescriptorRequest;

public class VerifyResourceDescriptorRequestToCommandAssembler {

	public VerifyResourceDescriptorCommand toVerifyResourceDescriptorCommand(
			final VerifyResourceDescriptorRequest request) {
		final VerifyResourceDescriptorCommand command = new VerifyResourceDescriptorCommand(
				request.getRecord());

		return command;
	}
}
