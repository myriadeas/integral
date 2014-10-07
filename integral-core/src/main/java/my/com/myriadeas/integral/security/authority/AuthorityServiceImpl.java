package my.com.myriadeas.integral.security.authority;

import org.springframework.util.Assert;

public class AuthorityServiceImpl {

	public void traverse(Authority authority) {
		Assert.notNull(authority);
		if (authority.getAuthorities() != null) {
			for (Authority _authority : authority.getAuthorities()) {
				traverse(_authority);
				authority.getGrantedAuthorities().addAll(
						_authority.getGrantedAuthorities());
			}
		}
	}
}
