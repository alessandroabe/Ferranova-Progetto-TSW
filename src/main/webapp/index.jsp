<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="it">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"> <!--per rendere la pagina respobsive-->
    <title>Ferranova</title>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ferraStyle.css"/>

</head>

<body>

<%@ include file="/WEB-INF/navbar.jsp" %>


<c:if test="${not empty UtenteConnesso }">

    <h2 id="saluto">Ciao ${UtenteConnesso.nome}, scegli in che categoria acquistare...</h2>

</c:if>

<main>
    <div class="catconteiner">
        <div class="catrow">
            <div class="categoria">
                <a href="catalogo?categoria=Ferramenta"><img src="images/Ferramenta.png" alt="ferramenta" width="400" height="400"></a>
            </div>
            <div class="categoria">
                <a href="catalogo?categoria=Edilizia"><img src="images/Edilizia.png" alt="ferramenta" width="400" height="400"></a>
            </div>
            <div class="categoria">
                <a href="catalogo?categoria=Agricoltura"><img src="images/Giardinaggio.png" alt="ferramenta" width="400" height="400"></a>
            </div>
        </div>
    </div>
</main>


<%@ include file="./WEB-INF/footer.jsp" %>
</body>

</html>