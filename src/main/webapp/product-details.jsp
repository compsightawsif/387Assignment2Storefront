<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.model.Product" %>
<%@ page import="com.dao.ProductDao" %>
<%@ page import="com.connection.DBConnection" %>
<html>
<head>
    <title>Order Details</title>
    <%@include file="includes/header.jsp" %>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
<c:if test="${not empty param.slug}">
    <%
        String slug = request.getParameter("slug");
        ProductDao productDAO = new ProductDao(DBConnection.getConnection());
        Product product = productDAO.getProductBySlug(slug);

    %>
    <h2>Product Name: <%= product.getName() %></h2>
    <p>Description: <%= product.getDescription() %></p>
    <p>Price: <%= product.getPrice() %></p>
    <p>Vendor: <%= product.getVendor() %></p>
    <p>SKU: <%= product.getSku() %></p>
    <p>Slug: <%= product.getUrlslug() %></p>

    <%
    %>
</c:if>
<div class="back-link">
    <a href="main.jsp">Back to Main Page</a>
</div>
</body>
</html>
