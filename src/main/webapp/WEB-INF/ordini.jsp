<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Ordini</title>
</head>
<body>
<!-- TODO: implemantare java e js della gestione degli ordini, anche css, probabilmente verranno tutte fuse in area utente-->


<div class="wrapper" style="margin-top: 200px">

    <!-- TODO: fare che ci sono codici promozionali-->

    ${utente.idutente}<br>

    <form action="futura servlet che farà cose" method="post">
        <c:forEach var="entry" items="${carrello.prodotti}">

            <a href="${pageContext.request.contextPath}/prod?id_prodotto=${entry.id}">${entry.titolo}</a><br>
            ${entry.id}<br>
            ${entry.titolo}<br>

        </c:forEach>
    </form>

</div>

</body>
</html>