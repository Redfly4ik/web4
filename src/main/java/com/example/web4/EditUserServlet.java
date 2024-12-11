package com.example.web4;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;

import java.sql.*;

public class EditUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Получаем данные из формы
        int id = Integer.parseInt(request.getParameter("id"));
        String email = request.getParameter("email");
        String dateRegister = request.getParameter("date_register");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phone_number");
        String login = request.getParameter("login");
        String firstName = request.getParameter("first_name");
        String secondName = request.getParameter("second_name");
        String password = request.getParameter("password");
        Integer idTariffPlan = request.getParameter("id_tariff_plan") != null ? Integer.parseInt(request.getParameter("id_tariff_plan")) : null;

        // Обновляем пользователя в базе данных
        try {
            boolean updated = UserData.updateUser(id, email, dateRegister, address, phoneNumber, login, firstName, secondName, password, idTariffPlan);
            if (updated) {
                // Перенаправляем на страницу с успешным обновлением
                response.sendRedirect("index.jsp"); // Пример: перенаправляем на список пользователей
            } else {
                // В случае ошибки редактирования показываем сообщение
                request.setAttribute("error", "Failed to update user.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("EditUserForm.jsp");
                dispatcher.forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Database error occurred.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("EditUserForm.jsp");
            dispatcher.forward(request, response);
        }
    }
}
