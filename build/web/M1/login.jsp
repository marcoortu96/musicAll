<%-- 
    Document   : login
    Created on : 10-apr-2018, 9.52.05
    Author     : marcoortu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
    <head>
        <title>MusicAll-login</title>
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
        
        <!--Corpo della pagina login-->
        <article>
            <!--Sezione titolo e logo-->
            <section class="logo">
                <img src="M1/img/logoMA.png" alt="logo musicaAll" width="200" height="180">
                <h2 id="titlelogin">MusicAll</h2>
            </section>
            <!--Sezione inserimetno credenziali e bottone per accedere alla pagina notizia-->
            <section class="login">
                <h3 class="login">Login</h3>
                <form action="login.html" method="post">
                    <label class="login" for="nome">Nome</label>
                    <input class="login" type="text" name="nome" id="nome" placeholder="Inserisci username"/>
                    <br/><br/>
                    <label class="login" for="pswd">Password</label>
                    <input class="login" type="password" name="pswd" id="pswd" placeholder="password"/>
                    <br/>
                    <button type="submit" class="submitBtn">Accedi</button> 
                </form>
            </section>
        </article>

       <!--Sezione footer con logo-->
       <jsp:include page="footer.jsp" />
    </body>
</html>

