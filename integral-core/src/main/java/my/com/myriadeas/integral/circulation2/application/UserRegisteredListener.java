package my.com.myriadeas.integral.circulation2.application;

import my.com.myriadeas.integral.core.domain.model.DomainEvent;
import my.com.myriadeas.integral.core.listener.EventListener;
import my.com.myriadeas.integral.identityaccess.domain.model.UserRegistered;

import org.apache.camel.Consume;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userRegisteredListener")
public class UserRegisteredListener implements EventListener {

	private static final Logger logger = LoggerFactory
			.getLogger(UserRegisteredListener.class);

	private BorrowerService borrowerService;

	@Override
	@Consume(uri = "vm:identityaccess.userRegistered?multipleConsumers=true")
	public void listen(DomainEvent domainEvent) {
		logger.debug("Entering listen(domainEvent={}) ", domainEvent);
		UserRegistered userRegistered = (UserRegistered) domainEvent;
		NewBorrowerCommand newBorrowerCommand = new NewBorrowerCommand(
				userRegistered.getUsername(), userRegistered.getId());
		borrowerService.newBorrower(newBorrowerCommand);
		logger.debug("Leaving listen() ");
	}

	@Autowired
	public void setBorrowerService(BorrowerService borrowerService) {
		this.borrowerService = borrowerService;
	}
}
