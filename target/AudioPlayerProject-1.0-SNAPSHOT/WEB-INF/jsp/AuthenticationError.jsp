<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Authentication Error</title>
    </head>
    <body>
        <h1>Authentication Error Page</h1>
        <c:choose>
            <c:when test="${empty message}">
                <p>Bad Credentials</p>
            </c:when>
            <c:otherwise>
                <p>${message}</p>
            </c:otherwise>
        </c:choose>
    </body>
</html>
