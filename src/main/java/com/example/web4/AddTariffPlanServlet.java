package com.example.web4;




import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.sql.*;

public class AddTariffPlanServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        double cost = Double.parseDouble(request.getParameter("cost"));
        int speed = Integer.parseInt(request.getParameter("speed"));

        try {
            if (TariffPlanData.addTariffPlan(name, cost, speed)) {
                response.sendRedirect("index.jsp");
            } else {
                response.getWriter().println("Error adding tariff plan");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
