<%-- 
    Document   : aside
    Created on : 10-apr-2018, 9.32.07
    Author     : marcoortu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 <!--Sezione a margine della pagina-->
        <aside>
            <!--Sezione di ricerca-->
            <div class="cerca">
                <label class="cerca" for="search">Cerca</label>
                <input class="cerca" type="text" name="search" id="search" placeholder="Cerca..."/>
            </div>
            <hr>
            <!--Sezione per le categorie-->
            <nav class="verticalnav">
                <h4 class="asideheader">Categorie</h4>
                <!--utilizzato per il responsive-->
                <h4 class="linkheader"><a href="">Categorie</a></h4> 
                <ul class="verticalnav list">
                    <c:forEach var="c" items="${categoryList}">
                    <li class="verticalnav categorie"><a href="notizie.html?category=${c}">${c}</a></li>
                    
                    </c:forEach>
                </ul>
            </nav>
            
            <!--Sezione per gli autori-->
            <nav class="verticalnav">
                <h4 class="asideheader">Autori</h4>
                <!--utilizzato per il responsive-->
                <h4 class="linkheader"><a href="">Autori</a></h4>
                <ul class="verticalnav author">
                    <c:forEach var="n" items="${userList}">
                        <li class="verticalnav"><a href="notizie.html?author=${n.id}"><img src="${n.urlProfImg}" alt="img profilo" height="20" width="20">${n.name} ${n.surname}</a></li>
                    </c:forEach>
                </ul>
            </nav>
        </aside>
