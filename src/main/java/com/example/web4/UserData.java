package com.example.web4;

import java.sql.*;

public class UserData {

    public static ResultSet getUsers() throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        Statement stmt = conn.createStatement();
        String sql = "SELECT * FROM users";
        return stmt.executeQuery(sql);
    }

    public static boolean addUser(String email, String dateRegister, String address, String phoneNumber,
                                  String login, String firstName, String secondName, String password,
                                  Integer idTariffPlan) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "INSERT INTO users (Email, Date_register, Adress, Number_phone, login, First_name, Second_name, password, id_tariff_plans) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, dateRegister);
            stmt.setString(3, address);
            stmt.setString(4, phoneNumber);
            stmt.setString(5, login);
            stmt.setString(6, firstName);
            stmt.setString(7, secondName);
            stmt.setString(8, password);
            if (idTariffPlan != null) {
                stmt.setInt(9, idTariffPlan);
            } else {
                stmt.setNull(9, java.sql.Types.INTEGER);
            }
            return stmt.executeUpdate() > 0;
        }
}



    public static boolean updateUser(int id, String email, String dateRegister, String address, String numberPhone,
                                     String login, String firstName, String secondName, String password,
                                     Integer idTariffPlans) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "UPDATE users SET Email = ?, Date_register = ?, Adress = ?, Number_phone = ?, login = ?, " +
                "First_name = ?, Second_name = ?, password = ?, id_tariff_plans = ? WHERE id_users = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, dateRegister);
            stmt.setString(3, address);
            stmt.setString(4, numberPhone);
            stmt.setString(5, login);
            stmt.setString(6, firstName);
            stmt.setString(7, secondName);
            stmt.setString(8, password);
            if (idTariffPlans != null) {
                stmt.setInt(9, idTariffPlans);
            } else {
                stmt.setNull(9, Types.INTEGER);
            }
            stmt.setInt(10, id);
            return stmt.executeUpdate() > 0;
        }
    }








    public static ResultSet getUserById(int id) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM users WHERE id_users = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        return stmt.executeQuery();
    }






    public static boolean deleteUser(int id) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "DELETE FROM users WHERE id_users = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }
}
