package my.com.myriadeas.integral.assetmanager.application.service;

import my.com.myriadeas.integral.assetmanager.application.command.ReleaseItemCommand;
import my.com.myriadeas.integral.assetmanager.infrastructure.ItemRepositoryImpl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AssetManagerWriteServiceImplTest {
	
	@Autowired
	AssetManagerWriteService assetManagerWriteService;
	
	@Autowired 
	ItemRepositoryImpl itemRepository;

	@Test
	public void testReleaseItem() {
		
		String itemIdentifier = "item000001";
		ReleaseItemCommand command = new ReleaseItemCommand(itemIdentifier);
		
		itemRepository
		//assetManagerWriteService.releaseItem(command);
		
		
	}

}
