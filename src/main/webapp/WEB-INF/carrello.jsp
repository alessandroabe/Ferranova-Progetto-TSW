<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Carrello Ferranova</title>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/carrelloStyle.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbarStyle.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/footerStyle.css"/>
</head>

<body>
<%@ include file="navbar.jsp" %>

<div class="container">
    <div class="cart">
        <h2>Carrello</h2>
        <table>
            <thead>
            <tr>
                <th>Prodotto</th>
                <th>Quantità</th>
                <th>Prezzo</th>
            </tr>
            </thead>
            <tbody>
            <c:set var="mappa" value="${Carrello.prodottiQuantita}"/>
            <c:set var="lista" value="${lista}"/>
            <c:forEach var="elemento" items="${lista}">
                <!-- TODO: fare in modo, penso con ajax, che si aggiorna la cosa con un input type numebr tipo nella pag dispositivi-->
                <tr>
                    <td>
                        <div class="product-info">
                            <img src="${pageContext.request.contextPath}/product_images/${elemento.id}/1.png"
                                 alt="immagine ${elemento.titolo}">
                            <div>
                                <p>${elemento.titolo}</p>
                                <span>Cod. prodotto: ${elemento.id}</span>
                            </div>
                        </div>
                    </td>
                    <td> ${mappa[elemento.id]} Pz.</td>
                    <td><fmt:setLocale value="fr_FR"/> <!-- Imposta la localizzazione su Francia che usa l'Euro -->
                        <fmt:formatNumber value="${elemento.prezzo * mappa[elemento.id] }" type="currency"
                                          currencySymbol="€"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="summary">
        <!-- TODO: conviene farlo in js sotto una certa cifra l'importo è gratis-->

        <c:set var="spedizione" value="${ sum <= 100 ? 10 : 0  }"/>
        <h2>Riassunto</h2>
        <p>Subtotale: <span><fmt:setLocale value="fr_FR"/> <!-- Imposta la localizzazione su Francia che usa l'Euro -->
                    <fmt:formatNumber value="${sum }" type="currency" currencySymbol="€"/></span></p>
        <p>Costi di spedizione: <span><fmt:setLocale value="fr_FR"/>
            <!-- Imposta la localizzazione su Francia che usa l'Euro -->
                    <fmt:formatNumber value="${spedizione }" type="currency" currencySymbol="€"/>
         </span></p>
        <p class="total">Totale: <span><fmt:setLocale value="fr_FR"/>
            <!-- Imposta la localizzazione su Francia che usa l'Euro -->
                    <fmt:formatNumber value="${sum + spedizione}" type="currency" currencySymbol="€"/></span></p>
        <button>ACQUISTA</button>
    </div>
</div>

<%@ include file="footer.jsp" %>

</body>
</html>