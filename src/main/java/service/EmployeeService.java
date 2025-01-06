package service;

import config.DatabaseConfig;
import model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeService {

    public Employee authenticateEmployee(String username, String password) {
        String sql = "SELECT * FROM LAUNDRY_EMPLOYEE WHERE Username = ? AND Password = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Employee employee = new Employee();
                    employee.setUserID(rs.getInt("EmployeeID"));
                    employee.setUsername(rs.getString("Username"));
                    employee.setPassword(rs.getString("Password"));
                    return employee;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }

    public Employee getEmployeeById(int employeeId) {
        String sql = "SELECT * FROM LAUNDRY_EMPLOYEE WHERE EmployeeID = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, employeeId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Employee employee = new Employee();
                    employee.setUserID(rs.getInt("EmployeeID"));
                    employee.setUsername(rs.getString("Username"));
                    employee.setPassword(rs.getString("Password"));
                    return employee;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
}
