package my.com.myriadeas.integral.index.interfaces.facade;


public interface IndexFacade {

	public IndexResponseDTO index(IndexRequestDTO request);
	
	public UnindexResponseDTO unindex(UnindexRequestDTO request);
}
