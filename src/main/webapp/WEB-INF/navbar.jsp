<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbarStyle.css"/>

</head>

<nav>

    <ul>
        <li style="float: left;"><a class="logo" href="index.jsp"><img src="images/logo.png" alt="home" width="111"
                                                                       height="16"></a></li>

        <!--questo a destra-->
        <li><a href="carrello.jsp"><img src="images/carrello.png" alt="carrello" width="15" height="15"></a></li>

        <li class="dropdown">
            <a href="#"><img src="images/utente.png" alt="area utente" width="15" height="15"></a>
            <div class="dropdown-content">
                <c:if test="${ empty UtenteConnesso }">
                    <a href="${pageContext.request.contextPath}/login.jsp">Login</a>
                    <a href="${pageContext.request.contextPath}/registrazione.jsp">Registrazione</a>
                </c:if>

                <c:if test="${not empty UtenteConnesso}">
                    <a href="profilo.jsp">Profilo</a>
                    <a href="ordini.jsp">Ordini</a>
                    <a href="preferiti.jsp">Preferiti</a>
                    <a href="logout">Logout</a>
                </c:if>
            </div>
        </li>
    </ul>
</nav>