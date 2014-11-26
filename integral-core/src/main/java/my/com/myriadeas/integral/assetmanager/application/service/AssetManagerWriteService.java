package my.com.myriadeas.integral.assetmanager.application.service;

import my.com.myriadeas.integral.assetmanager.application.command.CreateItemCommand;
import my.com.myriadeas.integral.assetmanager.application.command.DeleteItemCommand;
import my.com.myriadeas.integral.assetmanager.application.command.ReleaseItemCommand;
import my.com.myriadeas.integral.assetmanager.application.command.UnreleaseItemCommand;

public interface AssetManagerWriteService {

	public Long createItem(CreateItemCommand createItemCommand);

	public Long releaseItem(ReleaseItemCommand releaseItemCommand);

	public Long unreleaseItem(UnreleaseItemCommand unreleaseItemCommand);

	public Long deleteItem(DeleteItemCommand deleteItemCommand);

}
