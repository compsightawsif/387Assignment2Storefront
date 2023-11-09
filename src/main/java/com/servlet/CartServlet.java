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
        String pathInfo = request.getPathInfo();
        String redirect = "/387Assignment2Storefront/main.jsp";
        try {
            CartDao cdao = new CartDao(DBConnection.getConnection());
            ProductDao pdao = new ProductDao(DBConnection.getConnection());
            OrderDao odao = new OrderDao(DBConnection.getConnection());
            if (pathInfo != null && pathInfo.startsWith("/products/")) {
                String slug = pathInfo.substring(pathInfo.lastIndexOf("/") + 1);


                User u = (User) request.getSession().getAttribute("auth");
                int userId = u.getId();
                Product p = pdao.getProductBySlug(slug);
                cdao.addProductToCart(u.getId(), p.getSku(), 1);

            } else if (pathInfo != null && pathInfo.startsWith("/confirm")){
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
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
            response.sendRedirect(redirect);
        }catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        String redirect = "/387Assignment2Storefront/cart.jsp"; // Default redirect to the cart page

        try {
            CartDao cdao = new CartDao(DBConnection.getConnection());

            if (pathInfo != null && pathInfo.startsWith("/remove/")) {

                User user = (User) request.getAttribute("auth");
                int userId = user.getId();

                Product product = (Product) request.getAttribute("sku");
                String sku = product.getSku();
                ProductDao pdao = new ProductDao(DBConnection.getConnection());
                String productToRemove = String.valueOf(pdao.getProduct(sku));

                int cartItemId = Integer.parseInt(pathInfo.substring(pathInfo.lastIndexOf("/") + 1));

                cdao.removeProductFromCart(userId, productToRemove);

            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return; // Return to avoid the subsequent redirect in case of an error
            }

            response.sendRedirect(redirect);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
