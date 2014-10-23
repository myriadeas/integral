package my.com.myriadeas.integral.cataloguing2.interfaces.facade.assembler;

import my.com.myriadeas.integral.cataloguing2.application.service.command.SendToDeleteResourceDescriptorCommand;
import my.com.myriadeas.integral.cataloguing2.interfaces.facade.request.SendToDeleteResourceDescriptorRequest;

public class SendToDeleteResourceDescriptorRequestToCommandAssembler {

	public SendToDeleteResourceDescriptorCommand toSendToDeleteResourceDescriptorCommand(
			final SendToDeleteResourceDescriptorRequest request) {
		final SendToDeleteResourceDescriptorCommand command = new SendToDeleteResourceDescriptorCommand(
				request.getResourceDescriptorId());

		return command;
	}
}
