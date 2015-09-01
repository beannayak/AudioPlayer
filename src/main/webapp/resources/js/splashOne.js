var app = angular.module("linApp", []);

app.controller("SplashController", function ($scope, $rootScope) {
    $rootScope.splashVisible = true;
    $scope.myTimer = setInterval(function () {
        $rootScope.splashVisible = false;
        clearInterval($scope.myTimer);
        $scope.$apply($rootScope.splashVisible);
    }, 2000);
});

var appOne = angular.module("linAppPlayer", []);
appOne.controller("PlayerController", ['$scope', '$http', function ($scope, $http) {
        $scope.playing = true;
        $scope.audio = document.createElement('audio');
        $scope.audio.controls = "true";
        $scope.playAllSongs = false;
        $scope.songsList = [];

        var elem = angular.element('#audioController');
        elem.append($scope.audio);

        $scope.playAll = function () {
            $scope.playAllSongs = true;

            $http({
                url: "/AudioPlayerProject/api/getAllMusic",
                method: "GET",
            }).success(function (data, status, headers, config) {
                $scope.songsList = data;
                $scope.playAllSongs = true;
                $scope.audio.src = songsList[0].location;
                $scope.play()
                
            }).error(function (data, status, headers, config) {

            });
            //alert ($scope.songsList.length);
        };

        $scope.play = function () {
            $scope.audio.play();
            $scope.playing = true;
        };

        $scope.changeSrc = function (src) {
            $scope.audio.src = src;
            $scope.play();
        };

        $scope.stop = function () {
            $scope.audio.pause();
            $scope.playing = false;
        };

        $scope.audio.addEventListener('ended', function () {
            $scope.$apply(function () {
                $scope.stop();
            });
        });
    }]);