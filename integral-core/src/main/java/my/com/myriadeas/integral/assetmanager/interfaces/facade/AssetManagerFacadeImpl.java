package my.com.myriadeas.integral.assetmanager.interfaces.facade;

import my.com.myriadeas.integral.assetmanager.application.command.DeleteItemCommand;
import my.com.myriadeas.integral.assetmanager.application.command.ReleaseItemCommand;
import my.com.myriadeas.integral.assetmanager.application.command.UnreleaseItemCommand;
import my.com.myriadeas.integral.assetmanager.application.service.AssetManagerWriteService;
import my.com.myriadeas.integral.assetmanager.domain.assembler.DeleteItemMapper;
import my.com.myriadeas.integral.assetmanager.domain.assembler.ReleaseItemMapper;
import my.com.myriadeas.integral.assetmanager.domain.assembler.UnreleaseItemMapper;
import my.com.myriadeas.integral.assetmanager.domain.http.DeleteItemRequest;
import my.com.myriadeas.integral.assetmanager.domain.http.DeleteItemResponse;
import my.com.myriadeas.integral.assetmanager.domain.http.ReleaseItemRequest;
import my.com.myriadeas.integral.assetmanager.domain.http.ReleaseItemResponse;
import my.com.myriadeas.integral.assetmanager.domain.http.UnreleaseItemRequest;
import my.com.myriadeas.integral.assetmanager.domain.http.UnreleaseItemResponse;

import org.slf4j.Logger;

public class AssetManagerFacadeImpl implements AssetManagerFacade {

	private static final Logger logger = org.slf4j.LoggerFactory
			.getLogger(AssetManagerFacadeImpl.class);

	private AssetManagerWriteService assetManagerService;

	private ReleaseItemMapper releaseItemMapper;

	private UnreleaseItemMapper unreleaseItemMapper;

	private DeleteItemMapper deleteItemMapper;

	@Override
	public ReleaseItemResponse releaseItem(ReleaseItemRequest releaseItemRequest) {

		ReleaseItemCommand releaseItemCommand = new ReleaseItemCommand(
				releaseItemRequest.getItemIdentifier());
		assetManagerService.releaseItem(releaseItemCommand);

		ReleaseItemResponse releaseItemResponse = releaseItemMapper.convertTo(
				releaseItemCommand.getItemIdentifier(), true,
				"Release Item is success.");

		return releaseItemResponse;
	}

	@Override
	public UnreleaseItemResponse unreleaseItem(
			UnreleaseItemRequest unreleaseItemRequest) {

		UnreleaseItemCommand unreleaseItemCommand = new UnreleaseItemCommand(
				unreleaseItemRequest.getItemIdentifier());
		assetManagerService.unreleaseItem(unreleaseItemCommand);

		UnreleaseItemResponse unreleaseItemResponse = unreleaseItemMapper
				.convertTo(unreleaseItemCommand.getItemIdentifier(), true,
						"Unrelease Item is success.");

		return unreleaseItemResponse;
	}

	@Override
	public DeleteItemResponse unreleaseItem(DeleteItemRequest deleteItemRequest) {

		DeleteItemCommand deleteItemCommand = new DeleteItemCommand(
				deleteItemRequest.getItemIdentifier());
		assetManagerService.deleteItem(deleteItemCommand);

		DeleteItemResponse deleteItemResponse = deleteItemMapper.convertTo(
				deleteItemCommand.getItemIdentifier(), true,
				"Delete Item is success.");
		return deleteItemResponse;
	}

}
