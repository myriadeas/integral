package my.com.myriadeas.integral.cataloguing2.interfaces.facade.assembler;

import my.com.myriadeas.integral.cataloguing2.application.service.command.FinalizeResourceDescriptorCommand;
import my.com.myriadeas.integral.cataloguing2.interfaces.facade.request.FinalizeResourceDescriptorRequest;

public class FinalizeResourceDescriptorRequestToCommandAssembler {

	public FinalizeResourceDescriptorCommand toFinalizeResourceDescriptorCommand(
			final FinalizeResourceDescriptorRequest request) {
		final FinalizeResourceDescriptorCommand command = new FinalizeResourceDescriptorCommand(
				request.getId(), request.getRecord());

		return command;
	}
}
