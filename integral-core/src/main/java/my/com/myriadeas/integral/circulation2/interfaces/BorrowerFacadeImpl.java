package my.com.myriadeas.integral.circulation2.interfaces;

import my.com.myriadeas.integral.circulation2.application.BorrowerService;
import my.com.myriadeas.integral.circulation2.application.RegisterBorrowerCommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("borrowerFacadeImpl")
public class BorrowerFacadeImpl implements BorrowerFacade {

	private BorrowerService borrowerService;

	@Autowired
	public void setBorrowerService(BorrowerService borrowerService) {
		this.borrowerService = borrowerService;
	}

	@Override
	public RegisterBorrowerResponseDTO register(
			RegisterBorrowerRequestDTO registerBorrowerRequestDTO) {
		RegisterBorrowerCommand registerBorrowerCommand = new RegisterBorrowerCommand(
				registerBorrowerRequestDTO.getUsername(),
				registerBorrowerRequestDTO.getPatronCategoryCode());

		Long id = borrowerService.registerBorrower(registerBorrowerCommand);
		RegisterBorrowerResponseDTO registerBorrowerResponseDTO = new RegisterBorrowerResponseDTO(
				id, true, "Success register borrower");
		return registerBorrowerResponseDTO;
	}

}
