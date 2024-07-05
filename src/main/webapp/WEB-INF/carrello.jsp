<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Cart</title>
</head>
<body>
<!-- TODO: implemantare java e js della gestione del carrello, anche css, probabilmente verranno tutte fuse in area utente-->


<div  class="wrapper" style="margin-top: 200px">


    ${utente.idutente}<br>

    <form action="futura servlet che farà cose" method="post">
        <c:forEach var="entry" items="${carrello.prodotti}">

            <input type="checkbox" name="prodotti" id="${entry.id}">
            ${entry.id}<br>
            ${entry.titolo}<br>

        </c:forEach>
    </form>

</div>

</body>
</html>