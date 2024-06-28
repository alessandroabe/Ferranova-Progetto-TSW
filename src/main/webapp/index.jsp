<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="it">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"> <!--per rendere la pagina respobsive-->
    <title>Ferranova</title>
    <link rel="icon" type="image/x-icon" href="images/favicon.ico">
    <link rel="stylesheet" href="Styles/ferraStyle.css"/>

</head>

<body>

<nav>
    <ul>
        <li style="float: left;"><a class="logo" href="index.jsp"><img src="images/logo.png" alt="home" width="111" height="16" ></a></li>

        <!--questo a destra-->
        <li><a href=""><img src="images/carrello.png" alt="carrello" width="15" height="15" ></a></li>

        <li class="dropdown">
            <a href="#"><img src="images/utente.png" alt="area utente" width="15" height="15" ></a>
            <div class="dropdown-content">
                <a href="login.jsp">Login</a>
                <a href="registrazione.jsp">Registrazione</a>
            </div>
        </li>
    </ul>
</nav>

<!--<h2 id="saluto">Ciao Alessandro, scegli in che categoria acquistare...</h2>-->
<main>
    <div class="catconteiner">
        <div class="catrow">
            <div class="categoria">
                <a href=""><img src="images/Ferramenta.png" alt="ferramenta" width="400" height="400"></a>
            </div>
            <div class="categoria">
                <a href=""><img src="images/Edilizia.png" alt="ferramenta" width="400" height="400"></a>
            </div>
            <div class="categoria">
                <a href=""><img src="images/Giardinaggio.png" alt="ferramenta" width="400" height="400"></a>
            </div>
        </div>
    </div>
</main>



<footer>
    <div class="row">
        <div class="footer-col">
            <h3>Link Utili</h3>
            <ul class="list">
                <li><a href="index.jsp">Home</a></li>
                <li><a href="chiSiamo.html">Chi siamo</a></li>
                <li><a href="terminiEPrivacy.html">Termini e Privacy</a></li>
            </ul>
        </div>
        <div class="footer-col">
            <h3>Contatti</h3>
            <ul>
                <li><p>Email: ferranova@progettotsw.com</p></li>
                <li><p>Tel: +39 1234567890</p></li>
                <li><address>Indirizzo: Unisa, Fisciano(SA)</address></li>
            </ul>
        </div>
        <div class="footer-col">
            <h3>Seguici</h3>
            <ul>
                <li><a href="https://www.facebook.com/" target="_blank">Facebook</a></li>
                <li><a href="https://www.instagram.com/" target="_blank">Instagram</a></li>
                <li><a href="https://tiktok.com/" target="_blank">TikTok</a></li>
            </ul>
        </div>
    </div>
</footer>
</body>

</html>