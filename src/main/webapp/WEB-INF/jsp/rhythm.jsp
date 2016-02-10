<%-- 
    Document   : rhythm
    Created on : Feb 7, 2016, 5:04:27 PM
    Author     : binayak
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app="linAppPlayer">
    <head>
        <title>Next Try</title>
        
        <c:url var="baseUrl" value="/resources"/>
        <c:url var="baseHome" value="/" />
        <c:url var="logoutLink" value="${baseHome}j_spring_security_logout"/>
        
	<link rel="stylesheet" href="${baseUrl}/css/newStyles.css">
	<link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">
    </head>
    <body id="playerController" ng-controller="PlayerController">
	<div class="topbar">
		<div class="namebar">
			<div class="logo"><img src="${baseUrl}/img/logo.svg" /></div>
			<div id="PlayingSongName">
				code name: rhythm
			</div>
			<div id="userInfo">
				<a href="#">Welcome ${loggedInUser}</a>&nbsp;&nbsp;|&nbsp;&nbsp;
				<a href="${logoutLink}">Log out</a>
			</div>
		</div>
		<div class="playcontroler">
			<div class="playControls">
				<div class="playerbutton" ng-click="prevSong($event)"><i class="fa fa-fast-backward"></i></div>
				<div class="playbutton" ng-click="playSelected($event)"><i class="fa fa-play"></i></div>
				<div class="playerbutton" ng-click="nextSong($event)"><i class="fa fa-fast-forward"></i></div>
			</div>
			<div id="repeatButton" class="repeatButton" ng-click="changePlayFlow('repeat')"><i class="fa fa-compress"></i></div>
			<div id="shuffleButton" class="repeatButton" ng-click="changePlayFlow('shuffle')"><i class="fa fa fa-arrows-alt"></i></div>
			<div class="musicInfo" >
				<div class="musicIcon">
					<p class="fa fa-music"></p>
				</div>
				<div class="textInfo">
					<p id="playingSongName" class="header3 colorTitle">No Song Playing</p>
					<p id="playingSongArtistName" class="header6 colorSubtitle">By: ArtistName</p>
					<p id="playingSongAlbumName" class="header6 colorSubtitle">Album: AlbumName</p>
				</div>
			</div>
			<div class="seekBar">
				<div class="verticalAlign">
					<div id="timeProgress">0:00</div>
					<div class="progressBar">
						<div class="seeker">

						</div>
					</div>
					<div id="timeTotal">0:00</div>
				</div>
			</div>
		</div>
	</div>
	<div class="lists">
            <div class="hidden">
                <audio id="audioControl" controls="true"></audio>
            </div>
		<div class="mid-pos-inside-div">
			<div class="insidebar">
			</div>
			
			<div class="songsAndPlaylist">
                            <div class="side-bar-nav">
                                    <ul>
                                            <li class="topMenu clickableMenuItem"><div>Library</div>
                                            <li class="subMenu clickableMenuItem"><div>PlayQueue<div></li>
                                            <li class="subMenu clickableMenuItem" ng-click="changeSongsList(0, true)"><div>Musics<div></li>
                                            <li class="topMenu clickableMenuItem"><div>Playlists</div>
                                            <li class="subMenu clickableMenuItem"><div>Recently Played</div></li>
                                            <li class="subMenu clickableMenuItem"><div>Recently Addeded</div></li>
                                            <li class="subMenu clickableMenuItem"><div>Most Played</div></li>
                                            <li class="subMenu clickableMenuItem" ng-repeat="playlist in playlists" ng-click="changeSongsList(playlist.id, false)"><div>{{playlist.name}}</div></li>
                                    </ul>
                            </div>
                            <div class="side-bar-content">
                                <div class="songInfo" ng-repeat="song in songsList" ng-click="songClicked($index, $event)" ng-dblClick="playSong($index)" ng-right-click="rightClickHandler($index, $event)">
                                    <div class="selectable"> </div>
                                    <div class="albumArt nonSelectable">
                                        <img class="albumArtImage nonSelectable" ng-click="doNothing($index, $event)" ng-src="${baseHome}api/getImage/{{getImageNameFromLocation(song.location)}}.jpg" />
                                    </div>
                                    <div class="artistAlbumInfo">
                                        <p class="header1 nonSelectable" ng-click="doNothing($index, $event)" >Title: {{song.title}}</p>
                                        <p class="header4 nonSelectable" ng-click="doNothing($index, $event)">Artist: {{song.artist}}</p>
                                        <p class="header4 nonSelectable" ng-click="doNothing($index,$event)">Album: {{song.album}}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
			<div class="copyrightNotice">
				<p class="header4 text-middle">Linapp. Copyright © Linapp Co ltd. </p>
			</div>
		</div>
	</div>

	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.0/jquery.min.js"></script>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.9/jquery-ui.min.js"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js"></script>
        <script src="${baseUrl}/js/angular.js"></script>
        <script src="${baseUrl}/js/myJQuery.js"></script>
    </body>
</html>
