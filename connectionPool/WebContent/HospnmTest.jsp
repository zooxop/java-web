<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="pool.TestDAO" %>
<%@ page import="java.util.List" %>
<%
	TestDAO dao = new TestDAO();
	String rtn = dao.openQuery("select * from IT_HOSPITAL");
	
	response.addHeader("Content-type", "text/xml");
%>
<?xml version="1.0" encoding="UTF-8"?>
<nurionXml>
	<resultCode>200</resultCode>
	<resultXml>
		<xml xmlns:s='uuid:BDC6E3F0-6DA3-11d1-A2A3-00AA00C14882'
			xmlns:dt='uuid:C2F41010-65B3-11d1-A29F-00AA00C14882'
			xmlns:rs='urn:schemas-microsoft-com:rowset'
			xmlns:z='#RowsetSchema'>

<s:Schema id='RowsetSchema'>
	<s:ElementType name='row' content='eltOnly' rs:updatable='true'>
	<%= rtn %>
		<s:extends type='rs:rowbase'/>
	</s:ElementType>
</s:Schema>
