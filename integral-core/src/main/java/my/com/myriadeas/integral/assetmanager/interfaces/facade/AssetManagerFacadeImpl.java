package my.com.myriadeas.integral.assetmanager.interfaces.facade;

import my.com.myriadeas.integral.assetmanager.application.command.CreateItemCommand;
import my.com.myriadeas.integral.assetmanager.application.command.DeleteItemCommand;
import my.com.myriadeas.integral.assetmanager.application.command.ReleaseItemCommand;
import my.com.myriadeas.integral.assetmanager.application.command.UnreleaseItemCommand;
import my.com.myriadeas.integral.assetmanager.application.service.AssetManagerWriteService;
import my.com.myriadeas.integral.assetmanager.domain.assembler.CreateItemMapper;
import my.com.myriadeas.integral.assetmanager.domain.assembler.DeleteItemMapper;
import my.com.myriadeas.integral.assetmanager.domain.assembler.ReleaseItemMapper;
import my.com.myriadeas.integral.assetmanager.domain.assembler.UnreleaseItemMapper;
import my.com.myriadeas.integral.assetmanager.domain.http.CreateItemRequest;
import my.com.myriadeas.integral.assetmanager.domain.http.CreateItemResponse;
import my.com.myriadeas.integral.assetmanager.domain.http.DeleteItemRequest;
import my.com.myriadeas.integral.assetmanager.domain.http.DeleteItemResponse;
import my.com.myriadeas.integral.assetmanager.domain.http.ReleaseItemRequest;
import my.com.myriadeas.integral.assetmanager.domain.http.ReleaseItemResponse;
import my.com.myriadeas.integral.assetmanager.domain.http.UnreleaseItemRequest;
import my.com.myriadeas.integral.assetmanager.domain.http.UnreleaseItemResponse;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("assetManagerFacade")
public class AssetManagerFacadeImpl implements AssetManagerFacade {

	private static final Logger logger = org.slf4j.LoggerFactory
			.getLogger(AssetManagerFacadeImpl.class);

	@Autowired
	private AssetManagerWriteService assetManagerWriteService;

	@Autowired
	private CreateItemMapper createItemMapper;

	@Autowired
	private ReleaseItemMapper releaseItemMapper;

	@Autowired
	private UnreleaseItemMapper unreleaseItemMapper;

	@Autowired
	private DeleteItemMapper deleteItemMapper;

	@Override
	public CreateItemResponse createItem(CreateItemRequest createItemRequest) {
		logger.debug("Entering createItem(createItemRequest={})",
				createItemRequest);
		logger.info("ItemIdentifier(itemIdentifier={})",
				createItemRequest.getItemIdentifier());
		CreateItemCommand createItemCommand = new CreateItemCommand(
				createItemRequest.getItemIdentifier(),
				createItemRequest.getResourceDescriptorIdentifier(),
				createItemRequest.getForeignCost(),
				createItemRequest.getLocalCost());
		assetManagerWriteService.createItem(createItemCommand);
		CreateItemResponse createItemResponse = createItemMapper.convertTo(createItemCommand.getItemIdentifier(), true, "Create item is success.");
				
		logger.debug("Leaving createItem(createItemResponse={})", createItemResponse);
		return createItemResponse;
	}

	@Override
	public ReleaseItemResponse releaseItem(ReleaseItemRequest releaseItemRequest) {
		logger.debug("Entering releaseItem(releaseItemRequest={})",
				releaseItemRequest);
		ReleaseItemCommand releaseItemCommand = new ReleaseItemCommand(
				releaseItemRequest.getItemIdentifier());
		logger.info("Release Item Identifier={}",releaseItemRequest.getItemIdentifier());	
		assetManagerWriteService.releaseItem(releaseItemCommand);

		ReleaseItemResponse releaseItemResponse = releaseItemMapper.convertTo(
				releaseItemCommand.getItemIdentifier(), true,
				"Release item is success.");

		logger.debug("Leaving releaseItem(releaseItemResponse={})", releaseItemResponse);
		return releaseItemResponse;
	}

	@Override
	public UnreleaseItemResponse unreleaseItem(
			UnreleaseItemRequest unreleaseItemRequest) {
		logger.debug("Entering unreleaseItem(unreleaseItemRequest={})",
				unreleaseItemRequest);
		UnreleaseItemCommand unreleaseItemCommand = new UnreleaseItemCommand(
				unreleaseItemRequest.getItemIdentifier());
		assetManagerWriteService.unreleaseItem(unreleaseItemCommand);

		UnreleaseItemResponse unreleaseItemResponse = unreleaseItemMapper
				.convertTo(unreleaseItemCommand.getItemIdentifier(), true,
						"Unrelease item is success.");
		logger.debug("Leaving unreleaseItem(unreleaseItemResponse={})", unreleaseItemResponse);
		return unreleaseItemResponse;
	}

	@Override
	public DeleteItemResponse deleteItem(DeleteItemRequest deleteItemRequest) {
		logger.debug("Entering deleteItem(deleteItemRequest={})",
				deleteItemRequest);
		DeleteItemCommand deleteItemCommand = new DeleteItemCommand(
				deleteItemRequest.getItemIdentifier());
		assetManagerWriteService.deleteItem(deleteItemCommand);

		DeleteItemResponse deleteItemResponse = deleteItemMapper.convertTo(
				deleteItemCommand.getItemIdentifier(), true,
				"Delete item is success.");
		logger.debug("Leaving deleteItem(deleteItemRequest={})", deleteItemRequest);
		return deleteItemResponse;
	}

}
