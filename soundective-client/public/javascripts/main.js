require(["views/player",
         "models/songs",
         "models/playlist",
         "views/songView",
         "order!external/jquery-1.4.2.min",
         "order!external/projekktor-0.8.20.min",
         "order!external/speakker.min"], function(Player, Songs, Playlist, SongView) {

    $(document).ready(function() {

        var player = new Player({model: new Playlist}).render('.speakkerSmall');

        var songView = new SongView({model: new Songs});


        songView.model.fetch({
            success: function(data) {
                $('#songList').append(songView.render().el);

                player.model.addSongs(data);
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