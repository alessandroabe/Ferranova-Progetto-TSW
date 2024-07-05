<%@ page import="org.unisa.abeilleamorellifontana_pj.Model.Utente" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <title>Login Ferranova</title>
    <link rel="icon" type="image/x-icon" href="images/favicon.ico">
    <link rel="stylesheet" type="text/css" href="css/log_regStyle.css"/>
</head>

<body>

<%@ include file="/WEB-INF/navbar.jsp" %>


<div class="wrapper">
    <form action="login" method="post">

        <h3>Accedi al sito</h3>

        <div class="login">
            <input type="email" id="email" placeholder="email" name="email"><br><br>
            <input type="password" id="password" placeholder="password" name="password"><br><br>

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