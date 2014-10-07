<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//proxyTicket could be reused to make calls to the CAS service even if the
// target url differs

//final org.springframework.security.cas.authentication.CasAuthenticationToken token = (org.springframework.security.cas.authentication.CasAuthenticationToken) request.getUserPrincipal();
final org.springframework.security.cas.authentication.CasAuthenticationToken token = (org.springframework.security.cas.authentication.CasAuthenticationToken) org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();
String targetUrl = "http://localhost/integral-mystic/repository/";
final String proxyTicket = token.getAssertion().getPrincipal().getProxyTicketFor(targetUrl);
final String serviceUrl = targetUrl+"?ticket=" + java.net.URLEncoder.encode(proxyTicket, "UTF-8");
final String username = token.getName();
String proxyResponse = org.jasig.cas.client.util.CommonUtils.getResponseFromServer(serviceUrl, "UTF-8");
%>
{
    "ticket": "<%= proxyTicket %>",
    "serviceUrl": "http://127.0.0.1:9000/integral-mystic",
    "username" : "<%= username %>"
}