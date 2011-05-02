require(["views/playerView",
         "models/songs",
         "order!external/jquery-1.4.2.min",
         "order!external/projekktor-0.8.20.min",
         "order!external/speakker.min"], function(PlayerView, Songs) {

    $(document).ready(function(){

        var songs = new Songs;

        var playerView = new PlayerView({model: songs});

        var populatePlayList = function(data) {
           var newPlayList = data.map(function(element, index) {
                return media = {
                    0: {
                        src: '/song/' + element.get('id') + '.' + element.get('songType'),
                    },
                    config : {
                        title: element.get('title'),
                        poster: '/song/' + element.get('id') + '/album-image'
                    }
                };
            });

            $('.speakkerSmall').speakker({
                file: newPlayList,
                playlist: true,
                theme: 'light',
                playerFlashMP4: '/public/javascripts/external/swf/jarisplayer.swf',
                playerFlashMP3: '/public/javascripts/external/swf/jarisplayer.swf'
            });
        };

        songs.fetch({
            success: function(){
                populatePlayList(songs);

                $('#songList').append(playerView.render().el);
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