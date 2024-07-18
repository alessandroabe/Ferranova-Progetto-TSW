<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<nav>

    <ul class="ul-navbar">
        <li class="logo"><a href="${pageContext.request.contextPath}/index.jsp"><img src="images/logo.png" alt="home" width="111" height="16"></a></li>

        <div class="dropdown-link">

        <li class="nomeUtente"><p>${UtenteConnesso.nome}</p></li>

        <li class="dropdown">
            <a href="#"><i class="fa-solid fa-user" style="color: white; font-size: 16px"></i></a>
            <div class="dropdown-content">
                <c:if test="${ empty UtenteConnesso}">
                    <a href="${pageContext.request.contextPath}/login.jsp">Login</a>
                    <a href="${pageContext.request.contextPath}/registrazione.jsp">Registrazione</a>
                </c:if>

                <c:if test="${not empty UtenteConnesso}">
                    <a href="${pageContext.request.contextPath}/profilo.jsp">Profilo</a>
                    <a href="#">Ordini</a>
                    <a href="${pageContext.request.contextPath}/logout">Logout</a>
                </c:if>
            </div>
        </li>

        <li class="cart"><a href="${pageContext.request.contextPath}/carrello"><i class="fa-solid fa-cart-shopping" style="color: white; font-size: 16px"></i></a></li>

        </div>
    </ul>
</nav>