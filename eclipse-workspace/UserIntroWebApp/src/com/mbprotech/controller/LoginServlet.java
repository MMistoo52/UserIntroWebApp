package com.mbprotech.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uname = request.getParameter("username");
        String pass = request.getParameter("password");

        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE username=? AND password=?");
            ps.setString(1, uname);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int userId = rs.getInt("user_id");
                HttpSession session = request.getSession();
                session.setAttribute("user_id", userId);
                session.setAttribute("username", uname);

                ps = conn.prepareStatement("SELECT * FROM user_intro WHERE user_id=?");
                ps.setInt(1, userId);
                rs = ps.executeQuery();

                if (rs.next()) {
                    request.setAttribute("intro", rs.getString("introduction"));
                    request.setAttribute("hobbies", rs.getString("hobbies"));
                    request.getRequestDispatcher("userDetails.jsp").forward(request, response);
                } else {
                    response.sendRedirect("introductionForm.jsp");
                }
            } else {
                response.getWriter().println("Invalid credentials.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
