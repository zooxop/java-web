<%@ page trimDirectiveWhitespaces="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="pool.TestDAO" %>
<%@ page import="java.util.List" %>
<%
	String resQuery = request.getParameter("strXml");
	TestDAO dao = new TestDAO();
	String rtn = dao.openQuery(resQuery);
	out.clear();
	response.addHeader("Content-type", "text/xml");
%>
<?xml version="1.0" encoding="UTF-8"?>
<%= rtn %>
