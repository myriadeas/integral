package my.com.myriadeas.cas.adaptors.jdbc.integral;

import static org.junit.Assert.*;

import java.util.regex.Pattern;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class BCryptPasswordEncoderTest {

	@Test
	public void testHashedPassword() {

		String rawPassword = "arlina";
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(rawPassword);

		System.out.println(hashedPassword);
		assertTrue(passwordEncoder.matches(rawPassword, hashedPassword));

	}

	@Test
	public void testPattern() {

		Pattern BCRYPT_PATTERN = Pattern
				.compile("\\A\\$2a?\\$\\d\\d\\$[./0-9A-Za-z]{53}");
		String correctHashedPassword = "$2a$10$31jWF7BSmAzOqDTamLdP6OvArp1okXSpJ26HZfrR5333z7IifMfO.";
		String incorrectHashedPassword = "abcdefghijklmnnoopqrdtsuu.";

		assertTrue(BCRYPT_PATTERN.matcher(correctHashedPassword).matches());
		assertFalse(BCRYPT_PATTERN.matcher(incorrectHashedPassword).matches());

	}
}
