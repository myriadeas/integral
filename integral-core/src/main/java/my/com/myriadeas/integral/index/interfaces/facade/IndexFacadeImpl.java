package my.com.myriadeas.integral.index.interfaces.facade;

import my.com.myriadeas.integral.index.application.IndexCommand;
import my.com.myriadeas.integral.index.application.IndexService;
import my.com.myriadeas.integral.index.application.UnindexCommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("indexFacadeImpl")
public class IndexFacadeImpl implements IndexFacade {

	private IndexService service;

	@Override
	public IndexResponseDTO index(IndexRequestDTO request) {
		IndexCommand command = new IndexCommand(request.getMarc(),
				request.getResourceDescriptorId());
		service.index(command);
		return new IndexResponseDTO();
	}

	@Override
	public UnindexResponseDTO unindex(UnindexRequestDTO request) {
		UnindexCommand command = new UnindexCommand(
				request.getResourceDescriptorId());
		service.unindex(command);
		return new UnindexResponseDTO();
	}
	
	@Autowired
	public void setIndexService(IndexService service) {
		this.service = service;
	}

}
