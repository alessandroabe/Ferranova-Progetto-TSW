<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"> <!--per rendere la pagina respobsive-->
    <title>Ferramenta Ferranova</title>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/catalogoStyle.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbarStyle.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/footerStyle.css"/>

</head>
<body>

<%@ include file="navbar.jsp" %>

<div class="conteiner">
    <div class="sidebar">
        <!-- FIXME: finisci e rendi dinamico con macrocategorie-->
        <h2>Ferramenta</h2>
        <ul>
            <li>Utensili</li>
            <li>Viteria</li>
            <li>Oggettistica</li>
            <li>Elettronica</li>
            <li>Abbigliamento</li>
        </ul>
    </div>

    <div class="content">
<c:forEach var="entry" items="${catalogo}">
        <div class="product">
            <a href="${pageContext.request.contextPath}/prod?id_prodotto=${entry.id}">
                <img src="${pageContext.request.contextPath}/product_images/${entry.id}/1.png"
                     alt="immagine ${entry.titolo}"><br>
                <div class="product-title">${entry.titolo}</div>
            </a>
            <div class="product-price">
                <fmt:setLocale value="fr_FR"/> <!-- Imposta la localizzazione su Francia che usa l'Euro -->
                <fmt:formatNumber value="${entry.prezzo}" type="currency" currencySymbol="€"/></div>
            <button onclick="addToCart(${entry.id})" class="product-button">Aggiungi al carrello</button>
        </div>

    <script>
    function addToCart(product_id){
        var xhr = new XMLHttpRequest();
        xhr.open("GET","${pageContext.request.contextPath}/carrello?prod=" +product_id,true);//si scrive cosi un get
        //se si vuole passare un parametro da una pagina jsp
        xhr.onreadystatechange = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200) {
                    // La richiesta è stata completata con successo
                    alert("Prodotto aggiunto con successo");
                }else{
                    alert("errore");
                }
            }
        };
        xhr.send();
    }



    </script>



</c:forEach>
        <!-- Aggiungi altri prodotti qui -->
    </div>
</div>

<%@ include file="footer.jsp" %>

</body>
</html>

