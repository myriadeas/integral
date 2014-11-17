package my.com.myriadeas.integral.cataloguing2.interfaces.facade.assembler;

import my.com.myriadeas.integral.cataloguing2.application.service.command.VerifyResourceDescriptorCommand;
import my.com.myriadeas.integral.cataloguing2.interfaces.facade.request.VerifyRecordRequest;

public class VerifyResourceDescriptorRequestToCommandAssembler {

	public VerifyResourceDescriptorCommand toVerifyResourceDescriptorCommand(
			final VerifyRecordRequest request) {
		final VerifyResourceDescriptorCommand command = new VerifyResourceDescriptorCommand(
				request.getRecord());

		return command;
	}
}
