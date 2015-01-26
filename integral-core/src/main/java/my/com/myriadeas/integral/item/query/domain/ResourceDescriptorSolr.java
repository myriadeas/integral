package my.com.myriadeas.integral.item.query.domain;

import org.springframework.data.annotation.Id;

public class ResourceDescriptorSolr {

	@Id
	private String id;

	public ResourceDescriptorSolr() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}