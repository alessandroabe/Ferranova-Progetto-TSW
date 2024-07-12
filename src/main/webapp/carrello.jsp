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

<%@ include file="WEB-INF/navbar.jsp" %>

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
            <tr>
                <td>
                    <div class="product-info">
                        <img src="${pageContext.request.contextPath}/product_images/${prodotto.id}/1.png" style="width:100%"
                             alt="immagine ${prodotto.titolo}">
                        <div>
                            <p>${prodotto.titolo}</p>
                            <span>Cod. prodotto: ${prodotto.id}</span>
                        </div>
                    </div>
                </td>
                <td>1 Pz.</td>
                <td>10,00€</td>
            </tr>
            </tbody>
        </table>
    </div>
    <div class="summary">
        <h2>Riassunto</h2>
        <p>Subtotale: <span>280,37 €</span></p>
        <p>Costi di spedizione: <span>0,00 €</span></p>
        <p class="total">Totale: <span>280,37 €</span></p>
        <button>ACQUISTA</button>
    </div>
</div>

<%@ include file="WEB-INF/footer.jsp" %>

</body>
</html>