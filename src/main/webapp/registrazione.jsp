<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registrazione Ferranova</title>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/log_regStyle.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbarStyle.css"/>
    <script src="https://maps.googleapis.com/maps/api/js?key=YOUR_API_KEY&libraries=places"></script>
    <script>
        function initAutocomplete() {
            var input = document.getElementById('indirizzo');
            var autocomplete = new google.maps.places.Autocomplete(input);
            autocomplete.setFields(['address_component', 'geometry']);
        }
    </script>
</head>

<body>

<%@ include file="/WEB-INF/navbar.jsp" %>

<div class="wrapper">
    <form action="registrazione" method="post">

        <h3>Registrati al sito</h3>


        <label for="nome">Nome</label>
        <input type="text" id="nome" name="nome" required><br>

        <label for="cognome">Cognome</label>
        <input type="text" id="cognome" name="cognome" required><br>

        <label for="email">Email</label>
        <input type="email" id="email" name="email" required><br>

        <label for="password">Password</label>
        <div class="password-container">
            <input type="password" id="password" name="password" required><br>
            <button type="button" class="mostraPassword" id="mostraPassword" aria-label="mostraPassword" tabindex="0" onkeydown="togglePassword()" onclick="togglePassword()"><i class="fa-regular fa-eye"></i></button>
        </div>

        <label for="telefono">Telefono</label>
        <input type="tel" id="telefono" name="telefono" required><br>

        <label for="indirizzo">Indirizzo</label>
        <input type="text" id="indirizzo" name="indirizzo" required><br>


        <c:if test="${param.error == 1}">
            <div id="alert" class="alert">Account già esistente</div>
            <br>
        </c:if>

        <input type="submit" id="submit" value="Registra"/>
    </form>

    <div class="registrazione">
        <p>Sei già utente di Ferranova?</p>
        <a href="login.jsp">entra</a>
    </div>
</div>

<script src="${pageContext.request.contextPath}/js/viewPassword.js"></script>

</body>
</html>