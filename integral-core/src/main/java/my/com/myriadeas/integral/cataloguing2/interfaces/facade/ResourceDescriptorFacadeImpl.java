package my.com.myriadeas.integral.cataloguing2.interfaces.facade;

import java.io.UnsupportedEncodingException;

import my.com.myriadeas.integral.cataloguing.marc4j.utility.RecordToString;
import my.com.myriadeas.integral.cataloguing2.application.service.ResourceDescriptorReadService;
import my.com.myriadeas.integral.cataloguing2.application.service.ResourceDescriptorWriteService;
import my.com.myriadeas.integral.cataloguing2.application.service.command.CreateResourceDescriptorCommand;
import my.com.myriadeas.integral.cataloguing2.application.service.command.DeleteResourceDescriptorCommand;
import my.com.myriadeas.integral.cataloguing2.application.service.command.FinalizeResourceDescriptorCommand;
import my.com.myriadeas.integral.cataloguing2.application.service.command.ReviseResourceDescriptorCommand;
import my.com.myriadeas.integral.cataloguing2.application.service.command.SendToDeleteResourceDescriptorCommand;
import my.com.myriadeas.integral.cataloguing2.application.service.command.UpdateResourceDescriptorCommand;
import my.com.myriadeas.integral.cataloguing2.interfaces.facade.assembler.CreateResourceDescriptorRequestToCommandAssembler;
import my.com.myriadeas.integral.cataloguing2.interfaces.facade.assembler.DeleteResourceDescriptorRequestToCommandAssembler;
import my.com.myriadeas.integral.cataloguing2.interfaces.facade.assembler.FinalizeResourceDescriptorRequestToCommandAssembler;
import my.com.myriadeas.integral.cataloguing2.interfaces.facade.assembler.ReviseResourceDescriptorRequestToCommandAssembler;
import my.com.myriadeas.integral.cataloguing2.interfaces.facade.assembler.SendToDeleteResourceDescriptorRequestToCommandAssembler;
import my.com.myriadeas.integral.cataloguing2.interfaces.facade.assembler.UpdateResourceDescriptorRequestToCommandAssembler;
import my.com.myriadeas.integral.cataloguing2.interfaces.facade.request.CreateResourceDescriptorRequest;
import my.com.myriadeas.integral.cataloguing2.interfaces.facade.request.DeleteResourceDescriptorRequest;
import my.com.myriadeas.integral.cataloguing2.interfaces.facade.request.FinalizeResourceDescriptorRequest;
import my.com.myriadeas.integral.cataloguing2.interfaces.facade.request.ReviseResourceDescriptorRequest;
import my.com.myriadeas.integral.cataloguing2.interfaces.facade.request.SendToDeleteResourceDescriptorRequest;
import my.com.myriadeas.integral.cataloguing2.interfaces.facade.request.UpdateResourceDescriptorRequest;

import org.marc4j.marc.Record;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service(value = "resourceDescriptorFacade")
@com.fasterxml.jackson.annotation.JsonIgnoreProperties(ignoreUnknown = true)
public class ResourceDescriptorFacadeImpl implements ResourceDescriptorFacade {

	@Autowired
	ResourceDescriptorWriteService resourceDescriptorWriteService;

	@Autowired
	ResourceDescriptorReadService resourceDescriptorReadService;

	private Logger logger = LoggerFactory
			.getLogger(ResourceDescriptorFacadeImpl.class);

	RecordToString recordToString = new RecordToString();

	public String createResourceDescriptor(
			CreateResourceDescriptorRequest createResourceDescriptorRequest) {
		logger.debug("Entering createResourceDescriptor=(record={})",
				createResourceDescriptorRequest.getRecord());
		Assert.notNull(createResourceDescriptorRequest);
		CreateResourceDescriptorRequestToCommandAssembler createResourceDescriptorAssembler = new CreateResourceDescriptorRequestToCommandAssembler();
		CreateResourceDescriptorCommand createResourceDescriptorCommand = createResourceDescriptorAssembler
				.toCreateResourceDescriptorCommand(createResourceDescriptorRequest);
		String resourceDescriptorId = resourceDescriptorWriteService
				.createResourceDescriptor(createResourceDescriptorCommand);
		logger.debug(
				"Leaving createResourceDescriptor=(resourceDescriptorId={})",
				resourceDescriptorId);
		return resourceDescriptorId;
	}

	public void updateResourceDescriptor(
			UpdateResourceDescriptorRequest updateResourceDescriptorRequest)
			throws UnsupportedEncodingException {

		logger.debug(
				"Entering updateResourceDescriptor=(updateResourceDescriptorRequest.getResourceDescriptorId()={})",
				updateResourceDescriptorRequest.getResourceDescriptorId());
		UpdateResourceDescriptorRequestToCommandAssembler updateResourceDescriptorAssembler = new UpdateResourceDescriptorRequestToCommandAssembler();
		UpdateResourceDescriptorCommand updateResourceDescriptorCommand = updateResourceDescriptorAssembler
				.toUpdateResourceDescriptorCommand(updateResourceDescriptorRequest);
		resourceDescriptorWriteService
				.updateResourceDescriptor(updateResourceDescriptorCommand);
		logger.debug(
				"Leaving updateResourceDescriptor=(updateResourceDescriptorRequest.getResourceDescriptorId()={})",
				updateResourceDescriptorRequest.getResourceDescriptorId());

	}

