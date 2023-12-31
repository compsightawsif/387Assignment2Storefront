package com.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import com.connection.DBConnection;
import com.dao.UserDao;
import com.dao.CartDao;
import com.model.User;
import com.model.Cart;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/user-login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String userPasscode = request.getParameter("user_passcode");

            try {
                UserDao udao = new UserDao(DBConnection.getConnection());
                User user = udao.userLogin(Integer.parseInt(userPasscode));
                CartDao cdao = new CartDao(DBConnection.getConnection());
                if (user != null) {
                    request.getSession().setAttribute("auth", user);
                    Cart cart = cdao.createCart(user.getId());
                    request.getSession().setAttribute("cart", cart);
                    if (user.getRole().equals("Staff")) {
                        response.sendRedirect("staff-main.jsp");
                    } else {
                        response.sendRedirect("main.jsp");
                    }
                } else {
                    System.out.println("Not a valid user");
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
