/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import config.DatabaseConfig;
import model.Customer;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {

    public void registerCustomer(Customer customer) {
        String sql = "INSERT INTO CUSTOMER (Username, Password, PhoneNumber, Address) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, customer.getUsername());
            stmt.setString(2, customer.getPassword());
            stmt.setString(3, customer.getPhoneNumber());
            stmt.setString(4, customer.getAddress());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Customer authenticateCustomer(String username, String password) {
        String sql = "SELECT * FROM CUSTOMER WHERE Username = ? AND Password = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Customer customer = new Customer();
                    customer.setUserID(rs.getInt("CustomerID"));
                    customer.setUsername(rs.getString("Username"));
                    customer.setPassword(rs.getString("Password"));
                    return customer;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }

    public Customer getCustomerById(int customerId) {
        String sql = "SELECT * FROM CUSTOMER WHERE CustomerID = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, customerId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Customer customer = new Customer();
                    customer.setUserID(rs.getInt("CustomerID"));
                    customer.setUsername(rs.getString("Username"));
                    customer.setPassword(rs.getString("Password"));
                    customer.setPhoneNumber(rs.getString("PhoneNumber"));
                    customer.setAddress(rs.getString("Address"));
                    return customer;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Customer getCustomerByName(String customerName) {
    String sql = "SELECT * FROM CUSTOMER WHERE Username = ?";
    try (Connection conn = DatabaseConfig.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, customerName);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                Customer customer = new Customer();
                customer.setUserID(rs.getInt("CustomerID"));
                customer.setUsername(rs.getString("Username"));
                customer.setPassword(rs.getString("Password"));
                customer.setPhoneNumber(rs.getString("PhoneNumber"));
                customer.setAddress(rs.getString("Address"));
                return customer;
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Handle exception
    }
    return null;
}

    public void updateCustomerProfile(Customer customer) {
        String sql = "UPDATE CUSTOMER SET Username = ?, Password = ?, PhoneNumber = ?, Address = ? WHERE CustomerID = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, customer.getUsername());
            stmt.setString(2, customer.getPassword());
            stmt.setString(3, customer.getPhoneNumber());
            stmt.setString(4, customer.getAddress());
            stmt.setInt(5, customer.getUserID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCustomer(int customerId) {
        String sql = "DELETE FROM CUSTOMER WHERE CustomerID = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, customerId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM CUSTOMER";
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Customer customer = new Customer();
                customer.setUserID(rs.getInt("CustomerID"));
                customer.setUsername(rs.getString("Username"));
                customer.setPassword(rs.getString("Password"));
                customer.setPhoneNumber(rs.getString("PhoneNumber"));
                customer.setAddress(rs.getString("Address"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    public List<Customer> searchCustomers(String searchQuery) {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM CUSTOMER WHERE Username LIKE ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + searchQuery + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Customer customer = new Customer();
                    customer.setUserID(rs.getInt("CustomerID"));
                    customer.setUsername(rs.getString("Username"));
                    customer.setPassword(rs.getString("Password"));
                    customer.setPhoneNumber(rs.getString("PhoneNumber"));
                    customer.setAddress(rs.getString("Address"));
                    customers.add(customer);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
    public void addCustomer(Customer customer) {
        String sql = "INSERT INTO CUSTOMER (Username, Password, PhoneNumber, Address) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, customer.getUsername());
            stmt.setString(2, customer.getPassword());
            stmt.setString(3, customer.getPhoneNumber());
            stmt.setString(4, customer.getAddress());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = md.digest(password.getBytes());

        StringBuilder hexString = new StringBuilder();
        for (byte hashByte : hashBytes) {
            String hex = Integer.toHexString(0xff & hashByte);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    private boolean checkPassword(String password, String hashedPassword) throws NoSuchAlgorithmException {
        String inputPasswordHash = hashPassword(password);
        return inputPasswordHash.equals(hashedPassword);
    }
}
