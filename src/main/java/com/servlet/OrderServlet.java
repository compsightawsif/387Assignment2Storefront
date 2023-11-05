package com.servlet;

import com.connection.DBConnection;
import com.dao.OrderDao;
import com.model.Order;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/order/*")
public class OrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pathInfo = request.getPathInfo(); // Get the path information from the request URL

        if (pathInfo == null || pathInfo.equals("/orders")) {
            try {
                OrderDao odao = new OrderDao(DBConnection.getConnection());
                // Fetch the list of products from the database or data source
                List<Order> orders = odao.getAllOrders(); // Implement this method to retrieve products

                // Pass the list of products to the JSP page
                request.setAttribute("orders", orders);

                // Forward the request to the JSP page
                request.getRequestDispatcher("/staff-main.jsp").forward(request, response);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();

            }


        } else if (pathInfo.matches("/products/([a-zA-Z0-9-]+)")) {
//            String slug = pathInfo.substring(pathInfo.lastIndexOf("/") + 1);
//            Product product = YourDatabase.getProductBySlug(slug);
//            if (product != null) {
//                // Found the product
//                response.setContentType("application/json");
//                response.getWriter().write("{ \"name\": \"" + product.getName() + "\", \"slug\": \"" + product.getSlug() + "\" }");
//            } else {
//                // Product not found
//                response.sendError(HttpServletResponse.SC_NOT_FOUND);
//            }

        } else {
            // Handle invalid or unknown requests
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo != null && pathInfo.equals("/ship")) {
            response.setContentType("text/html;charset=UTF-8");
            int orderid = Integer.parseInt(request.getParameter("orderID"));
            String trackingNumber = request.getParameter("trackingNumber");
            try {
                OrderDao odao = new OrderDao(DBConnection.getConnection());
                Order order = odao.shipOrder(orderid, trackingNumber);
                response.sendRedirect("/387Assignment2Storefront/staff-main.jsp");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();

            }

        } else {
            // Handle invalid or unknown requests
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

}



