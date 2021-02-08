<%-- 
    Document   : filter
    Created on : 29-mag-2018, 10.32.35
    Author     : marcoortu
--%>

<%@page contentType="application/json" pageEncoding="UTF-8"%> 
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    
    <json:array>
          <c:forEach var="category" items="${categoryFound}">
            <json:object>
                <json:property name="categoria" value="${category}"/> 
            </json:object>
        </c:forEach>
        
        <c:forEach var="author" items="${userFound}">
            <json:object>
                <json:property name="id" value="${author.getId()}"/>
                <json:property name="name" value="${author.getName()}"/>
                <json:property name="surname" value="${author.getSurname()}"/>
                <json:property name="img" value="${author.getUrlProfImg()}"/>
            </json:object>
        </c:forEach>
    </json:array>  
