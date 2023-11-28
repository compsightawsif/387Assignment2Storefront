<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Claim Order</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<%@include file="includes/navbar.jsp" %>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header">Claim Order</div>
                <div class="card-body">
                    <form action="order/set-order-owner" method="POST">
                        <div class="form-group">
                            <label for="orderId">Order ID:</label>
                            <input type="text" class="form-control" id="orderId" name="orderId" required>
                        </div>
                        <button type="submit" class="btn btn-primary">Claim Order</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
