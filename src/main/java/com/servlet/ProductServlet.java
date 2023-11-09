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
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/products/*")
public class ProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/products")) {
            try {
                ProductDao pdao = new ProductDao(DBConnection.getConnection());
                List<Product> products = pdao.getAllProducts();

                request.setAttribute("products", products);

                request.getRequestDispatcher("/staff-main.jsp").forward(request, response);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();

            }


        } else if (pathInfo.matches("/products/([a-zA-Z0-9-]+)")) {
            // Handle the request to display a specific product by slug
            String slug = pathInfo.substring(pathInfo.lastIndexOf("/") + 1);
            try {
                ProductDao pdao = new ProductDao(DBConnection.getConnection());
                Product product = pdao.getProductBySlug(slug);

                if (product != null) {
                    // Found the product
                    request.setAttribute("product", product);
                    request.getRequestDispatcher("/product-details.jsp").forward(request, response);
                } else {
                    // Product not found
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }

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
        if (pathInfo != null && pathInfo.equals("/create")) {
            response.setContentType("text/html;charset=UTF-8");
            String sku = request.getParameter("sku");
            String name = request.getParameter("productName");
            try {
                ProductDao pdao = new ProductDao(DBConnection.getConnection());
                pdao.createProduct(sku, name);
                response.sendRedirect("/387Assignment2Storefront/staff-main.jsp");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();

            }

        } else if (pathInfo != null && pathInfo.equals("/update")) {
            response.setContentType("text/html;charset=UTF-8");
            int id = Integer.parseInt(request.getParameter("productIdToUpdate"));
            String name = request.getParameter("productNameToUpdate");
            String description = request.getParameter("productDescriptionToUpdate");
            double price = Double.parseDouble(request.getParameter("productPriceToUpdate"));
            String sku = request.getParameter("productSKUToUpdate");
            String vendor = request.getParameter("productVendorToUpdate");
            String slug = request.getParameter("productSlugToUpdate");
            try {
                ProductDao pdao = new ProductDao(DBConnection.getConnection());
                pdao.updateProduct(id, name, description, price, sku, vendor, slug);
                response.sendRedirect("/387Assignment2Storefront/staff-main.jsp");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();

            }

        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

}



