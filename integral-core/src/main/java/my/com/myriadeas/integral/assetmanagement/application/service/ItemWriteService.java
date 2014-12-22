package my.com.myriadeas.integral.assetmanagement.application.service;

import my.com.myriadeas.integral.assetmanagement.application.command.CreateItemCommand;
import my.com.myriadeas.integral.assetmanagement.application.command.DeleteItemCommand;
import my.com.myriadeas.integral.assetmanagement.application.command.ReceiveItemCommand;
import my.com.myriadeas.integral.assetmanagement.application.command.ReleaseItemCommand;
import my.com.myriadeas.integral.assetmanagement.application.command.UnreleaseItemCommand;
import my.com.myriadeas.integral.assetmanagement.domain.event.ItemReceived;

public interface ItemWriteService {
	
	public void receiveItem(ReceiveItemCommand receiveItemCommand);
	
	public Long createItem(CreateItemCommand createItemCommand);

	public void create(ReceiveItemCommand receiveItemCommand);

	public Long releaseItem(ReleaseItemCommand releaseItemCommand);

	public Long unreleaseItem(UnreleaseItemCommand unreleaseItemCommand);

	public Long deleteItem(DeleteItemCommand deleteItemCommand);

}
