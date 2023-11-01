<!DOCTYPE html>
<html>
<head>
    <title>All Products</title>
</head>
<body>
<h1>All Products</h1>
<ul>
    <c:forEach var="product" items="${allProducts}">
        <li>
            <a href="/products/${product.slug}">${product.name}</a>
        </li>
    </c:forEach>
</ul>

<% if (request.isUserInRole("staff")) { %>
<a href="${pageContext.request.contextPath}/products/download">Download Product Catalog</a>
<% } %>
</body>
</html>
