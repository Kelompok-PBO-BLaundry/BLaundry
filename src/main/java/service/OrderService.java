/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import config.DatabaseConfig;
import model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderService {

    public int placeCustomerOrder(Order order) {
        String sql = "INSERT INTO ORDERS (OrderDate, OrderStatus, ServiceType, TotalWeight, TotalItem, Duration, TotalAmount, DeliveryAddress, CustomerID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setDate(1, order.getOrderDate());
            stmt.setString(2, order.getOrderStatus());
            stmt.setString(3, order.getServiceType());
            stmt.setDouble(4, order.gettTotalWeight());
            stmt.setDouble(5, order.gettTotalItem());
            stmt.setString(6, order.getDuration());
            stmt.setDouble(7, order.getTotalAmount());
            stmt.setString(8, order.getDeliveryAddress());
            stmt.setInt(9, order.getCustomerId());
            
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating order failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    public int placeAdminOrder(Order order) {
        String sql = "INSERT INTO ORDERS (OrderDate, OrderStatus, ServiceType, TotalWeight, TotalItem, Duration, TotalAmount, DeliveryAddress, CustomerID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setDate(1, order.getOrderDate());
            stmt.setString(2, order.getOrderStatus());
            stmt.setString(3, order.getServiceType());
            stmt.setDouble(4, order.gettTotalWeight());
            stmt.setDouble(5, order.gettTotalItem());
            stmt.setString(6, order.getDuration());
            stmt.setDouble(7, order.getTotalAmount());
            stmt.setString(8, order.getDeliveryAddress());
            stmt.setInt(9, order.getCustomerId());
            
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating order failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

            return -1;
        }
    }

    public Order getOrderById(int orderId) {
        String sql = "SELECT o.*, c.Username AS CustomerName FROM ORDERS o JOIN CUSTOMER c ON o.CustomerID = c.CustomerID WHERE OrderID = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, orderId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Order order = new Order();
                    order.setOrderId(rs.getInt("OrderID"));
                    order.setOrderDate(rs.getDate("OrderDate"));
                    order.setOrderStatus(rs.getString("OrderStatus"));
                    order.setServiceType(rs.getString("ServiceType"));
                    order.setTotalWeight(rs.getDouble("TotalWeight"));
                    order.setTotalItem(rs.getDouble("TotalItem"));
                    order.setDuration(rs.getString("Duration"));
                    order.setTotalAmount(rs.getDouble("TotalAmount"));
                    order.setDeliveryAddress(rs.getString("DeliveryAddress"));
                    order.setCustomerId(rs.getInt("CustomerID"));
                    order.setCustomerName(rs.getString("CustomerName"));
                    return order;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT o.*, c.Username AS CustomerName FROM ORDERS o JOIN CUSTOMER c ON o.CustomerID = c.CustomerID";
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("OrderID"));
                order.setOrderDate(rs.getDate("OrderDate"));
                order.setOrderStatus(rs.getString("OrderStatus"));
                order.setServiceType(rs.getString("ServiceType"));
                order.setTotalWeight(rs.getDouble("TotalWeight"));
                order.setTotalItem(rs.getDouble("TotalItem"));
                order.setDuration(rs.getString("Duration"));
                order.setTotalAmount(rs.getDouble("TotalAmount"));
                order.setDeliveryAddress(rs.getString("DeliveryAddress"));
                order.setCustomerId(rs.getInt("CustomerID"));
                order.setCustomerName(rs.getString("CustomerName"));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public List<Order> getOrdersByCustomerId(int customerId) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT o.*, c.Username AS CustomerName FROM ORDERS o JOIN CUSTOMER c ON o.CustomerID = c.CustomerID WHERE o.CustomerID = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, customerId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Order order = new Order();
                    order.setOrderId(rs.getInt("OrderID"));
                    order.setOrderDate(rs.getDate("OrderDate"));
                    order.setOrderStatus(rs.getString("OrderStatus"));
                    order.setServiceType(rs.getString("ServiceType"));
                    order.setTotalWeight(rs.getDouble("TotalWeight"));
                    order.setTotalItem(rs.getDouble("TotalItem"));
                    order.setDuration(rs.getString("Duration"));
                    order.setTotalAmount(rs.getDouble("TotalAmount"));
                    order.setDeliveryAddress(rs.getString("DeliveryAddress"));
                    order.setCustomerId(rs.getInt("CustomerID"));
                    order.setCustomerName(rs.getString("CustomerName"));
                    orders.add(order);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public void updateOrderStatus(int orderId, String newStatus) {
        String sql = "UPDATE ORDERS SET OrderStatus = ? WHERE OrderID = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newStatus);
            stmt.setInt(2, orderId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteOrder(int orderId) {
        String sql = "DELETE FROM ORDERS WHERE OrderID = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, orderId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
