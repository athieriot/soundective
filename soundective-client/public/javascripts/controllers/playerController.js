require.def("controllers/playerController", ["views/player",
                           "models/songs",
                           "models/playlist",
                           "views/songView",
                           "views/finderStateView",
                           "order!external/jquery-1.4.2.min",
                           "order!external/underscore-1.1.6.min",
                           "order!external/backbone-0.3.3.min"], function(Player, Songs, Playlist, SongView, FinderStateView) {

    var PlayerController = Backbone.Controller.extend({
        standardPollingDelay: 10000,    //10s
        maxPollingDelay: 720000,        //2h

        initialize: function(args) {

            _.bindAll(this, 'play', 'finderStatePolling', 'poll');

            this.bind('poll', this.finderStatePolling);

            this.player = new Player({model: new Playlist}).render('.speakkerSmall');
            this.songView = new SongView({model: new Songs});
            this.finderStateView = new FinderStateView;

            this.songView.model.fetch({
                success: _.bind(function(data) {
                    $('#songList').append(this.songView.render().el);

                    this.player.model.addSongs(data);
                }, this)
            });

            this.interval = setInterval(this.poll, this.standardPollingDelay);
        },

        routes: {
            "play": "play", // #play
            "poll": "poll"  // #poll
        },

        finderStatePolling: function() {
            this.finderStateView.model.fetch({
                success: _.bind(function(data) {
                    $('#state').html(this.finderStateView.render().el);

                    //TODO: Adapt with long polling?
                    if(this.finderStateView.model.get('status') == 'Terminated')
                        clearInterval(this.interval);
                        this.interval = setInterval(this.poll, this.maxPollingDelay);
                }, this)
            });
        },

        play: this.player.play,
        poll: function() {
            this.trigger('poll');
        }
    });

    return PlayerController;
});