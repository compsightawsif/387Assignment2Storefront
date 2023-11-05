<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
<h1>Welcome to our E-Commerce Store</h1>
<div id="cart">
    <h2>Shopping Cart</h2>
    <ul id="cart-items">
    </ul>
</div>

<div id="products">
    <h2>Products</h2>
    <div class="product">
        <h3>Product 1</h3>
        <p>Price: $10</p>
        <button onclick="addToCart('Product 1', 10)">Add to Cart</button>
    </div>
    <div class="product">
        <h3>Product 2</h3>
        <p>Price: $15</p>
        <button onclick="addToCart('Product 2', 15)">Add to Cart</button>
    </div>
    <div class="product">
        <h3>Product 3</h3>
        <p>Price: $20</p>
        <button onclick="addToCart('Product 3', 20)">Add to Cart</button>
    </div>
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
