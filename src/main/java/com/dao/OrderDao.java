package com.dao;

import com.model.Order;
import com.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    private Connection connection;
    private String query;
    private PreparedStatement pst;
    private ResultSet rs;

    public OrderDao(Connection connection) {
        this.connection = connection;
    }

    public int createOrder (int userId) throws SQLException{

        try {
            query = "INSERT INTO storefront.order (user_id, order_number, order_date, status, tracking_number) VALUES (?,1,?,?,'');";
            pst = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, userId);
            pst.setString(2, "2023-11-08");
            pst.setString(3, "PENDING");
            int rowsInserted = pst.executeUpdate();
            if (rowsInserted > 0) {
                // Get the generated keys (including the order_id)
                try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int orderId = generatedKeys.getInt(1); // Retrieve the generated order_id
                        // Use orderId as needed
                        return orderId;
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void createOrderItem (int orderId, int productId, int userId, int quantity, double price) throws SQLException{

        try {
            query = "INSERT INTO storefront.order_item (order_id, product_id, user_id, order_date,quantity,total_price) VALUES (?,?,?,'2023-11-08 00:00:00', ?, ?);";
            pst = this.connection.prepareStatement(query);
            pst.setInt(1, orderId);
            pst.setInt(2, productId);
            pst.setInt(3, userId);
            pst.setInt(4, quantity);
            pst.setDouble(5, price);
            pst.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void shipOrder(int orderId, String trackingNumber) throws SQLException {
        try {
            query = "UPDATE storefront.order SET status = 'SHIPPED', tracking_number = ? WHERE order_id = ?;";
            pst = this.connection.prepareStatement(query);
            pst.setString(1, trackingNumber);
            pst.setInt(2, orderId);
            pst.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM storefront.order";

        try (PreparedStatement pst = connection.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("order_id"));
                order.setUserId(rs.getInt("user_id"));
                order.setOrderDate(rs.getString("order_date"));
                order.setStatus(rs.getString("status"));
                order.setTrackingNumber(rs.getString("tracking_number"));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    public List<Order> getOrders(int user_id) throws SQLException {
        List<Order> orders = new ArrayList<>();
        try {
            query = "SELECT * FROM storefront.order where user_id=?";
            pst = this.connection.prepareStatement(query);
            pst.setInt(1, user_id);
            rs = pst.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("order_id"));
                order.setUserId(rs.getInt("user_id"));
                order.setOrderDate(rs.getString("order_date"));
                order.setStatus(rs.getString("status"));
                order.setTrackingNumber(rs.getString("tracking_number"));
                orders.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    public List<Order> getOrders(int user_id, int order_id) throws SQLException {
        List<Order> orders = new ArrayList<>();
        try {
            query = "SELECT * FROM storefront.order where user_id=? and order_id=?";
            pst = this.connection.prepareStatement(query);
            pst.setInt(1, user_id);
            pst.setInt(2, order_id);
            rs = pst.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("order_id"));
                order.setUserId(rs.getInt("user_id"));
                order.setOrderDate(rs.getString("order_date"));
                order.setStatus(rs.getString("status"));
                order.setTrackingNumber(rs.getString("tracking_number"));
                orders.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    public Order getOrderByID(int order_id) throws SQLException {
        Order order = null;
        try {
            query = "SELECT * FROM storefront.order where order_id=?";
            pst = this.connection.prepareStatement(query);
            pst.setInt(1, order_id);
            rs = pst.executeQuery();
            while (rs.next()) {
                order = new Order();
                order.setId(rs.getInt("order_id"));
                order.setUserId(rs.getInt("user_id"));
                order.setOrderDate(rs.getString("order_date"));
                order.setStatus(rs.getString("status"));
                order.setTrackingNumber(rs.getString("tracking_number"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return order;
    }
}

