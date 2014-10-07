package my.com.myriadeas.integral.security.jpa.config;

import static my.com.myriadeas.spring.core.util.SpringEnvironmentUtil.DEV;
import static org.junit.Assert.fail;

import java.util.HashSet;

import my.com.myriadeas.integral.circulation.config.IntegralCirculationConfigDev;
import my.com.myriadeas.integral.data.jpa.domain.Patron;
import my.com.myriadeas.integral.data.jpa.domain.Permission;
import my.com.myriadeas.integral.data.jpa.domain.Role;
import my.com.myriadeas.integral.data.jpa.repositories.impl.BranchRepositoryImpl;
import my.com.myriadeas.integral.data.jpa.repositories.impl.OfficerRepositoryImpl;
import my.com.myriadeas.integral.data.jpa.repositories.impl.PatronRepositoryImpl;
import my.com.myriadeas.integral.data.jpa.repositories.impl.PermissionRepositoryImpl;
import my.com.myriadeas.integral.data.jpa.repositories.impl.RoleRepositoryImpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@ActiveProfiles(DEV)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { SecurityConfigDatabaseProviderDev.class, IntegralCirculationConfigDev.class })
public class SecurityConfigDevTest {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	PatronRepositoryImpl glpatrRepo;

	@Autowired
	PermissionRepositoryImpl glpermissionRepo;

	@Autowired
	RoleRepositoryImpl glroleRepo;

	@Autowired
	BranchRepositoryImpl glbrncRepo;

	@Autowired
	private OfficerRepositoryImpl userRepo;

	@Test
	public void testMarker() {
	}

	// TODO - No longer a valid test. Removed validation from method.
	// @Test
	// @Transactional
	public void testAuthentication() {
		Authentication authRequest = null;

		try {
			authRequest = new UsernamePasswordAuthenticationToken("hutingung",
					"hutingung");
			SecurityContextHolder.getContext().setAuthentication(
					authenticationManager.authenticate(authRequest));

			// glpatrRepo.findAll(new PageRequest(0, 1));
			glpatrRepo.findAll();
			fail("should throw access denied exception");
		} catch (AccessDeniedException ade) {
			ade.printStackTrace();

		}

		Role role = new Role("ROLE_OFFICER", "Officer");
		role.setPermissions(new HashSet<Permission>(glpermissionRepo.findAll()));
		glroleRepo.save(role);
		Patron patron = glpatrRepo.findByUsername("limsyenie");
		patron.setRoles(new HashSet<Role>(glroleRepo.findAll()));
		glpatrRepo.saveAndFlush(patron);
		// Set a user account that will initially own all the created data
		authRequest = new UsernamePasswordAuthenticationToken("limsyenie",
				"limsyenie");
		SecurityContextHolder.getContext().setAuthentication(
				authenticationManager.authenticate(authRequest));
		glpatrRepo.findAll();
		authRequest = new UsernamePasswordAuthenticationToken("limsyenie2",
				"limsyenie2");
		try {
			authenticationManager.authenticate(authRequest);
			fail("should throw authentication exception");
		} catch (AuthenticationException ae) {
		}

		// SUPERUSER
		// Set a user account that will initially own all the created data
		authRequest = new UsernamePasswordAuthenticationToken("arlina",
				"arlina");
		SecurityContextHolder.getContext().setAuthentication(
				authenticationManager.authenticate(authRequest));
		glbrncRepo.findAll();

	}
}
