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

    if (orderId != null && !orderId.isEmpty()) {
        // Order ID is provided in the URL
        int orderIdValue = Integer.parseInt(orderId); // Assuming it's an integer
        // You can use orderIdValue in your logic
%>
<%
    int orderID = Integer.parseInt(request.getParameter("orderID"));
    OrderDao orderDAO = new OrderDao(DBConnection.getConnection()); // Initialize the ProductDAO with your database connection
    Order order = orderDAO.getOrderByID(orderID); // Retrieve the products from the database

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
<!-- Add your logic for when orderID is not provided -->
<%
    }
%>
<div class="back-link">
    <a href="staff-main.jsp">Back to Staff Main Page</a>
</div>
</body>
</html>
