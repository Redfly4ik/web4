package com.example.web4;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id_users"));

        try {
            if (UserData.deleteUser(id)) {
                response.sendRedirect("index.jsp");
            } else {
                response.getWriter().println("Error deleting user");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("SQL Exception occurred: " + e.getMessage());
        }
    }
}
