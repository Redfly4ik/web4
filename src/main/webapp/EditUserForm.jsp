<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<%@ page import="com.example.web4.UserData" %>

<%
    int userId = Integer.parseInt(request.getParameter("id"));
    ResultSet user = UserData.getUserById(userId); // Реализуйте метод getUserById в UserData
    user.next();
%>

<form action="editUser" method="post">
    <input type="hidden" name="id" value="<%= userId %>">
    Email: <input type="email" name="email" value="<%= user.getString("Email") %>" required><br>
    Date Register: <input type="datetime-local" name="date_register" value="<%= user.getString("Date_register").replace(" ", "T") %>" required><br>
    Address: <input type="text" name="address" value="<%= user.getString("Adress") %>" required><br>
    Phone Number: <input type="text" name="phone_number" value="<%= user.getString("Number_phone") %>" required><br>
    Login: <input type="text" name="login" value="<%= user.getString("login") %>" required><br>
    First Name: <input type="text" name="first_name" value="<%= user.getString("First_name") %>" required><br>
    Second Name: <input type="text" name="second_name" value="<%= user.getString("Second_name") %>" required><br>
    Password: <input type="password" name="password" value="<%= user.getString("password") %>" required><br>
    Tariff Plan ID: <input type="number" name="id_tariff_plan" value="<%= user.getInt("id_tariff_plans") %>"><br>
    <input type="submit" value="Update User">
</form>
