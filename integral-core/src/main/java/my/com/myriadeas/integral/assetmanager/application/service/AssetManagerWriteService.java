package my.com.myriadeas.integral.assetmanager.application.service;

import my.com.myriadeas.integral.assetmanager.application.command.DeleteItemCommand;
import my.com.myriadeas.integral.assetmanager.application.command.ReleaseItemCommand;
import my.com.myriadeas.integral.assetmanager.application.command.UnreleaseItemCommand;

public interface AssetManagerWriteService {

	public void releaseItem(ReleaseItemCommand releaseItemCommand);

	public void unreleaseItem(UnreleaseItemCommand unreleaseItemCommand);

	public void deleteItem(DeleteItemCommand deleteItemCommand);

}
