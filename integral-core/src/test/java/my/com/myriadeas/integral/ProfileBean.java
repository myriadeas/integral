package my.com.myriadeas.integral;

import org.springframework.stereotype.Service;

@Service("profileBean")
public class ProfileBean {

	private String profile;

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}
	
	
}
