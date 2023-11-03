package com.dao;

import com.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    private Connection connection;
    private String query;
    private PreparedStatement pst;
    private ResultSet rs;

    public ProductDao(Connection connection) {
        this.connection = connection;
    }

    public Product createProduct(String sku, String name) throws SQLException {
        Product product = null;

        try {
            query = "INSERT INTO Product (SKU, Name, Description, Price, Vendor, urlSlug) VALUES (?, ?, '',0.00, '','');";
            pst = this.connection.prepareStatement(query);
            pst.setString(1, sku);
            pst.setString(2, name);
            pst.execute();
            System.out.println("pst: " + pst);
//            if (rs.next()) {
//                product = new Product();
//                product.setId(rs.getInt("product_id"));
//                product.setName(rs.getString("name"));
//                product.setSku(rs.getString("sku"));
//            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;

    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM Product"; // Adjust the query based on your actual table name

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("product_id"));
                product.setName(resultSet.getString("name"));
                product.setDescription(resultSet.getString("description"));
                product.setVendor(resultSet.getString("vendor"));
                product.setUrlslug(resultSet.getString("urlSlug"));
                product.setSku(resultSet.getString("sku"));
                product.setPrice(resultSet.getDouble("price"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }
}

