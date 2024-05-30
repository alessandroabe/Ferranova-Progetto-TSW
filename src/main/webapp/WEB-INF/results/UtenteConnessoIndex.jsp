<%@ page import="org.unisa.abeilleamorellifontana_pj.Model.Utente" %><%--
  Created by IntelliJ IDEA.
  User: chris
  Date: 28/05/2024
  Time: 17:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Homepage</title>
</head>
<body>
<%Utente Connesso = (Utente) session.getAttribute("UtenteConnesso");%>
<%if((boolean) session.getAttribute("connessione") == true) {%>
<%= "Utente Connesso: " + Connesso.getEmail()%>
<%}//dati riguardanti l'account che si e' connesso%>
</body>
</html>