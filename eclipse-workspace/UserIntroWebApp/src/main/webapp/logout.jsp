<%
  if (session != null) { session.invalidate(); }
  response.sendRedirect("login.jsp?msg=Logged+out");
%>
