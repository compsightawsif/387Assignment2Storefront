package com.servlet;
import com.connection.DBConnection;
import com.dao.CartDao;
import com.dao.OrderDao;
import com.model.Cart;
import java.util.List;
import com.model.Product;
import com.model.User;
import com.model.CartItem;
import com.dao.ProductDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/cart/*")
public class CartServlet extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Parse the product slug from the request URL
        String pathInfo = request.getPathInfo();
        String redirect = "/387Assignment2Storefront/main.jsp";
        try {
            CartDao cdao = new CartDao(DBConnection.getConnection());
            ProductDao pdao = new ProductDao(DBConnection.getConnection());
            OrderDao odao = new OrderDao(DBConnection.getConnection());
            if (pathInfo != null && pathInfo.startsWith("/products/")) {
                // Extract the slug from the URL
                String slug = pathInfo.substring(pathInfo.lastIndexOf("/") + 1);

                // Get the user's ID from the session or wherever you store it
                User u = (User) request.getSession().getAttribute("auth");
                int userId = u.getId();
                Product p = pdao.getProductBySlug(slug);
                cdao.addProductToCart(u.getId(), p.getSku(), 1);

            }else if (pathInfo != null && pathInfo.startsWith("/confirm")){
                User u = (User) request.getSession().getAttribute("auth");
                int orderId = odao.createOrder(u.getId());
                List <CartItem> ci = cdao.getCart(u.getId());
                for (CartItem item : ci) {
                    odao.createOrderItem(orderId, item.getProductId(), u.getId(), item.getQuantity(), item.getPrice());
                }
                cdao.clearCart(u.getId());
            } else if (pathInfo != null && pathInfo.startsWith("/update/")){
                int cartItemId = Integer.parseInt(pathInfo.substring(pathInfo.lastIndexOf("/") + 1));
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                cdao.updateQuantity(cartItemId, quantity);
                redirect = "/387Assignment2Storefront/cart.jsp";
            }else {
                // Handle invalid or unknown requests
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
            response.sendRedirect(redirect);
        }catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private int getUserIdFromSessionOrRequest(HttpServletRequest request) {
        // Your implementation to get the user's ID from a session or request parameter
        return Integer.parseInt((String) request.getAttribute("user_id")); // Example: return a user ID
    }

}
