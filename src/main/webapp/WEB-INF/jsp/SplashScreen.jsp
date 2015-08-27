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
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <c:url var="baseUrl" value="/resources"/>
        <link rel="stylesheet" type="text/css" href="${baseUrl}/css/splash.css"> 
        <link rel="stylesheet" href="${baseUrl}/css/normalize.css">
        <link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>
        <link rel="stylesheet" href="${baseUrl}/css/style.css">
        <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js"></script>
        <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
        <script src="${baseUrl}/js/splash.js"></script>
        <title>Linamp</title>
    </head>

    <body style="background: url(${baseUrl}/img/background.jpg)" id="imgDiv" ng-app="linApp">
        <div id="splashDiv" ng-controller="SplashController" ng-show="splashVisible">
            <div id="insideDiv">
                <img id="logo" src="${baseUrl}/img/appLogo.png"/>
                <p id="logoText">LINAPP</p>
            </div>
        </div>
        <div id="loginDiv" ng-show="!splashVisible">
            <div class="logmod">
                <div class="logmod__wrapper">
                    <!--<span class="logmod__close">Close</span>-->
                    <div class="logmod__container">
                        <ul class="logmod__tabs">
                            <li data-tabtar="lgm-2"><a href="#">Login</a></li>
                            <li data-tabtar="lgm-1"><a href="#">Sign Up</a></li>
                        </ul>
                        <div class="logmod__tab-wrapper">
                            <div class="logmod__tab lgm-1">
                                <div class="logmod__heading">
                                    <span class="logmod__heading-subtitle">Enter your personal details <strong>to create an acount</strong></span>
                                </div>
                                <div class="logmod__form">
                                    <!-- SignUp form -->
                                    <form accept-charset="utf-8" action="#" class="simform">
                                        <div class="sminputs">
                                            <div class="input full">
                                                <label class="string optional" for="user-name">Username*</label>
                                                <input class="string optional" maxlength="255" id="user-email" placeholder="Username" type="email" size="50" />
                                            </div>
                                        </div>
                                        <div class="sminputs">
                                            <div class="input string optional">
                                                <label class="string optional" for="user-pw">Password *</label>
                                                <input class="string optional" maxlength="255" id="user-pw" placeholder="Password" type="text" size="50" />
                                            </div>
                                            <div class="input string optional">
                                                <label class="string optional" for="user-pw-repeat">Repeat password *</label>
                                                <input class="string optional" maxlength="255" id="user-pw-repeat" placeholder="Repeat password" type="text" size="50" />
                                            </div>
                                        </div>
                                        <div class="sminputs">
                                            <div class="input full">
                                                <label class="string optional">Invitation-Code*</label>
                                                <input class="string optional" maxlength="255" id="invitaiton-code" placeholder="Invitation Code" type="text" size="50" />
                                            </div>
                                        </div>
                                        <div class="simform__actions">
                                            <input class="sumbit" name="commit" type="sumbit" value="Create Account" />
                                            <span class="simform__actions-sidetext">By creating an account you agree to our <a class="special" href="#" target="_blank" role="link">Terms & Privacy</a></span>
                                        </div> 
                                    </form>
                                </div> 
                            </div>
                            <div class="logmod__tab lgm-2">
                                <div class="logmod__heading">
                                    <span class="logmod__heading-subtitle">Enter your username and password <strong>to sign in</strong></span>
                                </div> 
                                <div class="logmod__form">
                                    <!-- Login Form -->
                                    <c:url var="baseHome" value="/" />
                                    <form accept-charset="utf-8" action="${baseHome}j_spring_security_check" class="simform" method="POST">
                                        <div class="sminputs">
                                            <div class="input full">
                                                <label class="string optional" for="user-name">Username*</label>
                                                <input class="string optional" maxlength="255" id="user-email" placeholder="Username" type="text" size="50" name="j_username" />
                                            </div>
                                        </div>
                                        <div class="sminputs">
                                            <div class="input full">
                                                <label class="string optional" for="user-pw">Password *</label>
                                                <input class="string optional" maxlength="255" id="user-pw" placeholder="Password" type="password" size="50" name="j_password"/>
                                                <span class="hide-password">Show</span>
                                            </div>
                                        </div>
                                        <div class="simform__actions">
                                            <input class="sumbit" type="submit" value="Log In" />
                                            <!--                                            <span class="simform__actions-sidetext"><a class="special" role="link" href="#">Forgot your password?<br>Click here</a></span>-->
                                        </div> 
                                    </form>
                                </div> 
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="${baseUrl}/js/index.js"></script>
    </body>
</html>

