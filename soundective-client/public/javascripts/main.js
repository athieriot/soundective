require(["Player", "external/jquery-1.4.2.min", "external/jquery.jplayer-2.0.0.min"], function(Player) {

    //TODO: Warning to the JPlayer error when loading before jquery
    $(document).ready(function(){

        var populatePlayList = function(data) {
            var contentPlayList = $.map(data, function(element, index) {
                var media = {};
                media.name = element.title;
                media.free = true
                media[element.songType] = "/song/" + element.id;

                return media;
            });

            var audioPlaylist = new Player.PlayList("1", contentPlayList, {
                ready: function() {
                    audioPlaylist.displayPlaylist();
                    audioPlaylist.playlistInit(false); // Parameter is a boolean for autoplay.
                },
                ended: function() {
                    audioPlaylist.playlistNext();
                },
                play: function() {
                    $(this).jPlayer("pauseOthers");
                },
                swfPath: "/public/javascripts/external/jplayer/flash",
                supplied: "mp3"
            });
        }

        $.ajax({
          url: "/songs",
          dataType: "json",
          success: function(data) {
            populatePlayList(data);
          }
        });

        $("#state").click(function() {
            $.ajax({
              url: "/finderState",
              dataType: "json",
              success: function(data) {
                alert(data.files + " " + data.songs);
              }
            });
        });

    })
});