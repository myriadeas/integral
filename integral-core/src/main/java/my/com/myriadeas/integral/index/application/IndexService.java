package my.com.myriadeas.integral.index.application;

public interface IndexService {

	public void index(String marc, String resourceDescriptorId);

	public void unindex(String resourceDescriptorId);
}
