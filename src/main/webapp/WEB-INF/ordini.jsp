<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"> <!--per rendere la pagina respobsive-->
    <title>Ordini Ferranova</title>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ordiniStyle.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbarStyle.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/footerStyle.css"/>
</head>
<body>

<%@ include file="navbar.jsp" %>

<h1>Ordini Effettuati</h1>
<div class="orders-container">
    <div class="order">
        <div class="order-header">
            <p class="dataOrdine"><strong>28.04.2024</strong></p>
            <div class=" order-details">
                <p><strong>ID ordine:</strong> 10401180320751</p>
                <p><strong>Stato:</strong> ordinato</p>
                <p><strong>Totale:</strong> 69,98 €</p>
                <p class="dataSpedizione"><strong>Data spedizione:</strong> 28.04.2024</p>
                <p><strong>Costo spedizione:</strong> 10,00 €</p>
                <p class="dataConsegna"><strong>Data consegna:</strong> -</p>

            </div>
        </div>
        <div class="order-products">
            <div class="product">
                <a href="prodotto.html"><img src="/images/cacciaviteCroce.jpg" alt="cacciavite a croce"></a>
                <p>cacciavite a croce</p>
                <p>2 Pz.</p>
                <p>50 €</p>
            </div>
            <div class="product">
                <img src="/images/martello.jpg" alt="martello">
                <p>Martello</p>
                <p>5 Pz.</p>
                <p>50 €</p>
            </div>
            <div class="product">
                <img src="/images/trapanoPercussione.jpg" alt="trapano a percussione">
                <p>Trapano a percussione</p>
                <p>1 Pz.</p>
                <p>50 €</p>
            </div>
            <div class="product">
                <img src="/images/trapanoPercussione.jpg" alt="trapano a percussione">
                <p>Trapano a percussione</p>
                <p>1 Pz.</p>
                <p>50 €</p>
            </div>
            <div class="product">
                <img src="/images/trapanoPercussione.jpg" alt="trapano a percussione">
                <p>Trapano a percussione</p>
                <p>1 Pz.</p>
                <p>50 €</p>
            </div>
            <div class="product">
                <img src="/images/trapanoPercussione.jpg" alt="trapano a percussione">
                <p>Trapano a percussione</p>
                <p>1 Pz.</p>
                <p>50 €</p>
            </div>
        </div>

    </div>
    <!-- Repeat similar blocks for other orders -->
    <div class="order">
        <div class="order-header">
            <p class="dataOrdine"><strong>28.04.2024</strong></p>
            <div class=" order-details">
                <p><strong>ID ordine:</strong> 10401180320751</p>
                <p><strong>Stato:</strong> ordinato</p>
                <p><strong>Totale:</strong> 69,98 €</p>
                <p class="dataSpedizione"><strong>Data spedizione:</strong> 28.04.2024</p>
                <p><strong>Costo spedizione:</strong> 10,00 €</p>
                <p class="dataConsegna"><strong>Data consegna:</strong> -</p>

            </div>
        </div>
        <div class="order-products">
            <div class="product">
                <a href="prodotto.html"><img src="/images/cacciaviteCroce.jpg" alt="cacciavite a croce"></a>
                <p>cacciavite a croce</p>
                <p>2 Pz.</p>
                <p>50 €</p>
            </div>
            <div class="product">
                <img src="/images/martello.jpg" alt="martello">
                <p>Martello</p>
                <p>5 Pz.</p>
                <p>50 €</p>
            </div>
            <div class="product">
                <img src="/images/trapanoPercussione.jpg" alt="trapano a percussione">
                <p>Trapano a percussione</p>
                <p>1 Pz.</p>
                <p>50 €</p>
            </div>
            <div class="product">
                <img src="/images/trapanoPercussione.jpg" alt="trapano a percussione">
                <p>Trapano a percussione</p>
                <p>1 Pz.</p>
                <p>50 €</p>
            </div>
            <div class="product">
                <img src="/images/trapanoPercussione.jpg" alt="trapano a percussione">
                <p>Trapano a percussione</p>
                <p>1 Pz.</p>
                <p>50 €</p>
            </div>
            <div class="product">
                <img src="/images/trapanoPercussione.jpg" alt="trapano a percussione">
                <p>Trapano a percussione</p>
                <p>1 Pz.</p>
                <p>50 €</p>
            </div>
        </div>
    </div>
</div>

<%@ include file="footer.jsp" %>

</body>
</html>
