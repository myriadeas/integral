package my.com.myriadeas.integral.cataloguing2.interfaces.facade.assembler;

import my.com.myriadeas.integral.cataloguing2.application.service.command.UpdateResourceDescriptorCommand;
import my.com.myriadeas.integral.cataloguing2.interfaces.facade.request.UpdateResourceDescriptorRequest;

public class UpdateResourceDescriptorRequestToCommandAssembler {

	public UpdateResourceDescriptorCommand toUpdateResourceDescriptorCommand(
			final UpdateResourceDescriptorRequest request) {
		final UpdateResourceDescriptorCommand command = new UpdateResourceDescriptorCommand(
				request.getResourceDescriptorId(), request.getRecord());

		return command;
	}
}
