require.def("controllers/playerController", ["views/player",
                           "models/artists",
                           "models/songs",
                           "models/playlist",
                           "views/artistView",
                           "views/finderStateView",
                           "order!external/jquery-1.4.2.min",
                           "order!external/underscore-1.1.6.min",
                           "order!external/backbone-0.3.3.min"], function(Player, Artists, Songs, Playlist, ArtistView, FinderStateView) {

    var PlayerController = Backbone.Controller.extend({
        standardPollingDelay: 10000,    //10s
        maxPollingDelay: 720000,        //2h

        initialize: function(args) {

            _.bindAll(this, 'play', 'finderStatePolling', 'poll');

            this.bind('poll', this.finderStatePolling);

            this.player = new Player({model: new Playlist}).render();
            this.artistView = new ArtistView({model: new Artists});
            this.finderStateView = new FinderStateView;

            this.artistView.model.fetch({
                success: _.bind(function(data) {
                    $('#artistList').html(this.artistView.render().el);
                }, this)
            });

            new Songs().fetch({
                success: _.bind(function(data) {
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

        play: function() {
            this.player.play();
        },
        poll: function() {
            this.trigger('poll');
        }
    });

    return PlayerController;
});