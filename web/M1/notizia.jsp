<%-- 
    Document   : notizie
    Created on : 10-apr-2018, 10.22.09
    Author     : marcoortu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
    <head>
        <title>MusicAll-notizie</title>
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
        
        <!--Corpo della pagina notizia-->
        <article>
            <!--Sezione per l'articolo della pagina-->
            <section class="news">
                <c:choose>
                    <c:when test="${requestScope['nid'] == -1}"> <!--Mostra tutti gli atricoli-->
                        <c:forEach var="n" items="${newsList}">
                            <section class="news sectionews" id="${n.getId()}">
                                <h1><a href="notizia.html?nid=${n.id}">${n.title}</a></h1>
                            <div>
                                <a href="notizia.html?nid=${n.id}"><img src="${n.img}" alt="${n.altImg}" height="160" width="180"></a>
                                <h6 class="category">${n.category}</h6>
                            </div>
                            <div>
                                <c:choose>
                                    <c:when test="${n.content.length() > 100}">
                                        <p>${n.content.substring(0, 100)}...</p>
                                        <h5>${n.author.name}  ${n.date}</h5>
                                    </c:when>
                                    <c:otherwise>
                                        <p>${n.content}</p>
                                        <h5>${n.author.name}  ${n.date}</h5>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            </section>
                        </c:forEach>
                    </c:when>
                    <c:otherwise> <!--Nella pagina viene mostrata la notizia cliccata-->
                        <h2 class="news">${news.title}</h2>
                        <br>
                        <div class="imgnews">
                            <img class="imgnews" src="${news.img}" alt="${news.altImg}">
                             <!--Descrizione immagine-->
                            <h6 class="news">${news.descrImg}</h6>
                        </div>

                        <div class="infonews">
                            <!--Informazione su autore , data di creazione e categoria dell’articolo-->
                            <h5 class="news"><strong>${news.category}</strong><br>${news.date}<br>di ${news.author.name}</h5>
                        </div>

                        <div class="paragnews">
                            <!--Testo dell'articolo-->
                            <p>
                                ${news.content}
                            </p>
                        </div>
                        
                        <h2 class="comments">Commenti:</h2>
                        
                        <c:forEach var="c" items="${commentList}">    
                            <section class="news">
                                <h5>${c.author.getName()} ${c.author.getSurname()}</h5>
                                <hr/>
                                <p>${c.content}</p>
                            </section>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </section>
        </article>
        <!--Sezione footer con logo-->
       <jsp:include page="footer.jsp" />
    </body>
</html>

