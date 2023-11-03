<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.model.Product" %>
<%@ page import="com.dao.ProductDao" %>
<%@ page import="com.connection.DBConnection" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Staff Dashboard</title>
    <%@include file="includes/header.jsp" %>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f3f3f3;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
        }
        h1, h2 {
            color: #333;
        }
        form {
            margin-top: 20px;
        }
        label {
            display: block;
            margin-bottom: 5px;
        }
        input[type="text"], input[type="number"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }
        button {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 3px;
            cursor: pointer;
        }
        button:hover {
            background-color: #0056b3;
        }
    </style>
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

    <!-- Order Shipping Form -->
    <h2>Ship an Order</h2>
    <form action="shipOrderServlet" method="post">
        <label for="orderID">Order ID:</label>
        <input type="number" id="orderID" name="orderID" required>
        <br>
        <button type="submit">Ship Order</button>
    </form>
</div>
</body>
</html>
