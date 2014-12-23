package my.com.myriadeas.integral.circulation2.application;

import java.util.HashMap;
import java.util.Map;

import my.com.myriadeas.integral.circulation2.domain.model.Borrower;
import my.com.myriadeas.integral.circulation2.domain.model.BorrowerRepository;
import my.com.myriadeas.integral.circulation2.domain.model.NewBorrowerCreated;
import my.com.myriadeas.integral.circulation2.domain.model.PatronCategory;
import my.com.myriadeas.integral.circulation2.domain.model.PatronCategoryRepository;
import my.com.myriadeas.integral.core.domain.model.DomainEvent;
import my.com.myriadeas.integral.core.publisher.Publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service("borrowerServiceImpl")
public class BorrowerServiceImpl implements BorrowerService {

	private static final Logger logger = LoggerFactory
			.getLogger(BorrowerServiceImpl.class);

	private BorrowerRepository borrowerRepository;

	private PatronCategoryRepository patronCategoryRepository;

	private Publisher publisher;

	@Transactional
	public Long newBorrower(NewBorrowerCommand newBorrowerCommand) {
		logger.debug("Entering newBorrower(newBorrowerCommand={})",
				newBorrowerCommand);
		Borrower borrower = new Borrower(newBorrowerCommand.getUsername(),
				newBorrowerCommand.getUserId(), null);
		this.borrowerRepository.save(borrower);
		Map<String, DomainEvent> events = new HashMap<String, DomainEvent>();
		DomainEvent event = new NewBorrowerCreated(borrower.getUsername(),
				borrower.getUserId());
		events.put("NewBorrowerCreated", event);
		publisher.publish(events);
		logger.debug("Borrower with username={} created",
				borrower.getUsername());
		logger.debug("Leaving newBorrower().");
		return borrower.getId();
	}

	@Transactional
	public Long registerBorrower(RegisterBorrowerCommand registerBorrowerCommand) {
		logger.debug("Entering newBorrower(registerBorrowerCommand={})",
				registerBorrowerCommand);
		PatronCategory patronCategory = this.patronCategoryRepository
				.findByCode(registerBorrowerCommand.getPatronCategoryCode());
		logger.debug("PatronCategory={}", patronCategory);
		logger.debug("username={}", registerBorrowerCommand.getUsername());
		Borrower borrower = this.borrowerRepository
				.findByUsername(registerBorrowerCommand.getUsername());
		logger.debug("Borrower={}", borrower);
		Assert.notNull(borrower);
		borrower.assignPatronCategory(patronCategory);
		borrowerRepository.save(borrower);
		logger.debug("Leaving newBorrower().");
		return borrower.getId();
	}

	@Autowired
	public void setBorrowerRepository(BorrowerRepository borrowerRepository) {
		this.borrowerRepository = borrowerRepository;
	}

	@Autowired
	public void setPatronCategoryRepository(
			PatronCategoryRepository patronCategoryRepository) {
		this.patronCategoryRepository = patronCategoryRepository;
	}

	@Autowired
	@Qualifier("circulationPublisher")
	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

}
