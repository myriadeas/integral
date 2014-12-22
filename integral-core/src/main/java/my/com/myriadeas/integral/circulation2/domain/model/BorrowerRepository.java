package my.com.myriadeas.integral.circulation2.domain.model;

public interface BorrowerRepository {

	public Borrower findByUsername(String username);

	public Borrower save(Borrower Borrower);
}
