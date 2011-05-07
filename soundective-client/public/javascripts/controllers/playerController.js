require.def("controllers/playerController", ["views/player",
                           "models/songs",
                           "models/playlist",
                           "views/songView",
                           "views/finderStateView",
                           "order!external/jquery-1.4.2.min",
                           "order!external/underscore-1.1.6.min",
                           "order!external/backbone-0.3.3.min"], function(Player, Songs, Playlist, SongView, FinderStateView) {

    var PlayerController = Backbone.Controller.extend({
        pollingDelay: 10000,

        initialize: function(args) {

            _.bindAll(this, 'play', 'finderStatePolling');

            this.player = new Player({model: new Playlist}).render('.speakkerSmall');
            this.songView = new SongView({model: new Songs});
            this.finderStateView = new FinderStateView;

            this.songView.model.fetch({
                success: _.bind(function(data) {
                    $('#songList').append(this.songView.render().el);

                    this.player.model.addSongs(data);
                }, this)
            });

            setInterval(this.finderStatePolling, this.pollingDelay);
        },

        finderStatePolling: function() {
            this.finderStateView.model.fetch({
                success: _.bind(function(data) {
                    $('#state').html(this.finderStateView.render().el);
                }, this)
            });
        },

        routes: {
            "play": "play", // #play
        },

        play: function() {
            this.player.play();
        }
    });

    return PlayerController;
});