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
            User u = (User) request.getSession().getAttribute("auth");
            Cart c = (Cart) request.getSession().getAttribute("cart");
            if (pathInfo.startsWith("/products/")) {
                String slug = pathInfo.substring(pathInfo.lastIndexOf("/") + 1);
                Product p = pdao.getProductBySlug(slug);
                cdao.addProductToCart(c.getCartId(), p.getSku(), 1);

            } else if (pathInfo.startsWith("/confirm")){
                if (u != null) {
                    int orderId = odao.createOrder(u.getId());
                    List <CartItem> ci = cdao.getCartItemsByCartId(c.getCartId());
                    for (CartItem item : ci) {
                        odao.createOrderItem(orderId, item.getProductId(), u.getId(), item.getQuantity(), item.getPrice());
                    }
                }
                else {
                    int orderId = odao.createGuestOrder();
                    List <CartItem> ci = cdao.getCartItemsByCartId(c.getCartId());
                    for (CartItem item : ci) {
                        odao.createGuestOrderItem(orderId, item.getProductId(), item.getQuantity(), item.getPrice());
                    }
                }
                cdao.clearCart(c.getCartId());

            } else if (pathInfo.startsWith("/update/")){
                int cartItemId = Integer.parseInt(pathInfo.substring(pathInfo.lastIndexOf("/") + 1));
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                cdao.updateQuantity(cartItemId, quantity);
                redirect = "/387Assignment2Storefront/cart.jsp";

            } else if (pathInfo.startsWith("/remove/")) {
                int cartItemId = Integer.parseInt(pathInfo.substring(pathInfo.lastIndexOf("/") + 1));
                cdao.removeProductFromCart(cartItemId);
                redirect = "/387Assignment2Storefront/cart.jsp";
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
            response.sendRedirect(redirect);
        }catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
