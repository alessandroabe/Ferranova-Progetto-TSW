<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>nome prodotto Ferranova</title>
<link rel="stylesheet" href="css/prodottoStyle.css">
</head>
<body>

<%@ include file="WEB-INF/navbar.jsp" %>

<div class="product-container">

<div class="product-image">
<img src="product_images/1/1.png" alt="Martello da Falegname">
</div>

<div class="product-details">
<h1 class="product-title">MARTELLO DA FALEGNAME CON MANICO LEGNO</h1>
<p class="product-price">4,99 €</p>
<div class="quantity-container">
<label for="quantity">Quantità: </label>
<input type="number" id="quantity" name="quantity" min="1" value="1">
</div>

<!--TODO usare onclick per aggiungere al carrello-->

<button class="add-to-cart">Aggiungi al carrello</button>

</div>



</div>

<div class="product-description">
    <h2>Descrizione</h2>
    <p>TESTA ACCIAIO FORGIATO</p>
</div>



</body>

<%@ include file="WEB-INF/footer.jsp" %>
</html>
