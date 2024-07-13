<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${prodotto.titolo} Ferranova</title>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/prodottoStyle.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/caroselloStyle.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbarStyle.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/footerStyle.css"/>
</head>
<body>

<%@ include file="navbar.jsp" %>

<div class="product-container">


    <div class="product-image">
        <c:forEach var="i" begin="1" end="${num_foto}">

            <div class="mySlides">
                <div class="numbertext">${i} / ${num_foto}</div>
                <img src="${pageContext.request.contextPath}/product_images/${prodotto.id}/${i}.png" style="width:100%"
                     alt="immagine ${prodotto.titolo}"><br>
            </div>
        </c:forEach>

        <a class="prev" onclick="plusSlides(-1)">&#10094;</a> <!--il codice corrisponde al simbolo della freccetta-->
        <a class="next" onclick="plusSlides(1)">&#10095;</a>
    </div>

    <div class="product-details">
        <h1 class="product-title">${prodotto.titolo}</h1>
        <div class="product-price">
            <fmt:setLocale value="fr_FR"/> <!-- Imposta la localizzazione su Francia che usa l'Euro -->
            <fmt:formatNumber value="${prodotto.prezzo}" type="currency" currencySymbol="€"/></div>
        <form action="" method="post">

            <div class="quantity-container">
                <label for="quantity">Pezzi disponibili: ${prodotto.quantita}</label><br>
                <!-- FIXME: br penso da levare -->
                <input type="number" id="quantity" name="quantity" min="1" value="1" max="${prodotto.quantita}">
            </div>

            <!--TODO mettere soltanto le jstl per iterare i prodotti in base al prodotto cliccato e inserirlo nell'ajax, adesso è con il form solo per testing-->

            <button onclick="addToCart(${prodotto.id})" class="add-to-cart">Aggiungi al carrello</button>
        </form>

        <script>
            function addToCart(product_id) {
                var quantity = document.getElementById("quantity").value;
                var xhr = new XMLHttpRequest();
                xhr.open("GET", "${pageContext.request.contextPath}/carrelloAjax?prod=" + product_id + "&quantity=" + quantity, true);
                //se si vuole passare un parametro da una pagina jsp
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === XMLHttpRequest.DONE) {
                        if (xhr.status === 200) {
                            // La richiesta è stata completata con successo
                            alert("Prodotto aggiunto con successo");
                        } else {
                            alert("errore");
                        }
                    }
                };
                xhr.send();
            }
        </script>

    </div>

</div>

<div class="product-description">
    <h2>Descrizione</h2>
    <p>${prodotto.descrizione}</p>
</div>

<%@ include file="footer.jsp" %>

<script src="${pageContext.request.contextPath}/js/carousel.js"></script>

</body>
</html>
