<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
  <title>Registrazione Ferranova</title>
  <link rel="stylesheet" href="Styles/loginStyle.css"/>
  <link rel="icon" type="image/x-icon" href="images/favicon.ico">

</head>

<body>

<nav>
  <img src="images/logo.png" alt="home" width="130" height="26" >
</nav>

<!--
     Cliccando il pulsante viene registrato un nuovo utente
-->

<%//if(session.getAttribute("alreadysub") != null && (boolean) session.getAttribute("alreadysub") == true)%>
<%//= "Questo account esiste gia"%>
<%//}%>


<!-- controllo account già esistente, in caso positivo showAlert diventa true-->
<%
  boolean showAlert = session.getAttribute("alreadysub") != null && (boolean) session.getAttribute("alreadysub") == true;
%>

  <div class="wrapper">
    <form action="registrazione" method="post">

      <h3>Registrati al sito</h3>

      <input type="text" id="nome" placeholder="nome" name="nome" required><br><br>
      <!--<input type="text" id="cognome" placeholder="cognome" required><br><br> Si deve mettere il cognome?-->
      <input type="text" id="telefono" placeholder="telefono"><br><br>
      <input type="email" id="email" placeholder="email" name="email"><br><br>
      <input type="password" id="password" placeholder="password" name="password" required><br><br>

      <!-- messaggio alert-->
      <div id="alert" class="alert" style="display:none;">Account già esistente</div><br>

      <input type="submit" id="submit" value="Registra"/>
    </form>
  </div>

<!-- mostra l'alert se showAlert è true-->
<script type="text/javascript">
  <% if (showAlert) { %>
  document.getElementById('alert').style.display = 'block';
  <% } %>
</script>

</body></html>