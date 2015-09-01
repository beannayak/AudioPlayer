<%-- 
    Document   : Player
    Created on : Aug 25, 2015, 8:49:32 PM
    Author     : binayak
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app="linAppPlayer">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <c:url var="baseUrl" value="/resources"/>

        <link rel="stylesheet" type="text/css" href="${baseUrl}/css/splash.css"> 
        <link rel="stylesheet" href="${baseUrl}/css/normalize.css">
        <link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>
        <link rel="stylesheet" href="${baseUrl}/css/style.css">
        <title>Linamp</title>
    </head>

    <body style="background: url(${baseUrl}/img/background.jpg); margin: 15px" id="imgDiv" ng-controller="PlayerController">
        <c:url var="baseHome" value="/" />

        <!-- <div ng-controller="PlayerController">
            <button ng-click="play()" ng-show="!playing">Play</button>
            <button ng-click="stop()" ng-show="playing">Pause</button>
        </div>-->

        <div style="width: 100%">
            <table style="width: 100%">
                <tr>
                    <td style="width: 33%; text-align: left"><a href='<c:url value="${baseHome}j_spring_security_logout"/>' > Logout</a></td>
                    <td style="width: 33%; text-align: center"><a href="${baseHome}player/saveSong" style="color: #ffffff" >AddSong</a></td>
                    <td style="text-align: right"><a href="${baseHome}player/numberOfSongs" style="color: #ffffff" >Last Song Added Number</a></td>
                </tr>
            </table>

        </div>

        <div id="audioController" style="clear: both; text-align: center"> </div>
        <div>
            <button id="playAll" ng-click="playAll()">PlayAll</button>
        </div>
        <div style="color: #ffffff">
            <c:forEach var="song" items="${songs}">
                <div style="border: #c6d626 dashed medium; margin: 5px;">
                    <div style="display: block;">
                        <img style="margin: 5px; float: left; width: 90px; height: 90px" src="${baseHome}api/getImage/${fn:replace(song.location, 'S', 'I')}.jpg" />
                        <p>Title: ${song.title}</p>
                        <p>Artist: ${song.artist}</p>
                        <p>Album: ${song.album}</p>
                        <p>location: <button style="color: #000000" ng-click="changeSrc('${baseHome}api/getSong/${song.location}.mp3')">${song.location}</button></p>
                    </div>
                    <div style="float: right">
                        <p><a href="${baseHome}api/deleteSong?songName=${song.location}" style="color: white">delete This Song</a></p>
                    </div>
                </div>
            </c:forEach>
        </div>

        <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
        <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js"></script>
        <script src="${baseUrl}/js/splashOne.js"></script>
    </body>
</html>
