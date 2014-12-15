package my.com.myriadeas.integral.assetmanager.application.service;

import my.com.myriadeas.integral.assetmanager.application.command.CreateItemCommand;
import my.com.myriadeas.integral.assetmanager.application.command.DeleteItemCommand;
import my.com.myriadeas.integral.assetmanager.application.command.ReceiveItemCommand;
import my.com.myriadeas.integral.assetmanager.application.command.ReleaseItemCommand;
import my.com.myriadeas.integral.assetmanager.application.command.UnreleaseItemCommand;
import my.com.myriadeas.integral.assetmanager.domain.event.ItemReceived;

public interface AssetManagerWriteService {
	
	public void receiveItem(ReceiveItemCommand receiveItemCommand);
	
	public Long createItem(CreateItemCommand createItemCommand);

	public void create(ItemReceived itemReceivedEvent);

	public Long releaseItem(ReleaseItemCommand releaseItemCommand);

	public Long unreleaseItem(UnreleaseItemCommand unreleaseItemCommand);

	public Long deleteItem(DeleteItemCommand deleteItemCommand);

}
