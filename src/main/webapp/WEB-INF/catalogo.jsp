<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <title>Catalogo Ferranova</title>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/log_regStyle.css"/>

</head>
<body>

<%@ include file="navbar.jsp" %>
<!-- TODO: codice css da fare ed estetica da rifare -->
<div>
    <br><br><br><br><br><br>
    <c:if test="${not empty UtenteConnesso }">

        ${UtenteConnesso.nome} <br><br>
        ${UtenteConnesso.email} <br><br>
        ${UtenteConnesso.admin} <br><br>
    </c:if>
</div>

<!-- TODO: levare le if e mettere il whose -->
<c:if test="${UtenteConnesso != null }">

    <div>
        <button id="logout" value="logout"
                onclick="location.href='${pageContext.request.contextPath}/logout'">
            logout
        </button>
    </div>

</c:if>

<div class="wrapper" style="margin-top: 200px">


    <c:forEach var="entry" items="${catalogo}">

        <a href="${pageContext.request.contextPath}/prod?id_prodotto=${entry.id}">${entry.titolo}</a><br>

    </c:forEach>
</div>


<c:if test="${sessionScope.UtenteConnesso == null }">
    <div>
        <a href="${pageContext.request.contextPath}/login.jsp">login</a>
        <a href="${pageContext.request.contextPath}/registrazione.jsp">registrazione</a>
    </div>
</c:if>
<a href="${pageContext.request.contextPath}/index.jsp">Home</a>

<%@ include file="footer.jsp" %>

</body>
</html>
