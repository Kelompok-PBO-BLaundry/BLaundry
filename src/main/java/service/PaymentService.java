package service;

import config.DatabaseConfig;
import model.Payment;

import java.sql.*;

public class PaymentService {

    public void processPayment(int orderId, String paymentMethod, double totalAmount, String userType) {
        String sql = "INSERT INTO PAYMENT (PaymentMethod, PaymentStatus, TotalAmount, OrderID) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, paymentMethod);
            stmt.setString(2, "Completed"); 
            stmt.setDouble(3, totalAmount);
            stmt.setInt(4, orderId);
            
            System.out.println(stmt.toString());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int paymentId = generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}