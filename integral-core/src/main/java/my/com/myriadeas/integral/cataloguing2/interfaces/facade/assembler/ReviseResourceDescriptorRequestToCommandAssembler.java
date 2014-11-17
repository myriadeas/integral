package my.com.myriadeas.integral.cataloguing2.interfaces.facade.assembler;

import my.com.myriadeas.integral.cataloguing2.application.service.command.ReviseResourceDescriptorCommand;
import my.com.myriadeas.integral.cataloguing2.interfaces.facade.request.ReviseResourceDescriptorRequest;

public class ReviseResourceDescriptorRequestToCommandAssembler {

	public ReviseResourceDescriptorCommand toReviseResourceDescriptorCommand(
			final ReviseResourceDescriptorRequest request) {
		final ReviseResourceDescriptorCommand command = new ReviseResourceDescriptorCommand(
				request.getId(), request.getRecord());

		return command;
	}
}
