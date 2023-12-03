package com.servlet;

import com.connection.DBConnection;
import com.dao.OrderDao;
import com.model.Order;
import com.model.User;
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

        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/orders")) {
            try {
                OrderDao odao = new OrderDao(DBConnection.getConnection());
                List<Order> orders = odao.getAllOrders();

                request.setAttribute("orders", orders);

                request.getRequestDispatcher("/staff-main.jsp").forward(request, response);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();

            }

        } else if (pathInfo.matches("/products/([a-zA-Z0-9-]+)")) {

        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo.startsWith("/ship")) {
            response.setContentType("text/html;charset=UTF-8");
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            String trackingNumber = request.getParameter("trackingNumber");
            try {
                OrderDao odao = new OrderDao(DBConnection.getConnection());
                odao.shipOrder(orderId, trackingNumber);
                response.sendRedirect("/387Assignment2Storefront/staff-main.jsp#orders");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();

            }
        } else if (pathInfo != null && pathInfo.equals("/set-order-owner")) {
            response.setContentType("text/html;charset=UTF-8");
            User user = (User) request.getSession().getAttribute("auth");
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            int userId = user.getId();

            try {
                OrderDao odao = new OrderDao(DBConnection.getConnection());
                odao.setOrderOwner(orderId, userId);
                response.sendRedirect("/387Assignment2Storefront/claim-order.jsp");

            } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

}



