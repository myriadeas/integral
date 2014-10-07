<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    try {
	org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	out.println("{ \"name\" : \"" + user.getUsername() + "\"}");
    } catch(Exception e) {
    	
    }
%>