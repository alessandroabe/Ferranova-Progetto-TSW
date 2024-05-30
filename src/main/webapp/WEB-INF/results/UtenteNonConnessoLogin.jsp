<%@ page import="org.unisa.abeilleamorellifontana_pj.Model.Utente" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Login</title>
</head>
<body>
<h1>Login
</h1>
<br/>
<%Utente Connesso = (Utente) session.getAttribute("UtenteConnesso");%>
<%if((boolean) session.getAttribute("connessione") == false) {%>
<%= "Email o password errati"%>
<%}//l'account non e' connesso quindi devo rifare il login%>
<form action="login" method="get">
  <label for="email">Email: </label>
  <input type="text" id="email" name="username"> <br>
  <label for="password">Password: </label>
  <input type="password" id="password" name="password">
  <input type="submit" value="Entra">
</form>
</body>
</html>