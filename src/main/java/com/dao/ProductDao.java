package com.dao;

import com.model.Order;
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

    public Product getProductBySlug(String slug) throws SQLException {
        Product product = null;

        query = "SELECT * FROM storefront.product where urlSlug=?";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            product = new Product();
            product.setId(resultSet.getInt("product_id"));
            product.setName(resultSet.getString("name"));
            product.setDescription(resultSet.getString("description"));
            product.setVendor(resultSet.getString("vendor"));
            product.setUrlslug(resultSet.getString("urlSlug"));
            product.setSku(resultSet.getString("sku"));
            product.setPrice(resultSet.getDouble("price"));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;

    }

    public void createProduct(String sku, String name) throws SQLException {

        try {
            query = "INSERT INTO product (SKU, Name, Description, Price, Vendor, urlSlug) VALUES (?, ?, '',0.00, '','');";
            pst = this.connection.prepareStatement(query);
            pst.setString(1, sku);
            pst.setString(2, name);
            pst.execute();
            System.out.println("pst: " + pst);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateProduct(int id, String name, String description, double price, String sku, String vendor, String slug) throws SQLException {
        Product product = null;
        try {
            query = "update storefront.product set name = ?, description = ?, price = ?, sku = ?, vendor=?, urlSlug=? WHERE product_id=?;";
            pst = this.connection.prepareStatement(query);
            pst.setString(1, name);
            pst.setString(2, description);
//            2 decimals
            String formatPrice = String.format("%.2f", price);
            price = Double.parseDouble(formatPrice);
            pst.setDouble(3, price);
            pst.setString(4, sku);
            pst.setString(5, vendor);
            pst.setString(6, slug);
            pst.setInt(7, id);
            pst.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM product"; // Adjust the query based on your actual table name

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

