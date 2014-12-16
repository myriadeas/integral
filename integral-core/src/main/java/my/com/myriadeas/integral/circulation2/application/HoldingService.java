package my.com.myriadeas.integral.circulation2.application;

public interface HoldingService {

	public Long newHolding(NewHoldingCommand command);

	public Long release(ReleaseHoldingCommand command);
}
