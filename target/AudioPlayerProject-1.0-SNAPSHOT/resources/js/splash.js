var app= angular.module("linApp", []);

app.controller ("SplashController", function($scope, $rootScope) {
    $rootScope.splashVisible = true;
    $scope.myTimer =  setInterval (function (){
        $rootScope.splashVisible = false;
        clearInterval($scope.myTimer);
        $scope.$apply($rootScope.splashVisible);
    }, 2000);
});

var appOne = angular.module("linAppPlayer", []);
appOne.controller ("PlayerController", function ($scope, $rootScope){
	$scope.playing = false;
  	$scope.audio = document.createElement('audio');
  	$scope.audio.src = 'media/ab.mp3';

  	$scope.play = function() {
            $scope.audio.play();
            $scope.playing = true;
  	};

  	$scope.stop = function() {
            $scope.audio.pause();
            $scope.playing = false;
  	};

  	$scope.audio.addEventListener('ended', function() {
            $scope.$apply(function() {
            	$scope.stop();
            });
  	});
});