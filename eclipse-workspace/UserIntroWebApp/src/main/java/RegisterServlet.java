// package com.yourpkg;  // <- optional but recommended. If you use a package, put the file in that package folder.

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/RegisterServlet")  // matches form action="RegisterServlet"
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String name = req.getParameter("name");
        String password = req.getParameter("password");

        try (Connection conn = DBUtil.getConnection()) {
            String sql = "INSERT INTO users (name, password) VALUES (?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, name);
                ps.setString(2, password);
                int rows = ps.executeUpdate();
                resp.setContentType("text/plain");
                resp.getWriter().println(rows > 0 ? "Registration successful!" : "Registration failed.");
            }
        } catch (Exception e) {
            e.printStackTrace();                           // log to console
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.setContentType("text/plain");
            resp.getWriter().println("Error: " + e.getMessage());
        }
    }
}
