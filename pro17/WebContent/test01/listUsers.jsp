<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="java.util.*" %>
<%@ page import="sec01.ex01.*" %>
<%
	UserDAO dao =  new UserDAO();
	List<UserVO> list = dao.listUsers();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	for (int i=0; i<list.size(); i++) {
		UserVO vo = new UserVO();
		vo = list.get(i);
%>
		<%=vo.getIau_emp_no() %> <br>
		<%=vo.getIau_penl_nm() %> <br>
<%
	}
%>
</body>
</html>