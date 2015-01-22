package my.com.myriadeas.integral.cqrs.query.accession.presentation;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import my.com.myriadeas.integral.assetmanagement.application.command.CreateItemCommand;
import my.com.myriadeas.integral.assetmanagement.application.service.ItemWriteService;
import my.com.myriadeas.integral.assetmanagement.config.AssetManagementConfigDev;
import my.com.myriadeas.integral.assetmanagement.domain.model.Item;
import my.com.myriadeas.integral.assetmanagement.infrastructure.ItemRepositoryImpl;
import my.com.myriadeas.integral.cqrs.query.accession.config.AccessionConfigDev;
import my.com.myriadeas.spring.core.util.SpringEnvironmentUtil;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
		AssetManagementConfigDev.class, AccessionConfigDev.class })
@ActiveProfiles(SpringEnvironmentUtil.DEV)
public class VufindQueryFacadeTest {

	@Autowired
	ItemWriteService itemWriteService;

	@Autowired
	private ItemRepositoryImpl itemRepository;

	@Autowired
	VufindQueryFacade vufindQueryFacade;

	@Test
	public void testMarker() throws Exception {
	}

	@Test
	public void testGetStatuses() throws Exception {
		String resourceDescriptorIdentifier = "0000000999";
		CreateItemCommand command = new CreateItemCommand(
				resourceDescriptorIdentifier, null, null);
		Long id = itemWriteService.createItem(command);
		List<Item> items = itemRepository
				.findByResourceDescriptorIdentifier(resourceDescriptorIdentifier);
		assertNotNull(items);

		List<String> resourceDescriptorIdentifiers = new ArrayList<String>();
		resourceDescriptorIdentifiers.add(resourceDescriptorIdentifier);

		List<List<VufindStatus>> statusesList = vufindQueryFacade
				.getStatuses(resourceDescriptorIdentifiers);

		System.out.println(statusesList);

	}

	@Test
	public void testGetHolding() throws Exception {
		String resourceDescriptorIdentifier = "0000000998";
		CreateItemCommand command = new CreateItemCommand(
				resourceDescriptorIdentifier, null, null);
		Long id = itemWriteService.createItem(command);
		List<Item> items = itemRepository
				.findByResourceDescriptorIdentifier(resourceDescriptorIdentifier);
		assertNotNull(items);

		List<VufindHolding> holdings = vufindQueryFacade
				.getHolding(resourceDescriptorIdentifier);

		System.out.println(holdings);

	}

}