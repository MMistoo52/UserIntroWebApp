<!DOCTYPE html>
<html>
  <body>
    <h2>Register</h2>
    <form action="RegisterServlet" method="post">
      <label>Username:</label><br>
      <input name="name" required><br><br>

      <label>Password:</label><br>
      <input type="password" name="password" required><br><br>

      <button type="submit">Register</button>
    </form>

    <p style="color:red;"><%= request.getParameter("error") != null ? request.getParameter("error") : "" %></p>
    <p>Already have an account? <a href="login.jsp">Login</a></p>
  </body>
</html>
