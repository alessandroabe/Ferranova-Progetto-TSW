<%@ page import="org.unisa.abeilleamorellifontana_pj.Model.Utente" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Ferranova</title>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/log_regStyle.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbarStyle.css"/>


</head>

<body>

<%@ include file="/WEB-INF/navbar.jsp" %>


<div class="wrapper">
    <form action="login" method="POST">

        <h3>Accedi al sito</h3>

        <div class="login">
            <label for="email">Email</label>
            <input type="email" id="email"  name="email"><br>
            <label for="password">Password</label>
            <input type="password" id="password" name="password"><br>

            <c:if test="${param.error == 1}">
                <div id="alert" class="alert">Email e/o password errate</div>
                <br>
            </c:if>
            <input type="submit" id="submit" value="Login"/>
        </div>

        <div class="registrazione">
            <p>Sei nuovo su Ferranova?</p>
            <a href="registrazione.jsp">registrati</a>
        </div>

    </form>
</div>

</body>
</html>