package my.com.myriadeas.integral.cqrs.query.accession.presentation;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import my.com.myriadeas.integral.assetmanagement.application.command.CreateItemCommand;
import my.com.myriadeas.integral.assetmanagement.application.service.ItemWriteService;
import my.com.myriadeas.integral.assetmanagement.config.AssetManagementConfigDev;
import my.com.myriadeas.integral.assetmanagement.domain.model.Item;
import my.com.myriadeas.integral.assetmanagement.domain.model.ItemStatus;
import my.com.myriadeas.integral.assetmanagement.infrastructure.ItemRepositoryImpl;
import my.com.myriadeas.integral.cqrs.query.accession.config.AccessionConfigDev;
import my.com.myriadeas.integral.cqrs.query.accession.presentation.AccessionListItemDto;
import my.com.myriadeas.integral.cqrs.query.accession.presentation.AccessionSearchCriteria;
import my.com.myriadeas.integral.cqrs.query.accession.presentation.SqlAccessionFinder;
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
public class SqlAccessionFinderTest {

	@Autowired
	ItemWriteService itemWriteService;

	@Autowired
	private ItemRepositoryImpl itemRepository;

	@Autowired
	SqlAccessionFinder sqlRdFinder;

	@Test
	public void testMarker() throws Exception {
	}

	@Test
	public void testSqlAccessionFinder() throws Exception {
		String resourceDescriptorIdentifier = "0000000001";
		CreateItemCommand command = new CreateItemCommand(
				resourceDescriptorIdentifier, null, null);
		Long id = itemWriteService.createItem(command);
		List<Item> items = itemRepository
				.findByResourceDescriptorIdentifier(resourceDescriptorIdentifier);
		assertNotNull(items);

		AccessionSearchCriteria accessionSearchCriteria = new AccessionSearchCriteria();
		Collection<String> specificResourceDescriptorIds = new ArrayList<String>();
		specificResourceDescriptorIds.add(resourceDescriptorIdentifier);
		accessionSearchCriteria
				.setSpecificResourceDescriptorIds(specificResourceDescriptorIds);
		accessionSearchCriteria.setStatus(ItemStatus.NEW);
		AccessionListItemDto accessionList = sqlRdFinder
				.findAccessions(accessionSearchCriteria).getItems().get(0);
		System.out.print("SQL:");
		System.out.println(accessionList);

	}

}