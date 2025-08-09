import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String name = req.getParameter("name");
    String password = req.getParameter("password");

    name = name == null ? null : name.trim();
    password = password == null ? null : password.trim();

    if (name == null || name.isEmpty() || password == null || password.isEmpty()) {
      resp.sendRedirect("login.jsp?error=Missing+credentials");
      return;
    }

    try (Connection conn = DBUtil.getConnection()) {
      // Find the user
      String sql = "SELECT id, password FROM users WHERE name = ?";
      try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, name);
        try (ResultSet rs = ps.executeQuery()) {
          if (!rs.next()) {
            resp.sendRedirect("login.jsp?error=Invalid+username+or+password");
            return;
          }

          int userId = rs.getInt("id");
          String dbPass = rs.getString("password");

          // Compare plain passwords (swap for BCrypt if you hash)
          if (!password.equals(dbPass)) {
            resp.sendRedirect("login.jsp?error=Invalid+username+or+password");
            return;
          }

          // Create session
          HttpSession session = req.getSession(true);
          session.setAttribute("userId", userId);
          session.setAttribute("userName", name);

          // First-time or returning?
          String check = "SELECT COUNT(*) FROM user_intro WHERE user_id = ?";
          try (PreparedStatement ps2 = conn.prepareStatement(check)) {
            ps2.setInt(1, userId);
            try (ResultSet rs2 = ps2.executeQuery()) {
              rs2.next();
              if (rs2.getInt(1) == 0) {
                resp.sendRedirect("intro.jsp");            // first login → fill intro
              } else {
                resp.sendRedirect("IntroductionServlet");   // returning → view intro
              }
            }
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      resp.sendRedirect("login.jsp?error=Server+error");
    }
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    resp.sendRedirect("login.jsp");
  }
}
