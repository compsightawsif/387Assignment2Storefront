<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.model.Product" %>
<%@ page import="com.dao.ProductDao" %>
<%@ page import="com.connection.DBConnection" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>E-commerce Site</title>
    <%@include file="includes/header.jsp" %>
    <link rel="stylesheet" type="text/css" href="css/styles.css">

</head>
<body>
<%@include file="includes/navbar.jsp" %>

<body>
<%
    ProductDao productDAO = new ProductDao(DBConnection.getConnection());
    List<Product> products = productDAO.getAllProducts();
%>
<h1>Welcome to our E-Commerce Store</h1>


<div class="container-fluid mt-4">
    <h2 class="mb-4">Product List</h2>

    <div class="row">
        <% for (Product product : products) { %>
        <div class="col-md-4 mb-4">
            <div class="card">
                <div class="card-body">
                    <img class="card-img-top" src="images/<%= product.getSku()%>.jpg"
                         alt="<%= product.getName()%> Image">
                    <h5 class="card-title"><%= product.getName()%>
                    </h5>
                    <p class="card-text"><%= product.getDescription()%>
                    </p>
                    <p class="card-text"><%= product.getPrice()%>
                    </p>
                    <form method="post" action="cart/products/<%= product.getUrlslug()%>">
                        <button type="submit" class="btn btn-primary mb-2">Add to Cart</button>
                    </form>
                    <a href="product-details.jsp?slug=<%= product.getUrlslug()%>" class="btn btn-secondary">View
                        Details</a>

                </div>
            </div>
        </div>
        <% } %>
    </div>
</div>

<%@include file="includes/footer.jsp" %>
</body>
</html>
