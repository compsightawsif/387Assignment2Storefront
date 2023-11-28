<%@ page import="com.model.User" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="main.jsp">E-commerce Site</a>
    <ul class="navbar-nav ml-auto">
        <%
            User user = (User) request.getSession().getAttribute("auth");
            if (user != null) {
        %>
        <li class="nav-item">
            <a class="nav-link" href="claim-order.jsp">Claim Order</a>
        </li>
        <%
            }
        %>
        <li class="nav-item">
        </li>
        <li class="nav-item">
            <a class="nav-link" href="cart.jsp">View Cart</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="login.jsp">Return to Login</a>
        </li>
    </ul>
</nav>