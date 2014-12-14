package my.com.myriadeas.integral.assetmanager.application.service;

import static org.junit.Assert.assertNotNull;
import my.com.myriadeas.integral.assetmanager.application.command.CreateItemCommand;
import my.com.myriadeas.integral.assetmanager.config.AssetManagerConfigDev;
import my.com.myriadeas.integral.assetmanager.infrastructure.ItemRepositoryImpl;
import my.com.myriadeas.spring.core.util.SpringEnvironmentUtil;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { AssetManagerConfigDev.class })
@ActiveProfiles(SpringEnvironmentUtil.DEV)
public class AssetManagerWriteServiceImplTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	AssetManagerWriteService assetManagerWriteService;
	
	@Autowired 
	ItemRepositoryImpl itemRepository;

	@Test
	public void testCreateItem() {
		
		String resourceDescriptorIdentifier = "resourcedescriptor01";
		CreateItemCommand command = new CreateItemCommand(resourceDescriptorIdentifier, null, null);
		assetManagerWriteService.createItem(command);
		
		assertNotNull(itemRepository.findByResourceDescriptorIdentifier(resourceDescriptorIdentifier));
		
	}

}
