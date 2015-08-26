<%-- 
    Document   : 403
    Created on : Aug 25, 2015, 8:37:20 PM
    Author     : binayak
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
    </head>
    <body>
        <c:url var="baseHome" value="/" />
        <div id="imgDiv">
            <img id="imgTag" src="${baseHome}resources/img/noPass.jpg" width="400px" height="400px"/><br />
            <label id="cautionText" >You don't have access to the page you requested</label>
        </div>
    </body>
</html>
