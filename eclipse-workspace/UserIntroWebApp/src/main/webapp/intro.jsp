<%
  if (session == null || session.getAttribute("userId") == null) {
    response.sendRedirect("login.jsp?error=Please+login"); return;
  }
%>
<!DOCTYPE html>
<html>
  <body>
    <h2>Introduce Yourself</h2>
    <form action="SaveIntroServlet" method="post">
      <label>Introduction:</label><br>
      <textarea name="introduction" rows="4" cols="60" required></textarea><br><br>

      <label>Hobbies (comma separated):</label><br>
      <input name="hobbies" size="60" required><br><br>

      <button type="submit">Save</button>
    </form>

    <p style="color:red;"><%= request.getParameter("error") != null ? request.getParameter("error") : "" %></p>
    <p><a href="logout.jsp">Logout</a></p>
  </body>
</html>
