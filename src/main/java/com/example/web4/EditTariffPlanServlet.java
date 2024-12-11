package com.example.web4;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

public class EditTariffPlanServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Получаем данные из формы
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String costParam = request.getParameter("cost");
        String speedParam = request.getParameter("speed");

        // Проверяем корректность входных данных
        double cost = 0;
        int speed = 0;

        if (name == null || name.trim().isEmpty()) {
            request.setAttribute("error", "Name cannot be null or empty.");
            forwardToForm(request, response);
            return;
        }

        try {
            if (costParam != null && !costParam.trim().isEmpty()) {
                cost = Double.parseDouble(costParam.trim());
            }
            if (speedParam != null && !speedParam.trim().isEmpty()) {
                speed = Integer.parseInt(speedParam.trim());
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid cost or speed value.");
            forwardToForm(request, response);
            return;
        }

        // Обновляем тарифный план в базе данных
        try {
            boolean updated = TariffPlanData.updateTariffPlan(id, name, cost, speed);
            if (updated) {
                // Перенаправляем на список тарифов после успешного обновления
                response.sendRedirect("index.jsp");
            } else {
                // Обрабатываем неудачное обновление
                request.setAttribute("error", "Failed to update tariff plan.");
                forwardToForm(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Database error occurred.");
            forwardToForm(request, response);
        }
    }

    // Метод для перенаправления обратно на форму редактирования
    private void forwardToForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("EditTariffPlanForm.jsp");
        dispatcher.forward(request, response);
    }
}