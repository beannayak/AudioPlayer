$(init);

function init(){
	$(".seeker").draggable({
		containment: ".progressBar",
                start: handleDragStart,
		stop: handleDragStop,
	});

	seekerToPercent(0);
        var dragCancelled = false;

	var seekBarClickHandler = function ( event ) {
		var expectedLeft = event.pageX - 5;
		var startingX = $(".progressBar").offset().left;
		var percentCalculator = (expectedLeft - startingX) / ($(".progressBar").width()-9) * 100 ;
		seekerToPercent(percentCalculator);
                playAtPercent(percentCalculator);
	};
	$(".progressBar").click(seekBarClickHandler);

        function handleDragStart (event, ui) {
            dragging = true;
        }

	function handleDragStop(event, ui) {
            if (!dragCancelled) {
		var expectedLeft = $(".seeker").offset().left;
		var startingX = $(".progressBar").offset().left;
		var percentCalculator = (expectedLeft - startingX) / ($(".progressBar").width()-9) * 100 ;
                playAtPercent (percentCalculator);
            }
            dragCancelled = false;
            dragging = false;
            $( '.ui-draggable-dragging' ).draggable({'revert': false })
	}

	function seekerToBeginning(){
		var expectedLeft = $(".progressBar").offset().left;
		$(".seeker").offset ({left: expectedLeft});
	}

	function seekerToPercent(percent) {
		var expectedLeft = $(".progressBar").offset().left + ($(".progressBar").width()-10) * (percent/100);
                var expectedTop = $(".progressBar").offset().top;
		$(".seeker").offset ({left: expectedLeft, top: expectedTop});
	}
        
        var duration;
        var music = document.getElementById('audioControl');
        music.addEventListener("timeupdate", timeUpdate, false);
        var dragging = false;

        function timeUpdate() {
            if (dragging === false) {
                var playPercent = 100 * (music.currentTime / duration);
                seekerToPercent(playPercent);
                var seconds = Math.floor(music.currentTime % 60);
                if (seconds < 10) {
                    seconds = "0" + seconds;
                }
                $("#timeProgress").html(Math.floor(music.currentTime  / 60) + ":" + seconds);
                if (Math.abs(music.currentTime - duration) < 0.001) {
                    angular.element(document.getElementById('playerController')).scope().songsOver();
                }
            }
        };
        
        $(document).keyup( function( e ){
            e.preventDefault();
            console.log(':::keypress:::',e);
            if( e.which=== 27 || e.keyCode === 27 ){     
                dragCancelled = true;
                $( '.ui-draggable-dragging' ).draggable({'revert': true }).trigger( 'mouseup' );
                timeUpdate();
            }                 
        });

        music.addEventListener("canplaythrough", function () {
            duration = music.duration;
            $("#timeTotal").html(Math.floor(duration / 60) + ":" + Math.floor(duration % 60));
        }, false);
        
        function playAtPercent (percent) {
            music.currentTime = duration * (percent / 100);
        };
}