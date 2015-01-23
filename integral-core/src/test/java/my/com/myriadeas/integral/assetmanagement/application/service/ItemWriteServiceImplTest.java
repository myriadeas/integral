package my.com.myriadeas.integral.assetmanagement.application.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;

import my.com.myriadeas.integral.assetmanagement.AbstractAssetManagementIntegrationTest;
import my.com.myriadeas.integral.assetmanagement.application.command.CreateItemCommand;
import my.com.myriadeas.integral.assetmanagement.application.command.ReceiveItemCommand;
import my.com.myriadeas.integral.assetmanagement.infrastructure.ItemRepositoryImpl;
import my.com.myriadeas.integral.item.query.solr.ResourceDescriptorSolrRepositoryImpl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ItemWriteServiceImplTest extends
		AbstractAssetManagementIntegrationTest {

	@Autowired
	ItemWriteService itemWriteService;

	@Autowired
	ItemRepositoryImpl itemRepository;
	
	@Autowired
	ResourceDescriptorSolrRepositoryImpl resourceDescriptorSolrRepositoryImpl;

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

		//String resourceDescriptorIdentifier = "resourcedescriptor02";
		//ReceiveItemCommand command = new ReceiveItemCommand("Title001",
		//		"tester", "1234567", new BigDecimal(2), new BigDecimal(15),
		//		new BigDecimal(36));
		//itemWriteService.create(command);

		//assertNotNull(itemRepository
		//		.findByResourceDescriptorIdentifier(resourceDescriptorIdentifier));


	System.out.println(resourceDescriptorSolrRepositoryImpl.findByTitle("Java Programming"));
	}
	

}
