package com.dao;

import com.model.Cart;
import com.model.CartItem;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class CartDao {
    private Connection connection;

    public CartDao(Connection connection) {
        this.connection = connection;
    }

    public Cart createCart(int userId) {
        if (getCartByUserId(userId) == null) {
            String insertQuery = "INSERT INTO cart (user_id) VALUES (?)";
            String selectQuery = "SELECT last_insert_rowid()";

            try (PreparedStatement insertStatement = this.connection.prepareStatement(insertQuery)) {
                insertStatement.setInt(1, userId);
                int rowsInserted = insertStatement.executeUpdate();

                if (rowsInserted > 0) {
                    try (Statement selectStatement = this.connection.createStatement()) {
                        try (ResultSet rs = selectStatement.executeQuery(selectQuery)) {
                            if (rs.next()) {
                                int cartId = rs.getInt(1);
                                Cart cart = new Cart();
                                cart.setCartId(cartId);
                                return cart;
                            } else {
                                throw new SQLException("Insertion failed, no ID obtained.");
                            }
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            Cart cart = getCartByUserId(userId);
            return cart;
        }

        return null;
    }

    public Cart createGuestCart() {
        String insertQuery = "INSERT INTO cart (user_id) VALUES (null)";
        String selectQuery = "SELECT last_insert_rowid()";

        try (PreparedStatement insertStatement = this.connection.prepareStatement(insertQuery)) {
            int rowsInserted = insertStatement.executeUpdate();

            if (rowsInserted > 0) {
                try (Statement selectStatement = this.connection.createStatement()) {
                    try (ResultSet rs = selectStatement.executeQuery(selectQuery)) {
                        if (rs.next()) {
                            int cartId = rs.getInt(1);
                            Cart cart = new Cart();
                            cart.setCartId(cartId);
                            return cart;
                        } else {
                            throw new SQLException("Insertion failed, no ID obtained.");
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void updateQuantity(int cartItemId, int quantity) {
        String query;
        PreparedStatement pst;
        try {
            query = "update cart_item set quantity = ? where cart_item_id = ?;";
            pst = this.connection.prepareStatement(query);
            pst.setInt(1, quantity);
            pst.setInt(2, cartItemId);
            pst.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Cart getCartByUserId(int userId) {
        Cart cart = null;
        try {
            // Check if the user has a cart
            String checkCartQuery = "SELECT * FROM Cart WHERE user_id = ?";
            PreparedStatement checkCartStatement = connection.prepareStatement(checkCartQuery);
            checkCartStatement.setInt(1, userId);

            ResultSet cartResultSet = checkCartStatement.executeQuery();

            if (!cartResultSet.next()) {
                // No cart exists for the user, return an empty list
                return null;
            }else{
                cart = new Cart();
                cart.setUserId(userId);
                cart.setCartId(cartResultSet.getInt("cart_id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any exceptions appropriately
        }
        return cart;
    }

    public List<CartItem> getCartItemsByUserId(int userId) {
        List<CartItem> cartItems = new ArrayList<>();

        try {
            // Check if the user has a cart
            String checkCartQuery = "SELECT cart_id FROM Cart WHERE user_id = ?";
            PreparedStatement checkCartStatement = connection.prepareStatement(checkCartQuery);
            checkCartStatement.setInt(1, userId);

            ResultSet cartResultSet = checkCartStatement.executeQuery();

            if (!cartResultSet.next()) {
                // No cart exists for the user, return an empty list
                return null;
            }

            // Retrieve the products in the user's cart
            String getCartItemsQuery = "SELECT * FROM Cart_Item " +
                    "WHERE cart_id = (SELECT cart_id FROM Cart WHERE user_id = ?)";
            PreparedStatement getCartItemsStatement = connection.prepareStatement(getCartItemsQuery);
            getCartItemsStatement.setInt(1, userId);

            ResultSet cartItemResultSet = getCartItemsStatement.executeQuery();

            while (cartItemResultSet.next()) {
                int cartItemId = cartItemResultSet.getInt("cart_item_id");
                int cartId = cartItemResultSet.getInt("cart_id");
                int productId = cartItemResultSet.getInt("product_id");
                int quantity = cartItemResultSet.getInt("quantity");
                double price = cartItemResultSet.getDouble("total_price");

                CartItem cartItem = new CartItem(cartItemId, cartId, productId, quantity, price);
                cartItems.add(cartItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any exceptions appropriately
        }

        return cartItems;
    }

    public List<CartItem> getCartItemsByCartId(int cartId) {
        List<CartItem> cartItems = new ArrayList<>();

        try {
            String checkCartQuery = "SELECT cart_id FROM Cart WHERE cart_id = ?";
            PreparedStatement checkCartStatement = connection.prepareStatement(checkCartQuery);
            checkCartStatement.setInt(1, cartId);

            ResultSet cartResultSet = checkCartStatement.executeQuery();

            if (!cartResultSet.next()) {
                // No cart exists for the user, return an empty list
                return null;
            }

            String getCartItemsQuery = "SELECT * FROM Cart_Item " +
                    "WHERE cart_id = ?";
            PreparedStatement getCartItemsStatement = connection.prepareStatement(getCartItemsQuery);
            getCartItemsStatement.setInt(1, cartId);

            ResultSet cartItemResultSet = getCartItemsStatement.executeQuery();

            while (cartItemResultSet.next()) {
                int cartItemId = cartItemResultSet.getInt("cart_item_id");
                cartId = cartItemResultSet.getInt("cart_id");
                int productId = cartItemResultSet.getInt("product_id");
                int quantity = cartItemResultSet.getInt("quantity");
                double price = cartItemResultSet.getDouble("total_price");

                CartItem cartItem = new CartItem(cartItemId, cartId, productId, quantity, price);
                cartItems.add(cartItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cartItems;
    }

    public boolean clearCart(int cartId) {
        try {
            // Check if the user has a cart
            String clearCartQuery = "DELETE FROM cart_item WHERE cart_id= ?";
            PreparedStatement clearCartStatement = connection.prepareStatement(clearCartQuery);
            clearCartStatement.setInt(1, cartId);

            int deletedRows = clearCartStatement.executeUpdate();
            return deletedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Handle any exceptions appropriately
        }
    }

    public boolean addProductToCart(int cartId, String sku, int quantity) {
        try {
            // Check if the product is already in the user's cart
            String checkQuery = "SELECT * FROM cart_item ci " +
                                "JOIN cart c on ci.cart_id = c.cart_id " +
                                "JOIN product p ON ci.product_id = p.product_id " +
                                "WHERE c.cart_id = ? AND p.sku = ?";
            PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
            checkStatement.setInt(1, cartId);
            checkStatement.setString(2, sku);

            ResultSet resultSet = checkStatement.executeQuery();

            if (resultSet.next()) {
                // The product is already in the cart, so update the quantity
                int cartItemId = resultSet.getInt("cart_item_id");
                int currentQuantity = resultSet.getInt("quantity");
                int newQuantity = currentQuantity + quantity;

                // Update the quantity for the existing cart item
                String updateQuery = "UPDATE cart_item SET quantity = ? WHERE cart_item_id = ?";
                PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                updateStatement.setInt(1, newQuantity);
                updateStatement.setInt(2, cartItemId);

                int updatedRows = updateStatement.executeUpdate();

                return updatedRows > 0; // Check if the update was successful
            } else {
                // The product is not in the cart, so insert a new cart item
                String insertQuery = "INSERT INTO cart_item (cart_id, product_id, quantity, total_price) " +
                        "VALUES (?, (SELECT product_id FROM product WHERE sku = ?), ?, 0.00)";
                PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
                insertStatement.setInt(1, cartId);
                insertStatement.setString(2, sku);
                insertStatement.setInt(3, quantity);

                int insertedRows = insertStatement.executeUpdate();

                return insertedRows > 0; // Check if the insertion was successful
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void removeProductFromCart(int cartItemId) {
        try {
            // Delete the cart item from the cart
            String deleteQuery = "DELETE FROM Cart_Item WHERE cart_item_id = ?";
            try (PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery)) {
                deleteStatement.setInt(1, cartItemId);
                deleteStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public boolean setProductQuantityInCart(int userId, String sku, int quantity) {
        try {
            // Check if the user has a cart; if not, create one
            String checkCartQuery = "SELECT cart_id FROM Cart WHERE user_id = ?";
            PreparedStatement checkCartStatement = connection.prepareStatement(checkCartQuery);
            checkCartStatement.setInt(1, userId);

            ResultSet cartResultSet = checkCartStatement.executeQuery();

            if (!cartResultSet.next()) {
                // The user doesn't have a cart; create one
                String createCartQuery = "INSERT INTO Cart (user_id) VALUES (?)";
                PreparedStatement createCartStatement = connection.prepareStatement(createCartQuery);
                createCartStatement.setInt(1, userId);
                createCartStatement.executeUpdate();
            }

            // Get the product's product_id using the SKU
            String getProductQuery = "SELECT product_id FROM Product WHERE sku = ?";
            PreparedStatement getProductStatement = connection.prepareStatement(getProductQuery);
            getProductStatement.setString(1, sku);

            ResultSet productResultSet = getProductStatement.executeQuery();

            if (!productResultSet.next()) {
                // The product with the given SKU doesn't exist
                return false;
            }

            int productId = productResultSet.getInt("product_id");

            // Check if the product is already in the user's cart
            String checkCartItemQuery = "SELECT cart_item_id FROM Cart_Item WHERE cart_id = " +
                    "(SELECT cart_id FROM Cart WHERE user_id = ?) AND product_id = ?";
            PreparedStatement checkCartItemStatement = connection.prepareStatement(checkCartItemQuery);
            checkCartItemStatement.setInt(1, userId);
            checkCartItemStatement.setInt(2, productId);

            ResultSet cartItemResultSet = checkCartItemStatement.executeQuery();

            if (cartItemResultSet.next()) {
                // The product is in the cart, so update the quantity
                int cartItemId = cartItemResultSet.getInt("cart_item_id");

                if (quantity > 0) {
                    // Update the quantity for the existing cart item
                    String updateCartItemQuery = "UPDATE Cart_Item SET quantity = ? WHERE cart_item_id = ?";
                    PreparedStatement updateCartItemStatement = connection.prepareStatement(updateCartItemQuery);
                    updateCartItemStatement.setInt(1, quantity);
                    updateCartItemStatement.setInt(2, cartItemId);

                    int updatedRows = updateCartItemStatement.executeUpdate();

                    return updatedRows > 0; // Check if the update was successful
                } else {
                    // Quantity is zero; remove the product from the cart
                    String deleteCartItemQuery = "DELETE FROM Cart_Item WHERE cart_item_id = ?";
                    PreparedStatement deleteCartItemStatement = connection.prepareStatement(deleteCartItemQuery);
                    deleteCartItemStatement.setInt(1, cartItemId);

                    int deletedRows = deleteCartItemStatement.executeUpdate();

                    return deletedRows > 0; // Check if the deletion was successful
                }
            } else {
                // The product is not in the cart, so insert a new cart item if the quantity is greater than zero
                if (quantity > 0) {
                    String insertCartItemQuery = "INSERT INTO Cart_Item (cart_id, product_id, quantity) " +
                            "VALUES ((SELECT cart_id FROM Cart WHERE user_id = ?), ?, ?)";
                    PreparedStatement insertCartItemStatement = connection.prepareStatement(insertCartItemQuery);
                    insertCartItemStatement.setInt(1, userId);
                    insertCartItemStatement.setInt(2, productId);
                    insertCartItemStatement.setInt(3, quantity);

                    int insertedRows = insertCartItemStatement.executeUpdate();

                    return insertedRows > 0; // Check if the insertion was successful
                } else {
                    // Quantity is zero; no action is needed
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}