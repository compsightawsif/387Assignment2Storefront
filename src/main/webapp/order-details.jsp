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
<%
    String orderId = request.getParameter("orderID");
    int orderIdValue;

    if (orderId != null && !orderId.isEmpty()) {
        // Order ID is provided in the URL
        orderIdValue = Integer.parseInt(orderId);
%>
<%
    OrderDao orderDAO = new OrderDao(DBConnection.getConnection());
    Order order = orderDAO.getOrderByID(orderIdValue);

%>
<h2>Order ID: <%= order.getId() %></h2>
<p>Order Date: <%= order.getOrderDate() %></p>
<p>Status: <%= order.getStatus() %></p>
<p>Tracking Number: <%= order.getTrackingNumber() %></p>

<%
%>
<%
} else {
    // Order ID is not provided in the URL
%>
<h2>Order ID is not provided in the URL</h2>
<%
    }
%>
<div class="back-link">
    <a href="staff-main.jsp">Back to Staff Main Page</a>
</div>
</body>
</html>
