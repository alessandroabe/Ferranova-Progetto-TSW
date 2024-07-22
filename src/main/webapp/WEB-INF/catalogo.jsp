<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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

<div class="container-catalogo">
    <div class="sidebar">
        <!-- FIXME: finisci e rendi dinamico con macrocategorie-->
        <form action="catalogo" method="get">
            <h2>${param.categoria}</h2>
            <ul class="ul-sottocategorie">
                <label> Cerca: <br>
                    <input type="text" class="ricerca" name="ricerca" placeholder="cerca articolo" <c:if test="${ not empty param.ricerca }">
                           value="${param.ricerca}" </c:if>">
                </label>
                <br><br>

                <c:forEach var="subCategoria" items="${subCategorie}">
                    <li>
                        <label>
                            <input type="checkbox" name="sottocategorie" value="${subCategoria.key}"
                            <c:if test="${subCategoria.value}"> checked</c:if>>
                                ${subCategoria.key}
                        </label>
                    </li>
                </c:forEach>
            </ul>
            <br>

            <label>Prezzo minimo:<br>
                <input class="rangePrice" type="range" name="prezzoMin" step="1" min="0" max="1000"
                       value="${ not empty param.prezzoMin ? param.prezzoMin : '0'}" oninput="this.nextElementSibling.value = this.value">
                <output>${not empty param.prezzoMin ? param.prezzoMin : '0'}</output>
            </label>
            <br><br>
            <label>Prezzo massimo:<br>
                <input class="rangePrice" type="range" name="prezzoMax" step="1" min="0" max="1000"
                       value="${not empty param.prezzoMax ? param.prezzoMax : '1000'}" oninput="this.nextElementSibling.value = this.value">
                <output>${not empty param.prezzoMax ? param.prezzoMax : '1000'}</output>
            </label>
            <br><br>
            <input type="hidden" name="categoria" value="${param.categoria}">
            <button type="submit" class="redirect-button">Filtra</button>
        </form>
        <br>

        <button class="redirect-button"
                onclick="window.location.replace('${pageContext.request.contextPath}/catalogo?categoria=${param.categoria}');">
            Resetta
        </button>
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
                <p id="alert-${entry.id}" class="alert"></p>
            </div>


            <script>
                function addToCart(product_id) {
                    let xhr = new XMLHttpRequest();
                    xhr.open("GET", "${pageContext.request.contextPath}/carrelloAjax?prod=" + product_id + "&quantity=" + 1, true);
                    //se si vuole passare un parametro da una pagina jsp
                    xhr.onreadystatechange = function () {
                        if (xhr.readyState === XMLHttpRequest.DONE) {
                            if (xhr.readyState === XMLHttpRequest.DONE) {
                                let alertElement = document.getElementById("alert-" + product_id);
                                if (xhr.status === 200) {
                                    alertElement.innerHTML = "Aggiunto al carrello";
                                    alertElement.style.color = "#335e1e";
                                } else if (xhr.status === 400) {
                                    alertElement.innerHTML = "Quantità non disponibile";
                                    alertElement.style.color = "red";
                                } else {
                                    alertElement.innerHTML = "Errore";
                                    alertElement.style.color = "red";
                                }

                                alertElement.style.opacity = 0;
                                alertElement.style.transform = "translateY(-10px)";
                                alertElement.style.display = "block";
                                alertElement.offsetHeight; // Forza il reflow per applicare le nuove proprietà
                                alertElement.style.transition = "opacity 0.5s, transform 0.5s";
                                alertElement.style.opacity = 1;
                                alertElement.style.transform = "translateY(10px)";

                                setTimeout(function () {
                                    alertElement.style.transition = "opacity 0.5s, transform 0.5s";
                                    alertElement.style.opacity = 0;
                                    alertElement.style.transform = "translateY(-10px)";
                                }, 2000); //dopo 3 secondi scompare

                                setTimeout(function () {
                                    alertElement.style.display = "none";
                                }, 2500); //dopo 4 secondi scompare
                            }
                        }
                    };
                    xhr.send();
                }
            </script>


        </c:forEach>
    </div>
</div>

<%@ include file="footer.jsp" %>

</body>
</html>

