package my.com.myriadeas.integral.circulation2.application;

public interface BorrowerService {
	
	public Long newBorrower(NewBorrowerCommand newBorrowerCommand);
	
	public Long registerBorrower(RegisterBorrowerCommand registerBorrowerCommand);

}
