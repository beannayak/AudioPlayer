<%-- 
    Document   : Player
    Created on : Aug 25, 2015, 8:49:32 PM
    Author     : binayak
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html ng-app="linAppPlayer">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <c:url var="baseUrl" value="/resources"/>
        <c:url var="baseHome" value="/" />
        <c:url var="logoutLink" value="${baseHome}j_spring_security_logout"/>

        <link rel="stylesheet" type="text/css" href="${baseUrl}/css/splash.css"> 
        <link rel="stylesheet" href="${baseUrl}/css/normalize.css">
        <link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>
        <link rel="stylesheet" href="${baseUrl}/css/style.css">
        <title>Linamp</title>
    </head>

    <body style="background: url(${baseUrl}/img/background.jpg); margin: 15px" id="imgDiv" ng-controller="PlayerController">
        <div id="header">
            <div id="logoDiv">
                <img style="margin: 8px; width: 140px; height: 80px" src="${baseUrl}/img/linapp.png" />
            </div>
            <div id="audioController"> 
                <p ng-show="playing">Currently playing: {{playingSongTitle}}</p>
            </div>
            <div id="userInfo">
                <p><a href='${logoutLink}' > Logout</a></p>
                <p><a href="${baseHome}player/saveSong" style="color: #ffffff" >AddSong</a></p>
            </div>
        </div>
        <div id="content">
            <div id="l_content"> 
                <p class="myALink"><a href="" ng-click="changePlaylist('${baseHome}api/getAllSongs')">Library</a></p>
                <p class="myALink"><a href="#">PlayLists</a></p>
                <div class="playlists">
                    <c:forEach var="playlist" items="${playlists}">
                        <p class="myALink"><a href="" ng-click="changePlaylist('${baseHome}api/getSongsFromPlaylist?playlistId=${playlist.id}')">> ${playlist.name}, ${playlist.id}</a></p>
                    </c:forEach>
                </div>
            </div>
            <div id="r_content"> 
                <div>
                    <button id="playAll" ng-click="playAll()">PlayAll</button>
                </div>
                <div id="songList" style="color: #ffffff">
                    <div class="songContainer" id="ramroo" ng-repeat="song in songsList">
                        <div id="songInfo">
                            <img class="albumArt" ng-src="${baseHome}api/getImage/{{getImageNameFromLocation(song.location)}}.jpg" />
                            <p>Title: {{song.title}}</p>
                            <p>Artist: {{song.artist}}</p>
                            <p>Album: {{song.album}}</p>
                        </div>
                        <div id="songToolbox" >
                            <p class="ng-binding"><a href="" class="ng-binding" ng-click="changeSrc(getConcatedLocation('${baseHome}api/getSong/', song.location, '.mp3'), song.title)">Play</a></p>
                            <p class="ng-binding"><a href="" class="ng-binding" ng-click="deleteSong(getConcatedLocation('${baseHome}api/deleteSong?songName=', song.location, ''), $event)" style="color: white">delete</a></p>
                            <p class="ng-binding"><a href="" class="ng-binding" ng-click="contextMenuPopUp(song.location)" style="color: white">...</a></p>
                        </div>
                        <div style="clear:both;"></div>
                    </div>
                </div>
            </div>
        </div>
        <div id="footer">
            <p>Linapp. Copyright &#169; Linapp Co ltd. </p>
        </div>

        <div id="jptDialog">
            <a ng-click="hideMe($event)" href="">Hide</a>
        </div>

        <link rel="stylesheet" href="${baseUrl}/css/sweetalert.css">

        <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
        <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js"></script>
        <script src="${baseUrl}/js/splashOne.js"></script>
        <script src="${baseUrl}/js/sweetalert.min.js"></script>        
    </body>
</html>
