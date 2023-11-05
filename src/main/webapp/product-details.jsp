<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.model.Product" %>
<%@ page import="com.dao.ProductDao" %>
<%@ page import="com.model.Order" %>
<%@ page import="com.dao.OrderDao" %>
<%@ page import="com.connection.DBConnection" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product Details</title>
</head>
<body>
<h1>Product Details</h1>

<div id="product-details">
    <h2 id="product-name"></h2>
    <p id="product-price"></p>
    <!-- Add more details here, e.g., product description, images, etc. -->
</div>

<a href="main.jsp">Back to Store</a>

<script>
    // Get the product name from the query parameter
    const urlParams = new URLSearchParams(window.location.search);
    const productName = urlParams.get('product');

    // Display the product details on the page
    if (productName) {
        const productNameElement = document.getElementById('product-name');
        const productPriceElement = document.getElementById('product-price');

        productNameElement.textContent = productName;
        // You can load more product details here, like description, images, etc.
    } else {
        // Handle the case where the product name is not provided in the query parameter.
    }
</script>
</body>
</html>
