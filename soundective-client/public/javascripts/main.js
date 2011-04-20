require(["Player", "models/songs", "order!external/jquery-1.4.2.min", "order!external/jquery.jplayer-2.0.0.min"], function(Player, Songs) {

    $(document).ready(function(){

        var songs = new Songs;

        var populatePlayList = function(data) {
            var contentPlayList = data.map(function(element, index) {
                var media = {};
                media.name = element.get('title');
                media.free = true
                media[element.get('songType')] = "/song/" + element.get('id');

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

        songs.fetch({
            success: function(){
                populatePlayList(songs);
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