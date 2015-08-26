var app= angular.module("linApp", []);

app.controller ("SplashController", function($scope, $rootScope) {
    $rootScope.splashVisible = true;
    $scope.myTimer =  setInterval (function (){
        $rootScope.splashVisible = false;
        clearInterval($scope.myTimer);
        $scope.$apply($rootScope.splashVisible);
    }, 500);
});

app.controller("LoginController", function ($scope){
    
});