package com.example.web4;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

public class AddUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String dateRegister = request.getParameter("date_register");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phone_number");
        String login = request.getParameter("login");
        String firstName = request.getParameter("first_name");
        String secondName = request.getParameter("second_name");
        String password = request.getParameter("password");
        String idTariffPlanStr = request.getParameter("id_tariff_plan");

        Integer idTariffPlan = idTariffPlanStr != null && !idTariffPlanStr.isEmpty() ? Integer.parseInt(idTariffPlanStr) : null;

        try {
            if (UserData.addUser(email, dateRegister, address, phoneNumber, login, firstName, secondName, password, idTariffPlan)) {
                response.sendRedirect("index.jsp");
            } else {
                response.getWriter().println("Error adding user");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
