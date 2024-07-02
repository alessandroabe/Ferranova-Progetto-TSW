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

<nav>
    <ul>
        <li style="float: left;"><a class="logo" href="index.jsp"><img src="images/logo.png" alt="home" width="111"
                                                                       height="16"></a></li>
        <!-- TODO: fai in modo che parta prima index, modifica CatalogoServlet-->
        <!--questo a destra-->
        <li><a href=""><img src="images/carrello.png" alt="carrello" width="15" height="15"></a></li>
        <!-- TODO: fai e cose fatte bene con le jstl, che nascondi le cose se loggato -->
        <li class="dropdown">
            <a href="#"><img src="images/utente.png" alt="area utente" width="15" height="15"></a>
            <div class="dropdown-content">
                <a href="login.jsp">Login</a>
                <a href="registrazione.jsp">Registrazione</a>
            </div>
        </li>
    </ul>
</nav>

<!--<h2 id="saluto">Ciao Alessandro, scegli in che categoria acquistare...</h2>-->
<main>
    <div class="catconteiner">
        <div class="catrow">
            <div class="categoria">
                <a href=""><img src="images/Ferramenta.png" alt="ferramenta" width="400" height="400"></a>
            </div>
            <div class="categoria">
                <a href=""><img src="images/Edilizia.png" alt="ferramenta" width="400" height="400"></a>
            </div>
            <div class="categoria">
                <a href=""><img src="images/Giardinaggio.png" alt="ferramenta" width="400" height="400"></a>
            </div>
        </div>
    </div>
</main>


<%@ include file="./WEB-INF/footer.jsp" %>

</body>

</html>