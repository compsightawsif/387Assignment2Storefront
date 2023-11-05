<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.model.Product" %>
<%@ page import="com.dao.ProductDao" %>
<%@ page import="com.model.Order" %>
<%@ page import="com.dao.OrderDao" %>
<%@ page import="com.connection.DBConnection" %><!DOCTYPE html>
<html>
<head>
    <title>Product Details</title>
    <%@include file="includes/header.jsp" %>
</head>
<body>

<%@include file="includes/navbar.jsp" %>

<div class="container mt-4">
    <h2>Product Details</h2>
    <p>Product Name: Product 1</p>
    <p>Price: $19.99</p>
    <!-- Include more product details here -->

    <!-- Role-based button display -->
    <c:choose>
        <c:when test="${userRole == 'staff'}">
            <a href="modifyProduct.jsp" class="btn btn-primary">Modify Product</a>
        </c:when>
        <c:otherwise>
            <a href="#" class="btn btn-secondary" disabled>Modify Product</a>
        </c:otherwise>
    </c:choose>
</div>

<%@include file="includes/footer.jsp" %>
</body>
</html>
