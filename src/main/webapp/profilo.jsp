<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profilo Ferranova</title>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/profiloStyle.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbarStyle.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/footerStyle.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>

<%@ include file="/WEB-INF/navbar.jsp" %>

<div class="container">
    <h1>Dati personali</h1>
    <p class="subparagraph">Visualizza i miei dati di login e spedizione.</p>

    <div class="profile-section">
        <div class="profile-item">
            <div class="icon"><i class="fas fa-user"></i></div>
            <div class="info">
                <label for="nome"><strong>Nome</strong></label>
                <input type="text" id="nome" value="${UtenteConnesso.nome}">
            </div>
            <button class="edit-button">Modifica</button>
        </div>
        <div class="profile-item">
            <div class="icon"><i class="fas fa-user"></i></div>
            <div class="info">
                <label for="cognome"><strong>Cognome</strong></label>
                <input type="text" id="cognome" value="${UtenteConnesso.cognome}">
            </div>
            <button class="edit-button">Modifica</button>
        </div>
        <div class="profile-item">
            <div class="icon"><i class="fas fa-mobile-alt"></i></div>
            <div class="info">
                <label for="telefono"><strong>Numero di telefono</strong></label>
                <input type="text" id="telefono" value="${UtenteConnesso.telefono}">
            </div>
            <button class="edit-button">Modifica</button>
        </div>
        <div class="profile-item">
            <div class="icon"><i class="fas fa-envelope"></i></div>
            <div class="info">
                <label for="email"><strong>Email</strong></label>
                <input type="email" id="email" value="${UtenteConnesso.email}">
            </div>
            <button class="edit-button">Modifica</button>
        </div>
        <div class="profile-item">
            <div class="icon"><i class="fas fa-lock"></i></div>
            <div class="info">
                <label for="password"><strong>La tua password</strong></label>
                <input type="password" id="password" value="password">
            </div>
            <button class="edit-button">Modifica</button>
        </div>
        <div class="profile-item">
            <div class="icon"><i class="fas fa-home"></i></div>
            <div class="info">
                <label for="indirizzo"><strong>Indirizzo</strong></label>
                <input type="text" id="indirizzo" value="${UtenteConnesso.indirizzo}">
            </div>
            <button class="edit-button">Modifica</button>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/footer.jsp" %>

</body>
</html>
