package my.com.myriadeas.integral.cataloguing2.interfaces.facade.request;

public class DeleteResourceDescriptorRequest {

	private Long id;

	public DeleteResourceDescriptorRequest(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
