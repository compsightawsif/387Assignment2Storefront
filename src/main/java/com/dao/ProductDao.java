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

    public Product getProduct(String sku) throws SQLException {
        Product product = null;

        query = "SELECT product.sku FROM product where sku=?";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            product = new Product();
            productAttributesResultSet(resultSet, product);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }

    public Product getProductbyID(int id) throws SQLException {
        Product product = null;

        try {
            query = "SELECT * FROM product where product_id=?";
            pst = this.connection.prepareStatement(query);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                product = new Product();
                product.setId(rs.getInt("product_id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));
                product.setVendor(rs.getString("vendor"));
                product.setSku(rs.getString("sku"));
                product.setUrlslug(rs.getString("urlSlug"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }

    public Product getProductBySlug(String slug) throws SQLException {
        Product product = null;

        try {
            query = "SELECT * FROM product where urlSlug=?";
            pst = this.connection.prepareStatement(query);
            pst.setString(1, slug);
            rs = pst.executeQuery();
            while (rs.next()) {
                product = new Product();
                product.setId(rs.getInt("product_id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));
                product.setVendor(rs.getString("vendor"));
                product.setSku(rs.getString("sku"));
                product.setUrlslug(rs.getString("urlSlug"));

            }

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

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateProduct(int id, String name, String description, double price, String sku, String vendor, String slug) throws SQLException {
        Product product = null;
        try {
            query = "update product set name = ?, description = ?, price = ?, sku = ?, vendor=?, urlSlug=? WHERE product_id=?;";
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
        String query = "SELECT * FROM product";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Product product = new Product();
                productAttributesResultSet(resultSet, product);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    private void productAttributesResultSet(ResultSet resultSet, Product product) throws SQLException {
        product.setId(resultSet.getInt("product_id"));
        product.setName(resultSet.getString("name"));
        product.setDescription(resultSet.getString("description"));
        product.setVendor(resultSet.getString("vendor"));
        product.setUrlslug(resultSet.getString("urlSlug"));
        product.setSku(resultSet.getString("sku"));
        product.setPrice(resultSet.getDouble("price"));
    }
}

