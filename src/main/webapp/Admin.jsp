<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pagina Admin Ferranova</title>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminStyle.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbarStyle.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/footerStyle.css"/>

</head>

<body>

<%@ include file="/WEB-INF/navbar.jsp" %>

<c:set var="mappa" value="${Carrello.prodottiQuantita}"/>
<c:set var="lista" value="${lista}"/>

<div class="buttons-container">
    <div>
        <button class="productsManage-button" aria-label="productsManage-button" tabindex="0" onkeydown="toggleView('products')" onclick="toggleView('products')">Gestione Prodotti</button>
    </div>

    <div>
        <button class="ordersManage-button" aria-label="ordersManage-button" tabindex="0" onkeydown="toggleView('orders')" onclick="toggleView('orders')">Gestione ordini clienti</button>
    </div>
</div>

<!--Parte della gestione prodotti-->

<div id="productsManage-container">
    <h2>Lista prodotti</h2>
    <div class="headContainer">
        <div class="select-categoryDiv" >
            <form action="select-category">
                <label for="select-category">Seleziona categoria</label>
                <select name="languages" id="select-category">
                    <option value="all">All</option>
                    <option value="ferramenta">ferramenta</option>
                    <option value="edilizia">edilizia</option>
                    <option value="giardinaggio">giardinaggio</option>
                </select>

                <input type="submit" id="submit-categoria" value="Seleziona"/>
            </form>
        </div>
        <div class="addProduct">
            <a href="">&plus; Aggiungi un nuovo prodotto</a>
        </div>
    </div>


    <table>
        <thead>
        <tr>
            <th>Prodotto</th>
            <th class="quantity-title">Quantità disponibile</th>
            <th class="price-title">Modifica prezzo</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach var="elemento" items="${lista}">
            <tr id="product-row-${elemento.id}">
                <td>
                    <div class="product-info">
                        <a href="${pageContext.request.contextPath}/prod?id_prodotto=${elemento.id}"><img
                                src="images/cacciaviteCroce.jpg"
                                alt="immagine ${elemento.titolo}"></a>

                        <div>
                            <p>cacciavite a croce</p>
                            <span>Cod. prodotto: 2</span>
                        </div>
                    </div>
                </td>
                <td>
                    <div class="quantity-container">
                        <button class="minus" aria-label="minus" tabindex="0" onkeydown="updateQuantity(prodottoID, 'update', -1)" onclick="updateQuantity(prodottoID, 'update', -1)">&minus;</button>
                        <p id="quantity-${elemento.id}">2 Pz.</p>
                        <button class="plus" aria-label="plus" tabindex="0" onkeydown="updateQuantity(prodottoID, 'update', 1)" onclick="updateQuantity(prodottoID, 'update', 1)">&plus;</button>
                        <button class="delete" aria-label="delete" tabindex="0" onkeydown="updateQuantity(prodottoID, 'update', 0)" onclick="updateQuantity(prodottoID, 'update', 0)">rimuovi prodotto</button>
                    </div>
                </td>
                <td id="price-${elemento.id}">
                    <div class="price-container">
                        <button class="minus"  aria-label="minus" tabindex="0" onkeydown="updatePrice(prodottoID, 'update', -1)" onclick="updatePrice(prodottoID, 'update', -1)">&minus;</button>
                        <p id="quantity-${elemento.id}">5,00 €</p>
                        <button class="plus" aria-label="plus" tabindex="0" onkeydown="updatePrice(prodottoID, 'update', 1)" onclick="updatePrice(prodottoID, 'update', 1)">&plus;</button>
                    </div>
                </td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
</div>



<!--Parte della gestione ordini-->

<div id="ordersManage-container">

    <c:forEach var="Ordine" items="${Ordini}">
        <div class="order">
            <div class="order-header">
                <p class="dataOrdine"><strong>${Ordine.dataOrdine}</strong></p>
                <div class=" order-details">
                    <p><strong>ID ordine:</strong>1</p>
                    <p><strong>Stato:</strong>ordinato</p>
                    <p><strong>Subtotale:</strong><fmt:setLocale value="fr_FR"/>
                        <!-- Imposta la localizzazione su Francia che usa l'Euro -->
                        <fmt:formatNumber value="${Ordine.prezzoOrdine}" type="currency"
                                          currencySymbol="€"/></p>
                    <p class="dataSpedizione"><strong>Data spedizione:</strong> - </p>
                    <p><strong>Costo spedizione:</strong><fmt:setLocale value="fr_FR"/>
                        <!-- Imposta la localizzazione su Francia che usa l'Euro -->
                        <fmt:formatNumber value="${Ordine.prezzoSpedizione}" type="currency"
                                          currencySymbol="€"/></p>
                    <p class="dataConsegna"><strong>Data consegna:</strong> - </p>


                    <form class="modificaOrdine">
                        <div>
                            <label for="statoOrdine">Modifica Stato ordine</label>
                            <select name="languages" id="statoOrdine">
                                <option value="ordinato">ordinato</option>
                                <option value="spedito">spedito</option>
                                <option value="consegnato">consegnato</option>
                            </select>
                        </div>

                        <div>
                            <label for="dataSpedizione">Modifica data di spedizione:</label>
                            <input type="date" id="dataSpedizione" name="dataSpedizione">
                        </div>

                        <div>
                            <label for="birthday">Modifica data di consegna:</label>
                            <input type="date" id="birthday" name="dataSpedizione">
                        </div>

                        <input type="submit" id="submit" value="Modifica"/>
                    </form>
                </div>
            </div>
            <div class="order-products">

                <c:forEach var="entry" items="${listaProdottiOrine}">

                    <div class="product">
                        <a href="prodotto.html"><img src="images/cacciaviteCroce.jpg" alt="${entry.nome}"></a>
                        <p>cacciavite</p>
                        <p>2 Pz.</p>
                        <p><fmt:setLocale value="fr_FR"/>
                            <!-- Imposta la localizzazione su Francia che usa l'Euro -->
                            <fmt:formatNumber value="${(mappa[elemento.id]).prezzo}" type="currency"
                                              currencySymbol="€"/></p>
                    </div>
                </c:forEach>

            </div>
        </div>
    </c:forEach>

</div>


<%@ include file="/WEB-INF/footer.jsp" %>

<script>
    function toggleView(view) {
        var productContainer = document.getElementById('productsManage-container');
        var orderContainer = document.getElementById('ordersManage-container');

        if (view === 'products') {
            productContainer.style.display = 'block';
            orderContainer.style.display = 'none';
        } else if (view === 'orders') {
            productContainer.style.display = 'none';
            orderContainer.style.display = 'block';
        }
    }
</script>

</body>
</html>