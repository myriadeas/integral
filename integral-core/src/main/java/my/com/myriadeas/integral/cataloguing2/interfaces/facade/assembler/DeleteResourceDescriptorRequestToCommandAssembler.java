package my.com.myriadeas.integral.cataloguing2.interfaces.facade.assembler;

import my.com.myriadeas.integral.cataloguing2.application.service.command.DeleteResourceDescriptorCommand;
import my.com.myriadeas.integral.cataloguing2.interfaces.facade.request.DeleteResourceDescriptorRequest;

public class DeleteResourceDescriptorRequestToCommandAssembler {

	public DeleteResourceDescriptorCommand toDeleteResourceDescriptorCommand(
			final DeleteResourceDescriptorRequest request) {
		final DeleteResourceDescriptorCommand command = new DeleteResourceDescriptorCommand(
				request.getId());

		return command;
	}
}
