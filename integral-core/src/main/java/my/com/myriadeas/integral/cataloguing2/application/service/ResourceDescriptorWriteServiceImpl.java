package my.com.myriadeas.integral.cataloguing2.application.service;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import my.com.myriadeas.integral.cataloguing.marc4j.utility.RecordToString;
import my.com.myriadeas.integral.cataloguing2.application.service.command.CreateResourceDescriptorCommand;
import my.com.myriadeas.integral.cataloguing2.application.service.command.DeleteResourceDescriptorCommand;
import my.com.myriadeas.integral.cataloguing2.application.service.command.FinalizeResourceDescriptorCommand;
import my.com.myriadeas.integral.cataloguing2.application.service.command.ReviseResourceDescriptorCommand;
import my.com.myriadeas.integral.cataloguing2.application.service.command.UpdateResourceDescriptorCommand;
import my.com.myriadeas.integral.cataloguing2.domain.model.ResourceDescriptor;
import my.com.myriadeas.integral.cataloguing2.infrastructure.ResourceDescriptorRepositoryImpl;
import my.com.myriadeas.integral.core.domain.model.DomainEvent;
import my.com.myriadeas.integral.core.publisher.Publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service(value = "resourceDescriptorWriteService")
@com.fasterxml.jackson.annotation.JsonIgnoreProperties(ignoreUnknown = true)
public class ResourceDescriptorWriteServiceImpl implements
		ResourceDescriptorWriteService {

	private Logger logger = LoggerFactory
			.getLogger(ResourceDescriptorWriteServiceImpl.class);

	@Autowired
	private ResourceDescriptorRepositoryImpl resourceDescriptorRepo;

	@Autowired
	@Qualifier("cataloguingPublisher")
	protected Publisher publisher;

	RecordToString recordToString = new RecordToString();

	// ??? how to get event from domain to publish??
	public Long createResourceDescriptor(
			CreateResourceDescriptorCommand createResourceDescriptorCommand) {
		logger.debug("Entering createResourceDescriptor=(record={})",
				createResourceDescriptorCommand.getRecord());
		Assert.notNull(createResourceDescriptorCommand);
		ResourceDescriptor resourceDescriptor = resourceDescriptorRepo
				.save(new ResourceDescriptor(createResourceDescriptorCommand
						.getRecord()));
		System.out.println("publisher=" + publisher);
		publisher.publish(resourceDescriptor
				.getResourceDescriptorCreatedEvent());
		logger.debug(
				"Leaving createResourceDescriptor=(resourceDescriptor={})",
				resourceDescriptor);
		return resourceDescriptor.getId();
	}

	public void updateResourceDescriptor(
			UpdateResourceDescriptorCommand updateResourceDescriptorCommand)
			throws UnsupportedEncodingException {

		logger.debug(
				"Entering updateResourceDescriptor=(updateResourceDescriptorCommand.getResourceDescriptorId()={})",
				updateResourceDescriptorCommand.getResourceDescriptorId());
		ResourceDescriptor resourceDescriptor = resourceDescriptorRepo
				.findByResourceDescriptorId(updateResourceDescriptorCommand
						.getResourceDescriptorId());
		Assert.notNull(resourceDescriptor);
		Map<String, DomainEvent> events = resourceDescriptor
				.update(recordToString.convert(updateResourceDescriptorCommand
						.getRecord()));
		resourceDescriptorRepo.save(resourceDescriptor);
		publisher.publish(events);
		logger.debug(
				"Leaving updateResourceDescriptor=(resourceDescriptor={})",
				resourceDescriptor);

	}

	@Override
	public void finalizeResourceDescriptor(
			FinalizeResourceDescriptorCommand finalizeResourceDescriptorCommand)
			throws UnsupportedEncodingException {
		logger.debug(
				"Entering finalizeResourceDescriptor=(finalizeResourceDescriptorCommand.getId()={})",
				finalizeResourceDescriptorCommand.getId());

		ResourceDescriptor resourceDescriptor = resourceDescriptorRepo
				.findOne(finalizeResourceDescriptorCommand.getId());
		Assert.notNull(resourceDescriptor);
		Map<String, DomainEvent> events = resourceDescriptor
				.finalize(recordToString
						.convert(finalizeResourceDescriptorCommand.getRecord()));
		resourceDescriptorRepo.save(resourceDescriptor);
		publisher.publish(events);

		logger.debug(
				"Leaving finalizeResourceDescriptor=(resourceDescriptor={})",
				resourceDescriptor);

	}

	@Override
	public void reviseResourceDescriptor(
			ReviseResourceDescriptorCommand reviseResourceDescriptorCommand)
			throws UnsupportedEncodingException {
		logger.debug(
				"Entering reviseResourceDescriptor=(reviseResourceDescriptorCommand.getResourceDescriptorId()={})",
				reviseResourceDescriptorCommand.getId());
		ResourceDescriptor resourceDescriptor = resourceDescriptorRepo
				.findOne(reviseResourceDescriptorCommand.getId());
		Assert.notNull(resourceDescriptor);
		Map<String, DomainEvent> events = resourceDescriptor
				.revise(recordToString.convert(reviseResourceDescriptorCommand
						.getRecord()));
		resourceDescriptorRepo.save(resourceDescriptor);
		publisher.publish(events);

		logger.debug(
				"Leaving reviseResourceDescriptor=(resourceDescriptor={})",
				resourceDescriptor);

	}

	public void deleteResourceDescriptor(
			DeleteResourceDescriptorCommand deleteResourceDescriptorCommand) {

		logger.debug(
				"Entering deleteResourceDescriptor=(deleteResourceDescriptorCommand.getResourceDescriptorId()={})",
				deleteResourceDescriptorCommand.getId());

		ResourceDescriptor resourceDescriptor = resourceDescriptorRepo
				.findOne(deleteResourceDescriptorCommand.getId());
		Assert.notNull(resourceDescriptor);
		Map<String, DomainEvent> events = resourceDescriptor.delete();
		resourceDescriptorRepo.save(resourceDescriptor);
		publisher.publish(events);

		logger.debug(
				"Leaving deleteResourceDescriptor=(resourceDescriptor={})",
				resourceDescriptor);
	}

}
