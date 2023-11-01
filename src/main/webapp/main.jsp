<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>E-commerce Site</title>
    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">E-commerce Site</a>
    <ul class="navbar-nav ml-auto">
        <li class="nav-item">
            <a class="nav-link" href="cart.jsp">View Cart</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="login.jsp">Staff Login</a>
        </li>
    </ul>
</nav>

<div class="container mt-5">
    <h1>Welcome to Our E-commerce Site</h1>
    <p>Explore our products and add them to your cart.</p>
    
    <!-- Product listing goes here -->
    <!-- Include product details, images, and "Add to Cart" buttons -->
</div>


    <%@include file="includes/footer.jsp" %>
</body>
</html>
