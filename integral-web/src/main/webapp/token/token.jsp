<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
final org.springframework.security.cas.authentication.CasAuthenticationToken token = (org.springframework.security.cas.authentication.CasAuthenticationToken) org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();
String proxyTicket = ((my.com.myriadeas.integral.security.SimpleUserDetails)token.getPrincipal()).getProxyTicket();
String serviceUrl = ((my.com.myriadeas.integral.security.SimpleUserDetails)token.getPrincipal()).getServiceUrl();
String username = ((my.com.myriadeas.integral.security.SimpleUserDetails)token.getPrincipal()).getUsername();
%>
{
    "ticket": "<%= proxyTicket %>",
    "serviceUrl": "<%= serviceUrl%>",
    "username" : "<%= username %>"
}