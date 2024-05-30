<%@ page import="model.Utente" %>
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
<form action="login" method="get">
    <label for="email">Email: </label>
    <input type="text" id="email" name="username"> <br>
    <label for="password">Password: </label>
    <input type="password" id="password" name="password">
    <input type="submit" value="Entra">
</form>
</body>
</html>