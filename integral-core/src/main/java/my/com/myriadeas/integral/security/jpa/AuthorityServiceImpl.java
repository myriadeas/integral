package my.com.myriadeas.integral.security.jpa;

import java.util.HashMap;
import java.util.Map;

import my.com.myriadeas.integral.data.jpa.domain.Permission;
import my.com.myriadeas.integral.data.jpa.repositories.impl.PermissionRepositoryImpl;
import my.com.myriadeas.integral.security.authority.Authority;
import my.com.myriadeas.integral.security.authority.AuthorityImpl;
import my.com.myriadeas.integral.security.authority.AuthorityService;
import my.com.myriadeas.integral.security.model.SecurityPermission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service("authorityServiceImpl")
public class AuthorityServiceImpl implements AuthorityService {

	@Autowired
	private PermissionRepositoryImpl permissionRepo;

	private Map<SecurityPermission, Authority> cachedAuthorities = null;

	@Override
	public Authority getAuthority(SecurityPermission permission) {
		if (cachedAuthorities.containsKey(permission)) {
			Authority authority = cachedAuthorities.get(permission);
			if (authority.getAuthorities().size() > authority
					.getGrantedAuthorities().size() - 1) {
				traverse(authority);
			}
		}
		return cachedAuthorities.get(permission);
	}

	protected void traverse(Authority authority) {
		Assert.notNull(authority);
		if (authority.getAuthorities() != null) {
			for (Authority _authority : authority.getAuthorities()) {
				traverse(_authority);
				authority.getGrantedAuthorities().addAll(
						_authority.getGrantedAuthorities());
			}
		}
	}

	@Override
	public void populateAuthorities() {
		Authority authorityEditPatron = new AuthorityImpl("Edit Patron",
				"PERM_EDIT_PATRON", true);
		Authority authorityEditGlpatr = new AuthorityImpl("Edit GLPATR",
				"PERM_EDIT_GLPATR", false);
		Authority authorityViewPatron = new AuthorityImpl("View Patron",
				"PERM_VIEW_PATRON", true);
		Authority authorityViewGlpatr = new AuthorityImpl("View Glpatr",
				"PERM_VIEW_GLPATR", false);
		Authority authorityViewGltown = new AuthorityImpl("View Gltown",
				"PERM_VIEW_GLTOWN", false);
		authorityViewPatron.addAuthority(authorityViewGlpatr).addAuthority(
				authorityViewGltown);
		saveAuthority(authorityViewPatron);
		authorityEditPatron.addAuthority(authorityViewPatron).addAuthority(
				authorityEditGlpatr);
		saveAuthority(authorityEditPatron);

	}

	protected void saveAuthority(Authority authority) {
		if (!authority.isExposed()) {
			return;
		}
		if (cachedAuthorities == null) {
			cachedAuthorities = new HashMap<SecurityPermission, Authority>();
		}
		Permission permissions = permissionRepo
				.findByPermission(authority.getAttribute());
		if (permissions == null) {
			permissions = new Permission(authority.getAttribute(),
					authority.getName());
		}
		permissionRepo.save(permissions);
		cachedAuthorities.put(permissions, authority);

	}

	@Override
	public void afterPropertiesSet() throws Exception {
		//TODO - don't populate authorities
		//populateAuthorities();
	}

}
