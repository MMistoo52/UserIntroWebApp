import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.*;

@WebServlet("/SaveIntroServlet")
public class SaveIntroServlet extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    HttpSession s = req.getSession(false);
    if (s == null || s.getAttribute("userId") == null) {
      resp.sendRedirect("login.jsp?error=Please+login"); return;
    }

    int userId = (int) s.getAttribute("userId");
    String introduction = req.getParameter("introduction");
    String hobbies = req.getParameter("hobbies");

    if (introduction == null || introduction.isBlank() ||
        hobbies == null || hobbies.isBlank()) {
      resp.sendRedirect("intro.jsp?error=Please+fill+all+fields"); return;
    }

    try (Connection conn = DBUtil.getConnection()) {
      String insert = "INSERT INTO user_intro (user_id, introduction, hobbies) VALUES (?, ?, ?)";
      try (PreparedStatement ps = conn.prepareStatement(insert)) {
        ps.setInt(1, userId);
        ps.setString(2, introduction);
        ps.setString(3, hobbies);
        ps.executeUpdate();
      }
      resp.sendRedirect("IntroductionServlet");
    } catch (Exception e) {
      e.printStackTrace();
      resp.sendRedirect("intro.jsp?error=save_failed");
    }
  }
}
