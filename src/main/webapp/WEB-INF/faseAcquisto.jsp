<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Fase di acquisto Ferranova</title>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/acquistoStyle.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbarStyle.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/footerStyle.css"/>

</head>

<body>

<%@ include file="navbar.jsp" %>

<div class="wrapper">
    <form action="acquisto" method="post">

        <h4>Nominativo</h4>

        <label for="nome">Nome:</label>
        <input type="text" id="nome" placeholder="nome" value="${UtenteConnesso.nome}" name="nome" required><br>

        <label for="cognome">Cognome:</label>
        <input type="text" id="cognome" placeholder="cognome" value="${UtenteConnesso.cognome}" name="cognome" required><br>


        <h4>Indirizzo</h4>

        <label for="indirizzo">Indirizzo:</label>
        <input type="text" id="indirizzo" placeholder="indirizzo" value="${UtenteConnesso.indirizzo}" name="indirizzo" required><br>


        <h4>Dati carta</h4>

        <label for="intestatario">Intestatario della carta:</label>
        <input type="text" id="intestatario" name="intestatario" placeholder="intestatario della carta" required><br>

        <label for="n_carta">Numero carta:</label>
        <input type="text" id="n_carta" name="intestatario" placeholder="numero della carta" required><br>

        <label for="scadenza">Scadenza:</label>
        <input type="text" id="scadenza" placeholder="scadenza" required><br>

        <label for="cvv">CVV:</label>
        <input type="text" id="cvv" placeholder="CVV" required><br>


        <!--todo attraverso una funzione ajax che dopo che clicchi enter applica lo sconto al prezzo totale se nel db c'è il coupon scritto-->
        <h5>Hai un coupon?</h5>

        <label for="coupon">Codice promozionale:</label>
        <input type="text" id="coupon" placeholder="Codice promozionale"><br>

        <div class="total-price">
            <p>Totale: <span><fmt:setLocale value="fr_FR"/>
                <!-- Imposta la localizzazione su Francia che usa l'Euro -->
                        <fmt:formatNumber value="${sum}" type="currency" currencySymbol="€"/></span></p>
        </div>

        <div class="divAcquista-button">
            <a class="acquista-button" href="${pageContext.request.contextPath}/ordineServlet">Acquista</a>
        </div>
    </form>
</div>

<%@ include file="footer.jsp" %>

</body>
</html>