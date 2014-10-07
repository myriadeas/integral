package my.com.myriadeas.integral.security.jpa.domain;

import java.util.HashSet;
import java.util.Set;

import my.com.myriadeas.integral.data.jpa.domain.Officer;
import my.com.myriadeas.integral.data.jpa.repositories.impl.OfficerRepositoryImpl;
import my.com.myriadeas.integral.security.IntegralUserDetails;
import my.com.myriadeas.integral.security.IntegralUserDetailsService;
import my.com.myriadeas.integral.security.authority.Authority;
import my.com.myriadeas.integral.security.authority.AuthorityService;
import my.com.myriadeas.integral.security.model.SecurityPermission;
import my.com.myriadeas.integral.security.model.SecurityRole;
import my.com.myriadeas.integral.security.model.SecurityUser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("integralUserDetailsServiceJpaImpl")
public class IntegralUserDetailsServiceJpaImpl implements IntegralUserDetailsService,
		AuthenticationUserDetailsService<CasAssertionAuthenticationToken> {

	private static final Logger logger = LoggerFactory
			.getLogger(IntegralUserDetailsServiceJpaImpl.class);

	@Autowired
	private OfficerRepositoryImpl userRepo;

	@Autowired
	private AuthorityService authorityService;

	@Override
	public UserDetails loadUserDetails(CasAssertionAuthenticationToken token)
			throws UsernameNotFoundException {
		return loadUserByUsername(token.getName());
	}

	@Override
	public IntegralUserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		logger.debug("loadUserByUsername={}", username);
		Officer user = userRepo.findByUsername(username);
		logger.debug("user={}", user);
		if (user == null) {
			logger.error("user not found");
			throw new UsernameNotFoundException("user not found. username="
					+ username);
		}
		Set<GrantedAuthority> grantedAuthorities = getGrantedAuthorities(user);
		IntegralUserDetails userDetails = new IntegralUserDetailsGlpatrImpl(user,
				username, user.getPassword(), grantedAuthorities);
		return userDetails;
	}

	@Override
	public Set<GrantedAuthority> getGrantedAuthorities(SecurityUser user) {
		logger.debug("Get granted authorities={} ", user);
		Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
		logger.debug("Roles={} ", user.getSecurityRoles());
		for (SecurityRole role : user.getSecurityRoles()) {
			logger.debug("Role={} ", role);
			logger.debug("Permissions={} ", role.getSecurityPermissions());
			for (SecurityPermission permission : role.getSecurityPermissions()) {
				logger.debug("Permission={} ", permission);
				Authority authority = authorityService.getAuthority(permission);
				logger.debug("authority={} ", authority);
				grantedAuthorities.addAll(authority.getGrantedAuthorities());
			}
			grantedAuthorities.add(role.getAuthority());
			logger.debug("grantedAuthorities={} ", grantedAuthorities);
		}

		logger.debug("roles/permission's grantedAuthorities={} ",
				grantedAuthorities);
		if (grantedAuthorities.isEmpty()) {
			grantedAuthorities.add(AuthorityUtils.createAuthorityList(
					"ROLE_GUEST").get(0));
		}
		logger.debug("final grantedAuthorities={} ", grantedAuthorities);
		return grantedAuthorities;
	}

}
