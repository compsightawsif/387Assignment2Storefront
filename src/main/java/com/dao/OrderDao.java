package com.dao;

import com.model.Order;
import com.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public void shipOrder(int orderId, String trackingNumber) throws SQLException {
        try {
            query = "update storefront.order set status = 'SHIPPED' WHERE order_id=? and tracking_number=?;";
            pst = this.connection.prepareStatement(query);
            pst.setInt(1, orderId);
            pst.setString(2, trackingNumber);
            pst.execute();
            System.out.println("pst: " + pst);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM storefront.order"; // Adjust the query based on your actual table name

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt("order_id"));
                order.setUserId(resultSet.getInt("user_id"));
                order.setOrderDate(resultSet.getString("order_date"));
                order.setStatus(resultSet.getString("status"));
                order.setTrackingNumber(resultSet.getString("tracking_number"));
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
            System.out.println(rs);
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

    public List<Order> getOrder(int user_id, int order_id) throws SQLException {
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
}

