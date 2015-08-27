<%-- 
    Document   : Player
    Created on : Aug 25, 2015, 8:49:32 PM
    Author     : binayak
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <c:url var="baseUrl" value="/resources"/>
        
        <link rel="stylesheet" type="text/css" href="${baseUrl}/css/splash.css"> 
        <link rel="stylesheet" href="${baseUrl}/css/normalize.css">
        <link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>
        <link rel="stylesheet" href="${baseUrl}/css/style.css">
        <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
        <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js"></script>
        <script src="${baseUrl}/js/splashOne.js"></script>
        <title>Linamp</title>
    </head>

    <body style="background: url(${baseUrl}/img/background.jpg); margin: 15px" id="imgDiv" ng-app="linAppPlayer">
        <c:url var="baseHome" value="/" />
        
        <!-- <div ng-controller="PlayerController">
            <button ng-click="play()" ng-show="!playing">Play</button>
            <button ng-click="stop()" ng-show="playing">Pause</button>
        </div>-->
        
        <a href='<c:url value="${baseHome}j_spring_security_logout"/>' > Logout</a> <br />
        
        <a href="${baseHome}player/saveSong" style="color: #ffffff" >AddSong</a> <br />
        
        <a href="${baseHome}player/numberOfSongs" style="color: #ffffff" >Last Song Added Number</a> <br/>
        
        <div style="color: #ffffff">
            
            <c:forEach var="song" items="${songs}">
                <div style="border: #c6d626 dashed medium; margin: 5px">
                    <img style="margin: 5px; float: left; width: 85px; height: 85px" src="${baseHome}api/getImage/${fn:replace(song.location, 'S', 'I')}.jpg" />
                    <p>Title: ${song.title}</p>
                    <p>Artist: ${song.artist}</p>
                    <p>Album: ${song.album}</p>
                    <p>location: <a style="color: #ffffff" href="${baseHome}api/getSong/${song.location}.mp3">${song.location}</a></p>
                </div>
            </c:forEach>
        </div>
    </body>
</html>
