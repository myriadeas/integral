package my.com.myriadeas.integral.assetmanager.interfaces.facade;

import my.com.myriadeas.integral.assetmanager.application.command.CreateItemCommand;
import my.com.myriadeas.integral.assetmanager.application.command.DeleteItemCommand;
import my.com.myriadeas.integral.assetmanager.application.command.ReceiveItemCommand;
import my.com.myriadeas.integral.assetmanager.application.command.ReleaseItemCommand;
import my.com.myriadeas.integral.assetmanager.application.command.UnreleaseItemCommand;
import my.com.myriadeas.integral.assetmanager.application.service.AssetManagerWriteService;
import my.com.myriadeas.integral.assetmanager.domain.assembler.CreateItemMapper;
import my.com.myriadeas.integral.assetmanager.domain.assembler.DeleteItemMapper;
import my.com.myriadeas.integral.assetmanager.domain.assembler.ReceiveItemMapper;
import my.com.myriadeas.integral.assetmanager.domain.assembler.ReleaseItemMapper;
import my.com.myriadeas.integral.assetmanager.domain.assembler.UnreleaseItemMapper;
import my.com.myriadeas.integral.assetmanager.domain.http.CreateItemRequest;
import my.com.myriadeas.integral.assetmanager.domain.http.CreateItemResponse;
import my.com.myriadeas.integral.assetmanager.domain.http.DeleteItemRequest;
import my.com.myriadeas.integral.assetmanager.domain.http.DeleteItemResponse;
import my.com.myriadeas.integral.assetmanager.domain.http.ReceiveItemRequest;
import my.com.myriadeas.integral.assetmanager.domain.http.ReceiveItemResponse;
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
	private ReceiveItemMapper receiveItemMapper;

	@Autowired
	private CreateItemMapper createItemMapper;

	@Autowired
	private ReleaseItemMapper releaseItemMapper;

	@Autowired
	private UnreleaseItemMapper unreleaseItemMapper;

	@Autowired
	private DeleteItemMapper deleteItemMapper;

	@Override
	public ReceiveItemResponse receiveItem(ReceiveItemRequest receiveItemRequest) {
		logger.debug("Entering receiveItem(receiveItemRequest={})",
				receiveItemRequest);
		ReceiveItemCommand receiveItemCommand = new ReceiveItemCommand(
				receiveItemRequest.getTitle(), receiveItemRequest.getAuthor(),
				receiveItemRequest.getIsbn(),
				receiveItemRequest.getNumberOfCopy(),
				receiveItemRequest.getForeignCost(),
				receiveItemRequest.getLocalCost());
		assetManagerWriteService.receiveItem(receiveItemCommand);
		ReceiveItemResponse receiveItemResponse = receiveItemMapper.convertTo(
				receiveItemCommand.getTitle(), true, "Receive item success.");
		
		logger.debug("Leaving receiveItem(receiveItemResponse={})",
				receiveItemResponse);
		return receiveItemResponse;
	}

	@Override
	public CreateItemResponse createItem(CreateItemRequest createItemRequest) {
		logger.debug("Entering createItem(createItemRequest={})",
				createItemRequest);
		CreateItemCommand createItemCommand = new CreateItemCommand(
				createItemRequest.getResourceDescriptorIdentifier(),
				createItemRequest.getForeignCost(),
				createItemRequest.getLocalCost());
		Long id = assetManagerWriteService.createItem(createItemCommand);
		CreateItemResponse createItemResponse = createItemMapper.convertTo(id,
				true, "Create item is success.");

		logger.debug("Leaving createItem(createItemResponse={})",
				createItemResponse);
		return createItemResponse;
	}

	@Override
	public ReleaseItemResponse releaseItem(ReleaseItemRequest releaseItemRequest) {
		logger.debug("Entering releaseItem(releaseItemRequest={})",
				releaseItemRequest);
		ReleaseItemCommand releaseItemCommand = new ReleaseItemCommand(
				releaseItemRequest.getItemIdentifier());
		logger.info("Release Item Identifier={}",
				releaseItemRequest.getItemIdentifier());
		Long id = assetManagerWriteService.releaseItem(releaseItemCommand);

		ReleaseItemResponse releaseItemResponse = releaseItemMapper.convertTo(
				id, true, "Release item is success.");

		logger.debug("Leaving releaseItem(releaseItemResponse={})",
				releaseItemResponse);
		return releaseItemResponse;
	}

	@Override
	public UnreleaseItemResponse unreleaseItem(
			UnreleaseItemRequest unreleaseItemRequest) {
		logger.debug("Entering unreleaseItem(unreleaseItemRequest={})",
				unreleaseItemRequest);
		UnreleaseItemCommand unreleaseItemCommand = new UnreleaseItemCommand(
				unreleaseItemRequest.getItemIdentifier());
		Long id = assetManagerWriteService.unreleaseItem(unreleaseItemCommand);

		UnreleaseItemResponse unreleaseItemResponse = unreleaseItemMapper
				.convertTo(id, true, "Unrelease item is success.");
		logger.debug("Leaving unreleaseItem(unreleaseItemResponse={})",
				unreleaseItemResponse);
		return unreleaseItemResponse;
	}

	@Override
	public DeleteItemResponse deleteItem(DeleteItemRequest deleteItemRequest) {
		logger.debug("Entering deleteItem(deleteItemRequest={})",
				deleteItemRequest);
		DeleteItemCommand deleteItemCommand = new DeleteItemCommand(
				deleteItemRequest.getItemIdentifier());
		Long id = assetManagerWriteService.deleteItem(deleteItemCommand);

		DeleteItemResponse deleteItemResponse = deleteItemMapper.convertTo(id,
				true, "Delete item is success.");
		logger.debug("Leaving deleteItem(deleteItemRequest={})",
				deleteItemRequest);
		return deleteItemResponse;
	}

}
