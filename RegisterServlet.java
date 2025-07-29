package com.mbprotech.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uname = request.getParameter("username");
        String pass = request.getParameter("password");

        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)");
            ps.setString(1, uname);
            ps.setString(2, pass);
            ps.executeUpdate();
            response.sendRedirect("login.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error during registration.");
        }
    }
}
