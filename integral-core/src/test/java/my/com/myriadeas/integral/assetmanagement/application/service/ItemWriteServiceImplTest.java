package my.com.myriadeas.integral.assetmanagement.application.service;

import static my.com.myriadeas.spring.core.util.SpringEnvironmentUtil.DEV;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import my.com.myriadeas.integral.assetmanagement.application.command.CreateItemCommand;
import my.com.myriadeas.integral.assetmanagement.application.command.ReceiveItemCommand;
import my.com.myriadeas.integral.assetmanagement.application.service.ItemWriteService;
import my.com.myriadeas.integral.assetmanagement.config.AssetManagementConfigDev;
import my.com.myriadeas.integral.assetmanagement.domain.event.ItemReceived;
import my.com.myriadeas.integral.assetmanagement.infrastructure.ItemRepositoryImpl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { AssetManagementConfigDev.class })
@ActiveProfiles(DEV)
public class ItemWriteServiceImplTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	ItemWriteService itemWriteService;

	@Autowired
	ItemRepositoryImpl itemRepository;

	@Test
	public void testCreateItem() {

		String resourceDescriptorIdentifier = "resourcedescriptor01";
		CreateItemCommand command = new CreateItemCommand(
				resourceDescriptorIdentifier, null, null);
		itemWriteService.createItem(command);

		assertNotNull(itemRepository
				.findByResourceDescriptorIdentifier(resourceDescriptorIdentifier));

	}

	@Test
	public void testCreate() {

		String resourceDescriptorIdentifier = "resourcedescriptor02";
		ReceiveItemCommand command = new ReceiveItemCommand("Title001", "tester", "1234567",
				new BigDecimal(2), new BigDecimal(15), new BigDecimal(36));
		itemWriteService.create(command);

		assertNotNull(itemRepository
				.findByResourceDescriptorIdentifier(resourceDescriptorIdentifier));

	}

}
