package my.com.myriadeas.integral.circulation.service;

import java.util.Date;
import java.util.List;

import my.com.myriadeas.integral.circulation.CirculationTransactionFlag;
import my.com.myriadeas.integral.circulation.facade.AbstractFacadeService;
import my.com.myriadeas.integral.circulation.request.AbstractCirculationRequest;
import my.com.myriadeas.integral.data.jpa.domain.CirculationTransaction;
import my.com.myriadeas.integral.data.jpa.domain.Item;
import my.com.myriadeas.integral.data.jpa.domain.Officer;
import my.com.myriadeas.integral.data.jpa.repositories.PatronRepository;
import my.com.myriadeas.integral.data.jpa.repositories.impl.CirculationTransactionRepositoryImpl;
import my.com.myriadeas.integral.data.jpa.repositories.impl.ItemRepositoryImpl;
import my.com.myriadeas.integral.publisher.Publisher;
import my.com.myriadeas.integral.security.jpa.SecurityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

/**
 * A base class class for circulation process
 * 
 * @author hutingung
 * 
 */
public abstract class AbstractCirculationService extends AbstractFacadeService implements CirculationService {

	private TitleRetrieverIntf titleRetriever;

	protected ItemRepositoryImpl itemRepo;

	protected CirculationTransactionRepositoryImpl circulationTransactionRepo;

	protected PatronRepository patronRepo;

	protected SecurityService securityService;


	@Autowired
	protected Publisher publisher;

	@Autowired
	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}

	@Autowired
	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	@Autowired
	public void setPatronRepo(PatronRepository patronRepo) {
		this.patronRepo = patronRepo;
	}

	@Autowired
	public void setItemRepo(ItemRepositoryImpl itemRepo) {
		this.itemRepo = itemRepo;
	}

	@Autowired
	public void setCirculationRepo(CirculationTransactionRepositoryImpl circulationTransactionRepo) {
		this.circulationTransactionRepo = circulationTransactionRepo;
	}

	@Autowired
	public void setTitleRetriever(TitleRetrieverIntf titleRetriever) {
		this.titleRetriever = titleRetriever;
	}

	protected Officer getOfficer() {
		return securityService.getLoginUser();
	}

	protected Officer getOfficer(AbstractCirculationRequest request) {
		return securityService.getLoginUser();
	}

	protected String getTitle(Item item) {
		return titleRetriever.getTitle(item);
	}

	protected Date getTransactionDate(AbstractCirculationRequest request) {
		return new Date();
	}

	// TODO - Cicirc should tie to item model. one to one. Cicirc can be null
	// if there is never circulation process.
	protected CirculationTransaction getCirculationTransaction(Item item) {
		Assert.notNull(item);
		List<CirculationTransaction> circulationTransactions = null;
		if (item.isCirculated() || item.isRenew()) {
			circulationTransactions = circulationTransactionRepo.findByPatronAndItemAndFlag(
					item.getPatron(), item,
					CirculationTransactionFlag.CHARGED);
		} else if (item.isAvailable()) {
			return null;
		}
		Assert.notEmpty(circulationTransactions, "circulationTransaction is empty");
		return circulationTransactions.get(0);
	}

	protected Item getItem(AbstractCirculationRequest request) {
		Assert.notNull(request);
		return itemRepo.findByItemIdentifier(request.getItemIdentifier());
	}
}
