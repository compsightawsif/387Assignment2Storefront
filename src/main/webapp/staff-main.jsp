<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.model.Product" %>
<%@ page import="com.dao.ProductDao" %>
<%@ page import="com.model.Order" %>
<%@ page import="com.dao.OrderDao" %>
<%@ page import="com.connection.DBConnection" %>
<%@ page import="java.util.Objects" %>
<%@ page import="com.dao.UserDao" %>
<%@ page import="com.model.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Staff Dashboard</title>
    <%@include file="includes/header.jsp" %>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
<h1>Admin Page</h1>
<%
    ProductDao productDAO = new ProductDao(DBConnection.getConnection());
    List<Product> products = productDAO.getAllProducts();
    OrderDao orderDAO = new OrderDao(DBConnection.getConnection());
    List<Order> orders = orderDAO.getAllOrders();
    UserDao userDao = new UserDao(DBConnection.getConnection());
    List<User> users = userDao.getAllUsers();
%>
<% String errorMessage = request.getParameter("errorMessage"); %>
<% if (errorMessage != null && !errorMessage.isEmpty()) { %>
<div style="color: red;">
    <%= errorMessage %>
</div>
<% } %>
<div class="container-fluid mt-4">
    <ul class="nav nav-tabs" id="myTabs">
        <li class="nav-item">
            <a class="nav-link active" id="users-tab" data-toggle="tab" href="#users">Users</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" id="orders-tab" data-toggle="tab" href="#orders">Orders</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" id="products-tab" data-toggle="tab" href="#products">Products</a>
        </li>
        <!-- Add more tabs as needed -->
    </ul>

    <div class="tab-content mt-2">
        <div class="tab-pane fade show active" id="users">
            <!-- Table for Category 1 -->
            <table class="table">
                <thead>

                <tr>
                    <th>User Id</th>
                    <th>User Passcode</th>
                    <th>Role</th>
                </tr>
                </thead>
                <tbody>
                <%
                    for (User user : users) {
                %>
                <tr>
                    <td><%= user.getId() %>
                    </td>
                    <form action="user/change-passcode?userId=<%= user.getId() %>" method="post">
                        <td><input type="text" class="form-control" value="<%= user.getPasscode() %>" minlength="4"
                                   maxlength="4" id="userPasscodeToChange" name="userPasscodeToChange" required>
                            <button type="submit" class="btn btn-primary">Update Passcode</button>
                        </td>
                    </form>
                    <form action="user/change-permission?userPasscode=<%= user.getPasscode() %>" method="post">
                        <td><select class="form-control" id="role" name="role" required>

                            <%
                                if (user.getRole().equals("Staff")) {
                            %>
                            <option value="Staff" selected>Staff</option>
                            <option value="Customer">Customer</option>
                            <%
                            } else {
                            %>
                            <option value="Staff">Staff</option>
                            <option value="Customer" selected>Customer</option>
                            <%
                                }
                            %>

                        </select>
                            <button type="submit" class="btn btn-primary">Update Role</button>
                        </td>

                    </form>

                </tr>
                <%
                    }
                %>
                </tbody>
            </table>
        </div>

        <div class="tab-pane fade" id="orders">
            <!-- Table for Category 2 -->
            <table class="table">
                <thead>

                <tr>
                    <th>Order Id</th>
                    <th>User Id</th>
                    <th>Order Date</th>
                    <th>Status</th>
                    <th>Tracking Number</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <%
                    for (Order order : orders) {
                %>
                <tr>
                    <td><a href="order-details.jsp?orderID=<%= order.getId() %>"><%= order.getId() %>
                    </a></td>
                    <td><%= order.getUserId() %>
                    </td>
                    <td><%= order.getOrderDate() %>
                    </td>
                    <td><%= order.getStatus() %>
                    </td>
                    <form method="post" action="order/ship?orderId=<%= order.getId() %>">
                        <td><input type="text" value="<%= order.getTrackingNumber() %>" minlength="8" maxlength="40"
                                   id="trackingNumberInput<%= order.getTrackingNumber() %>" name="trackingNumber"
                                   required></td>
                        <td>
                            <% if (!Objects.equals(order.getStatus(), "SHIPPED")) { %>
                            <button type="submit" class="btn btn-primary">Ship Order</button>
                            <% } %>
                        </td>
                    </form>
                </tr>
                <%
                    }
                %>
                </tbody>
            </table>
        </div>

        <div class="tab-pane fade" id="products">
            <table class="table">
                <thead>
                <tr>
                    <th>Product Name</th>
                    <th>SKU</th>
                </tr>
                </thead>
                <tbody>

                <tr>
                    <form action="products/create" method="post">
                        <td><input type="text" id="productName" name="productName" required></td>
                        <td><input type="text" id="sku" name="sku" required></td>
                        <td>
                            <button type="submit">Create Product</button>
                        </td>
                    </form>
                </tr>
                </tbody>
            </table>
            <!-- Table for Category 3 -->
            <table class="table">
                <thead>
                <tr>
                    <th>Product Id</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>SKU</th>
                    <th>Vendor</th>
                    <th>SLUG</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <%
                    for (Product product : products) {
                %>

                <tr>
                    <form method="post" action="products/update?productId=<%= product.getId() %>">
                        <td><a href="product-details.jsp?slug=<%= product.getUrlslug()%>"><%= product.getId() %>
                        </a></td>
                        <td><input type="text" id="productNameToUpdate" name="productNameToUpdate"
                                   value="<%= product.getName() %>"
                        >
                        </td>
                        <td><input type="text" id="productDescriptionToUpdate" name="productDescriptionToUpdate"
                                   value="<%= product.getDescription() %>">
                        </td>
                        <td><input type="number" id="productPriceToUpdate" step=".01" name="productPriceToUpdate"
                                   value="<%= product.getPrice() %>">
                        </td>
                        <td><input type="text" id="productSKUToUpdate" name="productSKUToUpdate"
                                   value="<%= product.getSku() %>"></td>
                        <td><input type="text" id="productVendorToUpdate" name="productVendorToUpdate"
                                   value="<%= product.getVendor() %>"></td>
                        <td><input type="text" id="productSlugToUpdate" name="productSlugToUpdate"
                                   value="<%= product.getUrlslug() %>"></td>
                        <td>
                            <button type="submit" class="btn btn-primary">Update Product</button>
                        </td>
                    </form>
                </tr>
                <%
                    }
                %>
                </tbody>
            </table>
        </div>
        <!-- Add more tab content for additional categories -->
    </div>
</div>
<footer>
    <a class="nav-link" href="login.jsp">Return to Login</a>
</footer>
<%@include file="includes/footer.jsp" %>


</body>
</html>
