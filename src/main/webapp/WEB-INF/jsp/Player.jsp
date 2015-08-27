<%-- 
    Document   : Player
    Created on : Aug 25, 2015, 8:49:32 PM
    Author     : binayak
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <body style="background: url(${baseUrl}/img/background.jpg)" id="imgDiv" ng-app="linAppPlayer">
        <div ng-controller="PlayerController">
            <button ng-click="play()" ng-show="!playing">Play</button>
            <button ng-click="stop()" ng-show="playing">Pause</button>
        </div>
    </body>
</html>
