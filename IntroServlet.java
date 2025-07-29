package com.mbprotech.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class IntroServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String intro = request.getParameter("intro");
        String hobbies = request.getParameter("hobbies");

        HttpSession session = request.getSession();
        int userId = (Integer) session.getAttribute("user_id");

        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO user_intro (user_id, introduction, hobbies) VALUES (?, ?, ?)");
            ps.setInt(1, userId);
            ps.setString(2, intro);
            ps.setString(3, hobbies);
            ps.executeUpdate();

            request.setAttribute("intro", intro);
            request.setAttribute("hobbies", hobbies);
            request.getRequestDispatcher("userDetails.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
