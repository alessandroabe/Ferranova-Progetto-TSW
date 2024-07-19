<%@ page import="org.unisa.abeilleamorellifontana_pj.Model.Utente" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cambio Password Ferranova</title>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/log_regStyle.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbarStyle.css"/>


</head>

<body>

<%@ include file="/WEB-INF/navbar.jsp" %>


<div class="wrapper">
    <form action="${pageContext.request.contextPath}/passwordServlet" method="post">

        <h3>Cambio Password</h3>

        <div class="cambioPassword">


                <label for="currentPassword">Password Corrente:</label>
                <input type="password" id="currentPassword" name="currentPassword" required /><br/>

                <label for="newPassword">Nuova Password:</label>
                <input type="password" id="newPassword" name="newPassword" required /><br/>

                <c:if test="${param.error == 1}">
                    <div id="alert" class="alert">Vecchia password errata</div>
                    <br>
                </c:if>

                <input id="submit" type="submit" value="Cambia Password" />


        </div>

    </form>

</div>

</body>
</html>