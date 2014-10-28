package my.com.myriadeas.integral.cataloguing2.interfaces.facade.assembler;

import my.com.myriadeas.integral.cataloguing2.application.service.command.CreateResourceDescriptorCommand;
import my.com.myriadeas.integral.cataloguing2.interfaces.facade.request.CreateResourceDescriptorRequest;

public class CreateResourceDescriptorRequestToCommandAssembler {

	public CreateResourceDescriptorCommand toCreateResourceDescriptorCommand(
			final CreateResourceDescriptorRequest request) {
		final CreateResourceDescriptorCommand command = new CreateResourceDescriptorCommand(
				request.getRecord());
		
		return command;
	}
}
