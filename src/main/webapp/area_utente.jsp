<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Area Utente</title>
</head>
<body>
<!-- TODO: implemantare java e js della gestione dell'area utente, anche css-->



<c:if test="${not empty UtenteConnesso }">

    ${UtenteConnesso.nome} <br><br>
    ${UtenteConnesso.email} <br><br>
    ${UtenteConnesso.admin} <br><br>
</c:if>


</body>
</html>
