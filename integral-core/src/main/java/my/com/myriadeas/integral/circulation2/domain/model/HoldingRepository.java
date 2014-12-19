package my.com.myriadeas.integral.circulation2.domain.model;

public interface HoldingRepository {

	public Holding save(Holding holding);
	
	public Holding findByItemIdentifier(String itemIdentifier);
}
