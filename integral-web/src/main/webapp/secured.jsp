secured

<%//proxyTicket could be reused to make calls to the CAS service even if the
// target url differs

//final org.springframework.security.cas.authentication.CasAuthenticationToken token = (org.springframework.security.cas.authentication.CasAuthenticationToken) request.getUserPrincipal();
final org.springframework.security.cas.authentication.CasAuthenticationToken token = (org.springframework.security.cas.authentication.CasAuthenticationToken) org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();
String targetUrl = "http://localhost/integral-mystic/repository/";
final String proxyTicket = token.getAssertion().getPrincipal().getProxyTicketFor(targetUrl);
//String proxyTicket = ((my.com.myriadeas.integral.security.SimpleUserDetails)token.getPrincipal()).getProxyTicket();
out.println(token.getCredentials());
out.println("<br/>");
out.println(token.getAssertion());
out.println("<br/>");
out.println("principal class=" + ((my.com.myriadeas.integral.security.SimpleUserDetails)token.getPrincipal()).getProxyTicket());
out.println("<br/>");
out.println("proxyTicket=" + proxyTicket);


final String serviceUrl = targetUrl+"?ticket=" + java.net.URLEncoder.encode(proxyTicket, "UTF-8");
String proxyResponse = org.jasig.cas.client.util.CommonUtils.getResponseFromServer(serviceUrl, "UTF-8");
out.println(proxyResponse);%>