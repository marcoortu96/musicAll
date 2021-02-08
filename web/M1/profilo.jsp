<%-- 
    Document   : Profilo
    Created on : 27-apr-2018, 8.37.20
    Author     : marcoortu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
       <title>MusicAll-Profilo</title>
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
        
        <!--Corpo della pagina profilo-->
        <article>
            <c:choose>
                <c:when test="${error == true}">
                    <p class="accessdeneid">Accesso negato!</p>
                </c:when>
                <c:otherwise>
                    <c:choose>           
                        <c:when test="${profiloSalvato == true}"> <!--Utente ha premuto il tasto salva per modificare il profilo-->
                            <section class="writearticle">
                                <p>Profilo aggiornato!!</p>
                                <h2 class="headerarticle"><strong>Profilo</strong></h2>
                                <form action="profilo.html" method="post"> 
                                    <img src="${profileToShow.urlProfImg}" alt="Immagine profilo" height="160" width="180">
                                    <label class="writearticle" for="name">Nome</label>
                                    <input class="writearticle" type="text" name="name" id="name" value="${profileToShow.name}"/>
                                    <br/><br/>
                                    <label class="writearticle" for="surname">Cognome</label>
                                    <input class="writearticle" type="text" name="surname" id="surname" value="${profileToShow.surname}"/>
                                    <br/><br/>
                                    <label class="writearticle" for="username">Username</label>
                                    <input class="writearticle" type="text" name="username" id="username" value="${profileToShow.username}"/>
                                    <br/><br/>
                                    <label class="writearticle" for="password">Password</label>
                                    <input class="writearticle" type="password" name="password" id="password" value="${profileToShow.password}"/>
                                    <br/><br/>
                                    <label class="writearticle" for="email">Email</label>
                                    <input class="writearticle" type="text" name="email" id="email" value="${profileToShow.email}"/>
                                    <br/><br/>
                                    <label class="writearticle" for="urlImmagine">Url immagine</label>
                                    <input class="writearticle" type="text" name="urlImmagine" id="urlImmagine" value="${profileToShow.urlProfImg}"/>
                                    <br/><br/>
                                    <button class="writearticle" type="submit" name="saveProfile" >Salva</button>
                                </form>
                            </section>
                        </c:when>
                        <c:otherwise>  <!--Utente visualizza il suo profilo-->
                            <section class="writearticle">
                                <h2 class="headerarticle"><strong>Profilo</strong></h2>
                                <form action="profilo.html" method="post">
                                    <img src="${user.urlProfImg}" alt="Immagine profilo" height="160" width="180">
                                    <label class="writearticle" for="name">Nome</label>
                                    <input class="writearticle" type="text" name="name" id="name" value="${user.name}"/>
                                    <br/><br/>
                                    <label class="writearticle" for="surname">Cognome</label>
                                    <input class="writearticle" type="text" name="surname" id="surname" value="${user.surname}"/>
                                    <br/><br/>
                                    <label class="writearticle" for="username">Username</label>
                                    <input class="writearticle" type="text" name="username" id="username" value="${user.username}"/>
                                    <br/><br/>
                                    <label class="writearticle" for="password">Password</label>
                                    <input class="writearticle" type="password" name="password" id="password" value="${user.password}"/>
                                    <br/><br/>
                                    <label class="writearticle" for="email">Email</label>
                                    <input class="writearticle" type="text" name="email" id="email" value="${user.email}"/>
                                    <br/><br/>
                                    <label class="writearticle" for="urlImmagine">Url immagine</label>
                                    <input class="writearticle" type="text" name="urlImmagine" id="urlImmagine" value="${user.urlProfImg}"/>
                                    <br/><br/>

                                    <button class="writearticle" type="submit" name="saveProfile" >Salva</button>
                                    <button class="writearticle" type="submit" name="deleteProfile">Elimina</button>
                                </form>
                            </section>
                        </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>
        </article>
        <!--Sezione footer con logo-->
        <jsp:include page="footer.jsp" />
    </body>
</html>
