<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chi siamo Ferranova</title>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbarStyle.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/footerStyle.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/privacyStyle.css"/>
</head>
<body>

<%@ include file="./WEB-INF/navbar.jsp" %>

<div class="container">
    <h1>Chi siamo?</h1>

    <h2>Sottotitolo</h2>
    <p>Paragrafo</p>

</div>


<%@ include file="./WEB-INF/footer.jsp" %>

</body>
</html>
