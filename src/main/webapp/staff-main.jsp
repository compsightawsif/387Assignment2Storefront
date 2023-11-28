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
<%
    ProductDao productDAO = new ProductDao(DBConnection.getConnection());
    List<Product> products = productDAO.getAllProducts();
%>

<div id="products">
    <h2>Product List</h2>
    <% for (Product product : products) { %>
    <div class="product">
        <a href="product-details.jsp?slug=<%= product.getUrlslug()%>"><%= product.getName()%></a>
        <p><%= product.getPrice()%></p>
    </div>
    <%}%>
</div>
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
        OrderDao orderDAO = new OrderDao(DBConnection.getConnection());
        List<Order> orders = orderDAO.getAllOrders();

        for (Order order : orders) {
    %>
    <tr>
        <td><a href="order-details.jsp?orderID=<%= order.getId() %>"><%= order.getId() %></a></td>
        <td><%= order.getUserId() %></td>
        <td><%= order.getOrderDate() %></td>
        <td><%= order.getStatus() %></td>
        <td><%= order.getTrackingNumber() %></td
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
        <label for="productIdToUpdate">Id:</label>
        <input type="number" id="productIdToUpdate" name="productIdToUpdate" required>
        <br>
        <label for="productNameToUpdate">Product Name:</label>
        <input type="text" id="productNameToUpdate" name="productNameToUpdate" required>
        <br>
        <label for="productDescriptionToUpdate">Product Description:</label>
        <input type="text" id="productDescriptionToUpdate" name="productDescriptionToUpdate" required>
        <br>
        <label for="productPriceToUpdate">Product Price:</label>
        <input type="number" id="productPriceToUpdate" step=".01" name="productPriceToUpdate" required>
        <br>
        <label for="productSKUToUpdate">Product SKU:</label>
        <input type="text" id="productSKUToUpdate" name="productSKUToUpdate" required>
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
    <br>
    <h2>Update User Permissions</h2>
    <form action="user/change-permission" method="post">
        <div class="form-group">
            <label for="userPasscode">User Passcode:</label>
            <input type="text" class="form-control" id="userPasscode" name="userPasscode" required>
        </div>
        <div class="form-group">
            <label for="role">Select Role:</label>
            <select class="form-control" id="role" name="role" required>
                <option value="Staff">Staff</option>
                <option value="Customer">Customer</option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Update Permissions</button>
    </form>
    <br>
    <h2>Update User Passcode</h2>
    <form action="user/change-passcode" method="post">
        <div class="form-group">
            <label for="userId">User ID:</label>
            <input type="text" class="form-control" id="userId" name="userId" required>
        </div>
        <div class="form-group">
            <label for="userPasscodeToChange">Enter New User Passcode:</label>
            <input type="text" class="form-control" id="userPasscodeToChange" name="userPasscodeToChange" required>
            <% if (request.getAttribute("errorMessage") != null) { %>
            <div style="color: red;">
                <%= request.getAttribute("errorMessage") %>
            </div>
            <% } %>
        </div>
        <button type="submit" class="btn btn-primary">Update Passcode</button>
    </form>
</div>
<%--<%@include file="includes/footer.jsp" %>--%>
</body>
</html>
