package com.example.web4;

import java.sql.*;

public class TariffPlanData {

    public static ResultSet getTariffPlans() throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        Statement stmt = conn.createStatement();
        String sql = "SELECT * FROM tariff_plans";
        return stmt.executeQuery(sql);
    }

    public static boolean addTariffPlan(String name, double cost, int speed) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "INSERT INTO tariff_plans (name, cost, speed) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setDouble(2, cost);
            stmt.setInt(3, speed);
            return stmt.executeUpdate() > 0;
        }
    }



    public static boolean updateTariffPlan(int id, String name, double cost, int speed) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "UPDATE tariff_plans SET name = ?, cost = ?, speed = ? WHERE id_tariff_plans = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setDouble(2, cost);
            stmt.setInt(3, speed);
            stmt.setInt(4, id);
            return stmt.executeUpdate() > 0;
        }
    }

    public static ResultSet getTariffPlanById(int id) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM tariff_plans WHERE id_tariff_plans = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        return stmt.executeQuery();
    }






    public static boolean deleteTariffPlan(int id) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "DELETE FROM tariff_plans WHERE id_tariff_plans = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }
}
