<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.model.Product" %>
<%@ page import="com.dao.ProductDao" %>
<%@ page import="com.connection.DBConnection" %>
<%@ page import="com.model.User" %>
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
    <img src="images/<%= product.getSku()%>.jpg"
         alt="<%= product.getName()%> Image"m>
    <p>Description: <%= product.getDescription() %></p>
    <p>Price: <%= product.getPrice() %></p>
    <p>Vendor: <%= product.getVendor() %></p>
    <p>SKU: <%= product.getSku() %></p>
    <p>Slug: <%= product.getUrlslug() %></p>

    <%
    %>
</c:if>

<%
    User user = (User) request.getSession().getAttribute("auth");
    if (user.getRole().equals("Customer")) {
%>
<div class="back-link">
    <a href="main.jsp">Back to Main Page</a>
</div>
<%
    }else{
%>
<div class="back-link">
    <a href="staff-main.jsp">Back to Product Dashboard</a>
</div>

<%
    }
%>
</body>
</html>
