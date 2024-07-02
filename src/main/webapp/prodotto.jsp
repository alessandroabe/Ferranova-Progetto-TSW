<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <title>Pagina ${prodotto.titolo}</title>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/loginStyle.css"/>
</head>
<body>

<div class="wrapper" style="margin-top: 200px">
    ${prodotto.id}<br>
    ${prodotto.titolo}<br>
    ${prodotto.descrizione}<br>
    ${prodotto.quantita}<br>
    ${prodotto.categoria}<br>
    ${prodotto.prezzo}<br>
</div>

<div><a href="">aggiungi al carrello</a></div>
</body>
</html>
