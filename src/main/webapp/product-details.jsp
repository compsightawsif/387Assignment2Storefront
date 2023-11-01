<!DOCTYPE html>
<html>
<head>
    <title>Product Details</title>
</head>
<body>
<h1>Product Details</h1>
<h2>${product.name}</h2>
<p>${product.description}</p>
<p>Price: $${product.price}</p>

<!-- Functionality to add the product to the user's cart -->
<form action="/cart/products/${product.slug}" method="post">
    <input type="hidden" name="sku" value="${product.sku}">
    <button type="submit">Add to Cart</button>
</form>

<!-- For staff users only, add functionality to make changes to the product -->
<% if (request.isUserInRole("staff")) { %>
<a href="/products/${product.slug}/edit">Edit Product</a>
<% } %>
</body>
</html>
