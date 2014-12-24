package my.com.myriadeas.integral.circulation2.application;

import my.com.myriadeas.integral.core.domain.model.DomainEvent;
import my.com.myriadeas.integral.core.listener.EventListener;
import my.com.myriadeas.integral.identityaccess.domain.model.GroupAdded;
import my.com.myriadeas.integral.identityaccess.domain.model.UserAddedToGroup;
import my.com.myriadeas.integral.identityaccess.domain.model.UserRegistered;

import org.apache.camel.Consume;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userAddedToGroupListener")
public class UserAddedToGroupListener implements EventListener {

	private static final Logger logger = LoggerFactory
			.getLogger(UserAddedToGroupListener.class);

	private BorrowerService borrowerService;

	@Override
	@Consume(uri = "vm:identityAccess.userAddedToGroup?multipleConsumers=true")
	public void listen(DomainEvent domainEvent) {
		logger.debug("Entering listen(domainEvent={}) ", domainEvent);
		UserAddedToGroup userAddedToGroup = (UserAddedToGroup) domainEvent;
		if (userAddedToGroup.getGroupName().equals("GROUP_PATRON")) {
			NewBorrowerCommand newBorrowerCommand = new NewBorrowerCommand(
					userAddedToGroup.getUserName(),
					userAddedToGroup.getUserId());
			borrowerService.newBorrower(newBorrowerCommand);
		}
		logger.debug("Leaving listen() ");
	}

	@Autowired
	public void setBorrowerService(BorrowerService borrowerService) {
		this.borrowerService = borrowerService;
	}
}
