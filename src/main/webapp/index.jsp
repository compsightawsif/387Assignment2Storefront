<%@ page import="com.connection.DBConnection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:forward page="login.jsp" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% out.print(DBConnection.getConnection()); %>
	<%-- <%@include file="includes/footer.jsp" %>  --%>
</body>
</html>