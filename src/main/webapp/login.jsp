<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <%@include file="includes/header.jsp" %>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header">Login</div>
                <div class="card-body">
                    <form action="user-login" method="POST">
                        <div class="form-group">
                            <label for="user_passcode">User Passcode</label>
                            <input type="text" class="form-control" id="user_passcode" name="user_passcode" required>
                        </div>
                        <button type="submit" class="btn btn-primary">Login</button>
                    </form>
                </div>
            </div>
            <br>
                <form method="post" action="guest-login">
                    <button type="submit" class="btn btn-secondary">Continue as guest</button>
                </form>

        </div>
    </div>
</div>

<%@include file="includes/footer.jsp" %>

</body>
</html>
