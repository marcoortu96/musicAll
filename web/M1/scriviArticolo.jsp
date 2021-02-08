<%-- 
    Document   : scriviArticolo
    Created on : 10-apr-2018, 9.27.09
    Author     : marcoortu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
    <head>
        <title>MusicAll-scrivi articolo</title>
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
        
        <!--Corpo della pagina scrviArticolo-->
        <article>
            <c:choose>
                <c:when test="${error == true}">
                    <p class="accessdeneid">Accesso negato!</p>
                </c:when>
                <c:otherwise>
                    <!--Utente preme il tasto salva articolo sia che sia nuovo o modificato-->
                    <c:choose> 
                        <c:when test="${articoloSalvato == true || articoloModificato == true}"> 
                            <section class="writearticle"> 
                                <h2 class="headerarticle"><strong>Scrivi articolo</strong></h2>
                                <form action="scriviArticolo.html?nid=${articleToShow.id}" method="post" >
                                    <c:if test="${flag == true}">
                                        <p>ID articolo: ${articleToShow.id}</p>
                                    </c:if>
                                    <label class="writearticle" for="title">Titolo</label>
                                    <input class="writearticle" type="text" name="title" id="title" value="${articleToShow.title}"/>
                                    <br/><br/>
                                    <label class="writearticle" for="data">Data</label>
                                    <input class="writearticle" type="date" name="data" id="data" value="${articleToShow.date}"/>
                                    <br/><br/>
                                    <label class="writearticle" for="urlImage">Url immagine</label>
                                    <input class="writearticle" type="url" name="urlImage" id="urlImage" value="${articleToShow.img}"/>
                                    <br/><br/>
                                    <label class="writearticle" for="didascalia">Didascalia</label>
                                    <input class="writearticle" type="text" name="didascalia" id="didascalia" value="${articleToShow.descrImg}"/>
                                    <br/><br/>
                                    <label class="writearticle" for="lungo">Testo</label>
                                    <textarea class="writearticle" rows="10" cols="33" name="lungo" id="lungo">${articleToShow.content}</textarea>
                                    <h4 class="categories">Categorie d'interesse</h4>
                                    <p class="categories">  
                                        <input class="categories" type="checkbox" name="categoria" value="Trap" ${arrayCategories[0]}> Trap
                                        <br/>
                                        <input class="categories" type="checkbox" name="categoria" value="Rap" ${arrayCategories[1]}> Rap
                                        <br/>
                                        <input class="categories" type="checkbox" name="categoria" value="Rock" ${arrayCategories[2]}> Rock
                                        <br/>
                                        <input class="categories" type="checkbox" name="categoria" value="Pop" ${arrayCategories[3]}> Pop
                                        <br/>
                                        <input class="categories" type="checkbox" name="categoria" value="Electronic" ${arrayCategories[4]}> Electronic
                                        <br/>
                                        <input class="categories" type="checkbox" name="categoria" value="Indie" ${arrayCategories[5]}> Indie
                                        <br/>
                                        <input class="categories" type="checkbox" name="categoria" value="Jazz" ${arrayCategories[6]}> Jazz
                                        <br/>
                                        <input class="categories" type="checkbox" name="categoria" value="Classica" ${arrayCategories[7]}> Classica
                                        <br/>
                                    </p>
                                    <button class="writearticle" type="submit" name="saveArticle" value="${articleToShow.id}">Salva</button>
                                </form>
                            </section> 
                        </c:when>
                        <c:when test="${newArt == true}"> <!-- ha premuto su nuovo articolo -->
                            <!--Seziopne per aggiungere tutte le informazioni per inserire un nuovo articolo-->
                            <section class="writearticle">
                                <h2 class="headerarticle"><strong>Scrivi articolo</strong></h2>
                                <form action="scriviArticolo.html" method="post">
                                    <label class="writearticle" for="title">Titolo</label>
                                    <input class="writearticle" type="text" name="title" id="title" value=""/>
                                    <br/><br/>
                                    <label class="writearticle" for="data">Data</label>
                                    <input class="writearticle" type="date" name="data" id="data" value=""/>
                                    <br/><br/>
                                    <label class="writearticle" for="urlImage">Url immagine</label>
                                    <input class="writearticle" type="url" name="urlImage" id="urlImage" value=""/>
                                    <br/><br/>
                                    <label class="writearticle" for="didascalia">Didascalia</label>
                                    <input class="writearticle" type="text" name="didascalia" id="didascalia" value=""/>
                                    <br/><br/>
                                    <label class="writearticle" for="lungo">Testo</label>
                                    <textarea class="writearticle" rows="10" cols="33" name="lungo" id="lungo"></textarea>
                                    <h4 class="categories">Categorie d'interesse</h4>
                                    <p class="categories">
                                        <input class="categories" type="checkbox" name="categoria" value="Trap"> Trap
                                        <br/>
                                        <input class="categories" type="checkbox" name="categoria" value="Rap"> Rap
                                        <br/>
                                        <input class="categories" type="checkbox" name="categoria" value="Rock"> Rock
                                        <br/>
                                        <input class="categories" type="checkbox" name="categoria" value="Pop"> Pop
                                        <br/>
                                        <input class="categories" type="checkbox" name="categoria" value="Electronic"> Electronic
                                        <br/>
                                        <input class="categories" type="checkbox" name="categoria" value="Indie"> Indie
                                        <br/>
                                        <input class="categories" type="checkbox" name="categoria" value="Jazz"> Jazz
                                        <br/>
                                        <input class="categories" type="checkbox" name="categoria" value="Classica"> Classica
                                        <br/>
                                    </p>
                                    <button class="writearticle" type="submit" name="saveNewArt">Salva</button>
                                </form>
                            </section>
                        </c:when>
                          
                    </c:choose>
                </c:otherwise>
            </c:choose>
        </article>
        <!--Sezione footer con logo-->
        <jsp:include page="footer.jsp" />
    </body>
</html>
