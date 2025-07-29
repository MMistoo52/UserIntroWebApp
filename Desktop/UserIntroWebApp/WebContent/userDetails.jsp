<%
    String username = (String) session.getAttribute("username");
    String intro = (String) request.getAttribute("intro");
    String hobbies = (String) request.getAttribute("hobbies");
%>
<h2>Welcome <%= username %></h2>
<table border="1">
    <tr><th>Introduction</th><td><%= intro %></td></tr>
    <tr><th>Hobbies</th><td><%= hobbies %></td></tr>
</table>
