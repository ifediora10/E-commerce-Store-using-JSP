package com.example.week_6.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.example.week_6.connection.DbCon;
import com.example.week_6.dao.UserDao;
import com.example.week_6.model.User;

@WebServlet("/user-signup")
public class SignUpServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            // Retrieve user input from the signup form
            String name = request.getParameter("signup-name");
            String email = request.getParameter("signup-email");
            String password = request.getParameter("signup-password");

            // Create a UserDao instance to interact with the database
            UserDao userDao = new UserDao(DbCon.getConnection());

            // Check if the email is already registered
            if (!userDao.isEmailRegistered(email)) {
                // If the email is not registered, create a new User object
                User newUser = new User(0,name, email, password);

                // Insert the new user into the database
                if (userDao.insertUser(newUser)) {
                    // Registration successful
                    response.sendRedirect("login.jsp");
                } else {
                    out.println("Failed to register the user.");
                }
            } else {
                out.println("Email is already registered.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}