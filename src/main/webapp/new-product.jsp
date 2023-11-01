<!DOCTYPE html>
<html>
<head>
    <title>Create New Product</title>
</head>
<body>
<h1>Create New Product</h1>

<form action="${pageContext.request.contextPath}/products/create" method="post">
    <input type="text" name="name" placeholder="Product Name">
    <input type="text" name="description" placeholder="Product Description">
    <input type="text" name="sku" placeholder="SKU">
    <input type="text" name="slug" placeholder="URL Slug">
    <input type="number" name="price" placeholder="Price">
    <button type="submit">Create Product</button>
</form>
</body>
</html>
