<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${prodotto.titolo} Ferranova</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/prodottoStyle.css">
</head>
<body>

<%@ include file="navbar.jsp" %>

<div class="product-container">


    <!--
    FIXME: fare la versione con più immagini
    <c:forEach var="i" begin="1" end="2">

        <img src="${pageContext.request.contextPath}/product_images/${prodotto.id}/${i}.png"
             alt="immagine ${prodotto.titolo}"><br>

    </c:forEach>
    -->
    <div class="product-image">
        <img src="${pageContext.request.contextPath}/product_images/${prodotto.id}/1.png"
             alt="immagine ${prodotto.titolo}"><br>
    </div>

    <div class="product-details">
        <h1 class="product-title">${prodotto.titolo}</h1>
        <div class="product-price">
            <fmt:setLocale value="fr_FR"/> <!-- Imposta la localizzazione su Francia che usa l'Euro -->
            <fmt:formatNumber value="${prodotto.prezzo}" type="currency" currencySymbol="€"/></div>
        <form action="" method="post">

        <div class="quantity-container">
            <label for="quantity">Quantità: ${prodotto.quantita}</label><br>
            <!-- FIXME: br penso da levare -->
            <input type="number" id="quantity" name="quantity" min="1" value="1" max="${prodotto.quantita}">
        </div>

        <!--TODO mettere soltanto le jstl per iterare i prodotti in base al prodotto cliccato e inserirlo nell'ajax, adesso è con il form solo per testing-->

        <input type="submit" class="add-to-cart" value="Aggiungi al carrello"/>
        </form>

    </div>

</div>

<div class="product-description">
    <h2>Descrizione</h2>
    <p>${prodotto.descrizione}</p>
</div>

<%@ include file="footer.jsp" %>

</body>
</html>
