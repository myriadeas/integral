package my.com.myriadeas.integral.security;

import java.io.UnsupportedEncodingException;

import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.Assert;

public class SimpleUserDetailsService implements
		AuthenticationUserDetailsService<CasAssertionAuthenticationToken> {

	private String integralMysticService;

	@Override
	public UserDetails loadUserDetails(CasAssertionAuthenticationToken token)
			throws UsernameNotFoundException {
		final String targetUrl = integralMysticService + "/repository/";
		final String proxyTicket = token.getAssertion().getPrincipal()
				.getProxyTicketFor(targetUrl);
		Assert.notNull(proxyTicket, "Can't grant proxy ticket. Login Failed");
		String serviceUrl;
		try {
			serviceUrl = targetUrl + "?ticket="
					+ java.net.URLEncoder.encode(proxyTicket, "UTF-8");
			org.jasig.cas.client.util.CommonUtils.getResponseFromServer(
					serviceUrl, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new UsernameNotFoundException("failed to login. e", e);
		}
		return new SimpleUserDetails(token.getName(), proxyTicket, this.integralMysticService);
	}

	public void setIntegralMysticService(String integralMysticService) {
		this.integralMysticService = integralMysticService;
	}

}
