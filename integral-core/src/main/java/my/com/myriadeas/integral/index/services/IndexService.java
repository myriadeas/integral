package my.com.myriadeas.integral.index.services;

public interface IndexService {

	public void index(String marc, String resourceDescriptorId);

	public void unindex(String resourceDescriptorId);
}
