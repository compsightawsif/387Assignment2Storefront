/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/10.1.15
 * Generated at: 2023-11-05 03:47:34 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.jsp.*;
import java.util.List;
import com.model.Product;
import com.dao.ProductDao;
import com.model.Order;
import com.dao.OrderDao;
import com.connection.DBConnection;

public final class staff_002dmain_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports,
                 org.apache.jasper.runtime.JspSourceDirectives {

  private static final jakarta.servlet.jsp.JspFactory _jspxFactory =
          jakarta.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(1);
    _jspx_dependants.put("/includes/header.jsp", Long.valueOf(1698892479508L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("jakarta.servlet");
    _jspx_imports_packages.add("jakarta.servlet.http");
    _jspx_imports_packages.add("jakarta.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("java.util.List");
    _jspx_imports_classes.add("com.dao.ProductDao");
    _jspx_imports_classes.add("com.model.Product");
    _jspx_imports_classes.add("com.model.Order");
    _jspx_imports_classes.add("com.connection.DBConnection");
    _jspx_imports_classes.add("com.dao.OrderDao");
  }

  private volatile jakarta.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public boolean getErrorOnELNotFound() {
    return false;
  }

  public jakarta.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final jakarta.servlet.http.HttpServletRequest request, final jakarta.servlet.http.HttpServletResponse response)
      throws java.io.IOException, jakarta.servlet.ServletException {

    if (!jakarta.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
        return;
      }
    }

    final jakarta.servlet.jsp.PageContext pageContext;
    jakarta.servlet.http.HttpSession session = null;
    final jakarta.servlet.ServletContext application;
    final jakarta.servlet.ServletConfig config;
    jakarta.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    jakarta.servlet.jsp.JspWriter _jspx_out = null;
    jakarta.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"UTF-8\">\r\n");
      out.write("    <title>Staff Dashboard</title>\r\n");
      out.write("    ");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\">\r\n");
      out.write("    ");
      out.write("\r\n");
      out.write("    <style>\r\n");
      out.write("        body {\r\n");
      out.write("            font-family: Arial, sans-serif;\r\n");
      out.write("            background-color: #f3f3f3;\r\n");
      out.write("        }\r\n");
      out.write("        .container {\r\n");
      out.write("            max-width: 600px;\r\n");
      out.write("            margin: 0 auto;\r\n");
      out.write("            background-color: #fff;\r\n");
      out.write("            padding: 20px;\r\n");
      out.write("            border-radius: 5px;\r\n");
      out.write("            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);\r\n");
      out.write("        }\r\n");
      out.write("        h1, h2 {\r\n");
      out.write("            color: #333;\r\n");
      out.write("        }\r\n");
      out.write("        form {\r\n");
      out.write("            margin-top: 20px;\r\n");
      out.write("        }\r\n");
      out.write("        label {\r\n");
      out.write("            display: block;\r\n");
      out.write("            margin-bottom: 5px;\r\n");
      out.write("        }\r\n");
      out.write("        input[type=\"text\"], input[type=\"number\"] {\r\n");
      out.write("            width: 100%;\r\n");
      out.write("            padding: 10px;\r\n");
      out.write("            margin-bottom: 10px;\r\n");
      out.write("            border: 1px solid #ccc;\r\n");
      out.write("            border-radius: 3px;\r\n");
      out.write("        }\r\n");
      out.write("        button {\r\n");
      out.write("            background-color: #007bff;\r\n");
      out.write("            color: #fff;\r\n");
      out.write("            border: none;\r\n");
      out.write("            padding: 10px 20px;\r\n");
      out.write("            border-radius: 3px;\r\n");
      out.write("            cursor: pointer;\r\n");
      out.write("        }\r\n");
      out.write("        button:hover {\r\n");
      out.write("            background-color: #0056b3;\r\n");
      out.write("        }\r\n");
      out.write("    </style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<h2>Product List</h2>\r\n");
      out.write("<table>\r\n");
      out.write("    <thead>\r\n");
      out.write("    <tr>\r\n");
      out.write("        <th>Name</th>\r\n");
      out.write("        <th>Description</th>\r\n");
      out.write("        <th>Price</th>\r\n");
      out.write("        <th>SKU</th>\r\n");
      out.write("        <th>Vendor</th>\r\n");
      out.write("        <th>urlSlug</th>\r\n");
      out.write("    </tr>\r\n");
      out.write("    </thead>\r\n");
      out.write("    <tbody>\r\n");
      out.write("    ");

        ProductDao productDAO = new ProductDao(DBConnection.getConnection()); // Initialize the ProductDAO with your database connection
        List<Product> products = productDAO.getAllProducts(); // Retrieve the products from the database

        for (Product product : products) {
    
      out.write("\r\n");
      out.write("    <tr>\r\n");
      out.write("        <td>");
      out.print( product.getName() );
      out.write("</td>\r\n");
      out.write("        <td>");
      out.print( product.getDescription() );
      out.write("</td>\r\n");
      out.write("        <td>");
      out.print( product.getPrice() );
      out.write("</td>\r\n");
      out.write("        <td>");
      out.print( product.getSku() );
      out.write("</td>\r\n");
      out.write("        <td>");
      out.print( product.getVendor() );
      out.write("</td>\r\n");
      out.write("        <td>");
      out.print( product.getUrlslug() );
      out.write("</td>\r\n");
      out.write("    </tr>\r\n");
      out.write("    ");

        }
    
      out.write("\r\n");
      out.write("    <br>\r\n");
      out.write("    <br>\r\n");
      out.write("\r\n");
      out.write("    </tbody>\r\n");
      out.write("</table>\r\n");
      out.write("<p></p>\r\n");
      out.write("<table>\r\n");
      out.write("    <thead>\r\n");
      out.write("    <tr>\r\n");
      out.write("        <th>Order Id</th>\r\n");
      out.write("        <th>User Id</th>\r\n");
      out.write("        <th>Order Date</th>\r\n");
      out.write("        <th>Status</th>\r\n");
      out.write("        <th>Tracking Number</th>\r\n");
      out.write("    </tr>\r\n");
      out.write("    </thead>\r\n");
      out.write("    <tbody>\r\n");
      out.write("    ");

        OrderDao orderDAO = new OrderDao(DBConnection.getConnection()); // Initialize the ProductDAO with your database connection
        List<Order> orders = orderDAO.getAllOrders(); // Retrieve the products from the database

        for (Order order : orders) {
    
      out.write("\r\n");
      out.write("    <tr>\r\n");
      out.write("        <td>");
      out.print( order.getId() );
      out.write("</td>\r\n");
      out.write("        <td>");
      out.print( order.getUserId() );
      out.write("</td>\r\n");
      out.write("        <td>");
      out.print( order.getOrderDate() );
      out.write("</td>\r\n");
      out.write("        <td>");
      out.print( order.getStatus() );
      out.write("</td>\r\n");
      out.write("        <td>");
      out.print( order.getTrackingNumber() );
      out.write("</td>\r\n");
      out.write("    </tr>\r\n");
      out.write("    ");

        }
    
      out.write("\r\n");
      out.write("    <br>\r\n");
      out.write("    <br>\r\n");
      out.write("\r\n");
      out.write("    </tbody>\r\n");
      out.write("</table>\r\n");
      out.write("<div class=\"container\">\r\n");
      out.write("    <h1>Welcome, Staff Member!</h1>\r\n");
      out.write("\r\n");
      out.write("    <!-- Product Creation Form -->\r\n");
      out.write("    <h2>Create a New Product</h2>\r\n");
      out.write("    <form action=\"products/create\" method=\"post\">\r\n");
      out.write("        <label for=\"productName\">Product Name:</label>\r\n");
      out.write("        <input type=\"text\" id=\"productName\" name=\"productName\" required>\r\n");
      out.write("        <br>\r\n");
      out.write("        <label for=\"sku\">SKU:</label>\r\n");
      out.write("        <input type=\"text\" id=\"sku\" name=\"sku\" required>\r\n");
      out.write("        <br>\r\n");
      out.write("        <button type=\"submit\">Create Product</button>\r\n");
      out.write("    </form>\r\n");
      out.write("    <p></p>\r\n");
      out.write("    <h2>Update a Product</h2>\r\n");
      out.write("    <form action=\"products/update\" method=\"post\">\r\n");
      out.write("        <label for=\"productIdToUpdate\">Id:</label>\r\n");
      out.write("        <input type=\"number\" id=\"productIdToUpdate\" name=\"productIdToUpdate\" required>\r\n");
      out.write("        <br>\r\n");
      out.write("        <label for=\"productNameToUpdate\">Product Name:</label>\r\n");
      out.write("        <input type=\"text\" id=\"productNameToUpdate\" name=\"productNameToUpdate\" required>\r\n");
      out.write("        <br>\r\n");
      out.write("        <label for=\"productDescriptionToUpdate\">Product Description:</label>\r\n");
      out.write("        <input type=\"text\" id=\"productDescriptionToUpdate\" name=\"productDescriptionToUpdate\" required>\r\n");
      out.write("        <br>\r\n");
      out.write("        <label for=\"productPriceToUpdate\">Product Price:</label>\r\n");
      out.write("        <input type=\"number\" id=\"productPriceToUpdate\" step=\".01\" name=\"productPriceToUpdate\" required>\r\n");
      out.write("        <br>\r\n");
      out.write("        <label for=\"productSKUToUpdate\">Product SKU:</label>\r\n");
      out.write("        <input type=\"text\" id=\"productSKUToUpdate\" name=\"productSKUToUpdate\" required>\r\n");
      out.write("        <br>\r\n");
      out.write("        <label for=\"productVendorToUpdate\">Product Vendor:</label>\r\n");
      out.write("        <input type=\"text\" id=\"productVendorToUpdate\" name=\"productVendorToUpdate\" required>\r\n");
      out.write("        <br>\r\n");
      out.write("        <label for=\"productSlugToUpdate\">Product Url Slug:</label>\r\n");
      out.write("        <input type=\"text\" id=\"productSlugToUpdate\" name=\"productSlugToUpdate\" required>\r\n");
      out.write("        <br>\r\n");
      out.write("        <button type=\"submit\">Update Product</button>\r\n");
      out.write("    </form>\r\n");
      out.write("    <p></p>\r\n");
      out.write("    <h2>Ship an Order</h2>\r\n");
      out.write("    <form action=\"order/ship\" method=\"post\">\r\n");
      out.write("        <label for=\"orderID\">Order ID:</label>\r\n");
      out.write("        <input type=\"number\" id=\"orderID\" name=\"orderID\" required>\r\n");
      out.write("        <br>\r\n");
      out.write("        <label for=\"trackingNumber\">Tracking Number:</label>\r\n");
      out.write("        <input type=\"text\" id=\"trackingNumber\" name=\"trackingNumber\" required>\r\n");
      out.write("        <button type=\"submit\">Ship Order</button>\r\n");
      out.write("    </form>\r\n");
      out.write("</div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof jakarta.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
