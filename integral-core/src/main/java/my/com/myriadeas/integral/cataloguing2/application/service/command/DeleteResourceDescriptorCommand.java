package my.com.myriadeas.integral.cataloguing2.application.service.command;

public class DeleteResourceDescriptorCommand {

	private Long id;

	public DeleteResourceDescriptorCommand(Long id) {
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
