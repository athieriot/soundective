require.def("views/player", ["order!external/jquery-1.4.2.min",
                           "order!external/underscore-1.1.6.min",
                           "order!external/backbone-0.3.3.min"], function() {


    var self = null;

    var Player = Backbone.View.extend({

        playerTheme: 'light',
        flashPath: '/public/javascripts/external/swf/jarisplayer.swf',

        initialize: function (args) {
            self = this;
        },

        events: {
        },

        render: function (domElement) {
            //Speakker initialization
            self.instance = $(domElement).speakker({
                file: self.model.toJSON(),
                playlist: true,
                theme: self.playerTheme,
                playerFlashMP4: self.flashPath,
                playerFlashMP3: self.flashPath
            });

            return self;
        },

        addSong: function(song) {
            //TODO: Verification of existence
            self.model.addSong(song);
            return self;
        },

        addSongs: function(songs) {
            songs.forEach(function(song) {self.addSong(song);});
            return self;
        },

        changeTitle: function () {
        },

        handleTitleClick: function () {
        }
    });

    return Player;
});