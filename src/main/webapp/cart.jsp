<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.model.CartItem" %>
<%@ page import="com.dao.CartDao" %>
<%@ page import="com.dao.ProductDao" %>
<%@ page import="com.model.User" %>
<%@ page import="com.model.Product" %>
<%@ page import="com.connection.DBConnection" %>
<%@ page import="com.model.User" %>
<!DOCTYPE html>
<html>
<head>
    <title>Shopping Cart</title>
    <%@include file="includes/header.jsp" %>
</head>
<body>

<%@include file="includes/navbar.jsp" %>

<div class="container mt-4">
    <h2>Your Shopping Cart</h2>

    <table class="table">
        <thead>
        <tr>
            <th>Product Id</th>
            <th>Quantity</th>
        </tr>
        </thead>
        <tbody>
        <%
            User u = (User) request.getSession().getAttribute("auth");
            CartDao cDAO = new CartDao(DBConnection.getConnection()); // Initialize the ProductDAO with your database connection
            ProductDao pDAO = new ProductDao(DBConnection.getConnection()); // Initialize the ProductDAO with your database connection
            List<CartItem> items = cDAO.getCart(u.getId()); // Retrieve the products from the database

            for (CartItem ci : items) {
                Product p = pDAO.getProductbyID(ci.getProductId());
        %>
        <tr>
            <td><%= p.getName() %></td>
            <form method="post" action="cart/update/<%= ci.getCartItemId() %>">
            <td>
                <input type="number" value="<%= ci.getQuantity() %>" min="1" max="99" id="quantityInput<%= ci.getCartItemId() %>" name="quantity">
                <input type="submit" value="Update Item">
            </form>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>

    <form method="post" action="cart/confirm">
        <input type="submit" value="Confirm Order">
    </form>
</div>

<%@include file="includes/footer.jsp" %>

</body>
</html>
