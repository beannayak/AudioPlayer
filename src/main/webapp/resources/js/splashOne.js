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
        $scope.playNumber = 0;
        $scope.playingSongTitle = "";

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
                $scope.audio.src = "/AudioPlayerProject/api/getSong/" + $scope.songsList[0].location + ".mp3";
                $scope.playNumber = 0;
                $scope.play($scope.songsList[0].title);
                
            }).error(function (data, status, headers, config) {

            });
        };

        $scope.play = function (title) {
            $scope.audio.play();
            $scope.playing = true;
            $scope.playingSongTitle = title;
        };

        $scope.changeSrc = function (src, title) {
            $scope.audio.src = src;
            $scope.play(title);
            $scope.playAllSongs = false;
        };

        $scope.stop = function () {
            $scope.audio.pause();
            $scope.playing = false;
        };

        $scope.audio.addEventListener('ended', function () {
            $scope.$apply(function () {
                $scope.playNumber += 1;
                if ($scope.playAllSongs === true && $scope.songsList.length > $scope.playNumber){
                    $scope.audio.src = "/AudioPlayerProject/api/getSong/" + $scope.songsList[$scope.playNumber].location + ".mp3";
                    $scope.play($scope.songsList[$scope.playNumber].title);
                } else {
                    $scope.stop();
                }
            });
        });
    }]);