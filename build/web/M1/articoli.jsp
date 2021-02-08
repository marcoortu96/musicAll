<%-- 
    Document   : articoli
    Created on : 10-apr-2018, 10.28.55
    Author     : marcoortu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
    <head>
        <title>MusicAll-articoli</title>
        <meta name="author" content="Marco Ortu">
        <meta name="keywords" content="HTML, CSS, Blog, Milestone1, FPW">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="M1/style.css">
    </head>
    
    <body>
       <!--Testata blog, titolo e logo-->
        <jsp:include page="header.jsp" />
        
        <!--navbar-->
        <jsp:include page="nav.jsp" />
  
        <!--aside-->
        <jsp:include page="aside.jsp" />
        
        <c:choose>
            <c:when test="${error == true}">
                <p class="accessdeneid">Accesso negato!</p>
            </c:when>
            <c:otherwise>
                <!--Corpo della pagina articoli-->
                <article>
                  <!--Sezione con tabella che comprende gli articoli della pagina-->
                    <section class="articoli">
                        <h2 class="headerarticle"><strong>I miei articoli</strong></h2>
                        <table>
                            <tr>
                                <th><strong>Data</strong></th>
                                <th><strong>Titolo</strong></th>
                                <th><strong>Modifica</strong></th>
                                <th><strong>Cancella</strong></th>
                            </tr>
                             <c:forEach var="a" items="${articleList}">
                            <tr>
                                <td>${a.date}</td>
                                <td>${a.title}</td>
                                <td><a href="scriviArticolo.html?nid=${a.id}"><img src="M1/img/pencil.png" alt="Modifica" width="40" height="40"></a></td>
                                <td><a href=""><img src="M1/img/cestino.png" alt="Cancella" width="25" height="25"></a></td>
                            </tr>
                            </c:forEach>
                        </table>
                        <!--bottone che manda alla pagina scriviArticolo-->
                        <form action="scriviArticolo.html" method="post">
                          <button class="addarticle" type="submit" name="nuovoArticolo">Aggiungi articolo</button>
                        </form>
                    </section>
                </article>
            </c:otherwise>
        </c:choose>
         <!--Sezione footer con logo-->
       <jsp:include page="footer.jsp" />
    </body>
</html>