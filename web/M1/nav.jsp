<%-- 
    Document   : nav
    Created on : 10-apr-2018, 9.18.32
    Author     : marcoortu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="topnav">
    <ul class="topnav">
        <li class="topnav"><a href="notizie.html">Notizie</a></li>
        <!--Se l'utente è loggato allora vedrà il link "I miei articoli"-->
        <c:if test="${sessionScope['loggedIn'] == true}">
            <li class="topnav"><a href="articoli.html">I miei articoli</a></li>
        </c:if>
        <!--Se l'utente è loggato allora vedrà il link "Profilo"-->
        <c:if test="${sessionScope['loggedIn'] == true}">
            <li class="topnav"><a href="profilo.html">Profilo</a></li>
        </c:if>
        <c:choose>
            <c:when test="${sessionScope['loggedIn'] == true}"> <!--Se loggato può effetturare logout-->
                <li class="topnav">
                    <form method="post" action="login.html" id="logout">
                        <p class="topnav">Ciao ${utente.getName()},</p>  
                        <button type="submit" name="logout" id="logoutBtn" > Logout </button>
                    </form>
                </li>
            </c:when>
            <c:otherwise> <!-- non è loggato, mostro login -->
                <li class="topnav"><a href="login.html">Login</a></li>
            </c:otherwise>
        </c:choose>    
    </ul>
</nav>