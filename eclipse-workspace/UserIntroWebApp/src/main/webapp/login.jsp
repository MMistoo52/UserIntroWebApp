<!DOCTYPE html>
<html>
  <body>
    <h2>Login</h2>
    <form action="LoginServlet" method="post">
      <label>Username:</label><br>
      <input name="name" required><br><br>

      <label>Password:</label><br>
      <input type="password" name="password" required><br><br>

      <button type="submit">Login</button>
    </form>

    <p style="color:green;"><%= request.getParameter("msg") != null ? request.getParameter("msg") : "" %></p>
    <p style="color:red;"><%= request.getParameter("error") != null ? request.getParameter("error") : "" %></p>

    <p>New user? <a href="index.jsp">Register here</a></p>
  </body>
</html>
