import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String name = req.getParameter("name");
    String password = req.getParameter("password");

    name = name == null ? null : name.trim();
    password = password == null ? null : password.trim();

    if (name == null || name.isEmpty() || password == null || password.isEmpty()) {
      resp.sendRedirect("index.jsp?error=Name+and+password+are+required");
      return;
    }

    try (Connection conn = DBUtil.getConnection()) {
      // Check duplicate username
      try (PreparedStatement dup = conn.prepareStatement("SELECT COUNT(*) FROM users WHERE name=?")) {
        dup.setString(1, name);
        try (ResultSet r = dup.executeQuery()) {
          r.next();
          if (r.getInt(1) > 0) {
            resp.sendRedirect("index.jsp?error=Username+already+taken");
            return;
          }
        }
      }

      // Insert user
      String sql = "INSERT INTO users (name, password) VALUES (?, ?)";
      try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, name);
        ps.setString(2, password); // (for real apps: hash before saving)
        int rows = ps.executeUpdate();
        if (rows > 0) {
          resp.sendRedirect("login.jsp?msg=Registered+successfully");
        } else {
          resp.sendRedirect("index.jsp?error=Registration+failed");
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      resp.sendRedirect("index.jsp?error=Server+error");
    }
  }
}
