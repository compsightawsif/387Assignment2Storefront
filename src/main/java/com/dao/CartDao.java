package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CartDao {
    private Connection connection;

    public CartDao(Connection connection) {
        this.connection = connection;
    }

    // Method to add a product to the user's cart
    public boolean addProductToCart(int userId, int productId, int quantity) {
        try {
            // Check if the product is already in the user's cart
            String checkQuery = "SELECT cart_item_id, quantity FROM Cart_Item WHERE cart_id = " +
                    "(SELECT cart_id FROM Cart WHERE user_id = ?) AND product_id = ?";
            PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
            checkStatement.setInt(1, userId);
            checkStatement.setInt(2, productId);

            ResultSet resultSet = checkStatement.executeQuery();

            if (resultSet.next()) {
                // The product is already in the cart, so update the quantity
                int cartItemId = resultSet.getInt("cart_item_id");
                int currentQuantity = resultSet.getInt("quantity");
                int newQuantity = currentQuantity + quantity;

                // Update the quantity for the existing cart item
                String updateQuery = "UPDATE Cart_Item SET quantity = ? WHERE cart_item_id = ?";
                PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                updateStatement.setInt(1, newQuantity);
                updateStatement.setInt(2, cartItemId);

                int updatedRows = updateStatement.executeUpdate();

                return updatedRows > 0; // Check if the update was successful
            } else {
                // The product is not in the cart, so insert a new cart item
                String insertQuery = "INSERT INTO Cart_Item (cart_id, product_id, quantity) " +
                        "VALUES ((SELECT cart_id FROM Cart WHERE user_id = ?), ?, 1)" +
                        "ON DUPLICATE KEY UPDATE ? = ? + 1";
                PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
                insertStatement.setInt(1, userId);
                insertStatement.setInt(2, productId);
                insertStatement.setInt(3, quantity);
                insertStatement.setInt(4, quantity);


                int insertedRows = insertStatement.executeUpdate();

                return insertedRows > 0; // Check if the insertion was successful
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Handle any exceptions appropriately
        }
    }
}