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
        <input type="text" id="nome" placeholder="nome" name="nome" required><br>

        <label for="cognome">Cognome:</label>
        <input type="text" id="cognome" placeholder="cognome" required><br>


        <h4>Indirizzo</h4>

        <label for="citta">Città:</label>
        <input type="text" id="citta" placeholder="città" required><br>

        <label for="cap">CAP:</label>
        <input type="text" id="cap" placeholder="CAP" required><br>

        <label for="via">Via:</label>
        <input type="text" id="via" placeholder="via" required><br>

        <label for="n_civico">Numero civico:</label>
        <input type="text" id="n_civico" placeholder="numero civico" required><br>


        <h4>Dati carta</h4>

        <label for="intestatario">Intestatario della carta:</label>
        <input type="text" id="intestatario" placeholder="intestatario della carta" required><br>

        <label for="n_carta">Numero carta:</label>
        <input type="text" id="n_carta" placeholder="numero della carta" required><br>

        <label for="scadenza">Scadenza:</label>
        <input type="text" id="scadenza" placeholder="scadenza" required><br>

        <label for="cvv">CVV:</label>
        <input type="text" id="cvv" placeholder="CVV" required><br>


        <h5>Hai un coupon?</h5>

        <label for="coupon">Codice promozionale:</label>
        <input type="text" id="coupon" placeholder="Codice promozionale"><br>

        <div class="total-price">
            <p>Totale: 100€</p>
        </div>


        <input type="submit" id="submit" value="Acquista"/>
    </form>
</div>

<%@ include file="footer.jsp" %>

</body>
</html>