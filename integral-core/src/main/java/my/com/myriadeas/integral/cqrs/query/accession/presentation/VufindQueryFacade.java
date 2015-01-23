package my.com.myriadeas.integral.cqrs.query.accession.presentation;

import java.util.ArrayList;
import java.util.List;

import my.com.myriadeas.integral.cqrs.query.PaginatedResult;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("vufindQueryFacade")
public class VufindQueryFacade {

	private static final Logger logger = org.slf4j.LoggerFactory
			.getLogger(VufindQueryFacade.class);

	@Autowired
	SqlAccessionFinder sqlAccessionFinder;

	public List<VufindStatus> getStatus(String bibliographicId) {
		logger.debug("Entering getStatus(bibliographicId={})", bibliographicId);
		PaginatedResult<AccessionListItemDto> accessions = sqlAccessionFinder
				.findAccessionsByResourceDescriptorId(bibliographicId);
		logger.debug("List of accessions=()", accessions);
		List<VufindStatus> statuses = new ArrayList<VufindStatus>();
		for (AccessionListItemDto item : accessions.getItems()) {
			logger.debug("Perform conversion");
			VufindStatus status = new VufindStatus();
			status.setId(item.getResourceDescriptorId());
			status.setItem_id(item.getAccessionId());
			status.setStatus(item.getStatus().toString());
			statuses.add(status);
		}
		logger.debug("Leaving getStatus(). statuses={}", statuses);
		return statuses;
	}

	public List<List<VufindStatus>> getStatuses(List<String> bibliographicIds) {
		logger.debug("Entering getStatuses(bibliographicIds={})",
				bibliographicIds);
		List<List<VufindStatus>> statusesList = new ArrayList<List<VufindStatus>>();
		for (String bibliographicId : bibliographicIds) {
			List<VufindStatus> statuses = this.getStatus(bibliographicId);
			statusesList.add(statuses);
		}
		logger.debug("Leaving getStatuses(). statusesList={}", statusesList);
		return statusesList;
	}

	public List<VufindHolding> getHolding(String bibliographicId) {
		logger.debug("Entering getHolding(bibliographicId={})", bibliographicId);
		List<VufindHolding> holdings = new ArrayList<VufindHolding>();
		logger.debug("Getting list of item");
		PaginatedResult<AccessionListItemDto> accessions = sqlAccessionFinder
				.findAccessionsByResourceDescriptorId(bibliographicId);
		List<AccessionListItemDto> items = accessions.getItems();
		for (AccessionListItemDto item : items) {
			logger.debug("Perform conversion");
			VufindHolding holding = new VufindHolding();
			holding.setId(item.getResourceDescriptorId());
			holding.setItem_id(item.getAccessionId());
			holding.setStatus(item.getStatus().toString());
			holding.setBarcode(item.getAccessionId());
			holdings.add(holding);
			
		}
		logger.debug("Leaving getHolding(). holdings={}", holdings);
		return holdings;
	}

}
