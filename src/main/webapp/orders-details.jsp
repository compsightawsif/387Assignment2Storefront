<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Order Details</title>
    <%@include file="includes/header.jsp" %>
</head>
<body>
<%@include file="includes/navbar.jsp" %>
<div class="container">
    <h1>Order Details</h1>

    <h2>Order ID: ${param.orderId}</h2>
    <!-- Display order details, e.g., products, quantities, prices, etc. -->
    <!-- You can retrieve this data from the database based on the order ID. -->
</div>
<%@include file="includes/footer.jsp" %>
</body>
</html>
