import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.*;

@WebServlet("/IntroductionServlet")
public class IntroductionServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    HttpSession s = req.getSession(false);
    if (s == null || s.getAttribute("userId") == null) {
      resp.sendRedirect("login.jsp?error=Please+login"); return;
    }

    int userId = (int) s.getAttribute("userId");
    String userName = (String) s.getAttribute("userName");

    resp.setContentType("text/html;charset=UTF-8");
    var out = resp.getWriter();
    out.println("<html><body>");
    out.println("<h2>Welcome, " + userName + "</h2>");
    out.println("<table border='1' cellpadding='8'><tr><th>Introduction</th><th>Hobbies</th></tr>");

    try (Connection conn = DBUtil.getConnection()) {
      String q = "SELECT introduction, hobbies FROM user_intro WHERE user_id = ?";
      try (PreparedStatement ps = conn.prepareStatement(q)) {
        ps.setInt(1, userId);
        try (ResultSet rs = ps.executeQuery()) {
          boolean any = false;
          while (rs.next()) {
            any = true;
            out.println("<tr><td>" + rs.getString("introduction") + "</td><td>" + rs.getString("hobbies") + "</td></tr>");
          }
          if (!any) {
            out.println("<tr><td colspan='2'>No intro yet. <a href='intro.jsp'>Add one</a></td></tr>");
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      out.println("<tr><td colspan='2'>Error loading intro.</td></tr>");
    }

    out.println("</table><p><a href='logout.jsp'>Logout</a></p>");
    out.println("</body></html>");
  }
}
