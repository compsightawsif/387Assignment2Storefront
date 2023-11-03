package com.servlet;

import com.connection.DBConnection;
import com.dao.ProductDao;
import com.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/create-product")
public class ProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("product.jsp");
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String sku = request.getParameter("sku");
            String name = request.getParameter("productName");

            try {
                ProductDao pdao = new ProductDao(DBConnection.getConnection());
                Product product = pdao.createProduct(sku, name);
                out.print("sku:"+product.getSku());
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();

            }


        }
    }

}
