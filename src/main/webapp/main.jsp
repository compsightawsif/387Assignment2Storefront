<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.model.Product" %>
<%@ page import="com.dao.ProductDao" %>
<%@ page import="com.dao.CartDao" %>
<%@ page import="com.connection.DBConnection" %><!DOCTYPE html>
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
<h1>Welcome to our E-Commerce Store</h1>
<div id="cart">
    <h2>Shopping Cart</h2>
    <ul id="cart-items">
    </ul>
</div>

<%
    ProductDao productDAO = new ProductDao(DBConnection.getConnection()); // Initialize the ProductDAO with database connection
    List<Product> products = productDAO.getAllProducts(); // Retrieve the products from the database
%>

<div id="products">
    <h2>Products</h2>
    <% for (Product product : products) { %>
    <div class="product">
        <h3><%= product.getName()%></h3>
        <p><%= product.getPrice()%></p>
        <form method="post" action="/cart/add">
            <input type="hidden" name="productId" value="123">
            <input type="submit" value="Add to Cart">
        </form>
<%--        <button onclick="addToCart(<%= product.getName()%>, <%= product.getPrice()%>)">Add to Cart</button>--%>
        <button onclick="location.href = 'product-details.jsp?product=<%= product.getName()%>'">View Details</button>
    </div>
    <%}%>
</div>

<script>
    function addToCart(productName, price) {
        // Create a new list item for the cart
        const cartItem = document.createElement('li');
        cartItem.textContent = `${productName} - $${price}`;

        // Add the item to the cart
        const cartItemsList = document.getElementById('cart-items');
        cartItemsList.appendChild(cartItem);
    }
</script>
    <%@include file="includes/footer.jsp" %>
</body>
</html>
