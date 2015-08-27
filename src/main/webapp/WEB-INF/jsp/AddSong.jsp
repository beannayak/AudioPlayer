<%-- 
    Document   : addSongs
    Created on : Aug 26, 2015, 2:29:48 PM
    Author     : binayak
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Linapp - AddSong</title>
    </head>
    <body>
        <c:url var="homeBase" value="/" />
        <c:url var="resourceBase" value="/resources/" />
        <form:form method="post" modelAttribute="addSong" enctype="multipart/form-data" action="${homeBase}player/saveSong">
            Title:
            <form:input path="title" type="text" /> 
            <form:errors path="title" /><br />
            
            Artist:
            <form:input path="artist" type="text" /> 
            <form:errors path="artist" /><br />
            
            Album:
            <form:input path="album" type="text" /> 
            <form:errors path="album" /><br />
            
            Song:
            <form:input path="song" type="file" /> 
            <form:errors path="song" /><br />
            
            Album Cover:
            <form:input path="albumCover" type="file" /> 
            <form:errors path="albumCover" /><br />
            
            <input type="submit" value="Submit" />
    </form:form>
</body>
</html>
