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
        <div class="select-categoryDiv">
            <form action="select-category">
                <label for="ricerca" >Cerca: </label>
                <input type="text" id="ricerca" name="ricerca" <c:if test="${ not empty param.ricerca }">
                       value="${param.ricerca}" </c:if>">
                <label for="select-category" class="select-categoryLabel">Categoria:</label>
                <select name="categoria" id="select-category">
                    <optgroup label="ferramenta">
                        <option value="ferramenta-utensili">utensili</option>
                        <option value="ferramenta-prodotti">prodotti</option>
                        <option value="ferramenta-viteria">viteria</option>
                        <option value="ferramenta-elettronica">elettronica</option>
                        <option value="ferramenta-abbigliamento">abbigliamento</option>
                    </optgroup>

                    <optgroup label="edilizia">
                        <option value="edilizia-utensili">utensili</option>
                        <option value="edilizia-materiali">materiali</option>
                        <option value="edilizia-prodotti">prodotti</option>
                        <option value="edilizia-viteria">viteria</option>
                        <option value="edilizia-abbigliamento">abbigliamento</option>
                    </optgroup>

                    <optgroup label="giardinaggio">
                        <option value="giardinaggio-utensili">utensili</option>
                        <option value="giardinaggio-prodotti">prodotti</option>
                        <option value="giardinaggio-semi-piante">semi/piante</option>
                        <option value="giardinaggio-fertilizzanti">fertilizzanti</option>
                        <option value="giardinaggio-abbigliamento">abbigliamento</option>
                    </optgroup>

                </select>
                <input type="submit" id="submit-categoria" value="Seleziona"/>
            </form>
        </div>
        <div class="addProduct">
            <a href="aggiungiProdotto.jsp">&plus; Aggiungi un nuovo prodotto</a>
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
        <c:forEach var="elemento" items="${prodotti}">
            <tr id="product-row-${elemento.id}">
                <td>
                    <div class="product-info">
                        <a href="${pageContext.request.contextPath}/prod?id_prodotto=${elemento.id}"><img src="${pageContext.request.contextPath}/product_images/${elemento.id}/1.png" alt="immagine ${elemento.titolo}"></a>
                        <div>
                            <p>${elemento.titolo}</p>
                            <span>${elemento.id}</span>
                        </div>
                    </div>
                </td>
                <td>
                    <div class="quantity-container">
                        <button class="minus" aria-label="minus" tabindex="0" onkeydown="updateQuantity(${elemento.id}, 'update', -1)" onclick="updateQuantity(${elemento.id}, 'update', -1)">&minus;</button>
                        <p id="quantity-${elemento.id}">${elemento.quantita} Pz.</p>
                        <button class="plus" aria-label="plus" tabindex="0" onkeydown="updateQuantity(${elemento.id}, 'update', 1)" onclick="updateQuantity(${elemento.id}, 'update', 1)">&plus;</button>
                        <button class="azzera" aria-label="azzera" tabindex="0" onkeydown="updateQuantity(${elemento.id}, 'azzera', 0)" onclick="updateQuantity(${elemento.id}, 'azzera', 0)">azzera quantità</button>
                    </div>
                </td>
                <td id="price-${elemento.id}">
                    <div class="price-container">
                        <input type="number" step="0.01" min="0" value="${elemento.prezzo}" id="newPrice-${elemento.id}">
                        <button class="changePrice" aria-label="changePrice" tabindex="0" onkeydown="updatePrice(${elemento.id}, 'updatePrice')" onclick="updatePrice(${elemento.id}, 'updatePrice')">cambia prezzo</button>
                        <p id="alert-${elemento.id}" class="alert alertAnimationOFF"></p>
                    </div>

                </td>
            </tr>

        </c:forEach>
        </tbody>
    </table>
</div>

