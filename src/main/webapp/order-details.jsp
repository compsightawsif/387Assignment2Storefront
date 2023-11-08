<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.model.Order" %>
<%@ page import="com.dao.OrderDao" %>
<%@ page import="com.connection.DBConnection" %>
<html>
<head>
    <title>Order Details</title>
    <%@include file="includes/header.jsp" %>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
<c:if test="${not empty param.orderID}">
    <%
        int orderID = Integer.parseInt(request.getParameter("orderID"));
        OrderDao orderDAO = new OrderDao(DBConnection.getConnection());
        Order order = orderDAO.getOrderByID(orderID);

    %>
    <h2>Order ID: <%= order.getId() %></h2>
    <p>Order Date: <%= order.getOrderDate() %></p>
    <p>Status: <%= order.getStatus() %></p>
    <p>Tracking Number: <%= order.getTrackingNumber() %></p>

    <%
    %>
</c:if>
<div class="back-link">
    <a href="staff-main.jsp">Back to Staff Main Page</a>
</div>
</body>
</html>
