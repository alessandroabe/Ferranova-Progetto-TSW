<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <title>Registrazione Ferranova</title>
    <link rel="stylesheet" type="text/css" href="css/loginStyle.css"/>
    <link rel="icon" type="image/x-icon" href="images/favicon.ico">

</head>

<body>

<%@ include file="./WEB-INF/navbar.jsp" %>


<div class="wrapper">
    <form action="registrazione" method="post">

        <h3>Registrati al sito</h3>

        <!-- TODO: devi mettere i label perchè servono per l'accessibilità -->
        <<label for="nome">Nome:</label>
        <input type="text" id="nome" name="nome" required><br>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br>

        <label for="telefono">Telefono:</label>
        <input type="tel" id="telefono" name="telefono" required><br>

        <label for="is_admin">Amministratore:</label>
        <input type="checkbox" id="is_admin" name="is_admin"><br>


        <c:if test="${param.error == 1}">
            <div id="alert" class="alert">Account già esistente</div>
            <br>
        </c:if>

        <input type="submit" id="submit" value="Registra"/>
    </form>
</div>
<div class="registrazione">
    <p>Sei già utente di Ferranova?</p>
    <a href="login.jsp">entra</a>
</div>

</body>
</html>