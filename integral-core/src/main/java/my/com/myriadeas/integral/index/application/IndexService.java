package my.com.myriadeas.integral.index.application;

public interface IndexService {

	public void index(IndexCommand command);

	public void unindex(UnindexCommand command);
}
