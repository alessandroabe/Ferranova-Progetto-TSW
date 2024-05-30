<%@ page import="org.unisa.abeilleamorellifontana_pj.Model.Utente" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login Ferranova</title>
    <link rel="icon" type="image/x-icon" href="images/favicon.ico">
    <link rel="stylesheet" href="Styles/loginStyle.css"/>
</head>

<body>

<nav>
    <img src="images/logo.png" alt="home" width="130" height="26" >
</nav>


<!--
  Cliccando si verifica sell'utente è nel db, se c'è si accede alla sessione,
  altrimenti riporta errore credenziali errate, e nel caso ci si voglia registrare
  cliccare il link sotto
 -->


<div class="wrapper">
    <form action="login" method="post">

        <h3>Accedi al sito</h3>
        <div class="login">
            <input type="email" id="email" placeholder="email" name="email"><br><br>
            <input type="password" id="password" placeholder="password" name="password"><br><br>

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