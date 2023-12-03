package com.servlet;

import com.connection.DBConnection;
import com.dao.UserDao;
import com.exceptions.PasscodeChangeException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/user/*")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo != null && pathInfo.equals("/change-permission")) {
            response.setContentType("text/html;charset=UTF-8");
            int userPasscode = Integer.parseInt(request.getParameter("userPasscode"));
            String role = request.getParameter("role");
            try {
                UserDao udao = new UserDao(DBConnection.getConnection());
                udao.changePermission(userPasscode, role);
                response.sendRedirect("/387Assignment2Storefront/staff-main.jsp");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }

        } else if (pathInfo != null && pathInfo.equals("/change-passcode")) {
            response.setContentType("text/html;charset=UTF-8");
            int userId =  Integer.parseInt(request.getParameter("userId"));
            int userPasscodeToChange = Integer.parseInt(request.getParameter("userPasscodeToChange"));
            try {
                UserDao udao = new UserDao(DBConnection.getConnection());
                boolean passcodeChanged = udao.setPasscode(userId, userPasscodeToChange);
                if (!passcodeChanged) {
                    request.setAttribute("errorMessage", "Failed to change passcode. Passcode already in use by another user.");
                    response.sendRedirect(request.getContextPath() + "/staff-main.jsp?errorMessage=Update+failed.+Please+try+again.");
//                    RequestDispatcher dispatcher = request.getRequestDispatcher("/staff-main.jsp");
//                    dispatcher.forward(request, response);
                }
                else {
                    request.setAttribute("errorMessage", null);
                    response.sendRedirect(request.getContextPath() + "/staff-main.jsp");
                }

            }  catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
