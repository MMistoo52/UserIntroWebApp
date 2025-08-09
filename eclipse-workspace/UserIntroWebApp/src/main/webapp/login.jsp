<!DOCTYPE html>
<html>
  <body>
    <h2>Login</h2>
    <form action="LoginServlet" method="post">
      Username: <input name="name" required><br><br>
      Password: <input type="password" name="password" required><br><br>
      <button type="submit">Login</button>
    </form>

    <p style="color:red;">
      ${param.error != null ? param.error : "" }
    </p>

    <p>New user? <a href="index.jsp">Register here</a></p>
  </body>
</html>
