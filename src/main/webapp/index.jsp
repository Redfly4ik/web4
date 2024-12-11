<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<%@ page import="com.example.web4.UserData" %>
<%@ page import="com.example.web4.TariffPlanData" %>
<%@ page import="com.example.web4.DatabaseConnection" %>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="style.css">
    <title>CRUD Operations</title>
</head>
<body>

<h3>Add User</h3>
<form action="addUser" method="post">
    Email: <input type="email" name="email" required><br>
    Date Register: <input type="datetime-local" name="date_register" required><br>
    Address: <input type="text" name="address" required><br>
    Phone Number: <input type="text" name="phone_number" required><br>
    Login: <input type="text" name="login" required><br>
    First Name: <input type="text" name="first_name" required><br>
    Second Name: <input type="text" name="second_name" required><br>
    Password: <input type="password" name="password" required><br>
    Tariff Plan ID: <input type="number" name="id_tariff_plan"><br>
    <input type="submit" value="Add User">
</form>

<h3>Add Tariff Plan</h3>
<form action="addTariffPlan" method="post">
    Name: <input type="text" name="name" required><br>
    Cost: <input type="number" name="cost" step="0.01" required><br>
    Speed: <input type="number" name="speed" required><br>
    <input type="submit" value="Add Tariff Plan">
</form>

<h3>Users</h3>
<%
    try {
        ResultSet rsUsers = UserData.getUsers();
        ResultSetMetaData resMetaDataUsers = rsUsers.getMetaData();
        int nColsUsers = resMetaDataUsers.getColumnCount();

        // Заголовки таблицы
        out.println("<table border='1'>");
        out.println("<tr>");
        for (int kCol = 1; kCol <= nColsUsers; kCol++) {
            out.println("<th>" + resMetaDataUsers.getColumnName(kCol) + "</th>");
        }
        out.println("<th>Actions</th>"); // Столбец для действий
        out.println("</tr>");

        // Данные таблицы
        while (rsUsers.next()) {
            out.println("<tr>");
            for (int kCol = 1; kCol <= nColsUsers; kCol++) {
                out.println("<td>" + rsUsers.getString(kCol) + "</td>");
            }

            // Кнопки Edit и Delete
            int userId = rsUsers.getInt("id_users");
            out.println("<td>");
            out.println("<form action='editUser' method='post' style='display:inline;'>");
            out.println("<input type='hidden' name='id' value='" + userId + "'>"); // Изменяем на 'id', так как в EditUserServlet используется этот параметр
            out.println("<input type='submit' value='Edit'>");
            out.println("</form>");
            out.println("<form action='deleteUser' method='post' style='display:inline;'>");
            out.println("<input type='hidden' name='id_users' value='" + userId + "'>");
            out.println("<input type='submit' value='Delete'>");
            out.println("</form>");
            out.println("</td>");

            out.println("</tr>");
        }
        out.println("</table>");

        rsUsers.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
%>

<h3>Tariff Plans</h3>
<%
    try {
        ResultSet rsTariffPlans = TariffPlanData.getTariffPlans();
        ResultSetMetaData resMetaDataTariff = rsTariffPlans.getMetaData();
        int nColsTariff = resMetaDataTariff.getColumnCount();

        // Заголовки таблицы
        out.println("<table border='1'>");
        out.println("<tr>");
        for (int kCol = 1; kCol <= nColsTariff; kCol++) {
            out.println("<th>" + resMetaDataTariff.getColumnName(kCol) + "</th>");
        }
        out.println("<th>Actions</th>"); // Столбец для действий
        out.println("</tr>");

        // Данные таблицы
        while (rsTariffPlans.next()) {
            out.println("<tr>");
            for (int kCol = 1; kCol <= nColsTariff; kCol++) {
                out.println("<td>" + rsTariffPlans.getString(kCol) + "</td>");
            }

            // Кнопки Edit и Delete
            int tariffId = rsTariffPlans.getInt("id_tariff_plans");
            out.println("<td>");
            out.println("<form action='editTariffPlan' method='post' style='display:inline;'>");
            out.println("<input type='hidden' name='id_tariff_plans' value='" + tariffId + "'>");
            out.println("<input type='submit' value='Edit'>");
            out.println("</form>");
            out.println("<form action='deleteTariffPlan' method='post' style='display:inline;'>");
            out.println("<input type='hidden' name='id_tariff_plans' value='" + tariffId + "'>");
            out.println("<input type='submit' value='Delete'>");
            out.println("</form>");
            out.println("</td>");

            out.println("</tr>");
        }
        out.println("</table>");

        rsTariffPlans.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
%>

</body>
</html>