<!--Parte della gestione ordini-->
<div id="ordersManage-container">
    <h2>Lista Ordini Clienti</h2>
    <c:forEach var="Ordine" items="${Ordini}">
        <div class="order">
            <div class="order-header">
                <p class="dataOrdine"><strong>${Ordine.dataOrdine}</strong></p>
                <div class="orderDetails-container">
                    <div class="order-details">
                        <p><strong>ID ordine:</strong> ${Ordine.idOrdine}</p>
                        <p><strong>ID cliente:</strong> ${Ordine.idUtente}</p>
                        <p><strong>Stato:</strong> ${Ordine.statoOrdine}</p>
                        <p><strong>Subtotale:</strong><fmt:setLocale value="fr_FR"/>
                            <fmt:formatNumber value="${Ordine.prezzoOrdine}" type="currency" currencySymbol="€"/>
                        </p>

                        <c:if test="${ empty Ordine.dataSpedizione}">
                        <p class="dataSpedizione"><strong>Data spedizione:</strong> - </p>
                        </c:if>

                        <c:if test="${ empty Ordine.dataSpedizione}">
                            <p class="dataSpedizione"><strong>Data spedizione:</strong>${Ordine.dataSpedizione}</p>
                        </c:if>

                        <p><strong>Costo spedizione:</strong><fmt:setLocale value="fr_FR"/>
                            <fmt:formatNumber value="${Ordine.prezzoSpedizione}" type="currency" currencySymbol="€"/>
                        </p>

                        <c:if test="${ empty Ordine.dataConsegna}">
                            <p class="dataSpedizione"><strong>Data consegna:</strong> - </p>
                        </c:if>

                        <c:if test="${ empty Ordine.dataConsegna}">
                            <p class="dataSpedizione"><strong>Data consegna:</strong>${Ordine.dataConsegna}</p>
                        </c:if>

                    </div>

                    <div class="order-details-manage">
                        <form action="modificaOrdine" method="get">

                                <label for="statoOrdine">Modifica Stato ordine</label>
                                <select name="stato" id="statoOrdine" required>
                                    <option value="ordinato">ordinato</option>
                                    <option value="spedito">spedito</option>
                                    <option value="consegnato">consegnato</option>
                                </select>

                                <!-- Campo nascosto per l'ID ordine -->
                                <input type="hidden" name="idOrdine" value="${Ordine.idOrdine}"><br>

                                <label for="dataSpedizione">Modifica data di spedizione:</label>
                                <input type="date" id="dataSpedizione" name="dataSpedizione" required><br>

                                <label for="birthday">Modifica data di consegna:</label>
                                <input type="date" id="birthday" name="dataConsegna" required><br>

                            <div class="inputOrderManage">
                                <input type="submit" id="submit" value="Modifica"/>
                            </div>

                        </form>
                    </div>
                </div>
            </div>
            <div class="order-products">
                <c:forEach var="entry" items="${Ordine.prodottiQuantitaOrdine}">
                    <c:set var="prodotto" value="${prodottoHashMap[entry.key]}"/>
                    <div class="product">
                        <a href="${pageContext.request.contextPath}/prod?id_prodotto=${prodotto.id}"><img src="${pageContext.request.contextPath}/product_images/${prodotto.id}/1.png" alt="${prodotto.titolo}"></a>
                        <p>${prodotto.titolo}</p>
                        <p>${entry.value.quantita} Pz.</p>
                        <p><fmt:setLocale value="fr_FR"/>
                            <fmt:formatNumber value="${prodotto.prezzo}" type="currency" currencySymbol="€"/>
                        </p>
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


    function updateQuantity(productId, action, quantity) {
        const xhr = new XMLHttpRequest();
        xhr.open("POST", "${pageContext.request.contextPath}/adminAjax", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.onreadystatechange = function () {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                var alertElement = document.getElementById("alert-" + productId);
                if (xhr.status === 200) {
                    const response = JSON.parse(xhr.responseText);
                    if (response.success) {
                        document.getElementById("quantity-" + productId).innerText = response.newQuantity + ' Pz.';
                        alertElement.innerHTML = "Quantità modificata";
                        alertElement.style.color = "#335e1e";
                    } else {
                        alertElement.innerHTML = "Errore";
                        alertElement.style.color = "red";
                    }
                } else {
                    alertElement.innerHTML = "Errore di connessione";
                    alertElement.style.color = "red";
                }

                // Transizione
                alertElement.classList.remove("alertAnimationOFF");
                alertElement.classList.add("alertAnimationON");


                setTimeout(function () {
                    alertElement.classList.remove("alertAnimationON");
                    alertElement.classList.add("alertAnimationOFF");
                }, 2000); //dopo 3 secondi scompare

            }
        };
        xhr.send("prod=" + productId + "&action=" + action + "&quantity=" + quantity);
    }





    function updatePrice(productId, action) {
        const xhr = new XMLHttpRequest();
        xhr.open("POST", "${pageContext.request.contextPath}/adminAjax", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        let price = document.getElementById("newPrice-" + productId).value;
        var alertElement = document.getElementById("alert-" + productId);
        xhr.onreadystatechange = function () {
            if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
                const response = JSON.parse(xhr.responseText);
                if (response.success) {
                    document.getElementById("newPrice-" + productId).value = response.newPrice;
                    alertElement.innerHTML = "Prezzo modificato";
                    alertElement.style.color = "#335e1e";
                } else {
                    alertElement.innerHTML = "Errore";
                    alertElement.style.color = "red";
                }
            } else {
            alertElement.innerHTML = "Errore di connessione";
            alertElement.style.color = "red";
            }

            // Transizione
            alertElement.style.opacity = 0;
            alertElement.style.transform = "translateX(400px)";
            alertElement.style.display = "block";
            alertElement.offsetHeight; // Forza il reflow per applicare le nuove proprietà
            alertElement.style.transition = "opacity 0.5s, transform 0.5s";
            alertElement.style.opacity = 1;
            alertElement.style.transform = "translateX(410px)";

            setTimeout(function () {
                alertElement.style.transition = "opacity 0.5s, transform 0.5s";
                alertElement.style.opacity = 0;
                alertElement.style.transform = "translateX(400px)";
            }, 2000); //dopo 3 secondi scompare

            setTimeout(function () {
                alertElement.style.display = "none";
            }, 2500); //dopo 4 sec
        };
        xhr.send("prod=" + productId + "&action=" + action + "&price=" + price);
    }

</script>

</body>
</html>
