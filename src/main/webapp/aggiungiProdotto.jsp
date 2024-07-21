<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Aggiungi Prodotti Ferranova</title>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/aggiungiProdottoStyle.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbarStyle.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/footerStyle.css"/>
</head>
<body>

<%@ include file="/WEB-INF/navbar.jsp" %>

<div class="container">
    <h1>Aggiungi Prodotto</h1>
    <form action="aggiungi-prodotto" method="post">
        <label for="titolo">Titolo:</label>
        <input type="text" id="titolo" name="titolo" required>

        <label for="descrizione">Descrizione:</label>
        <textarea id="descrizione" name="descrizione"></textarea>

        <label for="quantità">Quantità:</label>
        <input type="number" id="quantità" name="quantità" required>

        <label for="id_promozione">ID Promozione:</label>
        <input type="number" id="id_promozione" name="id_promozione">

        <label for="select-category">Categoria:</label>
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


        <label for="prezzo">Prezzo:</label>
        <input type="number" step="0.01" id="prezzo" name="prezzo" required>

        <label for="immagini">Immagini:</label>
        <input type="file" id="immagini" name="immagini[]" accept="image/*" multiple>

        <button type="submit">Aggiungi Prodotto</button>
    </form>
</div>

<%@ include file="/WEB-INF/footer.jsp" %>

</body>
</html>
