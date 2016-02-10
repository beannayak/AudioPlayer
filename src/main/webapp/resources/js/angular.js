var appOne = angular.module("linAppPlayer", []);

appOne.directive('ngRightClick', function($parse) {
    return function(scope, element, attrs) {
        var fn = $parse(attrs.ngRightClick);
        element.bind('contextmenu', function(event) {
            scope.$apply(function() {
                fn(scope, {$event:event});
            });
        });
    };
});

appOne.controller("PlayerController", ['$scope', '$http', function ($scope, $http) {
    $scope.songsList = [];
    $scope.selectedSong = 0;
    $scope.audio = document.getElementById("audioControl");
    $scope.audio.controls = "true";
    $scope.playing = false;
    $scope.playFlow = [];
    $scope.currentlyPlaying = NaN;
    $scope.previousSelected = null;
    $scope.playlists = [];

    $http({
        url: "/AudioPlayerProject/api/getAllPlaylists",
        method: "GET"
    }).success(function (data, status, headers, config) {
        $scope.playlists = data;
    }).error(function (data, status, headers, config) {

    });

    $("#repeatButton").addClass("playFlowSelected");
    $scope.changeSongsList = function (playlistId, allsongs) {
        var url = "/AudioPlayerProject/api/getSongsFromPlaylist?playlistId=" + playlistId;
        if (allsongs === true) {
            url = "/AudioPlayerProject/api/getAllSongs";
        }
        $scope.playFlow = [];
        $http({
            url: url,
            method: "GET"
        }).success(function (data, status, headers, config) {
            $scope.songsList = data;
            for (count=0; count<data.length; count++){
                $scope.playFlow.push(count);
            }
            $scope.changePlayFlow ("repeat");
        }).error(function (data, status, headers, config) {

        });
    };
    $scope.changeSongsList (0, true);

    $scope.getImageNameFromLocation = function (location) {
        var indexPos = "I" + location.substring(1);
        return indexPos;
    };
    
    $scope.select = function (indexPos, object) {
    	$scope.selectedSong = indexPos;
        if ($scope.previousSelected !== null) {
            angular.element(angular.element($scope.previousSelected).children()[0]).removeClass("selected")
        }
        angular.element(angular.element(object).children()[0]).addClass("selected")
        $scope.previousSelected = object;
    };

    $scope.songClicked = function (indexPos, obj) {
        $scope.select(indexPos, obj.target);
    };
    
    $scope.doNothing = function (indexPos, event){
        event.stopPropagation();
        $scope.select(indexPos, event.target.parentNode.parentNode);
    };
    
    $scope.playSong = function (indexPos) {
        $scope.selectedSong = indexPos;
        $scope.audio.src = "/AudioPlayerProject/api/getSong/" + $scope.songsList[$scope.selectedSong].location + ".mp3";
        $scope.playing = false;
        $scope.playSelected();
        for (count=0; count<=($scope.songsList.length - 1); count++){
            if ($scope.selectedSong === $scope.playFlow[count]) {
                $scope.currentlyPlaying = count;
                break;
            }
        }
        console.log ("funx: playSong, currentlyPlaying: " + $scope.currentlyPlaying);
        $("#playingSongName").html($scope.songsList[$scope.selectedSong].title);
        $("#playingSongArtistName").html("By: " + $scope.songsList[$scope.selectedSong].artist);
        $("#playingSongAlbumName").html("Album: " + $scope.songsList[$scope.selectedSong].album);
        $("title").html("Rhythm - " + $scope.songsList[$scope.selectedSong].title);
    };
    
    $scope.playSelected = function (event){
        if ($scope.currentlyPlaying === NaN){
            $scope.audio.src = "/AudioPlayerProject/api/getSong/" + $scope.songsList[$scope.selectedSong].location + ".mp3";
            $scope.playing = false;
        }
        if ($scope.playing) {
            $scope.audio.pause();
            $(".playbutton>i").removeClass("fa-pause");
            $(".playbutton>i").addClass("fa-play");
        } else {
            $scope.audio.play();
            $(".playbutton>i").removeClass("fa-play");
            $(".playbutton>i").addClass("fa-pause");
        }
        $scope.playing = !$scope.playing;
    };
    
    $scope.nextSong = function (event) {
        if (event !== null) {
            event.stopPropagation();
        }
        console.log($scope.currentlyPlaying);
        if ($scope.currentlyPlaying === ($scope.playFlow.length - 1)) {
            $scope.currentlyPlaying = -1;
        }
        $scope.playSong($scope.playFlow[$scope.currentlyPlaying + 1]);
    };
    
    $scope.prevSong = function (event) {
        event.stopPropagation();
        if ($scope.currentlyPlaying === 0) {
            $scope.currentlyPlaying = $scope.playFlow.length;
        }
        $scope.playSong($scope.playFlow[$scope.currentlyPlaying - 1]);
    };
    
    $scope.changePlayFlow = function (flow) {
        $("#repeatButton").removeClass("playFlowSelected");
        $("#shuffleButton").removeClass("playFlowSelected");
        if (flow === "repeat") {
            $("#repeatButton").addClass("playFlowSelected");
            $scope.currentlyPlaying = $scope.playFlow[$scope.currentlyPlaying];
            for (count=0; count<($scope.songsList.length); count++){
                $scope.playFlow[count] = count;
            }
        } else {
            $("#shuffleButton").addClass("playFlowSelected");
            for (count=0; count<($scope.songsList.length); count++){
                var random = $scope.getRandom(0, ($scope.songsList.length-1));
                if (count !== $scope.currentlyPlaying && random !== $scope.currentlyPlaying) {
                    $scope.swap ($scope.playFlow, count, random);
                }
            }
        }
        console.log($scope.playFlow);
    };
    
    $scope.songsOver = function () {
        console.log("fnx: songOver - Called");
        $scope.nextSong(null);
    };
    
    $scope.getRandom = function (from, to) {
        return Math.floor((Math.random() * (to - from)) + from); 
    };
    
    $scope.swap = function (array, posOne, posTwo) {
        var temp = array[posOne];
        array[posOne] = array[posTwo];
        array[posTwo] = temp;
    };
    
    $scope.rightClickHandler = function (index, event) {
        event.preventDefault();
        console.log("fnx: rightClickHandler - clicked on Index: " + index);
    };
}]);