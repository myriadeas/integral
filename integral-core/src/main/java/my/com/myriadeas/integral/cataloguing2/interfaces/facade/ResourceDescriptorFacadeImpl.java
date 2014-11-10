package my.com.myriadeas.integral.cataloguing2.interfaces.facade;

import java.io.UnsupportedEncodingException;

import my.com.myriadeas.integral.cataloguing.marc4j.utility.RecordToString;
import my.com.myriadeas.integral.cataloguing2.application.service.ResourceDescriptorReadService;
import my.com.myriadeas.integral.cataloguing2.application.service.ResourceDescriptorWriteService;
import my.com.myriadeas.integral.cataloguing2.application.service.command.CreateResourceDescriptorCommand;
import my.com.myriadeas.integral.cataloguing2.application.service.command.DeleteResourceDescriptorCommand;
import my.com.myriadeas.integral.cataloguing2.application.service.command.FinalizeResourceDescriptorCommand;
import my.com.myriadeas.integral.cataloguing2.application.service.command.ReviseResourceDescriptorCommand;
import my.com.myriadeas.integral.cataloguing2.application.service.command.UpdateResourceDescriptorCommand;
import my.com.myriadeas.integral.cataloguing2.application.service.command.VerifyResourceDescriptorCommand;
import my.com.myriadeas.integral.cataloguing2.interfaces.facade.assembler.CreateResourceDescriptorRequestToCommandAssembler;
import my.com.myriadeas.integral.cataloguing2.interfaces.facade.assembler.DeleteResourceDescriptorRequestToCommandAssembler;
import my.com.myriadeas.integral.cataloguing2.interfaces.facade.assembler.FinalizeResourceDescriptorRequestToCommandAssembler;
import my.com.myriadeas.integral.cataloguing2.interfaces.facade.assembler.ReviseResourceDescriptorRequestToCommandAssembler;
import my.com.myriadeas.integral.cataloguing2.interfaces.facade.assembler.UpdateResourceDescriptorRequestToCommandAssembler;
import my.com.myriadeas.integral.cataloguing2.interfaces.facade.assembler.VerifyResourceDescriptorRequestToCommandAssembler;
import my.com.myriadeas.integral.cataloguing2.interfaces.facade.request.CreateResourceDescriptorRequest;
import my.com.myriadeas.integral.cataloguing2.interfaces.facade.request.DeleteResourceDescriptorRequest;
import my.com.myriadeas.integral.cataloguing2.interfaces.facade.request.FinalizeResourceDescriptorRequest;
import my.com.myriadeas.integral.cataloguing2.interfaces.facade.request.ReviseResourceDescriptorRequest;
import my.com.myriadeas.integral.cataloguing2.interfaces.facade.request.UpdateResourceDescriptorRequest;
import my.com.myriadeas.integral.cataloguing2.interfaces.facade.request.VerifyResourceDescriptorRequest;

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

	public String createResourceDescriptor(Record record) {
		logger.debug("Entering createResourceDescriptor=(record={})", record);
		Assert.notNull(record);
		CreateResourceDescriptorRequest createResourceDescriptorRequest = new CreateResourceDescriptorRequest(
				record);
		String resourceDescriptorId = createResourceDescriptor(createResourceDescriptorRequest);
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
	public void finalizeResourceDescriptor(String resourceDescriptorId,
			Record record) throws UnsupportedEncodingException {
		logger.debug(
				"Entering finalizeResourceDescriptor=(resourceDescriptorId={})",
				resourceDescriptorId);

		FinalizeResourceDescriptorRequest finalizeResourceDescriptorRequest = new FinalizeResourceDescriptorRequest(
				resourceDescriptorId, record);
		finalizeResourceDescriptor(finalizeResourceDescriptorRequest);

		logger.debug(
				"Leaving finalizeResourceDescriptor=(resourceDescriptorId={})",
				resourceDescriptorId);

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

	public String verifyRecord(
			VerifyResourceDescriptorRequest verifyResourceDescriptorRequest) {
		logger.debug(
				"Entering verifyRecord=(verifyResourceDescriptorRequest.getRecord()={})",
				verifyResourceDescriptorRequest.getRecord());
		Assert.notNull(verifyResourceDescriptorRequest);
		VerifyResourceDescriptorRequestToCommandAssembler verifyResourceDescriptorAssembler = new VerifyResourceDescriptorRequestToCommandAssembler();
		VerifyResourceDescriptorCommand verifyResourceDescriptorCommand = verifyResourceDescriptorAssembler
				.toVerifyResourceDescriptorCommand(verifyResourceDescriptorRequest);
		return resourceDescriptorReadService
				.verifyRecord(verifyResourceDescriptorCommand);
	}

	public String convertRecord(Record record) {
		logger.debug("Entering convertRecord=(record={})", record);
		return resourceDescriptorReadService.convertRecord(record);
	}

}
