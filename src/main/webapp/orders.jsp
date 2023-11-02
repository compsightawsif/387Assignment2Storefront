<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Order History</title>
    <%@include file="includes/header.jsp" %>
</head>
<body>
<%@include file="includes/navbar.jsp" %>
<div class="container">
    <h1>Order History</h1>

    <table>
        <thead>
        <tr>
            <th>Order ID</th>
            <th>Order Date</th>
            <th>Total Amount</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <!-- Loop through the user's order history from the database -->
        <c:forEach items="${orderHistory}" var="order">
            <tr>
                <td>${order.orderId}</td>
                <td>${order.orderDate}</td>
                <td>${order.totalAmount}</td>
                <td><a href="order-details.jsp?orderId=${order.orderId}">View Details</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<%@include file="includes/footer.jsp" %>
</body>
</html>
