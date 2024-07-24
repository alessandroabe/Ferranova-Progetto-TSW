<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isErrorPage="true" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Errore Ferranova</title>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/acquistoConSuccessoStyle.css">
</head>
<body>


<div class="container">
    <div class="acquisto-corretto">
        <img src="${pageContext.request.contextPath}/images/Errore.png" alt="Errore">
    </div>
    <div>
        <a class="back-home" href="${pageContext.request.contextPath}/index.jsp">Torna alla home</a>
    </div>
</div>


<h1>Si è verificato un errore</h1>
<p>Errore: ${exception.message}</p>
<p>Dettagli: ${exception.stackTrace}</p>

</body>
</html>