<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
            <th>Product Name</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Total</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>Product 1</td>
            <td>$19.99</td>
            <td><input type="number" value="1" min="1" max="10"></td>
            <td>$19.99</td>
        </tr>
        <tr>
            <td>Product 2</td>
            <td>$29.99</td>
            <td><input type="number" value="2" min="1" max="10"></td>
            <td>$59.98</td>
        </tr>
        <!-- Add more product rows as needed -->
        </tbody>
    </table>

    <h4>Total Price: $79.97</h4>

    <a href="checkout.jsp" class="btn btn-primary">Proceed to Checkout</a>
    <button class="btn btn-danger">Empty Cart</button>
</div>

<%@include file="includes/footer.jsp" %>

</body>
</html>
