package my.com.myriadeas.integral.circulation2.domain.model;

public interface PatronCategoryRepository {

	public PatronCategory findByCode(String code);
	
	public PatronCategory save(PatronCategory patronCategory);
}
