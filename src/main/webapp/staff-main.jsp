<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.model.Product" %>
<%@ page import="com.dao.ProductDao" %>
<%@ page import="com.model.Order" %>
<%@ page import="com.dao.OrderDao" %>
<%@ page import="com.connection.DBConnection" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Staff Dashboard</title>
    <%@include file="includes/header.jsp" %>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
<h2>Product List</h2>
<table>
    <thead>
    <tr>
        <th>Name</th>
        <th>Description</th>
        <th>Price</th>
        <th>SKU</th>
        <th>Vendor</th>
        <th>urlSlug</th>
    </tr>
    </thead>
    <tbody>
    <%
        ProductDao productDAO = new ProductDao(DBConnection.getConnection()); // Initialize the ProductDAO with your database connection
        List<Product> products = productDAO.getAllProducts(); // Retrieve the products from the database

        for (Product product : products) {
    %>
    <tr>
        <td><%= product.getName() %></td>
        <td><%= product.getDescription() %></td>
        <td><%= product.getPrice() %></td>
        <td><%= product.getSku() %></td>
        <td><%= product.getVendor() %></td>
        <td><%= product.getUrlslug() %></td>
    </tr>
    <%
        }
    %>
    <br>
    <br>

    </tbody>
</table>
<p></p>
<table>
    <thead>
    <tr>
        <th>Order Id</th>
        <th>User Id</th>
        <th>Order Date</th>
        <th>Status</th>
        <th>Tracking Number</th>
    </tr>
    </thead>
    <tbody>
    <%
        OrderDao orderDAO = new OrderDao(DBConnection.getConnection()); // Initialize the ProductDAO with your database connection
        List<Order> orders = orderDAO.getAllOrders(); // Retrieve the products from the database

        for (Order order : orders) {
    %>
    <tr>
        <td><%= order.getId() %></td>
        <td><%= order.getUserId() %></td>
        <td><%= order.getOrderDate() %></td>
        <td><%= order.getStatus() %></td>
        <td><%= order.getTrackingNumber() %></td>
    </tr>
    <%
        }
    %>
    <br>
    <br>

    </tbody>
</table>
<div class="container">
    <h1>Welcome, Staff Member!</h1>

    <!-- Product Creation Form -->
    <h2>Create a New Product</h2>
    <form action="products/create" method="post">
        <label for="productName">Product Name:</label>
        <input type="text" id="productName" name="productName" required>
        <br>
        <label for="sku">SKU:</label>
        <input type="text" id="sku" name="sku" required>
        <br>
        <button type="submit">Create Product</button>
    </form>
    <p></p>
    <h2>Update a Product</h2>
    <form action="products/update" method="post">
        <label for="productSKUToUpdate">Product SKU:</label>
        <input type="text" id="productSKUToUpdate" name="productSKUToUpdate" required>
        <br>
        ---------------------------------------------------
        <label for="productNameToUpdate">Product Name:</label>
        <input type="text" id="productNameToUpdate" name="productNameToUpdate" required>
        <br>
        <label for="productDescriptionToUpdate">Product Description:</label>
        <input type="text" id="productDescriptionToUpdate" name="productDescriptionToUpdate" required>
        <br>
        <label for="productPriceToUpdate">Product Price:</label>
        <input type="number" id="productPriceToUpdate" step=".01" name="productPriceToUpdate" required>
        <br>
        <label for="productVendorToUpdate">Product Vendor:</label>
        <input type="text" id="productVendorToUpdate" name="productVendorToUpdate" required>
        <br>
        <label for="productSlugToUpdate">Product Url Slug:</label>
        <input type="text" id="productSlugToUpdate" name="productSlugToUpdate" required>
        <br>
        <button type="submit">Update Product</button>
    </form>
    <p></p>
    <h2>Ship an Order</h2>
    <form action="order/ship" method="post">
        <label for="orderID">Order ID:</label>
        <input type="number" id="orderID" name="orderID" required>
        <br>
        <label for="trackingNumber">Tracking Number:</label>
        <input type="text" id="trackingNumber" name="trackingNumber" required>
        <button type="submit">Ship Order</button>
    </form>
</div>
</body>
</html>
