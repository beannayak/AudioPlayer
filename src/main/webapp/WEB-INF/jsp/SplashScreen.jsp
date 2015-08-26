<%-- 
    Document   : SplashScreen
    Created on : Aug 25, 2015, 5:03:06 PM
    Author     : binayak
--%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <c:url var="imgCss" value="/resources/css/splash.css"/>
        <link rel="stylesheet" type="text/css" href="${imgCss}">
        <title>Linamp</title>
    </head>
    
    <c:url var="imgUrl" value="/resources/img/background.jpg"/>
    <body style="background: url(${imgUrl})" id="imgDiv" ng-app="linApp">
        <div id="splashdiv" ng-controller="SplashController">
            <c:url var="imgLogo" value="/resources/img/appLogo.png" />
            <img id="logo" src="${imgLogo}"/>
            <div id="logoName">
                <h1>Linamp</h1>
            </div>
        </div>
    </body>
</html>
