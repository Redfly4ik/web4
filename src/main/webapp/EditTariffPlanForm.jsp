<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<%@ page import="com.example.web4.TariffPlanData" %>

<%
    int tariffPlanId = Integer.parseInt(request.getParameter("id"));
    ResultSet tariffPlan = TariffPlanData.getTariffPlanById(tariffPlanId); // Метод для получения тарифного плана по ID
    tariffPlan.next();
%>

<h2>Edit Tariff Plan</h2>

<form action="editTariffPlan" method="post">

    <input type="hidden" name="id" value="<%= tariffPlanId %>">

    Name: <input type="text" name="name" value="<%= tariffPlan.getString("name") %>" required><br>
    Cost: <input type="number" step="0.01" name="cost" value="<%= tariffPlan.getDouble("cost") %>" required><br>
    Speed: <input type="number" name="speed" value="<%= tariffPlan.getInt("speed") %>" required><br>

    <input type="submit" value="Update Tariff Plan">
</form>

