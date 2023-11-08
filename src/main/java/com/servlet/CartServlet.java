package com.servlet;
import com.connection.DBConnection;
import com.dao.CartDao;
import com.model.Product;
import com.model.User;
import com.dao.ProductDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/cart/*")
public class CartServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        // Parse the product slug from the request URL
        String slug = request.getPathInfo();

        ProductDao pdao = new ProductDao(DBConnection.getConnection());

        Product product = pdao.getProductBySlug(slug);

        if (product != null) {
            // Replace this with your logic to get the user's ID (e.g., from a session or request parameter)
            int userId = getUserIdFromSessionOrRequest(request);

            // Get the quantity to add
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            // Initialize the CartDAO with your database connection
            CartDao cdao = new CartDao(DBConnection.getConnection());

            // Attempt to add the product to the user's cart
            boolean addedToCart = cdao.addProductToCart(userId, product.getSku(), quantity);

            if (addedToCart) {
                // Product added to the cart successfully
                response.setStatus(HttpServletResponse.SC_CREATED); // HTTP 201 Created
                response.getWriter().write("Product added to cart.");
            } else {
                // Failed to add the product to the cart
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // HTTP 500 Internal Server Error
                response.getWriter().write("Failed to add product to cart.");
            }
        } else {
            // Product not found
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); // HTTP 404 Not Found
            response.getWriter().write("Product not found.");
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private int getUserIdFromSessionOrRequest(HttpServletRequest request) {
        // Your implementation to get the user's ID from a session or request parameter
        return Integer.parseInt((String) request.getAttribute("user_id")); // Example: return a user ID
    }

}
