<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.dao.CartDao" %>
<%@ page import="com.dao.ProductDao" %>
<%@ page import="com.connection.DBConnection" %>
<%@ page import="com.model.User" %>
<%@ page import="com.model.*" %>
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
            Cart c = (Cart) request.getSession().getAttribute("cart");
            CartDao cDAO = new CartDao(DBConnection.getConnection());
            ProductDao pDAO = new ProductDao(DBConnection.getConnection());
            List<CartItem> items = cDAO.getCartItemsByCartId(c.getCartId());

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
            <td>
                <form method="post" action="cart/remove/"<%= ci.getCartItemId() %>">
                    <input type="submit" value="Remove from Cart">
                </form>
            </td>
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