	@Override
	public void finalizeResourceDescriptor(
			FinalizeResourceDescriptorRequest finalizeResourceDescriptorRequest)
			throws UnsupportedEncodingException {
		logger.debug(
				"Entering finalizeResourceDescriptor=(finalizeResourceDescriptorRequest.getResourceDescriptorId()={})",
				finalizeResourceDescriptorRequest.getResourceDescriptorId());

		FinalizeResourceDescriptorRequestToCommandAssembler finalizeResourceDescriptorAssembler = new FinalizeResourceDescriptorRequestToCommandAssembler();
		FinalizeResourceDescriptorCommand finalizeResourceDescriptorCommand = finalizeResourceDescriptorAssembler
				.toFinalizeResourceDescriptorCommand(finalizeResourceDescriptorRequest);
		resourceDescriptorWriteService
				.finalizeResourceDescriptor(finalizeResourceDescriptorCommand);
		logger.debug(
				"Leaving finalizeResourceDescriptor=(finalizeResourceDescriptorRequest.getResourceDescriptorId()={})",
				finalizeResourceDescriptorRequest.getResourceDescriptorId());

	}

	@Override
	public void reviseResourceDescriptor(
			ReviseResourceDescriptorRequest reviseResourceDescriptorRequest)
			throws UnsupportedEncodingException {
		logger.debug(
				"Entering reviseResourceDescriptor=(reviseResourceDescriptorRequest.getResourceDescriptorId()={})",
				reviseResourceDescriptorRequest.getResourceDescriptorId());
		ReviseResourceDescriptorRequestToCommandAssembler reviseResourceDescriptorAssembler = new ReviseResourceDescriptorRequestToCommandAssembler();
		ReviseResourceDescriptorCommand reviseResourceDescriptorCommand = reviseResourceDescriptorAssembler
				.toReviseResourceDescriptorCommand(reviseResourceDescriptorRequest);
		resourceDescriptorWriteService
				.reviseResourceDescriptor(reviseResourceDescriptorCommand);
		logger.debug(
				"Leaving finalizeResourceDescriptor=(reviseResourceDescriptorRequest.getResourceDescriptorId()={})",
				reviseResourceDescriptorRequest.getResourceDescriptorId());

	}

	public void deleteResourceDescriptor(
			DeleteResourceDescriptorRequest deleteResourceDescriptorRequest) {

		logger.debug(
				"Entering deleteResourceDescriptor=(deleteResourceDescriptorRequest.getResourceDescriptorId()={})",
				deleteResourceDescriptorRequest.getResourceDescriptorId());

		DeleteResourceDescriptorRequestToCommandAssembler deleteResourceDescriptorAssembler = new DeleteResourceDescriptorRequestToCommandAssembler();
		DeleteResourceDescriptorCommand deleteResourceDescriptorCommand = deleteResourceDescriptorAssembler
				.toDeleteResourceDescriptorCommand(deleteResourceDescriptorRequest);

		resourceDescriptorWriteService
				.deleteResourceDescriptor(deleteResourceDescriptorCommand);

		logger.debug(
				"Leaving deleteResourceDescriptor=(deleteResourceDescriptorRequest.getResourceDescriptorId()={})",
				deleteResourceDescriptorRequest.getResourceDescriptorId());
	}

	public void sendToDeleteResourceDescriptor(
			SendToDeleteResourceDescriptorRequest sendToDeleteResourceDescriptorRequest) {

		logger.debug(
				"Entering sendToDeleteResourceDescriptor=(sendToDeleteResourceDescriptorRequest.getResourceDescriptorId()={})",
				sendToDeleteResourceDescriptorRequest.getResourceDescriptorId());

		SendToDeleteResourceDescriptorRequestToCommandAssembler sendToDeleteResourceDescriptorAssembler = new SendToDeleteResourceDescriptorRequestToCommandAssembler();
		SendToDeleteResourceDescriptorCommand sendToDeleteResourceDescriptorCommand = sendToDeleteResourceDescriptorAssembler
				.toSendToDeleteResourceDescriptorCommand(sendToDeleteResourceDescriptorRequest);

		resourceDescriptorWriteService
				.sendToDeleteResourceDescriptor(sendToDeleteResourceDescriptorCommand);

		logger.debug(
				"Leaving sendToDeleteResourceDescriptor=(sendToDeleteResourceDescriptorRequest.getResourceDescriptorId()={})",
				sendToDeleteResourceDescriptorRequest.getResourceDescriptorId());
	}

	public String verifyRecord(Record record) {
		return resourceDescriptorReadService.verifyRecord(record);
	}

	public String convertRecord(Record record) {
		return resourceDescriptorReadService.convertRecord(record);
	}

}
