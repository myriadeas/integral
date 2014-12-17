package my.com.myriadeas.integral.circulation2.application;

import javax.transaction.Transactional;

import my.com.myriadeas.integral.circulation2.domain.model.Borrower;
import my.com.myriadeas.integral.circulation2.infrastructure.jpa.BorrowerRepositoryImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("borrowerServiceImpl")
public class BorrowerServiceImpl implements BorrowerService {
	
	private static final Logger logger = LoggerFactory
			.getLogger(BorrowerServiceImpl.class);
	
	private BorrowerRepositoryImpl borrowerRepository;

	@Transactional
	public Long newBorrower(NewBorrowerCommand newBorrowerCommand) {
		logger.debug("Entering newBorrower(newBorrowerCommand={})", newBorrowerCommand);
		Borrower borrower = new Borrower(newBorrowerCommand.getUsername(), newBorrowerCommand.getUserId(), null);
		borrowerRepository.save(borrower);
		
		logger.debug("Leaving newBorrower()", newBorrowerCommand);
		return borrower.getId();
	}

	@Override
	public Long registerBorrower(RegisterBorrowerCommand registerBorrowerCommand) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
