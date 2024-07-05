<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <title>Pagina ${prodotto.titolo}</title>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/log_regStyle.css"/>
</head>
<body>

<div class="wrapper" style="margin-top: 200px">
    ${prodotto.id}<br>
    <c:forEach var="i" begin="1" end="2">

        <img src="${pageContext.request.contextPath}/product_images/${prodotto.id}/${i}.png"
             alt="immagine ${prodotto.titolo}"><br>

    </c:forEach>
    ${prodotto.titolo}<br>
    ${prodotto.descrizione}<br>
    ${prodotto.quantita}<br>
    ${prodotto.categoria}<br>
    ${prodotto.prezzo}<br>
</div>

<div>
    <button onclick="loadDoc()">Aggiungi al carrello</button>
    <!--a href="carrello/prod= ${prodotto.id}">aggiungi al carrello</a--></div>


<script>
    function loadDoc() {
        const xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                document.getElementById("demo").innerHTML = this.responseText;
            }
        }
            xhttp.open("GET", "ajax_info.txt");
            xhttp.send();
        }

</script>

</body>
</html>
