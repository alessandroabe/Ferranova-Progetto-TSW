<%--
  Created by IntelliJ IDEA.
  User: chris
  Date: 30/05/2024
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registrazione</title>
</head>
<body>
<%if(session.getAttribute("alreadysub") != null && (boolean) session.getAttribute("alreadysub") == true) {%>
<%= "Questo account esiste gia"%>
<%}%>

<form action="registrazione" method="post">
  <label for="nome">Nome: </label>
  <input type="text" id="nome" name="nome"> <br>
  <label for="email">Email: </label>
  <input type="text" id="email" name="username"> <br>
  <label for="password">Password: </label>
  <input type="password" id="password" name="password"> <br>
  <label for="telefono">Telefono: </label>
  <input type="text" id="telefono" name="telefono"> <br>
  <input type="submit" value="Registrati">
</form>
</body>
</html>
